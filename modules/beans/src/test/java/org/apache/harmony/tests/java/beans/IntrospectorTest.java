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
 * @version $Revision: 1.14.6.3 $
 */
package org.apache.harmony.tests.java.beans;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import junit.textui.TestRunner;

import java.beans.Introspector;
import java.beans.BeanInfo;
import java.beans.BeanDescriptor;
import java.beans.EventSetDescriptor;
import java.beans.MethodDescriptor;
import java.beans.PropertyDescriptor;
import java.beans.IntrospectionException;
import org.apache.harmony.tests.java.beans.auxiliary.ChildBean;
import org.apache.harmony.tests.java.beans.auxiliary.GrannyBean;
import org.apache.harmony.tests.java.beans.auxiliary.MisprintBean;
import org.apache.harmony.tests.java.beans.auxiliary.OtherBean;
import org.apache.harmony.tests.java.beans.auxiliary.SampleBean;
import org.apache.harmony.tests.java.beans.auxiliary.StandardBean2;
import java.lang.reflect.Method;
import java.util.TooManyListenersException;

/**
 * The test checks the class java.beans.Introspector
 * @author Maxim V. Berkultsev
 * @version $Revision: 1.14.6.3 $
 */

public class IntrospectorTest extends TestCase {
    
    /**
     * 
     */
    public IntrospectorTest() {
        super();
    }
    
    /**
     *
     */
    public IntrospectorTest(String name) {
        super(name);
    }
    
    /**
     * The test checks the constructor is private
     */
    public void testIntrospectorConstructor() {
        try {
            Class.forName("java.beans.Introspector").newInstance();
            fail("No exception is thrown on new Introspector() call");
        } catch (Exception e) {}
    }
    
    /**
     * The test checks the getBeanDescriptor method
     */
    public void testBeanDescriptor() {
        String[] oldBeanInfoSearchPath = Introspector.getBeanInfoSearchPath();
        try {
            Introspector.setBeanInfoSearchPath(
                    new String[] { "java.beans.infos" } );
            BeanInfo info = Introspector.getBeanInfo(SampleBean.class);
            assertNotNull(info);
            BeanDescriptor descriptor = info.getBeanDescriptor();
            assertNotNull(descriptor);
            assertEquals(SampleBean.class, descriptor.getBeanClass());
        } catch (Exception e) {
            fail("Cannot extract bean info from SampleBean class");
        } finally {
            Introspector.setBeanInfoSearchPath(oldBeanInfoSearchPath);
        }
    }
    
    /**
     * The test checks the getMethodDescriptor method
     */
    public void testNonUniqueByNameMethodDescriptors() {
        try {
            BeanInfo info = Introspector.getBeanInfo(ChildBean.class);
            assertNotNull(info);
            MethodDescriptor[] mds = info.getMethodDescriptors();
            assertNotNull(mds);
            assertEquals(11, mds.length);
            assertEquals("wait", mds[8].getName());
            assertEquals("wait", mds[9].getName());
            assertEquals("wait", mds[10].getName());
        } catch (Exception e) {
            fail("Cannot extract bean info from ChildBean class: "
                    + e.getClass());
        }
    }
    
    /**
     * The test checks the getEventSetDescriptors method
     */
    public void testUnicastEventSetDescriptor() {
        try {
            BeanInfo info = Introspector.getBeanInfo(SampleBean.class);
            assertNotNull(info);
            EventSetDescriptor[] descriptors = info.getEventSetDescriptors();
            assertNotNull(descriptors);
            for(int i = 0; i < descriptors.length; ++i) {
                Method m = descriptors[i].getAddListenerMethod();
                if(m != null) {
                    Class[] exceptionTypes = m.getExceptionTypes();
                    boolean found = false;
                    
                    for(int j = 0; j < exceptionTypes.length; ++j) {
                        if(exceptionTypes[j].equals(
                                TooManyListenersException.class)) {
                            assertTrue(descriptors[i].isUnicast());
                            found = true;
                            break;
                        }
                    }
                    
                    if(!found) {
                        assertEquals(descriptors[i].isUnicast(), false);
                    }
                        
                }
            }
        } catch (Exception e) {
            fail("Cannot extract bean info from SampleBean class: "
                    + e.getClass());
        }
    }
    
    /**
     * The test checks the getEventSetDescriptors method
     */
    public void testEventSetDescriptorWithoutAddListenerMethod() {
        try {
            BeanInfo info = Introspector.getBeanInfo(OtherBean.class);
            assertNotNull(info);
            EventSetDescriptor[] descriptors = info.getEventSetDescriptors();
            assertNotNull(descriptors);
            assertEquals(0, descriptors.length);
        } catch (Exception e) {
            System.out.println(e.getClass() + ": " + e.getMessage());
            fail("Cannot extract bean info from SampleBean class");
        }
    }
    
