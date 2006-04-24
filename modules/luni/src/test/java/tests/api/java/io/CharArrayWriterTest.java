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

import java.io.CharArrayReader;
import java.io.CharArrayWriter;
import java.io.IOException;
import java.io.StringWriter;

public class CharArrayWriterTest extends junit.framework.TestCase {

	char[] hw = { 'H', 'e', 'l', 'l', 'o', 'W', 'o', 'r', 'l', 'd' };

	CharArrayWriter cw;

	CharArrayReader cr;

	/**
	 * @tests java.io.CharArrayWriter#CharArrayWriter()
	 */
	public void test_Constructor() {
		// Test for method java.io.CharArrayWriter()
		cw = new CharArrayWriter(90);
		assertTrue("Created incorrect writer", cw.size() == 0);
	}

	/**
	 * @tests java.io.CharArrayWriter#CharArrayWriter(int)
	 */
	public void test_ConstructorI() {
		// Test for method java.io.CharArrayWriter(int)
		cw = new CharArrayWriter();
		assertTrue("Created incorrect writer", cw.size() == 0);
	}

	/**
	 * @tests java.io.CharArrayWriter#close()
	 */
	public void test_close() {
		// Test for method void java.io.CharArrayWriter.close()
		cw.close();
	}

	/**
	 * @tests java.io.CharArrayWriter#flush()
	 */
	public void test_flush() {
		// Test for method void java.io.CharArrayWriter.flush()
		cw.flush();
	}

	/**
	 * @tests java.io.CharArrayWriter#reset()
	 */
	public void test_reset() {
		// Test for method void java.io.CharArrayWriter.reset()
		cw.write("HelloWorld", 5, 5);
		cw.reset();
		cw.write("HelloWorld", 0, 5);
		cr = new CharArrayReader(cw.toCharArray());
		try {
			char[] c = new char[100];
			cr.read(c, 0, 5);
			assertTrue("Reset failed to reset buffer", "Hello"
					.equals(new String(c, 0, 5)));
		} catch (IOException e) {
			fail("Exception during reset test : " + e.getMessage());
		}
	}

	/**
	 * @tests java.io.CharArrayWriter#size()
	 */
	public void test_size() {
		// Test for method int java.io.CharArrayWriter.size()
		assertTrue("Returned incorrect size", cw.size() == 0);
		cw.write(hw, 5, 5);
		assertTrue("Returned incorrect size", cw.size() == 5);
	}

	/**
	 * @tests java.io.CharArrayWriter#toCharArray()
	 */
	public void test_toCharArray() {
		// Test for method char [] java.io.CharArrayWriter.toCharArray()
		cw.write("HelloWorld", 0, 10);
		cr = new CharArrayReader(cw.toCharArray());
		try {
			char[] c = new char[100];
			cr.read(c, 0, 10);
			assertTrue("toCharArray failed to return correct array",
					"HelloWorld".equals(new String(c, 0, 10)));
		} catch (IOException e) {
			fail("Exception during toCharArray test : " + e.getMessage());
		}
	}

	/**
	 * @tests java.io.CharArrayWriter#toString()
	 */
	public void test_toString() {
		// Test for method java.lang.String java.io.CharArrayWriter.toString()
		cw.write("HelloWorld", 5, 5);
		cr = new CharArrayReader(cw.toCharArray());
		assertTrue("Returned incorrect string", "World".equals(cw.toString()));
	}

	/**
	 * @tests java.io.CharArrayWriter#write(char[], int, int)
	 */
	public void test_write$CII() {
		// Test for method void java.io.CharArrayWriter.write(char [], int, int)
		cw.write(hw, 5, 5);
		cr = new CharArrayReader(cw.toCharArray());
		try {
			char[] c = new char[100];
			cr.read(c, 0, 5);
			assertTrue("Writer failed to write correct chars", "World"
					.equals(new String(c, 0, 5)));
		} catch (IOException e) {
			fail("Exception during write test : " + e.getMessage());
		}
	}

    /**
     * @tests java.io.CharArrayWriter#write(char[], int, int)
     * Regression for HARMONY-387
     */
    public void test_write$CII_2() {
        CharArrayWriter obj = new CharArrayWriter();
        try {
            obj.write(new char []{'0'}, 0, -1);
            fail("IndexOutOfBoundsException expected");
        } catch (IndexOutOfBoundsException t) {
            assertEquals(
                    "IndexOutOfBoundsException rather than a subclass expected",
                    IndexOutOfBoundsException.class, t.getClass());
        }
    }

	/**
	 * @tests java.io.CharArrayWriter#write(int)
	 */
	public void test_writeI() {
		// Test for method void java.io.CharArrayWriter.write(int)
		cw.write('T');
		cr = new CharArrayReader(cw.toCharArray());
		try {
			assertTrue("Writer failed to write char", cr.read() == 'T');
		} catch (IOException e) {
			fail("Exception during write test : " + e.getMessage());
		}
	}

	/**
	 * @tests java.io.CharArrayWriter#write(java.lang.String, int, int)
	 */
	public void test_writeLjava_lang_StringII() {
		// Test for method void java.io.CharArrayWriter.write(java.lang.String,
		// int, int)
		cw.write("HelloWorld", 5, 5);
		cr = new CharArrayReader(cw.toCharArray());
		try {
			char[] c = new char[100];
			cr.read(c, 0, 5);
			assertTrue("Writer failed to write correct chars", "World"
					.equals(new String(c, 0, 5)));
		} catch (IOException e) {
			fail("Exception during write test : " + e.getMessage());
		}
	}

    /**
     * @tests java.io.CharArrayWriter#write(java.lang.String, int, int)
     * Regression for HARMONY-387
     */
    public void test_writeLjava_lang_StringII_2() throws StringIndexOutOfBoundsException {
        CharArrayWriter obj = new CharArrayWriter();
        try {
            obj.write((String) null, -1, 0);
            fail("NullPointerException expected");
        } catch (NullPointerException t) {
        }
    }

	/**
	 * @tests java.io.CharArrayWriter#writeTo(java.io.Writer)
	 */
	public void test_writeToLjava_io_Writer() {
		// Test for method void java.io.CharArrayWriter.writeTo(java.io.Writer)
		cw.write("HelloWorld", 0, 10);
		StringWriter sw = new StringWriter();
		try {
			cw.writeTo(sw);
			assertTrue("Writer failed to write correct chars", "HelloWorld"
					.equals(sw.toString()));
		} catch (IOException e) {
			fail("Exception during writeTo test : " + e.getMessage());
		}
	}

	/**
	 * Sets up the fixture, for example, open a network connection. This method
	 * is called before a test is executed.
	 */
	protected void setUp() {
		cw = new CharArrayWriter();
	}

	/**
	 * Tears down the fixture, for example, close a network connection. This
	 * method is called after a test is executed.
	 */
	protected void tearDown() {
		if (cr != null)
			cr.close();
		cw.close();
	}
}
