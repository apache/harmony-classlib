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

import java.beans.Statement;
import java.beans.DefaultPersistenceDelegate;
import java.util.Arrays;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import junit.textui.TestRunner;

import org.apache.harmony.beans.tests.java.beans.auxiliary.SampleException;

/**
 * Test the class java.beans.Statement.
 */
public class StatementTest extends TestCase {

    private static int testId = -1;

    /**
     * 
     */
    public StatementTest() {
        super();
    }
    
    /**
     *
     */
    public StatementTest(String name) {
        super(name);
    }
    
    /**
     * The test checks the method execute() for setter
     */
    public void testSetter() {
        Bean bean = new Bean();
        Statement s = new Statement(bean, "setText", new Object[] { "hello" });
        try {
            s.execute();
            assertEquals("hello", bean.getText());
        } catch (Exception e) {
            System.out.println(e.getClass() + ": " + e.getMessage());
            fail("Exception is thrown while trying to invoke Bean.getText()");
        }
    }
    
    /**
     * The test checks the method execute() for indexed setter
     */
    public void testIndexedSetter() {
        Bean bean = new Bean("hello");
        Statement s = new Statement(bean, "setChar",
                new Object[] { new Integer(1), new Character('a') });
        try {
            s.execute();
            assertEquals("hallo", bean.getText());
        } catch (Exception e) {
            System.out.println(e.getClass() + ": " + e.getMessage());
            fail("Exception is thrown while trying to invoke Bean.getText()");
        }
    }
    
    /**
     * The test checks the method execute() for array setter
     */
    public void testArraySetter() {
        int[] a = {1, 2, 3};
        Statement s = new Statement(a, "set",
                new Object[] { new Integer(1), new Integer(7) });
        try {
            s.execute();
            assertEquals(7, a[1]);
        } catch (Exception e) {
            System.out.println(e.getClass() + ": " + e.getMessage());
            fail("Exception is thrown while trying to invoke array[i] =");
        }
    }
    
    /**
     * The test checks the method execute() for static method
     */
    public void testStatic() {
        int currentId = getTestId();
        Statement s = new Statement(StatementTest.class, "nextTestId",
                new Object[] { });
        try {
            s.execute();
            assertEquals(++currentId, getTestId());
        } catch (Exception e) {
            System.out.println(e.getClass() + ": " + e.getMessage());
            fail("Exception is thrown while trying to invoke "
                    + "StatementTest.nextTestId()");
        }
    }
    
    /**
     * The test checks the method execute() if exception is thrown on method call
     */
    public void testExceptionThrownOnMethodCall() {
        Bean bean = new Bean("hello");
        Statement s = new Statement(bean, "setChar",
                new Object[] { new Integer(5), new Character('a') });
        try {
            s.execute();
            assertFalse("Exception must be thrown while Bean.setChar(5, 'a') "
                    + "invocation.", true);
        } catch (Exception e) {
            assertTrue(true);
        }
    }
    
    /**
     * The test checks the method execute() if exception is thrown on
     * static method call
     */
    public void testExceptionThrownOnStaticMethodCall() {
        Statement s = new Statement(StatementTest.class, "methodWithException",
                new Object[] {} );
        try {
            s.execute();
            assertFalse(
                    "Exception must be thrown with methodWithException call",
                    true);
        } catch (SampleException se) {
            assertTrue("SampleException is thrown as expected", true);
        } catch (Exception e) {
            assertTrue("Non expected exception: " + e.getClass(), false);
        }
    }
    
    /**
     * The test checks the method execute() with array as parameter 
     */
    public void testMethodWithArrayParam() {
        Statement s = new Statement(StatementTest.class, "methodWithIntArray",
            new Object[] { new int[] { 3 } } );
        try {
            s.execute();
            assertTrue("No exception is thrown for methodWithIntArray call",
                    true);
        } catch (Exception e) {
            assertTrue("Non expected exception: " + e.getClass(), false);
        }
    }
    
    /**
     * 
     */
    public static int getTestId() {
        return testId;
    }
    
    /**
     * 
     */
    public static void nextTestId() {
        ++testId;
    }
    
