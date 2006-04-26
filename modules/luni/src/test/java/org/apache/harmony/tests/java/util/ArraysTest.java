/*
 * Copyright 2006 The Apache Software Foundation or its licensors, as applicable
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */

package org.apache.harmony.tests.java.util;

import java.util.Arrays;

import junit.framework.TestCase;

public class ArraysTest extends TestCase {

	/**
	 * @tests java.util.Arrays#binarySearch(double[], double)
	 */
	public void test_binarySearch$DD() {
		double[] specials = new double[] { Double.NEGATIVE_INFINITY,
				-Double.MAX_VALUE, -2d, -Double.MIN_VALUE, -0d, 0d,
				Double.MIN_VALUE, 2d, Double.MAX_VALUE,
				Double.POSITIVE_INFINITY, Double.NaN };

		for (int i = 0; i < specials.length; i++) {
			int result = Arrays.binarySearch(specials, specials[i]);
			assertTrue("Assert 0: " + specials[i] + " invalid: " + result,
					result == i);
		}
		assertEquals("Assert 1: Invalid search index for -1d",
				-4, Arrays.binarySearch(specials, -1d));
		assertEquals("Assert 2: Invalid search index for 1d",
				-8, Arrays.binarySearch(specials, 1d));
	}
	
	/**
	 * @tests java.util.Arrays#binarySearch(float[], float)
	 */
	public void test_binarySearch$FF() {
		float[] specials = new float[] { Float.NEGATIVE_INFINITY,
				-Float.MAX_VALUE, -2f, -Float.MIN_VALUE, -0f, 0f,
				Float.MIN_VALUE, 2f, Float.MAX_VALUE, Float.POSITIVE_INFINITY,
				Float.NaN };

		for (int i = 0; i < specials.length; i++) {
			int result = Arrays.binarySearch(specials, specials[i]);
			assertTrue("Assert 0: " + specials[i] + " invalid: " + result,
					result == i);
		}
		assertEquals("Assert 1: Invalid search index for -1f",
				-4, Arrays.binarySearch(specials, -1f));
		assertEquals("Assert 2: Invalid search index for 1f",
				-8, Arrays.binarySearch(specials, 1f));
	}
	
	/**
	 * @tests java.util.Arrays#equals(double[], double[])
	 */
	public void test_equals$D$D() {
		double d[] = new double[100];
		double x[] = new double[100];
		Arrays.fill(d, Double.MAX_VALUE);
		Arrays.fill(x, Double.MIN_VALUE);

		assertTrue("Assert 0: Inequal arrays returned true", !Arrays.equals(d, x));

		Arrays.fill(x, Double.MAX_VALUE);
		assertTrue("Assert 1: equal arrays returned false", Arrays.equals(d, x));

		assertTrue("Assert 2: should be false",
				!Arrays.equals(new double[] { 1.0 }, new double[] { 2.0 }));

		assertTrue("Assert 3: NaN not equals",
				Arrays.equals(new double[] { Double.NaN }, new double[] { Double.NaN }));
		assertTrue("Assert 4: 0d equals -0d",
				!Arrays.equals(new double[] { 0d }, new double[] { -0d }));
	}
	
	/**
	 * @tests java.util.Arrays#equals(float[], float[])
	 */
	public void test_equals$F$F() {
		float d[] = new float[100];
		float x[] = new float[100];
		Arrays.fill(d, Float.MAX_VALUE);
		Arrays.fill(x, Float.MIN_VALUE);

		assertTrue("Assert 0: Inequal arrays returned true", !Arrays.equals(d, x));

		Arrays.fill(x, Float.MAX_VALUE);
		assertTrue("Assert 1: equal arrays returned false", Arrays.equals(d, x));

		assertTrue("Assert 2: NaN not equals",
				Arrays.equals(new float[] { Float.NaN }, new float[] { Float.NaN }));
		assertTrue("Assert 3: 0f equals -0f",
				!Arrays.equals(new float[] { 0f }, new float[] { -0f }));
	}
	
