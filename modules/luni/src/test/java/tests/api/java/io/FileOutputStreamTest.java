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

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileOutputStreamTest extends junit.framework.TestCase {

	public String fileName;

	private java.io.InputStream is;

	java.io.FileOutputStream fos;

	java.io.FileInputStream fis;

	java.io.File f;

	byte[] ibuf = new byte[4096];

	public String fileString = "Test_All_Tests\nTest_java_io_BufferedInputStream\nTest_java_io_BufferedOutputStream\nTest_java_io_ByteArrayInputStream\nTest_java_io_ByteArrayOutputStream\nTest_java_io_DataInputStream\nTest_java_io_File\nTest_java_io_FileDescriptor\nTest_java_io_FileInputStream\nTest_java_io_FileNotFoundException\nTest_FileOutputStream\nTest_java_io_FilterInputStream\nTest_java_io_FilterOutputStream\nTest_java_io_InputStream\nTest_java_io_IOException\nTest_java_io_OutputStream\nTest_java_io_PrintStream\nTest_java_io_RandomAccessFile\nTest_java_io_SyncFailedException\nTest_java_lang_AbstractMethodError\nTest_java_lang_ArithmeticException\nTest_java_lang_ArrayIndexOutOfBoundsException\nTest_java_lang_ArrayStoreException\nTest_java_lang_Boolean\nTest_java_lang_Byte\nTest_java_lang_Character\nTest_java_lang_Class\nTest_java_lang_ClassCastException\nTest_java_lang_ClassCircularityError\nTest_java_lang_ClassFormatError\nTest_java_lang_ClassLoader\nTest_java_lang_ClassNotFoundException\nTest_java_lang_CloneNotSupportedException\nTest_java_lang_Double\nTest_java_lang_Error\nTest_java_lang_Exception\nTest_java_lang_ExceptionInInitializerError\nTest_java_lang_Float\nTest_java_lang_IllegalAccessError\nTest_java_lang_IllegalAccessException\nTest_java_lang_IllegalArgumentException\nTest_java_lang_IllegalMonitorStateException\nTest_java_lang_IllegalThreadStateException\nTest_java_lang_IncompatibleClassChangeError\nTest_java_lang_IndexOutOfBoundsException\nTest_java_lang_InstantiationError\nTest_java_lang_InstantiationException\nTest_java_lang_Integer\nTest_java_lang_InternalError\nTest_java_lang_InterruptedException\nTest_java_lang_LinkageError\nTest_java_lang_Long\nTest_java_lang_Math\nTest_java_lang_NegativeArraySizeException\nTest_java_lang_NoClassDefFoundError\nTest_java_lang_NoSuchFieldError\nTest_java_lang_NoSuchMethodError\nTest_java_lang_NullPointerException\nTest_java_lang_Number\nTest_java_lang_NumberFormatException\nTest_java_lang_Object\nTest_java_lang_OutOfMemoryError\nTest_java_lang_RuntimeException\nTest_java_lang_SecurityManager\nTest_java_lang_Short\nTest_java_lang_StackOverflowError\nTest_java_lang_String\nTest_java_lang_StringBuffer\nTest_java_lang_StringIndexOutOfBoundsException\nTest_java_lang_System\nTest_java_lang_Thread\nTest_java_lang_ThreadDeath\nTest_java_lang_ThreadGroup\nTest_java_lang_Throwable\nTest_java_lang_UnknownError\nTest_java_lang_UnsatisfiedLinkError\nTest_java_lang_VerifyError\nTest_java_lang_VirtualMachineError\nTest_java_lang_vm_Image\nTest_java_lang_vm_MemorySegment\nTest_java_lang_vm_ROMStoreException\nTest_java_lang_vm_VM\nTest_java_lang_Void\nTest_java_net_BindException\nTest_java_net_ConnectException\nTest_java_net_DatagramPacket\nTest_java_net_DatagramSocket\nTest_java_net_DatagramSocketImpl\nTest_java_net_InetAddress\nTest_java_net_NoRouteToHostException\nTest_java_net_PlainDatagramSocketImpl\nTest_java_net_PlainSocketImpl\nTest_java_net_Socket\nTest_java_net_SocketException\nTest_java_net_SocketImpl\nTest_java_net_SocketInputStream\nTest_java_net_SocketOutputStream\nTest_java_net_UnknownHostException\nTest_java_util_ArrayEnumerator\nTest_java_util_Date\nTest_java_util_EventObject\nTest_java_util_HashEnumerator\nTest_java_util_Hashtable\nTest_java_util_Properties\nTest_java_util_ResourceBundle\nTest_java_util_tm\nTest_java_util_Vector\n";

	/**
	 * @tests java.io.FileOutputStream#FileOutputStream(java.io.File)
	 */
	public void test_ConstructorLjava_io_File() {
		// Test for method java.io.FileOutputStream(java.io.File)
		try {
			f = new File(fileName = System.getProperty("user.home"), "fos.tst");
			fos = new java.io.FileOutputStream(f);
		} catch (java.io.IOException e) {
			fail("Exception during Constructor test" + e.toString());
		}
	}

	/**
	 * @tests java.io.FileOutputStream#FileOutputStream(java.io.FileDescriptor)
	 */
	public void test_ConstructorLjava_io_FileDescriptor() {
		// Test for method java.io.FileOutputStream(java.io.FileDescriptor)
		try {
			f = new File(fileName = System.getProperty("user.home"), "fos.tst");
			fileName = f.getAbsolutePath();
			fos = new FileOutputStream(fileName);
			fos.write('l');
			fos.close();
			fis = new FileInputStream(fileName);
			fos = new FileOutputStream(fis.getFD());
			fos.close();
			fis.close();
		} catch (Exception e) {
			fail("Exception during constrcutor test: " + e.toString());
		}
	}

	/**
	 * @tests java.io.FileOutputStream#FileOutputStream(java.lang.String)
	 */
	public void test_ConstructorLjava_lang_String() {
		// Test for method java.io.FileOutputStream(java.lang.String)
		try {
			f = new File(fileName = System.getProperty("user.home"), "fos.tst");
			fileName = f.getAbsolutePath();
			fos = new java.io.FileOutputStream(f);
		} catch (java.io.IOException e) {
			fail("Exception during Constructor test : " + e.getMessage());
		}
	}

	/**
	 * @tests java.io.FileOutputStream#FileOutputStream(java.lang.String,
	 *        boolean)
	 */
	public void test_ConstructorLjava_lang_StringZ() {
		// Test for method java.io.FileOutputStream(java.lang.String, boolean)
		try {
			f = new java.io.File(System.getProperty("user.home"), "fos.tst");
			fos = new java.io.FileOutputStream(f.getPath(), false);
			fos.write("HI".getBytes(), 0, 2);
			fos.close();
			fos = new java.io.FileOutputStream(f.getPath(), true);
			fos.write(fileString.getBytes());
			fos.close();
			byte[] buf = new byte[fileString.length() + 2];
			fis = new FileInputStream(f.getPath());
			fis.read(buf, 0, buf.length);
			assertTrue("Failed to create appending stream", new String(buf, 0,
					buf.length).equals("HI" + fileString));
		} catch (java.io.IOException e) {
			fail("Exception during Constructor test : " + e.getMessage());
		}
	}

	/**
	 * @tests java.io.FileOutputStream#close()
	 */
	public void test_close() {
		// Test for method void java.io.FileOutputStream.close()

		try {
			f = new java.io.File(System.getProperty("user.home"), "output.tst");
			fos = new java.io.FileOutputStream(f.getPath());
			fos.close();
			fos.write(fileString.getBytes());
			fail("Close test failed - wrote to closed stream");
		} catch (java.io.IOException e) {
			// correct
		}

	}

	/**
	 * @tests java.io.FileOutputStream#getFD()
	 */
	public void test_getFD() {
		// Test for method java.io.FileDescriptor
		// java.io.FileOutputStream.getFD()
		try {
			f = new File(fileName = System.getProperty("user.home"), "testfd");
			fileName = f.getAbsolutePath();
			fos = new FileOutputStream(f);
			assertTrue("Returned invalid fd", fos.getFD().valid());
			fos.close();
			assertTrue("Returned invalid fd", !fos.getFD().valid());
		} catch (FileNotFoundException e) {
			fail("Could not find : " + fileName);
		} catch (IOException e) {
			fail("Exception during test : " + e.getMessage());
		}
	}

	/**
	 * @tests java.io.FileOutputStream#write(byte[])
	 */
	public void test_write$B() {
		// Test for method void java.io.FileOutputStream.write(byte [])
		try {
			f = new java.io.File(System.getProperty("user.home"), "output.tst");
			fos = new java.io.FileOutputStream(f.getPath());
			fos.write(fileString.getBytes());
			fis = new java.io.FileInputStream(f.getPath());
			byte rbytes[] = new byte[4000];
			fis.read(rbytes, 0, fileString.length());
			assertTrue("Incorrect string returned", new String(rbytes, 0,
					fileString.length()).equals(fileString));
		} catch (java.io.IOException e) {
			fail("Exception during write test : " + e.getMessage());
		}
	}

	/**
	 * @tests java.io.FileOutputStream#write(byte[], int, int)
	 */
	public void test_write$BII() {
		// Test for method void java.io.FileOutputStream.write(byte [], int,
		// int)
		try {
			f = new java.io.File(System.getProperty("user.home"), "output.tst");
			fos = new java.io.FileOutputStream(f.getPath());
			fos.write(fileString.getBytes(), 0, fileString.length());
			fis = new java.io.FileInputStream(f.getPath());
			byte rbytes[] = new byte[4000];
			fis.read(rbytes, 0, fileString.length());
			assertTrue("Incorrect bytes written", new String(rbytes, 0,
					fileString.length()).equals(fileString));
		} catch (java.io.IOException e) {
			fail("Exception during write test : " + e.getMessage());
		}
	}

	/**
	 * @tests java.io.FileOutputStream#write(int)
	 */
	public void test_writeI() {
		// Test for method void java.io.FileOutputStream.write(int)
		try {
			f = new java.io.File(System.getProperty("user.home"), "output.tst");
			fos = new java.io.FileOutputStream(f.getPath());
			fos.write('t');
			fis = new java.io.FileInputStream(f.getPath());
			assertEquals("Incorrect char written", 't', fis.read());
		} catch (java.io.IOException e) {
			fail("Exception during write test : " + e.getMessage());
		}

	}

	/**
	 * Sets up the fixture, for example, open a network connection. This method
	 * is called before a test is executed.
	 */
	protected void setUp() {
	}

	/**
	 * Tears down the fixture, for example, close a network connection. This
	 * method is called after a test is executed.
	 */
	protected void tearDown() {
		try {
			if (f != null)
				f.delete();
			if (fis != null)
				fis.close();
			if (fos != null)
				fos.close();
		} catch (Exception e) {
		}
	}
}
