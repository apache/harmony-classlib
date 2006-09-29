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
 * @author Alexander T. Simbirtsev
 * @version $Revision$
 * Created on 02.09.2004
 *
 */
package javax.swing;

import java.awt.Component;
import java.awt.Container;
import java.awt.EventQueue;
import java.awt.FocusTraversalPolicy;

import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.basic.BasicLookAndFeel;


public class JComponent_MultithreadedTest extends BasicSwingTestCase {

    protected JComponent panel;
    protected JFrame window;

    class MyInputVerifier extends InputVerifier{
        public boolean invoked = false;
        private boolean returnedValue = true;

        MyInputVerifier() {
        }

        MyInputVerifier(final boolean returnedValue) {
            this.returnedValue = returnedValue;
        }

        public boolean verify(final JComponent input) {
            invoked = true;
            return returnedValue;
        }
    }

    class RunnableResulted implements Runnable {
        public boolean result = false;

        public void run() {
            result = true;
        }
    }

    private boolean requestFocusInWindowForComponent(final JComponent c) throws Exception {
        boolean result = c.requestFocusInWindow();
        waitForFocus(c);

        return result;
    }

    private boolean requestFocusInWindowForComponent(final JComponent c, final boolean temporarily) throws Exception {
        c.requestFocusInWindow(temporarily);
        return waitForFocus(c);
    }

    private void requestFocusForComponent(final JComponent c) throws Exception {
        c.requestFocus();
        waitForFocus(c);
    }

    protected void requestDefaultFocusForComponent(final JComponent c, final Component newFocusOwner) throws Exception {
        c.requestDefaultFocus();
        waitForFocus(newFocusOwner);
    }

    private void requestFocusForComponent(final JComponent c, final boolean temporarily) throws Exception {
        c.requestFocus(temporarily);
        waitForFocus(c);
    }

    private void grabFocusForComponent(final JComponent c) throws Exception {
        c.grabFocus();
        waitForFocus(c);
    }

    /**
     * Constructor for JComponentTest_Multithreaded.
     */
    public JComponent_MultithreadedTest(final String str) {
        super(str);
    }

    /*
     * @see TestCase#setUp()
     */
    protected void setUp() throws Exception {
        super.setUp();
        panel = new JPanel();
        window = new JFrame();
    }

    /*
     * @see TestCase#tearDown()
     */
    protected void tearDown() throws Exception {
        window.dispose();
        super.tearDown();
    }

    /*
     * Class under test for void requestFocus()
     */
    public void testRequestFocus() throws Exception {
        MyInputVerifier verifier = new MyInputVerifier();
        JComponent panel1 = new JPanel();
        JComponent panel2 = new JPanel();
        JComponent panel3 = new JPanel();
        JComponent panel4 = new JPanel();
        window.getContentPane().add(panel1);
        window.getContentPane().add(panel2);
        window.getContentPane().add(panel3);
        window.getContentPane().add(panel4);
        window.pack();
        window.show();
        waitForIdle();

        SwingWaitTestCase.requestFocusInWindowForComponent(panel1);
        panel1.setInputVerifier(verifier);
        panel2.setVerifyInputWhenFocusTarget(true);
        requestFocusForComponent(panel2);
        assertEquals("verifier's invoked ", true, verifier.invoked);
        assertEquals("focus's gained ", true, panel2.isFocusOwner());
        verifier.invoked = false;

        requestFocusForComponent(panel1);
        assertEquals("focus's gained ", true, panel1.isFocusOwner());

        panel2.setVerifyInputWhenFocusTarget(false);
        requestFocusForComponent(panel2);
        assertEquals("verifier's not invoked ", false, verifier.invoked);
        assertEquals("focus's gained ", true, panel2.isFocusOwner());
    }

