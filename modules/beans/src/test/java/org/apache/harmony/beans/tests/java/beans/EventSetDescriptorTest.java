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

import java.beans.EventSetDescriptor;
import java.beans.IntrospectionException;
import java.beans.MethodDescriptor;
import java.io.IOException;
import java.io.Serializable;
import java.lang.reflect.Method;

import junit.framework.TestCase;
import org.apache.harmony.beans.tests.java.beans.mock.MockFakeListener;
import org.apache.harmony.beans.tests.java.beans.auxiliary.OtherBean;
import org.apache.harmony.beans.tests.java.beans.auxiliary.SampleListener;


/**
 * Unit test for EventSetDescriptor
 */
public class EventSetDescriptorTest extends TestCase {

	/*
	 * @see TestCase#setUp()
	 */
	protected void setUp() throws Exception {
		super.setUp();
	}

	/*
	 * @see TestCase#tearDown()
	 */
	protected void tearDown() throws Exception {
		super.tearDown();
	}

	/*
	 * Class under test for void EventSetDescriptor(Class, String, Class,
	 * String)
	 */
	public void testEventSetDescriptorClassStringClassString()
			throws IntrospectionException, ClassNotFoundException, IOException,
			SecurityException, NoSuchMethodException {
		String eventSetName = "mockPropertyChange";
		String listenerMethodName = eventSetName;
		Class sourceClass = MockSourceClass.class;
		Class listenerType = MockPropertyChangeListener.class;
		EventSetDescriptor esd = null;
		esd = new EventSetDescriptor(sourceClass, eventSetName, listenerType,
				listenerMethodName);
		String listenerName = getUnQualifiedClassName(listenerType);
		Method addMethod = sourceClass.getMethod("add" + listenerName,
				new Class[] { listenerType });
		Method removeMethod = sourceClass.getMethod("remove" + listenerName,
				new Class[] { listenerType });

		assertEquals(addMethod, esd.getAddListenerMethod());
		assertEquals(removeMethod, esd.getRemoveListenerMethod());
		assertNull(esd.getGetListenerMethod());
		assertEquals(1, esd.getListenerMethods().length);
		assertEquals(listenerMethodName, esd.getListenerMethods()[0].getName());
		assertEquals(1, esd.getListenerMethodDescriptors().length);
		assertEquals(listenerMethodName, esd.getListenerMethodDescriptors()[0]
				.getMethod().getName());

		assertEquals(listenerType, esd.getListenerType());
		assertTrue(esd.isInDefaultEventSet());
		assertFalse(esd.isUnicast());
	}

	public void testEventSetDescriptorClassStringClassString2()
			throws IntrospectionException, ClassNotFoundException, IOException,
			SecurityException, NoSuchMethodException {
		String eventSetName = "mockPropertyChange";
		String listenerMethodName = eventSetName;
		Class sourceClass = MockSourceClass.class;
		Class listenerType = MockPropertyChangeListener.class;
		EventSetDescriptor esd = null;
		try {
			esd = new EventSetDescriptor(sourceClass, "FFF", listenerType,
					listenerMethodName);
			fail("Should throw IntrospectionException.");
		} catch (IntrospectionException e) {

		}
	}

	/*
	 * Sourceclass==null
	 */
	public void testEventSetDescriptorClassStringClassString_sourceClassNull()
			throws IntrospectionException {
		String eventSetName = "mockPropertyChange";
		String listenerMethodName = eventSetName;
		Class sourceClass = null;
		Class listenerType = MockPropertyChangeListener.class;
		try {
			EventSetDescriptor esd = new EventSetDescriptor(sourceClass,
					eventSetName, listenerType, listenerMethodName);
			fail("Should throw NullPointerException.");
		} catch (NullPointerException e) {
		}
	}

	/*
	 * Event is null
	 */
	public void testEventSetDescriptorClassStringClassString_EventNull()
			throws IntrospectionException {
		String eventSetName = "mockPropertyChange";
		String listenerMethodName = eventSetName;
		Class sourceClass = MockSourceClass.class;
		Class listenerType = MockPropertyChangeListener.class;
		try {
			EventSetDescriptor esd = new EventSetDescriptor(sourceClass, null,
					listenerType, listenerMethodName);
			fail("Should throw NullPointerException.");
		} catch (NullPointerException e) {
		}
	}

	/*
	 * Eventsetname=""
	 */
	public void testEventSetDescriptorClassStringClassString_EventEmpty()
			throws IntrospectionException {
		String eventSetName = "mockPropertyChange";
		String listenerMethodName = eventSetName;
		Class sourceClass = MockSourceClass.class;
		Class listenerType = MockPropertyChangeListener.class;
		try {
			EventSetDescriptor esd = new EventSetDescriptor(sourceClass, "",
					listenerType, listenerMethodName);
			fail("Should throw StringIndexOutOfBoundsException.");
		} catch (StringIndexOutOfBoundsException e) {
		}
	}

	/*
	 * Event is not a subclass of java.util.EventObject.
	 */
	public void testEventSetDescriptorClassStringClassString_EventInvalid()
			throws IntrospectionException {
		String eventSetName = "MockFake";
		String listenerMethodName = "mockNotAEventObject";
		Class sourceClass = MockSourceClass.class;
		Class listenerType = MockPropertyChangeListener.class;
		EventSetDescriptor esd = new EventSetDescriptor(sourceClass,
				eventSetName, listenerType, listenerMethodName);
		assertEquals(listenerMethodName, esd.getListenerMethods()[0].getName());
	}

	public void testEventSetDescriptorClassStringClassString_AmbiguousEvent()
			throws IntrospectionException, ClassNotFoundException, IOException,
			SecurityException, NoSuchMethodException {
		String eventSetName = "mockPropertyChange";
		String listenerMethodName = eventSetName;
		Class sourceClass = MockSourceClass.class;
		Class listenerType = org.apache.harmony.beans.tests.java.beans.mock.MockPropertyChangeListener2.class;
		EventSetDescriptor esd = new EventSetDescriptor(sourceClass,
				eventSetName, listenerType, listenerMethodName);

		String listenerName = getUnQualifiedClassName(listenerType);
		Method addMethod = sourceClass.getMethod("add" + listenerName,
				new Class[] { listenerType });
		Method removeMethod = sourceClass.getMethod("remove" + listenerName,
				new Class[] { listenerType });

		assertEquals(addMethod, esd.getAddListenerMethod());
		assertEquals(removeMethod, esd.getRemoveListenerMethod());
		assertNull(esd.getGetListenerMethod());
		assertEquals(1, esd.getListenerMethods().length);
		assertEquals(listenerMethodName, esd.getListenerMethods()[0].getName());
		assertEquals(1, esd.getListenerMethodDescriptors().length);
		assertEquals(listenerMethodName, esd.getListenerMethodDescriptors()[0]
				.getMethod().getName());

		assertEquals(listenerType, esd.getListenerType());
		assertTrue(esd.isInDefaultEventSet());
		assertFalse(esd.isUnicast());
	}

