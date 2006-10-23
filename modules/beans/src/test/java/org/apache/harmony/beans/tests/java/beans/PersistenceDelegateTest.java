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
import java.beans.ExceptionListener;
import java.beans.Expression;
import java.beans.PersistenceDelegate;
import java.beans.Statement;

import java.util.Stack;
import java.util.EmptyStackException;

import junit.framework.TestCase;

import org.apache.harmony.beans.tests.support.mock.MockFoo;
import org.apache.harmony.beans.tests.support.mock.MockFooStop;

import tests.util.CallVerificationStack;

/**
 * Test java.beans.PersistenceDelegate
 */
public class PersistenceDelegateTest extends TestCase {

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        CallVerificationStack.getInstance().clear();
    }

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
        Encoder enc = new Encoder();
        Object o1 = new Object();
        Object o2 = new Object();
        enc.setPersistenceDelegate(MockFooStop.class,
                new MockPersistenceDelegate());
        pd.initialize(MockFoo.class, o1, o2, enc);
        assertSame(enc, CallVerificationStack.getInstance().pop());
        assertSame(o2, CallVerificationStack.getInstance().pop());
        assertSame(o1, CallVerificationStack.getInstance().pop());
        assertEquals(
                "org.apache.harmony.beans.tests.java.beans.PersistenceDelegateTest$MockPersistenceDelegate",
                CallVerificationStack.getInstance().getCurrentSourceClass());
        assertEquals("initialize", CallVerificationStack.getInstance()
                .getCurrentSourceMethod());
        assertSame(MockFooStop.class, CallVerificationStack.getInstance().pop());
        assertTrue(CallVerificationStack.getInstance().empty());
        // test interface
        o1 = new MockObject();
        enc.setPersistenceDelegate(MockInterface.class,
                new MockPersistenceDelegate());
        pd.initialize(MockObject.class, o1, o1, enc);
        assertTrue(CallVerificationStack.getInstance().empty());
    }

    /*
     * Tests initialize() with null class.
     */
    public void testInitialize_NullClass() {
        DummyPersistenceDelegate pd = new DummyPersistenceDelegate();
        Encoder enc = new Encoder();
        Object o1 = new Object();
        Object o2 = new Object();
        enc.setPersistenceDelegate(MockFooStop.class,
                new MockPersistenceDelegate());
        try {
            pd.initialize(null, o1, o2, enc);
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
        Encoder enc = new Encoder();
        enc.setPersistenceDelegate(MockFooStop.class,
                new MockPersistenceDelegate());
        pd.initialize(MockFoo.class, null, null, enc);
        assertSame(enc, CallVerificationStack.getInstance().pop());
        assertSame(null, CallVerificationStack.getInstance().pop());
        assertSame(null, CallVerificationStack.getInstance().pop());
        assertEquals(
                "org.apache.harmony.beans.tests.java.beans.PersistenceDelegateTest$MockPersistenceDelegate",
                CallVerificationStack.getInstance().getCurrentSourceClass());
        assertEquals("initialize", CallVerificationStack.getInstance()
                .getCurrentSourceMethod());
        assertSame(MockFooStop.class, CallVerificationStack.getInstance().pop());
        assertTrue(CallVerificationStack.getInstance().empty());
    }

    /*
     * Tests initialize() with null encoder.
     */
    public void testInitialize_NullEncoder() {
        DummyPersistenceDelegate pd = new DummyPersistenceDelegate();
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
        // DummyEncoder enc = new DummyEncoder();
        // pd.writeObject(new MockFoo(), enc);
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

    /*
     * Mock Encoder.
     */
    static class MockEncoder extends Encoder {

        @Override
        public Object get(Object oldInstance) {
            StackTraceElement[] eles = (new Throwable()).getStackTrace();
            if (eles[2].getClassName().equals(
                    MockPersistenceDelegate.class.getName())) {
                CallVerificationStack.getInstance().push(oldInstance);
            }
            return super.get(oldInstance);
        }

        @Override
        public ExceptionListener getExceptionListener() {
            return super.getExceptionListener();
        }

        @Override
        public PersistenceDelegate getPersistenceDelegate(Class<?> type) {
            return super.getPersistenceDelegate(type);
        }

        @Override
        public Object remove(Object oldInstance) {
            StackTraceElement[] eles = (new Throwable()).getStackTrace();
            if (eles[2].getClassName().equals(
                    MockPersistenceDelegate.class.getName())) {
                CallVerificationStack.getInstance().push(oldInstance);
            }
            return super.remove(oldInstance);
        }

        @Override
        public void setExceptionListener(ExceptionListener exceptionListener) {
            super.setExceptionListener(exceptionListener);
        }

        @Override
        public void setPersistenceDelegate(Class<?> type,
                PersistenceDelegate persistenceDelegate) {
            super.setPersistenceDelegate(type, persistenceDelegate);
        }

        @Override
        public void writeExpression(Expression oldExp) {
            StackTraceElement[] eles = (new Throwable()).getStackTrace();
            if (eles[2].getClassName().equals(
                    MockPersistenceDelegate.class.getName())) {
                CallVerificationStack.getInstance().push(oldExp);
            }
            super.writeExpression(oldExp);
        }

        @Override
        public void writeStatement(Statement oldStm) {
            StackTraceElement[] eles = (new Throwable()).getStackTrace();
            if (eles[2].getClassName().equals(
                    MockPersistenceDelegate.class.getName())) {
                CallVerificationStack.getInstance().push(oldStm);
            }
            super.writeStatement(oldStm);
        }

        @Override
        public void writeObject(Object o) {
            super.writeObject(o);
        }
    }

    /*
     * Mock PersistenceDelegate subclass.
     */
    static class MockPersistenceDelegate extends PersistenceDelegate {
        private boolean filter = false;

        private boolean mutatesToResult = true;

        public MockPersistenceDelegate() {
            // empty
        }

        public MockPersistenceDelegate(boolean mutatesToResult, boolean filter) {
            this.mutatesToResult = mutatesToResult;
            this.filter = filter;
        }

        public void setMutatesToResult(boolean ret) {
            this.mutatesToResult = ret;
        }

        @Override
        public Expression instantiate(Object oldInstance, Encoder out) {
            StackTraceElement[] eles = (new Throwable()).getStackTrace();
            if (!this.filter
                    || eles[2].getClassName().equals(this.getClass().getName())) {
                CallVerificationStack.getInstance().push(oldInstance);
                CallVerificationStack.getInstance().push(out);
            }
            return new Expression(oldInstance, MockFoo.class, "new", null);
        }

        @Override
        public void initialize(Class<?> type, Object oldInstance,
                Object newInstance, Encoder enc) {
            StackTraceElement[] eles = (new Throwable()).getStackTrace();
            if (!this.filter
                    || eles[2].getClassName().equals(this.getClass().getName())) {
                CallVerificationStack.getInstance().push(type);
                CallVerificationStack.getInstance().push(oldInstance);
                CallVerificationStack.getInstance().push(newInstance);
                CallVerificationStack.getInstance().push(enc);
            }
        }

        @Override
        public boolean mutatesTo(Object oldInstance, Object newInstance) {
            StackTraceElement[] eles = (new Throwable()).getStackTrace();
            if (!this.filter
                    || eles[2].getClassName().equals(this.getClass().getName())) {
                CallVerificationStack.getInstance().push(oldInstance);
                CallVerificationStack.getInstance().push(newInstance);
            }
            return this.mutatesToResult;
        }

        @Override
        public void writeObject(Object oldInstance, Encoder enc) {
            // CallVerificationStack.getInstance().push(oldInstance);
            // CallVerificationStack.getInstance().push(enc);
            super.writeObject(oldInstance, enc);
        }

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
            this.mutatesToFlag = mutatesToFlag;
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
                return mutatesToFlag;
            }
            return super.mutatesTo(oldInstance, newInstance);
        }
        
        String popMethod() {
            return methods.pop();
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
