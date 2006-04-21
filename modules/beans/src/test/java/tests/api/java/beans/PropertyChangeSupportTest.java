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

package tests.api.java.beans;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeListenerProxy;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import java.util.Arrays;

import junit.framework.TestCase;
import tests.util.SerializationTester;

/**
 * Test class PropertyeChangeSupport.
 */
public class PropertyChangeSupportTest extends TestCase {

	/*
	 * Test the constructor with a normal parameter.
	 */
	public void testConstructor_Normal() {
		Object src = new Object();
		new PropertyChangeSupport(src);
	}

	/*
	 * Test the method addPropertyChangeListener(PropertyeChangeListener) with a
	 * normal listener parameter.
	 */
	public void testAddPropertyChangeListener_PropertyChangeListener_Normal() {
		Object src = new Object();
		PropertyChangeSupport sup = new PropertyChangeSupport(src);
		PropertyChangeListener l1 = new MockPropertyChangeListener();
		PropertyChangeListener l2 = new MockPropertyChangeListener();
		PropertyChangeListener l3 = new PropertyChangeListenerProxy("myProp",
				l2);
		PropertyChangeListener l4 = new PropertyChangeListenerProxy("myProp",
				l3);

		sup.addPropertyChangeListener(l1);

		assertEquals(1, sup.getPropertyChangeListeners().length);
		assertSame(l1, sup.getPropertyChangeListeners()[0]);

		sup.removePropertyChangeListener(l1);
		sup.addPropertyChangeListener(l3);
		assertEquals(1, sup.getPropertyChangeListeners().length);
		assertSame(l2, ((PropertyChangeListenerProxy) sup
				.getPropertyChangeListeners()[0]).getListener());
		assertNotSame(l3, sup.getPropertyChangeListeners()[0]);

		sup.removePropertyChangeListener(sup.getPropertyChangeListeners()[0]);
		assertEquals(0, sup.getPropertyChangeListeners().length);
		sup.addPropertyChangeListener(l4);
		assertNotSame(l3, ((PropertyChangeListenerProxy) sup
				.getPropertyChangeListeners()[0]).getListener());
		assertNotSame(l4, sup.getPropertyChangeListeners()[0]);
		assertSame(
				l2,
				((PropertyChangeListenerProxy) ((PropertyChangeListenerProxy) sup
						.getPropertyChangeListeners()[0]).getListener())
						.getListener());
	}

	/*
	 * Test the method addPropertyChangeListener(PropertyeChangeListener) with a
	 * null listener parameter.
	 */
	public void testAddPropertyChangeListener_PropertyChangeListener_Null() {
		Object src = new Object();
		PropertyChangeSupport sup = new PropertyChangeSupport(src);

		sup.addPropertyChangeListener(null);
		PropertyChangeListener[] listeners = sup.getPropertyChangeListeners();
		assertEquals(1, listeners.length);
		assertSame(null, listeners[0]);
	}

	/*
	 * Test the method addPropertyChangeListener(PropertyeChangeListener) with a
	 * listener parameter that has already been registered.
	 */
	public void testAddPropertyChangeListener_PropertyChangeListener_Duplicate() {
		Object src = new Object();
		PropertyChangeSupport sup = new PropertyChangeSupport(src);
		PropertyChangeListener l1 = new MockPropertyChangeListener();

		sup.addPropertyChangeListener(l1);
		sup.addPropertyChangeListener(l1);

		PropertyChangeListener[] listeners = sup.getPropertyChangeListeners();
		assertEquals(2, listeners.length);
		assertSame(l1, listeners[0]);
		assertSame(l1, listeners[1]);
	}

	/*
	 * Test the method addPropertyChangeListener(PropertyeChangeListener,
	 * String) with a normal listener parameter and property name parameter.
	 */
	public void testAddPropertyChangeListener_PropertyChangeListener_String_Normal() {
		Object src = new Object();
		PropertyChangeSupport sup = new PropertyChangeSupport(src);
		PropertyChangeListener l1 = new MockPropertyChangeListener();
		PropertyChangeListener l2 = new MockPropertyChangeListener();
		PropertyChangeListener l3 = new PropertyChangeListenerProxy("myProp",
				l2);

		sup.addPropertyChangeListener("myProp2", l1);

		PropertyChangeListener[] listeners = sup.getPropertyChangeListeners();
		assertEquals(1, listeners.length);
		assertSame(l1, ((PropertyChangeListenerProxy) listeners[0])
				.getListener());

		sup.removePropertyChangeListener(listeners[0]);
		sup.addPropertyChangeListener("myProp3", l3);
		listeners = sup.getPropertyChangeListeners();
		assertEquals(1, listeners.length);
		// pay attention to this recursive proxy
		assertNotSame(l3, ((PropertyChangeListenerProxy) listeners[0])
				.getListener());
		assertNotSame(l3, ((PropertyChangeListenerProxy) listeners[0]));
		assertSame(
				l2,
				((PropertyChangeListenerProxy) ((PropertyChangeListenerProxy) listeners[0])
						.getListener()).getListener());

		listeners = sup.getPropertyChangeListeners("myProp");
		assertEquals(0, listeners.length);

		listeners = sup.getPropertyChangeListeners("myProp3");
		assertEquals(1, listeners.length);
		// pay attention to this recursive proxy
		assertNotSame(l3, ((PropertyChangeListenerProxy) listeners[0])
				.getListener());
		assertNotSame(l3, ((PropertyChangeListenerProxy) listeners[0]));
		assertSame(l2, ((PropertyChangeListenerProxy) listeners[0])
				.getListener());

	}

	/*
	 * Test the method addPropertyChangeListener(PropertyeChangeListener,
	 * String) with a null listener parameter and a normal property name
	 * parameter.
	 */
	public void testAddPropertyChangeListener_PropertyChangeListener_String_NullListener() {
		Object src = new Object();
		PropertyChangeSupport sup = new PropertyChangeSupport(src);

		sup.addPropertyChangeListener("myProp", null);

		PropertyChangeListener[] listeners = sup.getPropertyChangeListeners();
		assertEquals(1, listeners.length);
		assertSame(null, ((PropertyChangeListenerProxy) listeners[0])
				.getListener());

		PropertyChangeListener l3 = new PropertyChangeListenerProxy("myProp",
				null);
		assertEquals(1, listeners.length);
		assertSame(null, ((PropertyChangeListenerProxy) listeners[0])
				.getListener());
	}

