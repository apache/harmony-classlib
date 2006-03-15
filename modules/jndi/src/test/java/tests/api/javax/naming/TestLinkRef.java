/* Copyright 2004 The Apache Software Foundation or its licensors, as applicable
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
package tests.api.javax.naming;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.naming.CompositeName;
import javax.naming.LinkRef;
import javax.naming.MalformedLinkException;
import javax.naming.Name;
import javax.naming.NamingException;
import javax.naming.StringRefAddr;

import junit.framework.TestCase;
import tests.api.javax.naming.util.Log;

public class TestLinkRef extends TestCase {
	private static Log log = new Log(TestLinkRef.class);

	public void testConstructor_ByName() throws NamingException {
		Name name = new CompositeName("www.apache.org/index.html");
		LinkRef linkRef = new LinkRef(name);
		assertEquals(1, linkRef.size());
		assertEquals(name.toString(), linkRef.getLinkName());
	}

	public void testConstrcutor_ByNameNull() {
		Name name = null;
		try {
			LinkRef linkRef = new LinkRef(name);
			fail("It should throw NullPointerException.");
		} catch (NullPointerException e) {
		}
	}

	public void testConstructor_ByString() {
		String name = "www.apache.org/index.html";
		LinkRef linkRef = new LinkRef(name);
		assertEquals(1, linkRef.size());
	}

	public void testConstrcutor_ByStringNull() {
		String name = null;
		LinkRef linkRef = new LinkRef(name);
		assertNull(linkRef.get("LinkAddress").getContent());
	}

	public void testGetLinkName_Simple() throws NamingException {
		String name = "www.apache.org/index.html";
		LinkRef linkRef = new LinkRef(name);

		assertEquals(name, linkRef.getLinkName());
	}

	public void testGetLinkName_MalformedLinkException() {
		/*
		 * log.setMethod("testGetLinkName_MalformedLinkException");
		 * 
		 * String name = "www.apache.org/index.html"; LinkRef linkRef = new
		 * LinkRef(name); linkRef.className = "illegal class name";
		 * log.log(linkRef.toString()); try { String link =
		 * linkRef.getLinkName(); fail( "It should throw
		 * MalformedLinkException."); } catch (Throwable e) {
		 * log.log(e.getClass().getName()); log.log(e.getMessage()); }
		 */
	}

	public void testGetLinkName_NamingException() throws NamingException {
		String name = "www.apache.org/index.html";
		LinkRef linkRef = new LinkRef(name);
		linkRef.clear();
		StringRefAddr stringRefAddr = new StringRefAddr("illegal type name",
				name);
		try {
			String link = linkRef.getLinkName();
			fail("It should throw a MalformedLinkException");
		} catch (MalformedLinkException e1) {
		}
	}

	public void testGetLinkName_InvalidClassName() throws NamingException {
		String name = "www.apache.org/index.html";
		MyLinkRef linkRef = new MyLinkRef(name);
		linkRef.setClassName("Invalid Class name");
		try {
			String link = linkRef.getLinkName();
			fail("Should throw MalformedLinkException");
		} catch (MalformedLinkException e) {
		}
	}

	public void testGetClassName() {
		String name = "www.apache.org/index.html";
		LinkRef linkRef = new LinkRef(name);
		assertEquals(LinkRef.class.getName(), linkRef.getClassName());
	}

	public void testGetFactoryClassName() {
		String name = "www.apache.org/index.html";
		LinkRef linkRef = new LinkRef(name);
		assertNull(linkRef.getFactoryClassName());
	}

	public void testGetFactoryClassLocation() {
		String name = "www.apache.org/index.html";
		LinkRef linkRef = new LinkRef(name);
		assertNull(linkRef.getFactoryClassLocation());
	}

	public void testEquals_Simple() {
		String name = "www.apache.org/index.html";
		LinkRef linkRef0 = new LinkRef(name);
		LinkRef linkRef1 = new LinkRef(name);

		assertTrue(linkRef0.equals(linkRef1));
		assertTrue(linkRef0.equals(linkRef0));
		assertTrue(linkRef1.equals(linkRef0));
		assertFalse(linkRef0.equals(null));
	}

	public void testHashcode_Simple() {
		String name = "www.apache.org/index.html";
		StringRefAddr addr = new StringRefAddr("LinkAddress", name);
		String className = LinkRef.class.getName();
		LinkRef linkRef = new LinkRef(name);

		assertEquals(className.hashCode() + addr.hashCode(), linkRef.hashCode());
	}

	public void testToString_Simple() {
		String name = "www.apache.org/index.html";
		LinkRef linkRef = new LinkRef(name);
		StringRefAddr addr0 = new StringRefAddr("LinkAddress", name);
		StringRefAddr addr1 = new StringRefAddr("LinkAddress", "www.apache.org");
		linkRef.add(addr1);

		/*
		 * assertEquals( "Reference class name: " + LinkRef.class.getName() +
		 * "\nReference addresses:\n\t" + addr0.toString() + "\n\t" +
		 * addr1.toString() + "\n", linkRef.toString());
		 */
		assertNotNull(linkRef.toString());
	}

	public void testSerializable_Simple() throws ClassNotFoundException,
			IOException {
		String name = "www.apache.org/index.html";
		LinkRef linkRef = new LinkRef(name);
		StringRefAddr addr = new StringRefAddr("StringRefAddr",
				"This is a String RefAddr.");
		linkRef.add(addr);

		// write to byte array
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ObjectOutputStream oos = new ObjectOutputStream(baos);
		oos.writeObject(linkRef);
		byte[] buffer = baos.toByteArray();
		oos.close();
		baos.close();

		// read from byte array
		ByteArrayInputStream bais = new ByteArrayInputStream(buffer);
		ObjectInputStream ois = new ObjectInputStream(bais);
		LinkRef linkRef2 = (LinkRef) ois.readObject();
		ois.close();
		bais.close();

		assertEquals(linkRef, linkRef2);
	}

	public void testSerializable_compatibility() throws ClassNotFoundException,
			IOException {
		ObjectInputStream ois = new ObjectInputStream(getClass()
				.getClassLoader().getResourceAsStream("data/LinkRef.ser"));
		LinkRef linkRef2 = (LinkRef) ois.readObject();
		ois.close();

		String name = "www.eclipse.org/org/index.html";
		LinkRef linkRef = new LinkRef(name);
		StringRefAddr addr = new StringRefAddr("StringRefAddr",
				"This is a String RefAddr.");
		linkRef.add(addr);

		assertEquals(linkRef, linkRef2);
	}

	class MyLinkRef extends LinkRef {

		public MyLinkRef(String s) {
			super(s);
		}

		public void setClassName(String className) {
			this.className = className;
		}
	}
}
