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
import java.beans.Expression;
import java.util.Arrays;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import junit.textui.TestRunner;

import org.apache.harmony.beans.tests.java.beans.auxiliary.SampleBean;

/**
 * Test the class java.beans.Expression.
 */
public class ExpressionTest extends TestCase {

    private static int testId = -1;

    /**
     * 
     */
    public ExpressionTest() {
        super();
    }
    
    /**
     *
     */
    public ExpressionTest(String name) {
        super(name);
    }
    
    /**
     * The test checks the correct constructor is initialized
     */
    public void testConstructor() {
        Expression expr = new Expression(SampleBean.class, "new",
                new Object[] { "hello" });
        try {
            Object result = expr.getValue();
            if(result != null && result instanceof SampleBean) {
                SampleBean bean = (SampleBean) result;
                assertEquals("hello", bean.getText());
            } else {
                fail("Cannot instantiate an instance of Bean class.");
            }
        } catch (Exception e) {
            System.out.println(e.getClass() + ": " + e.getMessage());
            fail("Exception is thrown while trying to invoke new Bean(String)");
        }
    }
    
    /**
     * The test checks the correct static method is initialized
     */
    public void testStatic() {
        SampleBean theBean = new SampleBean();
        Expression expr = new Expression(SampleBean.class, "create",
                new Object[] { "hello", theBean });
        try {
            Object result = expr.getValue();
            if(result != null && result instanceof SampleBean) {
                SampleBean bean = (SampleBean) result;
                assertEquals("hello", bean.getText());
                assertEquals(theBean, bean.getObject());
            } else {
                fail("Cannot instantiate an instance of Bean class by "
                        + "static method.");
            }
        } catch (Exception e) {
            System.out.println(e.getClass() + ": " + e.getMessage());
            fail("Exception is thrown while trying to invoke "
                    + "SampleBean.create(String, SampleBean)");
        }
    }
    
    /**
     * The test checks the correct getter is initialized
     */
    public void testGetter() {
        Expression expr = new Expression(new SampleBean("hello"), "getText",
                new Object[] {});
        try {
            Object result = expr.getValue();
            if(result != null && result instanceof String) {
                assertEquals("hello", result);
            } else {
                fail("Result of SampleBean.getText() call is not "
                        + "of String type.");
            }
        } catch (Exception e) {
            System.out.println(e.getClass() + ": " + e.getMessage());
            fail("Exception is thrown while trying to invoke "
                    + "SampleBean.getText()");
        }
    }
    
    /**
     * The test checks the correct array getter is initialized
     */
    public void testArrayGetter() {
        int[] a = {1, 2, 3};
        Expression expr = new Expression(a, "get",
                new Object[] { new Integer(1) });
        try {
            Object result = expr.getValue();
            if(result != null && result instanceof Integer) {
                assertEquals(new Integer(2), result);
            } else {
                fail("Result of array getter is not of Integer type.");
            }
        } catch (Exception e) {
            System.out.println(e.getClass() + ": " + e.getMessage());
            fail("Exception is thrown while trying to invoke get element "
                    + "from array");
        }
    }
    
    /**
     * 
     */
    public static Test suite() {
        return new TestSuite(ExpressionTest.class);
    }
    
    /**
     * 
     */
    public static void main(String[] args) {
        TestRunner.run(suite());
    }
    
	/*
	 * Test the constructor under normal conditions.
	 */
	public void testConstructor_Normal() {
		Object arg1 = new Object();
		Object arg2 = "string";
		Object[] oa = new Object[] { arg1, arg2 };
		Expression t = new Expression(arg1, "method", oa);
		assertSame(arg1, t.getTarget());
		assertSame("method", t.getMethodName());
		assertSame(oa, t.getArguments());
		assertSame(arg1, t.getArguments()[0]);
		assertSame(arg2, t.getArguments()[1]);
		Pattern p = Pattern
				.compile(".*=Object[0-9]+\\.method\\(Object[0-9]+, \"string\"\\);");
		Matcher m = p.matcher(t.toString());
		assertTrue(m.matches());
	}

	/*
	 * Test the constructor with null target.
	 */
	public void testConstructor_NullTarget() {
		Object arg = new Object();
		Object[] oa = new Object[] { arg };
		Expression t = new Expression(null, "method", oa);
		assertSame(null, t.getTarget());
		assertSame("method", t.getMethodName());
		assertSame(oa, t.getArguments());
		assertSame(arg, t.getArguments()[0]);

		Pattern p = Pattern.compile(".*=null\\.method\\(Object[0-9]+\\);");
		Matcher m = p.matcher(t.toString());
		assertTrue(m.matches());
	}

