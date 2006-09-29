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
package javax.swing.event;

import java.awt.EventQueue;
import java.awt.event.MouseEvent;

import javax.swing.JComponent;
import javax.swing.JPanel;

import junit.framework.TestCase;

public class MouseInputAdapterTest extends TestCase {
    private TestAdapter adapter;

    public MouseInputAdapterTest(final String name) {
        super(name);
    }

    protected void setUp() throws Exception {
        adapter = new TestAdapter();
    }

    protected void tearDown() throws Exception {
        adapter = null;
    }


    public void testMouseInputAdapter() throws Exception {
        JComponent component = new JPanel();
        component.addMouseListener(adapter);
        component.dispatchEvent(new MouseEvent(component, MouseEvent.MOUSE_ENTERED, EventQueue.getMostRecentEventTime(), 0, 0, 0, 1, false));
        assertTrue(adapter.isEventOccured());
    }


    private class TestAdapter extends MouseInputAdapter {
        private boolean eventOccured;

        public void mouseEntered(final MouseEvent e) {
            eventOccured = true;
        }

        public boolean isEventOccured() {
            return eventOccured;
        }
    }
}
