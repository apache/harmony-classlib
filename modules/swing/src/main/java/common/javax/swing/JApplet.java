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
 * @version $Revision: 1.1 $
 */

package javax.swing;

import java.applet.Applet;

import java.awt.AWTEvent;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.HeadlessException;
import java.awt.KeyboardFocusManager;
import java.awt.LayoutManager;

import javax.accessibility.Accessible;
import javax.accessibility.AccessibleContext;

public class JApplet extends Applet implements Accessible, RootPaneContainer {
    protected JRootPane rootPane;
    protected boolean rootPaneCheckingEnabled;
    protected AccessibleContext accessibleContext;

    public JApplet() throws HeadlessException {
        setLayout(new BorderLayout());

        setRootPaneCheckingEnabled(true);

        setRootPane(createRootPane());

        setLocale(JComponent.getDefaultLocale());

        setBackground(Color.white);

        // enable events
        enableEvents(AWTEvent.KEY_EVENT_MASK);

        setFocusTraversalPolicyProvider(true);

        setFocusTraversalPolicy(KeyboardFocusManager.
                getCurrentKeyboardFocusManager().
                getDefaultFocusTraversalPolicy());
    }

    protected class AccessibleJApplet extends AccessibleApplet {
        protected AccessibleJApplet() {
        }
    }

    protected void addImpl(final Component comp, final Object constraints,
                           final int index) {
        if (isRootPaneCheckingEnabled()) {
            getContentPane().add(comp, constraints, index);
            return;
        }

        super.addImpl(comp, constraints, index);
    }

    protected void setRootPane(final JRootPane root) {
        if (rootPane != null) {
            remove(rootPane);
        }

        rootPane = root;

        if (root != null) {
            super.addImpl(root, null, 0);
        }
    }

    public JRootPane getRootPane() {
        return rootPane;
    }

    protected JRootPane createRootPane() {
        return new JRootPane();
    }

    public void setJMenuBar(final JMenuBar menuBar) {
        getRootPane().setJMenuBar(menuBar);
    }

    public JMenuBar getJMenuBar() {
        return getRootPane().getJMenuBar();
    }

    public void setLayeredPane(final JLayeredPane layeredPane) {
        getRootPane().setLayeredPane(layeredPane);
    }

    public JLayeredPane getLayeredPane() {
        return getRootPane().getLayeredPane();
    }

    public AccessibleContext getAccessibleContext() {
        if (accessibleContext == null) {
            accessibleContext = new AccessibleJApplet();
        }

        return accessibleContext;
    }

    public void setLayout(final LayoutManager layout) {
        if (isRootPaneCheckingEnabled()) {
            getContentPane().setLayout(layout);
        } else {
            super.setLayout(layout);
        }
    }

    /**
     * Just calls paint(g). This method was overridden to prevent
     * an unnecessary call to clear the background.
     *
     * @param g - the graphics context to paint
     */
    public void update(final Graphics g) {
        paint(g);
    }

    public void setContentPane(final Container contentPane) {
        getRootPane().setContentPane(contentPane);
    }

    public Container getContentPane() {
        return getRootPane().getContentPane();
    }

    public void setGlassPane(final Component glassPane) {
        getRootPane().setGlassPane(glassPane);
    }

    public void remove(final Component comp) {
        if (comp == getRootPane()) {
            // remove directly from JApplet
            super.remove(comp);
        } else {
            getContentPane().remove(comp);
        }
    }

    public Component getGlassPane() {
        return getRootPane().getGlassPane();
    }

    protected void setRootPaneCheckingEnabled(final boolean enabled) {
        rootPaneCheckingEnabled = enabled;
    }

    protected boolean isRootPaneCheckingEnabled() {
        return rootPaneCheckingEnabled;
    }
}