	/*
	 * Test the constructor with an array target.
	 */
	public void testConstructor_ArrayTarget() {
		Object arg = new Object();
		Object[] oa = new Object[] { arg };
		Expression t = new Expression(oa, "method", oa);
		assertSame(oa, t.getTarget());
		assertSame("method", t.getMethodName());
		assertSame(oa, t.getArguments());
		assertSame(arg, t.getArguments()[0]);

		Pattern p = Pattern
				.compile(".*=ObjectArray[0-9]+\\.method\\(Object[0-9]+\\);");
		Matcher m = p.matcher(t.toString());
		assertTrue(m.matches());
	}

	/*
	 * Test the constructor with null method name.
	 */
	public void testConstructor_NullMethodName() {
		Object target = new Object();
		Object[] oa = new Object[] { new Object() };
		Expression t = new Expression(target, null, oa);
		assertSame(target, t.getTarget());
		assertSame(null, t.getMethodName());
		assertSame(oa, t.getArguments());

		Pattern p = Pattern
				.compile(".*=Object[0-9]+\\.null\\(Object[0-9]+\\);");
		Matcher m = p.matcher(t.toString());
		assertTrue(m.matches());
	}

	/*
	 * Test the constructor with the method name "new".
	 */
	public void testConstructor_NewMethodName() {
		Object target = new Object();
		Object[] oa = new Object[] { new Object() };
		Expression t = new Expression(target, "new", oa);
		assertSame(target, t.getTarget());
		assertSame("new", t.getMethodName());
		assertSame(oa, t.getArguments());

		Pattern p = Pattern.compile(".*=Object[0-9]+\\.new\\(Object[0-9]+\\);");
		Matcher m = p.matcher(t.toString());
		assertTrue(m.matches());
	}

	/*
	 * Test the constructor with empty method name.
	 */
	public void testConstructor_EmptyMethodName() {
		Object target = new Object();
		Object[] oa = new Object[] { new Object() };
		Expression t = new Expression(target, "", oa);
		assertSame(target, t.getTarget());
		assertSame("", t.getMethodName());
		assertSame(oa, t.getArguments());

		Pattern p = Pattern.compile(".*=Object[0-9]+\\.\\(Object[0-9]+\\);");
		Matcher m = p.matcher(t.toString());
		assertTrue(m.matches());
	}

	/*
	 * Test the constructor with null arguments.
	 */
	public void testConstructor_NullArguments() {
		Object target = new Object();
		Expression t = new Expression(target, "method", null);
		assertSame(target, t.getTarget());
		assertSame("method", t.getMethodName());
		assertEquals(0, t.getArguments().length);

		Pattern p = Pattern.compile(".*=Object[0-9]+\\.method\\(\\);");
		Matcher m = p.matcher(t.toString());
		assertTrue(m.matches());
	}

	/*
	 * Test the constructor with a null argument.
	 */
	public void testConstructor_NullArgument() {
		Object target = new Object();
		Object[] oa = new Object[] { null };
		Expression t = new Expression(target, "method", oa);
		assertSame(target, t.getTarget());
		assertSame("method", t.getMethodName());
		assertSame(oa, t.getArguments());
		assertNull(t.getArguments()[0]);

		Pattern p = Pattern.compile(".*=Object[0-9]+\\.method\\(null\\);");
		Matcher m = p.matcher(t.toString());
		assertTrue(m.matches());
	}

	/*
	 * Test the constructor(value, ...) under normal conditions.
	 */
	public void testConstructor_Value_Normal() throws Exception {
		Object val = new Object();
		Object arg1 = new Object();
		Object arg2 = new Object();
		Object[] oa = new Object[] { arg1, arg2 };
		Expression t = new Expression(val, arg1, "method", oa);
		assertSame(val, t.getValue());
		assertSame(arg1, t.getTarget());
		assertSame("method", t.getMethodName());
		assertSame(oa, t.getArguments());
		assertSame(arg1, t.getArguments()[0]);
		assertSame(arg2, t.getArguments()[1]);
		Pattern p = Pattern
				.compile("Object[0-9]+=Object[0-9]+\\.method\\(Object[0-9]+, Object[0-9]+\\);");
		Matcher m = p.matcher(t.toString());
		assertTrue(m.matches());
	}