	/*
	 * Test the method addPropertyChangeListener(PropertyeChangeListener,
	 * String) with a normal listener parameter and a null property name
	 * parameter.
	 */
	public void testAddPropertyChangeListener_PropertyChangeListener_String_NullProperty() {
		Object src = new Object();
		PropertyChangeSupport sup = new PropertyChangeSupport(src);
		PropertyChangeListener l1 = new MockPropertyChangeListener();
		PropertyChangeListener l2 = new MockPropertyChangeListener();
		PropertyChangeListener l3 = new PropertyChangeListenerProxy("myProp",
				l2);

		try {
			sup.addPropertyChangeListener(null, l1);
			fail("Should throw NullPointerException!");
		} catch (NullPointerException ex) {
			// expected
		}

		try {
			sup.addPropertyChangeListener(null, l3);
			fail("Should throw NullPointerException!");
		} catch (NullPointerException ex) {
			// expected
		}

		l3 = new PropertyChangeListenerProxy(null, l2);
		try {
			sup.addPropertyChangeListener(l3);
			fail("Should throw NullPointerException!");
		} catch (NullPointerException ex) {
			// expected
		}
	}

	/*
	 * Test the method addPropertyChangeListener(PropertyeChangeListener,
	 * String) with a listener parameter that has already been registered for
	 * the named property.
	 */
	public void testAddPropertyChangeListener_PropertyChangeListener_String_Duplicate() {
		Object src = new Object();
		PropertyChangeSupport sup = new PropertyChangeSupport(src);
		PropertyChangeListener l1 = new MockPropertyChangeListener();
		PropertyChangeListener l2 = new MockPropertyChangeListener();
		PropertyChangeListener l3 = new PropertyChangeListenerProxy("myProp",
				l2);

		sup.addPropertyChangeListener("myProp2", l1);
		sup.addPropertyChangeListener("myProp2", l1);

		PropertyChangeListener[] listeners = sup.getPropertyChangeListeners();
		assertEquals(2, listeners.length);
		assertSame(l1, ((PropertyChangeListenerProxy) listeners[0])
				.getListener());
		assertSame(l1, ((PropertyChangeListenerProxy) listeners[1])
				.getListener());

		sup.removePropertyChangeListener(listeners[0]);
		sup.removePropertyChangeListener(listeners[1]);
		sup.addPropertyChangeListener("myProp3", l3);
		sup.addPropertyChangeListener("myProp3", l3);
		listeners = sup.getPropertyChangeListeners();
		assertEquals(2, listeners.length);
		assertSame(
				l2,
				((PropertyChangeListenerProxy) ((PropertyChangeListenerProxy) listeners[0])
						.getListener()).getListener());
		assertSame(
				l2,
				((PropertyChangeListenerProxy) ((PropertyChangeListenerProxy) listeners[1])
						.getListener()).getListener());
	}

	/*
	 * Test the method removePropertyChangeListener(PropertyeChangeListener)
	 * with a normal listener parameter.
	 */
	public void testRemovePropertyChangeListener_PropertyChangeListener_Normal() {
		Object src = new Object();
		PropertyChangeSupport sup = new PropertyChangeSupport(src);
		PropertyChangeListener l1 = new MockPropertyChangeListener();
		PropertyChangeListener l2 = new MockPropertyChangeListener();
		PropertyChangeListener l3 = new PropertyChangeListenerProxy("myProp",
				l2);

		sup.addPropertyChangeListener(l1);

		PropertyChangeListener[] listeners = sup.getPropertyChangeListeners();
		assertEquals(1, listeners.length);
		sup.removePropertyChangeListener(l1);
		listeners = sup.getPropertyChangeListeners();
		assertEquals(0, listeners.length);
		sup.addPropertyChangeListener(l3);
		listeners = sup.getPropertyChangeListeners();
		assertEquals(1, listeners.length);
		sup.removePropertyChangeListener(l3);
		listeners = sup.getPropertyChangeListeners();
		assertEquals(0, listeners.length);
		sup.addPropertyChangeListener("myProp3", l2);
		listeners = sup.getPropertyChangeListeners();
		assertEquals(1, listeners.length);
		sup.removePropertyChangeListener(l2);
		listeners = sup.getPropertyChangeListeners();
		assertEquals(1, listeners.length);
		sup.removePropertyChangeListener(listeners[0]);
		listeners = sup.getPropertyChangeListeners();
		assertEquals(0, listeners.length);
	}

	/*
	 * Test the method removePropertyChangeListener(PropertyeChangeListener)
	 * with a null listener parameter.
	 */
	public void testRemovePropertyChangeListener_PropertyChangeListener_Null() {
		Object src = new Object();
		PropertyChangeSupport sup = new PropertyChangeSupport(src);
		sup.removePropertyChangeListener(null);
		assertEquals(0, sup.getPropertyChangeListeners().length);
		sup.addPropertyChangeListener(null);
		assertEquals(1, sup.getPropertyChangeListeners().length);
		sup.removePropertyChangeListener(null);
		assertEquals(0, sup.getPropertyChangeListeners().length);
	}

	/*
	 * Test the method removePropertyChangeListener(PropertyeChangeListener)
	 * with a non-registered listener parameter.
	 */
	public void testRemovePropertyChangeListener_PropertyChangeListener_NonRegistered() {
		Object src = new Object();
		PropertyChangeSupport sup = new PropertyChangeSupport(src);
		sup.removePropertyChangeListener(new MockPropertyChangeListener());
		assertEquals(0, sup.getPropertyChangeListeners().length);
	}

	/*
	 * Test the method removePropertyChangeListener(PropertyeChangeListener,
	 * String) when a listener for all properties has been registered.
	 */
	public void testRemovePropertyChangeListener_PropertyChangeListener_String_AllRegistered() {
		Object src = new Object();
		PropertyChangeSupport sup = new PropertyChangeSupport(src);
		Object newValue = new Object();
		Object oldValue = new Object();

		MockPropertyChangeListener l1 = new MockPropertyChangeListener(src,
				"myProp", oldValue, newValue);

		sup.addPropertyChangeListener(l1);

		sup.removePropertyChangeListener("myProp", l1);
		assertEquals(1, sup.getPropertyChangeListeners().length);
		assertEquals(0, sup.getPropertyChangeListeners("myProp").length);
		sup.firePropertyChange("myProp", oldValue, newValue);
		l1.assertCalled();
	}

