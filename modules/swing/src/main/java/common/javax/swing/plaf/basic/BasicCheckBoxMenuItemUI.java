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
* @author Alexander T. Simbirtsev
* @version $Revision$
*/
package javax.swing.plaf.basic;

import java.awt.event.MouseEvent;

import javax.swing.JComponent;
import javax.swing.JMenuItem;
import javax.swing.MenuElement;
import javax.swing.MenuSelectionManager;
import javax.swing.plaf.ComponentUI;

public class BasicCheckBoxMenuItemUI extends BasicMenuItemUI {

    private static final String PROPERTY_PREFIX = "CheckBoxMenuItem";

    public static ComponentUI createUI(final JComponent b) {
        return new BasicCheckBoxMenuItemUI();
    }

    protected String getPropertyPrefix() {
        return PROPERTY_PREFIX;
    }

    public void processMouseEvent(final JMenuItem item,
                                  final MouseEvent e,
                                  final MenuElement[] path,
                                  final MenuSelectionManager manager) {

    }
}
