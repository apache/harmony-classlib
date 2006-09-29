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
 * @author Anton Avtamonov
 * @version $Revision$
 */
package javax.swing;

import java.awt.event.ActionListener;

public class JComponentRTest extends BasicSwingTestCase {
    public void testComponentInstantiation() throws Exception {
        Object result = (ActionListener)new JComboBox();
        result = JPanel.class;
    }

    public void testPaintDoubleBufferedForInvisibleComponent() throws Exception {
        JButton b = new JButton();
        b.paintDoubleBuffered(createTestGraphics());
    }

    public void testResetKeyboardActions() throws Exception {
        JComponent c = new JComponent() {};
        c.resetKeyboardActions();
    }
}
