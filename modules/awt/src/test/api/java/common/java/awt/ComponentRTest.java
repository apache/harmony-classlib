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

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import junit.framework.TestCase;

public class ComponentRTest extends TestCase {
    private static PropertyChangeEvent lastEvent;

    private static class PropertyChangeListenerImpl
    implements PropertyChangeListener {
        public void propertyChange(PropertyChangeEvent pce) {
            lastEvent = pce;
        }

    }


    public final void testFirePropertyChange() {
        Component comp = new Component(){
            public void firePropertyChange(String s, Object o1, Object o2) {
                // consuming object property change
             }

        };
        comp.addPropertyChangeListener(new PropertyChangeListenerImpl());
        assertNull(lastEvent);
        comp.setFocusable(false);
        assertNotNull("boolean property change was fired", lastEvent);
        Object newVal = lastEvent.getNewValue();
        Object oldVal = lastEvent.getOldValue();
        assertTrue("new value is Boolean", newVal instanceof Boolean);
        assertFalse(((Boolean)newVal).booleanValue());
        assertTrue(((Boolean)oldVal).booleanValue());

    }
    
    public final void testGetMinimumSize() {
        final Component comp = new Component() {
        };
        Dimension size = new Dimension(100, 100);
        Dimension defSize = new Dimension(1, 1);
        comp.setSize(size);
        assertEquals(size, comp.getMinimumSize());
        comp.addNotify();
        assertEquals(defSize, comp.getMinimumSize());
        size.setSize(13, 13);
        comp.setSize(size);
        assertEquals(defSize, comp.getMinimumSize());
        comp.removeNotify();
        assertEquals(size, comp.getMinimumSize());
    }

    public static void main(String[] args) {
        junit.textui.TestRunner.run(ComponentRTest.class);
    }

}
