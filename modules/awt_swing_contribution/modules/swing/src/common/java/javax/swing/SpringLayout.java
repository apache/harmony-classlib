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
 * @author Roman I. Chernyatchik
 * @version $Revision$
 */
package javax.swing;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.LayoutManager2;
import java.util.HashMap;

public class SpringLayout implements LayoutManager2 {
    public static final String WEST = "West";
    public static final String EAST = "East";
    public static final String NORTH = "North";
    public static final String SOUTH = "South";

    private static final byte WEST_EDGE = 0;
    private static final byte EAST_EDGE = 1;
    private static final byte NORTH_EDGE = 2;
    private static final byte SOUTH_EDGE = 3;
    private static final byte WIDTH = 4;
    private static final byte HEIGHT = 5;

    public static class Constraints {
        private Spring x;
        private Spring y;
        private Spring width;
        private Spring height;

        private Spring[] edges = new Spring[4];

        public Constraints() {
            this(null, null, null, null);
        }

        public Constraints(final Spring x, final Spring y) {
            this(x, y, null, null);
        }

        public Constraints(final Component c) {
            this(Spring.constant(c.getX()),
                 Spring.constant(c.getY()),
                 Spring.width(c),
                 Spring.height(c));
        }

        public Constraints(final Spring x, final Spring y,
                           final Spring width, final Spring height) {
             this.width = width;
             this.height = height;
             this.edges[WEST_EDGE] = x;
             this.edges[EAST_EDGE] = Spring.sum(x, width);
             this.edges[NORTH_EDGE] = y;
             this.edges[SOUTH_EDGE] = Spring.sum(y, height);
        }

        public void setX(final Spring x) {
            edges[WEST_EDGE] = x;
            width = Spring.sum(edges[EAST_EDGE],
                               Spring.minus(edges[WEST_EDGE]));
        }

        public Spring getX() {
            return x == null ? calculateX() : x;
        }

        public void setY(final Spring y) {
            edges[NORTH_EDGE] = y;
            height = Spring.sum(edges[SOUTH_EDGE],
                                Spring.minus(edges[NORTH_EDGE]));
        }

        public Spring getY() {
            return y == null ? calculateY() : y;
        }

        public void setWidth(final Spring width) {
            this.width = width;
            edges[EAST_EDGE] = Spring.sum(edges[WEST_EDGE], width);
        }

        public Spring getWidth() {
            return width;
        }

        public void setHeight(final Spring height) {
            this.height = height;
            edges[SOUTH_EDGE] = Spring.sum(edges[NORTH_EDGE], height);
        }

        public Spring getHeight() {
            return height;
        }

        public void setConstraint(final String edgeName, final Spring s) {
            final int edge = SpringLayout.getType(edgeName);

            switch (edge) {
                case EAST_EDGE :
                    edges[EAST_EDGE] = s;
                    edges[WEST_EDGE] = Spring.sum(edges[EAST_EDGE],
                                                  Spring.minus(width));
                    break;
                case WEST_EDGE:
                    setX(s);
                    break;
                case SOUTH_EDGE:
                    edges[SOUTH_EDGE] = s;
                    edges[NORTH_EDGE] = Spring.sum(edges[SOUTH_EDGE],
                                                   Spring.minus(height));
                    break;
                case NORTH_EDGE:
                    setY(s);
                    break;
                default:
                    break;
            }
        }

        public Spring getConstraint(final String edgeName) {
            final int edge = SpringLayout.getType(edgeName);
            if (edge >= 0) {
                return edges[edge];
            }
            return null;
        }

        private Spring calculateX() {
            if (edges[WEST_EDGE] != null) {
                x = edges[WEST_EDGE];
            } else if (edges[EAST_EDGE] != null) {
                x = Spring.sum(edges[EAST_EDGE], Spring.minus(width));
            } else {
                x = Spring.constant(0);
            }
            return x;
        }

