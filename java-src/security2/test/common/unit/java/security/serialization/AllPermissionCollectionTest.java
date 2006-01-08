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

import java.security.AllPermission;
import java.security.PermissionCollection;
import java.util.Enumeration;

import com.openintel.drl.security.test.SerializationTest;

/**
 * Serialization tests for <code>AllPermissionCollection</code>
 * 
 */

public class AllPermissionCollectionTest extends SerializationTest {

    protected Object[] getData() {
        PermissionCollection c1 = new AllPermission().newPermissionCollection();
        PermissionCollection c2 = new AllPermission().newPermissionCollection();
        c2.add(new AllPermission());
        return new Object[] { c1, c2 };
    }

    protected void assertDeserialized(Object golden, Object test) {
        assertSame(golden.getClass(), test.getClass());
        Enumeration e1 = ((PermissionCollection) golden).elements();
        Enumeration e2 = ((PermissionCollection) test).elements();
        assertEquals(e1.hasMoreElements(), e2.hasMoreElements());
    }
}