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
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.Inflater;
import java.util.zip.InflaterInputStream;
import java.util.zip.ZipException;

import tests.support.resource.Support_Resources;

public class InflaterInputStreamTest extends junit.framework.TestCase {

	// files hyts_constru(O),hyts_constru(OD),hyts_constru(ODI) needs to be
	// included as resources
	byte outPutBuf[] = new byte[500];

	class MyInflaterInputStream extends java.util.zip.InflaterInputStream {
		MyInflaterInputStream(InputStream in) {
			super(in);
		}

		MyInflaterInputStream(InputStream in, Inflater infl) {
			super(in, infl);
		}

		MyInflaterInputStream(InputStream in, Inflater infl, int size) {
			super(in, infl, size);
		}

		void myFill() throws IOException {
			fill();
		}
	}

	/**
	 * @tests java.util.zip.InflaterInputStream#InflaterInputStream(java.io.InputStream)
	 */
	public void test_ConstructorLjava_io_InputStream() {
		// test method
		// java.util.zip.inflaterInputStream.InflaterInputStream(InputStream)
		int result = 0;
		int buffer[] = new int[500];
		try {
			InputStream infile = Support_Resources
					.getStream("hyts_constru(O).txt");

			InflaterInputStream inflatIP = new InflaterInputStream(infile);

			int i = 0;
			while ((result = inflatIP.read()) != -1) {
				buffer[i] = result;
				i++;
			}
			inflatIP.close();
		} catch (FileNotFoundException e) {
			fail(
					"input file to test InflaterInputStream constructor is not found");
		} catch (ZipException e) {
			fail(
					"read() threw an zip exception while testing constructor");
		} catch (IOException e) {
			fail("read() threw an exception while testing constructor");
		}
	}

	/**
	 * @tests java.util.zip.InflaterInputStream#InflaterInputStream(java.io.InputStream,
	 *        java.util.zip.Inflater)
	 */
	public void test_ConstructorLjava_io_InputStreamLjava_util_zip_Inflater() {
		// test method
		// java.util.zip.inflaterInputStream.InflaterInputStream(InputStream,Inflater)

		byte byteArray[] = new byte[100];
		try {
			InputStream infile = Support_Resources
					.getStream("hyts_constru(OD).txt");
			Inflater inflate = new Inflater();
			InflaterInputStream inflatIP = new InflaterInputStream(infile,
					inflate);

			inflatIP.read(byteArray, 0, 5);// ony suppose to read in 5 bytes
			inflatIP.close();
		} catch (FileNotFoundException e) {
			fail(
					"input file to test InflaterInputStream constructor is not found");
		} catch (ZipException e) {
			fail(
					"read() threw an zip exception while testing constructor");
		} catch (IOException e) {
			fail("read() threw an exception while testing constructor");
		}
	}

	/**
	 * @tests java.util.zip.InflaterInputStream#InflaterInputStream(java.io.InputStream,
	 *        java.util.zip.Inflater, int)
	 */
	public void test_ConstructorLjava_io_InputStreamLjava_util_zip_InflaterI() {
		// test method
		// java.util.zip.inflaterInputStream.InflaterInputStream(InputStream,Inflater,int)
		int result = 0;
		int buffer[] = new int[500];
		try {
			InputStream infile = Support_Resources
					.getStream("hyts_constru(ODI).txt");
			Inflater inflate = new Inflater();
			InflaterInputStream inflatIP = new InflaterInputStream(infile,
					inflate, 1);

			int i = 0;
			while ((result = inflatIP.read()) != -1) {
				buffer[i] = result;
				i++;
			}
			inflatIP.close();
		} catch (FileNotFoundException e) {
			fail(
					"input file to test InflaterInputStream constructor is not found");
		} catch (ZipException e) {
			fail(
					"read() threw an zip exception while testing constructor");
		} catch (IOException e) {
			fail("read() threw an exception while testing constructor");
		}
	}

