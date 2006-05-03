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

import org.apache.harmony.security.support.SerializationTest;


/**
 * Serialization tests for <code>Permission</code>
 * 
 */

public class PermissionTest extends SerializationTest {

    /**
     * @see com.intel.drl.test.SerializationTest#getData()
     */
    protected Object[] getData() {
        return new Object[] { new RealPermission(null),
                new RealPermission("IYF&*%^sd 43") };
    }

    protected void assertDeserialized(Object golden, Object test) {
        assertSame(golden.getClass(), test.getClass());
        assertEquals(((Permission) golden).getName(), ((Permission) test)
                .getName());
    }
}

// Bare extension to instantiate abstract Permission class
final class RealPermission extends Permission {

    public RealPermission(String name) {
        super(name);
    }

    public boolean equals(Object obj) {
        return false;
    }

    public String getActions() {
        return null;
    }

    public int hashCode() {
        return 0;
    }

    public boolean implies(Permission permission) {
        return false;
    }
}