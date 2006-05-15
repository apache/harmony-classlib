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

package org.apache.harmony.beans.tests.java.beans.mock;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;

/**
 * This is a mock bean for EventHandler test.
 */
public class MockButton implements Serializable {
	String name;

	String label;

	PropertyChangeSupport support;

	static public String defaultName = "MockButton 1";

	public MockButton() {
		this.name = defaultName;
		this.label = this.name;
		this.support = new PropertyChangeSupport(this);
	}

	public void addPropertyChangeListener(PropertyChangeListener listener) {
		this.support.addPropertyChangeListener(listener);
	}

	/**
	 * @return Returns the label.
	 */
	public String getLabel() {
		return label;
	}

	/**
	 * @param label
	 *            The label to set.
	 */
	public void setLabel(String label) {
		this.support.firePropertyChange("label", this.label, label);
		this.label = label;
	}

	/**
	 * @return Returns the name.
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            The name to set.
	 */
	public void setName(String name) {
		this.name = name;
	}

}
