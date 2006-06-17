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
 * @author Michael Danilov
 * @version $Revision$
 */
package org.apache.harmony.awt.datatransfer.windows;

import java.awt.datatransfer.DataFlavor;

import org.apache.harmony.awt.datatransfer.ClipboardTranslator;
import org.apache.harmony.awt.datatransfer.NativeTranslationManager;
import org.apache.harmony.awt.datatransfer.PureTextTranslator;
import org.apache.harmony.awt.datatransfer.TextTranslator;
import org.apache.harmony.awt.datatransfer.TranslationPoint;
import org.apache.harmony.awt.datatransfer.UnsupportedTranslationPoint;
import org.apache.harmony.awt.nativebridge.Int16Pointer;
import org.apache.harmony.awt.nativebridge.NativeBridge;
import org.apache.harmony.awt.nativebridge.windows.Win32;
import org.apache.harmony.awt.nativebridge.windows.WindowsDefs;


public final class WinTranslationManager extends NativeTranslationManager {

    private static final int MAX_FORMAT_NAME_LENGTH = 200;

    private static final Win32 win32 = Win32.getInstance();
    private static final NativeBridge bridge = NativeBridge.getInstance();

    private static final HDROPTranslator hdropTranslator = new HDROPTranslator();
    private static final PureTextTranslator unicodeTranslator =
            new PureTextTranslator(WinFlavorMap.UNICODE, "utf-16LE");
    private static final WinTextTranslator textTranslator =
            new WinTextTranslator();

    public WinTranslationManager() {
        super();

        addNativeFormat(WinFlavorMap.UNICODE, (WindowsDefs.CF_UNICODETEXT));
        addNativeFormat(WinFlavorMap.TEXT, (WindowsDefs.CF_TEXT));
        addNativeFormat(WinFlavorMap.HDROP, (WindowsDefs.CF_HDROP));
        addNativeFormat(WinFlavorMap.LOCALE, (WindowsDefs.CF_LOCALE));
        addNativeFormat(WinFlavorMap.BITMAP, (WindowsDefs.CF_DIB));
        addNativeFormat(WinFlavorMap.ENHANCED_METAFILE, (WindowsDefs.CF_ENHMETAFILE));
        addNativeFormat(WinFlavorMap.METAFILE, (WindowsDefs.CF_METAFILEPICT));
        addNativeFormat("BITMAP", (WindowsDefs.CF_BITMAP));
        addNativeFormat("DIBV5", (WindowsDefs.CF_DIBV5));
        addNativeFormat("DIF", (WindowsDefs.CF_DIF));
        addNativeFormat("DSPBITMAP", (WindowsDefs.CF_DSPBITMAP));
        addNativeFormat("DSPENHMETAFILE", (WindowsDefs.CF_DSPENHMETAFILE));
        addNativeFormat("DSPMETAFILEPICT", (WindowsDefs.CF_DSPMETAFILEPICT));
        addNativeFormat("DSPTEXT ", (WindowsDefs.CF_DSPTEXT));
        addNativeFormat("OEMTEXT", (WindowsDefs.CF_OEMTEXT));
        addNativeFormat("OWNERDISPLAY", (WindowsDefs.CF_OWNERDISPLAY));
        addNativeFormat("PALETTE", (WindowsDefs.CF_PALETTE));
        addNativeFormat("PENDATA", (WindowsDefs.CF_PENDATA));
        addNativeFormat("RIFF", (WindowsDefs.CF_RIFF));
        addNativeFormat("SYLK", (WindowsDefs.CF_SYLK));
        addNativeFormat("WAVE", (WindowsDefs.CF_WAVE));
        addNativeFormat("TIFF", (WindowsDefs.CF_TIFF));

        initTranslationTable();
    }

    private void initTranslationTable() {
        try {
            TextTranslator.TextFlavorsIterator iterator =
                    TextTranslator.getFlavorsIterator();
            URLTranslator urlTrans = new URLTranslator();

            for (DataFlavor i = iterator.getMainFlavor(); i != null; i = iterator.getNextFlavor()) {
                addTranslationPoint(
                        new TranslationPoint(i, WinFlavorMap.UNICODE), unicodeTranslator);
                addTranslationPoint(
                        new TranslationPoint(i, WinFlavorMap.TEXT), textTranslator);
                addTranslationPoint(
                        new TranslationPoint(i, WinFlavorMap.UNIFORM_RESOURCE_LOCATOR), urlTrans);
            }
            addTranslationPoint(WinFlavorMap.urlAndURLObject, new URLTranslator());
            addTranslationPoint(WinFlavorMap.urlAndURIList, new URLTranslator());
            addTranslationPoint(WinFlavorMap.htmlAndUTFStream, new HTMLTranslator());

            addTranslationPoint(WinFlavorMap.hdropAndFileList, hdropTranslator);
            addTranslationPoint(WinFlavorMap.binaryAndRTF, getByteTranslator());
            addTranslationPoint(WinFlavorMap.binaryAndLocale, getByteTranslator());
        } catch (UnsupportedTranslationPoint e) {
            throw new RuntimeException("Can't register standart clipboard translator:" + e);
        }
    }

    protected String getRegisteredNative(long format) {
        Int16Pointer natPtr = bridge.createInt16Pointer(MAX_FORMAT_NAME_LENGTH, false);

        if (win32.GetClipboardFormatNameW((int) format, natPtr, MAX_FORMAT_NAME_LENGTH) == 0) {
            throw new RuntimeException("Cannot retrieve system clipboard format name");
        }

        String nat = natPtr.getString();
        addNativeFormat(nat, format);

        return nat;
    }

    protected long registerNewFormat(String nat) {
        long format = win32.RegisterClipboardFormatW(nat);

        if (format == 0) {
            throw new RuntimeException("Cannot register system clipboard format");
        }
        addNativeFormat(nat, format);

        return format;
    }

    protected ClipboardTranslator deriveTranslatorIfText(TranslationPoint point) {
        if (point.flavor.isFlavorTextType()) {
            if (point.natife.equals(WinFlavorMap.UNICODE)) {
                return unicodeTranslator;
            } else if (point.natife.equals(WinFlavorMap.TEXT)) {
                return textTranslator;
            }
        }

        return null;
    }

}
