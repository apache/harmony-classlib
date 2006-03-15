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

package tests.api.java.util;

import java.util.NoSuchElementException;
import java.util.StringTokenizer;

public class StringTokenizerTest extends junit.framework.TestCase {

	/**
	 * @tests java.util.StringTokenizer#StringTokenizer(java.lang.String)
	 */
	public void test_ConstructorLjava_lang_String() {
		// Test for method java.util.StringTokenizer(java.lang.String)
		assertTrue("Used in tests", true);
	}

	/**
	 * @tests java.util.StringTokenizer#StringTokenizer(java.lang.String,
	 *        java.lang.String)
	 */
	public void test_ConstructorLjava_lang_StringLjava_lang_String() {
		// Test for method java.util.StringTokenizer(java.lang.String,
		// java.lang.String)
		StringTokenizer st = new StringTokenizer("This:is:a:test:String", ":");
		assertTrue("Created incorrect tokenizer", st.countTokens() == 5
				&& (st.nextElement().equals("This")));
	}

	/**
	 * @tests java.util.StringTokenizer#StringTokenizer(java.lang.String,
	 *        java.lang.String, boolean)
	 */
	public void test_ConstructorLjava_lang_StringLjava_lang_StringZ() {
		// Test for method java.util.StringTokenizer(java.lang.String,
		// java.lang.String, boolean)
		StringTokenizer st = new StringTokenizer("This:is:a:test:String", ":",
				true);
		st.nextElement();
		assertTrue("Created incorrect tokenizer", st.countTokens() == 8
				&& (st.nextElement().equals(":")));
	}

	/**
	 * @tests java.util.StringTokenizer#countTokens()
	 */
	public void test_countTokens() {
		// Test for method int java.util.StringTokenizer.countTokens()
		StringTokenizer st = new StringTokenizer("This is a test String");

		assertTrue("Incorrect token count returned", st.countTokens() == 5);
	}

	/**
	 * @tests java.util.StringTokenizer#hasMoreElements()
	 */
	public void test_hasMoreElements() {
		// Test for method boolean java.util.StringTokenizer.hasMoreElements()

		StringTokenizer st = new StringTokenizer("This is a test String");
		st.nextElement();
		assertTrue("hasMoreElements returned incorrect value", st
				.hasMoreElements());
		st.nextElement();
		st.nextElement();
		st.nextElement();
		st.nextElement();
		assertTrue("hasMoreElements returned incorrect value", !st
				.hasMoreElements());
	}

	/**
	 * @tests java.util.StringTokenizer#hasMoreTokens()
	 */
	public void test_hasMoreTokens() {
		// Test for method boolean java.util.StringTokenizer.hasMoreTokens()
		StringTokenizer st = new StringTokenizer("This is a test String");
		for (int counter = 0; counter < 5; counter++) {
			assertTrue(
					"StringTokenizer incorrectly reports it has no more tokens",
					st.hasMoreTokens());
			st.nextToken();
		}
		assertTrue("StringTokenizer incorrectly reports it has more tokens",
				!st.hasMoreTokens());
	}

	/**
	 * @tests java.util.StringTokenizer#nextElement()
	 */
	public void test_nextElement() {
		// Test for method java.lang.Object
		// java.util.StringTokenizer.nextElement()
		StringTokenizer st = new StringTokenizer("This is a test String");
		assertTrue("nextElement returned incorrect value", ((String) st
				.nextElement()).equals("This"));
		assertTrue("nextElement returned incorrect value", ((String) st
				.nextElement()).equals("is"));
		assertTrue("nextElement returned incorrect value", ((String) st
				.nextElement()).equals("a"));
		assertTrue("nextElement returned incorrect value", ((String) st
				.nextElement()).equals("test"));
		assertTrue("nextElement returned incorrect value", ((String) st
				.nextElement()).equals("String"));
		try {
			st.nextElement();
			fail(
					"nextElement failed to throw a NoSuchElementException when it should have been out of elements");
		} catch (NoSuchElementException e) {
			return;
		}
	}

	/**
	 * @tests java.util.StringTokenizer#nextToken()
	 */
	public void test_nextToken() {
		// Test for method java.lang.String
		// java.util.StringTokenizer.nextToken()
		StringTokenizer st = new StringTokenizer("This is a test String");
		assertTrue("nextToken returned incorrect value", st.nextToken().equals(
				"This"));
		assertTrue("nextToken returned incorrect value", st.nextToken().equals(
				"is"));
		assertTrue("nextToken returned incorrect value", st.nextToken().equals(
				"a"));
		assertTrue("nextToken returned incorrect value", st.nextToken().equals(
				"test"));
		assertTrue("nextToken returned incorrect value", st.nextToken().equals(
				"String"));
		try {
			st.nextToken();
			fail(
					"nextToken failed to throw a NoSuchElementException when it should have been out of elements");
		} catch (NoSuchElementException e) {
			return;
		}
	}

	/**
	 * @tests java.util.StringTokenizer#nextToken(java.lang.String)
	 */
	public void test_nextTokenLjava_lang_String() {
		// Test for method java.lang.String
		// java.util.StringTokenizer.nextToken(java.lang.String)
		StringTokenizer st = new StringTokenizer("This is a test String");
		assertTrue(
				"nextToken(String) returned incorrect value with normal token String",
				st.nextToken(" ").equals("This"));
		assertTrue(
				"nextToken(String) returned incorrect value with custom token String",
				st.nextToken("tr").equals(" is a "));
		assertTrue(
				"calling nextToken() did not use the new default delimiter list",
				st.nextToken().equals("es"));
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
