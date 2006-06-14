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

import java.security.*;
import java.util.Collection;
import java.util.Enumeration;
import java.util.HashSet;

import org.apache.harmony.security.tests.support.SerializationTest;


/**
 * Serialization tests for <code>Permissions</code>
 * 
 */

public class PermissionsTest extends SerializationTest {

    /**
     * Returns array containing empty and non-empty Permissions.
     */
    protected Object[] getData() {
        Permissions ps = new Permissions();
        Permission[] arr = new Permission[] {
            new AllPermission(),
            new SecurityPermission("abc"),
            new FakePermission("jkabhj"),
            new UnresolvedPermission("131234", null, null, null),
            new UnresolvedPermission("KUJKHVKJgyuygjhb", "xcv456", "26r ytf",
                new java.security.cert.Certificate[0]), };

        for (int i = 0; i < arr.length; i++) {
            ps.add(arr[i]);
        }
        return new Object[] {
            new Permissions(), ps };
    }
}

/**
 * Custom Permission implementation, intended for testing.
 */
class FakePermission extends BasicPermission {

    public FakePermission(String name) {
        super(name);
    }

    public PermissionCollection newPermissionCollection() {
        return null;
    }
}