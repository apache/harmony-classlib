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

package javax.security.sasl;

import com.openintel.drl.security.test.PerformanceTest;;

/**
 * Tests for constructors and methods of RealmCallback class
 * 
 */

public class RealmCallbackTest extends PerformanceTest {

    public static void main(String[] args) {
        junit.textui.TestRunner.run(RealmCallbackTest.class);
    }

    public RealmCallbackTest(String arg0) {
        super(arg0);
    }
    
    private static final String[] prompts = {
            "Prompts",
            "Another prompt",
            "Long String for prompt Long String for prompt Long String for prompt Long String for prompt Long String for prompt Long String for prompt Long String for prompt Long String for prompt Long String for prompt Long String for prompt Long String for prompt Long String for prompt Long String for prompt Long String for prompt Long String for prompt Long String for prompt Long String for prompt Long String for prompt Long String for prompt Long String for prompt Long String for prompt" };

    /**
     * Test for
     * <code>RealmCallback(String prompt, String defaultRealmInfo)</code>
     * constructor 
     * Assertion: throws IllegalArgumentException if parameters are
     * null or empty String
     */
    public void test01() {
        try {
            new RealmCallback(null, "def");
            fail("IllegalArgumentException should be thrown for null prompt");
        } catch (IllegalArgumentException e) {
        }
        try {
            new RealmCallback("", "def");
            fail("IllegalArgumentException should be thrown for empty prompt");
        } catch (IllegalArgumentException e) {
        }
        try {
            new RealmCallback("prompt", null);
            fail("IllegalArgumentException should be thrown for null default info");
        } catch (IllegalArgumentException e) {
        }
        try {
            new RealmCallback("prompt", "");
            fail("IllegalArgumentException should be thrown for empty default info");
        } catch (IllegalArgumentException e) {
        }
    }

    /**
     * Test for
     * <code>RealmCallback(String prompt, String defaultRealmInfo)</code>
     * constructor Assertion: creates instance of RealmCallback object
     */
    public void test02() {
        String[] defInfo = {
                "DefaultRealmInfo",
                "Another default realm info",
                "Long string for default realm info Long string for default realm info Long string for default realm info Long string for default realm info Long string for default realm info Long string for default realm info Long string for default realm info Long string for default realm info Long string for default realm info Long string for default realm info Long string for default realm info Long string for default realm info Long string for default realm info Long string for default realm info Long string for default realm info Long string for default realm info " };
        RealmCallback rCB;
        StringBuffer sb = new StringBuffer("");
        String ss;
        for (int i = 0; i < prompts.length; i++) {
            for (int j = 0; j < prompts.length; j++) {
                rCB = new RealmCallback(prompts[i], defInfo[j]);
                assertTrue("Incorrect object", rCB instanceof RealmCallback);
                assertEquals("Incoorect default info", rCB.getDefaultText(),
                        defInfo[j]);
                assertEquals("Incorrect prompt", rCB.getPrompt(), prompts[i]);
                assertNull("Not null text", rCB.getText());
                sb.replace(0, sb.length(), prompts[i]);
                sb.append(defInfo[j]);
                ss = sb.toString();
                rCB.setText(ss);
                assertEquals("Incorrect text", rCB.getText(), ss);
                rCB.setText(null);
                assertNull("Not null text", rCB.getText());
            }
        }
    }

    /**
     * Test for <code>RealmCallback(String prompt</code> constructor
     * Assertion: throws IllegalArgumentException if parameter is null or empty
     * string
     */
    public void test03() {
        try {
            new RealmCallback(null);
            fail("IllegalArgumentException should be thrown for null prompt");
        } catch (IllegalArgumentException e) {
        }
        try {
            new RealmCallback("");
            fail("IllegalArgumentException should be thrown for empty prompt");
        } catch (IllegalArgumentException e) {
        }
    }

    /**
     * Test for <code>RealmCallback(String prompt)</code> constructor
     * Assertion: creates instance of RealmCallback object
     */
    public void test04() {
        RealmCallback rCB;
        StringBuffer sb = new StringBuffer("");
        String ss;
        for (int i = 0; i < prompts.length; i++) {
            rCB = new RealmCallback(prompts[i]);
            assertTrue("Incorrect object", rCB instanceof RealmCallback);
            assertNull("Incoorect default info", rCB.getDefaultText());
            assertEquals("Incorrect prompt", rCB.getPrompt(), prompts[i]);
            assertNull("Not null text", rCB.getText());
            sb = new StringBuffer(prompts[i]);
            sb.replace(0, sb.length(), prompts[i]);
            ss = sb.toString();
            rCB.setText(ss);
            assertEquals("Incorrect text", rCB.getText(), ss);
            rCB.setText(null);
            assertNull("Not null text", rCB.getText());
        }
        }
}