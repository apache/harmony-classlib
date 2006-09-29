/*
 *  Copyright 2005 - 2006 The Apache Software Foundation or its licensors, as applicable.
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
 * @author Dmitry A. Durnev
 * @version $Revision$
 */
package java.awt;

import java.awt.TextArea.AccessibleAWTTextArea;

import javax.accessibility.AccessibleState;

import junit.framework.TestCase;

public class AccessibleAWTTextAreaTest extends TestCase {
    TextArea textArea;
    AccessibleAWTTextArea aTextArea;

    public static void main(String[] args) {
        junit.textui.TestRunner.run(AccessibleAWTTextAreaTest.class);
    }

    protected void setUp() throws Exception {
        super.setUp();
        textArea = new TextArea();
        aTextArea = textArea.new AccessibleAWTTextArea();
        assertTrue(textArea.getAccessibleContext()
                   instanceof AccessibleAWTTextArea);
    }

    protected void tearDown() throws Exception {
        super.tearDown();
    }

    /*
     * Test method for 'java.awt.TextArea.AccessibleAWTTextArea.getAccessibleStateSet()'
     */
    public void testGetAccessibleStateSet() {
        AccessibleState state = AccessibleState.MULTI_LINE;
        assertTrue("text area is multi-line text",
                   aTextArea.getAccessibleStateSet().contains(state));

    }

    /*
     * Test method for 'java.awt.TextArea.AccessibleAWTTextArea.AccessibleAWTTextArea(TextArea)'
     */
    public void testAccessibleAWTTextArea() {
        assertTrue(aTextArea instanceof AccessibleAWTTextArea);

    }

}
