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

import java.text.CharacterIterator;
import java.text.StringCharacterIterator;

public class StringCharacterIteratorTest extends junit.framework.TestCase {

	/**
	 * @tests java.text.StringCharacterIterator#StringCharacterIterator(java.lang.String)
	 */
	public void test_ConstructorLjava_lang_String() {
		StringCharacterIterator it = new StringCharacterIterator("testing");
		assertTrue("Wrong begin index", it.getBeginIndex() == 0);
		assertTrue("Wrong end index", it.getEndIndex() == 7);
		assertTrue("Wrong current index", it.getIndex() == 0);
		assertTrue("Wrong current char", it.current() == 't');
		assertTrue("Wrong next char", it.next() == 'e');
	}

	/**
	 * @tests java.text.StringCharacterIterator#StringCharacterIterator(java.lang.String,
	 *        int)
	 */
	public void test_ConstructorLjava_lang_StringI() {
		StringCharacterIterator it = new StringCharacterIterator("testing", 3);
		assertTrue("Wrong begin index", it.getBeginIndex() == 0);
		assertTrue("Wrong end index", it.getEndIndex() == 7);
		assertTrue("Wrong current index", it.getIndex() == 3);
		assertTrue("Wrong current char", it.current() == 't');
		assertTrue("Wrong next char", it.next() == 'i');
	}

	/**
	 * @tests java.text.StringCharacterIterator#StringCharacterIterator(java.lang.String,
	 *        int, int, int)
	 */
	public void test_ConstructorLjava_lang_StringIII() {
		StringCharacterIterator it = new StringCharacterIterator("testing", 2,
				6, 4);
		assertTrue("Wrong begin index", it.getBeginIndex() == 2);
		assertTrue("Wrong end index", it.getEndIndex() == 6);
		assertTrue("Wrong current index", it.getIndex() == 4);
		assertTrue("Wrong current char", it.current() == 'i');
		assertTrue("Wrong next char", it.next() == 'n');
	}

	/**
	 * @tests java.text.StringCharacterIterator#clone()
	 */
	public void test_clone() {
		StringCharacterIterator it = new StringCharacterIterator("testing", 2,
				6, 4);
		StringCharacterIterator clone = (StringCharacterIterator) it.clone();
		assertTrue("Clone not equal", it.equals(clone));
	}

	/**
	 * @tests java.text.StringCharacterIterator#current()
	 */
	public void test_current() {
		StringCharacterIterator it = new StringCharacterIterator("testing", 2,
				6, 4);
		assertTrue("Wrong current char", it.current() == 'i');
	}

	/**
	 * @tests java.text.StringCharacterIterator#equals(java.lang.Object)
	 */
	public void test_equalsLjava_lang_Object() {
		StringCharacterIterator it1 = new StringCharacterIterator("testing", 2,
				6, 4);
		StringCharacterIterator it2 = new StringCharacterIterator("xxstinx", 2,
				6, 4);
		assertTrue("Range is equal", !it1.equals(it2));
		StringCharacterIterator it3 = new StringCharacterIterator("testing", 2,
				6, 2);
		it3.setIndex(4);
		assertTrue("Not equal", it1.equals(it3));
	}

	/**
	 * @tests java.text.StringCharacterIterator#first()
	 */
	public void test_first() {
		StringCharacterIterator it1 = new StringCharacterIterator("testing", 2,
				6, 4);
		assertTrue("Wrong first char", it1.first() == 's');
		assertTrue("Wrong next char", it1.next() == 't');
		it1 = new StringCharacterIterator("testing", 2, 2, 2);
		assertTrue("Not DONE", it1.first() == CharacterIterator.DONE);
	}

	/**
	 * @tests java.text.StringCharacterIterator#getBeginIndex()
	 */
	public void test_getBeginIndex() {
		StringCharacterIterator it1 = new StringCharacterIterator("testing", 2,
				6, 4);
		assertTrue("Wrong begin index 2", it1.getBeginIndex() == 2);
	}

	/**
	 * @tests java.text.StringCharacterIterator#getEndIndex()
	 */
	public void test_getEndIndex() {
		StringCharacterIterator it1 = new StringCharacterIterator("testing", 2,
				6, 4);
		assertTrue("Wrong end index 6", it1.getEndIndex() == 6);
	}