    /**
     * 
     */
    public static void methodWithException() throws Exception {
        throw new SampleException("sample");
    }
    
    /**
     * 
     */
    public static void methodWithIntArray(int[] array) {
    }

    /**
     * 
     */
    public static Test suite() {
        return new TestSuite(StatementTest.class);
    }
    
    /**
     * 
     */
    public static void main(String[] args) {
        TestRunner.run(suite());
    }
    
    public class Bean {
        
        private String text;
        
        public Bean() {
            text = null;
        }
        
        public Bean(String text) {
            this.text = text;
        }
        
        public String getText() {
            return text;
        }
        
        public void setText(String text) {
            this.text = text;
        }
        
        public char getChar(int idx) throws IllegalAccessException {
            if(text == null) {
                throw new IllegalAccessException("Text property is null.");
            } else {
                return text.charAt(idx);
            }
        }
        
        public void setChar(int idx, char value) throws IllegalAccessException {
            if(text == null) {
                throw new IllegalAccessException("Text property is null.");
            } else {
                // IndexOutOfBounds exception is thrown, if indexed bounds are violated
                char oldValue = text.charAt(idx);
                
                String newText = "";
                
                if(idx > 0) {
                    newText += text.substring(0, idx);
                }
                
                newText += value;
                
                if(idx < (text.length() - 1)) {
                    newText += text.substring(idx + 1); 
                }
                
                text = newText;
                
            }
        }
    }
    
    
    
	/*
	 * Test the constructor under normal conditions.
	 */
	public void testConstructor_Normal() {
		Object arg1 = new Object();
		Object arg2 = "string";
		Object[] oa = new Object[] { arg1, arg2 };
		Statement t = new Statement(arg1, "method", oa);
		assertSame(arg1, t.getTarget());
		assertSame("method", t.getMethodName());
		assertSame(oa, t.getArguments());
		assertSame(arg1, t.getArguments()[0]);
		assertSame(arg2, t.getArguments()[1]);

		Pattern p = Pattern
				.compile("Object[0-9]+\\.method\\(Object[0-9]+, \"string\"\\);");
		Matcher m = p.matcher(t.toString());
		assertTrue(m.matches());
	}

	/*
	 * Test the constructor with null target.
	 */
	public void testConstructor_NullTarget() {
		Object arg = new Object();
		Object[] oa = new Object[] { arg };
		Statement t = new Statement(null, "method", oa);
		assertSame(null, t.getTarget());
		assertSame("method", t.getMethodName());
		assertSame(oa, t.getArguments());
		assertSame(arg, t.getArguments()[0]);

		Pattern p = Pattern.compile("null\\.method\\(Object[0-9]+\\);");
		Matcher m = p.matcher(t.toString());
		assertTrue(m.matches());
	}

	/*
	 * Test the constructor with an array target.
	 */
	public void testConstructor_ArrayTarget() {
		Object arg = new Object();
		Object[] oa = new Object[] { arg };
		Statement t = new Statement(oa, "method", oa);
		assertSame(oa, t.getTarget());
		assertSame("method", t.getMethodName());
		assertSame(oa, t.getArguments());
		assertSame(arg, t.getArguments()[0]);

		Pattern p = Pattern
				.compile("ObjectArray[0-9]+\\.method\\(Object[0-9]+\\);");
		Matcher m = p.matcher(t.toString());
		assertTrue(m.matches());
	}

	/*
	 * Test the constructor with null method name.
	 */
	public void testConstructor_NullMethodName() {
		Object target = new Object();
		Object[] oa = new Object[] { new Object() };
		Statement t = new Statement(target, null, oa);
		assertSame(target, t.getTarget());
		assertSame(null, t.getMethodName());
		assertSame(oa, t.getArguments());

		Pattern p = Pattern.compile("Object[0-9]+\\.null\\(Object[0-9]+\\);");
		Matcher m = p.matcher(t.toString());
		assertTrue(m.matches());
	}

