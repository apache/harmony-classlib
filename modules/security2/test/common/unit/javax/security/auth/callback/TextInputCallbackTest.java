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

import com.openintel.drl.security.test.PerformanceTest;

/**
 * Tests TextInputCallback class
 */

public class TextInputCallbackTest extends PerformanceTest {

    TextInputCallback text = null;

    public static void main(String[] args) {
        junit.textui.TestRunner.run(TextInputCallbackTest.class);
    }

    /**
     * Test for TextInputCallback(String), TextInputCallback(String, String) ctors 
     */
    public final void testTextInputCallback_01() {
        text = new TextInputCallback("prompt");
        assertEquals("prompt", text.getPrompt());
        text = new TextInputCallback("prompt", "defaultText");
        assertEquals("prompt", text.getPrompt());
        assertEquals("defaultText", text.getDefaultText());
        text.setText("Text");
        assertEquals("Text", text.getText());

    }

    /**
     * Test for TextOutputCallback(String prompt,String defaultText) ctor
     * if prompt or defaultText is null or empty then expected IAE  
     */
    public final void testTextInputCallback_02() {
        try {
            text = new TextInputCallback("", "defaultText");
            fail("Prompt and DefaultText should not be empty");
        } catch (IllegalArgumentException e) {
        }
        try {
            text = new TextInputCallback(null);
            fail("Prompt and DefaultText should not be null");
        } catch (IllegalArgumentException e) {
        }
        try {
            text = new TextInputCallback(null, "defaultText");
            fail("Prompt and DefaultText should not be null");
        } catch (IllegalArgumentException e) {
        }
        
        try {
            text = new TextInputCallback("prompt", "");
            fail("Prompt and DefaultText should not be null");
        } catch (IllegalArgumentException e) {
        }

        try {
            text = new TextInputCallback("prompt", null);
            fail("Prompt and DefaultText should not be null");
        } catch (IllegalArgumentException e) {
        }

    }


}