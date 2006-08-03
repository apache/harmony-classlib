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
 * @author Pavel Dolgov
 * @version $Revision$
 */
/*
 * Created on 15.02.2005
 *
 */
package java.awt;

import java.awt.event.*;

import junit.framework.TestCase;

public class MenuTest extends TestCase {

    Frame frame;
    Component comp;

    boolean bMenuShown;
    volatile boolean bMenuClicked;
    boolean bMenuHidden;
    volatile boolean bEndModalLoop;
    Robot robot;

    protected void setUp() throws Exception {
        super.setUp();

        frame = new Frame("MenuTest");
        frame.setBounds(100, 100, 300, 200);
        comp = new Component() {
            public void paint(Graphics g) {
                g.setColor(Color.yellow);
                g.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        frame.add(comp);
    }

    protected void tearDown() throws Exception {
        if (frame.isDisplayable()) {
            frame.dispose();
        }
        robot = null;

        super.tearDown();
    }

    public void testAddRemove() {
        // create topmost menu
        PopupMenu popup = new PopupMenu();
        comp.add(popup);
        assertTrue(popup.getParent() == comp);

        // create submenu
        Menu menu = new Menu("Menu");
        popup.add(menu);
        assertEquals(popup, menu.getParent());
        assertEquals(1, popup.getItemCount());

        // add item to submenu
        MenuItem item = new MenuItem("Item");
        menu.add(item);
        assertEquals(menu, item.getParent());
        assertEquals(1, menu.getItemCount());

        // move item from submenu to topmost menu
        popup.add(item);
        assertEquals(popup, item.getParent());
        assertEquals(0, menu.getItemCount());
        assertEquals(2, popup.getItemCount());

        // remove submenu from topmost menu
        popup.remove(menu);
        assertNull(menu.getParent());
        assertEquals(1, popup.getItemCount());

        // remove item from topmost menu
        popup.remove(item);
        assertNull(item.getParent());
        assertEquals(0, popup.getItemCount());

        // remove topmost menu from component
        comp.remove(popup);
        assertNull(popup.getParent());
    }

    public void testAddRemoveListener() {
        MenuItem item = new MenuItem("Item");
        ActionListener listener = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            }};
        item.addActionListener(listener);
        assertNotNull(item.getActionListeners());
        assertEquals(1, item.getActionListeners().length);
        assertEquals(listener, item.getActionListeners()[0]);
    }

    public void testShowPopupMenu() {
        bMenuShown = bMenuClicked = bMenuHidden = false;
        bEndModalLoop = false;
        frame.setVisible(true);

        final PopupMenu popup = new PopupMenu();
        final Object endModalLoop = new Object();
        comp.add(popup);
        assertEquals(comp, popup.getParent());

        comp.addMouseListener(new MouseAdapter() {
            public void mouseReleased(MouseEvent e) {
                if (e.getButton() != MouseEvent.BUTTON3) {
                    return;
                }
                popup.show(comp, 10, 10);
                synchronized(endModalLoop) {
                    bEndModalLoop = true;
                    endModalLoop.notifyAll();
                }
            }
        });

        // add single item to the popup menu
        MenuItem item = new MenuItem("Item");
        item.setActionCommand("item");
        item.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                synchronized(robot) {
                    bMenuClicked = true;
                    robot.notifyAll();
                }
            }
        });
        popup.add(item);

        assertEquals("Item", item.getLabel());
        assertEquals("item", item.getActionCommand());
        assertEquals(popup, item.getParent());

        try {
            robot = new Robot();
            robot.setAutoDelay(100);
        } catch (AWTException e) {
            fail("failed to create robot " + e.getMessage());
        }

        Point clickLocation = comp.getLocation();
        Point shift = frame.getLocation();
        Point menuLocation = new Point(10, 10);
        clickLocation.x += shift.x + menuLocation.x + 5;
        clickLocation.y += shift.y + menuLocation.y + 5;

        try {
            synchronized(robot) {
                // simulate right-click
                robot.mouseMove(clickLocation.x, clickLocation.y);
                clickLocation.translate(20, 5);
                robot.mousePress(MouseEvent.BUTTON3_MASK);
                robot.mouseRelease(MouseEvent.BUTTON3_MASK);
                robot.mouseMove(clickLocation.x, clickLocation.y);

                // wait until popup menu is shown
                bMenuShown = waitForPixelChange(clickLocation, false);
                assertTrue("show menu", bMenuShown && !bMenuClicked && !bMenuHidden);

                synchronized(endModalLoop) {
                    // click the menu item
                    robot.mousePress(MouseEvent.BUTTON1_MASK);
                    robot.mouseRelease(MouseEvent.BUTTON1_MASK);

                    robot.wait(5000);
                    assertTrue("click menu", bMenuClicked);
                
                    endModalLoop.wait(5000);
                    assertTrue("end modal loop", bEndModalLoop);
                }

                // wait until menu is hidden
                bMenuHidden = waitForPixelChange(clickLocation, true);
                assertTrue("hide menu", bMenuHidden);
            }
        } catch (InterruptedException e) {
            fail("robot thread interrupted " + e.getMessage());
        }
    }

    boolean waitForPixelChange(Point point, boolean equals) throws InterruptedException {
        for (int i=0; i<50; i++) {
            Thread.sleep(100);
            if (robot.getPixelColor(point.x, point.y).equals(Color.yellow) == equals) {
                return true;
            }
        }
        return false;
    }
}
