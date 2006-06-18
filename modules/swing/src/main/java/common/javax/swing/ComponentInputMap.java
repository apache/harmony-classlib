/*
 *  Copyright 2005 - 2006 The Apache Software Software Foundation or its licensors, as applicable.
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
 * @author Alexander T. Simbirtsev
 * @version $Revision$
 */
package javax.swing;

public class ComponentInputMap extends InputMap {

    private JComponent component = null;

    public ComponentInputMap(final JComponent component) {
        if (component == null) {
            throw new IllegalArgumentException("ComponentInputMaps must be associated with a non-null JComponent");
        }
        this.component = component;
    }

    public void put(final KeyStroke keyStroke, final Object key) {
        super.put(keyStroke, key);
        if (component != null) {
            component.componentInputMapChanged(this);
        }
    }

    public void remove(final KeyStroke keyStroke) {
        super.remove(keyStroke);
        component.componentInputMapChanged(this);
    }

    public JComponent getComponent() {
        return component;
    }

    public void setParent(final InputMap parent) {
        if (parent != null && (!(parent instanceof ComponentInputMap) ||
                (((ComponentInputMap)parent).getComponent() != component))) {
            throw new IllegalArgumentException("ComponentInputMap must have a ComponentInputMap parent associated with the same component");
        }
        super.setParent(parent);
        if (component != null) {
            component.componentInputMapChanged(this);
        }
    }

    public void clear() {
        super.clear();
        component.componentInputMapChanged(this);
    }

}