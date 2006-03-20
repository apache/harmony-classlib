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
 * @version $Revision: 1.2.6.3 $
 */
package java.beans;

import java.beans.Expression;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import junit.textui.TestRunner;

import java.beans.auxiliary.SampleBean;

/**
 * The test checks the class java.beans.Expression
 * @author Maxim V. Berkultsev
 * @version $Revision: 1.2.6.3 $
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
                fail("Cannot instantialte an instance of Bean class.");
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
                fail("Cannot instantialte an instance of Bean class by "
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
}
