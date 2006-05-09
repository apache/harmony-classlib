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

import java.beans.DefaultPersistenceDelegate;
import java.beans.Encoder;
import java.beans.Expression;
import java.beans.PersistenceDelegate;
import java.beans.Statement;
import java.beans.XMLEncoder;
import java.io.ByteArrayOutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Vector;

import junit.framework.TestCase;

/**
 * Test some customized persistence delegate, not including the AWT and SWING
 * ones.
 */
public class CustomizedPersistenceDelegateTest extends TestCase {

	private MockEncoder enc = null;

	protected void setUp() throws Exception {
		super.setUp();
		enc = new MockEncoder();
	}

	public void testStringPD() {
		enc.writeObject("");
		assertEquals("", enc.get(""));
	}

	public void testArrayPD() {
		Integer[] ia = new Integer[] { new Integer(1), new Integer(11100) };
		enc.writeObject(ia);
		assertTrue(Arrays.equals(ia, (Integer[]) enc.get(ia)));
	}

	public void testClassPD() {
		enc.writeObject(Integer.class);
		assertSame(Integer.class, enc.get(Integer.class));
		enc.writeObject(String.class);
		assertSame(String.class, enc.get(String.class));
		enc.writeObject(Class.class);
		assertSame(Class.class, enc.get(Class.class));
	}

	/*
	 * Test the PD for proxy classes. Please note that this testcase has the
	 * side effect that a PD for the system classloader is registered in the
	 * encoder and may affect other testcases because the registration is
	 * static.
	 */
	public void testProxyPD() throws Exception {
		MyInterface o = (MyInterface) Proxy.newProxyInstance(ClassLoader
				.getSystemClassLoader(), new Class[] { MyInterface.class },
				new MyHandler(1));
		PersistenceDelegate pd = new PersistenceDelegate() {
			public Expression instantiate(Object o, Encoder e) {
				return new Expression(o, ClassLoader.class,
						"getSystemClassLoader", null);
			}
		};
		enc.setPersistenceDelegate(ClassLoader.getSystemClassLoader()
				.getClass(), pd);
		enc.writeObject(o);
		MyInterface o2 = (MyInterface) enc.get(o);
		assertEquals(o.getProp(), o2.getProp());
	}

	public void testPrimitivePD() {
		// int
		int[] ia = new int[] { 1, 2 };
		enc.writeObject(ia);
		assertTrue(Arrays.equals(ia, (int[]) enc.get(ia)));
		ia = new int[0];
		enc.writeObject(ia);
		assertTrue(Arrays.equals(ia, (int[]) enc.get(ia)));

		// short
		short[] sa = new short[] { 1, 2 };
		enc.writeObject(sa);
		assertTrue(Arrays.equals(sa, (short[]) enc.get(sa)));
		sa = new short[0];
		enc.writeObject(sa);
		assertTrue(Arrays.equals(sa, (short[]) enc.get(sa)));

		// long
		long[] la = new long[] { 1, 2 };
		enc.writeObject(la);
		assertTrue(Arrays.equals(la, (long[]) enc.get(la)));
		la = new long[0];
		enc.writeObject(la);
		assertTrue(Arrays.equals(la, (long[]) enc.get(la)));

		// float
		float[] fa = new float[] { 1, 2 };
		enc.writeObject(fa);
		assertTrue(Arrays.equals(fa, (float[]) enc.get(fa)));
		fa = new float[0];
		enc.writeObject(fa);
		assertTrue(Arrays.equals(fa, (float[]) enc.get(fa)));

		// double
		double[] da = new double[] { -1, 2.0 };
		enc.writeObject(da);
		assertTrue(Arrays.equals(da, (double[]) enc.get(da)));
		da = new double[0];
		enc.writeObject(da);
		assertTrue(Arrays.equals(da, (double[]) enc.get(da)));

		// boolean
		boolean[] ba = new boolean[] { true, false };
		enc.writeObject(ba);
		assertTrue(Arrays.equals(ba, (boolean[]) enc.get(ba)));
		ba = new boolean[0];
		enc.writeObject(ba);
		assertTrue(Arrays.equals(ba, (boolean[]) enc.get(ba)));

		// char
		char[] ca = new char[] { 'c', 'b' };
		enc.writeObject(ca);
		assertTrue(Arrays.equals(ca, (char[]) enc.get(ca)));
		ca = new char[0];
		enc.writeObject(ca);
		assertTrue(Arrays.equals(ca, (char[]) enc.get(ca)));

		// byte
		byte[] bba = new byte[] { 112, 12 };
		enc.writeObject(bba);
		assertTrue(Arrays.equals(bba, (byte[]) enc.get(bba)));
		bba = new byte[0];
		enc.writeObject(bba);
		assertTrue(Arrays.equals(bba, (byte[]) enc.get(bba)));
	}