	/*
	 * ListenerType=null
	 */
	public void testEventSetDescriptorClassStringClassString_ListenerNull()
			throws IntrospectionException {
		String eventSetName = "mockPropertyChange";
		String listenerMethodName = eventSetName;
		Class sourceClass = MockSourceClass.class;
		Class listenerType = null;
		try {
			EventSetDescriptor esd = new EventSetDescriptor(sourceClass,
					eventSetName, listenerType, listenerMethodName);
			fail("Should throw NullPointerException.");
		} catch (NullPointerException e) {
		}
	}

	/*
	 * ListenerType does not implement any EventListener
	 */
	public void testEventSetDescriptorClassStringClassString_ListenerInvalid()
			throws IntrospectionException, SecurityException,
			NoSuchMethodException {
		String eventSetName = "MockPropertyChange";
		String listenerMethodName = "mockPropertyChange";
		Class sourceClass = MockSourceClass.class;
		Class listenerType = MockFakeListener.class;
		EventSetDescriptor esd = new EventSetDescriptor(sourceClass,
				eventSetName, listenerType, listenerMethodName);
		String listenerName = getUnQualifiedClassName(listenerType);
		Method addMethod = sourceClass.getMethod("add" + listenerName,
				new Class[] { listenerType });
		Method removeMethod = sourceClass.getMethod("remove" + listenerName,
				new Class[] { listenerType });

		assertEquals(addMethod, esd.getAddListenerMethod());
		assertEquals(removeMethod, esd.getRemoveListenerMethod());
		assertNull(esd.getGetListenerMethod());
		assertEquals(1, esd.getListenerMethods().length);
		assertEquals(listenerMethodName, esd.getListenerMethods()[0].getName());
		assertEquals(1, esd.getListenerMethodDescriptors().length);
		assertEquals(listenerMethodName, esd.getListenerMethodDescriptors()[0]
				.getMethod().getName());

		assertEquals(listenerType, esd.getListenerType());
		assertTrue(esd.isInDefaultEventSet());
		assertFalse(esd.isUnicast());
	}

	/*
	 * listenerMethodName is null
	 */
	public void testEventSetDescriptorClassStringClassString_listenerMethodNameNull()
			throws IntrospectionException {
		String eventSetName = "mockPropertyChange";
		String listenerMethodName = null;
		Class sourceClass = MockSourceClass.class;
		Class listenerType = MockPropertyChangeListener.class;
		try {
			EventSetDescriptor esd = new EventSetDescriptor(sourceClass,
					eventSetName, listenerType, listenerMethodName);
			fail("Should throw NullPointerException.");
		} catch (NullPointerException e) {
		}

	}

	/*
	 * No this method specified by listenerMethodName
	 */
	public void testEventSetDescriptorClassStringClassString_listenerMethodNameInvalid() {
		String eventSetName = "mockPropertyChange";
		String listenerMethodName = "";
		Class sourceClass = MockSourceClass.class;
		Class listenerType = MockPropertyChangeListener.class;
		try {
			EventSetDescriptor esd = new EventSetDescriptor(sourceClass,
					eventSetName, listenerType, listenerMethodName);
			fail("Should throw IntrospectionException.");
		} catch (IntrospectionException e) {
		}
	}

	/*
	 * Class under test for void EventSetDescriptor(Class, String, Class,
	 * String[], String, String)
	 */
	public void testEventSetDescriptorClassStringClassStringArrayStringString()
			throws IntrospectionException {
		Class sourceClass = MockSourceClass.class;
		String eventSetName = "MockPropertyChange";
		Class listenerType = MockPropertyChangeListener.class;
		String[] listenerMethodNames = { "mockPropertyChange",
				"mockPropertyChange2", };
		String addMethod = "addMockPropertyChangeListener";
		String removeMethod = "removeMockPropertyChangeListener";

		EventSetDescriptor esd = new EventSetDescriptor(sourceClass,
				eventSetName, listenerType, listenerMethodNames, addMethod,
				removeMethod);

		assertEquals(addMethod, esd.getAddListenerMethod().getName());
		assertEquals(removeMethod, esd.getRemoveListenerMethod().getName());
		assertNull(esd.getGetListenerMethod());
		assertEquals(2, esd.getListenerMethods().length);
		assertEquals(listenerMethodNames[0], esd.getListenerMethods()[0]
				.getName());
		assertEquals(listenerMethodNames[1], esd.getListenerMethods()[1]
				.getName());
		assertEquals(2, esd.getListenerMethodDescriptors().length);
		assertEquals(listenerMethodNames[0],
				esd.getListenerMethodDescriptors()[0].getMethod().getName());
		assertEquals(listenerMethodNames[1],
				esd.getListenerMethodDescriptors()[1].getMethod().getName());

		assertEquals(listenerType, esd.getListenerType());
		assertTrue(esd.isInDefaultEventSet());
		assertFalse(esd.isUnicast());
	}

	/*
	 * sourceClass is null
	 */
	public void testEventSetDescriptorClassStringClassStringArrayStringString_sourceClassNull()
			throws IntrospectionException {
		Class sourceClass = null;
		String eventSetName = "MockPropertyChange";
		Class listenerType = MockPropertyChangeListener.class;
		String[] listenerMethodNames = { "mockPropertyChange",
				"mockPropertyChange2", };
		String addMethod = "addMockPropertyChangeListener";
		String removeMethod = "removeMockPropertyChangeListener";

		try {
			EventSetDescriptor esd = new EventSetDescriptor(sourceClass,
					eventSetName, listenerType, listenerMethodNames, addMethod,
					removeMethod);
			fail("Should throw NullPointerException.");
		} catch (NullPointerException e) {
		}
	}

	/*
	 * Event is null
	 */
	public void testEventSetDescriptorClassStringClassStringArrayStringString_eventNull()
			throws IntrospectionException {
		Class sourceClass = MockSourceClass.class;
		String eventSetName = null;
		Class listenerType = MockPropertyChangeListener.class;
		String[] listenerMethodNames = { "mockPropertyChange",
				"mockPropertyChange2", };
		String addMethod = "addMockPropertyChangeListener";
		String removeMethod = "removeMockPropertyChangeListener";

		EventSetDescriptor esd = new EventSetDescriptor(sourceClass,
				eventSetName, listenerType, listenerMethodNames, addMethod,
				removeMethod);
		assertEquals(addMethod, esd.getAddListenerMethod().getName());
		assertEquals(removeMethod, esd.getRemoveListenerMethod().getName());
		assertNull(esd.getGetListenerMethod());
		assertEquals(2, esd.getListenerMethods().length);
		assertEquals(listenerMethodNames[0], esd.getListenerMethods()[0]
				.getName());
		assertEquals(listenerMethodNames[1], esd.getListenerMethods()[1]
				.getName());
		assertEquals(2, esd.getListenerMethodDescriptors().length);
		assertEquals(listenerMethodNames[0],
				esd.getListenerMethodDescriptors()[0].getMethod().getName());
		assertEquals(listenerMethodNames[1],
				esd.getListenerMethodDescriptors()[1].getMethod().getName());

		assertEquals(listenerType, esd.getListenerType());
		assertTrue(esd.isInDefaultEventSet());
		assertFalse(esd.isUnicast());
	}