	/**
	 * @tests java.util.zip.InflaterInputStream#read()
	 */
	public void test_read() {
		// test method java.util.zip.inflaterInputStream.Read()
		int result = 0;
		int buffer[] = new int[500];
		byte orgBuffer[] = { 1, 3, 4, 7, 8 };
		try {
			InputStream infile = Support_Resources
					.getStream("hyts_constru(OD).txt");
			Inflater inflate = new Inflater();
			InflaterInputStream inflatIP = new InflaterInputStream(infile,
					inflate);

			int i = 0;
			while ((result = inflatIP.read()) != -1) {
				buffer[i] = result;
				i++;
			}
			inflatIP.close();
		} catch (FileNotFoundException e) {
			fail(
					"input file to test InflaterInputStream constructor is not found");
		} catch (ZipException e) {
			fail(
					"read() threw an zip exception while testing constructor");
		} catch (IOException e) {
			fail("read() threw an exception while testing constructor");
		}

		for (int j = 0; j < orgBuffer.length; j++) {
			assertTrue(
					"orginal compressed data did not equal decompressed data",
					buffer[j] == orgBuffer[j]);
		}
	}

	/**
	 * @tests java.util.zip.InflaterInputStream#read(byte[], int, int)
	 */
	public void test_read$BII() {
		/*
		 * // test method java.util.zip.inflaterInputStream.read(byte,int,int)
		 * byte byteArray[] = new byte[100]; byte orgBuffer[] = { 1, 3, 4, 7, 8 };
		 * try { InputStream infile = Support_Resources
		 * .getStream("hyts_constru(OD).txt"); Inflater inflate = new
		 * Inflater(); InflaterInputStream inflatIP = new
		 * InflaterInputStream(infile, inflate);
		 * 
		 * inflatIP.read(byteArray, 0, 4);// ony suppose to read in 4 bytes
		 * inflatIP.close(); assertTrue( "the fifth element of byteArray
		 * contained a non zero value", byteArray[4] == 0); } catch
		 * (FileNotFoundException e) { fail( "input file to test
		 * InflaterInputStream constructor is not found"); } catch
		 * (ZipException e) { fail( "read() threw an zip exception while
		 * testing constructor"); } catch (IOException e) {
		 * assertTrue("read() threw an exception while testing constructor",
		 * false); }
		 * 
		 * for (int j = 0; j < 4; j++) { assertTrue( "orginal compressed data
		 * did not equal decompressed data", byteArray[j] == orgBuffer[j]); }
		 * 
		 * InflaterInputStream iis = setupIISForReadTest(); try { int n =
		 * iis.read(); n = iis.read(new byte[1], -1, 0); fail("FAILED: expected
		 * IOOBE, but the method returns: " //$NON-NLS-1$ + n); } catch
		 * (IOException e) { fail("FAILED: unexpected " + e); //$NON-NLS-1$ }
		 * catch (IndexOutOfBoundsException e2) { // NO OP }
		 * 
		 * iis = setupIISForReadTest(); try { int n = iis.read(); n =
		 * iis.read(null, 0, 1); fail("FAILED: expected NPE, but the method
		 * returns: " //$NON-NLS-1$ + n); } catch (IOException e) {
		 * fail("FAILED: unexpected " + e); //$NON-NLS-1$ } catch
		 * (NullPointerException e2) { // NO OP }
		 * 
		 * iis = setupIISForReadTest(); try { int n = iis.read(); n =
		 * iis.read(new byte[1], 0, 0); assertEquals(0, n); } catch (Exception
		 * e) { fail("FAILED: unexpected " + e); //$NON-NLS-1$ }
		 */
	}

	/**
	 * @return
	 */
	private InflaterInputStream setupIISForReadTest() {
		/*
		 * ByteArrayOutputStream baos; DeflaterOutputStream dos;
		 * ByteArrayInputStream bais; InflaterInputStream iis;
		 * 
		 * baos = new ByteArrayOutputStream(); dos = new
		 * DeflaterOutputStream(baos); try { dos.write(2); dos.close(); } catch
		 * (IOException e3) { fail(); e3.printStackTrace(); } bais = new
		 * ByteArrayInputStream(baos.toByteArray()); iis = new
		 * InflaterInputStream(bais); return iis;
		 */
		return null;
	}

