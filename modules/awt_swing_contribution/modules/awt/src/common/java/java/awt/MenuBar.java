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
 * @author Pavel Dolgov
 * @version $Revision$
 */
package java.awt;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashSet;

import javax.accessibility.Accessible;
import javax.accessibility.AccessibleContext;
import javax.accessibility.AccessibleRole;

import org.apache.harmony.awt.gl.MultiRectArea;
import org.apache.harmony.awt.state.MenuBarState;
import org.apache.harmony.awt.wtk.NativeWindow;


public class MenuBar extends MenuComponent implements MenuContainer, Accessible {

    private static final long serialVersionUID = -4930327919388951260L;
    private final ArrayList menuList;
    private Menu helpMenu; // one of the list items
    private boolean unfolded;

    final State menuBarState = new State();
    private final MenuBarBox box = new MenuBarBox();

    protected class AccessibleAWTMenuBar extends AccessibleAWTMenuComponent {

        private static final long serialVersionUID = -8577604491830083815L;

        public AccessibleRole getAccessibleRole() {
            return AccessibleRole.MENU_BAR;
        }
    }

    /**
     * The accessor to MenuBar internal state, utilized by the visual theme
     */
    final class State extends MenuComponent.State implements MenuBarState {

        private Frame getFrame() {
            return ((Frame)MenuBar.this.parent);
        }

        public Point getLocationOnScreen() {
            return MenuBar.this.getScreenLocation();
        }

        void calculate() {
            Frame f = getFrame();
            Insets ins = f.getNativeInsets();
            int width = f.w - ins.left - ins.right;
            toolkit.theme.layoutMenuBar(this, width);
        }

        void reset() {
            super.reset();
        }
    }


    /**
     * Pseudo pop-up box for menu bar. Actually it's Frame's child window,
     * but in other aspects it is similar to the normal pop-up box. 
     */
    private final class MenuBarBox extends MenuComponent.MenuPopupBox {

        boolean isMenuBar() {
            return true;
        }

        boolean isActive() {
            return toolkit.dispatcher.popupDispatcher.isActive(this);
        }

        Dimension getSize() {
            size.setSize(menuBarState.getSize());
            return size;
        }

        Point getLocation () {
            location.setLocation(MenuBar.this.getLocation());
            return location;
        }

        Point getScreenLocation() {
            return MenuBar.this.getScreenLocation();
        }

        void paint(Graphics gr) {
            MenuBar.this.paint(gr);
        }

        Rectangle calculateBounds() {
            Dimension size = MenuBar.this.menuBarState.getSize();
            Point location = MenuBar.this.getLocation();
            return new Rectangle(location, size);
        }

        void show() {
            show((Frame)MenuBar.this.getParent());
        }

        void hide() {
            selectItem(-1);
            toolkit.dispatcher.popupDispatcher.deactivate(this);
        }

        void updateBounds() {
            Rectangle bounds = calculateBounds();
            boolean moved = !location.equals(bounds.getLocation());
            boolean resized = !size.equals(bounds.getSize());
            if (moved || resized) {
                int mask = 0;
                mask |= moved ? 0 : NativeWindow.BOUNDS_NOMOVE;
                mask |= resized ? 0 : NativeWindow.BOUNDS_NOSIZE;
                if (nativeWindow != null) {
                    nativeWindow.setBounds(bounds.x, bounds.y, bounds.width, bounds.height, mask);
                }
                location.setLocation(bounds.getLocation());
                size.setSize(bounds.getSize());
            }
        }
    }

    public MenuBar() throws HeadlessException {
        toolkit.lockAWT();
        try {
            menuList = new ArrayList();
        } finally {
            toolkit.unlockAWT();
        }
    }

