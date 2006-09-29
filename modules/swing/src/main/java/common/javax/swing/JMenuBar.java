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

import java.awt.Component;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import javax.accessibility.Accessible;
import javax.accessibility.AccessibleContext;
import javax.accessibility.AccessibleRole;
import javax.accessibility.AccessibleSelection;
import javax.accessibility.AccessibleStateSet;
import javax.swing.plaf.MenuBarUI;

import org.apache.harmony.x.swing.StringConstants;
import org.apache.harmony.x.swing.Utilities;


public class JMenuBar extends JComponent implements Accessible, MenuElement {

    // TODO: implement
    protected class AccessibleJMenuBar extends AccessibleJComponent implements AccessibleSelection {
        public void addAccessibleSelection(final int i) {
            throw new UnsupportedOperationException("Not implemented");
        }

        public void clearAccessibleSelection() {
            throw new UnsupportedOperationException("Not implemented");
        }

        public Accessible getAccessibleSelection(final int i) {
            throw new UnsupportedOperationException("Not implemented");
        }

        public int getAccessibleSelectionCount() {
            throw new UnsupportedOperationException("Not implemented");
        }

        public AccessibleRole getAccessibleRole() {
            return AccessibleRole.MENU_BAR;
        }

        public AccessibleSelection getAccessibleSelection() {
            throw new UnsupportedOperationException("Not implemented");
        }

        public AccessibleStateSet getAccessibleStateSet() {
            throw new UnsupportedOperationException("Not implemented");
        }

        public boolean isAccessibleChildSelected(final int i) {
            throw new UnsupportedOperationException("Not implemented");
        }

        public void removeAccessibleSelection(final int i) {
            throw new UnsupportedOperationException("Not implemented");
        }

        public void selectAllAccessibleSelection() {
            throw new UnsupportedOperationException("Not implemented");
        }
    }

    private final static String UI_CLASS_ID = "MenuBarUI";

    private SingleSelectionModel selectionModel = new DefaultSingleSelectionModel();
    private boolean borderPainted = true;
    private Insets margin;

    public JMenuBar() {
        updateUI();
    }

    public JMenu add(final JMenu menu) {
        super.add(menu);
        return menu;
    }

    public AccessibleContext getAccessibleContext() {
        return (accessibleContext == null) ? (accessibleContext = new AccessibleJMenuBar())
                : accessibleContext;
    }

    public JMenu getMenu(final int index) {
        if (index < 0 || index >= getMenuCount()) {
            return null;
        }
        Component c = getComponent(index);
        return (c instanceof JMenu) ? (JMenu)c : null;
    }

    public int getMenuCount() {
        return getComponentCount();
    }

    /**
     * @deprecated
     */
    public Component getComponentAtIndex(final int index) {
        return getComponent(index);
    }

    public int getComponentIndex(final Component c) {
        for (int i = 0; i < getComponentCount(); i++) {
            if (getComponent(i) == c) {
                return i;
            }
        }
        return -1;
    }

    public MenuElement[] getSubElements() {
        return Utilities.getSubElements(this);
    }

    public void setSelectionModel(final SingleSelectionModel model) {
        SingleSelectionModel oldValue = selectionModel;
        selectionModel = model;
        firePropertyChange(StringConstants.SELECTION_MODEL_PROPERTY, oldValue, selectionModel);
    }

    public SingleSelectionModel getSelectionModel() {
        return selectionModel;
    }

    public void setSelected(final Component selection) {
        if (selectionModel != null) {
            selectionModel.setSelectedIndex(getComponentIndex(selection));
        }
    }

    public boolean isSelected() {
        return (selectionModel != null) ? selectionModel.isSelected() : false;
    }

    public void setBorderPainted(final boolean painted) {
        boolean oldValue = borderPainted;
        borderPainted = painted;
        firePropertyChange(AbstractButton.BORDER_PAINTED_CHANGED_PROPERTY,
                           oldValue,
                           borderPainted);
    }

    public boolean isBorderPainted() {
        return borderPainted;
    }

    protected void paintBorder(final Graphics g) {
        if (isBorderPainted()) {
            super.paintBorder(g);
        }
    }

    protected boolean processKeyBinding(final KeyStroke ks, final KeyEvent event, final int condition, final boolean pressed) {
        MenuSelectionManager.defaultManager().processKeyEvent(event);
        if (event.isConsumed()) {
            return true;
        }
        if (super.processKeyBinding(ks, event, condition, pressed)) {
            return true;
        }
        return SwingUtilities.processKeyEventOnChildren(this, event);
    }

    public void processKeyEvent(final KeyEvent e, final MenuElement[] path, final MenuSelectionManager manager) {
    }

    public void processMouseEvent(final MouseEvent event, final MenuElement[] path, final MenuSelectionManager manager) {
    }

    public void menuSelectionChanged(final boolean isIncluded) {
    }

    public Component getComponent() {
        return this;
    }

    public void setHelpMenu(final JMenu menu) {
        throw new Error("setHelpMenu() hasn't been implemented yet");
    }

    public JMenu getHelpMenu() {
        throw new Error("getHelpMenu() hasn't been implemented yet");
    }

    public void setMargin(final Insets margin) {
        Insets oldValue = this.margin;
        this.margin = margin;
        firePropertyChange(AbstractButton.MARGIN_CHANGED_PROPERTY, oldValue, margin);
    }

    public Insets getMargin() {
        return margin;
    }

    public String getUIClassID() {
        return UI_CLASS_ID;
    }

    public MenuBarUI getUI() {
        return (MenuBarUI)ui;
    }

    public void setUI(final MenuBarUI ui) {
        super.setUI(ui);
    }

    public void updateUI() {
        setUI((MenuBarUI)UIManager.getUI(this));
    }
}

