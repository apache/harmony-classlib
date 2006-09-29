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

import java.awt.Component;
import java.awt.Container;
import java.awt.FocusTraversalPolicy;
import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;

public class DefaultFocusManagerTest extends TestCase {
    private DefaultFocusManager focusManager;
    private List components;

    public DefaultFocusManagerTest(final String name) {
        super(name);
    }

    protected void setUp() throws Exception {
        components = new ArrayList();
        components.add(new JButton("1"));
        components.add(new JButton("2"));
        components.add(new JButton("3"));
        components.add(new JButton("4"));

        focusManager = new DefaultFocusManager();
        focusManager.setDefaultFocusTraversalPolicy(new TestFocusTraversalPolicy(components));
    }

    protected void tearDown() throws Exception {
        components = null;
        focusManager = null;
    }

    public void testGetComponentBefore() throws Exception {
        assertEquals(components.get(0), focusManager.getComponentBefore(null, (Component)components.get(1)));
        assertEquals(components.get(1), focusManager.getComponentBefore(null, (Component)components.get(2)));
        assertEquals(components.get(2), focusManager.getComponentBefore(null, (Component)components.get(3)));
        assertEquals(components.get(3), focusManager.getComponentBefore(null, (Component)components.get(0)));
    }

    public void testGetComponentAfter() throws Exception {
        assertEquals(components.get(0), focusManager.getComponentAfter(null, (Component)components.get(3)));
        assertEquals(components.get(1), focusManager.getComponentAfter(null, (Component)components.get(0)));
        assertEquals(components.get(2), focusManager.getComponentAfter(null, (Component)components.get(1)));
        assertEquals(components.get(3), focusManager.getComponentAfter(null, (Component)components.get(2)));
    }

    public void testGetLastComponent() throws Exception {
        assertEquals(components.get(3), focusManager.getLastComponent(null));
    }

    public void testGetFirstComponent() throws Exception {
        assertEquals(components.get(0), focusManager.getFirstComponent(null));
    }

    //TODO: Is not clear how this method should work
    public void testCompareTabOrder() throws Exception {
        assertEquals(false, focusManager.compareTabOrder((Component)components.get(1), (Component)components.get(2)));
    }



    private class TestFocusTraversalPolicy extends FocusTraversalPolicy {
        private List components;

        public TestFocusTraversalPolicy(final List components) {
            this.components = components;
        }

        public Component getComponentAfter(final Container focusCycleRoot, final Component component) {
            int index = components.indexOf(component);
            if (index == -1) {
                return null;
            }

            if (index == components.size() - 1) {
                return (Component)components.get(0);
            } else {
                return (Component)components.get(index + 1);
            }
        }

        public Component getComponentBefore(final Container focusCycleRoot, final Component component) {
            int index = components.indexOf(component);
            if (index == -1) {
                return null;
            }

            if (index == 0) {
                return (Component)components.get(components.size() - 1);
            } else {
                return (Component)components.get(index - 1);
            }
        }

        public Component getDefaultComponent(final Container focusCycleRoot) {
            return getFirstComponent(focusCycleRoot);
        }

        public Component getFirstComponent(final Container focusCycleRoot) {
            return (Component)components.get(0);
        }

        public Component getLastComponent(final Container focusCycleRoot) {
            return (Component)components.get(components.size() - 1);
        }
    }
}
