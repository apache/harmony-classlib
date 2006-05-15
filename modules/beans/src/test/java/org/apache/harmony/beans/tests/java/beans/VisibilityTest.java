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

package org.apache.harmony.beans.tests.java.beans;

import java.beans.Visibility;

import junit.framework.TestCase;

/**
 * Test the signature of the interface Visibility.
 */
public class VisibilityTest extends TestCase {

	public void testSignature() {
		DummyVisibility o = new DummyVisibility();
		assertTrue(o instanceof Visibility);
	}

	static class DummyVisibility implements Visibility {

		public boolean avoidingGui() {
			return false;
		}

		public void dontUseGui() {
		}

		public boolean needsGui() {
			return false;
		}

		public void okToUseGui() {
		}
	}

}
