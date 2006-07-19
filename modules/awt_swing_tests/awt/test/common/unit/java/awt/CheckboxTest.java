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
 * @author Dmitry A. Durnev
 * @version $Revision$
 */
package java.awt;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;

import junit.framework.TestCase;

/**
 * CheckboxTest
 */
public class CheckboxTest extends TestCase {

    class TestCheckbox extends Checkbox {
       
        public TestCheckbox() throws HeadlessException {
            super();
        }

        public TestCheckbox(String arg0) throws HeadlessException {
            super(arg0);
        }

        public TestCheckbox(String arg0, boolean arg1) throws HeadlessException {
            super(arg0, arg1);
        }

        public TestCheckbox(String arg0, boolean arg1, CheckboxGroup arg2)
                throws HeadlessException {
            super(arg0, arg1, arg2);
        }

        public TestCheckbox(String arg0, CheckboxGroup arg1, boolean arg2)
                throws HeadlessException {
            super(arg0, arg1, arg2);
        }

        public void paint(Graphics g) {
            super.paint(g);
            EventQueue.invokeLater(new Runnable() {
                public void run() {
                    paintFlag = true;
                }
            });
        }
    }

    private TestCheckbox checkbox;
    private Frame frame;
    private Insets insets;
    private boolean eventProcessed;
    private boolean eventExpected;
    private boolean paintFlag;

    /*
     * @see TestCase#setUp()
     */
    protected void setUp() throws Exception {
        super.setUp();

        frame = new Frame();
        checkbox = new TestCheckbox("Checkbox");
        frame.add(checkbox);
        frame.setBounds(0, 0, 100, 100);
        paintFlag = false;
        frame.setVisible(true);
        while (!paintFlag) {
            Thread.yield();
        }
        insets = frame.getInsets();
        paintFlag = false;
        frame.setSize(200 + insets.left + insets.right,
                      200 + insets.top + insets.bottom);
        frame.validate();
        while (!paintFlag) {
            Thread.yield();
        }
    }

    /*
     * @see TestCase#tearDown()
     */
    protected void tearDown() throws Exception {
        frame.dispose();
        super.tearDown();
    }

    public void testCheckbox() {
        checkbox = new TestCheckbox();

        assertEquals("", checkbox.getLabel());
        assertFalse(checkbox.getState());
        assertNull(checkbox.getCheckboxGroup());
    }

    public void testCheckboxStringBoolean() {
        String text = "checkbox";
        boolean checked = true;
        checkbox = new TestCheckbox(text, checked);
        checkCheckbox(text, checked, null);
    }

    public void testCheckboxString() {
        checkCheckbox("Checkbox", false, null);
    }

    public void testCheckboxStringBooleanCheckboxGroup() {
        String text = "checkbox";
        boolean checked = false;
        CheckboxGroup group = new CheckboxGroup();
        checkbox = new TestCheckbox(text, checked, group);
        checkCheckbox(text, checked, group);
    }

    public void testCheckboxStringCheckboxGroupBoolean() {
        String text = null;
        boolean checked = true;
        CheckboxGroup group = new CheckboxGroup();
        checkbox = new TestCheckbox(text, group, checked);
        checkCheckbox(text, checked, group);
    }

    private void checkCheckbox(String text, boolean state, CheckboxGroup group) {
        assertEquals(text, checkbox.getLabel());
        assertEquals(state, checkbox.getState());
        assertSame(group, checkbox.getCheckboxGroup());
    }

    public void testGetSetLabel() {
        checkbox.setLabel(null);
        assertNull(checkbox.getLabel());
        Dimension nullSize = checkbox.getPreferredSize();

        String text = "Checkbox";
        checkbox.setLabel(text);
        assertEquals(text, checkbox.getLabel());
    }

    public void testGetSetState() {
        assertFalse(checkbox.getState());
        checkbox.setState(true);
        assertTrue(checkbox.getState());
        checkbox.setState(false);
        assertFalse(checkbox.getState());
    }

    public void testGetSelectedObjects() {
        assertNull(checkbox.getSelectedObjects());
        checkbox.setState(true);
        Object[] selected = checkbox.getSelectedObjects();
        assertNotNull(selected);
        assertEquals(1, selected.length);
        assertTrue(selected[0] instanceof String);
        String strSelected = (String) selected[0];
        assertSame(checkbox.getLabel(), strSelected);
    }

