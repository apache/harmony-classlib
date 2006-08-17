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
     * @tests java.util.EnumMap#put(Object,Object)
     */
    @SuppressWarnings( { "unchecked", "boxing" })
    public void test_putLjava_lang_ObjectLjava_lang_Object() {
        EnumMap enumSizeMap = new EnumMap(Size.class);
        try {
            enumSizeMap.put(Color.Red, 2);
            fail("Expected ClassCastException"); //$NON-NLS-1$
        } catch (ClassCastException e) {
            // Expected
        }
        assertNull("Return non-null for non mapped key", enumSizeMap.put( //$NON-NLS-1$
                Size.Small, 1));

        EnumMap enumColorMap = new EnumMap<Color, Double>(Color.class);
        try {
            enumColorMap.put(Size.Big, 2);
            fail("Expected ClassCastException"); //$NON-NLS-1$
        } catch (ClassCastException e) {
            // Expected
        }
        try {
            enumColorMap.put(null, 2);
            fail("Expected NullPointerException"); //$NON-NLS-1$
        } catch (NullPointerException e) {
            // Expected
        }
        assertNull("Return non-null for non mapped key", enumColorMap.put( //$NON-NLS-1$
                Color.Green, 2));
        assertEquals("Return wrong value", 2, enumColorMap.put(Color.Green, //$NON-NLS-1$
                new Double(4)));
        assertEquals("Return wrong value", new Double(4), enumColorMap.put( //$NON-NLS-1$
                Color.Green, new Integer("3")));//$NON-NLS-1$
        assertEquals("Return wrong value", new Integer("3"), enumColorMap.put( //$NON-NLS-1$//$NON-NLS-2$
                Color.Green, null));
        Float f = new Float("3.4");//$NON-NLS-1$
        assertNull("Return non-null for non mapped key", enumColorMap.put( //$NON-NLS-1$
                Color.Green, f));
        assertNull("Return non-null for non mapped key", enumColorMap.put( //$NON-NLS-1$
                Color.Blue, 2));
        assertEquals("Return wrong value", 2, enumColorMap.put(Color.Blue, //$NON-NLS-1$
                new Double(4)));
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
