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
 * @author Vadim L. Bogdanov
 * @version $Revision$
 */

package javax.swing;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Rectangle;

import java.beans.PropertyVetoException;

import java.io.Serializable;

import org.apache.harmony.x.swing.Utilities;


public class DefaultDesktopManager implements DesktopManager, Serializable {
    private static final String WAS_ICON_PROPERTY = "wasIcon";

    private transient boolean isOutlineDragging;
    private transient Rectangle prevOutlineBounds;
    // is true if we have begun to drag/resize but didn't move the mouse
    private transient boolean shouldClearOutline = true;

    public void minimizeFrame(final JInternalFrame f) {
        Rectangle bounds = getPreviousBounds(f);
        setBoundsForFrame(f, bounds.x, bounds.y, bounds.width, bounds.height);
    }

    public void maximizeFrame(final JInternalFrame f) {
        Container desktop = f.getParent();
        if (desktop == null) {
            return;
        }

        setPreviousBounds(f, f.getBounds());
        Rectangle innerBounds = getInnerBounds(desktop);
        setBoundsForFrame(f, innerBounds.x, innerBounds.y,
                          innerBounds.width, innerBounds.height);

        try {
            f.setSelected(true);
        } catch (final PropertyVetoException e) {
        }
    }

    public void iconifyFrame(final JInternalFrame f) {
        Container desktop = f.getParent();
        if (desktop == null) {
            return;
        }

        if (!wasIcon(f)) {
            f.getDesktopIcon().setBounds(getBoundsForIconOf(f));
        }
        JLayeredPane.putLayer(f.getDesktopIcon(), f.getLayer());
        try {
            f.setSelected(false);
        } catch (final PropertyVetoException e) {
        }
        desktop.remove(f);
        desktop.add(f.getDesktopIcon());
        setWasIcon(f, Boolean.TRUE);
        moveSelectionToNextFrame(desktop);

        repaint(desktop, f.getBounds());
    }

    public void deiconifyFrame(final JInternalFrame f) {
        addFrame(f, true);
    }

    public void openFrame(final JInternalFrame f) {
        addFrame(f, false);
    }

    public void deactivateFrame(final JInternalFrame f) {
        JDesktopPane desktop = f.getDesktopPane();
        if (desktop != null) {
            desktop.setSelectedFrame(null);
        }
    }

    public void closeFrame(final JInternalFrame f) {
        try {
            f.setSelected(false);
        } catch (final PropertyVetoException e) {
        }

        removeIconFor(f);

        Container desktop = f.getParent();
        if (desktop == null) {
            return;
        }
        desktop.remove(f);
        repaint(desktop, f.getBounds());
        moveSelectionToNextFrame(desktop);
    }

    public void activateFrame(final JInternalFrame f) {
        JDesktopPane desktop = f.getDesktopPane();
        if (desktop == null || desktop.getSelectedFrame() == f) {
            return;
        }

        if (desktop.getSelectedFrame() != null) {
            try {
                desktop.getSelectedFrame().setSelected(false);
            } catch (final PropertyVetoException e) {
            }
        }
        desktop.moveToFront(f);
        desktop.setSelectedFrame(f);
    }

    public void setBoundsForFrame(final JComponent f, final int x, final int y,
                                  final int width, final int height) {
        f.setBounds(x, y, width, height);
    }

    public void beginResizingFrame(final JComponent f, final int direction) {
        beginDragFrameOperation(f);
    }

    public void resizeFrame(final JComponent f, final int x, final int y,
            final int width, final int height) {
        if (isOutlineDragging) {
            moveFrameOutline(f, x, y, width, height);
        } else {
            setBoundsForFrame(f, x, y, width, height);
        }
    }

    public void endResizingFrame(final JComponent f) {
        endDragFrameOperation(f);
    }

    public void beginDraggingFrame(final JComponent f) {
        beginDragFrameOperation(f);
    }

    public void dragFrame(final JComponent f, final int x, final int y) {
        if (isOutlineDragging) {
            moveFrameOutline(f, x, y,
                             prevOutlineBounds.width, prevOutlineBounds.height);
        } else {
            setBoundsForFrame(f, x, y, f.getWidth(), f.getHeight());
        }
    }

    public void endDraggingFrame(final JComponent f) {
        endDragFrameOperation(f);
    }

    protected void setWasIcon(final JInternalFrame f, final Boolean value) {
        f.putClientProperty(WAS_ICON_PROPERTY, value);
    }

    protected boolean wasIcon(final JInternalFrame f) {
        Object value = f.getClientProperty(WAS_ICON_PROPERTY);
        return value instanceof Boolean ? ((Boolean)value).booleanValue() : false;
    }

    protected void setPreviousBounds(final JInternalFrame f,
                                     final Rectangle bounds) {
        f.setNormalBounds(bounds);
    }

