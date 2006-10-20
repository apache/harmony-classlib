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
 * @author Pavel Dolgov, Dmitry A. Durnev
 * @version $Revision$
 */
package org.apache.harmony.awt.wtk.windows;

import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.font.TextAttribute;
import java.awt.im.InputMethodHighlight;
import java.util.Map;
import java.util.TreeSet;
import org.apache.harmony.awt.ComponentInternals;
import org.apache.harmony.awt.nativebridge.Int32Pointer;
import org.apache.harmony.awt.nativebridge.NativeBridge;
import org.apache.harmony.awt.nativebridge.windows.Win32;
import org.apache.harmony.awt.nativebridge.windows.WindowsDefs;
import org.apache.harmony.awt.wtk.SystemProperties;

/**
 * WinSystemProperties
 * More information about Windows Desktop Properties can be found at http://java.sun.com/j2se/1.5.0/docs/guide/swing/1.4/w2k_props.html
 */

public class WinSystemProperties implements SystemProperties {

    private static final Win32 win32 = Win32.getInstance();
    
    private static final int SPI_GETFONTSMOOTHINGTYPE = 0x200A;
    private static final int SPI_SETFONTSMOOTHINGTYPE = 0x200B;
    private static final int SPI_GETFONTSMOOTHINGCONTRAST = 0x200C;
    private static final int SPI_SETFONTSMOOTHINGCONTRAST = 0x200D;

    private static final int sysColorIndices[] = {    // SystemColor.* constants
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
            // colors not present in SystemColor class:
            WindowsDefs.COLOR_GRADIENTACTIVECAPTION,
            WindowsDefs.COLOR_GRADIENTINACTIVECAPTION,
            WindowsDefs.COLOR_HOTLIGHT,
            WindowsDefs.COLOR_APPWORKSPACE,
            WindowsDefs.COLOR_MENUBAR,
    };
    
    private static final String sysColorProps[] = { // color property names
            "win.desktop.backgroundColor", // 0 DESKTOP
            "win.frame.activeCaptionColor", // 1 ACTIVE_CAPTION
            "win.frame.captionTextColor", // 2 ACTIVE_CAPTION_TEXT
            "win.frame.activeBorderColor", // 3 ACTIVE_CAPTION_BORDER
            "win.frame.inactiveCaptionColor", // 4 INACTIVE_CAPTION
            "win.frame.inactiveCaptionTextColor", // 5 INACTIVE_CAPTION_TEXT
            "win.frame.inactiveBorderColor", // 6 INACTIVE_CAPTION_BORDER
            "win.frame.backgroundColor", // 7 WINDOW
            "win.frame.color", //? 8 WINDOW_BORDER
            "win.frame.textColor", // 9 WINDOW_TEXT
            "win.menu.backgroundColor", // 10 MENU
            "win.menu.textColor", // 11 MENU_TEXT
            "win.frame.backgroundColor", // 12 TEXT
            "win.frame.textColor", // 13 TEXT_TEXT
            "win.item.highlightColor", // 14 TEXT_HIGHLIGHT
            "win.item.highlightTextColor", // 15 TEXT_HIGHLIGHT_TEXT
            "win.text.grayedTextColor", // 16 TEXT_INACTIVE_TEXT
            "win.3d.backgroundColor", // 17 CONTROL
            "win.button.textColor", // 18 CONTROL_TEXT
            "win.3d.lightColor", // 19 CONTROL_HIGHLIGHT
            "win.3d.highlightColor", // 20 CONTROL_LT_HIGHLIGHT
            "win.3d.shadowColor", // 21 CONTROL_SHADOW
            "win.3d.darkShadowColor", // 22 CONTROL_DK_SHADOW
            "win.scrollbar.backgroundColor", // 23 SCROLLBAR
            "win.tooltip.backgroundColor", // 24 INFO
            "win.tooltip.textColor", // 25 INFO_TEXT
            // colors not present in SystemColor class:
            "win.frame.activeCaptionGradientColor",
            "win.frame.inactiveCaptionGradientColor",
            "win.item.hotTrackedColor",
            "win.mdi.backgroundColor",
            "win.menubar.backgroundColor",
    };

    
    private static final int sysFontIndices[] = {

        WindowsDefs.ANSI_FIXED_FONT, 
        WindowsDefs.ANSI_VAR_FONT,                                           
        WindowsDefs.DEFAULT_GUI_FONT,                                           
        WindowsDefs.DEVICE_DEFAULT_FONT,                                           
        WindowsDefs.OEM_FIXED_FONT,                                           
        WindowsDefs.SYSTEM_FONT,                                           
        WindowsDefs.SYSTEM_FIXED_FONT,
    
    };
    