	/*
	 * Test the constructor(value, ...) with null target.
	 */
	public void testConstructor_Value_NullTarget() throws Exception {
		Object val = new Object();
		Object arg = new Object();
		Object[] oa = new Object[] { arg };
		Expression t = new Expression(val, null, "method", oa);
		assertSame(val, t.getValue());
		assertSame(null, t.getTarget());
		assertSame("method", t.getMethodName());
		assertSame(oa, t.getArguments());
		assertSame(arg, t.getArguments()[0]);

		Pattern p = Pattern
				.compile("Object[0-9]+=null\\.method\\(Object[0-9]+\\);");
		Matcher m = p.matcher(t.toString());
		assertTrue(m.matches());
	}

	/*
	 * Test the constructor(value, ...) with an array target.
	 */
	public void testConstructor_Value_ArrayTarget() throws Exception {
		Object val = new Object();
		Object arg = new Object();
		Object[] oa = new Object[] { arg };
		Expression t = new Expression(val, oa, "method", oa);
		assertSame(val, t.getValue());
		assertSame(oa, t.getTarget());
		assertSame("method", t.getMethodName());
		assertSame(oa, t.getArguments());
		assertSame(arg, t.getArguments()[0]);

		Pattern p = Pattern
				.compile("Object[0-9]+=ObjectArray[0-9]+\\.method\\(Object[0-9]+\\);");
		Matcher m = p.matcher(t.toString());
		assertTrue(m.matches());
	}

	/*
	 * Test the constructor(value, ...) with null method name.
	 */
	public void testConstructor_Value_NullMethodName() throws Exception {
		Object val = new Object();
		Object target = new Object();
		Object[] oa = new Object[] { new Object() };
		Expression t = new Expression(val, target, null, oa);
		assertSame(val, t.getValue());
		assertSame(target, t.getTarget());
		assertSame(null, t.getMethodName());
		assertSame(oa, t.getArguments());

		Pattern p = Pattern
				.compile("Object[0-9]+=Object[0-9]+\\.null\\(Object[0-9]+\\);");
		Matcher m = p.matcher(t.toString());
		assertTrue(m.matches());
	}

	/*
	 * Test the constructor(value, ...) with the method name "new".
	 */
	public void testConstructor_Value_NewMethodName() throws Exception {
		Object val = new Object();
		Object target = new Object();
		Object[] oa = new Object[] { new Object() };
		Expression t = new Expression(val, target, "new", oa);
		assertSame(val, t.getValue());
		assertSame(target, t.getTarget());
		assertSame("new", t.getMethodName());
		assertSame(oa, t.getArguments());

		Pattern p = Pattern
				.compile("Object[0-9]+=Object[0-9]+\\.new\\(Object[0-9]+\\);");
		Matcher m = p.matcher(t.toString());
		assertTrue(m.matches());
	}

	/*
	 * Test the constructor(value, ...) with empty method name.
	 */
	public void testConstructor_Value_EmptyMethodName() throws Exception {
		Object val = new Object();
		Object target = new Object();
		Object[] oa = new Object[] { new Object() };
		Expression t = new Expression(val, target, "", oa);
		assertSame(val, t.getValue());
		assertSame(target, t.getTarget());
		assertSame("", t.getMethodName());
		assertSame(oa, t.getArguments());

		Pattern p = Pattern
				.compile("Object[0-9]+=Object[0-9]+\\.\\(Object[0-9]+\\);");
		Matcher m = p.matcher(t.toString());
		assertTrue(m.matches());
	}

	/*
	 * Test the constructor(value, ...) with null arguments.
	 */
	public void testConstructor_Value_NullArguments() throws Exception {
		Object val = new Object();
		Object target = new Object();
		Expression t = new Expression(val, target, "method", null);
		assertSame(val, t.getValue());
		assertSame(target, t.getTarget());
		assertSame("method", t.getMethodName());
		assertEquals(0, t.getArguments().length);

		Pattern p = Pattern
				.compile("Object[0-9]+=Object[0-9]+\\.method\\(\\);");
		Matcher m = p.matcher(t.toString());
		assertTrue(m.matches());
	}

	/*
	 * Test the constructor(value, ...) with a null argument.
	 */
	public void testConstructor_Value_NullArgument() throws Exception {
		Object val = new Object();
		Object target = new Object();
		Object[] oa = new Object[] { null };
		Expression t = new Expression(val, target, "method", oa);
		assertSame(val, t.getValue());
		assertSame(target, t.getTarget());
		assertSame("method", t.getMethodName());
		assertSame(oa, t.getArguments());
		assertNull(t.getArguments()[0]);

		Pattern p = Pattern
				.compile("Object[0-9]+=Object[0-9]+\\.method\\(null\\);");
		Matcher m = p.matcher(t.toString());
		assertTrue(m.matches());
	}

