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
import java.io.Externalizable;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.NotActiveException;
import java.io.NotSerializableException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.ObjectStreamException;
import java.io.ObjectStreamField;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.Arrays;

public class ObjectOutputStreamTest extends junit.framework.TestCase implements
		Serializable {

	java.io.File f;

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

	private static class SerializationTest implements java.io.Serializable {
		int anInt = INIT_INT_VALUE;

		public SerializationTest() {
			super();
		}
	}

	private static class SerializationTestSubclass1 extends SerializationTest
			implements Serializable {
		String aString = INIT_STR_VALUE;

		public SerializationTestSubclass1() {
			super();
			// Just to change default superclass init value
			anInt = INIT_INT_VALUE / 2;
		}
	}

	private static class SpecTestSuperClass implements Runnable, Serializable {
		protected java.lang.String instVar;

		public void run() {
		}
	}

	private static class SpecTest extends SpecTestSuperClass implements
			Cloneable, Serializable {
		public java.lang.String instVar1;

		public static java.lang.String staticVar1;

		public static java.lang.String staticVar2;
		{
			instVar1 = "NonStaticInitialValue";
		}
		static {
			staticVar1 = "StaticInitialValue";
			staticVar1 = new String(staticVar1);
		}

		public Object method(Object objParam, Object objParam2) {
			return new Object();
		}

		public boolean method(boolean bParam, Object objParam) {
			return true;
		}

		public boolean method(boolean bParam, Object objParam, Object objParam2) {
			return true;
		}

	}

	private static class SpecTestSubclass extends SpecTest implements
			Serializable {
		public transient java.lang.String transientInstVar = "transientValue";
	}

	private static class ReadWriteObject implements java.io.Serializable {
		public boolean calledWriteObject = false;

		public boolean calledReadObject = false;

		public ReadWriteObject() {
			super();
		}

		private void readObject(java.io.ObjectInputStream in)
				throws java.io.IOException, ClassNotFoundException {
			calledReadObject = true;
			in.readObject();
		}

		private void writeObject(java.io.ObjectOutputStream out)
				throws java.io.IOException {
			calledWriteObject = true;
			out.writeObject(FOO);
		}
	}

	private static class PublicReadWriteObject implements java.io.Serializable {
		public boolean calledWriteObject = false;

		public boolean calledReadObject = false;

		public PublicReadWriteObject() {
			super();
		}

		public void readObject(java.io.ObjectInputStream in)
				throws java.io.IOException, ClassNotFoundException {
			calledReadObject = true;
			in.readObject();
		}

		public void writeObject(java.io.ObjectOutputStream out)
				throws java.io.IOException {
			calledWriteObject = true;
			out.writeObject(FOO);
		}
	}

	private static class FieldOrder implements Serializable {
		String aaa1NonPrimitive = "aaa1";

		int bbb1PrimitiveInt = 5;

		boolean aaa2PrimitiveBoolean = true;

		String bbb2NonPrimitive = "bbb2";
	}

	private static class JustReadObject implements java.io.Serializable {
		public boolean calledReadObject = false;

		public JustReadObject() {
			super();
		}

		private void readObject(java.io.ObjectInputStream in)
				throws java.io.IOException, ClassNotFoundException {
			calledReadObject = true;
			in.defaultReadObject();
		}
	}

	private static class JustWriteObject implements java.io.Serializable {
		public boolean calledWriteObject = false;

		public JustWriteObject() {
			super();
		}

		private void writeObject(java.io.ObjectOutputStream out)
				throws java.io.IOException, ClassNotFoundException {
			calledWriteObject = true;
			out.defaultWriteObject();
		}
	}

	private static class ClassBasedReplacementWhenDumping implements
			java.io.Serializable {
		public boolean calledReplacement = false;

		public ClassBasedReplacementWhenDumping() {
			super();
		}

		private Object writeReplace() {
			calledReplacement = true;
			return FOO; // Replacement is a String
		}
	}

	private static class MultipleClassBasedReplacementWhenDumping implements
			java.io.Serializable {
		private static class C1 implements java.io.Serializable {
			private Object writeReplace() {
				return new C2();
			}
		}

		private static class C2 implements java.io.Serializable {
			private Object writeReplace() {
				return new C3();
			}
		}

		private static class C3 implements java.io.Serializable {
			private Object writeReplace() {
				return FOO;
			}
		}

		public MultipleClassBasedReplacementWhenDumping() {
			super();
		}

		private Object writeReplace() {
			return new C1();
		}
	}

	private static class ClassBasedReplacementWhenLoading implements
			java.io.Serializable {
		public ClassBasedReplacementWhenLoading() {
			super();
		}

		private Object readResolve() {
			return FOO; // Replacement is a String
		}
	}

	private static class ClassBasedReplacementWhenLoadingViolatesFieldType
			implements java.io.Serializable {
		public ClassBasedReplacementWhenLoading classBasedReplacementWhenLoading = new ClassBasedReplacementWhenLoading();

		public ClassBasedReplacementWhenLoadingViolatesFieldType() {
			super();
		}
	}

	private static class MyExceptionWhenDumping implements java.io.Serializable {
		private static class MyException extends java.io.IOException {
		};

		public boolean anInstanceVar = false;

		public MyExceptionWhenDumping() {
			super();
		}

		private void readObject(java.io.ObjectInputStream in)
				throws java.io.IOException, ClassNotFoundException {
			in.defaultReadObject();
		}

		private void writeObject(java.io.ObjectOutputStream out)
				throws java.io.IOException, ClassNotFoundException {
			throw new MyException();
		}
	}

	private static class NonSerializableExceptionWhenDumping implements
			java.io.Serializable {
		public Object anInstanceVar = new Object();

		public NonSerializableExceptionWhenDumping() {
			super();
		}
	}

	private static class MyUnserializableExceptionWhenDumping implements
			java.io.Serializable {
		private static class MyException extends java.io.IOException {
			private Object notSerializable = new Object();
		};

		public boolean anInstanceVar = false;

		public MyUnserializableExceptionWhenDumping() {
			super();
		}

		private void readObject(java.io.ObjectInputStream in)
				throws java.io.IOException, ClassNotFoundException {
			in.defaultReadObject();
		}

		private void writeObject(java.io.ObjectOutputStream out)
				throws java.io.IOException, ClassNotFoundException {
			throw new MyException();
		}
	}

	private static class WithUnmatchingSerialPersistentFields implements
			java.io.Serializable {
		private static final ObjectStreamField[] serialPersistentFields = { new ObjectStreamField(
				"value", String.class) };

		public int anInstanceVar = 5;

		public WithUnmatchingSerialPersistentFields() {
			super();
		}
	}

	private static class WithMatchingSerialPersistentFields implements
			java.io.Serializable {
		private static final ObjectStreamField[] serialPersistentFields = { new ObjectStreamField(
				"anInstanceVar", String.class) };

		public String anInstanceVar = FOO + FOO;

		public WithMatchingSerialPersistentFields() {
			super();
		}
	}

	private static class SerialPersistentFields implements java.io.Serializable {
		private static final String SIMULATED_FIELD_NAME = "text";

		private static final ObjectStreamField[] serialPersistentFields = { new ObjectStreamField(
				SIMULATED_FIELD_NAME, String.class) };

		public int anInstanceVar = 5;

		public SerialPersistentFields() {
			super();
		}

		private void readObject(java.io.ObjectInputStream in)
				throws java.io.IOException, ClassNotFoundException {
			ObjectInputStream.GetField fields = in.readFields();
			anInstanceVar = Integer.parseInt((String) fields.get(
					SIMULATED_FIELD_NAME, "-5"));
		}

		private void writeObject(java.io.ObjectOutputStream out)
				throws java.io.IOException, ClassNotFoundException {
			ObjectOutputStream.PutField fields = out.putFields();
			fields.put(SIMULATED_FIELD_NAME, Integer.toString(anInstanceVar));
			out.writeFields();
		}
	}

	private static class WriteFieldsWithoutFetchingPutFields implements
			java.io.Serializable {
		private static final String SIMULATED_FIELD_NAME = "text";

		private static final ObjectStreamField[] serialPersistentFields = { new ObjectStreamField(
				SIMULATED_FIELD_NAME, String.class) };

		public int anInstanceVar = 5;

		public WriteFieldsWithoutFetchingPutFields() {
			super();
		}

		private void readObject(java.io.ObjectInputStream in)
				throws java.io.IOException, ClassNotFoundException {
			in.readFields();
		}

		private void writeObject(java.io.ObjectOutputStream out)
				throws java.io.IOException, ClassNotFoundException {
			out.writeFields();
		}
	}

	private static class SerialPersistentFieldsWithoutField implements
			java.io.Serializable {
		public int anInstanceVar = 5;

		public SerialPersistentFieldsWithoutField() {
			super();
		}

		private void readObject(java.io.ObjectInputStream in)
				throws java.io.IOException, ClassNotFoundException {
			in.readFields();
		}

		private void writeObject(java.io.ObjectOutputStream out)
				throws java.io.IOException, ClassNotFoundException {
			out.putFields();
			out.writeFields();
		}
	}

	private static class NotSerializable {
		private int foo;

		public NotSerializable() {
		}

		protected Object writeReplace() throws ObjectStreamException {
			return new Integer(42);
		}
	}

	private static class ExternalizableWithReplace implements Externalizable {
		private int foo;

		public ExternalizableWithReplace() {
		}

		protected Object writeReplace() throws ObjectStreamException {
			return new Integer(42);
		}

		public void writeExternal(ObjectOutput out) {
		}

		public void readExternal(ObjectInput in) {
		}
	}

	protected static final String MODE_XLOAD = "xload";

	protected static final String MODE_XDUMP = "xdump";

	static final String FOO = "foo";

	static final String MSG_WITE_FAILED = "Failed to write: ";

	private static final boolean DEBUG = false;

	protected static boolean xload = false;

	protected static boolean xdump = false;

	protected static String xFileName = null;

	protected ObjectInputStream ois;

	protected ObjectOutputStream oos;

	protected ByteArrayOutputStream bao;

	static final int INIT_INT_VALUE = 7;

	static final String INIT_STR_VALUE = "a string that is blortz";

	/**
	 * @tests java.io.ObjectOutputStream#ObjectOutputStream(java.io.OutputStream)
	 */
	public void test_ConstructorLjava_io_OutputStream() {
		// Test for method java.io.ObjectOutputStream(java.io.OutputStream)

		try {
			oos.close();
			oos = new ObjectOutputStream(new ByteArrayOutputStream());
			oos.close();
		} catch (Exception e) {
			fail("Failed to create ObjectOutputStream : " + e.getMessage());
		}
	}

	/**
	 * @tests java.io.ObjectOutputStream#ObjectOutputStream(java.io.OutputStream)
	 */
	public void test_ConstructorLjava_io_OutputStream_subtest0() {
		System.setSecurityManager(new SecurityManager());
		try {
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			// should not cause SecurityException
			new ObjectOutputStream(out);
			// should not cause SecurityException
			class SubTest1 extends ObjectOutputStream {
				SubTest1(OutputStream out) throws IOException {
					super(out);
				}
			}
			;
			// should not cause SecurityException
			new SubTest1(out);
			class SubTest2 extends ObjectOutputStream {
				SubTest2(OutputStream out) throws IOException {
					super(out);
				}

				public void writeUnshared(Object obj) throws IOException {
				}
			}
			;
			try {
				new SubTest2(out);
				fail("should throw SecurityException 1");
			} catch (SecurityException e) {
			}
			class SubTest3 extends ObjectOutputStream {
				SubTest3(OutputStream out) throws IOException {
					super(out);
				}

				public PutField putFields() throws IOException {
					return null;
				}
			}
			;
			try {
				new SubTest3(out);
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
	 * @tests java.io.ObjectOutputStream#close()
	 */
	public void test_close() {
		// Test for method void java.io.ObjectOutputStream.close()
	}

	/**
	 * @tests java.io.ObjectOutputStream#defaultWriteObject()
	 */
	public void test_defaultWriteObject() {
		// Test for method void java.io.ObjectOutputStream.defaultWriteObject()

		try {
			oos.defaultWriteObject();
		} catch (NotActiveException e) {
			// Correct
			return;
		} catch (IOException e) {
		}
		fail(
				"Failed to throw NotActiveException when invoked outside readObject");
	}

	/**
	 * @tests java.io.ObjectOutputStream#flush()
	 */
	public void test_flush() {
		// Test for method void java.io.ObjectOutputStream.flush()
		try {
			int size = bao.size();
			oos.writeByte(127);
			assertTrue("Data flushed already", bao.size() == size);
			oos.flush();
			assertTrue("Failed to flush data", bao.size() > size);
			// we don't know how many bytes are actually written for 1
			// byte, so we test > <before>
			oos.close();
			oos = null;
		} catch (IOException e) {
			fail("Exception serializing data : " + e.getMessage());
		}
	}

	/**
	 * @tests java.io.ObjectOutputStream#putFields()
	 */
	public void test_putFields() {
		// Test for method java.io.ObjectOutputStream$PutField
		// java.io.ObjectOutputStream.putFields()

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
			assertEquals("readFields / writeFields failed--first field not set",
					"Gabba", sth.getText1());
			assertNull(
					"readFields / writeFields failed--second field should not have been set",
					sth.getText2());
		} catch (Exception e) {
			fail("Exception thrown : " + e.getMessage());
		}
	}

	/**
	 * @tests java.io.ObjectOutputStream#reset()
	 */
	public void test_reset() {
		// Test for method void java.io.ObjectOutputStream.reset()
		try {
			String o = "HelloWorld";
			oos.writeObject(o);
			oos.writeObject(o);
			oos.reset();
			oos.writeObject(o);
			ois = new ObjectInputStream(new ByteArrayInputStream(bao
					.toByteArray()));
			ois.close();
		} catch (IOException e) {
			fail("Exception serializing data : " + e.getMessage());
		}
	}

	private static class ExternalTest implements Externalizable {
		public String value;

		public ExternalTest() {
		}

		public void setValue(String val) {
			value = val;
		}

		public String getValue() {
			return value;
		}

		public void writeExternal(ObjectOutput output) {
			try {
				output.writeUTF(value);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		public void readExternal(ObjectInput input) {
			try {
				value = input.readUTF();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * @tests java.io.ObjectOutputStream#useProtocolVersion(int)
	 */
	public void test_useProtocolVersionI() {
		// Test for method void
		// java.io.ObjectOutputStream.useProtocolVersion(int)
		try {
			oos.useProtocolVersion(ObjectOutputStream.PROTOCOL_VERSION_1);
			ExternalTest t1 = new ExternalTest();
			t1.setValue("hello1");
			oos.writeObject(t1);
			oos.close();
			ois = new ObjectInputStream(new ByteArrayInputStream(bao
					.toByteArray()));
			ExternalTest t2 = (ExternalTest) ois.readObject();
			ois.close();
			assertTrue(
					"Cannot read/write PROTOCAL_VERSION_1 Externalizable objects: "
							+ t2.getValue(), t1.getValue()
							.equals(t2.getValue()));
		} catch (IOException e) {
			fail("Unexpected: " + e);
		} catch (ClassNotFoundException e) {
			fail("Unexpected: " + e);
		}
	}

	/**
	 * @tests java.io.ObjectOutputStream#write(byte[])
	 */
	public void test_write$B() {
		// Test for method void java.io.ObjectOutputStream.write(byte [])
		try {
			byte[] buf = new byte[10];
			oos.write("HelloWorld".getBytes());
			oos.close();
			ois = new ObjectInputStream(new ByteArrayInputStream(bao
					.toByteArray()));
			ois.read(buf, 0, 10);
			ois.close();
			assertEquals("Read incorrect bytes", "HelloWorld", new String(buf, 0, 10)
					);
		} catch (IOException e) {
			fail("Exception serializing data : " + e.getMessage());
		}
	}

	/**
	 * @tests java.io.ObjectOutputStream#write(byte[], int, int)
	 */
	public void test_write$BII() {
		// Test for method void java.io.ObjectOutputStream.write(byte [], int,
		// int)
		try {
			byte[] buf = new byte[10];
			oos.write("HelloWorld".getBytes(), 0, 10);
			oos.close();
			ois = new ObjectInputStream(new ByteArrayInputStream(bao
					.toByteArray()));
			ois.read(buf, 0, 10);
			ois.close();
			assertEquals("Read incorrect bytes", "HelloWorld", new String(buf, 0, 10)
					);
		} catch (IOException e) {
			fail("Exception serializing data : " + e.getMessage());
		}
	}

	/**
	 * @tests java.io.ObjectOutputStream#write(int)
	 */
	public void test_writeI() {
		// Test for method void java.io.ObjectOutputStream.write(int)
		try {
			oos.write('T');
			oos.close();
			ois = new ObjectInputStream(new ByteArrayInputStream(bao
					.toByteArray()));
			assertEquals("Read incorrect byte", 'T', ois.read());
			ois.close();
		} catch (IOException e) {
			fail("Exception serializing data : " + e.getMessage());
		}
	}

	/**
	 * @tests java.io.ObjectOutputStream#writeBoolean(boolean)
	 */
	public void test_writeBooleanZ() {
		// Test for method void java.io.ObjectOutputStream.writeBoolean(boolean)
		try {
			oos.writeBoolean(true);
			oos.close();
			ois = new ObjectInputStream(new ByteArrayInputStream(bao
					.toByteArray()));
			assertTrue("Wrote incorrect byte value", ois.readBoolean());
		} catch (IOException e) {
			fail("Exception serializing data : " + e.getMessage());
		}
	}

	/**
	 * @tests java.io.ObjectOutputStream#writeByte(int)
	 */
	public void test_writeByteI() {
		// Test for method void java.io.ObjectOutputStream.writeByte(int)
		try {
			oos.writeByte(127);
			oos.close();
			ois = new ObjectInputStream(new ByteArrayInputStream(bao
					.toByteArray()));
			assertEquals("Wrote incorrect byte value", 127, ois.readByte());
		} catch (IOException e) {
			fail("Exception serializing data : " + e.getMessage());
		}
	}

	/**
	 * @tests java.io.ObjectOutputStream#writeBytes(java.lang.String)
	 */
	public void test_writeBytesLjava_lang_String() {
		// Test for method void
		// java.io.ObjectOutputStream.writeBytes(java.lang.String)
		try {
			byte[] buf = new byte[10];
			oos.writeBytes("HelloWorld");
			oos.close();
			ois = new ObjectInputStream(new ByteArrayInputStream(bao
					.toByteArray()));
			ois.readFully(buf);
			ois.close();
			assertEquals("Wrote incorrect bytes value", "HelloWorld", new String(buf, 0, 10)
					);
		} catch (IOException e) {
			fail("Exception serializing data : " + e.getMessage());
		}
	}

	/**
	 * @tests java.io.ObjectOutputStream#writeChar(int)
	 */
	public void test_writeCharI() {
		// Test for method void java.io.ObjectOutputStream.writeChar(int)
		try {
			oos.writeChar('T');
			oos.close();
			ois = new ObjectInputStream(new ByteArrayInputStream(bao
					.toByteArray()));
			assertEquals("Wrote incorrect char value", 'T', ois.readChar());
		} catch (IOException e) {
			fail("Exception serializing data : " + e.getMessage());
		}
	}

	/**
	 * @tests java.io.ObjectOutputStream#writeChars(java.lang.String)
	 */
	public void test_writeCharsLjava_lang_String() {
		// Test for method void
		// java.io.ObjectOutputStream.writeChars(java.lang.String)
		try {
			int avail = 0;
			char[] buf = new char[10];
			oos.writeChars("HelloWorld");
			oos.close();
			ois = new ObjectInputStream(new ByteArrayInputStream(bao
					.toByteArray()));
			// Number of prim data bytes in stream / 2 to give char index
			avail = ois.available() / 2;
			for (int i = 0; i < avail; ++i)
				buf[i] = ois.readChar();
			ois.close();
			assertEquals("Wrote incorrect chars", "HelloWorld", new String(buf, 0, 10)
					);
		} catch (IOException e) {
			fail("Exception serializing data : " + e.getMessage());
		}
	}

	/**
	 * @tests java.io.ObjectOutputStream#writeDouble(double)
	 */
	public void test_writeDoubleD() {
		// Test for method void java.io.ObjectOutputStream.writeDouble(double)
		try {
			oos.writeDouble(Double.MAX_VALUE);
			oos.close();
			ois = new ObjectInputStream(new ByteArrayInputStream(bao
					.toByteArray()));
			assertTrue("Wrote incorrect double value",
					ois.readDouble() == Double.MAX_VALUE);
		} catch (IOException e) {
			fail("Exception serializing data : " + e.getMessage());
		}
	}

	/**
	 * @tests java.io.ObjectOutputStream#writeFields()
	 */
	public void test_writeFields() {
		// Test for method void java.io.ObjectOutputStream.writeFields()
		assertTrue("Used to test", true);
	}

	/**
	 * @tests java.io.ObjectOutputStream#writeFloat(float)
	 */
	public void test_writeFloatF() {
		// Test for method void java.io.ObjectOutputStream.writeFloat(float)
		try {
			oos.writeFloat(Float.MAX_VALUE);
			oos.close();
			ois = new ObjectInputStream(new ByteArrayInputStream(bao
					.toByteArray()));
			assertTrue("Wrote incorrect double value",
					ois.readFloat() == Float.MAX_VALUE);
			ois.close();
			ois = null;
		} catch (IOException e) {
			fail("Exception serializing data : " + e.getMessage());
		}
	}

	/**
	 * @tests java.io.ObjectOutputStream#writeInt(int)
	 */
	public void test_writeIntI() {
		// Test for method void java.io.ObjectOutputStream.writeInt(int)
		try {
			oos.writeInt(Integer.MAX_VALUE);
			oos.close();
			ois = new ObjectInputStream(new ByteArrayInputStream(bao
					.toByteArray()));
			assertTrue("Wrote incorrect double value",
					ois.readInt() == Integer.MAX_VALUE);
			ois.close();
		} catch (IOException e) {
			fail("Exception serializing data : " + e.getMessage());
		}
	}

	/**
	 * @tests java.io.ObjectOutputStream#writeLong(long)
	 */
	public void test_writeLongJ() {
		// Test for method void java.io.ObjectOutputStream.writeLong(long)
		try {
			oos.writeLong(Long.MAX_VALUE);
			oos.close();
			ois = new ObjectInputStream(new ByteArrayInputStream(bao
					.toByteArray()));
			assertTrue("Wrote incorrect double value",
					ois.readLong() == Long.MAX_VALUE);
		} catch (IOException e) {
			fail("Exception serializing data : " + e.getMessage());
		}
	}

	/**
	 * @tests java.io.ObjectOutputStream#writeObject(java.lang.Object)
	 */
	public void test_writeObjectLjava_lang_Object() {
		// Test for method void
		// java.io.ObjectOutputStream.writeObject(java.lang.Object)

		Object objToSave = null;
		Object objLoaded;

		try {
			SerialPersistentFieldsWithoutField spf = new SerialPersistentFieldsWithoutField();
			final int CONST = -500;
			spf.anInstanceVar = CONST;
			objToSave = spf;
			if (DEBUG)
				System.out.println("Obj = " + objToSave);
			objLoaded = dumpAndReload(objToSave);
			assertTrue(
					"serialPersistentFields do not work properly in this implementation",
					((SerialPersistentFieldsWithoutField) objLoaded).anInstanceVar != CONST);

		} catch (IOException e) {
			fail("Exception serializing " + objToSave + "\t->"
					+ e.getMessage());
		} catch (ClassNotFoundException e) {
			fail("Unable to read Object type : " + e.getMessage());
		} catch (Error err) {
			System.out.println("Error when obj = " + objToSave);
			err.printStackTrace();
			throw err;
		}
	}

	/**
	 * @tests java.io.ObjectOutputStream#writeObject(java.lang.Object)
	 */
	public void test_writeObject_NotSerializable() {
		ObjectOutput out = null;
		try {
			out = new ObjectOutputStream(new ByteArrayOutputStream());
			out.writeObject(new NotSerializable());
			fail("Expected NotSerializableException");
		} catch (NotSerializableException e) {
		} catch (IOException e) {
			fail("Unexpected1: " + e);
		}
		try {
			out.writeObject(new ExternalizableWithReplace());
		} catch (IOException e) {
			fail("Unexpected2: " + e);
		}
	}

	/**
	 * @tests java.io.ObjectOutputStream#writeShort(int)
	 */
	public void test_writeShortI() {
		// Test for method void java.io.ObjectOutputStream.writeShort(int)
		try {
			oos.writeShort(127);
			oos.close();
			ois = new ObjectInputStream(new ByteArrayInputStream(bao
					.toByteArray()));
			assertEquals("Wrote incorrect short value", 127, ois.readShort());
		} catch (IOException e) {
			fail("Exception serializing data : " + e.getMessage());
		}
	}

	/**
	 * @tests java.io.ObjectOutputStream#writeUTF(java.lang.String)
	 */
	public void test_writeUTFLjava_lang_String() {
		// Test for method void
		// java.io.ObjectOutputStream.writeUTF(java.lang.String)
		try {
			oos.writeUTF("HelloWorld");
			oos.close();
			ois = new ObjectInputStream(new ByteArrayInputStream(bao
					.toByteArray()));
			assertEquals("Wrote incorrect UTF value", 
					"HelloWorld", ois.readUTF());
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
		if (oos != null)
			try {
				oos.close();
			} catch (Exception e) {
			}
		if (f != null && f.exists())
			if (!f.delete()) {
				fail("Error cleaning up files during teardown");
			}
	}

	protected Object reload() throws IOException, ClassNotFoundException {

		// Choose the load stream
		if (xload || xdump) {
			// Load from pre-existing file
			ois = new ObjectInputStream(new FileInputStream(xFileName + "-"
					+ getName() + ".ser"));
		} else {
			// Just load from memory, we dumped to memory
			ois = new ObjectInputStream(new ByteArrayInputStream(bao
					.toByteArray()));
		}

		try {
			return ois.readObject();
		} finally {
			ois.close();
		}
	}

	protected void dump(Object o) throws IOException, ClassNotFoundException {

		// Choose the dump stream
		if (xdump) {
			oos = new ObjectOutputStream(new FileOutputStream(
					f = new java.io.File(xFileName + "-" + getName() + ".ser")));
		} else {
			oos = new ObjectOutputStream(bao = new ByteArrayOutputStream());
		}

		// Dump the object
		try {
			oos.writeObject(o);
		} finally {
			oos.close();
		}
	}

	/**
	 * @tests java.io.ObjectOutputStream#writeInt(int)
	 * @tests java.io.ObjectOutputStream#writeObject(java.lang.Object)
	 * @tests java.io.ObjectOutputStream#writeUTF(java.lang.String)
	 */

	public void testMixPrimitivesAndObjects() {
		int i = 7;
		String s1 = "string 1";
		String s2 = "string 2";
		byte[] bytes = { 1, 2, 3 };
		try {
			oos = new ObjectOutputStream(bao = new ByteArrayOutputStream());
			oos.writeInt(i);
			oos.writeObject(s1);
			oos.writeUTF(s2);
			oos.writeObject(bytes);
			oos.close();

			ois = new ObjectInputStream(new ByteArrayInputStream(bao
					.toByteArray()));

			int j = ois.readInt();
			assertTrue("Wrong int :" + j, i == j);

			String l1 = (String) ois.readObject();
			assertTrue("Wrong obj String :" + l1, s1.equals(l1));

			String l2 = (String) ois.readUTF();
			assertTrue("Wrong UTF String :" + l2, s2.equals(l2));

			byte[] bytes2 = (byte[]) ois.readObject();
			assertTrue("Wrong byte[]", Arrays.equals(bytes, bytes2));
		} catch (IOException e) {
			fail("Unexpected IOException: " + e.toString());
		} catch (ClassNotFoundException e) {
			fail("Unexpected ClassNotFoundException: " + e.toString());
		} finally {
			try {
				if (oos != null)
					oos.close();
				if (ois != null)
					ois.close();
			} catch (IOException e) {
			}
		}
	}

	protected Object dumpAndReload(Object o) throws IOException,
			ClassNotFoundException {
		dump(o);
		return reload();
	}
}
