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

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeSupport;
import java.util.EventObject;

import tests.util.SerializationTester;

import junit.framework.TestCase;

/**
 * Test class java.beans.PropertyChangeEvent.
 */
public class PropertyChangeEventTest extends TestCase {
	/*
	 * Test the constructor with normal parameters.
	 */
	public void testConstructor_Normal() {
		Object src = new Object();
		Object oldValue = new Object();
		Object newValue = new Object();

		PropertyChangeEvent event = new PropertyChangeEvent(src, "myPropName",
				oldValue, newValue);
		assertTrue(event instanceof EventObject);
		assertSame(src, event.getSource());
		assertEquals("myPropName", event.getPropertyName());
		assertSame(oldValue, event.getOldValue());
		assertSame(newValue, event.getNewValue());
		assertNull(event.getPropagationId());
	}

	/*
	 * Test the constructor with null parameters except the source parameter.
	 */
	public void testConstructor_Null() {
		Object src = new Object();
		PropertyChangeEvent event = new PropertyChangeEvent(src, null, null,
				null);
		assertSame(src, event.getSource());
		assertEquals(null, event.getPropertyName());
		assertSame(null, event.getOldValue());
		assertSame(null, event.getNewValue());
		assertNull(event.getPropagationId());
	}

	/*
	 * Test the constructor with null properties but non-null old and new
	 * values.
	 */
	public void testConstructor_NullProperty() {
		Object src = new Object();
		Object oldValue = new Object();
		Object newValue = new Object();
		PropertyChangeEvent event = new PropertyChangeEvent(src, null,
				oldValue, newValue);
		assertSame(src, event.getSource());
		assertEquals(null, event.getPropertyName());
		assertSame(oldValue, event.getOldValue());
		assertSame(newValue, event.getNewValue());
		assertNull(event.getPropagationId());
	}

	/*
	 * Test the constructor with null source parameter.
	 */
	public void testConstructor_NullSrc() {
		try {
			new PropertyChangeEvent(null, "prop", new Object(), new Object());
			fail("Should throw IllegalArgumentException!");
		} catch (IllegalArgumentException ex) {
			// expected
		}
	}

	/*
	 * Test the method setPropagationId() with a normal value.
	 */
	public void testSetPropagationId_Normal() {
		Object src = new Object();
		Object oldValue = new Object();
		Object newValue = new Object();

		PropertyChangeEvent event = new PropertyChangeEvent(src, "myPropName",
				oldValue, newValue);
		assertNull(event.getPropagationId());

		Object pid = new Object();
		event.setPropagationId(pid);

		assertSame(src, event.getSource());
		assertEquals("myPropName", event.getPropertyName());
		assertSame(oldValue, event.getOldValue());
		assertSame(newValue, event.getNewValue());
		assertSame(pid, event.getPropagationId());
	}

	/*
	 * Test the method setPropagationId() with a null value.
	 */
	public void testSetPropagationId_Null() {
		Object src = new Object();
		Object oldValue = new Object();
		Object newValue = new Object();

		PropertyChangeEvent event = new PropertyChangeEvent(src, "myPropName",
				oldValue, newValue);
		assertNull(event.getPropagationId());

		// set null when already null
		event.setPropagationId(null);
		assertNull(event.getPropagationId());

		// set a non-null value
		Object pid = new Object();
		event.setPropagationId(pid);
		assertSame(src, event.getSource());
		assertEquals("myPropName", event.getPropertyName());
		assertSame(oldValue, event.getOldValue());
		assertSame(newValue, event.getNewValue());
		assertSame(pid, event.getPropagationId());

		// reset to null
		event.setPropagationId(null);
		assertNull(event.getPropagationId());
	}

	/*
	 * Test serialization/deserialization.
	 */
	public void testSerialization() throws Exception {
		Object src = new Object();
		Object oldValue = "oldValue";
		Object newValue = "newValue";

		PropertyChangeEvent event = new PropertyChangeEvent(src, "myPropName",
				oldValue, newValue);
		PropertyChangeEvent deEvent = (PropertyChangeEvent) SerializationTester
				.getDeserilizedObject(event);
		assertEquals(event.getNewValue(), deEvent.getNewValue());
		assertEquals(event.getOldValue(), deEvent.getOldValue());
		assertEquals(event.getPropagationId(), deEvent.getPropagationId());
		assertEquals(event.getPropertyName(), deEvent.getPropertyName());
	}

	/*
	 * Test serialization compatibility.
	 */
	public void testSerializationCompatibility() throws Exception {
		Object src = new Object();
		Object oldValue = "oldValue";
		Object newValue = "newValue";

		PropertyChangeEvent event = new PropertyChangeEvent(src, "myPropName",
				oldValue, newValue);
		PropertyChangeEvent deEvent = (PropertyChangeEvent) SerializationTester
				.readObject(event,
						"serialization/java/beans/PropertyChangeEvent.ser");
		assertEquals(event.getNewValue(), deEvent.getNewValue());
		assertEquals(event.getOldValue(), deEvent.getOldValue());
		assertEquals(event.getPropagationId(), deEvent.getPropagationId());
		assertEquals(event.getPropertyName(), deEvent.getPropertyName());
	}
}
