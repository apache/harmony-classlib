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
package tests.api.java.text;

import java.text.AttributedCharacterIterator;
import java.text.AttributedString;
import java.text.CharacterIterator;

public class AttributedStringTest extends junit.framework.TestCase {

	/**
	 * @tests java.text.AttributedString#AttributedString(java.lang.String)
	 */
	public void test_ConstructorLjava_lang_String() {
		String test = "Test string";
		AttributedString attrString = new AttributedString(test);
		AttributedCharacterIterator it = attrString.getIterator();
		StringBuffer buf = new StringBuffer();
		buf.append(it.first());
		char ch;
		while ((ch = it.next()) != CharacterIterator.DONE)
			buf.append(ch);
		assertTrue("Wrong string: " + buf, buf.toString().equals(test));
	}

	protected void setUp() {
	}

	protected void tearDown() {
	}
}
