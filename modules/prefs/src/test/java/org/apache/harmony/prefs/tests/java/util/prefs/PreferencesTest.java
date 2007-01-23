/* Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.harmony.prefs.tests.java.util.prefs;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.util.prefs.AbstractPreferences;
import java.util.prefs.BackingStoreException;
import java.util.prefs.InvalidPreferencesFormatException;
import java.util.prefs.NodeChangeListener;
import java.util.prefs.PreferenceChangeListener;
import java.util.prefs.Preferences;

import junit.framework.TestCase;

/**
 * 
 */
public class PreferencesTest extends TestCase {

	MockSecurityManager manager = new MockSecurityManager();

	MockInputStream stream = null;

	InputStream in;

	/*
	 * @see TestCase#setUp()
	 */
	protected void setUp() throws Exception {
		super.setUp();
		in = new ByteArrayInputStream("<!DOCTYPE preferences SYSTEM \"http://java.sun.com/dtd/preferences.dtd\"><preferences><root type=\"user\"><map></map></root></preferences>".getBytes("UTF-8"));
		stream = new MockInputStream(in);
	}

	/*
	 * @see TestCase#tearDown()
	 */
	protected void tearDown() throws Exception {
		super.tearDown();
		stream.close();
	}

	public void testSystemNodeForPackage() throws BackingStoreException {
		Preferences p = null;
        try {
            p = Preferences.systemNodeForPackage(Object.class);
        } catch (SecurityException e) {
            // may be caused by absence of privileges on the underlying OS 
            return;
        }
		assertEquals("/java/lang", p.absolutePath());
		assertTrue(p instanceof AbstractPreferences);
		Preferences root = Preferences.systemRoot();
		Preferences parent = root.node("java");
		assertSame(parent, p.parent());
		assertFalse(p.isUserNode());
		assertEquals("lang", p.name());
		assertEquals("System Preference Node: " + p.absolutePath(), p
				.toString());
        try {
            assertEquals(0, p.childrenNames().length);
        } catch (BackingStoreException e) {
            // could be thrown according to specification
        }
        try {
            assertEquals(0, p.keys().length);
        } catch (BackingStoreException e) {
            // could be thrown according to specification
        }
        try {
            parent.removeNode();
        } catch (BackingStoreException e) {
            // could be thrown according to specification
        }
        try {
            p = Preferences.userNodeForPackage(null);
            fail("NullPointerException has not been thrown");
        } catch (NullPointerException e) {
            // expected
        }
	}

	public void testSystemRoot() throws BackingStoreException {
		Preferences p = Preferences.systemRoot();
		assertTrue(p instanceof AbstractPreferences);
		assertEquals("/", p.absolutePath());
		assertSame(null, p.parent());
		assertFalse(p.isUserNode());
		assertEquals("", p.name());
		assertEquals("System Preference Node: " + p.absolutePath(), p
				.toString());
		// assertEquals(0, p.childrenNames().length);
		// assertEquals(0, p.keys().length);
	}

	public void testConsts() {
		assertEquals(80, Preferences.MAX_KEY_LENGTH);
		assertEquals(80, Preferences.MAX_NAME_LENGTH);
		assertEquals(8192, Preferences.MAX_VALUE_LENGTH);
	}

	public void testUserNodeForPackage() throws BackingStoreException {
		Preferences p = Preferences.userNodeForPackage(Object.class);
		assertEquals("/java/lang", p.absolutePath());
		assertTrue(p instanceof AbstractPreferences);
		Preferences root = Preferences.userRoot();
		Preferences parent = root.node("java");
		assertSame(parent, p.parent());
		assertTrue(p.isUserNode());
		assertEquals("lang", p.name());
		assertEquals("User Preference Node: " + p.absolutePath(), p.toString());
		assertEquals(0, p.childrenNames().length);
		assertEquals(0, p.keys().length);

		try {
			p = Preferences.userNodeForPackage(null);
			fail();
		} catch (NullPointerException e) {
		}
	}

	public void testUserRoot() throws BackingStoreException {
		Preferences p = Preferences.userRoot();
		assertTrue(p instanceof AbstractPreferences);
		assertEquals("/", p.absolutePath());
		assertSame(null, p.parent());
		assertTrue(p.isUserNode());
		assertEquals("", p.name());
		assertEquals("User Preference Node: " + p.absolutePath(), p.toString());
		// assertEquals(0, p.childrenNames().length);
		// assertEquals(p.keys().length, 0);
	}

