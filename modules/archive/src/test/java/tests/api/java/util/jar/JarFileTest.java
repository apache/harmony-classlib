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



import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.security.cert.Certificate;
import java.util.Enumeration;
import java.util.Vector;
import java.util.jar.Attributes;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.jar.JarOutputStream;
import java.util.jar.Manifest;
import java.util.zip.ZipEntry;

import tests.support.Support_PlatformFile;
import tests.support.resource.Support_Resources;

public class JarFileTest extends junit.framework.TestCase {

	java.util.zip.ZipEntry zipEntry;

	java.util.jar.JarEntry jarEntry;

	java.util.jar.JarFile jarFile;

	final String jarName = "hyts_patch.jar"; // a 'normal' jar file

	// same as patch.jar but without a manifest file
	final String jarName2 = "hyts_patch2.jar"; 

	final String jarName3 = "hyts_manifest1.jar";

	final String jarName4 = "hyts_signed.jar";

	final String entryName = "foo/bar/A.class";

	final String entryName2 = "Blah.txt";

	final String entryName3 = "coucou/FileAccess.class";

	File resources;

	/**
	 * @tests java.util.jar.JarFile#JarFile(java.io.File)
	 */
	public void test_ConstructorLjava_io_File() {
		// Test for method java.util.jar.JarFile(java.io.File)
		/*
		 * try { assertTrue("Error in created file", new JarFile(new
		 * java.io.File(jarName)).getEntry(entryName).getName().equals(entryName)); }
		 * catch (Exception e) { fail("Exception during test: " +
		 * e.toString()); }
		 */
	}

	/**
	 * @tests java.util.jar.JarFile#JarFile(java.lang.String)
	 */
	public void test_ConstructorLjava_lang_String() {
		// Test for method java.util.jar.JarFile(java.lang.String)
		/*
		 * try { assertTrue("Error in created file", new
		 * JarFile(jarName).getEntry(entryName).getName().equals(entryName)); }
		 * catch (Exception e) { fail("Exception during test: " +
		 * e.toString()); }
		 */
	}

	/**
	 * @tests java.util.jar.JarFile#entries()
	 */
	public void test_entries() {
		// Test for method java.util.Enumeration java.util.jar.JarFile.entries()

		/*
		 * Note only (and all of) the following should be contained in the file
		 * META-INF/ META-INF/MANIFEST.MF foo/ foo/bar/ foo/bar/A.class Blah.txt
		 */

		try {
			Support_Resources.copyFile(resources, null, jarName);
			JarFile jarFile = new JarFile(new File(resources, jarName));
			Enumeration e = jarFile.entries();
			int i = 0;
			while (e.hasMoreElements()) {
				i++;
				e.nextElement();
			}
			jarFile.close();
			assertTrue("Wrong number of elements--wanted 6, got: " + i, i == 6);
		} catch (Exception e) {
			fail("Exception during test: " + e.toString());
		}

		System.out.println("start");
		System.out.flush();

		try {
			Support_Resources.copyFile(resources, null, jarName);
			JarFile jarFile = new JarFile(new File(resources, jarName));
			Enumeration enumeration = jarFile.entries();
			jarFile.close();
			boolean pass = false;
			try {
				enumeration.hasMoreElements();
			} catch (IllegalStateException e) {
				pass = true;
			}
			assertTrue("hasMoreElements did not detect closed jar file", pass);
			Support_Resources.copyFile(resources, null, jarName);
			jarFile = new JarFile(new File(resources, jarName));
			enumeration = jarFile.entries();
			jarFile.close();
			pass = false;
			try {
				enumeration.nextElement();
			} catch (IllegalStateException e) {
				pass = true;
			}
			assertTrue("nextElement did not detect closed jar file", pass);
		} catch (Exception e) {
			fail("Exception during entries test: " + e.toString());
		}
		System.out.println("finish");
		System.out.flush();

	}

