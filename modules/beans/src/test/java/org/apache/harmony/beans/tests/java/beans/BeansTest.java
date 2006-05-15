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

import java.awt.Component;
import java.beans.AppletInitializer;
import java.beans.Beans;
import java.beans.beancontext.BeanContext;
import java.beans.beancontext.BeanContextSupport;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.io.Externalizable;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.net.URLClassLoader;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.textui.TestRunner;
import junit.framework.TestSuite;
import org.apache.harmony.beans.tests.java.beans.mock.MockAppletInitializer;
import org.apache.harmony.beans.tests.java.beans.auxiliary.SampleBean;

/**
 * Unit test for java.beans.Beans
 */
public class BeansTest extends TestCase {
	private File fileURLCP = new File(System.getProperty("user.home"), "urlcp");

	private File fileSer;

	private File fileRes;

	private File fileClass;

	public BeansTest() {
		File path = new File(fileURLCP, "serialization/java/beans/mock");
		path.mkdirs();
		fileSer = new File(path, "MockJavaBean2.ser");
		fileClass = new File(path, "MockJavaBean2.class");
	}

	protected void tearDown() throws Exception {
		super.tearDown();
		if (fileSer.exists()) {
			fileSer.delete();
		}

		if (fileClass.exists()) {
			fileClass.delete();
		}
		fileURLCP.delete();
	}

	/*
	 * public Beans()
	 */
	public void testBeans() {
		Beans beans = new Beans();
	}

	/*
	 * public static Object getInstanceOf(Object bean, Class targetType)
	 */
	public void testGetInstanceOf() {
		MockJavaBean bean = new MockJavaBean();
		Class type = Component.class;
		Object obj = Beans.getInstanceOf(bean, type);
		assertSame(bean, obj);
	}

	public void testGetInstanceOf_BeanNull() {
		Class type = Component.class;
		Object obj = Beans.getInstanceOf(null, type);
		assertNull(obj);
	}

	public void testGetInstanceOf_TargetTypeNull() {
		MockJavaBean bean = new MockJavaBean();
		Object obj = Beans.getInstanceOf(bean, null);
		assertSame(bean, obj);
	}

	/*
	 * Class under test for Object instantiate(ClassLoader, String)
	 */
	public void testInstantiateClassLoaderString_Class() throws IOException,
			ClassNotFoundException, NoSuchMethodException, SecurityException,
			IllegalAccessException, InvocationTargetException {
		// copy class
		InputStream bin = getClass().getResourceAsStream(
				"/binary/java/beans/mock/MockJavaBean2.bin");
		copyFile(bin, fileClass);

		URLClassLoader loader = new URLClassLoader(new URL[] { fileURLCP
				.toURL() });
		Object bean = Beans.instantiate(loader,
				"org.apache.harmony.beans.tests.java.beans.mock.MockJavaBean2");
		assertEquals("as_class", (String) bean.getClass().getMethod(
				"getPropertyOne", (Class[])null).invoke(bean, null));
		assertSame(loader, bean.getClass().getClassLoader());
	}

	public void testInstantiateClassLoaderString_Ser() throws IOException,
			ClassNotFoundException, IllegalAccessException,
			InvocationTargetException, NoSuchMethodException {
		// copy class
		InputStream bin = getClass().getResourceAsStream(
				"/binary/java/beans/mock/MockJavaBean2.bin");
		copyFile(bin, fileClass);

		// copy ser
		InputStream res = getClass().getResourceAsStream(
				"/serialization/java/beans/mock/MockJavaBean2.ser");
		copyFile(res, fileSer);

		URLClassLoader loader = new URLClassLoader(new URL[] { fileURLCP
				.toURL() });
		Object bean = Beans.instantiate(loader,
				"org.apache.harmony.beans.tests.java.beans.mock.MockJavaBean2");
		assertEquals("as_object", (String) bean.getClass().getMethod(
				"getPropertyOne", (Class[])null).invoke(bean, null));
		assertSame(loader, bean.getClass().getClassLoader());
	}

