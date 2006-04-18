/* Copyright 2005 The Apache Software Foundation or its licensors, as applicable
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

package tests.api.java.beans;

import java.beans.Expression;
import java.beans.PersistenceDelegate;
import java.beans.Statement;
import java.beans.XMLEncoder;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import junit.framework.TestCase;
import tests.api.java.beans.EncoderTest.SampleBean;
import tests.api.java.beans.mock.MockBean4Codec;

/**
 * Tests for XMLEncoder
 */
public class XMLEncoderTest extends TestCase {

	public static void main(String[] args) {

		// VerboseEncoder enc = new VerboseEncoder();
		//
		// MockBean4Codec b = new MockBean4Codec();
		// b.getBornFriend().getZarr()[0] = 888;
		// b.setNill(b.getBornFriend());
		//
		// enc.writeObject(b);
		// enc.flush();

		junit.textui.TestRunner.run(XMLEncoderTest.class);
	}

	public static class DependencyBean {
		private int ints[] = new int[] { 1 };

		private Object ref;

		public int[] getInts() {
			return ints;
		}

		public void setInts(int[] ints) {
			this.ints = ints;
		}

		public Object getRef() {
			return ref;
		}

		public void setRef(Object ref) {
			this.ref = ref;
		}
	}

	public static class VerboseEncoder extends XMLEncoder {

		private PrintWriter out;

		private boolean ident;

		public VerboseEncoder() {
			this(new PrintWriter(System.out, true), true);
		}

		public VerboseEncoder(PrintWriter out, boolean ident) {
			super(System.out);
			this.out = out;
			this.ident = ident;
		}

		public Object get(Object arg0) {
			String identStr = ident ? ident() : "";
			out.println(identStr + "get()> " + arg0);
			Object result = super.get(arg0);
			out.println(identStr + "get()< " + result);
			return result;
		}

		public PersistenceDelegate getPersistenceDelegate(Class type) {
			PersistenceDelegate result = super.getPersistenceDelegate(type);
			return result;
		}

		public Object remove(Object arg0) {
			String identStr = ident ? ident() : "";
			out.println(identStr + "remove()> " + arg0);
			Object result = super.remove(arg0);
			out.println(identStr + "remove()< " + result);
			return result;
		}

		public void writeExpression(Expression arg0) {
			String identStr = ident ? ident() : "";
			out.println(identStr + "writeExpression()> " + string(arg0));
			super.writeExpression(arg0);
			out.println(identStr + "writeExpression()< ");
		}

		public void writeStatement(Statement arg0) {
			String identStr = ident ? ident() : "";
			out.println(identStr + "writeStatement()> " + string(arg0));
			super.writeStatement(arg0);
			out.println(identStr + "writeStatement()< ");
		}

		public void writeObject(Object arg0) {
			String identStr = ident ? ident() : "";
			out.println(identStr + "writeObject()> " + arg0);
			super.writeObject(arg0);
			out.println(identStr + "writeObject()< ");
		}
	}

	public static String ident() {
		Exception ex = new Exception();
		int level = ex.getStackTrace().length;
		StringBuffer buf = new StringBuffer();
		for (int i = 0; i < level; i++) {
			buf.append("  ");
		}
		return buf.toString();
	}

	public static String string(Statement stat) {
		String str = "(" + stat.getTarget() + ")." + stat.getMethodName() + "(";
		Object args[] = stat.getArguments();
		for (int i = 0; i < args.length; i++) {
			if (i > 0) {
				str += ", ";
			}
			str += args[i];
		}
		str = str + ")";
		return str;
	}

	public static String string(Expression exp) {
		String str = "";
		try {
			str += str + exp.getValue();
		} catch (Exception e) {
			e.printStackTrace();
		}
		str += "=" + string((Statement) exp);
		return str;
	}

	public void testWriteExpression() {
		// covered by testWriteObject
	}