	/*
	 * Test the constructor(value, ...) with a null value.
	 */
	public void testConstructor_Value_NullValue() throws Exception {
		Object target = new Object();
		Object[] oa = new Object[] { null };
		Expression t = new Expression(null, target, "method", oa);
		assertSame(null, t.getValue());
		assertSame(target, t.getTarget());
		assertSame("method", t.getMethodName());
		assertSame(oa, t.getArguments());
		assertNull(t.getArguments()[0]);

		Pattern p = Pattern.compile("null=Object[0-9]+\\.method\\(null\\);");
		Matcher m = p.matcher(t.toString());
		assertTrue(m.matches());
	}

	/*
	 * Test the setValue() method with a non-null value when the value of the
	 * expression is still unbounded.
	 */
	public void testSetValue_UnboundNormal() throws Exception {
		MockObject mo = new MockObject(false);
		Expression t = new Expression(mo, "method", new Object[0]);
		t.setValue(mo);
		assertSame(mo, t.getValue());
		MockObject.assertNotCalled();
	}

	/*
	 * Test the setValue() method with a null value when the value of the
	 * expression is still unbounded.
	 */
	public void testSetValue_UnboundNull() throws Exception {
		MockObject mo = new MockObject(false);
		Expression t = new Expression(mo, "method", new Object[0]);
		t.setValue(null);
		assertSame(null, t.getValue());
		MockObject.assertNotCalled();
	}

	/*
	 * Test the setValue() method when the value of the expression is set by the
	 * constructor.
	 */
	public void testSetValue_Constructor() throws Exception {
		MockObject mo = new MockObject(false);
		Expression t = new Expression(mo, mo, "method", new Object[0]);
		assertSame(mo, t.getValue());
		MockObject.assertNotCalled();
		t.setValue(null);
		assertSame(null, t.getValue());
		MockObject.assertNotCalled();
	}

	/*
	 * Test the setValue() method when the value of the expression is set by a
	 * previous call to setValue().
	 */
	public void testSetValue_Set() throws Exception {
		MockObject mo = new MockObject(false);
		Expression t = new Expression(mo, "method", new Object[0]);
		t.setValue(mo);
		assertSame(mo, t.getValue());
		MockObject.assertNotCalled();
		t.setValue(null);
		assertSame(null, t.getValue());
		MockObject.assertNotCalled();
	}

	/*
	 * Test the setValue() method when the value of the expression is set by a
	 * previous call to getValue().
	 */
	public void testSetValue_Evaluated() throws Exception {
		MockObject mo = new MockObject(false);
		Expression t = new Expression(mo, "method", new Object[0]);
		assertEquals("method1", t.getValue());
		MockObject.assertCalled("method1", new Object[0]);
		t.setValue(mo);
		assertSame(mo, t.getValue());
		MockObject.assertNotCalled();
	}

	/*
	 * Test the getValue() method when the value of the expression is evaluated
	 * by a previous call to getValue().
	 */
	public void testGetValue_Evaluated() throws Exception {
		MockObject mo = new MockObject(false);
		Expression t = new Expression(mo, "method", new Object[0]);
		assertEquals("method1", t.getValue());
		MockObject.assertCalled("method1", new Object[0]);
		assertEquals("method1", t.getValue());
		MockObject.assertNotCalled();
	}

	/*
	 * Test the getValue() method when the value of expression is set by the
	 * constructor.
	 */
	public void testGetValue_Constructor() throws Exception {
		MockObject mo = new MockObject(false);
		Expression t = new Expression(mo, mo, "method", new Object[0]);
		assertSame(mo, t.getValue());
		MockObject.assertNotCalled();
	}

	/*
	 * Test the getValue() method when the value of expression is set by a
	 * previous call to setValue().
	 */
	public void testGetValue_Set() throws Exception {
		MockObject mo = new MockObject(false);
		Expression t = new Expression(mo, "method", new Object[0]);
		t.setValue(mo);
		assertSame(mo, t.getValue());
		MockObject.assertNotCalled();
	}

	/*
	 * Test the method getValue() with a normal object, a valid method name and
	 * valid arguments.
	 */
	public void testGetValue_UnboundedNormalInstanceMethod() throws Exception {
		MockObject mo = new MockObject(false);
		Expression t = new Expression(mo, "method", new Object[0]);
		assertEquals("method1", t.getValue());
		MockObject.assertCalled("method1", new Object[0]);
		t = new Expression(mo, "method", null);
		assertEquals("method1", t.getValue());
		MockObject.assertCalled("method1", new Object[0]);
	}

