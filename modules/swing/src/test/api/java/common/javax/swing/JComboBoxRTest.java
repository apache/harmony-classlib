/*
 *  Licensed to the Apache Software Foundation (ASF) under one or more
 *  contributor license agreements.  See the NOTICE file distributed with
 *  this work for additional information regarding copyright ownership.
 *  The ASF licenses this file to You under the Apache License, Version 2.0
 *  (the "License"); you may not use this file except in compliance with
 *  the License.  You may obtain a copy of the License at
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
 * @author Anton Avtamonov
 * @version $Revision$
 */
package javax.swing;

import junit.framework.TestCase;

public class JComboBoxRTest extends TestCase {
    public void testJComboBox() throws Exception {
        new JComboBox();
    }

    public void testRemoveAllItems() throws Exception {
        JComboBox cb = new JComboBox(new String[] {"1", "2", "4"});
        assertEquals(3, cb.getItemCount());

        cb.removeAllItems();
        assertEquals(0, cb.getItemCount());

        cb.addItem("new");
        assertEquals(1, cb.getItemCount());
    }

    public void testKeyboardActionsEnabled() throws Exception {
        JComboBox cb = new JComboBox(new String[] {"1", "2", "4"});
        checkActionState(cb, "hidePopup", false);
        checkActionState(cb, "enterPressed", false);
        checkActionState(cb, "selectPrevious", false);

        checkActionState(cb, "togglePopup", true);
        checkActionState(cb, "spacePopup", true);
    }


    private void checkActionState(final JComboBox cb, final String actionName, final boolean expectedState) {
        Action action = cb.getActionMap().get(actionName);
        assertNotNull(action);
        assertEquals(expectedState, action.isEnabled());
    }
}
