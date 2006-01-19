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
 * Tests TextOutputCallback class
 */

public class TextOutputCallbackTest extends PerformanceTest {

    TextOutputCallback text = null;
    
    public static void main(String[] args) {
        junit.textui.TestRunner.run(TextOutputCallbackTest.class);
    }

    /**
     * Test for TextOutputCallback(int msgType,String msg) ctor 
     */
    public final void testTextOutputCallback_01() {
        text = new TextOutputCallback(0, "message");
        assertEquals("message", text.getMessage());
        assertEquals(0, text.getMessageType());
    }

    /**
     * Test for TextOutputCallback(int msgType,String msg) ctor 
     */
    public final void testTextOutputCallback_02() {
        int[] m = { TextOutputCallback.INFORMATION, TextOutputCallback.WARNING,
                TextOutputCallback.ERROR };
        for (int i = 0; i < m.length; i++) {
            text = new TextOutputCallback(m[i], "message");
        }
    }

    /**
     * Test for TextOutputCallback(int msgType,String msg) ctor, 
     * if mgsType is not INFORMATION, WARNING or ERROR, then expected IAE 
     */
    public final void testTextOutputCallback_03() {
        try {
            text = new TextOutputCallback(5, "message");
            fail("messageType should be either INFORMATION, WARNING or ERROR");
        } catch (IllegalArgumentException e) {
        }
    }

    /**
     * Test for TextOutputCallback(int msgType,String msg) ctor, 
     * if msg is null or empty 
     */
    public final void testInit() {
        try {
            text = new TextOutputCallback(1, "");
            fail("Message should not be empty or null");
        } catch (IllegalArgumentException e) {
        }
        try {
            text = new TextOutputCallback(1, null);
            fail("Message should not be empty or null");
        } catch (IllegalArgumentException e) {
        }

    }

}