	public void testWriteStatement() {
		// coverd by testWriteStatement
	}

	public void testWriteObject_Null() throws IOException {
		assertCodedXML(null, "null.xml");
	}

	public void testWriteObject_Integer() throws IOException {
		assertCodedXML(new Integer(3), "int.xml");
	}

	public void testWriteObject_StringCodec() throws IOException {
		SampleBean b = new SampleBean();
		b.setMyid("<Li Yang> & \"liyang'");
		SampleBean c = new SampleBean();
		c.setMyid("a child");
		b.setRef(c);
		assertCodedXML(b, "SampleBean_StringCodec.xml");
	}

	public void testWriteObject_IntArray() throws IOException {
		assertCodedXML(new int[] { 1, 2, 3 }, "IntArray.xml");
	}

	public void testWriteObject_PropertyDependency() throws IOException {
		DependencyBean b = new DependencyBean();
		b.getInts()[0] = 888;
		b.setRef(b.getInts());
		assertCodedXML(b, "DependencyBean.xml");
	}

	public void testWriteObject_NoChange() throws IOException {
		assertCodedXML(new MockBean4Codec(), "MockBean4Codec_NoChange.xml");
	}

	public void testWriteObject_BornFriendChange() throws IOException {
		MockBean4Codec b = new MockBean4Codec();
		b.getBornFriend().getZarr()[0] = 888;
		b.setNill(b.getBornFriend());

		assertCodedXML(b, "MockBean4Codec_BornFriendChange.xml");
	}

	public void testWriteObject_ManyChanges() throws IOException {
		assertCodedXML(MockBean4Codec.getInstanceOfManyChanges(),
				"MockBean4Codec_ManyChanges.xml");
	}

	public void testWriteObject_ManyChanges_2() throws IOException {
		assertCodedXML(MockBean4Codec.getInstanceOfManyChanges2(),
				"MockBean4Codec_ManyChanges_2.xml");
	}

	public void testWriteObject_SetOwner() throws IOException {
		ByteArrayOutputStream temp = new ByteArrayOutputStream();
		XMLEncoder enc = new XMLEncoder(temp);

		MockBean4Owner_Target t = new MockBean4Owner_Target();
		MockBean4Owner_Owner o = new MockBean4Owner_Owner();
		t.setV(o);
		enc.setOwner(o);

		assertCodedXML(t, "MockBean4Owner_SetOwner.xml", temp, enc);

	}

	public void testWriteObject_SetOwnerWithWriteStatement() throws IOException {
		ByteArrayOutputStream temp = new ByteArrayOutputStream();
		XMLEncoder enc = new XMLEncoder(temp);

		MockBean4Owner_Target t = new MockBean4Owner_Target();
		MockBean4Owner_Owner o = new MockBean4Owner_Owner();
		t.setV(o);
		enc.setOwner(o);

		enc.writeStatement(new Statement(o, "loading", new Object[] {}));

		assertCodedXML(t, "MockBean4Owner_SetOwnerWithWriteStatement.xml",
				temp, enc);

	}

	public void testWriteObject_StaticField() throws IOException {
		ByteArrayOutputStream temp = new ByteArrayOutputStream();
		XMLEncoder enc = new XMLEncoder(temp);

		enc.setPersistenceDelegate(MockBean4StaticField.class,
				new MockBean4StaticField_PD());

		assertCodedXML(MockBean4StaticField.inst, "MockBean4StaticField.xml",
				temp, enc);

	}

	public void testClose() {
		ByteArrayOutputStream out = new ByteArrayOutputStream() {
			boolean closeCalled = false;

			public void close() throws IOException {
				if (closeCalled) {
					throw new IOException("close already called!");
				} else {
					closeCalled = true;
					super.close();
				}
			}
		};
		XMLEncoder enc = new XMLEncoder(out);
		enc.writeObject(new Integer(3));
		assertTrue(out.size() == 0);

		enc.close();

		assertTrue(out.size() > 0);
		try {
			out.close();
			fail();
		} catch (IOException e) {
			assertEquals("close already called!", e.getMessage());
		}
	}