	/*
	 * Eventsetname=""
	 */
	public void testEventSetDescriptorClassStringClassStringArrayStringString_eventEmpty()
			throws IntrospectionException {
		Class sourceClass = MockSourceClass.class;
		String eventSetName = "";
		Class listenerType = MockPropertyChangeListener.class;
		String[] listenerMethodNames = { "mockPropertyChange",
				"mockPropertyChange2", };
		String addMethod = "addMockPropertyChangeListener";
		String removeMethod = "removeMockPropertyChangeListener";

		EventSetDescriptor esd = new EventSetDescriptor(sourceClass,
				eventSetName, listenerType, listenerMethodNames, addMethod,
				removeMethod);
		assertEquals(addMethod, esd.getAddListenerMethod().getName());
		assertEquals(removeMethod, esd.getRemoveListenerMethod().getName());
		assertNull(esd.getGetListenerMethod());
		assertEquals(2, esd.getListenerMethods().length);
		assertEquals(listenerMethodNames[0], esd.getListenerMethods()[0]
				.getName());
		assertEquals(listenerMethodNames[1], esd.getListenerMethods()[1]
				.getName());
		// ESD does not check parameter type.
		assertEquals(MockPropertyChangeEvent.class, esd.getListenerMethods()[1]
				.getParameterTypes()[0]);

		assertEquals(2, esd.getListenerMethodDescriptors().length);
		assertEquals(listenerMethodNames[0],
				esd.getListenerMethodDescriptors()[0].getMethod().getName());
		assertEquals(listenerMethodNames[1],
				esd.getListenerMethodDescriptors()[1].getMethod().getName());

		assertEquals(listenerType, esd.getListenerType());
		assertTrue(esd.isInDefaultEventSet());
		assertFalse(esd.isUnicast());
	}

	/*
	 * listenerType=null
	 */
	public void testEventSetDescriptorClassStringClassStringArrayStringString_ListenerNull()
			throws IntrospectionException {
		Class sourceClass = MockSourceClass.class;
		String eventSetName = "MockPropertyChange";
		Class listenerType = null;
		String[] listenerMethodNames = { "mockPropertyChange",
				"mockPropertyChange2", };
		String addMethod = "addMockPropertyChangeListener";
		String removeMethod = "removeMockPropertyChangeListener";
		try {
			EventSetDescriptor esd = new EventSetDescriptor(sourceClass,
					eventSetName, listenerType, listenerMethodNames, addMethod,
					removeMethod);
			fail("Should throw NullPointerException.");
		} catch (NullPointerException e) {
		}

	}

	/*
	 * listenerMethodNames=null
	 */
	public void testEventSetDescriptorClassStringClassStringArrayStringString_listenerMethodNamesNull()
			throws IntrospectionException {
		Class sourceClass = MockSourceClass.class;
		String eventSetName = "MockPropertyChange";
		Class listenerType = MockPropertyChangeListener.class;
		String[] listenerMethodNames = null;
		String addMethod = "addMockPropertyChangeListener";
		String removeMethod = "removeMockPropertyChangeListener";
		try {
			EventSetDescriptor esd = new EventSetDescriptor(sourceClass,
					eventSetName, listenerType, listenerMethodNames, addMethod,
					removeMethod);
			fail("Should throw NullPointerException.");
		} catch (NullPointerException e) {
		}

	}

	/*
	 * contain invalid method.
	 */
	public void testEventSetDescriptorClassStringClassStringArrayStringString_listenerMethodNamesInvalid() {
		Class sourceClass = MockSourceClass.class;
		String eventSetName = "MockPropertyChange";
		Class listenerType = MockPropertyChangeListener.class;
		String[] listenerMethodNames = { "mockPropertyChange_Invalid",
				"mockPropertyChange2", };
		String addMethod = "addMockPropertyChangeListener";
		String removeMethod = "removeMockPropertyChangeListener";
		try {
			EventSetDescriptor esd = new EventSetDescriptor(sourceClass,
					eventSetName, listenerType, listenerMethodNames, addMethod,
					removeMethod);
			fail("Should throw IntrospectionException.");
		} catch (IntrospectionException e) {
		}
	}

	public void testEventSetDescriptorClassStringClassStringArrayStringString_listenerMethodNamesEmpty()
			throws IntrospectionException {
		Class sourceClass = MockSourceClass.class;
		String eventSetName = "MockPropertyChange";
		Class listenerType = MockPropertyChangeListener.class;
		String[] listenerMethodNames = {};
		String addMethod = "addMockPropertyChangeListener";
		String removeMethod = "removeMockPropertyChangeListener";
		EventSetDescriptor esd = new EventSetDescriptor(sourceClass,
				eventSetName, listenerType, listenerMethodNames, addMethod,
				removeMethod);
		assertEquals(0, esd.getListenerMethods().length);
	}

	/*
	 * addListenerMethodName==null
	 */
	public void testEventSetDescriptorClassStringClassStringArrayStringString_addListenerMethodNameNull()
			throws IntrospectionException {
		Class sourceClass = MockSourceClass.class;
		String eventSetName = "MockPropertyChange";
		Class listenerType = MockPropertyChangeListener.class;
		String[] listenerMethodNames = { "mockPropertyChange",
				"mockPropertyChange2", };
		String addMethod = null;
		String removeMethod = "removeMockPropertyChangeListener";
		EventSetDescriptor esd = new EventSetDescriptor(sourceClass,
				eventSetName, listenerType, listenerMethodNames, addMethod,
				removeMethod);
		assertNull(esd.getAddListenerMethod());
	}

	/*
	 * addListenerMethodName is invalid (args)
	 */
	public void testEventSetDescriptorClassStringClassStringArrayStringString_addListenerMethodNameInvalid()
			throws IntrospectionException {
		Class sourceClass = MockSourceClass.class;
		String eventSetName = "MockPropertyChange";
		Class listenerType = MockPropertyChangeListener.class;
		String[] listenerMethodNames = { "mockPropertyChange",
				"mockPropertyChange2", };
		String addMethod = "addMockPropertyChangeListener_Invalid";
		String removeMethod = "removeMockPropertyChangeListener";
		try {
			EventSetDescriptor esd = new EventSetDescriptor(sourceClass,
					eventSetName, listenerType, listenerMethodNames, addMethod,
					removeMethod);
			fail("Should throw IntrospectionException.");
		} catch (IntrospectionException e) {
		}
	}