	/*
	 * Test the method removePropertyChangeListener(PropertyeChangeListener,
	 * String) when a listener for the named property has been registered.
	 */
	public void testRemovePropertyChangeListener_PropertyChangeListener_String_PropertyRegistered() {
		Object src = new Object();
		PropertyChangeSupport sup = new PropertyChangeSupport(src);
		PropertyChangeListener l1 = new MockPropertyChangeListener();
		sup.addPropertyChangeListener("myProp", l1);
		assertEquals(1, sup.getPropertyChangeListeners().length);

		sup.removePropertyChangeListener("myProp", l1);
		assertEquals(0, sup.getPropertyChangeListeners().length);
		assertEquals(0, sup.getPropertyChangeListeners("myProp").length);

		PropertyChangeListener l2 = new MockPropertyChangeListener();
		PropertyChangeListener l3 = new PropertyChangeListenerProxy("myProp",
				l2);
		sup.addPropertyChangeListener(l3);
		assertEquals(1, sup.getPropertyChangeListeners().length);
		sup.removePropertyChangeListener("myProp", l2);
		assertEquals(0, sup.getPropertyChangeListeners().length);
		assertEquals(0, sup.getPropertyChangeListeners("myProp").length);
	}

	/*
	 * Test the method removePropertyChangeListener(PropertyeChangeListener,
	 * String) with a non-registered listener parameter.
	 */
	public void testRemovePropertyChangeListener_PropertyChangeListener_String_NonRegistered() {
		Object src = new Object();
		PropertyChangeSupport sup = new PropertyChangeSupport(src);
		sup.removePropertyChangeListener("myProp",
				new MockPropertyChangeListener());
		assertEquals(0, sup.getPropertyChangeListeners().length);
	}

	/*
	 * Test the method removePropertyChangeListener(PropertyeChangeListener,
	 * String) with a null listener parameter.
	 */
	public void testRemovePropertyChangeListener_PropertyChangeListener_String_NullListener() {
		Object src = new Object();
		PropertyChangeSupport sup = new PropertyChangeSupport(src);

		sup.removePropertyChangeListener("myProp", null);
		assertEquals(0, sup.getPropertyChangeListeners().length);
		sup.addPropertyChangeListener("myProp", null);
		assertEquals(1, sup.getPropertyChangeListeners().length);
		sup.removePropertyChangeListener("myProp", null);
		assertEquals(0, sup.getPropertyChangeListeners().length);
	}

	/*
	 * Test the method removePropertyChangeListener(PropertyeChangeListener,
	 * String) with a null property name parameter.
	 */
	public void testRemovePropertyChangeListener_PropertyChangeListener_String_NullProperty() {
		Object src = new Object();
		PropertyChangeSupport sup = new PropertyChangeSupport(src);
		// sup
		// .removePropertyChangeListener(null,
		// new MockPropertyChangeListener());
		sup.addPropertyChangeListener("myProp",
				new MockPropertyChangeListener());
		try {
			sup.removePropertyChangeListener(null,
					new MockPropertyChangeListener());
			fail("Should throw NullPointerException!");
		} catch (NullPointerException ex) {
			// expected
		}
	}

	/*
	 * Test the method getPropertyChangeListeners() when there is one listener
	 * for all properties and one for a named property.
	 */
	public void testGetPropertyChangeListener_Normal() {
		Object src = new Object();
		PropertyChangeSupport sup = new PropertyChangeSupport(src);
		PropertyChangeListener l1 = new MockPropertyChangeListener();
		PropertyChangeListener l2 = new MockPropertyChangeListener();
		PropertyChangeListener l3 = new PropertyChangeListenerProxy("myProp",
				l2);
		PropertyChangeListener l4 = new MockPropertyChangeListener();

		sup.addPropertyChangeListener(l1);
		sup.addPropertyChangeListener(l3);
		sup.addPropertyChangeListener("myProp2", l4);

		assertEquals(3, sup.getPropertyChangeListeners().length);
	}

	/*
	 * Test the method getPropertyChangeListeners() when there is no listeners.
	 */
	public void testGetPropertyChangeListener_Empty() {
		Object src = new Object();
		PropertyChangeSupport sup = new PropertyChangeSupport(src);
		assertEquals(0, sup.getPropertyChangeListeners().length);
	}

	/*
	 * Test the method getPropertyChangeListeners(String) when there is one
	 * listener for all properties and one for the named property and a third
	 * for another named property.
	 */
	public void testGetPropertyChangeListener_String_Normal() {
		Object src = new Object();
		PropertyChangeSupport sup = new PropertyChangeSupport(src);
		PropertyChangeListener l1 = new MockPropertyChangeListener();
		PropertyChangeListener l2 = new MockPropertyChangeListener();
		PropertyChangeListener l3 = new PropertyChangeListenerProxy("myProp",
				l2);
		PropertyChangeListener l4 = new MockPropertyChangeListener();

		sup.addPropertyChangeListener(l1);
		sup.addPropertyChangeListener(l3);
		sup.addPropertyChangeListener("myProp2", l4);

		assertEquals(1, sup.getPropertyChangeListeners("myProp").length);
		assertSame(l2, sup.getPropertyChangeListeners("myProp")[0]);
		sup.addPropertyChangeListener("myProp",
				new MockPropertyChangeListener());
		assertEquals(2, sup.getPropertyChangeListeners("myProp").length);
	}

	/*
	 * Test the method getPropertyChangeListeners(String) when there is no
	 * listener for the named property but there is one for another named
	 * property.
	 */
	public void testGetPropertyChangeListener_String_None() {
		Object src = new Object();
		PropertyChangeSupport sup = new PropertyChangeSupport(src);
		PropertyChangeListener l1 = new MockPropertyChangeListener();
		PropertyChangeListener l2 = new MockPropertyChangeListener();
		PropertyChangeListener l3 = new PropertyChangeListenerProxy("myProp2",
				l2);
		PropertyChangeListener l4 = new MockPropertyChangeListener();

		sup.addPropertyChangeListener(l1);
		sup.addPropertyChangeListener(l3);
		sup.addPropertyChangeListener("myProp3", l4);

		assertEquals(0, sup.getPropertyChangeListeners("myProp").length);
	}