	public void testInstantiateClassLoaderString_ClassLoaderNull()
			throws IOException, ClassNotFoundException {
		Object bean = Beans.instantiate(null, MockJavaBean.class.getName());
		assertEquals(bean.getClass(), MockJavaBean.class);
		assertSame(ClassLoader.getSystemClassLoader(), bean.getClass()
				.getClassLoader());
	}

	public void testInstantiateClassLoaderString_BeanNameNull()
			throws IOException, ClassNotFoundException {
		try {
			Object bean = Beans.instantiate(null, null);
			fail("Should throw NullPointerException");
		} catch (NullPointerException e) {
		}
	}

	/*
	 * Class under test for Object instantiate(ClassLoader, String, BeanContext)
	 */
	public void testInstantiateClassLoaderStringBeanContext_Class()
			throws ClassNotFoundException, ClassNotFoundException, IOException,
			IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {
		// copy class
		InputStream bin = getClass().getResourceAsStream(
				"/binary/java/beans/mock/MockJavaBean2.bin");
		copyFile(bin, fileClass);

		URLClassLoader loader = new URLClassLoader(new URL[] { fileURLCP
				.toURL() });
		BeanContext context = new BeanContextSupport();
		Object bean = Beans.instantiate(loader,
				"org.apache.harmony.beans.tests.java.beans.mock.MockJavaBean2", context);
		assertEquals("as_class", (String) bean.getClass().getMethod(
				"getPropertyOne", (Class[])null).invoke(bean, null));
		assertSame(loader, bean.getClass().getClassLoader());
		assertTrue(context.contains(bean));
	}

	public void testInstantiateClassLoaderStringBeanContext_Ser()
			throws IOException, ClassNotFoundException, IllegalAccessException,
			InvocationTargetException, NoSuchMethodException {
		// copy class
		InputStream bin = getClass().getResourceAsStream(
				"/binary/java/beans/mock/MockJavaBean2.bin");
		copyFile(bin, fileClass);

		// copy ser
		InputStream res = getClass().getResourceAsStream(
				"/serialization/java/beans/mock/MockJavaBean2.ser");
		copyFile(res, fileSer);

		URLClassLoader loader = new URLClassLoader(new URL[] { fileURLCP
				.toURL() });
		BeanContext context = new BeanContextSupport();
		Object bean = Beans.instantiate(loader,
				"org.apache.harmony.beans.tests.java.beans.mock.MockJavaBean2", context);
		assertEquals("as_object", (String) bean.getClass().getMethod(
				"getPropertyOne", (Class[])null).invoke(bean, null));
		assertSame(loader, bean.getClass().getClassLoader());
		assertTrue(context.contains(bean));
	}

	public void testInstantiateClassLoaderStringBeanContext_ClassLoaderNull()
			throws IOException, ClassNotFoundException {
		BeanContext context = new BeanContextSupport();
		Object bean = Beans.instantiate(null, MockJavaBean.class.getName(),
				context);
		assertEquals(bean.getClass(), MockJavaBean.class);
		assertSame(ClassLoader.getSystemClassLoader(), bean.getClass()
				.getClassLoader());
		assertTrue(context.contains(bean));
	}

	public void testInstantiateClassLoaderStringBeanContext_BeanNameNull()
			throws IOException, ClassNotFoundException {
		BeanContext context = new BeanContextSupport();
		URLClassLoader loader = new URLClassLoader(new URL[] { fileURLCP
				.toURL() });
		try {
			Object bean = Beans.instantiate(loader, null, context);
			fail("Should throw NullPointerException.");
		} catch (NullPointerException e) {
		}
	}

	public void testInstantiateClassLoaderStringBeanContext_ContextNull()
			throws IOException, ClassNotFoundException {
		URLClassLoader loader = new URLClassLoader(new URL[] { fileURLCP
				.toURL() });
		Object bean = Beans.instantiate(loader, MockJavaBean.class.getName(),
				null);
		assertEquals(bean.getClass(), MockJavaBean.class);
	}

