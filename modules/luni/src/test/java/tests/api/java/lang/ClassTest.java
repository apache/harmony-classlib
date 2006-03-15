/* Copyright 1998, 2005 The Apache Software Foundation or its licensors, as applicable
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

package tests.api.java.lang;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

import tests.support.resource.Support_Resources;

public class ClassTest extends junit.framework.TestCase {

	static class StaticMember$Class {
		class Member2$A {
		}
	}

	class Member$Class {
		class Member3$B {
		}
	}

	public static class TestClass {
		private int privField = 1;

		public int pubField = 2;

		private Object cValue = null;

		public Object ack = new Object();

		private int privMethod() {
			return 1;
		}

		public int pubMethod() {
			return 2;
		}

		public Object cValue() {
			return cValue;
		}

		public TestClass() {
		}

		private TestClass(Object o) {
		}
	}

	public static class SubTestClass extends TestClass {
	}

	/**
	 * @tests java.lang.Class#forName(java.lang.String)
	 */
	public void test_forNameLjava_lang_String() {
		// Test for method java.lang.Class
		// java.lang.Class.forName(java.lang.String)
		try {
			assertTrue("Class for name failed for java.lang.Object", Class
					.forName("java.lang.Object") == java.lang.Object.class);
		} catch (Exception e) {
			fail("Unexpected exception " + e
					+ " in Class.forName(java.lang.Object)");
		}
		try {
			assertTrue(
					"Class for name failed for [[Ljava.lang.Object;",
					Class.forName("[[Ljava.lang.Object;") == java.lang.Object[][].class);
		} catch (Exception e) {
			fail("Unexpected exception " + e
					+ " in Class.forName([[Ljava.lang.Object;)");
		}
		try {
			assertTrue("Class for name failed for [I",
					Class.forName("[I") == int[].class);
		} catch (Exception e) {
			fail("Unexpected exception " + e + " in Class.forName([I)");
		}

		try {
			assertTrue("Class for name worked (but should not have) for int",
					Class.forName("int") == null
							|| Class.forName("int") != null);
		} catch (Exception e) {
			// Exception ok here.
		}

		boolean exception = false;
		try {
			Class.forName("int");
		} catch (ClassNotFoundException e) {
			exception = true;
		}
		assertTrue("Found class int", exception);

		exception = false;
		try {
			Class.forName("byte");
		} catch (ClassNotFoundException e) {
			exception = true;
		}
		assertTrue("Found class byte", exception);

		exception = false;
		try {
			Class.forName("char");
		} catch (ClassNotFoundException e) {
			exception = true;
		}
		assertTrue("Found class char", exception);

		exception = false;
		try {
			Class.forName("void");
		} catch (ClassNotFoundException e) {
			exception = true;
		}
		assertTrue("Found class void", exception);

		exception = false;
		try {
			Class.forName("short");
		} catch (ClassNotFoundException e) {
			exception = true;
		}
		assertTrue("Found class short", exception);

		exception = false;
		try {
			Class.forName("long");
		} catch (ClassNotFoundException e) {
			exception = true;
		}
		assertTrue("Found class long", exception);

		exception = false;
		try {
			Class.forName("boolean");
		} catch (ClassNotFoundException e) {
			exception = true;
		}
		assertTrue("Found class boolean", exception);

		exception = false;
		try {
			Class.forName("float");
		} catch (ClassNotFoundException e) {
			exception = true;
		}
		assertTrue("Found class float", exception);

		exception = false;
		try {
			Class.forName("double");
		} catch (ClassNotFoundException e) {
			exception = true;
		}
		assertTrue("Found class double", exception);
	}

	/**
	 * @tests java.lang.Class#getClasses()
	 */
	public void test_getClasses() {
		// Test for method java.lang.Class [] java.lang.Class.getClasses()
		assertTrue("Incorrect class array returned", ClassTest.class
				.getClasses().length == 2);
	}

	/**
	 * @tests java.lang.Class#getClasses()
	 */
	public void test_getClasses_subtest0() {
		final java.security.Permission privCheckPermission = new java.security.BasicPermission(
				"Privilege check") {
		};
		class MyCombiner implements java.security.DomainCombiner {
			boolean combine;

			public java.security.ProtectionDomain[] combine(
					java.security.ProtectionDomain[] executionDomains,
					java.security.ProtectionDomain[] parentDomains) {
				combine = true;
				return new java.security.ProtectionDomain[0];
			}

			public boolean isPriviledged() {
				combine = false;
				try {
					java.security.AccessController
							.checkPermission(privCheckPermission);
				} catch (SecurityException e) {
				}
				return !combine;
			}
		}
		;
		final MyCombiner combiner = new MyCombiner();
		class SecurityManagerCheck extends SecurityManager {
			String reason;

			Class checkClass;

			int checkType;

			int checkPermission = 0;

			int checkMemberAccess = 0;

			int checkPackageAccess = 0;

			public void setExpected(String reason, Class cls, int type) {
				this.reason = reason;
				checkClass = cls;
				checkType = type;
				checkPermission = 0;
				checkMemberAccess = 0;
				checkPackageAccess = 0;
			}

			public void checkPermission(java.security.Permission perm) {
				if (combiner.isPriviledged())
					return;
				checkPermission++;
			}

			public void checkMemberAccess(Class cls, int type) {
				if (combiner.isPriviledged())
					return;
				checkMemberAccess++;
				assertTrue(reason + " unexpected class: " + cls,
						cls == checkClass);
				assertTrue(reason + "unexpected type: " + type,
						type == checkType);
			}

			public void checkPackageAccess(String packageName) {
				if (combiner.isPriviledged())
					return;
				checkPackageAccess++;
				String name = checkClass.getName();
				int index = name.lastIndexOf('.');
				String checkPackage = name.substring(0, index);
				assertTrue(reason + " unexpected package: " + packageName,
						packageName.equals(checkPackage));
			}

			public void assertProperCalls() {
				assertTrue(reason + " unexpected checkPermission count: "
						+ checkPermission, checkPermission == 0);
				assertTrue(reason + " unexpected checkMemberAccess count: "
						+ checkMemberAccess, checkMemberAccess == 1);
				assertTrue(reason + " unexpected checkPackageAccess count: "
						+ checkPackageAccess, checkPackageAccess == 1);
			}
		}

		java.security.AccessControlContext acc = new java.security.AccessControlContext(
				new java.security.ProtectionDomain[0]);
		java.security.AccessControlContext acc2 = new java.security.AccessControlContext(
				acc, combiner);

		java.security.PrivilegedAction action = new java.security.PrivilegedAction() {
			public Object run() {
				File resources = Support_Resources.createTempFolder();
				try {
					Support_Resources.copyFile(resources, null,
							"hyts_security.jar");
					File file = new File(resources.toString()
							+ "/hyts_security.jar");
					java.net.URL url = new java.net.URL("file:"
							+ file.getPath());
					ClassLoader loader = new java.net.URLClassLoader(
							new java.net.URL[] { url }, null);
					Class cls = Class.forName("packB.SecurityTestSub", false,
							loader);
					SecurityManagerCheck sm = new SecurityManagerCheck();
					System.setSecurityManager(sm);
					try {
						sm.setExpected("getClasses", cls,
								java.lang.reflect.Member.PUBLIC);
						cls.getClasses();
						sm.assertProperCalls();

						sm.setExpected("getDeclaredClasses", cls,
								java.lang.reflect.Member.DECLARED);
						cls.getDeclaredClasses();
						sm.assertProperCalls();

						sm.setExpected("getConstructor", cls,
								java.lang.reflect.Member.PUBLIC);
						cls.getConstructor(new Class[0]);
						sm.assertProperCalls();

						sm.setExpected("getConstructors", cls,
								java.lang.reflect.Member.PUBLIC);
						cls.getConstructors();
						sm.assertProperCalls();

						sm.setExpected("getDeclaredConstructor", cls,
								java.lang.reflect.Member.DECLARED);
						cls.getDeclaredConstructor(new Class[0]);
						sm.assertProperCalls();

						sm.setExpected("getDeclaredConstructors", cls,
								java.lang.reflect.Member.DECLARED);
						cls.getDeclaredConstructors();
						sm.assertProperCalls();

						sm.setExpected("getField", cls,
								java.lang.reflect.Member.PUBLIC);
						cls.getField("publicField");
						sm.assertProperCalls();

						sm.setExpected("getFields", cls,
								java.lang.reflect.Member.PUBLIC);
						cls.getFields();
						sm.assertProperCalls();

						sm.setExpected("getDeclaredField", cls,
								java.lang.reflect.Member.DECLARED);
						cls.getDeclaredField("publicField");
						sm.assertProperCalls();

						sm.setExpected("getDeclaredFields", cls,
								java.lang.reflect.Member.DECLARED);
						cls.getDeclaredFields();
						sm.assertProperCalls();

						sm.setExpected("getDeclaredMethod", cls,
								java.lang.reflect.Member.DECLARED);
						cls.getDeclaredMethod("publicMethod", new Class[0]);
						sm.assertProperCalls();

						sm.setExpected("getDeclaredMethods", cls,
								java.lang.reflect.Member.DECLARED);
						cls.getDeclaredMethods();
						sm.assertProperCalls();

						sm.setExpected("getMethod", cls,
								java.lang.reflect.Member.PUBLIC);
						cls.getMethod("publicMethod", new Class[0]);
						sm.assertProperCalls();

						sm.setExpected("getMethods", cls,
								java.lang.reflect.Member.PUBLIC);
						cls.getMethods();
						sm.assertProperCalls();

						sm.setExpected("newInstance", cls,
								java.lang.reflect.Member.PUBLIC);
						cls.newInstance();
						sm.assertProperCalls();
					} finally {
						System.setSecurityManager(null);
					}
				} catch (Exception e) {
					if (e instanceof RuntimeException)
						throw (RuntimeException) e;
					fail("unexpected exception: " + e);
				}
				return null;
			}
		};
		java.security.AccessController.doPrivileged(action, acc2);
	}

	/**
	 * @tests java.lang.Class#getComponentType()
	 */
	public void test_getComponentType() {
		// Test for method java.lang.Class java.lang.Class.getComponentType()
		assertTrue("int array does not have int component type", int[].class
				.getComponentType() == int.class);
		assertTrue("Object array does not have Object component type",
				Object[].class.getComponentType() == Object.class);
		assertTrue("Object has non-null component type", Object.class
				.getComponentType() == null);
	}

	/**
	 * @tests java.lang.Class#getConstructor(java.lang.Class[])
	 */
	public void test_getConstructor$Ljava_lang_Class() {
		// Test for method java.lang.reflect.Constructor
		// java.lang.Class.getConstructor(java.lang.Class [])
		try {
			TestClass.class.getConstructor(new Class[0]);
			try {
				TestClass.class.getConstructor(new Class[] { Object.class });
			} catch (NoSuchMethodException e) {
				// Correct - constructor with obj is private
				return;
			}
			fail("Found private constructor");
		} catch (NoSuchMethodException e) {
			fail("Exception during getConstructor test : " + e.getMessage());
		}
	}

	/**
	 * @tests java.lang.Class#getConstructors()
	 */
	public void test_getConstructors() {
		// Test for method java.lang.reflect.Constructor []
		// java.lang.Class.getConstructors()
		try {
			java.lang.reflect.Constructor[] c = TestClass.class
					.getConstructors();
			assertTrue("Incorrect number of constructors returned",
					c.length == 1);
		} catch (Exception e) {
			fail("Exception during getDeclaredConstructor test:"
					+ e.toString());
		}
	}

	/**
	 * @tests java.lang.Class#getDeclaredClasses()
	 */
	public void test_getDeclaredClasses() {
		// Test for method java.lang.Class []
		// java.lang.Class.getDeclaredClasses()
		assertTrue("Incorrect class array returned", ClassTest.class
				.getClasses().length == 2);
	}

	/**
	 * @tests java.lang.Class#getDeclaredConstructor(java.lang.Class[])
	 */
	public void test_getDeclaredConstructor$Ljava_lang_Class() {
		// Test for method java.lang.reflect.Constructor
		// java.lang.Class.getDeclaredConstructor(java.lang.Class [])
		try {
			java.lang.reflect.Constructor c = TestClass.class
					.getDeclaredConstructor(new Class[0]);
			assertTrue("Incorrect constructor returned", ((TestClass) (c
					.newInstance(new Object[0]))).cValue() == null);
			c = TestClass.class
					.getDeclaredConstructor(new Class[] { Object.class });
		} catch (NoSuchMethodException e) {
			fail("Exception during getDeclaredConstructor test: "
					+ e.toString());
		} catch (Exception e) {
			fail("Exception during getDeclaredConstructor test:"
					+ e.toString());
		}
	}

	/**
	 * @tests java.lang.Class#getDeclaredConstructors()
	 */
	public void test_getDeclaredConstructors() {
		// Test for method java.lang.reflect.Constructor []
		// java.lang.Class.getDeclaredConstructors()
		try {
			java.lang.reflect.Constructor[] c = TestClass.class
					.getDeclaredConstructors();
			assertTrue("Incorrect number of constructors returned",
					c.length == 2);
		} catch (Exception e) {
			fail("Exception during getDeclaredConstructor test:"
					+ e.toString());
		}
	}

	/**
	 * @tests java.lang.Class#getDeclaredField(java.lang.String)
	 */
	public void test_getDeclaredFieldLjava_lang_String() {
		// Test for method java.lang.reflect.Field
		// java.lang.Class.getDeclaredField(java.lang.String)
		try {
			java.lang.reflect.Field f = TestClass.class
					.getDeclaredField("pubField");
			assertTrue("Returned incorrect field",
					f.getInt(new TestClass()) == 2);
		} catch (Exception e) {
			fail("Exception getting fields : " + e.getMessage());
		}
	}

	/**
	 * @tests java.lang.Class#getDeclaredFields()
	 */
	public void test_getDeclaredFields() {
		// Test for method java.lang.reflect.Field []
		// java.lang.Class.getDeclaredFields()
		try {
			java.lang.reflect.Field[] f = TestClass.class.getDeclaredFields();
			assertTrue("Returned incorrect number of fields", f.length == 4);
			f = SubTestClass.class.getDeclaredFields();
			// Declared fields do not include inherited
			assertTrue("Returned incorrect number of fields", f.length == 0);
		} catch (Exception e) {
			fail("Exception getting fields : " + e.getMessage());
		}
	}

	/**
	 * @tests java.lang.Class#getDeclaredMethod(java.lang.String,
	 *        java.lang.Class[])
	 */
	public void test_getDeclaredMethodLjava_lang_String$Ljava_lang_Class() {
		// Test for method java.lang.reflect.Method
		// java.lang.Class.getDeclaredMethod(java.lang.String, java.lang.Class
		// [])
		try {
			java.lang.reflect.Method m = TestClass.class.getDeclaredMethod(
					"pubMethod", new Class[0]);
			assertTrue("Returned incorrect method", ((Integer) (m.invoke(
					new TestClass(), new Class[0]))).intValue() == 2);
			m = TestClass.class.getDeclaredMethod("privMethod", new Class[0]);
			try {
				// Invoking private non-sub, non-package
				m.invoke(new TestClass(), new Class[0]);
			} catch (IllegalAccessException e) {
				// Correct
				return;
			}
		} catch (Exception e) {
			fail("Exception getting methods : " + e.getMessage());
		}
	}

	/**
	 * @tests java.lang.Class#getDeclaredMethods()
	 */
	public void test_getDeclaredMethods() {
		// Test for method java.lang.reflect.Method []
		// java.lang.Class.getDeclaredMethods()
		try {
			java.lang.reflect.Method[] m = TestClass.class.getDeclaredMethods();
			assertTrue("Returned incorrect number of methods", m.length == 3);
			m = SubTestClass.class.getDeclaredMethods();
			assertTrue("Returned incorrect number of methods", m.length == 0);
		} catch (Exception e) {
			fail("Exception getting methods : " + e.getMessage());
		}
	}

	/**
	 * @tests java.lang.Class#getDeclaringClass()
	 */
	public void test_getDeclaringClass() {
		// Test for method java.lang.Class java.lang.Class.getDeclaringClass()
		assertTrue(TestClass.class.getDeclaringClass().equals(ClassTest.class));
	}

	/**
	 * @tests java.lang.Class#getField(java.lang.String)
	 */
	public void test_getFieldLjava_lang_String() {
		// Test for method java.lang.reflect.Field
		// java.lang.Class.getField(java.lang.String)
		try {
			java.lang.reflect.Field f = TestClass.class.getField("pubField");
			assertTrue("Returned incorrect field",
					f.getInt(new TestClass()) == 2);
			try {
				f = TestClass.class.getField("privField");
			} catch (NoSuchFieldException e) {
				// Correct
				return;
			}
			fail("Private field access failed to throw exception");
		} catch (Exception e) {
			fail("Exception getting fields : " + e.getMessage());
		}
	}

	/**
	 * @tests java.lang.Class#getFields()
	 */
	public void test_getFields() {
		// Test for method java.lang.reflect.Field []
		// java.lang.Class.getFields()
		try {
			java.lang.reflect.Field[] f = TestClass.class.getFields();
			assertTrue("Incorrect number of fields returned: " + f.length,
					f.length == 2);
			f = SubTestClass.class.getFields();
			assertTrue("Incorrect number of fields returned: " + f.length,
					f.length == 2);// Check inheritance of pub fields
		} catch (Exception e) {
			fail("Exception getting fields : " + e.getMessage());
		}
	}

	/**
	 * @tests java.lang.Class#getInterfaces()
	 */
	public void test_getInterfaces() {
		// Test for method java.lang.Class [] java.lang.Class.getInterfaces()
		Class[] interfaces;
		List interfaceList;
		interfaces = java.lang.Object.class.getInterfaces();
		assertTrue("Incorrect interface list for Object",
				interfaces.length == 0);
		interfaceList = Arrays.asList(java.util.Vector.class.getInterfaces());
		assertTrue("Incorrect interface list for Vector", interfaceList
				.contains(java.lang.Cloneable.class)
				&& interfaceList.contains(java.io.Serializable.class)
				&& interfaceList.contains(java.util.List.class));
	}

	/**
	 * @tests java.lang.Class#getMethod(java.lang.String, java.lang.Class[])
	 */
	public void test_getMethodLjava_lang_String$Ljava_lang_Class() {
		// Test for method java.lang.reflect.Method
		// java.lang.Class.getMethod(java.lang.String, java.lang.Class [])
		try {
			java.lang.reflect.Method m = TestClass.class.getMethod("pubMethod",
					new Class[0]);
			assertTrue("Returned incorrect method", ((Integer) (m.invoke(
					new TestClass(), new Class[0]))).intValue() == 2);
			try {
				m = TestClass.class.getMethod("privMethod", new Class[0]);
			} catch (NoSuchMethodException e) {
				// Correct
				return;
			}
			fail("Failed to throw exception accessing private method");
		} catch (Exception e) {
			fail("Exception getting methods : " + e.getMessage());
		}
	}

	/**
	 * @tests java.lang.Class#getMethods()
	 */
	public void test_getMethods() {
		// Test for method java.lang.reflect.Method []
		// java.lang.Class.getMethods()
		try {
			java.lang.reflect.Method[] m = TestClass.class.getMethods();
			assertTrue("Returned incorrect number of methods: " + m.length,
					m.length == 2 + Object.class.getMethods().length);
			m = SubTestClass.class.getMethods();
			assertTrue("Returned incorrect number of sub-class methods: "
					+ m.length,
					m.length == 2 + Object.class.getMethods().length);
			// Number of inherited methods
		} catch (Exception e) {
			fail("Exception getting methods : " + e.getMessage());
		}
	}

	/**
	 * @tests java.lang.Class#getModifiers()
	 */
	public void test_getModifiers() {
		// Test for method int java.lang.Class.getModifiers()

		class defaultClass {
		}
		;

		final int publicFlag = 0x0001;
		final int privateFlag = 0x0002;
		final int protectedFlag = 0x0004;
		assertTrue("default class is public", (defaultClass.class
				.getModifiers() & publicFlag) == 0);
		assertTrue("default class is protected", (defaultClass.class
				.getModifiers() & protectedFlag) == 0);
		assertTrue("default class is private", (defaultClass.class
				.getModifiers() & privateFlag) == 0);
		assertTrue("public class is not public",
				(Object.class.getModifiers() & publicFlag) != 0);
		assertTrue("public class is protected",
				(Object.class.getModifiers() & protectedFlag) == 0);
		assertTrue("public class is private",
				(Object.class.getModifiers() & privateFlag) == 0);
	}

	/**
	 * @tests java.lang.Class#getName()
	 */
	public void test_getName() {
		// Test for method java.lang.String java.lang.Class.getName()
		String className = null;
		try {
			className = Class.forName("java.lang.Object").getName();
		} catch (ClassNotFoundException e) {
			fail("Should be able to find java.lang.Object");
			return;
		}
		assertTrue("Class getName printed wrong value:" + className, className
				.equals("java.lang.Object"));
		assertTrue("Class getName printed wrong value:" + int.class.getName(),
				int.class.getName().equals("int"));
		try {
			className = Class.forName("[I").getName();
		} catch (ClassNotFoundException e) {
			fail("Should be able to find class [I");
			return;
		}
		assertTrue("Class getName printed wrong value:" + className, className
				.equals("[I"));

		try {
			className = Class.forName("[Ljava.lang.Object;").getName();
		} catch (ClassNotFoundException e) {
			fail("Should be able to find [Ljava.lang.Object;");
			return;
		}

		assertTrue("Class getName printed wrong value:" + className, className
				.equals("[Ljava.lang.Object;"));
	}

	/**
	 * @tests java.lang.Class#getResource(java.lang.String)
	 */
	public void test_getResourceLjava_lang_String() {
		// Test for method java.net.URL
		// java.lang.Class.getResource(java.lang.String)
		String name = Support_Resources.RESOURCE_PACKAGE + "hyts_compressD.txt";
		System.setSecurityManager(new SecurityManager());
		try {
			java.net.URL res = Object.class.getResource("Object.class");
			assertTrue("Object.class should not be found", res == null);

			assertTrue("Security: the file " + name
					+ " can not be found in this directory", ClassTest.class
					.getResource(name) != null);
		} finally {
			System.setSecurityManager(null);
		}
	}

	/**
	 * @tests java.lang.Class#getResourceAsStream(java.lang.String)
	 */
	public void test_getResourceAsStreamLjava_lang_String() {
		// Test for method java.io.InputStream
		// java.lang.Class.getResourceAsStream(java.lang.String)

		String name = Support_Resources.RESOURCE_PACKAGE + "hyts_compressD.txt";
		Class clazz = null;
		try {
			clazz = Class.forName("tests.api.java.lang.ClassTest");
		} catch (ClassNotFoundException e) {
			fail(
					"Should be able to find the class tests.api.java.lang.ClassTest");
		}
		assertTrue("the file " + name + " can not be found in this directory",
				clazz.getResourceAsStream(name) != null);

		System.setSecurityManager(new SecurityManager());
		try {
			InputStream res = Object.class.getResourceAsStream("Object.class");
			assertTrue("Object.class should not be found", res == null);
			InputStream is = ClassTest.class.getResourceAsStream(name);
			assertTrue("Security: the file " + name
					+ " can not be found in this directory", is != null);
		} finally {
			System.setSecurityManager(null);
		}

		name = "hyts_Foo.c";
		assertTrue("the file " + name
				+ " should not be found in this directory", clazz
				.getResourceAsStream(name) == null);
		assertTrue("the file " + name
				+ " can not be found in the root directory", clazz
				.getResourceAsStream("/" + name) != null);

		try {
			clazz = Class.forName("java.lang.Object");
		} catch (ClassNotFoundException e) {
			fail("Should be able to find the class java.lang.Object");
		}
		InputStream str = clazz.getResourceAsStream("Class.class");
		assertTrue(
				"java.lang.Object couldn't find its class with getResource...",
				str != null);
		try {
			assertTrue("Cannot read single byte", str.read() != -1);
			assertTrue("Cannot read multiple bytes", str.read(new byte[5]) == 5);
			str.close();
		} catch (IOException e) {
			fail("Exception while closing resource stream 1.");
		}

		InputStream str2 = getClass().getResourceAsStream(
				Support_Resources.RESOURCE_PACKAGE + "hyts_compressD.txt");
		assertTrue("Can't find resource", str2 != null);
		try {
			assertTrue("Cannot read single byte", str2.read() != -1);
			assertTrue("Cannot read multiple bytes",
					str2.read(new byte[5]) == 5);
			str2.close();
		} catch (IOException e) {
			fail("IOException while closing resource stream 2 : "
					+ e.getMessage());
		}

	}

	/**
	 * @tests java.lang.Class#getSuperclass()
	 */
	public void test_getSuperclass() {
		// Test for method java.lang.Class java.lang.Class.getSuperclass()

		assertTrue("Object has a superclass???",
				Object.class.getSuperclass() == null);
		assertTrue(
				"Normal class has bogus superclass",
				java.io.FileInputStream.class.getSuperclass() == java.io.InputStream.class);
		assertTrue("Array class has bogus superclass",
				java.io.FileInputStream[].class.getSuperclass() == Object.class);
		assertTrue("Base class has a superclass",
				int.class.getSuperclass() == null);
		assertTrue("Interface class has a superclass", Cloneable.class
				.getSuperclass() == null);
	}

	/**
	 * @tests java.lang.Class#isArray()
	 */
	public void test_isArray() {
		// Test for method boolean java.lang.Class.isArray()
		assertTrue("Non-array type claims to be.", !int.class.isArray());
		Class clazz = null;
		try {
			clazz = Class.forName("[I");
		} catch (ClassNotFoundException e) {
			fail("Should be able to find the class [I");
		}
		assertTrue("int Array type claims not to be.", clazz.isArray());

		try {
			clazz = Class.forName("[Ljava.lang.Object;");
		} catch (ClassNotFoundException e) {
			fail("Should be able to find the class [Ljava.lang.Object;");
		}

		assertTrue("Object Array type claims not to be.", clazz.isArray());

		try {
			clazz = Class.forName("java.lang.Object");
		} catch (ClassNotFoundException e) {
			fail("Should be able to find the class java.lang.Object");
		}
		assertTrue("Non-array Object type claims to be.", !clazz.isArray());
	}

	/**
	 * @tests java.lang.Class#isAssignableFrom(java.lang.Class)
	 */
	public void test_isAssignableFromLjava_lang_Class() {
		// Test for method boolean
		// java.lang.Class.isAssignableFrom(java.lang.Class)
		Class clazz1 = null;
		Class clazz2 = null;
		try {
			clazz1 = Class.forName("java.lang.Object");
		} catch (ClassNotFoundException e) {
			fail("Should be able to find the class java.lang.Object");
		}
		try {
			clazz2 = Class.forName("java.lang.Class");
		} catch (ClassNotFoundException e) {
			fail("Should be able to find the class java.lang.Class");
		}
		assertTrue("returned false for superclass", clazz1
				.isAssignableFrom(clazz2));

		try {
			clazz1 = Class.forName("tests.api.java.lang.ClassTest$TestClass");
		} catch (ClassNotFoundException e) {
			fail(
					"Should be able to find the class tests.api.java.lang.ClassTest$TestClass");
		}
		assertTrue("returned false for same class", clazz1
				.isAssignableFrom(clazz1));

		try {
			clazz1 = Class.forName("java.lang.Runnable");
		} catch (ClassNotFoundException e) {
			fail("Should be able to find the class java.lang.Runnable");
		}
		try {
			clazz2 = Class.forName("java.lang.Thread");
		} catch (ClassNotFoundException e) {
			fail("Should be able to find the class java.lang.Thread");
		}
		assertTrue("returned false for implemented interface", clazz1
				.isAssignableFrom(clazz2));
	}

	/**
	 * @tests java.lang.Class#isInterface()
	 */
	public void test_isInterface() {
		// Test for method boolean java.lang.Class.isInterface()
		assertTrue("Prim type claims to be interface.", !int.class
				.isInterface());
		Class clazz = null;
		try {
			clazz = Class.forName("[I");
		} catch (ClassNotFoundException e) {
			fail("Should be able to find the class [I");
		}
		assertTrue("Prim Array type claims to be interface.", !clazz
				.isInterface());

		try {
			clazz = Class.forName("java.lang.Runnable");
		} catch (ClassNotFoundException e) {
			fail("Should be able to find the class java.lang.Runnable");
		}
		assertTrue("Interface type claims not to be interface.", clazz
				.isInterface());

		try {
			clazz = Class.forName("java.lang.Object");
		} catch (ClassNotFoundException e) {
			fail("Should be able to find the class java.lang.Object");
		}
		assertTrue("Object type claims to be interface.", !clazz.isInterface());

		try {
			clazz = Class.forName("[Ljava.lang.Object;");
		} catch (ClassNotFoundException e) {
			fail("Should be able to find the class [Ljava.lang.Object;");
		}
		assertTrue("Array type claims to be interface.", !clazz.isInterface());
	}

	/**
	 * @tests java.lang.Class#isPrimitive()
	 */
	public void test_isPrimitive() {
		// Test for method boolean java.lang.Class.isPrimitive()
		assertTrue("Interface type claims to be primitive.", !Runnable.class
				.isPrimitive());
		assertTrue("Object type claims to be primitive.", !Object.class
				.isPrimitive());
		assertTrue("Prim Array type claims to be primitive.", !int[].class
				.isPrimitive());
		assertTrue("Array type claims to be primitive.", !Object[].class
				.isPrimitive());
		assertTrue("Prim type claims not to be primitive.", int.class
				.isPrimitive());
		assertTrue("Object type claims to be primitive.", !Object.class
				.isPrimitive());
	}

	/**
	 * @tests java.lang.Class#newInstance()
	 */
	public void test_newInstance() {
		// Test for method java.lang.Object java.lang.Class.newInstance()

		Class clazz = null;
		try {
			try {
				clazz = Class.forName("java.lang.Object");
			} catch (ClassNotFoundException e) {
				fail("Should be able to find the class java.lang.Object");
			}
			assertTrue("new object instance was null",
					clazz.newInstance() != null);
		} catch (Exception e) {
			fail("Unexpected exception " + e + " in newInstance()");
		}
		try {
			try {
				clazz = Class.forName("java.lang.Throwable");
			} catch (ClassNotFoundException e) {
				fail(
						"Should be able to find the class java.lang.Throwable");
			}
			assertTrue("new Throwable instance was not a throwable", clazz
					.newInstance().getClass() == clazz);
		} catch (Exception e) {
			fail("Unexpected exception " + e + " in newInstance()");
		}
		int r = 0;
		try {
			try {
				clazz = Class.forName("java.lang.Integer");
			} catch (ClassNotFoundException e) {
				fail(
						"Should be able to find the class java.lang.Integer");
			}
			assertTrue(
					"Allowed to do newInstance, when no default constructor",
					clazz.newInstance() != null || clazz.newInstance() == null);
		} catch (Exception e) {
			r = 1;
		}
		assertTrue(
				"Exception for instantiating a newInstance with no default constructor is not thrown",
				r == 1);
		// There needs to be considerably more testing here.
	}

	/**
	 * @tests java.lang.Class#toString()
	 */
	public void test_toString() {
		// Test for method java.lang.String java.lang.Class.toString()
		assertTrue(
				"Class toString printed wrong value:" + int.class.toString(),
				int.class.toString().equals("int"));
		Class clazz = null;
		try {
			clazz = Class.forName("[I");
		} catch (ClassNotFoundException e) {
			fail("Should be able to find the class [I");
		}
		assertTrue("Class toString printed wrong value:" + clazz.toString(),
				clazz.toString().equals("class [I"));

		try {
			clazz = Class.forName("java.lang.Object");
		} catch (ClassNotFoundException e) {
			fail("Should be able to find the class java.lang.Object");
		}
		assertTrue("Class toString printed wrong value:" + clazz.toString(),
				clazz.toString().equals("class java.lang.Object"));

		try {
			clazz = Class.forName("[Ljava.lang.Object;");
		} catch (ClassNotFoundException e) {
			fail("Should be able to find the class [Ljava.lang.Object;");
		}
		assertTrue("Class toString printed wrong value:" + clazz.toString(),
				clazz.toString().equals("class [Ljava.lang.Object;"));
	}

	/**
	 * Sets up the fixture, for example, open a network connection. This method
	 * is called before a test is executed.
	 */
	protected void setUp() {
	}

	/**
	 * Tears down the fixture, for example, close a network connection. This
	 * method is called after a test is executed.
	 */
	protected void tearDown() {
	}
}