	/*
	 * removeListenerMethodName==null
	 */
	public void testEventSetDescriptorClassStringClassStringArrayStringString_removeListenerMethodNameNull()
			throws IntrospectionException {
		Class sourceClass = MockSourceClass.class;
		String eventSetName = "MockPropertyChange";
		Class listenerType = MockPropertyChangeListener.class;
		String[] listenerMethodNames = { "mockPropertyChange",
				"mockPropertyChange2", };
		String addMethod = "removeMockPropertyChangeListener";
		String removeMethod = null;
		EventSetDescriptor esd = new EventSetDescriptor(sourceClass,
				eventSetName, listenerType, listenerMethodNames, addMethod,
				removeMethod);
		assertNull(esd.getRemoveListenerMethod());
	}

	/*
	 * removeListenerMethodName is invalid
	 */
	public void testEventSetDescriptorClassStringClassStringArrayStringString_removeListenerMethodNameInvalid()
			throws IntrospectionException {
		Class sourceClass = MockSourceClass.class;
		String eventSetName = "MockPropertyChange";
		Class listenerType = MockPropertyChangeListener.class;
		String[] listenerMethodNames = { "mockPropertyChange",
				"mockPropertyChange2", };
		String addMethod = "removeMockPropertyChangeListener";
		String removeMethod = "addMockPropertyChangeListener_Invalid";
		try {
			EventSetDescriptor esd = new EventSetDescriptor(sourceClass,
					eventSetName, listenerType, listenerMethodNames, addMethod,
					removeMethod);
			fail("Should throw IntrospectionException.");
		} catch (IntrospectionException e) {
		}

	}

	/*
	 * Class under test for void EventSetDescriptor(Class, String, Class,
	 * String[], String, String, String)
	 */
	public void testEventSetDescriptorClassStringClassStringArrayStringStringString()
			throws IntrospectionException {
		Class sourceClass = MockSourceClass.class;
		String eventSetName = "MockPropertyChange";
		Class listenerType = MockPropertyChangeListener.class;
		String[] listenerMethodNames = { "mockPropertyChange",
				"mockPropertyChange2", };
		String addMethod = "addMockPropertyChangeListener";
		String removeMethod = "removeMockPropertyChangeListener";
		String getMethod = "getMockPropertyChangeListener";
		EventSetDescriptor esd = new EventSetDescriptor(sourceClass,
				eventSetName, listenerType, listenerMethodNames, addMethod,
				removeMethod, getMethod);

		assertEquals(addMethod, esd.getAddListenerMethod().getName());
		assertEquals(removeMethod, esd.getRemoveListenerMethod().getName());
		assertEquals(getMethod, esd.getGetListenerMethod().getName());

		assertEquals(2, esd.getListenerMethods().length);
		assertEquals(listenerMethodNames[0], esd.getListenerMethods()[0]
				.getName());
		assertEquals(listenerMethodNames[1], esd.getListenerMethods()[1]
				.getName());
		assertEquals(2, esd.getListenerMethodDescriptors().length);
		assertEquals(listenerMethodNames[0],
				esd.getListenerMethodDescriptors()[0].getMethod().getName());
		assertEquals(listenerMethodNames[1],
				esd.getListenerMethodDescriptors()[1].getMethod().getName());

		assertEquals(listenerType, esd.getListenerType());
		assertTrue(esd.isInDefaultEventSet());
		assertFalse(esd.isUnicast());
	}

	/*
	 * getListenerMethodName is null
	 */
	public void testEventSetDescriptorClassStringClassStringArrayStringStringString_getListenerMethodNameNull()
			throws IntrospectionException {
		Class sourceClass = MockSourceClass.class;
		String eventSetName = "MockPropertyChange";
		Class listenerType = MockPropertyChangeListener.class;
		String[] listenerMethodNames = { "mockPropertyChange",
				"mockPropertyChange2", };
		String addMethod = "addMockPropertyChangeListener";
		String removeMethod = "removeMockPropertyChangeListener";
		String getMethod = null;
		;
		EventSetDescriptor esd = new EventSetDescriptor(sourceClass,
				eventSetName, listenerType, listenerMethodNames, addMethod,
				removeMethod, getMethod);
		assertNull(esd.getGetListenerMethod());
	}

	/*
	 * getListenerMethodName is invalid (return void)
	 */
	public void testEventSetDescriptorClassStringClassStringArrayStringStringString_getListenerMethodNameInvalid()
			throws IntrospectionException {
		Class sourceClass = MockSourceClass.class;
		String eventSetName = "MockPropertyChange";
		Class listenerType = MockPropertyChangeListener.class;
		String[] listenerMethodNames = { "mockPropertyChange",
				"mockPropertyChange2", };
		String addMethod = "addMockPropertyChangeListener";
		String removeMethod = "removeMockPropertyChangeListener";
		String getMethod = addMethod;
		EventSetDescriptor esd = new EventSetDescriptor(sourceClass,
				eventSetName, listenerType, listenerMethodNames, addMethod,
				removeMethod, getMethod);
		assertEquals(addMethod, esd.getGetListenerMethod().getName());
	}

	/*
	 * Class under test for void EventSetDescriptor(String, Class, Method[],
	 * Method, Method)
	 */
	public void testEventSetDescriptorStringClassMethodArrayMethodMethod()
			throws SecurityException, NoSuchMethodException,
			IntrospectionException {
		String eventSetName = "MockPropertyChange";
		Class listenerType = MockPropertyChangeListener.class;
		Method[] listenerMethods = new Method[] {
				listenerType.getMethod("mockPropertyChange",
						new Class[] { MockPropertyChangeEvent.class }),
				listenerType.getMethod("mockPropertyChange2",
						new Class[] { MockPropertyChangeEvent.class }), };
		Class sourceClass = MockSourceClass.class;
		Method addMethod = sourceClass.getMethod(
				"addMockPropertyChangeListener", new Class[] { listenerType });
		Method removeMethod = sourceClass.getMethod(
				"removeMockPropertyChangeListener",
				new Class[] { listenerType });
		EventSetDescriptor esd = new EventSetDescriptor(eventSetName,
				listenerType, listenerMethods, addMethod, removeMethod);

		assertEquals(addMethod, esd.getAddListenerMethod());
		assertEquals(removeMethod, esd.getRemoveListenerMethod());
		assertNull(esd.getGetListenerMethod());
		assertEquals(listenerMethods, esd.getListenerMethods());

		assertEquals(2, esd.getListenerMethodDescriptors().length);
		assertEquals(listenerMethods[0], esd.getListenerMethodDescriptors()[0]
				.getMethod());
		assertEquals(listenerMethods[1], esd.getListenerMethodDescriptors()[1]
				.getMethod());

		assertEquals(listenerType, esd.getListenerType());
		assertTrue(esd.isInDefaultEventSet());
		assertFalse(esd.isUnicast());
	}

