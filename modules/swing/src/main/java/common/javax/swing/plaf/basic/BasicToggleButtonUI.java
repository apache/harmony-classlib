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
 * @author Alexander T. Simbirtsev
 * @version $Revision$
 * Created on 25.04.2005

 */
package javax.swing.plaf.basic;

import java.awt.Graphics;
import java.awt.Rectangle;

import javax.swing.AbstractButton;
import javax.swing.JComponent;
import javax.swing.plaf.ComponentUI;

public class BasicToggleButtonUI extends BasicButtonUI {

    private static final String PROPERTY_PREFIX = "ToggleButton.";

    private static BasicToggleButtonUI basicToggleButtonUI;

    public static ComponentUI createUI(final JComponent b) {
        if (basicToggleButtonUI == null) {
            basicToggleButtonUI = new BasicToggleButtonUI();
        }
        return basicToggleButtonUI;
    }

    protected String getPropertyPrefix() {
        return PROPERTY_PREFIX;
    }

    protected int getTextShiftOffset() {
        return 0;
    }

    protected void paintIcon(final Graphics g, final AbstractButton b, final Rectangle iconRect) {
        super.paintIcon(g, b, iconRect);
    }
}