	/**
	 * @tests java.util.Arrays#sort(double[])
	 */
	public void test_sort$D() {
		// Test a basic sort
		double[] reversedArray = new double[100];
		for (int counter = 0; counter < reversedArray.length; counter ++)
			reversedArray[counter] = (double)(reversedArray.length - counter - 1);
		Arrays.sort(reversedArray);
		for (int counter = 0; counter < reversedArray.length; counter ++) {
			assertTrue("Assert 0: Resulting array not sorted",
					reversedArray[counter] == (double)counter);
		}

		// These have to sort as per the Double compare ordering
		double[] specials1 = new double[]{Double.NaN, Double.MAX_VALUE, Double.MIN_VALUE, 0d, -0d, Double.POSITIVE_INFINITY, Double.NEGATIVE_INFINITY};
		double[] specials2 = new double[]{0d, Double.POSITIVE_INFINITY, -0d, Double.NEGATIVE_INFINITY, Double.MIN_VALUE, Double.NaN, Double.MAX_VALUE};
		double[] answer = new double[]{Double.NEGATIVE_INFINITY, -0d, 0d, Double.MIN_VALUE, Double.MAX_VALUE, Double.POSITIVE_INFINITY, Double.NaN};

		Arrays.sort(specials1);
		Object[] print1 = new Object[specials1.length];
		for (int i = 0; i < specials1.length; i++) {
			print1[i] = new Double(specials1[i]);
		}
		assertTrue("Assert 1: specials sort incorrectly" + Arrays.asList(print1),
				Arrays.equals(specials1, answer));

		Arrays.sort(specials2);
		Object[] print2 = new Object[specials2.length];
		for (int i = 0; i < specials2.length; i++)
			print2[i] = new Double(specials2[i]);
		assertTrue("Assert 2: specials sort incorrectly " + Arrays.asList(print2), 
				Arrays.equals(specials2, answer));
	}
	
	/**
	 * @tests java.util.Arrays#sort(float[])
	 */
	public void test_sort$F() {
		// Test a basic sort
		float[] reversedArray = new float[100];
		for (int counter = 0; counter < reversedArray.length; counter ++) {
			reversedArray[counter] = (float)(reversedArray.length - counter - 1);
		}
		Arrays.sort(reversedArray);
		for (int counter = 0; counter < reversedArray.length; counter ++) {
			assertTrue("Assert 0: Resulting array not sorted",
					reversedArray[counter] == (float)counter);
		}

		float[] specials1 = new float[]{Float.NaN, Float.MAX_VALUE, Float.MIN_VALUE, 0f, -0f, Float.POSITIVE_INFINITY, Float.NEGATIVE_INFINITY};
		float[] specials2 = new float[]{0f, Float.POSITIVE_INFINITY, -0f, Float.NEGATIVE_INFINITY, Float.MIN_VALUE, Float.NaN, Float.MAX_VALUE};
		float[] answer = new float[]{Float.NEGATIVE_INFINITY, -0f, 0f, Float.MIN_VALUE, Float.MAX_VALUE, Float.POSITIVE_INFINITY, Float.NaN};

		Arrays.sort(specials1);
		Object[] print1 = new Object[specials1.length];
		for (int i = 0; i < specials1.length; i++) {
			print1[i] = new Float(specials1[i]);
		}
		assertTrue("Assert 1: specials sort incorrectly" + Arrays.asList(print1),
				Arrays.equals(specials1, answer));

		Arrays.sort(specials2);
		Object[] print2 = new Object[specials2.length];
		for (int i = 0; i < specials2.length; i++) {
			print2[i] = new Float(specials2[i]);
		}
		assertTrue("Assert 2: specials sort incorrectly" + Arrays.asList(print2), 
				Arrays.equals(specials2, answer));
	}
}