	public void testMethodPD() throws Exception {
		Method m = Object.class.getMethod("getClass", (Class[])null);
		enc.writeObject(m);
		assertEquals(m, enc.get(m));
	}

	public void testFieldPD() throws Exception {
		Field f = MyHandler.class.getField("i");
		enc.writeObject(f);
		assertEquals(f, enc.get(f));
	}

	static class MockPersistenceDelegate extends DefaultPersistenceDelegate {
		private String name;

		public MockPersistenceDelegate(String s) {
			this.name = s;
		}

		protected void initialize(Class type, Object oldInstance,
				Object newInstance, Encoder enc) {
			// System.out.println(name + " initialize called: " + type);
			// new Throwable().printStackTrace();
			super.initialize(type, oldInstance, newInstance, enc);
			// System.out.println(name + " initialize exited");
		}

		protected Expression instantiate(Object oldInstance, Encoder enc) {
			// System.out.println(name + " instantiate called");
			return super.instantiate(oldInstance, enc);
		}

		protected boolean mutatesTo(Object o1, Object o2) {
			// System.out.println(name + " mutatesTo called");
			return super.mutatesTo(o1, o2);
		}
	}

	static class MockPersistenceDelegate2 extends PersistenceDelegate {
		private String name;

		public MockPersistenceDelegate2(String s) {
			this.name = s;
		}

		protected void initialize(Class type, Object oldInstance,
				Object newInstance, Encoder enc) {
			// System.out.println(name + " initialize called: " + type);
			// new Throwable().printStackTrace();
			super.initialize(type, oldInstance, newInstance, enc);
			// System.out.println(name + " initialize exited");
		}

		protected Expression instantiate(Object oldInstance, Encoder enc) {
			// System.out.println(name + " instantiate called");
			return null;
		}

		protected boolean mutatesTo(Object o1, Object o2) {
			// System.out.println(name + " mutatesTo called");
			return super.mutatesTo(o1, o2);
		}
	}

	static class MockEncoder extends Encoder {
		public void writeObject(Object o) {
			// System.out.println("write object: " + o);
			super.writeObject(o);
		}

		public PersistenceDelegate getPersistenceDelegate(Class type) {
			// System.out.println("getPersistenceDelegate for " + type);
			return super.getPersistenceDelegate(type);
		}

		public void writeExpression(Expression oldExp) {
			// System.out.println("Expression: " + oldExp);
			super.writeExpression(oldExp);
		}

		public void writeStatement(Statement oldStat) {
			// System.out.println("Statement: " + oldStat);
			if (oldStat.getMethodName().equals("add")) {
				new Throwable().printStackTrace();
			}
			super.writeStatement(oldStat);
		}

		public Object get(Object o) {
			// StackTraceElement[] stack = new Throwable().getStackTrace();
			// new Throwable().printStackTrace();
			// System.out.println("get object: " + o);
			return super.get(o);
		}
	}

	/*
	 * Interface for the proxy class.
	 */
	public static interface MyInterface {
		void setProp(int i);

		int getProp();
	}

	/*
	 * Mock grand parent.
	 */
	public static class MockGrandParent {
		boolean b;

		public boolean getPropb() {
			return this.b;
		}

		public void setPropb(boolean b) {
			this.b = b;
		}
	}

	/*
	 * Mock parent.
	 */
	public static class MockParent extends MockGrandParent {
		String s;

		int i;

		public int getProp() {
			return 2;
		}

		public void setProp(int i) {
			this.i = i;
		}

		public String getProps() {
			return this.s;
		}

		public void setProps(String s) {
			this.s = s;
		}
	}