	/**
	 * @tests java.util.zip.InflaterInputStream#skip(long)
	 */
	public void test_skipJ() {
		// Test for method java.util.zip.Inflater.InputStream.skip(long)

		try {
			InputStream is = Support_Resources.getStream("hyts_available.tst");
			InflaterInputStream iis = new InflaterInputStream(is);

			// Tests for skipping a negative number of bytes.
			try {
				iis.skip(-3);
				fail("IllegalArgumentException not thrown");
			} catch (IllegalArgumentException e) {
			}
			assertTrue("Incorrect Byte Returned.", iis.read() == 5);

			try {
				iis.skip(Integer.MIN_VALUE);
				fail("IllegalArgumentException not thrown");
			} catch (IllegalArgumentException e) {
			}
			assertTrue("Incorrect Byte Returned.", iis.read() == 4);

			// Test to make sure the correct number of bytes were skipped
			assertTrue("Incorrect Number Of Bytes Skipped.", iis.skip(3) == 3);

			// Test to see if the number of bytes skipped returned is true.
			assertTrue("Incorrect Byte Returned.", iis.read() == 7);

			assertTrue("Incorrect Number Of Bytes Skipped.", iis.skip(0) == 0);
			assertTrue("Incorrect Byte Returned.", iis.read() == 0);

			// Test for skipping more bytes than available in the stream
			assertTrue("Incorrect Number Of Bytes Skipped.", iis.skip(4) == 2);
			assertTrue("Incorrect Byte Returned.", iis.read() == -1);
			iis.close();
		} catch (IOException e) {
			fail("Unexpected IOException during test");
		}
	}

	/**
	 * @tests java.util.zip.InflaterInputStream#skip(long)
	 */
	public void test_skipJ2() {
		// test method java.util.zip.inflaterInputStream.skip(long)
		int result = 0;
		int buffer[] = new int[100];
		byte orgBuffer[] = { 1, 3, 4, 7, 8 };
		try {
			// testing for negative input to skip
			InputStream infile = Support_Resources
					.getStream("hyts_constru(OD).txt");
			Inflater inflate = new Inflater();
			InflaterInputStream inflatIP = new InflaterInputStream(infile,
					inflate, 10);
			long skip;
			try {
				skip = inflatIP.skip(Integer.MIN_VALUE);
				fail("Expected IllegalArgumentException when skip() is called with negative parameter");
			} catch (IllegalArgumentException e) {
			}
			inflatIP.close();

			// testing for number of bytes greater than input.
			InputStream infile2 = Support_Resources
					.getStream("hyts_constru(OD).txt");
			InflaterInputStream inflatIP2 = new InflaterInputStream(infile2);

			// looked at how many bytes the skip skipped. It is
			// 5 and its supposed to be the entire input stream.

			skip = inflatIP2.skip(Integer.MAX_VALUE);
			// System.out.println(skip);
			assertTrue("method skip() returned wrong number of bytes skiped",
					skip == 5);

			// test for skiping of 2 bytes
			InputStream infile3 = Support_Resources
					.getStream("hyts_constru(OD).txt");
			InflaterInputStream inflatIP3 = new InflaterInputStream(infile3);
			skip = inflatIP3.skip(2);
			assertTrue(
					"the number of bytes returned by skip did not correspond with its input parameters",
					skip == 2);
			int i = 0;
			result = 0;
			while ((result = inflatIP3.read()) != -1) {
				buffer[i] = result;
				i++;
			}
			inflatIP2.close();

			for (int j = 2; j < orgBuffer.length; j++) {
				assertTrue(
						"orginal compressed data did not equal decompressed data",
						buffer[j - 2] == orgBuffer[j]);
			}
		} catch (FileNotFoundException e) {
			fail(
					"input file to test InflaterInputStream constructor is not found");
		} catch (ZipException e) {
			fail(
					"read() threw an zip exception while testing constructor");
		} catch (IOException e) {
			fail("read() threw an exception while testing constructor");
		}
	}

	/**
	 * @tests java.util.zip.InflaterInputStream#available()
	 */
	public void test_available() {
		// Test for method java.util.zip.Inflater.InputStream.available()

		try {

			InputStream is = Support_Resources.getStream("hyts_available.tst");
			InflaterInputStream iis = new InflaterInputStream(is);

			int available;
			int read;
			for (int i = 0; i < 11; i++) {
				read = iis.read();
				available = iis.available();
				if (read == -1)
					assertTrue("Bytes Available Should Return 0 ",
							available == 0);
				else
					assertTrue("Bytes Available Should Return 1.",
							available == 1);
			}

			iis.close();
			try {
				iis.available();
				fail("available after close should throw IOException.");
			} catch (IOException e) {
			}
		} catch (IOException e) {
			fail("Unexpected IOException during test");
		}
	}

	/**
	 * @tests java.util.zip.InflaterInputStream#close()
	 */
	public void test_close() {
		InflaterInputStream iin = new InflaterInputStream(
				new ByteArrayInputStream(new byte[0]));
		try {
			iin.close();
			// test for exception
			iin.close();
		} catch (IOException e) {
			fail("Threw exception");
		}
	}

	protected void setUp() {
	}

	protected void tearDown() {
	}

}
