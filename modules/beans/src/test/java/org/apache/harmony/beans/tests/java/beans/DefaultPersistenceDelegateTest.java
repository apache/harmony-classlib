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

import java.beans.DefaultPersistenceDelegate;
import java.beans.Encoder;
import java.beans.ExceptionListener;
import java.beans.Expression;
import java.beans.Introspector;
import java.beans.PersistenceDelegate;
import java.beans.PropertyDescriptor;
import java.beans.SimpleBeanInfo;
import java.beans.Statement;

import junit.framework.TestCase;

import org.apache.harmony.beans.tests.support.mock.MockFoo;
import org.apache.harmony.beans.tests.support.mock.MockFoo2;
import org.apache.harmony.beans.tests.support.mock.MockFooStop;

import tests.util.CallVerificationStack;

/**
 * Tests the class java.beans.DefaultPersistenceDelegate
 */
public class DefaultPersistenceDelegateTest extends TestCase {

	/*
	 * @see TestCase#setUp()
	 */
	protected void setUp() throws Exception {
		super.setUp();
		Introspector.flushCaches();
		CallVerificationStack.getInstance().clear();
	}

	/*
	 * Test the default constructor.
	 */
	public void testConstructor_Default() {
		new DefaultPersistenceDelegate();
	}

	/*
	 * Test the constructor with normal property names.
	 */
	public void testConstructor_StringArray_Normal() {
		new DefaultPersistenceDelegate(new String[] { "prop1", "", null });
	}

	/*
	 * Test the constructor with an empty string array.
	 */
	public void testConstructor_StringArray_Empty() {
		new DefaultPersistenceDelegate(new String[0]);
	}

	/*
	 * Test the constructor with null.
	 */
	public void testConstructor_StringArray_Null() {
		new DefaultPersistenceDelegate(null);
	}

	/*
	 * Test instantiate() under normal conditions: two properties, valid getter
	 * method but no setter method and no such a constructor requiring the two
	 * properties.
	 */
	public void testInstantiate_Normal() throws Exception {
		MockPersistenceDelegate pd = new MockPersistenceDelegate(new String[] {
				"prop1", "prop2" });
		MockBean b = new MockBean();
		Encoder enc = new Encoder();
		b.setAll("bean1", 2);
		Expression e = pd.instantiate(b, enc);
		assertSame(b, e.getValue());
		assertSame(MockBean.class, e.getTarget());
		assertEquals("new", e.getMethodName());
		assertSame("bean1", e.getArguments()[0]);
		assertEquals(new Integer(2), e.getArguments()[1]);
	}

	/*
	 * Test instantiate() with null instance.
	 */
	public void testInstantiate_NullInstance() throws Exception {
		MockPersistenceDelegate pd = new MockPersistenceDelegate(new String[] {
				"prop1", "prop2" });
		Encoder enc = new Encoder();
		try {
			pd.instantiate(null, enc);
			fail("Should throw NullPointerException!");
		} catch (NullPointerException ex) {
			// expected
		}
	}

	/*
	 * Test instantiate() with null encoder.
	 */
	public void testInstantiate_NullEncoder() throws Exception {
		MockPersistenceDelegate pd = new MockPersistenceDelegate(new String[] {
				"prop1", "prop2" });
		MockBean b = new MockBean();
		b.setAll("bean1", 2);
		Expression e = pd.instantiate(b, null);
		assertSame(b, e.getValue());
		assertSame(MockBean.class, e.getTarget());
		assertEquals("new", e.getMethodName());
		assertSame("bean1", e.getArguments()[0]);
		assertEquals(new Integer(2), e.getArguments()[1]);
		assertEquals(2, e.getArguments().length);
	}

	/*
	 * Test instantiate() with null property name.
	 */
	public void testInstantiate_NullProperty() throws Exception {
		MockPersistenceDelegate pd = new MockPersistenceDelegate(new String[] {
				"prop1", null });
		MockBean b = new MockBean();
		b.setAll("bean1", 2);
		try {
			pd.instantiate(b, new Encoder());
			fail("Should throw NullPointerException!");
		} catch (NullPointerException ex) {
			// expected
		}
	}

