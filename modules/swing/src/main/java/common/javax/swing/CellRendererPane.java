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
 * @author Anton Avtamonov
 * @version $Revision$
 */

package javax.swing;

import java.awt.Component;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.Rectangle;

import javax.accessibility.Accessible;
import javax.accessibility.AccessibleContext;
import javax.accessibility.AccessibleRole;

public class CellRendererPane extends Container implements Accessible {
    protected AccessibleContext accessibleContext;

    protected class AccessibleCellRendererPane extends AccessibleAWTContainer {
        public AccessibleRole getAccessibleRole() {
            return AccessibleRole.PANEL;
        }
    }

    public AccessibleContext getAccessibleContext() {
        if (accessibleContext == null) {
            accessibleContext = new AccessibleCellRendererPane();
        }

        return accessibleContext;
    }

    public void invalidate() {
    }

    public void paint(final Graphics g) {
    }

    public void update(final Graphics g) {
    }

    public void paintComponent(final Graphics g, final Component c, final Container p, final int x, final int y, final int w, final int h) {
        paintComponent(g, c, p, x, y, w, h, false);
    }

    public void paintComponent(final Graphics g, final Component c, final Container p, final Rectangle r) {
        paintComponent(g, c, p, r.x, r.y, r.width, r.height);
    }

    public void paintComponent(final Graphics g, final Component c, final Container p, final int x, final int y, final int w, final int h, final boolean shouldValidate) {
        add(c);
        c.setBounds(x, y, w, h);

        if (shouldValidate) {
            c.validate();
        }

        Graphics newGraphics = g.create(x, y, w, h);
        if (c instanceof JComponent) {
            JComponent jc = (JComponent)c;

            boolean isDoubleBuffered = jc.isDoubleBuffered();
            jc.setDoubleBuffered(false);
            jc.paint(jc.getComponentGraphics(newGraphics));
            jc.setDoubleBuffered(isDoubleBuffered);
        } else {
            c.paint(newGraphics);
        }
        newGraphics.dispose();

        c.setBounds(-w, -h, 0, 0);
    }



    protected void addImpl(final Component c, final Object constraints, final int index) {
        Component[] components = getComponents();
        for (int i = 0; i < components.length; i++) {
            if (c == components[i]) {
                return;
            }
        }

        super.addImpl(c, constraints, index);
    }
}