    /**
     * The test checks the getEventSetDescriptors method
     */
    public void testIllegalEventSetDescriptor() {
        try {
            BeanInfo info = Introspector.getBeanInfo(MisprintBean.class);
            assertNotNull(info);
            EventSetDescriptor[] descriptors = info.getEventSetDescriptors();
            assertNotNull(descriptors);
            assertEquals(0, descriptors.length);
        } catch (Exception e) {
            System.out.println(e.getClass() + ": " + e.getMessage());
            fail("Cannot extract bean info from MisprintBean class");
        }
    }
    
    /**
     * The test checks the getPropertyDescriptors method
     */
    public void testPropertyDescriptorWithSetMethod() {
        try {
            BeanInfo info = Introspector.getBeanInfo(OtherBean.class);
            assertNotNull(info);
            PropertyDescriptor[] descriptors = info.getPropertyDescriptors();
            assertNotNull(descriptors);
            assertEquals(2, descriptors.length);
            assertEquals("class", descriptors[0].getName());
            assertEquals("number", descriptors[1].getName());
        } catch (Exception e) {
            System.out.println(e.getClass() + ": " + e.getMessage());
            fail("Cannot extract bean info from SampleBean class");
        }
    }
    
    /**
     * The test checks the getPropertyDescriptors method
     */
    public void testUseAllBeanInfo() {
        try {
            BeanInfo info = Introspector.getBeanInfo(ChildBean.class,
                    Introspector.USE_ALL_BEANINFO);
            PropertyDescriptor[] pds = info.getPropertyDescriptors();
            assertEquals(1, pds.length);
            assertEquals("childText", pds[0].getName());
        } catch(IntrospectionException ie) {
            fail(ie.getClass() + ": " + ie.getMessage());
        }
    }
    
    /**
     * The test checks the getPropertyDescriptors method for
     * IGNORE_IMMEDIATE_BEANINFO flag
     */
    public void testIgnoreImmediateBeanInfo() {
        try {
            BeanInfo info = Introspector.getBeanInfo(ChildBean.class,
                    Introspector.IGNORE_IMMEDIATE_BEANINFO);
            PropertyDescriptor[] pds = info.getPropertyDescriptors();
            assertEquals(1, pds.length);
            assertEquals("parentText", pds[0].getName());
        } catch(IntrospectionException ie) {
            fail(ie.getClass() + ": " + ie.getMessage());
        }
    }
    
    /**
     * The test checks the getPropertyDescriptors method for
     * IGNORE_ALL_BEANINFO flag
     */
    public void testIgnoreAllBeanInfo() {
        try {
            BeanInfo info = Introspector.getBeanInfo(ChildBean.class,
                    Introspector.IGNORE_ALL_BEANINFO);
            PropertyDescriptor[] pds = info.getPropertyDescriptors();
            assertEquals(2, pds.length);
            assertEquals("class", pds[0].getName());
            assertEquals("text", pds[1].getName());
        } catch(IntrospectionException ie) {
            fail(ie.getClass() + ": " + ie.getMessage());
        }
    }
    
    /**
     * The test checks the getPropertyDescriptors method
     */
    public void testAdditionalBeanInfo() {
        try {
            BeanInfo info = Introspector.getBeanInfo(StandardBean2.class);
            PropertyDescriptor[] pds = info.getPropertyDescriptors();
            assertEquals(1, pds.length);
            assertEquals("grannyText", pds[0].getName());
        } catch(IntrospectionException ie) {
            fail(ie.getClass() + ": " + ie.getMessage());
        }
    }
    
    /**
     * The test checks the getPropertyDescriptors for stop class prune
     */
    public void testStopClass() {
        try {
            BeanInfo info = Introspector.getBeanInfo(ChildBean.class,
                    GrannyBean.class);
            PropertyDescriptor[] pds = info.getPropertyDescriptors();
            assertEquals(1, pds.length);
            assertEquals("childText", pds[0].getName());
        } catch(IntrospectionException ie) {
            fail(ie.getClass() + ": " + ie.getMessage());
        }
    }
    
    /**
     * 
     */
    public static Test suite() {
        return new TestSuite(IntrospectorTest.class);
    }
    
    /**
     * 
     */
    public static void main(String[] args) {
        TestRunner.run(suite());
    }
}
