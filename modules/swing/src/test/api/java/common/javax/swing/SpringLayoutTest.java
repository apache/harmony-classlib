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
 * @author Roman I. Chernyatchik
 * @version $Revision$
 */
package javax.swing;

import java.awt.Component;
import java.awt.Container;
import java.awt.Rectangle;

import javax.swing.SpringLayout.Constraints;

public class SpringLayoutTest extends SwingTestCase {
    private Component component;
    private SpringLayout layout;

    protected void setUp() throws Exception {
        super.setUp();

        layout = new SpringLayout();
        component = new JLabel("label");
    }

    public void testSpringLayout() {
        SpringLayout.Constraints constrains = new SpringLayout.Constraints();
//        assertNull(constrains.getX());
//        assertNull(constrains.getY());
        assertNull(constrains.getWidth());
        assertNull(constrains.getHeight());


        constrains = new SpringLayout.Constraints(Spring.width(component),
                                                  Spring.constant(0));
        assertNull(constrains.getWidth());
        assertNull(constrains.getHeight());
        SpringTest.assertSizes(0, 0, 0, 0, constrains.getY());
        SpringTest.assertSizes(component.getMinimumSize().width,
                                component.getPreferredSize().width,
                                component.getMaximumSize().width,
                                constrains.getX());
        assertNull(constrains.getWidth());
        assertNull(constrains.getHeight());


        constrains = new SpringLayout.Constraints(component);
        SpringTest.assertSizes(0, 0, 0, 0, constrains.getX());
        SpringTest.assertSizes(0, 0, 0, 0, constrains.getY());
        SpringTest.assertSizes(component.getMinimumSize().width,
                                component.getPreferredSize().width,
                                component.getMaximumSize().width,
                                constrains.getWidth());
        SpringTest.assertSizes(component.getMinimumSize().height,
                                component.getPreferredSize().height,
                                component.getMaximumSize().height,
                                constrains.getHeight());


        constrains = new SpringLayout.Constraints(Spring.constant(1),
                                                  Spring.constant(2),
                                                  Spring.constant(3),
                                                  Spring.constant(4));
        SpringTest.assertSizes(1, 1, 1, 1, constrains.getX());
        SpringTest.assertSizes(2, 2, 2, 2, constrains.getY());
        SpringTest.assertSizes(3, 3, 3, 3, constrains.getWidth());
        SpringTest.assertSizes(4, 4, 4, 4, constrains.getHeight());

        constrains = new SpringLayout.Constraints(Spring.constant(1), null);
        SpringTest.assertSizes(1, 1, 1, 1, constrains.getX());
      //  assertNull(constrains.getY());


        Container container = new JPanel();
        container.setLayout(layout);
        container.add(new JLabel(""));
        constrains =  layout.getConstraints(component);

        SpringTest.assertSizes(0, 0, 0, 0, constrains.getX());
        SpringTest.assertSizes(0, 0, 0, 0, constrains.getY());
        SpringTest.assertSizes(component.getMinimumSize().width,
                                component.getPreferredSize().width,
                                component.getMaximumSize().width,
                                constrains.getWidth());
        SpringTest.assertSizes(component.getMinimumSize().height,
                                component.getPreferredSize().height,
                                component.getMaximumSize().height,
                                constrains.getHeight());
    }


    public void testRemoveLayoutComponent() {
        layout.removeLayoutComponent(null);
        layout.removeLayoutComponent(new JLabel());

        layout.addLayoutComponent(component,
                                  new SpringLayout.Constraints(component));
        layout.removeLayoutComponent(null);
        layout.removeLayoutComponent(new JLabel());

        JPanel panel = new JPanel();
        panel.add(component);
        assertSame(panel, component.getParent());
        layout.removeLayoutComponent(component);
        assertNotNull(component.getParent());

        Constraints constraints = layout.getConstraints(component);
        SpringTest.assertSizes(0, 0, 0, 0, constraints.getX());
        SpringTest.assertSizes(0, 0, 0, 0, constraints.getY());
    }

   public void testGetLayoutAlignmentX() {
        new JPanel().add(component);

        assertEquals(0.5f, layout.getLayoutAlignmentX(null), 0.01);
        assertEquals(0.5f, layout.getLayoutAlignmentX(component.getParent()),
                     0.01);
    }

    public void testGetLayoutAlignmentY() {
        new JPanel().add(component);
        assertEquals(0.5f, layout.getLayoutAlignmentY(null), 0.01);
        assertEquals(0.5f, layout.getLayoutAlignmentY(component.getParent()),
                     0.01);
    }

    private void assertLocation(final int x, final int y,
                              final Rectangle bounds) {

        assertEquals(x, bounds.x);
        assertEquals(y, bounds.y);
    }

    private void assertSize(final int width, final int height,
                            final Rectangle bounds) {

        assertEquals(width, bounds.width);
        assertEquals(height, bounds.height);
    }

    private void assertBounds(final int x, final int y,
                              final int width, final int height,
                              final Rectangle bounds) {
        assertLocation(x, y, bounds);
        assertSize(x, y, bounds);
    }
    public void testGetConstraints_ForComponent() {
        layout.addLayoutComponent(component, "not_constraints");
        Constraints constraints = layout.getConstraints(component);

        assertNotNull(constraints);
        SpringTest.assertSizes(0, 0, 0, constraints.getX());
        SpringTest.assertSizes(0, 0, 0, constraints.getY());
        SpringTest.assertSizes(component.getMinimumSize().width,
                                component.getPreferredSize().width,
                                component.getMaximumSize().width,
                                constraints.getWidth());
        SpringTest.assertSizes(component.getMinimumSize().height,
                                component.getPreferredSize().height,
                                component.getMaximumSize().height,
                                constraints.getHeight());

        layout = new SpringLayout();
        layout.addLayoutComponent(component, null);
        constraints = layout.getConstraints(component);

        assertNotNull(constraints);
        SpringTest.assertSizes(0, 0, 0, constraints.getX());
        SpringTest.assertSizes(0, 0, 0, constraints.getY());
        SpringTest.assertSizes(component.getMinimumSize().width,
                                component.getPreferredSize().width,
                                component.getMaximumSize().width,
                                constraints.getWidth());
        SpringTest.assertSizes(component.getMinimumSize().height,
                                component.getPreferredSize().height,
                                component.getMaximumSize().height,
                                constraints.getHeight());

        Constraints componentConstraints =
            new SpringLayout.Constraints(component);
        layout.addLayoutComponent(component, constraints);
        constraints = layout.getConstraints(component);
        assertFalse(componentConstraints.equals(constraints));
        SpringTest.assertValues(componentConstraints.getX(),
                                constraints.getX());
        SpringTest.assertValues(componentConstraints.getY(),
                                constraints.getY());
        SpringTest.assertValues(componentConstraints.getWidth(),
                                constraints.getWidth());
        SpringTest.assertValues(componentConstraints.getHeight(),
                                constraints.getHeight());
    }
}