	/*
	 * Test instantiate() with empty property name.
	 */
	public void testInstantiate_EmptyProperty() throws Exception {
		MockPersistenceDelegate pd = new MockPersistenceDelegate(new String[] {
				"prop1", "" });
		MockBean b = new MockBean();
		b.setAll("bean1", 2);
		try {
			pd.instantiate(b, null);
			fail("Should throw NullPointerException!");
		} catch (NullPointerException ex) {
			// expected
		}
	}

	/*
	 * Test instantiate() with no property.
	 */
	public void testInstantiate_NoProperty() throws Exception {
		MockPersistenceDelegate pd = new MockPersistenceDelegate();
		MockBean b = new MockBean();
		b.setAll("bean1", 2);
		Expression e = pd.instantiate(b, new Encoder());
		assertSame(b, e.getValue());
		assertSame(MockBean.class, e.getTarget());
		assertEquals("new", e.getMethodName());
		assertEquals(0, e.getArguments().length);
	}

	/*
	 * Test instantiate() with one normal property name, but non-existing getter
	 * method.
	 */
	public void testInstantiate_NonExistingGetter() throws Exception {
		MockPersistenceDelegate pd = new MockPersistenceDelegate(new String[] {
				"prop1", "non_existing" });
		MockBean b = new MockBean();
		b.setAll("bean1", 2);
		Encoder enc = new Encoder();
		ExceptionListener el = new ExceptionListener() {
			public void exceptionThrown(Exception e) {
				CallVerificationStack.getInstance().push(e);
			}
		};
		enc.setExceptionListener(el);
		Expression e = pd.instantiate(b, enc);
		assertSame(b, e.getValue());
		assertSame(MockBean.class, e.getTarget());
		assertEquals("new", e.getMethodName());
		assertEquals(2, e.getArguments().length);
		assertSame(b.getProp1(), e.getArguments()[0]);
		assertSame(null, e.getArguments()[1]);
		assertTrue(CallVerificationStack.getInstance().pop() instanceof Exception);

		enc.setExceptionListener(null);
		assertNotNull(enc.getExceptionListener());
		e = pd.instantiate(b, enc);
		assertSame(b, e.getValue());
		assertSame(MockBean.class, e.getTarget());
		assertEquals("new", e.getMethodName());
		assertEquals(2, e.getArguments().length);
		assertSame(b.getProp1(), e.getArguments()[0]);
		assertSame(null, e.getArguments()[1]);
	}

	/*
	 * Test instantiate() with one normal property name, but non-existing getter
	 * method, and null encoder.
	 */
	public void testInstantiate_NonExistingGetterNulEncoder() throws Exception {
		MockPersistenceDelegate pd = new MockPersistenceDelegate(new String[] {
				"prop1", "non_existing" });
		MockBean b = new MockBean();
		b.setAll("bean1", 2);
		try {
			pd.instantiate(b, null);
			fail("Should throw NullPointerException!");
		} catch (NullPointerException ex) {
			// expected
		}
	}

	/*
	 * Test instantiate() with one normal property name, but an invalid getter
	 * method (requiring an argument, for instance).
	 */
	public void testInstantiate_InvalidGetter() throws Exception {
		MockPersistenceDelegate pd = new MockPersistenceDelegate(new String[] {
				"prop1", "prop4" });
		MockBean b = new MockBean();
		b.setAll("bean1", 2);
		Expression e = pd.instantiate(b, new Encoder());
		assertSame(b, e.getValue());
		assertSame(MockBean.class, e.getTarget());
		assertEquals("new", e.getMethodName());
		assertEquals(2, e.getArguments().length);
		assertSame(b.getProp1(), e.getArguments()[0]);
		assertSame(null, e.getArguments()[1]);
	}