	/*
	 * eventSetName=null
	 */
	public void testEventSetDescriptorStringClassMethodArrayMethodMethod_EventNull()
			throws SecurityException, NoSuchMethodException,
			IntrospectionException {
		String eventSetName = null;
		Class listenerType = MockPropertyChangeListener.class;
		Method[] listenerMethods = new Method[] {
				listenerType.getMethod("mockPropertyChange",
						new Class[] { MockPropertyChangeEvent.class }),
				listenerType.getMethod("mockPropertyChange2",
						new Class[] { MockPropertyChangeEvent.class }), };
		Class sourceClass = MockSourceClass.class;
		Method addMethod = sourceClass.getMethod(
				"addMockPropertyChangeListener", new Class[] { listenerType });
		Method removeMethod = sourceClass.getMethod(
				"removeMockPropertyChangeListener",
				new Class[] { listenerType });
		EventSetDescriptor esd = new EventSetDescriptor(eventSetName,
				listenerType, listenerMethods, addMethod, removeMethod);

		assertEquals(addMethod, esd.getAddListenerMethod());
		assertEquals(removeMethod, esd.getRemoveListenerMethod());
		assertNull(esd.getGetListenerMethod());
		assertEquals(listenerMethods, esd.getListenerMethods());

		assertEquals(2, esd.getListenerMethodDescriptors().length);
		assertEquals(listenerMethods[0], esd.getListenerMethodDescriptors()[0]
				.getMethod());
		assertEquals(listenerMethods[1], esd.getListenerMethodDescriptors()[1]
				.getMethod());

		assertEquals(listenerType, esd.getListenerType());
		assertTrue(esd.isInDefaultEventSet());
		assertFalse(esd.isUnicast());
	}

	/*
	 * eventSetName=""
	 */
	public void testEventSetDescriptorStringClassMethodArrayMethodMethod_EventEmpty()
			throws SecurityException, NoSuchMethodException,
			IntrospectionException {
		String eventSetName = "";
		Class listenerType = MockPropertyChangeListener.class;
		Method[] listenerMethods = new Method[] {
				listenerType.getMethod("mockPropertyChange",
						new Class[] { MockPropertyChangeEvent.class }),
				listenerType.getMethod("mockPropertyChange2",
						new Class[] { MockPropertyChangeEvent.class }), };
		Class sourceClass = MockSourceClass.class;
		Method addMethod = sourceClass.getMethod(
				"addMockPropertyChangeListener", new Class[] { listenerType });
		Method removeMethod = sourceClass.getMethod(
				"removeMockPropertyChangeListener",
				new Class[] { listenerType });
		EventSetDescriptor esd = new EventSetDescriptor(eventSetName,
				listenerType, listenerMethods, addMethod, removeMethod);

		assertEquals(addMethod, esd.getAddListenerMethod());
		assertEquals(removeMethod, esd.getRemoveListenerMethod());
		assertNull(esd.getGetListenerMethod());
		assertEquals(listenerMethods, esd.getListenerMethods());

		assertEquals(2, esd.getListenerMethodDescriptors().length);
		assertEquals(listenerMethods[0], esd.getListenerMethodDescriptors()[0]
				.getMethod());
		assertEquals(listenerMethods[1], esd.getListenerMethodDescriptors()[1]
				.getMethod());

		assertEquals(listenerType, esd.getListenerType());
		assertTrue(esd.isInDefaultEventSet());
		assertFalse(esd.isUnicast());
	}

	/*
	 * listenerType=null
	 */
	public void testEventSetDescriptorStringClassMethodArrayMethodMethod_ListenerTypeNull()
			throws SecurityException, NoSuchMethodException,
			IntrospectionException {
		String eventSetName = "MockPropertyChange";
		Class listenerType = MockPropertyChangeListener.class;
		Method[] listenerMethods = new Method[] {
				listenerType.getMethod("mockPropertyChange",
						new Class[] { MockPropertyChangeEvent.class }),
				listenerType.getMethod("mockPropertyChange2",
						new Class[] { MockPropertyChangeEvent.class }), };
		Class sourceClass = MockSourceClass.class;
		Method addMethod = sourceClass.getMethod(
				"addMockPropertyChangeListener", new Class[] { listenerType });
		Method removeMethod = sourceClass.getMethod(
				"removeMockPropertyChangeListener",
				new Class[] { listenerType });
		EventSetDescriptor esd = new EventSetDescriptor(eventSetName, null,
				listenerMethods, addMethod, removeMethod);

		assertEquals(addMethod, esd.getAddListenerMethod());
		assertEquals(removeMethod, esd.getRemoveListenerMethod());
		assertNull(esd.getGetListenerMethod());
		assertEquals(listenerMethods, esd.getListenerMethods());

		assertEquals(2, esd.getListenerMethodDescriptors().length);
		assertEquals(listenerMethods[0], esd.getListenerMethodDescriptors()[0]
				.getMethod());
		assertEquals(listenerMethods[1], esd.getListenerMethodDescriptors()[1]
				.getMethod());

		assertNull(esd.getListenerType());
		assertTrue(esd.isInDefaultEventSet());
		assertFalse(esd.isUnicast());
	}

	/*
	 * listenerMethods=null
	 */
	public void testEventSetDescriptorStringClassMethodArrayMethodMethod_listenerMethodsNull()
			throws IntrospectionException, NoSuchMethodException {
		String eventSetName = "MockPropertyChange";
		Class listenerType = MockPropertyChangeListener.class;
		Method[] listenerMethods = new Method[] {
				listenerType.getMethod("mockPropertyChange",
						new Class[] { MockPropertyChangeEvent.class }),
				listenerType.getMethod("mockPropertyChange2",
						new Class[] { MockPropertyChangeEvent.class }), };
		Class sourceClass = MockSourceClass.class;
		Method addMethod = sourceClass.getMethod(
				"addMockPropertyChangeListener", new Class[] { listenerType });
		Method removeMethod = sourceClass.getMethod(
				"removeMockPropertyChangeListener",
				new Class[] { listenerType });
		EventSetDescriptor esd = new EventSetDescriptor(eventSetName,
				listenerType, (Method[]) null, addMethod, removeMethod);

		assertEquals(addMethod, esd.getAddListenerMethod());
		assertEquals(removeMethod, esd.getRemoveListenerMethod());
		assertNull(esd.getGetListenerMethod());

		assertNull(esd.getListenerMethods());
		assertNull(esd.getListenerMethodDescriptors());

		assertEquals(listenerType, esd.getListenerType());
		assertTrue(esd.isInDefaultEventSet());
		assertFalse(esd.isUnicast());
	}

