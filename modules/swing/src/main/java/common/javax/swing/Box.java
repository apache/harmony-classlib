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

import java.awt.AWTError;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.LayoutManager;
import javax.accessibility.Accessible;
import javax.accessibility.AccessibleContext;
import javax.accessibility.AccessibleRole;

public class Box extends JComponent implements Accessible {

    protected class AccessibleBox extends Container.AccessibleAWTContainer {
        protected AccessibleBox() {
        }

        public AccessibleRole getAccessibleRole() {
            return AccessibleRole.FILLER;
        }
    };

    public static class Filler extends JComponent implements Accessible {

        private Dimension minimumBoxSize;
        private Dimension preferredBoxSize;
        private Dimension maximumBoxSize;

        protected class AccessibleBoxFiller extends Component.AccessibleAWTComponent {
            protected AccessibleBoxFiller() {
            }

            public AccessibleRole getAccessibleRole() {
                return AccessibleRole.FILLER;
            }
        };

        protected AccessibleContext accessibleContext;

        public Filler(final Dimension minimumSize, final Dimension preferredSize,
                      final Dimension maximumSize) {
            super();

            minimumBoxSize = minimumSize;
            preferredBoxSize = preferredSize;
            maximumBoxSize = maximumSize;
        }

        public void changeShape(final Dimension minimumSize,
                                final Dimension preferredSize,
                                final Dimension maximumSize) {
            minimumBoxSize = minimumSize;
            preferredBoxSize = preferredSize;
            maximumBoxSize = maximumSize;

            invalidate();
        }

        public AccessibleContext getAccessibleContext() {
            return (accessibleContext == null) ? (accessibleContext = new AccessibleBoxFiller())
                                               : accessibleContext;
        }

        public Dimension getPreferredSize() {
            return preferredBoxSize;
        }

        public Dimension getMinimumSize() {
            return minimumBoxSize;
        }

        public Dimension getMaximumSize() {
            return maximumBoxSize;
        }

    }

    protected AccessibleContext accessibleContext;

    public Box(final int axisType) {
        super.setLayout(new BoxLayout(this, axisType));
    }

    public AccessibleContext getAccessibleContext() {
        return (accessibleContext == null) ? (accessibleContext = new AccessibleBox())
                                           : accessibleContext;
    }

    public void setLayout(final LayoutManager layout) {
        throw new AWTError("Illegal request");
    }

    public static Component createRigidArea(final Dimension size) {
        return new Filler(new Dimension(size), new Dimension(size),
                          new Dimension(size));
    }

    public static Box createVerticalBox() {
        return new Box(BoxLayout.Y_AXIS);
    }

    public static Box createHorizontalBox() {
        return new Box(BoxLayout.X_AXIS);
    }

    public static Component createVerticalStrut(final int height) {
        return new Filler(new Dimension(0, height), new Dimension(0, height),
                          new Dimension(Short.MAX_VALUE, height));
    }

    public static Component createHorizontalStrut(final int width) {
        return new Filler(new Dimension(width, 0), new Dimension(width, 0),
                          new Dimension(width, Short.MAX_VALUE));
    }

    public static Component createVerticalGlue() {
        return new Filler(new Dimension(0, 0), new Dimension(0, 0),
                          new Dimension(0, Short.MAX_VALUE));
    }

    public static Component createHorizontalGlue() {
        return new Filler(new Dimension(0, 0), new Dimension(0, 0),
                          new Dimension(Short.MAX_VALUE, 0));
    }

    public static Component createGlue() {
        return new Filler(new Dimension(0, 0), new Dimension(0, 0),
                          new Dimension(Short.MAX_VALUE, Short.MAX_VALUE));
    }

}

