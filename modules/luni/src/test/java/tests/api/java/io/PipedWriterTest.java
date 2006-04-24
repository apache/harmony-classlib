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

import java.io.IOException;
import java.io.PipedReader;
import java.io.PipedWriter;

public class PipedWriterTest extends junit.framework.TestCase {

	static class PReader implements Runnable {
		public PipedReader pr;

		public char[] buf = new char[10];

		public PReader(PipedWriter pw) {
			try {
				pr = new PipedReader(pw);
			} catch (IOException e) {
				System.out.println("Exception setting up reader: "
						+ e.toString());
			}
		}

		public PReader(PipedReader pr) {
			this.pr = pr;
		}

		public void run() {
			try {
				int r = 0;
				for (int i = 0; i < buf.length; i++) {
					r = pr.read();
					if (r == -1)
						break;
					buf[i] = (char) r;
				}
			} catch (Exception e) {
				System.out.println("Exception reading ("
						+ Thread.currentThread().getName() + "): "
						+ e.toString());
			}
		}
	}

	Thread rdrThread;

	PReader reader;

	PipedWriter pw;

	/**
	 * @tests java.io.PipedWriter#PipedWriter()
	 */
	public void test_Constructor() {
		// Test for method java.io.PipedWriter()
		// Used in tests
	}

	/**
	 * @tests java.io.PipedWriter#PipedWriter(java.io.PipedReader)
	 */
	public void test_ConstructorLjava_io_PipedReader() {
		// Test for method java.io.PipedWriter(java.io.PipedReader)
		try {
			char[] buf = new char[10];
			"HelloWorld".getChars(0, 10, buf, 0);
			PipedReader rd = new PipedReader();
			pw = new PipedWriter(rd);
			rdrThread = new Thread(reader = new PReader(rd),
					"Constructor(Reader)");
			rdrThread.start();
			pw.write(buf);
			pw.close();
			rdrThread.join(500);
		} catch (Exception e) {
			fail("Exception during constructor test: " + e.toString());
		}
		assertTrue("Failed to construct writer", "HelloWorld"
				.equals(new String(reader.buf)));
	}

	/**
	 * @tests java.io.PipedWriter#close()
	 */
	public void test_close() {
		// Test for method void java.io.PipedWriter.close()
		char[] buf = new char[10];
		try {
			"HelloWorld".getChars(0, 10, buf, 0);
			PipedReader rd = new PipedReader();
			pw = new PipedWriter(rd);
			reader = new PReader(rd);
			pw.close();
		} catch (Exception e) {
			fail("Exception during close test : " + e.getMessage());
		}
		try {
			pw.write(buf);
		} catch (Exception e) {
			// correct
			return;
		}
		fail(
				"Should have thrown exception when attempting to write to closed writer.");

	}

	/**
	 * @tests java.io.PipedWriter#connect(java.io.PipedReader)
	 */
	public void test_connectLjava_io_PipedReader() {
		// Test for method void java.io.PipedWriter.connect(java.io.PipedReader)
		try {
			char[] buf = new char[10];
			"HelloWorld".getChars(0, 10, buf, 0);
			PipedReader rd = new PipedReader();
			pw = new PipedWriter();
			pw.connect(rd);
			rdrThread = new Thread(reader = new PReader(rd), "connect");
			rdrThread.start();
			pw.write(buf);
			pw.close();
			rdrThread.join(500);
		} catch (Exception e) {
			fail("Exception during write test : " + e.getMessage());
		}
		assertTrue("Failed to write correct chars", "HelloWorld"
				.equals(new String(reader.buf)));
	}

	/**
	 * @tests java.io.PipedWriter#flush()
	 */
	public void test_flush() {
		// Test for method void java.io.PipedWriter.flush()
		try {
			char[] buf = new char[10];
			"HelloWorld".getChars(0, 10, buf, 0);
			pw = new PipedWriter();
			rdrThread = new Thread(reader = new PReader(pw), "flush");
			rdrThread.start();
			pw.write(buf);
			pw.flush();
			rdrThread.join(700);
		} catch (Exception e) {
			fail("Exception during flush test : " + e.getMessage());
		}
		assertTrue("Failed to flush chars", "HelloWorld".equals(new String(
				reader.buf)));
	}