    private static final String sysFontDesktopProps[] = { // color property names
            "win.ansiFixed.font", // 0 ANSI_FIXED_FONT
            "win.ansiVar.font", // 1 ANSI_VAR_FONT
            "win.defaultGUI.font", // 2 DEFAULT_GUI_FONT
            "win.deviceDefault.font", // 3 DEVICE_DEFAULT_FONT            
            "win.oemFixed.font", // 4 OEM_FIXED_FONT            
            "win.system.font", // 5 SYSTEM_FONT
            "win.systemFixed.font", // 6 SYSTEM_FIXED_FONT            
    };    

    private class WinPlaySound implements Runnable {
        final String sndName;
        
        WinPlaySound(String snd) {
            sndName = snd;            
        }
        
        public void run() {
            final int flags = (WindowsDefs.SND_ALIAS | WindowsDefs.SND_SYNC |
                               WindowsDefs.SND_NODEFAULT);
            // have to start another thread to wait until playing ends
            new Thread() {
                @Override
                public void run() {                    
                    win32.PlaySoundW(sndName, 0, flags);
                }
            }.start();
        }
        
        @Override
        public String toString() {
            return ("WinPlaySound(" + sndName + ")");
        }
    };
    
    private int sysColorCache[];

    private Font defaultFont;

    private class CacheLock {}
    private final Object cacheLock = new CacheLock();

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
            updateColorCache();
            updateColorDesktopProperties(null);
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

    private void updateColorDesktopProperties(Map<String, Object> props) {
        for (int i = 0; i < sysColorIndices.length; i++) {
            String name = sysColorProps[i];
            Color val = new Color(sysColorCache[i]);
            
            setDesktopProperty(name, val, props);
        }
    }

    private void setDesktopProperty(String name, Object val, Map<String, Object> props) {
        if (props != null) {
            props.put(name, val);
            return; // skip firing property change
        }
        ComponentInternals ci = ComponentInternals.getComponentInternals();
        ci.setDesktopProperty(name, val);
    }
    
    public Font getDefaultFont() {
        synchronized (cacheLock) {
            if (defaultFont == null) {
                defaultFont = (Font) Toolkit.getDefaultToolkit().
                getDesktopProperty("win.defaultGUI.font");
            }
            return defaultFont;
        }
    }

    private Font getFont(long hFont) {
        Win32.LOGFONTW logFont = win32.createLOGFONTW(false);
        win32.GetObjectW(hFont, logFont.size(), logFont.longLockPointer());
        logFont.unlock();
        return getFont(logFont);
    }
    
    private Font getFont(Win32.LOGFONTW logFont) {
        String name = logFont.get_lfFaceName().getString();
        int size = Math.abs(logFont.get_lfHeight());
        int weight = logFont.get_lfWeight();
        int bold = (weight == WindowsDefs.FW_BOLD ? Font.BOLD : 0);
        int italic = (logFont.get_lfItalic() != 0) ? Font.ITALIC : 0;
        return new Font(name, bold | italic, size);
    }
    
    private void initSysFonts(Map<String, Object> props) {
        for (int i = 0; i < sysFontIndices.length; i++) {
            long hFont = win32.GetStockObject(sysFontIndices[i]);            
            Font font = getFont(hFont);
            if (props != null) {
                String propName = sysFontDesktopProps[i];
                setFontProperty(propName, font, props);
            }
        }       
    }
    
    private void setBoolProperty(String propName, int val, Map<String, Object> props) {
        setDesktopProperty(propName, (val != 0 ? Boolean.TRUE : Boolean.FALSE),
                           props);
    }

    private void setIntProperty(String propName, int val, Map<String, Object> props) {
        setDesktopProperty(propName, new Integer(val), props);
    }
    
    private void setFontProperty(String propName, Font font, Map<String, Object> props) {
        setDesktopProperty(propName, font, props);
        setIntProperty(propName + ".height", font.getSize(), props);
    }

