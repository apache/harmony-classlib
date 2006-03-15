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

package tests.api.java.text;

import java.text.CollationElementIterator;
import java.text.CollationKey;
import java.text.Collator;
import java.text.ParseException;
import java.text.RuleBasedCollator;
import java.text.StringCharacterIterator;
import java.util.Locale;

import junit.framework.TestCase;

/**
 * Test RuleBasedCollator
 * 
 * Only test normal condition.
 * 
 */
public class RuleBasedCollatorTest extends TestCase {

	public void testHashCode() throws ParseException {
		{
			String rule = "< a < b < c < d";
			RuleBasedCollator coll = new RuleBasedCollator(rule);
			assertEquals(rule.hashCode(), coll.hashCode());
		}

		{
			String rule = "< a < b < c < d < e";
			RuleBasedCollator coll = new RuleBasedCollator(rule);
			assertEquals(rule.hashCode(), coll.hashCode());
		}

	}

	public void testClone() throws ParseException {
		RuleBasedCollator coll = (RuleBasedCollator) Collator
				.getInstance(Locale.US);
		RuleBasedCollator clone = (RuleBasedCollator) coll.clone();
		assertNotSame(coll, clone);
		assertEquals(coll.getRules(), clone.getRules());
		assertEquals(coll.getDecomposition(), clone.getDecomposition());
		assertEquals(coll.getStrength(), clone.getStrength());
	}

	/*
	 * Class under test for boolean equals(java.lang.Object)
	 */
	public void testEqualsObject() throws ParseException {
		String rule = "< a < b < c < d < e";
		RuleBasedCollator coll = new RuleBasedCollator(rule);

		assertEquals(Collator.TERTIARY, coll.getStrength());
		assertEquals(Collator.NO_DECOMPOSITION, coll.getDecomposition());
		RuleBasedCollator other = new RuleBasedCollator(rule);
		assertTrue(coll.equals(other));

		coll.setStrength(Collator.PRIMARY);
		assertFalse(coll.equals(other));

		coll.setStrength(Collator.TERTIARY);
		coll.setDecomposition(Collator.CANONICAL_DECOMPOSITION);
		assertFalse(coll.equals(other));
	}

	/*
	 * Class under test for int compare(java.lang.String, java.lang.String)
	 */
	public void testCompareStringString() throws ParseException {
		String rule = "< c < b < a";
		RuleBasedCollator coll = new RuleBasedCollator(rule);
		assertEquals(-1, coll.compare("c", "a"));
	}

	public void testGetCollationKey() {
		RuleBasedCollator coll = (RuleBasedCollator) Collator
				.getInstance(Locale.GERMAN);
		String source = "abc";
		CollationKey key1 = coll.getCollationKey(source);
		assertEquals(source, key1.getSourceString());
		String source2 = "abb";
		CollationKey key2 = coll.getCollationKey(source2);
		assertEquals(source2, key2.getSourceString());
		assertTrue(key1.compareTo(key2) > 0);
		assertTrue(coll.compare(source, source2) > 0);

	}

	public void testGetRules() throws ParseException {
		String rule = "< a = b < c";
		RuleBasedCollator coll = new RuleBasedCollator(rule);
		assertEquals(rule, coll.getRules());
	}

