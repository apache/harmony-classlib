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

import tests.support.Support_PlatformFile;

public class RandomAccessFileTest extends junit.framework.TestCase {

	public String fileName;

	public boolean ufile = true;

	java.io.RandomAccessFile raf;

	java.io.FileInputStream fis;

	java.io.File f;

	String unihw = "\u0048\u0065\u006C\u0801\u006C\u006F\u0020\u0057\u0081\u006F\u0072\u006C\u0064";

	java.io.FileOutputStream fos;

	public String fileString = "Test_All_Tests\nTest_java_io_BufferedInputStream\nTest_java_io_BufferedOutputStream\nTest_java_io_ByteArrayInputStream\nTest_java_io_ByteArrayOutputStream\nTest_java_io_DataInputStream\nTest_java_io_File\nTest_java_io_FileDescriptor\nTest_java_io_FileInputStream\nTest_java_io_FileNotFoundException\nTest_java_io_FileOutputStream\nTest_java_io_FilterInputStream\nTest_java_io_FilterOutputStream\nTest_java_io_InputStream\nTest_java_io_IOException\nTest_java_io_OutputStream\nTest_java_io_PrintStream\nTest_RandomAccessFile\nTest_java_io_SyncFailedException\nTest_java_lang_AbstractMethodError\nTest_java_lang_ArithmeticException\nTest_java_lang_ArrayIndexOutOfBoundsException\nTest_java_lang_ArrayStoreException\nTest_java_lang_Boolean\nTest_java_lang_Byte\nTest_java_lang_Character\nTest_java_lang_Class\nTest_java_lang_ClassCastException\nTest_java_lang_ClassCircularityError\nTest_java_lang_ClassFormatError\nTest_java_lang_ClassLoader\nTest_java_lang_ClassNotFoundException\nTest_java_lang_CloneNotSupportedException\nTest_java_lang_Double\nTest_java_lang_Error\nTest_java_lang_Exception\nTest_java_lang_ExceptionInInitializerError\nTest_java_lang_Float\nTest_java_lang_IllegalAccessError\nTest_java_lang_IllegalAccessException\nTest_java_lang_IllegalArgumentException\nTest_java_lang_IllegalMonitorStateException\nTest_java_lang_IllegalThreadStateException\nTest_java_lang_IncompatibleClassChangeError\nTest_java_lang_IndexOutOfBoundsException\nTest_java_lang_InstantiationError\nTest_java_lang_InstantiationException\nTest_java_lang_Integer\nTest_java_lang_InternalError\nTest_java_lang_InterruptedException\nTest_java_lang_LinkageError\nTest_java_lang_Long\nTest_java_lang_Math\nTest_java_lang_NegativeArraySizeException\nTest_java_lang_NoClassDefFoundError\nTest_java_lang_NoSuchFieldError\nTest_java_lang_NoSuchMethodError\nTest_java_lang_NullPointerException\nTest_java_lang_Number\nTest_java_lang_NumberFormatException\nTest_java_lang_Object\nTest_java_lang_OutOfMemoryError\nTest_java_lang_RuntimeException\nTest_java_lang_SecurityManager\nTest_java_lang_Short\nTest_java_lang_StackOverflowError\nTest_java_lang_String\nTest_java_lang_StringBuffer\nTest_java_lang_StringIndexOutOfBoundsException\nTest_java_lang_System\nTest_java_lang_Thread\nTest_java_lang_ThreadDeath\nTest_java_lang_ThreadGroup\nTest_java_lang_Throwable\nTest_java_lang_UnknownError\nTest_java_lang_UnsatisfiedLinkError\nTest_java_lang_VerifyError\nTest_java_lang_VirtualMachineError\nTest_java_lang_vm_Image\nTest_java_lang_vm_MemorySegment\nTest_java_lang_vm_ROMStoreException\nTest_java_lang_vm_VM\nTest_java_lang_Void\nTest_java_net_BindException\nTest_java_net_ConnectException\nTest_java_net_DatagramPacket\nTest_java_net_DatagramSocket\nTest_java_net_DatagramSocketImpl\nTest_java_net_InetAddress\nTest_java_net_NoRouteToHostException\nTest_java_net_PlainDatagramSocketImpl\nTest_java_net_PlainSocketImpl\nTest_java_net_Socket\nTest_java_net_SocketException\nTest_java_net_SocketImpl\nTest_java_net_SocketInputStream\nTest_java_net_SocketOutputStream\nTest_java_net_UnknownHostException\nTest_java_util_ArrayEnumerator\nTest_java_util_Date\nTest_java_util_EventObject\nTest_java_util_HashEnumerator\nTest_java_util_Hashtable\nTest_java_util_Properties\nTest_java_util_ResourceBundle\nTest_java_util_tm\nTest_java_util_Vector\n";

