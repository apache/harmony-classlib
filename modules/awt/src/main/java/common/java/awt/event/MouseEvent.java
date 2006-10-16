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
 * @author Michael Danilov
 * @version $Revision$
 */
package java.awt.event;

import java.awt.Component;
import java.awt.Point;
import java.awt.Toolkit;

public class MouseEvent extends InputEvent {

    private static final long serialVersionUID = -991214153494842848L;

    public static final int MOUSE_FIRST = 500;

    public static final int MOUSE_LAST = 507;

    public static final int MOUSE_CLICKED = 500;

    public static final int MOUSE_PRESSED = 501;

    public static final int MOUSE_RELEASED = 502;

    public static final int MOUSE_MOVED = 503;

    public static final int MOUSE_ENTERED = 504;

    public static final int MOUSE_EXITED = 505;

    public static final int MOUSE_DRAGGED = 506;

    public static final int MOUSE_WHEEL = 507;

    public static final int NOBUTTON = 0;

    public static final int BUTTON1 = 1;

    public static final int BUTTON2 = 2;

    public static final int BUTTON3 = 3;

    private boolean popupTrigger;
    private int clickCount;
    private int button;
    private int x;
    private int y;

    public static String getMouseModifiersText(int modifiers) {
        return getModifiersExText(extractExFlags(modifiers));
    }

    static String addMouseModifiersExText(String text, int modifiersEx) {
        if ((modifiersEx & InputEvent.BUTTON1_DOWN_MASK) != 0) {
            text += ((text.length() > 0) ? "+" : "") +
                    Toolkit.getProperty("AWT.button1", "Button1");
        }
        if ((modifiersEx & InputEvent.BUTTON2_DOWN_MASK) != 0) {
            text += ((text.length() > 0) ? "+" : "") +
                    Toolkit.getProperty("AWT.button2", "Button2");
        }
        if ((modifiersEx & InputEvent.BUTTON3_DOWN_MASK) != 0) {
            text += ((text.length() > 0) ? "+" : "") +
                    Toolkit.getProperty("AWT.button3", "Button3");
        }

        return text;
    }

    public MouseEvent(Component source, int id, long when,
                      int modifiers, int x, int y,
                      int clickCount, boolean popupTrigger) {
        this(source, id, when, modifiers, x, y,
             clickCount, popupTrigger, NOBUTTON);
    }

    public MouseEvent(Component source, int id, long when,
                      int modifiers, int x, int y,
                      int clickCount, boolean popupTrigger, int button) {
        super(source, id, when, modifiers);

        if ((button != NOBUTTON) && (button != BUTTON1) &&
                (button != BUTTON2) && (button != BUTTON3)) {
            throw new IllegalArgumentException("Invalid button value");
        }

        this.popupTrigger = popupTrigger;
        this.clickCount = clickCount;
        this.button = button;
        this.x = x;
        this.y = y;
    }

    public int getButton() {
        return button;
    }

    public int getClickCount() {
        return clickCount;
    }

    public Point getPoint() {
        return new Point(x, y);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean isPopupTrigger() {
        return popupTrigger;
    }

    public void translatePoint(int x, int y) {
        this.x += x;
        this.y += y;
    }

    @Override
    public String paramString() {
        /* The format is based on 1.5 release behavior 
         * which can be revealed by the following code:
         * 
         * MouseEvent e = new MouseEvent(new Component(){}, 
         *          MouseEvent.MOUSE_PRESSED, 0, 
         *          MouseEvent.BUTTON1_DOWN_MASK|MouseEvent.CTRL_DOWN_MASK,
         *          10, 20, 1, false, MouseEvent.BUTTON1);
         * System.out.println(e);
         */

        String idString = null;
        String paramString = null;

        switch (id) {
        case MOUSE_MOVED:
            idString = "MOUSE_MOVED";
            break;
        case MOUSE_CLICKED:
            idString = "MOUSE_CLICKED";
            break;
        case MOUSE_PRESSED:
            idString = "MOUSE_PRESSED";
            break;
        case MOUSE_RELEASED:
            idString = "MOUSE_RELEASED";
            break;
        case MOUSE_DRAGGED:
            idString = "MOUSE_DRAGGED";
            break;
        case MOUSE_ENTERED:
            idString = "MOUSE_ENTERED";
            break;
        case MOUSE_EXITED:
            idString = "MOUSE_EXITED";
            break;
        case MOUSE_WHEEL:
            idString = "MOUSE_WHEEL";
            break;
        default:
            idString = "unknown type";
        }

        paramString = idString + ",(" + getX() + "," + getY() + ")" +
                ",button=" + button;
        if (getModifiersEx() > 0) {
            paramString += 
                    ",modifiers=" + getModifiersExText(getModifiersEx()) +
                    ",extModifiers=" + getModifiersExText(getModifiersEx());
        }
        paramString += ",clickCount=" + getClickCount();

        return paramString;
    }

}
