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

package tests.api.java.net;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.JarURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.jar.JarOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import tests.support.resource.Support_Resources;

public class JarURLConnectionTest extends junit.framework.TestCase {

	JarURLConnection juc;

	URLConnection uc;

	/**
	 * @tests java.net.JarURLConnection#getAttributes()
	 */
	public void test_getAttributes() {
		try {
			URL u = new URL("jar:"
					+ Support_Resources.getResourceURL("/JUC/lf.jar!/swt.dll"));
			juc = (JarURLConnection) u.openConnection();
			java.util.jar.Attributes a = juc.getJarEntry().getAttributes();
			assertTrue("Returned incorrect Attributes", a.get(
					new java.util.jar.Attributes.Name("Digest-Algorithms"))
					.equals("SHA MD5"));
		} catch (java.io.IOException e) {
			fail("Exception during test : " + e.getMessage());
		}
	}

	/**
	 * @tests java.net.JarURLConnection#getEntryName()
	 */
	public void test_getEntryName() {
		try {
			URL u = new URL("jar:"
					+ Support_Resources.getResourceURL("/JUC/lf.jar!/plus.bmp"));
			juc = (JarURLConnection) u.openConnection();
			assertTrue("Returned incorrect entryName", juc.getEntryName()
					.equals("plus.bmp"));
			u = new URL("jar:"
					+ Support_Resources.getResourceURL("/JUC/lf.jar!/"));
			juc = (JarURLConnection) u.openConnection();
			assertTrue("Returned incorrect entryName",
					juc.getEntryName() == null);
		} catch (java.io.IOException e) {
			fail("IOException during test : " + e.getMessage());
		}
	}

	/**
	 * @tests java.net.JarURLConnection#getJarEntry()
	 */
	public void test_getJarEntry() {
		try {
			URL u = new URL("jar:"
					+ Support_Resources.getResourceURL("/JUC/lf.jar!/plus.bmp"));
			juc = (JarURLConnection) u.openConnection();
			assertTrue("Returned incorrect JarEntry", juc.getJarEntry()
					.getName().equals("plus.bmp"));
			u = new URL("jar:"
					+ Support_Resources.getResourceURL("/JUC/lf.jar!/"));
			juc = (JarURLConnection) u.openConnection();
			assertTrue("Returned incorrect JarEntry", juc.getJarEntry() == null);
		} catch (java.io.IOException e) {
			fail("IOException during test : " + e.getMessage());
		}
	}

	/**
	 * @tests java.net.JarURLConnection#getJarFile()
	 */
	public void test_getJarFile() {
		URL url = null;
		try {
			url = new URL("jar:"
					+ Support_Resources.getResourceURL("/JUC/lf.jar!/missing"));
		} catch (MalformedURLException e) {
			fail("Unexpected MalformedURLException : " + e.getMessage());
		} catch (java.io.IOException e) {
			fail("Unexpected IOException : " + e.getMessage());
		}
		JarURLConnection connection = null;
		try {
			connection = (JarURLConnection) url.openConnection();
		} catch (IOException e) {
			fail("Unexpected IOException : " + e.getMessage());
		}
		int exception = 0;
		try {
			connection.connect();
		} catch (IOException e) {
			exception = 1;
		}
		assertTrue("Did not throw exception on connect", exception == 1);
		exception = 0;
		try {
			connection.getJarFile();
		} catch (IOException e) {
			exception = 1;
		}
		assertTrue("Did not throw exception after connect", exception == 1);
		File resources = Support_Resources.createTempFolder();
		try {
			Support_Resources.copyFile(resources, null, "hyts_att.jar");
			File file = new File(resources.toString() + "/hyts_att.jar");
			URL fUrl1 = new URL("jar:file:" + file.getPath() + "!/");
			JarURLConnection con1 = (JarURLConnection) fUrl1.openConnection();
			ZipFile jf1 = con1.getJarFile();
			JarURLConnection con2 = (JarURLConnection) fUrl1.openConnection();
			ZipFile jf2 = con2.getJarFile();
			assertTrue("file: JarFiles not the same", jf1 == jf2);
			jf1.close();
			assertTrue("File should exist", file.exists());
			new URL("jar:" + Support_Resources.getResourceURL("/JUC/lf.jar!/"));
			con1 = (JarURLConnection) fUrl1.openConnection();
			jf1 = con1.getJarFile();
			con2 = (JarURLConnection) fUrl1.openConnection();
			jf2 = con2.getJarFile();
			assertTrue("http: JarFiles not the same", jf1 == jf2);
			jf1.close();
		} catch (IOException e) {
			e.printStackTrace();
			fail("Unexpected exception : " + e.getMessage());
		}
	}

	/**
	 * @tests java.net.JarURLConnection.getJarFile()
         * 
         * Regression test for HARMONY-29
	 */
	public void test_getJarFile29() throws Exception {
                File jarFile = File.createTempFile("1+2 3", "test.jar");
                jarFile.deleteOnExit();
                JarOutputStream out = new JarOutputStream(new FileOutputStream(jarFile));
                out.putNextEntry(new ZipEntry("test"));
                out.closeEntry();
                out.close();

                JarURLConnection conn = (JarURLConnection) new URL("jar:file:/"+jarFile.getAbsolutePath().replaceAll(" ", "%20")+"!/").openConnection();
                conn.getJarFile().entries();
        }

	/**
	 * @tests java.net.JarURLConnection#getJarFileURL()
	 */
	public void test_getJarFileURL() {
		try {
			URL fileURL = new URL(Support_Resources
					.getResourceURL("/JUC/lf.jar"));
			URL u = new URL("jar:"
					+ Support_Resources.getResourceURL("/JUC/lf.jar!/plus.bmp"));
			juc = (JarURLConnection) u.openConnection();
			assertTrue("Returned incorrect file URL", juc.getJarFileURL()
					.equals(fileURL));
		} catch (java.io.IOException e) {
			fail("IOException during test : " + e.getMessage());
		}
	}

	/**
	 * @tests java.net.JarURLConnection#getMainAttributes()
	 */
	public void test_getMainAttributes() {
		try {
			URL u = new URL("jar:"
					+ Support_Resources.getResourceURL("/JUC/lf.jar!/swt.dll"));
			juc = (JarURLConnection) u.openConnection();
			java.util.jar.Attributes a = juc.getMainAttributes();
			assertTrue("Returned incorrect Attributes", a.get(
					java.util.jar.Attributes.Name.MANIFEST_VERSION).equals(
					"1.0"));
		} catch (java.io.IOException e) {
			fail("IOException during test : " + e.getMessage());
		}
	}

	protected void setUp() {
	}

	protected void tearDown() {
	}
}
