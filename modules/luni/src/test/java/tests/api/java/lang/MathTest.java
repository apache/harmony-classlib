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
	 * @tests java.lang.Math#ceil(double)
	 */
	public void test_ceilD() {
		// Test for method double java.lang.Math.ceil(double)
		assertTrue("Incorrect ceiling for double", Math.ceil(78.89) == 79);
		assertTrue("Incorrect ceiling for double", Math.ceil(-78.89) == -78);
	}

	/**
	 * @tests java.lang.Math#cos(double)
	 */
	public void test_cosD() {
		// Test for method double java.lang.Math.cos(double)
		assertTrue("Incorrect answer", Math.cos(0) == 1.0);
		assertTrue("Incorrect answer", Math.cos(1) == 0.5403023058681398);
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
	 * @tests java.lang.Math#floor(double)
	 */
	public void test_floorD() {
		// Test for method double java.lang.Math.floor(double)
		assertTrue("Incorrect floor for double", Math.floor(78.89) == 78);
		assertTrue("Incorrect floor for double", Math.floor(-78.89) == -79);
	}

	/**
	 * @tests java.lang.Math#IEEEremainder(double, double)
	 */
	public void test_IEEEremainderDD() {
		// Test for method double java.lang.Math.IEEEremainder(double, double)
		assertTrue("Incorrect remainder returned",
				Math.IEEEremainder(1.0, 1.0) == 0.0);
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
	 * @tests java.lang.Math#max(double, double)
	 */
	public void test_maxDD() {
		// Test for method double java.lang.Math.max(double, double)
		assertTrue("Incorrect double max value", Math.max(-1908897.6000089,
				1908897.6000089) == 1908897.6000089);
		assertTrue("Incorrect double max value",
				Math.max(2.0, 1908897.6000089) == 1908897.6000089);
		assertTrue("Incorrect double max value", Math.max(-2.0,
				-1908897.6000089) == -2.0);

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
		assertTrue("Incorrect int max value",
				Math.max(-19088976, 19088976) == 19088976);
		assertTrue("Incorrect int max value",
				Math.max(20, 19088976) == 19088976);
		assertTrue("Incorrect int max value", Math.max(-20, -19088976) == -20);
	}

	/**
	 * @tests java.lang.Math#max(long, long)
	 */
	public void test_maxJJ() {
		// Test for method long java.lang.Math.max(long, long)
		assertTrue("Incorrect long max value", Math.max(-19088976000089L,
				19088976000089L) == 19088976000089L);
		assertTrue("Incorrect long max value",
				Math.max(20, 19088976000089L) == 19088976000089L);
		assertTrue("Incorrect long max value",
				Math.max(-20, -19088976000089L) == -20);
	}

	/**
	 * @tests java.lang.Math#min(double, double)
	 */
	public void test_minDD() {
		// Test for method double java.lang.Math.min(double, double)
		assertTrue("Incorrect double min value", Math.min(-1908897.6000089,
				1908897.6000089) == -1908897.6000089);
		assertTrue("Incorrect double min value",
				Math.min(2.0, 1908897.6000089) == 2.0);
		assertTrue("Incorrect double min value", Math.min(-2.0,
				-1908897.6000089) == -1908897.6000089);
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
		assertTrue("Incorrect int min value",
				Math.min(-19088976, 19088976) == -19088976);
		assertTrue("Incorrect int min value", Math.min(20, 19088976) == 20);
		assertTrue("Incorrect int min value",
				Math.min(-20, -19088976) == -19088976);

	}

	/**
	 * @tests java.lang.Math#min(long, long)
	 */
	public void test_minJJ() {
		// Test for method long java.lang.Math.min(long, long)
		assertTrue("Incorrect long min value", Math.min(-19088976000089L,
				19088976000089L) == -19088976000089L);
		assertTrue("Incorrect long min value",
				Math.min(20, 19088976000089L) == 20);
		assertTrue("Incorrect long min value",
				Math.min(-20, -19088976000089L) == -19088976000089L);
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
		assertTrue("Incorrect root returned1", Math.sqrt(Math.pow(Math.sqrt(2),
				4)) == 2);
	}

	/**
	 * @tests java.lang.Math#rint(double)
	 */
	public void test_rintD() {
		// Test for method double java.lang.Math.rint(double)
		assertTrue("Failed to round properly - up to odd",
				Math.rint(2.9) == 3.0);
		assertTrue("Failed to round properly - NaN", Double.isNaN(Math
				.rint(Double.NaN)));
		assertTrue("Failed to round properly down  to even",
				Math.rint(2.1) == 2.0);
		assertTrue("Failed to round properly " + 2.5 + " to even", Math
				.rint(2.5) == 2.0);
	}

	/**
	 * @tests java.lang.Math#round(double)
	 */
	public void test_roundD() {
		// Test for method long java.lang.Math.round(double)
		assertTrue("Incorrect rounding of a float", Math.round(-90.89d) == -91);
	}

	/**
	 * @tests java.lang.Math#round(float)
	 */
	public void test_roundF() {
		// Test for method int java.lang.Math.round(float)
		assertTrue("Incorrect rounding of a float", Math.round(-90.89f) == -91);
	}

	/**
	 * @tests java.lang.Math#sin(double)
	 */
	public void test_sinD() {
		// Test for method double java.lang.Math.sin(double)
		assertTrue("Incorrect answer", Math.sin(0) == 0.0);
		assertTrue("Incorrect answer", Math.sin(1) == 0.8414709848078965);
	}

	/**
	 * @tests java.lang.Math#sqrt(double)
	 */
	public void test_sqrtD() {
		// Test for method double java.lang.Math.sqrt(double)
		assertTrue("Incorrect root returned2", Math.sqrt(49) == 7);
	}

	/**
	 * @tests java.lang.Math#tan(double)
	 */
	public void test_tanD() {
		// Test for method double java.lang.Math.tan(double)
		assertTrue("Incorrect answer", Math.tan(0) == 0.0);
		assertTrue("Incorrect answer", Math.tan(1) == 1.5574077246549023);

	}

	/**
	 * @tests java.lang.Math#random()
	 */
	public void test_random() {
		// There isn't a place for these tests so just stick them here
		assertTrue("Wrong value E",
				Double.doubleToLongBits(Math.E) == 4613303445314885481L);
		assertTrue("Wrong value PI",
				Double.doubleToLongBits(Math.PI) == 4614256656552045848L);

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
