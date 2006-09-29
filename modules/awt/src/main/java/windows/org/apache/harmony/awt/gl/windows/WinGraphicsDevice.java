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
 * @author Alexey A. Petrenko
 * @version $Revision$
 */
package org.apache.harmony.awt.gl.windows;

import java.awt.Dimension;
import java.awt.DisplayMode;
import java.awt.GraphicsConfiguration;
import java.awt.Rectangle;
import java.util.Vector;

import org.apache.harmony.awt.gl.GLGraphicsDevice;
import org.apache.harmony.awt.nativebridge.windows.Win32;
import org.apache.harmony.awt.nativebridge.windows.WindowsDefs;

/**
 * Windows GraphicsDevice implementation
 *
 */
public class WinGraphicsDevice extends GLGraphicsDevice {
    private DisplayMode displayMode = null;

    private static final Win32 win32 = Win32.getInstance();

    private final int type;
    private final Rectangle bounds;
    private final String id;
    private final boolean primary;

    private Dimension resolution = null;

    private WinGraphicsConfiguration defaultConfig = null;
    private WinGraphicsConfiguration []configs = null;

    private byte []idBytes = null;

    public WinGraphicsDevice(int left, int top, int right, int bottom, String id, boolean primary) {
        type = TYPE_RASTER_SCREEN;
        bounds = new Rectangle(left, top, right-left, bottom-top);
        displayMode = new DisplayMode(bounds.width, bounds.height, DisplayMode.BIT_DEPTH_MULTI, DisplayMode.REFRESH_RATE_UNKNOWN);
        this.id = id;
        this.primary = primary;
    }

    public WinGraphicsDevice(long hwnd) {
        type = TYPE_RASTER_SCREEN;

        long hmon = win32.MonitorFromWindow(hwnd, WindowsDefs.MONITOR_DEFAULTTOPRIMARY);
        Win32.MONITORINFOEXW mi = win32.createMONITORINFOEXW(false);
        mi.get_MONITORINFO().set_cbSize(mi.size());
        if (win32.GetMonitorInfoW(hmon, mi.shortLockPointer()) == 0)
            throw new RuntimeException("Can not get monitor info");
        Win32.RECT rect = mi.get_MONITORINFO().get_rcMonitor();
        int x = (int)rect.get_left();
        int y = (int)rect.get_top();
        int width = (int)rect.get_right() - x;
        int height = (int)rect.get_bottom() - y;
        bounds = new Rectangle(x, y, width, height);
        displayMode = new DisplayMode(bounds.width, bounds.height, DisplayMode.BIT_DEPTH_MULTI, DisplayMode.REFRESH_RATE_UNKNOWN);
        id = mi.get_szDevice().getString();
        primary = (mi.get_MONITORINFO().get_dwFlags() & WindowsDefs.MONITORINFOF_PRIMARY) == WindowsDefs.MONITORINFOF_PRIMARY;
        rect.free();
        mi.free();
    }

    public int getType() {
        return type;
    }

    public GraphicsConfiguration getDefaultConfiguration() {
        if (defaultConfig == null) {
            long hdc = win32.CreateDCW(null, id, null, null);
            if (hdc == 0)
                throw new RuntimeException("Can not create DC for device");

            int dci = win32.GetPixelFormat(hdc);
            Win32.PIXELFORMATDESCRIPTOR pfd = win32.createPIXELFORMATDESCRIPTOR(false);
            if (win32.DescribePixelFormat(hdc, dci, pfd.size(), pfd) > 0) {
                defaultConfig = new WinGraphicsConfiguration(this, dci, pfd);
            } else {
                getConfigurations();
            }
            win32.DeleteDC(hdc);
        }

        return defaultConfig;
    }

    public GraphicsConfiguration[] getConfigurations() {
        if (configs == null) {
            long hdc = win32.CreateDCW(null, id, null, null);
            if (hdc == 0)
                throw new RuntimeException("Can not create DC for device");

            // If we created DC why do not ask it about resolution?
            if (resolution == null) {
                int width = win32.GetDeviceCaps(hdc, WindowsDefs.LOGPIXELSY);
                int height = win32.GetDeviceCaps(hdc, WindowsDefs.LOGPIXELSX);
                resolution = new Dimension(width, height);
            }

            Win32.PIXELFORMATDESCRIPTOR pfd = win32.createPIXELFORMATDESCRIPTOR(false);
            int pfnum = win32.DescribePixelFormat(hdc, 1, pfd.size(), pfd);
            if (pfnum == 0)
                return null;

            // Choose default configuration
            int dci = win32.GetPixelFormat(hdc);

            Vector gcv = new Vector(100);
            int i = 1;
            while (win32.DescribePixelFormat(hdc, i, pfd.size(), pfd) > 0) {
                WinGraphicsConfiguration gc = new WinGraphicsConfiguration(this, i, pfd);

                if (!gcv.contains(gc))
                    gcv.add(gc);

                // Is default PixelFormat?
                if (dci == i) {
                    int idx = gcv.indexOf(gc);
                    gcv.set(idx, gc);
                    defaultConfig = gc;
                }

                i++;
            }
            pfd.free();

            win32.DeleteDC(hdc);

            configs = new WinGraphicsConfiguration [gcv.size()];
            gcv.toArray(configs);

            // If we can not find default PixelFormat
            // let's use first one
            // Probably not best idea...
            if (defaultConfig == null)
                defaultConfig = configs[0];
        }

        return configs;
    }

    public String getIDstring() {
        return id;
    }

    public boolean isDefaultDevice() {
        return primary;
    }

    public Rectangle getBounds() {
        return bounds;
    }

    public String toString() {
        return getClass().getName()+"[Bounds: "+bounds+", ID: "+id+", primary: "+primary+"]";
    }

    byte[] getIDBytes() {
        if (idBytes == null) {
            idBytes = (id + "\0x00").getBytes();
        }

        return idBytes;
    }

    public DisplayMode getDisplayMode() {
        return displayMode;
    }

    public DisplayMode[] getDisplayModes() {
        DisplayMode []dms = {displayMode};
        return  dms;
    }

    public Dimension getResolution() {
        if (resolution == null) {
            long hdc = win32.CreateDCW(null, id, null, null);
            if (hdc == 0)
                throw new RuntimeException("Can not create DC for device");

            if (resolution == null) {
                int width = win32.GetDeviceCaps(hdc, WindowsDefs.LOGPIXELSY);
                int height = win32.GetDeviceCaps(hdc, WindowsDefs.LOGPIXELSX);
                resolution = new Dimension(width, height);
            }

            win32.DeleteDC(hdc);
        }

        return resolution;
    }
}