	/**
	 * @tests java.io.RandomAccessFile#RandomAccessFile(java.io.File,
	 *        java.lang.String)
	 */
	public void test_ConstructorLjava_io_FileLjava_lang_String() {
		// Test for method java.io.RandomAccessFile(java.io.File,
		// java.lang.String)
		try {
			raf = new java.io.RandomAccessFile(f, "rw");
			raf.write(20);
			raf.seek(0);
			assertTrue("Incorrect int read/written", raf.read() == 20);
			raf.close();
		} catch (Exception e) {
			fail("Exception during constructor test: " + e.toString());
		}
	}

	/**
	 * @tests java.io.RandomAccessFile#RandomAccessFile(java.lang.String,
	 *        java.lang.String)
	 */
	public void test_ConstructorLjava_lang_StringLjava_lang_String() {
		// Test for method java.io.RandomAccessFile(java.lang.String,
		// java.lang.String)
		try {
			raf = new java.io.RandomAccessFile(fileName, "rw");
			raf.write("Test".getBytes(), 0, 4);
		} catch (java.io.IOException e) {
			fail("Constructor test threw an IOException : " + e.getMessage());
		}
	}

	/**
	 * @tests java.io.RandomAccessFile#close()
	 */
	public void test_close() {
		// Test for method void java.io.RandomAccessFile.close()
		try {
			raf = new java.io.RandomAccessFile(fileName, "rw");
			raf.close();
			raf.write("Test".getBytes(), 0, 4);
			fail("Failed to close file properly");
		} catch (java.io.IOException e) {
		}

	}

	/**
	 * @tests java.io.RandomAccessFile#getFD()
	 */
	public void test_getFD() {
		// Test for method java.io.FileDescriptor
		// java.io.RandomAccessFile.getFD()
		try {
			raf = new java.io.RandomAccessFile(fileName, "rw");
			assertTrue("Returned invalid fd", raf.getFD().valid());
			raf.close();
			assertTrue("Returned valid fd after close", !raf.getFD().valid());
		} catch (java.io.IOException e) {
			fail("getFD test threw an IOException : " + e.getMessage());
		}
	}

	/**
	 * @tests java.io.RandomAccessFile#getFilePointer()
	 */
	public void test_getFilePointer() {
		// Test for method long java.io.RandomAccessFile.getFilePointer()
		try {
			raf = new java.io.RandomAccessFile(fileName, "rw");
			raf.write(fileString.getBytes(), 0, 1000);
			assertTrue("Incorrect filePointer returned",
					raf.getFilePointer() == 1000);
		} catch (java.io.IOException e) {
			fail("getFilePointer test threw an IOException : " + e.getMessage());
		}
	}

	/**
	 * @tests java.io.RandomAccessFile#length()
	 */
	public void test_length() {
		// Test for method long java.io.RandomAccessFile.length()
		try {
			raf = new java.io.RandomAccessFile(fileName, "rw");
			raf.write(fileString.getBytes());
			assertTrue("Incorrect length returned", raf.length() == fileString
					.length());
		} catch (java.io.IOException e) {
			fail("length test threw an IOException : " + e.getMessage());
		}
	}

	/**
	 * @tests java.io.RandomAccessFile#read()
	 */
	public void test_read() {
		// Test for method int java.io.RandomAccessFile.read()
		try {
			java.io.FileOutputStream fos = new java.io.FileOutputStream(
					fileName);
			fos.write(fileString.getBytes(), 0, fileString.length());
			fos.close();
			int c;
			raf = new java.io.RandomAccessFile(fileName, "r");
			c = raf.read();
			assertTrue("Incorrect bytes returned from read", c == fileString
					.charAt(0));
		} catch (java.io.IOException e) {
			fail("Read test threw an IOException : " + e.getMessage());
		}

	}