	/*
	 * Test instantiate() with one normal property name, but a getter method
	 * that will throw an exception.
	 */
	public void testInstantiate_ExceptionalGetter() throws Exception {
		MockPersistenceDelegate pd = new MockPersistenceDelegate(new String[] {
				"prop1", "prop5" });
		MockBean b = new MockBean();
		b.setAll("bean1", 2);
		Expression e = pd.instantiate(b, new Encoder());
		assertSame(b, e.getValue());
		assertSame(MockBean.class, e.getTarget());
		assertEquals("new", e.getMethodName());
		assertEquals(2, e.getArguments().length);
		assertSame(b.getProp1(), e.getArguments()[0]);
		assertSame(null, e.getArguments()[1]);
	}

	/*
	 * Test instantiate() with one normal property name, but a getter method
	 * that will throw an error.
	 */
	public void testInstantiate_ErrorGetter() throws Exception {
		MockPersistenceDelegate pd = new MockPersistenceDelegate(new String[] {
				"prop1", "prop7" });
		MockBean b = new MockBean();
		b.setAll("bean1", 2);
		Expression e = pd.instantiate(b, new Encoder());
		assertSame(b, e.getValue());
		assertSame(MockBean.class, e.getTarget());
		assertEquals("new", e.getMethodName());
		assertEquals(2, e.getArguments().length);
		assertSame(b.getProp1(), e.getArguments()[0]);
		assertSame(null, e.getArguments()[1]);
	}

	/*
	 * Test instantiate() with one normal property name, but a private getter
	 * method.
	 */
	public void testInstantiate_PrivateGetter() throws Exception {
		MockPersistenceDelegate pd = new MockPersistenceDelegate(new String[] {
				"prop1", "prop6" });
		MockBean b = new MockBean();
		b.setAll("bean1", 2);
		Expression e = pd.instantiate(b, new Encoder());
		assertSame(b, e.getValue());
		assertSame(MockBean.class, e.getTarget());
		assertEquals("new", e.getMethodName());
		assertEquals(2, e.getArguments().length);
		assertSame(b.getProp1(), e.getArguments()[0]);
		assertSame(null, e.getArguments()[1]);
	}

	/*
	 * Test instantiate() with a property name starting with initial upper case
	 * letter, and a valid getter method.
	 */
	public void testInstantiate_InitialUpperCasePropName() throws Exception {
		String[] props = new String[] { "Prop1", "prop2" };
		MockPersistenceDelegate pd = new MockPersistenceDelegate(props);
		MockBean b = new MockBean();
		b.setAll("bean1", 2);
		Expression e = pd.instantiate(b, new Encoder());
		assertSame(b, e.getValue());
		assertSame(MockBean.class, e.getTarget());
		assertEquals("new", e.getMethodName());
		assertEquals(2, e.getArguments().length);
		assertSame(b.getProp1(), e.getArguments()[0]);
		assertEquals(new Integer(2), e.getArguments()[1]);
	}

	/*
	 * Test instantiate() with a bean with no getter.
	 */
	public void testInstantiate_NoGetter() throws Exception {
		MockEncoder enc = new MockEncoder();
		MockPersistenceDelegate pd = new MockPersistenceDelegate(
				new String[] { "i" });
		MockNoGetterBean2 b = new MockNoGetterBean2(3);
		enc.setExceptionListener(new ExceptionListener() {
			public void exceptionThrown(Exception e) {
				CallVerificationStack.getInstance().push(e);
			}
		});
		Expression e = pd.instantiate(b, enc);
		assertSame(b, e.getValue());
		assertSame(MockNoGetterBean2.class, e.getTarget());
		assertEquals("new", e.getMethodName());
		assertEquals(1, e.getArguments().length);
		assertSame(null, e.getArguments()[0]);
		assertFalse(CallVerificationStack.getInstance().empty());
	}