    public Menu add(Menu menu) {
        toolkit.lockAWT();
        try {
            if (!menuList.contains(menu)) {
                MenuContainer oldParent = menu.getParent();
                if (oldParent != null) {
                    oldParent.remove(menu);
                }
                menu.setParent(this);
                menuList.add(menu);
            }
            menuBarState.reset();
            updateParent();
            return menu;
        } finally {
            toolkit.unlockAWT();
        }
    }

    public void remove(MenuComponent menu) {
        toolkit.lockAWT();
        try {
            menuList.remove(menu);
            if (menu == helpMenu) {
                helpMenu = null;
            }
            menu.setParent(null);
            menuBarState.reset();
            updateParent();
        } finally {
            toolkit.unlockAWT();
        }
    }

    public void remove(int index) {
        toolkit.lockAWT();
        try {
            Menu menu = (Menu)menuList.get(index);
            remove(menu);
        } finally {
            toolkit.unlockAWT();
        }
    }

    public void addNotify() {
        toolkit.lockAWT();
        try {
            menuBarState.calculate();
            box.show();
        } finally {
            toolkit.unlockAWT();
        }
    }

    public AccessibleContext getAccessibleContext() {
        toolkit.lockAWT();
        try {
            return super.getAccessibleContext();
        } finally {
            toolkit.unlockAWT();
        }
    }

    public void removeNotify() {
        toolkit.lockAWT();
        try {
            box.removeNotify();
        } finally {
            toolkit.unlockAWT();
        }
    }

    public void deleteShortcut(MenuShortcut ms) {
        toolkit.lockAWT();
        try {
            MenuItem mi = getShortcutMenuItemImpl(ms);
            if (mi != null) {
                mi.deleteShortcut();
            }
        } finally {
            toolkit.unlockAWT();
        }
    }

    public MenuItem getShortcutMenuItem(MenuShortcut ms) {
        toolkit.lockAWT();
        try {
            return getShortcutMenuItemImpl(ms);
        } finally {
            toolkit.unlockAWT();
        }
    }

    public Enumeration shortcuts() {
        toolkit.lockAWT();
        try {
            HashSet shortcuts = new HashSet();
            collectShortcuts(shortcuts);
            return Collections.enumeration(shortcuts);
        } finally {
            toolkit.unlockAWT();
        }
    }

    public int countMenus() {
        toolkit.lockAWT();
        try {
            return menuList.size();
        } finally {
            toolkit.unlockAWT();
        }
    }

    public Menu getHelpMenu() {
        toolkit.lockAWT();
        try {
            return helpMenu;
        } finally {
            toolkit.unlockAWT();
        }
    }

    public Menu getMenu(int index) {
        toolkit.lockAWT();
        try {
            return (Menu)menuList.get(index);
        } finally {
            toolkit.unlockAWT();
        }
    }

    public int getMenuCount() {
        toolkit.lockAWT();
        try {
            return menuList.size();
        } finally {
            toolkit.unlockAWT();
        }
    }

    public void setHelpMenu(Menu menu) {
        if (menu == null) {
            throw new NullPointerException();
        }
        toolkit.lockAWT();
        try {
            if (helpMenu == menu) {
                return;
            }
            helpMenu = menu;
            add(menu);
        } finally {
            toolkit.unlockAWT();
        }
    }

    boolean hasDefaultFont() {
        return true;
    }

    void updateParent() {
        MenuContainer parent = getParent();
        if (parent == null) {
            return;
        }
        Frame f = (Frame)parent;
        f.invalidate();
        f.validate();
    }

    MenuItem getItem(int index) {
        return (MenuItem)menuList.get(index);
    }

    int getItemCount() {
        return menuList.size();
    }

    int getWidth() {
        return menuBarState.getWidth();
    }

    int getHeight() {
        return menuBarState.getHeight();
    }

    Point getLocation() {
        Frame f = (Frame)getParent();
        if (f == null) {
            return new Point(0, 0);
        }
        Insets ins = f.getNativeInsets();
        return new Point(ins.left, ins.top);
    }