	/*
	 * Test the method getValue() with a normal object, a valid method that
	 * throws an exception and valid arguments.
	 */
	public void testGetValue_UnboundedExceptionalMethod() throws Exception {
		MockObject mo = new MockObject(false);
		Expression t = new Expression(mo, "method", new Object[] { null, null });
		try {
			t.getValue();
			fail("Should throw NullPointerException!");
		} catch (NullPointerException ex) {
			// expected
		}
		MockObject.assertCalled("method4", new Object[] { null, null });
	}

	/*
	 * Test the method getValue() with a normal object and a non-existing method
	 * name.
	 */
	public void testGetValue_UnboundedNonExistingMethod() throws Exception {
		MockObject mo = new MockObject(false);
		Expression t = new Expression(mo, "method_not_existing", new Object[] {
				null, null });
		try {
			t.getValue();
			fail("Should throw NoSuchMethodException!");
		} catch (NoSuchMethodException ex) {
			// expected
		}
	}

	/*
	 * Test the method getValue() with a null object.
	 */
	public void testGetValue_UnboundedNullTarget() throws Exception {
		Expression t = new Expression(null, "method_not_existing",
				new Object[] { null, null });
		try {
			t.getValue();
			fail("Should throw NullPointerException!");
		} catch (NullPointerException ex) {
			// expected
		}
	}

	/*
	 * Test the method getValue() with a null method name.
	 */
	public void testGetValue_UnboundedNullMethodName() throws Exception {
		MockObject mo = new MockObject(false);
		Expression t = new Expression(mo, null, new Object[] { null, null });
		try {
			t.getValue();
			fail("Should throw NoSuchMethodException!");
		} catch (NoSuchMethodException ex) {
			// expected
		}
	}

	/*
	 * Test the method getValue() with a normal object, a valid method and
	 * invalid arguments (in terms of type, numbers, etc.).
	 */
	public void testGetValue_UnboundedInvalidArguments() throws Exception {
		MockObject mo = new MockObject(false);
		Expression t = new Expression(mo, "method", new Object[] {
				new Object(), new Object(), new Object() });
		try {
			t.getValue();
			fail("Should throw NoSuchMethodException!");
		} catch (NoSuchMethodException ex) {
			// expected
		}
	}

	/*
	 * Test the method getValue() with a normal object, an overloaded method and
	 * valid arguments. 
	 */
	public void testGetValue_UnboundedOverloadedMethods() throws Exception {
		MockObject mo = new MockObject(false);
		Object[] arguments = new Object[] { new Object() };
		Expression t = new Expression(mo, "method", arguments);
		assertEquals("method2", t.getValue());
		MockObject.assertCalled("method2", arguments);

		arguments = new Object[] { "test" };
		t = new Expression(mo, "method", arguments);
		assertEquals("method3", t.getValue());
		MockObject.assertCalled("method3", arguments);
	}

	/*
	 * Test the method getValue() with a normal object, an overloaded method and
	 * null arguments. 
	 * 
	 * Note: decided by definition position.
	 */
	public void testGetValue_UnboundedOverloadedMethodsNull() throws Exception {
		MockObject mo = new MockObject(false);
		Object[] arguments = new Object[] { null };
		Expression t = new Expression(mo, "method", arguments);
		assertEquals("method1-2", t.getValue());
		MockObject.assertCalled("method1-2", arguments);
	}

	/*
	 * Test the method getValue() with a normal object, the method name "new"
	 * and valid arguments.
	 */
	public void testGetValue_UnboundedNormalConstructor() throws Exception {
		Expression t = new Expression(MockObject.class, "new", new Object[0]);
		t.getValue();
		MockObject.assertCalled("new0", new Object[0]);
		t = new Expression(MockObject.class, "new", null);
		assertTrue(t.getValue() instanceof MockObject);
		MockObject.assertCalled("new0", new Object[0]);
	}

	/*
	 * Test the method getValue() with a normal object, the method name "new"
	 * that throws an exception and valid arguments.
	 */
	public void testGetValue_UnboundedExceptionalConstructor() throws Exception {
		Expression t = new Expression(MockObject.class, "new", new Object[] {
				null, null });
		try {
			t.getValue();
			fail("Should throw NullPointerException!");
		} catch (NullPointerException ex) {
			// expected
		}
		MockObject.assertCalled("new4", new Object[] { null, null });
	}

