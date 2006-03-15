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

package tests.api.java.util.regex;

import junit.framework.TestCase;
import java.util.regex.*;

/**
 * TODO Type description
 * 
 */
public class SplitTests extends TestCase {
	
	public void testSimple() {
		Pattern p = Pattern.compile("/");
		String[] results = p.split("have/you/done/it/right");
		String[] expected = new String[] { "have", "you", "done", "it", "right" };
		assertEquals(expected.length, results.length);
		for (int i = 0; i < expected.length; i++) {
			assertEquals(results[i], expected[i]);
		}
	}

	public void testSplit1() {
		Pattern p = null;

		// This set of tests comes from the O'Reilly NIO book, page 160
		try {
			p = Pattern.compile(" ");
		} catch (PatternSyntaxException e) {
			fail();
		}

		String input = "poodle zoo";
		String tokens[];

		tokens = p.split(input, 1);
		assertTrue(tokens.length == 1);
		assertTrue(tokens[0].equals(input));
		tokens = p.split(input, 2);
		assertTrue(tokens.length == 2);
		assertTrue(tokens[0].equals("poodle"));
		assertTrue(tokens[1].equals("zoo"));
		tokens = p.split(input, 5);
		assertTrue(tokens.length == 2);
		assertTrue(tokens[0].equals("poodle"));
		assertTrue(tokens[1].equals("zoo"));
		tokens = p.split(input, -2);
		assertTrue(tokens.length == 2);
		assertTrue(tokens[0].equals("poodle"));
		assertTrue(tokens[1].equals("zoo"));
		tokens = p.split(input, 0);
		assertTrue(tokens.length == 2);
		assertTrue(tokens[0].equals("poodle"));
		assertTrue(tokens[1].equals("zoo"));
		tokens = p.split(input);
		assertTrue(tokens.length == 2);
		assertTrue(tokens[0].equals("poodle"));
		assertTrue(tokens[1].equals("zoo"));

		try {
			p = Pattern.compile("d");
		} catch (PatternSyntaxException e) {
			fail();
			return;
		}

		tokens = p.split(input, 1);
		assertTrue(tokens.length == 1);
		assertTrue(tokens[0].equals(input));
		tokens = p.split(input, 2);
		assertTrue(tokens.length == 2);
		assertTrue(tokens[0].equals("poo"));
		assertTrue(tokens[1].equals("le zoo"));
		tokens = p.split(input, 5);
		assertTrue(tokens.length == 2);
		assertTrue(tokens[0].equals("poo"));
		assertTrue(tokens[1].equals("le zoo"));
		tokens = p.split(input, -2);
		assertTrue(tokens.length == 2);
		assertTrue(tokens[0].equals("poo"));
		assertTrue(tokens[1].equals("le zoo"));
		tokens = p.split(input, 0);
		assertTrue(tokens.length == 2);
		assertTrue(tokens[0].equals("poo"));
		assertTrue(tokens[1].equals("le zoo"));
		tokens = p.split(input);
		assertTrue(tokens.length == 2);
		assertTrue(tokens[0].equals("poo"));
		assertTrue(tokens[1].equals("le zoo"));

		try {
			p = Pattern.compile("o");
		} catch (PatternSyntaxException e) {
			fail();
		}

		tokens = p.split(input, 1);
		assertTrue(tokens.length == 1);
		assertTrue(tokens[0].equals(input));
		tokens = p.split(input, 2);
		assertTrue(tokens.length == 2);
		assertTrue(tokens[0].equals("p"));
		assertTrue(tokens[1].equals("odle zoo"));
		tokens = p.split(input, 5);
		assertTrue(tokens.length == 5);
		assertTrue(tokens[0].equals("p"));
		assertTrue(tokens[1].equals(""));
		assertTrue(tokens[2].equals("dle z"));
		assertTrue(tokens[3].equals(""));
		assertTrue(tokens[4].equals(""));
		tokens = p.split(input, -2);
		assertTrue(tokens.length == 5);
		assertTrue(tokens[0].equals("p"));
		assertTrue(tokens[1].equals(""));
		assertTrue(tokens[2].equals("dle z"));
		assertTrue(tokens[3].equals(""));
		assertTrue(tokens[4].equals(""));
		tokens = p.split(input, 0);
		assertTrue(tokens.length == 3);
		assertTrue(tokens[0].equals("p"));
		assertTrue(tokens[1].equals(""));
		assertTrue(tokens[2].equals("dle z"));
		tokens = p.split(input);
		assertTrue(tokens.length == 3);
		assertTrue(tokens[0].equals("p"));
		assertTrue(tokens[1].equals(""));
		assertTrue(tokens[2].equals("dle z"));
	}

	public void testSplit2() {
		Pattern p = Pattern.compile("");
		String s[];
		s = p.split("a", -1);
		assertEquals(3, s.length);
		assertEquals("", s[0]);
		assertEquals("a", s[1]);
		assertEquals("", s[2]);

		s = p.split("", -1);
		assertEquals(1, s.length);
		assertEquals("", s[0]);

		s = p.split("abcd", -1);
		assertEquals(6, s.length);
		assertEquals("", s[0]);
		assertEquals("a", s[1]);
		assertEquals("b", s[2]);
		assertEquals("c", s[3]);
		assertEquals("d", s[4]);
		assertEquals("", s[5]);

		// Match with a surrogate pair .. strangely splits the surrogate pair. I
		// would have expected
		// the third matched string to be "\ud869\uded6" (aka \u2a6d6)
		s = p.split("a\ud869\uded6b", -1);
		assertEquals(6, s.length);
		assertEquals("", s[0]);
		assertEquals("a", s[1]);
		assertEquals("\ud869", s[2]);
		assertEquals("\uded6", s[3]);
		assertEquals("b", s[4]);
		assertEquals("", s[5]);
	}
}