    Point getScreenLocation() {
        Frame f = (Frame)getParent();
        if (f == null) {
            return new Point(0, 0);
        }
        Insets ins = f.getNativeInsets();
        return new Point(f.x + ins.left, f.y + ins.top);
    }

    void paint(Graphics gr) {
        toolkit.theme.drawMenuBar(menuBarState, gr);
    }

    Rectangle getItemRect(int index) {
        return menuBarState.getItem(index).getItemBounds();
    }

    final void onMouseEvent(int eventId, Point where, int mouseButton, long when, int modifiers) {
        int index = toolkit.theme.getMenuBarItemIndex(menuBarState, where);

        if (box.isActive()) {
            switch (eventId) {
            case MouseEvent.MOUSE_PRESSED:
                endMenu();
                break;
            case MouseEvent.MOUSE_RELEASED:
                if (index >= 0) {
                    selectItem(index);
                } else {
                    endMenu();
                }
                break;
            default:
                if (index >= 0 || getSelectedItemIndex() < 0) {
                    selectItem(index);
                }
            }
        } else {
            if ((index >= 0) && (eventId == MouseEvent.MOUSE_PRESSED)) {
                toolkit.dispatcher.popupDispatcher.activate(box);
                showSubMenu(index);
            } else {
                selectItem(index);
            }
        }
    }

    void onKeyEvent(int eventId, int vKey, long when, int modifiers) {
        if (eventId != KeyEvent.KEY_PRESSED) {
            return;
        }
        Menu subMenu;
        switch (vKey) {
        case KeyEvent.VK_ESCAPE:
            endMenu();
            break;
        case KeyEvent.VK_RIGHT:
        case KeyEvent.VK_LEFT:
            selectNextItem(vKey == KeyEvent.VK_RIGHT);
            subMenu = getSelectedSubmenu();
            if (subMenu != null) {
                subMenu.selectNextItem(true, false);
            }
            break;
        case KeyEvent.VK_UP:
        case KeyEvent.VK_DOWN:
        case KeyEvent.VK_ENTER:
            subMenu = getSelectedSubmenu();
            if (subMenu != null && !subMenu.isVisible()) {
                unfolded = true;
                showSubMenu(getSelectedItemIndex());
                subMenu = getSelectedSubmenu();
                subMenu.selectNextItem(true, false);
            }
        }
    }

    boolean isActive() {
        return box.isActive();
    }

    MenuBar getMenuBar() {
        return this;
    }

    PopupBox getPopupBox() {
        return box;
    }

    void hide() {
        // do nothing
    }

    void itemHidden(MenuComponent mc) {
        unfolded = false;
    }
    Point getSubmenuLocation(int index) {
        return toolkit.theme.getMenuBarItemLocation(menuBarState, index);
    }

    void selectNextItem(boolean forward) {
        super.selectNextItem(forward, unfolded);
    }

    void selectItem(int index) {
        unfolded = (index >= 0);
        super.selectItem(index, true);
    }

    void showSubMenu(int index) {
        unfolded = (index >= 0);
        super.showSubMenu(index);
    }

    void validate() {
        menuBarState.calculate();
        box.updateBounds();
    }

    Graphics getGraphics(MultiRectArea clip) {
        return box.getGraphics(clip);
    }

    AccessibleContext createAccessibleContext() {
        return new AccessibleAWTMenuBar();
    }

    void handleShortcut(KeyEvent ke) {
        MenuShortcut shortcut = MenuShortcut.lookup(ke);
        MenuItem item = getShortcutMenuItem(shortcut);
        if (item != null) {
            item.itemSelected(ke.getWhen(), ke.getModifiersEx());
            ke.consume();
        }
    }

    void collectShortcuts(HashSet shortcuts) {
        for (int i=0; i<menuList.size(); i++) {
            ((Menu)menuList.get(i)).collectShortcuts(shortcuts);
        }
    }
}

