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
 * @author Dmitry A. Durnev, Pavel Dolgov
 * @version $Revision$
 */
package org.apache.harmony.awt.wtk.windows;

import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;

import org.apache.harmony.awt.nativebridge.Int16Pointer;
import org.apache.harmony.awt.nativebridge.NativeBridge;
import org.apache.harmony.awt.nativebridge.windows.Win32;
import org.apache.harmony.awt.nativebridge.windows.WindowsDefs;
import org.apache.harmony.awt.wtk.NativeEvent;
import org.apache.harmony.awt.wtk.NativeEventListener;
import org.apache.harmony.awt.wtk.NativeEventQueue;
import org.apache.harmony.awt.wtk.NativeWindow;

/**
 * Handler of Windows messages
 */
public class WinEventQueue implements NativeEventQueue {
    static final NativeBridge bridge = NativeBridge.getInstance();
    static final Win32 win32 = Win32.getInstance();

    /**
     * Invisible auxlitary window for service messages
     */
    private long javaWindow;
    /**
     * Theme handles currently open
     */
    private final ThemeMap themeMap = new ThemeMap();

    /**
     * The message being processed
     */
    private final Win32.MSG lastMsg = win32.createMSG(false);

    /**
     * Callback to EventDispatchThread
     */
    private NativeEventListener listener;

    final int dispatchThreadID = win32.GetCurrentThreadId();
    final Thread dispatchThread = Thread.currentThread();
    final WinSystemProperties systemProperties;

    /**
     * The last keyboard message
     */
    private final Win32.MSG charMsg = win32.createMSG(false);
    /**
     * Accumulated typed characters
     */
    private final StringBuffer lastTranslation = new StringBuffer();
    /**
     * Recently typed character
     */
    private char lastChar;
    /**
     * Keycodes and characters stored 
     * until WM_KEYUP or WM_SYSKEYUP message is received 
     */
    private final HashMap keyCodeToChar = new HashMap();

    final WinWindowFactory factory;

    /**
     * Levels of re-entrant windowProc() calls, saved/restored 
     * when entering/leaving nested modal loops
     */
    private LinkedList nestingEndsStack;
    /**
     * Level of re-entrant windowProc() calls for current modal loop. 
     * For main modal loop it is zero.
     */
    private int curNestingEnd;
    private boolean empty;
    /**
     * Current level of re-entrant windowProc() calls
     */
    private int msgNestingCnt;

    private long utcOffset = -1;
    /**
     * Return value for the Windows message being processed
     */
    private final long[] result = new long[1];

    private LinkedList preprocessors = new LinkedList();

    /**
     * Initialize message pump, create auxlitary window for service messages 
     * @param systemProperties
     */
    public WinEventQueue(WinSystemProperties systemProperties) {
        this.systemProperties = systemProperties;
        // Force create EventQueue
        win32.PeekMessageW(lastMsg, 0, WindowsDefs.WM_USER, WindowsDefs.WM_USER, WindowsDefs.PM_NOREMOVE);

        empty = true;
        msgNestingCnt = 0;
        nestingEndsStack = new LinkedList();

        WindowProcHandler.registerCallback();
        factory = new WinWindowFactory(this);

        javaWindow = createJavaWindow();
    }

    public void addPreprocessor(Preprocessor preprocessor) {
        preprocessors.add(preprocessor);
    }

    /**
     * Wait for next Windows messages. The re-entrant calls to windowProc()
     * may occur while waiting
     * @return false when WM_QUIT received
     */
    public boolean waitEvent() {
        win32.GetMessageW(lastMsg, 0, 0, 0);

        empty = (win32.PeekMessageW(0, 0, 0, 0, WindowsDefs.PM_NOREMOVE) == 0);

        return lastMsg.get_message() != WindowsDefs.WM_QUIT;
    }

