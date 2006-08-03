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
 * @author Michael Danilov
 * @version $Revision$
 */
package java.awt;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import junit.framework.TestCase;

public class ButtonTest extends TestCase {

    class TestButton extends Button {
        public TestButton(String label) {
            super(label);
        }
        public TestButton() {
            super();
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

    private TestButton button;
    private Frame frame;
    private Insets insets;
    private boolean eventProcessed;
    private boolean eventExpected;
    private boolean paintFlag;

    protected void setUp() throws Exception {
        super.setUp();

        frame = new Frame();
        button = new TestButton("Button");
        frame.add(button);
        frame.setBounds(0, 0, 100, 100);
        paintFlag = false;
        frame.setVisible(true);
        while (!paintFlag) {
            Thread.yield();
        }
        insets = frame.getInsets();
        paintFlag = false;
        frame.setSize(200 + insets.left + insets.right, 200 + insets.top + insets.bottom);
        frame.validate();
        while (!paintFlag) {
            Thread.yield();
        }
        while (!button.hasFocus()) {
            Thread.yield();
        }
    }

    protected void tearDown() throws Exception {
        frame.dispose();

        super.tearDown();
    }

    public void testButton() {
        button = new TestButton();

        assertTrue(button.getLabel().equals(""));
    }

    public void testButtonString() {
        assertTrue(button.getLabel() == "Button");
    }

    public void testGetSetLabel() {
        button.setLabel(null);
        assertTrue(button.getLabel() == null);
        Dimension nullSize = button.getPreferredSize();

        button.setLabel("Button");
        assertTrue(button.getLabel().equals("Button"));
    }

    public void testGetSetActionCommand() {
        assertTrue(button.getActionCommand() == "Button");
        button.setLabel(null);
        assertTrue(button.getActionCommand() == null);

        button.setActionCommand("Button Command");
        assertTrue(button.getActionCommand() == "Button Command");
    }

    public void testAddGetRemoveActionListener() {
        assertTrue(button.getActionListeners().length == 0);

        ActionListener listener = new ActionListener() {
            public void actionPerformed(ActionEvent e) {}
        };
        button.addActionListener(listener);
        assertTrue(button.getActionListeners().length == 1);
        assertTrue(button.getActionListeners()[0] == listener);

        button.removeActionListener(listener);
        assertTrue(button.getActionListeners().length == 0);
    }

    public void testProcessEvent() {
        eventProcessed = false;
        button.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent a0) {
                eventProcessed = true;
            }
        });
        button.processEvent(new KeyEvent(button, KeyEvent.KEY_PRESSED, 0, 0, 0, 's'));
        assertTrue(eventProcessed);
    }

    public void testProcessActionEvent() {
        eventProcessed = false;
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                eventProcessed = true;
            }
        });
        button.processEvent(new ActionEvent(button, ActionEvent.ACTION_PERFORMED, null, 0, 0));
        assertTrue(eventProcessed);
    }

    public void testGetListenersClass() {
        assertTrue(button.getListeners(KeyListener.class).length == 0);

        KeyAdapter listener = new KeyAdapter() {};
        button.addKeyListener(listener);
        assertTrue(button.getListeners(KeyListener.class).length == 1);
        assertTrue(button.getListeners(KeyListener.class)[0] == listener);

        button.removeKeyListener(listener);
        assertTrue(button.getListeners(KeyListener.class).length == 0);
    }

    Robot robot;

    public void testBehaviour() {
        try {
            robot = new Robot();
        } catch (AWTException e1) {
            e1.printStackTrace();
        }
        robot.setAutoWaitForIdle(true);

        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (eventExpected) {
                    eventExpected = false;
                    eventProcessed = true;
                } else {
                    throw new RuntimeException("Unexpected event");
                }
            }
        });

//        colorTest();
        actionOnMouseTest();
        actionOnSpaceTest();
        noActionOnMouseTest();
        noActionOnSpaceTest();
    }

    private void colorTest() {
        assertEquals(SystemColor.control.getRGB(), robot.getPixelColor(insets.left + 10, insets.top + 10).getRGB());
        paintFlag = false;
        button.setBackground(Color.green);
        while (!paintFlag) {
            Thread.yield();
        }
        assertEquals(Color.green, robot.getPixelColor(insets.left + 10, insets.top + 10));
        button.setBackground(null);
    };

    private void noActionOnSpaceTest() {
        eventExpected = false;
        robot.keyPress(KeyEvent.VK_SPACE);
        eventProcessed = false;
        button.setFocusable(false);
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
        robot.mousePress(InputEvent.BUTTON1_MASK);
        eventProcessed = false;
        robot.mouseMove(insets.left + 200, insets.top + 200);
        robot.mouseRelease(InputEvent.BUTTON1_MASK);
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
        robot.mousePress(InputEvent.BUTTON1_MASK);
        eventExpected = true;
        eventProcessed = false;
        robot.mouseRelease(InputEvent.BUTTON1_MASK);
        while (!eventProcessed) {
            Thread.yield();
        }
        assertTrue(eventProcessed);
    }

}
