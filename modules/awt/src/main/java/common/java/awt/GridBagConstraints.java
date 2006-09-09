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

import java.io.Serializable;

public class GridBagConstraints implements Cloneable, Serializable {
    private static final long serialVersionUID = -1000070633030801713L;

    public static final int RELATIVE = -1;
    public static final int REMAINDER = 0;

    public static final int NONE = 0;
    public static final int BOTH = 1;
    public static final int HORIZONTAL = 2;
    public static final int VERTICAL = 3;

    public static final int CENTER = 10;
    public static final int NORTH = 11;
    public static final int NORTHEAST = 12;
    public static final int EAST = 13;
    public static final int SOUTHEAST = 14;
    public static final int SOUTH = 15;
    public static final int SOUTHWEST = 16;
    public static final int WEST = 17;
    public static final int NORTHWEST = 18;

    public static final int PAGE_START = 19;
    public static final int PAGE_END = 20;
    public static final int LINE_START = 21;
    public static final int LINE_END = 22;
    public static final int FIRST_LINE_START = 23;
    public static final int FIRST_LINE_END = 24;
    public static final int LAST_LINE_START = 25;
    public static final int LAST_LINE_END = 26;

    public int gridx;
    public int gridy;
    public int gridwidth;
    public int gridheight;

    public double weightx;
    public double weighty;

    public int anchor;
    public int fill;

    public Insets insets;
    public int ipadx;
    public int ipady;

    public GridBagConstraints(int gridx, int gridy, int gridwidth,
            int gridheight, double weightx, double weighty, int anchor,
            int fill, Insets insets, int ipadx, int ipady)
    {
        this.gridx = gridx;
        this.gridy = gridy;
        this.gridwidth = gridwidth;
        this.gridheight = gridheight;
        this.weightx = weightx;
        this.weighty = weighty;
        this.anchor = anchor;
        this.fill = fill;
        this.insets = (Insets) insets.clone();
        this.ipadx = ipadx;
        this.ipady = ipady;
    }

    public GridBagConstraints() {
        gridx = RELATIVE;
        gridy = RELATIVE;
        gridwidth = 1;
        gridheight = 1;
        weightx = 0.;
        weighty = 0.;
        anchor = CENTER;
        fill = NONE;
        insets = new Insets(0, 0, 0, 0);
        ipadx = 0;
        ipady = 0;
    }

    @Override
    public Object clone() {
        return new GridBagConstraints(gridx, gridy, gridwidth, gridheight,
                weightx, weighty, anchor, fill, insets, ipadx, ipady);
    }

    void verify() throws IllegalArgumentException {
        int maxN = GridBagLayout.MAXGRIDSIZE - 1;

        if (((gridx != RELATIVE) && (gridx < 0)) || (gridx >= maxN)) {
            throw new IllegalArgumentException(
                    "wrong value of GridBagConstraints' gridx");
        }
        if (((gridy != RELATIVE) && (gridy < 0)) || (gridy >= maxN)) {
            throw new IllegalArgumentException(
                    "wrong value of GridBagConstraints' gridy");
        }
        if (((gridwidth != RELATIVE) && (gridwidth != REMAINDER) && (gridwidth < 0))
                || (gridwidth > maxN))
        {
            throw new IllegalArgumentException(
                    "wrong value of GridBagConstraints' gridwidth");
        }
        if (((gridheight != RELATIVE) && (gridheight != REMAINDER) && (gridheight < 0))
                || (gridheight > maxN))
        {
            throw new IllegalArgumentException(
                    "wrong value of GridBagConstraints' gridheight");
        }
        if (((gridx >= 0) || (gridy >= 0))
                && ((gridwidth <= 0) || (gridheight <= 0)))
        {
            throw new IllegalArgumentException(
                    "relative grid size parameter goes after absolute grid coordinate");
        }
        if ((gridx != RELATIVE) && ((gridwidth + gridx) > maxN)) {
            throw new IllegalArgumentException(
                    "wrong values sum of GridBagConstraints' gridwidth and gridx");
        }
        if ((gridy != RELATIVE) && ((gridheight + gridy) > maxN)) {
            throw new IllegalArgumentException(
                    "wrong values sum of GridBagConstraints' gridheight and gridy");
        }
        if ((gridwidth == RELATIVE) && (gridheight == RELATIVE)) {
            throw new IllegalArgumentException(
                    "component has RELATIVE width and height");
        }

        if (weightx < 0.) {
            throw new IllegalArgumentException(
                    "wrong value of GridBagConstraints' weightx");
        }
        if (weighty < 0.) {
            throw new IllegalArgumentException(
                    "wrong value of GridBagConstraints' weighty");
        }

        if ((anchor != CENTER) && (anchor != NORTH) && (anchor != NORTHEAST)
                && (anchor != EAST) && (anchor != SOUTHEAST)
                && (anchor != SOUTH) && (anchor != SOUTHWEST)
                && (anchor != WEST) && (anchor != NORTHWEST)
                && (anchor != PAGE_START) && (anchor != PAGE_END)
                && (anchor != LINE_START) && (anchor != LINE_END)
                && (anchor != FIRST_LINE_START) && (anchor != FIRST_LINE_END)
                && (anchor != LAST_LINE_START) && (anchor != LAST_LINE_END)) {
            throw new IllegalArgumentException(
                    "wrong value of GridBagConstraints' anchor");
        }
        if ((fill != NONE) && (fill != HORIZONTAL) && (fill != VERTICAL)
                && (fill != BOTH)) {
            throw new IllegalArgumentException(
                    "wrong value of GridBagConstraints' fill");
        }

        if (ipadx < 0) {
            throw new IllegalArgumentException(
                    "wrong value of GridBagConstraints' ipadx");
        }
        if (ipady < 0) {
            throw new IllegalArgumentException(
                    "wrong value of GridBagConstraints' ipady");
        }
        if ((insets == null) || (insets.left < 0) || (insets.left < 0)
                || (insets.left < 0) || (insets.left < 0)) {
            throw new IllegalArgumentException(
                    "wrong value of GridBagConstraints' insets");
        }
    }
}
