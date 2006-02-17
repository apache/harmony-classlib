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

import java.text.MessageFormat;
import java.text.ParseException;
import java.util.Locale;

import junit.framework.TestCase;

public class MessageFormatTest extends TestCase {

	/**
	 * @tests java.text.MessageFormat(java.util.Locale)
	 */
	public void test_ConstructorLjava_util_Locale() {
		// Regression for HARMONY-65
		try {
			new MessageFormat("{0,number,integer", Locale.US);
			fail("Assert 0: Failed to detect unmatched brackets.");
		} catch (IllegalArgumentException e) {
			// expected
		}
	}

	/**
	 * @tests java.text.MessageFormat(java.lang.String)
	 */
	public void test_ConstructorLjava_lang_String() {
		// Regression for HARMONY-65
		try {
			new MessageFormat("{0,number,integer");
			fail("Assert 0: Failed to detect unmatched brackets.");
		} catch (IllegalArgumentException e) {
			// expected
		}
	}

	/**
	 * @tests java.text.MessageFormat#applyPattern(java.lang.String)
	 */
    public void test_applyPatternLjava_lang_String() {
		// Regression for HARMONY-65
		MessageFormat mf = new MessageFormat("{0,number,integer}");
		String badpattern = "{0,number,#";
		try {
			mf.applyPattern(badpattern);
			fail("Assert 0: Failed to detect unmatched brackets.");
		} catch (IllegalArgumentException e) {
			// expected
		}
	} 

	/**
	 * @tests java.text.MessageFormat#parse(java.lang.String)
	 */
	public void test_parse() throws ParseException {
		// Regression for HARMONY-63
		MessageFormat mf = new MessageFormat("{0,number,#,####}", Locale.US);
		Object[] res = mf.parse("1,00,00");
		assertEquals("Assert 0: incorrect size of parsed data ", 1, res.length);
		assertEquals("Assert 1: parsed value incorrectly", new Long(10000), (Long)res[0]);
	} 
    
	/**
	 * @tests java.text.MessageFormat#toPattern()
	 */
	public void test_toPattern() {
		// Regression for HARMONY-59
		new MessageFormat("CHOICE {1,choice}").toPattern();
	}
}
