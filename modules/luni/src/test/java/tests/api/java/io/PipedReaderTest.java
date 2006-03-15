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

import java.io.PipedReader;
import java.io.PipedWriter;

public class PipedReaderTest extends junit.framework.TestCase {

	static class PWriter implements Runnable {
		public PipedWriter pw;

		public PWriter(PipedReader reader) {
			try {
				pw = new PipedWriter(reader);
			} catch (Exception e) {
				System.out.println("Couldn't create writer");
			}
		}

		public PWriter() {
			pw = new PipedWriter();
		}

		public void run() {
			try {
				char[] c = new char[11];
				"Hello World".getChars(0, 11, c, 0);
				pw.write(c);
				Thread.sleep(10000);
			} catch (InterruptedException e) {
			} catch (Exception e) {
				System.out.println("Exception occurred: " + e.toString());
			}
		}
	}

	PipedReader preader;

	PWriter pwriter;

	Thread t;

	/**
	 * @tests java.io.PipedReader#PipedReader()
	 */
	public void test_Constructor() {
		// Test for method java.io.PipedReader()
		// Used in test
	}

	/**
	 * @tests java.io.PipedReader#PipedReader(java.io.PipedWriter)
	 */
	public void test_ConstructorLjava_io_PipedWriter() {
		// Test for method java.io.PipedReader(java.io.PipedWriter)
		try {
			preader = new PipedReader(new PipedWriter());

		} catch (Exception e) {
			fail("Exception during constructor test: " + e.toString());
		}
	}

	/**
	 * @tests java.io.PipedReader#close()
	 */
	public void test_close() {
		// Test for method void java.io.PipedReader.close()
		char[] c = null;
		try {
			preader = new PipedReader();
			t = new Thread(new PWriter(preader), "");
			t.start();
			Thread.sleep(500); // Allow writer to start
			c = new char[11];
			preader.read(c, 0, 11);
			preader.close();
		} catch (Exception e) {
			fail("Exception during close test : " + e.getMessage());
		}
		assertTrue("Read incorrect chars", new String(c).equals("Hello World"));
	}

	/**
	 * @tests java.io.PipedReader#connect(java.io.PipedWriter)
	 */
	public void test_connectLjava_io_PipedWriter() {
		// Test for method void java.io.PipedReader.connect(java.io.PipedWriter)
		char[] c = null;
		try {
			preader = new PipedReader();
			t = new Thread(pwriter = new PWriter(), "");
			preader.connect(pwriter.pw);
			t.start();
			Thread.sleep(500); // Allow writer to start
			c = new char[11];
			preader.read(c, 0, 11);
		} catch (Exception e) {
			fail("Exception during connect test : " + e.getMessage());
		}
		assertTrue("Read incorrect chars", new String(c).equals("Hello World"));
		try {
			preader.connect(pwriter.pw);
		} catch (Exception e) {
			// Correct
			return;
		}
		fail(
				"Failed to throw exception connecting to pre-connected reader");
	}

	/**
	 * @tests java.io.PipedReader#read()
	 */
	public void test_read() {
		// Test for method int java.io.PipedReader.read()
		char[] c = null;
		try {
			preader = new PipedReader();
			t = new Thread(new PWriter(preader), "");
			t.start();
			Thread.sleep(500); // Allow writer to start
			c = new char[11];
			for (int i = 0; i < c.length; i++)
				c[i] = (char) preader.read();
		} catch (Exception e) {
			fail("Exception during read test : " + e.getMessage());
		}
		assertTrue("Read incorrect chars: " + new String(c), new String(c)
				.equals("Hello World"));
	}

	/**
	 * @tests java.io.PipedReader#read(char[], int, int)
	 */
	public void test_read$CII() {
		// Test for method int java.io.PipedReader.read(char [], int, int)
		char[] c = null;
		try {
			preader = new PipedReader();
			t = new Thread(new PWriter(preader), "");
			t.start();
			Thread.sleep(500); // Allow writer to start
			c = new char[11];
			int n = 0;
			int x = n;
			while (x < 11) {
				n = preader.read(c, x, 11 - x);
				x = x + n;
			}
		} catch (Exception e) {
			fail("Exception during read test : " + e.getMessage());
		}
		assertTrue("Read incorrect chars: " + new String(c), new String(c)
				.equals("Hello World"));
		try {
			preader.close();
			preader.read(c, 8, 7);
		} catch (Exception e) {
			// Correct
			return;
		}
		fail("Failed to throw exception reading from closed reader");
	}

	/**
	 * @tests java.io.PipedReader#ready()
	 */
	public void test_ready() {
		// Test for method boolean java.io.PipedReader.ready()
		char[] c = null;
		try {
			preader = new PipedReader();
			t = new Thread(new PWriter(preader), "");
			t.start();
			Thread.sleep(500); // Allow writer to start
			assertTrue("Reader should be ready", preader.ready());
			c = new char[11];
			for (int i = 0; i < c.length; i++)
				c[i] = (char) preader.read();
			assertTrue("Reader should not be ready after reading all chars",
					!preader.ready());
		} catch (Exception e) {
			fail("Exception during read test : " + e.getMessage());
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
		if (t != null)
			t.interrupt();
	}
}
