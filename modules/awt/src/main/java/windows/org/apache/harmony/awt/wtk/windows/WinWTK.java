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
package org.apache.harmony.awt.wtk.windows;

import java.awt.GraphicsDevice;

import org.apache.harmony.awt.wtk.*;


public class WinWTK extends WTK {

    /**
     * @see org.apache.harmony.awt.wtk.WTK#getGraphicsFactory()
     */
    public GraphicsFactory getGraphicsFactory() {
        return graphicsFactory;
    }

    /**
     * @see org.apache.harmony.awt.wtk.WTK#getNativeEventQueue()
     */
    public NativeEventQueue getNativeEventQueue() {
        return eventQueue;
    }

    /**
     * @see org.apache.harmony.awt.wtk.WTK#getWindowFactory()
     */
    public WindowFactory getWindowFactory() {
        return eventQueue.factory;
    }

    /**
     * @see org.apache.harmony.awt.wtk.WTK#getCursorFactory()
     */
    public CursorFactory getCursorFactory() {
        return cursorFactory;
    }

    /**
     * @see org.apache.harmony.awt.wtk.WTK#getNativeMouseInfo()
     */
    public NativeMouseInfo getNativeMouseInfo() {
        return mouseInfo;
    }

    /**
     * @see org.apache.harmony.awt.wtk.WTK#getSystemProperties()
     */
    public SystemProperties getSystemProperties() {
        return systemProperties;
    }

    WinEventQueue getWinEventQueue() {
        return eventQueue;
    }

    /**
     * @see org.apache.harmony.awt.wtk.WTK#getNativeRobot(java.awt.GraphicsDevice)
     */
    public NativeRobot getNativeRobot(GraphicsDevice screen) {
        if (robot == null) {
            robot = new WinRobot();
        }
        return robot;
    }

    private final WinSystemProperties systemProperties = new WinSystemProperties();
    private final WinEventQueue eventQueue = new WinEventQueue(systemProperties);
    private final GraphicsFactory graphicsFactory = new org.apache.harmony.awt.gl.windows.WinGraphics2DFactory();
    private final CursorFactory cursorFactory = new WinCursorFactory();
    private final NativeMouseInfo mouseInfo = new WinMouseInfo();
    private WinRobot robot;

}