	/*
	 * Test the method getPropertyChangeListeners(String) with a null parameter.
	 */
	public void testGetPropertyChangeListener_String_Null() {
		Object src = new Object();
		PropertyChangeSupport sup = new PropertyChangeSupport(src);
		// sup.getPropertyChangeListeners(null);
		sup.addPropertyChangeListener("myProp",
				new MockPropertyChangeListener());
		try {
			sup.getPropertyChangeListeners(null);
			fail("Should throw NullPointerException!");
		} catch (NullPointerException ex) {
			// expected
		}
	}

	/*
	 * Test the method hasListeners(String) when there is one listener for all
	 * properties.
	 */
	public void testHasListener_AllRegistered() {
		Object src = new Object();
		PropertyChangeSupport sup = new PropertyChangeSupport(src);
		PropertyChangeListener l1 = new MockPropertyChangeListener();
		PropertyChangeListener l2 = new MockPropertyChangeListener();
		PropertyChangeListener l3 = new PropertyChangeListenerProxy("myProp",
				l2);

		assertFalse(sup.hasListeners("myProp"));
		sup.addPropertyChangeListener(l1);
		assertTrue(sup.hasListeners("myProp"));
		sup.removePropertyChangeListener(l1);
		assertFalse(sup.hasListeners("myProp"));
		sup.addPropertyChangeListener(l3);
		assertTrue(sup.hasListeners("myProp"));
	}

	/*
	 * Test the method hasListeners(String) when there is one listener for the
	 * named property.
	 */
	public void testHasListener_PropertyRegistered() {
		Object src = new Object();
		PropertyChangeSupport sup = new PropertyChangeSupport(src);
		PropertyChangeListener l1 = new MockPropertyChangeListener();
		PropertyChangeListener l2 = new MockPropertyChangeListener();
		PropertyChangeListener l3 = new PropertyChangeListenerProxy("myProp2",
				l2);

		assertFalse(sup.hasListeners("myProp"));
		sup.addPropertyChangeListener("myProP", l1);
		assertFalse(sup.hasListeners("myProp"));
		sup.addPropertyChangeListener("myProp", l2);
		assertTrue(sup.hasListeners("myProp"));
		sup.removePropertyChangeListener("myProp", l2);
		assertFalse(sup.hasListeners("myProp"));
		sup.addPropertyChangeListener("myProp", l3);
		assertFalse(sup.hasListeners("myProp"));
	}

	/*
	 * Test the method hasListeners(String) when there is no listeners.
	 */
	public void testHasListener_None() {
		Object src = new Object();
		PropertyChangeSupport sup = new PropertyChangeSupport(src);
		assertFalse(sup.hasListeners("myProp"));
	}

	/*
	 * Test the method hasListeners(String) with a null parameter.
	 */
	public void testHasListener_Null() {
		Object src = new Object();
		PropertyChangeSupport sup = new PropertyChangeSupport(src);
		// assertFalse(sup.hasListeners(null));

		PropertyChangeListener l1 = new MockPropertyChangeListener();
		sup.addPropertyChangeListener("myProP", l1);
		try {
			sup.hasListeners(null);
			fail("Should throw NullPointerException!");
		} catch (NullPointerException ex) {
			// expected
		}
	}

	/*
	 * Test the method firePropertyChange(String, Object, Object) with normal
	 * parameters, when there is no listeners.
	 */
	public void testFirePropertyChange_Object_NoListeners() {
		Object src = new Object();
		PropertyChangeSupport sup = new PropertyChangeSupport(src);
		sup.firePropertyChange("myProp", new Object(), new Object());
	}

	/*
	 * Test the method firePropertyChange(String, Object, Object) with normal
	 * parameters, when there is a listener for all properties and another for
	 * the named property.
	 */
	public void testFirePropertyChange_Object_Normal() {
		Object src = new Object();
		PropertyChangeSupport sup = new PropertyChangeSupport(src);
		Object newValue = new Object();
		Object oldValue = new Object();

		MockPropertyChangeListener l1 = new MockPropertyChangeListener(src,
				"myProp", oldValue, newValue);
		MockPropertyChangeListener l2 = new MockPropertyChangeListener(src,
				"myProp", oldValue, newValue);
		PropertyChangeListener l3 = new PropertyChangeListenerProxy("myProp",
				l2);
		MockPropertyChangeListener l4 = new MockPropertyChangeListener(src,
				"myProp", oldValue, newValue);
		sup.addPropertyChangeListener(l1);
		sup.addPropertyChangeListener(l3);
		sup.addPropertyChangeListener("myProp", l4);

		sup.firePropertyChange("myProp", oldValue, newValue);
		l1.assertCalled();
		l2.assertCalled();
		l4.assertCalled();
	}

	/*
	 * Test the method firePropertyChange(String, Object, Object) with equal old
	 * and new non-null values, when there is a listener for all properties and
	 * another for the named property.
	 */
	public void testFirePropertyChange_Object_EqualValues() {
		Object src = new Object();
		PropertyChangeSupport sup = new PropertyChangeSupport(src);
		Object newValue = new Object();
		Object oldValue = newValue;

		MockPropertyChangeListener l1 = new MockPropertyChangeListener(src,
				"myProp", oldValue, newValue);
		MockPropertyChangeListener l2 = new MockPropertyChangeListener(src,
				"myProp", oldValue, newValue);
		PropertyChangeListener l3 = new PropertyChangeListenerProxy("myProp",
				l2);
		MockPropertyChangeListener l4 = new MockPropertyChangeListener(src,
				"myProp", oldValue, newValue);
		sup.addPropertyChangeListener(l1);
		sup.addPropertyChangeListener(l3);
		sup.addPropertyChangeListener("myProp", l4);

		sup.firePropertyChange("myProp", oldValue, newValue);
		l1.assertNotCalled();
		l2.assertNotCalled();
		l4.assertNotCalled();
	}

	/*
	 * Test the method firePropertyChange(String, Object, Object) with null old
	 * and new values, when there is a listener for all properties and another
	 * for the named property.
	 */
	public void testFirePropertyChange_Object_NullValues() {
		Object src = new Object();
		PropertyChangeSupport sup = new PropertyChangeSupport(src);
		Object newValue = null;
		Object oldValue = null;

		MockPropertyChangeListener l1 = new MockPropertyChangeListener(src,
				"myProp", oldValue, newValue);
		MockPropertyChangeListener l2 = new MockPropertyChangeListener(src,
				"myProp", oldValue, newValue);
		PropertyChangeListener l3 = new PropertyChangeListenerProxy("myProp",
				l2);
		MockPropertyChangeListener l4 = new MockPropertyChangeListener(src,
				"myProp", oldValue, newValue);
		sup.addPropertyChangeListener(l1);
		sup.addPropertyChangeListener(l3);
		sup.addPropertyChangeListener("myProp", l4);

		sup.firePropertyChange("myProp", oldValue, newValue);
		l1.assertCalled();
		l2.assertCalled();
		l4.assertCalled();
	}

