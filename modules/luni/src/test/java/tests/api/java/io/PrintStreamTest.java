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

package tests.api.java.io;

import java.io.IOException;
import java.io.OutputStream;

public class PrintStreamTest extends junit.framework.TestCase {

	java.io.ByteArrayOutputStream bos;

	private java.io.PrintStream os;

	java.io.ByteArrayInputStream bis;

	byte[] ibuf = new byte[4096];

	public String fileString = "Test_All_Tests\nTest_java_io_BufferedInputStream\nTest_java_io_BufferedOutputStream\nTest_java_io_ByteArrayInputStream\nTest_java_io_ByteArrayOutputStream\nTest_java_io_DataInputStream\nTest_java_io_File\nTest_java_io_FileDescriptor\nTest_java_io_FileInputStream\nTest_java_io_FileNotFoundException\nTest_java_io_FileOutputStream\nTest_java_io_FilterInputStream\nTest_java_io_FilterOutputStream\nTest_java_io_InputStream\nTest_java_io_IOException\nTest_java_io_OutputStream\nTest_PrintStream\nTest_java_io_RandomAccessFile\nTest_java_io_SyncFailedException\nTest_java_lang_AbstractMethodError\nTest_java_lang_ArithmeticException\nTest_java_lang_ArrayIndexOutOfBoundsException\nTest_java_lang_ArrayStoreException\nTest_java_lang_Boolean\nTest_java_lang_Byte\nTest_java_lang_Character\nTest_java_lang_Class\nTest_java_lang_ClassCastException\nTest_java_lang_ClassCircularityError\nTest_java_lang_ClassFormatError\nTest_java_lang_ClassLoader\nTest_java_lang_ClassNotFoundException\nTest_java_lang_CloneNotSupportedException\nTest_java_lang_Double\nTest_java_lang_Error\nTest_java_lang_Exception\nTest_java_lang_ExceptionInInitializerError\nTest_java_lang_Float\nTest_java_lang_IllegalAccessError\nTest_java_lang_IllegalAccessException\nTest_java_lang_IllegalArgumentException\nTest_java_lang_IllegalMonitorStateException\nTest_java_lang_IllegalThreadStateException\nTest_java_lang_IncompatibleClassChangeError\nTest_java_lang_IndexOutOfBoundsException\nTest_java_lang_InstantiationError\nTest_java_lang_InstantiationException\nTest_java_lang_Integer\nTest_java_lang_InternalError\nTest_java_lang_InterruptedException\nTest_java_lang_LinkageError\nTest_java_lang_Long\nTest_java_lang_Math\nTest_java_lang_NegativeArraySizeException\nTest_java_lang_NoClassDefFoundError\nTest_java_lang_NoSuchFieldError\nTest_java_lang_NoSuchMethodError\nTest_java_lang_NullPointerException\nTest_java_lang_Number\nTest_java_lang_NumberFormatException\nTest_java_lang_Object\nTest_java_lang_OutOfMemoryError\nTest_java_lang_RuntimeException\nTest_java_lang_SecurityManager\nTest_java_lang_Short\nTest_java_lang_StackOverflowError\nTest_java_lang_String\nTest_java_lang_StringBuffer\nTest_java_lang_StringIndexOutOfBoundsException\nTest_java_lang_System\nTest_java_lang_Thread\nTest_java_lang_ThreadDeath\nTest_java_lang_ThreadGroup\nTest_java_lang_Throwable\nTest_java_lang_UnknownError\nTest_java_lang_UnsatisfiedLinkError\nTest_java_lang_VerifyError\nTest_java_lang_VirtualMachineError\nTest_java_lang_vm_Image\nTest_java_lang_vm_MemorySegment\nTest_java_lang_vm_ROMStoreException\nTest_java_lang_vm_VM\nTest_java_lang_Void\nTest_java_net_BindException\nTest_java_net_ConnectException\nTest_java_net_DatagramPacket\nTest_java_net_DatagramSocket\nTest_java_net_DatagramSocketImpl\nTest_java_net_InetAddress\nTest_java_net_NoRouteToHostException\nTest_java_net_PlainDatagramSocketImpl\nTest_java_net_PlainSocketImpl\nTest_java_net_Socket\nTest_java_net_SocketException\nTest_java_net_SocketImpl\nTest_java_net_SocketInputStream\nTest_java_net_SocketOutputStream\nTest_java_net_UnknownHostException\nTest_java_util_ArrayEnumerator\nTest_java_util_Date\nTest_java_util_EventObject\nTest_java_util_HashEnumerator\nTest_java_util_Hashtable\nTest_java_util_Properties\nTest_java_util_ResourceBundle\nTest_java_util_tm\nTest_java_util_Vector\n";

