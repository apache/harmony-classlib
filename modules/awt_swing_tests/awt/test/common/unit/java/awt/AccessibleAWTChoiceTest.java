/*
 *  Copyright 2005 - 2006 The Apache Software Software Foundation or its licensors, as applicable.
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

import java.awt.Choice.AccessibleAWTChoice;

import javax.accessibility.AccessibleContext;
import javax.accessibility.AccessibleRole;

import junit.framework.TestCase;

/**
 * AccessibleAWTChoice
 */
public class AccessibleAWTChoiceTest extends TestCase {
    Choice choice;
    AccessibleContext ac;

    public static void main(String[] args) {
        junit.textui.TestRunner.run(AccessibleAWTChoiceTest.class);
    }

    /*
     * @see TestCase#setUp()
     */
    protected void setUp() throws Exception {
        super.setUp();
        choice = new Choice();
        ac = choice.getAccessibleContext();
        assertNotNull(ac);
    }

    /*
     * @see TestCase#tearDown()
     */
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    public final void testGetAccessibleAction() {
        assertTrue(ac.getAccessibleAction() instanceof AccessibleAWTChoice);
    }

    public final void testGetAccessibleRole() {
        assertSame(AccessibleRole.COMBO_BOX, ac.getAccessibleRole());
    }

    public final void testAccessibleAWTChoice() {
        assertNotNull(choice.new AccessibleAWTChoice());
    }

    public final void testGetAccessibleActionCount() {
        assertEquals(0, ac.getAccessibleAction().getAccessibleActionCount());
    }

    public final void testDoAccessibleAction() {
        assertFalse(ac.getAccessibleAction().doAccessibleAction(0));
    }

    public final void testGetAccessibleActionDescription() {
        assertNull(ac.getAccessibleAction().getAccessibleActionDescription(0));
    }

}
