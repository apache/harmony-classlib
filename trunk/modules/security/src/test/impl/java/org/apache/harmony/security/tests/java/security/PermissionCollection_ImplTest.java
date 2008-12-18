/*
 *  Licensed to the Apache Software Foundation (ASF) under one or more
 *  contributor license agreements.  See the NOTICE file distributed with
 *  this work for additional information regarding copyright ownership.
 *  The ASF licenses this file to You under the Apache License, Version 2.0
 *  (the "License"); you may not use this file except in compliance with
 *  the License.  You may obtain a copy of the License at
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

package org.apache.harmony.security.tests.java.security;
import java.security.Permission;
import java.security.PermissionCollection;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Enumeration;

import junit.framework.TestCase;

/**
 * Tests for <code>PermissionCollection</code>
 * 
 */

public class PermissionCollection_ImplTest extends TestCase {
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
        assertEquals("no elements", superString + " (\n)\n", pc.toString());
        
        // several elements
        pc = new RealPermissionCollection(Arrays.asList(new Object[]{"aaa", "bbb", "ccc"}));
        superString = pc.getClass().getName() + "@" + Integer.toHexString(pc.hashCode());
        assertEquals("several elements", superString + " (\n aaa\n bbb\n ccc\n)\n", pc.toString());
    }
   
}
