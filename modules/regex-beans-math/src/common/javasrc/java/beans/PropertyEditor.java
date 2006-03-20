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

/**
 * @author Maxim V. Berkultsev
 * @version $Revision: 1.3.6.3 $
 */

public interface PropertyEditor {

    /**
     * @com.intel.drl.spec_ref
     */
    public void paintValue(Graphics gfx, Rectangle box);

    /**
     * @com.intel.drl.spec_ref
     */
    public void setAsText(String text) throws IllegalArgumentException;

    /**
     * @com.intel.drl.spec_ref
     */
    public String[] getTags();

    /**
     * @com.intel.drl.spec_ref
     */
    public String getJavaInitializationString();

    /**
     * @com.intel.drl.spec_ref
     */
    public String getAsText();

    /**
     * @com.intel.drl.spec_ref
     */
    public void setValue(Object value);

    /**
     * @com.intel.drl.spec_ref
     */
    public Object getValue();

    /**
     * @com.intel.drl.spec_ref
     */
    public void removePropertyChangeListener(PropertyChangeListener listener);

    /**
     * @com.intel.drl.spec_ref
     */
    public void addPropertyChangeListener(PropertyChangeListener listener);

    /**
     * @com.intel.drl.spec_ref
     */
    public Component getCustomEditor();

    /**
     * @com.intel.drl.spec_ref
     */
    public boolean supportsCustomEditor();

    /**
     * @com.intel.drl.spec_ref
     */
    public boolean isPaintable();
}