	/*
	 * Test the constructor with the method name "new".
	 */
	public void testConstructor_NewMethodName() {
		Object target = new Object();
		Object[] oa = new Object[] { new Object() };
		Statement t = new Statement(target, "new", oa);
		assertSame(target, t.getTarget());
		assertSame("new", t.getMethodName());
		assertSame(oa, t.getArguments());

		Pattern p = Pattern.compile("Object[0-9]+\\.new\\(Object[0-9]+\\);");
		Matcher m = p.matcher(t.toString());
		assertTrue(m.matches());
	}

	/*
	 * Test the constructor with empty method name.
	 */
	public void testConstructor_EmptyMethodName() {
		Object target = new Object();
		Object[] oa = new Object[] { new Object() };
		Statement t = new Statement(target, "", oa);
		assertSame(target, t.getTarget());
		assertSame("", t.getMethodName());
		assertSame(oa, t.getArguments());

		Pattern p = Pattern.compile("Object[0-9]+\\.\\(Object[0-9]+\\);");
		Matcher m = p.matcher(t.toString());
		assertTrue(m.matches());
	}

	/*
	 * Test the constructor with null arguments.
	 */
	public void testConstructor_NullArguments() {
		Object target = new Object();
		Statement t = new Statement(target, "method", null);
		assertSame(target, t.getTarget());
		assertSame("method", t.getMethodName());
		assertEquals(0, t.getArguments().length);

		Pattern p = Pattern.compile("Object[0-9]+\\.method\\(\\);");
		Matcher m = p.matcher(t.toString());
		assertTrue(m.matches());
	}

	/*
	 * Test the constructor with a null argument.
	 */
	public void testConstructor_NullArgument() {
		Object target = new Object();
		Object[] oa = new Object[] { null };
		Statement t = new Statement(target, "method", oa);
		assertSame(target, t.getTarget());
		assertSame("method", t.getMethodName());
		assertSame(oa, t.getArguments());
		assertNull(t.getArguments()[0]);

		Pattern p = Pattern.compile("Object[0-9]+\\.method\\(null\\);");
		Matcher m = p.matcher(t.toString());
		assertTrue(m.matches());
	}

	public void testGetArguments() {
		// Covered in the testcases for the constructor
	}

	public void testGetMethodName() {
		// Covered in the testcases for the constructor
	}

	public void testGetTarget() {
		// Covered in the testcases for the constructor
	}

	public void testToString() {
		// Covered in the testcases for the constructor
	}

	/*
	 * Test the method execute() with a normal object, a valid method name and
	 * valid arguments.
	 */
	public void testExecute_NormalInstanceMethod() throws Exception {
		MockObject mo = new MockObject(false);
		Statement t = new Statement(mo, "method", new Object[0]);
		t.execute();
		MockObject.assertCalled("method1", new Object[0]);
		t = new Statement(mo, "method", null);
		t.execute();
		MockObject.assertCalled("method1", new Object[0]);
	}

	/*
	 * Test the method execute() with a normal object, a valid method that
	 * throws an exception and valid arguments.
	 */
	public void testExecute_ExceptionalMethod() throws Exception {
		MockObject mo = new MockObject(false);
		Statement t = new Statement(mo, "method", new Object[] { null, null });
		try {
			t.execute();
			fail("Should throw NullPointerException!");
		} catch (NullPointerException ex) {
			// expected
		}
		MockObject.assertCalled("method4", new Object[] { null, null });
	}

	/*
	 * Test the method execute() with a normal object and a non-existing method
	 * name.
	 */
	public void testExecute_NonExistingMethod() throws Exception {
		MockObject mo = new MockObject(false);
		Statement t = new Statement(mo, "method_not_existing", new Object[] {
				null, null });
		try {
			t.execute();
			fail("Should throw NoSuchMethodException!");
		} catch (NoSuchMethodException ex) {
			// expected
		}
	}

	/*
	 * Test the method execute() with a null object.
	 */
	public void testExecute_NullTarget() throws Exception {
		Statement t = new Statement(null, "method_not_existing", new Object[] {
				null, null });
		try {
			t.execute();
			fail("Should throw NullPointerException!");
		} catch (NullPointerException ex) {
			// expected
		}
	}

