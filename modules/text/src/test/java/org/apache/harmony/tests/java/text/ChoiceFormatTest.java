/* Copyright 2006 The Apache Software Foundation or its licensors, as applicable
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

package org.apache.harmony.tests.java.text;

import java.text.ChoiceFormat;

import junit.framework.TestCase;

public class ChoiceFormatTest extends TestCase {

	/**
	 * @tests java.text.ChoiceFormat#toPattern()
	 */
	public void test_toPattern() {
		// Regression for HARMONY-59
		ChoiceFormat cf = new ChoiceFormat("");
		assertEquals("", cf.toPattern());

		cf = new ChoiceFormat("-1#NEGATIVE_ONE|0#ZERO|1#ONE|1<GREATER_THAN_ONE");
		assertEquals("-1.0#NEGATIVE_ONE|0.0#ZERO|1.0#ONE|1.0<GREATER_THAN_ONE",
				cf.toPattern());
	}

	/**
	 * @tests java.text.ChoiceFormat#format(long)
	 */
	public void test_formatL() {
		ChoiceFormat fmt = new ChoiceFormat(
				"-1#NEGATIVE_ONE|0#ZERO|1#ONE|1<GREATER_THAN_ONE");

		assertEquals("NEGATIVE_ONE", fmt.format(Long.MIN_VALUE));
		assertEquals("NEGATIVE_ONE", fmt.format(-1));
		assertEquals("ZERO", fmt.format(0));
		assertEquals("ONE", fmt.format(1));
		assertEquals("GREATER_THAN_ONE", fmt.format(Long.MAX_VALUE));
	}
	
	/**
	 * @tests java.text.ChoiceFormat#format(double)
	 */
	public void test_formatD() {
		ChoiceFormat fmt = new ChoiceFormat(
				"-1#NEGATIVE_ONE|0#ZERO|1#ONE|1<GREATER_THAN_ONE");
		assertEquals("NEGATIVE_ONE", fmt.format(Double.NEGATIVE_INFINITY));
		assertEquals("NEGATIVE_ONE", fmt.format(-999999999D));
		assertEquals("NEGATIVE_ONE", fmt.format(-1.1));
		assertEquals("NEGATIVE_ONE", fmt.format(-1.0));
		assertEquals("NEGATIVE_ONE", fmt.format(-0.9));
		assertEquals("ZERO", fmt.format(0.0));
		assertEquals("ZERO", fmt.format(0.9));
		assertEquals("ONE", fmt.format(1.0));
		assertEquals("GREATER_THAN_ONE", fmt.format(1.1));
		assertEquals("GREATER_THAN_ONE", fmt.format(999999999D));
		assertEquals("GREATER_THAN_ONE", fmt.format(Double.POSITIVE_INFINITY));
	}
}
