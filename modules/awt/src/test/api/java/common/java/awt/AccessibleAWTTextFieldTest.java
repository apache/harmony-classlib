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

import java.awt.TextField.AccessibleAWTTextField;

import javax.accessibility.AccessibleState;

import junit.framework.TestCase;

public class AccessibleAWTTextFieldTest extends TestCase {
    TextField textField;
    AccessibleAWTTextField aTextField;

    public static void main(String[] args) {
        junit.textui.TestRunner.run(AccessibleAWTTextFieldTest.class);
    }

    protected void setUp() throws Exception {
        super.setUp();
        textField = new TextField();
        aTextField = textField.new AccessibleAWTTextField();
        assertTrue(textField.getAccessibleContext() instanceof AccessibleAWTTextField);
    }

    protected void tearDown() throws Exception {
        super.tearDown();
    }

    /*
     * Test method for 'java.awt.TextField.AccessibleAWTTextField.getAccessibleStateSet()'
     */
    public void testGetAccessibleStateSet() {
        AccessibleState state = AccessibleState.SINGLE_LINE;
        assertTrue("text field is a single-line text",
                   aTextField.getAccessibleStateSet().contains(state));
    }

    /*
     * Test method for 'java.awt.TextField.AccessibleAWTTextField.AccessibleAWTTextField(TextField)'
     */
    public void testAccessibleAWTTextField() {
        assertTrue(aTextField instanceof AccessibleAWTTextField);

    }

}
