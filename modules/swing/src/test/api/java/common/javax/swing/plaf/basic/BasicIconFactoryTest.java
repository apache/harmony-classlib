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
 * @author Sergey Burlak
 * @version $Revision$
 */

package javax.swing.plaf.basic;

import javax.swing.Icon;
import javax.swing.SwingTestCase;

public class BasicIconFactoryTest extends SwingTestCase {
    public void testGetRadioButtonIcon() {
        checkIcon(BasicIconFactory.getRadioButtonIcon(), 13, 13);
        assertSame(BasicIconFactory.getRadioButtonIcon(), BasicIconFactory.getRadioButtonIcon());
    }

    public void testGetMenuArrowIcon() {
        checkIcon(BasicIconFactory.getMenuArrowIcon(), 8, 4);
        assertSame(BasicIconFactory.getMenuArrowIcon(), BasicIconFactory.getMenuArrowIcon());
    }

    public void testGetMenuItemArrowIcon() {
        checkIcon(BasicIconFactory.getMenuItemArrowIcon(), 8, 4);
        assertSame(BasicIconFactory.getMenuItemArrowIcon(), BasicIconFactory.getMenuItemArrowIcon());
    }

    public void testCreateEmptyFrameIcon() {
        checkIcon(BasicIconFactory.createEmptyFrameIcon(), 16, 14);
        assertSame(BasicIconFactory.createEmptyFrameIcon(), BasicIconFactory.createEmptyFrameIcon());
    }

    public void testGetCheckBoxIcon() {
        checkIcon(BasicIconFactory.getCheckBoxIcon(), 13, 13);
        assertSame(BasicIconFactory.getCheckBoxIcon(), BasicIconFactory.getCheckBoxIcon());
    }

    public void testGetCheckBoxMenuItemIcon() {
        checkIcon(BasicIconFactory.getCheckBoxMenuItemIcon(), 9, 9);
        assertSame(BasicIconFactory.getCheckBoxMenuItemIcon(), BasicIconFactory.getCheckBoxMenuItemIcon());
    }

    public void testGetMenuItemCheckIcon() {
        checkIcon(BasicIconFactory.getMenuItemCheckIcon(), 9, 9);
        assertSame(BasicIconFactory.getMenuItemCheckIcon(), BasicIconFactory.getMenuItemCheckIcon());
    }

    public void testGetRadioButtonMenuItemIcon() {
        checkIcon(BasicIconFactory.getRadioButtonMenuItemIcon(), 6, 6);
        assertSame(BasicIconFactory.getRadioButtonMenuItemIcon(), BasicIconFactory.getRadioButtonMenuItemIcon());
    }

    private void checkIcon(final Icon icon, final int height, final int width) {
        assertNotNull(icon);
        assertEquals(height, icon.getIconHeight());
        assertEquals(width, icon.getIconWidth());
    }
}
