/*
 *  Licensed to the Apache Software Foundation (ASF) under one or more
 *  contributor license agreements.  See the NOTICE file distributed with
 *  this work for additional information regarding copyright ownership.
 *  The ASF licenses this file to You under the Apache License, Version 2.0
 *  (the "License"); you may not use this file except in compliance with
 *  the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package org.apache.harmony.luni.tests.java.lang;

import java.io.File;
import java.lang.reflect.Method;
import java.net.URL;

import tests.support.resource.Support_Resources;

public class PackageTest extends junit.framework.TestCase {

	private File resources;

	private String resPath;

	/**
	 * There is a newer version of this class with some actual tests but since
	 * the class is not implemented they all fail. For now use the stub test
	 * methods.
	 */

	/**
	 * @tests java.lang.Package#getImplementationVendor()
	 * @tests java.lang.Package#getImplementationVersion()
	 * @tests java.lang.Package#getSpecificationTitle()
	 * @tests java.lang.Package#getSpecificationVendor()
	 * @tests java.lang.Package#getSpecificationVersion()
	 * @tests java.lang.Package#getImplementationTitle()
	 */
	public void test_helper_Attributes() {

		// All attributes in the package entry
		java.net.URL[] urls = new java.net.URL[1];
		Class<?> c = null;
		java.net.URLClassLoader ucl = null;
		Support_Resources.copyFile(resources, "Package",
				"hyts_all_attributes.jar");
		try {
			java.net.URL resourceURL = new URL("file:/" + resPath
					+ "/Package/hyts_all_attributes.jar");
			urls[0] = resourceURL;
			ucl = new java.net.URLClassLoader(urls, null);
			c = Class.forName("p.C", true, ucl);
			assertEquals("Package getImplementationTitle returns a wrong string (1)",
					
							"p Implementation-Title", c.getPackage().getImplementationTitle());
			assertEquals("Package getImplementationVendor returns a wrong string (1)",
					
							"p Implementation-Vendor", c.getPackage().getImplementationVendor());
			assertEquals("Package getImplementationVersion returns a wrong string (1)",
					"2.2.2", c.getPackage().getImplementationVersion());
			assertEquals("Package getSpecificationTitle returns a wrong string (1)",
					
							"p Specification-Title", c.getPackage().getSpecificationTitle());
			assertEquals("Package getSpecificationVendor returns a wrong string (1)",
					
							"p Specification-Vendor", c.getPackage().getSpecificationVendor());
			assertEquals("Package getSpecificationVersion returns a wrong string (1)",
					"2.2.2", c.getPackage().getSpecificationVersion());
		} catch (Exception e) {
			fail("Exception during helperAttributes test : " + e.getMessage());
		}

		// No entry for the package
		Support_Resources.copyFile(resources, "Package", "hyts_no_entry.jar");
		try {
			java.net.URL resourceURL = new URL("file:/" + resPath
					+ "/Package/hyts_no_entry.jar");
			urls[0] = resourceURL;
			ucl = new java.net.URLClassLoader(urls, null);
			c = Class.forName("p.C", true, ucl);
			assertEquals("Package getImplementationTitle returns a wrong string (2)",
					
							"MF Implementation-Title", c.getPackage().getImplementationTitle());
			assertEquals("Package getImplementationVendor returns a wrong string (2)",
					
							"MF Implementation-Vendor", c.getPackage().getImplementationVendor());
			assertEquals("Package getImplementationVersion returns a wrong string (2)",
					"5.3.b1", c.getPackage().getImplementationVersion());
			assertEquals("Package getSpecificationTitle returns a wrong string (2)",
					
							"MF Specification-Title", c.getPackage().getSpecificationTitle());
			assertEquals("Package getSpecificationVendor returns a wrong string (2)",
					
							"MF Specification-Vendor", c.getPackage().getSpecificationVendor());
			assertEquals("Package getSpecificationVersion returns a wrong string (2)",
					"1.2.3", c.getPackage().getSpecificationVersion());
		} catch (Exception e) {
			fail("Exception in helperAttributes test : " + e.getMessage());
		}

		// No attributes in the package entry
		Support_Resources.copyFile(resources, "Package",
				"hyts_no_attributes.jar");
		try {
			java.net.URL resourceURL = new URL("file:/" + resPath
					+ "/Package/hyts_no_attributes.jar");
			urls[0] = resourceURL;
			ucl = new java.net.URLClassLoader(urls, null);
			c = Class.forName("p.C", true, ucl);
			assertEquals("Package getImplementationTitle returns a wrong string (3)",
					
							"MF Implementation-Title", c.getPackage().getImplementationTitle());
			assertEquals("Package getImplementationVendor returns a wrong string (3)",
					
							"MF Implementation-Vendor", c.getPackage().getImplementationVendor());
			assertEquals("Package getImplementationVersion returns a wrong string (3)",
					"5.3.b1", c.getPackage().getImplementationVersion());
			assertEquals("Package getSpecificationTitle returns a wrong string (3)",
					
							"MF Specification-Title", c.getPackage().getSpecificationTitle());
			assertEquals("Package getSpecificationVendor returns a wrong string (3)",
					
							"MF Specification-Vendor", c.getPackage().getSpecificationVendor());
			assertEquals("Package getSpecificationVersion returns a wrong string (3)",
					"1.2.3", c.getPackage().getSpecificationVersion());
		} catch (Exception e) {
			fail("Exception during helperAttributes test : " + e.getMessage());
		}

		// Some attributes in the package entry
		Support_Resources.copyFile(resources, "Package",
				"hyts_some_attributes.jar");
		try {
			java.net.URL resourceURL = new URL("file:/" + resPath
					+ "/Package/hyts_some_attributes.jar");
			urls[0] = resourceURL;
			ucl = new java.net.URLClassLoader(urls, null);
			c = Class.forName("p.C", true, ucl);
			assertEquals("Package getImplementationTitle returns a wrong string (4)",
					
							"p Implementation-Title", c.getPackage().getImplementationTitle());
			assertEquals("Package getImplementationVendor returns a wrong string (4)",
					
							"MF Implementation-Vendor", c.getPackage().getImplementationVendor());
			assertEquals("Package getImplementationVersion returns a wrong string (4)",
					"2.2.2", c.getPackage().getImplementationVersion());
			assertEquals("Package getSpecificationTitle returns a wrong string (4)",
					
							"MF Specification-Title", c.getPackage().getSpecificationTitle());
			assertEquals("Package getSpecificationVendor returns a wrong string (4)",
					
							"p Specification-Vendor", c.getPackage().getSpecificationVendor());
			assertEquals("Package getSpecificationVersion returns a wrong string (4)",
					"2.2.2", c.getPackage().getSpecificationVersion());
		} catch (Exception e) {
			fail("Exception during helperAttributes test : " + e.getMessage());
		}

		// subdirectory Package
		Support_Resources.copyFile(resources, "Package", "hyts_pq.jar");
		try {
			java.net.URL resourceURL = new URL("file:/" + resPath
					+ "/Package/hyts_pq.jar");
			urls[0] = resourceURL;
			ucl = new java.net.URLClassLoader(urls, null);
			c = Class.forName("p.q.C", true, ucl);
			assertEquals("Package getImplementationTitle returns a wrong string (5)",
					
							"p Implementation-Title", c.getPackage().getImplementationTitle());
			assertEquals("Package getImplementationVendor returns a wrong string (5)",
					
							"p Implementation-Vendor", c.getPackage().getImplementationVendor());
			assertEquals("Package getImplementationVersion returns a wrong string (5)",
					"1.1.3", c.getPackage().getImplementationVersion());
			assertEquals("Package getSpecificationTitle returns a wrong string (5)",
					
							"p Specification-Title", c.getPackage().getSpecificationTitle());
			assertEquals("Package getSpecificationVendor returns a wrong string (5)",
					
							"p Specification-Vendor", c.getPackage().getSpecificationVendor());
			assertEquals("Package getSpecificationVersion returns a wrong string (5)",
					
							"2.2.0.0.0.0.0.0.0.0.0", c.getPackage().getSpecificationVersion());

		} catch (Exception e) {
			fail("Exception during helperAttributes test : " + e.getMessage());
		}
	}

	private static Object invokeMethod(Object obj, String name,
			Object[] parameters) {

		Class[] types = new Class[parameters.length];
		for (int i = 0; i < parameters.length; i++) {
			types[i] = parameters[i].getClass();
		}
		Class<?> c = null;
		if (obj instanceof Class)
			c = (Class) obj;
		else
			c = obj.getClass();
		while (c != null) {
			try {
				Method m = c.getDeclaredMethod(name, types);
				m.setAccessible(true);
				return m.invoke(obj, parameters);
			} catch (NoSuchMethodException e) {
				c = c.getSuperclass();
			} catch (Exception e) {
				break;
			}
		}
		return null;
	}

	/**
	 * @tests java.lang.Package#getName()
	 */
	public void test_getName() {
		java.net.URL[] urls = new java.net.URL[1];
		Class<?> c = null;
		java.net.URLClassLoader ucl = null;
		Support_Resources.copyFile(resources, "Package", "hyts_pq.jar");
		try {
			java.net.URL resourceURL = new URL("file:/" + resPath
					+ "/Package/hyts_pq.jar");
			urls[0] = resourceURL;
			ucl = new java.net.URLClassLoader(urls, null);
			c = Class.forName("p.q.C", true, ucl);
			assertEquals("Package getName returns a wrong string", "p.q", c.getPackage()
					.getName());

		} catch (Exception e) {
			fail("Exception during getName test : " + e.getMessage());
		}

	}

	/**
	 * @tests java.lang.Package#getPackage(java.lang.String)
	 */
	public void test_getPackageLjava_lang_String() {
		// Test for method java.lang.Package
		// java.lang.Package.getPackage(java.lang.String)
		Package.getPackage("java.lang");
		try {
			assertTrue("Package getPackage failed for java.lang", Package
					.getPackage("java.lang") == java.lang.Object.class
					.getPackage());
		} catch (Exception e) {
			fail("Unexpected exception " + e
					+ " in Package.getPackage(java.lang.String)");
		}

	}

	/**
	 * @tests java.lang.Package#getPackages()
	 */
	public void test_getPackages() {
		// Test for method java.lang.Package [] java.lang.Package.getPackages()
		java.net.URL[] urls = new java.net.URL[1];

		java.net.URLClassLoader ucl = null;
		Support_Resources.copyFile(resources, "Package", "hyts_pq.jar");
		try {
			java.net.URL resourceURL = new URL("file:/" + resPath
					+ "/Package/hyts_pq.jar");
			urls[0] = resourceURL;
			ucl = new java.net.URLClassLoader(urls, null);
			Class.forName("p.q.C", true, ucl);
			Package[] pckgs = (Package[]) invokeMethod(ucl, "getPackages",
					new Object[0]);
			boolean found = false;
			for (int i = 0; i < pckgs.length; i++)
				if (pckgs[i].getName().equals("p.q")) {
					found = true;
					break;
				}
			assertTrue("Package getPackages failed to retrieve a package",
					found);
		} catch (Exception e) {
			fail("Unexpected exception " + e
					+ " in Package.getPackages()");
		}
	}

	/**
	 * @tests java.lang.Package#hashCode()
	 */
	public void test_hashCode() {
		// Test for method int java.lang.Package.hashCode()
		Package p1 = Package.getPackage("java.lang");
		Package p2 = Package.getPackage("java.lang");
		if (p1 != null)
			assertTrue(
					"Package hashCode does not return the same hashcode for 2 packages with the same name",
					p1.hashCode() == p2.hashCode());
	}

	/**
	 * @tests java.lang.Package#isCompatibleWith(java.lang.String)
	 */
	public void test_isCompatibleWithLjava_lang_String() {
		// Test for method boolean
		// java.lang.Package.isCompatibleWith(java.lang.String)
		java.net.URL[] urls = new java.net.URL[1];
		Class<?> c = null;
		java.net.URLClassLoader ucl = null;
		Support_Resources.copyFile(resources, "Package", "hyts_c.jar");
		try {
			java.net.URL resourceURL = new URL("file:/" + resPath
					+ "/Package/hyts_c.jar");
			urls[0] = resourceURL;
			ucl = new java.net.URLClassLoader(urls, null);
			c = Class.forName("p.C", true, ucl);
			Package p = c.getPackage();
			assertTrue("Package isCompatibleWith fails with lower version", p
					.isCompatibleWith("2.1.9.") == true);
			assertTrue("Package isCompatibleWith fails with same version (1)",
					p.isCompatibleWith("2.2.0") == true);
			assertTrue("Package isCompatibleWith fails with same version (2)",
					p.isCompatibleWith("2.2") == true);
			assertTrue("Package isCompatibleWith fails with higher version", p
					.isCompatibleWith("2.2.0.0.1") == false);
		} catch (Exception e) {
			fail("Exception during isCompatibleWith test : " + e.getMessage());
		}
	}

	/**
	 * @tests java.lang.Package#isSealed()
	 */
	public void test_isSealed() {
		// Test for method boolean java.lang.Package.isSealed()
		java.net.URL[] urls = new java.net.URL[1];
		Class<?> c = null;
		java.net.URLClassLoader ucl = null;
		Support_Resources.copyFile(resources, "Package", "hyts_pq.jar");
		try {
			java.net.URL resourceURL = new URL("file:/" + resPath
					+ "/Package/hyts_pq.jar");
			urls[0] = resourceURL;
			ucl = new java.net.URLClassLoader(urls, null);
			c = Class.forName("p.q.C", true, ucl);
			assertTrue("Package isSealed returns wrong boolean", c.getPackage()
					.isSealed() == true);

		} catch (Exception e) {
			fail("Exception during isSealed test : " + e.getMessage());
		}

	}

	/**
	 * @tests java.lang.Package#isSealed(java.net.URL)
	 */
	public void test_isSealedLjava_net_URL() {
		// Test for method boolean java.lang.Package.isSealed(java.net.URL)
		java.net.URL[] urls = new java.net.URL[1];
		Class<?> c = null;
		java.net.URLClassLoader ucl = null;
		Support_Resources.copyFile(resources, "Package", "hyts_c.jar");
		try {
			java.net.URL resourceURL = new URL("file:/" + resPath
					+ "/Package/hyts_c.jar");
			urls[0] = resourceURL;
			ucl = new java.net.URLClassLoader(urls, null);
			c = Class.forName("p.C", true, ucl);
			assertTrue(
					"Package isSealed returns wrong boolean (1)",
					c.getPackage().isSealed(new URL("file:/" + resPath + "/")) == false);
			assertTrue("Package isSealed returns wrong boolean (2)",
					c.getPackage()
							.isSealed(
									new URL("file:/" + resPath
											+ "/Package/hyts_c.jar")) == true);
		} catch (Exception e) {
			fail("Exception during isSealed test : " + e.getMessage());
		}
	}

	/**
	 * @tests java.lang.Package#toString()
	 */
	public void test_toString() {
		// Test for method java.lang.String java.lang.Package.toString()
		java.net.URL[] urls = new java.net.URL[1];
		Class<?> c = null;
		java.net.URLClassLoader ucl = null;
		Support_Resources.copyFile(resources, "Package", "hyts_c.jar");
		try {
			java.net.URL resourceURL = new URL("file:/" + resPath
					+ "/Package/hyts_c.jar");
			urls[0] = resourceURL;
			ucl = java.net.URLClassLoader.newInstance(urls, null);
			c = Class.forName("p.C", true, ucl);
			assertTrue("Package toString returns wrong string", c.getPackage()
					.toString().length() > 0);
		} catch (Exception e) {
			fail("Exception during isSealed test : " + e.getMessage());
		}
	}

	@Override
    protected void setUp() {
		resources = Support_Resources.createTempFolder();
		resPath = resources.toString();
		if (resPath.charAt(0) == '/' || resPath.charAt(0) == '\\')
			resPath = resPath.substring(1);
	}
}
