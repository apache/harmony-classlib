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

import java.awt.Font;

import org.apache.harmony.awt.nativebridge.windows.Win32;
import org.apache.harmony.awt.nativebridge.windows.WindowsDefs;
import org.apache.harmony.awt.wtk.SystemProperties;

/**
 * WinSystemProperties
 */

public class WinSystemProperties implements SystemProperties {

    private static final Win32 win32 = Win32.getInstance();

    private static int sysColorIndices[] = {    // SystemColor.* constants
            WindowsDefs.COLOR_DESKTOP,          // 0 DESKTOP
            WindowsDefs.COLOR_ACTIVECAPTION,    // 1 ACTIVE_CAPTION
            WindowsDefs.COLOR_CAPTIONTEXT,      // 2 ACTIVE_CAPTION_TEXT
            WindowsDefs.COLOR_ACTIVEBORDER,     // 3 ACTIVE_CAPTION_BORDER
            WindowsDefs.COLOR_INACTIVECAPTION,  // 4 INACTIVE_CAPTION
            WindowsDefs.COLOR_INACTIVECAPTIONTEXT, // 5 INACTIVE_CAPTION_TEXT
            WindowsDefs.COLOR_INACTIVEBORDER,   // 6 INACTIVE_CAPTION_BORDER
            WindowsDefs.COLOR_WINDOW,           // 7 WINDOW
            WindowsDefs.COLOR_WINDOWFRAME,      // 8 WINDOW_BORDER
            WindowsDefs.COLOR_WINDOWTEXT,       // 9 WINDOW_TEXT
            WindowsDefs.COLOR_MENU,             // 10 MENU
            WindowsDefs.COLOR_MENUTEXT,         // 11 MENU_TEXT
            WindowsDefs.COLOR_WINDOW,           // 12 TEXT
            WindowsDefs.COLOR_WINDOWTEXT,       // 13 TEXT_TEXT
            WindowsDefs.COLOR_HIGHLIGHT,        // 14 TEXT_HIGHLIGHT
            WindowsDefs.COLOR_HIGHLIGHTTEXT,    // 15 TEXT_HIGHLIGHT_TEXT
            WindowsDefs.COLOR_GRAYTEXT,         // 16 TEXT_INACTIVE_TEXT
            WindowsDefs.COLOR_3DFACE,           // 17 CONTROL
            WindowsDefs.COLOR_BTNTEXT,          // 18 CONTROL_TEXT
            WindowsDefs.COLOR_3DLIGHT,          // 19 CONTROL_HIGHLIGHT
            WindowsDefs.COLOR_3DHILIGHT,        // 20 CONTROL_LT_HIGHLIGHT
            WindowsDefs.COLOR_3DSHADOW,         // 21 CONTROL_SHADOW
            WindowsDefs.COLOR_3DDKSHADOW,       // 22 CONTROL_DK_SHADOW
            WindowsDefs.COLOR_SCROLLBAR,        // 23 SCROLLBAR
            WindowsDefs.COLOR_INFOBK,           // 24 INFO
            WindowsDefs.COLOR_INFOTEXT,         // 25 INFO_TEXT
    };

    private int sysColorCache[] = null;

    private Font defaultFont;

    private final Object cacheLock = new Object();

    public int getSystemColorARGB(int index) {
        synchronized (cacheLock) {
            if (sysColorCache == null) {
                updateColorCache();
            }
            return sysColorCache[index];
        }
    }

    void resetSystemColors() {
        synchronized (cacheLock) {
            sysColorCache = null;
        }
    }

    private void updateColorCache() {
        sysColorCache = new int[sysColorIndices.length];
        for (int i=0; i<sysColorIndices.length; i++) {
            int n = sysColorIndices[i];
            int bgr = win32.GetSysColor(n);
            int b = (bgr & 0xFF0000) >> 16;
            int g = (bgr & 0xFF00);
            int r = (bgr & 0xFF) << 16;
            sysColorCache[i] = (0xFF000000 | r | g | b);
        }
    }

    public Font getDefaultFont() {
        synchronized (cacheLock) {
            if (defaultFont == null) {
                initDefaultFont();
            }
            return defaultFont;
        }
    }

    private void initDefaultFont() {
        long hFont = win32.GetStockObject(WindowsDefs.DEFAULT_GUI_FONT);
        Win32.LOGFONTW logFont = win32.createLOGFONTW(false);
        win32.GetObjectW(hFont, logFont.size(), logFont.longLockPointer());
        logFont.unlock();

        String name = logFont.get_lfFaceName().getString();
        int size = Math.abs(logFont.get_lfHeight());
        int bold = (logFont.get_lfWeight() == WindowsDefs.FW_BOLD) ? Font.BOLD : 0;
        int italic = (logFont.get_lfItalic() != 0) ? Font.ITALIC : 0;

        defaultFont = new Font(name, bold|italic, size);
    }
}