	/**
	 * @tests java.util.jar.JarFile#getJarEntry(java.lang.String)
	 */
	public void test_getJarEntryLjava_lang_String() {
		// Test for method java.util.jar.JarEntry
		// java.util.jar.JarFile.getJarEntry(java.lang.String)
		try {
			Support_Resources.copyFile(resources, null, jarName);
			JarFile jarFile = new JarFile(new File(resources, jarName));
			assertTrue("Error in returned entry", jarFile.getEntry(entryName)
					.getSize() == 311);
			jarFile.close();
		} catch (Exception e) {
			fail("Exception during test: " + e.toString());
		}

		// tests for signed jars
		// test all signed jars in the /Testres/Internal/SignedJars directory
		String jarDirUrl = Support_Resources
				.getResourceURL("/../internalres/signedjars");
		Vector signedJars = new Vector();
		try {
			InputStream is = new URL(jarDirUrl + "/jarlist.txt").openStream();
			while (is.available() > 0) {
				StringBuffer linebuff = new StringBuffer(80); // Typical line
				// length
				done: while (true) {
					int nextByte = is.read();
					switch (nextByte) {
					case -1:
						break done;
					case (byte) '\r':
						if (linebuff.length() == 0) {
							// ignore
						}
						break done;
					case (byte) '\n':
						if (linebuff.length() == 0) {
							// ignore
						}
						break done;
					default:
						linebuff.append((char) nextByte);
					}
				}
				if (linebuff.length() == 0) {
					break;
				}
				String line = linebuff.toString();
				signedJars.add(line);
			}
			is.close();
		} catch (IOException e) {
			// no list of jars found
		}

		for (int i = 0; i < signedJars.size(); i++) {
			String jarName = (String) signedJars.get(i);
			try {
				File file = Support_Resources.getExternalLocalFile(jarDirUrl
						+ "/" + jarName);
				JarFile jarFile = new JarFile(file, true);
				boolean foundCerts = false;
				Enumeration e = jarFile.entries();
				while (e.hasMoreElements()) {
					JarEntry entry = (JarEntry) e.nextElement();
					InputStream is = jarFile.getInputStream(entry);
					is.skip(100000);
					is.close();
					Certificate[] certs = entry.getCertificates();
					if (certs != null && certs.length > 0) {
						foundCerts = true;
						break;
					}
				}
				assertTrue(
						"No certificates found during signed jar test for jar \""
								+ jarName + "\"", foundCerts);
			} catch (IOException e) {
				fail("Exception during signed jar test for jar \""
						+ jarName + "\": " + e.toString());
			}
		}
	}

	/**
	 * @tests java.util.jar.JarFile#getManifest()
	 */
	public void test_getManifest() {
		// Test for method java.util.jar.Manifest
		// java.util.jar.JarFile.getManifest()
		try {
			Support_Resources.copyFile(resources, null, jarName);
			JarFile jarFile = new JarFile(new File(resources, jarName));
			assertTrue("Error--Manifest not returned",
					jarFile.getManifest() != null);
			jarFile.close();
		} catch (Exception e) {
			fail("Exception during 1st test: " + e.toString());
		}
		try {
			Support_Resources.copyFile(resources, null, jarName2);
			JarFile jarFile = new JarFile(new File(resources, jarName2));
			assertTrue("Error--should have returned null", jarFile
					.getManifest() == null);
			jarFile.close();
		} catch (Exception e) {
			fail("Exception during 2nd test: " + e.toString());
		}

		try {
			// jarName3 was created using the following test
			Support_Resources.copyFile(resources, null, jarName3);
			JarFile jarFile = new JarFile(new File(resources, jarName3));
			assertTrue("Should find manifest without verifying", jarFile
					.getManifest() != null);
			jarFile.close();
		} catch (Exception e) {
			fail("Exception during 3rd test: " + e.toString());
		}

		try {
			// this is used to create jarName3 used in the previous test
			Manifest manifest = new Manifest();
			Attributes attributes = manifest.getMainAttributes();
			attributes.put(new Attributes.Name("Manifest-Version"), "1.0");
			ByteArrayOutputStream manOut = new ByteArrayOutputStream();
			manifest.write(manOut);
			byte[] manBytes = manOut.toByteArray();
			File file = new File(Support_PlatformFile.getNewPlatformFile(
					"hyts_manifest1", ".jar"));
			JarOutputStream jarOut = new JarOutputStream(new FileOutputStream(
					file.getAbsolutePath()));
			ZipEntry entry = new ZipEntry("META-INF/");
			entry.setSize(0);
			jarOut.putNextEntry(entry);
			entry = new ZipEntry(JarFile.MANIFEST_NAME);
			entry.setSize(manBytes.length);
			jarOut.putNextEntry(entry);
			jarOut.write(manBytes);
			entry = new ZipEntry("myfile");
			entry.setSize(1);
			jarOut.putNextEntry(entry);
			jarOut.write(65);
			jarOut.close();
			JarFile jar = new JarFile(file.getAbsolutePath(), false);
			assertTrue("Should find manifest without verifying", jar
					.getManifest() != null);
			jar.close();
			file.delete();
		} catch (IOException e) {
			fail("IOException 3");
		}
	}