	/*
	 * Test instantiate() with a property name that has an unregular getter
	 * method, defined by its beaninfo.
	 */
	public void testInstantiate_NotRegularGetter() throws Exception {
		MockPersistenceDelegate pd = new MockPersistenceDelegate(new String[] {
				"prop1", "i" });
		MockFoo2 b = new MockFoo2(2);
		Expression e = pd.instantiate(b, new Encoder());
		assertSame(b, e.getValue());
		assertSame(MockFoo2.class, e.getTarget());
		assertEquals("new", e.getMethodName());
		assertEquals(2, e.getArguments().length);
		assertEquals(new Integer(2), e.getArguments()[0]);
		assertEquals(null, e.getArguments()[1]);
	}

	/*
	 * Tests mutatesTo() under normal conditions without any properties.
	 */
	public void testMutatesTo_NormalNoProperty() {
		MockPersistenceDelegate pd = new MockPersistenceDelegate();
		assertTrue(pd.mutatesTo("test1", "test2"));
		assertFalse(pd.mutatesTo(new Object(), new Object() {
			public int hashCode() {
				return super.hashCode();
			}
		}));
		assertFalse(pd.mutatesTo(new MockFoo(), new MockFooStop()));
	}

	/*
	 * Tests mutatesTo() under normal conditions with properties but no equal
	 * method defined.
	 */
	public void testMutatesTo_NormalWithPropertyNoEqualMethod() {
		MockPersistenceDelegate pd = new MockPersistenceDelegate(
				new String[] { "name" });
		assertFalse(pd.mutatesTo(new Object(), new Object() {
			public int hashCode() {
				return super.hashCode();
			}
		}));
	}

	/*
	 * Tests mutatesTo() under normal conditions with null properties and equal
	 * method defined.
	 */
	public void testMutatesTo_NormalWithNullPropertyPublicEqualMethod() {
		MockPersistenceDelegate pd = new MockPersistenceDelegate(
				new String[] { null });
		assertFalse(pd.mutatesTo("test1", "test2"));
	}

	/*
	 * Tests mutatesTo() under normal conditions with empty properties and equal
	 * method defined.
	 */
	public void testMutatesTo_NormalWithEmptyPropertyPublicEqualMethod() {
		MockPersistenceDelegate pd = new MockPersistenceDelegate(new String[0]);
		assertTrue(pd.mutatesTo("test1", "test2"));
	}

	/*
	 * Tests mutatesTo() under normal conditions with properties and equal
	 * method defined.
	 */
	public void testMutatesTo_NormalWithPropertyPublicEqualMethod() {
		MockPersistenceDelegate pd = new MockPersistenceDelegate(
				new String[] { "name" });
		assertFalse(pd.mutatesTo("test1", "test2"));
		assertTrue(pd.mutatesTo("test1", "test1"));
	}

	/*
	 * Tests mutatesTo() under normal conditions with properties and protected
	 * equal method defined.
	 */
	public void testMutatesTo_NormalWithPropertyProtectedEqualMethod() {
		MockPersistenceDelegate pd = new MockPersistenceDelegate(
				new String[] { "name" });
		assertFalse(pd.mutatesTo(new MockPersistenceDelegate(), "test"));
	}

	/*
	 * Tests mutatesTo() with null parameters.
	 */
	public void testMutatesTo_Null() {
		MockPersistenceDelegate pd = new MockPersistenceDelegate();
		assertFalse(pd.mutatesTo("test", null));
		assertFalse(pd.mutatesTo(null, null));
		try {
			pd.mutatesTo(null, "test");
			fail("Should throw NullPointerException!");
		} catch (NullPointerException ex) {
			// expected
		}
	}

