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

package javax.security.auth.callback;


import org.apache.harmony.security.test.PerformanceTest;

/**
 * Tests UnsupportedCallbackException class
 */

public class UnsupportedCallbackExceptionTest extends PerformanceTest {

    NameCallback nc = new NameCallback("prompt");
    
    public static void main(String[] args) {
        junit.textui.TestRunner.run(UnsupportedCallbackExceptionTest.class);
    }
    
    /**
     * Test for UnsupportedCallbackException(Callback c) ctor 
     */
    public final void testUnsupportedCallbackException_01() {
        UnsupportedCallbackException ce = new UnsupportedCallbackException(nc);
        assertEquals((Callback)nc, ce.getCallback());
        try {
            throw ce;
        }catch (Exception e){
            assertTrue(ce.equals(e));
            assertEquals(nc, ce.getCallback());
        }

    }

    /**
     * Test for UnsupportedCallbackException(Callback c, String msg) ctor 
     */
    public final void testUnsupportedCallbackException_02() {
        UnsupportedCallbackException ce = new UnsupportedCallbackException((Callback)nc, "message");
        assertEquals ("message", ce.getMessage());
        assertEquals((Callback)nc, ce.getCallback());
        try {
            throw ce;
        }catch (Exception e){
            assertTrue(ce.equals(e));
            assertEquals((Callback)nc, ce.getCallback());
        }

    }

    /**
     * Test for UnsupportedCallbackException(Callback c, String msg) ctor 
     * when callback and msg is null 
     */
    public final void testUnsupportedCallbackException_03() {
        UnsupportedCallbackException ce = new UnsupportedCallbackException(null, null);
        assertEquals (null, ce.getMessage());
        assertEquals(null, ce.getCallback());
        try {
            throw ce;
        }catch (Exception e){
            assertTrue(ce.equals(e));
        }
    }

}
