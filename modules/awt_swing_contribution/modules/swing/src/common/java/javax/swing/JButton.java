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

import javax.accessibility.Accessible;
import javax.accessibility.AccessibleContext;
import javax.accessibility.AccessibleRole;

import org.apache.harmony.x.swing.StringConstants;


public class JButton extends AbstractButton implements Accessible {

    protected class AccessibleJButton extends AccessibleAbstractButton {
        public AccessibleRole getAccessibleRole() {
            return AccessibleRole.PUSH_BUTTON;
        }
    };

    private boolean defaultCapable = true;

    private static final String UI_CLASS_ID = "ButtonUI";

    public JButton(final String text, final Icon icon) {
        setModel(new DefaultButtonModel());
        init(text, icon);
    }

    public JButton(final Icon icon) {
        this(null, icon);
    }

    public JButton(final Action action) {
        setModel(new DefaultButtonModel());
        setAction(action);
        init(getText(), getIcon());
    }

    public JButton(final String text) {
        this(text, null);
    }

    public JButton() {
        this(null, null);
    }

    public AccessibleContext getAccessibleContext() {
        return (accessibleContext == null) ? (accessibleContext = new AccessibleJButton())
                : accessibleContext;
    }

    public String getUIClassID() {
        return UI_CLASS_ID;
    }

    public void setDefaultCapable(final boolean defaultCapable) {
        boolean oldValue = this.defaultCapable;
        this.defaultCapable = defaultCapable;
        firePropertyChange(StringConstants.DEFAULT_CAPABLE_PROPERTY_CHANGED, oldValue, defaultCapable);
    }

    public boolean isDefaultCapable() {
        return defaultCapable;
    }

    public boolean isDefaultButton() {
        final JRootPane rootPane = getRootPane();
        return isDefaultButton(rootPane);
    }

    public void removeNotify() {
        final JRootPane rootPane = getRootPane();
        if (isDefaultButton(rootPane)) {
            rootPane.setDefaultButton(null);
        }
        super.removeNotify();
    }

    private boolean isDefaultButton(final JRootPane rootPane) {
        return (rootPane != null) && (rootPane.getDefaultButton() == this);
    }

}

