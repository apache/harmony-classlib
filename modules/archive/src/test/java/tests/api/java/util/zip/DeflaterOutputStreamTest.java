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



import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.zip.Deflater;
import java.util.zip.DeflaterOutputStream;
import java.util.zip.InflaterInputStream;

public class DeflaterOutputStreamTest extends junit.framework.TestCase {
	byte outPutBuf[] = new byte[500];

	class MyDeflaterOutputStream extends java.util.zip.DeflaterOutputStream {
		MyDeflaterOutputStream(OutputStream out) {
			super(out);
		}

		MyDeflaterOutputStream(OutputStream out, Deflater defl) {
			super(out, defl);
		}

		MyDeflaterOutputStream(OutputStream out, Deflater defl, int size) {
			super(out, defl, size);
		}

		byte[] getProtectedBuf() {
			return buf;
		}

		void myDeflate() throws IOException {
			deflate();
		}
	}

	/**
	 * @tests java.util.zip.DeflaterOutputStream#DeflaterOutputStream(java.io.OutputStream,
	 *        java.util.zip.Deflater)
	 */
	public void test_ConstructorLjava_io_OutputStreamLjava_util_zip_Deflater() {
		// Test for method
		// java.util.zip.deflaterOutputStream.DeflaterOutPutStream(OutputStream,Deflater)

		try {
			byte byteArray[] = { 1, 3, 4, 7, 8 };
			File f1 = new File("hyts_Constru(OD).tst");
			FileOutputStream fos = new FileOutputStream(f1);
			Deflater defl = null;
			MyDeflaterOutputStream dos;
			// Test for a null Deflater.
			try {
				dos = new MyDeflaterOutputStream(fos, defl);
				fail("NullPointerException Not Thrown");
			} catch (NullPointerException e) {
			}
			defl = new Deflater();
			dos = new MyDeflaterOutputStream(fos, defl);

			// Test to see if DflaterOutputStream was created with the correct
			// buffer.
			assertTrue("Incorrect Buffer Size",
					dos.getProtectedBuf().length == 512);

			dos.write(byteArray);
			dos.close();
			f1.delete();
		} catch (SecurityException e) {
			fail("SecurityException During Test");
		} catch (IOException e) {
			fail("IOException During Test");
		}
	}

	/**
	 * @tests java.util.zip.DeflaterOutputStream#DeflaterOutputStream(java.io.OutputStream)
	 */
	public void test_ConstructorLjava_io_OutputStream() {
		// Test for method
		// java.util.zip.deflaterOutputStream.DeflaterOutPutStream(OutputStream)

		try {
			File f1 = new File("hyts_Constru(O).tst");
			FileOutputStream fos = new FileOutputStream(f1);
			MyDeflaterOutputStream dos = new MyDeflaterOutputStream(fos);

			// Test to see if DflaterOutputStream was created with the correct
			// buffer.
			assertTrue("Incorrect Buffer Size",
					dos.getProtectedBuf().length == 512);

			dos.write(outPutBuf);
			dos.close();
			f1.delete();
		} catch (SecurityException e) {
			fail("SecurityException During Test");
		} catch (IOException e) {
			fail("IOException During Test");
		}

	}

	/**
	 * @tests java.util.zip.DeflaterOutputStream#DeflaterOutputStream(java.io.OutputStream,
	 *        java.util.zip.Deflater, int)
	 */
	public void test_ConstructorLjava_io_OutputStreamLjava_util_zip_DeflaterI() {
		// Test for method
		// java.util.zip.deflaterOutputStream.DeflaterOutPutStream(OutputStream,Deflater,int)

		try {
			int buf = 5;
			int negBuf = -5;
			int zeroBuf = 0;
			byte byteArray[] = { 1, 3, 4, 7, 8, 3, 6 };
			File f1 = new File("hyts_Constru(ODI).tst");
			FileOutputStream fos = new FileOutputStream(f1);
			Deflater defl = null;
			MyDeflaterOutputStream dos;

			// Test for a null Deflater.
			try {
				dos = new MyDeflaterOutputStream(fos, defl, buf);
				fail("NullPointerException Not Thrown");
			} catch (NullPointerException e) {
			}
			defl = new Deflater();

			// Test for a negative buf.
			try {
				dos = new MyDeflaterOutputStream(fos, defl, negBuf);
				fail("IllegalArgumentException Not Thrown");
			} catch (IllegalArgumentException e) {
			}

			// Test for a zero buf.
			try {
				dos = new MyDeflaterOutputStream(fos, defl, zeroBuf);
				fail("IllegalArgumentException Not Thrown");
			} catch (IllegalArgumentException e) {
			}

			// Test to see if DflaterOutputStream was created with the correct
			// buffer.
			dos = new MyDeflaterOutputStream(fos, defl, buf);
			assertTrue("Incorrect Buffer Size",
					dos.getProtectedBuf().length == 5);

			dos.write(byteArray);
			dos.close();
			f1.delete();
		} catch (SecurityException e) {
			fail("SecurityException During Test");
		} catch (IOException e) {
			fail("IOException During Test");
		}
	}

