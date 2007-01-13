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

import java.awt.Point;
import java.util.Hashtable;
import javax.swing.JLabel;
import javax.swing.JSlider;
import javax.swing.SwingTestCase;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class BasicSliderUITest extends SwingTestCase {
    private BasicSliderUI sliderUI;

    private JSlider slider;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        try {
            UIManager.setLookAndFeel(new BasicLookAndFeel() {
                private static final long serialVersionUID = 1L;

                @Override
                public boolean isNativeLookAndFeel() {
                    return true;
                }

                @Override
                public boolean isSupportedLookAndFeel() {
                    return true;
                }

                @Override
                public String getDescription() {
                    return "";
                }

                @Override
                public String getID() {
                    return "";
                }

                @Override
                public String getName() {
                    return "";
                }
            });
        } catch (UnsupportedLookAndFeelException e1) {
            e1.printStackTrace();
        }
        slider = new JSlider();
        sliderUI = new BasicSliderUI(slider);
    }

    @Override
    protected void tearDown() throws Exception {
        sliderUI = null;
        slider = null;
        super.tearDown();
    }

    public void testCreateUI() throws Exception {
        assertNotNull(BasicSliderUI.createUI(slider));
        assertFalse(BasicSliderUI.createUI(slider) == BasicSliderUI.createUI(slider));
    }

    public void testCreateTrackListener() throws Exception {
        assertNotNull(sliderUI.createTrackListener(slider));
        assertFalse(sliderUI.createTrackListener(slider) == sliderUI
                .createTrackListener(slider));
    }

    public void testCreateChangeListener() throws Exception {
        assertNotNull(sliderUI.createChangeListener(slider));
        assertFalse(sliderUI.createChangeListener(slider) == sliderUI
                .createChangeListener(slider));
    }

    public void testCreateComponentListener() throws Exception {
        assertNotNull(sliderUI.createComponentListener(slider));
        assertFalse(sliderUI.createComponentListener(slider) == sliderUI
                .createComponentListener(slider));
    }

    public void testCreateScrollListener() throws Exception {
        assertNotNull(sliderUI.createScrollListener(slider));
        assertFalse(sliderUI.createScrollListener(slider) == sliderUI
                .createScrollListener(slider));
    }

    public void testCreatePropertyChangeListener() throws Exception {
        assertNotNull(sliderUI.createPropertyChangeListener(slider));
        assertFalse(sliderUI.createPropertyChangeListener(slider) == sliderUI
                .createPropertyChangeListener(slider));
    }

    public void testGetShadowColor() throws Exception {
        assertNull(sliderUI.getShadowColor());
        sliderUI.installUI(slider);
        assertEquals(UIManager.getColor("Slider.shadow"), sliderUI.getShadowColor());
    }

    public void testGetHighlightColor() throws Exception {
        assertNull(sliderUI.getHighlightColor());
        sliderUI.installUI(slider);
        assertEquals(UIManager.getColor("Slider.highlight"), sliderUI.getHighlightColor());
    }

    public void testGetFocusColor() throws Exception {
        assertNull(sliderUI.getFocusColor());
        sliderUI.installUI(slider);
        assertEquals(UIManager.getColor("Slider.focus"), sliderUI.getFocusColor());
    }

    public void testGetLowestValueLabel() throws Exception {
        sliderUI.installUI(slider);
        slider.setLabelTable(slider.createStandardLabels(1));
        assertEquals("0", ((JLabel) sliderUI.getLowestValueLabel()).getText());
        slider.setLabelTable(slider.createStandardLabels(2, 57));
        assertEquals("57", ((JLabel) sliderUI.getLowestValueLabel()).getText());
    }

    public void testGetHighestValueLabel() throws Exception {
        sliderUI.installUI(slider);
        slider.setLabelTable(slider.createStandardLabels(1));
        assertEquals("100", ((JLabel) sliderUI.getHighestValueLabel()).getText());
        slider.setLabelTable(slider.createStandardLabels(2, 56));
        assertEquals("100", ((JLabel) sliderUI.getHighestValueLabel()).getText());
    }

    public void testGetWidthOfHighValueLabel() throws Exception {
        sliderUI.installUI(slider);
        Hashtable<Integer, JLabel> t = new Hashtable<Integer, JLabel>();
        t.put(new Integer("1"), new JLabel("1"));
        t.put(new Integer("100"), new JLabel("100"));
        JLabel label = new JLabel("1000000");
        t.put(new Integer("1000000"), label);
        slider.setLabelTable(t);
        assertEquals(label.getWidth(), sliderUI.getWidthOfHighValueLabel());
    }

    public void testGetWidthOfLowValueLabel() throws Exception {
        sliderUI.installUI(slider);
        Hashtable<Integer, JLabel> t = new Hashtable<Integer, JLabel>();
        JLabel label = new JLabel("1");
        t.put(new Integer("1"), label);
        t.put(new Integer("100"), new JLabel("100"));
        t.put(new Integer("1000000"), new JLabel("1000000"));
        slider.setLabelTable(t);
        assertEquals(label.getWidth(), sliderUI.getWidthOfLowValueLabel());
    }

    public void testGetHightOfHighValueLabel() throws Exception {
        sliderUI.installUI(slider);
        Hashtable<Integer, JLabel> t = new Hashtable<Integer, JLabel>();
        t.put(new Integer("1"), new JLabel("1"));
        t.put(new Integer("100"), new JLabel("100"));
        JLabel label = new JLabel("1000000");
        t.put(new Integer("1000000"), label);
        slider.setLabelTable(t);
        assertEquals(label.getHeight(), sliderUI.getHeightOfHighValueLabel());
    }

    public void testGetHeightOfLowValueLabel() throws Exception {
        sliderUI.installUI(slider);
        Hashtable<Integer, JLabel> t = new Hashtable<Integer, JLabel>();
        JLabel label = new JLabel("1");
        t.put(new Integer("1"), label);
        t.put(new Integer("100"), new JLabel("100"));
        t.put(new Integer("1000000"), new JLabel("1000000"));
        slider.setLabelTable(t);
        assertEquals(label.getHeight(), sliderUI.getHeightOfLowValueLabel());
    }

    public void testGetWidthOfWidestLabel() throws Exception {
        sliderUI.installUI(slider);
        Hashtable<Integer, JLabel> t = new Hashtable<Integer, JLabel>();
        t.put(new Integer("1"), new JLabel("1"));
        JLabel label = new JLabel("___________100");
        t.put(new Integer("100"), label);
        t.put(new Integer("1000000"), new JLabel("1000000"));
        slider.setLabelTable(t);
        assertEquals(label.getWidth(), sliderUI.getWidthOfWidestLabel());
    }

    public void testGetHeightOfTallestLabel() throws Exception {
        sliderUI.installUI(slider);
        Hashtable<Integer, JLabel> t = new Hashtable<Integer, JLabel>();
        JLabel label = new JLabel("1");
        label.setFont(label.getFont().deriveFont(50f));
        t.put(new Integer("1"), label);
        t.put(new Integer("100"), new JLabel("100"));
        t.put(new Integer("1000000"), new JLabel("1000000"));
        slider.setLabelTable(t);
        assertEquals(label.getHeight(), sliderUI.getHeightOfTallestLabel());
    }

    public void testSetThumbLocation() throws Exception {
        sliderUI.installUI(slider);
        sliderUI.setThumbLocation(200, 500);
        assertEquals(new Point(200, 500), sliderUI.thumbRect.getLocation());
        sliderUI.setThumbLocation(200, -500);
        assertEquals(new Point(200, -500), sliderUI.thumbRect.getLocation());
        sliderUI.setThumbLocation(-200, 500);
        assertEquals(new Point(-200, 500), sliderUI.thumbRect.getLocation());
    }
    
    /**
     * Regression test for HARMONY-2591
     * */
    public void testActionScrollerEnabled() {
        BasicSliderUI.ActionScroller m = sliderUI.new ActionScroller(new JSlider(),
                3, true);
        assertTrue(m.isEnabled());
    } 
    
}
