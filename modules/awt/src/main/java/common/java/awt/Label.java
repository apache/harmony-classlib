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

import javax.accessibility.Accessible;
import javax.accessibility.AccessibleContext;
import javax.accessibility.AccessibleRole;

import org.apache.harmony.awt.state.LabelState;


public class Label extends Component implements Accessible {

    private static final long serialVersionUID = 3094126758329070636L;

    protected class AccessibleAWTLabel
    extends Component.AccessibleAWTComponent {

        private static final long serialVersionUID = -3568967560160480438L;

        public AccessibleAWTLabel() {
            // define default constructor explicitly just to make it public
        }

        @Override
        public String getAccessibleName() {
            return Label.this.getText();
        }

        @Override
        public AccessibleRole getAccessibleRole() {
            return AccessibleRole.LABEL;
        }
    }

    class State extends Component.ComponentState implements LabelState {

        final Dimension textSize = new Dimension();

        public String getText() {
            return text;
        }

        public Dimension getTextSize() {
            return textSize;
        }

        public void setTextSize(Dimension size) {
            textSize.width = size.width;
            textSize.height = size.height;
        }

        public int getAlignment() {
            return alignment;
        }

        @Override
        public void calculate() {
            toolkit.theme.calculateLabel(state);
        }

    }

    public static final int LEFT = 0;

    public static final int CENTER = 1;

    public static final int RIGHT = 2;

    private String text = null;

    private int alignment = LEFT;

    Color savedBackground;

    final State state = new State();

    public Label(String text) throws HeadlessException {
        this(text, LEFT);
        toolkit.lockAWT();
        try {
        } finally {
            toolkit.unlockAWT();
        }
    }

    public Label() throws HeadlessException {
        this(new String(""), LEFT);
        toolkit.lockAWT();
        try {
        } finally {
            toolkit.unlockAWT();
        }
    }

    public Label(String text, int alignment) throws HeadlessException {
        toolkit.lockAWT();
        try {
            this.text = text;
            setAlignmentImpl(alignment);
        } finally {
            toolkit.unlockAWT();
        }
    }

    @Override
    protected String paramString() {
        /* The format is based on 1.5 release behavior 
         * which can be revealed by the following code:
         * System.out.println(new Label());
         */

        toolkit.lockAWT();
        try {
            return (super.paramString() + ",align=" + getAlignString() +
                    ",text=" + text);
        } finally {
            toolkit.unlockAWT();
        }
    }

    private String getAlignString() {
        String alignStr = "left";
        switch (alignment) {
        case CENTER:
            alignStr = "center";
            break;
        case RIGHT:
            alignStr = "right";
            break;
        }
        return alignStr;
    }

    @Override
    public void addNotify() {
        toolkit.lockAWT();
        try {
            super.addNotify();
        } finally {
            toolkit.unlockAWT();
        }
    }

    @Override
    public AccessibleContext getAccessibleContext() {
        toolkit.lockAWT();
        try {
            return super.getAccessibleContext();
        } finally {
            toolkit.unlockAWT();
        }
    }

    public int getAlignment() {
        toolkit.lockAWT();
        try {
            return alignment;
        } finally {
            toolkit.unlockAWT();
        }
    }

    public String getText() {
        toolkit.lockAWT();
        try {
            return text;
        } finally {
            toolkit.unlockAWT();
        }
    }

    public void setAlignment(int alignment) {
        toolkit.lockAWT();
        try {
            setAlignmentImpl(alignment);
            doRepaint();
        } finally {
            toolkit.unlockAWT();
        }
    }

    private void setAlignmentImpl(int alignment) {
        if ((alignment < LEFT) || (alignment > RIGHT)) {
            throw new IllegalArgumentException("improper alignment: " +
                                               alignment);
        }
        this.alignment = alignment;
    }

    public void setText(String text) {
        toolkit.lockAWT();
        try {
            this.text = text;
            doRepaint();
        } finally {
            toolkit.unlockAWT();
        }
    }

    private void doRepaint() {
        if (isDisplayable()) {
            invalidate();
            if (isShowing()) {
                repaint();
            }
        }
    }

    @Override
    void prepaint(Graphics g) {
        toolkit.theme.drawLabel(g, state);
    }

    @Override
    boolean isPrepainter() {
        return true;
    }

    @Override
    String autoName() {
        return ("label" + toolkit.autoNumber.nextLabel++);
    }

    @Override
    void validateImpl() {
        super.validateImpl();
        toolkit.theme.calculateLabel(state);
    }

    @Override
    void setEnabledImpl(boolean value) {
        super.setEnabledImpl(value);
        repaint();
    }

    @Override
    ComponentBehavior createBehavior() {
        return new HWBehavior(this);
    }

    @Override
    Dimension getDefaultMinimumSize() {
        if (getFont() == null) {
            return new Dimension(0, 0);
        }
        return state.getDefaultMinimumSize();
    }

    @Override
    void resetDefaultSize() {
        state.reset();
    }

    @Override
    boolean hasDefaultFont() {
        return true;
    }

    @Override
    AccessibleContext createAccessibleContext() {
        return new AccessibleAWTLabel();
    }
}

