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

package tests.api.java.lang;

public class StringBufferTest extends junit.framework.TestCase {

	StringBuffer testBuffer;

	/**
	 * @tests java.lang.StringBuffer#StringBuffer()
	 */
	public void test_Constructor() {
		// Test for method java.lang.StringBuffer()
		new StringBuffer();
		assertTrue("Invalid buffer created", true);
	}

	/**
	 * @tests java.lang.StringBuffer#StringBuffer(int)
	 */
	public void test_ConstructorI() {
		// Test for method java.lang.StringBuffer(int)
		StringBuffer sb = new StringBuffer(8);
		assertTrue("Newly constructed buffer is of incorrect length", sb
				.length() == 0);
	}

	/**
	 * @tests java.lang.StringBuffer#StringBuffer(java.lang.String)
	 */
	public void test_ConstructorLjava_lang_String() {
		// Test for method java.lang.StringBuffer(java.lang.String)

		StringBuffer sb = new StringBuffer("HelloWorld");

		assertTrue("Invalid buffer created", sb.length() == 10
				&& (sb.toString().equals("HelloWorld")));

		boolean pass = false;
		try {
			new StringBuffer(null);
		} catch (NullPointerException e) {
			pass = true;
		}
		assertTrue("Should throw NullPointerException", pass);
	}

	/**
	 * @tests java.lang.StringBuffer#append(char[])
	 */
	public void test_append$C() {
		// Test for method java.lang.StringBuffer
		// java.lang.StringBuffer.append(char [])
		char buf[] = new char[4];
		"char".getChars(0, 4, buf, 0);
		testBuffer.append(buf);
		assertTrue("Append of char[] failed", testBuffer.toString().equals(
				"This is a test bufferchar"));
	}

	/**
	 * @tests java.lang.StringBuffer#append(char[], int, int)
	 */
	public void test_append$CII() {
		// Test for method java.lang.StringBuffer
		// java.lang.StringBuffer.append(char [], int, int)
		StringBuffer sb = new StringBuffer();
		char[] buf1 = { 'H', 'e', 'l', 'l', 'o' };
		char[] buf2 = { 'W', 'o', 'r', 'l', 'd' };
		sb.append(buf1, 0, buf1.length);
		assertTrue("Buffer is invalid length after append", sb.length() == 5);
		sb.append(buf2, 0, buf2.length);
		assertTrue("Buffer is invalid length after append", sb.length() == 10);
		assertTrue("Buffer contains invalid chars", (sb.toString()
				.equals("HelloWorld")));
	}

	/**
	 * @tests java.lang.StringBuffer#append(char)
	 */
	public void test_appendC() {
		// Test for method java.lang.StringBuffer
		// java.lang.StringBuffer.append(char)
		StringBuffer sb = new StringBuffer();
		char buf1 = 'H';
		char buf2 = 'W';
		sb.append(buf1);
		assertTrue("Buffer is invalid length after append", sb.length() == 1);
		sb.append(buf2);
		assertTrue("Buffer is invalid length after append", sb.length() == 2);
		assertTrue("Buffer contains invalid chars",
				(sb.toString().equals("HW")));
	}

	/**
	 * @tests java.lang.StringBuffer#append(double)
	 */
	public void test_appendD() {
		// Test for method java.lang.StringBuffer
		// java.lang.StringBuffer.append(double)
		StringBuffer sb = new StringBuffer();
		sb.append(Double.MAX_VALUE);
		assertTrue("Buffer is invalid length after append", sb.length() == 22);
		assertTrue("Buffer contains invalid characters", sb.toString().equals(
				"1.7976931348623157E308"));
	}

	/**
	 * @tests java.lang.StringBuffer#append(float)
	 */
	public void test_appendF() {
		// Test for method java.lang.StringBuffer
		// java.lang.StringBuffer.append(float)
		StringBuffer sb = new StringBuffer();
		final float floatNum = 900.87654F;
		sb.append(floatNum);
		assertTrue("Buffer is invalid length after append: " + sb.length(), sb
				.length() == String.valueOf(floatNum).length());
		assertTrue("Buffer contains invalid characters", sb.toString().equals(
				String.valueOf(floatNum)));
	}