    /**
     * Post the service message to the auxlitary window to ensure
     * the method waitEvent() will leave the waiting state
     */
    public void awake() {
        if (win32.GetCurrentThreadId() != dispatchThreadID) {
            win32.PostThreadMessageW(dispatchThreadID, WinEvent.WM_AWAKE, 0, 0);
        }
        listener.onAwake();
    }

    /**
     * Handle the service message posted to event dispatch thread
     * @param msg - the Windows message
     */
    private void processThreadMessage(Win32.MSG msg) {
        callListener(0, msg.get_message(), msg.get_wParam(), msg.get_lParam());
    }

    /**
     * Indirectly call the windowProc() to handle the message being processed.
     * Also translate keyboard messages before calling windowProc()
     * @return - the value returned by windowProc()
     */
    public long dispatchEventToListener() {
        if (lastMsg.get_hwnd() == 0) {
            processThreadMessage(lastMsg);
            return 0;
        }

        translateMessage(lastMsg);
        return win32.DispatchMessageW(lastMsg);
    }

    /**
     * Translate key code to typed character for keyboard messages,
     * do nothing for all other messages
     * @param msg - the Windows message to translate
     */
    private void translateMessage(Win32.MSG msg) {
        if (win32.TranslateMessage(msg) == 0) {
            return;
        }

        lastChar = KeyEvent.CHAR_UNDEFINED;
        lastTranslation.setLength(0);

        switch (msg.get_message()) {
        case WindowsDefs.WM_KEYDOWN:
            peekChar(msg, WindowsDefs.WM_CHAR, WindowsDefs.WM_DEADCHAR);
            break;
        case WindowsDefs.WM_SYSKEYDOWN:
            peekChar(msg, WindowsDefs.WM_SYSCHAR, WindowsDefs.WM_SYSDEADCHAR);
            break;
        default:
            peekChar(msg, WindowsDefs.WM_CHAR, WindowsDefs.WM_DEADCHAR);
        }
    }

    /**
     * Determine typed character for WM_KEYDOWN or WM_SYSKEYDOWN message,
     * and store the translation for decoding of subsequent
     * WM_KEYUP or WM_SYSKEYUP messages
     * @param msg - message to find the typed character for
     * @param min - the low bound of range of messages to peek
     * @param max - the high bound of range of messages to peek
     */
    private void peekChar(Win32.MSG msg, int min, int max) {
        while (win32.PeekMessageW(charMsg, msg.get_hwnd(),
                min, max, WindowsDefs.PM_REMOVE) != 0) {

            int msgId = charMsg.get_message();
            int vKey = (int)msg.get_wParam();
            lastChar = (char)charMsg.get_wParam();

            if (msgId == WindowsDefs.WM_CHAR || msgId == WindowsDefs.WM_SYSCHAR) {
                lastTranslation.append(lastChar);
            }

            keyCodeToChar.put(new Integer(vKey), new Character(lastChar));
        }
    }

    /**
     * The callback called by Windows framework to handle the messages
     * @param hwnd - window handle
     * @param msg - message code
     * @param wParam - first message-dependent parameter
     * @param lParam - second message-dependent parameter
     * @return - message-dependent value
     */
    public long windowProc(long hwnd, int msg, long wParam, long lParam) {
        if ((utcOffset < 0) && (lastMsg.get_time() != 0l)) {
            utcOffset = System.currentTimeMillis() - lastMsg.get_time();
        }

        if (preProcessMessage(hwnd, msg,  wParam, lParam, result)) {
            return result[0];
        }
        if (callListener(hwnd, msg,  wParam, lParam)) {
            return 0;
        }

        if (msg == WindowsDefs.WM_MOUSEACTIVATE) {
            return WindowsDefs.MA_NOACTIVATE;
        }
        return win32.DefWindowProcW(hwnd, msg, wParam, lParam);
    }