	/*
	 * listenerMethods is invalid
	 */
	public void testEventSetDescriptorStringClassMethodArrayMethodMethod_listenerMethodsInvalid()
			throws SecurityException, NoSuchMethodException,
			IntrospectionException {
		String eventSetName = "MockPropertyChange";
		Class listenerType = MockPropertyChangeListener.class;
		Method[] listenerMethods = new Method[] {
				listenerType.getMethod("mockPropertyChange",
						new Class[] { MockPropertyChangeEvent.class }),
				listenerType.getMethod("mockPropertyChange_Invalid", (Class[])null), };
		Class sourceClass = MockSourceClass.class;
		Method addMethod = sourceClass.getMethod(
				"addMockPropertyChangeListener", new Class[] { listenerType });
		Method removeMethod = sourceClass.getMethod(
				"removeMockPropertyChangeListener",
				new Class[] { listenerType });
		EventSetDescriptor esd = new EventSetDescriptor(eventSetName,
				listenerType, listenerMethods, addMethod, removeMethod);
		assertEquals(listenerMethods, esd.getListenerMethods());
	}

	/*
	 * addListenerMethod = null
	 */
	public void testEventSetDescriptorStringClassMethodArrayMethodMethod_addListenerMethodNull()
			throws IntrospectionException, NoSuchMethodException {
		String eventSetName = "MockPropertyChange";
		Class listenerType = MockPropertyChangeListener.class;
		Method[] listenerMethods = new Method[] {
				listenerType.getMethod("mockPropertyChange",
						new Class[] { MockPropertyChangeEvent.class }),
				listenerType.getMethod("mockPropertyChange2",
						new Class[] { MockPropertyChangeEvent.class }), };
		Class sourceClass = MockSourceClass.class;
		Method addMethod = sourceClass.getMethod(
				"addMockPropertyChangeListener", new Class[] { listenerType });
		Method removeMethod = sourceClass.getMethod(
				"removeMockPropertyChangeListener",
				new Class[] { listenerType });
		EventSetDescriptor esd = new EventSetDescriptor(eventSetName,
				listenerType, listenerMethods, null, removeMethod);

		assertNull(esd.getAddListenerMethod());
	}

	/*
	 * addListenerMethod is invalid
	 */
	public void testEventSetDescriptorStringClassMethodArrayMethodMethod_addListenerMethodInvalid()
			throws IntrospectionException, NoSuchMethodException {
		String eventSetName = "MockPropertyChange";
		Class listenerType = MockPropertyChangeListener.class;
		Method[] listenerMethods = new Method[] {
				listenerType.getMethod("mockPropertyChange",
						new Class[] { MockPropertyChangeEvent.class }),
				listenerType.getMethod("mockPropertyChange2",
						new Class[] { MockPropertyChangeEvent.class }), };
		Class sourceClass = MockSourceClass.class;
		Method addMethod = sourceClass.getMethod(
				"addMockPropertyChangeListener_Invalid", (Class[])null);
		Method removeMethod = sourceClass.getMethod(
				"removeMockPropertyChangeListener",
				new Class[] { listenerType });
		EventSetDescriptor esd = new EventSetDescriptor(eventSetName,
				listenerType, listenerMethods, addMethod, removeMethod);
		assertEquals(addMethod, esd.getAddListenerMethod());
	}

	/*
	 * removeListenerMethod = null
	 */
	public void testEventSetDescriptorStringClassMethodArrayMethodMethod_remveListenerMethodNull()
			throws IntrospectionException, NoSuchMethodException {
		String eventSetName = "MockPropertyChange";
		Class listenerType = MockPropertyChangeListener.class;
		Method[] listenerMethods = new Method[] {
				listenerType.getMethod("mockPropertyChange",
						new Class[] { MockPropertyChangeEvent.class }),
				listenerType.getMethod("mockPropertyChange2",
						new Class[] { MockPropertyChangeEvent.class }), };
		Class sourceClass = MockSourceClass.class;
		Method addMethod = sourceClass.getMethod(
				"addMockPropertyChangeListener", new Class[] { listenerType });
		Method removeMethod = sourceClass.getMethod(
				"removeMockPropertyChangeListener",
				new Class[] { listenerType });
		EventSetDescriptor esd = new EventSetDescriptor(eventSetName,
				listenerType, listenerMethods, null, null);
		assertNull(esd.getRemoveListenerMethod());
	}

	/*
	 * removeListenerMethod is invalid
	 */
	public void testEventSetDescriptorStringClassMethodArrayMethodMethod_removeListenerMethodInvalid()
			throws IntrospectionException, NoSuchMethodException {
		String eventSetName = "MockPropertyChange";
		Class listenerType = MockPropertyChangeListener.class;
		Method[] listenerMethods = new Method[] {
				listenerType.getMethod("mockPropertyChange",
						new Class[] { MockPropertyChangeEvent.class }),
				listenerType.getMethod("mockPropertyChange2",
						new Class[] { MockPropertyChangeEvent.class }), };
		Class sourceClass = MockSourceClass.class;
		Method addMethod = sourceClass.getMethod(
				"addMockPropertyChangeListener", new Class[] { listenerType });
		Method removeMethod = sourceClass.getMethod(
				"addMockPropertyChangeListener_Invalid", (Class[])null);
		EventSetDescriptor esd = new EventSetDescriptor(eventSetName,
				listenerType, listenerMethods, addMethod, removeMethod);
		assertEquals(removeMethod, esd.getRemoveListenerMethod());
	}

	/*
	 * Class under test for void EventSetDescriptor(String, Class, Method[],
	 * Method, Method, Method)
	 */
	public void testEventSetDescriptorStringClassMethodArrayMethodMethodMethod()
			throws SecurityException, NoSuchMethodException,
			IntrospectionException {
		String eventSetName = "MockPropertyChange";
		Class listenerType = MockPropertyChangeListener.class;
		Method[] listenerMethods = new Method[] {
				listenerType.getMethod("mockPropertyChange",
						new Class[] { MockPropertyChangeEvent.class }),
				listenerType.getMethod("mockPropertyChange2",
						new Class[] { MockPropertyChangeEvent.class }), };
		Class sourceClass = MockSourceClass.class;
		Method addMethod = sourceClass.getMethod(
				"addMockPropertyChangeListener", new Class[] { listenerType });
		Method removeMethod = sourceClass.getMethod(
				"removeMockPropertyChangeListener",
				new Class[] { listenerType });
		Method getMethod = sourceClass.getMethod(
				"getMockPropertyChangeListener", new Class[] { listenerType });

		EventSetDescriptor esd = new EventSetDescriptor(eventSetName,
				listenerType, listenerMethods, addMethod, removeMethod,
				getMethod);

		assertEquals(getMethod, esd.getGetListenerMethod());
	}

