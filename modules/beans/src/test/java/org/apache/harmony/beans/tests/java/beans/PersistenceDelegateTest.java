/* 
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
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

import java.beans.Encoder;
import java.beans.Expression;
import java.beans.PersistenceDelegate;
import java.beans.Statement;
import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.EmptyStackException;
import java.util.Stack;

import junit.framework.TestCase;

import org.apache.harmony.beans.tests.support.mock.MockFoo;
import org.apache.harmony.beans.tests.support.mock.MockFooStop;
/**
 * Test java.beans.PersistenceDelegate
 */
public class PersistenceDelegateTest extends TestCase {

    /*
     * Test the constructor.
     */
    public void testConstructor() {
        new DummyPersistenceDelegate();
    }

    /*
     * Tests writeObject() under normal condition when mutatesTo() returns True.
     */
    public void testWriteObject_NormalMutatesToTrue() {
        MockPersistenceDelegate2 pd = new MockPersistenceDelegate2(true);
        MockEncoder2 enc = new MockEncoder2();
        MockFoo foo = new MockFoo();

        pd.writeObject(foo, enc);
        
        assertEquals("initialize", pd.popMethod());
        assertEquals("mutatesTo", pd.popMethod());
    }

    /*
     * Tests writeObject() under normal condition when mutatesTo() returns
     * false.
     */
    public void testWriteObject_NormalMutatesToFalse() {
        MockPersistenceDelegate2 pd = new MockPersistenceDelegate2(false);
        MockEncoder2 enc = new MockEncoder2();
        MockFoo foo = new MockFoo();

        pd.writeObject(foo, enc);
        
        assertEquals("instantiate", pd.popMethod());
        assertEquals("mutatesTo", pd.popMethod());
        assertWasAdded(MockFoo.class.getClass(), "new", null, enc);
    }

    /*
     * Tests writeObject() when object is null.
     */
    public void testWriteObject_NullObject() {
		MockPersistenceDelegate2 pd = new MockPersistenceDelegate2();
		Encoder enc = new Encoder();

        try {
            pd.writeObject(null, enc);
            fail("Should throw NullPointerException!");
        } catch (NullPointerException ex) {
            // expected
        }
    }

    /*
     * Tests writeObject() when encoder is null.
     */
    public void testWriteObject_NullEncoder() {
		MockPersistenceDelegate2 pd = new MockPersistenceDelegate2();

        try {
            pd.writeObject(new MockFoo(), null);
            fail("Should throw NullPointerException!");
        } catch (NullPointerException ex) {
            // expected
        }
    }

    /*
     * Tests initialize() under normal conditions.
     */
    public void testInitialize_Normal() {
        DummyPersistenceDelegate pd = new DummyPersistenceDelegate();
        MockPersistenceDelegate3 pd3 = new MockPersistenceDelegate3();
        Encoder enc = new Encoder();
        
        enc.setPersistenceDelegate(MockFooStop.class, pd3);
        pd.initialize(MockFoo.class, new MockFoo(), new MockFoo(), enc);
        assertEquals("initialize", pd3.popMethod());
        assertFalse("Extra statement has been detected", pd3.hasMoreMethods());
        
        // test interface
        pd3 = new MockPersistenceDelegate3();
        enc.setPersistenceDelegate(MockInterface.class, pd3);
        pd.initialize(MockObject.class, new MockObject(), new MockObject(),
                      enc);
        assertFalse("Extra statement has been detected", pd3.hasMoreMethods());
    }

    /*
     * Tests initialize() with null class.
     */
    public void testInitialize_NullClass() {
        DummyPersistenceDelegate pd = new DummyPersistenceDelegate();
        Encoder enc = new Encoder();

        enc.setPersistenceDelegate(MockFooStop.class,
                new DummyPersistenceDelegate());
    
        try {
            pd.initialize(null, new Object(), new Object(), enc);
            fail("Should throw NullPointerException!");
        } catch (NullPointerException ex) {
            // expected
        }
    }

    /*
     * Tests initialize() with null old and new instances.
     */
    public void testInitialize_NullInstances() {
        DummyPersistenceDelegate pd = new DummyPersistenceDelegate();
        MockPersistenceDelegate3 pd3 = new MockPersistenceDelegate3();
        Encoder enc = new Encoder();

        enc.setPersistenceDelegate(MockFooStop.class, pd3);
        pd.initialize(MockFoo.class, null, null, enc);
        assertEquals("initialize", pd3.popMethod());
    }