	public void testImportPreferences() throws Exception {
		Preferences prefs = null;
		try {
			prefs = Preferences.userNodeForPackage(PreferencesTest.class);
			// assertEquals(0, prefs.childrenNames().length);
			// assertFalse(prefs.nodeExists("mock/child/grandson"));

			prefs.put("prefskey", "oldvalue");
			prefs.put("prefskey2", "oldvalue2");
			in = PreferencesTest.class.getResourceAsStream("/prefs/java/util/prefs/userprefs.xml");
			Preferences.importPreferences(in);

			prefs = Preferences.userNodeForPackage(PreferencesTest.class);
			assertEquals(1, prefs.childrenNames().length);
			assertTrue(prefs.nodeExists("mock/child/grandson"));
			assertEquals("newvalue", prefs.get("prefskey", null));
			assertEquals("oldvalue2", prefs.get("prefskey2", null));
			assertEquals("newvalue3", prefs.get("prefskey3", null));

			in = PreferencesTest.class
					.getResourceAsStream("/prefs/java/util/prefs/userprefs-badform.xml");
			try {
				Preferences.importPreferences(in);
				fail();
			} catch (InvalidPreferencesFormatException e) {
			}

			in = PreferencesTest.class
					.getResourceAsStream("/prefs/java/util/prefs/userprefs-badtype.xml");
			try {
				Preferences.importPreferences(in);
				fail();
			} catch (InvalidPreferencesFormatException e) {
			}

			in = PreferencesTest.class
					.getResourceAsStream("/prefs/java/util/prefs/userprefs-badencoding.xml");
			try {
				Preferences.importPreferences(in);
				fail();
			} catch (InvalidPreferencesFormatException e) {
			}

			in = PreferencesTest.class
					.getResourceAsStream("/prefs/java/util/prefs/userprefs-higherversion.xml");
			try {
				Preferences.importPreferences(in);
				fail();
			} catch (InvalidPreferencesFormatException e) {
			}

			in = PreferencesTest.class
					.getResourceAsStream("/prefs/java/util/prefs/userprefs-ascii.xml");
			Preferences.importPreferences(in);
			prefs = Preferences.userNodeForPackage(PreferencesTest.class);
		} finally {
			try {
				prefs = Preferences.userRoot().node("tests");
				prefs.removeNode();
			} catch (Exception e) {
			}
		}
	}

	public void testImportPreferencesException() throws Exception {
		try {
			Preferences.importPreferences(null);
			fail();
		} catch (MalformedURLException e) {
		}

		byte[] source = new byte[0];
		InputStream in = new ByteArrayInputStream(source);
		try {
			Preferences.importPreferences(in);
			fail();
		} catch (InvalidPreferencesFormatException e) {
		}

		stream.setResult(MockInputStream.exception);
		try {
			Preferences.importPreferences(stream);
			fail();
		} catch (IOException e) {
		}

		stream.setResult(MockInputStream.runtimeException);
		try {
			Preferences.importPreferences(stream);
			fail();
		} catch (RuntimeException e) {
		}
	}

	public void testSecurity() throws InvalidPreferencesFormatException,
			IOException {
		try {
			manager.install();
			try {
				Preferences.userRoot();
				fail();
			} catch (SecurityException e) {
			}
			try {
				Preferences.systemRoot();
				fail();
			} catch (SecurityException e) {
			}
			try {
				Preferences.userNodeForPackage(null);
				fail();
			} catch (SecurityException e) {
			}

			try {
				Preferences.systemNodeForPackage(null);
				fail();
			} catch (SecurityException e) {
			}

			try {
				Preferences.importPreferences(stream);
				fail();
			} catch (SecurityException e) {
			}
		} finally {
			manager.restoreDefault();
		}
	}

