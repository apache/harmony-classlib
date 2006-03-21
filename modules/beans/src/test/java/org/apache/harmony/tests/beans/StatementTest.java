/*
 *  Copyright 2005 The Apache Software Foundation or its licensors, as applicable.
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

/**
 * @author Maxim V. Berkultsev
 * @version $Revision: 1.4.6.3 $
 */
package org.apache.harmony.tests.beans;

import java.beans.Statement;
import org.apache.harmony.tests.beans.auxiliary.SampleException;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import junit.textui.TestRunner;

/**
 * The test checks the class java.beans.Statement
 * @author Maxim V. Berkultsev
 * @version $Revision: 1.4.6.3 $
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
}