    /**
     * Call native event listener, pass the native event constructed from
     * the following parameters 
     * @param hwnd - window handle
     * @param msg - message code
     * @param wParam - first message-dependent parameter
     * @param lParam - second message-dependent parameter
     * @return - false if the default Windows handler should be called; 
     *  true otherwise 
     */
    private boolean callListener(long hwnd, int msg, long wParam, long lParam) {
        if (listener == null) {
            // not intialized yet
            if (msg == WindowsDefs.WM_CREATE) {
                // we must return true for WM_CREATE
                // to continue window creation
                return true;
            }
            return false;
        }

        if (msg == WindowsDefs.WM_KEYUP || msg == WindowsDefs.WM_SYSKEYUP) {
            Character ch = (Character)keyCodeToChar.remove(new Integer((int)wParam));
            if (ch != null) {
                lastChar = ch.charValue();
            } else {
                lastChar = KeyEvent.CHAR_UNDEFINED;
            }
        }

        WinEvent decoder = new WinEvent(hwnd, msg, wParam,
                lParam, utcOffset + lastMsg.get_time(),
                lastTranslation, lastChar, this);

        msgNestingCnt++;
        listener.onEventBegin();
        try {
            if ((decoder.getEventId() != NativeEvent.ID_PLATFORM) &&
                    (decoder.getEventId() != NativeEvent.ID_JAVA_EVENT))
            {
                return listener.onEvent(decoder);
            }
            return false;
        } finally {
            listener.onEventEnd();

            if (msgNestingCnt == curNestingEnd) {
                listener.onEventNestingEnd();
            }
            msgNestingCnt--;
        }
    }

    /**
     * Enter nested modal loop
     */
    public void onModalLoopBegin() {
        curNestingEnd = msgNestingCnt + 1;
        nestingEndsStack.addLast(new Integer(curNestingEnd));
    }

    /**
     * Leave nested modal loop
     */
    public void onModalLoopEnd() {
        nestingEndsStack.removeLast();
        if ( !nestingEndsStack.isEmpty()) {
            curNestingEnd = ((Integer) nestingEndsStack.getLast()).intValue();
        } else {
            curNestingEnd = -1;
        }
    }

    /**
     * Call the message preprocessors, handle the Windows messages that
     * should not be passed to the default Windows handler 
     *  
     * @param hwnd - window handle
     * @param msg - message code
     * @param wParam - first message-dependent parameter
     * @param lParam - second message-dependent parameter
     * @param result - result[0] is the return value of the message
     * @return - false if the default Windows handler should be called; 
     */
    private boolean preProcessMessage(long hwnd, int msg, long wParam, long lParam, long[] result) {
        for (Iterator i = preprocessors.iterator(); i.hasNext(); ) {
            if (((Preprocessor) i.next()).preprocess(hwnd, msg, wParam, lParam, result)) {
                return true;
            }
        }

        switch (msg) {
        case WindowsDefs.WM_CREATE:
            factory.onCreateWindow(hwnd);
            break;

        case WindowsDefs.WM_NCACTIVATE:
            // prevent non-focusable window's non-client area from being
            // changed to indicate its active state
            long hwndOther = lParam;
            NativeWindow wnd = factory.getWindowById(hwnd);
            NativeWindow otherWnd = factory.getWindowById(hwndOther);
            if (wParam != 0 && !wnd.isFocusable() ||
                    wParam == 0 && wnd.isFocusable() &&
                    otherWnd != null && !otherWnd.isFocusable() ) {

                result[0] = 0;
                return true;
            }
            break;

        case WindowsDefs.WM_SETCURSOR:
            short htCode = (short) lParam;
            if (htCode == WindowsDefs.HTCLIENT) {
                result[0] = 0;
                return true;
            }
            break;

        case WindowsDefs.WM_GETMINMAXINFO:
            if (processGetMinMaxInfo(hwnd, lParam)) {
                result[0] = 0;
                return true;
            }
            break;

        case WindowsDefs.WM_ERASEBKGND:
            result[0] = 1;
            return true;

        case WindowsDefs.WM_SYSCOMMAND:
            // suppress system menu activation on F10 key
            if ( (wParam & 0xFFF0) == WindowsDefs.SC_KEYMENU) {
                result[0] = 0;
                return true;
            }
            break;

        case WindowsDefs.WM_SYSCOLORCHANGE:
            result[0] = 0;
            if (hwnd == javaWindow) {
                systemProperties.resetSystemColors();
            }
            break;
        }

        return false;
    }

