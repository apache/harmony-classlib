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
 * @author Boris V. Kuznetsov
 * @version $Revision$
 */
package javax.net.ssl;

import junit.framework.TestCase;

/**
 * Tests for <code>SSLPermission</code> class constructors.
 *  
 */
public class SSLPermissionTest extends TestCase {

    /*
     * Class under test for void SSLPermission(String)
     */
    public void testSSLPermissionString() {
        SSLPermission p = new SSLPermission("name");
        if (p == null) {
            fail("null permission");
        }
    }

    /*
     * Class under test for void SSLPermission(String, String)
     */
    public void testSSLPermissionStringString() {
        SSLPermission p = new SSLPermission("name", "action");
        if (p == null) {
            fail("null permission");
        }
    }
}
