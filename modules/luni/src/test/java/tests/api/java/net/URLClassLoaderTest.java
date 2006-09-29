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

package tests.api.java.net;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.net.URLStreamHandler;
import java.net.URLStreamHandlerFactory;
import java.util.Enumeration;
import java.util.NoSuchElementException;

import tests.support.Support_Configuration;
import tests.support.resource.Support_Resources;

public class URLClassLoaderTest extends junit.framework.TestCase {

	class BogusClassLoader extends ClassLoader {
		public URL getResource(String res) {
			try {
				return new URL("http://test/BogusClassLoader");
			} catch (MalformedURLException e) {
				return null;
			}
		}
	}

	URLClassLoader ucl;

	/**
	 * @tests java.net.URLClassLoader#URLClassLoader(java.net.URL[])
	 */
	public void test_Constructor$Ljava_net_URL() {
		try {
			// ClassLoader cl = new BogusClassLoader();
			URL[] u = new URL[0];
			ucl = new URLClassLoader(u);
			assertTrue("Failed to set parent", ucl != null
					&& ucl.getParent() == URLClassLoader.getSystemClassLoader());

			URLClassLoader loader = new URLClassLoader(new URL[] { null });
			boolean exception = false;
			try {
				Class.forName("test", false, loader);
			} catch (ClassNotFoundException e) {
				exception = true;
			}
			assertTrue("Should throw ClassNotFoundException", exception);
		} catch (Exception e) {
			fail("Exception during test : " + e.getMessage());
		}
	}

	/**
	 * @tests java.net.URLClassLoader#URLClassLoader(java.net.URL[],
	 *        java.lang.ClassLoader)
	 */
	public void test_Constructor$Ljava_net_URLLjava_lang_ClassLoader() {
		ClassLoader cl = new BogusClassLoader();
		URL[] u = new URL[0];
		ucl = new URLClassLoader(u, cl);
		URL res = ucl.getResource("J");
		assertEquals("Failed to set parent", "/BogusClassLoader", res == null ? false : res.getFile()
				);
	}

	/**
	 * @tests java.net.URLClassLoader#findResources(java.lang.String)
	 */
	public void test_findResourcesLjava_lang_String() {
		Enumeration res = null;
		String[] resValues = { "This is a test resource file.",
				"This is a resource from a subdir" };
		try {
			URL[] urls = new URL[2];
			urls[0] = new URL(Support_Resources.getResourceURL("/"));
			urls[1] = new URL(Support_Resources.getResourceURL("/subdir1/"));
			ucl = new URLClassLoader(urls);
			res = ucl.findResources("RESOURCE.TXT");

		} catch (Exception e) {
			fail("Exception during findResource : " + e.getMessage());
		}
		
		if (res == null) {
			fail("Failed to locate resources");
		}

		int i = 0;
		while (res.hasMoreElements()) {
			StringBuffer sb = new StringBuffer();
			try {
				java.io.InputStream is = ((URL) res.nextElement()).openStream();
				int c;
				while ((c = is.read()) != -1)
					sb.append((char) c);
				assertTrue("Returned incorrect resource/or in wrong order: "
						+ sb.toString(), sb.toString().equals(resValues[i++]));
			} catch (java.io.IOException e) {
				fail("Exception during findResourcesTest: "
						+ e.toString());
			}
		}
		assertTrue("Incorrect number of resources returned: " + i, i == 2);
	}

	/**
	 * @tests java.net.URLClassLoader#getURLs()
	 */
	public void test_getURLs() {
		try {
			URL[] urls = new URL[4];
			urls[0] = new URL("http://" + Support_Configuration.HomeAddress);
			urls[1] = new URL("http://" + Support_Configuration.TestResources
					+ "/");
			urls[2] = new URL("ftp://" + Support_Configuration.TestResources
					+ "/");
			urls[3] = new URL("jar:file:c://"
					+ Support_Configuration.TestResources + "!/");
			ucl = new URLClassLoader(urls);
			URL[] ucUrls = ucl.getURLs();
			for (int i = 0; i < urls.length; i++)
				assertTrue("Returned incorrect URL[]", urls[i]
						.equals(ucUrls[i]));
		} catch (java.io.IOException e) {
			fail("Exception during getURLS test : " + e.getMessage());
		}
	}