	/**
	 * @tests java.lang.StringBuffer#append(int)
	 */
	public void test_appendI() {
		// Test for method java.lang.StringBuffer
		// java.lang.StringBuffer.append(int)
		StringBuffer sb = new StringBuffer();
		sb.append(9000);
		assertTrue("Buffer is invalid length after append", sb.length() == 4);
		sb.append(1000);
		assertTrue("Buffer is invalid length after append", sb.length() == 8);
		assertTrue("Buffer contains invalid characters", sb.toString().equals(
				"90001000"));
	}

	/**
	 * @tests java.lang.StringBuffer#append(long)
	 */
	public void test_appendJ() {
		// Test for method java.lang.StringBuffer
		// java.lang.StringBuffer.append(long)

		StringBuffer sb = new StringBuffer();
		long t = 927654321098L;
		sb.append(t);
		assertTrue("Buffer is of invlaid length", sb.length() == 12);
		assertTrue("Buffer contains invalid characters", sb.toString().equals(
				"927654321098"));
	}

	/**
	 * @tests java.lang.StringBuffer#append(java.lang.Object)
	 */
	public void test_appendLjava_lang_Object() {
		// Test for method java.lang.StringBuffer
		// java.lang.StringBuffer.append(java.lang.Object)
		StringBuffer sb = new StringBuffer();
		Object obj1 = new Object();
		Object obj2 = new Object();
		sb.append(obj1);
		sb.append(obj2);
		assertTrue("Buffer contains invalid characters", sb.toString().equals(
				obj1.toString() + obj2.toString()));
	}

	/**
	 * @tests java.lang.StringBuffer#append(java.lang.String)
	 */
	public void test_appendLjava_lang_String() {
		// Test for method java.lang.StringBuffer
		// java.lang.StringBuffer.append(java.lang.String)
		StringBuffer sb = new StringBuffer();
		String buf1 = "Hello";
		String buf2 = "World";
		sb.append(buf1);
		assertTrue("Buffer is invalid length after append", sb.length() == 5);
		sb.append(buf2);
		assertTrue("Buffer is invalid length after append", sb.length() == 10);
		assertTrue("Buffer contains invalid chars", (sb.toString()
				.equals("HelloWorld")));
	}

	/**
	 * @tests java.lang.StringBuffer#append(boolean)
	 */
	public void test_appendZ() {
		// Test for method java.lang.StringBuffer
		// java.lang.StringBuffer.append(boolean)
		StringBuffer sb = new StringBuffer();
		sb.append(false);
		assertTrue("Buffer is invalid length after append", sb.length() == 5);
		sb.append(true);
		assertTrue("Buffer is invalid length after append", sb.length() == 9);
		assertTrue("Buffer is invalid length after append", (sb.toString()
				.equals("falsetrue")));
	}

	/**
	 * @tests java.lang.StringBuffer#capacity()
	 */
	public void test_capacity() {
		// Test for method int java.lang.StringBuffer.capacity()
		StringBuffer sb = new StringBuffer(10);
		assertTrue("Returned incorrect capacity", sb.capacity() == 10);
		sb.ensureCapacity(100);
		assertTrue("Returned incorrect capacity", sb.capacity() >= 100);
	}

	/**
	 * @tests java.lang.StringBuffer#charAt(int)
	 */
	public void test_charAtI() {
		// Test for method char java.lang.StringBuffer.charAt(int)
		assertTrue("Returned incorrect char", testBuffer.charAt(3) == 's');

		// Test for StringIndexOutOfBoundsException
		boolean exception = false;
		try {
			testBuffer.charAt(-1);
		} catch (StringIndexOutOfBoundsException e) {
			exception = true;
		} catch (ArrayIndexOutOfBoundsException e) {
		}
		assertTrue("Should throw StringIndexOutOfBoundsException", exception);
	}

