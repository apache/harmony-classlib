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

package tests.api.java.util;

import java.util.Observable;
import java.util.Observer;
import java.util.Vector;

public class ObservableTest extends junit.framework.TestCase {

	static class TestObserver implements Observer {
		public Vector objv = new Vector();

		int updateCount = 0;

		public void update(Observable observed, Object arg) {
			++updateCount;
			objv.add(arg);
		}

		public int updateCount() {
			return updateCount;
		}

	}

	static class DeleteTestObserver implements Observer {
		int updateCount = 0;

		boolean deleteAll = false;

		public DeleteTestObserver(boolean all) {
			deleteAll = all;
		}

		public void update(Observable observed, Object arg) {
			++updateCount;
			if (deleteAll)
				observed.deleteObservers();
			else
				observed.deleteObserver(this);
		}

		public int updateCount() {
			return updateCount;
		}

	}

	static class TestObservable extends Observable {
		public void doChange() {
			setChanged();
		}
	}

	Observer observer;

	TestObservable observable;

	/**
	 * @tests java.util.Observable#Observable()
	 */
	public void test_Constructor() {
		// Test for method java.util.Observable()
		try {
			Observable ov = new Observable();
			assertTrue("Wrong initial values.", !ov.hasChanged());
			assertTrue("Wrong initial values.", ov.countObservers() == 0);
		} catch (Exception e) {
			fail("Exception during test : " + e.getMessage());
		}
	}

	/**
	 * @tests java.util.Observable#addObserver(java.util.Observer)
	 */
	public void test_addObserverLjava_util_Observer() {
		// Test for method void
		// java.util.Observable.addObserver(java.util.Observer)
		TestObserver test = new TestObserver();
		observable.addObserver(test);
		assertTrue("Failed to add observer", observable.countObservers() == 1);
		observable.addObserver(test);
		assertTrue("Duplicate observer", observable.countObservers() == 1);

		Observable o = new Observable();
		try {
			o.addObserver(null);
			fail("Expected adding a null observer to throw a NPE.");
		} catch (NullPointerException ex) {
			// expected;
		} catch (Throwable ex) {
			fail("Did not expect adding a new observer to throw a "
					+ ex.getClass().getName());
		}
	}

	/**
	 * @tests java.util.Observable#countObservers()
	 */
	public void test_countObservers() {
		// Test for method int java.util.Observable.countObservers()
		assertTrue("New observable had > 0 observers", observable
				.countObservers() == 0);
		observable.addObserver(new TestObserver());
		assertTrue("Observable with observer returned other than 1", observable
				.countObservers() == 1);
	}

	/**
	 * @tests java.util.Observable#deleteObserver(java.util.Observer)
	 */
	public void test_deleteObserverLjava_util_Observer() {
		// Test for method void
		// java.util.Observable.deleteObserver(java.util.Observer)
		observable.addObserver(observer = new TestObserver());
		observable.deleteObserver(observer);
		assertTrue("Failed to delete observer",
				observable.countObservers() == 0);

	}

	/**
	 * @tests java.util.Observable#deleteObservers()
	 */
	public void test_deleteObservers() {
		// Test for method void java.util.Observable.deleteObservers()
		observable.addObserver(new TestObserver());
		observable.addObserver(new TestObserver());
		observable.addObserver(new TestObserver());
		observable.addObserver(new TestObserver());
		observable.addObserver(new TestObserver());
		observable.addObserver(new TestObserver());
		observable.addObserver(new TestObserver());
		observable.addObserver(new TestObserver());
		observable.deleteObservers();
		assertTrue("Failed to delete observers",
				observable.countObservers() == 0);
	}

	/**
	 * @tests java.util.Observable#hasChanged()
	 */
	public void test_hasChanged() {
		// TODO : Implement test
	}

	/**
	 * @tests java.util.Observable#notifyObservers()
	 */
	public void test_notifyObservers() {
		// Test for method void java.util.Observable.notifyObservers()
		observable.addObserver(observer = new TestObserver());
		observable.notifyObservers();
		assertTrue("Notified when unchnaged", ((TestObserver) observer)
				.updateCount() == 0);
		((TestObservable) observable).doChange();
		observable.notifyObservers();
		assertTrue("Failed to notify",
				((TestObserver) observer).updateCount() == 1);

		DeleteTestObserver observer1, observer2;
		observable.deleteObservers();
		observable.addObserver(observer1 = new DeleteTestObserver(false));
		observable.addObserver(observer2 = new DeleteTestObserver(false));
		observable.doChange();
		observable.notifyObservers();
		assertTrue("Failed to notify all", observer1.updateCount() == 1
				&& observer2.updateCount() == 1);
		assertTrue("Failed to delete all", observable.countObservers() == 0);

		observable.addObserver(observer1 = new DeleteTestObserver(false));
		observable.addObserver(observer2 = new DeleteTestObserver(false));
		observable.doChange();
		observable.notifyObservers();
		assertTrue("Failed to notify all 2", observer1.updateCount() == 1
				&& observer2.updateCount() == 1);
		assertTrue("Failed to delete all 2", observable.countObservers() == 0);
	}

	/**
	 * @tests java.util.Observable#notifyObservers(java.lang.Object)
	 */
	public void test_notifyObserversLjava_lang_Object() {
		// Test for method void
		// java.util.Observable.notifyObservers(java.lang.Object)
		Object obj;
		observable.addObserver(observer = new TestObserver());
		observable.notifyObservers();
		assertTrue("Notified when unchanged", ((TestObserver) observer)
				.updateCount() == 0);
		((TestObservable) observable).doChange();
		observable.notifyObservers(obj = new Object());
		assertTrue("Failed to notify",
				((TestObserver) observer).updateCount() == 1);
		assertTrue("Failed to pass Object arg", ((TestObserver) observer).objv
				.elementAt(0).equals(obj));
	}

	/**
	 * Sets up the fixture, for example, open a network connection. This method
	 * is called before a test is executed.
	 */
	protected void setUp() {
		observable = new TestObservable();
	}

	/**
	 * Tears down the fixture, for example, close a network connection. This
	 * method is called after a test is executed.
	 */
	protected void tearDown() {
	}
}
