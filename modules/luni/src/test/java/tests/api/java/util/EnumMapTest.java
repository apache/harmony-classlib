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
import java.util.HashMap;
import java.util.Map;

import org.apache.harmony.luni.util.NotYetImplementedException;

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
    @SuppressWarnings({ "unchecked", "boxing" })
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

        EnumMap enumColorMap = new EnumMap<Color, Double>(Color.class);
        assertNull("Return non-null for non mapped key", enumColorMap.put( //$NON-NLS-1$
                Color.Green, 2));
        assertEquals("Get returned incorrect value for given key", 2, //$NON-NLS-1$
                enumColorMap.get(Color.Green));

        EnumMap enumEmptyMap = new EnumMap<Empty, Double>(Empty.class);
        try {
            enumEmptyMap.put(Color.Red, 2);
            fail("Expected ClassCastException"); //$NON-NLS-1$
        } catch (ClassCastException e) {
            // Expected
        }

        EnumMap enumSizeMap = new EnumMap(Size.class);
        assertNull("Return non-null for non mapped key", enumSizeMap.put( //$NON-NLS-1$
                Size.Big, 2));
        assertEquals("Get returned incorrect value for given key", 2, //$NON-NLS-1$
                enumSizeMap.get(Size.Big));
        try {
            enumSizeMap.put(Color.Red, 2);
            fail("Expected ClassCastException"); //$NON-NLS-1$
        } catch (ClassCastException e) {
            // Expected
        }
        
        enumSizeMap = new EnumMap(Size.Middle.getClass());
        assertNull("Return non-null for non mapped key", enumSizeMap.put( //$NON-NLS-1$
                Size.Small, 1));
        assertEquals("Get returned incorrect value for given key", 1, //$NON-NLS-1$
                enumSizeMap.get(Size.Small));
        try {
            enumSizeMap.put(Color.Red, 2);
            fail("Expected ClassCastException"); //$NON-NLS-1$
        } catch (ClassCastException e) {
            // Expected
        }
    }
    
    /**
     * @tests java.util.EnumMap#EnumMap(EnumMap)
     */
    @SuppressWarnings({ "unchecked", "boxing" })
    public void test_ConstructorLjava_util_EnumMap() {
        EnumMap enumMap;
        EnumMap enumColorMap = null;
        try {
            enumMap = new EnumMap(enumColorMap);
            fail("Expected NullPointerException"); //$NON-NLS-1$
        } catch (NullPointerException e) {
            // Expected
        }

        enumColorMap = new EnumMap<Color, Double>(Color.class);
        Double double1 = new Double(1);
        enumColorMap.put(Color.Green, 2);
        enumColorMap.put(Color.Blue, double1);
        
        enumMap = new EnumMap(enumColorMap);
        assertEquals("Constructor fails", 2, enumMap.get(Color.Green)); //$NON-NLS-1$
        assertSame("Constructor fails", double1, enumMap.get(Color.Blue)); //$NON-NLS-1$
        assertNull("Constructor fails", enumMap.get(Color.Red)); //$NON-NLS-1$
        enumMap.put(Color.Red, 1);
        assertEquals("Wrong value", 1, enumMap.get(Color.Red)); //$NON-NLS-1$

        try {
            enumMap.put(Size.Middle, 2);
            fail("Expected ClassCastException"); //$NON-NLS-1$
        } catch (ClassCastException e) {
            // Expected
        }
    }

    /**
     * @tests java.util.EnumMap#EnumMap(Map)
     */
    @SuppressWarnings({ "unchecked", "boxing" })
    public void test_ConstructorLjava_util_Map() {
        EnumMap enumMap;
        Map enumColorMap = null;
        try {
            enumMap = new EnumMap(enumColorMap);
            fail("Expected NullPointerException"); //$NON-NLS-1$
        } catch (NullPointerException e) {
            // Expected
        }
        enumColorMap = new EnumMap<Color, Double>(Color.class);
        enumMap = new EnumMap(enumColorMap);
        enumColorMap.put(Color.Blue, 3);
        enumMap = new EnumMap(enumColorMap);

        HashMap hashColorMap = null;
        try {
            enumMap = new EnumMap(hashColorMap);
            fail("Expected NullPointerException");//$NON-NLS-1$
        } catch (NullPointerException e) {
            // Expected
        }

        hashColorMap = new HashMap();
        try {
            enumMap = new EnumMap(hashColorMap);
            fail("Expected IllegalArgumentException"); //$NON-NLS-1$
        } catch (IllegalArgumentException e) {
            // Expected
        }

        hashColorMap.put(Color.Green, 2);
        enumMap = new EnumMap(hashColorMap);
        assertEquals("Constructor fails", 2, enumMap.get(Color.Green)); //$NON-NLS-1$
        assertNull("Constructor fails", enumMap.get(Color.Red)); //$NON-NLS-1$
        enumMap.put(Color.Red, 1);
        assertEquals("Wrong value", 1, enumMap.get(Color.Red)); //$NON-NLS-1$
        hashColorMap.put(Size.Big, 3);
        try {
            enumMap = new EnumMap(hashColorMap);
            fail("Expected ClassCastException"); //$NON-NLS-1$
        } catch (ClassCastException e) {
            // Expected
        }

        hashColorMap = new HashMap();
        hashColorMap.put(new Integer(1), 1);
        try {
            enumMap = new EnumMap(hashColorMap);
            fail("Expected ClassCastException"); //$NON-NLS-1$
        } catch (ClassCastException e) {
            // Expected
        }
    }
    
    /**
     * @tests java.util.EnumMap#clear()
     */
    @SuppressWarnings({ "unchecked", "boxing" })
    public void test_clear() {
        EnumMap enumSizeMap = new EnumMap(Size.class);
        enumSizeMap.put(Size.Small, 1);
        enumSizeMap.clear();
        assertNull("Failed to clear all elements", enumSizeMap.get(Size.Small)); //$NON-NLS-1$
    }
    
    /**
     * @tests java.util.EnumMap#get(Object)
     */
    @SuppressWarnings({ "unchecked", "boxing" })
    public void test_getLjava_lang_Object() {
        EnumMap enumSizeMap = new EnumMap(Size.class);
        assertNull("Get returned non-null for non mapped key", enumSizeMap //$NON-NLS-1$
                .get(Size.Big));
        enumSizeMap.put(Size.Big, 1);
        assertEquals("Get returned incorrect value for given key", 1, //$NON-NLS-1$
                enumSizeMap.get(Size.Big));
        
        assertNull("Get returned non-null for non mapped key", enumSizeMap //$NON-NLS-1$
                .get(Size.Small));
        assertNull("Get returned non-null for non existent key", enumSizeMap //$NON-NLS-1$
                .get(Color.Red));
        assertNull("Get returned non-null for non existent key", enumSizeMap //$NON-NLS-1$
                .get(new Integer(1)));
        assertNull("Get returned non-null for non existent key", enumSizeMap //$NON-NLS-1$
                .get(null));

        EnumMap enumColorMap = new EnumMap<Color, Double>(Color.class);
        assertNull("Get returned non-null for non mapped key", enumColorMap //$NON-NLS-1$
                .get(Color.Green));
        enumColorMap.put(Color.Green, 2);
        assertEquals("Get returned incorrect value for given key", 2, //$NON-NLS-1$
                enumColorMap.get(Color.Green));
        assertNull("Get returned non-null for non mapped key", enumColorMap //$NON-NLS-1$
                .get(Color.Blue));
        
        enumColorMap.put(Color.Green, new Double(4));
        assertEquals("Get returned incorrect value for given key", //$NON-NLS-1$
                new Double(4), enumColorMap.get(Color.Green));
        enumColorMap.put(Color.Green, new Integer("3"));//$NON-NLS-1$
        assertEquals("Get returned incorrect value for given key", new Integer( //$NON-NLS-1$
                "3"), enumColorMap.get(Color.Green));//$NON-NLS-1$
        enumColorMap.put(Color.Green, null);
        assertNull("Can not handle null value", enumColorMap.get(Color.Green)); //$NON-NLS-1$
        Float f = new Float("3.4");//$NON-NLS-1$
        enumColorMap.put(Color.Green, f);
        assertSame("Get returned incorrect value for given key", f, //$NON-NLS-1$
                enumColorMap.get(Color.Green));
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
     * @tests java.util.EnumMap#putAll(Map)
     */
    @SuppressWarnings({ "unchecked", "boxing" })
    public void test_putAllLjava_util_Map() {
        EnumMap enumColorMap = new EnumMap<Color, Double>(Color.class);
        enumColorMap.put(Color.Green, 2);

        // TODO: The EnumMap.entrySet() will be implemented later.
        try {
            EnumMap enumSizeMap = new EnumMap(Size.class);
            enumColorMap.putAll(enumSizeMap);

            enumSizeMap.put(Size.Big, 1);
            try {
                enumColorMap.putAll(enumSizeMap);
                fail("Expected ClassCastException"); //$NON-NLS-1$
            } catch (ClassCastException e) {
                // Expected
            }

            EnumMap enumColorMap1 = new EnumMap<Color, Double>(Color.class);
            enumColorMap1.put(Color.Blue, 3);
            enumColorMap.putAll(enumColorMap1);
            assertEquals("Get returned incorrect value for given key", 3, //$NON-NLS-1$
                    enumColorMap.get(Color.Blue));
            assertEquals("Wrong Size", 2, enumColorMap.size()); //$NON-NLS-1$

            enumColorMap = new EnumMap<Color, Double>(Color.class);
        } catch (NotYetImplementedException e) {
            // Expected
        }
        
        HashMap hashColorMap = null;
        try {
            enumColorMap.putAll(hashColorMap);
            fail("Expected NullPointerException"); //$NON-NLS-1$
        } catch (NullPointerException e) {
            // Expected
        }

        hashColorMap = new HashMap();
        enumColorMap.putAll(hashColorMap);

        hashColorMap.put(Color.Green, 2);
        enumColorMap.putAll(hashColorMap);
        assertEquals("Get returned incorrect value for given key", 2, //$NON-NLS-1$
                enumColorMap.get(Color.Green));
        assertNull("Get returned non-null for non mapped key", enumColorMap //$NON-NLS-1$
                .get(Color.Red));
        hashColorMap.put(Color.Red, new Integer(1));
        enumColorMap.putAll(hashColorMap);
        assertEquals("Get returned incorrect value for given key", new Integer( //$NON-NLS-1$
                2), enumColorMap.get(Color.Green));
        hashColorMap.put(Size.Big, 3);
        try {
            enumColorMap.putAll(hashColorMap);
            fail("Expected ClassCastException"); //$NON-NLS-1$
        } catch (ClassCastException e) {
            // Expected
        }

        hashColorMap = new HashMap();
        hashColorMap.put(new Integer(1), 1);
        try {
            enumColorMap.putAll(hashColorMap);
            fail("Expected ClassCastException"); //$NON-NLS-1$
        } catch (ClassCastException e) {
            // Expected
        }
    }
    
    /**
     * @tests java.util.EnumMap#remove(Object)
     */
    @SuppressWarnings({ "unchecked", "boxing" })
    public void test_removeLjava_lang_Object() {
        EnumMap enumSizeMap = new EnumMap(Size.class);
        assertNull("Remove of non-mapped key returned non-null", enumSizeMap //$NON-NLS-1$
                .remove(Size.Big));
        enumSizeMap.put(Size.Big, 3);
        enumSizeMap.put(Size.Middle, 2);

        assertNull("Get returned non-null for non mapped key", enumSizeMap //$NON-NLS-1$
                .get(Size.Small));
        assertEquals("Remove returned incorrect value", 3, enumSizeMap //$NON-NLS-1$
                .remove(Size.Big));
        assertNull("Get returned non-null for non mapped key", enumSizeMap //$NON-NLS-1$
                .get(Size.Big));
        assertNull("Remove of non-mapped key returned non-null", enumSizeMap //$NON-NLS-1$
                .remove(Size.Big));
        assertNull("Remove of non-existent key returned non-null", enumSizeMap //$NON-NLS-1$
                .remove(Color.Red));
        assertNull("Remove of non-existent key returned non-null", enumSizeMap //$NON-NLS-1$
                .remove(new Double(4)));
        assertNull("Remove of non-existent key returned non-null", enumSizeMap //$NON-NLS-1$
                .remove(null));

        EnumMap enumColorMap = new EnumMap<Color, Double>(Color.class);
        assertNull("Get returned non-null for non mapped key", enumColorMap //$NON-NLS-1$
                .get(Color.Green));
        enumColorMap.put(Color.Green, new Double(4));
        assertEquals("Remove returned incorrect value", new Double(4), //$NON-NLS-1$
                enumColorMap.remove(Color.Green));
        assertNull("Get returned non-null for non mapped key", enumColorMap //$NON-NLS-1$
                .get(Color.Green));
        enumColorMap.put(Color.Green, null);
        assertNull("Can not handle null value", enumColorMap //$NON-NLS-1$
                .remove(Color.Green));
        assertNull("Get returned non-null for non mapped key", enumColorMap //$NON-NLS-1$
                .get(Color.Green));
    }

    /**
     * @tests java.util.EnumMap#size()
     */
    @SuppressWarnings({ "unchecked", "boxing" })
    public void test_size() {
        EnumMap enumSizeMap = new EnumMap(Size.class);
        assertEquals("Wrong size", 0, enumSizeMap.size()); //$NON-NLS-1$
        enumSizeMap.put(Size.Small, 1);
        assertEquals("Wrong size", 1, enumSizeMap.size()); //$NON-NLS-1$
        enumSizeMap.put(Size.Small, 0);
        assertEquals("Wrong size", 1, enumSizeMap.size()); //$NON-NLS-1$
        try {
            enumSizeMap.put(Color.Red, 2);
            fail("Expected ClassCastException"); //$NON-NLS-1$
        } catch (ClassCastException e) {
            // Expected
        }
        assertEquals("Wrong size", 1, enumSizeMap.size()); //$NON-NLS-1$
        
        enumSizeMap.put(Size.Middle, null);
        assertEquals("Wrong size", 2, enumSizeMap.size()); //$NON-NLS-1$
        enumSizeMap.remove(Size.Big);
        assertEquals("Wrong size", 2, enumSizeMap.size()); //$NON-NLS-1$
        enumSizeMap.remove(Size.Middle);
        assertEquals("Wrong size", 1, enumSizeMap.size()); //$NON-NLS-1$
        enumSizeMap.remove(Color.Green);
        assertEquals("Wrong size", 1, enumSizeMap.size()); //$NON-NLS-1$

        EnumMap enumColorMap = new EnumMap<Color, Double>(Color.class);
        enumColorMap.put(Color.Green, 2);
        assertEquals("Wrong size", 1, enumColorMap.size()); //$NON-NLS-1$
        enumColorMap.remove(Color.Green);
        assertEquals("Wrong size", 0, enumColorMap.size()); //$NON-NLS-1$

        EnumMap enumEmptyMap = new EnumMap<Empty, Double>(Empty.class);
        assertEquals("Wrong size", 0, enumEmptyMap.size()); //$NON-NLS-1$
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