        private Spring calculateY() {
            if (edges[NORTH_EDGE] != null) {
                y = edges[NORTH_EDGE];
            } else if (edges[SOUTH_EDGE] != null) {
                y = Spring.sum(edges[SOUTH_EDGE], Spring.minus(height));
            } else {
                y = Spring.constant(0);
            }
            return y;
        }

        private void clearConstraints() {
            x = null;
            y = null;
            width.setValue(Spring.UNSET);
            height.setValue(Spring.UNSET);
            edges[WEST_EDGE].setValue(Spring.UNSET);
            edges[EAST_EDGE].setValue(Spring.UNSET);
            edges[NORTH_EDGE].setValue(Spring.UNSET);
            edges[SOUTH_EDGE].setValue(Spring.UNSET);
        }
    }

    private static class ProxySpring extends Spring{
        private final byte edgeType;
        private final Constraints constraints;

        public ProxySpring(final byte edgeType, final Constraints constraints) {
            this.edgeType = edgeType;
            this.constraints = constraints;
        }

        public int getMinimumValue() {
            return getSpring().getMinimumValue();
        }

        public int getPreferredValue() {
            return getSpring().getPreferredValue();
        }

        public int getMaximumValue() {
            return getSpring().getMaximumValue();
        }

        public int getValue() {
            return getSpring().getValue();
        }

        public void setValue(final int value) {
            getSpring().setValue(value);
        }

        private Spring getSpring() {
            switch (edgeType) {
                case WEST_EDGE:
                    return constraints.getX();
                case EAST_EDGE:
                    return Spring.sum(constraints.getX(),
                                      constraints.getWidth());
                case NORTH_EDGE:
                    return constraints.getY();

                case SOUTH_EDGE:
                    return Spring.sum(constraints.getY(),
                                      constraints.getHeight());
                case WIDTH:
                    return constraints.getWidth();

                case HEIGHT:
                    return constraints.getHeight();

                default:
                    return null;
            }
        }
    }

    private static final float CENTERED = 0.5f;

    private HashMap constraintsMap = new HashMap();
    private HashMap calculatedprings = new HashMap();

    private Container target;

    private boolean isLayoutValid = false;

    private Dimension minimumSize;
    private Dimension preferredSize;
    private Dimension maximumSize;

    public SpringLayout() {
    }

    public void addLayoutComponent(final String name, final Component c) {
        // Specified by LayoutManager2 but is not used
    }

    public void removeLayoutComponent(final Component c) {
        constraintsMap.remove(c);
    }

    public Dimension minimumLayoutSize(final Container parent) {
        if (!isLayoutValid || target != parent) {
            calculateLayoutParams();
        }
        return minimumSize;
    }

    public Dimension preferredLayoutSize(final Container parent) {
        calculateLayoutParams();
        return preferredSize;
    }

    public Dimension maximumLayoutSize(final Container parent) {
        calculateLayoutParams();
        return maximumSize;
    }

    public void addLayoutComponent(final Component component,
                                   final Object constraints) {
        if (constraints != null && constraints instanceof Constraints) {
            constraintsMap.put(component, constraints);
        }
    }

    public float getLayoutAlignmentX(final Container p) {
        return CENTERED;
    }

    public float getLayoutAlignmentY(final Container p) {
        return CENTERED;
    }

    public void invalidateLayout(final Container p) {
        isLayoutValid = false;
    }

    public void putConstraint(final String edge1, final Component component1,
                              final int pad,
                              final String edge2, final Component component2) {

        putConstraint(edge1, component1,
                      Spring.constant(pad),
                      edge2, component2);
    }

