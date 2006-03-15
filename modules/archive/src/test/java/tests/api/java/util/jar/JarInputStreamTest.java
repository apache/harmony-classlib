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
package tests.api.java.util.jar;



import java.net.URL;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.jar.JarEntry;
import java.util.jar.JarInputStream;
import java.util.jar.Manifest;

import tests.support.resource.Support_Resources;

public class JarInputStreamTest extends junit.framework.TestCase {
	// a 'normal' jar file
	String jarName;

	// same as patch.jar but without a manifest file
	String jarName2;

	final String entryName = "foo/bar/A.class";

	final String entryName2 = "Blah.txt";

	/**
	 * @tests java.util.jar.JarInputStream#JarInputStream(java.io.InputStream)
	 */
	public void test_ConstructorLjava_io_InputStream() {
		// Test for method java.util.jar.JarInputStream(java.io.InputStream)
		try {
			java.io.InputStream is = new URL(jarName).openConnection()
					.getInputStream();
			boolean hasCorrectEntry = false;
			JarInputStream jis = new JarInputStream(is);
			assertTrue("The jar input stream should have a manifest", jis
					.getManifest() != null);
			JarEntry je = jis.getNextJarEntry();
			while (je != null) {
				if (je.getName().equals(entryName))
					hasCorrectEntry = true;
				je = jis.getNextJarEntry();
			}
			assertTrue(
					"The jar input stream does not contain the correct entries",
					hasCorrectEntry);
		} catch (Exception e) {
			fail("Exception during test: " + e.toString());
		}

	}

	/**
	 * @tests java.util.jar.JarInputStream#getManifest()
	 */
	public void test_getManifest() {
		// Test for method java.util.jar.Manifest
		// java.util.jar.JarInputStream.getManifest()
		try {
			Manifest m;

			java.io.InputStream is = new URL(jarName2).openConnection()
					.getInputStream();
			JarInputStream jis = new JarInputStream(is);
			m = jis.getManifest();
			assertTrue("The jar input stream should not have a manifest",
					m == null);

			is = new URL(jarName).openConnection().getInputStream();
			jis = new JarInputStream(is);
			m = jis.getManifest();
			assertTrue("The jar input stream should have a manifest", m != null);
		} catch (Exception e) {
			fail("Exception during test: " + e.toString());
		}

	}

	/**
	 * @tests java.util.jar.JarInputStream#getNextJarEntry()
	 */
	public void test_getNextJarEntry() {
		// Test for method java.util.jar.JarEntry
		// java.util.jar.JarInputStream.getNextJarEntry()
		final Set desired = new HashSet(Arrays.asList(new String[] { "foo/",
				"foo/bar/", "foo/bar/A.class", "Blah.txt" }));
		Set actual = new HashSet();
		try {
			java.io.InputStream is = new URL(jarName).openConnection()
					.getInputStream();
			JarInputStream jis = new JarInputStream(is);
			JarEntry je = jis.getNextJarEntry();
			while (je != null) {
				actual.add(je.toString());
				je = jis.getNextJarEntry();
			}
		} catch (Exception e) {
			fail("Exception during test: " + e.toString());
		}
		assertTrue("Set of entries is not correct", actual.equals(desired));
	}

	/**
	 * Sets up the fixture, for example, open a network connection. This method
	 * is called before a test is executed.
	 */
	protected void setUp() {
		jarName = Support_Resources.getURL("morestuff/hyts_patch.jar");
		jarName2 = Support_Resources.getURL("morestuff/hyts_patch2.jar");
	}

	/**
	 * Tears down the fixture, for example, close a network connection. This
	 * method is called after a test is executed.
	 */
	protected void tearDown() {
	}

}
