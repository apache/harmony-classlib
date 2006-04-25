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

package org.apache.harmony.text.tests.java.text;

import java.text.AttributedCharacterIterator;
import java.text.AttributedString;
import java.text.CharacterIterator;

public class AttributedCharacterIteratorTest extends junit.framework.TestCase {

	/**
	 * @tests java.text.AttributedCharacterIterator#current()
	 */
	public void test_current() {
		String test = "Test 23ring";
		AttributedString attrString = new AttributedString(test);
		AttributedCharacterIterator it = attrString.getIterator();
		assertTrue("Wrong first", it.current() == 'T');
		it.next();
		assertTrue("Wrong second", it.current() == 'e');
		for (int i = 0; i < 9; i++)
			it.next();
		assertTrue("Wrong last", it.current() == 'g');
		it.next();
		assertTrue("Wrong final", it.current() == CharacterIterator.DONE);

		it = attrString.getIterator(null, 2, 8);
		assertTrue("Wrong first2", it.current() == 's');
	}

	/**
	 * @tests java.text.AttributedCharacterIterator#first()
	 */
	public void test_first() {
		String test = "Test 23ring";
		AttributedString attrString = new AttributedString(test);
		AttributedCharacterIterator it = attrString.getIterator();
		assertTrue("Wrong first1", it.first() == 'T');
		it = attrString.getIterator(null, 0, 3);
		assertTrue("Wrong first2", it.first() == 'T');
		it = attrString.getIterator(null, 2, 8);
		assertTrue("Wrong first3", it.first() == 's');
		it = attrString.getIterator(null, 11, 11);
		assertTrue("Wrong first4", it.first() == CharacterIterator.DONE);
	}

	/**
	 * @tests java.text.AttributedCharacterIterator#getBeginIndex()
	 */
	public void test_getBeginIndex() {
		String test = "Test 23ring";
		AttributedString attrString = new AttributedString(test);
		AttributedCharacterIterator it = attrString.getIterator(null, 2, 6);
		assertTrue("Wrong begin index", it.getBeginIndex() == 2);
	}

	/**
	 * @tests java.text.AttributedCharacterIterator#getEndIndex()
	 */
	public void test_getEndIndex() {
		String test = "Test 23ring";
		AttributedString attrString = new AttributedString(test);
		AttributedCharacterIterator it = attrString.getIterator(null, 2, 6);
		assertTrue("Wrong begin index", it.getEndIndex() == 6);
	}

	/**
	 * @tests java.text.AttributedCharacterIterator#getIndex()
	 */
	public void test_getIndex() {
		String test = "Test 23ring";
		AttributedString attrString = new AttributedString(test);
		AttributedCharacterIterator it = attrString.getIterator();
		assertTrue("Wrong first", it.getIndex() == 0);
		it.next();
		assertTrue("Wrong second", it.getIndex() == 1);
		for (int i = 0; i < 9; i++)
			it.next();
		assertTrue("Wrong last", it.getIndex() == 10);
		it.next();
		assertTrue("Wrong final", it.getIndex() == 11);
	}

	/**
	 * @tests java.text.AttributedCharacterIterator#last()
	 */
	public void test_last() {
		String test = "Test 23ring";
		AttributedString attrString = new AttributedString(test);
		AttributedCharacterIterator it = attrString.getIterator();
		assertTrue("Wrong last1", it.last() == 'g');
		it = attrString.getIterator(null, 0, 3);
		assertTrue("Wrong last2", it.last() == 's');
		it = attrString.getIterator(null, 2, 8);
		assertTrue("Wrong last3", it.last() == 'r');
		it = attrString.getIterator(null, 0, 0);
		assertTrue("Wrong last4", it.last() == CharacterIterator.DONE);
	}

	/**
	 * @tests java.text.AttributedCharacterIterator#next()
	 */
	public void test_next() {
		String test = "Test 23ring";
		AttributedString attrString = new AttributedString(test);
		AttributedCharacterIterator it = attrString.getIterator();
		assertTrue("Wrong first", it.next() == 'e');
		for (int i = 0; i < 8; i++)
			it.next();
		assertTrue("Wrong last", it.next() == 'g');
		assertTrue("Wrong final", it.next() == CharacterIterator.DONE);

		it = attrString.getIterator(null, 2, 8);
		assertTrue("Wrong first2", it.next() == 't');
	}

	/**
	 * @tests java.text.AttributedCharacterIterator#previous()
	 */
	public void test_previous() {
		String test = "Test 23ring";
		AttributedString attrString = new AttributedString(test);
		AttributedCharacterIterator it = attrString.getIterator();
		it.setIndex(11);
		assertTrue("Wrong first", it.previous() == 'g');
	}

	/**
	 * @tests java.text.AttributedCharacterIterator#setIndex(int)
	 */
	public void test_setIndexI() {
		String test = "Test 23ring";
		AttributedString attrString = new AttributedString(test);
		AttributedCharacterIterator it = attrString.getIterator();
		it.setIndex(5);
		assertTrue("Wrong first", it.current() == '2');
	}

	/**
	 * @tests java.text.AttributedCharacterIterator#getRunLimit(java.text.AttributedCharacterIterator$Attribute)
	 */
	public void test_getRunLimitLjava_text_AttributedCharacterIterator$Attribute() {
		AttributedString as = new AttributedString("test");
		as.addAttribute(AttributedCharacterIterator.Attribute.LANGUAGE, "a", 2,
				3);
		AttributedCharacterIterator it = as.getIterator();
		assertTrue(
				"non-null value limit",
				it.getRunLimit(AttributedCharacterIterator.Attribute.LANGUAGE) == 2);

		as = new AttributedString("test");
		as.addAttribute(AttributedCharacterIterator.Attribute.LANGUAGE, null,
				2, 3);
		it = as.getIterator();
		assertTrue(
				"null value limit",
				it.getRunLimit(AttributedCharacterIterator.Attribute.LANGUAGE) == 4);
	}

	protected void setUp() {
	}

	protected void tearDown() {
	}
}