	/**
	 * @tests java.util.zip.DeflaterOutputStream#close()
	 */
	public void test_close() {
		// Test for method java.util.zip.DeflaterOutputStream.close()
		try {
			File f1 = new File("close.tst");
			FileOutputStream fos = new FileOutputStream(f1);
			DeflaterOutputStream dos = new DeflaterOutputStream(fos);
			byte byteArray[] = { 1, 3, 4, 6 };
			dos.write(byteArray);

			FileInputStream fis = new FileInputStream(f1);
			InflaterInputStream iis = new InflaterInputStream(fis);
			try {
				iis.read();
				fail("EOFException Not Thrown");
			} catch (EOFException e) {
			}

			dos.close();

			// Test to see if the finish method wrote the bytes to the file.
			assertTrue("Incorrect Byte Returned.", iis.read() == 1);
			assertTrue("Incorrect Byte Returned.", iis.read() == 3);
			assertTrue("Incorrect Byte Returned.", iis.read() == 4);
			assertTrue("Incorrect Byte Returned.", iis.read() == 6);
			assertTrue("Incorrect Byte Returned.", iis.read() == -1);
			assertTrue("Incorrect Byte Returned.", iis.read() == -1);
			iis.close();

			// Not sure if this test will stay.
			FileOutputStream fos2 = new FileOutputStream(f1);
			DeflaterOutputStream dos2 = new DeflaterOutputStream(fos2);
			fos2.close();
			try {
				dos2.close();
				fail("IOException not thrown");
			} catch (IOException e) {
			}

			// Test to write to a closed DeflaterOutputStream
			try {
				dos.write(5);
				fail(
						"DeflaterOutputStream Able To Write After Being Closed.");
			} catch (IOException e) {
			}

			// Test to write to a FileOutputStream that should have been closed
			// by
			// the DeflaterOutputStream.
			try {
				fos.write(("testing").getBytes());
				fail(
						"FileOutputStream Able To Write After Being Closed.");
			} catch (IOException e) {
			}

			f1.delete();
		} catch (SecurityException e) {
			fail("Unexpected SecurityException during test");
		} catch (IOException e) {
			fail("Unexpected IOException during test");
		}
	}

	/**
	 * @tests java.util.zip.DeflaterOutputStream#finish()
	 */
	public void test_finish() {
		// Test for method java.util.zip.DeflaterOutputStream.finish()

		// Need test to see if method finish() actually finishes
		// Only testing possible errors, not if it actually works

		try {
			File f1 = new File("finish.tst");
			FileOutputStream fos1 = new FileOutputStream(f1);
			DeflaterOutputStream dos = new DeflaterOutputStream(fos1);
			byte byteArray[] = { 1, 3, 4, 6 };
			dos.write(byteArray);
			dos.finish();

			// Test to see if the same FileOutputStream can be used with the
			// DeflaterOutputStream after finish is called.
			try {
				dos.write(1);
				fail("IOException not thrown");
			} catch (IOException e) {
			}

			// Test for writing with a new FileOutputStream using the same
			// DeflaterOutputStream.
			FileOutputStream fos2 = new FileOutputStream(f1);
			try {
				dos = new DeflaterOutputStream(fos2);
				dos.write(1);
			} catch (IOException e) {
				fail("Unexpected IOException");
			}

			// Test for writing to FileOutputStream fos1, which should be open.
			try {
				fos1.write(("testing").getBytes());
			} catch (IOException e) {
				fail(
						"Unexpected IOException While Using The FileOutputStream 1.");
			}

			// Test for writing to FileOutputStream fos2, which should be open.
			try {
				fos2.write(("testing").getBytes());
			} catch (IOException e) {
				fail("Unexpected IOException while using the FileOutputStream 2");
			}

			// Not sure if this test will stay.
			FileOutputStream fos3 = new FileOutputStream(f1);
			DeflaterOutputStream dos3 = new DeflaterOutputStream(fos3);
			fos3.close();
			try {
				dos3.finish();
				fail("IOException not thrown");
			} catch (IOException e) {
			}

			// dos.close() won't close fos1 because it has been re-assigned to
			// fos2
			fos1.close();
			dos.close();
			f1.delete();
		} catch (SecurityException e) {
			fail("Unexpected SecurityException during test");
		} catch (IOException e) {
			fail("Unexpected IOException during test");
		}

	}

