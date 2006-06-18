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

import java.awt.datatransfer.ClipboardOwner;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;

import org.apache.harmony.awt.ContextStorage;
import org.apache.harmony.awt.datatransfer.ClipboardEntry;
import org.apache.harmony.awt.datatransfer.FlavorInfo;
import org.apache.harmony.awt.datatransfer.NativeClipboard;
import org.apache.harmony.awt.datatransfer.SystemTransferable;
import org.apache.harmony.awt.nativebridge.windows.Win32;
import org.apache.harmony.awt.nativebridge.windows.WindowsDefs;
import org.apache.harmony.awt.wtk.windows.WinEventQueue;
import org.apache.harmony.misc.accessors.AccessorFactory;
import org.apache.harmony.misc.accessors.ArrayAccessor;
import org.apache.harmony.misc.accessors.LockedArray;
import org.apache.harmony.misc.accessors.MemoryAccessor;

public final class WinClipboard extends NativeClipboard
        implements WinEventQueue.Preprocessor {

    private static final Win32 win32 = Win32.getInstance();
    private static final MemoryAccessor memAccess = AccessorFactory.getMemoryAccessor();
    private static final ArrayAccessor memArrAccess = AccessorFactory.getArrayAccessor();

    private final Requestor requestor;
    private final Owner owner;
    private final Viewer viewer;

    private SystemTransferable contentsStub;

    public WinClipboard() {
        super("System");

        requestor = new Requestor();
        owner = new Owner();
        viewer = new Viewer();

        ((WinEventQueue) ContextStorage.getNativeEventQueue()).addPreprocessor(this);

        updateContents();
    }

    public void onShutdown() {
        if (win32.GetClipboardOwner() == javaWindow) {
            owner.renderAllFormats();
        }
        viewer.unregister();
    }

    public void onRestart() {
        viewer.register();
        updateContents();
    }

    public void setContents(Transferable contents, ClipboardOwner owner) {
        awtSynchronizer.lock();
        try {
            super.setContents(contents, owner);

            this.owner.acquire();
        } finally {
            awtSynchronizer.unlock();
        }
    }

    public Object getData(DataFlavor flavor) throws UnsupportedFlavorException, IOException {
        awtSynchronizer.lock();
        try {
            if (owner.isOwner) {
                return super.getData(flavor);
            } else {
                FlavorInfo info = (FlavorInfo) contentsStub.infoMap.get(flavor);

                if (info == null) {
                    throw new UnsupportedFlavorException(flavor);
                }
                if (info.data == null) {
                    if (info.translated) {
                        throw new RuntimeException("Data translation failed.");
                    } else {
                        if (info.rawData == null) {
                            if (info.read) {
                                throw new IOException("Can't read data.");
                            } else {
                                info.read = true;
                                info.rawData = requestor.getData((int) info.format);
                                if (info.rawData == null) {
                                    throw new IOException("Can't read data.");
                                }
                            }
                        }
                        info.translated = true;
                        info.data = translationManager.translateNative2Java(flavor, info);
                    }
                }

                if (info.data != null) {
                    return info.data;
                }
                throw new RuntimeException(
                        "Data translation failed. Can't find proper clipboard translator.");
            }
        } finally {
            awtSynchronizer.unlock();
        }
    }

    public Transferable getContents(Object requestor) {
        awtSynchronizer.lock();
        try {
            if (owner.isOwner || contentsStub.flavors.isEmpty()) {
                return super.getContents(requestor);
            } else {
                HashSet requestedFormats = new HashSet();
                HashMap dataMap = new HashMap();

                for (Iterator i = contentsStub.flavors.iterator(); i.hasNext(); ) {
                    FlavorInfo info = (FlavorInfo) contentsStub.infoMap.get(i.next());
                    Long format = new Long(info.format);

                    if (requestedFormats.contains(format)) {
                        info.rawData = (byte[]) dataMap.get(format);
                    } else {
                        requestedFormats.add(format);
                        info.rawData = this.requestor.getData((int) info.format);
                        dataMap.put(format, info.rawData);
                    }
                }

                return contentsStub;
            }
        } finally {
            awtSynchronizer.unlock();
        }
    }

    public boolean preprocess(long hwnd, int msg, long wParam, long lParam, long[] result) {
        awtSynchronizer.lock();
        try {
            if (hwnd != javaWindow) {
                return false;
            }

            switch (msg) {
            case WindowsDefs.WM_CHANGECBCHAIN:
                viewer.onChainChange(msg, wParam, lParam);
                result[0] = 0;
                return true;
            case WindowsDefs.WM_DRAWCLIPBOARD:
                viewer.onDraw(msg, wParam, lParam);
                return false;           // to not miss AWT events by sleeping
            case WindowsDefs.WM_RENDERFORMAT:
                owner.renderFormat((int) wParam);
                result[0] = 0;
                return true;
            default:
                return false;
            }
        } finally {
            awtSynchronizer.unlock();
        }
    }

    private void updateContents() {
        awtSynchronizer.lock();
        try {
            LinkedList formats = requestor.getFormatsList();
            contentsStub = translationManager.createTransferableStub(formats);

            super.setContents(contentsStub, null);
        } finally {
            awtSynchronizer.unlock();
        }
    }

    private void openClipboard() {
        long timeout = System.currentTimeMillis() + OPS_TIMEOUT;

        while (win32.OpenClipboard(javaWindow) == 0) {
            if (System.currentTimeMillis() > timeout) {
                throw new IllegalStateException("Can't open clipboard.");
            }
            Thread.yield();
        }
    }

    private class Requestor {

        LinkedList getFormatsList() {
            LinkedList formats = new LinkedList();

            openClipboard();
            try {
                for (int i = win32.EnumClipboardFormats(0);
                        i != 0; i = win32.EnumClipboardFormats(i))
                {
                    formats.add(new Long(i));
                }
            } finally {
                win32.CloseClipboard();
            }

            return formats;
        }

        byte[] getData(int format) {
            byte[] data = null;

            openClipboard();
            try {
                long globalHandler = win32.GetClipboardData(format);
                long dataLength = win32.GlobalSize(globalHandler);

                if ((dataLength > 0) && (((int) dataLength) == dataLength)) {
                    long globalPtr = win32.GlobalLock(globalHandler);
                    LockedArray arr = null;

                    try {
                        data = new byte[(int) dataLength];
                        arr = memArrAccess.lockArrayShort(data);

                        if (globalPtr == 0) {
                            throw new RuntimeException(
                                    "Cannot allocate global memory (" + dataLength + " bytes)");
                        }

                        memAccess.memcpy(arr.getAddress(), globalPtr, dataLength);
                    } finally {
                        win32.GlobalUnlock(globalHandler);
                        arr.release();
                    }
                }
            } finally {
                win32.CloseClipboard();
            }

            return data;
        }

    }

    private class Viewer {

        private long nextHWND;

        Viewer() {
            register();
        }

        void onDraw(int msg, long wParam, long lParam) {
            if (win32.GetClipboardOwner() != javaWindow) {
                owner.isOwner = false;
                updateContents();
            }
            win32.SendMessageW(nextHWND, msg, wParam, lParam);
        }

        void onChainChange(int msg, long wParam, long lParam) {
            if (wParam == nextHWND) {
                nextHWND = lParam;
            } else if (nextHWND != 0) {
                win32.SendMessageW(nextHWND, msg, wParam, lParam);
            }
        }

        void register() {
            owner.isOwner = false;
            nextHWND = win32.SetClipboardViewer(javaWindow);
        }

        void unregister() {
            owner.isOwner = false;
            win32.ChangeClipboardChain(javaWindow, nextHWND);
        }

    }

    private class Owner {

        boolean isOwner;

        private final HashMap contentMap = new HashMap();
        private final ArrayList formats = new ArrayList();

        void acquire() {
            LinkedList entries = translationManager.translateJava2Native(contents);

            isOwner = true;
            openClipboard();
            try {
                win32.EmptyClipboard();
                contentMap.clear();
                formats.clear();

                for (Iterator i = entries.iterator(); i.hasNext();) {
                    ClipboardEntry entry = (ClipboardEntry) i.next();
                    Integer format = new Integer((int) entry.format);

                    formats.add(format);
                    contentMap.put(format, entry.data);
                    win32.SetClipboardData((int) entry.format, 0);
                }
            } finally {
                win32.CloseClipboard();
            }
        }

        void renderFormat(int format) {
            // Initiated by native system so must not open clipboard it's already opened
            byte[] data = (byte[]) contentMap.get(new Integer(format));

            if (data == null) {
                return;
            }

            int size = data.length;
            long globalHandler = win32.GlobalAlloc(WindowsDefs.GMEM_MOVEABLE, size);
            long globalPtr = win32.GlobalLock(globalHandler);
            LockedArray arr = memArrAccess.lockArrayShort(data);

            if (globalPtr == 0) {
                return;
            }

            memAccess.memcpy(globalPtr, arr.getAddress(), size);
            arr.release();
            win32.GlobalUnlock(globalHandler);
            win32.SetClipboardData(format, globalHandler);
        }

        void renderAllFormats() {
            openClipboard();
            try {
                if (win32.GetClipboardOwner() != javaWindow) {
                    return;
                }
                win32.EmptyClipboard();

                for (Iterator i = formats.iterator(); i.hasNext();) {
                    renderFormat(((Integer) i.next()).intValue());
                }
            } finally {
                win32.CloseClipboard();
            }
        }

    }

}
