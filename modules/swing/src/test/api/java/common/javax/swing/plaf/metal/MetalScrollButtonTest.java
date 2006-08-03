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

package javax.swing.plaf.metal;

import javax.swing.SwingTestCase;

public class MetalScrollButtonTest extends SwingTestCase{
    private MetalScrollButton button;
    private int width = 20;

    protected void setUp() throws Exception {
        button = new MetalScrollButton(MetalScrollButton.HORIZONTAL, width, false);
    }

    protected void tearDown() throws Exception {
        button = null;
    }

    public void testGetButtonWidth() throws Exception {
        assertEquals(width, button.getButtonWidth());
    }

    public void testFreeStanding() throws Exception {
        button.setFreeStanding(true);
    }
}