	/*
	 * Class under test for Object instantiate(ClassLoader, String, BeanContext,
	 * AppletInitializer)
	 */
	public void testInstantiateClassLoaderStringBeanContextAppletInitializer_Class()
			throws IOException, ClassNotFoundException, IllegalAccessException,
			InvocationTargetException, NoSuchMethodException {
		// copy class
		InputStream bin = getClass().getResourceAsStream(
				"/binary/java/beans/mock/MockJavaBean2.bin");
		copyFile(bin, fileClass);
		URLClassLoader loader = new URLClassLoader(new URL[] { fileURLCP
				.toURL() });
		String beanName = "org.apache.harmony.beans.tests.java.beans.mock.MockJavaBean2";
		BeanContext context = new BeanContextSupport();
		AppletInitializer appInit = new MockAppletInitializer();
		Object bean = Beans.instantiate(loader, beanName, context, appInit);

		assertEquals("as_class", (String) bean.getClass().getMethod(
				"getPropertyOne", (Class[])null).invoke(bean, null));
		assertSame(loader, bean.getClass().getClassLoader());
		assertTrue(context.contains(bean));
	}

	public void testInstantiateClassLoaderStringBeanContextAppletInitializer_Ser()
			throws ClassNotFoundException, IOException,
			InvocationTargetException, NoSuchMethodException,
			IllegalAccessException {
		// copy class
		InputStream bin = getClass().getResourceAsStream(
				"/binary/java/beans/mock/MockJavaBean2.bin");
		copyFile(bin, fileClass);

		// copy ser
		InputStream res = getClass().getResourceAsStream(
				"/serialization/java/beans/mock/MockJavaBean2.ser");
		copyFile(res, fileSer);

		URLClassLoader loader = new URLClassLoader(new URL[] { fileURLCP
				.toURL() });
		String beanName = "org.apache.harmony.beans.tests.java.beans.mock.MockJavaBean2";
		BeanContext context = new BeanContextSupport();
		AppletInitializer appInit = new MockAppletInitializer();
		Object bean = Beans.instantiate(loader, beanName, context, appInit);

		assertEquals("as_object", (String) bean.getClass().getMethod(
				"getPropertyOne", (Class[])null).invoke(bean, null));
		assertSame(loader, bean.getClass().getClassLoader());
		assertTrue(context.contains(bean));
	}

	public void testInstantiateClassLoaderStringBeanContextAppletInitializer_LoaderNull()
			throws IOException, ClassNotFoundException {
		String beanName = "org.apache.harmony.beans.tests.java.beans.MockJavaBean";
		BeanContext context = new BeanContextSupport();
		AppletInitializer appInit = new MockAppletInitializer();
		Object bean = Beans.instantiate(null, beanName, context, appInit);
		assertSame(ClassLoader.getSystemClassLoader(), bean.getClass()
				.getClassLoader());
		assertEquals(beanName, bean.getClass().getName());
		assertTrue(context.contains(bean));
	}

	public void testInstantiateClassLoaderStringBeanContextAppletInitializer_BeanNull()
			throws IOException, ClassNotFoundException {
		URLClassLoader loader = new URLClassLoader(new URL[] { fileURLCP
				.toURL() });
		BeanContext context = new BeanContextSupport();
		AppletInitializer appInit = new MockAppletInitializer();
		try {
			Object bean = Beans.instantiate(loader, null, context, appInit);
			fail("Should throw NullPointerException.");
		} catch (NullPointerException e) {
		}
	}

	public void testInstantiateClassLoaderStringBeanContextAppletInitializer_ContextNull()
			throws IOException, ClassNotFoundException {
		URLClassLoader loader = new URLClassLoader(new URL[] { fileURLCP
				.toURL() });
		String beanName = "org.apache.harmony.beans.tests.java.beans.MockJavaBean";
		AppletInitializer appInit = new MockAppletInitializer();
		Object bean = Beans.instantiate(loader, beanName, null, appInit);
		assertSame(ClassLoader.getSystemClassLoader(), bean.getClass()
				.getClassLoader());
		assertEquals(beanName, bean.getClass().getName());
	}

