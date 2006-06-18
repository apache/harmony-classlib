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
package org.apache.harmony.awt.wtk.windows;

import java.awt.Dimension;
import java.awt.Image;

import org.apache.harmony.awt.nativebridge.windows.Win32;
import org.apache.harmony.awt.wtk.CursorFactory;
import org.apache.harmony.awt.wtk.NativeCursor;

import org.apache.harmony.awt.nativebridge.windows.WindowsDefs;

/**
 * Implementation of CursorFactory for Windows platform.
 */
public class WinCursorFactory extends CursorFactory implements WindowsDefs {

    static final Win32 win32 = Win32.getInstance();

    /**
     * Java to native type translation table:
     * native id for LoadCursor(commented native id for LoadImage(from winuser.h)),
     * commented Java cursor type
     */
    static final long [] predefined = {
            IDC_ARROW/*OCR_NORMAL*/, /*DEFAULT_CURSOR*/
            IDC_CROSS/*OCR_CROSS*/, /*CROSSHAIR_CURSOR*/
            IDC_IBEAM/*OCR_IBEAM*/, /*TEXT_CURSOR*/
            IDC_WAIT /*OCR_WAIT*/, /*WAIT_CURSOR*/
            IDC_SIZENESW/*OCR_SIZENESW*/, /*SW_RESIZE_CURSOR*/
            IDC_SIZENWSE/*OCR_SIZENWSE*/, /*SE_RESIZE_CURSOR*/
            IDC_SIZENWSE/*OCR_SIZENWSE*/, /*NW_RESIZE_CURSOR*/
            IDC_SIZENESW/*OCR_SIZENESW*/, /*NE_RESIZE_CURSOR*/
            IDC_SIZENS/*OCR_SIZENS*/, /*N_RESIZE_CURSOR*/
            IDC_SIZENS/*OCR_SIZENS*/, /*S_RESIZE_CURSOR*/
            IDC_SIZEWE/*OCR_SIZEWE*/, /*W_RESIZE_CURSOR*/
            IDC_SIZEWE/*OCR_SIZEWE*/, /*E_RESIZE_CURSOR*/
            IDC_HAND/*OCR_HAND*/, /*HAND_CURSOR*/
            IDC_SIZEALL/*OCR_SIZEALL*/, /*MOVE_CURSOR*/

    };
    /**
     * @see org.apache.harmony.awt.wtk.CursorFactory#createCursor(int)
     */
    public NativeCursor createCursor(int type) {
        if (type >= 0 && type < predefined.length) {
            long hCursor = win32.LoadCursorW(0l, predefined[type]);
            return new WinCursor(hCursor);
        }
        return null;
    }

    /**
     * @see org.apache.harmony.awt.wtk.CursorFactory#createCustomCursor(java.awt.Image, int, int)
     */
    public NativeCursor createCustomCursor(Image img, int xHotSpot, int yHotSpot) {
        long hCursor = WinIcons.createIcon(false, img, xHotSpot, yHotSpot);
        return new WinCursor(hCursor, false);
    }

    /**
     * @see org.apache.harmony.awt.wtk.CursorFactory#getBestCursorSize(int, int)
     */
    public Dimension getBestCursorSize(int prefWidth, int prefHeight) {
        return new Dimension(win32.GetSystemMetrics(SM_CXCURSOR),
                win32.GetSystemMetrics(SM_CYCURSOR));
    }

    /**
     * @see org.apache.harmony.awt.wtk.CursorFactory#getMaximumCursorColors()
     */
    public int getMaximumCursorColors() {
        long screenDC = win32.GetDC(0);
        int nColors = win32.GetDeviceCaps(screenDC, NUMCOLORS);
        if (nColors < 0) {
            //if device has more than 256 colors:
            final int COLORS_PER_PLANE = 256;
            int nPlanes = win32.GetDeviceCaps(screenDC, PLANES);
            nColors = COLORS_PER_PLANE * nPlanes;
        }
        win32.ReleaseDC(0, screenDC);
        return nColors;
    }
}

