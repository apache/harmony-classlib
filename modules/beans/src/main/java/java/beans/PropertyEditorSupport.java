/*
 *  Copyright 2005 The Apache Software Foundation or its licensors, as applicable.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

/**
 * @author Maxim V. Berkultsev
 * @version $Revision: 1.3.6.3 $
 */
package java.beans;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * @author Maxim V. Berkultsev
 * @version $Revision: 1.3.6.3 $
 */

public class PropertyEditorSupport implements PropertyEditor {
    
    Object source = null;
    ArrayList listeners = new ArrayList();
    Object oldValue = null;
    Object newValue = null;

    /**
     * @com.intel.drl.spec_ref
     */
    protected PropertyEditorSupport(Object source) {
        if( source == null ) {
            throw new NullPointerException("source is null");
        }
        this.source = source;
    }

    /**
     * @com.intel.drl.spec_ref
     */
    protected PropertyEditorSupport() {
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public void paintValue(Graphics gfx, Rectangle box) {
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public void setAsText(String text) throws IllegalArgumentException {
        setValue(text);
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public String[] getTags() {
        return null;
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public String getJavaInitializationString() {
        return "???";
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public String getAsText() {
        return newValue == null ? "null" : newValue.toString();
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public void setValue(Object value) {
        this.oldValue = this.newValue;
        this.newValue = value;
        firePropertyChange();
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public Object getValue() {
        return newValue;
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public synchronized void removePropertyChangeListener(
            PropertyChangeListener listener) {
        if(listeners != null) {
            listeners.remove(listener);
        }
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public synchronized void addPropertyChangeListener(
            PropertyChangeListener listener) {
        listeners.add(listener);
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public Component getCustomEditor() {
        return null;
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public boolean supportsCustomEditor() {
        return false;
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public boolean isPaintable() {
        return false;
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public void firePropertyChange() {
        if(listeners.size() > 0) {
            PropertyChangeEvent event = new PropertyChangeEvent(
                    source, null, oldValue, newValue);
            Iterator iterator = listeners.iterator();
            while (iterator.hasNext()) {
                PropertyChangeListener listener =
                        (PropertyChangeListener) iterator.next();
                listener.propertyChange(event);
            }
        }
    }
}
