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

import java.util.EnumMap;

import junit.framework.TestCase;

public class EnumMapTest extends TestCase {
    enum Size {
        Small, Middle, Big {};
    }

    enum Color {
        Red, Green, Blue {};
    }

    enum Empty {
        //Empty
    }

    /**
     * @tests java.util.EnumMap#EnumMap(Class)
     */
    @SuppressWarnings("unchecked")
    public void test_ConstructorLjava_lang_Class() {
        try {
            new EnumMap((Class) null);
            fail("Expected NullPointerException"); //$NON-NLS-1$
        } catch (NullPointerException e) {
            // Expected
        }
        
        try {
            new EnumMap(Size.Big.getClass());
            fail("Expected NullPointerException"); //$NON-NLS-1$
        } catch (NullPointerException e) {
            // Expected
        }

        try {
            new EnumMap(Integer.class);
            fail("Expected NullPointerException"); //$NON-NLS-1$
        } catch (NullPointerException e) {
            // Expected
        }

        assertNotNull(new EnumMap<Color, Double>(Color.class));
        
        assertNotNull(new EnumMap<Empty, Double>(Empty.class));

        assertNotNull(new EnumMap(Size.class));
        
        assertNotNull(new EnumMap(Size.Middle.getClass()));
        
    }

    /**
     * Sets up the fixture.
     */
    @Override
    protected void setUp() throws Exception {
        super.setUp();
    }

    /**
     * Tears down the fixture.
     */
    @Override
    protected void tearDown() throws Exception{
        super.tearDown();
    }
}