	/*
	 * Class under test for java.text.CollationElementIterator
	 * getCollationElementIterator(java.lang.String)
	 */
	public void testGetCollationElementIteratorString() {
		{
			Locale locale = new Locale("es", "", "TRADITIONAL");
			RuleBasedCollator coll = (RuleBasedCollator) Collator
					.getInstance(locale);
			System.out.println("Rules: " + coll.getRules());
			String source = "cha";
			CollationElementIterator iterator = coll
					.getCollationElementIterator(source);
			int[] e_offset = { 0, 2, 3 };
			int offset = iterator.getOffset();
			int i = 0;
			assertEquals(e_offset[i++], offset);
			while (offset != source.length()) {
				iterator.next();
				offset = iterator.getOffset();
				assertEquals(e_offset[i++], offset);
			}
		}

		{
			Locale locale = new Locale("de", "DE");
			RuleBasedCollator coll = (RuleBasedCollator) Collator
					.getInstance(locale);
			String source = "\u00E6b";
			CollationElementIterator iterator = coll
					.getCollationElementIterator(source);
			int[] e_offset = { 0, 1, 1, 2 };
			int offset = iterator.getOffset();
			int i = 0;
			assertEquals(e_offset[i++], offset);
			while (offset != source.length()) {
				iterator.next();
				offset = iterator.getOffset();
				assertEquals(e_offset[i++], offset);
			}
		}
	}

	/*
	 * Class under test for java.text.CollationElementIterator
	 * getCollationElementIterator(java.text.CharacterIterator)
	 */
	public void testGetCollationElementIteratorCharacterIterator() {
		{
			Locale locale = new Locale("es", "", "TRADITIONAL");
			RuleBasedCollator coll = (RuleBasedCollator) Collator
					.getInstance(locale);
			String text = "cha";
			StringCharacterIterator source = new StringCharacterIterator(text);
			CollationElementIterator iterator = coll
					.getCollationElementIterator(source);
			int[] e_offset = { 0, 2, 3 };
			int offset = iterator.getOffset();
			int i = 0;
			assertEquals(e_offset[i++], offset);
			while (offset != text.length()) {
				iterator.next();
				offset = iterator.getOffset();
				// System.out.println(offset);
				assertEquals(e_offset[i++], offset);
			}
		}

		{
			Locale locale = new Locale("de", "DE");
			RuleBasedCollator coll = (RuleBasedCollator) Collator
					.getInstance(locale);
			String text = "\u00E6b";
			StringCharacterIterator source = new StringCharacterIterator(text);
			CollationElementIterator iterator = coll
					.getCollationElementIterator(source);
			int[] e_offset = { 0, 1, 1, 2 };
			int offset = iterator.getOffset();
			int i = 0;
			assertEquals(e_offset[i++], offset);
			while (offset != text.length()) {
				iterator.next();
				offset = iterator.getOffset();
				assertEquals(e_offset[i++], offset);
			}
		}
	}

	public void testStrength() {
		RuleBasedCollator coll = (RuleBasedCollator) Collator
				.getInstance(Locale.US);
		for (int i = 0; i < 4; i++) {
			coll.setStrength(i);
			assertEquals(i, coll.getStrength());
		}

	}

	public void testDecomposition() {
		RuleBasedCollator coll = (RuleBasedCollator) Collator
				.getInstance(Locale.US);
		for (int i = 0; i < 2; i++) {
			coll.setDecomposition(i);
			assertEquals(i, coll.getDecomposition());
		}
	}

	public void testCollator_GetInstance() {
		Collator coll = Collator.getInstance();
		Object obj1 = "a";
		Object obj2 = "b";
		assertEquals(-1, coll.compare(obj1, obj2));

		Collator coll2 = Collator.getInstance();
		assertFalse(coll.equals("A", "\uFF21"));
	}

	public void testGetAvaiableLocales() {
		// Locale[] locales = Collator.getAvailableLocales();
		// for (int i = 0; i < locales.length; i++) {
		// Locale locale = locales[i];
		// }
	}

	// Test CollationKey
	public void testCollationKey() {
		Collator coll = Collator.getInstance(Locale.US);
		String text = "abc";
		CollationKey key = coll.getCollationKey(text);
		int hashCode = key.hashCode();

		CollationKey key2 = coll.getCollationKey("abc");

		assertEquals(0, key.compareTo(key2));
		Object key3 = key2;
		assertEquals(0, key.compareTo(key3));
		assertTrue(key.equals(key2));

		byte[] bytes = key.toByteArray();
	}
}