	/**
	 * @tests java.io.RandomAccessFile#read(byte[])
	 */
	public void test_read$B() {
		// Test for method int java.io.RandomAccessFile.read(byte [])
		try {
			java.io.FileOutputStream fos = new java.io.FileOutputStream(
					fileName);
			fos.write(fileString.getBytes(), 0, fileString.length());
			fos.close();
			raf = new java.io.RandomAccessFile(fileName, "r");
			byte[] rbuf = new byte[4000];
			raf.read(rbuf);
			assertTrue("Incorrect bytes returned from read", fileString
					.equals(new String(rbuf, 0, fileString.length())));
		} catch (java.io.IOException e) {
			fail("Read test threw an IOException : " + e.getMessage());
		}

	}

	/**
	 * @tests java.io.RandomAccessFile#read(byte[], int, int)
	 */
	public void test_read$BII() {
		// Test for method int java.io.RandomAccessFile.read(byte [], int, int)
		try {
			raf = new java.io.RandomAccessFile(fileName, "rw");
			byte[] rbuf = new byte[4000];
			java.io.FileOutputStream fos = new java.io.FileOutputStream(
					fileName);
			fos.write(fileString.getBytes(), 0, fileString.length());
			fos.close();
			raf.read(rbuf, 0, fileString.length());
			assertTrue("Incorrect bytes returned from read", fileString
					.equals(new String(rbuf, 0, fileString.length())));
		} catch (java.io.IOException e) {
			fail("Read test threw an IOException : " + e.getMessage());
		}
	}

	/**
	 * @tests java.io.RandomAccessFile#readBoolean()
	 */
	public void test_readBoolean() {
		// Test for method boolean java.io.RandomAccessFile.readBoolean()
		try {
			raf = new java.io.RandomAccessFile(fileName, "rw");
			raf.writeBoolean(true);
			raf.seek(0);
			assertTrue("Incorrect boolean read/written", raf.readBoolean());
			raf.close();
		} catch (java.io.IOException e) {
			fail("readBoolean test threw an IOException : " + e.getMessage());
		}
	}

	/**
	 * @tests java.io.RandomAccessFile#readByte()
	 */
	public void test_readByte() {
		// Test for method byte java.io.RandomAccessFile.readByte()
		try {
			raf = new java.io.RandomAccessFile(fileName, "rw");
			raf.writeByte(127);
			raf.seek(0);
			assertTrue("Incorrect bytes read/written", raf.readByte() == 127);
			raf.close();
		} catch (java.io.IOException e) {
			fail("readByte test threw an IOException : " + e.getMessage());
		}
	}

	/**
	 * @tests java.io.RandomAccessFile#readChar()
	 */
	public void test_readChar() {
		// Test for method char java.io.RandomAccessFile.readChar()
		try {
			raf = new java.io.RandomAccessFile(fileName, "rw");
			raf.writeChar('T');
			raf.seek(0);
			assertTrue("Incorrect char read/written", raf.readChar() == 'T');
			raf.close();
		} catch (java.io.IOException e) {
			fail("readChar test threw an IOException : " + e.getMessage());
		}
	}

	/**
	 * @tests java.io.RandomAccessFile#readDouble()
	 */
	public void test_readDouble() {
		// Test for method double java.io.RandomAccessFile.readDouble()
		try {
			raf = new java.io.RandomAccessFile(fileName, "rw");
			raf.writeDouble(Double.MAX_VALUE);
			raf.seek(0);
			assertTrue("Incorrect double read/written",
					raf.readDouble() == Double.MAX_VALUE);
			raf.close();
		} catch (java.io.IOException e) {
			fail("readDouble test threw an IOException : " + e.getMessage());
		}
	}

	/**
	 * @tests java.io.RandomAccessFile#readFloat()
	 */
	public void test_readFloat() {
		// Test for method float java.io.RandomAccessFile.readFloat()
		try {
			raf = new java.io.RandomAccessFile(fileName, "rw");
			raf.writeFloat(Float.MAX_VALUE);
			raf.seek(0);
			assertTrue("Incorrect float read/written",
					raf.readFloat() == Float.MAX_VALUE);
			raf.close();
		} catch (java.io.IOException e) {
			fail("readFloat test threw an IOException : " + e.getMessage());
		}
	}

