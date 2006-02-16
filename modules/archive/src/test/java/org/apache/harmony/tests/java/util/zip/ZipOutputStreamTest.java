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

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import junit.framework.TestCase;

public class ZipOutputStreamTest extends TestCase {

	/**
	 * @tests java.util.zip.ZipOutputStream#close()
	 */
	public void test_close() throws IOException {
		// Regression for HARMONY-97
		ZipOutputStream zos = new ZipOutputStream(new ByteArrayOutputStream());
		zos.putNextEntry(new ZipEntry("myFile"));
		zos.close();
		zos.close(); // Should be a no-op
	}
	
	/**
	 * @tests java.util.zip.ZipOutputStream#finish()
	 */
	public void test_finish() throws IOException {
		ZipOutputStream zos = new ZipOutputStream(new ByteArrayOutputStream());
		zos.putNextEntry(new ZipEntry("myFile"));
		zos.finish();
		zos.close();
		try {
			zos.finish();
			fail("Assert 0: Expected IOException");
		} catch (IOException e) {
			// Expected
		}
	}
}
