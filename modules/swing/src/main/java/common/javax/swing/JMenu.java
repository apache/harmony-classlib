/*
 *  Licensed to the Apache Software Foundation (ASF) under one or more
 *  contributor license agreements.  See the NOTICE file distributed with
 *  this work for additional information regarding copyright ownership.
 *  The ASF licenses this file to You under the Apache License, Version 2.0
 *  (the "License"); you may not use this file except in compliance with
 *  the License.  You may obtain a copy of the License at
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
import java.awt.ComponentOrientation;
import java.awt.Point;
import java.awt.event.InputEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.beans.PropertyChangeListener;
import java.io.Serializable;

import javax.accessibility.Accessible;
import javax.accessibility.AccessibleContext;
import javax.accessibility.AccessibleRole;
import javax.accessibility.AccessibleSelection;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;

import org.apache.harmony.x.swing.Utilities;


public class JMenu extends JMenuItem implements Accessible, MenuElement {

    // TODO implement accessibility
    protected class AccessibleJMenu extends AccessibleJMenuItem implements AccessibleSelection {
        public void addAccessibleSelection(final int i) {
            throw new UnsupportedOperationException("not implemented");
        }

        public void clearAccessibleSelection() {
            throw new UnsupportedOperationException("not implemented");
        }

        public int getAccessibleChildrenCount() {
            throw new UnsupportedOperationException("not implemented");
        }

        public Accessible getAccessibleChild(final int i) {
            throw new UnsupportedOperationException("not implemented");
        }

        public AccessibleRole getAccessibleRole() {
            return AccessibleRole.MENU;
        }

        public AccessibleSelection getAccessibleSelection() {
            throw new UnsupportedOperationException("not implemented");
        }

        public Accessible getAccessibleSelection(final int i) {
            throw new UnsupportedOperationException("not implemented");
        }

        public int getAccessibleSelectionCount() {
            throw new UnsupportedOperationException("not implemented");
        }

        public boolean isAccessibleChildSelected(final int i) {
            throw new UnsupportedOperationException("not implemented");
        }

        public void removeAccessibleSelection(final int i) {
            throw new UnsupportedOperationException("not implemented");
        }

        public void selectAllAccessibleSelection() {
            throw new UnsupportedOperationException("not implemented");
        }
    }

    protected class WinListener extends WindowAdapter implements Serializable {
        private final JPopupMenu popup;

        public WinListener(final JPopupMenu p) {
            popup = p;
        }

        public void windowClosing(final WindowEvent e) {
            setSelected(false);
        }
    }

    protected WinListener popupListener;

    private static final String UI_CLASS_ID = "MenuUI";

    private static final Object ALL_ACTION_PROPERTIES = new Object() {
        public boolean equals(final Object o) {
            return !Action.ACCELERATOR_KEY.equals(o);
        }
    };

    private int delay = 200;
    private JPopupMenu popup;
    private transient MenuEvent menuEvent;
    private transient int[] uiMnemonicModifiers;
    private transient boolean crossMenuMnemonic;

    public JMenu() {
        super();
    }

    public JMenu(final String text) {
        super(text);
    }

    public JMenu(final String text, final boolean b) {
        super(text);
    }

    public JMenu(final Action action) {
        setDefaultModelAndFocus();
        setAction(action);
        init(getText(), getIcon());
    }

    void configurePropertyFromAction(final Action action, final Object propertyName) {
        if (propertyName == null || propertyName.equals(Action.ACCELERATOR_KEY)) {
            return;
        }
        super.configurePropertyFromAction(action, propertyName);
    }

    public AccessibleContext getAccessibleContext() {
        return (accessibleContext == null) ? (accessibleContext = new AccessibleJMenu())
                : accessibleContext;
    }

    protected PropertyChangeListener createActionChangeListener(final JMenuItem item) {
        return item.createActionPropertyChangeListener(getAction());
    }

    protected JMenuItem createActionComponent(final Action action) {
        return JMenuItem.createJMenuItem(action);
    }

    protected WinListener createWinListener(final JPopupMenu popup) {
        return new WinListener(popup);
    }

    public void addMenuListener(final MenuListener listener) {
        listenerList.add(MenuListener.class, listener);
    }

    public void removeMenuListener(final MenuListener listener) {
        listenerList.remove(MenuListener.class, listener);
    }

    public MenuListener[] getMenuListeners() {
        return (MenuListener[])getListeners(MenuListener.class);
    }

    protected void fireMenuCanceled() {
        final MenuListener[] listeners = getMenuListeners();
        if (listeners.length == 0){
            return;
        }

        if (menuEvent == null) {
            menuEvent = new MenuEvent(this);
        }
        for (int i = 0; i < listeners.length; i++) {
            listeners[i].menuCanceled(menuEvent);
        }
    }

    protected void fireMenuDeselected() {
        final MenuListener[] listeners = getMenuListeners();
        if (listeners.length == 0){
            return;
        }

        if (menuEvent == null) {
            menuEvent = new MenuEvent(this);
        }
        for (int i = 0; i < listeners.length; i++) {
            listeners[i].menuDeselected(menuEvent);
        }
    }

    protected void fireMenuSelected() {
        final MenuListener[] listeners = getMenuListeners();
        if (listeners.length == 0){
            return;
        }

        if (menuEvent == null) {
            menuEvent = new MenuEvent(this);
        }
        for (int i = 0; i < listeners.length; i++) {
            listeners[i].menuSelected(menuEvent);
        }
    }

    public void doClick(final int time) {
        final MenuElement[] path = Utilities.getMenuElementPath(getPopupMenu());
        MenuSelectionManager.defaultManager().setSelectedPath(path);
    }

    public JPopupMenu getPopupMenu() {
        if (popup == null) {
            popup = new JPopupMenu();
            popup.setInvoker(this);
        }
        return popup;
    }

    protected Point getPopupMenuOrigin() {
        final boolean leftToRight = getComponentOrientation().isLeftToRight();
        Point result = Utilities.getPopupLocation(getBounds(),
                                                  getPopupMenu().getPreferredSize(),
                                                  leftToRight, !isTopLevelMenu());
        String prefix = isTopLevelMenu() ? "Menu.menuPopupOffset" : "Menu.submenuPopupOffset";
        int xOffset = UIManager.getInt(prefix + "X");
        int yOffset = UIManager.getInt(prefix + "Y");
        if (!leftToRight) {
            xOffset = -xOffset;
        }
        result.translate(xOffset - getX(), yOffset - getY());
        return result;
    }

    public boolean isPopupMenuVisible() {
        return popup != null ? popup.isVisible() : false;
    }

    public void setPopupMenuVisible(final boolean visible) {
        if (visible == isPopupMenuVisible()) {
            return;
        }
        popup = getPopupMenu();
        if (visible) {
            if (isShowing()) {
                Point origin = getPopupMenuOrigin();
                popup.show(this, origin.x, origin.y);
            }
        } else {
            popup.setVisible(visible);
        }
    }

    public String getUIClassID() {
        return UI_CLASS_ID;
    }

    public void setSelected(final boolean selected) {
        if (selected != isSelected()) {
            super.setSelected(selected);
            if (selected) {
                fireMenuSelected();
            } else {
                fireMenuDeselected();
            }
        }
    }

    public boolean isTearOff() {
        throw new Error("Not yet implemented");
    }

    public boolean isTopLevelMenu() {
        return (getParent() instanceof JMenuBar);
    }

    public void menuSelectionChanged(final boolean b) {
        setSelected(b);
    }

    public void setAccelerator(final KeyStroke keyStroke) {
        throw new Error("setAccelerator() in not used for JMenu. Use setMnemonic() instead.");
    }

    public void setComponentOrientation(final ComponentOrientation orientation) {
        super.setComponentOrientation(orientation);
        if (popup != null) {
            popup.setComponentOrientation(orientation);
        }
    }

    public void applyComponentOrientation(final ComponentOrientation orientation) {
        super.applyComponentOrientation(orientation);
        if (popup != null) {
            popup.applyComponentOrientation(orientation);
        }
    }

    public void setDelay(final int delay) {
        if (delay < 0) {
            throw new IllegalArgumentException("Delay must be positive");
        }
        this.delay = delay;
    }

    public int getDelay() {
        return delay;
    }

    public void setMenuLocation(final int x, final int y) {
        if (popup != null) {
            popup.setLocation(x, y);
        }
    }

    public Component add(final Component c) {
        return getPopupMenu().add(c);
    }

    public JMenuItem add(final Action action) {
        return getPopupMenu().add(action);
    }

    public Component add(final Component c, final int index) {
        return getPopupMenu().add(c, index);
    }

    public JMenuItem add(final String text) {
        return getPopupMenu().add(text);
    }

    public JMenuItem add(final JMenuItem item) {
        return getPopupMenu().add(item);
    }

    public void addSeparator() {
        getPopupMenu().addSeparator();
    }

    public void insert(final String text, final int index) {
        JMenuItem item = new JMenuItem(text);
        getPopupMenu().insert(item, getValidIndex(index));
    }

    public JMenuItem insert(final JMenuItem item, final int index) {
        getPopupMenu().insert(item, index);
        return item;
    }

    public JMenuItem insert(final Action action, final int index) {
        JMenuItem item = createActionComponent(action);
        getPopupMenu().insert(item, getValidIndex(index));
        return item;
    }

    public void insertSeparator(final int index) {
        getPopupMenu().insert(new JPopupMenu.Separator(), getValidIndex(index));
    }

    public Component getMenuComponent(final int index) {
        if (popup == null || index < 0 || index >= getMenuComponentCount()) {
            return null;
        }
        return popup.getComponent(index);
    }

    public JMenuItem getItem(final int index) {
        if (popup == null || index < 0 || index >= getItemCount()) {
            return null;
        }
        Component c = popup.getComponent(index);
        return (c instanceof JMenuItem) ? (JMenuItem)c : null;
    }

    public int getItemCount() {
        return getMenuComponentCount();
    }

    public int getMenuComponentCount() {
        return (popup != null) ? popup.getComponentCount() : 0;
    }

    public Component[] getMenuComponents() {
        return (popup != null) ? popup.getComponents() : new Component[0];
    }

    public MenuElement[] getSubElements() {
        return new MenuElement[] {getPopupMenu()};
    }

    public void remove(final Component c) {
        if (popup == null) {
            return;
        }
        Component[] subComponents = getMenuComponents();
        for (int i = 0; i < subComponents.length; i++) {
            if (subComponents[i] == c) {
                popup.remove(i);
                break;
            }
        }
    }

    public void remove(final int i) {
        if (popup != null) {
            popup.remove(i);
        }
    }

    public void remove(final JMenuItem item) {
        remove((Component)item);
    }

    public void removeAll() {
        if (popup != null) {
            popup.removeAll();
        }
    }

    public boolean isMenuComponent(final Component c) {
        if (c == null) {
            return false;
        }
        if (c == this) {
            return true;
        }
        Component[] subComponents = getMenuComponents();
        for (int i = 0; i < subComponents.length; i++) {
            if (subComponents[i] == c) {
                return true;
            }
        }
        for (int i = 0; i < subComponents.length; i++) {
            if (subComponents[i] instanceof JMenu &&
                    ((JMenu)subComponents[i]).isMenuComponent(c)) {

                return true;
            }
        }

        return false;
    }

    public void updateUI() {
        super.updateUI();
        uiMnemonicModifiers = (int[])UIManager.get("Menu.shortcutKeys");
        crossMenuMnemonic = UIManager.getBoolean("Menu.crossMenuMnemonic");
    }

    void setDefaultModelAndFocus() {
        setModel(createDefaultModel());
        setFocusPainted(false);
        setHorizontalAlignment(SwingConstants.LEADING);
    }

    Object getActionPropertiesFilter() {
        return ALL_ACTION_PROPERTIES;
    }

    boolean isMnemonicKeyStroke(final KeyStroke keyStroke) {
        if (keyStroke.getKeyCode() != getMnemonic()) {
            return false;
        }
        final int modifiers = keyStroke.getModifiers();
        if (isTopLevelMenu()) {
            final MenuSelectionManager defaultManager = MenuSelectionManager.defaultManager();
            final boolean pathEmpty = defaultManager.isPathEmpty();
            if (!pathEmpty && !crossMenuMnemonic) {
                return false;
            }
            boolean enableStandardModifiers = defaultManager.isComponentPartOfCurrentMenu(this);
            return isMnemonicModifiers(modifiers, enableStandardModifiers);
        }
        return isStandardModifiers(modifiers);
    }

    private boolean isMnemonicModifiers(final int modifiers, final boolean forceStandardCheck) {
        if (forceStandardCheck && isStandardModifiers(modifiers)) {
            return true;
        }

        if (Utilities.isEmptyArray(uiMnemonicModifiers)) {
            return false;
        }

        for (int i = 0; i < uiMnemonicModifiers.length; i++) {
            if ((modifiers & uiMnemonicModifiers[i]) != 0) {
                return true;
            }
        }
        return false;
    }

    private boolean isStandardModifiers(final int modifiers) {
        return (modifiers == 0) || (modifiers & InputEvent.ALT_DOWN_MASK) != 0;
    }

    private int getValidIndex(final int index) {
        return index < getItemCount() ? index : getItemCount();
    }
}