	public void testInstantiateClassLoaderStringBeanContextAppletInitializer_InitializerNull()
			throws IOException, ClassNotFoundException {
		URLClassLoader loader = new URLClassLoader(new URL[] { fileURLCP
				.toURL() });
		String beanName = "org.apache.harmony.beans.tests.java.beans.MockJavaBean";
		BeanContext context = new BeanContextSupport();
		Object bean = Beans.instantiate(loader, beanName, context, null);
		assertSame(ClassLoader.getSystemClassLoader(), bean.getClass()
				.getClassLoader());
		assertEquals(beanName, bean.getClass().getName());
	}

	public void testInstantiateClassLoaderStringBeanContextAppletInitializer_AppletBean()
			throws IOException, ClassNotFoundException {
		/*
		 * String beanName = MockAppletBean.class.getName(); BeanContext context =
		 * new BeanContextSupport(); MockAppletInitializer appInit = new
		 * MockAppletInitializer(); MockAppletBean bean = (MockAppletBean)
		 * Beans.instantiate(null, beanName, context, appInit);
		 * assertSame(ClassLoader.getSystemClassLoader(), bean.getClass()
		 * .getClassLoader()); assertEquals(beanName,
		 * bean.getClass().getName()); assertTrue(context.contains(bean));
		 * assertTrue(appInit.activateHasBeenCalled());
		 * assertTrue(appInit.initializeHasBeenCalled());
		 * assertTrue(bean.initHasBeenCalled());
		 */
	}

	public void testInstantiateClassLoaderStringBeanContextAppletInitializer_AppletBean_SER()
			throws IOException, ClassNotFoundException {
		/*
		 * String beanName = MockAppletBean2.class.getName(); BeanContext
		 * context = new BeanContextSupport(); MockAppletInitializer appInit =
		 * new MockAppletInitializer(); MockAppletBean2 bean = (MockAppletBean2)
		 * Beans.instantiate(null, beanName, context, appInit);
		 * assertSame(ClassLoader.getSystemClassLoader(), bean.getClass()
		 * .getClassLoader()); assertEquals(beanName,
		 * bean.getClass().getName()); assertTrue(context.contains(bean));
		 * assertTrue(appInit.activateHasBeenCalled());
		 * assertTrue(appInit.initializeHasBeenCalled());
		 * assertFalse(bean.initHasBeenCalled());
		 */
	}

	public void testInstantiateClassLoaderStringBeanContextAppletInitializer_AppletBean_2()
			throws IOException, ClassNotFoundException {
		/*
		 * String beanName = MockAppletBean.class.getName(); BeanContext context =
		 * new BeanContextSupport(); MockAppletInitializer appInit = new
		 * MockAppletInitializer(); MockAppletBean bean = (MockAppletBean)
		 * Beans.instantiate(null, beanName, context, null);
		 */
	}

	/*
	 * public static boolean isInstanceOf(Object bean, Class targetType)
	 */
	public void testIsInstanceOf() {
		MockJavaBean bean = new MockJavaBean();
		assertTrue(Beans.isInstanceOf(bean, Serializable.class));
	}

	public void testIsInstanceOf_BeanNull() {
		try {
			boolean is = Beans.isInstanceOf(null, Serializable.class);
			fail("Should throw NullPointerException.");
		} catch (NullPointerException e) {
		}
	}

	public void testIsInstanceOf_TypeNull() {
		MockJavaBean bean = new MockJavaBean();
		assertFalse(Beans.isInstanceOf(bean, null));
	}

	public void testIsInstanceOf_TypeInvalid() {
		MockJavaBean bean = new MockJavaBean();
		assertFalse(Beans.isInstanceOf(bean, String.class));
	}

