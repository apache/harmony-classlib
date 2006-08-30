/* Copyright 2004 The Apache Software Foundation or its licensors, as applicable
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

package org.apache.harmony.logging.tests.java.util.logging;

import java.util.logging.ErrorManager;

import junit.framework.TestCase;

public class ErrorManagerTest extends TestCase {

    public void test_errorStringStringint() {
        ErrorManager em = new ErrorManager();
        em.error(null, new NullPointerException(), ErrorManager.GENERIC_FAILURE);
        em.error("An error message.", null, ErrorManager.GENERIC_FAILURE);
        em.error(null, null, ErrorManager.GENERIC_FAILURE);
    }

    public void test_constants() {
        assertEquals(3, ErrorManager.CLOSE_FAILURE);
        assertEquals(2, ErrorManager.FLUSH_FAILURE);
        assertEquals(5, ErrorManager.FORMAT_FAILURE);
        assertEquals(0, ErrorManager.GENERIC_FAILURE);
        assertEquals(4, ErrorManager.OPEN_FAILURE);
        assertEquals(1, ErrorManager.WRITE_FAILURE);
    }

}
