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
 * @author Alexander T. Simbirtsev
 * @version $Revision$
 */
package javax.swing.text;

import java.awt.event.KeyEvent;

import junit.framework.TestCase;

public class JTextComponentRTest extends TestCase {

    protected void setUp() throws Exception {
        super.setUp();
    }

    protected void tearDown() throws Exception {
        super.tearDown();
    }

    public void testProcessKeyEvent() {
        class JMyTextComponent extends JTextComponent {
            public String getUIClassID() {
                return "TextFieldUI";
            }

            public void test(final KeyEvent event) {
                processKeyEvent(event);
            }
        };
        JMyTextComponent c = new JMyTextComponent();
        KeyEvent event = new KeyEvent(c, KeyEvent.KEY_TYPED, 0, 0, KeyEvent.VK_UNDEFINED, '\n');
        c.setKeymap(null);
        c.test(event);
    }
}
