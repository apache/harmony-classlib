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
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.zip.Checksum;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

import tests.support.resource.Support_Resources;

public class GZIPInputStreamTest extends junit.framework.TestCase {
	File resources;

	class TestGZIPInputStream extends GZIPInputStream {
		TestGZIPInputStream(InputStream in) throws IOException {
			super(in);
		}

		TestGZIPInputStream(InputStream in, int size) throws IOException {
			super(in, size);
		}

		Checksum getChecksum() {
			return crc;
		}

		boolean endofInput() {
			return eos;
		}
	}

	/**
	 * @tests java.util.zip.GZIPInputStream#GZIPInputStream(java.io.InputStream)
	 */
	public void test_ConstructorLjava_io_InputStream() {
		// test method java.util.zip.GZIPInputStream.constructor
		try {
			Support_Resources.copyFile(resources, "GZIPInputStream",
					"hyts_gInput.txt");
			final URL gInput = new File(resources.toString()
					+ "/GZIPInputStream/hyts_gInput.txt").toURL();
			TestGZIPInputStream inGZIP = new TestGZIPInputStream(gInput
					.openConnection().getInputStream());
			assertTrue("the constructor for GZIPInputStream is null",
					inGZIP != null);
			assertTrue("the CRC value of the inputStream is not zero", inGZIP
					.getChecksum().getValue() == 0);
			inGZIP.close();
		} catch (IOException e) {
			fail(
					"an IO error occured while trying to open the input file");
		}
	}

	/**
	 * @tests java.util.zip.GZIPInputStream#GZIPInputStream(java.io.InputStream,
	 *        int)
	 */
	public void test_ConstructorLjava_io_InputStreamI() {
		// test method java.util.zip.GZIPInputStream.constructorI
		try {
			Support_Resources.copyFile(resources, "GZIPInputStream",
					"hyts_gInput.txt");
			final URL gInput = new File(resources.toString()
					+ "/GZIPInputStream/hyts_gInput.txt").toURL();
			TestGZIPInputStream inGZIP = new TestGZIPInputStream(gInput
					.openConnection().getInputStream(), 200);
			assertTrue("the constructor for GZIPInputStream is null",
					inGZIP != null);
			assertTrue("the CRC value of the inputStream is not zero", inGZIP
					.getChecksum().getValue() == 0);
			inGZIP.close();
		} catch (IOException e) {
			fail(
					"an IO error occured while trying to open the input file");
		}
	}

	/**
	 * @tests java.util.zip.GZIPInputStream#read(byte[], int, int)
	 */
	public void test_read$BII() {
		// test method java.util.zip.GZIPInputStream.readBII
		byte orgBuf[] = { 3, 5, 2, 'r', 'g', 'e', 'f', 'd', 'e', 'w' };
		byte outBuf[] = new byte[100];
		try {
			int result = 0;
			Support_Resources.copyFile(resources, "GZIPInputStream",
					"hyts_gInput.txt");
			String resPath = resources.toString();
			if (resPath.charAt(0) == '/' || resPath.charAt(0) == '\\')
				resPath = resPath.substring(1);
			final URL gInput = new URL("file:/" + resPath
					+ "/GZIPInputStream/hyts_gInput.txt");
			TestGZIPInputStream inGZIP = new TestGZIPInputStream(gInput
					.openConnection().getInputStream());
			while (!(inGZIP.endofInput())) {
				result += inGZIP.read(outBuf, result, outBuf.length - result);
			}
			assertTrue(
					"the checkSum value of the compressed and decompressed data does not equal",
					inGZIP.getChecksum().getValue() == 3097700292L);
			for (int i = 0; i < orgBuf.length; i++) {
				assertTrue(
						"the decompressed data does not equal the orginal data decompressed",
						orgBuf[i] == outBuf[i]);
				// System.out.println(orgBuf[i] + " " + outBuf[i]);
			}
			int r = 0;
			try {
				inGZIP.read(outBuf, 100, 1);
			} catch (ArrayIndexOutOfBoundsException e) {
				r = 1;
			}
			inGZIP.close();
			assertTrue("Boundary Check was not present", r == 1);
		} catch (IOException e) {
			e.printStackTrace();
			fail("unexpected: " + e);
		}

		try {
			// Create compressed data which is exactly 512 bytes (after the
			// header),
			// the size of the InflaterStream internal buffer
			byte[] test = new byte[507];
			for (int i = 0; i < 256; i++)
				test[i] = (byte) i;
			for (int i = 256; i < test.length; i++)
				test[i] = (byte) (256 - i);
			ByteArrayOutputStream bout = new ByteArrayOutputStream();
			GZIPOutputStream out = new GZIPOutputStream(bout);
			out.write(test);
			out.close();
			byte[] comp = bout.toByteArray();
			GZIPInputStream gin2 = new GZIPInputStream(
					new ByteArrayInputStream(comp), 512);
			int result, total = 0;
			while ((result = gin2.read(test)) != -1)
				total += result;
			assertTrue("Should return -1", gin2.read() == -1);
			gin2.close();
			assertTrue("Incorrectly decompressed", total == test.length);

			gin2 = new GZIPInputStream(new ByteArrayInputStream(comp), 512);
			total = 0;
			while ((result = gin2.read(new byte[200])) != -1) {
				total += result;
			}
			assertTrue("Should return -1", gin2.read() == -1);
			gin2.close();
			assertTrue("Incorrectly decompressed", total == test.length);

			gin2 = new GZIPInputStream(new ByteArrayInputStream(comp), 516);
			total = 0;
			while ((result = gin2.read(new byte[200])) != -1) {
				total += result;
			}
			assertTrue("Should return -1", gin2.read() == -1);
			gin2.close();
			assertTrue("Incorrectly decompressed", total == test.length);

			comp[40] = 0;
			gin2 = new GZIPInputStream(new ByteArrayInputStream(comp), 512);
			boolean exception = false;
			try {
				while (gin2.read(test) != -1)
					;
			} catch (IOException e) {
				exception = true;
			}
			assertTrue("Exception expected", exception);
		} catch (IOException e) {
			fail("Unexpected: " + e);
		}
	}

	/**
	 * @tests java.util.zip.GZIPInputStream#close()
	 */
	public void test_close() {
		// test method java.util.zip.GZIPInputStream.close
		byte outBuf[] = new byte[100];
		try {
			int result = 0;
			Support_Resources.copyFile(resources, "GZIPInputStream",
					"hyts_gInput.txt");
			String resPath = resources.toString();
			if (resPath.charAt(0) == '/' || resPath.charAt(0) == '\\')
				resPath = resPath.substring(1);
			final URL gInput = new URL("file:/" + resPath
					+ "/GZIPInputStream/hyts_gInput.txt");
			TestGZIPInputStream inGZIP = new TestGZIPInputStream(gInput
					.openConnection().getInputStream());
			while (!(inGZIP.endofInput())) {
				result += inGZIP.read(outBuf, result, outBuf.length - result);
			}
			assertTrue(
					"the checkSum value of the compressed and decompressed data does not equal",
					inGZIP.getChecksum().getValue() == 3097700292L);
			inGZIP.close();
			int r = 0;
			try {
				inGZIP.read(outBuf, 0, 1);
			} catch (IOException e) {
				r = 1;
			}
			assertTrue(
					"GZIPInputStream can still be used after close is called",
					r == 1);
		} catch (IOException e) {
			e.printStackTrace();
			fail("unexpected: " + e);
		}
	}

	protected void setUp() {
		resources = Support_Resources.createTempFolder();
	}

	protected void tearDown() {
	}

}