	/**
	 * @tests java.lang.StringBuffer#delete(int, int)
	 */
	public void test_deleteII() {
		// Test for method java.lang.StringBuffer
		// java.lang.StringBuffer.delete(int, int)
		testBuffer.delete(7, 7);
		assertTrue("Deleted chars when start == end", testBuffer.toString()
				.equals("This is a test buffer"));
		testBuffer.delete(4, 14);
		assertTrue("Deleted incorrect chars", testBuffer.toString().equals(
				"This buffer"));

		testBuffer = new StringBuffer("This is a test buffer");
		String sharedStr = testBuffer.toString();
		testBuffer.delete(0, testBuffer.length());
		assertTrue("Didn't clone shared buffer", sharedStr
				.equals("This is a test buffer"));
		assertTrue("Deleted incorrect chars", testBuffer.toString().equals(""));
		testBuffer.append("more stuff");
		assertTrue("Didn't clone shared buffer 2", sharedStr
				.equals("This is a test buffer"));
		assertTrue("Wrong contents", testBuffer.toString().equals("more stuff"));
		try {
			testBuffer.delete(-5, 2);
		} catch (IndexOutOfBoundsException e) {
		}
		assertTrue("Wrong contents 2", testBuffer.toString().equals(
				"more stuff"));
	}

	/**
	 * @tests java.lang.StringBuffer#deleteCharAt(int)
	 */
	public void test_deleteCharAtI() {
		// Test for method java.lang.StringBuffer
		// java.lang.StringBuffer.deleteCharAt(int)
		testBuffer.deleteCharAt(3);
		assertTrue("Deleted incorrect char", testBuffer.toString().equals(
				"Thi is a test buffer"));
	}

	/**
	 * @tests java.lang.StringBuffer#ensureCapacity(int)
	 */
	public void test_ensureCapacityI() {
		// Test for method void java.lang.StringBuffer.ensureCapacity(int)
		StringBuffer sb = new StringBuffer(10);

		sb.ensureCapacity(100);
		assertTrue("Failed to increase capacity", sb.capacity() >= 100);
	}

	/**
	 * @tests java.lang.StringBuffer#getChars(int, int, char[], int)
	 */
	public void test_getCharsII$CI() {
		// Test for method void java.lang.StringBuffer.getChars(int, int, char
		// [], int)

		char[] buf = new char[10];
		testBuffer.getChars(4, 8, buf, 2);
		assertTrue("Returned incorrect chars", new String(buf, 2, 4)
				.equals(testBuffer.toString().substring(4, 8)));

		boolean exception = false;
		try {
			StringBuffer buf2 = new StringBuffer("");
			buf2.getChars(0, 0, new char[5], 2);
		} catch (IndexOutOfBoundsException e) {
			exception = true;
		}
		assertTrue("did not expect IndexOutOfBoundsException", !exception);
	}

	/**
	 * @tests java.lang.StringBuffer#insert(int, char[])
	 */
	public void test_insertI$C() {
		// Test for method java.lang.StringBuffer
		// java.lang.StringBuffer.insert(int, char [])
		char buf[] = new char[4];
		"char".getChars(0, 4, buf, 0);
		testBuffer.insert(15, buf);
		assertTrue("Insert test failed", testBuffer.toString().equals(
				"This is a test charbuffer"));

		boolean exception = false;
		StringBuffer buf1 = new StringBuffer("abcd");
		try {
			buf1.insert(-1, (char[]) null);
		} catch (StringIndexOutOfBoundsException e) {
			exception = true;
		} catch (NullPointerException e) {
		}
		assertTrue("Should throw StringIndexOutOfBoundsException", exception);
	}

	/**
	 * @tests java.lang.StringBuffer#insert(int, char[], int, int)
	 */
	public void test_insertI$CII() {
		// Test for method java.lang.StringBuffer
		// java.lang.StringBuffer.insert(int, char [], int, int)
		char[] c = new char[] { 'n', 'o', 't', ' ' };
		testBuffer.insert(8, c, 0, 4);
		assertTrue("Insert failed: " + testBuffer.toString(), testBuffer
				.toString().equals("This is not a test buffer"));

		boolean exception = false;
		StringBuffer buf1 = new StringBuffer("abcd");
		try {
			buf1.insert(-1, (char[]) null, 0, 0);
		} catch (StringIndexOutOfBoundsException e) {
			exception = true;
		} catch (NullPointerException e) {
		}
		assertTrue("Should throw StringIndexOutOfBoundsException", exception);
	}

	/**
	 * @tests java.lang.StringBuffer#insert(int, char)
	 */
	public void test_insertIC() {
		// Test for method java.lang.StringBuffer
		// java.lang.StringBuffer.insert(int, char)
		testBuffer.insert(15, 'T');
		assertTrue("Insert test failed", testBuffer.toString().equals(
				"This is a test Tbuffer"));
	}

