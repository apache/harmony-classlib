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

import java.util.Properties;

public class BooleanTest extends junit.framework.TestCase {

	Boolean t = new Boolean(true);

	Boolean t2 = new Boolean(true);

	Boolean f = new Boolean(false);

	/**
	 * @tests java.lang.Boolean#Boolean(java.lang.String)
	 */
	public void test_ConstructorLjava_lang_String() {
		// Test for method java.lang.Boolean(java.lang.String)
		Boolean truth = new Boolean("true");
		Boolean falsehood = new Boolean("false");
		Boolean bogus = new Boolean("bogus");
		Boolean nulled = new Boolean((String) null);

		assertTrue("Wrong value in Boolean", truth.booleanValue() == true);
		assertTrue("Wrong value in Boolean", falsehood.booleanValue() == false);
		assertTrue("Bogus boolean has wrong value", bogus.equals(f));
		assertTrue("Null boolean has wrong value", nulled.equals(f));
	}

	/**
	 * @tests java.lang.Boolean#Boolean(boolean)
	 */
	public void test_ConstructorZ() {
		// Test for method java.lang.Boolean(boolean)
		Boolean truth = new Boolean(true);
		Boolean falsehood = new Boolean(false);

		assertTrue("Wrong value in Boolean", truth.booleanValue() == true);
		assertTrue("Wrong value in Boolean", falsehood.booleanValue() == false);
		assertTrue("Constructed Boolean which doesn't equal.", truth.equals(t));
		assertTrue("Constructed Boolean which doesn't equal.", falsehood
				.equals(f));
	}

	/**
	 * @tests java.lang.Boolean#booleanValue()
	 */
	public void test_booleanValue() {
		// Test for method boolean java.lang.Boolean.booleanValue()
		assertTrue("Wrong value in Boolean", t.booleanValue() == true);
		assertTrue("Wrong value in Boolean", f.booleanValue() == false);
	}

	/**
	 * @tests java.lang.Boolean#equals(java.lang.Object)
	 */
	public void test_equalsLjava_lang_Object() {
		// Test for method boolean java.lang.Boolean.equals(java.lang.Object)
		assertTrue("Booleans not equal to itself", t.equals(t));
		assertTrue("Boolean not equal to boolean of same sense", t.equals(t2));
		assertTrue("Boolean equals boolean of opposite sense", !t.equals(f));
		assertTrue("Boolean equals random other object", !t
				.equals(new Object()));
		assertTrue("Boolean equals null", !t.equals((Object) null));
	}

	/**
	 * @tests java.lang.Boolean#getBoolean(java.lang.String)
	 */
	public void test_getBooleanLjava_lang_String() {
		// Test for method boolean
		// java.lang.Boolean.getBoolean(java.lang.String)
		Properties p = System.getProperties();
		p.put("Blah", "true");
		p.put("Blah2", "TRuE");
		p.put("Blah3", "yes");
		p.put("Blah4", "TRUE ");
		assertTrue("a) Should have returned true", Boolean.getBoolean("Blah"));
		assertTrue("b) Should have returned true", Boolean.getBoolean("Blah2"));
		assertTrue("c) Should have returned false", !Boolean
				.getBoolean("Blah3"));
		assertTrue("d) Should have returned false", !Boolean
				.getBoolean("Blah4"));
	}

	/**
	 * @tests java.lang.Boolean#hashCode()
	 */
	public void test_hashCode() {
		// Test for method int java.lang.Boolean.hashCode()

		// Known values. See comments in java.lang.Boolean.hashCode().
		assertTrue("Incorrect hash for true Boolean.", t.hashCode() == 1231);
		assertTrue("Incorrect hash for false Boolean.", f.hashCode() == 1237);
	}

	/**
	 * @tests java.lang.Boolean#toString()
	 */
	public void test_toString() {
		// Test for method java.lang.String java.lang.Boolean.toString()
		assertTrue("Boolean true value printed wrong.", t.toString().equals(
				"true"));
		assertTrue("Boolean false value printed wrong.", f.toString().equals(
				"false"));
	}

	/**
	 * @tests java.lang.Boolean#valueOf(java.lang.String)
	 */
	public void test_valueOfLjava_lang_String() {
		// Test for method java.lang.Boolean
		// java.lang.Boolean.valueOf(java.lang.String)
		assertTrue("Failed to parse true to true", Boolean.valueOf("true")
				.booleanValue());
		assertTrue("Failed to parse miked case true to true", Boolean.valueOf(
				"TrUe").booleanValue());
		assertTrue("parsed non-true to true", !Boolean.valueOf("ddddd")
				.booleanValue());
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
