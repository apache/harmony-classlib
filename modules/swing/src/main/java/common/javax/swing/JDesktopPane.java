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
 * @author Vadim L. Bogdanov
 * @version $Revision$
 */

package javax.swing;

import java.awt.Component;

import java.util.Vector;

import javax.accessibility.Accessible;
import javax.accessibility.AccessibleContext;
import javax.accessibility.AccessibleRole;

import javax.swing.plaf.DesktopPaneUI;

/**
 * A container used to create a virtual desktop.
 *
 */
public class JDesktopPane extends JLayeredPane implements Accessible {

    /**
     * Indicates that the entire content of the component should appear,
     * when it is being dragged.
     */
    public static final int LIVE_DRAG_MODE = 0;

    /**
     * Indicates that an outline should appear instead of the entire content
     * of the component, when it is being dragged.
     */
    public static final int OUTLINE_DRAG_MODE = 1;

    // The style of dragging to use.
    private int dragMode = LIVE_DRAG_MODE;

    // The desktop manager.
    private DesktopManager desktopManager;

    // The currently active internal frame.
    private JInternalFrame selectedFrame;

    public JDesktopPane() {
        setFocusCycleRoot(true);
        updateUI();
    }

    protected class AccessibleJDesktopPane extends AccessibleJComponent {
        protected AccessibleJDesktopPane() {
        }

        public AccessibleRole getAccessibleRole() {
            return AccessibleRole.DESKTOP_PANE;
        }
    }

    /**
     * Sets the UI object for this component.
     *
     * @param ui the UI object to set
     */
    public void setUI(final DesktopPaneUI ui) {
        // setUI() from super (JComponent) should always be called
        super.setUI(ui);
    }

    /**
     * Returns the UI object for this component.
     *
     * @return UI object for this component
     */
    public DesktopPaneUI getUI() {
        return (DesktopPaneUI)ui;
    }

    /**
     * Resets UI object with the default object from <code>UIManager</code>
     */
    public void updateUI() {
        setUI((DesktopPaneUI)UIManager.getUI(this));
    }

    /*
     * Returns all internal frames in the array including iconified frames.
     */
    private JInternalFrame[] getAllFramesInArray(final Component[] array) {
        Vector vector = new Vector(array.length);

        for (int i = 0; i < array.length; i++) {
            if (array[i] instanceof JInternalFrame) {
                // internal frame
                vector.add(array[i]);
            } else if (array[i] instanceof JInternalFrame.JDesktopIcon) {
                // iconified internal frame
                vector.add(((JInternalFrame.JDesktopIcon)array[i]).
                           getInternalFrame());
            }
        }

        JInternalFrame[] frames = new JInternalFrame[vector.size()];
        vector.toArray(frames);
        return frames;
    }

    /**
     * Returns all internal frames in the specified layer. Iconified frames
     * are also taken into account.
     *
     * @param layer the layer to search for internal frames
     *
     * @return all internal frames in the specified layer
     */
    public JInternalFrame[] getAllFramesInLayer(final int layer) {
        return getAllFramesInArray(getComponentsInLayer(layer));
    }

    /**
     * Returns all internal frames in the desktop pane including iconified
     * frames.
     *
     * @return all internal frames in the desktop pane
     */
    public JInternalFrame[] getAllFrames() {
        return getAllFramesInArray(getComponents());
    }

    /**
     * Sets the currently active internal frame.
     *
     * @param f the currently active internal frame
     */
    public void setSelectedFrame(final JInternalFrame f) {
        selectedFrame = f;
    }

    /**
     * Returns the currently active internal frame or <code>null</code>,
     * if there is no active frame.
     *
     * @return the currently active internal frame or <code>null</code>
     */
    public JInternalFrame getSelectedFrame() {
        return selectedFrame;
    }

    /**
     * Sets the desktop manager.
     *
     * @param m the desktop manager
     */
    public void setDesktopManager(final DesktopManager m) {
        DesktopManager oldValue = getDesktopManager();
        desktopManager = m;
        firePropertyChange("desktopManager", oldValue, m);
    }

    /**
     * Returns the desktop manager.
     *
     * @return the desktop manager
     */
    public DesktopManager getDesktopManager() {
        return desktopManager;
    }

    /**
     * Returns the accessible context for the desktop pane.
     *
     * @return the accessible context for the desktop pane
     */
    public AccessibleContext getAccessibleContext() {
        if (accessibleContext == null) {
            accessibleContext = new AccessibleJDesktopPane();
        }

        return accessibleContext;
    }

    /**
     * Returns string representation of this desktop pane.
     *
     * @return string representation of this desktop pane
     */
    protected String paramString() {
        return super.paramString();
    }

    /**
     * Returns the name of the L&F class that renders this component.
     *
     * @return the string "DesktopPaneUI"
     */
    public String getUIClassID() {
        return "DesktopPaneUI";
    }

    /**
     * Always returns true.
     *
     * @return <code>true</code>
     */
    public boolean isOpaque() {
        return true;
    }

    /**
     * Sets the style of dragging used by desktop pane.
     *
     * @param mode the style of dragging to use
     */
    public void setDragMode(final int mode) {
        LookAndFeel.markPropertyNotInstallable(this, "dragMode");
        dragMode = mode;
    }

    /**
     * Returns the style of dragging used by desktop pane.
     *
     * @return the used style of dragging
     */
    public int getDragMode() {
        return dragMode;
    }
}
