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

package tests.api.java.util.prefs;

import java.io.NotSerializableException;
import java.util.prefs.PreferenceChangeEvent;
import java.util.prefs.Preferences;

import tests.util.SerializationTester;

import junit.framework.TestCase;

/**
 * 
 */
public class PreferenceChangeEventTest extends TestCase {

	PreferenceChangeEvent event;

	public void testPreferenceChangeEventException() {
		try {
			event = new PreferenceChangeEvent(null, "key", "value");
			fail();
		} catch (IllegalArgumentException e) {
		}
	}

	public void testConstructorNullValue() {
		event = new PreferenceChangeEvent(Preferences.userRoot(), "key", null);
		assertEquals("key", event.getKey());
		assertNull(event.getNewValue());
		assertSame(Preferences.userRoot(), event.getNode());
		assertSame(Preferences.userRoot(), event.getSource());

		event = new PreferenceChangeEvent(Preferences.userRoot(), "", null);
		assertEquals("", event.getKey());
		assertNull(event.getNewValue());
		assertSame(Preferences.userRoot(), event.getNode());
		assertSame(Preferences.userRoot(), event.getSource());

		event = new PreferenceChangeEvent(Preferences.userRoot(), null, "value");
		assertNull(event.getKey());
		assertEquals("value", event.getNewValue());
		assertSame(Preferences.userRoot(), event.getNode());
		assertSame(Preferences.userRoot(), event.getSource());

		event = new PreferenceChangeEvent(Preferences.userRoot(), null, "");
		assertNull(event.getKey());
		assertEquals("", event.getNewValue());
		assertSame(Preferences.userRoot(), event.getNode());
		assertSame(Preferences.userRoot(), event.getSource());
	}

	public void testConstructor() {
		event = new PreferenceChangeEvent(Preferences.userRoot(), "key",
				"value");
		assertEquals("key", event.getKey());
		assertEquals("value", event.getNewValue());
		assertSame(Preferences.userRoot(), event.getNode());
		assertSame(Preferences.userRoot(), event.getSource());
	}

	public void testSerialization() throws Exception {
		event = new PreferenceChangeEvent(Preferences.userRoot(), "key",
				"value");
		try {
			SerializationTester.writeObject(event, "test.txt");
			fail();
		} catch (NotSerializableException e) {
		}

	}

}