	/**
	 * @tests java.io.PrintStream#PrintStream(java.io.OutputStream)
	 */
	public void test_ConstructorLjava_io_OutputStream() {
		// Test for method java.io.PrintStream(java.io.OutputStream)
		try {
			os = new java.io.PrintStream(bos);
			os.print(2345.76834720202);
		} catch (Exception e) {
			fail("IOException during constructor test : " + e.getMessage());
		} finally {
			try {
				os.close();
			} catch (Exception e) {
				fail("IOException during constructor test : " + e.getMessage());
			}
		}
	}

	/**
	 * @tests java.io.PrintStream#PrintStream(java.io.OutputStream, boolean)
	 */
	public void test_ConstructorLjava_io_OutputStreamZ() {
		// Test for method java.io.PrintStream(java.io.OutputStream, boolean)
		try {
			os = new java.io.PrintStream(bos);
			os.println(2345.76834720202);
			os.flush();
			assertTrue("Bytes not written", bos.size() > 0);
		} catch (Exception e) {
			fail("Exception during constructor test : " + e.getMessage());
		} finally {
			try {
				os.close();
			} catch (Exception e) {
				fail("Exception during constructor test : " + e.getMessage());
			}
		}
	}

	/**
	 * @tests java.io.PrintStream#checkError()
	 */
	public void test_checkError() {
		// Test for method boolean java.io.PrintStream.checkError()
		try {
			os = new java.io.PrintStream(new OutputStream() {
				public void write(int b) throws IOException {
					throw new IOException();
				}

				public void write(byte[] b, int o, int l)
						throws java.io.IOException {
					throw new java.io.IOException();
				}
			});
			os.print(fileString.substring(0, 501));
			assertTrue("Checkerror should return true", os.checkError() == true);
		} catch (Exception e) {
			fail("Exception raised : " + e.getMessage());
		}
	}

	/**
	 * @tests java.io.PrintStream#close()
	 */
	public void test_close() {
		// Test for method void java.io.PrintStream.close()
		try {
			os = new java.io.PrintStream(bos);
			os.close();
		} catch (Exception e) {
			fail("Close test raised exception : " + e.getMessage());
		} finally {
			try {
				bos.close();
			} catch (java.io.IOException e) {
				fail("IOException during flush test : " + e.getMessage());
			}
		}
	}

	/**
	 * @tests java.io.PrintStream#flush()
	 */
	public void test_flush() {
		// Test for method void java.io.PrintStream.flush()
		try {
			os = new java.io.PrintStream(bos);
			os.print(fileString.substring(0, 501));
			os.flush();
			assertTrue("Bytes not written after flush.", bos.size() == 501);
			bos.close();
		} catch (java.io.IOException e) {
			fail("Flush test failed with IOException: " + e.toString());
		} finally {
			try {
				os.close();
				bos.close();
			} catch (java.io.IOException e) {
				fail("IOException during flush test: " + e.toString());
			}
		}
	}

	/**
	 * @tests java.io.PrintStream#print(char[])
	 */
	public void test_print$C() {
		// Test for method void java.io.PrintStream.print(char [])
		int r = 0;
		os = new java.io.PrintStream(bos, true);
		try {
			os.print((char[]) null);
		} catch (NullPointerException ex) {
			r = 1;
		}
		assertTrue(
				"expected null Pointer Exception for print(char[]) not thrown",
				r == 1);

		os = new java.io.PrintStream(bos, true);
		char[] sc = new char[4000];
		fileString.getChars(0, fileString.length(), sc, 0);
		os.print(sc);
		bis = new java.io.ByteArrayInputStream(bos.toByteArray());
		os.close();
		byte[] rbytes = new byte[4000];
		bis.read(rbytes, 0, fileString.length());
		assertTrue("Incorrect char[] written", new String(rbytes, 0, fileString
				.length()).equals(fileString));
	}