	public void testAbstractMethods() {
		Preferences p = new MockPreferences();
		p.absolutePath();
		try {
			p.childrenNames();
		} catch (BackingStoreException e4) {
		}
		try {
			p.clear();
		} catch (BackingStoreException e5) {
		}
		try {
			p.exportNode(null);
		} catch (IOException e6) {
		} catch (BackingStoreException e6) {
		}
		try {
			p.exportSubtree(null);
		} catch (IOException e7) {
		} catch (BackingStoreException e7) {
		}
		try {
			p.flush();
		} catch (BackingStoreException e8) {
		}
		p.get(null, null);
		p.getBoolean(null, false);
		p.getByteArray(null, null);
		p.getFloat(null, 0.1f);
		p.getDouble(null, 0.1);
		p.getInt(null, 1);
		p.getLong(null, 1l);
		p.isUserNode();
		try {
			p.keys();
		} catch (BackingStoreException e) {
		}
		p.name();
		p.node(null);
		try {
			p.nodeExists(null);
		} catch (BackingStoreException e1) {
		}
		p.parent();
		p.put(null, null);
		p.putBoolean(null, false);
		p.putByteArray(null, null);
		p.putDouble(null, 1);
		p.putFloat(null, 1f);
		p.putInt(null, 1);
		p.putLong(null, 1l);
		p.remove(null);
		try {
			p.removeNode();
		} catch (BackingStoreException e2) {
		}
		p.addNodeChangeListener(null);
		p.addPreferenceChangeListener(null);
		p.removeNodeChangeListener(null);
		p.removePreferenceChangeListener(null);
		try {
			p.sync();
		} catch (BackingStoreException e3) {
		}
		p.toString();
	}

	static class MockInputStream extends InputStream {

		static final int normal = 0;

		static final int exception = 1;

		static final int runtimeException = 2;

		int result = normal;

		InputStream wrapper;

		public void setResult(int i) {
			result = i;
		}

		private void checkException() throws IOException {
			switch (result) {
			case normal:
				return;
			case exception:
				throw new IOException("test");
			case runtimeException:
				throw new RuntimeException("test");
			}
		}

		public MockInputStream(InputStream in) {
			wrapper = in;
		}

		public int read() throws IOException {
			checkException();
			return wrapper.read();
		}
	}

	static class MockPreferences extends Preferences {

		public MockPreferences() {
			super();
		}

		public String absolutePath() {
			return null;
		}

		public String[] childrenNames() throws BackingStoreException {
			return null;
		}

		public void clear() throws BackingStoreException {
		}

		public void exportNode(OutputStream ostream) throws IOException,
				BackingStoreException {
		}

		public void exportSubtree(OutputStream ostream) throws IOException,
				BackingStoreException {
		}

		public void flush() throws BackingStoreException {
		}

		public String get(String key, String deflt) {
			return null;
		}

		public boolean getBoolean(String key, boolean deflt) {
			return false;
		}

		public byte[] getByteArray(String key, byte[] deflt) {
			return null;
		}

		public double getDouble(String key, double deflt) {
			return 0;
		}

		public float getFloat(String key, float deflt) {
			return 0;
		}

		public int getInt(String key, int deflt) {
			return 0;
		}

		public long getLong(String key, long deflt) {
			return 0;
		}

		public boolean isUserNode() {
			return false;
		}

		public String[] keys() throws BackingStoreException {
			return null;
		}

		public String name() {
			return null;
		}

		public Preferences node(String name) {
			return null;
		}

		public boolean nodeExists(String name) throws BackingStoreException {
			return false;
		}

		public Preferences parent() {
			return null;
		}

		public void put(String key, String value) {

		}

		public void putBoolean(String key, boolean value) {

		}

		public void putByteArray(String key, byte[] value) {

		}

		public void putDouble(String key, double value) {

		}

		public void putFloat(String key, float value) {

		}

		public void putInt(String key, int value) {

		}

		public void putLong(String key, long value) {

		}

		public void remove(String key) {

		}

		public void removeNode() throws BackingStoreException {

		}

		public void addNodeChangeListener(NodeChangeListener ncl) {

		}

		public void addPreferenceChangeListener(PreferenceChangeListener pcl) {

		}

		public void removeNodeChangeListener(NodeChangeListener ncl) {

		}

		public void removePreferenceChangeListener(PreferenceChangeListener pcl) {

		}

		public void sync() throws BackingStoreException {

		}

		public String toString() {
			return null;
		}

	}

}

