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

import java.awt.event.WindowEvent;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;

import javax.accessibility.AccessibleContext;
import javax.accessibility.AccessibleRole;
import javax.accessibility.AccessibleStateSet;

import org.apache.harmony.awt.gl.MultiRectArea;
import org.apache.harmony.awt.wtk.NativeWindow;


public class Frame extends Window implements MenuContainer {
    private static final long serialVersionUID = 2673458971256075116L;

    /**
     * @deprecated Please use {@link Cursor#DEFAULT_CURSOR}.
     */
    public static final int DEFAULT_CURSOR = 0;

    /**
     * @deprecated Please use {@link Cursor#CROSSHAIR_CURSOR}.
     */
    public static final int CROSSHAIR_CURSOR = 1;

    /**
     * @deprecated Please use {@link Cursor#TEXT_CURSOR}.
     */
    public static final int TEXT_CURSOR = 2;

    /**
     * @deprecated Please use {@link Cursor#WAIT_CURSOR}.
     */
    public static final int WAIT_CURSOR = 3;

    /**
     * @deprecated Please use {@link Cursor#SW_RESIZE_CURSOR}.
     */
    public static final int SW_RESIZE_CURSOR = 4;

    /**
     * @deprecated Please use {@link Cursor#SE_RESIZE_CURSOR}.
     */
    public static final int SE_RESIZE_CURSOR = 5;

    /**
     * @deprecated Please use {@link Cursor#NW_RESIZE_CURSOR}.
     */
    public static final int NW_RESIZE_CURSOR = 6;

    /**
     * @deprecated Please use {@link Cursor#NE_RESIZE_CURSOR}.
     */
    public static final int NE_RESIZE_CURSOR = 7;

    /**
     * @deprecated Please use {@link Cursor#N_RESIZE_CURSOR}.
     */
    public static final int N_RESIZE_CURSOR = 8;

    /**
     * @deprecated Please use {@link Cursor#S_RESIZE_CURSOR}.
     */
    public static final int S_RESIZE_CURSOR = 9;

    /**
     * @deprecated Please use {@link Cursor#W_RESIZE_CURSOR}.
     */
    public static final int W_RESIZE_CURSOR = 10;

    /**
     * @deprecated Please use {@link Cursor#E_RESIZE_CURSOR}.
     */
    public static final int E_RESIZE_CURSOR = 11;

    /**
     * @deprecated Please use {@link Cursor#HAND_CURSOR}.
     */
    public static final int HAND_CURSOR = 12;

    /**
     * @deprecated Please use {@link Cursor#MOVE_CURSOR}.
     */
    public static final int MOVE_CURSOR = 13;

    public static final int NORMAL = 0;

    public static final int ICONIFIED = 1;

    public static final int MAXIMIZED_HORIZ = 2;

    public static final int MAXIMIZED_VERT = 4;

    public static final int MAXIMIZED_BOTH = 6;

    private int state = NORMAL;
    private MenuBar menuBar;

    private Image iconImage;

    private Rectangle maximizedBounds;

    protected  class AccessibleAWTFrame extends AccessibleAWTWindow {
        private static final long serialVersionUID = -6172960752956030250L;

        public AccessibleRole getAccessibleRole() {
            toolkit.lockAWT();
            try {
                return AccessibleRole.FRAME;
            } finally {
                toolkit.unlockAWT();
            }
        }

        public AccessibleStateSet getAccessibleStateSet() {
            // do nothing(Window does everything already)
            return super.getAccessibleStateSet();
        }
    }

    public Frame(String title, GraphicsConfiguration gc) {
        super(null, gc);
        toolkit.lockAWT();
        try {
            setTitle(title);
            setResizable(true);
            toolkit.allFrames.add(this);
        } finally {
            toolkit.unlockAWT();
        }
    }

    public Frame(String title) throws HeadlessException {
        this(title, null);
        toolkit.lockAWT();
        try {
        } finally {
            toolkit.unlockAWT();
        }
    }

    public Frame() throws HeadlessException {
        this("");
        toolkit.lockAWT();
        try {
        } finally {
            toolkit.unlockAWT();
        }
    }

    public Frame(GraphicsConfiguration gc) {
        this("", gc);
        toolkit.lockAWT();
        try {
        } finally {
            toolkit.unlockAWT();
        }
    }

    protected void finalize() throws Throwable {
        // do nothing
    }

    public void remove(MenuComponent popup) {
        toolkit.lockAWT();
        try {
            // TODO: implement
            // temporary solve
            super.remove(popup);
        } finally {
            toolkit.unlockAWT();
        }
    }

    public int getState() {
        toolkit.lockAWT();
        try {
            return getExtendedState();
        } finally {
            toolkit.unlockAWT();
        }
    }

