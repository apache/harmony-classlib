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
import java.util.regex.PatternSyntaxException;

/**
 * TODO Type description
 * 
 */
public class PatternSyntaxExceptionTest extends TestCase {
	public void testCase() {
		String regex = "(";
		try {
			Pattern.compile(regex);
            fail("PatternSyntaxException expected");
		} catch (PatternSyntaxException e) {
			assertEquals(e.getDescription(), "')' is expected.");
			// The reference JDK index:
			// assertTrue(e.getIndex() == 1);
			assertTrue(e.getIndex() == 0);
			assertEquals(e.getMessage(), "')' is expected. near index 0\r\n(\r\n^");
			assertEquals(e.getPattern(), regex);
		}
    }
    
    public void testCase2() {
		String regex = "[4-";
		try {
			Pattern.compile(regex);
            fail("PatternSyntaxException expected");
		} catch (PatternSyntaxException e) {
			assertEquals(e.getDescription(), 
					"Unexpected end of the pattern in a character class.");
			assertTrue(e.getIndex() == 3);
			assertEquals(e.getMessage(),
							"Unexpected end of the pattern in a character class. near index 3\r\n[4-\r\n   ^");
            assertEquals(e.getPattern(), regex);
		}
	}
}
