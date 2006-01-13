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

import java.text.CollationKey;
import java.text.ParseException;
import java.text.RuleBasedCollator;

import junit.framework.TestCase;

public class RuleBasedCollatorTest extends TestCase {

	public void test_getCollationKeyLjava_lang_String() {
		// Regression test for HARMONY-28
		String source = null;
		RuleBasedCollator rbc = null;
		try {
			String Simple = "< a< b< c< d";
			rbc = new RuleBasedCollator(Simple);
		} catch (ParseException e) {
			fail("Assert 0: Unexpected format exception " + e);
		}
		CollationKey ck = rbc.getCollationKey(source);
		assertNull("Assert 1: getCollationKey (null) does not return null", ck);
	}
}