	/*
	 * Test the method firePropertyChange(String, Object, Object) with a
	 * non-null old value and a null new value, when there is a listener for all
	 * properties and another for the named property.
	 */
	public void testFirePropertyChange_Object_NullNewValue() {
		Object src = new Object();
		PropertyChangeSupport sup = new PropertyChangeSupport(src);
		Object newValue = null;
		Object oldValue = new Object();

		MockPropertyChangeListener l1 = new MockPropertyChangeListener(src,
				"myProp", oldValue, newValue);
		MockPropertyChangeListener l2 = new MockPropertyChangeListener(src,
				"myProp", oldValue, newValue);
		PropertyChangeListener l3 = new PropertyChangeListenerProxy("myProp",
				l2);
		MockPropertyChangeListener l4 = new MockPropertyChangeListener(src,
				"myProp", oldValue, newValue);
		sup.addPropertyChangeListener(l1);
		sup.addPropertyChangeListener(l3);
		sup.addPropertyChangeListener("myProp", l4);

		sup.firePropertyChange("myProp", oldValue, newValue);
		l1.assertCalled();
		l2.assertCalled();
		l4.assertCalled();
	}

	/*
	 * Test the method firePropertyChange(String, Object, Object) with a null
	 * old value and a non-null new value, when there is a listener for all
	 * properties and another for the named property.
	 */
	public void testFirePropertyChange_Object_NullOldValue() {
		Object src = new Object();
		PropertyChangeSupport sup = new PropertyChangeSupport(src);
		Object newValue = new Object();
		Object oldValue = null;

		MockPropertyChangeListener l1 = new MockPropertyChangeListener(src,
				"myProp", oldValue, newValue);
		MockPropertyChangeListener l2 = new MockPropertyChangeListener(src,
				"myProp", oldValue, newValue);
		PropertyChangeListener l3 = new PropertyChangeListenerProxy("myProp",
				l2);
		MockPropertyChangeListener l4 = new MockPropertyChangeListener(src,
				"myProp", oldValue, newValue);
		sup.addPropertyChangeListener(l1);
		sup.addPropertyChangeListener(l3);
		sup.addPropertyChangeListener("myProp", l4);

		sup.firePropertyChange("myProp", oldValue, newValue);
		l1.assertCalled();
		l2.assertCalled();
		l4.assertCalled();
	}

	/*
	 * Test the method firePropertyChange(String, Object, Object) with a null
	 * property name parameter.
	 */
	public void testFirePropertyChange_Object_NullProperty() {
		Object src = new Object();
		PropertyChangeSupport sup = new PropertyChangeSupport(src);
		Object newValue = new Object();
		Object oldValue = new Object();

		MockPropertyChangeListener l1 = new MockPropertyChangeListener(src,
				null, oldValue, newValue);
		MockPropertyChangeListener l2 = new MockPropertyChangeListener(src,
				null, oldValue, newValue);
		PropertyChangeListener l3 = new PropertyChangeListenerProxy("myProp",
				l2);
		MockPropertyChangeListener l4 = new MockPropertyChangeListener(src,
				null, oldValue, newValue);
		sup.addPropertyChangeListener(l1);
		sup.addPropertyChangeListener(l3);
		sup.addPropertyChangeListener("myProp", l4);

		sup.firePropertyChange(null, oldValue, newValue);
		l1.assertCalled();
		l2.assertNotCalled();
		l4.assertNotCalled();
	}

	/*
	 * Test the method firePropertyChange(String, Object, Object) when a null
	 * listener has been registered.
	 */
	public void testFirePropertyChange_Object_NullListener() {
		Object src = new Object();
		PropertyChangeSupport sup = new PropertyChangeSupport(src);
		Object newValue = new Object();
		Object oldValue = new Object();

		sup.addPropertyChangeListener(null);
		try {
			sup.firePropertyChange("myProp", oldValue, newValue);
			fail("Should throw NullPointerException!");
		} catch (NullPointerException ex) {
			// expected
		}
	}

	/*
	 * Test the method firePropertyChange(PropertyChangeEvent) with normal
	 * parameters, when there is a listener for all properties and another for
	 * the named property.
	 */
	public void testFirePropertyChange_PropertyChangeEvent_Normal() {
		Object src = new Object();
		PropertyChangeSupport sup = new PropertyChangeSupport(src);
		Object newValue = new Object();
		Object oldValue = new Object();
		Object src2 = new Object();
		PropertyChangeEvent event = new PropertyChangeEvent(src2, "myProp",
				oldValue, newValue);

		MockPropertyChangeListener l1 = new MockPropertyChangeListener(src2,
				"myProp", oldValue, newValue);
		MockPropertyChangeListener l2 = new MockPropertyChangeListener(src2,
				"myProp", oldValue, newValue);
		PropertyChangeListener l3 = new PropertyChangeListenerProxy("myProp",
				l2);
		MockPropertyChangeListener l4 = new MockPropertyChangeListener(src2,
				"myProp", oldValue, newValue);
		sup.addPropertyChangeListener(l1);
		sup.addPropertyChangeListener(l3);
		sup.addPropertyChangeListener("myProp", l4);

		sup.firePropertyChange(event);
		l1.assertCalled();
		l2.assertCalled();
		l4.assertCalled();
	}

	/*
	 * Test the method firePropertyChange(PropertyChangeEvent) with equal old
	 * and new non-null values, when there is a listener for all properties and
	 * another for the named property.
	 */
	public void testFirePropertyChange_PropertyChangeEvent_EqualValues() {
		Object src = new Object();
		PropertyChangeSupport sup = new PropertyChangeSupport(src);
		Object newValue = new Object();
		Object oldValue = newValue;
		Object src2 = new Object();
		PropertyChangeEvent event = new PropertyChangeEvent(src2, "myProp",
				oldValue, newValue);

		MockPropertyChangeListener l1 = new MockPropertyChangeListener(src2,
				"myProp", oldValue, newValue);
		MockPropertyChangeListener l2 = new MockPropertyChangeListener(src2,
				"myProp", oldValue, newValue);
		PropertyChangeListener l3 = new PropertyChangeListenerProxy("myProp",
				l2);
		MockPropertyChangeListener l4 = new MockPropertyChangeListener(src2,
				"myProp", oldValue, newValue);
		sup.addPropertyChangeListener(l1);
		sup.addPropertyChangeListener(l3);
		sup.addPropertyChangeListener("myProp", l4);

		sup.firePropertyChange(event);
		l1.assertNotCalled();
		l2.assertNotCalled();
		l4.assertNotCalled();
	}

