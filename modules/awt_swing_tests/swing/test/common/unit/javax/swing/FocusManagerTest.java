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
 * @author Anton Avtamonov
 * @version $Revision$
 */
package javax.swing;

import java.awt.Component;
import java.awt.Container;
import java.awt.DefaultFocusTraversalPolicy;
import java.awt.FocusTraversalPolicy;
import java.awt.KeyboardFocusManager;

import junit.framework.TestCase;

public class FocusManagerTest extends TestCase {
    public FocusManagerTest(final String name) {
        super(name);
    }

    protected void setUp() throws Exception {
        KeyboardFocusManager.getCurrentKeyboardFocusManager().setDefaultFocusTraversalPolicy(new TestPolicy());
    }

    public void testGetSetCurrentManager() throws Exception {
        FocusManager m = new FocusManager() {};
        FocusManager.setCurrentManager(m);
        assertEquals(m, KeyboardFocusManager.getCurrentKeyboardFocusManager());
    }

    public void testIsFocusManagerEnabled() throws Exception {
        assertTrue(FocusManager.isFocusManagerEnabled());
        assertFalse(KeyboardFocusManager.getCurrentKeyboardFocusManager().getDefaultFocusTraversalPolicy() instanceof DefaultFocusTraversalPolicy);

        FocusManager.disableSwingFocusManager();
        assertFalse(FocusManager.isFocusManagerEnabled());
        assertTrue(KeyboardFocusManager.getCurrentKeyboardFocusManager().getDefaultFocusTraversalPolicy() instanceof DefaultFocusTraversalPolicy);
    }


    private class TestPolicy extends FocusTraversalPolicy {
        public Component getComponentAfter(final Container a0, final Component a1) {
            return null;
        }

        public Component getComponentBefore(final Container a0, final Component a1) {
            return null;
        }

        public Component getDefaultComponent(final Container a0) {
            return null;
        }

        public Component getFirstComponent(final Container a0) {
            return null;
        }

        public Component getLastComponent(final Container a0) {
            return null;
        }
    }
}