	public void testFlush() {
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		XMLEncoder enc = new XMLEncoder(out);
		Integer i = new Integer(3);
		enc.writeObject(i);
		assertTrue(out.size() == 0);
		assertNotNull(enc.get(i));

		enc.flush();

		assertTrue(out.size() > 0);
		assertNull(enc.get(i));
	}

	public void testXMLEncoder_Null() {
		try {
			XMLEncoder enc = new XMLEncoder(null);
		} catch (NullPointerException e) {
			// expected
		}
	}

	public void testGetOwner() {
		XMLEncoder enc = new XMLEncoder(System.out);
		assertNull(enc.getOwner());
	}

	public void testSetOwner() {
		XMLEncoder enc = new XMLEncoder(System.out);
		Object owner = Boolean.FALSE;

		enc.setOwner(owner);
		assertSame(owner, enc.getOwner());

		enc.setOwner(null);
		assertNull(enc.getOwner());
	}

	private void assertCodedXML(Object obj, String xmlFile) throws IOException {
		ByteArrayOutputStream temp = new ByteArrayOutputStream();
		XMLEncoder enc = new XMLEncoder(temp);

		assertCodedXML(obj, xmlFile, temp, enc);
	}

	private void assertCodedXML(Object obj, String xmlFile,
			ByteArrayOutputStream temp, XMLEncoder enc) throws IOException {
		if (enc == null || temp == null) {
			temp = new ByteArrayOutputStream();
			enc = new XMLEncoder(temp);
		}
		enc.writeObject(obj);
		enc.close();
		byte bytes[] = temp.toByteArray();

		InputStream refIn = XMLEncoderTest.class.getResourceAsStream(xmlFile);
		if (refIn == null) {
			FileOutputStream file = new FileOutputStream(xmlFile);
			file.write(bytes);
			file.close();
			throw new Error("resource " + xmlFile + " not exist in "
					+ XMLEncoderTest.class.getPackage()
					+ ", write in current dir!");
		}
		BufferedReader xml = new BufferedReader(new InputStreamReader(
				new ByteArrayInputStream(bytes), "UTF-8"));
		BufferedReader refXml = new BufferedReader(new InputStreamReader(refIn,
				"UTF-8"));

		String line = null, refLine = null;
		int lineNum = 0;
		while (true) {
			lineNum++;
			line = xml.readLine();
			refLine = refXml.readLine();
			if (line == null && refLine == null) {
				break;
			}
			if (line == null) {
				throw new RuntimeException("line " + lineNum
						+ ", xml ends, but ref line is: " + refLine);
			}
			if (refLine == null) {
				throw new RuntimeException("line " + lineNum
						+ ", ref xml ends, but line is: " + line);
			}
			if (lineNum == 2) {
				String trim = line.trim();
				assertTrue(trim.startsWith("<java version=\""));
				assertTrue(trim.endsWith("\" class=\"java.beans.XMLDecoder\">"));
			} else {
				String trim = line.trim();
				String refTrim = refLine.trim();
				if (trim.endsWith(" />") && refTrim.endsWith("/>")
						&& trim.length() == refTrim.length() + 1) {
					trim = trim.substring(0, trim.length() - 3);
					refTrim = refTrim.substring(0, refTrim.length() - 2);
				}
				if (!trim.equals(refTrim)) {
					System.out.println("---- Bad xml ----");
					BufferedReader reader = new BufferedReader(
							new InputStreamReader(new ByteArrayInputStream(
									bytes), "UTF-8"));
					String l = null;
					while ((l = reader.readLine()) != null) {
						System.out.println(l);
					}
					throw new RuntimeException("line " + lineNum
							+ ", expected: " + refLine + ", but was: " + line);
				}
			}
		}
	}

}
