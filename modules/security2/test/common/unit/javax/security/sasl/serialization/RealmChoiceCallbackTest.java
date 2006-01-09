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

import javax.security.sasl.RealmChoiceCallback;
import com.openintel.drl.security.test.SerializationTest;

/**
 * Test for RealmChoiceCallback seialization
 * 
 */

public class RealmChoiceCallbackTest extends SerializationTest {

    public static String[] msgs = {
            "New String",
            "Another string",
            "Long string. Long string. Long string. Long string. Long string. Long string. Long string. Long string. Long string. Long string. Long string. Long string. Long string. Long string. Long string. Long string. Long string.",
            "t"};

    public static final int [] idx = {2, 3};
    protected Object[] getData() {
        Object [] oo = {
                new RealmChoiceCallback(msgs[0], msgs, 0, true),
                new RealmChoiceCallback(msgs[1], msgs, 1, true),
                new RealmChoiceCallback(msgs[1], msgs, 0, false),
                new RealmChoiceCallback(msgs[2], msgs, 0, false)

        };        
//       
        for (int i = 0; i < oo.length; i++) {
            RealmChoiceCallback rc = (RealmChoiceCallback)oo[i];           
            if (rc.allowMultipleSelections()) {
                rc.setSelectedIndexes(idx);
            } else {
                rc.setSelectedIndex(msgs.length - 1);
            }
        }
//                
        return oo;
    }

    protected void assertDeserialized(Object oref, Object otest) {
        RealmChoiceCallback ref = (RealmChoiceCallback) oref;
        RealmChoiceCallback test = (RealmChoiceCallback) otest;
        
        boolean all = ref.allowMultipleSelections();
        assertEquals(all, test.allowMultipleSelections());
        String prompt = ref.getPrompt();
        assertEquals(prompt, test.getPrompt());
        
        String [] ch = ref.getChoices();
        String [] tCh = test.getChoices();        
        assertEquals(ch.length, tCh.length);        
        for (int i = 0; i < ch.length; i++) {
            assertEquals(ch[i], tCh[i]);
        }
        assertEquals(ref.getDefaultChoice(), test.getDefaultChoice());
        int [] in = ref.getSelectedIndexes();
        int [] tIn = test.getSelectedIndexes();
//        assertNull("in is not null", in);            
//        assertNull("tIn is not null", tIn);
//        
        if (!all) {
            assertEquals("Incorrect length in ", in.length, 1);
            assertEquals("Incorrect length tIn ", tIn.length, 1);
            assertEquals("Incorrect index", in[0], tIn[0]);
        } else {
            assertEquals("Incorrect length", in.length, tIn.length);
            for (int i = 0; i < in.length; i++) {
                assertEquals(in[i], tIn[i]);
            }            
        }
//        
    }

    public static void main(String[] args) {
        junit.textui.TestRunner.run(RealmChoiceCallbackTest.class);
    }
}