	/**
	 * @tests java.io.PrintStream#print(char)
	 */
	public void test_printC() {
		// Test for method void java.io.PrintStream.print(char)
		os = new java.io.PrintStream(bos, true);
		os.print('t');
		bis = new java.io.ByteArrayInputStream(bos.toByteArray());
		assertTrue("Incorrect char written", bis.read() == 't');
	}

	/**
	 * @tests java.io.PrintStream#print(double)
	 */
	public void test_printD() {
		// Test for method void java.io.PrintStream.print(double)
		byte[] rbuf = new byte[100];
		os = new java.io.PrintStream(bos, true);
		os.print(2345.76834720202);
		bis = new java.io.ByteArrayInputStream(bos.toByteArray());
		bis.read(rbuf, 0, 16);
		assertTrue("Incorrect double written", new String(rbuf, 0, 16)
				.equals("2345.76834720202"));
	}

	/**
	 * @tests java.io.PrintStream#print(float)
	 */
	public void test_printF() {
		// Test for method void java.io.PrintStream.print(float)
		os = new java.io.PrintStream(bos, true);
		byte rbuf[] = new byte[10];
		os.print(29.08764f);
		os.flush();
		bis = new java.io.ByteArrayInputStream(bos.toByteArray());
		bis.read(rbuf, 0, 8);
		assertTrue("Incorrect float written", new String(rbuf, 0, 8)
				.equals("29.08764"));

	}

	/**
	 * @tests java.io.PrintStream#print(int)
	 */
	public void test_printI() {
		// Test for method void java.io.PrintStream.print(int)
		os = new java.io.PrintStream(bos, true);
		os.print(768347202);
		byte[] rbuf = new byte[18];
		bis = new java.io.ByteArrayInputStream(bos.toByteArray());
		bis.read(rbuf, 0, 9);
		assertTrue("Incorrect int written", new String(rbuf, 0, 9)
				.equals("768347202"));
	}

	/**
	 * @tests java.io.PrintStream#print(long)
	 */
	public void test_printJ() {
		// Test for method void java.io.PrintStream.print(long)
		byte[] rbuf = new byte[100];
		os = new java.io.PrintStream(bos, true);
		os.print(9875645283333L);
		os.close();
		bis = new java.io.ByteArrayInputStream(bos.toByteArray());
		bis.read(rbuf, 0, 13);
		assertTrue("Incorrect long written", new String(rbuf, 0, 13)
				.equals("9875645283333"));
	}

	/**
	 * @tests java.io.PrintStream#print(java.lang.Object)
	 */
	public void test_printLjava_lang_Object() {
		// Test for method void java.io.PrintStream.print(java.lang.Object)
		os = new java.io.PrintStream(bos, true);
		os.print((Object) null);
		os.flush();
		bis = new java.io.ByteArrayInputStream(bos.toByteArray());
		byte[] nullbytes = new byte[4];
		bis.read(nullbytes, 0, 4);
		assertTrue("null should be written", new String(nullbytes, 0, 4)
				.equals("null"));
		try {
			bis.close();
			bos.close();
			os.close();
		} catch (java.io.IOException e) {
			fail("Unexpectted IO exception : " + e.getMessage());
		}
		java.io.ByteArrayOutputStream bos1 = new java.io.ByteArrayOutputStream();
		os = new java.io.PrintStream(bos1, true);
		os.print(new java.util.Vector());
		bis = new java.io.ByteArrayInputStream(bos1.toByteArray());
		byte[] rbytes = new byte[2];
		bis.read(rbytes, 0, 2);
		assertTrue("Incorrect Object written", new String(rbytes, 0, 2)
				.equals("[]"));
	}

	/**
	 * @tests java.io.PrintStream#print(java.lang.String)
	 */
	public void test_printLjava_lang_String() {
		// Test for method void java.io.PrintStream.print(java.lang.String)
		os = new java.io.PrintStream(bos, true);
		os.print((String) null);
		bis = new java.io.ByteArrayInputStream(bos.toByteArray());
		byte[] nullbytes = new byte[4];
		bis.read(nullbytes, 0, 4);
		assertTrue("null should be written", new String(nullbytes, 0, 4)
				.equals("null"));
		try {
			bis.close();
			bos.close();
			os.close();
		} catch (java.io.IOException e) {
			fail("Unexpected IO exception : " + e.getMessage());
		}
		java.io.ByteArrayOutputStream bos1 = new java.io.ByteArrayOutputStream();
		os = new java.io.PrintStream(bos1, true);
		os.print("Hello World");
		bis = new java.io.ByteArrayInputStream(bos1.toByteArray());
		byte rbytes[] = new byte[100];
		bis.read(rbytes, 0, 11);
		assertTrue("Incorrect string written", new String(rbytes, 0, 11)
				.equals("Hello World"));
	}

