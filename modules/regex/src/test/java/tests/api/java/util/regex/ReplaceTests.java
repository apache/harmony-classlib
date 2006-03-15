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
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.util.regex.PatternSyntaxException;

public class ReplaceTests extends TestCase {
	
	public void testSimpleReplace() {
		String target, pattern, repl, s;
		Pattern p = null;
		Matcher m;

		target = "foobarfobarfoofo1";
		pattern = "fo[^o]";
		repl = "xxx";
		try {
			p = Pattern.compile(pattern);
		} catch (PatternSyntaxException e) {
			System.out.println(e.getMessage());
			fail();
		}
		m = p.matcher(target);
		s = m.replaceFirst(repl);
		assertTrue(s.equals("foobarxxxarfoofo1"));
		s = m.replaceAll(repl);
		assertTrue(s.equals("foobarxxxarfooxxx"));
	}

	public void testCaptureReplace() {
		String target, pattern, repl, s;
		Pattern p = null;
		Matcher m;

		target = "[31]foo;bar[42];[99]xyz";
		pattern = "\\[([0-9]+)\\]([a-z]+)";
		repl = "$2[$1]";
		try {
			p = Pattern.compile(pattern);
		} catch (PatternSyntaxException e) {
			System.out.println(e.getMessage());
			fail();
		}
		m = p.matcher(target);
		s = m.replaceFirst(repl);
		assertTrue(s.equals("foo[31];bar[42];[99]xyz"));
		s = m.replaceAll(repl);
		assertTrue(s.equals("foo[31];bar[42];xyz[99]"));

		target = "[31]foo(42)bar{63}zoo;[12]abc(34)def{56}ghi;{99}xyz[88]xyz(77)xyz;";
		pattern = "\\[([0-9]+)\\]([a-z]+)\\(([0-9]+)\\)([a-z]+)\\{([0-9]+)\\}([a-z]+)";
		repl = "[$5]$6($3)$4{$1}$2";
		try {
			p = Pattern.compile(pattern);
		} catch (PatternSyntaxException e) {
			System.out.println(e.getMessage());
			fail();
		}
		m = p.matcher(target);
		s = m.replaceFirst(repl);
		// System.out.println(s);
		assertTrue(s
				.equals("[63]zoo(42)bar{31}foo;[12]abc(34)def{56}ghi;{99}xyz[88]xyz(77)xyz;"));
		s = m.replaceAll(repl);
		// System.out.println(s);
		assertTrue(s
				.equals("[63]zoo(42)bar{31}foo;[56]ghi(34)def{12}abc;{99}xyz[88]xyz(77)xyz;"));
	}

	public void testEscapeReplace() {
		String target, pattern, repl, s;

		target = "foo'bar''foo";
		pattern = "'";
		repl = "\\'";
		s = target.replaceAll(pattern, repl);
		assertTrue(s.equals("foo'bar''foo"));
		repl = "\\\\'";
		s = target.replaceAll(pattern, repl);
		assertTrue(s.equals("foo\\'bar\\'\\'foo"));
		repl = "\\$3";
		s = target.replaceAll(pattern, repl);
		assertTrue(s.equals("foo$3bar$3$3foo"));
	}
}