	/**
	 * @tests java.lang.StringBuffer#insert(int, double)
	 */
	public void test_insertID() {
		// Test for method java.lang.StringBuffer
		// java.lang.StringBuffer.insert(int, double)
		testBuffer.insert(15, Double.MAX_VALUE);
		assertTrue("Insert test failed", testBuffer.toString().equals(
				"This is a test " + Double.MAX_VALUE + "buffer"));
	}

	/**
	 * @tests java.lang.StringBuffer#insert(int, float)
	 */
	public void test_insertIF() {
		// Test for method java.lang.StringBuffer
		// java.lang.StringBuffer.insert(int, float)
		testBuffer.insert(15, Float.MAX_VALUE);
		String testBufferString = testBuffer.toString();
		String expectedResult = "This is a test "
				+ String.valueOf(Float.MAX_VALUE) + "buffer";
		assertTrue("Insert test failed, got: " + "\'" + testBufferString + "\'"
				+ " but wanted: " + "\'" + expectedResult + "\'",
				testBufferString.equals(expectedResult));
	}

	/**
	 * @tests java.lang.StringBuffer#insert(int, int)
	 */
	public void test_insertII() {
		// Test for method java.lang.StringBuffer
		// java.lang.StringBuffer.insert(int, int)
		testBuffer.insert(15, 100);
		assertTrue("Insert test failed", testBuffer.toString().equals(
				"This is a test 100buffer"));
	}

	/**
	 * @tests java.lang.StringBuffer#insert(int, long)
	 */
	public void test_insertIJ() {
		// Test for method java.lang.StringBuffer
		// java.lang.StringBuffer.insert(int, long)
		testBuffer.insert(15, 88888888888888888L);
		assertTrue("Insert test failed", testBuffer.toString().equals(
				"This is a test 88888888888888888buffer"));
	}

	/**
	 * @tests java.lang.StringBuffer#insert(int, java.lang.Object)
	 */
	public void test_insertILjava_lang_Object() {
		// Test for method java.lang.StringBuffer
		// java.lang.StringBuffer.insert(int, java.lang.Object)
		Object obj1 = new Object();
		testBuffer.insert(15, obj1);
		assertTrue("Insert test failed", testBuffer.toString().equals(
				"This is a test " + obj1.toString() + "buffer"));
	}

	/**
	 * @tests java.lang.StringBuffer#insert(int, java.lang.String)
	 */
	public void test_insertILjava_lang_String() {
		// Test for method java.lang.StringBuffer
		// java.lang.StringBuffer.insert(int, java.lang.String)

		testBuffer.insert(15, "STRING ");
		assertTrue("Insert test failed", testBuffer.toString().equals(
				"This is a test STRING buffer"));
	}

	/**
	 * @tests java.lang.StringBuffer#insert(int, boolean)
	 */
	public void test_insertIZ() {
		// Test for method java.lang.StringBuffer
		// java.lang.StringBuffer.insert(int, boolean)
		testBuffer.insert(15, true);
		assertTrue("Insert test failed", testBuffer.toString().equals(
				"This is a test truebuffer"));
	}

	/**
	 * @tests java.lang.StringBuffer#length()
	 */
	public void test_length() {
		// Test for method int java.lang.StringBuffer.length()
		assertTrue("Incorrect length returned", testBuffer.length() == 21);
	}

	/**
	 * @tests java.lang.StringBuffer#replace(int, int, java.lang.String)
	 */
	public void test_replaceIILjava_lang_String() {
		// Test for method java.lang.StringBuffer
		// java.lang.StringBuffer.replace(int, int, java.lang.String)
		testBuffer.replace(5, 9, "is a replaced");
		assertTrue("Replace failed, wanted: " + "\'"
				+ "This is a replaced test buffer" + "\'" + " but got: " + "\'"
				+ testBuffer.toString() + "\'", testBuffer.toString().equals(
				"This is a replaced test buffer"));
		assertTrue("insert1", new StringBuffer().replace(0, 0, "text")
				.toString().equals("text"));
		assertTrue("insert2", new StringBuffer("123").replace(3, 3, "text")
				.toString().equals("123text"));
		assertTrue("insert2", new StringBuffer("123").replace(1, 1, "text")
				.toString().equals("1text23"));
	}