	/**
	 * @tests java.io.RandomAccessFile#readFully(byte[])
	 */
	public void test_readFully$B() {
		// Test for method void java.io.RandomAccessFile.readFully(byte [])
		try {
			byte[] buf = new byte[10];
			raf = new java.io.RandomAccessFile(fileName, "rw");
			raf.writeBytes("HelloWorld");
			raf.seek(0);
			raf.readFully(buf);
			assertTrue("Incorrect bytes read/written", "HelloWorld"
					.equals(new String(buf, 0, 10)));
			raf.close();
		} catch (java.io.IOException e) {
			fail("readFully threw an IOException : " + e.getMessage());
		}
	}

	/**
	 * @tests java.io.RandomAccessFile#readFully(byte[], int, int)
	 */
	public void test_readFully$BII() {
		// Test for method void java.io.RandomAccessFile.readFully(byte [], int,
		// int)
		try {
			byte[] buf = new byte[10];
			raf = new java.io.RandomAccessFile(fileName, "rw");
			raf.writeBytes("HelloWorld");
			raf.seek(0);
			raf.readFully(buf, 0, buf.length);
			assertTrue("Incorrect bytes read/written", "HelloWorld"
					.equals(new String(buf, 0, 10)));
			try {
				raf.readFully(buf, 0, buf.length);
			} catch (java.io.EOFException e) {
				// correct
				return;
			}
			fail("Reading past end of buffer did not throw EOFException");
		} catch (java.io.IOException e) {
			fail("readFully test threw an IOException : " + e.getMessage());
		}
	}

	/**
	 * @tests java.io.RandomAccessFile#readInt()
	 */
	public void test_readInt() {
		// Test for method int java.io.RandomAccessFile.readInt()
		try {
			raf = new java.io.RandomAccessFile(fileName, "rw");
			raf.writeInt(Integer.MIN_VALUE);
			raf.seek(0);
			assertTrue("Incorrect int read/written",
					raf.readInt() == Integer.MIN_VALUE);
			raf.close();
		} catch (java.io.IOException e) {
			fail("readInt test threw an IOException : " + e.getMessage());
		}
	}

	/**
	 * @tests java.io.RandomAccessFile#readLine()
	 */
	public void test_readLine() {
		// Test for method java.lang.String java.io.RandomAccessFile.readLine()
		try {
			raf = new java.io.RandomAccessFile(fileName, "rw");
			String s = "Goodbye\nCruel\nWorld\n";
			raf.write(s.getBytes(), 0, s.length());
			raf.seek(0);
			assertTrue("1st readLine returned incorrect string", "Goodbye"
					.equals(raf.readLine()));
			assertTrue("2nd readLine returned incorrect string", "Cruel"
					.equals(raf.readLine()));
			assertTrue("3rd readLine returned incorrect string", "World"
					.equals(raf.readLine()));
		} catch (java.io.IOException e) {
			fail("readLine test threw an IOException : " + e.getMessage());
		}

	}

	/**
	 * @tests java.io.RandomAccessFile#readLong()
	 */
	public void test_readLong() {
		// Test for method long java.io.RandomAccessFile.readLong()
		try {
			raf = new java.io.RandomAccessFile(fileName, "rw");
			raf.writeLong(Long.MAX_VALUE);
			raf.seek(0);
			assertTrue("Incorrect long read/written",
					raf.readLong() == Long.MAX_VALUE);
			raf.close();
		} catch (java.io.IOException e) {
			fail("readLongtest threw an IOException : " + e.getMessage());
		}
	}

	/**
	 * @tests java.io.RandomAccessFile#readShort()
	 */
	public void test_readShort() {
		// Test for method short java.io.RandomAccessFile.readShort()
		try {
			raf = new java.io.RandomAccessFile(fileName, "rw");
			raf.writeShort(Short.MIN_VALUE);
			raf.seek(0);
			assertTrue("Incorrect long read/written",
					raf.readShort() == Short.MIN_VALUE);
			raf.close();
		} catch (java.io.IOException e) {
			fail("readShort test threw an IOException : " + e.getMessage());
		}
	}

