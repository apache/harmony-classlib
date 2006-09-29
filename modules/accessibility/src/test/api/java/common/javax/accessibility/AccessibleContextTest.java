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
 * @author Dennis Ushakov
 * @version $Revision$
 */

package javax.accessibility;

import java.awt.IllegalComponentStateException;
import java.util.Locale;

import javax.swing.BasicSwingTestCase;

public class AccessibleContextTest extends BasicSwingTestCase {
    private AccessibleContext context;
    private PropertyChangeController propertyController;

    public void setUp() {
        context = new AccessibleContext() {
            public AccessibleRole getAccessibleRole() {
                    return null;
                };
            public AccessibleStateSet getAccessibleStateSet() {
                    return null;
                };
            public int getAccessibleIndexInParent() {
                    return 0;
                };
            public int getAccessibleChildrenCount() {
                    return 0;
                };
            public Accessible getAccessibleChild(int i) {
                    return null;
                };
            public Locale getLocale() throws IllegalComponentStateException {
                    return Locale.ENGLISH;
                };
        };
        propertyController = new PropertyChangeController(/*true*/);
        context.addPropertyChangeListener(propertyController);
    }

    public void tearDown() {
        context = null;
    }

    public void testSetGetAccessibleName() {
        String name = "componentName";
        context.setAccessibleName(name);
        assertTrue(propertyController.isChanged(AccessibleContext.ACCESSIBLE_NAME_PROPERTY));
        assertEquals(name, context.getAccessibleName());
        assertEquals(name, context.accessibleName);
    }

    public void testSetGetAccessibleDescription() {
        String description = "componentDescription";
        context.setAccessibleDescription(description);
        assertTrue(propertyController.isChanged(AccessibleContext.ACCESSIBLE_DESCRIPTION_PROPERTY));
        assertEquals(description, context.getAccessibleDescription());
        assertEquals(description, context.accessibleDescription);
    }

    public void testSetGetAccessibleParent() {
        Accessible parent = new Accessible() {
            private String name = "parentName";

            public AccessibleContext getAccessibleContext() {
                return null;
            }

            public String toString() {
                return name;
            }
        };
        context.setAccessibleParent(parent);
        assertEquals(parent, context.getAccessibleParent());
        assertEquals(parent, context.accessibleParent);
        assertFalse(propertyController.isChanged());
    }

    public void testGetAccessibleOthers() {
        assertNull(context.getAccessibleAction());
        assertNull(context.getAccessibleComponent());
        assertNull(context.getAccessibleSelection());
        assertNull(context.getAccessibleText());
        assertNull(context.getAccessibleEditableText());
        assertNull(context.getAccessibleValue());
        assertNull(context.getAccessibleIcon());
        assertSame(context.getAccessibleRelationSet(), context.getAccessibleRelationSet());
        assertNull(context.getAccessibleTable());
        assertFalse(propertyController.isChanged());
    }
}