	/*
	 * Test the method firePropertyChange(PropertyChangeEvent) with null old and
	 * new values, when there is a listener for all properties and another for
	 * the named property.
	 */
	public void testFirePropertyChange_PropertyChangeEvent_NullValues() {
		Object src = new Object();
		PropertyChangeSupport sup = new PropertyChangeSupport(src);
		Object newValue = null;
		Object oldValue = null;
		Object src2 = new Object();
		PropertyChangeEvent event = new PropertyChangeEvent(src2, "myProp",
				oldValue, newValue);

		MockPropertyChangeListener l1 = new MockPropertyChangeListener(src2,
				"myProp", oldValue, newValue);
		MockPropertyChangeListener l2 = new MockPropertyChangeListener(src2,
				"myProp", oldValue, newValue);
		PropertyChangeListener l3 = new PropertyChangeListenerProxy("myProp",
				l2);
		MockPropertyChangeListener l4 = new MockPropertyChangeListener(src2,
				"myProp", oldValue, newValue);
		sup.addPropertyChangeListener(l1);
		sup.addPropertyChangeListener(l3);
		sup.addPropertyChangeListener("myProp", l4);

		sup.firePropertyChange(event);
		l1.assertCalled();
		l2.assertCalled();
		l4.assertCalled();
	}

	/*
	 * Test the method firePropertyChange(PropertyChangeEvent) with a non-null
	 * old value and a null new value, when there is a listener for all
	 * properties and another for the named property.
	 */
	public void testFirePropertyChange_PropertyChangeEvent_NullNewValue() {
		Object src = new Object();
		PropertyChangeSupport sup = new PropertyChangeSupport(src);
		Object newValue = null;
		Object oldValue = new Object();
		Object src2 = new Object();
		PropertyChangeEvent event = new PropertyChangeEvent(src2, "myProp",
				oldValue, newValue);

		MockPropertyChangeListener l1 = new MockPropertyChangeListener(src2,
				"myProp", oldValue, newValue);
		MockPropertyChangeListener l2 = new MockPropertyChangeListener(src2,
				"myProp", oldValue, newValue);
		PropertyChangeListener l3 = new PropertyChangeListenerProxy("myProp",
				l2);
		MockPropertyChangeListener l4 = new MockPropertyChangeListener(src2,
				"myProp", oldValue, newValue);
		sup.addPropertyChangeListener(l1);
		sup.addPropertyChangeListener(l3);
		sup.addPropertyChangeListener("myProp", l4);

		sup.firePropertyChange(event);
		l1.assertCalled();
		l2.assertCalled();
		l4.assertCalled();
	}

	/*
	 * Test the method firePropertyChange(PropertyChangeEvent) with a null old
	 * value and a non-null new value, when there is a listener for all
	 * properties and another for the named property.
	 */
	public void testFirePropertyChange_PropertyChangeEvent_NullOldValue() {
		Object src = new Object();
		PropertyChangeSupport sup = new PropertyChangeSupport(src);
		Object newValue = new Object();
		Object oldValue = null;
		Object src2 = new Object();
		PropertyChangeEvent event = new PropertyChangeEvent(src2, "myProp",
				oldValue, newValue);

		MockPropertyChangeListener l1 = new MockPropertyChangeListener(src2,
				"myProp", oldValue, newValue);
		MockPropertyChangeListener l2 = new MockPropertyChangeListener(src2,
				"myProp", oldValue, newValue);
		PropertyChangeListener l3 = new PropertyChangeListenerProxy("myProp",
				l2);
		MockPropertyChangeListener l4 = new MockPropertyChangeListener(src2,
				"myProp", oldValue, newValue);
		sup.addPropertyChangeListener(l1);
		sup.addPropertyChangeListener(l3);
		sup.addPropertyChangeListener("myProp", l4);

		sup.firePropertyChange(event);
		l1.assertCalled();
		l2.assertCalled();
		l4.assertCalled();
	}

	/*
	 * Test the method firePropertyChange(PropertyChangeEvent) with a null
	 * property name parameter.
	 */
	public void testFirePropertyChange_PropertyChangeEvent_NullProperty() {
		Object src = new Object();
		PropertyChangeSupport sup = new PropertyChangeSupport(src);
		Object newValue = new Object();
		Object oldValue = new Object();
		Object src2 = new Object();
		PropertyChangeEvent event = new PropertyChangeEvent(src2, null,
				oldValue, newValue);

		MockPropertyChangeListener l1 = new MockPropertyChangeListener(src2,
				null, oldValue, newValue);
		MockPropertyChangeListener l2 = new MockPropertyChangeListener(src2,
				null, oldValue, newValue);
		PropertyChangeListener l3 = new PropertyChangeListenerProxy("myProp",
				l2);
		MockPropertyChangeListener l4 = new MockPropertyChangeListener(src2,
				null, oldValue, newValue);
		sup.addPropertyChangeListener(l1);
		sup.addPropertyChangeListener(l3);
		sup.addPropertyChangeListener("myProp", l4);

		sup.firePropertyChange(event);
		l1.assertCalled();
		l2.assertNotCalled();
		l4.assertNotCalled();
	}

	/*
	 * Test the method firePropertyChange(PropertyChangeEvent) when null.
	 */
	public void testFirePropertyChange_PropertyChangeEvent_Null() {
		Object src = new Object();
		PropertyChangeSupport sup = new PropertyChangeSupport(src);

		try {
			sup.firePropertyChange(null);
			fail("Should throw NullPointerException!");
		} catch (NullPointerException ex) {
			// expected
		}
	}

	/*
	 * Test the method firePropertyChange(PropertyChangeEvent) when a null
	 * listener has been registered.
	 */
	public void testFirePropertyChange_PropertyChangeEvent_NullListener() {
		Object src = new Object();
		PropertyChangeSupport sup = new PropertyChangeSupport(src);
		Object newValue = new Object();
		Object oldValue = new Object();
		Object src2 = new Object();
		PropertyChangeEvent event = new PropertyChangeEvent(src2, "myProp",
				oldValue, newValue);

		sup.addPropertyChangeListener(null);
		try {
			sup.firePropertyChange(event);
			fail("Should throw NullPointerException!");
		} catch (NullPointerException ex) {
			// expected
		}
	}