    /*
     * Class under test for boolean requestFocusInWindow()
     */
    public void testRequestFocusInWindow() throws Exception {
        MyInputVerifier verifier = new MyInputVerifier();
        MyInputVerifier verifier2 = new MyInputVerifier(false);
        JComponent panel1 = new JPanel();
        JComponent panel2 = new JPanel();
        JComponent panel3 = new JPanel();
        JComponent panel4 = new JPanel();
        window.getContentPane().add(panel1);
        window.getContentPane().add(panel2);
        window.getContentPane().add(panel3);
        window.getContentPane().add(panel4);
        window.pack();
        window.show();
        waitForIdle();

        SwingWaitTestCase.requestFocusInWindowForComponent(panel1);
        panel1.setInputVerifier(verifier);
        panel2.setVerifyInputWhenFocusTarget(true);
        assertTrue("focus can be gained ", requestFocusInWindowForComponent(panel2));
        assertEquals("verifier's invoked ", true, verifier.invoked);
        assertEquals("focus's gained ", true, panel2.isFocusOwner());
        verifier.invoked = false;

        assertTrue("focus can be gained ", requestFocusInWindowForComponent(panel1));
        assertEquals("focus's gained ", true, panel1.isFocusOwner());

        panel2.setVerifyInputWhenFocusTarget(false);
        assertTrue("focus can be gained ", requestFocusInWindowForComponent(panel2));
        assertEquals("verifier's not invoked ", false, verifier.invoked);
        assertEquals("focus's gained ", true, panel2.isFocusOwner());

        panel1.setVerifyInputWhenFocusTarget(true);
        panel2.setInputVerifier(verifier2);
        assertFalse("focus can be gained ", requestFocusInWindowForComponent(panel1, true));
        assertEquals("verifier's invoked ", true, verifier2.invoked);
        assertEquals("focus's gained ", false, panel1.isFocusOwner());
        verifier.invoked = false;
    }

    /*
     * Class under test for boolean requestFocus(boolean)
     */
    public void testRequestFocusboolean() throws Exception {
        MyInputVerifier verifier = new MyInputVerifier();
        JComponent panel1 = new JPanel();
        JComponent panel2 = new JPanel();
        JComponent panel3 = new JPanel();
        JComponent panel4 = new JPanel();
        window.getContentPane().add(panel1);
        window.getContentPane().add(panel2);
        window.getContentPane().add(panel3);
        window.getContentPane().add(panel4);
        window.pack();
        window.show();
        waitForIdle();

        SwingWaitTestCase.requestFocusInWindowForComponent(panel1);
        panel1.setInputVerifier(verifier);
        panel2.setVerifyInputWhenFocusTarget(true);
        requestFocusForComponent(panel2, false);
        assertEquals("verifier's invoked ", true, verifier.invoked);
        assertEquals("focus's gained ", true, panel2.isFocusOwner());
        verifier.invoked = false;

        requestFocusForComponent(panel1, false);
        assertEquals("focus's gained ", true, panel1.isFocusOwner());

        panel2.setVerifyInputWhenFocusTarget(false);
        requestFocusForComponent(panel2, false);
        assertEquals("verifier's not invoked ", false, verifier.invoked);
        assertEquals("focus's gained ", true, panel2.isFocusOwner());

        requestFocusForComponent(panel1, false);
        assertEquals("focus's gained ", true, panel1.isFocusOwner());

        panel2.setVerifyInputWhenFocusTarget(true);
        requestFocusForComponent(panel2, true);
        assertEquals("verifier's invoked ", true, verifier.invoked);
        assertEquals("focus's gained ", true, panel2.isFocusOwner());
        verifier.invoked = false;

        requestFocusForComponent(panel1, true);
        assertEquals("focus's gained ", true, panel1.isFocusOwner());

        panel2.setVerifyInputWhenFocusTarget(false);
        requestFocusForComponent(panel2, true);
        assertEquals("verifier's not invoked ", false, verifier.invoked);
        assertEquals("focus's gained ", true, panel2.isFocusOwner());
    }