	/**
	 * @tests java.text.StringCharacterIterator#getIndex()
	 */
	public void test_getIndex() {
		StringCharacterIterator it1 = new StringCharacterIterator("testing", 2,
				6, 4);
		assertTrue("Wrong index 4", it1.getIndex() == 4);
		it1.next();
		assertTrue("Wrong index 5", it1.getIndex() == 5);
		it1.last();
		assertTrue("Wrong index 4/2", it1.getIndex() == 5);
	}

	/**
	 * @tests java.text.StringCharacterIterator#hashCode()
	 */
	public void test_hashCode() {
		StringCharacterIterator it1 = new StringCharacterIterator("testing", 2,
				6, 4);
		StringCharacterIterator it2 = new StringCharacterIterator("xxstinx", 2,
				6, 4);
		assertTrue("Hash is equal", it1.hashCode() != it2.hashCode());
		StringCharacterIterator it3 = new StringCharacterIterator("testing", 2,
				6, 2);
		assertTrue("Hash equal1", it1.hashCode() != it3.hashCode());
		it3 = new StringCharacterIterator("testing", 0, 6, 4);
		assertTrue("Hash equal2", it1.hashCode() != it3.hashCode());
		it3 = new StringCharacterIterator("testing", 2, 5, 4);
		assertTrue("Hash equal3", it1.hashCode() != it3.hashCode());
		it3 = new StringCharacterIterator("froging", 2, 6, 4);
		assertTrue("Hash equal4", it1.hashCode() != it3.hashCode());
	}

	/**
	 * @tests java.text.StringCharacterIterator#last()
	 */
	public void test_last() {
		StringCharacterIterator it1 = new StringCharacterIterator("testing", 2,
				6, 3);
		assertTrue("Wrong last char", it1.last() == 'n');
		assertTrue("Wrong previous char", it1.previous() == 'i');
		it1 = new StringCharacterIterator("testing", 2, 2, 2);
		assertTrue("Not DONE", it1.last() == CharacterIterator.DONE);
	}

	/**
	 * @tests java.text.StringCharacterIterator#next()
	 */
	public void test_next() {
		StringCharacterIterator it1 = new StringCharacterIterator("testing", 2,
				6, 3);
		char result = it1.next();
		assertTrue("Wrong next char1: " + result, result == 'i');
		assertTrue("Wrong next char2", it1.next() == 'n');
		assertTrue("Wrong next char3", it1.next() == CharacterIterator.DONE);
		assertTrue("Wrong next char4", it1.next() == CharacterIterator.DONE);
		int index = it1.getIndex();
		assertTrue("Wrong index: " + index, index == 6);
		assertTrue("Wrong current char",
				it1.current() == CharacterIterator.DONE);
	}

	/**
	 * @tests java.text.StringCharacterIterator#previous()
	 */
	public void test_previous() {
		StringCharacterIterator it1 = new StringCharacterIterator("testing", 2,
				6, 4);
		assertTrue("Wrong previous char1", it1.previous() == 't');
		assertTrue("Wrong previous char2", it1.previous() == 's');
		assertTrue("Wrong previous char3",
				it1.previous() == CharacterIterator.DONE);
		assertTrue("Wrong previous char4",
				it1.previous() == CharacterIterator.DONE);
		assertTrue("Wrong index", it1.getIndex() == 2);
		assertTrue("Wrong current char", it1.current() == 's');
	}

	/**
	 * @tests java.text.StringCharacterIterator#setIndex(int)
	 */
	public void test_setIndexI() {
		StringCharacterIterator it1 = new StringCharacterIterator("testing", 2,
				6, 4);
		assertTrue("Wrong result1", it1.setIndex(2) == 's');
		char result = it1.next();
		assertTrue("Wrong next char: " + result, result == 't');
		assertTrue("Wrong result2", it1.setIndex(6) == CharacterIterator.DONE);
		assertTrue("Wrong previous char", it1.previous() == 'n');
	}

	/**
	 * @tests java.text.StringCharacterIterator#setText(java.lang.String)
	 */
	public void test_setTextLjava_lang_String() {
		StringCharacterIterator it1 = new StringCharacterIterator("testing", 2,
				6, 4);
		it1.setText("frog");
		assertTrue("Wrong begin index", it1.getBeginIndex() == 0);
		assertTrue("Wrong end index", it1.getEndIndex() == 4);
		assertTrue("Wrong current index", it1.getIndex() == 0);
	}

	protected void setUp() {
	}

	protected void tearDown() {
	}
}