	/**
	 * @tests java.io.PrintStream#print(boolean)
	 */
	public void test_printZ() {
		// Test for method void java.io.PrintStream.print(boolean)
		java.io.DataInputStream dis = null;
		os = new java.io.PrintStream(bos, true);
		os.print(true);
		dis = new java.io.DataInputStream(new java.io.ByteArrayInputStream(bos
				.toByteArray()));
		try {
			assertTrue("Incorrect boolean written", dis.readBoolean() == true);
		} catch (java.io.IOException e) {
			fail("Exception during test : " + e.getMessage());
		}
	}

	/**
	 * @tests java.io.PrintStream#println()
	 */
	public void test_println() {
		// Test for method void java.io.PrintStream.println()
		char c;
		os = new java.io.PrintStream(bos, true);
		os.println("");
		bis = new java.io.ByteArrayInputStream(bos.toByteArray());
		assertTrue("Newline not written", (c = (char) bis.read()) == '\r'
				|| c == '\n');
	}

	/**
	 * @tests java.io.PrintStream#println(char[])
	 */
	public void test_println$C() {
		// Test for method void java.io.PrintStream.println(char [])
		os = new java.io.PrintStream(bos, true);
		char[] sc = new char[4000];
		fileString.getChars(0, fileString.length(), sc, 0);
		os.println(sc);
		bis = new java.io.ByteArrayInputStream(bos.toByteArray());
		byte[] rbytes = new byte[4000];
		bis.read(rbytes, 0, fileString.length());
		assertTrue("Incorrect char[] written", new String(rbytes, 0, fileString
				.length()).equals(fileString));
		// In this particular test method, the end of data is not immediately
		// followed by newLine separator in the reading buffer, instead its
		// followed by zeros. The newline is written as the last entry
		// in the inputStream buffer. Therefore, we must keep reading until we
		// hit a new line.
		int r;
		boolean newline = false;
		while ((r = bis.read()) != -1) {
			if (r == '\r' || r == '\n')
				newline = true;
		}
		assertTrue("Newline not written", newline);
	}

	/**
	 * @tests java.io.PrintStream#println(char)
	 */
	public void test_printlnC() {
		// Test for method void java.io.PrintStream.println(char)
		int c;
		os = new java.io.PrintStream(bos, true);
		os.println('t');
		bis = new java.io.ByteArrayInputStream(bos.toByteArray());
		assertTrue("Incorrect char written", bis.read() == 't');
		assertTrue("Newline not written", (c = bis.read()) == '\r' || c == '\n');
	}

	/**
	 * @tests java.io.PrintStream#println(double)
	 */
	public void test_printlnD() {
		// Test for method void java.io.PrintStream.println(double)
		int c;
		os = new java.io.PrintStream(bos, true);
		os.println(2345.76834720202);
		bis = new java.io.ByteArrayInputStream(bos.toByteArray());
		byte[] rbuf = new byte[100];
		bis.read(rbuf, 0, 16);
		assertTrue("Incorrect double written", new String(rbuf, 0, 16)
				.equals("2345.76834720202"));
		assertTrue("Newline not written", (c = bis.read()) == '\r' || c == '\n');
	}

	/**
	 * @tests java.io.PrintStream#println(float)
	 */
	public void test_printlnF() {
		// Test for method void java.io.PrintStream.println(float)
		int c;
		byte[] rbuf = new byte[100];
		os = new java.io.PrintStream(bos, true);
		os.println(29.08764f);
		bis = new java.io.ByteArrayInputStream(bos.toByteArray());
		bis.read(rbuf, 0, 8);
		assertTrue("Incorrect float written", new String(rbuf, 0, 8)
				.equals("29.08764"));
		assertTrue("Newline not written", (c = bis.read()) == '\r' || c == '\n');
	}

	/**
	 * @tests java.io.PrintStream#println(int)
	 */
	public void test_printlnI() {
		// Test for method void java.io.PrintStream.println(int)
		int c;
		os = new java.io.PrintStream(bos, true);
		os.println(768347202);
		byte[] rbuf = new byte[100];
		bis = new java.io.ByteArrayInputStream(bos.toByteArray());
		bis.read(rbuf, 0, 9);
		assertTrue("Incorrect int written", new String(rbuf, 0, 9)
				.equals("768347202"));
		assertTrue("Newline not written", (c = bis.read()) == '\r' || c == '\n');
	}