	/*
	 * Test the method execute() with a null method name.
	 */
	public void testExecute_NullMethodName() throws Exception {
		MockObject mo = new MockObject(false);
		Statement t = new Statement(mo, null, new Object[] { null, null });
		try {
			t.execute();
			fail("Should throw NoSuchMethodException!");
		} catch (NoSuchMethodException ex) {
			// expected
		}
	}

	/*
	 * Test the method execute() with a normal object, a valid method and
	 * invalid arguments (in terms of type, numbers, etc.).
	 */
	public void testExecute_InvalidArguments() throws Exception {
		MockObject mo = new MockObject(false);
		Statement t = new Statement(mo, "method", new Object[] { new Object(),
				new Object(), new Object() });
		try {
			t.execute();
			fail("Should throw NoSuchMethodException!");
		} catch (NoSuchMethodException ex) {
			// expected
		}
	}

	/*
	 * Test the method execute() with a normal object, an overloaded method and
	 * valid arguments. 
	 */
	public void testExecute_OverloadedMethods() throws Exception {
		MockObject mo = new MockObject(false);
		Object[] arguments = new Object[] { new Object() };
		Statement t = new Statement(mo, "method", arguments);
		t.execute();
		MockObject.assertCalled("method2", arguments);

		arguments = new Object[] { "test" };
		t = new Statement(mo, "method", arguments);
		t.execute();
		MockObject.assertCalled("method3", arguments);
	}

	/*
	 * Test the method execute() with a normal object, an overloaded method and
	 * null arguments. See Java Language Specification (15.11) for reference.
	 */
	public void testExecute_OverloadedMethodsNull() throws Exception {
		MockObject mo = new MockObject(false);
		Object[] arguments = new Object[] { null };
		Statement t = new Statement(mo, "method", arguments);
		t.execute();
		MockObject.assertCalled("method1-2", arguments);
	}

	/*
	 * Test the method execute() with a normal object, the method name "new" and
	 * valid arguments.
	 */
	public void testExecute_NormalConstructor() throws Exception {
		Statement t = new Statement(MockObject.class, "new", new Object[0]);
		t.execute();
		MockObject.assertCalled("new0", new Object[0]);
		t = new Statement(MockObject.class, "new", null);
		t.execute();
		MockObject.assertCalled("new0", new Object[0]);
	}

	/*
	 * Test the method execute() with a normal object, the method name "new"
	 * that throws an exception and valid arguments.
	 */
	public void testExecute_ExceptionalConstructor() throws Exception {
		Statement t = new Statement(MockObject.class, "new", new Object[] {
				null, null });
		try {
			t.execute();
			fail("Should throw NullPointerException!");
		} catch (NullPointerException ex) {
			// expected
		}
		MockObject.assertCalled("new4", new Object[] { null, null });
	}

	/*
	 * Test the method execute() with a normal object, the method name "new" and
	 * invalid arguments (in terms of type, numbers, etc.).
	 */
	public void testExecute_NonExistingConstructor() throws Exception {
		Statement t = new Statement(MockObject.class, "new", new Object[] {
				null, null, null });
		try {
			t.execute();
			fail("Should throw NoSuchMethodException!");
		} catch (NoSuchMethodException ex) {
			// expected
		}
	}

	/*
	 * Test the method execute() with a normal object with overloaded
	 * constructors, the method name "new" and valid arguments. 
	 */
	public void testExecute_OverloadedConstructors() throws Exception {
		Object[] arguments = new Object[] { new Object() };
		Statement t = new Statement(MockObject.class, "new", arguments);
		t.execute();
		MockObject.assertCalled("new2", arguments);

		arguments = new Object[] { "test" };
		t = new Statement(MockObject.class, "new", arguments);
		t.execute();
		// MockObject.assertCalled("new2", arguments);

		arguments = new Object[] { new Integer(1) };
		t = new Statement(MockObject.class, "new", arguments);
		t.execute();
		MockObject.assertCalled("new1-2", arguments);
	}