    @SuppressWarnings("unchecked")
    public void init(Map<String, ?> deskProps) {
        Map<String, Object> desktopProperties = (Map<String, Object>)deskProps;
        synchronized (cacheLock) {
            getDnDProps(desktopProperties);
            getShellProps(desktopProperties);
            getMouseProps(desktopProperties);
            if (sysColorCache == null) {
                updateColorCache();
            }
            updateColorDesktopProperties(desktopProperties);
            initSysFonts(desktopProperties);
            getNonClientMetrics(desktopProperties);
            getIconMetrics(desktopProperties);
            getDragSize(true, desktopProperties);
            getDragSize(false, desktopProperties);
            getGradientCaptions(desktopProperties);
            getFullWindowDrags(desktopProperties);
            getHighContrast(desktopProperties);
            getHotTracking(desktopProperties);
            getKeyboardCues(desktopProperties);
            getFontSmooth(desktopProperties);
            getFontSmoothContrast(desktopProperties);
            getFontSmoothType(desktopProperties);
            getXPTheme(desktopProperties);
            initSoundProps(desktopProperties);
            TreeSet<String> keySet = new TreeSet<String>(desktopProperties.keySet());
            keySet.add("awt.dynamicLayoutSupported");
            String[] propNames = keySet.toArray(new String[0]);            
            desktopProperties.put("win.propNames", propNames);
            
        }

    }
    
    void processSettingChange(long idx) {
        switch((int)idx) {
        case WindowsDefs.SPI_SETNONCLIENTMETRICS:
            getNonClientMetrics(null);
            break;
        case WindowsDefs.SPI_SETICONMETRICS:
            getIconMetrics(null);
            break;
        case WindowsDefs.SPI_SETDRAGHEIGHT:                        
        case WindowsDefs.SPI_SETDRAGWIDTH:
            getDragSize(idx == WindowsDefs.SPI_SETDRAGWIDTH, null);
            break;
        case WindowsDefs.SPI_SETGRADIENTCAPTIONS:
            getGradientCaptions(null);
            break;
        case WindowsDefs.SPI_SETDRAGFULLWINDOWS:
            getFullWindowDrags(null);
            break;
        case WindowsDefs.SPI_SETHIGHCONTRAST:
            getHighContrast(null);
            break;
        case WindowsDefs.SPI_SETHOTTRACKING:
            getHotTracking(null);
            break;
        case WindowsDefs.SPI_SETKEYBOARDCUES:
            getKeyboardCues(null);
            break;
        case WindowsDefs.SPI_SETFONTSMOOTHING:
            getFontSmooth(null);
            break;
        case SPI_SETFONTSMOOTHINGCONTRAST:
            getFontSmoothContrast(null);
            break;
        case SPI_SETFONTSMOOTHINGTYPE:
            getFontSmoothType(null);
            break;
        case WindowsDefs.SPI_SETDOUBLECLICKTIME:
        case WindowsDefs.SPI_SETWHEELSCROLLLINES:
            getMouseProps(null);
            break;
        }
        
    }
    
    private void getFullWindowDrags(Map<String, Object> props) {        
        getBoolSPI("win.frame.fullWindowDragsOn", 
                   WindowsDefs.SPI_GETDRAGFULLWINDOWS, props);
        
    }
    
    private void getGradientCaptions(Map<String, Object> props) {        
        getBoolSPI("win.frame.captionGradientsOn", 
                   WindowsDefs.SPI_GETGRADIENTCAPTIONS, props);        
    }
    
    private void getHotTracking(Map<String, Object> props) {        
        getBoolSPI("win.item.hotTrackingOn", 
                   WindowsDefs.SPI_GETHOTTRACKING, props);        
    }
    
    private void getKeyboardCues(Map<String, Object> props) {        
        getBoolSPI("win.menu.keyboardCuesOn", 
                   WindowsDefs.SPI_GETKEYBOARDCUES, props);        
    }
    
    private void getFontSmooth(Map<String, Object> props) {        
        getBoolSPI("win.text.fontSmoothingOn", 
                   WindowsDefs.SPI_GETFONTSMOOTHING, props);        
    }
    
    private void getFontSmoothContrast(Map<String, Object> props) {
        getIntSPI("win.text.fontSmoothingContrast",
                  SPI_GETFONTSMOOTHINGCONTRAST, props);
    }
    
    private void getFontSmoothType(Map<String, Object> props) {
        getIntSPI("win.text.fontSmoothingType", SPI_GETFONTSMOOTHINGTYPE, props);
    }
    