	/*
	 * getListenerMethod is null
	 */
	public void testEventSetDescriptorStringClassMethodArrayMethodMethodMethod_getListenerMethodNull()
			throws IntrospectionException, NoSuchMethodException {
		String eventSetName = "MockPropertyChange";
		Class listenerType = MockPropertyChangeListener.class;
		Method[] listenerMethods = new Method[] {
				listenerType.getMethod("mockPropertyChange",
						new Class[] { MockPropertyChangeEvent.class }),
				listenerType.getMethod("mockPropertyChange2",
						new Class[] { MockPropertyChangeEvent.class }), };
		Class sourceClass = MockSourceClass.class;
		Method addMethod = sourceClass.getMethod(
				"addMockPropertyChangeListener", new Class[] { listenerType });
		Method removeMethod = sourceClass.getMethod(
				"removeMockPropertyChangeListener",
				new Class[] { listenerType });
		Method getMethod = sourceClass.getMethod(
				"getMockPropertyChangeListener", new Class[] { listenerType });

		EventSetDescriptor esd = new EventSetDescriptor(eventSetName,
				listenerType, listenerMethods, addMethod, removeMethod, null);
		assertNull(esd.getGetListenerMethod());
	}

	/*
	 * getListenerMethod is invalid
	 */
	public void testEventSetDescriptorStringClassMethodArrayMethodMethodMethod_getListenerMethodInvalid()
			throws IntrospectionException, NoSuchMethodException {
		String eventSetName = "MockPropertyChange";
		Class listenerType = MockPropertyChangeListener.class;
		Method[] listenerMethods = new Method[] {
				listenerType.getMethod("mockPropertyChange",
						new Class[] { MockPropertyChangeEvent.class }),
				listenerType.getMethod("mockPropertyChange2",
						new Class[] { MockPropertyChangeEvent.class }), };
		Class sourceClass = MockSourceClass.class;
		Method addMethod = sourceClass.getMethod(
				"addMockPropertyChangeListener", new Class[] { listenerType });
		Method removeMethod = sourceClass.getMethod(
				"removeMockPropertyChangeListener",
				new Class[] { listenerType });
		Method getMethod = sourceClass.getMethod(
				"addMockPropertyChangeListener_Invalid", (Class[])null);

		EventSetDescriptor esd = new EventSetDescriptor(eventSetName,
				listenerType, listenerMethods, addMethod, removeMethod,
				getMethod);
		assertEquals(getMethod, esd.getGetListenerMethod());
	}

	/*
	 * Class under test for void EventSetDescriptor(String, Class,
	 * MethodDescriptor[], Method, Method)
	 */
	public void testEventSetDescriptorStringClassMethodDescriptorArrayMethodMethod()
			throws SecurityException, NoSuchMethodException,
			IntrospectionException {
		String eventSetName = "MockPropertyChange";
		Class listenerType = MockPropertyChangeListener.class;
		Method[] listenerMethods = {
				listenerType.getMethod("mockPropertyChange",
						new Class[] { MockPropertyChangeEvent.class }),
				listenerType.getMethod("mockPropertyChange2",
						new Class[] { MockPropertyChangeEvent.class }), };
		MethodDescriptor[] listenerMethodDescriptors = {
				new MethodDescriptor(listenerMethods[0]),
				new MethodDescriptor(listenerMethods[1]), };
		Class sourceClass = MockSourceClass.class;
		Method addMethod = sourceClass.getMethod(
				"addMockPropertyChangeListener", new Class[] { listenerType });
		Method removeMethod = sourceClass.getMethod(
				"removeMockPropertyChangeListener",
				new Class[] { listenerType });

		EventSetDescriptor esd = new EventSetDescriptor(eventSetName,
				listenerType, listenerMethodDescriptors, addMethod,
				removeMethod);

		assertEquals(addMethod, esd.getAddListenerMethod());
		assertEquals(removeMethod, esd.getRemoveListenerMethod());
		assertNull(esd.getGetListenerMethod());
		assertEquals(listenerMethods[0], esd.getListenerMethods()[0]);
		assertEquals(listenerMethods[1], esd.getListenerMethods()[1]);

		assertEquals(2, esd.getListenerMethodDescriptors().length);
		assertEquals(listenerMethods[0], esd.getListenerMethodDescriptors()[0]
				.getMethod());
		assertEquals(listenerMethods[1], esd.getListenerMethodDescriptors()[1]
				.getMethod());

		assertEquals(listenerType, esd.getListenerType());
		assertTrue(esd.isInDefaultEventSet());
		assertFalse(esd.isUnicast());
	}

	/*
	 * listenerMethodDescriptors is null
	 */
	public void testEventSetDescriptorStringClassMethodDescriptorArrayMethodMethod_ListenerMDNull()
			throws IntrospectionException, NoSuchMethodException {
		String eventSetName = "MockPropertyChange";
		Class listenerType = MockPropertyChangeListener.class;
		Method[] listenerMethods = {
				listenerType.getMethod("mockPropertyChange",
						new Class[] { MockPropertyChangeEvent.class }),
				listenerType.getMethod("mockPropertyChange2",
						new Class[] { MockPropertyChangeEvent.class }), };
		MethodDescriptor[] listenerMethodDescriptors = {
				new MethodDescriptor(listenerMethods[0]),
				new MethodDescriptor(listenerMethods[1]), };
		Class sourceClass = MockSourceClass.class;
		Method addMethod = sourceClass.getMethod(
				"addMockPropertyChangeListener", new Class[] { listenerType });
		Method removeMethod = sourceClass.getMethod(
				"removeMockPropertyChangeListener",
				new Class[] { listenerType });

		EventSetDescriptor esd = new EventSetDescriptor(eventSetName,
				listenerType, (MethodDescriptor[]) null, addMethod,
				removeMethod);

		assertNull(esd.getListenerMethodDescriptors());
		assertNull(esd.getListenerMethods());
	}

	/*
	 * listenerMethodDescriptors is invalid
	 */
	public void testEventSetDescriptorStringClassMethodDescriptorArrayMethodMethod_ListenerMDInvalid()
			throws SecurityException, NoSuchMethodException,
			IntrospectionException {
		String eventSetName = "MockPropertyChange";
		Class listenerType = MockPropertyChangeListener.class;
		Method[] listenerMethods = {
				listenerType.getMethod("mockPropertyChange",
						new Class[] { MockPropertyChangeEvent.class }),
				listenerType.getMethod("mockPropertyChange_Invalid", (Class[])null), };
		MethodDescriptor[] listenerMethodDescriptors = {
				new MethodDescriptor(listenerMethods[0]),
				new MethodDescriptor(listenerMethods[1]), };
		Class sourceClass = MockSourceClass.class;
		Method addMethod = sourceClass.getMethod(
				"addMockPropertyChangeListener", new Class[] { listenerType });
		Method removeMethod = sourceClass.getMethod(
				"removeMockPropertyChangeListener",
				new Class[] { listenerType });

		EventSetDescriptor esd = new EventSetDescriptor(eventSetName,
				listenerType, listenerMethodDescriptors, addMethod,
				removeMethod);
		assertEquals(0, esd.getListenerMethods()[1].getParameterTypes().length);
		assertEquals(listenerMethodDescriptors[1], esd
				.getListenerMethodDescriptors()[1]);
	}