	/**
	 * @tests java.io.RandomAccessFile#readUnsignedByte()
	 */
	public void test_readUnsignedByte() {
		// Test for method int java.io.RandomAccessFile.readUnsignedByte()
		try {
			raf = new java.io.RandomAccessFile(fileName, "rw");
			raf.writeByte(-1);
			raf.seek(0);
			assertTrue("Incorrect byte read/written",
					raf.readUnsignedByte() == 255);
			raf.close();
		} catch (java.io.IOException e) {
			fail("readUnsignedByte test threw an IOException : "
					+ e.getMessage());
		}
	}

	/**
	 * @tests java.io.RandomAccessFile#readUnsignedShort()
	 */
	public void test_readUnsignedShort() {
		// Test for method int java.io.RandomAccessFile.readUnsignedShort()
		try {
			raf = new java.io.RandomAccessFile(fileName, "rw");
			raf.writeShort(-1);
			raf.seek(0);
			assertTrue("Incorrect byte read/written",
					raf.readUnsignedShort() == 65535);
			raf.close();
		} catch (java.io.IOException e) {
			fail("readUnsignedShort test threw an IOException : " + e.getMessage());
		}
	}

	/**
	 * @tests java.io.RandomAccessFile#readUTF()
	 */
	public void test_readUTF() {
		// Test for method java.lang.String java.io.RandomAccessFile.readUTF()
		try {
			raf = new java.io.RandomAccessFile(fileName, "rw");
			raf.writeUTF(unihw);
			raf.seek(0);
			assertTrue("Incorrect utf string read", raf.readUTF().equals(unihw));
			raf.close();
		} catch (java.io.IOException e) {
			fail("readUTF test threw an IOException : " + e.getMessage());
		}
	}

	/**
	 * @tests java.io.RandomAccessFile#seek(long)
	 */
	public void test_seekJ() {
		// Test for method void java.io.RandomAccessFile.seek(long)
		try {
			raf = new java.io.RandomAccessFile(fileName, "rw");
			raf.write(fileString.getBytes(), 0, fileString.length());
			raf.seek(12);
			assertTrue("Seek failed to set filePointer",
					raf.getFilePointer() == 12);
		} catch (java.io.IOException e) {
			fail("seek test threw an IOException : " + e.getMessage());
		}
	}

	/**
	 * @tests java.io.RandomAccessFile#skipBytes(int)
	 */
	public void test_skipBytesI() {
		// Test for method int java.io.RandomAccessFile.skipBytes(int)
		try {
			byte[] buf = new byte[5];
			raf = new java.io.RandomAccessFile(fileName, "rw");
			raf.writeBytes("HelloWorld");
			raf.seek(0);
			raf.skipBytes(5);
			raf.readFully(buf);
			assertTrue("Failed to skip bytes", "World".equals(new String(buf,
					0, 5)));
			raf.close();
		} catch (java.io.IOException e) {
			fail("skipBytes threw an IOException : " + e.getMessage());
		}
	}

	/**
	 * @tests java.io.RandomAccessFile#write(byte[])
	 */
	public void test_write$B() {
		// Test for method void java.io.RandomAccessFile.write(byte [])
		try {
			raf = new java.io.RandomAccessFile(fileName, "rw");
			byte[] rbuf = new byte[4000];
			raf.write(fileString.getBytes());
			raf.close();
			fis = new java.io.FileInputStream(fileName);
			fis.read(rbuf, 0, fileString.length());
			assertTrue("Incorrect bytes written", fileString.equals(new String(
					rbuf, 0, fileString.length())));
		} catch (java.io.IOException e) {
			fail("Write test threw an IOException : " + e.getMessage());
		}
	}

	/**
	 * @tests java.io.RandomAccessFile#write(byte[], int, int)
	 */
	public void test_write$BII() {
		// Test for method void java.io.RandomAccessFile.write(byte [], int,
		// int)
		try {
			raf = new java.io.RandomAccessFile(fileName, "rw");
			byte[] rbuf = new byte[4000];
			raf.write(fileString.getBytes(), 0, fileString.length());
			raf.close();
			fis = new java.io.FileInputStream(fileName);
			fis.read(rbuf, 0, fileString.length());
			assertTrue("Incorrect bytes written", fileString.equals(new String(
					rbuf, 0, fileString.length())));
		} catch (java.io.IOException e) {
			fail("Write test threw an IOException : " + e.getMessage());
		}
	}

