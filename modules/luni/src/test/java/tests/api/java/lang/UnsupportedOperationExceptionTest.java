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

import java.util.AbstractCollection;
import java.util.Iterator;

public class UnsupportedOperationExceptionTest extends junit.framework.TestCase {

	/**
	 * @tests java.lang.UnsupportedOperationException#UnsupportedOperationException()
	 */
	public void test_Constructor() {
		// Test for method java.lang.UnsupportedOperationException()
		class UnsupportedCollection extends AbstractCollection {
			public int size() {
				return 0;
			}

			public Iterator iterator() {
				return null;
			}
		}
		try {
			UnsupportedCollection uc = new UnsupportedCollection();
			uc.add(new Object());
		} catch (UnsupportedOperationException e) {
			return;
		} catch (Exception e) {
			fail("Exception during Constructor : " + e.getMessage());
		}
		fail("Constructor failed.");
	}

	/**
	 * @tests java.lang.UnsupportedOperationException#UnsupportedOperationException(java.lang.String)
	 */
	public void test_ConstructorLjava_lang_String() {
		// Test for method
		// java.lang.UnsupportedOperationException(java.lang.String)
		try {
			throw new UnsupportedOperationException("HelloWorld");
		} catch (UnsupportedOperationException e) {
			assertTrue("Wrong message given.", e.getMessage().equals(
					"HelloWorld"));
			return;
		} catch (Exception e) {
			fail("Exception during Constructor : " + e.getMessage());
		}
		fail("Constructor failed");
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