    /*
     * Class under test for boolean requestFocusInWindow(boolean)
     */
    public void testRequestFocusInWindowboolean() throws Exception {
        MyInputVerifier verifier = new MyInputVerifier();
        MyInputVerifier verifier2 = new MyInputVerifier(false);
        JComponent panel1 = new JPanel();
        JComponent panel2 = new JPanel();
        JComponent panel3 = new JPanel();
        JComponent panel4 = new JPanel();
        window.getContentPane().add(panel1);
        window.getContentPane().add(panel2);
        window.getContentPane().add(panel3);
        window.getContentPane().add(panel4);
        window.pack();
        window.show();
        waitForIdle();

        SwingWaitTestCase.requestFocusInWindowForComponent(panel1);
        panel1.setInputVerifier(verifier);
        panel2.setVerifyInputWhenFocusTarget(true);
        assertTrue("focus can be gained ", requestFocusInWindowForComponent(panel2, false));
        assertEquals("verifier's invoked ", true, verifier.invoked);
        assertEquals("focus's gained ", true, panel2.isFocusOwner());
        verifier.invoked = false;

        assertTrue("focus can be gained ", requestFocusInWindowForComponent(panel1, false));
        assertEquals("focus's gained ", true, panel1.isFocusOwner());

        panel2.setVerifyInputWhenFocusTarget(false);
        assertTrue("focus can be gained ", requestFocusInWindowForComponent(panel2, false));
        assertEquals("verifier's not invoked ", false, verifier.invoked);
        assertEquals("focus's gained ", true, panel2.isFocusOwner());

        assertTrue("focus can be gained ", requestFocusInWindowForComponent(panel1, false));
        assertEquals("focus's gained ", true, panel1.isFocusOwner());

        panel2.setVerifyInputWhenFocusTarget(true);
        assertTrue("focus can be gained ", requestFocusInWindowForComponent(panel2, true));
        assertEquals("verifier's invoked ", true, verifier.invoked);
        assertEquals("focus's gained ", true, panel2.isFocusOwner());
        verifier.invoked = false;

        assertTrue("focus can be gained ", requestFocusInWindowForComponent(panel1, true));
        assertEquals("focus's gained ", true, panel1.isFocusOwner());

        panel2.setVerifyInputWhenFocusTarget(false);
        assertTrue("focus can be gained ", requestFocusInWindowForComponent(panel2, true));
        assertEquals("verifier's not invoked ", false, verifier.invoked);
        assertEquals("focus's gained ", true, panel2.isFocusOwner());

        panel1.setVerifyInputWhenFocusTarget(true);
        panel2.setInputVerifier(verifier2);
        assertFalse("focus can be gained ", requestFocusInWindowForComponent(panel1, true));
        assertEquals("verifier's invoked ", true, verifier2.invoked);
        assertEquals("focus's gained ", false, panel1.isFocusOwner());
        verifier.invoked = false;
    }

    public void testGrabFocus() throws Exception {
        MyInputVerifier verifier = new MyInputVerifier();
        JComponent panel1 = new JPanel();
        JComponent panel2 = new JPanel();
        JComponent panel3 = new JPanel();
        JComponent panel4 = new JPanel();
        window.getContentPane().add(panel1);
        window.getContentPane().add(panel2);
        window.getContentPane().add(panel3);
        window.getContentPane().add(panel4);
        window.pack();
        window.show();
        waitForIdle();

        SwingWaitTestCase.requestFocusInWindowForComponent(panel1);
        panel1.setInputVerifier(verifier);
        panel2.setVerifyInputWhenFocusTarget(true);
        grabFocusForComponent(panel2);
        assertEquals("verifier's invoked ", true, verifier.invoked);
        assertEquals("focus's gained ", true, panel2.isFocusOwner());
        verifier.invoked = false;

        grabFocusForComponent(panel1);
        assertEquals("focus's gained ", true, panel1.isFocusOwner());

        panel2.setVerifyInputWhenFocusTarget(false);
        grabFocusForComponent(panel2);
        assertEquals("verifier's not invoked ", false, verifier.invoked);
        assertEquals("focus's gained ", true, panel2.isFocusOwner());
    }

