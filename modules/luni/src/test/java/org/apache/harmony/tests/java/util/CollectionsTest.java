/*
 * Copyright 2006 The Apache Software Foundation or its licensors, as applicable
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */

package org.apache.harmony.tests.java.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import junit.framework.TestCase;

public class CollectionsTest extends TestCase {

	/**
	 * @tests java.util.Collections#rotate(java.util.List, int)
	 */
	public void test_rotateLjava_util_ListI() {
		// Regression for HARMONY-19 Rotate an *empty* list
		Collections.rotate(new ArrayList(), 25);

		// Regression for HARMONY-20
		List list = new ArrayList();
		list.add(0, "zero");
		list.add(1, "one");
		list.add(2, "two");
		list.add(3, "three");
		list.add(4, "four");

		Collections.rotate(list, Integer.MIN_VALUE);
		assertEquals("Rotated incorrectly at position 0, ", "three",
				(String) list.get(0));
		assertEquals("Rotated incorrectly at position 1, ", "four",
				(String) list.get(1));
		assertEquals("Rotated incorrectly at position 2, ", "zero",
				(String) list.get(2));
		assertEquals("Rotated incorrectly at position 3, ", "one",
				(String) list.get(3));
		assertEquals("Rotated incorrectly at position 4, ", "two",
				(String) list.get(4));
	}

	/**
	 * @tests java.util.Collections#synchronizedCollection(java.util.Collection)
	 */
	public void test_synchronizedCollectionLjava_util_Collection() {
		try {
			// Regression for HARMONY-93
			Collections.synchronizedCollection(null);
			fail("Assert 0: synchronizedCollection(null) must throw NPE");
		} catch (NullPointerException e) {
			// expected
		}
	}

	/**
	 * @tests java.util.Collections#synchronizedSortedMap(java.util.SortedMap)
	 */
	public void test_synchronizedSortedMapLjava_util_SortedMap() {
		try {
			// Regression for HARMONY-93
			Collections.synchronizedSortedMap(null);
			fail("Assert 0: synchronizedSortedMap(null) must throw NPE");
		} catch (NullPointerException e) {
			// expected
		}
	}

	/**
	 * @tests java.util.Collections#synchronizedMap(java.util.Map)
	 */
	public void test_synchronizedMapLjava_util_Map() {
		try {
			// Regression for HARMONY-93
			Collections.synchronizedMap(null);
			fail("Assert 0: synchronizedMap(map) must throw NPE");
		} catch (NullPointerException e) {
			// expected
		}
	}

	/**
	 * @tests java.util.Collections#synchronizedSet(java.util.Set)
	 */
	public void test_synchronizedSetLjava_util_Set() {
		try {
			// Regression for HARMONY-93
			Collections.synchronizedSet(null);
			fail("Assert 0: synchronizedSet(set) must throw NPE");
		} catch (NullPointerException e) {
			// expected
		}
	}

	/**
	 * @tests java.util.Collections#synchronizedSortedSet(java.util.SortedSet)
	 */
	public void test_synchronizedSortedSetLjava_util_SortedSet() {
		try {
			// Regression for HARMONY-93
			Collections.synchronizedSortedSet(null);
			fail("Assert 0: synchronizedSortedSet(null) must throw NPE");
		} catch (NullPointerException e) {
			// expected
		}
	}

	/**
	 * @tests java.util.Collections#unmodifiableCollection(java.util.Collection)
	 */
	public void test_unmodifiableCollectionLjava_util_Collection() {
		try {
			// Regression for HARMONY-93
			Collections.unmodifiableCollection(null);
			fail("Assert 0: unmodifiableCollection(null) must throw NPE");
		} catch (NullPointerException e) {
			// expected
		}
	}

	/**
	 * @tests java.util.Collections#unmodifiableMap(java.util.Map)
	 */
	public void test_unmodifiableMapLjava_util_Map() {
		try {
			// Regression for HARMONY-93
			Collections.unmodifiableMap(null);
			fail("Assert 0: unmodifiableMap(null) must throw NPE");
		} catch (NullPointerException e) {
			// expected
		}
	}

	/**
	 * @tests java.util.Collections#unmodifiableSet(java.util.Set)
	 */
	public void test_unmodifiableSetLjava_util_Set() {
		try {
			// Regression for HARMONY-93
			Collections.unmodifiableSet(null);
			fail("Assert 0: unmodifiableSet(null) must throw NPE");
		} catch (NullPointerException e) {
			// expected
		}
	}

	/**
	 * @tests java.util.Collections#unmodifiableSortedMap(java.util.SortedMap)
	 */
	public void test_unmodifiableSortedMapLjava_util_SortedMap() {
		try {
			// Regression for HARMONY-93
			Collections.unmodifiableSortedMap(null);
			fail("Assert 0: unmodifiableSortedMap(null) must throw NPE");
		} catch (NullPointerException e) {
			// expected
		}
	}

	/**
	 * @tests java.util.Collections#unmodifiableSortedSet(java.util.SortedSet)
	 */
	public void test_unmodifiableSortedSetLjava_util_SortedSet() {
		try {
			// Regression for HARMONY-93
			Collections.unmodifiableSortedSet(null);
			fail("Assert 0: unmodifiableSortedSet(null) must throw NPE");
		} catch (NullPointerException e) {
			// expected
		}
	}
}