	/*
	 * Test the method execute() with a normal object with overloaded
	 * constructors, the method name "new" and null arguments.
	 */
	public void testExecute_OverloadedConstructorsNull() throws Exception {
		Object[] arguments = new Object[] { null };
		Statement t = new Statement(MockObject.class, "new", arguments);
		try {
			t.execute();
			fail("Should throw NullPointerException!");
		} catch (NullPointerException ex) {
			// expected
		}
		// MockObject.assertCalled("new2", arguments);
	}

	/*
	 * Test the method execute() with the Class object, a static method name and
	 * valid arguments.
	 */
	public void testExecute_NormalStaticMethodViaClass() throws Exception {
		Object[] arguments = new Object[] { new Object() };
		Statement t = new Statement(MockObject.class, "staticMethod", arguments);
		t.execute();
		MockObject.assertCalled("staticMethod", arguments);
	}

	/*
	 * Test the method execute() with an object, a static method name and valid
	 * arguments.
	 */
	public void testExecute_NormalStaticMethodViaObject() throws Exception {
		MockObject mo = new MockObject(false);
		Object[] arguments = new Object[] { new Object() };
		Statement t = new Statement(mo, "staticMethod", arguments);
		t.execute();
		MockObject.assertCalled("staticMethod", arguments);
	}

	/*
	 * Test the method execute() with a Class object of a normal class that has
	 * a method of the same signature as Class.forName(String), a static method
	 * name "forName" and valid argument "string".
	 */
	public void testExecute_AmbitiousStaticMethod() throws Exception {
		Object[] arguments = new String[] { "test" };
		Statement t = new Statement(MockObject.class, "forName", arguments);
		t.execute();
		MockObject.assertCalled("forName", arguments);

		t = new Statement(String.class, "forName",
				new Object[] { "java.lang.String" });
		t.execute();
	}

	/*
	 * Test the method execute() with the special method Class.forName().
	 */
	public void testExecute_ClassForName() throws Exception {
		Object[] arguments = new String[] { this.getClass().getName() };
		Statement t = new Statement(Class.class, "forName", arguments);
		t.execute();

		t = new Statement(String.class, "forName",
				new Object[] { "java.lang.String" });
		t.execute();
	}

	/*
	 * Test the method execute() with a normal array object, the method name
	 * "get" and valid and invalid arguments.
	 */
	public void testExecute_ArrayGet() throws Exception {
		Object[] array = new Object[] { "test" };
		Statement t = new Statement(array, "get", new Object[] {
				new Integer(0), new Object() });
		t.execute();

		array = new Object[] { "test" };
		t = new Statement(array, "get", new Object[0]);
		try {
			t.execute();
			fail("Should throw ArrayIndexOutOfBoundsException!");
		} catch (ArrayIndexOutOfBoundsException ex) {
			// expected
		}
	}

	/*
	 * Test the method execute() with a normal array object, the method name
	 * "set" and valid arguments.
	 */
	public void testExecute_ArraySet() throws Exception {
		Object[] array = new Object[] { "test" };
		Statement t = new Statement(array, "set", new Object[] {
				new Integer(0), "test2" });
		t.execute();
		assertEquals("test2", array[0]);
	}

	/*
	 * Test the method execute() with a normal array object, the method name
	 * "set" and null index argument.
	 */
	public void testExecute_ArrayNullIndex() throws Exception {
		Object[] array = new Object[] { "test" };
		Statement t = new Statement(array, "set",
				new Object[] { null, "test2" });
		try {
			t.execute();
			fail("Should throw NullPointerException!");
		} catch (NullPointerException ex) {
			// expected
		}
	}

	/*
	 * Test the method execute() with a normal array object, the method name
	 * "set" and invalid arguments.
	 */
	public void testExecute_ArrayInvalidSet() throws Exception {
		Object[] array = new Object[] { "test" };
		Statement t = new Statement(array, "set", new Object[] {
				new Integer(0), "test2", new Object() });
		t.execute();
		assertEquals("test2", array[0]);

		try {
			t = new Statement(array, "set", new Object[] { "testtest", "test2",
					new Object() });
			t.execute();
			fail("Should throw ClassCastException!");
		} catch (ClassCastException ex) {
			// expected
		}

		t = new Statement(array, "set", new Object[] { new Integer(0) });
		try {
			t.execute();
			fail("Should throw ArrayIndexOutOfBoundsException!");
		} catch (ArrayIndexOutOfBoundsException ex) {
			// expected
		}
	}