    public void addNotify() {
        toolkit.lockAWT();
        try {
            super.addNotify();
            if (menuBar != null) {
                menuBar.addNotify();
            }
        } finally {
            toolkit.unlockAWT();
        }
    }

    public AccessibleContext getAccessibleContext() {
        toolkit.lockAWT();
        try {
            return super.getAccessibleContext();
        } finally {
            toolkit.unlockAWT();
        }
    }

    protected String paramString() {
        toolkit.lockAWT();
        try {
            return (super.paramString() + ",title=" + getTitle() +
                    (isResizable() ? ",resizable" : "") +
                    "," + getStateString());
        } finally {
            toolkit.unlockAWT();
        }
    }

    private String getStateString() {
        String str = "";
        if ( (state & NORMAL) != 0) {
            str = "normal";
        }
        if ( (state & ICONIFIED) != 0) {
            str = "iconified";
        }
        if ( (state & MAXIMIZED_VERT) != 0) {
            str = "maximized_vert";
        }
        if ( (state & MAXIMIZED_HORIZ) != 0) {
            str = "maximized_horiz";
        }
        if ( (state & MAXIMIZED_BOTH) != 0) {
            str = "maximized";
        }

        return str;
    }

    public void removeNotify() {
        toolkit.lockAWT();
        try {
            if (menuBar != null) {
                menuBar.removeNotify();
            }
            super.removeNotify();
        } finally {
            toolkit.unlockAWT();
        }
    }

    /**
     * @deprecated
     */
    public void setCursor(int cursorType) {
        toolkit.lockAWT();
        try {
            setCursor(Cursor.getPredefinedCursor(cursorType));
        } finally {
            toolkit.unlockAWT();
        }
    }

    /**
     * @deprecated
     */
    public int getCursorType() {
        toolkit.lockAWT();
        try {
            return getCursor().getType();
        } finally {
            toolkit.unlockAWT();
        }
    }

    public static Frame[] getFrames() {
        Toolkit.staticLockAWT();
        try {
            return Toolkit.getDefaultToolkit().allFrames.getFrames();
        } finally {
            Toolkit.staticUnlockAWT();
        }
    }

    /**
     * Returns icon image of this frame. 
     */
    public Image getIconImage() {
        toolkit.lockAWT();
        try {
            return iconImage;
        } finally {
            toolkit.unlockAWT();
        }
    }

    public Rectangle getMaximizedBounds() {
        toolkit.lockAWT();
        try {
            return maximizedBounds;
        } finally {
            toolkit.unlockAWT();
        }
    }

    public MenuBar getMenuBar() {
        toolkit.lockAWT();
        try {
            return menuBar;
        } finally {
            toolkit.unlockAWT();
        }
    }

    public String getTitle() {
        toolkit.lockAWT();
        try {
            return super.getTitle();
        } finally {
            toolkit.unlockAWT();
        }
    }

    public boolean isResizable() {
        toolkit.lockAWT();
        try {
            return super.isResizable();
        } finally {
            toolkit.unlockAWT();
        }
    }

    public boolean isUndecorated() {
        toolkit.lockAWT();
        try {
            return super.isUndecorated();
        } finally {
            toolkit.unlockAWT();
        }
    }

    public int getExtendedState() {
        toolkit.lockAWT();
        try {
            return state;
        } finally {
            toolkit.unlockAWT();
        }
    }

    public void setExtendedState(int state) {
        toolkit.lockAWT();
        try {
            int oldState = this.state;
            int newState = NORMAL;

            if (((state & ICONIFIED) > 0) &&
                  toolkit.isFrameStateSupported(ICONIFIED)) {
                newState |= ICONIFIED;
            }
            if (((state & MAXIMIZED_BOTH) == MAXIMIZED_BOTH) &&
                  toolkit.isFrameStateSupported(MAXIMIZED_BOTH))
            {
                newState |= MAXIMIZED_BOTH;
            } else {
                if (((state & MAXIMIZED_VERT) > 0) &&
                        toolkit.isFrameStateSupported(MAXIMIZED_VERT))
                {
                    newState |= MAXIMIZED_VERT;
                }
                if (((state & MAXIMIZED_HORIZ) > 0) &&
                        toolkit.isFrameStateSupported(MAXIMIZED_HORIZ))
                {
                    newState |= MAXIMIZED_HORIZ;
                }
            }

            if (newState != oldState) {
                NativeWindow window = behaviour.getNativeWindow();

                this.state = newState;
                if (window != null) {
                    window.setState(state);
                }
                postStateEvents(oldState);
            }
        } finally {
            toolkit.unlockAWT();
        }
    }