	/**
	 * @tests java.io.RandomAccessFile#write(int)
	 */
	public void test_writeI() {
		// Test for method void java.io.RandomAccessFile.write(int)
		try {
			byte[] rbuf = new byte[4000];
			raf = new java.io.RandomAccessFile(fileName, "rw");
			raf.write('t');
			raf.close();
			fis = new java.io.FileInputStream(fileName);
			fis.read(rbuf, 0, 1);
			assertTrue("Incorrect byte written", 't' == rbuf[0]);
		} catch (java.io.IOException e) {
			fail("Write test threw an IOException : " + e.getMessage());
		}
	}

	/**
	 * @tests java.io.RandomAccessFile#writeBoolean(boolean)
	 */
	public void test_writeBooleanZ() {
		// Test for method void java.io.RandomAccessFile.writeBoolean(boolean)
		try {
			raf = new java.io.RandomAccessFile(fileName, "rw");
			raf.writeBoolean(true);
			raf.seek(0);
			assertTrue("Incorrect boolean read/written", raf.readBoolean());
			raf.close();
		} catch (java.io.IOException e) {
			fail("writeBoolean test threw an IOException : " + e.getMessage());
		}
	}

	/**
	 * @tests java.io.RandomAccessFile#writeByte(int)
	 */
	public void test_writeByteI() {
		// Test for method void java.io.RandomAccessFile.writeByte(int)
		try {
			raf = new java.io.RandomAccessFile(fileName, "rw");
			raf.writeByte(127);
			raf.seek(0);
			assertTrue("Incorrect byte read/written", raf.readByte() == 127);
			raf.close();
		} catch (java.io.IOException e) {
			fail("Write test threw an IOException : " + e.getMessage());
		}
	}

	/**
	 * @tests java.io.RandomAccessFile#writeBytes(java.lang.String)
	 */
	public void test_writeBytesLjava_lang_String() {
		// Test for method void
		// java.io.RandomAccessFile.writeBytes(java.lang.String)
		try {
			byte[] buf = new byte[10];
			raf = new java.io.RandomAccessFile(fileName, "rw");
			raf.writeBytes("HelloWorld");
			raf.seek(0);
			raf.readFully(buf);
			assertTrue("Incorrect bytes read/written", "HelloWorld"
					.equals(new String(buf, 0, 10)));
			raf.close();
		} catch (java.io.IOException e) {
			fail("writeBytes threw an IOException : " + e.getMessage());
		}
	}

	/**
	 * @tests java.io.RandomAccessFile#writeChar(int)
	 */
	public void test_writeCharI() {
		// Test for method void java.io.RandomAccessFile.writeChar(int)
		try {
			raf = new java.io.RandomAccessFile(fileName, "rw");
			raf.writeChar('T');
			raf.seek(0);
			assertTrue("Incorrect char read/written", raf.readChar() == 'T');
			raf.close();
		} catch (java.io.IOException e) {
			fail("writeChar test threw an IOException : " + e.getMessage());
		}
	}

	/**
	 * @tests java.io.RandomAccessFile#writeChars(java.lang.String)
	 */
	public void test_writeCharsLjava_lang_String() {
		// Test for method void
		// java.io.RandomAccessFile.writeChars(java.lang.String)
		try {
			raf = new java.io.RandomAccessFile(fileName, "rw");
			raf.writeChars("HelloWorld");
			char[] hchars = new char[10];
			"HelloWorld".getChars(0, 10, hchars, 0);
			raf.seek(0);
			for (int i = 0; i < hchars.length; i++)
				assertTrue("Incorrect string written",
						raf.readChar() == hchars[i]);
			raf.close();
		} catch (java.io.IOException e) {
			fail("writeChars test threw an IOException : " + e.getMessage());
		}
	}