    public void testRequestDefaultFocus() throws Exception {
        final JComponent panel1 = new JPanel();
        final JComponent panel2 = new JPanel();
        final JComponent panel3 = new JPanel();
        final JComponent panel4 = new JPanel(); // this component is to be returned
                                                // by our FocusTraversalPolicy()
        FocusTraversalPolicy policy = new FocusTraversalPolicy() {
            public Component getComponentAfter(final Container a0, final Component a1) {
                return null;
            }
            public Component getComponentBefore(final Container a0, final Component a1) {
                return null;
            }

            public Component getDefaultComponent(final Container a0) {
                return panel4;
            }

            public Component getFirstComponent(final Container a0) {
                return null;
            }

            public Component getLastComponent(final Container a0) {
                return null;
            }
        };

        window.getContentPane().add(panel1);
        window.getContentPane().add(panel2);
        window.getContentPane().add(panel3);
        window.getContentPane().add(panel4);
        window.pack();
        window.show();
        waitForIdle();

        requestDefaultFocusForComponent(panel2, window);
        assertTrue("focus's gained ", window.isFocusOwner());

        panel3.setFocusCycleRoot(false);
        panel3.setFocusTraversalPolicy(policy);
        requestDefaultFocusForComponent(panel3, window);
        assertEquals("focus's gained ", true, window.isFocusOwner());

        panel3.setFocusCycleRoot(true);
        requestDefaultFocusForComponent(panel3, panel4);
        assertEquals("focus's gained ", true, panel4.isFocusOwner());

        panel3.setFocusCycleRoot(false);
        requestDefaultFocusForComponent(panel3, window);
        assertEquals("focus's gained ", false, window.isFocusOwner());
    }

    public void testUpdateUI() throws Exception {
        LookAndFeel laf = UIManager.getLookAndFeel();
        try {
            JButton button = new JButton();

            BasicLookAndFeel lookAndFeel1 = new BasicLookAndFeel() {
                public boolean isSupportedLookAndFeel() {
                    return true;
                }
                public boolean isNativeLookAndFeel() {
                    return false;
                }
                public String getName() {
                    return "BasicLookAndFeel";
                }
                public String getID() {
                    return "BasicLookAndFeel";
                }
                public String getDescription() {
                    return "BasicLookAndFeel";
                }
            };
            UIManager.setLookAndFeel(lookAndFeel1);

            ComponentUI ui = button.getUI();
            assertTrue("current component's ui is correct ", ui.getClass().getName().endsWith("MetalButtonUI"));

            button.updateUI();
            ui = button.getUI();
            assertTrue("L&F change affected component's ui ", ui.getClass().getName().endsWith("BasicButtonUI"));
        } finally {
            UIManager.setLookAndFeel(laf);
        }
    }

    public void testRevalidate() throws Exception {
        final JButton button = new JButton("test");
        final JFrame frame = new JFrame();
        EventQueue.invokeAndWait(new Runnable() {
            public void run() {
                frame.getContentPane().add(button);
                frame.setVisible(true);
                assertTrue(button.isValid());
                button.revalidate();
                assertFalse(button.isValid());
            }
        });
        waitForIdle();
        EventQueue.invokeAndWait(new Thread());
        assertTrue(button.isValid());
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                frame.dispose();
            }
        });

    }

    public void testAddNotify() throws Exception {
        PropertyChangeController listener = new PropertyChangeController();

        JButton panel1 = new JButton();
        JPanel panel2 = new JPanel();
        JPanel panel3 = new JPanel();
        panel1.addPropertyChangeListener(listener);
        window.getContentPane().add(panel2);
        window.getContentPane().add(panel3);
        window.pack();
        window.show();
        waitForIdle();
        SwingWaitTestCase.requestFocusInWindowForComponent(panel2);

        panel2.add(panel1);

        listener.checkPropertyFired(panel1, "ancestor", null, panel2);
        listener.reset();

        panel3.add(panel1);
        listener.checkPropertyFired(panel1, "ancestor", null, panel3);
    }

    public void testRemoveNotify() throws Exception {
        PropertyChangeController listener = new PropertyChangeController();

        JPanel panel1 = new JPanel();
        JPanel panel2 = new JPanel();
        JPanel panel3 = new JPanel();
        panel1.addPropertyChangeListener(listener);
        window.getContentPane().add(panel2);
        window.getContentPane().add(panel3);
        window.pack();
        window.show();
        waitForIdle();
        SwingWaitTestCase.requestFocusInWindowForComponent(panel2);

        panel2.add(panel1);

        listener.checkPropertyFired(panel1, "ancestor", null, panel2);
        listener.reset();

        panel3.add(panel1);
        listener.checkPropertyFired(panel1, "ancestor", null, panel3);
    }
}