	/*
	 * Test the method getValue() with a normal object, the method name "new"
	 * and invalid arguments (in terms of type, numbers, etc.).
	 */
	public void testGetValue_UnboundedNonExistingConstructor() throws Exception {
		Expression t = new Expression(MockObject.class, "new", new Object[] {
				null, null, null });
		try {
			t.getValue();
			fail("Should throw NoSuchMethodException!");
		} catch (NoSuchMethodException ex) {
			// expected
		}
	}

	/*
	 * Test the method getValue() with a normal object with overloaded
	 * constructors, the method name "new" and valid arguments. See Java
	 * Language Specification (15.11) for reference.
	 * 
	 * Note: decided by definition position.
	 */
	public void testGetValue_UnboundedOverloadedConstructors() throws Exception {
		Object[] arguments = new Object[] { new Object() };
		Expression t = new Expression(MockObject.class, "new", arguments);
		t.getValue();
		MockObject.assertCalled("new2", arguments);

		arguments = new Object[] { "test" };
		t = new Expression(MockObject.class, "new", arguments);
		assertTrue(t.getValue() instanceof MockObject);
		// MockObject.assertCalled("new2", arguments);

		arguments = new Object[] { new Integer(1) };
		t = new Expression(MockObject.class, "new", arguments);
		assertTrue(t.getValue() instanceof MockObject);
		MockObject.assertCalled("new1-2", arguments);
	}

	/*
	 * Test the method getValue() with a normal object with overloaded
	 * constructors, the method name "new" and null arguments. See Java Language
	 * Specification (15.11) for reference.
	 */
	public void testGetValue_UnboundedOverloadedConstructorsNull()
			throws Exception {
		Object[] arguments = new Object[] { null };
		Expression t = new Expression(MockObject.class, "new", arguments);
		try {
			t.getValue(); 
			fail("Should throw NullPointerException!");
		} catch (NullPointerException ex) {
			// expected
		}
	}

	/*
	 * Test the method getValue() with the Class object, a static method name
	 * and valid arguments.
	 */
	public void testGetValue_UnboundedNormalStaticMethodViaClass()
			throws Exception {
		Object[] arguments = new Object[] { new Object() };
		Expression t = new Expression(MockObject.class, "staticMethod",
				arguments);
		assertEquals("staticMethod", t.getValue());
		MockObject.assertCalled("staticMethod", arguments);
	}

	/*
	 * Test the method getValue() with an object, a static method name and valid
	 * arguments.
	 */
	public void testGetValue_UnboundedNormalStaticMethodViaObject()
			throws Exception {
		MockObject mo = new MockObject(false);
		Object[] arguments = new Object[] { new Object() };
		Expression t = new Expression(mo, "staticMethod", arguments);
		assertEquals("staticMethod", t.getValue());
		MockObject.assertCalled("staticMethod", arguments);
	}

	/*
	 * Test the method getValue() with a Class object of a normal class that has
	 * a method of the same signature as Class.forName(String), a static method
	 * name "forName" and valid argument "string".
	 */
	public void testGetValue_UnboundedAmbitiousStaticMethod() throws Exception {
		Object[] arguments = new Object[] { "test" };
		Expression t = new Expression(MockObject.class, "forName", arguments);
		assertEquals(null, t.getValue());
		MockObject.assertCalled("forName", arguments);

		t = new Expression(String.class, "forName",
				new Object[] { "java.lang.String" });
		assertSame(String.class, t.getValue());
	}

	/*
	 * Test the method getValue() with the special method Class.forName().
	 */
	public void testGetValue_UnboundedClassForName() throws Exception {
		Object[] arguments = new String[] { this.getClass().getName() };
		Expression t = new Expression(Class.class, "forName", arguments);
		assertSame(this.getClass(), t.getValue());

		// t = new Expression(String.class, "forName", arguments);
		// assertSame(this.getClass(), t.getValue());
	}

	/*
	 * Test the method getValue() with a normal array object, the method name
	 * "get" and valid arguments.
	 */
	public void testGetValue_UnboundedArrayGet() throws Exception {
		Object[] array = new Object[] { "test" };
		Expression t = new Expression(array, "get", new Object[] { new Integer(
				0) });
		assertEquals("test", t.getValue());
	}

	/*
	 * Test the method getValue() with a normal array object, the method name
	 * "getInt" and invalid arguments.
	 */
	public void testGetValue_UnboundedArrayInvalidSetInt() throws Exception {
		int[] array = new int[] { 1 };
		Expression t = new Expression(array, "getInt",
				new Object[] { new Integer(0) });
		try {
			t.getValue();
			fail("Should throw NoSuchMethodException!");
		} catch (NoSuchMethodException ex) {
			// expected
		}
	}