	/*
	 * Test the method firePropertyChange(String, boolean, boolean) with normal
	 * parameters, when there is a listener for all properties and another for
	 * the named property.
	 */
	public void testFirePropertyChange_Boolean_Normal() {
		Object src = new Object();
		PropertyChangeSupport sup = new PropertyChangeSupport(src);
		Object newValue = Boolean.TRUE;
		Object oldValue = Boolean.FALSE;

		MockPropertyChangeListener l1 = new MockPropertyChangeListener(src,
				"myProp", oldValue, newValue);
		MockPropertyChangeListener l2 = new MockPropertyChangeListener(src,
				"myProp", oldValue, newValue);
		PropertyChangeListener l3 = new PropertyChangeListenerProxy("myProp",
				l2);
		MockPropertyChangeListener l4 = new MockPropertyChangeListener(src,
				"myProp", oldValue, newValue);
		sup.addPropertyChangeListener(l1);
		sup.addPropertyChangeListener(l3);
		sup.addPropertyChangeListener("myProp", l4);

		sup.firePropertyChange("myProp", false, true);
		l1.assertCalled();
		l2.assertCalled();
		l4.assertCalled();
	}

	/*
	 * Test the method firePropertyChange(String, boolean, boolean) with equal
	 * old and new non-null values, when there is a listener for all properties
	 * and another for the named property.
	 */
	public void testFirePropertyChange_Boolean_EqualValues() {
		Object src = new Object();
		PropertyChangeSupport sup = new PropertyChangeSupport(src);
		Object newValue = Boolean.TRUE;
		Object oldValue = newValue;

		MockPropertyChangeListener l1 = new MockPropertyChangeListener(src,
				"myProp", oldValue, newValue);
		MockPropertyChangeListener l2 = new MockPropertyChangeListener(src,
				"myProp", oldValue, newValue);
		PropertyChangeListener l3 = new PropertyChangeListenerProxy("myProp",
				l2);
		MockPropertyChangeListener l4 = new MockPropertyChangeListener(src,
				"myProp", oldValue, newValue);
		sup.addPropertyChangeListener(l1);
		sup.addPropertyChangeListener(l3);
		sup.addPropertyChangeListener("myProp", l4);

		sup.firePropertyChange("myProp", true, true);
		l1.assertNotCalled();
		l2.assertNotCalled();
		l4.assertNotCalled();
	}

	/*
	 * Test the method firePropertyChange(String, boolean, boolean) with a null
	 * property name parameter.
	 */
	public void testFirePropertyChange_Boolean_NullProperty() {
		Object src = new Object();
		PropertyChangeSupport sup = new PropertyChangeSupport(src);
		Object newValue = Boolean.TRUE;
		Object oldValue = Boolean.FALSE;

		MockPropertyChangeListener l1 = new MockPropertyChangeListener(src,
				null, oldValue, newValue);
		MockPropertyChangeListener l2 = new MockPropertyChangeListener(src,
				null, oldValue, newValue);
		PropertyChangeListener l3 = new PropertyChangeListenerProxy("myProp",
				l2);
		MockPropertyChangeListener l4 = new MockPropertyChangeListener(src,
				null, oldValue, newValue);
		sup.addPropertyChangeListener(l1);
		sup.addPropertyChangeListener(l3);
		sup.addPropertyChangeListener("myProp", l4);

		sup.firePropertyChange(null, false, true);
		l1.assertCalled();
		l2.assertNotCalled();
		l4.assertNotCalled();
	}

	/*
	 * Test the method firePropertyChange(String, boolean, boolean) when a null
	 * listener has been registered.
	 */
	public void testFirePropertyChange_Boolean_NullListener() {
		Object src = new Object();
		PropertyChangeSupport sup = new PropertyChangeSupport(src);

		sup.addPropertyChangeListener(null);
		try {
			sup.firePropertyChange("myProp", true, false);
			fail("Should throw NullPointerException!");
		} catch (NullPointerException ex) {
			// expected
		}
	}

	/*
	 * Test the method firePropertyChange(String, int, int) with normal
	 * parameters, when there is a listener for all properties and another for
	 * the named property.
	 */
	public void testFirePropertyChange_Int_Normal() {
		Object src = new Object();
		PropertyChangeSupport sup = new PropertyChangeSupport(src);
		Integer newValue = new Integer(1);
		Integer oldValue = new Integer(2);

		MockPropertyChangeListener l1 = new MockPropertyChangeListener(src,
				"myProp", oldValue, newValue);
		MockPropertyChangeListener l2 = new MockPropertyChangeListener(src,
				"myProp", oldValue, newValue);
		PropertyChangeListener l3 = new PropertyChangeListenerProxy("myProp",
				l2);
		MockPropertyChangeListener l4 = new MockPropertyChangeListener(src,
				"myProp", oldValue, newValue);
		sup.addPropertyChangeListener(l1);
		sup.addPropertyChangeListener(l3);
		sup.addPropertyChangeListener("myProp", l4);

		sup.firePropertyChange("myProp", oldValue.intValue(), newValue
				.intValue());
		l1.assertCalled();
		l2.assertCalled();
		l4.assertCalled();
	}

	/*
	 * Test the method firePropertyChange(String, int, int) with equal old and
	 * new non-null values, when there is a listener for all properties and
	 * another for the named property.
	 */
	public void testFirePropertyChange_Int_EqualValues() {
		Object src = new Object();
		PropertyChangeSupport sup = new PropertyChangeSupport(src);
		Integer newValue = new Integer(1);
		Integer oldValue = newValue;

		MockPropertyChangeListener l1 = new MockPropertyChangeListener(src,
				"myProp", oldValue, newValue);
		MockPropertyChangeListener l2 = new MockPropertyChangeListener(src,
				"myProp", oldValue, newValue);
		PropertyChangeListener l3 = new PropertyChangeListenerProxy("myProp",
				l2);
		MockPropertyChangeListener l4 = new MockPropertyChangeListener(src,
				"myProp", oldValue, newValue);
		sup.addPropertyChangeListener(l1);
		sup.addPropertyChangeListener(l3);
		sup.addPropertyChangeListener("myProp", l4);

		sup.firePropertyChange("myProp", oldValue.intValue(), newValue
				.intValue());
		l1.assertNotCalled();
		l2.assertNotCalled();
		l4.assertNotCalled();
	}

