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
 * @version $Revision: 1.4.6.4 $
 */
package org.apache.harmony.tests.java.beans;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import junit.textui.TestRunner;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.EventHandler;
import org.apache.harmony.tests.java.beans.auxiliary.InvocationObject;
import org.apache.harmony.tests.java.beans.auxiliary.SampleEvent;
import org.apache.harmony.tests.java.beans.auxiliary.SampleListener;
import java.lang.reflect.Method;

/**
 * The test checks the class java.beans.EventHandler
 * @author Maxim V. Berkultsev
 * @version $Revision: 1.4.6.4 $
 */

public class EventHandlerTest extends TestCase {
    
    private Object object;
    private String methodName;
    private Object[] params;
    
    private String text = "something";
    
    /**
     * 
     */
    public EventHandlerTest() {
        super();
    }
    
    /**
     *
     */
    public EventHandlerTest(String name) {
        super(name);
    }
    
    /**
     * The test checks event handler accessors
     */
    public void testAccessors() {
        InvocationObject invocationObject = new InvocationObject(this);
        EventHandler handler = new EventHandler(
            invocationObject, "someText", "source.text", "actionPerformed" );
        assertEquals(invocationObject, handler.getTarget());
        assertEquals("someText", handler.getAction());
        assertEquals("source.text", handler.getEventPropertyName());
        assertEquals("actionPerformed", handler.getListenerMethodName());
    }
    
    /**
     * The test checks the method invoke() with null listener value
     */
    public void testNullListenerMethodName() {
        InvocationObject invocationObject = new InvocationObject(this);
        
        EventHandler handler = new EventHandler(
            invocationObject, "someText", "source.text", null );
        
        Object proxy = EventHandler.create(ActionListener.class,
            invocationObject, "someText", "source.text");

        Method m = null;
        try {
            m = ActionListener.class.getMethod("actionPerformed",
                    new Class[] { ActionEvent.class } );
            Object result = handler.invoke(proxy, m,
                    new Object[] { new ActionEvent(this, 0, "") } );
            
            assertEquals(invocationObject.getSomeText(), getText());
        } catch (Exception e) {
            fail("Method actionPerformed not found in interface");
        }
    }
    
    /**
     * The test checks the method invoke()
     */
    public void testInvoke() {
        InvocationObject invocationObject = new InvocationObject(this);
        
        EventHandler handler = new EventHandler(
            invocationObject, "someText", "source.text", "actionPerformed" );
        
        Object proxy = EventHandler.create(ActionListener.class,
            invocationObject, "someText", "source.text");

        Method m = null;
        try {
            m = ActionListener.class.getMethod("actionPerformed",
                    new Class[] { ActionEvent.class } );
            Object result = handler.invoke(proxy, m,
                    new Object[] { new ActionEvent(this, 0, "") } );
            
            assertEquals(invocationObject, handler.getTarget());
            assertEquals(invocationObject.getSomeText(), getText());
        } catch (Exception e) {
            fail("Method actionPerformed not found in interface");
        }
    }
    
    /**
     * The test checks the method invoke() with null property name
     */
    public void testInvokeWithNullPropertyName() {
        InvocationObject invocationObject = new InvocationObject(this);
        
        EventHandler handler = new EventHandler(
            invocationObject, "doSomething", null, null );
        
        Object proxy = EventHandler.create(SampleListener.class,
                invocationObject, "doSomething");

        try {
            Method m = SampleListener.class.getMethod(
                "fireSampleEvent", new Class[] { SampleEvent.class } );
            Object result = handler.invoke(proxy, m, null);
            
            assertEquals(invocationObject, handler.getTarget());
            assertEquals("doSomething", getMethodName());
        } catch (Exception e) {
            fail("Method doSomething not found in interface");
        }
    }
    
    /**
     * The test checks the object created with the create() method call
     */
    public void testCreateWithMethodCall() {
        Object invocationObject = new InvocationObject(this);
        ActionListener listener = (ActionListener) EventHandler.create(
            ActionListener.class, invocationObject, "doSomething");
        listener.actionPerformed(new ActionEvent(this, 0, ""));
        
        assertEquals(getObject(), invocationObject);
        assertEquals("doSomething", getMethodName());
        
        Object[] params = getParams();
        if(params.length != 0) {
            fail("Number of params should be 0");
        }
    }
    
    /**
     * The test checks the setter is initialized properly
     */
    public void testCreateWithSetterCall() {
        Object invocationObject = new InvocationObject(this);
        ActionEvent ae = new ActionEvent(this, 0, "");
        ActionListener listener = (ActionListener) EventHandler.create(
            ActionListener.class, invocationObject, "someObject", "source");
        listener.actionPerformed(ae);
        
        assertEquals(getObject(), invocationObject);
        assertEquals("setSomeObject", getMethodName());
        
        Object[] params = getParams();
        if(params.length != 1) {
            fail("Number of params should be 1");
        } else {
            assertEquals(ae.getSource(), params[0]);
        }
    }
    
    /**
     * The test checks the object created with the create() method call for
     * dot-separated property
     */
    public void testCreateWithDottedParameterCall() {
        Object invocationObject = new InvocationObject(this);
        ActionEvent ae = new ActionEvent(this, 0, "");
        ActionListener listener = (ActionListener) EventHandler.create(
            ActionListener.class, invocationObject, "someText", "source.text");
        listener.actionPerformed(ae);
        
        assertEquals(getObject(), invocationObject);
        assertEquals("setSomeText", getMethodName());
        
        Object[] params = getParams();
        if(params.length != 1) {
            fail("Number of params should be 1");
        } else {
            assertEquals(((EventHandlerTest) ae.getSource()).getText(),
                    params[0]);
        }
    }
    
    /**
     * The test checks the event is fired for object created with the create()
     */
    public void testCreateWithMethodCallWhichIsSetter() {
        InvocationObject invocationObject = new InvocationObject(this);
        SampleEvent event = new SampleEvent("bean");
        
        SampleListener listener = (SampleListener) EventHandler.create(
                SampleListener.class,
            invocationObject, "doSomething", "i", null);
        
        listener.fireSampleEvent(event);
        
        assertEquals("doSomething", getMethodName());
        assertTrue(event.getI() == invocationObject.getIntValue());
    }
    
    /**
     * fireSampleEvent scenario
     */
    public void testCreateForStaticMethodAsPropertyGetter() {
        InvocationObject invocationObject = new InvocationObject(this);
        SampleEvent event = new SampleEvent("bean");
        
        SampleListener listener = (SampleListener) EventHandler.create(
                SampleListener.class,
            invocationObject, "someValue", "j");
        
        listener.fireSampleEvent(event);
        
        assertEquals("setSomeValue", getMethodName());
    }
    
    /**
     * 
     */
    public static Test suite() {
        //TestSuite suite = new TestSuite();
        //suite.addTest(new EventHandlerTest("testCreateForStaticMethodAsPropertyGetter"));
        //return suite;
        return new TestSuite(EventHandlerTest.class);
    }
    
    /**
     * 
     */
    public static void main(String[] args) {
        TestRunner.run(suite());
    }
    
    public void logMethodCall(
            Object object, String methodName, Object[] params) {
        this.object = object;
        this.methodName = methodName;
        this.params = params;
    }
    
    public String getText() {
        return text;
    }
    
    private Object getObject() {
        return object;
    }
    
    private String getMethodName() {
        return methodName;
    }
    
    private Object[] getParams() {
        return params;
    }
}
