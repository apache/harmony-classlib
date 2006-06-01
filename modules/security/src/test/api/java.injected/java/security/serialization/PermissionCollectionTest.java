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

import java.security.Permission;
import java.security.PermissionCollection;
import java.util.Enumeration;

import org.apache.harmony.security.tests.support.SerializationTest;


/**
 * Serialization tests for <code>PermissionCollection</code>
 * 
 */

public class PermissionCollectionTest extends SerializationTest {

    /**
     * @see com.intel.drl.test.SerializationTest#getData()
     */
    protected Object[] getData() {
        return new Object[] { new RealPermissionCollection(false),
                new RealPermissionCollection(true) };
    }

    protected void assertDeserialized(Object golden, Object test) {
        assertSame(golden.getClass(), test.getClass());
        assertEquals(((PermissionCollection) golden).isReadOnly(),
                ((PermissionCollection) test).isReadOnly());
    }
}

// Bare extension to instantiate abstract PermissionCollection class
final class RealPermissionCollection extends PermissionCollection {

    public RealPermissionCollection(boolean readOnly) {
        if (readOnly) {
            setReadOnly();
        }
    }

    public void add(Permission permission) {}

    public Enumeration elements() {
        return null;
    }

    public boolean implies(Permission permission) {
        return false;
    }
}