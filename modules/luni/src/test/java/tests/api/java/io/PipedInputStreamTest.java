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
import java.io.PipedInputStream;
import java.io.PipedOutputStream;

public class PipedInputStreamTest extends junit.framework.TestCase {

	static class PWriter implements Runnable {
		PipedOutputStream pos;

		public byte bytes[];

		public void run() {
			try {
				pos.write(bytes);
				synchronized (this) {
					notify();
				}
			} catch (IOException e) {
				e.printStackTrace(System.out);
				System.out.println("Could not write bytes");
			}
		}

		public PWriter(PipedOutputStream pout, int nbytes) {
			pos = pout;
			bytes = new byte[nbytes];
			for (int i = 0; i < bytes.length; i++)
				bytes[i] = (byte) (System.currentTimeMillis() % 9);
		}
	}

	Thread t;

	PWriter pw;

	PipedInputStream pis;

	PipedOutputStream pos;

	/**
	 * @tests java.io.PipedInputStream#PipedInputStream()
	 */
	public void test_Constructor() {
		// Test for method java.io.PipedInputStream()
		// Used in tests
	}

	/**
	 * @tests java.io.PipedInputStream#PipedInputStream(java.io.PipedOutputStream)
	 */
	public void test_ConstructorLjava_io_PipedOutputStream() {
		// Test for method java.io.PipedInputStream(java.io.PipedOutputStream)
		try {
			pis = new PipedInputStream(new PipedOutputStream());
			pis.available();
		} catch (Exception e) {
			fail("Exception during constructor test : " + e.getMessage());
		}
	}

	/**
	 * @tests java.io.PipedInputStream#available()
	 */
	public void test_available() {
		// Test for method int java.io.PipedInputStream.available()
		pis = new PipedInputStream();
		pos = new PipedOutputStream();
		try {
			pis.connect(pos);
			t = new Thread(pw = new PWriter(pos, 1000));
			t.start();
		} catch (IOException e) {
			fail("Exception during available test: " + e.toString());
		}
		try {
			synchronized (pw) {
				pw.wait(10000);
			}
			assertTrue("Available returned incorrect number of bytes: "
					+ pis.available(), pis.available() == 1000);
		} catch (IOException e) {
			fail("Exception getting available bytes : " + e.getMessage());
		} catch (InterruptedException e) {
			fail("InterruptedException getting available bytes : "
					+ e.getMessage());
		}

		try {
			PipedInputStream pin = new PipedInputStream();
			PipedOutputStream pout = new PipedOutputStream(pin);
			// We know the PipedInputStream buffer size is 1024.
			// Writing another byte would cause the write to wait
			// for a read before returning
			for (int i = 0; i < 1024; i++)
				pout.write(i);
			assertTrue("Incorrect available count: " + pin.available(), pin
					.available() == 1024);
		} catch (IOException e) {
			fail("Unexpected: " + e);
		}
	}

	/**
	 * @tests java.io.PipedInputStream#close()
	 */
	public void test_close() {
		// Test for method void java.io.PipedInputStream.close()
		pis = new PipedInputStream();
		pos = new PipedOutputStream();
		try {
			pis.connect(pos);
			pis.close();
			pos.write((byte) 127);
		} catch (IOException e) {
			// The spec for PipedInput saya an exception should be thrown if
			// a write is attempted to a closed input. The PipedOuput spec
			// indicates that an exception should be thrown only when the
			// piped input thread is terminated without closing
			return;
		}
		fail("Failed to throw expected exception");
	}

	/**
	 * @tests java.io.PipedInputStream#connect(java.io.PipedOutputStream)
	 */
	public void test_connectLjava_io_PipedOutputStream() {
		// Test for method void
		// java.io.PipedInputStream.connect(java.io.PipedOutputStream)
		pis = new PipedInputStream();
		pos = new PipedOutputStream();
		try {
			assertTrue("Non-conected pipe returned non-zero available bytes",
					pis.available() == 0);
		} catch (IOException e) {
			fail("IOException getting avail bytes : " + e.getMessage());
		}
		try {
			pis.connect(pos);
			t = new Thread(pw = new PWriter(pos, 1000));
			t.start();
		} catch (IOException e) {
			fail("Exception during connect test : " + e.getMessage());
		}
		try {
			synchronized (pw) {
				pw.wait(10000);
			}
			assertTrue("Available returned incorrect number of bytes: "
					+ pis.available(), pis.available() == 1000);
		} catch (IOException e) {
			fail("Exception getting available bytes : " + e.getMessage());
		} catch (InterruptedException e) {
			fail("InterruptedException getting available bytes : "
					+ e.getMessage());
		}
	}

	/**
	 * @tests java.io.PipedInputStream#read()
	 */
	public void test_read() {
		// Test for method int java.io.PipedInputStream.read()
		pis = new PipedInputStream();
		pos = new PipedOutputStream();
		try {
			pis.connect(pos);
			t = new Thread(pw = new PWriter(pos, 1000));
			t.start();
		} catch (IOException e) {
			fail("Exception during read test : " + e.getMessage());
		}
		try {
			synchronized (pw) {
				pw.wait(10000);
			}
			assertTrue("Available returned incorrect number of bytes: "
					+ pis.available(), pis.available() == 1000);
			assertTrue("read returned incorrect byte",
					((byte) pis.read()) == pw.bytes[0]);
		} catch (IOException e) {
			fail("Exception reading byte : " + e.getMessage());
		} catch (InterruptedException e) {
			fail("InterruptedException getting available bytes : "
					+ e.getMessage());
		}
	}

