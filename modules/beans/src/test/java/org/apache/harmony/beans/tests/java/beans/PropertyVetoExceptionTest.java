/* Copyright 2005 The Apache Software Foundation or its licensors, as applicable
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.harmony.beans.tests.java.beans;

import java.beans.Beans;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyVetoException;
import java.io.IOException;

import junit.framework.TestCase;
import tests.util.SerializationTester;

/**
 * Unit test for class PropertyVetoException
 */
public class PropertyVetoExceptionTest extends TestCase {

	private PropertyChangeEvent event;

	protected void setUp() throws Exception {
		super.setUp();
		MockJavaBean myBean = new MockJavaBean("Bean_PropertyVetoExceptionTest");
		event = new PropertyChangeEvent(myBean, "propertyOne", "value_old",
				"value_one");
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public void testPropertyVetoException() {
		String message = "testPropertyVetoException";
		PropertyVetoException e = new PropertyVetoException(message, event);
		assertSame(message, e.getMessage());
		assertSame(event, e.getPropertyChangeEvent());
	}

	public void testPropertyVetoException_MessageNull() {
		String message = null;
		PropertyVetoException e = new PropertyVetoException(message, event);
		assertNull(e.getMessage());
		assertSame(event, e.getPropertyChangeEvent());
	}

	public void testPropertyVetoException_EventNull() {
		String message = "testPropertyVetoException";
		PropertyVetoException e = new PropertyVetoException(message, null);
		assertSame(message, e.getMessage());
		assertNull(e.getPropertyChangeEvent());
	}

	public void testSerializablization() throws IOException,
			ClassNotFoundException {
		String message = "testPropertyVetoException";
		PropertyVetoException e = new PropertyVetoException(message, event);
		assertSame(message, e.getMessage());
		assertSame(event, e.getPropertyChangeEvent());

		PropertyVetoException deserializedException = (PropertyVetoException) SerializationTester
				.getDeserilizedObject(e);

		assertEquals(message, deserializedException.getMessage());
		assertEquals(event.getNewValue(), deserializedException
				.getPropertyChangeEvent().getNewValue());
		assertEquals(event.getOldValue(), deserializedException
				.getPropertyChangeEvent().getOldValue());
		assertEquals(event.getPropertyName(), deserializedException
				.getPropertyChangeEvent().getPropertyName());
		assertEquals(event.getPropagationId(), deserializedException
				.getPropertyChangeEvent().getPropagationId());
		assertNull(deserializedException.getPropertyChangeEvent().getSource());
	}

	public void testSerializablization_Compatibility() throws Exception {
		String message = "testPropertyVetoException";
		PropertyVetoException e = new PropertyVetoException(message, event);
		assertSame(message, e.getMessage());
		assertSame(event, e.getPropertyChangeEvent());

		PropertyVetoException deserializedException = (PropertyVetoException) SerializationTester
				.readObject(e, "serialization/java/beans/PropertyVetoException.ser");

		assertEquals(message, deserializedException.getMessage());
		assertEquals(event.getNewValue(), deserializedException
				.getPropertyChangeEvent().getNewValue());
		assertEquals(event.getOldValue(), deserializedException
				.getPropertyChangeEvent().getOldValue());
		assertEquals(event.getPropertyName(), deserializedException
				.getPropertyChangeEvent().getPropertyName());
		assertEquals(event.getPropagationId(), deserializedException
				.getPropertyChangeEvent().getPropagationId());
		assertNull(deserializedException.getPropertyChangeEvent().getSource());
	}
    
    public void testPropertyVetoExceptionMessage() {
        // Regression for HARMONY-235 (tracking the similar bug)
        PropertyChangeEvent event = new PropertyChangeEvent(new Beans(),
                "propertyName", "oldValue", "newValue");

        String message = "test message";

        PropertyVetoException e = new PropertyVetoException(message, event);
        assertEquals(message, e.getMessage());
    }
}