    public void setNativeEventListener(NativeEventListener e) {
        listener = e;
    }

    public boolean isEmpty() {
        return empty;
    }

    public ThemeMap getThemeMap() {
        return themeMap;
    }

    NativeServer getNativeServer() {
        // TODO: obtain NativeServer in more elegant way
        return (NativeServer)((NativeEventListener) dispatchThread).getSynchronizer();
    }

    /**
     * create invisible auxlitary window to listen to the service messages
     * @return HWND of created window
     */
    private static long createJavaWindow() {
        long hwnd = win32.CreateWindowExW(0,
                WindowProcHandler.windowClassName, "JavaWindow",
                0, 0, 0, 0, 0, 0, 0, 0, null);
        int winError = win32.GetLastError();

        if (hwnd == 0) {
            throw new InternalError("Failure to create JavaWindow "
                    + "GetLastError returned " + winError);
        }

        return hwnd;
    }

    /**
     * Apply window's maximized bounds
     * @param hwnd
     */
    private boolean processGetMinMaxInfo(long hwnd, long ptrMinMaxInfo) {
        WinWindow win = factory.getWinWindowById(hwnd);
        if (win == null) {
            return false;
        }
        Rectangle maxBounds = win.maximizedBounds;
        final int MAX = Integer.MAX_VALUE;
        if (maxBounds == null) {
            return false;
        }
        Win32.MINMAXINFO info = win32.createMINMAXINFO(ptrMinMaxInfo);

        if (maxBounds.width < MAX) {
            info.get_ptMaxSize().set_x(maxBounds.width);
        }
        if (maxBounds.height < MAX) {
            info.get_ptMaxSize().set_y(maxBounds.height);
        }
        if (maxBounds.x < MAX) {
            info.get_ptMaxPosition().set_x(maxBounds.x);
        }
        if (maxBounds.y < MAX) {
            info.get_ptMaxPosition().set_y(maxBounds.y);
        }
        return true;
    }

    public long getJavaWindow() {
        return javaWindow;
    }

    /**
     * Interface for message pre-processing
     */
    public interface Preprocessor {

        public boolean preprocess(long hwnd, int msg, long wParam, long lParam, long[] result);

    }

    /**
     * The Windows theme handles currently open, and their names
     */
    public class ThemeMap {
        private final HashMap themes = new HashMap();
        private boolean enabled;

        ThemeMap() {
            enabled = (win32.IsThemeActive() != 0);
        }

        public long get(String name) {
            Long obj = (Long)themes.get(name);
            if (obj != null) {
                return obj.longValue();
            }
            long hTheme = open(name);
            themes.put(new Long(hTheme), name);
            return hTheme;
        }

        private long open(String name) {
            Int16Pointer namePtr = bridge.createInt16Pointer(name, false);
            long hTheme = win32.OpenThemeData(javaWindow, namePtr.lock());
            namePtr.unlock();
            return hTheme;
        }

        public boolean isEnabled() {
            return enabled;
        }

        void refresh() {
            enabled = (win32.IsThemeActive() != 0);

            for (Iterator it = themes.entrySet().iterator(); it.hasNext();) {
                Map.Entry e = (Map.Entry)it.next();
                long hTheme = ((Long)e.getValue()).longValue();
                if (hTheme != 0) {
                    win32.CloseThemeData(hTheme);
                }
                if (enabled) {
                    hTheme = open((String)e.getKey());
                } else {
                    hTheme = 0;
                }
                e.setValue(new Long(hTheme));
            }
        }
    }
}
