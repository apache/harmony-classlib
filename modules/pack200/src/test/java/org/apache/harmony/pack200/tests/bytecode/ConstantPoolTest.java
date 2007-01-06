package org.apache.harmony.archive.tests.internal.pack200.bytecode;

import junit.framework.TestCase;

import org.apache.harmony.archive.internal.pack200.bytecode.CPMember;
import org.apache.harmony.archive.internal.pack200.bytecode.CPUTF8;
import org.apache.harmony.archive.internal.pack200.bytecode.ClassConstantPool;

public class ConstantPoolTest extends TestCase {
	private ClassConstantPool pool;

	public void setUp() {
		pool = new ClassConstantPool();
	}
	public void testDuplicateUTF8() {
		CPUTF8 u1 = new CPUTF8("thing");
		CPUTF8 u2 = new CPUTF8("thing");
		pool.add(u1);
		pool.add(u2);
		assertEquals(1,pool.size());
	}
	public void testDuplicateField() {
		CPMember cp1 = new CPMember("name:I",0,null);
		pool.add(cp1);
		assertEquals(2,pool.size());
		CPMember cp2 = new CPMember("name:I",0,null);
		pool.add(cp2);
		assertEquals(2,pool.size());
	}
	public void testIndex() {
		pool.add(new CPUTF8("OtherThing"));
		CPUTF8 u1 = new CPUTF8("thing");
		pool.add(u1);
		pool.resolve();
		assertTrue(pool.indexOf(u1) > 0);
	}
}
