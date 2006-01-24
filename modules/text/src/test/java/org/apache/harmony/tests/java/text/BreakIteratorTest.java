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

package org.apache.harmony.tests.java.text;

import java.text.BreakIterator;
import java.util.Locale;

import junit.framework.TestCase;

public class BreakIteratorTest extends TestCase {

	public void test_next() {
		// Regression test for HARMONY-30
		BreakIterator bi = BreakIterator.getWordInstance(Locale.US);
		bi.setText("This is the test, WordInstance");
		int n = bi.first();
		n = bi.next();
		assertEquals("Assert 0: next() returns incorrect value ", 4, n);
	}
}
