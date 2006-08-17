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

import junit.framework.TestCase;

public class EnumSetTest extends TestCase {

    enum EnumWithAllInnerClass {
        a {},
        b {},
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
}