	/**
	 * @tests java.util.jar.JarFile#getInputStream(java.util.zip.ZipEntry)
	 */
	public void test_getInputStreamLjava_util_jar_JarEntry() {
		File localFile = null;
		try {
			Support_Resources.copyFile(resources, null, jarName);
			localFile = new File(resources, jarName);
		} catch (Exception e) {
			fail("Failed to create local file: " + e);
		}

		byte[] b = new byte[1024];
		try {
			JarFile jf = new JarFile(localFile);
			java.io.InputStream is = jf.getInputStream(jf.getEntry(entryName));
			jf.close();
			assertTrue("Returned invalid stream", is.available() > 0);
			int r = is.read(b, 0, 1024);
			is.close();
			StringBuffer sb = new StringBuffer(r);
			for (int i = 0; i < r; i++) {
				sb.append((char) (b[i] & 0xff));
			}
			String contents = sb.toString();
			assertTrue("Incorrect stream read", contents.indexOf("bar") > 0);
		} catch (Exception e) {
			fail("Exception during test: " + e.toString());
		}

		try {
			JarFile jf = new JarFile(localFile);
			InputStream in = jf.getInputStream(new JarEntry("invalid"));
			assertTrue("Got stream for non-existent entry", in == null);
		} catch (Exception e) {
			fail("Exception during test 2: " + e);
		}
	}

	/**
	 * @tests java.util.jar.JarFile#getInputStream(java.util.zip.ZipEntry)
	 */
	public void test_getInputStreamLjava_util_jar_JarEntry_subtest0() {
		File signedFile = null;
		try {
			Support_Resources.copyFile(resources, null, jarName4);
			signedFile = new File(resources, jarName4);
		} catch (Exception e) {
			fail("Failed to create local file 2: " + e);
		}

		try {
			JarFile jar = new JarFile(signedFile);
			JarEntry entry = new JarEntry(entryName3);
			InputStream in = jar.getInputStream(entry);
			in.read();
		} catch (Exception e) {
			fail("Exception during test 3: " + e);
		}

		try {
			JarFile jar = new JarFile(signedFile);
			JarEntry entry = new JarEntry(entryName3);
			InputStream in = jar.getInputStream(entry);
			in.read(new byte[1077]);
			assertTrue("found certificates", entry.getCertificates() == null);
		} catch (Exception e) {
			fail("Exception during test 4: " + e);
		}

		boolean exception = false;
		try {
			JarFile jar = new JarFile(signedFile);
			JarEntry entry = new JarEntry(entryName3);
			entry.setSize(1076);
			InputStream in = jar.getInputStream(entry);
			in.read(new byte[2048]);
		} catch (SecurityException e) {
			exception = true;
		} catch (Exception e) {
			fail("Exception during test 5: " + e);
		}
		assertTrue("Failed to throw SecurityException", exception);
	}

	/**
	 * Sets up the fixture, for example, open a network connection. This method
	 * is called before a test is executed.
	 */
	protected void setUp() {
		resources = Support_Resources.createTempFolder();

	}

	/**
	 * Tears down the fixture, for example, close a network connection. This
	 * method is called after a test is executed.
	 */
	protected void tearDown() {
	}

}