	/*
	 * Test initialize() under normal conditions with a bean that does not have
	 * bean info class.
	 */
	public void testInitialize_Normal() throws Exception {
		MockEncoder enc = new MockEncoder();
		MockPersistenceDelegate pd = new MockPersistenceDelegate();
		MockFoo b = new MockFoo();
		b.setName("myName");
		// b.setLabel("myLabel");

		enc.writeObject(b);
		CallVerificationStack.getInstance().clear();
		MockFoo b2 = (MockFoo) enc.get(b);
		b2.setName("YourName");

		pd.initialize(MockFoo.class, b, b2, enc);

		// should have called writeStatement()
		Statement stm = (Statement) CallVerificationStack.getInstance().pop();
		assertSame(b, stm.getTarget());
		assertEquals("setName", stm.getMethodName());
		assertEquals(1, stm.getArguments().length);
		assertEquals("myName", stm.getArguments()[0]);

		// should have called get()
		assertEquals("myName", CallVerificationStack.getInstance().pop());

		// should have called writeExpression()
		Expression exp = (Expression) CallVerificationStack.getInstance().pop();
		assertSame(b.getName(), exp.getValue());
		assertSame(b, exp.getTarget());
		assertEquals("getName", exp.getMethodName());
		assertEquals(0, exp.getArguments().length);

		// should have called get()
		assertEquals(null, CallVerificationStack.getInstance().pop());

		// should have called writeExpression()
		exp = (Expression) CallVerificationStack.getInstance().pop();
		assertSame(b.getLabel(), exp.getValue());
		assertSame(b, exp.getTarget());
		assertEquals("getLabel", exp.getMethodName());
		assertEquals(0, exp.getArguments().length);

		assertTrue(CallVerificationStack.getInstance().empty());
	}

	/*
	 * Test initialize() under normal conditions with a bean that does have bean
	 * info class.
	 */
	public void testInitialize_NormalBeanInfo() throws Exception {
		MockEncoder enc = new MockEncoder();
		MockPersistenceDelegate pd = new MockPersistenceDelegate();
		MockFoo2 b = new MockFoo2(2);

		enc.writeObject(b);
		CallVerificationStack.getInstance().clear();
		MockFoo2 b2 = (MockFoo2) enc.get(b);
		b2.myset(3);

		pd.initialize(MockFoo2.class, b, b2, enc);

		// should have called writeStatement()
		Statement stm = (Statement) CallVerificationStack.getInstance().pop();
		assertSame(b, stm.getTarget());
		assertEquals("myset", stm.getMethodName());
		assertEquals(1, stm.getArguments().length);
		assertEquals(new Integer(2), stm.getArguments()[0]);

		// should have called get()
		assertEquals(new Integer(2), CallVerificationStack.getInstance().pop());

		// should have called writeExpression()
		Expression exp = (Expression) CallVerificationStack.getInstance().pop();
		assertEquals(new Integer(2), exp.getValue());
		assertSame(b, exp.getTarget());
		assertEquals("myget", exp.getMethodName());
		assertEquals(0, exp.getArguments().length);

		assertTrue(CallVerificationStack.getInstance().empty());
	}

	/*
	 * Test initialize() when oldInstance == newInstance.
	 */
	public void testInitialize_SameInstance() throws Exception {
		MockEncoder enc = new MockEncoder();
		MockPersistenceDelegate pd = new MockPersistenceDelegate();
		MockFoo b = new MockFoo();
		b.setName("mymyName");
		// b.setLabel("myLabel");

		pd.initialize(MockFoo.class, b, b, enc);

		// should have called get()
		assertEquals("mymyName", CallVerificationStack.getInstance().pop());

		// should have called writeExpression()
		Expression exp = (Expression) CallVerificationStack.getInstance().pop();
		assertSame(b.getName(), exp.getValue());
		assertSame(b, exp.getTarget());
		assertEquals("getName", exp.getMethodName());
		assertEquals(0, exp.getArguments().length);

		// should have called get()
		assertEquals(null, CallVerificationStack.getInstance().pop());

		// should have called writeExpression()
		exp = (Expression) CallVerificationStack.getInstance().pop();
		assertSame(b.getLabel(), exp.getValue());
		assertSame(b, exp.getTarget());
		assertEquals("getLabel", exp.getMethodName());
		assertEquals(0, exp.getArguments().length);

		assertTrue(CallVerificationStack.getInstance().empty());
	}

