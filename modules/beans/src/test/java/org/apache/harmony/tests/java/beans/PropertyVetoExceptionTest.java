/*
 *  Copyright 2006 The Apache Software Foundation or its licensors, as applicable.
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

package org.apache.harmony.tests.java.beans;

import java.beans.Beans;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyVetoException;

import junit.framework.TestCase;

public class PropertyVetoExceptionTest extends TestCase {

    public void testPropertyVetoExceptionMessage() {
        // Regression for HARMONY-235 (tracking the similar bug)
        PropertyChangeEvent event = new PropertyChangeEvent(new Beans(),
                "propertyName", "oldValue", "newValue");

        String message = "test message";

        PropertyVetoException e = new PropertyVetoException(message, event);
        assertEquals(message, e.getMessage());
    }
}
