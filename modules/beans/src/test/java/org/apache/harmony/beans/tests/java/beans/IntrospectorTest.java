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

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.beans.BeanDescriptor;
import java.beans.BeanInfo;
import java.beans.EventSetDescriptor;
import java.beans.IndexedPropertyDescriptor;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.MethodDescriptor;
import java.beans.PropertyChangeListener;
import java.beans.PropertyDescriptor;
import java.beans.SimpleBeanInfo;
import java.io.Serializable;
import java.lang.reflect.Method;
import java.security.Permission;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.EventListener;
import java.util.EventObject;
import java.util.PropertyPermission;
import java.util.TooManyListenersException;

import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.apache.harmony.beans.tests.support.ChildBean;
import org.apache.harmony.beans.tests.support.GrannyBean;
import org.apache.harmony.beans.tests.support.MisprintBean;
import org.apache.harmony.beans.tests.support.OtherBean;
import org.apache.harmony.beans.tests.support.SampleBean;
import org.apache.harmony.beans.tests.support.StandardBean2;
import org.apache.harmony.beans.tests.support.mock.FakeFox;
import org.apache.harmony.beans.tests.support.mock.FakeFox01;
import org.apache.harmony.beans.tests.support.mock.FakeFox011;
import org.apache.harmony.beans.tests.support.mock.FakeFox01BeanInfo;
import org.apache.harmony.beans.tests.support.mock.FakeFox02;
import org.apache.harmony.beans.tests.support.mock.FakeFox031;
import org.apache.harmony.beans.tests.support.mock.FakeFox041;
import org.apache.harmony.beans.tests.support.mock.FakeFox0411;
import org.apache.harmony.beans.tests.support.mock.MockButton;
import org.apache.harmony.beans.tests.support.mock.MockFoo;
import org.apache.harmony.beans.tests.support.mock.MockFooButton;
import org.apache.harmony.beans.tests.support.mock.MockFooLabel;
import org.apache.harmony.beans.tests.support.mock.MockFooStop;
import org.apache.harmony.beans.tests.support.mock.MockFooSub;
import org.apache.harmony.beans.tests.support.mock.MockFooSubSub;
import org.apache.harmony.beans.tests.support.mock.MockJavaBean;
import org.apache.harmony.beans.tests.support.mock.MockNullSubClass;
import org.apache.harmony.beans.tests.support.mock.MockSubClass;

/**
 * Unit test for Introspector.
 */
public class IntrospectorTest extends TestCase {

    private String[] defaultPackage;
    
    public IntrospectorTest(String str) {
        super(str);
    }
    
