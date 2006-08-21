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
        a, b,
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
}
