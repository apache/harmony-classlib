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

import java.awt.Component;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.beans.PropertyChangeListener;
import java.beans.PropertyEditor;

import tests.api.java.beans.PropertyChangeListenerTest.DummyPropertyChangeListener;

import junit.framework.TestCase;

/**
 * Test the signature of the interface PropertyEditor.
 */
public class PropertyEditorTest extends TestCase {

	public void testSignature() {
		DummyPropertyEditor o = new DummyPropertyEditor();
		assertTrue(o instanceof PropertyEditor);
	}

	static class DummyPropertyEditor implements PropertyEditor {

		public void addPropertyChangeListener(PropertyChangeListener listener) {
		}

		public String getAsText() {
			return null;
		}

		public Component getCustomEditor() {
			return null;
		}

		public String getJavaInitializationString() {
			return null;
		}

		public String[] getTags() {
			return null;
		}

		public Object getValue() {
			return null;
		}

		public boolean isPaintable() {
			return false;
		}

		public void paintValue(Graphics graphics, Rectangle box) {
		}

		public void removePropertyChangeListener(PropertyChangeListener listener) {
		}

		public void setAsText(String text) throws IllegalArgumentException {
		}

		public void setValue(Object value) {
		}

		public boolean supportsCustomEditor() {
			return false;
		}
	}
}