	/*
	 * Test the method execute() with a normal array object, the method name
	 * "getInt" and invalid arguments.
	 */
	public void testExecute_ArrayInvalidSetInt() throws Exception {
		int[] array = new int[] { 1 };
		Statement t = new Statement(array, "getInt",
				new Object[] { new Integer(0) });
		try {
			t.execute();
			fail("Should throw NoSuchMethodException!");
		} catch (NoSuchMethodException ex) {
			// expected
		}
	}

	/*
	 * Test the method execute() with a normal array object, the method name
	 * "gets".
	 */
	public void testExecute_ArrayInvalidName() throws Exception {
		Object[] array = new Object[] { "test" };
		Statement t = new Statement(array, "gets", new Object[] {
				new Integer(0), new Object() });
		try {
			t.execute();
			fail("Should throw NoSuchMethodException!");
		} catch (NoSuchMethodException ex) {
			// expected
		}
	}

	/*
	 * Test the method execute() with a normal object with overloaded methods
	 * (primitive type VS wrapper class), a valid method name and valid
	 * arguments.
	 * 
	 * Note: decided by definition position!
	 */
	public void testExecute_PrimitiveVSWrapper() throws Exception {
		MockObject mo = new MockObject(false);
		Object[] arguments = new Object[] { new Integer(1) };
		Statement t = new Statement(mo, "methodB", arguments);
		t.execute();
		MockObject.assertCalled("methodB1", arguments);

		arguments = new Object[] { Boolean.FALSE };
		t = new Statement(mo, "methodB", arguments);
		t.execute();
		MockObject.assertCalled("methodB4", arguments);
	}

	/*
	 * Test the method execute() with a protected method but within java.beans
	 * package.
	 */
	public void testExecute_ProtectedMethodWithPackage() throws Exception {
		DefaultPersistenceDelegate dpd = new DefaultPersistenceDelegate();
		Object[] arguments = new Object[] { "test", "test" };
		Statement t = new Statement(dpd, "mutatesTo", arguments);
		try {
			t.execute();
			fail("Should throw NoSuchMethodException!");
		} catch (NoSuchMethodException e) {
			// expected
		}
	}

	/*
	 * Test the method execute() with a method that is applicable via type
	 * conversion.
	 */
	public void testExecute_ApplicableViaTypeConversion() throws Exception {
		MockObject mo = new MockObject(false);
		// mo.methodB('c');
		Object[] arguments = new Object[] { new Character((char) 1) };
		Statement t = new Statement(mo, "methodB", arguments);
		try {
			t.execute();
			fail("Should throw NoSuchMethodException!");
		} catch (NoSuchMethodException e) {
			// expected
		}
	}

	/*
	 * Test the method execute() with two equal specific methods.
	 * 
	 * Note: decided by definition position!
	 */
	public void testExecute_EqualSpecificMethods() throws Exception {
		MockObject mo = new MockObject(false);
		Object[] arguments = new Object[] { new MockObject(false),
				new MockObject(false) };
		Statement t = new Statement(mo, "equalSpecificMethod", arguments);
		t.execute();
		MockObject.assertCalled("equalSpecificMethod1", arguments);
	}

	/*
	 * Test the method execute() with two equal specific methods but one
	 * declaring thrown exception.
	 * 
	 * Note: decided by definition position!
	 */
	public void testExecute_EqualSpecificMethodsException() throws Exception {
		MockObject mo = new MockObject(false);
		Object[] arguments = new Object[] { new MockObject(false),
				new MockObject(false), new Object() };
		Statement t = new Statement(mo, "equalSpecificMethod", arguments);
		t.execute();
		MockObject.assertCalled("equalSpecificMethod4", arguments);
	}

