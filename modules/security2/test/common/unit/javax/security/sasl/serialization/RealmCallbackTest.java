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
* @author Vera Y. Petrashkova
* @version $Revision$
*/

package javax.security.sasl.serialization;

import javax.security.sasl.RealmCallback;

import org.apache.harmony.security.test.SerializationTest;

/**
 * Test for RealmCallback seialization
 * 
 */

public class RealmCallbackTest extends SerializationTest {

    public static String[] msgs = {
            "New String",
            "Long string. Long string. Long string. Long string. Long string. Long string. Long string. Long string. Long string. Long string. Long string. Long string. Long string. Long string. Long string. Long string. Long string." };

    public static String addText = "This text was set to RealmCallback";
    
    protected Object[] getData() {
        Object [] oo = {
                new RealmCallback(msgs[0], msgs[1]),
                new RealmCallback(msgs[1], msgs[0]),
                new RealmCallback(msgs[1], msgs[1])
        };
        for (int i = 0; i < oo.length; i++) {
            ((RealmCallback)oo[i]).setText(addText);
        }
        return oo;
    }

    protected void assertDeserialized(Object oref, Object otest) {
        RealmCallback ref = (RealmCallback) oref;
        RealmCallback test = (RealmCallback) otest;
        String dText = ref.getDefaultText();
        String text = ref.getText();
        String prompt = ref.getPrompt();
        assertEquals(dText, test.getDefaultText());
        assertEquals(text, test.getText());
        assertEquals(prompt, test.getPrompt());

    }

    public static void main(String[] args) {
        junit.textui.TestRunner.run(RealmCallbackTest.class);
    }
}