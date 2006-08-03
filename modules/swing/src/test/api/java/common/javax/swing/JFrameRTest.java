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
 * @author Vadim L. Bogdanov
 * @version $Revision$
 */
package javax.swing;

import javax.swing.plaf.metal.MetalLookAndFeel;

public class JFrameRTest extends SwingTestCase {
    public JFrameRTest(final String name) {
        super(name);
    }

    public void testInitDecorations() throws Exception {
        UIManager.setLookAndFeel(new MetalLookAndFeel() {
            public boolean getSupportsWindowDecorations() {
                return false;
            }
        });

        JFrame.setDefaultLookAndFeelDecorated(true);
        JFrame f = new JFrame();
        assertFalse(f.isUndecorated());

        JDialog.setDefaultLookAndFeelDecorated(true);
        JDialog d = new JDialog();
        assertFalse(d.isUndecorated());
    }
}
