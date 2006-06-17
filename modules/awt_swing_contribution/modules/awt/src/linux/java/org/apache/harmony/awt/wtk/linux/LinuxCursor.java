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
package org.apache.harmony.awt.wtk.linux;

import org.apache.harmony.awt.wtk.NativeCursor;

import org.apache.harmony.awt.nativebridge.linux.X11;

/**
 * Implementation of NativeCursor for Linux(X11) platform.
 */
public class LinuxCursor implements NativeCursor {
    private static X11 x11 = X11.getInstance();
    private final long display;
    private final long cursorId;
    private boolean system;
    private boolean valid = true; //cursor is valid if it has not been destroyed yet

    LinuxCursor(final long id, final boolean system, long display) {
        cursorId = id;
        this.system = system;
        this.display = display;
    }

    LinuxCursor(final long id, long display) {
        this(id, true, display);
    }

    /**
     * @see org.apache.harmony.awt.wtk.NativeCursor#setCursor(long)
     */
    public void setCursor(long winID) {
        if (valid) {
            x11.XDefineCursor(display, winID, cursorId);
        }
    }

    /**
     * Destroys any non-system(user-defined cursor)
     * @see org.apache.harmony.awt.wtk.NativeCursor#destroyCursor()
     */
    public void destroyCursor() {
        if (!system) {
            destroy();
        }
    }

    /**
     * Destroys any cursor
     */
    void destroy() {
        if (valid) {
            x11.XFreeCursor(display, cursorId);
            valid = false;
        }
    }
    /**
     * @see org.apache.harmony.awt.wtk.NativeCursor#getId()
     */
    public long getId() {
        return cursorId;
    }

}