    public void putConstraint(final String edge1, final Component component1,
                              final Spring pad,
                              final String edge2, final Component component2) {

        Constraints constraints1 =  getConstraints(component1);

        Spring springValue;
        final byte edge1Type = getType(edge1);
        final byte edge2Type = getType(edge2);

        final boolean edge1IsHorizontal =  edge1Type == EAST_EDGE
                                           || edge1Type == WEST_EDGE;
        final boolean edge2IsHorizontal =  edge2Type == EAST_EDGE
                                           || edge2Type == WEST_EDGE;

        springValue = Spring.sum(new ProxySpring(edge2Type,
                                                 getConstraints(component2)),
                                 pad);
        if (edge1IsHorizontal &&  edge2IsHorizontal) {

            if (edge1Type == EAST_EDGE) {
                constraints1.setConstraint(edge1, springValue);
            } else {
                constraints1.edges[WEST_EDGE] = springValue;
                constraints1.edges[EAST_EDGE] =
                    Spring.sum(constraints1.edges[WEST_EDGE],
                               new ProxySpring(WIDTH, constraints1));
            }
            constraints1.x = null;
        } else if (!edge1IsHorizontal && !edge2IsHorizontal) {
            if (edge1Type == SOUTH_EDGE) {
                constraints1.setConstraint(edge1, springValue);
            } else {
                constraints1.edges[NORTH_EDGE] = springValue;
                constraints1.edges[SOUTH_EDGE] =
                    Spring.sum(constraints1.edges[NORTH_EDGE],
                               new ProxySpring(HEIGHT, constraints1));
            }
            constraints1.y = null;
        }
    }

    public Constraints getConstraints(final Component component) {
        Constraints constraints = (Constraints)constraintsMap.get(component);
        if (constraints != null) {
            return (Constraints) constraints;
        }
        constraints = new Constraints(Spring.constant(0),
                                      Spring.constant(0),
                                      Spring.width(component),
                                      Spring.height(component));
        constraintsMap.put(component, constraints);
        return constraints;
    }

    public Spring getConstraint(final String edgeName,
                                final Component component) {

        return getConstraints(component).getConstraint(edgeName);
    }

    public void layoutContainer(final Container parent) {
        Component component;
        Constraints constraints;

        if (target != parent) {
            target = parent;
        }

        for (int i = 0; i < target.getComponentCount(); i++) {
           constraints = getConstraints(target.getComponent(i));
           constraints.clearConstraints();
        }

        calculatedprings.clear();

        Constraints targetConstraints =  getConstraints(target);
        targetConstraints.clearConstraints();
        targetConstraints.getWidth().setValue(target.getWidth());
        targetConstraints.getHeight().setValue(target.getHeight());
        targetConstraints.getConstraint(SpringLayout.EAST)
            .setValue(target.getWidth());
        targetConstraints.getConstraint(SpringLayout.SOUTH)
            .setValue(target.getHeight());

        for (int i = 0; i < target.getComponentCount(); i++) {
            component = target.getComponent(i);
            constraints = getConstraints(component);

            component.setBounds(getValue(constraints.getX()),
                                getValue(constraints.getY()),
                                getValue(constraints.getWidth()),
                                getValue(constraints.getHeight()));
        }
        isLayoutValid = true;

    }

    private void calculateLayoutParams() {
//      Constraints constraints = getConstraints(target);
//      minimumSize = new Dimension(constraints.getWidth().getMinimumValue(),
//                                  constraints.getHeight().getMinimumValue());
//      preferredSize = new Dimension(constraints.getWidth().getPreferredValue(),
//                                    constraints.getHeight().getPreferredValue());
//      maximumSize = new Dimension(constraints.getWidth().getMaximumValue(),
//                                  constraints.getHeight().getMaximumValue());
        minimumSize = new Dimension(0, 0);
        preferredSize = new Dimension(0, 0);
        maximumSize = new Dimension(0, 0);
    }

    private int getValue(final Spring s) {
        if (!calculatedprings.containsKey(s)) {
            int value = s.getValue();
            calculatedprings.put(s, new Integer(value));
            return value;
        }
        return ((Integer)calculatedprings.get(s)).intValue();
    }

    private static byte getType(final String edge) {
        if (EAST.equals(edge)) {
            return EAST_EDGE;
        } else if (WEST.equals(edge)) {
            return WEST_EDGE;
        } else if (NORTH.equals(edge)) {
            return NORTH_EDGE;
        } else if (SOUTH.equals(edge)) {
            return SOUTH_EDGE;
        }
        return -1;
    }
}