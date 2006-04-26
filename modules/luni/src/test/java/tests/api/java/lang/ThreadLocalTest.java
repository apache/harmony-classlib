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

public class ThreadLocalTest extends junit.framework.TestCase {

	/**
	 * @tests java.lang.ThreadLocal#ThreadLocal()
	 */
	public void test_Constructor() {
		// Test for method java.lang.ThreadLocal()

		ThreadLocal l = new ThreadLocal();
		assertTrue("Failed to create ThreadLocal", l instanceof ThreadLocal);

	}

	/**
	 * @tests java.lang.ThreadLocal#get()
	 */
	public void test_get() {
		// Test for method java.lang.Object java.lang.ThreadLocal.get()
		ThreadLocal l = new ThreadLocal();
		assertNull("ThreadLocal's initial value is null", l.get());

		// The ThreadLocal has to run once for each thread that touches the
		// ThreadLocal
		final Object INITIAL_VALUE = "'foo'";
		final ThreadLocal l1 = new ThreadLocal() {
			protected Object initialValue() {
				return INITIAL_VALUE;
			}
		};

		assertTrue("ThreadLocal's initial value should be " + INITIAL_VALUE
				+ " but is " + l1.get(), l1.get() == INITIAL_VALUE);

		// We need this because inner types cannot assign to variables in
		// container method. But assigning to object slots in the container
		// method is ok.
		class ResultSlot {
			public Object result = null;
		}

		final ResultSlot THREADVALUE = new ResultSlot();
		Thread t = new Thread() {
			public void run() {
				THREADVALUE.result = l1.get();
			}
		};

		// Wait for the other Thread assign what it observes as the value of the
		// variable
		t.start();
		try {
			t.join();
		} catch (InterruptedException ie) {
			fail("Interrupted!!");
		}

		assertTrue("ThreadLocal's initial value in other Thread should be "
				+ INITIAL_VALUE, THREADVALUE.result == INITIAL_VALUE);

	}

	/**
	 * @tests java.lang.ThreadLocal#set(java.lang.Object)
	 */
	public void test_setLjava_lang_Object() {
		// Test for method void java.lang.ThreadLocal.set(java.lang.Object)

		final Object OBJ = new Object();
		final ThreadLocal l = new ThreadLocal();
		l.set(OBJ);
		assertTrue("ThreadLocal's initial value is " + OBJ, l.get() == OBJ);

		// We need this because inner types cannot assign to variables in
		// container method.
		// But assigning to object slots in the container method is ok.
		class ResultSlot {
			public Object result = null;
		}

		final ResultSlot THREADVALUE = new ResultSlot();
		Thread t = new Thread() {
			public void run() {
				THREADVALUE.result = l.get();
			}
		};

		// Wait for the other Thread assign what it observes as the value of the
		// variable
		t.start();
		try {
			t.join();
		} catch (InterruptedException ie) {
			fail("Interrupted!!");
		}

		// ThreadLocal is not inherited, so the other Thread should see it as
		// null
		assertNull("ThreadLocal's value in other Thread should be null",
				THREADVALUE.result);

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
	}
}