    private void getDragSize(boolean cx, Map<String, Object> props) {
        int sm_idx = (cx ? WindowsDefs.SM_CXDRAG : WindowsDefs.SM_CYDRAG);
        String propName = "win.drag";
        propName += (cx ? ".width" : ".height");
        getSM(propName, sm_idx, props);
    }

    private void getNonClientMetrics(Map<String, Object> props) {
        Win32.NONCLIENTMETRICSW ncm = win32.createNONCLIENTMETRICSW(false);
        ncm.set_cbSize(ncm.size());
        win32.SystemParametersInfoW(WindowsDefs.SPI_GETNONCLIENTMETRICS,
                                    ncm.size(), ncm.getElementPointer(0), 0);
        setFontProperty("win.frame.captionFont",
                        getFont(ncm.get_lfCaptionFont()), props);
        setFontProperty("win.frame.smallCaptionFont",
                        getFont(ncm.get_lfSmCaptionFont()), props);
        setFontProperty("win.menu.font", getFont(ncm.get_lfMenuFont()), props);
        setFontProperty("win.messagebox.font",
                        getFont(ncm.get_lfMessageFont()), props);
        setFontProperty("win.status.font", getFont(ncm.get_lfStatusFont()),
                        props);
        setFontProperty("win.tooltip.font", getFont(ncm.get_lfStatusFont()),
                        props);
        setIntProperty("win.frame.sizingBorderWidth", ncm.get_iBorderWidth(),
                       props);
        setIntProperty("win.scrollbar.width", ncm.get_iScrollWidth(),
                       props);
        setIntProperty("win.scrollbar.height", ncm.get_iScrollHeight(),
                       props);
        setIntProperty("win.frame.captionButtonWidth", ncm.get_iCaptionWidth(),
                       props);
        setIntProperty("win.frame.captionButtonHeight", ncm.get_iCaptionHeight(),
                       props);
        setIntProperty("win.frame.captionHeight", ncm.get_iCaptionHeight(),
                       props);
        setIntProperty("win.frame.smallCaptionButtonWidth", ncm.get_iSmCaptionWidth(),
                       props);
        setIntProperty("win.frame.smallCaptionButtonHeight", ncm.get_iSmCaptionHeight(),
                       props);
        setIntProperty("win.frame.smallCaptionHeight", ncm.get_iSmCaptionHeight(),
                       props);
        setIntProperty("win.menu.buttonWidth", ncm.get_iMenuWidth(), props);
        setIntProperty("win.menu.height", ncm.get_iMenuHeight(), props);        
    }
    
    private void getIconMetrics(Map<String, Object> props) {
        Win32.ICONMETRICSW im = win32.createICONMETRICSW(false);
        im.set_cbSize(im.size());
        win32.SystemParametersInfoW(WindowsDefs.SPI_GETICONMETRICS,
                                    im.size(), im.getElementPointer(0), 0);
        setFontProperty("win.icon.font", getFont(im.get_lfFont()), props);
        setIntProperty("win.icon.hspacing", im.get_iHorzSpacing(), props);
        setBoolProperty("win.icon.titleWrappingOn", im.get_iTitleWrap(), props);
        setIntProperty("win.icon.vspacing", im.get_iVertSpacing(), props);
    }
    
    private void getHighContrast(Map<String, Object> props) {
        Win32.HIGHCONTRASTA hc = win32.createHIGHCONTRASTA(false);
        hc.set_cbSize(hc.size());
        win32.SystemParametersInfoW(WindowsDefs.SPI_GETHIGHCONTRAST,
                                    hc.size(), hc.getElementPointer(0), 0);        
        setBoolProperty("win.highContrast.on", 
                        hc.get_dwFlags() & WindowsDefs.HCF_HIGHCONTRASTON, 
                        props);
    }
    
    private void getSM(String propName, int idx, Map<String, Object> props) {
        setIntProperty(propName, win32.GetSystemMetrics(idx), props);
    }
    
    private int getIntSPI(int idx) {
        NativeBridge bridge = NativeBridge.getInstance();
        Int32Pointer ptr = bridge.createInt32Pointer(1, false);
        win32.SystemParametersInfoW(idx, 0, ptr, 0);
        return ptr.get(0);
    }
    
    private void getBoolSPI(String propName, int idx, Map<String, Object> props) {
        setBoolProperty(propName, getIntSPI(idx), props);
    }
    
    private void getIntSPI(String propName, int idx, Map<String, Object> props) {
        setIntProperty(propName, getIntSPI(idx), props);
    }
    
