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
 * @author Sergey Burlak
 * @version $Revision$
 */

package javax.swing.plaf.basic;

import javax.swing.JComponent;
import javax.swing.LookAndFeel;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.ViewportUI;

public class BasicViewportUI extends ViewportUI {

    private static BasicViewportUI basicViewportUI;

    public BasicViewportUI() {
    }

    public void uninstallUI(final JComponent c) {
        uninstallDefaults(c);
    }

    protected void uninstallDefaults(final JComponent c) {
    }

    public void installUI(final JComponent c) {
        installDefaults(c);
    }

    protected void installDefaults(final JComponent c) {
        LookAndFeel.installColorsAndFont(c, "Viewport.background", "Viewport.foreground", "Viewport.font");
        LookAndFeel.installProperty(c, "opaque", Boolean.TRUE);
    }

    public static ComponentUI createUI(final JComponent c) {
        if (basicViewportUI == null) {
            basicViewportUI = new BasicViewportUI();
        }

        return basicViewportUI;
    }

}

