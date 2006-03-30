/* Copyright 2005 The Apache Software Foundation or its licensors, as applicable
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

import junit.framework.TestCase;

public class FloatTest extends TestCase {

	/**
	 * @tests java.lang.Float#compareTo(java.lang.Float)
	 * @tests java.lang.Float#compare(float, float)
	 */
	public void test_compareToLjava_lang_Float() {
		// A selection of float values in ascending order.
		float[] values = new float[] { Float.NEGATIVE_INFINITY,
				-Float.MAX_VALUE, -2f, -Float.MIN_VALUE, -0f, 0f,
				Float.MIN_VALUE, 2f, Float.MAX_VALUE, Float.POSITIVE_INFINITY,
				Float.NaN };

		for (int i = 0; i < values.length; i++) {
			float f1 = values[i];

			// Test that each value compares equal to itself; and each object is equal to another object
			// like itself
			assertTrue("Assert 0: compare() should be equal: " + f1, Float
					.compare(f1, f1) == 0);
			Float objFloat = new Float(f1);
			assertTrue("Assert 1: compareTo() should be equal: " + objFloat,
					objFloat.compareTo(objFloat) == 0);

			// Test that the Float-defined order is respected
			for (int j = i + 1; j < values.length; j++) {
				float f2 = values[j];
				assertTrue("Assert 2: compare() " + f1 + " should be less " + f2,
						Float.compare(f1, f2) == -1);
				assertTrue("Assert 3: compare() " + f2 + " should be greater " + f1,
						Float.compare(f2, f1) == 1);

				Float F2 = new Float(f2);
				assertTrue("Assert 4: compareTo() " + f1 + " should be less " + f2,
						objFloat.compareTo(F2) == -1);
				assertTrue("Assert 5: compareTo() " + f2 + " should be greater " + f1,
						F2.compareTo(objFloat) == 1);
			}
		}
	}

	/**
	 * @tests java.lang.Float#equals(java.lang.Object)
	 */
	public void test_equalsLjava_lang_Object() {
		Float f1 = new Float(8765.4321f);
		Float f2 = new Float(8765.4321f);
		Float f3 = new Float(-1.0f);
		assertTrue("Assert 0: Equality test failed", f1.equals(f2)
				&& !(f1.equals(f3)));

		assertTrue("Assert 1: NaN should not be == Nan",
				Float.NaN != Float.NaN);
		assertTrue("Assert 2: NaN should not be == Nan",
				new Float(Float.NaN).equals(new Float(Float.NaN)));
		assertTrue("Assert 3: -0f should be == 0f",
				0f == -0f);
		assertTrue("Assert 4: -0f should not be equals() 0f",
				!new Float(0f).equals(new Float(-0f)));
	}
	    
	/**
	 * @tests java.lang.Float#toHexString(float)
	 */
	public void test_toHexStringF() 
	{
		//the follow values comes from the Float Javadoc/Spec
		assertEquals("0x0.0p0", Float.toHexString(0.0F));
		assertEquals("-0x0.0p0", Float.toHexString(-0.0F));
		assertEquals("0x1.0p0", Float.toHexString(1.0F));
		assertEquals("-0x1.0p0", Float.toHexString(-1.0F));
		assertEquals("0x1.0p1", Float.toHexString(2.0F));
		assertEquals("0x1.8p1", Float.toHexString(3.0F));
		assertEquals("0x1.0p-1", Float.toHexString(0.5F));
		assertEquals("0x1.0p-2", Float.toHexString(0.25F));
		assertEquals("0x1.fffffep127", Float.toHexString(Float.MAX_VALUE));
		assertEquals("0x0.000002p-126", Float.toHexString(Float.MIN_VALUE));
        
		//test edge cases
		assertEquals("NaN", Float.toHexString(Float.NaN));
		assertEquals("-Infinity", Float.toHexString(Float.NEGATIVE_INFINITY));
		assertEquals("Infinity", Float.toHexString(Float.POSITIVE_INFINITY));
        
		//test various numbers
		assertEquals("-0x1.da8p6", Float.toHexString(-118.625F));
		assertEquals("0x1.295788p23", Float.toHexString(9743299.65F));
		assertEquals("0x1.295788p23", Float.toHexString(9743299.65000F));
		assertEquals("0x1.295788p23", Float.toHexString(9743299.650001234F));
		assertEquals("0x1.700d1p33", Float.toHexString(12349743299.65000F));
	}
    
	/**
	 * @tests java.lang.Float#valueOf(float)
	 */
	public void test_valueOfF() 
	{
		assertEquals(new Float(Float.MIN_VALUE), Float.valueOf(Float.MIN_VALUE));
		assertEquals(new Float(Float.MAX_VALUE), Float.valueOf(Float.MAX_VALUE));
		assertEquals(new Float(0), Float.valueOf(0));

		int s = -128;
		while (s < 128) 
		{
			assertEquals(new Float(s), Float.valueOf(s));
			assertEquals(new Float(s + 0.1F), Float.valueOf(s + 0.1F));
			assertEquals(Float.valueOf(s + 0.1F), Float.valueOf(s + 0.1F));
			s++;
		}
	}
}
