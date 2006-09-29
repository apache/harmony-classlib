/*
 *  Copyright 2005 - 2006 The Apache Software Foundation or its licensors, as applicable.
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

import javax.accessibility.AccessibleContext;
import javax.accessibility.AccessibleRole;

public class JCheckBoxMenuItem extends JMenuItem {

    protected class AccessibleJCheckBoxMenuItem extends AccessibleJMenuItem {
        public AccessibleRole getAccessibleRole() {
            return AccessibleRole.CHECK_BOX;
        }
    }

    private final static String UI_CLASS_ID = "CheckBoxMenuItemUI";

    public JCheckBoxMenuItem() {
        this(null, null, false);
    }

    public JCheckBoxMenuItem(final Icon icon) {
        this(null, icon, false);
    }

    public JCheckBoxMenuItem(final String text) {
        this(text, null, false);
    }

    public JCheckBoxMenuItem(final String text, final Icon icon) {
        this(text, icon, false);
    }

    public JCheckBoxMenuItem(final String text, final boolean selected) {
        this(text, null, selected);
    }

    public JCheckBoxMenuItem(final String text, final Icon icon,
                             final boolean selected) {
        setDefaultModelAndFocus();
        setSelected(selected);
        init(text, icon);
    }

    public JCheckBoxMenuItem(final Action action) {
        super(action);
    }

    public String getUIClassID() {
        return UI_CLASS_ID;
    }

    public void setState(final boolean b) {
        setSelected(b);
    }

    public boolean getState() {
        return isSelected();
    }

    public AccessibleContext getAccessibleContext() {
        return (accessibleContext == null) ? (accessibleContext = new AccessibleJCheckBoxMenuItem())
                : accessibleContext;
    }

    ButtonModel createDefaultModel() {
        return new JToggleButton.ToggleButtonModel();
    }

}