	/**
	 * @tests java.net.URLClassLoader#newInstance(java.net.URL[])
	 */
	public void test_newInstance$Ljava_net_URL() {
		// Verify that loaded class' have correct permissions
		Class cl = null;
		URL res = null;
		URL[] urls = null;
		try {
			urls = new URL[1];
			urls[0] = new URL(Support_Resources.getResourceURL("/UCL/UCL.jar"));
			ucl = URLClassLoader.newInstance(urls);
			cl = ucl.loadClass("ucl.ResClass");
		} catch (Exception e) {
			fail("Exception during test : " + e.getMessage());
		} catch (Error e) {
			fail("Exception during test : " + e.getMessage());
		}

		try {
			res = cl.getClassLoader().getResource("XX.class");
			assertNotNull("Failed to load class", cl);
			assertNotNull(
					"Loaded class unable to access resource from same codeSource",
					res);
			cl = null;
		} catch (Error e) {
			fail("Test error : " + e.getMessage());
		}
		try {
			urls[0] = new URL("jar:"
					+ Support_Resources.getResourceURL("/UCL/UCL.jar!/"));
			ucl = URLClassLoader.newInstance(urls);
			cl = ucl.loadClass("ucl.ResClass");
		} catch (Exception e) {
			fail("Exception using explicit jar : " + e.getMessage());
		}
		assertNotNull("Failed to load class from explicit jar URL", cl);
	}

	/**
	 * @tests java.net.URLClassLoader#newInstance(java.net.URL[],
	 *        java.lang.ClassLoader)
	 */
	public void test_newInstance$Ljava_net_URLLjava_lang_ClassLoader() {
		ClassLoader cl = new BogusClassLoader();
		URL[] u = new URL[0];
		ucl = URLClassLoader.newInstance(u, cl);
		URL res = ucl.getResource("J");
		assertEquals("Failed to set parent", "/BogusClassLoader", res == null ? false : res.getFile()
				);
	}

	/**
	 * @tests java.net.URLClassLoader#URLClassLoader(java.net.URL[],
	 *        java.lang.ClassLoader, java.net.URLStreamHandlerFactory)
	 */
	public void test_Constructor$Ljava_net_URLLjava_lang_ClassLoaderLjava_net_URLStreamHandlerFactory() {
		class TestFactory implements URLStreamHandlerFactory {
			public URLStreamHandler createURLStreamHandler(String protocol) {
				return null;
			}
		}
		ClassLoader cl = new BogusClassLoader();
		URL[] u = new URL[0];
		ucl = new URLClassLoader(u, cl, new TestFactory());
		URL res = ucl.getResource("J");
		assertEquals("Failed to set parent", "/BogusClassLoader", res == null ? false : res.getFile()
				);
	}