    /*
     * Tests initialize() with null encoder.
     */
    public void testInitialize_NullEncoder() {
        DummyPersistenceDelegate pd = new DummyPersistenceDelegate();

        try {
            pd.initialize(MockFoo.class, new Object(), new Object(), null);
            fail("Should throw NullPointerException!");
        } catch (NullPointerException ex) {
            // expected
        }
    }

    /**
     * Circular redundancy check. Should not hang. 
     * Regression test for HARMONY-2073
     */
    public void testInitialize_circularRedundancy() {
        Encoder enc = new Encoder();
        DummyPersistenceDelegate pd = new DummyPersistenceDelegate();
        
        enc.setPersistenceDelegate(MockFooStop.class, pd);
        pd.initialize(MockFoo.class, new MockFoo(), new MockFoo(), enc);
    }

    /*
     * Tests mutatesTo() under normal conditions.
     */
    public void testMutatesTo_Normal() {
        DummyPersistenceDelegate pd = new DummyPersistenceDelegate();
        assertTrue(pd.mutatesTo("test1", "test2"));
        assertFalse(pd.mutatesTo(new Object(), new Object() {
            @Override
            public int hashCode() {
                return super.hashCode();
            }
        }));
        assertFalse(pd.mutatesTo(new MockFoo(), new MockFooStop()));
    }

    /*
     * Tests mutatesTo() with null parameters.
     */
    public void testMutatesTo_Null() {
        DummyPersistenceDelegate pd = new DummyPersistenceDelegate();

        assertFalse(pd.mutatesTo("test", null));
        assertFalse(pd.mutatesTo(null, null));
        assertFalse(pd.mutatesTo(null, "test"));
    }

    public void test_writeObject_Null_LXMLEncoder() throws Exception{
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        XMLEncoder encoder = new XMLEncoder(new BufferedOutputStream(byteArrayOutputStream));
        encoder.writeObject(null);
        encoder.close();

        DataInputStream stream = new DataInputStream(new ByteArrayInputStream(byteArrayOutputStream.toByteArray()));
        XMLDecoder decoder = new XMLDecoder(stream);
        assertNull(decoder.readObject());
        stream = new DataInputStream(PersistenceDelegateTest.class.getResourceAsStream("/xml/null.xml"));
        decoder = new XMLDecoder(stream);
        assertNull(decoder.readObject());
    }

    class Bar {
        public int value;
        public void barTalk() {
            System.out.println("Bar is coming!");
        }
    }
    public void test_writeObject_java_lang_reflect_Field()
			throws SecurityException, NoSuchFieldException, IOException {
		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		XMLEncoder encoder = new XMLEncoder(new BufferedOutputStream(
				byteArrayOutputStream));
		Field value = Bar.class.getField("value");
		encoder.writeObject(value);
		encoder.close();

		DataInputStream stream = new DataInputStream(new ByteArrayInputStream(
				byteArrayOutputStream.toByteArray()));
		
		XMLDecoder decoder = new XMLDecoder(stream); Field field = (Field)
		decoder.readObject();

        assertEquals(value, field);
		assertEquals(value.getName(), field.getName());
	}
    
    public void test_writeObject_java_lang_reflect_Method() throws SecurityException, NoSuchMethodException{
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        XMLEncoder encoder = new XMLEncoder(new BufferedOutputStream(
            byteArrayOutputStream));
        Method method = Bar.class.getMethod("barTalk", (Class[])null);

        encoder.writeObject(method);
        encoder.close();
        DataInputStream stream = new DataInputStream(new ByteArrayInputStream(
                byteArrayOutputStream.toByteArray()));
        XMLDecoder decoder = new XMLDecoder(stream);
        Method aMethod = (Method) decoder.readObject();
        assertEquals(method, aMethod);
        assertEquals(method.getName(), aMethod.getName());
        assertEquals("barTalk", aMethod.getName());
    }

    // <--

    private void assertWasAdded(Class<?> targetClass, String methodName,
                                 Object[] args, MockEncoder2 enc) {
        try {
            while (true) {
                Statement stmt = enc.pop();

                if (equals(stmt, targetClass, methodName, args)) {
                    break;
                }
            }
        } catch (EmptyStackException e) {
            fail("Required statement was not found");
        }
    }