	/*
	 * Test the method execute() with int method while providing a null
	 * parameter.
	 */
	public void testExecute_IntMethodNullParameter() throws Exception {
		MockObject mo = new MockObject(false);
		Object[] arguments = new Object[] { null };
		Statement t = new Statement(mo, "intMethod", arguments);
		try {
			t.execute();
			fail("Should throw NullPointerException!");
		} catch (NullPointerException ex) {
			// expected
		}
	}

	/*
	 * Test the method execute() with int array method while providing an
	 * Integer array parameter.
	 */
	public void testExecute_IntArrayMethod() throws Exception {
		MockObject mo = new MockObject(false);
		Object[] arguments = new Object[] { new Integer[] { new Integer(1) } };
		Statement t = new Statement(mo, "intArrayMethod", arguments);
		try {
			t.execute();
			fail("Should throw NoSuchMethodException!");
		} catch (NoSuchMethodException ex) {
			// expected
		}
	}

	/*
	 * Test the method execute() with Integer array method while providing an
	 * int array parameter.
	 */
	public void testExecute_IntegerArrayMethod() throws Exception {
		MockObject mo = new MockObject(false);
		Object[] arguments = new Object[] { new int[] { 1 } };
		Statement t = new Statement(mo, "integerArrayMethod", arguments);
		try {
			t.execute();
			fail("Should throw NoSuchMethodException!");
		} catch (NoSuchMethodException ex) {
			// expected
		}
	}

	/*
	 * Super class of MockObject.
	 */
	public static class MockParent {

		protected static String calledMethod = null;

		protected static Vector receivedArguments = new Vector();

		public void method() {
			reset();
			calledMethod = "method1";
		}

		protected void method(Boolean o) {
			reset();
			calledMethod = "method1-1";
			receivedArguments.add(o);
		}

		public void method(Integer o) {
			reset();
			calledMethod = "method1-2";
			receivedArguments.add(o);
		}

		public void method(Object o) {
			reset();
			calledMethod = "method2";
			receivedArguments.add(o);
		}

		public void method(String o) {
			reset();
			calledMethod = "method3";
			receivedArguments.add(o);
		}

		public void method(Object o, Object o2) {
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

		public void intMethod(int i) {
			reset();
			calledMethod = "intMethod";
			receivedArguments.add(new Integer(i));
		}

		public void intArrayMethod(int[] ia) {
			reset();
			calledMethod = "intArrayMethod";
			receivedArguments.add(ia);
		}

		public void integerArrayMethod(Integer[] ia) {
			reset();
			calledMethod = "integerArrayMethod";
			receivedArguments.add(ia);
		}

		public void methodB(Integer i) {
			reset();
			calledMethod = "methodB1";
			receivedArguments.add(i);
		}

		public void methodB(int i) {
			reset();
			calledMethod = "methodB2";
			receivedArguments.add(new Integer(i));
		}

		public void methodB(boolean b) {
			reset();
			calledMethod = "methodB3";
			receivedArguments.add(new Boolean(b));
		}

		public void methodB(Boolean b) {
			reset();
			calledMethod = "methodB4";
			receivedArguments.add(b);
		}

		public static void staticMethod(Object o) {
			reset();
			calledMethod = "staticMethod";
			receivedArguments.add(o);
		}

		public void equalSpecificMethod(MockObject o, MockParent p) {
			reset();
			calledMethod = "equalSpecificMethod1";
			receivedArguments.add(o);
			receivedArguments.add(p);
		}

		public void equalSpecificMethod(MockParent p, MockObject o) {
			reset();
			calledMethod = "equalSpecificMethod2";
			receivedArguments.add(p);
			receivedArguments.add(o);
		}

		public void equalSpecificMethod(MockParent p, MockObject o, Object o2)
				throws Exception {
			reset();
			calledMethod = "equalSpecificMethod4";
			receivedArguments.add(p);
			receivedArguments.add(o);
			receivedArguments.add(o2);
		}

		public void equalSpecificMethod(MockObject o, MockParent p, Object o2) {
			reset();
			calledMethod = "equalSpecificMethod3";
			receivedArguments.add(o);
			receivedArguments.add(p);
			receivedArguments.add(o2);
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
}