	/*
	 * Test the method getValue() with a normal array object, the method name
	 * "gets".
	 */
	public void testGetValue_UnboundedArrayInvalidName() throws Exception {
		Object[] array = new Object[] { "test" };
		Expression t = new Expression(array, "gets", new Object[] {
				new Integer(0), new Object() });
		try {
			t.getValue();
			fail("Should throw NoSuchMethodException!");
		} catch (NoSuchMethodException ex) {
			// expected
		}
	}

	/*
	 * Test the method getValue() with a normal object with overloaded methods
	 * (primitive type VS wrapper class), a valid method name and valid
	 * arguments.
	 * 
	 * Note: decided by definition position!
	 */
	public void testGetValue_UnboundedPrimitiveVSWrapper() throws Exception {
		MockObject mo = new MockObject(false);
		Object[] arguments = new Object[] { new Integer(1) };
		Expression t = new Expression(mo, "methodB", arguments);
		assertEquals("methodB1", t.getValue());
		MockObject.assertCalled("methodB1", arguments);

		arguments = new Object[] { Boolean.FALSE };
		t = new Expression(mo, "methodB", arguments);
		assertEquals("methodB3", t.getValue());
		MockObject.assertCalled("methodB3", arguments);
	}

	/*
	 * Test the method getValue() with a normal object with void method name and
	 * valid arguments.
	 */
	public void testGetValue_UnboundedVoidMethod() throws Exception {
		MockObject mo = new MockObject(false);
		Object[] arguments = new Object[] { new Integer(1) };
		Expression t = new Expression(mo, "voidMethod", arguments);
		assertNull(t.getValue());
		MockObject.assertCalled("voidMethod2", arguments);
	}

	/*
	 * Test the method getValue() with a protected method but within java.beans
	 * package.
	 */
	public void testGetValue_ProtectedMethodWithPackage() throws Exception {
		DefaultPersistenceDelegate dpd = new DefaultPersistenceDelegate();
		Object[] arguments = new Object[] { "test", "test" };
		Expression t = new Expression(dpd, "mutatesTo", arguments);
		try {
			t.getValue();
			fail("Should throw NoSuchMethodException!");
		} catch (NoSuchMethodException e) {
			// expected
		}
	}

	/*
	 * Test the method getValue() with a method that is applicable via type
	 * conversion.
	 */
	public void testGetValue_ApplicableViaTypeConversion() throws Exception {
		MockObject mo = new MockObject(false);
		// mo.methodB('c');
		Object[] arguments = new Object[] { new Character((char) 1) };
		Expression t = new Expression(mo, "methodB", arguments);
		try {
			t.getValue();
			fail("Should throw NoSuchMethodException!");
		} catch (NoSuchMethodException e) {
			// expected
		}
	}

	/*
	 * Test that setValue() is called in the constructor.
	 */
	public void testConstructor_Value_SetValueCalledIn() {
		Object o = new Object();
		MockExpression exp = new MockExpression(o);
		exp.assertCalled(o);
	}

	/*
	 * Test the method getValue() with two equal specific methods.
	 * 
	 * Note: decided by definition position! should be ambiguous.
	 */
	public void testGetValue_EqualSpecificMethods() throws Exception {
		MockObject mo = new MockObject(false);
		Object[] arguments = new Object[] { new MockObject(false),
				new MockObject(false) };
		Expression t = new Expression(mo, "equalSpecificMethod", arguments);
		assertEquals("equalSpecificMethod1", t.getValue());
		MockObject.assertCalled("equalSpecificMethod1", arguments);
	}

	/*
	 * Test the method getValue() with two equal specific methods but one
	 * declaring thrown exception.
	 * 
	 * Note: decided by definition position! should call the one with
	 * exception.
	 */
	public void testGetValue_EqualSpecificMethodsException() throws Exception {
		MockObject mo = new MockObject(false);
		Object[] arguments = new Object[] { new MockObject(false),
				new MockObject(false), new Object() };
		Expression t = new Expression(mo, "equalSpecificMethod", arguments);
		assertEquals("equalSpecificMethod3", t.getValue());
		MockObject.assertCalled("equalSpecificMethod3", arguments);
	}

	/*
	 * Super class of MockObject.
	 */
	public static class MockParent {

		protected static String calledMethod = null;

		protected static Vector receivedArguments = new Vector();

		public Object method() {
			reset();
			calledMethod = "method1";
			return calledMethod;
		}

		protected Object method(Boolean o) {
			reset();
			calledMethod = "method1-1";
			receivedArguments.add(o);
			return calledMethod;
		}

