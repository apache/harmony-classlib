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
* @author Maxim V. Makarov
* @version $Revision$
*/

package org.apache.harmony.auth.tests.javax.security.auth;

import javax.security.auth.DestroyFailedException;

import junit.framework.TestCase;

/**
 * Tests DestroyFailedException class
 */
public class DestroyFailedExceptionTest extends TestCase {

    /**
     * @tests javax.security.auth.DestroyFailedException#DestroyFailedException()
     */
    public final void testCtor1() {
        assertNull(new DestroyFailedException().getMessage());
    }

    /**
     * @tests javax.security.auth.DestroyFailedException#DestroyFailedException(
     *        java.lang.String)
     */
    public final void testCtor2() {
        assertNull(new DestroyFailedException(null).getMessage());

        String message = "";
        assertSame(message, new DestroyFailedException(message).getMessage());

        message = "message";
        assertSame(message, new DestroyFailedException(message).getMessage());
    }

    public static void main(String[] args) {
        junit.textui.TestRunner.run(DestroyFailedExceptionTest.class);
    }
}
