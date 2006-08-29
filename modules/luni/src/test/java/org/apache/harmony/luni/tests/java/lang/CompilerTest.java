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

package org.apache.harmony.luni.tests.java.lang;

public class CompilerTest extends junit.framework.TestCase {

	/**
	 * @tests java.lang.Compiler#command(java.lang.Object)
	 */
	public void test_commandLjava_lang_Object() {
		// Test for method java.lang.Object
		// java.lang.Compiler.command(java.lang.Object)
		try {
			assertNull("Incorrect behavior.",
					Compiler.command(new Object()));
		} catch (Exception e) {
			fail("Exception during test : " + e.getMessage());
		}
	}

	/**
	 * @tests java.lang.Compiler#compileClass(java.lang.Class)
	 */
	public void test_compileClassLjava_lang_Class() {
		// Test for method boolean
		// java.lang.Compiler.compileClass(java.lang.Class)
		try {
			// Do not test return value, may return true or false depending on
			// if the jit is enabled. Make the call to ensure it doesn't crash.
			Compiler.compileClass(Compiler.class);
		} catch (Exception e) {
			fail("Exception during test.");
		}
	}

	/**
	 * @tests java.lang.Compiler#compileClasses(java.lang.String)
	 */
	public void test_compileClassesLjava_lang_String() {
		// Test for method boolean
		// java.lang.Compiler.compileClasses(java.lang.String)
		try {
			// Do not test return value, may return true or false depending on
			// if the jit is enabled. Make the call to ensure it doesn't crash.
			Compiler.compileClasses("Compiler");
		} catch (Exception e) {
			fail("Exception during test.");
		}
	}

	/**
	 * @tests java.lang.Compiler#disable()
	 */
	public void test_disable() {
		// Test for method void java.lang.Compiler.disable()
		try {
			Compiler.disable();
			Compiler.compileClass(Compiler.class);
		} catch (Exception e) {
			fail("Exception during test : " + e.getMessage());
		}
	}

	/**
	 * @tests java.lang.Compiler#enable()
	 */
	public void test_enable() {
		// Test for method void java.lang.Compiler.enable()
		try {
			Compiler.disable();
			Compiler.enable();
			Compiler.compileClass(Compiler.class);
		} catch (Exception e) {
			fail("Exception during test : " + e.getMessage());
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
	}
}
