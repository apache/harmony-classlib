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

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.NotActiveException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.io.StreamCorruptedException;
import java.util.Hashtable;
import java.util.Vector;

import tests.support.resource.Support_Resources;

public class ObjectInputStreamTest extends junit.framework.TestCase implements
		Serializable {

	ObjectInputStream ois;

	ObjectOutputStream oos;

	ByteArrayOutputStream bao;

	public class SerializableTestHelper implements Serializable {
		public String aField1;

		public String aField2;

		SerializableTestHelper() {
			aField1 = null;
			aField2 = null;
		}

		SerializableTestHelper(String s, String t) {
			aField1 = s;
			aField2 = t;
		}

		private void readObject(ObjectInputStream ois) throws IOException {
			// note aField2 is not read
			try {
				ObjectInputStream.GetField fields = ois.readFields();
				aField1 = (String) fields.get("aField1", "Zap");
			} catch (Exception e) {
				System.out.println("Exception during test: " + e.toString());
			}
		}

		private void writeObject(ObjectOutputStream oos) throws IOException {
			// note aField2 is not written
			ObjectOutputStream.PutField fields = oos.putFields();
			fields.put("aField1", aField1);
			oos.writeFields();
		}

		public String getText1() {
			return aField1;
		}

		public void setText1(String s) {
			aField1 = s;
		}

		public String getText2() {
			return aField2;
		}

		public void setText2(String s) {
			aField2 = s;
		}
	}

	public static class A1 implements Serializable {
		static final long serialVersionUID = 5942584913446079661L;

		B1 b1 = new B1();

		B1 b2 = b1;

		Vector v = new Vector();
	}

	public static class B1 implements Serializable {
		int i = 5;

		Hashtable h = new Hashtable();
	}

	/**
	 * @tests java.io.ObjectInputStream#readObject()
	 */
	public void test_readObjectMissingClasses() {
		try {
			// To create or update the resource, uncomment the following, and
			// the B1 class definition and references above.
			ObjectOutputStream out = new ObjectOutputStream(
					new FileOutputStream("hyts_missingclass.ser"));
			out.writeObject(new tests.api.java.io.ObjectInputStreamTest.A1());
			out.close();

			ObjectInputStream in = new ObjectInputStream(Support_Resources
					.getStream("hyts_missingclass.ser"));
			in.readObject();
			in.close();
			// the serialized data should load without any exceptions.
		} catch (Exception e) {
			e.printStackTrace();
			fail("unexpected: " + e);
		}
	}

	/**
	 * @tests java.io.ObjectInputStream#ObjectInputStream(java.io.InputStream)
	 */
	public void test_ConstructorLjava_io_InputStream() {
		// Test for method java.io.ObjectInputStream(java.io.InputStream)
		try {
			oos.writeDouble(Double.MAX_VALUE);
			oos.close();
			ois = new ObjectInputStream(new ByteArrayInputStream(bao
					.toByteArray()));
			ois.close();
			oos.close();
		} catch (IOException e) {
			fail("Exception contructing stream : " + e.getMessage());
		}
		boolean exception = false;
		try {
			ois = new ObjectInputStream(new ByteArrayInputStream(new byte[90]));
		} catch (StreamCorruptedException e) {
			// Correct
			exception = true;
		} catch (IOException e) {
			fail("Exception contructing stream : " + e.getMessage());
		}
		assertTrue("Expected exception", exception);
	}

	/**
	 * @tests java.io.ObjectInputStream#ObjectInputStream(java.io.InputStream)
	 */
	public void test_ConstructorLjava_io_InputStream_subtest0() {
		System.setSecurityManager(new SecurityManager());
		try {
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			ObjectOutputStream obout = new ObjectOutputStream(out);
			obout.write(0);
			obout.close();
			InputStream in = new ByteArrayInputStream(out.toByteArray());
			// should not cause SecurityException
			new ObjectInputStream(in);
			class SubTest1 extends ObjectInputStream {
				SubTest1(InputStream in) throws IOException {
					super(in);
				}
			}
			;
			in.reset();
			// should not cause SecurityException
			new SubTest1(in);
			class SubTest2 extends ObjectInputStream {
				SubTest2(InputStream in) throws IOException {
					super(in);
				}

				public Object readUnshared() throws IOException,
						ClassNotFoundException {
					return null;
				}
			}
			;
			in.reset();
			try {
				new SubTest2(in);
				fail("should throw SecurityException 1");
			} catch (SecurityException e) {
			}
			class SubTest3 extends ObjectInputStream {
				SubTest3(InputStream in) throws IOException {
					super(in);
				}

				public GetField readFields() throws IOException,
						ClassNotFoundException, NotActiveException {
					return null;
				}
			}
			;
			in.reset();
			try {
				new SubTest3(in);
				fail("should throw SecurityException 2");
			} catch (SecurityException e) {
			}
		} catch (IOException e) {
			fail("Unexpeced: " + e);
		} finally {
			System.setSecurityManager(null);
		}
	}

	/**
	 * @tests java.io.ObjectInputStream#available()
	 */
	public void test_available() {
		// Test for method int java.io.ObjectInputStream.available()
		try {
			oos.writeBytes("HelloWorld");
			oos.close();
			ois = new ObjectInputStream(new ByteArrayInputStream(bao
					.toByteArray()));
			assertTrue("Read incorrect bytes", ois.available() == 10);
			ois.close();
		} catch (IOException e) {
			fail("Exception serializing data : " + e.getMessage());
		}
	}

	/**
	 * @tests java.io.ObjectInputStream#close()
	 */
	public void test_close() {
		// Test for method void java.io.ObjectInputStream.close()
		try {
			oos.writeBytes("HelloWorld");
			oos.close();
			ois = new ObjectInputStream(new ByteArrayInputStream(bao
					.toByteArray()));
			ois.close();
		} catch (IOException e) {
			fail("Failed closing stream : " + e.getMessage());
		}
	}

	/**
	 * @tests java.io.ObjectInputStream#defaultReadObject()
	 */
	public void test_defaultReadObject() {
		// Test for method void java.io.ObjectInputStream.defaultReadObject()
		// SM. This method may as well be private, as if called directly it
		// throws an exception.
		try {
			String s = "HelloWorld";
			oos.writeObject(s);
			oos.close();
			ois = new ObjectInputStream(new ByteArrayInputStream(bao
					.toByteArray()));
			ois.defaultReadObject();
			fail("defaultReadObject should fail.");
			ois.close();
		} catch (NotActiveException e) {
			// Desired behavior.
			return;
		} catch (Exception e) {
			fail("Wrong exception during test : " + e.getMessage());
		}
		fail("defaultReadObject should have thrown exception");
	}

	/**
	 * @tests java.io.ObjectInputStream#read()
	 */
	public void test_read() {
		// Test for method int java.io.ObjectInputStream.read()
		try {
			oos.write('T');
			oos.close();
			ois = new ObjectInputStream(new ByteArrayInputStream(bao
					.toByteArray()));
			assertTrue("Read incorrect byte value", ois.read() == 'T');
			ois.close();
		} catch (IOException e) {
			fail("Exception serializing data : " + e.getMessage());
		}
	}

	/**
	 * @tests java.io.ObjectInputStream#read(byte[], int, int)
	 */
	public void test_read$BII() {
		// Test for method int java.io.ObjectInputStream.read(byte [], int, int)
		try {
			byte[] buf = new byte[10];
			oos.writeBytes("HelloWorld");
			oos.close();
			ois = new ObjectInputStream(new ByteArrayInputStream(bao
					.toByteArray()));
			ois.read(buf, 0, 10);
			ois.close();
			assertTrue("Read incorrect bytes", new String(buf, 0, 10)
					.equals("HelloWorld"));
		} catch (IOException e) {
			fail("Exception serializing data : " + e.getMessage());
		}
	}

	/**
	 * @tests java.io.ObjectInputStream#readBoolean()
	 */
	public void test_readBoolean() {
		// Test for method boolean java.io.ObjectInputStream.readBoolean()
		try {
			oos.writeBoolean(true);
			oos.close();
			ois = new ObjectInputStream(new ByteArrayInputStream(bao
					.toByteArray()));
			assertTrue("Read incorrect boolean value", ois.readBoolean());
			ois.close();
		} catch (IOException e) {
			fail("Exception serializing data : " + e.getMessage());
		}
	}

	/**
	 * @tests java.io.ObjectInputStream#readByte()
	 */
	public void test_readByte() {
		// Test for method byte java.io.ObjectInputStream.readByte()
		try {
			oos.writeByte(127);
			oos.close();
			ois = new ObjectInputStream(new ByteArrayInputStream(bao
					.toByteArray()));
			assertTrue("Read incorrect byte value", ois.readByte() == 127);
			ois.close();
		} catch (IOException e) {
			fail("Exception serializing data : " + e.getMessage());
		}
	}

	/**
	 * @tests java.io.ObjectInputStream#readChar()
	 */
	public void test_readChar() {
		// Test for method char java.io.ObjectInputStream.readChar()
		try {
			oos.writeChar('T');
			oos.close();
			ois = new ObjectInputStream(new ByteArrayInputStream(bao
					.toByteArray()));
			assertTrue("Read incorrect char value", ois.readChar() == 'T');
			ois.close();
		} catch (IOException e) {
			fail("Exception serializing data : " + e.getMessage());
		}
	}

	/**
	 * @tests java.io.ObjectInputStream#readDouble()
	 */
	public void test_readDouble() {
		// Test for method double java.io.ObjectInputStream.readDouble()
		try {
			oos.writeDouble(Double.MAX_VALUE);
			oos.close();
			ois = new ObjectInputStream(new ByteArrayInputStream(bao
					.toByteArray()));
			assertTrue("Read incorrect double value",
					ois.readDouble() == Double.MAX_VALUE);
			ois.close();
		} catch (IOException e) {
			fail("Exception serializing data : " + e.getMessage());
		}
	}

	/**
	 * @tests java.io.ObjectInputStream#readFields()
	 */
	public void test_readFields() {
		// Test for method java.io.ObjectInputStream$GetField
		// java.io.ObjectInputStream.readFields()

		SerializableTestHelper sth;

		/*
		 * "SerializableTestHelper" is an object created for these tests with
		 * two fields (Strings) and simple implementations of readObject and
		 * writeObject which simply read and write the first field but not the
		 * second
		 */

		try {
			oos.writeObject(new SerializableTestHelper("Gabba", "Jabba"));
			oos.flush();
			ois = new ObjectInputStream(new ByteArrayInputStream(bao
					.toByteArray()));
			sth = (SerializableTestHelper) (ois.readObject());
			assertTrue("readFields / writeFields failed--first field not set",
					sth.getText1().equals("Gabba"));
			assertTrue(
					"readFields / writeFields failed--second field should not have been set",
					sth.getText2() == null);
		} catch (Exception e) {
			fail("Exception serializing data : " + e.getMessage());
		}
	}

	/**
	 * @tests java.io.ObjectInputStream#readFloat()
	 */
	public void test_readFloat() {
		// Test for method float java.io.ObjectInputStream.readFloat()
		try {
			oos.writeFloat(Float.MAX_VALUE);
			oos.close();
			ois = new ObjectInputStream(new ByteArrayInputStream(bao
					.toByteArray()));
			assertTrue("Read incorrect float value",
					ois.readFloat() == Float.MAX_VALUE);
			ois.close();
		} catch (IOException e) {
			fail("Exception serializing data : " + e.getMessage());
		}
	}

	/**
	 * @tests java.io.ObjectInputStream#readFully(byte[])
	 */
	public void test_readFully$B() {
		// Test for method void java.io.ObjectInputStream.readFully(byte [])
		try {
			byte[] buf = new byte[10];
			oos.writeBytes("HelloWorld");
			oos.close();
			ois = new ObjectInputStream(new ByteArrayInputStream(bao
					.toByteArray()));
			ois.readFully(buf);
			ois.close();
			assertTrue("Read incorrect bytes", new String(buf, 0, 10)
					.equals("HelloWorld"));
		} catch (IOException e) {
			fail("Exception serializing data : " + e.getMessage());
		}
	}

	/**
	 * @tests java.io.ObjectInputStream#readFully(byte[], int, int)
	 */
	public void test_readFully$BII() {
		// Test for method void java.io.ObjectInputStream.readFully(byte [],
		// int, int)
		try {
			byte[] buf = new byte[10];
			oos.writeBytes("HelloWorld");
			oos.close();
			ois = new ObjectInputStream(new ByteArrayInputStream(bao
					.toByteArray()));
			ois.readFully(buf, 0, 10);
			ois.close();
			assertTrue("Read incorrect bytes", new String(buf, 0, 10)
					.equals("HelloWorld"));
		} catch (IOException e) {
			fail("Exception serializing data : " + e.getMessage());
		}
	}

	/**
	 * @tests java.io.ObjectInputStream#readInt()
	 */
	public void test_readInt() {
		// Test for method int java.io.ObjectInputStream.readInt()
		try {
			oos.writeInt(Integer.MAX_VALUE);
			oos.close();
			ois = new ObjectInputStream(new ByteArrayInputStream(bao
					.toByteArray()));
			assertTrue("Read incorrect int value",
					ois.readInt() == Integer.MAX_VALUE);
			ois.close();
		} catch (IOException e) {
			fail("Exception serializing data : " + e.getMessage());
		}
	}

	/**
	 * @tests java.io.ObjectInputStream#readLine()
	 */
	public void test_readLine() {
		// Test for method java.lang.String java.io.ObjectInputStream.readLine()
		try {
			oos.writeBytes("HelloWorld\nSecondLine");
			oos.close();
			ois = new ObjectInputStream(new ByteArrayInputStream(bao
					.toByteArray()));
			ois.readLine();
			assertTrue("Read incorrect string value", ois.readLine().equals(
					"SecondLine"));
			ois.close();
		} catch (IOException e) {
			fail("Exception serializing data : " + e.getMessage());
		}
	}

	/**
	 * @tests java.io.ObjectInputStream#readLong()
	 */
	public void test_readLong() {
		// Test for method long java.io.ObjectInputStream.readLong()
		try {
			oos.writeLong(Long.MAX_VALUE);
			oos.close();
			ois = new ObjectInputStream(new ByteArrayInputStream(bao
					.toByteArray()));
			assertTrue("Read incorrect long value",
					ois.readLong() == Long.MAX_VALUE);
			ois.close();
		} catch (IOException e) {
			fail("Exception serializing data : " + e.getMessage());
		}
	}

	/**
	 * @tests java.io.ObjectInputStream#readObject()
	 */
	public void test_readObject() {
		// Test for method java.lang.Object
		// java.io.ObjectInputStream.readObject()
		try {
			String s = "HelloWorld";
			oos.writeObject(s);
			oos.close();
			ois = new ObjectInputStream(new ByteArrayInputStream(bao
					.toByteArray()));
			assertTrue("Read incorrect Object value", ((String) ois
					.readObject()).equals(s));
			ois.close();
		} catch (IOException e) {
			fail("Exception serializing data : " + e.getMessage());
		} catch (ClassNotFoundException e) {
			fail("Exception serializing data : " + e.getMessage());
		}
	}

	/**
	 * @tests java.io.ObjectInputStream#readObject()
	 */
	public void test_readObjectCorrupt() {
		byte[] bytes = { 00, 00, 00, 0x64, 0x43, 0x48, (byte) 0xFD, 0x71, 00,
				00, 0x0B, (byte) 0xB8, 0x4D, 0x65 };
		ByteArrayInputStream bin = new ByteArrayInputStream(bytes);
		boolean exception = false;
		try {
			ObjectInputStream in = new ObjectInputStream(bin);
			in.readObject();
			fail("Unexpected read of corrupted stream");
		} catch (StreamCorruptedException e) {
			exception = true;
		} catch (IOException e) {
			fail("Unexpected: " + e);
		} catch (ClassNotFoundException e) {
			fail("Unexpected: " + e);
		}
		assertTrue("Expected StreamCorruptedException", exception);
	}

	/**
	 * @tests java.io.ObjectInputStream#readShort()
	 */
	public void test_readShort() {
		// Test for method short java.io.ObjectInputStream.readShort()
		try {
			oos.writeShort(Short.MAX_VALUE);
			oos.close();
			ois = new ObjectInputStream(new ByteArrayInputStream(bao
					.toByteArray()));
			assertTrue("Read incorrect short value",
					ois.readShort() == Short.MAX_VALUE);
			ois.close();
		} catch (IOException e) {
			fail("Exception serializing data : " + e.getMessage());
		}
	}

	/**
	 * @tests java.io.ObjectInputStream#readUnsignedByte()
	 */
	public void test_readUnsignedByte() {
		// Test for method int java.io.ObjectInputStream.readUnsignedByte()
		try {
			oos.writeByte(-1);
			oos.close();
			ois = new ObjectInputStream(new ByteArrayInputStream(bao
					.toByteArray()));
			assertTrue("Read incorrect unsignedByte value", ois
					.readUnsignedByte() == 255);
			ois.close();
		} catch (IOException e) {
			fail("Exception serializing data : " + e.getMessage());
		}
	}

	/**
	 * @tests java.io.ObjectInputStream#readUnsignedShort()
	 */
	public void test_readUnsignedShort() {
		// Test for method int java.io.ObjectInputStream.readUnsignedShort()
		try {
			oos.writeShort(-1);
			oos.close();
			ois = new ObjectInputStream(new ByteArrayInputStream(bao
					.toByteArray()));
			assertTrue("Read incorrect unsignedShort value", ois
					.readUnsignedShort() == 65535);
			ois.close();
		} catch (IOException e) {
			fail("Exception serializing data : " + e.getMessage());
		}
	}

	/**
	 * @tests java.io.ObjectInputStream#readUTF()
	 */
	public void test_readUTF() {
		// Test for method java.lang.String java.io.ObjectInputStream.readUTF()
		try {
			oos.writeUTF("HelloWorld");
			oos.close();
			ois = new ObjectInputStream(new ByteArrayInputStream(bao
					.toByteArray()));
			assertTrue("Read incorrect utf value", ois.readUTF().equals(
					"HelloWorld"));
			ois.close();
		} catch (IOException e) {
			fail("Exception serializing data : " + e.getMessage());
		}
	}

	/**
	 * @tests java.io.ObjectInputStream#skipBytes(int)
	 */
	public void test_skipBytesI() {
		// Test for method int java.io.ObjectInputStream.skipBytes(int)
		try {
			byte[] buf = new byte[10];
			oos.writeBytes("HelloWorld");
			oos.close();
			ois = new ObjectInputStream(new ByteArrayInputStream(bao
					.toByteArray()));
			ois.skipBytes(5);
			ois.read(buf, 0, 5);
			ois.close();
			assertTrue("Skipped incorrect bytes", new String(buf, 0, 5)
					.equals("World"));
		} catch (IOException e) {
			fail("Exception serializing data : " + e.getMessage());
		}
	}

	/**
	 * Sets up the fixture, for example, open a network connection. This method
	 * is called before a test is executed.
	 */
	protected void setUp() {
		try {
			oos = new ObjectOutputStream(bao = new ByteArrayOutputStream());
		} catch (Exception e) {
			fail("Setup failed : " + e.getMessage());
		}
	}

	/**
	 * Tears down the fixture, for example, close a network connection. This
	 * method is called after a test is executed.
	 */
	protected void tearDown() {
	}
}