	public void testSetInDefaultEventSet() throws SecurityException,
			NoSuchMethodException, IntrospectionException {
		String eventSetName = "MockPropertyChange";
		Class listenerType = MockPropertyChangeListener.class;
		Method[] listenerMethods = {
				listenerType.getMethod("mockPropertyChange",
						new Class[] { MockPropertyChangeEvent.class }),
				listenerType.getMethod("mockPropertyChange2",
						new Class[] { MockPropertyChangeEvent.class }), };
		MethodDescriptor[] listenerMethodDescriptors = {
				new MethodDescriptor(listenerMethods[0]),
				new MethodDescriptor(listenerMethods[1]), };
		Class sourceClass = MockSourceClass.class;
		Method addMethod = sourceClass.getMethod(
				"addMockPropertyChangeListener", new Class[] { listenerType });
		Method removeMethod = sourceClass.getMethod(
				"removeMockPropertyChangeListener",
				new Class[] { listenerType });

		EventSetDescriptor esd = new EventSetDescriptor(eventSetName,
				listenerType, listenerMethodDescriptors, addMethod,
				removeMethod);
		esd.setInDefaultEventSet(true);
		assertTrue(esd.isInDefaultEventSet());
	}

	public void testSetInDefaultEventSet_false() throws SecurityException,
			NoSuchMethodException, IntrospectionException {
		String eventSetName = "MockPropertyChange";
		Class listenerType = MockPropertyChangeListener.class;
		Method[] listenerMethods = {
				listenerType.getMethod("mockPropertyChange",
						new Class[] { MockPropertyChangeEvent.class }),
				listenerType.getMethod("mockPropertyChange2",
						new Class[] { MockPropertyChangeEvent.class }), };
		MethodDescriptor[] listenerMethodDescriptors = {
				new MethodDescriptor(listenerMethods[0]),
				new MethodDescriptor(listenerMethods[1]), };
		Class sourceClass = MockSourceClass.class;
		Method addMethod = sourceClass.getMethod(
				"addMockPropertyChangeListener", new Class[] { listenerType });
		Method removeMethod = sourceClass.getMethod(
				"removeMockPropertyChangeListener",
				new Class[] { listenerType });

		EventSetDescriptor esd = new EventSetDescriptor(eventSetName,
				listenerType, listenerMethodDescriptors, addMethod,
				removeMethod);
		assertTrue(esd.isInDefaultEventSet());
		esd.setInDefaultEventSet(false);
		assertFalse(esd.isInDefaultEventSet());
	}

	public void testSetUnicast() throws SecurityException,
			NoSuchMethodException, IntrospectionException {
		String eventSetName = "MockPropertyChange";
		Class listenerType = MockPropertyChangeListener.class;
		Method[] listenerMethods = {
				listenerType.getMethod("mockPropertyChange",
						new Class[] { MockPropertyChangeEvent.class }),
				listenerType.getMethod("mockPropertyChange2",
						new Class[] { MockPropertyChangeEvent.class }), };
		MethodDescriptor[] listenerMethodDescriptors = {
				new MethodDescriptor(listenerMethods[0]),
				new MethodDescriptor(listenerMethods[1]), };
		Class sourceClass = MockSourceClass.class;
		Method addMethod = sourceClass.getMethod(
				"addMockPropertyChangeListener", new Class[] { listenerType });
		Method removeMethod = sourceClass.getMethod(
				"removeMockPropertyChangeListener",
				new Class[] { listenerType });

		EventSetDescriptor esd = new EventSetDescriptor(eventSetName,
				listenerType, listenerMethodDescriptors, addMethod,
				removeMethod);
		assertFalse(esd.isUnicast());
		esd.setInDefaultEventSet(true);
		assertTrue(esd.isInDefaultEventSet());
	}

	public void testSetUnicast_false() throws SecurityException,
			NoSuchMethodException, IntrospectionException {
		String eventSetName = "MockPropertyChange";
		Class listenerType = MockPropertyChangeListener.class;
		Method[] listenerMethods = {
				listenerType.getMethod("mockPropertyChange",
						new Class[] { MockPropertyChangeEvent.class }),
				listenerType.getMethod("mockPropertyChange2",
						new Class[] { MockPropertyChangeEvent.class }), };
		MethodDescriptor[] listenerMethodDescriptors = {
				new MethodDescriptor(listenerMethods[0]),
				new MethodDescriptor(listenerMethods[1]), };
		Class sourceClass = MockSourceClass.class;
		Method addMethod = sourceClass.getMethod(
				"addMockPropertyChangeListener", new Class[] { listenerType });
		Method removeMethod = sourceClass.getMethod(
				"removeMockPropertyChangeListener",
				new Class[] { listenerType });

		EventSetDescriptor esd = new EventSetDescriptor(eventSetName,
				listenerType, listenerMethodDescriptors, addMethod,
				removeMethod);
		assertFalse(esd.isUnicast());
		esd.setInDefaultEventSet(false);
		assertFalse(esd.isInDefaultEventSet());
	}


    /**
     * The test checks the constructor
     */
    public void testEventSetDescriptorConstructor() {
        try {
            new EventSetDescriptor(OtherBean.class, "sample",
                    SampleListener.class, "fireSampleEvent");
        } catch (Exception e) {
            fail("Exception of " + e.getClass() + 
                " class with message " + e.getMessage() + " is thrown");
        }
        
    }
    
	protected String getUnQualifiedClassName(Class classType) {
		String qName = classType.getName();
		return qName.substring(qName.lastIndexOf('.') + 1);
	}

	class MockSourceClass implements Serializable {

		/**
		 * Comment for <code>serialVersionUID</code>
		 */
		private static final long serialVersionUID = 1L;

		public void addMockPropertyChangeListener(
				MockPropertyChangeListener listener) {
		}

		public void addMockPropertyChangeListener_Invalid() {
		}

		public void removeMockPropertyChangeListener(
				MockPropertyChangeListener listener) {
		}

		public MockPropertyChangeListener[] getMockPropertyChangeListener(
				MockPropertyChangeListener listeners) {
			return null;
		}

		public void addMockPropertyChangeListener2(
				org.apache.harmony.beans.tests.java.beans.mock.MockPropertyChangeListener2 listener) {
		}

		public void removeMockPropertyChangeListener2(
				org.apache.harmony.beans.tests.java.beans.mock.MockPropertyChangeListener2 listener) {
		}

		public void addMockFakeListener(MockFakeListener listener) {

		}

		public void removeMockFakeListener(MockFakeListener listener) {

		}
	}

}