	/*
	 * Test initialize() with a bean with a transient property.
	 */
	public void testInitialize_TransientProperty() throws Exception {
		MockEncoder enc = new MockEncoder();
		MockPersistenceDelegate pd = new MockPersistenceDelegate();
		MockTransientBean b = new MockTransientBean();
		b.setName("myName");
		pd.initialize(MockTransientBean.class, b, new MockTransientBean(), enc);
		assertTrue(CallVerificationStack.getInstance().empty());
		// set transient to false
		Introspector.flushCaches();
		MockTransientBeanBeanInfo.setTransient(false);
		pd.initialize(MockTransientBean.class, new MockTransientBean(),
				new MockTransientBean(), enc);
		assertFalse(CallVerificationStack.getInstance().empty());
	}

	/*
	 * Test initialize() with a bean with no setter.
	 */
	public void testInitialize_NoSetter() throws Exception {
		MockEncoder enc = new MockEncoder();
		MockPersistenceDelegate pd = new MockPersistenceDelegate();
		MockNoSetterBean b = new MockNoSetterBean();
		enc.setExceptionListener(new ExceptionListener() {
			public void exceptionThrown(Exception e) {
				CallVerificationStack.getInstance().push(e);
			}
		});
		b.setName("myName");
		pd.initialize(MockNoSetterBean.class, b, new MockNoSetterBean(), enc);
		assertTrue(CallVerificationStack.getInstance().empty());

		pd = new MockPersistenceDelegate(new String[] { "name" });
		enc.setExceptionListener(new ExceptionListener() {
			public void exceptionThrown(Exception e) {
				CallVerificationStack.getInstance().push(e);
			}
		});
		b.setName("myName");
		pd.initialize(MockNoSetterBean.class, b, new MockNoSetterBean(), enc);
		assertTrue(CallVerificationStack.getInstance().empty());
	}

	/*
	 * Test initialize() with a bean with no getter.
	 */
	public void testInitialize_NoGetter() throws Exception {
		MockEncoder enc = new MockEncoder();
		MockPersistenceDelegate pd = new MockPersistenceDelegate();
		MockNoGetterBean b = new MockNoGetterBean();

		b.setName("myName");
		enc.setExceptionListener(new ExceptionListener() {
			public void exceptionThrown(Exception e) {
				CallVerificationStack.getInstance().push(e);
			}
		});
		enc.writeObject(b);
		CallVerificationStack.getInstance().clear();
		MockNoGetterBean b2 = (MockNoGetterBean) enc.get(b);
		b2.setName("yourName");
		b2.setLabel("hehe");
		pd.initialize(MockNoGetterBean.class, b, b2, enc);
		assertTrue(CallVerificationStack.getInstance().empty());
	}

	/*
	 * Tests initialize() with null class.
	 */
	public void testInitialize_NullClass() {
		MockPersistenceDelegate pd = new MockPersistenceDelegate();
		Encoder enc = new Encoder();
		Object o1 = new Object();
		Object o2 = new Object();
		// enc.setPersistenceDelegate(MockFooStop.class,
		// new MockPersistenceDelegate());
		try {
			enc.setExceptionListener(new ExceptionListener() {
				public void exceptionThrown(Exception e) {
					CallVerificationStack.getInstance().push(e);
				}
			});
			pd.initialize(null, o1, o2, enc);
			fail("Should throw NullPointerException!");
		} catch (NullPointerException ex) {
			// expected
		}
		assertTrue(CallVerificationStack.getInstance().empty());
	}

	/*
	 * Tests initialize() with null old and new instances.
	 */
	public void testInitialize_NullInstances() {
		MockPersistenceDelegate pd = new MockPersistenceDelegate();
		Encoder enc = new Encoder();
		MockFoo b = new MockFoo();
		b.setName("myName");
		// enc.setPersistenceDelegate(MockFooStop.class,
		// new MockPersistenceDelegate());
		enc.setExceptionListener(new ExceptionListener() {
			public void exceptionThrown(Exception e) {
				CallVerificationStack.getInstance().push(e);
			}
		});
		try {
			pd.initialize(MockFoo.class, null, b, enc);
			fail("Should throw NullPointerException!");
		} catch (NullPointerException ex) {
			// expected
		}
		assertTrue(CallVerificationStack.getInstance().empty());
		pd.initialize(MockFoo.class, b, null, enc);
		assertFalse(CallVerificationStack.getInstance().empty());
	}