    protected Rectangle getPreviousBounds(final JInternalFrame f) {
        return f.getNormalBounds();
    }

    protected Rectangle getBoundsForIconOf(final JInternalFrame f) {
        if (wasIcon(f)) {
            return f.getDesktopIcon().getBounds();
        }

        Dimension iconSize = f.getDesktopIcon().getSize();
        Rectangle result = new Rectangle(0, 0, iconSize.width, iconSize.height);

        Container desktop = f.getParent();
        if (desktop == null) {
            return result;
        }

        Rectangle innerBounds = getInnerBounds(desktop);
        // maximum x position
        int totalWidth = innerBounds.width - iconSize.width;
        if (totalWidth < 0) {
            totalWidth = 0;
        }

        result.y = (int)innerBounds.getMaxY() - iconSize.height;
        while (true) {
            for (result.x = innerBounds.x;
                 result.x <= totalWidth;
                 result.x += iconSize.width) {

                if (isFreeDesktopIconPosition(desktop, result)) {
                    return result;
                }
            }
            result.y -= iconSize.height;
        }
    }

    protected void removeIconFor(final JInternalFrame f) {
        Container desktop = f.getDesktopIcon().getParent();
        if (desktop == null) {
            return;
        }

        desktop.remove(f.getDesktopIcon());
        repaint(desktop, f.getDesktopIcon().getBounds());
    }

    private boolean isFreeDesktopIconPosition(final Container desktop,
                                              final Rectangle bounds) {
        for (int i = 0; i < desktop.getComponentCount(); i++) {
            Component c = desktop.getComponent(i);

            Rectangle occupiedBouds = null;
            if (c instanceof JInternalFrame.JDesktopIcon) {
                occupiedBouds = c.getBounds();
            } else if (c instanceof JInternalFrame) {
                JInternalFrame f = (JInternalFrame) c;
                if (wasIcon(f)) {
                    occupiedBouds = f.getDesktopIcon().getBounds();
                }
            }

            if (occupiedBouds != null && bounds.intersects(occupiedBouds)) {
                return false;
            }
        }
        return true;
    }

    private void moveSelectionToNextFrame(final Container desktop) {
        for (int i = 0; i < desktop.getComponentCount(); i++) {
            if (desktop.getComponent(i) instanceof JInternalFrame) {
                try {
                    ((JInternalFrame)desktop.getComponent(i)).setSelected(true);
                    break;
                } catch (final PropertyVetoException e) {
                }
            }
        }
    }

    private void paintOutline(final JComponent f, final int x, final int y,
                              final int width, final int height) {
        Graphics g = f.getParent().getGraphics();
        g = g.create();
        g.setXORMode(Color.WHITE);
        if (shouldClearOutline) {
            g.drawRect(prevOutlineBounds.x, prevOutlineBounds.y,
                       prevOutlineBounds.width - 1, prevOutlineBounds.height - 1);
        } else {
            shouldClearOutline = true;
        }
        g.drawRect(x, y, width - 1, height - 1);
        g.dispose();
    }

    private void repaint(final Container desktop, final Rectangle clipRect) {
        desktop.repaint(clipRect.x, clipRect.y,
                        clipRect.width, clipRect.height);
    }

    private void addFrame(final JInternalFrame f, final boolean select) {
        Container desktop = f.getDesktopIcon().getParent();
        if (desktop == null) {
            return;
        }

        removeIconFor(f);
        if (!select) {
            desktop.add(f);
        } else {
            desktop.add(f, 0);
            try {
                f.setSelected(true);
            } catch (final PropertyVetoException e) {
            }
        }
    }

    private void beginDragFrameOperation(final JComponent f) {
        isOutlineDragging = false;
        if (!(f.getParent() instanceof JDesktopPane)) {
            return;
        }

        JDesktopPane desktop = (JDesktopPane)f.getParent();
        if (desktop.getDragMode() == JDesktopPane.OUTLINE_DRAG_MODE) {
            isOutlineDragging = true;
            prevOutlineBounds = f.getBounds(prevOutlineBounds);
            shouldClearOutline = false;
        }
    }

    private void endDragFrameOperation(final JComponent f) {
        if (isOutlineDragging) {
            setBoundsForFrame(f, prevOutlineBounds.x, prevOutlineBounds.y,
                prevOutlineBounds.width, prevOutlineBounds.height);
            isOutlineDragging = false;
        }
    }

    private void moveFrameOutline(final JComponent f, final int x, final int y,
                                  final int width, final int height) {
        paintOutline(f, x, y, width, height);
        prevOutlineBounds.setBounds(x, y, width, height);
    }

    private Rectangle getInnerBounds(final Container parent) {
        Rectangle innerBounds = new Rectangle(0, 0, parent.getWidth(),
                                              parent.getHeight());
        return Utilities.subtractInsets(innerBounds, parent.getInsets());
    }
}