	/**
	 * @tests java.io.PipedInputStream#read(byte[], int, int)
	 */
	public void test_read$BII() {
		// Test for method int java.io.PipedInputStream.read(byte [], int, int)
		pis = new PipedInputStream();
		pos = new PipedOutputStream();
		try {
			pis.connect(pos);
			t = new Thread(pw = new PWriter(pos, 1000));
			t.start();
		} catch (IOException e) {
			fail("Exception during read test : " + e.getMessage());
		}
		try {
			byte[] buf = new byte[400];
			synchronized (pw) {
				pw.wait(10000);
			}
			assertTrue("Available returned incorrect number of bytes: "
					+ pis.available(), pis.available() == 1000);
			pis.read(buf, 0, 400);
			for (int i = 0; i < 400; i++)
				assertTrue("read returned incorrect byte[]",
						buf[i] == pw.bytes[i]);
		} catch (IOException e) {
			fail("Exception reading byte[] : " + e.getMessage());
		} catch (InterruptedException e) {
			fail("InterruptedException getting available bytes : "
					+ e.getMessage());
		}
	}

    /**
     * @tests java.io.PipedInputStream#read(byte[], int, int)
     * Regression for HARMONY-387
     */
    public void test_read$BII_2() throws IOException {
        PipedInputStream obj = new PipedInputStream();
        try {
            obj.read(new byte[0], 0, -1);
            fail("IndexOutOfBoundsException expected");
        } catch (IndexOutOfBoundsException t) {
            assertEquals(
                    "IndexOutOfBoundsException rather than a subclass expected",
                    IndexOutOfBoundsException.class, t.getClass());
        }
    }

    /**
     * @tests java.io.PipedInputStream#read(byte[], int, int)
     */
    public void test_read$BII_3() {
        PipedInputStream obj = new PipedInputStream();
        try {
            obj.read(new byte[0], -1, 0);
            fail("IndexOutOfBoundsException expected");
        } catch (ArrayIndexOutOfBoundsException t) {
            fail("IndexOutOfBoundsException expected");
        } catch (IndexOutOfBoundsException t) {
        } catch (IOException t) {
            fail("Unexpected IOException: " + t.getMessage());
        }
    }

    /**
     * @tests java.io.PipedInputStream#read(byte[], int, int)
     */
    public void test_read$BII_4() {
        PipedInputStream obj = new PipedInputStream();
        try {
            obj.read(new byte[0], -1, -1);
            fail("IndexOutOfBoundsException expected");
        } catch (ArrayIndexOutOfBoundsException t) {
            fail("IndexOutOfBoundsException expected");
        } catch (IndexOutOfBoundsException t) {
        } catch (IOException t) {
            fail("Unexpected IOException: " + t.getMessage());
        }
    }

    /**
	 * @tests java.io.PipedInputStream#receive(int)
	 */
	public void test_receive() {
		pis = new PipedInputStream();
		pos = new PipedOutputStream();

		// test if writer recognizes dead reader
		try {
			pis.connect(pos);
			class WriteRunnable implements Runnable {
				boolean pass = false;

				boolean readerAlive = true;

				public void run() {
					try {
						pos.write(1);
						while (readerAlive)
							;
						try {
							// should throw exception since reader thread
							// is now dead
							pos.write(1);
						} catch (IOException e) {
							pass = true;
						}
					} catch (IOException e) {
					}
				}
			}
			WriteRunnable writeRunnable = new WriteRunnable();
			Thread writeThread = new Thread(writeRunnable);
			class ReadRunnable implements Runnable {
				boolean pass;

				public void run() {
					try {
						int one = pis.read();
						pass = true;
					} catch (IOException e) {
					}
				}
			}
			;
			ReadRunnable readRunnable = new ReadRunnable();
			Thread readThread = new Thread(readRunnable);
			writeThread.start();
			readThread.start();
			while (readThread.isAlive())
				;
			writeRunnable.readerAlive = false;
			assertTrue("reader thread failed to read", readRunnable.pass);
			while (writeThread.isAlive())
				;
			assertTrue("writer thread failed to recognize dead reader",
					writeRunnable.pass);
		} catch (IOException e) {
			fail("Exception during receive test: " + e.toString());
		}

		// attempt to write to stream after writer closed
		pis = new PipedInputStream();
		pos = new PipedOutputStream();

		try {
			pis.connect(pos);
			class MyRunnable implements Runnable {
				boolean pass;

				public void run() {
					try {
						pos.write(1);
					} catch (IOException e) {
						pass = true;
					}
				}
			}
			MyRunnable myRun = new MyRunnable();
			synchronized (pis) {
				t = new Thread(myRun);
				// thread t will be blocked inside pos.write(1)
				// when it tries to call the synchronized method pis.receive
				// because we hold the monitor for object pis
				t.start();
				try {
					// wait for thread t to get to the call to pis.receive
					Thread.sleep(100);
				} catch (InterruptedException e) {
				}
				// now we close
				pos.close();
			}
			// we have exited the synchronized block, so now thread t will make
			// a call to pis.receive AFTER the output stream was closed,
			// in which case an IOException should be thrown
			while (t.isAlive()) {
				;
			}
			assertTrue(
					"write failed to throw IOException on closed PipedOutputStream",
					myRun.pass);
		} catch (IOException e) {
			fail("Exception during second part of receive test: "
					+ e.toString());
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
		try {
			if (t != null)
				t.interrupt();
		} catch (Exception e) {
		}
	}
}
