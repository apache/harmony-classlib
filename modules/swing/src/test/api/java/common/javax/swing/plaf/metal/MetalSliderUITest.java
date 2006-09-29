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
package javax.swing.plaf.metal;

import javax.swing.JSlider;
import javax.swing.SwingTestCase;

public class MetalSliderUITest extends SwingTestCase {
    private MetalSliderUI sliderUI;
    private JSlider slider;

    protected void setUp() throws Exception {
        slider = new JSlider();
        sliderUI = new MetalSliderUI();
    }

    protected void tearDown() throws Exception {
        sliderUI = null;
        slider = null;
    }

    public void testCreateUI() throws Exception {
        assertNotNull(MetalSliderUI.createUI(slider));
        assertFalse(MetalSliderUI.createUI(slider) == MetalSliderUI.createUI(slider));
    }

    public void testCreatePropertyChangeListener() throws Exception {
        assertNotNull(sliderUI.createPropertyChangeListener(slider));
        assertFalse(sliderUI.createPropertyChangeListener(slider) == sliderUI.createPropertyChangeListener(slider));
        assertTrue(sliderUI.createPropertyChangeListener(slider) instanceof MetalSliderUI.MetalPropertyListener);
    }

    public void testGetThumbOverhang() throws Exception {
        sliderUI.installUI(slider);
        assertEquals(4, sliderUI.getThumbOverhang());
    }
}