	/*
	 * Mock object.
	 */
	public static class MockObject extends AbstractList implements MyInterface,
			List {
		int i;

		Vector v = new Vector();

		public void add(int location, Object object) {
			v.add(location, object);
		}

		public boolean add(Object object) {
			return v.add(object);
		}

		public boolean addAll(Collection collection) {
			return v.addAll(collection);
		}

		public boolean addAll(int location, Collection collection) {
			return v.addAll(location, collection);
		}

		public void clear() {
			v.clear();
		}

		public boolean contains(Object object) {
			return v.contains(object);
		}

		public boolean containsAll(Collection collection) {
			return v.containsAll(collection);
		}

		public Object get(int location) {
			return v.get(location);
		}

		public int indexOf(Object object) {
			return v.indexOf(object);
		}

		public boolean isEmpty() {
			return v.isEmpty();
		}

		public Iterator iterator() {
			return v.iterator();
		}

		public int lastIndexOf(Object object) {
			return v.lastIndexOf(object);
		}

		public ListIterator listIterator() {
			return v.listIterator();
		}

		public ListIterator listIterator(int location) {
			return v.listIterator(location);
		}

		public Object remove(int location) {
			return v.remove(location);
		}

		public boolean remove(Object object) {
			return v.remove(object);
		}

		public boolean removeAll(Collection collection) {
			return v.removeAll(collection);
		}

		public boolean retainAll(Collection collection) {
			return v.retainAll(collection);
		}

		public Object set(int location, Object object) {
			return v.set(location, object);
		}

		public int size() {
			return v.size();
		}

		public List subList(int start, int end) {
			return v.subList(start, end);
		}

		public Object[] toArray() {
			return v.toArray();
		}

		public Object[] toArray(Object[] array) {
			return v.toArray(array);
		}

		public int getProp() {
			return i;
		}

		public void setProp(int i) {
			this.i = i;
		}
	}

	/*
	 * Handler for the proxy class.
	 */
	public static class MyHandler implements InvocationHandler {

		public int i;

		public MyHandler() {
			this.i = 0;
		}

		public MyHandler(int i) {
			this.i = i;
		}

		public Object invoke(Object proxy, Method method, Object[] args)
				throws Throwable {
			if (method.getName().equals("setProp")) {
				this.i = ((Integer) args[0]).intValue();
				return null;
			} else if (method.getName().equals("getProp")) {
				return new Integer(i);
			}
			return null;
		}

		public void setI(int i) {
			this.i = i;
		}

		public int getI() {
			return this.i;
		}
	}

	public static void main1(String[] args) throws Exception {
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		XMLEncoder enc = new XMLEncoder(os);
		MockEncoder enc2 = new MockEncoder();
		enc2.setPersistenceDelegate(MyInterface.class,
				new MockPersistenceDelegate("interface"));
		enc2.setPersistenceDelegate(MockParent.class,
				new MockPersistenceDelegate("parent class"));
		enc2.setPersistenceDelegate(MockGrandParent.class,
				new MockPersistenceDelegate("grand parent class"));

		// Vector
		Vector v = new Vector();
		v.add("hehe");
		// enc.writeObject(v);

		// ArrayList
		ArrayList al = new ArrayList();
		al.add("hehe");
		// enc.writeObject(al);

		// Field
		Field m = Integer.class.getDeclaredField("TYPE");
		// enc.writeObject(m);

		// Complex bean with indexed properties
		ComplexBean cb = new ComplexBean();
		cb.setI(0, true);
		// enc.writeObject(cb);

		MockObject o = new MockObject();
		o.add("haha");
		o.setProp(3);
		// o.setProps("test");
		// o.setPropb(true);
		// Menu mm = new Menu("MyMenu");
		// MenuItem mi = new MenuItem("menu1");
		// mm.add(mi);
		// enc.writeObject(mm);

		// enc2.writeObject(o);
		// enc.writeObject(o);
		// new MockPersistenceDelegate2("PD").initialize(AbstractList.class, v,
		// new Vector(), enc2);

		enc.close();

		System.out.println(os.toString());
		// System.out.println(enc2.getPersistenceDelegate(MyInterface.class));
		// System.out.println(enc2.getPersistenceDelegate(Vector.class));
		// System.out.println(enc2.getPersistenceDelegate(List.class));
		// System.out.println(enc2.getPersistenceDelegate(Collection.class));
		// System.out.println(enc2
		// .getPersistenceDelegate(AbstractCollection.class));
		// System.out.println(enc2.getPersistenceDelegate(AbstractList.class));
		// System.out.println(enc2.getPersistenceDelegate(Map.class));
		// System.out.println(enc2.getPersistenceDelegate(Hashtable.class));
		// System.out.println(enc2.getPersistenceDelegate(HashMap.class));
		// System.out.println(enc2.getPersistenceDelegate(Component.class));

	}

	public static void main(String[] args) throws Exception {
		XMLEncoder e = new XMLEncoder(System.out);
		e.writeStatement(new Expression(Integer.class, "getField",
				new Object[] { "TYPE" }));
		// e.writeObject(Integer.TYPE);
		e.close();
	}

	public static class ComplexBean {
		boolean[] ba = new boolean[2];

		public void setI(int i, boolean b) {
			ba[i] = b;
		}

		public boolean getI(int i) {
			return ba[i];
		}
		//        
		// public void setI(boolean[] ba) {
		// this.ba = ba;
		// }
		//        
		// public boolean[] getI() {
		// return ba;
		// }
	}
}
