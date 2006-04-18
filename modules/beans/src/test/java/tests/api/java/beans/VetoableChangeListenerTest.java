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

package tests.api.java.beans;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyEditor;
import java.beans.PropertyVetoException;
import java.beans.VetoableChangeListener;

import tests.api.java.beans.PropertyEditorTest.DummyPropertyEditor;

import junit.framework.TestCase;

/**
 * Test the signature of the interface VetoableChangeListener.
 */
public class VetoableChangeListenerTest extends TestCase {

	public void testSignature() {
		DummyVetoableChangeListener o = new DummyVetoableChangeListener();
		assertTrue(o instanceof VetoableChangeListener);
	}

	static class DummyVetoableChangeListener implements VetoableChangeListener {

		public void vetoableChange(PropertyChangeEvent event)
				throws PropertyVetoException {
		}
	}

}
