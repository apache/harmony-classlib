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



import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.CRC32;
import java.util.zip.ZipEntry;
import java.util.zip.ZipException;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

public class ZipOutputStreamTest extends junit.framework.TestCase {

	ZipOutputStream zos;

	ByteArrayOutputStream bos;

	ZipInputStream zis;

	static final String data = "HelloWorldHelloWorldHelloWorldHelloWorldHelloWorldHelloWorldHelloWorldHelloWorldHelloWorldHelloWorldHelloWorldHelloWorldHelloWorld";

	/**
	 * @tests java.util.zip.ZipOutputStream#close()
	 */
	public void test_close() {
		boolean thrown = false;
		try {
			zos.close();
		} catch (ZipException e) {
			// Correct
			thrown = true;
		} catch (IOException e) {
			fail("Exception closing on stream with no entries");
		}
		if (!thrown)
			fail("Close on empty stream failed to throw exception");
		try {
			zos = new ZipOutputStream(bos);
			zos.putNextEntry(new ZipEntry("XX"));
			zos.closeEntry();
			zos.close();
		} catch (IOException e) {
			fail("Exception during close test: " + e.toString());
		}

	}

	/**
	 * @tests java.util.zip.ZipOutputStream#closeEntry()
	 */
	public void test_closeEntry() {
		try {
			ZipEntry ze = new ZipEntry("testEntry");
			ze.setTime(System.currentTimeMillis());
			zos.putNextEntry(ze);
			zos.write("Hello World".getBytes());
			zos.closeEntry();
			assertTrue("closeEntry failed to update required fields", ze
					.getSize() == 11
					&& ze.getCompressedSize() == 13);

		} catch (IOException e) {
			fail("Exception during closeEntry: " + e.toString());
		}
	}

	/**
	 * @tests java.util.zip.ZipOutputStream#finish()
	 */
	public void test_finish() {
		try {
			ZipEntry ze = new ZipEntry("test");
			zos.putNextEntry(ze);
			zos.write("Hello World".getBytes());
			zos.finish();
			assertTrue("Finish failed to closeCurrentEntry", ze.getSize() == 11);
		} catch (IOException e) {
			fail("Exception during finish test: " + e.toString());
		}
	}

	/**
	 * @tests java.util.zip.ZipOutputStream#putNextEntry(java.util.zip.ZipEntry)
	 */
	public void test_putNextEntryLjava_util_zip_ZipEntry() {
		try {
			ZipEntry ze = new ZipEntry("testEntry");
			ze.setTime(System.currentTimeMillis());
			zos.putNextEntry(ze);
			zos.write("Hello World".getBytes());
			zos.closeEntry();
			zos.close();
			zis = new ZipInputStream(
					new ByteArrayInputStream(bos.toByteArray()));
			ZipEntry ze2 = zis.getNextEntry();
			zis.closeEntry();
			assertTrue("Failed to write correct entry", ze.getName().equals(
					ze2.getName())
					&& ze.getCrc() == ze2.getCrc());
			try {
				zos.putNextEntry(ze);
			} catch (IOException e) {
				// Correct
				return;
			}
			fail(
					"Entry with incorrect setting failed to throw exception");
		} catch (IOException e) {
			fail("Exception during putNextEntry: " + e.toString());
		}

	}

	/**
	 * @tests java.util.zip.ZipOutputStream#setComment(java.lang.String)
	 */
	public void test_setCommentLjava_lang_String() {
		// There is no way to get the comment back, so no way to determine if
		// the comment is set correct
		try {
			zos.setComment("test setComment");
		} catch (Exception e) {
			fail("Trying to set comment failed");
		}
		try {
			zos.setComment(new String(new byte[0xFFFF + 1]));
			fail("Comment over 0xFFFF in length should throw exception");
		} catch (IllegalArgumentException e) {
			// Passed
		}
	}