		public Object method(Integer o) {
			reset();
			calledMethod = "method1-2";
			receivedArguments.add(o);
			return calledMethod;
		}

		public Object method(Object o) {
			reset();
			calledMethod = "method2";
			receivedArguments.add(o);
			return calledMethod;
		}

		public Object method(String o) {
			reset();
			calledMethod = "method3";
			receivedArguments.add(o);
			return calledMethod;
		}

		public Object method(Object o, Object o2) {
			reset();
			calledMethod = "method4";
			receivedArguments.add(o);
			receivedArguments.add(o2);
			throw new NullPointerException();
		}

		public static void reset() {
			receivedArguments.clear();
			calledMethod = null;
		}
	}

	/*
	 * Mock object.
	 */
	public static class MockObject extends MockParent {

		public MockObject() {
			reset();
			calledMethod = "new0";
		}

		public MockObject(boolean testingConstructor) {
			reset();
			if (testingConstructor) {
				calledMethod = "new1";
			}
		}

		public MockObject(Integer o) {
			reset();
			calledMethod = "new1-2";
			receivedArguments.add(o);
		}

		public MockObject(Object o) {
			reset();
			calledMethod = "new2";
			receivedArguments.add(o);
		}

		public MockObject(String o) {
			reset();
			calledMethod = "new3";
			receivedArguments.add(o);
		}

		public MockObject(Object o, Object o2) {
			reset();
			calledMethod = "new4";
			receivedArguments.add(o);
			receivedArguments.add(o2);
			throw new NullPointerException();
		}

		public Object methodB(Integer i) {
			reset();
			calledMethod = "methodB1";
			receivedArguments.add(i);
			return calledMethod;
		}

		public Object methodB(int i) {
			reset();
			calledMethod = "methodB2";
			receivedArguments.add(new Integer(i));
			return calledMethod;
		}

		public Object methodB(boolean b) {
			reset();
			calledMethod = "methodB3";
			receivedArguments.add(new Boolean(b));
			return calledMethod;
		}

		// public Object methodB(Boolean b) {
		// reset();
		// calledMethod = "methodB4";
		// receivedArguments.add(b);
		// return calledMethod;
		// }

		public Object voidMethod(Object o) {
			reset();
			calledMethod = "voidMethod";
			receivedArguments.add(o);
			return "voidMethod";
		}

		public void voidMethod(Integer o) {
			reset();
			calledMethod = "voidMethod2";
			receivedArguments.add(o);
		}

		public static Object staticMethod(Object o) {
			reset();
			calledMethod = "staticMethod";
			receivedArguments.add(o);
			return calledMethod;
		}

		public Object equalSpecificMethod(MockObject o, MockParent p) {
			reset();
			calledMethod = "equalSpecificMethod1";
			receivedArguments.add(o);
			receivedArguments.add(p);
			return calledMethod;
		}

		public Object equalSpecificMethod(MockParent p, MockObject o) {
			reset();
			calledMethod = "equalSpecificMethod2";
			receivedArguments.add(p);
			receivedArguments.add(o);
			return calledMethod;
		}

		public Object equalSpecificMethod(MockObject o, MockParent p, Object o2) {
			reset();
			calledMethod = "equalSpecificMethod3";
			receivedArguments.add(o);
			receivedArguments.add(p);
			receivedArguments.add(o2);
			return calledMethod;
		}

		public Object equalSpecificMethod(MockParent p, MockObject o, Object o2)
				throws Exception {
			reset();
			calledMethod = "equalSpecificMethod4";
			receivedArguments.add(p);
			receivedArguments.add(o);
			receivedArguments.add(o2);
			return calledMethod;
		}

		public static Class forName(String o) {
			reset();
			calledMethod = "forName";
			receivedArguments.add(o);
			return null;
		}

		public static void assertCalled(String methodName, Object[] arguments) {
			assertEquals(methodName, calledMethod);
			assertTrue(Arrays.equals(arguments, receivedArguments.toArray()));
			reset();
		}

		public static void assertNotCalled() {
			assertEquals(null, calledMethod);
			assertTrue(receivedArguments.isEmpty());
		}
	}

	/*
	 * Mock expression to test that setValue() is called in the constructor.
	 */
	public static class MockExpression extends Expression {

		private Object acceptedValue;

		public MockExpression(Object o) {
			super(o, null, null, null);
		}

		public void setValue(Object o) {
			acceptedValue = o;
		}

		public void assertCalled(Object o) {
			assertSame(o, acceptedValue);
		}
	}
}