	/**
	 * @tests java.io.PipedWriter#write(char[], int, int)
	 */
	public void test_write$CII() {
		// Test for method void java.io.PipedWriter.write(char [], int, int)
		try {
			char[] buf = new char[10];
			"HelloWorld".getChars(0, 10, buf, 0);
			pw = new PipedWriter();
			rdrThread = new Thread(reader = new PReader(pw), "writeCII");
			rdrThread.start();
			pw.write(buf, 0, 10);
			pw.close();
			rdrThread.join(1000);
		} catch (Exception e) {
			fail("Exception during write test : " + e.getMessage());
		}
		assertTrue("Failed to write correct chars", "HelloWorld"
				.equals(new String(reader.buf)));
	}

    /**
     * @tests java.io.PipedWriter#write(char[], int, int)
     * Regression for HARMONY-387
     */
    public void test_write$CII_2() throws IOException {
        PipedReader pr = new PipedReader();
        PipedWriter obj = null;
        try {
            obj = new java.io.PipedWriter(pr);
            obj.write(new char[0], (int) 0, (int) -1);
            fail("IndexOutOfBoundsException expected");
        } catch (IndexOutOfBoundsException t) {
            assertEquals(
                    "IndexOutOfBoundsException rather than a subclass expected",
                    IndexOutOfBoundsException.class, t.getClass());
        }
    }

    /**
     * @tests java.io.PipedWriter#write(char[], int, int)
     */
    public void test_write$CII_3() {
        PipedReader pr = new PipedReader();
        PipedWriter obj = null;
        try {
            obj = new java.io.PipedWriter(pr);
            obj.write(new char[0], (int) -1, (int) 0);
            fail("IndexOutOfBoundsException expected");
        } catch (ArrayIndexOutOfBoundsException t) {
            fail("IndexOutOfBoundsException expected");
        } catch (IndexOutOfBoundsException t) {
        } catch (IOException e) {
            fail("Unexpected IOException: " + e.getMessage());
        }
    }

    /**
     * @tests java.io.PipedWriter#write(char[], int, int)
     */
    public void test_write$CII_4() {
        PipedReader pr = new PipedReader();
        PipedWriter obj = null;
        try {
            obj = new java.io.PipedWriter(pr);
            obj.write(new char[0], (int) -1, (int) -1);
            fail("IndexOutOfBoundsException expected");
        } catch (ArrayIndexOutOfBoundsException t) {
            fail("IndexOutOfBoundsException expected");
        } catch (IndexOutOfBoundsException t) {
        } catch (IOException e) {
            fail("Unexpected IOException: " + e.getMessage());
        }
    }

    /**
     * @tests java.io.PipedWriter#write(char[], int, int)
     */
    public void test_write$CII_5() {
        PipedReader pr = new PipedReader();
        PipedWriter obj = null;
        try {
            obj = new PipedWriter(pr);
            obj.write((char[]) null, (int) -1, (int) 0);
            fail("NullPointerException expected");
        } catch (IndexOutOfBoundsException t) {
            fail("NullPointerException expected");
        } catch (NullPointerException t) {
        } catch (IOException e) {
            fail("Unexpected IOException: " + e.getMessage());
        }
    }

    /**
     * @tests java.io.PipedWriter#write(char[], int, int)
     */
    public void test_write$CII_6() {
        PipedReader pr = new PipedReader();
        PipedWriter obj = null;
        try {
            obj = new PipedWriter(pr);
            obj.write((char[]) null, (int) -1, (int) -1);
            fail("NullPointerException expected");
        } catch (IndexOutOfBoundsException t) {
            fail("NullPointerException expected");
        } catch (NullPointerException t) {
        } catch (IOException e) {
            fail("Unexpected IOException: " + e.getMessage());
        }
    }

    /**
	 * @tests java.io.PipedWriter#write(int)
	 */
	public void test_writeI() {
		// Test for method void java.io.PipedWriter.write(int)
		try {
			pw = new PipedWriter();
			rdrThread = new Thread(reader = new PReader(pw), "writeI");
			rdrThread.start();
			pw.write(1);
			pw.write(2);
			pw.write(3);
			pw.close();
			rdrThread.join(1000);
		} catch (Exception e) {
			fail("Exception during write test : " + e.getMessage());
		}
		assertTrue("Failed to write correct chars: " + (int) reader.buf[0]
				+ " " + (int) reader.buf[1] + " " + (int) reader.buf[2],
				reader.buf[0] == 1 && reader.buf[1] == 2 && reader.buf[2] == 3);
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
		try {
			if (rdrThread != null)
				rdrThread.interrupt();
		} catch (Exception e) {
		}
		try {
			if (pw != null)
				pw.close();
		} catch (Exception e) {
		}
	}
}