	/**
	 * @tests java.util.zip.DeflaterOutputStream#write(int)
	 */
	public void test_writeI() {
		// Test for method java.util.zip.deflaterOutputStream.write(int)

		try {
			File f1 = new File("writeI1.tst");
			FileOutputStream fos = new FileOutputStream(f1);
			DeflaterOutputStream dos = new DeflaterOutputStream(fos);
			for (int i = 0; i < 3; i++)
				dos.write(i);
			dos.close();
			FileInputStream fis = new FileInputStream(f1);
			InflaterInputStream iis = new InflaterInputStream(fis);
			for (int i = 0; i < 3; i++)
				assertTrue("Incorrect Byte Returned.", iis.read() == i);
			assertTrue("Incorrect Byte Returned (EOF).", iis.read() == -1);
			assertTrue("Incorrect Byte Returned (EOF).", iis.read() == -1);
			iis.close();

			// Not sure if this test is that important.
			// Checks to see if you can write using the DeflaterOutputStream
			// after
			// the FileOutputStream has been closed.
			FileOutputStream fos2 = new FileOutputStream(f1);
			DeflaterOutputStream dos2 = new DeflaterOutputStream(fos2);
			fos2.close();
			try {
				dos2.write(2);
				fail("IOException not thrown");
			} catch (IOException e) {
			}

			f1.delete();
		} catch (SecurityException e) {
			fail("Unexpected SecurityException during test");
		} catch (IOException e) {
			fail("Unexpected IOException during test.");
		}
	}

	/**
	 * @tests java.util.zip.DeflaterOutputStream#write(byte[], int, int)
	 */
	public void test_write$BII() {
		// Test method
		// java.util.zip.deflaterOutputStream.write(byteArrat,int,int)
		try {
			byte byteArray[] = { 1, 3, 4, 7, 8, 3, 6 };

			// Test to see if the correct bytes are saved.
			File f1 = new File("writeBII.tst");
			FileOutputStream fos1 = new FileOutputStream(f1);
			DeflaterOutputStream dos1 = new DeflaterOutputStream(fos1);
			dos1.write(byteArray, 2, 3);
			dos1.close();
			FileInputStream fis = new FileInputStream(f1);
			InflaterInputStream iis = new InflaterInputStream(fis);
			assertTrue("Incorrect Byte Returned.", iis.read() == 4);
			assertTrue("Incorrect Byte Returned.", iis.read() == 7);
			assertTrue("Incorrect Byte Returned.", iis.read() == 8);
			assertTrue("Incorrect Byte Returned (EOF).", iis.read() == -1);
			assertTrue("Incorrect Byte Returned (EOF).", iis.read() == -1);
			iis.close();
			f1.delete();

			// Test for trying to write more bytes than available from the array
			File f2 = new File("writeBII2.tst");
			FileOutputStream fos2 = new FileOutputStream(f2);
			DeflaterOutputStream dos2 = new DeflaterOutputStream(fos2);
			try {
				dos2.write(byteArray, 2, 10);
				fail("IndexOutOfBoundsException not thrown");
			} catch (IndexOutOfBoundsException e) {
			}

			// Test for trying to write a negative number of bytes.
			try {
				dos2.write(byteArray, 2, Integer.MIN_VALUE);
				fail("IndexOutOfBoundsException not thrown");
			} catch (IndexOutOfBoundsException e) {
			}

			// Test for trying to start writing from a byte < 0 from the array.
			try {
				dos2.write(byteArray, Integer.MIN_VALUE, 2);
				fail("IndexOutOfBoundsException not thrown");
			} catch (IndexOutOfBoundsException e) {
			}

			// Test for trying to start writing from a byte > than the array
			// size.
			try {
				dos2.write(byteArray, 10, 2);
				fail("IndexOutOfBoundsException not thrown");
			} catch (IndexOutOfBoundsException e) {
			}
			dos2.close();

			// Not sure if this test is that important.
			// Checks to see if you can write using the DeflaterOutputStream
			// after
			// the FileOutputStream has been closed.
			FileOutputStream fos3 = new FileOutputStream(f2);
			DeflaterOutputStream dos3 = new DeflaterOutputStream(fos3);
			fos3.close();
			try {
				dos3.write(byteArray, 2, 3);
				fail("IOException not thrown");
			} catch (IOException e) {
			}

			f2.delete();
		} catch (SecurityException e) {
			fail("Unexpectd SecurityException during test");
		} catch (IOException e) {
			fail("Unexpected IOException during test");
		}
	}

	protected void setUp() {
		// setting up a deflater to be used
		byte byteArray[] = { 1, 3, 4, 7, 8 };
		int x = 0;
		Deflater deflate = new Deflater(1);
		deflate.setInput(byteArray);
		while (!(deflate.needsInput())) {
			x += deflate.deflate(outPutBuf, x, outPutBuf.length - x);
		}
		deflate.finish();
		while (!(deflate.finished())) {
			x = x + deflate.deflate(outPutBuf, x, outPutBuf.length - x);
		}
		deflate.end();
	}

	protected void tearDown() {

	}

}
