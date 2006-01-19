/*
 *  Copyright 2005 The Apache Software Foundation or its licensors, as applicable.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

/**
* @author Alexey V. Varlamov
* @version $Revision$
*/

package java.security;

import java.util.*;

import org.apache.harmony.security.test.PerformanceTest;


/**
 * Tests for <code>PermissionCollection</code>
 * 
 */

public class PermissionCollectionTest extends PerformanceTest {

    public static void main(String[] args) {
        junit.textui.TestRunner.run(PermissionCollectionTest.class);
    }

    /**
     * Constructor for PermissionCollectionTest.
     * @param arg0
     */
    public PermissionCollectionTest(String arg0) {
        super(arg0);
    }
   
    // Bare extension to instantiate abstract PermissionCollection class
    private static final class RealPermissionCollection extends PermissionCollection
    {
        final private Collection col; 
        public RealPermissionCollection(Collection col)
        {
            this.col = col;            
        }
        
        public void add(Permission permission) {}
        
        public Enumeration elements() 
        {
            return col == null ? null : Collections.enumeration(col);
        }
        
        public boolean implies(Permission permission) 
        {
            return false;
        }
    }
    
    /** Test toString() transformation with different elements. */
    public void testToString()
    {
        // no elements
        PermissionCollection pc = new RealPermissionCollection(null);
        String superString = pc.getClass().getName() + "@" + Integer.toHexString(pc.hashCode());
        assertEquals("no elements", superString + " (\n)", pc.toString());
        
        // several elements
        pc = new RealPermissionCollection(Arrays.asList(new Object[]{"aaa", "bbb", "ccc"}));
        superString = pc.getClass().getName() + "@" + Integer.toHexString(pc.hashCode());
        assertEquals("several elements", superString + " (\n  aaa\n  bbb\n  ccc\n)", pc.toString());
    }
    
    /** Test read-only flag. Should be false by default and can be set once forever. */
    public void testReadOnly()
    {
        PermissionCollection pc = new RealPermissionCollection(null);
        assertFalse("should not be read-only by default", pc.isReadOnly());
        pc.setReadOnly();
        assertTrue("explicitly set read-only", pc.isReadOnly());
        pc.setReadOnly();
        assertTrue("more calls to setReadOnly() should not harm", pc.isReadOnly());
    }
}
