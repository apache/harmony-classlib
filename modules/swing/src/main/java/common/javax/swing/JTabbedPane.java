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
 * @author Vadim Bogdanov
 * @version $Revision$
 */

package javax.swing;

import java.awt.Color;
import java.awt.Component;
import java.awt.Point;
import java.awt.Rectangle;

import java.awt.event.InputEvent;
import java.awt.event.MouseEvent;

import java.io.Serializable;
import java.util.Vector;

import javax.accessibility.Accessible;
import javax.accessibility.AccessibleContext;
import javax.accessibility.AccessibleRole;
import javax.accessibility.AccessibleSelection;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import javax.swing.plaf.TabbedPaneUI;
import javax.swing.plaf.UIResource;

import org.apache.harmony.x.swing.StringConstants;

public class JTabbedPane extends JComponent
    implements Serializable, Accessible, SwingConstants {

    // TODO: implement
    protected class AccessibleJTabbedPane extends AccessibleJComponent
            implements AccessibleSelection, ChangeListener {

        public AccessibleJTabbedPane() {
        }

        public void addAccessibleSelection(final int i) {
            throw new UnsupportedOperationException("Not implemented");
        }

        public void clearAccessibleSelection() {
            throw new UnsupportedOperationException("Not implemented");
        }

        public Accessible getAccessibleAt(final Point p) {
            throw new UnsupportedOperationException("Not implemented");
        }

        public Accessible getAccessibleChild(final int i) {
            throw new UnsupportedOperationException("Not implemented");
        }

        public int getAccessibleChildrenCount() {
            throw new UnsupportedOperationException("Not implemented");
        }

        public AccessibleRole getAccessibleRole() {
            throw new UnsupportedOperationException("Not implemented");
        }

        public AccessibleSelection getAccessibleSelection() {
            throw new UnsupportedOperationException("Not implemented");
        }

        public Accessible getAccessibleSelection(final int i) {
            throw new UnsupportedOperationException("Not implemented");
        }

        public int getAccessibleSelectionCount() {
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

        public void stateChanged(final ChangeEvent e) {
            throw new UnsupportedOperationException("Not implemented");
        }
    }

    protected class ModelListener implements ChangeListener, Serializable {
        public void stateChanged(final ChangeEvent e) {
            fireStateChanged();
        }
    }

    private class JTabInfo extends AbstractButton {
        private Component comp;
        private boolean enabled = true;
        private Icon disabledIcon;

        public JTabInfo(final String title, final Icon icon,
                 final Component comp, final String tip) {
            setModel(new DefaultButtonModel());
            setText(title);
            setIcon(icon);
            setComp(comp);
            setToolTipText(tip);
            setBackground(JTabbedPane.this.getBackground());
            setForeground(JTabbedPane.this.getForeground());
            setMnemonic(-1);
        }

        public void setComp(final Component comp) {
            this.comp = comp;
        }

        public Component getComp() {
            return comp;
        }

        public boolean isEnabled() {
            return enabled;
        }

        public void setEnabled(final boolean enabled) {
            this.enabled = enabled;
        }

        public void setDisabledIcon(final Icon icon) {
            disabledIcon = icon;
        }

        public Icon getDisabledIcon() {
            return disabledIcon;
        }
    }

    public static final int SCROLL_TAB_LAYOUT = 1;

    public static final int WRAP_TAB_LAYOUT = 0;

    protected transient ChangeEvent changeEvent = new ChangeEvent(this);

    protected ChangeListener changeListener;

    protected SingleSelectionModel model;

    protected int tabPlacement = TOP;

    private int tabLayoutPolicy = WRAP_TAB_LAYOUT;
    private Vector tabInfos = new Vector(2);

    public JTabbedPane() {
        this(TOP, WRAP_TAB_LAYOUT);
    }

    public JTabbedPane(final int tabPlacement) {
        this(tabPlacement, WRAP_TAB_LAYOUT);
    }

    public JTabbedPane(final int tabPlacement, final int tabLayoutPolicy) {
        setModel(new DefaultSingleSelectionModel());
        setTabPlacement(tabPlacement);
        setTabLayoutPolicy(tabLayoutPolicy);
        ToolTipManager.sharedInstance().registerComponent(this);

        updateUI();
    }

    /**
     * If <code>component</code> is <code>instanceof UIResource</code>,
     * it is added to the tabbed pane without creating a new tab. It can be
     * used in UI's to add custom components.
     */
    public Component add(final Component component) {
        if (component instanceof UIResource) {
            addImpl(component, null, -1);
        } else {
            insertTab(component.getName(), null, component, null, getTabCount());
        }
        return component;
    }

    public Component add(final Component component, final int index) {
        insertTab(component.getName(), null, component, null, index);
        return component;
    }

    public void add(final Component component, final Object constraints) {
        add(component, constraints, getTabCount());
    }

    public void add(final Component component,
                    final Object constraints,
                    final int index) {
        Icon icon = null;
        String title = null;
        if (constraints instanceof Icon) {
            icon = (Icon)constraints;
        } else if (constraints instanceof String) {
            title = (String)constraints;
        } else {
            title = component.getName();
        }

        insertTab(title, icon, component, null, index);
    }

    public Component add(final String title, final Component component) {
        insertTab(title, null, component, null, getTabCount());
        return component;
    }

    public void addTab(final String title, final Component component) {
        insertTab(title, null, component, null, getTabCount());
    }

    public void addTab(final String title, final Icon icon,
                       final Component component) {
        insertTab(title, icon, component, null, getTabCount());
    }

    public void addTab(final String title, final Icon icon,
                       final Component component, final String tip) {
        insertTab(title, icon, component, tip, getTabCount());
    }

    public void addChangeListener(final ChangeListener l) {
        listenerList.add(ChangeListener.class, l);
    }

    public void removeChangeListener(final ChangeListener l) {
        listenerList.remove(ChangeListener.class, l);
    }

    public ChangeListener[] getChangeListeners() {
        return (ChangeListener[])listenerList.getListeners(ChangeListener.class);
    }

    protected ChangeListener createChangeListener() {
        return new ModelListener();
    }

    protected void fireStateChanged() {
        ChangeListener[] listeners = getChangeListeners();
        for (int i = 0; i < listeners.length; i++) {
            listeners[i].stateChanged(changeEvent);
        }
    }

    public AccessibleContext getAccessibleContext() {
        return accessibleContext == null
            ? (accessibleContext = new AccessibleJTabbedPane())
            : accessibleContext;
    }

    public Rectangle getBoundsAt(final int index) {
        return getUI() != null ? getUI().getTabBounds(this, index) : null;
    }

    public void setComponentAt(final int index, final Component comp) {
        int oldIndex = indexOfComponent(comp);
        if (oldIndex == index) {
            return;
        }
        JTabInfo tabInfo = getTabAt(index);

        if (oldIndex != -1) {
            removeTabAt(oldIndex);
        }
        if (tabInfo.getComp() != comp) {
            removeComponentFromContainer(tabInfo.getComp());
            addComponentToContainer(comp);
        }
        tabInfo.setComp(comp);
    }

    public Component getComponentAt(final int index) {
        return getTabAt(index).getComp();
    }

    public int getTabCount() {
        return tabInfos.size();
    }

    public int getTabRunCount() {
        return getUI() != null ? getUI().getTabRunCount(this) : 0;
    }

    public void setTitleAt(final int index, final String title) {
        getTabAt(index).setText(title);
        repaint();
    }

    public String getTitleAt(final int index) {
        return getTabAt(index).getText();
    }

    public String getToolTipText(final MouseEvent event) {
        int index = indexAtLocation(event.getX(), event.getY());
        return index > -1 ? getToolTipTextAt(index) : super.getToolTipText(event);
    }

    public void setToolTipTextAt(final int index, final String toolTipText) {
        getTabAt(index).setToolTipText(toolTipText);
    }

    public String getToolTipTextAt(final int index) {
        return getTabAt(index).getToolTipText();
    }

    public int indexAtLocation(final int x, final int y) {
        return getUI() != null ? getUI().tabForCoordinate(this, x, y) : -1;
    }

    public int indexOfComponent(final Component comp) {
        for (int i = 0; i < tabInfos.size(); i++) {
            if (comp == getComponentAt(i)) {
                return i;
            }
        }
        return -1;
    }

    public int indexOfTab(final Icon icon) {
        for (int i = 0; i < tabInfos.size(); i++) {
            if (icon == getIconAt(i)) {
                return i;
            }
        }
        return -1;
    }

    public int indexOfTab(final String title) {
        for (int i = 0; i < tabInfos.size(); i++) {
            if (title == getTitleAt(i)) {
                return i;
            }
        }
        return -1;
    }

    public void insertTab(final String title, final Icon icon,
                          final Component comp, final String tip,
                          final int index) {
        int oldIndex = comp != null ? indexOfComponent(comp) : -1;
        if (oldIndex != -1) {
            tabInfos.remove(oldIndex);
        }
        String realTitle = title == null ? "" : title;
        JTabInfo tabInfo = new JTabInfo(realTitle, icon, comp, tip);
        tabInfos.add(index, tabInfo);

        if (oldIndex == -1) {
            addComponentToContainer(comp);
        }

        if (getTabCount() == 1) {
            setSelectedIndex(0);
        } else if (index <= getSelectedIndex() && (oldIndex == -1 || oldIndex > getSelectedIndex())) {
            setSelectedIndex(getSelectedIndex() + 1);
        }

        repaint();
    }

    protected String paramString() {
        return super.paramString();
    }

    /**
     * If <code>component</code> is <code>instanceof UIResource</code>,
     * it is removed without removing any tab.
     */
    public void remove(final Component comp) {
        if (comp instanceof UIResource) {
            removeComponentFromContainer(comp);
            return;
        }

        int index = indexOfComponent(comp);
        if (index != -1) {
            removeTabAt(index);
        }
    }

    /**
     * If <code>component</code> is <code>instanceof UIResource</code>,
     * it is removed without removing any tab.
     */
    public void remove(final int index) {
        if (getComponent(index) instanceof UIResource) {
            super.remove(index);
            return;
        }

        removeTabAt(index);
    }

    public void removeTabAt(final int index) {
        Component comp = getComponentAt(index);

        if (getSelectedIndex() >= getTabCount() - 1) {
            setSelectedIndex(getTabCount() - 2);
        }

        tabInfos.remove(index);

        removeComponentFromContainer(comp);

        comp.setVisible(true);

        repaint();
    }

    public void removeAll() {
        super.removeAll();
        tabInfos.removeAllElements();
    }

    public void setBackgroundAt(final int index, final Color background) {
        Color realBackground = background == null ? getBackground() : background;
        getTabAt(index).setBackground(realBackground);

        repaint();
    }

    public Color getBackgroundAt(final int index) {
        return getTabAt(index).getBackground();
    }

    public void setForegroundAt(final int index, final Color foreground) {
        Color realForeground = foreground == null ? getForeground() : foreground;
        getTabAt(index).setForeground(realForeground);
        repaint();
    }

    public Color getForegroundAt(final int index) {
        return getTabAt(index).getForeground();
    }

    public void setDisabledIconAt(final int index, final Icon disabledIcon) {
        getTabAt(index).setDisabledIcon(disabledIcon);
    }

    public Icon getDisabledIconAt(final int index) {
        return getTabAt(index).getDisabledIcon();
    }

    public void setDisplayedMnemonicIndexAt(final int tabIndex,
                                            final int mnemonicIndex) {
        getTabAt(tabIndex).setDisplayedMnemonicIndex(mnemonicIndex);
    }

    public int getDisplayedMnemonicIndexAt(final int index) {
        return getTabAt(index).getDisplayedMnemonicIndex();
    }

    public void setEnabledAt(final int index, final boolean enabled) {
        getTabAt(index).setEnabled(enabled);
        repaint();
    }

    public boolean isEnabledAt(final int index) {
        return getTabAt(index).isEnabled();
    }

    public void setIconAt(final int index, final Icon icon) {
        getTabAt(index).setIcon(icon);
        repaint();
    }

    public Icon getIconAt(final int index) {
        return getTabAt(index).getIcon();
    }

    public void setMnemonicAt(final int tabIndex, final int mnemonic) {
        int oldValue = getMnemonicAt(tabIndex);
        if (oldValue == mnemonic) {
            return;
        }

        InputMap inputMap = getInputMap(WHEN_IN_FOCUSED_WINDOW, true);
        inputMap.remove(KeyStroke.getKeyStroke(oldValue, InputEvent.ALT_DOWN_MASK));
        inputMap.put(KeyStroke.getKeyStroke(mnemonic, InputEvent.ALT_DOWN_MASK),
                     StringConstants.MNEMONIC_ACTION);

        getTabAt(tabIndex).setMnemonic(mnemonic);
        repaint();
    }

    public int getMnemonicAt(final int index) {
        return getTabAt(index).getMnemonic();
    }

    public void setModel(final SingleSelectionModel model) {
        if (changeListener == null) {
            changeListener = createChangeListener();
        }

        SingleSelectionModel oldValue = this.model;
        if (oldValue != null) {
            oldValue.removeChangeListener(changeListener);
        }

        this.model = model;
        if (model != null) {
            model.addChangeListener(changeListener);
        }

        firePropertyChange("model", oldValue, model);
    }

    public SingleSelectionModel getModel() {
        return model;
    }

    public void setSelectedComponent(final Component comp) {
        int index = indexOfComponent(comp);
        if (index == -1) {
            throw new IllegalArgumentException(
                "Component not found in the tabbed pane");
        }

        setSelectedIndex(index);
    }

    public Component getSelectedComponent() {
        return getModel().isSelected() ? getComponentAt(getSelectedIndex()) : null;
    }

    public void setSelectedIndex(final int index) {
        if (index < -1 || index >= getTabCount()) {
            throw new IndexOutOfBoundsException("index < -1 || index >= tab count");
        }

        getModel().setSelectedIndex(index);
    }

    public int getSelectedIndex() {
        return getModel().getSelectedIndex();
    }

    public void setTabLayoutPolicy(final int tabLayoutPolicy) {
        if (tabLayoutPolicy != WRAP_TAB_LAYOUT
                && tabLayoutPolicy != SCROLL_TAB_LAYOUT) {
            throw new IllegalArgumentException("invalid tabLayoutPolicy");
        }

        int oldValue = this.tabLayoutPolicy;
        this.tabLayoutPolicy = tabLayoutPolicy;
        firePropertyChange("tabLayoutPolicy", oldValue, tabLayoutPolicy);
    }

    public int getTabLayoutPolicy() {
        return tabLayoutPolicy;
    }

    public void setTabPlacement(final int tabPlacement) {
        if (tabPlacement != TOP
                && tabPlacement != BOTTOM
                && tabPlacement != LEFT
                && tabPlacement != RIGHT) {
            throw new IllegalArgumentException("invalid tabPlacement");
        }

        int oldValue = this.tabPlacement;
        this.tabPlacement = tabPlacement;
        firePropertyChange("tabPlacement", oldValue, tabPlacement);
    }

    public int getTabPlacement() {
        return tabPlacement;
    }

    public void setUI(final TabbedPaneUI ui) {
        super.setUI(ui);
    }

    public TabbedPaneUI getUI() {
        return (TabbedPaneUI)ui;
    }

    public String getUIClassID() {
        return "TabbedPaneUI";
    }

    public void updateUI() {
        setUI((TabbedPaneUI)UIManager.getUI(this));
    }

    private JTabInfo getTabAt(final int index) {
        return (JTabInfo)tabInfos.get(index);
    }

    private void addComponentToContainer(final Component comp) {
        if (comp != null) {
            comp.setVisible(false);
            if (getComponentZOrder(comp) == -1) {
                addImpl(comp, null, -1);
            }
        }
    }

    private void removeComponentFromContainer(final Component comp) {
        int componentIndex = getComponentZOrder(comp);
        if (componentIndex != -1) {
            super.remove(componentIndex);
        }
    }
}
