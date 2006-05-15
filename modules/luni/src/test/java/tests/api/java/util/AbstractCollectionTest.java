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

package tests.api.java.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;

public class AbstractCollectionTest extends junit.framework.TestCase {

    Collection org;

    /**
     * @tests java.util.AbstractCollection#add(java.lang.Object)
     */
    public void test_addLjava_lang_Object() {
        assertTrue("Nothing to test--unsupported operation", true);
    }

    /**
     * @tests java.util.AbstractCollection#addAll(java.util.Collection)
     */
    public void test_addAllLjava_util_Collection() {
        Collection col = new HashSet();
        col.addAll(org);
        assertEquals("Size", 100, col.size());
    }

    /**
     * @tests java.util.AbstractCollection#containsAll(java.util.Collection)
     */
    public void test_containsAllLjava_util_Collection() {
        ArrayList col = new ArrayList(org);
        assertTrue("Should contain all of the elements it started with", col
                .containsAll(org));
        col.remove(5);
        assertTrue(
                "Should no longer contain all of the elements it started with",
                !col.containsAll(org));
    }

    /**
     * @tests java.util.AbstractCollection#isEmpty()
     */
    public void test_isEmpty() {
        Collection col = new ArrayList();
        assertTrue("Should be empty when created", col.isEmpty());
        col.addAll(org);
        assertTrue("Should not be empty", !col.isEmpty());
        col.clear();
        assertTrue("Should be empty after clear", col.isEmpty());
    }

    /**
     * @tests java.util.AbstractCollection#removeAll(java.util.Collection)
     */
    public void test_removeAllLjava_util_Collection() {
        Collection someCol = new HashSet(org);
        Collection anotherCol = new HashSet(org);

        anotherCol.remove(new Integer(5));
        someCol.removeAll(anotherCol);
        assertEquals("Size--wanted 1", 1, someCol.size());

        someCol.remove(new Integer(5));
        assertEquals("Size--wanted 0", 0, someCol.size());
    }

    /**
     * @tests java.util.AbstractCollection#retainAll(java.util.Collection)
     */
    public void test_retainAllLjava_util_Collection() {
        Collection someCol = new HashSet(org);
        Collection anotherCol = new HashSet(org);

        anotherCol.remove(new Integer(5));
        someCol.retainAll(anotherCol);
        assertEquals("Size--wanted 99", 99, someCol.size());

        someCol.add(new Integer(5));
        assertEquals("Size--wanted 100", 100, someCol.size());
    }

    /**
     * @tests java.util.AbstractCollection#toArray()
     */
    public void test_toArray() {
        Object[] objArray = org.toArray();
        assertEquals("Length", 100, objArray.length);
        HashSet duplicates = new HashSet();
        for (int i = objArray.length - 1; i >= 0; i--) {
            assertTrue("The returned array has an incorrect value. At i = " + i
                    + " got: " + ((Integer) objArray[i]).intValue(), org
                    .contains(objArray[i]));
            assertTrue("Duplicate found at i = " + i, !duplicates
                    .contains(objArray[i]));
            duplicates.add(objArray[i]);
        }
    }

    /**
     * @tests java.util.AbstractCollection#toArray(java.lang.Object[])
     */
    public void test_toArray$Ljava_lang_Object() {

        try {
            org.toArray(null);
            fail("No expected NullPointerException");
        } catch (NullPointerException e) {
            // expected
        }

        try {
            org.toArray(new String[org.size()]);
            fail("No expected ArrayStoreException");
        } catch (ArrayStoreException e) {
            // expected
        }

        Object[] objArray = new Object[100];
        org.toArray(objArray);
        assertEquals("a) Length", 100, objArray.length);
        HashSet duplicates = new HashSet();
        for (int i = objArray.length - 1; i >= 0; i--) {
            assertTrue("a) The returned array has an incorrect value at i = "
                    + i, org.contains(objArray[i]));
            assertTrue("Duplicate found at i = " + i, !duplicates
                    .contains(objArray[i]));
            duplicates.add(objArray[i]);
        }

        Integer[] intArray = new Integer[105];
        intArray[100] = new Integer(1203);
        org.toArray(intArray);
        assertEquals("b) Length", 105, intArray.length);
        duplicates = new HashSet();
        for (int i = 99; i >= 0; i--) {
            assertTrue("b) The returned array has an incorrect value at i = "
                    + i, org.contains(intArray[i]));
            assertTrue("Duplicate found at i = " + i, !duplicates
                    .contains(intArray[i]));
            duplicates.add(intArray[i]);
        }
        assertNull("End of list should be null", intArray[100]);

        intArray = new Integer[1];
        intArray = (Integer[]) org.toArray(intArray);
        assertEquals("c) Length", 100, intArray.length);
        duplicates = new HashSet();
        for (int i = intArray.length - 1; i >= 0; i--) {
            assertTrue("c) The returned array has an incorrect value. At i = "
                    + i + " got: " + intArray[i].intValue(), org
                    .contains(intArray[i]));
            assertTrue("Duplicate found at i = " + i, !duplicates
                    .contains(intArray[i]));
            duplicates.add(intArray[i]);
        }
    }

    protected void setUp() {
        org = new HashSet();
        for (int i = 0; i < 100; i++)
            org.add(new Integer(i));

    }

    protected void tearDown() {
    }
}
