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

package javax.security.auth.callback.serialization;

import javax.security.auth.callback.ChoiceCallback;

import org.apache.harmony.security.test.SerializationTest;


/**
 * Serialization test for ChoiceCallback class
 */

public class SerChoiceCallbackTest extends SerializationTest {
  

    public static void main(String[] args) {
        junit.textui.TestRunner.run(SerChoiceCallbackTest.class);
    }

    protected Object[] getData() {

        String prompt = "prompt";
        int defaultChoice = 1;
        String[] choices = {"AAA", "BBB"};
        
        return new Object[] {new ChoiceCallback(prompt, choices, defaultChoice, true)};
    }

    protected void assertDeserialized(Object golden, Object test) {
        assertTrue(golden instanceof ChoiceCallback);
        assertEquals(((ChoiceCallback) golden).getPrompt(),
                ((ChoiceCallback) test).getPrompt());
        assertEquals(((ChoiceCallback) golden).getDefaultChoice(),
                ((ChoiceCallback) test).getDefaultChoice());
    }
    
}