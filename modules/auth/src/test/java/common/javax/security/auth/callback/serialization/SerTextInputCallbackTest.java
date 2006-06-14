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

import java.io.Serializable;

import javax.security.auth.callback.TextInputCallback;

import org.apache.harmony.security.tests.support.SerializationTest;


/**
 * Serialization test for TextInputCallback class
 */

public class SerTextInputCallbackTest extends SerializationTest implements
        SerializationTest.SerializableAssert {

    public static void main(String[] args) {
        junit.textui.TestRunner.run(SerTextInputCallbackTest.class);
    }

    protected Object[] getData() {
        TextInputCallback t = new TextInputCallback("prmpt","defText");
        t.setText("new text");
        return new Object[] { new TextInputCallback("prompt","defaultTextInput"), t };
    }

    public void assertDeserialized(Serializable golden, Serializable test) {
        assertTrue(test instanceof TextInputCallback);
        assertEquals(((TextInputCallback) golden).getDefaultText(),
                ((TextInputCallback) test).getDefaultText());
        assertEquals(((TextInputCallback) golden).getPrompt(),
                ((TextInputCallback) test).getPrompt());
        assertEquals(((TextInputCallback) golden).getText(),
                ((TextInputCallback) test).getText());
    }
}