	public void testSetDesignTime_true() {
		Beans.setDesignTime(true);
		assertTrue(Beans.isDesignTime());
	}

	public void testSetDesignTime_false() {
		Beans.setDesignTime(false);
		assertFalse(Beans.isDesignTime());
	}

	public void testSetGuiAvailable_true() {
		Beans.setGuiAvailable(true);
		assertTrue(Beans.isGuiAvailable());
	}

	public void testSetGuiAvailable_false() {
		Beans.setGuiAvailable(false);
		assertFalse(Beans.isGuiAvailable());
	}

	private void serialize(Object obj, File file) throws FileNotFoundException,
			IOException {
		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(
				file));
		oos.writeObject(obj);
		oos.close();
	}

	private void copyFile(InputStream src, File dest) throws IOException {
		FileOutputStream fos = new FileOutputStream(dest);
		int length = 0;
		byte[] bytes = new byte[1024];
		while ((length = src.read(bytes)) != -1) {
			fos.write(bytes, 0, length);
		}
		fos.close();
		src.close();
	}
    
    
    /**
     * The test checks the method instantiate()
     * using specific classloader for class loading
     */
    public void testLoadBySpecificClassLoader() {
        String beanName = "org.apache.harmony.beans.tests.java.beans.auxiliary.SampleBean";
        
        try {
            ClassLoader cls = createSpecificClassLoader();
            Object bean = Beans.instantiate(cls, beanName);
            
            assertNotNull(bean);
            assertEquals(bean.getClass(), SampleBean.class);
            
            SampleBean sampleBean = (SampleBean) bean;
            checkValues(sampleBean);
        } catch (ClassNotFoundException cnfe) {
            fail("Class with name " + beanName + " is not found");
        } catch (IOException ioe) {
            fail("IOException is thrown while loading " + beanName + " class");
        }
    }
    
    /**
     * The test checks the method instantiate()
     * using default classloader for class loading
     */
    public void testLoadByDefaultClassLoader() {
        String beanName = "org.apache.harmony.beans.tests.java.beans.auxiliary.SampleBean";
        
        try {
            Object bean = Beans.instantiate(null, beanName);
            
            assertNotNull(bean);
            assertEquals(bean.getClass(), SampleBean.class);
            
            SampleBean sampleBean = (SampleBean) bean;
            checkValues(sampleBean);
        } catch (ClassNotFoundException cnfe) {
            fail("Class with name " + beanName + " is not found");
        } catch (IOException ioe) {
            fail("IOException is thrown while loading " + beanName + " class");
        }
    }

    //regression test for HARMONY-358
    public void testInstantiate() throws Exception {
        try {
            Class.forName(this.getClass().getName(), true, null);
            fail("This test is designed to run from classpath rather then from bootclasspath");
        } catch (ClassNotFoundException ok) {
        }
        assertNotNull(Beans.instantiate(null, this.getClass().getName()));
    } 

    //regression test for HARMONY-402
    public void test_isInstanceOf_Object_Class() {
        ObjectBean bean = new ObjectBean();
        // correct non-null targetType
        Class targetType = Externalizable.class;
        assertTrue(Beans.isInstanceOf(bean, targetType));

        // null targetType
        targetType = null;
        assertFalse(Beans.isInstanceOf(bean, targetType));
    } 
    
    /**
     * 
     */
    public static Test suite() {
        return new TestSuite(BeansTest.class);
    }
    
    /**
     * 
     */
    public static void main(String[] args) {
        TestRunner.run(suite());
    }
    
    private ClassLoader createSpecificClassLoader() {
        return new ClassLoader() {
            public Class loadClass(String name) throws ClassNotFoundException {
                Class result = super.loadClass(name);
                return result;
            }
        };        
    }
    
    private void checkValues(SampleBean sampleBean) {
        assertEquals(null, sampleBean.getText());
    }

    private class ObjectBean implements Externalizable { 
        public void writeExternal(ObjectOutput out) {}; 
        public void readExternal(ObjectInput in){}; 
    } 
}
