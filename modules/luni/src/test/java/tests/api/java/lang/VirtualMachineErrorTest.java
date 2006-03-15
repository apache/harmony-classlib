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

public class VirtualMachineErrorTest extends junit.framework.TestCase {

	public class TestVirtualMachineError extends VirtualMachineError {
		public TestVirtualMachineError() {
			super();
		}

		public TestVirtualMachineError(String detailMessage) {
			super(detailMessage);
		}
	}

	/**
	 * @tests java.lang.VirtualMachineError#VirtualMachineError()
	 */
	public void test_Constructor() {
		// Test for method java.lang.VirtualMachineError()
		// Tested more via subclasses (i.e. StackOverFlowError).
		try {
			if (true)
				throw new TestVirtualMachineError();
		} catch (VirtualMachineError e) {
			return;
		}
		fail("Constructor failed");
	}

	/**
	 * @tests java.lang.VirtualMachineError#VirtualMachineError(java.lang.String)
	 */
	public void test_ConstructorLjava_lang_String() {
		// Test for method java.lang.VirtualMachineError(java.lang.String)
		// Tested more via subclasses (i.e. StackOverFlowError).
		try {
			if (true)
				throw new TestVirtualMachineError("HelloWorld");
		} catch (VirtualMachineError e) {
			assertTrue("VerifyError(String) failed.", e.getMessage().equals(
					"HelloWorld"));
			return;
		}
		fail("Constructor failed");
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
