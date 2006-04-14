/* Copyright 2004 The Apache Software Foundation or its licensors, as applicable
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

package tests.api.java.util.regex;

import junit.framework.TestCase;
import java.util.regex.*;

/**
 * Tests Matcher methods
 * 
 */
public class MatcherTest extends TestCase {
	public void test_toString() {
		Pattern p = Pattern.compile("foo");
		Matcher m = p.matcher("bar");
		assertTrue(Pattern.matches("java.util.regex.Matcher@[0-9a-fA-F]*", m
				.toString()));
	}

	public void testErrorConditions() throws PatternSyntaxException {
        // Test match cursors in absence of a match
		Pattern p = Pattern.compile("foo");
		Matcher m = p.matcher("bar");
		assertFalse(m.matches());

		try {
			m.start();
			fail("IllegalStateException expected");
		} catch (IllegalStateException e) {
		}

		try {
			m.end();
			fail("IllegalStateException expected");
		} catch (IllegalStateException e) {
		}

		try {
			m.group();
			fail("IllegalStateException expected");
		} catch (IllegalStateException e) {
		}

		try {
			m.start(1);
			fail("IllegalStateException expected");
		} catch (IllegalStateException e) {
		}

		try {
			m.end(1);
			fail("IllegalStateException expected");
		} catch (IllegalStateException e) {
		}

		try {
			m.group(1);
			fail("IllegalStateException expected");
		} catch (IllegalStateException e) {
		}
    }

    public void testErrorConditions2() throws PatternSyntaxException {
		// Test match cursors in absence of a match
        Pattern p = Pattern.compile("(foo[0-9])(bar[a-z])");
        Matcher m = p.matcher("foo1barzfoo2baryfoozbar5");

		assertTrue(m.find());
		assertTrue(m.start() == 0);
		assertTrue(m.end() == 8);
		assertTrue(m.start(1) == 0);
		assertTrue(m.end(1) == 4);
		assertTrue(m.start(2) == 4);
		assertTrue(m.end(2) == 8);

		try {
			m.start(3);
			fail("IndexOutOfBoundsException expected");
		} catch (IndexOutOfBoundsException e) {
		}

		try {
			m.end(3);
			fail("IndexOutOfBoundsException expected");
		} catch (IndexOutOfBoundsException e) {
		}

		try {
			m.group(3);
			fail("IndexOutOfBoundsException expected");
		} catch (IndexOutOfBoundsException e) {
		}

		try {
			m.start(-1);
			fail("IndexOutOfBoundsException expected");
		} catch (IndexOutOfBoundsException e) {
		}

		try {
			m.end(-1);
			fail("IndexOutOfBoundsException expected");
		} catch (IndexOutOfBoundsException e) {
		}

		try {
			m.group(-1);
			fail("IndexOutOfBoundsException expected");
		} catch (IndexOutOfBoundsException e) {
		}

		assertTrue(m.find());
		assertTrue(m.start() == 8);
		assertTrue(m.end() == 16);
		assertTrue(m.start(1) == 8);
		assertTrue(m.end(1) == 12);
		assertTrue(m.start(2) == 12);
		assertTrue(m.end(2) == 16);

		try {
			m.start(3);
			fail("IndexOutOfBoundsException expected");
		} catch (IndexOutOfBoundsException e) {
		}

		try {
			m.end(3);
			fail("IndexOutOfBoundsException expected");
		} catch (IndexOutOfBoundsException e) {
		}

		try {
			m.group(3);
			fail("IndexOutOfBoundsException expected");
		} catch (IndexOutOfBoundsException e) {
		}

		try {
			m.start(-1);
			fail("IndexOutOfBoundsException expected");
		} catch (IndexOutOfBoundsException e) {
		}

		try {
			m.end(-1);
			fail("IndexOutOfBoundsException expected");
		} catch (IndexOutOfBoundsException e) {
		}

		try {
			m.group(-1);
			fail("IndexOutOfBoundsException expected");
		} catch (IndexOutOfBoundsException e) {
		}

		assertFalse(m.find());

		try {
			m.start(3);
            fail("IllegalStateException expected");
		} catch (IllegalStateException e) {
		}

		try {
			m.end(3);
			fail("IllegalStateException expected");
		} catch (IllegalStateException e) {
		}

		try {
			m.group(3);
			fail("IllegalStateException expected");
		} catch (IllegalStateException e) {
		}

		try {
			m.start(-1);
			fail("IllegalStateException expected");
		} catch (IllegalStateException e) {
		}

		try {
			m.end(-1);
			fail("IllegalStateException expected");
		} catch (IllegalStateException e) {
		}

		try {
			m.group(-1);
			fail("IllegalStateException expected");
		} catch (IllegalStateException e) {
		}
	}
}

