/* Copyright 2006 The Apache Software Foundation or its licensors, as applicable
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

package org.apache.harmony.tests.java.util.zip;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

import junit.framework.TestCase;

public class ZipInputStreamTest extends TestCase {

	byte[] zipBytes;

	byte[] dataBytes = "Some data in my file".getBytes();

	public void setUp() throws IOException {
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		ZipOutputStream zos = new ZipOutputStream(bos);
		ZipEntry entry = new ZipEntry("myFile");
		zos.putNextEntry(entry);
		zos.write(dataBytes);
		zos.closeEntry();
		zos.close();
		zipBytes = bos.toByteArray();
	}

	/**
	 * @tests java.util.zip.ZipInputStream#skip(long)
	 */
	public void test_skipJ() throws IOException {
		ZipInputStream zis = new ZipInputStream(new ByteArrayInputStream(
				zipBytes));
		zis.getNextEntry();
		long skipLen = dataBytes.length / 2;
		assertEquals("Assert 0: failed valid skip", skipLen, zis.skip(skipLen));
		zis.skip(dataBytes.length);
		assertEquals("Assert 1: performed invalid skip", 0, zis.skip(1));
		assertEquals("Assert 2: failed zero len skip", 0, zis.skip(0));
		try {
			zis.skip(-1);
			fail("Assert 3: Expected Illegal argument exception");
		} catch (IllegalArgumentException e) {
			// Expected
		}
	}

}
