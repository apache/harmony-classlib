/* Copyright 2006 The Apache Software Foundation or its licensors, as applicable
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
import java.util.EnumSet;
import java.util.Set;

import junit.framework.TestCase;

public class EnumSetTest extends TestCase {
    
    static enum EnumWithInnerClass {
        a, b, c, d, e, f {
        },
    }

    enum EnumWithAllInnerClass {
        a {},
        b {},
    }
    
    static enum EnumFoo {
        a, b,c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s, t, u, v, w, x, y, z, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, W, X, Y, Z, aa, bb, cc, dd, ee, ff, gg, hh, ii, jj, kk, ll,
    }
    
    static enum EmptyEnum {
        // expected
    }

    /**
     * @tests java.util.EnumSet#noneOf(java.lang.Class)
     */
    @SuppressWarnings("unchecked")
    public void test_NoneOf_LClass() {
        try {
            EnumSet.noneOf((Class) null);
            fail("Should throw NullPointerException"); //$NON-NLS-1$
        } catch (NullPointerException e) {
            // expected
        }

        try {
            EnumSet.noneOf(Enum.class);
            fail("Should throw ClassCastException"); //$NON-NLS-1$
        } catch (ClassCastException cce) {
            // expected
        }

        Class<EnumWithAllInnerClass> c = (Class<EnumWithAllInnerClass>) EnumWithAllInnerClass.a
                .getClass();
        try {
            EnumSet.noneOf(c);
            fail("Should throw ClassCastException"); //$NON-NLS-1$
        } catch (ClassCastException e) {
            // expected
        }

        EnumSet<EnumWithAllInnerClass> setWithInnerClass = EnumSet
                .noneOf(EnumWithAllInnerClass.class);
        assertNotNull(setWithInnerClass);
    }
    
    /**
     * @tests java.util.EnumSet#add(E)
     */
    @SuppressWarnings("unchecked")
    public void test_add_E() {
        Set<EnumFoo> set = EnumSet.noneOf(EnumFoo.class);
        set.add(EnumFoo.a);
        set.add(EnumFoo.b);
        
        try {
            set.add(null);
            fail("Should throw NullPointerException"); //$NON-NLS-1$
        } catch (NullPointerException e) {
            // expected
        }

        set.clear();
        try {
            set.add(null);
            fail("Should throw NullPointerException"); //$NON-NLS-1$
        } catch (NullPointerException e) {
            // expected
        }

        boolean result = set.add(EnumFoo.a);
        assertEquals("Size should be 1:", 1, set.size()); //$NON-NLS-1$
        assertTrue("Return value should be true", result); //$NON-NLS-1$

        result = set.add(EnumFoo.a);
        assertEquals("Size should be 1:", 1, set.size()); //$NON-NLS-1$
        assertFalse("Return value should be false", result); //$NON-NLS-1$

        set.add(EnumFoo.b);
        assertEquals("Size should be 2:", 2, set.size()); //$NON-NLS-1$
        
        Set rawSet = set;
        try {
            rawSet.add(EnumWithAllInnerClass.a);
            fail("Should throw ClassCastException"); //$NON-NLS-1$
        } catch(ClassCastException e) {
            // expected
        }
        
        try {
            rawSet.add(EnumWithInnerClass.a);
            fail("Should throw ClassCastException"); //$NON-NLS-1$
        } catch(ClassCastException e) {
            // expected
        }
        
        try {
            rawSet.add(new Object());
            fail("Should throw ClassCastException"); //$NON-NLS-1$
        } catch(ClassCastException e) {
            // expected
        }
    }
    
    /**
     * @tests java.util.EnumSet#addAll(Collection)
     */
    @SuppressWarnings( { "unchecked", "boxing" })
    public void test_addAll_LCollection() {

        Set<EnumFoo> set = EnumSet.noneOf(EnumFoo.class);
        assertEquals("Size should be 0:", 0, set.size()); //$NON-NLS-1$

        try {
            set.addAll(null);
            fail("Should throw NullPointerException"); //$NON-NLS-1$
        } catch (NullPointerException e) {
            // expected
        }

        Set emptySet = EnumSet.noneOf(EmptyEnum.class);
        Enum[] elements = EmptyEnum.class.getEnumConstants();
        for(int i = 0; i < elements.length; i++) {
            emptySet.add(elements[i]);
        }
        boolean result = set.addAll(emptySet);
        assertFalse(result);

        Collection<EnumFoo> collection = new ArrayList<EnumFoo>();
        collection.add(EnumFoo.a);
        collection.add(EnumFoo.b);
        result = set.addAll(collection);
        assertTrue("addAll should be successful", result); //$NON-NLS-1$
        assertEquals("Size should be 2:", 2, set.size()); //$NON-NLS-1$

        set = EnumSet.noneOf(EnumFoo.class);

        Collection rawCollection = new ArrayList<Integer>();
        result = set.addAll(rawCollection);
        assertFalse(result);
        rawCollection.add(1);
        try {
            set.addAll(rawCollection);
            fail("Should throw ClassCastException"); //$NON-NLS-1$
        } catch (ClassCastException e) {
            // expected
        }

        Set<EnumFoo> fullSet = EnumSet.noneOf(EnumFoo.class);
        fullSet.add(EnumFoo.a);
        fullSet.add(EnumFoo.b);
        result = set.addAll(fullSet);
        assertTrue("addAll should be successful", result); //$NON-NLS-1$
        assertEquals("Size of set should be 2", 2, set.size()); //$NON-NLS-1$

        try {
            fullSet.addAll(null);
            fail("Should throw NullPointerException"); //$NON-NLS-1$
        } catch (NullPointerException e) {
            // expected
        }

        Set fullSetWithSubclass = EnumSet.noneOf(EnumWithInnerClass.class);
        elements = EnumWithInnerClass.class.getEnumConstants();
        for(int i = 0; i < elements.length; i++) {
            fullSetWithSubclass.add(elements[i]);
        }
        try {
            set.addAll(fullSetWithSubclass);
            fail("Should throw ClassCastException"); //$NON-NLS-1$
        } catch (ClassCastException e) {
            // expected
        }
        Set<EnumWithInnerClass> setWithSubclass = fullSetWithSubclass;
        result = setWithSubclass.addAll(setWithSubclass);
        assertFalse("Should return false", result); //$NON-NLS-1$

        Set<EnumWithInnerClass> anotherSetWithSubclass = EnumSet
                .noneOf(EnumWithInnerClass.class);
        elements = EnumWithInnerClass.class.getEnumConstants();
        for(int i = 0; i < elements.length; i++) {
            anotherSetWithSubclass.add((EnumWithInnerClass) elements[i]);
        }
        result = setWithSubclass.addAll(anotherSetWithSubclass);
        assertFalse("Should return false", result); //$NON-NLS-1$

        anotherSetWithSubclass.remove(EnumWithInnerClass.a);
        result = setWithSubclass.addAll(anotherSetWithSubclass);
        assertFalse("Should return false", result); //$NON-NLS-1$

    }
    
    /**
     * @tests java.util.EnumSet#remove(Object)
     */
    public void test_remove_LOject() {
        Set<EnumFoo> set = EnumSet.noneOf(EnumFoo.class);
        Enum[] elements = EnumFoo.class.getEnumConstants();
        for(int i = 0; i < elements.length; i++) {
            set.add((EnumFoo) elements[i]);
        }
        
        boolean result = set.remove(null);
        assertFalse("'set' does not contain null", result); //$NON-NLS-1$

        result = set.remove(EnumFoo.a);
        assertTrue("Should return true", result); //$NON-NLS-1$
        result = set.remove(EnumFoo.a);
        assertFalse("Should return false", result); //$NON-NLS-1$

        assertEquals("Size of set should be 63:", 63, set.size()); //$NON-NLS-1$

        result = set.remove(EnumWithInnerClass.a);
        assertFalse("Should return false", result); //$NON-NLS-1$
        result = set.remove(EnumWithInnerClass.f);
        assertFalse("Should return false", result); //$NON-NLS-1$
    }
    
    /**
     * @tests java.util.EnumSet#equals(Object)
     */
    public void test_equals_LObject() {
        Set<EnumFoo> set = EnumSet.noneOf(EnumFoo.class);
        Enum[] elements = EnumFoo.class.getEnumConstants();
        for(int i = 0; i < elements.length; i++) {
            set.add((EnumFoo) elements[i]);
        }
        
        assertFalse("Should return false", set.equals(null)); //$NON-NLS-1$
        assertFalse(
                "Should return false", set.equals(new Object())); //$NON-NLS-1$

        Set<EnumFoo> anotherSet = EnumSet.noneOf(EnumFoo.class);
        elements = EnumFoo.class.getEnumConstants();
        for(int i = 0; i < elements.length; i++) {
            anotherSet.add((EnumFoo) elements[i]);
        }
        assertTrue("Should return true", set.equals(anotherSet)); //$NON-NLS-1$
        
        anotherSet.remove(EnumFoo.a);
        assertFalse(
                "Should return false", set.equals(anotherSet)); //$NON-NLS-1$

        Set<EnumWithInnerClass> setWithInnerClass = EnumSet
                .noneOf(EnumWithInnerClass.class);
        elements = EnumWithInnerClass.class.getEnumConstants();
        for(int i = 0; i < elements.length; i++) {
            setWithInnerClass.add((EnumWithInnerClass) elements[i]);
        }
        
        assertFalse(
                "Should return false", set.equals(setWithInnerClass)); //$NON-NLS-1$

        setWithInnerClass.clear();
        set.clear();
        assertTrue("Should be equal", set.equals(setWithInnerClass)); //$NON-NLS-1$
        
    }
    
    /**
     * @tests java.util.EnumSet#clear()
     */
    public void test_clear() {
        Set<EnumFoo> set = EnumSet.noneOf(EnumFoo.class);
        set.add(EnumFoo.a);
        set.add(EnumFoo.b);
        assertEquals("Size should be 2", 2, set.size()); //$NON-NLS-1$

        set.clear();

        assertEquals("Size should be 0", 0, set.size()); //$NON-NLS-1$
    }
    
    /**
     * @tests java.util.EnumSet#size()
     */
    public void test_size() {
        Set<EnumFoo> set = EnumSet.noneOf(EnumFoo.class);
        set.add(EnumFoo.a);
        set.add(EnumFoo.b);
        assertEquals("Size should be 2", 2, set.size()); //$NON-NLS-1$
    }
    
    /**
     * @tests java.util.EnumSet#complementOf(java.util.EnumSet)
     */
    public void test_ComplementOf_LEnumSet() {

        try {
            EnumSet.complementOf((EnumSet<EnumFoo>) null);
            fail("Should throw NullPointerException"); //$NON-NLS-1$
        } catch (NullPointerException npe) {
            // expected
        }

        EnumSet<EnumWithInnerClass> set = EnumSet
                .noneOf(EnumWithInnerClass.class);
        set.add(EnumWithInnerClass.d);
        set.add(EnumWithInnerClass.e);
        set.add(EnumWithInnerClass.f);

        assertEquals("Size should be 3:", 3, set.size()); //$NON-NLS-1$

        EnumSet<EnumWithInnerClass> complementOfE = EnumSet.complementOf(set);
        assertTrue(set.contains(EnumWithInnerClass.d));
        assertEquals(
                "complementOfE should have size 3", 3, complementOfE.size()); //$NON-NLS-1$
        assertTrue("complementOfE should contain EnumWithSubclass.a:", //$NON-NLS-1$ 
                complementOfE.contains(EnumWithInnerClass.a));
        assertTrue("complementOfE should contain EnumWithSubclass.b:", //$NON-NLS-1$
                complementOfE.contains(EnumWithInnerClass.b));
        assertTrue("complementOfE should contain EnumWithSubclass.c:", //$NON-NLS-1$
                complementOfE.contains(EnumWithInnerClass.c));
    }
    
    /**
     * @tests java.util.EnumSet#contains(Object)
     */
    public void test_contains_LObject() {
        Set<EnumFoo> set = EnumSet.noneOf(EnumFoo.class);
        Enum[] elements = EnumFoo.class.getEnumConstants();
        for(int i = 0; i < elements.length; i++) {
            set.add((EnumFoo)elements[i]);
        }
        boolean result = set.contains(null);
        assertFalse("Should not contain null:", result); //$NON-NLS-1$

        result = set.contains(EnumFoo.a);
        assertTrue("Should contain EnumFoo.a", result); //$NON-NLS-1$
        result = set.contains(EnumFoo.ll);
        assertTrue("Should contain EnumFoo.ll", result); //$NON-NLS-1$

        result = set.contains(EnumFoo.b);
        assertTrue("Should contain EnumFoo.b", result); //$NON-NLS-1$

        result = set.contains(new Object());
        assertFalse("Should not contain Object instance", result); //$NON-NLS-1$

        result = set.contains(EnumWithInnerClass.a);
        assertFalse("Should not contain EnumWithSubclass.a", result); //$NON-NLS-1$
        
        set = EnumSet.noneOf(EnumFoo.class);
        set.add(EnumFoo.aa);
        set.add(EnumFoo.bb);
        set.add(EnumFoo.cc);
        
        assertEquals("Size of set should be 3", 3, set.size()); //$NON-NLS-1$
        assertTrue("set should contain EnumFoo.aa", set.contains(EnumFoo.aa)); //$NON-NLS-1$

        Set<EnumWithInnerClass> setWithSubclass = EnumSet
                .noneOf(EnumWithInnerClass.class);
        setWithSubclass.add(EnumWithInnerClass.a);
        setWithSubclass.add(EnumWithInnerClass.b);
        setWithSubclass.add(EnumWithInnerClass.c);
        setWithSubclass.add(EnumWithInnerClass.d);
        setWithSubclass.add(EnumWithInnerClass.e);
        setWithSubclass.add(EnumWithInnerClass.f);
        result = setWithSubclass.contains(EnumWithInnerClass.f);
        assertTrue("Should contain EnumWithSubclass.f", result); //$NON-NLS-1$
    }
    
    /**
     * @tests java.util.EnumSet#containsAll(Collection)
     */
    @SuppressWarnings( { "unchecked", "boxing" })
    public void test_containsAll_LCollection() {
        EnumSet<EnumFoo> set = EnumSet.noneOf(EnumFoo.class);
        Enum[] elements = EnumFoo.class.getEnumConstants();
        for(int i = 0; i < elements.length; i++) {
            set.add((EnumFoo)elements[i]);
        }
        try {
            set.containsAll(null);
            fail("Should throw NullPointerException"); //$NON-NLS-1$
        } catch (NullPointerException e) {
            // expected
        }

        EnumSet<EmptyEnum> emptySet = EnumSet.noneOf(EmptyEnum.class);
        elements = EmptyEnum.class.getEnumConstants();
        for(int i = 0; i < elements.length; i++) {
            emptySet.add((EmptyEnum)elements[i]);
        }
        boolean result = set.containsAll(emptySet);
        assertTrue("Should return true", result); //$NON-NLS-1$

        Collection rawCollection = new ArrayList();
        result = set.containsAll(rawCollection);
        assertTrue("Should contain empty collection:", result); //$NON-NLS-1$

        rawCollection.add(1);
        result = set.containsAll(rawCollection);
        assertFalse("Should return false", result); //$NON-NLS-1$

        rawCollection.add(EnumWithInnerClass.a);
        result = set.containsAll(rawCollection);
        assertFalse("Should return false", result); //$NON-NLS-1$

        EnumSet rawSet = EnumSet.noneOf(EnumFoo.class);
        result = set.containsAll(rawSet);
        assertTrue("Should contain empty set", result); //$NON-NLS-1$

        emptySet = EnumSet.noneOf(EmptyEnum.class);
        result = set.containsAll(emptySet);
        assertTrue("No class cast should be performed on empty set", result); //$NON-NLS-1$

        Collection<EnumFoo> collection = new ArrayList<EnumFoo>();
        collection.add(EnumFoo.a);
        result = set.containsAll(collection);
        assertTrue("Should contain all elements in collection", result); //$NON-NLS-1$

        EnumSet<EnumFoo> fooSet = EnumSet.noneOf(EnumFoo.class);
        fooSet.add(EnumFoo.a);
        result = set.containsAll(fooSet);
        assertTrue("Should return true", result); //$NON-NLS-1$

        set.clear();
        try {
            set.containsAll(null);
            fail("Should throw NullPointerException"); //$NON-NLS-1$
        } catch (NullPointerException e) {
            // expected
        }

        Collection<EnumWithInnerClass> collectionWithSubclass = new ArrayList<EnumWithInnerClass>();
        collectionWithSubclass.add(EnumWithInnerClass.a);
        result = set.containsAll(collectionWithSubclass);
        assertFalse("Should return false", result); //$NON-NLS-1$

        EnumSet<EnumWithInnerClass> setWithSubclass = EnumSet
                .noneOf(EnumWithInnerClass.class);
        setWithSubclass.add(EnumWithInnerClass.a);
        result = set.containsAll(setWithSubclass);
        assertFalse("Should return false", result); //$NON-NLS-1$
    }
}
