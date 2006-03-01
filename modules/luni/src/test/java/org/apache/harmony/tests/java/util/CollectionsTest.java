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

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

import junit.framework.TestCase;

public class CollectionsTest extends TestCase {

	/**
	 * @tests java.util.Collections#binarySearch(java.util.List,
	 *        java.lang.Object, java.util.Comparator)
	 */
	public void test_binarySearchLjava_util_ListLjava_lang_ObjectLjava_util_Comparator() {
		// Regression for HARMONY-94
		LinkedList lst = new LinkedList();
		lst.add(new Integer(30));
		Collections.sort(lst, null);
		int index = Collections.binarySearch(lst, new Integer(2), null);
		assertEquals(-1, index);
	} 
	   
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
    
    /**
     * @tests java.util.Collections#frequency(java.util.Collection,Object)
     */
    public void test_frequencyLjava_util_CollectionLint() {
        try {
            Collections.frequency(null, null);
            fail("Assert 0: frequency(null,<any>) must throw NPE");
        } catch (NullPointerException e) {}

        List strings = Arrays.asList(new String[] { "1", "2", "3", "1", "1" });

        assertEquals("Assert 1: did not find three \"1\" strings", 3,
                Collections.frequency(strings, "1"));

        assertEquals("Assert 2: did not find one \"2\" strings", 1, Collections
                .frequency(strings, "2"));

        assertEquals("Assert 3: did not find three \"3\" strings", 1,
                Collections.frequency(strings, "3"));

        assertEquals("Assert 4: matched on null when there are none", 0,
                Collections.frequency(strings, null));

        List objects = Arrays.asList(new Object[] { new Integer(1), null, null,
                new Long(1) });

        assertEquals("Assert 5: did not find one Integer(1)", 1, Collections
                .frequency(objects, new Integer(1)));

        assertEquals("Assert 6: did not find one Long(1)", 1, Collections
                .frequency(objects, new Long(1)));

        assertEquals("Assert 7: did not find two null references", 2,
                Collections.frequency(objects, null));
    }

    /**
     * @tests java.util.Collections#reverseOrder()
     */
    public void test_reverseOrder() {
        Comparator roc = Collections.reverseOrder();
        assertNotNull("Assert 0: comparator must not be null", roc);

        assertTrue("Assert 1: comparator must implement Serializable",
                roc instanceof Serializable);

        String[] fixtureDesc = new String[] { "2", "1", "0" };
        String[] numbers = new String[] { "0", "1", "2" };
        Arrays.sort(numbers, roc);
        assertTrue("Assert 2: the arrays are not equal, the sort failed",
                Arrays.equals(fixtureDesc, numbers));
    }

    /**
     * @tests java.util.Collections#reverseOrder(java.util.Comparator)
     */
    public void test_reverseOrderLjava_util_Comparator() {
        Comparator roc = Collections
                .reverseOrder(String.CASE_INSENSITIVE_ORDER);
        assertNotNull("Assert 0: comparator must not be null", roc);

        assertTrue("Assert 1: comparator must implement Serializable",
                roc instanceof Serializable);

        String[] fixtureDesc = new String[] { "2", "1", "0" };
        String[] numbers = new String[] { "0", "1", "2" };
        Arrays.sort(numbers, roc);
        assertTrue("Assert 2: the arrays are not equal, the sort failed",
                Arrays.equals(fixtureDesc, numbers));

        roc = Collections.reverseOrder(null);
        assertNotNull("Assert 3: comparator must not be null", roc);

        assertTrue("Assert 4: comparator must implement Serializable",
                roc instanceof Serializable);

        numbers = new String[] { "0", "1", "2" };
        Arrays.sort(numbers, roc);
        assertTrue("Assert 5: the arrays are not equal, the sort failed",
                Arrays.equals(fixtureDesc, numbers));
    }
}