	/**
	 * @tests java.net.URLClassLoader#findClass(java.lang.String)
	 */
	public void test_findClassLjava_lang_String() {
		File resources = Support_Resources.createTempFolder();
		String resPath = resources.toString();
		if (resPath.charAt(0) == '/' || resPath.charAt(0) == '\\')
			resPath = resPath.substring(1);

		try {
			java.net.URL[] urls = new java.net.URL[1];
			java.net.URLClassLoader ucl = null;
			boolean classFound;
			boolean exception;
			boolean goodException;
			Enumeration en;
			boolean resourcesFound;
			Support_Resources.copyFile(resources, "JarIndex", "hyts_11.jar");
			Support_Resources.copyFile(resources, "JarIndex", "hyts_12.jar");
			Support_Resources.copyFile(resources, "JarIndex", "hyts_13.jar");
			Support_Resources.copyFile(resources, "JarIndex", "hyts_14.jar");
			urls[0] = new URL("file:/" + resPath + "/JarIndex/hyts_11.jar");
			ucl = URLClassLoader.newInstance(urls, null);
			URL resURL = ucl.findResource("Test.txt");
			URL reference = new URL("jar:file:/" + resPath.replace('\\', '/')
					+ "/JarIndex/hyts_14.jar!/Test.txt");
			assertTrue("Resource not found: " + resURL + " ref: " + reference,
					resURL.equals(reference));

			classFound = false;
			try {
				Class c = Class.forName("cpack.CNothing", true, ucl);
				if (c != null)
					classFound = true;
			} catch (ClassNotFoundException e) {
				classFound = false;
			}
			assertTrue("Class not found (1)", classFound);

			Support_Resources.copyFile(resources, "JarIndex", "hyts_21.jar");
			Support_Resources.copyFile(resources, "JarIndex", "hyts_22.jar");
			Support_Resources.copyFile(resources, "JarIndex", "hyts_23.jar");
			urls[0] = new URL("file:/" + resPath + "/JarIndex/hyts_21.jar");
			ucl = URLClassLoader.newInstance(urls, null);
			en = ucl.findResources("bpack/");

			try {
				resourcesFound = true;
				URL url1 = (URL) en.nextElement();
				URL url2 = (URL) en.nextElement();
				System.out.println(url1);
				System.out.println(url2);
				resourcesFound = resourcesFound
						&& url1.equals(new URL("jar:file:/"
								+ resPath.replace('\\', '/')
								+ "/JarIndex/hyts_22.jar!/bpack/"));
				resourcesFound = resourcesFound
						&& url2.equals(new URL("jar:file:/"
								+ resPath.replace('\\', '/')
								+ "/JarIndex/hyts_23.jar!/bpack/"));
				if (en.hasMoreElements())
					resourcesFound = false;
			} catch (NoSuchElementException e) {
				resourcesFound = false;
			}
			assertTrue("Resources not found (1)", resourcesFound);

			classFound = false;
			try {
				Class c = Class.forName("bpack.Homer", true, ucl);
				if (c != null)
					classFound = true;
			} catch (ClassNotFoundException e) {
				classFound = false;
			}
			assertTrue("Class not found (2)", classFound);

			exception = false;
			try {
				Class.forName("bpack.Bart", true, ucl);
			} catch (org.apache.harmony.luni.util.InvalidJarIndexException e) {
				exception = true;
			} catch (ClassNotFoundException e) {
				exception = false;
			}
			assertTrue("InvalidJarIndexException should be thrown", exception);

			goodException = false;
			try {
				Class.forName("Main4", true, ucl);
			} catch (org.apache.harmony.luni.util.InvalidJarIndexException e) {
				goodException = false;
			} catch (ClassNotFoundException e) {
				goodException = true;
			}
			assertTrue("ClassNotFoundException should be thrown", goodException);

			Support_Resources
					.copyFile(resources, "JarIndex", "hyts_22-new.jar");
			urls[0] = new URL("file:/" + resPath + "/JarIndex/hyts_22-new.jar");
			ucl = URLClassLoader.newInstance(urls, null);
			assertNotNull("Cannot find resource",
					ucl.findResource("cpack/"));
			Support_Resources.copyFile(resources, "JarIndex", "hyts_11.jar");
			urls[0] = new URL("file:/" + resPath + "/JarIndex/hyts_31.jar");
			ucl = URLClassLoader.newInstance(urls, null);

			exception = false;
			try {
				Class.forName("cpack.Mock", true, ucl);
			} catch (org.apache.harmony.luni.util.InvalidJarIndexException e) {
				exception = false;
			} catch (ClassNotFoundException e) {
				exception = true;
			}
			assertTrue("InvalidJarIndexException should be thrown", exception);

			// testing circular reference
			Support_Resources.copyFile(resources, "JarIndex", "hyts_41.jar");
			Support_Resources.copyFile(resources, "JarIndex", "hyts_42.jar");
			urls[0] = new URL("file:/" + resPath + "/JarIndex/hyts_41.jar");
			ucl = URLClassLoader.newInstance(urls, null);
			en = ucl.findResources("bpack/");
			try {
				resourcesFound = true;
				resourcesFound = resourcesFound
						&& ((URL) en.nextElement()).equals(new URL("jar:file:/"
								+ resPath.replace('\\', '/')
								+ "/JarIndex/hyts_42.jar!/bpack/"));
				if (en.hasMoreElements())
					resourcesFound = false;
			} catch (NoSuchElementException e) {
				resourcesFound = false;
			}
			assertTrue("Resources not found (2)", resourcesFound);
		} catch (MalformedURLException e) {
			fail("Unexpected MalformedURLException during test : " + e);
		} catch (java.io.IOException e) {
			fail("Unexpected IOException during test : " + e.getMessage());
		}
	}

	/**
	 * @tests java.net.URLClassLoader#findResource(java.lang.String)
	 */
	public void test_findResourceLjava_lang_String() {
		URL res = null;
		try {
			URL[] urls = new URL[2];
			urls[0] = new URL("http://" + Support_Configuration.HomeAddress);
			urls[1] = new URL(Support_Resources.getResourceURL("/"));
			ucl = new URLClassLoader(urls);
			res = ucl.findResource("RESOURCE.TXT");
		} catch (Exception e) {
			fail("Exception during findResource : " + e.getMessage());
		}
		
		if (res == null) {
			fail("Failed to locate resource");
		}
		StringBuffer sb = new StringBuffer();
		try {
			java.io.InputStream is = res.openStream();

			int c;
			while ((c = is.read()) != -1)
				sb.append((char) c);
			is.close();
		} catch (java.io.IOException e) {
		}
		assertTrue("Returned incorrect resource", !sb.toString().equals(
				"This is a test resource file"));
	}

	/**
	 * @tests java.net.URLClassLoader#getResource(java.lang.String)
	 */
	public void test_getResourceLjava_lang_String() {
		try {
			URL url1 = new URL("file:///");
			java.net.URLClassLoader loader = new java.net.URLClassLoader(
					new URL[] { url1 }, null);
			long start = System.currentTimeMillis();
			// try without the leading /
			URL result = loader.getResource("dir1/file1");
			long end = System.currentTimeMillis();
			long time = end - start;
			if (time < 100)
				time = 100;

			start = System.currentTimeMillis();
			// try with the leading forward slash
			result = loader.getResource("/dir1/file1");
			end = System.currentTimeMillis();
			long uncTime = end - start;
			assertTrue("too long. UNC path formed? UNC time: " + uncTime
					+ " regular time: " + time, uncTime <= (time * 4));
		} catch (MalformedURLException e) {
			fail("unexpected: " + e);
		}
	}

	protected void setUp() {

	}

	protected void tearDown() {

	}
}
