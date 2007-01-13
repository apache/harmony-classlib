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
 * @author Sergey Burlak
 * @version $Revision$
 */
package javax.swing.plaf.basic;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.SwingConstants;
import javax.swing.SwingTestCase;

public class BasicArrowButtonTest extends SwingTestCase {
    private BasicArrowButton button;

    @Override
    protected void setUp() throws Exception {
        button = new BasicArrowButton(SwingConstants.NORTH);
    }

    @Override
    protected void tearDown() throws Exception {
        button = null;
    }

    public void testGetPreferredSize() throws Exception {
        assertEquals(new Dimension(16, 16), button.getPreferredSize());
        assertFalse(button.getPreferredSize() == button.getPreferredSize());
    }

    public void testGetMaximumSize() throws Exception {
        assertEquals(new Dimension(2147483647, 2147483647), button.getMaximumSize());
    }

    public void testGetMinimumSize() throws Exception {
        assertEquals(new Dimension(5, 5), button.getMinimumSize());
        assertFalse(button.getMinimumSize() == button.getMinimumSize());
    }

    public void testFocusTraversable() throws Exception {
        assertFalse(button.isFocusTraversable());
    }
    
    /**
     * Regression test for HARMONY-2629 
     * */
    public void testGetBackground() {
        final Color c = Color.red;
        BasicArrowButton b = new BasicArrowButton(240, c, c, c, c);

        System.out.println("parameter background == " + c);
        System.out.println("getBackground()==" + b.getBackground());
        assertSame(c, b.getBackground());
    } 
}
