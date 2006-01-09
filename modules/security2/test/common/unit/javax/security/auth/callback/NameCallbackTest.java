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
 * Tests NameCallback class
 */

public class NameCallbackTest extends PerformanceTest {

    NameCallback nc;

    public static void main(String[] args) {
        junit.textui.TestRunner.run(NameCallbackTest.class);
    }

    /**
     * Class under test for void NameCallback(String)
     */
    public final void testNameCallback_01() {
        nc = new NameCallback("prompt", "defaultName");
        assertEquals("prompt", nc.getPrompt());
        assertEquals("defaultName", nc.getDefaultName());
        nc.setName("Name");
        assertEquals("Name", nc.getName());
    }

    /**
     * Test for NameCallback(String p, String msg) when
     * prompt and message is null and empty. 
     */
    public final void testNameCallback_02() {
        try {
            nc = new NameCallback("", "DefaultName");
            fail("Prompt and DefaultName should not be empty");
        } catch (IllegalArgumentException e) {
        }
        try {
            nc = new NameCallback(null, "DefaultName");
            fail("Prompt and DefaultName should not null");
        } catch (IllegalArgumentException e) {
        }
        try {
            nc = new NameCallback("Prompt", "");
            fail("Prompt and DefaultName should not be empty");
        } catch (IllegalArgumentException e) {
        }
        try {
            nc = new NameCallback("Prompt", null);
            fail("Prompt and DefaultName should not null");
        } catch (IllegalArgumentException e) {
        }

    }

}