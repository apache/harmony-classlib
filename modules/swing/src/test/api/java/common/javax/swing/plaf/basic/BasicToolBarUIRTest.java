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
 * @author Vadim L. Bogdanov
 * @version $Revision$
 */
package javax.swing.plaf.basic;

import javax.swing.JButton;
import javax.swing.JToolBar;
import javax.swing.SwingTestCase;

public class BasicToolBarUIRTest extends SwingTestCase {

    public BasicToolBarUIRTest(final String name) {
        super(name);
    }

    protected void setUp() throws Exception {
        super.setUp();
    }

    protected void tearDown() throws Exception {
        super.tearDown();
    }

    public void testNavigateFocusedComponent() {
        class MyButton extends JButton {
            public boolean requestedFocus;
            public void requestFocus() {
                requestedFocus = true;
                super.requestFocus();
            }
        }

        JToolBar toolbar = new JToolBar();
        BasicToolBarUI ui = new BasicToolBarUI();
        toolbar.setUI(ui);
        MyButton b1 = new MyButton();
        MyButton b2 = new MyButton();
        b2.setFocusable(false);
        toolbar.add(b1);
        toolbar.add(b2);
        ui.focusedCompIndex = 0;

        ui.navigateFocusedComp(BasicToolBarUI.EAST);
        assertFalse(b2.requestedFocus);
    }
}