    public IntrospectorTest() {}

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        defaultPackage = Introspector.getBeanInfoSearchPath();
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
        Introspector.flushCaches();
        Introspector.setBeanInfoSearchPath(defaultPackage);
    }

    /**
     * The test checks the constructor is private
     */
    public void testIntrospectorConstructor() {
        try {
            Class.forName("java.beans.Introspector").newInstance();
            fail("No exception is thrown on new Introspector() call");
        } catch (Exception e) {
        }
    }

    /**
     * The test checks the getBeanDescriptor method
     */
    public void testBeanDescriptor() throws Exception {
        String[] oldBeanInfoSearchPath = Introspector.getBeanInfoSearchPath();
        try {
            Introspector
                    .setBeanInfoSearchPath(new String[] { "java.beans.infos" });
            BeanInfo info = Introspector.getBeanInfo(SampleBean.class);
            assertNotNull(info);
            BeanDescriptor descriptor = info.getBeanDescriptor();
            assertNotNull(descriptor);
            assertEquals(SampleBean.class, descriptor.getBeanClass());
        } finally {
            Introspector.setBeanInfoSearchPath(oldBeanInfoSearchPath);
        }
    }

    /**
     * The test checks the getMethodDescriptor method
     * 
     * @throws IntrospectionException
     */
    public void testNonUniqueByNameMethodDescriptors()
            throws IntrospectionException {
        BeanInfo info = Introspector.getBeanInfo(ChildBean.class);
        assertNotNull(info);
        MethodDescriptor[] mds = info.getMethodDescriptors();
        assertNotNull(mds);
        assertEquals(11, mds.length);
    }

    /**
     * The test checks the getEventSetDescriptors method
     * 
     * @throws IntrospectionException
     */
    public void testUnicastEventSetDescriptor() throws IntrospectionException {
        BeanInfo info = Introspector.getBeanInfo(SampleBean.class);
        assertNotNull(info);
        EventSetDescriptor[] descriptors = info.getEventSetDescriptors();
        assertNotNull(descriptors);
        for (EventSetDescriptor descriptor : descriptors) {
            Method m = descriptor.getAddListenerMethod();
            if (m != null) {
                Class<?>[] exceptionTypes = m.getExceptionTypes();
                boolean found = false;

                for (Class<?> et : exceptionTypes) {
                    if (et
                            .equals(TooManyListenersException.class)) {
                        assertTrue(descriptor.isUnicast());
                        found = true;
                        break;
                    }
                }

                if (!found) {
                    assertFalse(descriptor.isUnicast());
                }
            }
        }
    }

    /**
     * The test checks the getEventSetDescriptors method
     * 
     * @throws IntrospectionException
     */
    public void testEventSetDescriptorWithoutAddListenerMethod()
            throws IntrospectionException {
        BeanInfo info = Introspector.getBeanInfo(OtherBean.class);
        EventSetDescriptor[] descriptors;

        assertNotNull(info);
        descriptors = info.getEventSetDescriptors();
        assertNotNull(descriptors);
        assertEquals(1, descriptors.length);
        assertTrue(contains("sample", descriptors));
    }

    /**
     * The test checks the getEventSetDescriptors method
     * 
     * @throws IntrospectionException
     */
    public void testIllegalEventSetDescriptor() throws IntrospectionException {
        BeanInfo info = Introspector.getBeanInfo(MisprintBean.class);
        assertNotNull(info);
        EventSetDescriptor[] descriptors = info.getEventSetDescriptors();
        assertNotNull(descriptors);
        assertEquals(0, descriptors.length);
    }

    /**
     * The test checks the getPropertyDescriptors method
     * 
     * @throws IntrospectionException
     */
    public void testPropertyDescriptorWithSetMethod()
            throws IntrospectionException {
        BeanInfo info = Introspector.getBeanInfo(OtherBean.class);
        assertNotNull(info);
        PropertyDescriptor[] descriptors = info.getPropertyDescriptors();
        assertNotNull(descriptors);
        assertEquals(2, descriptors.length);
        assertEquals("class", descriptors[0].getName());
        assertEquals("number", descriptors[1].getName());
    }

    /**
     * The test checks the getPropertyDescriptors method
     * 
     * @throws IntrospectionException
     */
    public void testUseAllBeanInfo() throws IntrospectionException {
        BeanInfo info = Introspector.getBeanInfo(ChildBean.class,
                Introspector.USE_ALL_BEANINFO);
        PropertyDescriptor[] pds = info.getPropertyDescriptors();
        assertEquals(1, pds.length);
        assertEquals("childText", pds[0].getName());
    }

    public void testGetBeanInfo_NPE() throws IntrospectionException {
        // Regression for HARMONY-257
        try {
            Introspector.getBeanInfo((java.lang.Class) null);
            fail("getBeanInfo should throw NullPointerException");
        } catch (NullPointerException e) {
        }

        try {
            Introspector.getBeanInfo((java.lang.Class) null,
                    (java.lang.Class) null);
            fail("getBeanInfo should throw NullPointerException");
        } catch (NullPointerException e) {
        }

        try {
            Introspector.getBeanInfo((java.lang.Class) null, 0);
            fail("getBeanInfo should throw NullPointerException");
        } catch (NullPointerException e) {
        }
    }

    /**
     * The test checks the getPropertyDescriptors method for
     * IGNORE_IMMEDIATE_BEANINFO flag
     * 
     * @throws IntrospectionException
     */
    public void testIgnoreImmediateBeanInfo() throws IntrospectionException {
        BeanInfo info = Introspector.getBeanInfo(ChildBean.class,
                Introspector.IGNORE_IMMEDIATE_BEANINFO);
        PropertyDescriptor[] pds = info.getPropertyDescriptors();
        assertEquals(1, pds.length);
        assertEquals("parentText", pds[0].getName());
    }

    /**
     * The test checks the getPropertyDescriptors method for IGNORE_ALL_BEANINFO
     * flag
     * 
     * @throws IntrospectionException
     */
    public void testIgnoreAllBeanInfo() throws IntrospectionException {
        BeanInfo info = Introspector.getBeanInfo(ChildBean.class,
                Introspector.IGNORE_ALL_BEANINFO);
        PropertyDescriptor[] pds = info.getPropertyDescriptors();
        assertEquals(2, pds.length);
        assertEquals("class", pds[0].getName());
        assertEquals("text", pds[1].getName());
    }

    /**
     * The test checks the getPropertyDescriptors method
     * 
     * @throws IntrospectionException
     */
    public void testAdditionalBeanInfo() throws IntrospectionException {
        BeanInfo info = Introspector.getBeanInfo(StandardBean2.class);
        assertNull(info.getAdditionalBeanInfo());
        PropertyDescriptor[] pds = info.getPropertyDescriptors();
        assertEquals(2, pds.length);
        assertEquals("class", pds[0].getName());
    }

    /**
     * The test checks the getPropertyDescriptors for stop class prune
     * 
     * @throws IntrospectionException
     */
    public void testStopClass() throws IntrospectionException {
        BeanInfo info = Introspector.getBeanInfo(ChildBean.class,
                GrannyBean.class);
        PropertyDescriptor[] pds = info.getPropertyDescriptors();
        assertEquals(1, pds.length);
        assertEquals("childText", pds[0].getName());
    }

    /*
     * Common
     */
    public void testDecapitalize() {
        assertEquals("fooBah", Introspector.decapitalize("FooBah"));
        assertEquals("fooBah", Introspector.decapitalize("fooBah"));
        assertEquals("x", Introspector.decapitalize("X"));
        assertNull(Introspector.decapitalize(null));
        assertEquals("", Introspector.decapitalize(""));
        assertEquals("a1", Introspector.decapitalize("A1"));
    }

    public void testFlushCaches() throws IntrospectionException {
        BeanInfo info = Introspector.getBeanInfo(MockJavaBean.class);
        BeanDescriptor beanDesc = new BeanDescriptor(MockJavaBean.class);
        assertEquals(beanDesc.getName(), info.getBeanDescriptor().getName());
        assertEquals(beanDesc.isExpert(), info.getBeanDescriptor().isExpert());

        Introspector.flushCaches();
        BeanInfo cacheInfo = Introspector.getBeanInfo(MockJavaBean.class);
        assertNotSame(info, cacheInfo);
        beanDesc = new BeanDescriptor(MockJavaBean.class);
        assertEquals(beanDesc.getName(), info.getBeanDescriptor().getName());
        assertEquals(beanDesc.isExpert(), info.getBeanDescriptor().isExpert());
    }

    public void testFlushFromCaches() throws IntrospectionException {
        BeanInfo info = Introspector.getBeanInfo(MockFooSubSub.class);
        BeanInfo info2 = Introspector.getBeanInfo(MockFooSubSub.class);
        assertSame(info, info2);
        Introspector.flushFromCaches(MockFooSubSub.class);
        BeanInfo info3 = Introspector.getBeanInfo(MockFooSubSub.class);
        assertNotSame(info, info3);
    }

    public void testFlushFromCaches_Null() throws IntrospectionException {
        BeanInfo info = Introspector.getBeanInfo(MockJavaBean.class);
        BeanDescriptor beanDesc = new BeanDescriptor(MockJavaBean.class);
        assertEquals(beanDesc.getName(), info.getBeanDescriptor().getName());
        assertEquals(beanDesc.isExpert(), info.getBeanDescriptor().isExpert());
        try {
            Introspector.flushFromCaches(null);
            fail("Should throw NullPointerException.");
        } catch (NullPointerException e) {
        }
    }

    /*
     * Class under test for BeanInfo getBeanInfo(Class) No XXXXBeanInfo + test
     * cache info
     */
    public void testGetBeanInfoClass_no_BeanInfo()
            throws IntrospectionException {
        Class<FakeFox> beanClass = FakeFox.class;
        BeanInfo info = Introspector.getBeanInfo(beanClass);
        assertNull(info.getAdditionalBeanInfo());
        BeanDescriptor beanDesc = info.getBeanDescriptor();
        assertEquals("FakeFox", beanDesc.getName());
        assertEquals(0, info.getEventSetDescriptors().length);
        assertEquals(-1, info.getDefaultEventIndex());
        assertEquals(-1, info.getDefaultPropertyIndex());

        MethodDescriptor[] methodDesc = info.getMethodDescriptors();
        Method[] methods = beanClass.getMethods();
        assertEquals(methods.length, methodDesc.length);
        ArrayList<Method> methodList = new ArrayList<Method>();

        for (Method element : methods) {
            methodList.add(element);
        }

        for (MethodDescriptor element : methodDesc) {
            assertTrue(methodList.contains(element.getMethod()));
        }

        PropertyDescriptor[] propertyDesc = info.getPropertyDescriptors();
        assertEquals(1, propertyDesc.length);
        for (PropertyDescriptor element : propertyDesc) {
            if (element.getName().equals("class")) {
                assertNull(element.getWriteMethod());
                assertNotNull(element.getReadMethod());
            }
        }

        BeanInfo cacheInfo = Introspector.getBeanInfo(FakeFox.class);
        assertSame(info, cacheInfo);
    }

    /*
     * There is a BeanInfo class + test cache info
     */
    public void testGetBeanInfoClass_HaveBeanInfo()
            throws IntrospectionException {
        Class<FakeFox01> beanClass = FakeFox01.class;
        BeanInfo info = Introspector.getBeanInfo(beanClass);
        // printInfo(info);

        BeanInfo beanInfo = new FakeFox01BeanInfo();

        assertBeanInfoEquals(beanInfo, info);
        assertEquals(-1, info.getDefaultEventIndex());
        assertEquals(0, info.getDefaultPropertyIndex());

        BeanInfo cacheInfo = Introspector.getBeanInfo(beanClass);
        assertSame(info, cacheInfo);
    }

    public void testGetBeanInfoClass_ClassNull() throws IntrospectionException {
        try {
            Introspector.getBeanInfo(null);
            fail("Should throw NullPointerException.");
        } catch (NullPointerException e) {
        }
    }

    /*
     * Class under test for BeanInfo getBeanInfo(Class, Class)
     */
    public void testGetBeanInfoClassClass_Property()
            throws IntrospectionException {
        BeanInfo info = Introspector.getBeanInfo(MockFoo.class,
                MockFooStop.class);
        PropertyDescriptor[] pds = info.getPropertyDescriptors();

        assertEquals(2, pds.length);
        assertTrue(contains("name", String.class, pds));
        assertTrue(contains("complexLabel", MockFooLabel.class, pds));
    }

    public void testGetBeanInfoClassClass_Method()
            throws IntrospectionException {
        BeanInfo info = Introspector.getBeanInfo(MockFoo.class,
                MockFooStop.class);
        MethodDescriptor[] mds = info.getMethodDescriptors();
        
        assertEquals(4, mds.length);
        assertTrue(contains("getName", mds));
        assertTrue(contains("setName", mds));
        assertTrue(contains("getComplexLabel", mds));
        assertTrue(contains("setComplexLabel", mds));
        try {
            Introspector.getBeanInfo(MockFoo.class, Serializable.class);
            fail("Shoule throw exception, stopclass must be superclass of given bean");
        } catch (IntrospectionException e) {
        }
    }

    public void testGetBeanInfoClassClass_StopNull()
            throws IntrospectionException {
        BeanInfo info = Introspector.getBeanInfo(MockFoo.class);// , null);
        PropertyDescriptor[] pds = info.getPropertyDescriptors();
        boolean name = false;
        boolean label = false;
        for (PropertyDescriptor element : pds) {
            if (element.getName().equals("name")) {
                name = true;
            }
            if (element.getName().equals("label")) {
                label = true;
            }
        }

        assertTrue(name);
        assertTrue(label);
    }

    public void testGetBeanInfoClassClass_ClassNull()
            throws IntrospectionException {
        try {
            Introspector.getBeanInfo(null, MockFooStop.class);
            fail("Should throw NullPointerException.");
        } catch (NullPointerException e) {
        }
    }

    /*
     * StopClass is not a supper class of the bean.
     */
    public void testGetBeanInfoClassClass_ClassInvalid()
            throws IntrospectionException {
        try {
            Introspector.getBeanInfo(MockButton.class, MockFooStop.class);
            fail("Should throw IntrospectionException.");
        } catch (IntrospectionException e) {
        }
    }

    /*
     * Class under test for BeanInfo getBeanInfo(Class, int)
     */
    public void testGetBeanInfoClassint_UseAll_Property()
            throws IntrospectionException {
        BeanInfo info = Introspector.getBeanInfo(MockFooSubSub.class,
                Introspector.USE_ALL_BEANINFO);
        PropertyDescriptor[] pds = info.getPropertyDescriptors();
        int parentProperty = 0;
        for (PropertyDescriptor element : pds) {
            String name = element.getName();
            if (name.startsWith("text")) {
                parentProperty++;
                assertEquals("text.MockFooSubBeanInfo", name);
            }
        }
        assertEquals(1, parentProperty);
    }

    public void testGetBeanInfoClassint_UseAll_Method()
            throws IntrospectionException {
        BeanInfo info = Introspector.getBeanInfo(MockFooSubSub.class,
                Introspector.USE_ALL_BEANINFO);
        MethodDescriptor[] mds = info.getMethodDescriptors();
        int parentMethodGet = 0;
        int parentMethodSet = 0;
        for (MethodDescriptor element : mds) {
            String name = element.getName();
            if (name.startsWith("getText")) {
                parentMethodGet++;
                assertEquals("getText.MockFooSubBeanInfo", name);
            }
            if (name.startsWith("setText")) {
                parentMethodSet++;
                assertEquals("setText.MockFooSubBeanInfo", name);
            }
        }

        assertEquals(1, parentMethodGet);
        assertEquals(1, parentMethodSet);
    }

    public void testGetBeanInfoClassint_UseAll_Event()
            throws IntrospectionException {
        BeanInfo info = Introspector.getBeanInfo(MockFooSubSub.class,
                Introspector.USE_ALL_BEANINFO);
        EventSetDescriptor[] esds = info.getEventSetDescriptors();
        assertEquals(1, esds.length);
        for (EventSetDescriptor element : esds) {
            String name = element.getName();
            assertEquals("mockPropertyChange.MockFooSubBeanInfo", name);
        }
    }

    /*
     * FLAG=IGNORE_IMMEDIATE;
     */
    public void testGetBeanInfoClassint_IGNORE_IMMEDIATE_Property()
            throws IntrospectionException {
        BeanInfo info = Introspector.getBeanInfo(MockFooSub.class,
                Introspector.IGNORE_IMMEDIATE_BEANINFO);
        PropertyDescriptor[] pds = info.getPropertyDescriptors();
        int fromParent = 0;
        for (PropertyDescriptor element : pds) {
            String name = element.getName();
            if (name.startsWith("childName")) {
                fromParent++;
                assertEquals("childName.MockFooChildBeanInfo", name);
            }
        }
        assertEquals(1, fromParent);
    }

    /*
     * FLAG=IGNORE_IMMEDIATE;
     */
    public void testGetBeanInfoClassint_IGNORE_IMMEDIATE_Method()
            throws IntrospectionException {
        BeanInfo info = Introspector.getBeanInfo(MockFooSub.class,
                Introspector.IGNORE_IMMEDIATE_BEANINFO);
        MethodDescriptor[] mds = info.getMethodDescriptors();
        int parentMethodGet = 0;
        int parentMethodSet = 0;
        for (MethodDescriptor element : mds) {
            String name = element.getName();
            if (name.startsWith("getChildName")) {
                parentMethodGet++;
                assertEquals("getChildName.MockFooChildBeanInfo", name);
            }
            if (name.startsWith("setChildName")) {
                parentMethodSet++;
                assertEquals("setChildName.MockFooChildBeanInfo", name);
            }
        }

        assertEquals(1, parentMethodGet);
        assertEquals(1, parentMethodSet);
    }

    /*
     * FLAG=IGNORE_IMMEDIATE;
     */
    public void testGetBeanInfoClassint_IGNORE_IMMEDIATE_Event()
            throws IntrospectionException {
        BeanInfo info = Introspector.getBeanInfo(MockFooSub.class,
                Introspector.IGNORE_IMMEDIATE_BEANINFO);
        EventSetDescriptor[] esds = info.getEventSetDescriptors();

        assertEquals(2, esds.length);
        assertTrue(contains("mockPropertyChange", esds));
        assertTrue(contains("mockPropertyChange.MockFooChildBeanInfo", esds));
    }

    /*
     * FLAG=IGNORE_ALL_BEANINFO;
     */
    public void testGetBeanInfoClassint_IGNORE_ALL_Property()
            throws IntrospectionException {
        BeanInfo info = Introspector.getBeanInfo(MockFooSub.class,
                Introspector.IGNORE_ALL_BEANINFO);
        PropertyDescriptor[] pds = info.getPropertyDescriptors();
        int text = 0;
        for (PropertyDescriptor element : pds) {
            String name = element.getName();
            if (name.startsWith("text")) {
                text++;
                assertEquals("text", name);
            }
        }
        assertEquals(1, text);
    }

    /*
     * FLAG=IGNORE_ALL_BEANINFO;
     */
    public void testGetBeanInfoClassint_IGNORE_ALL_Method()
            throws IntrospectionException {
        BeanInfo info = Introspector.getBeanInfo(MockFooSub.class,
                Introspector.IGNORE_ALL_BEANINFO);
        MethodDescriptor[] mds = info.getMethodDescriptors();
        int getMethod = 0;
        int setMethod = 0;
        for (MethodDescriptor element : mds) {
            String name = element.getName();
            if (name.startsWith("getText")) {
                getMethod++;
                assertEquals("getText", name);
            }
            if (name.startsWith("setText")) {
                setMethod++;
                assertEquals("setText", name);
            }
        }

        assertEquals(1, getMethod);
        assertEquals(1, setMethod);
    }

    /*
     * FLAG=IGNORE_ALL_BEANINFO;
     */
    public void testGetBeanInfoClassint_IGNORE_ALL_Event()
            throws IntrospectionException {
        BeanInfo info = Introspector.getBeanInfo(MockFooSub.class,
                Introspector.IGNORE_ALL_BEANINFO);
        EventSetDescriptor[] esds = info.getEventSetDescriptors();

        assertEquals(1, esds.length);
        assertTrue(contains("mockPropertyChange", esds));
    }

    /*
     * FLAG invalid;
     */
    public void testGetBeanInfoClassint_FLAG_Invalid()
            throws IntrospectionException {
        BeanInfo info = Introspector.getBeanInfo(MockFooSub.class, -1);
        PropertyDescriptor[] pds = info.getPropertyDescriptors();

        Introspector.getBeanInfo(MockFooSub.class,
                Introspector.IGNORE_ALL_BEANINFO);
        PropertyDescriptor[] pds2 = info.getPropertyDescriptors();
        assertEquals(pds.length, pds2.length);
        for (int i = 0; i < pds.length; i++) {
            assertEquals(pds[i], pds2[i]);
        }
    }
    
    public void testSetBeanInfoSearchPath_null() throws IntrospectionException{
        String[] oldPath = Introspector.getBeanInfoSearchPath();
        try{
            Introspector.setBeanInfoSearchPath(null);
            try{
                Introspector.getBeanInfoSearchPath();
                fail("should throw NPE");
            }catch(NullPointerException e){
            }
            String[] newPath = new String[]{"mock", null, ""};
            Introspector.setBeanInfoSearchPath(newPath);
            Introspector.getBeanInfo(this.getClass());
        }finally{
            Introspector.setBeanInfoSearchPath(oldPath);
        }
    }

    public void testGetBeanInfoSearchPath() {
        String[] path = Introspector.getBeanInfoSearchPath();
        assertEquals(1, path.length);
        assertTrue(path[0].endsWith("beans.infos"));
    }

    public void testGetBeanInfoSearchPath_Default()
            throws IntrospectionException, ClassNotFoundException {
        BeanInfo info = Introspector.getBeanInfo(MockFooButton.class);
        PropertyDescriptor[] pds = info.getPropertyDescriptors();
        BeanDescriptor beanDesc;

        assertEquals(2, pds.length);
        assertEquals("class", pds[0].getName());

        beanDesc = info.getBeanDescriptor();
        assertEquals("MockFooButton", beanDesc.getName());
    }

    public void testSetBeanInfoSearchPath() throws IntrospectionException {
        String[] oldPath = Introspector.getBeanInfoSearchPath();
        String[] newPath = new String[] { "org.apache.harmony.beans.tests.java.beans", };
        Introspector.setBeanInfoSearchPath(newPath);

        BeanInfo info = Introspector.getBeanInfo(MockFooLabel.class);
        PropertyDescriptor[] pds = info.getPropertyDescriptors();
        for (PropertyDescriptor element : pds) {
            String name = element.getName();
            assertEquals("text.MockFooLabelBeanInfo", name);
        }

        String[] path = Introspector.getBeanInfoSearchPath();
        for (int i = 0; i < newPath.length; i++) {
            assertEquals(newPath[i], path[i]);
        }
        Introspector.setBeanInfoSearchPath(oldPath);
    }

    /*
     * Sub is PropertyDescriptor, Super is null
     */
    public void testIntrospection_1() throws IntrospectionException {
        Class<FakeFox101> beanClass = FakeFox101.class;
        BeanInfo info = Introspector.getBeanInfo(beanClass);

        assertEquals(0, info.getEventSetDescriptors().length);
        assertEquals(11, info.getMethodDescriptors().length);

        PropertyDescriptor[] propertyDesc = info.getPropertyDescriptors();
        assertEquals(2, propertyDesc.length);

        for (PropertyDescriptor element : propertyDesc) {
            if (element.getName().equals("class")) {
                assertNull(element.getWriteMethod());
                assertNotNull(element.getReadMethod());
            } else {
                assertEquals("fox101", element.getName());
                assertEquals("setFox101", element.getWriteMethod()
                        .getName());
                assertEquals("getFox101", element.getReadMethod()
                        .getName());
            }
        }
    }

    /*
     * Sub is PropertyDescriptor, Super is PropertyDescriptor get in sub, set in
     * super
     */
    public void testIntrospection_2() throws IntrospectionException {
        Class<FakeFox201> beanClass = FakeFox201.class;
        BeanInfo info = Introspector.getBeanInfo(beanClass);

        assertEquals(0, info.getEventSetDescriptors().length);
        assertEquals(11, info.getMethodDescriptors().length);

        PropertyDescriptor[] propertyDesc = info.getPropertyDescriptors();
        assertEquals(2, propertyDesc.length);

        for (PropertyDescriptor element : propertyDesc) {
            if (element.getName().equals("class")) {
                assertNull(element.getWriteMethod());
                assertNotNull(element.getReadMethod());
            } else {
                assertEquals("fox201", element.getName());
                assertEquals(String.class.getName(), element
                        .getPropertyType().getName());
                assertNotNull(element.getWriteMethod().getName());
                assertNotNull(element.getReadMethod().getName());
            }
        }
    }

    /*
     * Sub is PropertyDescriptor, Super is PropertyDescriptor Sub get ->
     * Integer, super set->String
     */
    public void testIntrospection_3() throws IntrospectionException {
        Class<FakeFox202> beanClass = FakeFox202.class;
        BeanInfo info = Introspector.getBeanInfo(beanClass);

        assertEquals(0, info.getEventSetDescriptors().length);
        assertEquals(11, info.getMethodDescriptors().length);

        PropertyDescriptor[] propertyDesc = info.getPropertyDescriptors();
        assertEquals(2, propertyDesc.length);

        for (PropertyDescriptor element : propertyDesc) {
            if (element.getName().equals("class")) {
                assertNull(element.getWriteMethod());
                assertNotNull(element.getReadMethod());
            } else {
                assertEquals("fox201", element.getName());
                assertEquals(Integer.class.getName(), element
                        .getPropertyType().getName());
                assertNull(element.getWriteMethod());
                assertNotNull(element.getReadMethod());
            }
        }
    }

    /*
     * Sub is PropertyDescriptor, Super is PropertyDescriptor Sub set->String,
     * super get -> Integer
     */
    public void testIntrospection_4() throws IntrospectionException {
        // XXX will fail on RI, see HARMONY-2289
        Class<FakeFox301> beanClass = FakeFox301.class;
        BeanInfo info = Introspector.getBeanInfo(beanClass);

        assertEquals(0, info.getEventSetDescriptors().length);
        assertEquals(11, info.getMethodDescriptors().length);

        PropertyDescriptor[] propertyDesc = info.getPropertyDescriptors();
        assertEquals(2, propertyDesc.length);

        for (PropertyDescriptor element : propertyDesc) {
            if (element.getName().equals("class")) {
                assertNull(element.getWriteMethod());
                assertNotNull(element.getReadMethod());
            } else {
                assertEquals("fox301", element.getName());
                assertEquals(Integer.class.getName(), element
                        .getPropertyType().getName());
                assertNull(element.getWriteMethod());
                assertNotNull(element.getReadMethod());
            }
        }
    }

    /*
     * Sub PropertyDescriptor, super IndexedPropertyDescriptor
     */
    public void testIntrospection_5() throws IntrospectionException {
        Class<FakeFox401> beanClass = FakeFox401.class;
        BeanInfo info = Introspector.getBeanInfo(beanClass);
        PropertyDescriptor[] pds;

        
        assertEquals(0, info.getEventSetDescriptors().length);
        assertEquals(13, info.getMethodDescriptors().length);

        pds = info.getPropertyDescriptors();
        assertEquals(2, pds.length);

        for (PropertyDescriptor element : pds) {
            if (element.getName().equals("class")) {
                assertNull(element.getWriteMethod());
                assertNotNull(element.getReadMethod());
            } else {
                IndexedPropertyDescriptor indexedDesc =
                        (IndexedPropertyDescriptor) element;
                
                assertEquals("fox401", element.getName());
                assertEquals(String[].class.getName(), element
                        .getPropertyType().getName());
                assertEquals(String.class.getName(), indexedDesc
                        .getIndexedPropertyType().getName());
                assertNotNull(element.getWriteMethod());
                assertNotNull(element.getReadMethod());
                assertNotNull(indexedDesc.getIndexedReadMethod());
                assertNotNull(indexedDesc.getIndexedWriteMethod());
            }
        }
    }

    /*
     * Sub PropertyDescriptor, super IndexedPropertyDescriptor Get/Set is
     * mismatch
     */
    public void testIntrospection_6() throws IntrospectionException {
        Class<FakeFox402> beanClass = FakeFox402.class;
        BeanInfo info = Introspector.getBeanInfo(beanClass);
        assertEquals(0, info.getEventSetDescriptors().length);
        assertEquals(13, info.getMethodDescriptors().length);
        PropertyDescriptor[] propertyDesc = info.getPropertyDescriptors();
        assertEquals(2, propertyDesc.length);

        for (PropertyDescriptor element : propertyDesc) {
            if (element.getName().equals("class")) {
                assertNull(element.getWriteMethod());
                assertNotNull(element.getReadMethod());
            } else {
                assertEquals("fox401", element.getName());
                assertEquals(Integer[].class.getName(), element
                        .getPropertyType().getName());
                assertNull(element.getWriteMethod());
                assertNotNull(element.getReadMethod());
            }
        }
    }

    /*
     * Sub is IndexedPropertyDescriptor, Super is PropertyDescriptor sub set,
     * indexed get. Super get
     */
    public void testIntrospection_7() throws IntrospectionException {
        Class<FakeFox501> beanClass = FakeFox501.class;
        BeanInfo info = Introspector.getBeanInfo(beanClass);
        PropertyDescriptor[] propertyDesc;
        
        assertEquals(0, info.getEventSetDescriptors().length);
        assertEquals(12, info.getMethodDescriptors().length);

        propertyDesc = info.getPropertyDescriptors();
        assertEquals(2, propertyDesc.length);

        for (PropertyDescriptor element : propertyDesc) {
            if (element.getName().equals("class")) {
                assertNull(element.getWriteMethod());
                assertNotNull(element.getReadMethod());
            } else {
                IndexedPropertyDescriptor indexedDesc =
                        (IndexedPropertyDescriptor) element;
                
                assertEquals("fox501", element.getName());
                assertEquals(String[].class.getName(), element
                        .getPropertyType().getName());
                assertNotNull(element.getWriteMethod());
                assertNotNull(element.getReadMethod());
                assertNotNull(indexedDesc.getIndexedReadMethod());
                assertNull(indexedDesc.getIndexedWriteMethod());
                assertEquals(String.class.getName(), indexedDesc
                        .getIndexedPropertyType().getName());
            }
        }
    }

    /*
     * Sub is IndexedPropertyDescriptor, Super is PropertyDescriptor sub indexed
     * set. Super get
     */
    public void testIntrospection_8() throws IntrospectionException {
        Class<FakeFox502> beanClass = FakeFox502.class;
        BeanInfo info = Introspector.getBeanInfo(beanClass);
        assertEquals(0, info.getEventSetDescriptors().length);
        assertEquals(11, info.getMethodDescriptors().length);

        PropertyDescriptor[] propertyDesc = info.getPropertyDescriptors();
        assertEquals(2, propertyDesc.length);

        for (PropertyDescriptor element : propertyDesc) {
            if (element.getName().equals("class")) {
                assertNull(element.getWriteMethod());
                assertNotNull(element.getReadMethod());
            } else {
                IndexedPropertyDescriptor indexedDesc = (IndexedPropertyDescriptor) element;
                assertEquals("fox501", element.getName());
                assertEquals(String[].class.getName(), element
                        .getPropertyType().getName());
                assertNull(element.getWriteMethod());
                assertNotNull(element.getReadMethod());
                assertNull(indexedDesc.getIndexedReadMethod());
                assertNotNull(indexedDesc.getIndexedWriteMethod());
                assertEquals(String.class.getName(), indexedDesc
                        .getIndexedPropertyType().getName());
            }
        }
    }

    /*
     * Sub is IndexedPropertyDescriptor, Super is PropertyDescriptor sub indexed
     * set. Super get. Type is different
     */
    public void testIntrospection_9() throws IntrospectionException {
        Class<FakeFox503> beanClass = FakeFox503.class;
        BeanInfo info = Introspector.getBeanInfo(beanClass);
        assertEquals(0, info.getEventSetDescriptors().length);
        assertEquals(11, info.getMethodDescriptors().length);

        PropertyDescriptor[] propertyDesc = info.getPropertyDescriptors();
        assertEquals(2, propertyDesc.length);

        for (PropertyDescriptor element : propertyDesc) {
            if (element.getName().equals("class")) {
                assertNull(element.getWriteMethod());
                assertNotNull(element.getReadMethod());
            } else {
                IndexedPropertyDescriptor indexedDesc = (IndexedPropertyDescriptor) element;
                assertEquals("fox501", element.getName());
                assertNull(element.getPropertyType());
                assertNull(element.getWriteMethod());
                assertNull(element.getReadMethod());
                assertNull(indexedDesc.getIndexedReadMethod());
                assertNotNull(indexedDesc.getIndexedWriteMethod());
                assertEquals(Integer.class.getName(), indexedDesc
                        .getIndexedPropertyType().getName());
            }
        }
    }

    /*
     * Test introspect events, add/remove
     */
    public void testIntrospection_10() throws IntrospectionException {
        Class<FakeFox601> beanClass = FakeFox601.class;
        BeanInfo info = Introspector.getBeanInfo(beanClass);
        PropertyDescriptor[] propertyDesc;
        EventSetDescriptor eventDesc;
        
        assertEquals(1, info.getEventSetDescriptors().length);
        eventDesc = info.getEventSetDescriptors()[0];
        assertNotNull(eventDesc.getAddListenerMethod());
        assertNotNull(eventDesc.getRemoveListenerMethod());
        assertNull(eventDesc.getGetListenerMethod());
        assertEquals(1, eventDesc.getListenerMethods().length);

        assertEquals(11, info.getMethodDescriptors().length);

        propertyDesc = info.getPropertyDescriptors();
        assertEquals(1, propertyDesc.length);

        for (PropertyDescriptor element : propertyDesc) {
            if (element.getName().equals("class")) {
                assertNull(element.getWriteMethod());
                assertNotNull(element.getReadMethod());
            }
        }
    }

    /*
     * Test introspect events, add/remove/get
     */
    public void testIntrospection_11() throws IntrospectionException {
        Class<FakeFox602> beanClass = FakeFox602.class;
        BeanInfo info = Introspector.getBeanInfo(beanClass);
        EventSetDescriptor eventDesc;
        PropertyDescriptor[] propertyDesc;

        assertEquals(1, info.getEventSetDescriptors().length);
        eventDesc = info.getEventSetDescriptors()[0];
        assertFalse(eventDesc.isUnicast());
        assertNotNull(eventDesc.getAddListenerMethod());
        assertNotNull(eventDesc.getRemoveListenerMethod());
        assertNotNull(eventDesc.getGetListenerMethod());
        assertEquals(1, eventDesc.getListenerMethods().length);

        assertEquals(12, info.getMethodDescriptors().length);

        propertyDesc = info.getPropertyDescriptors();
        assertEquals(2, propertyDesc.length);

        for (PropertyDescriptor element : propertyDesc) {
            if (element.getName().equals("class")) {
                assertNull(element.getWriteMethod());
                assertNotNull(element.getReadMethod());
            }
        }
    }

    /*
     * Test introspect events, add
     */
    public void testIntrospection_12() throws IntrospectionException {
        Class<FakeFox603> beanClass = FakeFox603.class;
        BeanInfo info = Introspector.getBeanInfo(beanClass);
        assertEquals(0, info.getEventSetDescriptors().length);

        assertEquals(10, info.getMethodDescriptors().length);

        PropertyDescriptor[] propertyDesc = info.getPropertyDescriptors();
        assertEquals(1, propertyDesc.length);

        for (PropertyDescriptor element : propertyDesc) {
            if (element.getName().equals("class")) {
                assertNull(element.getWriteMethod());
                assertNotNull(element.getReadMethod());
            }
        }
    }

    /*
     * Test introspect events, add/remove Add throws TooManyListenersException
     */
    public void testIntrospection_13() throws IntrospectionException {
        Class<FakeFox604> beanClass = FakeFox604.class;
        BeanInfo info = Introspector.getBeanInfo(beanClass);
        assertEquals(1, info.getEventSetDescriptors().length);
        EventSetDescriptor eventDesc = info.getEventSetDescriptors()[0];
        assertTrue(eventDesc.isUnicast());
        assertNotNull(eventDesc.getAddListenerMethod());
        assertNotNull(eventDesc.getRemoveListenerMethod());
        assertNull(eventDesc.getGetListenerMethod());
        assertEquals(1, eventDesc.getListenerMethods().length);

        assertEquals(11, info.getMethodDescriptors().length);

        PropertyDescriptor[] propertyDesc = info.getPropertyDescriptors();
        assertEquals(1, propertyDesc.length);

        for (PropertyDescriptor element : propertyDesc) {
            if (element.getName().equals("class")) {
                assertNull(element.getWriteMethod());
                assertNotNull(element.getReadMethod());
            }
        }
    }

    // for test coverage
    public void testIntrospection_14() throws IntrospectionException {
        Class<FakeFox5001> beanClass = FakeFox5001.class;
        Introspector.getBeanInfo(beanClass);

        // Introspector in = new Introspector();
    }

    /*
     * Test Introspection with BeanInfo No immediate BeanInfo Have super
     * BeanInfo
     */
    public void testBeanInfo_1() throws IntrospectionException {
        Class<FakeFox011> beanClass = FakeFox011.class;
        BeanInfo info = Introspector.getBeanInfo(beanClass);
        assertNull(info.getAdditionalBeanInfo());
        BeanDescriptor beanDesc = info.getBeanDescriptor();
        assertEquals("FakeFox011", beanDesc.getName());
        assertEquals(0, info.getEventSetDescriptors().length);
        assertEquals(-1, info.getDefaultEventIndex());
        assertEquals(0, info.getDefaultPropertyIndex());

        MethodDescriptor[] methodDesc = info.getMethodDescriptors();

        assertEquals(4, methodDesc.length);

        PropertyDescriptor[] propertyDesc = info.getPropertyDescriptors();
        assertEquals(2, propertyDesc.length);
        for (PropertyDescriptor element : propertyDesc) {
            if (element.getName().equals("class")) {
                assertNull(element.getWriteMethod());
                assertNotNull(element.getReadMethod());
            }
        }
    }

    public void testBeanInfo_2() throws IntrospectionException {
        Class<FakeFox02> beanClass = FakeFox02.class;
        BeanInfo info = Introspector.getBeanInfo(beanClass);
        assertNull(info.getAdditionalBeanInfo());
        BeanDescriptor beanDesc = info.getBeanDescriptor();
        assertEquals("FakeFox02", beanDesc.getName());
        assertEquals(0, info.getEventSetDescriptors().length);
        assertEquals(-1, info.getDefaultEventIndex());
        assertEquals(-1, info.getDefaultPropertyIndex());

        MethodDescriptor[] methodDesc = info.getMethodDescriptors();

        assertEquals(11, methodDesc.length);

        PropertyDescriptor[] propertyDesc = info.getPropertyDescriptors();
        assertEquals(1, propertyDesc.length);
        for (PropertyDescriptor element : propertyDesc) {
            if (element.getName().equals("fox02")) {
                assertEquals("fox02.beaninfo", element.getDisplayName());
            }
        }
    }

    public void testPropertySort() throws IntrospectionException {
        Class<FakeFox70> beanClass = FakeFox70.class;
        BeanInfo info = Introspector.getBeanInfo(beanClass);
        PropertyDescriptor[] descs = info.getPropertyDescriptors();
        String[] names = { "a", "aaa", "bb", "bbb", "bc", "class", "ddd", "ff", };
        for (int i = 0; i < descs.length; i++) {
            assertEquals(names[i], descs[i].getName());
        }
    }

    public void testIntrospectProperties() throws IntrospectionException {
        Class<FakeFox80> beanClass = FakeFox80.class;
        BeanInfo info = Introspector.getBeanInfo(beanClass);
        assertEquals(2, info.getPropertyDescriptors().length);
    }

    public void testIntrospectProperties2() throws IntrospectionException {
        Class<FakeFox90> beanClass = FakeFox90.class;
        BeanInfo info = Introspector.getBeanInfo(beanClass);
        // printInfo(info);
        PropertyDescriptor[] pds = info.getPropertyDescriptors();
        assertEquals(2, pds.length);
        assertNull(pds[1].getReadMethod());
    }

    public void testExplicitBeanInfo() throws IntrospectionException {
        BeanInfo info = Introspector.getBeanInfo(MockFoo23.class);

        assertNull(info.getAdditionalBeanInfo());
        BeanDescriptor beanDesc = info.getBeanDescriptor();
        assertEquals("IntrospectorTest$MockFoo23", beanDesc.getName());
        assertEquals(0, info.getEventSetDescriptors().length);
        assertEquals(-1, info.getDefaultEventIndex());
        assertEquals(-1, info.getDefaultPropertyIndex());

        MethodDescriptor[] methodDesc = info.getMethodDescriptors();

        assertEquals(10, methodDesc.length);

        PropertyDescriptor[] propertyDesc = info.getPropertyDescriptors();
        assertEquals(1, propertyDesc.length);
        assertEquals("name.beanInfo", propertyDesc[0].getDisplayName());
        assertNotNull(propertyDesc[0].getReadMethod());
        assertNull(propertyDesc[0].getWriteMethod());
    }

    public void testExplicitBeanInfo2() throws IntrospectionException {
        BeanInfo info = Introspector.getBeanInfo(MockFox001.class);

        assertNull(info.getAdditionalBeanInfo());
        BeanDescriptor beanDesc = info.getBeanDescriptor();
        assertEquals("IntrospectorTest$MockFox001", beanDesc.getName());
        assertEquals(0, info.getEventSetDescriptors().length);
        assertEquals(-1, info.getDefaultEventIndex());
        assertEquals(-1, info.getDefaultPropertyIndex());

        MethodDescriptor[] methodDesc = info.getMethodDescriptors();

        assertEquals(1, methodDesc.length);

        PropertyDescriptor[] propertyDesc = info.getPropertyDescriptors();
        assertEquals(1, propertyDesc.length);
        assertEquals("name.beaninfo", propertyDesc[0].getDisplayName());
        assertNotNull(propertyDesc[0].getReadMethod());
        assertNull(propertyDesc[0].getWriteMethod());
    }

    public void testExplicitBeanInfo3() throws IntrospectionException {
        BeanInfo info = Introspector.getBeanInfo(MockFox011.class);
        // printInfo(info);

        assertNull(info.getAdditionalBeanInfo());
        BeanDescriptor beanDesc = info.getBeanDescriptor();
        assertEquals("IntrospectorTest$MockFox011", beanDesc.getName());
        assertEquals(0, info.getEventSetDescriptors().length);
        assertEquals(-1, info.getDefaultEventIndex());
        assertEquals(-1, info.getDefaultPropertyIndex());

        MethodDescriptor[] methodDesc = info.getMethodDescriptors();

        assertEquals(1, methodDesc.length);

        PropertyDescriptor[] propertyDesc = info.getPropertyDescriptors();
        assertEquals(3, propertyDesc.length);

        assertEquals("class", propertyDesc[0].getDisplayName());
        assertNotNull(propertyDesc[0].getReadMethod());
        assertNull(propertyDesc[0].getWriteMethod());

        assertEquals("label", propertyDesc[1].getDisplayName());
        assertNotNull(propertyDesc[1].getReadMethod());
        assertNull(propertyDesc[1].getWriteMethod());

        assertEquals("name", propertyDesc[2].getDisplayName());
        assertNotNull(propertyDesc[2].getReadMethod());
        assertNotNull(propertyDesc[2].getWriteMethod());
    }

    /*
     * If Bean1 has wrong getter method: public int getProp6(boolean i), then
     * Introspector.getBeanInfo(Bean1) throws java.beans.IntrospectionException.
     */
    public void testIntrospectorGetBeanInfo() throws IntrospectionException {
        Class<FakeFoxInfo> clazz = FakeFoxInfo.class;
        BeanInfo info = Introspector.getBeanInfo(clazz);
        // printInfo(info);
        PropertyDescriptor[] pds = info.getPropertyDescriptors();
        assertEquals("prop6", pds[1].getName());
        assertNull(pds[1].getReadMethod());
        assertNotNull(pds[1].getWriteMethod());
    }

    public void testGetBeanInfoComplexHierarchy() throws Exception {
        Introspector.flushCaches();
        BeanInfo subinfo = Introspector.getBeanInfo(MockSubClass.class);
        PropertyDescriptor[] allProps = subinfo.getPropertyDescriptors();
        boolean propFound = false;
        for (int i = 0; i < allProps.length; i++) {
            if (allProps[i].getName().equals("value")) {
                assertTrue(allProps[i].isExpert());
                assertTrue(allProps[i].isHidden());
                assertTrue(allProps[i].isBound());
                assertFalse(allProps[i].isConstrained());
                assertEquals("adddisplay", allProps[i].getDisplayName());
                assertEquals("subdesc", allProps[i].getShortDescription());
                propFound = true;
                break;
            }
        }
        assertTrue(propFound);

        boolean eventFound = false;
        EventSetDescriptor[] events = subinfo.getEventSetDescriptors();
        for (int i = 0; i < events.length; i++) {
            if (events[i].getName().equals("mockPropertyChange")) {
                assertTrue(events[i].isExpert());
                assertTrue(events[i].isHidden());
                assertFalse(events[i].isUnicast());
                assertFalse(events[i].isInDefaultEventSet());
                assertEquals("adddisplay", events[i].getDisplayName());
                assertEquals("subdesc", events[i].getShortDescription());
                eventFound = true;
                break;
            }
        }
        assertTrue(eventFound);
    }

    public void testGetBeanInfoExplicitNull() throws Exception {
        Introspector.flushCaches();
        BeanInfo subinfo = Introspector.getBeanInfo(MockNullSubClass.class);
        assertNotNull(subinfo.getPropertyDescriptors());
        assertNotNull(subinfo.getEventSetDescriptors());
        assertNotNull(subinfo.getMethodDescriptors());
        assertEquals(-1, subinfo.getDefaultEventIndex());
        assertEquals(-1, subinfo.getDefaultPropertyIndex());
    }

    static class FakeFoxInfo {

        public int getProp6(boolean i) {
            return 0;
        }

        public void setProp6(boolean i) {

        }
    }

    /*
     * setBeanInfoSearchPath method of Introspector doesn't invoke
     * checkPropertiesAccess method of SecurityManager class
     */
    public void testSetBeanInfoSearchPath2() {
        SecurityManager dfl = System.getSecurityManager();
        try {
            SecurityManager sm = new MockSecurity1();
            System.setSecurityManager(sm);
            assertSame(sm, System.getSecurityManager());
            try {
                // test here
                {
                    String[] newPath = new String[] { "a", "b" };
                    Introspector.setBeanInfoSearchPath(newPath);
                    String[] path = Introspector.getBeanInfoSearchPath();
                    assertTrue(Arrays.equals(newPath, path));
                    assertNotSame(newPath, path);
                    path[0] = "c";
                    newPath[0] = "d";
                    String[] path2 = Introspector.getBeanInfoSearchPath();
                    assertEquals("d", path2[0]);
                }
                {
                    String[] newPath = new String[] {};
                    Introspector.setBeanInfoSearchPath(newPath);
                    String[] path = Introspector.getBeanInfoSearchPath();
                    assertNotSame(newPath, path);
                    assertTrue(Arrays.equals(newPath, path));
                }
                {
                    String[] newPath = null;
                    Introspector.setBeanInfoSearchPath(newPath);
                    try {
                        Introspector.getBeanInfoSearchPath();
                        fail("Should throw NullPointerException.");
                    } catch (NullPointerException e) {
                    }
                }
            } catch (SecurityException e) {
            }
        } finally {
            try {
                System.setSecurityManager(dfl);
            } catch (Exception e) {
                System.out.println(e);
            }

        }

    }

    static class MockSecurity1 extends SecurityManager {

        @Override
        public void checkPermission(Permission p) {

        }
    }

    public void testSetBeanInfoSearchPath3() {
        SecurityManager dfl = System.getSecurityManager();
        try {
            SecurityManager sm = new MockSecurity2();
            System.setSecurityManager(sm);
            assertSame(sm, System.getSecurityManager());

            Introspector.getBeanInfoSearchPath();
            String[] newPath = new String[] { "a", "b" };
            try {
                // test here
                Introspector.setBeanInfoSearchPath(newPath);
                fail("Should throw SecurityException");
            } catch (SecurityException e) {
                // System.out.println(e);
            }
        } finally {
            try {
                System.setSecurityManager(dfl);
            } catch (Exception e) {
            }
        }
    }

    static class MockSecurity2 extends SecurityManager {

        @Override
        public void checkPermission(Permission p) {
            if (p instanceof PropertyPermission) {
                throw new SecurityException("Expected exception.");
            }
        }
    }

    /*
     * If Bean3 has empty BeanInfo, then
     * Introspector.getBeanInfo(Bean3.class).getBeanDescriptor() returns null.
     */
    public void testNullBeanDescriptor() throws IntrospectionException {
        Class<Bean3> clazz = Bean3.class;
        BeanInfo info = Introspector.getBeanInfo(clazz);
        // printInfo(info);
        assertNotNull(info.getBeanDescriptor());
    }

    public static class Bean3 {

        private String prop1;

        public String getProp1() {
            return prop1;
        }

        public void setProp1(String prop1) {
            this.prop1 = prop1;
        }
    }

    public static class Bean3BeanInfo extends SimpleBeanInfo {
    }

    public void testGetPropertyDescriptors_H1838()
            throws IntrospectionException {
        // Regression for HARMONY-1838
        PropertyDescriptor[] propertyDescriptors = Introspector.getBeanInfo(
                Bean.class).getPropertyDescriptors();

        assertEquals("class", propertyDescriptors[0].getName());
        assertEquals("prop1", propertyDescriptors[1].getName());
        assertEquals("prop2", propertyDescriptors[2].getName());
        assertEquals(3, propertyDescriptors.length);
    }

    public static class Bean {
        public String getProp1(int i) {
            return null;
        }

        public void setProp2(int i, String str) {
        }
    }

    /*
     * 
     */
    public void testGetPropertyDescriptors() throws IntrospectionException {
        Class<Bean2> clazz = Bean2.class;
        BeanInfo info = Introspector.getBeanInfo(clazz);
        PropertyDescriptor[] pds = info.getPropertyDescriptors();

        assertEquals(2, pds.length);
        assertEquals("property1", pds[0].getName());
        assertEquals("property8", pds[1].getName());
    }
    
    public void testHarmony4861() throws IntrospectionException {
		final PropertyDescriptor[] propertyDescriptors = Introspector
				.getBeanInfo(TestBean.class).getPropertyDescriptors();

		for (PropertyDescriptor d : propertyDescriptors) {
			if (d.getName().equals("prop1")) { //$NON-NLS-1$
				assertEquals("isProp1", d.getReadMethod().getName()); //$NON-NLS-1$
				return;
			}
		}
	}

	public static class TestBean {
		boolean prop1;

		public void setProp1(boolean prop1) {
			this.prop1 = prop1;
		}

		public boolean isProp1() {
			return prop1;
		}

		public boolean getProp1() {
			return prop1;
		}
	}

    public static TestSuite suite() {
//        TestSuite suite = new TestSuite();
        TestSuite suite = new TestSuite(IntrospectorTest.class);
        
//        suite.addTest(new IntrospectorTest("testIntrospection_7"));
        return suite;
    }
    
    public static class Bean1 {

        private int i;

        public int ggetI() {
            return i;
        }

        public void ssetI(int i) {
            this.i = i;
        }
    }

    public static class Bean1BeanInfo extends SimpleBeanInfo {

        @Override
        public PropertyDescriptor[] getPropertyDescriptors() {
            try {
                PropertyDescriptor _property1 = new PropertyDescriptor(
                        "property1", Bean1.class, "ggetI", "ssetI");
                PropertyDescriptor[] pds = new PropertyDescriptor[] { _property1 };
                return pds;
            } catch (IntrospectionException exception) {
                return null;
            }
        }
    }

    public static class Bean2 extends Bean1 {

        private int property8;

        public int getProperty8() {
            return property8;
        }

        public void setProperty8(int property8) {
            this.property8 = property8;
        }
    }

    private static void assertBeanInfoEquals(BeanInfo beanInfo, BeanInfo info) {
        // compare BeanDescriptor
        assertEquals(beanInfo.getBeanDescriptor().getDisplayName(), info
                .getBeanDescriptor().getDisplayName());

        // compare MethodDescriptor
        MethodDescriptor[] methodDesc1 = beanInfo.getMethodDescriptors();
        MethodDescriptor[] methodDesc2 = info.getMethodDescriptors();
        assertEquals(methodDesc1.length, methodDesc2.length);

        for (int i = 0; i < methodDesc1.length; i++) {
            assertEquals(methodDesc1[i].getMethod(), methodDesc2[i].getMethod());
            assertEquals(methodDesc1[i].getDisplayName(), methodDesc2[i]
                    .getDisplayName());
        }

        // compare PropertyDescriptor
        PropertyDescriptor[] propertyDesc1 = beanInfo.getPropertyDescriptors();
        PropertyDescriptor[] propertyDesc2 = info.getPropertyDescriptors();
        assertEquals(propertyDesc1.length, propertyDesc2.length);

        for (int i = 0; i < propertyDesc1.length; i++) {
            assertEquals(propertyDesc1[i], propertyDesc2[i]);
        }

        // compare EventSetDescriptor
        EventSetDescriptor[] eventDesc1 = beanInfo.getEventSetDescriptors();
        EventSetDescriptor[] eventDesc2 = beanInfo.getEventSetDescriptors();
        if (eventDesc1 == null) {
            assertNull(eventDesc2);
        }
        if (eventDesc2 == null) {
            assertNull(eventDesc1);
        }
        if ((eventDesc1 != null) && (eventDesc1 != null)) {
            assertEquals(eventDesc1.length, eventDesc2.length);
            for (int i = 0; i < eventDesc1.length; i++) {
                assertEquals(eventDesc1[i].getAddListenerMethod(),
                        eventDesc2[i].getAddListenerMethod());
                assertEquals(eventDesc1[i].getRemoveListenerMethod(),
                        eventDesc2[i].getRemoveListenerMethod());
                assertEquals(eventDesc1[i].getGetListenerMethod(),
                        eventDesc2[i].getGetListenerMethod());
                assertEquals(eventDesc1[i].getListenerMethods().length,
                        eventDesc2[i].getListenerMethods().length);
            }
        }

    }

    private static boolean contains(String propName, Class<?> propClass,
            PropertyDescriptor[] pds)
    {
        for (PropertyDescriptor pd : pds) {
            if (propName.equals(pd.getName()) &&
                    propClass.equals(pd.getPropertyType())) {
                return true;
            }
        }
        
        return false;
    }
    
    private static boolean contains(String methodName,
            MethodDescriptor[] mds)
    {
        for (MethodDescriptor md : mds) {
            if (methodName.equals(md.getName())) {
                return true;
            }
        }
        
        return false;
    }

    private static boolean contains(String eventSetName,
            EventSetDescriptor[] esds)
    {
        for (EventSetDescriptor esd : esds) {
            if (eventSetName.equals(esd.getName())) {
                return true;
            }
        }
        
        return false;
    }
    
    /*
     * The following classes are used to test introspect properties
     */
    static class FakeFox10 {

    }

    static class FakeFox101 extends FakeFox10 {

        public void setFox101(String value) {
        }

        public String getFox101() {
            return null;
        }
    }

    static class FakeFox20 {

        public void setFox201(String value) {
        }
    }

    static class FakeFox201 extends FakeFox20 {

        public String getFox201() {
            return null;
        }
    }

    static class FakeFox202 extends FakeFox20 {

        public Integer getFox201() {
            return null;
        }
    }

    static class FakeFox30 {

        public Integer getFox301() {
            return null;
        }
    }

    static class FakeFox301 extends FakeFox30 {

        public void setFox301(String value) {
        }

    }

    static class FakeFox40 {

        public String getFox401(int i) {
            return null;
        }

        public void setFox401(int i, String value) {

        }
    }

    static class FakeFox401 extends FakeFox40 {

        public String[] getFox401() {
            return null;
        }

        public void setFox401(String[] value) {

        }
    }

    static class FakeFox402 extends FakeFox40 {

        public Integer[] getFox401() {
            return null;
        }

        public void setFox401(String[] value) {

        }
    }

    static class FakeFox50 {

        public String[] getFox501() {
            return null;
        }
    }

    static class FakeFox501 extends FakeFox50 {

        public String getFox501(int i) {
            return null;
        }

        public void setFox501(String[] value) {

        }
    }

    static class FakeFox502 extends FakeFox50 {

        public void setFox501(int i, String value) {
            return;
        }
    }

    static class FakeFox503 extends FakeFox50 {

        public void setFox501(int i, Integer value) {
            return;
        }
    }

    static class FakeFox500 {

        public String getFakeFox500(int i) {
            return null;
        }
    }

    static class FakeFox5001 extends FakeFox500 {

        public void setFakeFox500(int i, String value) {

        }
    }

    /*
     * The following classes are used to test introspect Event
     */
    static class FakeFox60 {

    }

    static class FakeFox601 {

        public void addFakeFox601Listener(FakeFox601Listener listener) {

        }

        public void removeFakeFox601Listener(FakeFox601Listener listener) {

        }
    }

    static class FakeFox602 extends FakeFox601 {

        @Override
        public void addFakeFox601Listener(FakeFox601Listener listener) {

        }

        @Override
        public void removeFakeFox601Listener(FakeFox601Listener listener) {

        }

        public FakeFox601Listener[] getFakeFox601Listeners() {
            return null;
        }
    }

    static class FakeFox603 extends FakeFox60 {

        public void addFakeFox601Listener(FakeFox601Listener listener) {

        }
    }

    static class FakeFox604 {

        public void addFakeFox601Listener(FakeFox601Listener listener)
                throws TooManyListenersException {

        }

        public void removeFakeFox601Listener(FakeFox601Listener listener) {

        }
    }

    static interface FakeFox601Listener extends EventListener {

        public void fakeFox601(FakeFox601Event event);

        public void fakeFox601(String event);
    }

    static class FakeFox601Event extends EventObject {

        /**
         * Comment for <code>serialVersionUID</code>
         */
        private static final long serialVersionUID = 1L;

        public FakeFox601Event(Object source) {
            super(source);
        }
    }

    static class FakeFox70 {

        int ddd;

        int bbb;

        int bc;

        Integer ff;

        String a;

        String bb;

        String aaa;

        public String getA() {
            return a;
        }

        public void setA(String a) {
            this.a = a;
        }

        public String getAaa() {
            return aaa;
        }

        public void setAaa(String aaa) {
            this.aaa = aaa;
        }

        public String getBb() {
            return bb;
        }

        public void setBb(String bb) {
            this.bb = bb;
        }

        public int getBbb() {
            return bbb;
        }

        public void setBbb(int bbb) {
            this.bbb = bbb;
        }

        public int getBc() {
            return bc;
        }

        public void setBc(int bc) {
            this.bc = bc;
        }

        public int getDdd() {
            return ddd;
        }

        public void setDdd(int ddd) {
            this.ddd = ddd;
        }

        public Integer getFf() {
            return ff;
        }

        public void setFf(Integer ff) {
            this.ff = ff;
        }
    }

    static class FakeFox80 {

        public String get() {
            return null;
        }

        public String get123() {
            return null;
        }
    }

    static class FakeFox90 {

        public String getFox(String value) {
            return null;
        }

        public void setFox(String value) {

        }
    }

    static class MockFoo23 {

        public String getName() {
            return null;
        }
    }

    public static class MockFoo23BeanInfo extends SimpleBeanInfo {

        @Override
        public PropertyDescriptor[] getPropertyDescriptors() {
            // System.out.println("MockFoo23BeanInfo is called");
            PropertyDescriptor pd = null;
            Class<MockFoo23> clazz = MockFoo23.class;
            try {
                Method getter = clazz.getMethod("getName", new Class[] {});
                pd = new PropertyDescriptor("name", getter, null);
                pd.setDisplayName(pd.getName() + ".beanInfo");
            } catch (IntrospectionException e) {
                e.printStackTrace();
            } catch (SecurityException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }

            return new PropertyDescriptor[] { pd };
        }
    }

    public static class MockFox00 {

        public String getLabel() {
            return null;
        }

        public void setName(String value) {

        }
    }

    public static class MockFox00BeanInfo extends SimpleBeanInfo {

        @Override
        public MethodDescriptor[] getMethodDescriptors() {
            MethodDescriptor md = null;
            try {
                Class<MockFox00> clz = MockFox00.class;
                Method method = clz.getMethod("getLabel", new Class[] {});
                md = new MethodDescriptor(method);

            } catch (Exception e) {

            }

            return new MethodDescriptor[] { md };
        }
    }

    public static class MockFox001 extends MockFox00 {

        public String getName() {
            return null;
        }
    }

    public static class MockFox001BeanInfo extends SimpleBeanInfo {

        @Override
        public PropertyDescriptor[] getPropertyDescriptors() {
            // System.out.println("MockFox001BeanInfo is called");
            PropertyDescriptor pd = null;
            Class<MockFox001> clazz = MockFox001.class;
            try {
                Method getter = clazz.getMethod("getName", new Class[] {});
                pd = new PropertyDescriptor("name", getter, null);
                pd.setDisplayName(pd.getName() + ".beaninfo");
            } catch (IntrospectionException e) {
                e.printStackTrace();
            } catch (SecurityException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }

            return new PropertyDescriptor[] { pd };
        }

        @Override
        public MethodDescriptor[] getMethodDescriptors() {
            MethodDescriptor md = null;
            try {
                Class<MockFox001> clz = MockFox001.class;
                Method method = clz.getMethod("getName", new Class[] {});
                md = new MethodDescriptor(method);

            } catch (Exception e) {

            }

            return new MethodDescriptor[] { md };
        }
    }

    public static class MockFox01 {

        public String getLabel() {
            return null;
        }

        public void setName(String value) {

        }
    }

    public static class MockFox011 extends MockFox01 {

        public String getName() {
            return null;
        }
    }

    public static class MockFox011BeanInfo extends SimpleBeanInfo {

        @Override
        public MethodDescriptor[] getMethodDescriptors() {
            MethodDescriptor md = null;
            try {
                Class<MockFox011> clz = MockFox011.class;
                Method m = clz.getMethod("getName", new Class[] {});
                md = new MethodDescriptor(m);
            } catch (Exception e) {

            }
            return new MethodDescriptor[] { md };
        }
    }
    public void testProperty() throws IntrospectionException {
        Class<MockSubClassForPorpertiesStandard> beanClass = MockSubClassForPorpertiesStandard.class;
        BeanInfo info = Introspector.getBeanInfo(beanClass);
        assertEquals(-1, info.getDefaultEventIndex());
        assertEquals(-1, info.getDefaultPropertyIndex());
        PropertyDescriptor[] pds = info.getPropertyDescriptors();
        for (PropertyDescriptor pd : pds) {
            assertFalse(pd.isBound());
            assertFalse(pd.isConstrained());
            assertFalse(pd.isExpert());
            assertFalse(pd.isHidden());
            assertFalse(pd.isPreferred());
        }
        assertEquals(2, info.getPropertyDescriptors().length);
        
        BeanInfo dummyInfo = Introspector.getBeanInfo(FakeFox041.class);
        PropertyDescriptor[] p = dummyInfo.getPropertyDescriptors();
        assertFalse(p[0].isBound());
        assertFalse(p[0].isConstrained());
        assertFalse(p[1].isBound());
        assertFalse(p[1].isConstrained());
        assertTrue(p[2].isBound());
        assertTrue(p[2].isConstrained());
        
        dummyInfo = Introspector.getBeanInfo(FakeFox0411.class);
        p = dummyInfo.getPropertyDescriptors();
        assertFalse(p[0].isBound());
        assertFalse(p[0].isConstrained());
        assertFalse(p[1].isBound());
        assertFalse(p[1].isConstrained());
        assertTrue(p[2].isBound());
        assertFalse(p[2].isConstrained());
        assertTrue(p[3].isBound());
        assertTrue(p[3].isConstrained());
        
        dummyInfo = Introspector.getBeanInfo(FakeFox0411.class, FakeFox041.class);
        p = dummyInfo.getPropertyDescriptors();
        assertFalse(p[0].isBound());
        assertFalse(p[0].isConstrained());
    }

    public void testDefaultEvent() throws IntrospectionException {
        Class beanClass = MockClassForDefaultEvent.class;
        BeanInfo info = Introspector.getBeanInfo(beanClass);
        assertEquals(-1, info.getDefaultEventIndex());
        assertEquals(-1, info.getDefaultPropertyIndex());
        EventSetDescriptor[] events = info.getEventSetDescriptors();
        for (EventSetDescriptor event : events) {
            assertFalse(event.isUnicast());
            assertTrue(event.isInDefaultEventSet());
            assertFalse(event.isExpert());
            assertFalse(event.isHidden());
            assertFalse(event.isPreferred());
        }
    }

    public void testDefaultIndex() throws IntrospectionException {
        Introspector
                .setBeanInfoSearchPath(new String[] { "org.apache.harmony.beans.tests.support" });

        BeanInfo dummyInfo = Introspector.getBeanInfo(FakeFox031.class);
        assertEquals(-1, dummyInfo.getDefaultPropertyIndex());
        assertEquals(-1, dummyInfo.getDefaultEventIndex());
    }

    static class MockBaseClassForPorpertiesStandard {
        int a = 0;

        int b = 1;
    }

    static class MockSubClassForPorpertiesStandard extends
            MockBaseClassForPorpertiesStandard {
        int a = 2;

        int b = 3;

        public int getName() {
            return a;
        }

        public void setName(int i) {
            a = i;
        }
    }
    
    static class MockClassForDefaultEvent {
        public void addPropertyChangeListener(PropertyChangeListener a) {
        }

        public void removePropertyChangeListener(PropertyChangeListener a) {
        }
    }
    static class MockBaseClassForPorperties {
        int a = 0;

        int b = 1;
    }

    static class MockSubClassForPorperties extends MockBaseClassForPorperties {
        int a = 2;

        int b = 3;

        int c = 3;

        public int getName() {
            return a;
        }

        public void setName(int i) {
            a = i;
        }
    }

    public void testGetIcon() throws IntrospectionException {
        Class<MockSubClassForPorperties> beanClass = MockSubClassForPorperties.class;
        BeanInfo info = Introspector.getBeanInfo(beanClass);
        assertNotNull(info.getIcon(BeanInfo.ICON_COLOR_16x16));
    }

    public static class MockBaseClassForPorpertiesBeanInfo extends
            SimpleBeanInfo {

        @Override
        public MethodDescriptor[] getMethodDescriptors() {
            MethodDescriptor md = null;
            try {
                Class<MockSubClassForPorperties> clz = MockSubClassForPorperties.class;
                Method m = clz.getMethod("getName", new Class[] {});
                md = new MethodDescriptor(m);
            } catch (Exception e) {

            }
            return new MethodDescriptor[] { md };
        }

        @Override
        public PropertyDescriptor[] getPropertyDescriptors() {
            PropertyDescriptor[] pds = new PropertyDescriptor[2];
            Class<MockSubClassForPorperties> clazz = MockSubClassForPorperties.class;
            try {
                Method getter = clazz.getMethod("getName");
                Method setter = clazz.getMethod("setName", Integer.TYPE);
                pds[0] = new PropertyDescriptor("a", getter, setter);
                pds[0].setConstrained(true);
                pds[0].setBound(true);
                pds[0].setExpert(true);
                pds[0].setHidden(true);
                pds[1] = new PropertyDescriptor("b", getter, setter);
            } catch (IntrospectionException e) {
                e.printStackTrace();
            } catch (SecurityException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }

            return pds;
        }

        public Image getIcon(int iconKind) {
            return null;
        }
    }

    public static class MockSubClassForPorpertiesBeanInfo extends
            SimpleBeanInfo {

        @Override
        public MethodDescriptor[] getMethodDescriptors() {
            MethodDescriptor md = null;
            try {
                Class<MockSubClassForPorperties> clz = MockSubClassForPorperties.class;
                Method m = clz.getMethod("getName", new Class[] {});
                md = new MethodDescriptor(m);
            } catch (Exception e) {

            }
            return new MethodDescriptor[] { md };
        }

        @Override
        public PropertyDescriptor[] getPropertyDescriptors() {
            PropertyDescriptor[] pds = new PropertyDescriptor[2];
            Class<MockSubClassForPorperties> clazz = MockSubClassForPorperties.class;
            try {
                Method getter = clazz.getMethod("getName");
                Method setter = clazz.getMethod("setName", Integer.TYPE);
                pds[0] = new PropertyDescriptor("a", getter, setter);
                pds[1] = new PropertyDescriptor("b", getter, setter);
            } catch (IntrospectionException e) {
                e.printStackTrace();
            } catch (SecurityException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }
            return pds;
        }

        public Image getIcon(int iconKind) {
            return new BufferedImage(16, 16, 1);
        }

    }

    /*
     * Regression test for HARMONY-4892
     */
    public static class MyBean {

        public static String invisble;

        public static String getInvisible() {
            return invisble;
        }

        public String visible;

        public String getVisible() {
            return visible;
        }

        public void setVisible(String a) {
            this.visible = a;
        }
    }

    public void testPropertyDescriptors() throws IntrospectionException {
        BeanInfo info = Introspector.getBeanInfo(MyBean.class);
        for (PropertyDescriptor pd : info.getPropertyDescriptors()) {
            assertFalse(pd.getName().equals("invisible"));
        }
    }
}