	/**
	 * @tests java.util.zip.ZipOutputStream#setLevel(int)
	 */
	public void test_setLevelI() {
		try {
			ZipEntry ze = new ZipEntry("test");
			zos.putNextEntry(ze);
			zos.write(data.getBytes());
			zos.closeEntry();
			long csize = ze.getCompressedSize();
			zos.setLevel(9); // Max Compression
			zos.putNextEntry(ze = new ZipEntry("test2"));
			zos.write(data.getBytes());
			zos.closeEntry();
			assertTrue("setLevel failed", csize <= ze.getCompressedSize());
		} catch (IOException e) {
			fail("Exception during setLevel test: " + e.toString());
		}
	}

	/**
	 * @tests java.util.zip.ZipOutputStream#setMethod(int)
	 */
	public void test_setMethodI() {
		try {
			ZipEntry ze = new ZipEntry("test");
			zos.setMethod(ZipOutputStream.STORED);
			CRC32 tempCrc = new CRC32();
			tempCrc.update(data.getBytes());
			ze.setCrc(tempCrc.getValue());
			ze.setSize(new String(data).length());
			zos.putNextEntry(ze);
			zos.write(data.getBytes());
			zos.closeEntry();
			long csize = ze.getCompressedSize();
			zos.setMethod(ZipOutputStream.DEFLATED);
			zos.putNextEntry(ze = new ZipEntry("test2"));
			zos.write(data.getBytes());
			zos.closeEntry();
			assertTrue("setLevel failed", csize >= ze.getCompressedSize());
		} catch (IOException e) {
			fail("Exception during setLevel test: " + e.toString());
		}
	}

	/**
	 * @tests java.util.zip.ZipOutputStream#write(byte[], int, int)
	 */
	public void test_write$BII() {
		try {
			ZipEntry ze = new ZipEntry("test");
			zos.putNextEntry(ze);
			zos.write(data.getBytes());
			zos.closeEntry();
			zos.close();
			zos = null;
			zis = new ZipInputStream(
					new ByteArrayInputStream(bos.toByteArray()));
			zis.getNextEntry();
			byte[] b = new byte[data.length()];
			int r = 0;
			int count = 0;
			while (count != b.length
					&& (r = zis.read(b, count, b.length)) != -1)
				count += r;
			zis.closeEntry();
			assertTrue("Write failed to write correct bytes", new String(b)
					.equals(data));
		} catch (IOException e) {
			fail("Exception during write test: " + e.toString());
		}

		try {
			File f = File.createTempFile("testZip", "tst");
			f.deleteOnExit();
			FileOutputStream stream = new FileOutputStream(f);
			ZipOutputStream zip = new ZipOutputStream(stream);
			zip.setMethod(ZipEntry.STORED);

			try {
				zip.putNextEntry(new ZipEntry("Second"));
				fail("Not set an entry. Should have thrown ZipException.");
			} catch (Exception e) {
				assertTrue(e instanceof ZipException);
			} // We have not set an entry

			try {
				// We try to write data without entry
				zip.write(new byte[2]);
				fail("Writing data without an entry. Should have thrown IOException");
			} catch (Exception e) {
				assertTrue(e instanceof IOException);
			}

			try {
				// Try to write without an entry and with nonsense offset and
				// length
				zip.write(new byte[2], 0, 12);
				fail("Writing data without an entry. Should have thrown IndexOutOfBoundsException");
			} catch (Exception e) {
				assertTrue("Caught a " + e.getClass().getName(),
						e instanceof IndexOutOfBoundsException);
			}
		} catch (IOException e) {
			fail("ERROR: " + e);
		}
	}

	protected void setUp() {
		zos = new ZipOutputStream(bos = new ByteArrayOutputStream());
	}

	protected void tearDown() {

		try {
			if (zos != null)
				zos.close();
			if (zis != null)
				zis.close();
		} catch (Exception e) {
		}
	}

}
