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
 * @author Dmitry A. Durnev
 * @version $Revision$
 */
package java.awt;

import java.awt.event.KeyEvent;

import junit.framework.TestCase;
@SuppressWarnings("serial")
public class KeyboardFocusManagerRTest extends TestCase {
    Robot robot;
    Frame f;
    KeyboardFocusManager kfm;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        robot = null;
        try {
            robot = new Robot();
        } catch (AWTException e) {
            fail();
        }
        f = new Frame();
        kfm = new DefaultKeyboardFocusManager();
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
        if (f != null) {
            f.dispose();
        }
    }

    @SuppressWarnings("deprecation")
    public final void testRedispatchEvent() {
        Component c1 = new Component(){};
        Component c2 = new Component(){};
        Panel p = new Panel();
        c1.setName("comp1");
        c2.setName("comp2");
        c1.setFocusable(true);
        c2.setFocusable(true);
        p.add(c1);
        p.add(c2);
        f.add(p);
        f.setSize(100, 100);
        f.show();
        waitFocus(c1);
        kfm = KeyboardFocusManager.getCurrentKeyboardFocusManager();
        assertSame(c1, kfm.getFocusOwner());
        KeyEvent ke = new KeyEvent(c1, KeyEvent.KEY_PRESSED, 0l, 0,
                                   KeyEvent.VK_TAB,
                                   KeyEvent.CHAR_UNDEFINED);
        kfm.redispatchEvent(c1, ke);
        robot.delay(200);
        assertSame(c2, kfm.getFocusOwner());
    }

    private void waitFocus(Component comp) {
        int time = 0;
        int timeout = 32;
        while (!comp.isFocusOwner() && (time < 30000)) {
            robot.delay(timeout);
            time += timeout;
            timeout <<= 1;
        }
        assertTrue("component has focus", comp.isFocusOwner());
    }

    public final void testSetDefaultFocusTraversalPolicy() {
        try {
            kfm.setDefaultFocusTraversalPolicy(null);
            fail("IllegalArgumentException was not thrown!");
        } catch (IllegalArgumentException iae) {
        }
    }

    @SuppressWarnings("deprecation")
    public final void testClearGlobalFocusOwner() {
        f.setSize(200, 200);
        f.show();
        waitFocus(f);
        kfm.clearGlobalFocusOwner();
        robot.delay(300);
        assertFalse("frame is not the focus owner", f.isFocusOwner());
        assertNull("focus owner is null", kfm.getFocusOwner());
        assertNull("permanent focus owner is null", kfm.getPermanentFocusOwner());
    }

    public static void main(String[] args) {
        junit.textui.TestRunner.run(KeyboardFocusManagerRTest.class);
    }

}