    private boolean equals(Statement stmt, Class<?> targetClass,
                              String methodName, Object[] args) {
           
        if (stmt == null || !methodName.equals(stmt.getMethodName())) {
            return false;
        }

        if (targetClass != null) {
            // check if we have the object of the same class at least
            if (targetClass != stmt.getTarget().getClass()) {
                return false;
            }
        } else {
            if (stmt.getTarget() != null) {
                return false;
            }
        }
        
        if (args != null) {
            if (stmt.getArguments() == null ||
                args.length != stmt.getArguments().length) {
                return false;
            }

            for (int i = 0; i < args.length; i++) {

                if (args[i] != null) {
                    if (!args[i].equals(stmt.getArguments()[i])) {
                        return false;
                    }
                } else {
                    if (stmt.getArguments()[i] != null) {
                        return false;
                    }
                }
            }
        } else {
            if (stmt.getArguments() != null && stmt.getArguments().length > 0) {
                return false;
            }
        }
        
        return true;
    }

    /*
     * Mock interface.
     */
    static interface MockInterface {

    }

    /*
     * Mock interface.
     */
    static class MockObject implements MockInterface {

    }

    static class MockEncoder2 extends Encoder {
        Stack<Statement> stmts = new Stack<Statement>();
        
        @Override
        public void writeExpression(Expression expr) {
            stmts.push(expr);
            super.writeExpression(expr);
        }

        @Override
        public void writeStatement(Statement stmt) {
            stmts.push(stmt);
            super.writeStatement(stmt);
        }
        
        @Override
        public void writeObject(Object obj) {
            super.writeObject(obj);
        }

        public Statement pop() {
            return stmts.pop();
        }

    }

    static class MockPersistenceDelegate2 extends PersistenceDelegate {
        private Boolean mutatesToFlag = null;
        Stack<String> methods = new Stack<String>();

        public MockPersistenceDelegate2() {}
        
        public MockPersistenceDelegate2(boolean mutatesToFlag) {
            this.mutatesToFlag = Boolean.valueOf(mutatesToFlag);
        }

        @Override
        public void initialize(Class<?> type, Object oldInstance,
                Object newInstance, Encoder enc) {
            methods.push("initialize");
            super.initialize(type, oldInstance, newInstance, enc);
        }
        
        @Override
        public Expression instantiate(Object oldInstance, Encoder out) {
            methods.push("instantiate");
            return new Expression(oldInstance.getClass(), "new", null);
        }
        
        @Override
        public boolean mutatesTo(Object oldInstance, Object newInstance) {
            methods.push("mutatesTo");

            if (mutatesToFlag != null) {
                return mutatesToFlag.booleanValue();
            }
            return super.mutatesTo(oldInstance, newInstance);
        }
        
        String popMethod() {
            return methods.pop();
        }
        
        boolean hasMoreMethods() {
            return !methods.empty();
        }
    }

    static class MockPersistenceDelegate3 extends PersistenceDelegate {
        Stack<String> methods = new Stack<String>();

        @Override
        public void initialize(Class<?> type, Object oldInstance,
                Object newInstance, Encoder enc) {
            methods.push("initialize");
        }

        @Override
        public Expression instantiate(Object oldInstance, Encoder out) {
            methods.push("instantiate");
            return null;
        }

        @Override
        public boolean mutatesTo(Object oldInstance, Object newInstance) {
            methods.push("mutatesTo");
            return true;
        }

        String popMethod() {
            return methods.pop();
        }

        boolean hasMoreMethods() {
            return !methods.empty();
        }
    }

    /*
     * Dummy PersistenceDelegate subclass.
     */
    static class DummyPersistenceDelegate extends PersistenceDelegate {
        @Override
        public Expression instantiate(Object oldInstance, Encoder out) {
            return new Expression(oldInstance.getClass(), "new", null);
        }

        @Override
        public void initialize(Class<?> type, Object oldInstance,
                Object newInstance, Encoder enc) {
            super.initialize(type, oldInstance, newInstance, enc);
        }

        @Override
        public boolean mutatesTo(Object oldInstance, Object newInstance) {
            return super.mutatesTo(oldInstance, newInstance);
        }

        @Override
        public void writeObject(Object oldInstance, Encoder enc) {
            super.writeObject(oldInstance, enc);
        }

    }

}
