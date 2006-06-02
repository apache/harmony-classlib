/* Copyright 1998, 2006 The Apache Software Foundation or its licensors, as applicable
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
package org.apache.harmony.text.tests.java.text;

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

    public void test_addAttributeLjava_text_AttributedCharacterIterator$AttributeLjava_lang_ObjectII() {
        AttributedString as = new AttributedString("test");
        as.addAttribute(AttributedCharacterIterator.Attribute.LANGUAGE, "a", 2,
                3);
        AttributedCharacterIterator it = as.getIterator();
        assertEquals("non-null value limit", 2, it
                .getRunLimit(AttributedCharacterIterator.Attribute.LANGUAGE));

        as = new AttributedString("test");
        as.addAttribute(AttributedCharacterIterator.Attribute.LANGUAGE, null,
                2, 3);
        it = as.getIterator();
        assertEquals("null value limit", 4, it
                .getRunLimit(AttributedCharacterIterator.Attribute.LANGUAGE));

        try {
            as = new AttributedString("test");
            as.addAttribute(AttributedCharacterIterator.Attribute.LANGUAGE,
                    null, -1, 3);
            fail("Expected IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            // Expected
        }
    }
}
