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

import java.awt.Panel.AccessibleAWTPanel;

import javax.accessibility.AccessibleContext;
import javax.accessibility.AccessibleRole;

import junit.framework.TestCase;

/**
 * AccessibleAWTPanelTest
 */
public class AccessibleAWTPanelTest extends TestCase {
    Panel panel;
    AccessibleContext ac;

    public static void main(String[] args) {
        junit.textui.TestRunner.run(AccessibleAWTPanelTest.class);
    }

    /*
     * @see TestCase#setUp()
     */
    protected void setUp() throws Exception {
        super.setUp();
        panel = new Panel();
        ac = panel.getAccessibleContext();
    }

    /*
     * @see TestCase#tearDown()
     */
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    public final void testGetAccessibleRole() {
        assertSame(AccessibleRole.PANEL, ac.getAccessibleRole());
    }

    public final void testAccessibleAWTPanel() {
        assertTrue(ac instanceof AccessibleAWTPanel);
    }

}