	/*
	 * Test the method firePropertyChange(String, int, int) with a null property
	 * name parameter.
	 */
	public void testFirePropertyChange_Int_NullProperty() {
		Object src = new Object();
		PropertyChangeSupport sup = new PropertyChangeSupport(src);
		Integer newValue = new Integer(1);
		Integer oldValue = new Integer(2);

		MockPropertyChangeListener l1 = new MockPropertyChangeListener(src,
				null, oldValue, newValue);
		MockPropertyChangeListener l2 = new MockPropertyChangeListener(src,
				null, oldValue, newValue);
		PropertyChangeListener l3 = new PropertyChangeListenerProxy("myProp",
				l2);
		MockPropertyChangeListener l4 = new MockPropertyChangeListener(src,
				null, oldValue, newValue);
		sup.addPropertyChangeListener(l1);
		sup.addPropertyChangeListener(l3);
		sup.addPropertyChangeListener("myProp", l4);

		sup.firePropertyChange(null, oldValue.intValue(), newValue.intValue());
		l1.assertCalled();
		l2.assertNotCalled();
		l4.assertNotCalled();
	}

	/*
	 * Test the method firePropertyChange(String, int, int) when a null listener
	 * has been registered.
	 */
	public void testFirePropertyChange_Int_NullListener() {
		Object src = new Object();
		PropertyChangeSupport sup = new PropertyChangeSupport(src);

		sup.addPropertyChangeListener(null);
		try {
			sup.firePropertyChange("myProp", 1, 2);
			fail("Should throw NullPointerException!");
		} catch (NullPointerException ex) {
			// expected
		}
	}

	/*
	 * Test serialization/deserialization.
	 */
	public void testSerialization() throws Exception {
		Object src = "PropertyChangeSupportSerializationTest";
		PropertyChangeSupport sup = new PropertyChangeSupport(src);
		PropertyChangeSupport sup2 = new PropertyChangeSupport(src);
		Integer newValue = new Integer(1);
		Integer oldValue = new Integer(2);

		MockPropertyChangeListener l1 = new MockPropertyChangeListener(src,
				"myProp", oldValue, newValue);
		MockPropertyChangeListener2 l2 = new MockPropertyChangeListener2();

		sup.addPropertyChangeListener(l1);
		sup.addPropertyChangeListener("myProp", l1);
		sup.addPropertyChangeListener("myProp", l2);
		sup2.addPropertyChangeListener(l1);
		sup2.addPropertyChangeListener("myProp", l1);

		PropertyChangeSupport deSup = (PropertyChangeSupport) SerializationTester
				.getDeserilizedObject(sup);
		assertEquals(sup2.getPropertyChangeListeners()[0], deSup
				.getPropertyChangeListeners()[0]);
		assertEquals(((PropertyChangeListenerProxy) sup2
				.getPropertyChangeListeners()[1]).getListener(),
				((PropertyChangeListenerProxy) deSup
						.getPropertyChangeListeners()[1]).getListener());
	}

	/*
	 * Test serialization/deserialization compatibility
	 */
	public void testSerializationCompatibility() throws Exception {
		Object src = "PropertyChangeSupportSerializationTest";
		PropertyChangeSupport sup = new PropertyChangeSupport(src);
		PropertyChangeSupport sup2 = new PropertyChangeSupport(src);
		Integer newValue = new Integer(1);
		Integer oldValue = new Integer(2);

		MockPropertyChangeListener l1 = new MockPropertyChangeListener(src,
				"myProp", oldValue, newValue);
		MockPropertyChangeListener2 l2 = new MockPropertyChangeListener2();

		sup.addPropertyChangeListener(l1);
		sup.addPropertyChangeListener("myProp", l1);
		sup.addPropertyChangeListener("myProp", l2);
		sup2.addPropertyChangeListener(l1);
		sup2.addPropertyChangeListener("myProp", l1);

		PropertyChangeSupport deSup = (PropertyChangeSupport) SerializationTester
				.readObject(sup,
						"tests/api/java/beans/PropertyChangeSupport.ser");
		assertEquals(sup2.getPropertyChangeListeners()[0], deSup
				.getPropertyChangeListeners()[0]);
		assertEquals(((PropertyChangeListenerProxy) sup2
				.getPropertyChangeListeners()[1]).getListener(),
				((PropertyChangeListenerProxy) deSup
						.getPropertyChangeListeners()[1]).getListener());
	}

	/*
	 * Mock PropertyChangeListener.
	 */
	static class MockPropertyChangeListener implements PropertyChangeListener,
			Serializable {

		private transient Object expSrc;

		private String expPropName;

		private transient Object expOldValue;

		private transient Object expNewValue;

		private transient PropertyChangeEvent event;

		private transient boolean called = false;

		public MockPropertyChangeListener() {
		}

		public MockPropertyChangeListener(Object src, String propName,
				Object oldValue, Object newValue) {
			this.expSrc = src;
			this.expPropName = propName;
			this.expOldValue = oldValue;
			this.expNewValue = newValue;
		}

		public void setAll(Object src, String propName, Object oldValue,
				Object newValue) {
			this.expSrc = src;
			this.expPropName = propName;
			this.expOldValue = oldValue;
			this.expNewValue = newValue;
		}

		public void propertyChange(PropertyChangeEvent event) {
			this.event = event;
		}

		public void assertCalled() {
			assertSame(expSrc, event.getSource());
			assertEquals(expPropName, event.getPropertyName());
			assertEquals(expOldValue, event.getOldValue());
			assertEquals(expNewValue, event.getNewValue());
			assertNull(event.getPropagationId());
		}

		public void assertNotCalled() {
			assertNull(event);
			assertFalse(called);
		}

		public boolean equals(Object obj) {
			if (obj instanceof MockPropertyChangeListener) {
				MockPropertyChangeListener l = (MockPropertyChangeListener) obj;
				return null == this.expPropName ? null == l.expPropName
						: this.expPropName.equals(l.expPropName);
			}
			return false;
		}
	}

	/*
	 * Mock PropertyChangeListener which is not serializable.
	 */
	static class MockPropertyChangeListener2 implements PropertyChangeListener {

		public void propertyChange(PropertyChangeEvent event) {
		}
	}
}