	/*
	 * Tests initialize() with null encoder.
	 */
	public void testInitialize_NullEncoder() {
		MockPersistenceDelegate pd = new MockPersistenceDelegate();
		Object o1 = new Object();
		Object o2 = new Object();
		try {
			pd.initialize(MockFoo.class, o1, o2, null);
			fail("Should throw NullPointerException!");
		} catch (NullPointerException ex) {
			// expected
		}
	}

	/*
	 * Test initialize() with a property name that has an unregular getter
	 * method, defined by its beaninfo.
	 */
	public void testInitialize_NotRegularGetter() throws Exception {
		MockPersistenceDelegate pd = new MockPersistenceDelegate();
		// new String[] { "prop1" });
		MockEncoder enc = new MockEncoder();

		MockFoo2 b = new MockFoo2(2);
		MockFoo2 b2 = new MockFoo2(1);
		pd.initialize(MockFoo2.class, b, b2, enc);

		// should have called writeStatement()
		Statement stm = (Statement) CallVerificationStack.getInstance().pop();
		assertSame(b, stm.getTarget());
		assertEquals("myset", stm.getMethodName());
		assertEquals(1, stm.getArguments().length);
		assertEquals(new Integer(2), stm.getArguments()[0]);

		// should have called get()
		assertEquals(new Integer(2), CallVerificationStack.getInstance().pop());

		// should have called writeExpression()
		Expression exp = (Expression) CallVerificationStack.getInstance().pop();
		assertEquals(new Integer(2), exp.getValue());
		assertSame(b, exp.getTarget());
		assertEquals("myget", exp.getMethodName());
		assertEquals(0, exp.getArguments().length);
	}

	/*
	 * Tests array persistence delegate
	 */
	public void testArrayPD_Normal() {
		Encoder enc = new MockEncoder();
		int[] ia = new int[] { 1 };
		PersistenceDelegate pd = enc.getPersistenceDelegate(ia.getClass());
		pd.writeObject(ia, enc);
	}

	/*
	 * BeanInfo for the MockBean below.
	 */
	public static class MockNoGetterBeanBeanInfo extends SimpleBeanInfo {

		public PropertyDescriptor[] getPropertyDescriptors() {
			try {
				PropertyDescriptor pd = new PropertyDescriptor("name", null,
						MockNoGetterBean.class.getMethod("setName",
								new Class[] { String.class }));
				PropertyDescriptor pd2 = new PropertyDescriptor("ii", null,
						MockNoGetterBean.class.getMethod("setII",
								new Class[] { Integer.class }));
				return new PropertyDescriptor[] { pd, pd2 };
			} catch (Exception e) {
				throw new Error(e);
			}
		}
	}

	/*
	 * Mock bean with no getter.
	 */
	public static class MockNoGetterBean extends MockFoo {
		public void setII(Integer i) {
		}
	}

	/*
	 * Mock bean with no getter.
	 */
	public static class MockNoGetterBean2 {
		public MockNoGetterBean2(int i) {
		}

		public void setI(int i) {
		}
	}

	/*
	 * BeanInfo for the MockBean below.
	 */
	public static class MockNoSetterBeanBeanInfo extends SimpleBeanInfo {

		public PropertyDescriptor[] getPropertyDescriptors() {
			try {
				PropertyDescriptor pd = new PropertyDescriptor("name",
						MockNoSetterBean.class.getMethod("getName", (Class[])null), null);
				return new PropertyDescriptor[] { pd };
			} catch (Exception e) {
				throw new Error(e);
			}
		}
	}