    public void testGetSetCheckboxGroup() {
        assertNull(checkbox.getCheckboxGroup());
        CheckboxGroup group = new CheckboxGroup();

        checkbox.setCheckboxGroup(group);
        assertSame(group, checkbox.getCheckboxGroup());
        checkbox.setCheckboxGroup(null);
        assertNull(checkbox.getCheckboxGroup());

    }

    public void testAddGetRemoveItemListener() {
        assertEquals(0, checkbox.getItemListeners().length);

        ItemListener listener = new ItemListener() {
            public void itemStateChanged(ItemEvent ie) {
            }
        };
        checkbox.addItemListener(listener);
        assertEquals(1, checkbox.getItemListeners().length);
        assertSame(listener, checkbox.getItemListeners()[0]);

        checkbox.removeItemListener(listener);
        assertEquals(0, checkbox.getItemListeners().length);
    }

    public void testProcessItemEvent() {
        eventProcessed = false;
        checkbox.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent ie) {
                eventProcessed = true;
            }
        });
        checkbox.processEvent(new ItemEvent(checkbox,
                                              ItemEvent.ITEM_STATE_CHANGED,
                                              checkbox, ItemEvent.SELECTED));
        assertTrue(eventProcessed);
    }

    public void testGetListenersClass() {
        Class cls = ItemListener.class;
        assertEquals(0, checkbox.getListeners(cls).length);

        ItemListener listener = new ItemListener() {

            public void itemStateChanged(ItemEvent ie) {
            }};
        checkbox.addItemListener(listener);
        assertEquals(1, checkbox.getListeners(cls).length);
        assertSame(listener, checkbox.getListeners(cls)[0]);

        checkbox.removeItemListener(listener);
        assertEquals(0, checkbox.getListeners(cls).length);
    }

    Robot robot;

    public void testBehaviour() {
        try {
            robot = new Robot();
        } catch (AWTException e1) {
            e1.printStackTrace();
        }
        robot.setAutoWaitForIdle(true);

        checkbox.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent ie) {
                if (eventExpected) {
                    eventExpected = false;
                    eventProcessed = true;
                } else {
                    //throw new RuntimeException("Unexpected event");
                }
            }
        });

        colorTest();
        actionOnMouseTest();
        actionOnSpaceTest();
        noActionOnMouseTest();
        noActionOnSpaceTest();
    }

    private void colorTest() {
        assertEquals(checkbox.getBackground().getRGB(),
                     robot.getPixelColor(insets.left + 10,
                                         insets.top + 10).getRGB());
        paintFlag = false;
        checkbox.setBackground(Color.blue);
        while (!paintFlag) {
            Thread.yield();
        }
        assertEquals(Color.blue,
                     robot.getPixelColor(insets.left + 10,
                                         insets.top + 10));
        checkbox.setBackground(null);
    };

    private void noActionOnSpaceTest() {
        eventExpected = false;
        robot.keyPress(KeyEvent.VK_SPACE);
        eventProcessed = false;
        checkbox.setFocusable(false);
        robot.keyRelease(KeyEvent.VK_SPACE);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
        }
        assertFalse(eventProcessed);
    }

    private void noActionOnMouseTest() {
        eventExpected = false;
        robot.mouseMove(insets.left + 99, insets.top + 99);
        robot.mouseMove(insets.left + 100, insets.top + 100);
        robot.mousePress(KeyEvent.BUTTON1_MASK);
        eventProcessed = false;
        robot.mouseMove(insets.left + 200, insets.top + 200);
        robot.mouseRelease(KeyEvent.BUTTON1_MASK);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
        }
        assertFalse(eventProcessed);
    }

    private void actionOnSpaceTest() {
        eventExpected = false;
        robot.keyPress(KeyEvent.VK_SPACE);
        eventExpected = true;
        eventProcessed = false;
        robot.keyRelease(KeyEvent.VK_SPACE);
        while (!eventProcessed) {
            Thread.yield();
        }
        assertTrue(eventProcessed);
    }

    private void actionOnMouseTest() {
        eventExpected = false;
        robot.mouseMove(insets.left + 99, insets.top + 99);
        robot.mouseMove(insets.left + 100, insets.top + 100);
        robot.mousePress(KeyEvent.BUTTON1_MASK);
        eventExpected = true;
        eventProcessed = false;
        robot.mouseRelease(KeyEvent.BUTTON1_MASK);
        while (!eventProcessed) {
            Thread.yield();
        }
        assertTrue(eventProcessed);
    }
}
