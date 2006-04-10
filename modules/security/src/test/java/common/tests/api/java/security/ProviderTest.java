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

package tests.api.java.security;

import java.security.Provider;

public class ProviderTest extends junit.framework.TestCase {

	class TestProvider extends Provider {
		TestProvider(String name, double version, String info) {
			super(name, version, info);
		}
	}

	TestProvider provTest = new TestProvider("provTest", 1.2,
			"contains nothings, purely for testing the class");

	/**
	 * @tests java.security.Provider#entrySet()
	 */
	public void test_entrySet() {
		// test method of java.security.provider.entrySet
		provTest.put("test.prop", "this is a test property");
		try {
			provTest.entrySet().add("another property value");
			fail("was able to modify the entrySet");
		} catch (UnsupportedOperationException e) {
			// expected
		}
	}

	/**
	 * @tests java.security.Provider#getInfo()
	 */
	public void test_getInfo() {
		// test method of java.security.provider.getInfo
		assertEquals("the information of the provider is not stored properly",
				"contains nothings, purely for testing the class", provTest
						.getInfo());
	}

	/**
	 * @tests java.security.Provider#getName()
	 */
	public void test_getName() {
		// test method of java.security.provider.getName
		assertEquals("the name of the provider is not stored properly",
				"provTest", provTest.getName());
	}

	/**
	 * @tests java.security.Provider#getVersion()
	 */
	public void test_getVersion() {
		// test method of java.security.provider.getVersion
		assertEquals("the version of the provider is not stored properly",
				1.2, provTest.getVersion(), 0);
	}

	/**
	 * @tests java.security.Provider#keySet()
	 */
	public void test_keySet() {
		// test method of java.security.provider.keySet
		provTest.put("test.prop", "this is a test property");
		try {
			provTest.keySet().add("another property key");
			fail("was able to modify the keySet");
		} catch (UnsupportedOperationException e) {
			// expected
		}
	}

	/**
	 * @tests java.security.Provider#values()
	 */
	public void test_values() {
		// test method of java.security.provider.values
		provTest.put("test.prop", "this is a test property");
		try {
			provTest.values().add("another property value");
			fail("was able to modify the values collection");
		} catch (UnsupportedOperationException e) {
			// expected
		}
	}

	protected void setUp() {
	}

	protected void tearDown() {
	}
}