	/**
	 * @tests java.io.RandomAccessFile#writeDouble(double)
	 */
	public void test_writeDoubleD() {
		// Test for method void java.io.RandomAccessFile.writeDouble(double)
		try {
			raf = new java.io.RandomAccessFile(fileName, "rw");
			raf.writeDouble(Double.MAX_VALUE);
			raf.seek(0);
			assertTrue("Incorrect double read/written",
					raf.readDouble() == Double.MAX_VALUE);
			raf.close();
		} catch (java.io.IOException e) {
			fail("writeDouble test threw an IOException : " + e.getMessage());
		}
	}

	/**
	 * @tests java.io.RandomAccessFile#writeFloat(float)
	 */
	public void test_writeFloatF() {
		// Test for method void java.io.RandomAccessFile.writeFloat(float)
		try {
			raf = new java.io.RandomAccessFile(fileName, "rw");
			raf.writeFloat(Float.MAX_VALUE);
			raf.seek(0);
			assertTrue("Incorrect float read/written",
					raf.readFloat() == Float.MAX_VALUE);
			raf.close();
		} catch (java.io.IOException e) {
			fail("writeFloat test threw an IOException : " + e.getMessage());
		}
	}

	/**
	 * @tests java.io.RandomAccessFile#writeInt(int)
	 */
	public void test_writeIntI() {
		// Test for method void java.io.RandomAccessFile.writeInt(int)
		try {
			raf = new java.io.RandomAccessFile(fileName, "rw");
			raf.writeInt(Integer.MIN_VALUE);
			raf.seek(0);
			assertTrue("Incorrect int read/written",
					raf.readInt() == Integer.MIN_VALUE);
			raf.close();
		} catch (java.io.IOException e) {
			fail("writeLong test threw an IOException : " + e.getMessage());
		}
	}

	/**
	 * @tests java.io.RandomAccessFile#writeLong(long)
	 */
	public void test_writeLongJ() {
		// Test for method void java.io.RandomAccessFile.writeLong(long)
		try {
			raf = new java.io.RandomAccessFile(fileName, "rw");
			raf.writeLong(Long.MAX_VALUE);
			raf.seek(0);
			assertTrue("Incorrect long read/written",
					raf.readLong() == Long.MAX_VALUE);
			raf.close();
		} catch (java.io.IOException e) {
			fail("writeLong test threw an IOException : " + e.getMessage());
		}
	}

	/**
	 * @tests java.io.RandomAccessFile#writeShort(int)
	 */
	public void test_writeShortI() {
		// Test for method void java.io.RandomAccessFile.writeShort(int)
		try {
			raf = new java.io.RandomAccessFile(fileName, "rw");
			raf.writeShort(Short.MIN_VALUE);
			raf.seek(0);
			assertTrue("Incorrect long read/written",
					raf.readShort() == Short.MIN_VALUE);
			raf.close();
		} catch (java.io.IOException e) {
			fail("writeShort test threw an IOException : " + e.getMessage());
		}
	}

	/**
	 * @tests java.io.RandomAccessFile#writeUTF(java.lang.String)
	 */
	public void test_writeUTFLjava_lang_String() {
		// Test for method void
		// java.io.RandomAccessFile.writeUTF(java.lang.String)
		try {
			raf = new java.io.RandomAccessFile(fileName, "rw");
			raf.writeUTF(unihw);
			raf.seek(0);
			assertTrue("Incorrect utf string", raf.readUTF().equals(unihw));
			raf.close();
		} catch (java.io.IOException e) {
			fail("writeUTF test threw an IOException : " + e.getMessage());
		}
	}

	/**
	 * Sets up the fixture, for example, open a network connection. This method
	 * is called before a test is executed.
	 */
	protected void setUp() {
		try {
			String fname = Support_PlatformFile.getNewPlatformFile("",
					"raf.tst");
			f = new java.io.File(System.getProperty("user.dir"), fname);
			fileName = f.getAbsolutePath();
			if (f.exists())
				if (!f.delete()) {
					fail("Unable to delete test file : " + f);
				}
		} catch (Exception e) {
			fail("Exception during setUp : " + e.getMessage());
		}
	}

	/**
	 * Tears down the fixture, for example, close a network connection. This
	 * method is called after a test is executed.
	 */
	protected void tearDown() {
		try {
			if (fis != null)
				fis.close();
			if (fos != null)
				fos.close();
			if (raf != null)
				raf.close();
		} catch (Throwable e) {
		}
		if (f.exists())
			f.delete();
	}
}
