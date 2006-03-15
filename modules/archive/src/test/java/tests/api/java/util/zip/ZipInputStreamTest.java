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
package tests.api.java.util.zip;



import java.util.zip.ZipInputStream;

import tests.support.resource.Support_Resources;

public class ZipInputStreamTest extends junit.framework.TestCase {
	// the file hyts_zipFile.zip used in setup needs to included as a resource
	java.util.zip.ZipFile zfile;

	java.util.zip.ZipEntry zentry;

	java.util.zip.ZipInputStream zis;

	/**
	 * @tests java.util.zip.ZipInputStream#ZipInputStream(java.io.InputStream)
	 */
	public void test_ConstructorLjava_io_InputStream() {
		// Test for method java.util.zip.ZipInputStream(java.io.InputStream)
		try {
			zentry = zis.getNextEntry();
			zis.closeEntry();
		} catch (java.io.IOException e) {
			fail("Failed to create ZipInputStream");
		}
	}

	/**
	 * @tests java.util.zip.ZipInputStream#close()
	 */
	public void test_close() {
		// Test for method void java.util.zip.ZipInputStream.close()
		try {
			zis.close();
			byte[] rbuf = new byte[10];
			zis.read(rbuf, 0, 1);
		} catch (java.io.IOException e) {
			return;
		}
		fail("Read data after stream was closed--is this an error?");
	}

	/**
	 * @tests java.util.zip.ZipInputStream#closeEntry()
	 */
	public void test_closeEntry() {
		// Test for method void java.util.zip.ZipInputStream.closeEntry()
		try {
			zentry = zis.getNextEntry();
			zis.closeEntry();
		} catch (java.io.IOException e) {
			fail("Exception during closeEntry test");
		}
	}

	/**
	 * @tests java.util.zip.ZipInputStream#getNextEntry()
	 */
	public void test_getNextEntry() {
		// Test for method java.util.zip.ZipEntry
		// java.util.zip.ZipInputStream.getNextEntry()
		try {
			assertTrue("getNextEntry failed", zis.getNextEntry() != null);
		} catch (java.io.IOException e) {
			fail("Exception during getNextEntry test");
		}
	}

	/**
	 * @tests java.util.zip.ZipInputStream#read(byte[], int, int)
	 */
	public void test_read$BII() {
		// Test for method int java.util.zip.ZipInputStream.read(byte [], int,
		// int)
		try {
			zentry = zis.getNextEntry();
			byte[] rbuf = new byte[(int) zentry.getSize()];
			int r = zis.read(rbuf, 0, rbuf.length);
			new String(rbuf, 0, r);
			assertTrue("Failed to read entry", r == 12);
		} catch (java.io.IOException e) {
			fail("Exception during read test");
		}
	}

	/**
	 * @tests java.util.zip.ZipInputStream#skip(long)
	 */
	public void test_skipJ() {
		// Test for method long java.util.zip.ZipInputStream.skip(long)
		try {
			zentry = zis.getNextEntry();
			byte[] rbuf = new byte[(int) zentry.getSize()];
			zis.skip(2);
			int r = zis.read(rbuf, 0, rbuf.length);
			assertTrue("Failed to skip data", r == 10);
		} catch (java.io.IOException e) {
			fail("Unexpected1: " + e);
		}

		try {
			zentry = zis.getNextEntry();
			zentry = zis.getNextEntry();
			long s = zis.skip(1025);
			assertTrue("invalid skip: " + s, s == 1025);
		} catch (java.io.IOException e) {
			fail("Unexpected2: " + e);
		}
	}

	/**
	 * Sets up the fixture, for example, open a network connection. This method
	 * is called before a test is executed.
	 */
	protected void setUp() {

		try {
			java.io.InputStream is = Support_Resources
					.getStream("hyts_ZipFile.zip");
			if (is == null)
				System.out.println("file hyts_ZipFile.zip can not be found");
			zis = new ZipInputStream(is);
		} catch (Exception e) {
			System.out.println("Exception during ZipFile setup:");
			e.printStackTrace();
		}
	}

	/**
	 * Tears down the fixture, for example, close a network connection. This
	 * method is called after a test is executed.
	 */
	protected void tearDown() {

		if (zis != null)
			try {
				zis.close();
			} catch (Exception e) {
			}
	}

}