	private String writeString(String in) {
		StringBuffer result = new StringBuffer();
		result.append("\"");
		for (int i = 0; i < in.length(); i++) {
			result.append(" 0x" + Integer.toHexString(in.charAt(i)));
		}
		result.append("\"");
		return result.toString();
	}

	private void reverseTest(String id, String org, String rev, String back) {
		// create non-shared StringBuffer
		StringBuffer sb = new StringBuffer(org);
		sb.reverse();
		String reversed = sb.toString();
		assertTrue("reversed surrogate " + id + ": " + writeString(reversed),
				reversed.equals(rev));
		// create non-shared StringBuffer
		sb = new StringBuffer(reversed);
		sb.reverse();
		reversed = sb.toString();
		assertTrue("reversed surrogate " + id + "a: " + writeString(reversed),
				reversed.equals(back));

		// test algorithm when StringBuffer is shared
		sb = new StringBuffer(org);
		String copy = sb.toString();
		assertEquals(org, copy);
		sb.reverse();
		reversed = sb.toString();
		assertTrue("reversed surrogate " + id + ": " + writeString(reversed),
				reversed.equals(rev));
		sb = new StringBuffer(reversed);
		copy = sb.toString();
		assertEquals(rev, copy);
		sb.reverse();
		reversed = sb.toString();
		assertTrue("reversed surrogate " + id + "a: " + writeString(reversed),
				reversed.equals(back));

	}

	/**
	 * @tests java.lang.StringBuffer#reverse()
	 */
	public void test_reverse() {
		// Test for method java.lang.StringBuffer
		// java.lang.StringBuffer.reverse()
		String org;
		org = "a";
		reverseTest("0", org, org, org);

		org = "ab";
		reverseTest("1", org, "ba", org);

		org = "abcdef";
		reverseTest("2", org, "fedcba", org);

		org = "abcdefg";
		reverseTest("3", org, "gfedcba", org);

	}

	/**
	 * @tests java.lang.StringBuffer#setCharAt(int, char)
	 */
	public void test_setCharAtIC() {
		// Test for method void java.lang.StringBuffer.setCharAt(int, char)
		StringBuffer s = new StringBuffer("HelloWorld");
		s.setCharAt(4, 'Z');
		assertTrue("Returned incorrect char", s.charAt(4) == 'Z');
	}

	/**
	 * @tests java.lang.StringBuffer#setLength(int)
	 */
	public void test_setLengthI() {
		// Test for method void java.lang.StringBuffer.setLength(int)
		testBuffer.setLength(1000);
		assertTrue("Failed to increase length", testBuffer.length() == 1000);
		assertTrue("Increase in length trashed buffer", testBuffer.toString()
				.startsWith("This is a test buffer"));
		testBuffer.setLength(2);
		assertTrue("Failed to decrease length", testBuffer.length() == 2);
		assertTrue("Decrease in length failed", testBuffer.toString().equals(
				"Th"));
	}

	/**
	 * @tests java.lang.StringBuffer#substring(int)
	 */
	public void test_substringI() {
		// Test for method java.lang.String
		// java.lang.StringBuffer.substring(int)
		assertTrue("Returned incorrect substring", testBuffer.substring(5)
				.equals("is a test buffer"));
	}

	/**
	 * @tests java.lang.StringBuffer#substring(int, int)
	 */
	public void test_substringII() {
		// Test for method java.lang.String
		// java.lang.StringBuffer.substring(int, int)
		assertTrue("Returned incorrect substring", testBuffer.substring(5, 7)
				.equals("is"));
	}

	/**
	 * @tests java.lang.StringBuffer#toString()
	 */
	public void test_toString() {
		// Test for method java.lang.String java.lang.StringBuffer.toString()
		assertTrue("Incorrect string value returned", testBuffer.toString()
				.equals("This is a test buffer"));
	}

	/**
	 * Sets up the fixture, for example, open a network connection. This method
	 * is called before a test is executed.
	 */
	protected void setUp() {
		testBuffer = new StringBuffer("This is a test buffer");
	}

	/**
	 * Tears down the fixture, for example, close a network connection. This
	 * method is called after a test is executed.
	 */
	protected void tearDown() {
	}

	protected void doneSuite() {
	}
}