    void updateExtendedState(int state) {
        int oldState = this.state;

        if (state != oldState) {
            this.state = state;
            postStateEvents(oldState);
        }
    }

    private void postStateEvents(int oldState) {
        int newIconified = (state & ICONIFIED);
        int oldIconified = (oldState & ICONIFIED);

        if (newIconified != oldIconified) {
            int eventID = (newIconified != 0 ? WindowEvent.WINDOW_ICONIFIED :
                                               WindowEvent.WINDOW_DEICONIFIED);

            postEvent(new WindowEvent(this, eventID, 0, 0));
        }
        postEvent(new WindowEvent(this, WindowEvent.WINDOW_STATE_CHANGED,
                                  oldState, state));
    }

    public void setIconImage(Image image) {
        toolkit.lockAWT();
        try {
            iconImage = image;
            NativeWindow win = getNativeWindow();
            if (win != null) {
                win.setIconImage(image);
            }
        } finally {
            toolkit.unlockAWT();
        }
    }

    public void setMaximizedBounds(Rectangle bounds) {
        toolkit.lockAWT();
        try {
            maximizedBounds = bounds;
            NativeWindow win = getNativeWindow();
            if (win != null) {
                win.setMaximizedBounds(bounds);
            }
        } finally {
            toolkit.unlockAWT();
        }
    }

    public void setMenuBar(MenuBar menu) {
        toolkit.lockAWT();
        try {
            if (menuBar != null) {
                if (isDisplayable()) {
                    menuBar.removeNotify();
                }
            }
            menuBar = menu;
            if (menuBar != null) {
                menuBar.setParent(this);
                if (isDisplayable()) {
                    menuBar.addNotify();
                }
            }
        } finally {
            toolkit.unlockAWT();
        }
    }

    public void setResizable(boolean resizable) {
        toolkit.lockAWT();
        try {
            super.setResizable(resizable);
        } finally {
            toolkit.unlockAWT();
        }
    }

    public void setState(int state) {
        toolkit.lockAWT();
        try {
            setExtendedState(state);
        } finally {
            toolkit.unlockAWT();
        }
    }

    public void setTitle(String title) {
        super.setTitle(title);
    }

    public void setUndecorated(boolean undecorated) {
        toolkit.lockAWT();
        try {
            super.setUndecorated(undecorated);
        } finally {
            toolkit.unlockAWT();
        }
    }

    public Insets getInsets() {
        toolkit.lockAWT();
        try {
            Insets insets = super.getInsets();
            if (menuBar != null) {
                insets.top += menuBar.getHeight();
            }
            return insets;
        } finally {
            toolkit.unlockAWT();
        }
    }

    void setBounds(int x, int y, int w, int h, int bMask,
                   boolean updateBehavior) {
        boolean widthChanged =  (this.w != w);
        super.setBounds(x, y, w, h, bMask, updateBehavior);
        if ((menuBar != null) && widthChanged) {
            menuBar.validate();
        }
    }

    void nativeWindowCreated(NativeWindow win) {
        super.nativeWindowCreated(win);

        win.setMaximizedBounds(getMaximizedBounds());
    }

    String autoName() {
        int number = toolkit.autoNumber.nextFrame++;
        return ("frame" + Integer.toString(number));
    }

    AccessibleContext createAccessibleContext() {
        return new AccessibleAWTFrame();
    }

    void paintMenuBar(MultiRectArea clipArea) {
        if (menuBar == null) {
            return;
        }
        Point menuPos = menuBar.getLocation();
        Rectangle bounds = new Rectangle(menuPos.x, menuPos.y,
                                         menuBar.getWidth(),
                                         menuBar.getHeight());
        MultiRectArea menuClip = new MultiRectArea(clipArea);
        if (menuClip.getRectCount() > 0) {
            clipArea.substract(bounds);
            menuClip.translate(-menuPos.x, -menuPos.y);
            Graphics g = menuBar.getGraphics(menuClip);
            if (g != null) {
                menuBar.paint(g);
            }
        }
    }

    void validateMenuBar() {
        if (menuBar != null) {
            menuBar.validate();
        }
    }


    static final class AllFrames {
        private final ArrayList<WeakReference<Frame>> frames = new ArrayList<WeakReference<Frame>>();

        void add(Frame f) {
            frames.add(new WeakReference<Frame>(f));
        }

        Frame[] getFrames() {
            ArrayList<Frame> aliveFrames = new ArrayList<Frame>();

            for(Iterator<WeakReference<Frame>> it = frames.iterator(); it.hasNext(); ) {
                WeakReference<Frame> ref = it.next();
                Frame f = ref.get();
                if (f != null) {
                    aliveFrames.add(f);
                }
            }

            return aliveFrames.toArray(new Frame[aliveFrames.size()]);
        }
    }
}

