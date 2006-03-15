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
public class MatcherTests extends TestCase {
	public void test_toString() {
		Pattern p = Pattern.compile("foo");
		Matcher m = p.matcher("bar");
		assertTrue(Pattern.matches("java.util.regex.Matcher@[0-9a-fA-F]*", m
				.toString()));
	}

	public void testErrorConditions() {
		Pattern p;
		Matcher m;
		boolean errFound;
		try {
			// Test match cursors in absence of a match
			p = Pattern.compile("foo");
			m = p.matcher("bar");
			assertFalse(m.matches());

			errFound = false;
			try {
				m.start();
			} catch (IllegalStateException e) {
				errFound = true;
			}
			assertTrue(errFound);

			errFound = false;
			try {
				m.end();
			} catch (IllegalStateException e) {
				errFound = true;
			}
			assertTrue(errFound);

			errFound = false;
			try {
				m.group();
			} catch (IllegalStateException e) {
				errFound = true;
			}
			assertTrue(errFound);

			errFound = false;
			try {
				m.start(1);
			} catch (IllegalStateException e) {
				errFound = true;
			}
			assertTrue(errFound);

			errFound = false;
			try {
				m.end(1);
			} catch (IllegalStateException e) {
				errFound = true;
			}
			assertTrue(errFound);

			errFound = false;
			try {
				m.group(1);
			} catch (IllegalStateException e) {
				errFound = true;
			}
			assertTrue(errFound);

			// Test match cursors in absence of a match
			p = Pattern.compile("(foo[0-9])(bar[a-z])");
			m = p.matcher("foo1barzfoo2baryfoozbar5");

			assertTrue(m.find());
			assertTrue(m.start() == 0);
			assertTrue(m.end() == 8);
			assertTrue(m.start(1) == 0);
			assertTrue(m.end(1) == 4);
			assertTrue(m.start(2) == 4);
			assertTrue(m.end(2) == 8);
			errFound = false;
			try {
				m.start(3);
			} catch (IndexOutOfBoundsException e) {
				errFound = true;
			}
			assertTrue(errFound);
			errFound = false;
			try {
				m.end(3);
			} catch (IndexOutOfBoundsException e) {
				errFound = true;
			}
			assertTrue(errFound);
			errFound = false;
			try {
				m.group(3);
			} catch (IndexOutOfBoundsException e) {
				errFound = true;
			}
			assertTrue(errFound);
			errFound = false;
			try {
				m.start(-1);
			} catch (IndexOutOfBoundsException e) {
				errFound = true;
			}
			assertTrue(errFound);
			errFound = false;
			try {
				m.end(-1);
			} catch (IndexOutOfBoundsException e) {
				errFound = true;
			}
			assertTrue(errFound);
			errFound = false;
			try {
				m.group(-1);
			} catch (IndexOutOfBoundsException e) {
				errFound = true;
			}
			assertTrue(errFound);

			assertTrue(m.find());
			assertTrue(m.start() == 8);
			assertTrue(m.end() == 16);
			assertTrue(m.start(1) == 8);
			assertTrue(m.end(1) == 12);
			assertTrue(m.start(2) == 12);
			assertTrue(m.end(2) == 16);
			errFound = false;
			try {
				m.start(3);
			} catch (IndexOutOfBoundsException e) {
				errFound = true;
			}
			assertTrue(errFound);
			errFound = false;
			try {
				m.end(3);
			} catch (IndexOutOfBoundsException e) {
				errFound = true;
			}
			assertTrue(errFound);
			errFound = false;
			try {
				m.group(3);
			} catch (IndexOutOfBoundsException e) {
				errFound = true;
			}
			assertTrue(errFound);
			errFound = false;
			try {
				m.start(-1);
			} catch (IndexOutOfBoundsException e) {
				errFound = true;
			}
			assertTrue(errFound);
			errFound = false;
			try {
				m.end(-1);
			} catch (IndexOutOfBoundsException e) {
				errFound = true;
			}
			assertTrue(errFound);
			errFound = false;
			try {
				m.group(-1);
			} catch (IndexOutOfBoundsException e) {
				errFound = true;
			}
			assertTrue(errFound);

			assertFalse(m.find());
			errFound = false;
			try {
				m.start(3);
			} catch (IllegalStateException e) {
				errFound = true;
			}
			assertTrue(errFound);

			errFound = false;
			try {
				m.end(3);
			} catch (IllegalStateException e) {
				errFound = true;
			}
			assertTrue(errFound);

			errFound = false;
			try {
				m.group(3);
			} catch (IllegalStateException e) {
				errFound = true;
			}
			assertTrue(errFound);

			errFound = false;
			try {
				m.start(-1);
			} catch (IllegalStateException e) {
				errFound = true;
			}
			assertTrue(errFound);

			errFound = false;
			try {
				m.end(-1);
			} catch (IllegalStateException e) {
				errFound = true;
			}
			assertTrue(errFound);

			errFound = false;
			try {
				m.group(-1);
			} catch (IllegalStateException e) {
				errFound = true;
			}
			assertTrue(errFound);
		} catch (PatternSyntaxException e) {
			fail();
		}
	}
}