    void getXPTheme(Map<String, Object> props) {
        setBoolProperty("win.xpstyle.themeActive", win32.IsThemeActive(), props);
    }
    
    private void getDnDProps(Map<String, Object> props) {
        String propName = "DnD";
        setIntProperty(propName + ".gestureMotionThreshold",
                       WindowsDefs.DD_DEFDRAGMINDIST, props);
        propName += ".Autoscroll";
        setIntProperty(propName + ".initialDelay",
                       WindowsDefs.DD_DEFSCROLLDELAY, props);
        setIntProperty(propName + ".interval",
                       WindowsDefs.DD_DEFSCROLLINTERVAL, props);        
        
    }
    
    private void getShellProps(Map<String, Object> props) {        
        Win32.SHELLFLAGSTATE sfs = win32.createSHELLFLAGSTATE(false);
        int flags = (WindowsDefs.SSF_SHOWATTRIBCOL | 
                     WindowsDefs.SSF_SHOWALLOBJECTS);
        win32.SHGetSettings(sfs, flags);
        String propName = "awt.file";
        setBoolProperty(propName + ".showAttribCol", 
                        sfs.get_fShowAttribCol(), props);
        setBoolProperty(propName + ".showHiddenFiles", 
                        (sfs.get_fShowAllObjects()), props);
    }
    
    private void getMouseProps(Map<String, Object> props) {
        String propName = "awt.";
        getSM(propName + "mouse.numButtons", WindowsDefs.SM_CMOUSEBUTTONS, props);
        setIntProperty(propName + "multiClickInterval", 
                       win32.GetDoubleClickTime(), props);
        setBoolProperty(propName + "wheelMousePresent",
                        win32.GetSystemMetrics(WindowsDefs.SM_MOUSEWHEELPRESENT),
                        props);
        getIntSPI(propName + "wheelScrollLines",
                  WindowsDefs.SPI_GETWHEELSCROLLLINES, props);
        
    }
    
    private void setSoundProperty(String propName, String sndName, Map<String, Object> props) {
        String base = "win.sound.";
        setDesktopProperty(base + propName, new WinPlaySound(sndName), props);
    }
    
    private void initSoundProps(Map<String, Object> props) {
        setSoundProperty("asterisk", "SystemAsterisk", props);
        setSoundProperty("close", "Close", props);
        setSoundProperty("default", ".Default", props);
        setSoundProperty("exclamation", "SystemExclamation", props);
        setSoundProperty("exit", "SystemExit", props);
        setSoundProperty("hand", "SystemHand", props);
        setSoundProperty("maximize", "Maximize", props);
        setSoundProperty("menuCommand", "MenuCommand", props);
        setSoundProperty("menuPopup", "MenuPopup", props);
        setSoundProperty("minimize", "Minimize", props);
        setSoundProperty("open", "Open", props);
        setSoundProperty("question", "SystemQuestion", props);
        setSoundProperty("restoreDown", "RestoreDown", props);
        setSoundProperty("restoreUp", "RestoreUp", props);
        setSoundProperty("start", "SystemStart", props);
    
    }

    @SuppressWarnings("unchecked")
    public void mapInputMethodHighlight(InputMethodHighlight highlight, Map<TextAttribute, ?> map) {
        Map<TextAttribute, Object> _map = (Map<TextAttribute, Object>)map;
        TextAttribute key = TextAttribute.INPUT_METHOD_UNDERLINE;
        boolean selected = highlight.isSelected();
        switch(highlight.getState()) {
        case InputMethodHighlight.RAW_TEXT:
            _map.put(key, selected ? TextAttribute.UNDERLINE_LOW_GRAY :
                TextAttribute.UNDERLINE_LOW_DOTTED);
            break;
        case InputMethodHighlight.CONVERTED_TEXT:
            _map.put(key, selected ? TextAttribute.UNDERLINE_LOW_ONE_PIXEL :
                TextAttribute.UNDERLINE_LOW_DOTTED);
            if (selected) {
                // maybe get colors from system properties?
                key = TextAttribute.BACKGROUND;
                _map.put(key, Color.white);
                key = TextAttribute.FOREGROUND;
                _map.put(key, new Color(0, 0, 128));
                key = TextAttribute.SWAP_COLORS;
                _map.put(key, TextAttribute.SWAP_COLORS_ON);
            }
            break;
        }
    }
}