	/**
	 * @tests java.io.PrintStream#println(long)
	 */
	public void test_printlnJ() {
		// Test for method void java.io.PrintStream.println(long)
		int c;
		os = new java.io.PrintStream(bos, true);
		os.println(9875645283333L);
		bis = new java.io.ByteArrayInputStream(bos.toByteArray());
		byte[] rbuf = new byte[100];
		bis.read(rbuf, 0, 13);
		assertTrue("Incorrect long written", new String(rbuf, 0, 13)
				.equals("9875645283333"));
		assertTrue("Newline not written", (c = bis.read()) == '\r' || c == '\n');
	}

	/**
	 * @tests java.io.PrintStream#println(java.lang.Object)
	 */
	public void test_printlnLjava_lang_Object() {
		// Test for method void java.io.PrintStream.println(java.lang.Object)
		char c;
		os = new java.io.PrintStream(bos, true);
		os.println(new java.util.Vector());
		bis = new java.io.ByteArrayInputStream(bos.toByteArray());
		byte[] rbytes = new byte[2];
		bis.read(rbytes, 0, 2);
		assertTrue("Incorrect Vector written", new String(rbytes, 0, 2)
				.equals("[]"));
		assertTrue("Newline not written", (c = (char) bis.read()) == '\r'
				|| c == '\n');
	}

	/**
	 * @tests java.io.PrintStream#println(java.lang.String)
	 */
	public void test_printlnLjava_lang_String() {
		// Test for method void java.io.PrintStream.println(java.lang.String)
		char c;
		os = new java.io.PrintStream(bos, true);
		os.println("Hello World");
		bis = new java.io.ByteArrayInputStream(bos.toByteArray());
		byte rbytes[] = new byte[100];
		bis.read(rbytes, 0, 11);
		assertTrue("Incorrect string written", new String(rbytes, 0, 11)
				.equals("Hello World"));
		assertTrue("Newline not written", (c = (char) bis.read()) == '\r'
				|| c == '\n');
	}

	/**
	 * @tests java.io.PrintStream#println(boolean)
	 */
	public void test_printlnZ() {
		// Test for method void java.io.PrintStream.println(boolean)
		int c;
		os = new java.io.PrintStream(bos, true);
		os.println(true);
		bis = new java.io.ByteArrayInputStream(bos.toByteArray());
		byte[] rbuf = new byte[100];
		bis.read(rbuf, 0, 4);
		assertTrue("Incorrect boolean written", new String(rbuf, 0, 4)
				.equals("true"));
		assertTrue("Newline not written", (c = bis.read()) == '\r' || c == '\n');
	}

	/**
	 * @tests java.io.PrintStream#write(byte[], int, int)
	 */
	public void test_write$BII() {
		// Test for method void java.io.PrintStream.write(byte [], int, int)
		os = new java.io.PrintStream(bos, true);
		os.write(fileString.getBytes(), 0, fileString.length());
		bis = new java.io.ByteArrayInputStream(bos.toByteArray());
		byte rbytes[] = new byte[4000];
		bis.read(rbytes, 0, fileString.length());
		assertTrue("Incorrect bytes written", new String(rbytes, 0, fileString
				.length()).equals(fileString));
	}

	/**
	 * @tests java.io.PrintStream#write(int)
	 */
	public void test_writeI() {
		// Test for method void java.io.PrintStream.write(int)
		os = new java.io.PrintStream(bos, true);
		os.write('t');
		bis = new java.io.ByteArrayInputStream(bos.toByteArray());
		assertTrue("Incorrect char written", bis.read() == 't');
	}

	/**
	 * Sets up the fixture, for example, open a network connection. This method
	 * is called before a test is executed.
	 */
	protected void setUp() {
		try {
			bos = new java.io.ByteArrayOutputStream();
		} catch (Throwable e) {
			fail("Exception during setup : " + e.getMessage());
		}
	}

	/**
	 * Tears down the fixture, for example, close a network connection. This
	 * method is called after a test is executed.
	 */
	protected void tearDown() {

		try {
			os.close();
			bis.close();
		} catch (Throwable e) {
		}
	}
}