	/*
	 * Mock bean with no setter.
	 */
	public static class MockNoSetterBean {
		private String name;

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}
	}

	/*
	 * BeanInfo for the MockBean below.
	 */
	public static class MockTransientBeanBeanInfo extends SimpleBeanInfo {

		private static boolean trans = true;

		public static void setTransient(boolean b) {
			trans = b;
		}

		public PropertyDescriptor[] getPropertyDescriptors() {
			try {
				PropertyDescriptor pd = new PropertyDescriptor("name",
						MockTransientBean.class);
				pd.setValue("transient", new Boolean(trans));
				return new PropertyDescriptor[] { pd };
			} catch (Exception e) {
				throw new Error(e);
			}
		}
	}

	/*
	 * Mock bean with a transient property.
	 */
	public static class MockTransientBean extends MockFoo {
	}

	/*
	 * Mock bean.
	 */
	public static class MockBean {
		private transient String prop1;

		private transient int prop2;

		public String getProp1() {
			return this.prop1;
		}

		public int getProp2() {
			return this.prop2;
		}

		public int getProp3() {
			return this.prop2;
		}

		public void setProp3() {
			// empty
		}

		public int getProp4(int i) {
			return this.prop2;
		}

		public int getProp5() throws Exception {
			throw new Exception();
		}

		public int getProp7() {
			throw new Error();
		}

		public void setAll(String prop1, int prop2) {
			this.prop1 = prop1;
			this.prop2 = prop2;
		}

		public int get() {
			return this.prop2;
		}

	}

	/*
	 * Mock DefaultPersistenceDelegate subclass.
	 */
	static class MockPersistenceDelegate extends DefaultPersistenceDelegate {

		public MockPersistenceDelegate() {
			super();
		}

		public MockPersistenceDelegate(String[] props) {
			super(props);
		}

		public Expression instantiate(Object oldInstance, Encoder out) {
			return super.instantiate(oldInstance, out);
		}

		public void initialize(Class<?> type, Object oldInstance,
				Object newInstance, Encoder enc) {
			super.initialize(type, oldInstance, newInstance, enc);
		}

		public boolean mutatesTo(Object oldInstance, Object newInstance) {
			return super.mutatesTo(oldInstance, newInstance);
		}

		protected boolean equals(String o) {
			return true;
		}

	}

	/*
	 * Mock Encoder.
	 */
	static class MockEncoder extends Encoder {

		public ExceptionListener getExceptionListener() {
			return super.getExceptionListener();
		}

		public PersistenceDelegate getPersistenceDelegate(
                  Class<?> type) {
			return super.getPersistenceDelegate(type);
		}

		public void setExceptionListener(ExceptionListener exceptionListener) {
			super.setExceptionListener(exceptionListener);
		}

		public void setPersistenceDelegate(Class<?> type,
				PersistenceDelegate persistenceDelegate) {
			super.setPersistenceDelegate(type, persistenceDelegate);
		}

		private void recordCall(Object param) {
			StackTraceElement[] eles = (new Throwable()).getStackTrace();
			int i = 0;
			// skip Throwable.init()
			while (eles[i].getClassName().equals("java.lang.Throwable")) {
				i++;
			}
			// skip calls from MockEncoder
			while (eles[i].getClassName().equals(MockEncoder.class.getName())) {
				i++;
			}
			// skip calls from DefaultPersistenceDelegate & PersistenceDelegate
			while (eles[i].getClassName().equals(
					DefaultPersistenceDelegate.class.getName())
					|| eles[i].getClassName().equals(
							PersistenceDelegate.class.getName())) {
				i++;
			}
			if (i > 2
					&& eles[++i].getClassName().equals(
							DefaultPersistenceDelegateTest.class.getName())) {
				CallVerificationStack.getInstance().push(param);
			}
		}

		public Object get(Object oldInstance) {
			recordCall(oldInstance);
			return super.get(oldInstance);
		}

		public Object remove(Object oldInstance) {
			recordCall(oldInstance);
			return super.remove(oldInstance);
		}

		public void writeExpression(Expression oldExp) {
			recordCall(oldExp);
			super.writeExpression(oldExp);
		}

		public void writeStatement(Statement oldStm) {
			recordCall(oldStm);
			super.writeStatement(oldStm);
		}

		public void writeObject(Object o) {
			recordCall(o);
			super.writeObject(o);
		}
	}

}
