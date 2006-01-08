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

package java.security.serialization;

import java.security.PermissionCollection;
import java.util.*;

import com.openintel.drl.security.test.SerializationTest;


/**
 * Serialization tests for <code>BasicPermissionCollection</code>
 * 
 */

public class BasicPermissionCollectionTest extends SerializationTest {

    protected Object[] getData() 
    {
        PermissionCollection c1 = new RealBasicPermission("234").newPermissionCollection();
        PermissionCollection c2 = new RealBasicPermission("1.4.kjhgj.*").newPermissionCollection();
     	c2.add(new RealBasicPermission("1.4.kjhgj.sdlvjb3o4i578"));
     	c2.add(new RealBasicPermission("1.4"));
        PermissionCollection c3 = new RealBasicPermission("*").newPermissionCollection();
        c3.add(new RealBasicPermission("1.4.kjhgj.*"));
        return new Object[] {c1,c2,c3};
    }
    
    protected void assertDeserialized(Object golden, Object test) {
        assertSame(golden.getClass(), test.getClass());
        PermissionCollection c1 = (PermissionCollection)golden;
        PermissionCollection c2 = (PermissionCollection)test;
        Collection s1 = new HashSet();
        Collection s2 = new HashSet();
        for(Enumeration en = c1.elements(); en.hasMoreElements(); s1.add(en.nextElement())) {}
        for(Enumeration en = c2.elements(); en.hasMoreElements(); s2.add(en.nextElement())) {}
        assertEquals(s1, s2);
    }
}
