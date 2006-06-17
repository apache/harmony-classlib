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
package org.apache.harmony.awt.datatransfer.linux;

import java.awt.EventQueue;
import java.awt.datatransfer.ClipboardOwner;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.FlavorListener;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;

import org.apache.harmony.awt.ComponentInternals;
import org.apache.harmony.awt.ContextStorage;
import org.apache.harmony.awt.PeriodicTimer;
import org.apache.harmony.awt.RelativeTimer;
import org.apache.harmony.awt.datatransfer.ClipboardEntry;
import org.apache.harmony.awt.datatransfer.FlavorInfo;
import org.apache.harmony.awt.datatransfer.NativeClipboard;
import org.apache.harmony.awt.datatransfer.SystemTransferable;
import org.apache.harmony.awt.nativebridge.linux.X11;
import org.apache.harmony.awt.nativebridge.linux.X11Defs;
import org.apache.harmony.awt.wtk.linux.LinuxEventDecoder;
import org.apache.harmony.awt.wtk.linux.LinuxWindowFactory;
import org.apache.harmony.misc.accessors.AccessorFactory;
import org.apache.harmony.misc.accessors.ArrayAccessor;
import org.apache.harmony.misc.accessors.LockedArray;
import org.apache.harmony.misc.accessors.MemoryAccessor;

public final class LinuxSelection
        extends NativeClipboard implements LinuxWindowFactory.Preprocessor
{

    private static final ArrayAccessor memArrAccess = AccessorFactory.getArrayAccessor();
    private static final MemoryAccessor memAccess = AccessorFactory.getMemoryAccessor();
    private static final int POINTER_SIZE = memAccess.getPointerSize();
    private static final X11 x11 = X11.getInstance();

    private static final int MAX_TARGETS_NUM = 50;
    private static final int INT_SIZE = 4;
    private static final int EXAMENATION_PERIOD = 1000;

    private final ComponentInternals toolkit;
    private final LinuxWindowFactory factory;
    private final long display;

    private final long xaSelection;
    private final long xaTargets;
    private final long xaMultiple;
    private final long xaText;
    private final long xaUTF8;
    private final long xaSTRING;

    private final Owner owner;
    private final Requestor requestor;
    private final RelativeTimer examinationTimer;

    private final Runnable dataRequestor = new Runnable() {
        public void run() {
            flavorInfo.rawData = requestor.requestData(flavorInfo.format);
        }
    };
    private final Runnable targetsRequestor = new Runnable() {
        public void run() {
            availableFormats = requestor.requestTargets();
        }
    };

    private FlavorInfo flavorInfo;
    private LinkedList availableFormats;

    public LinuxSelection(String selection) {
        super("System");

        toolkit = ComponentInternals.getComponentInternals();
        factory = (LinuxWindowFactory) ContextStorage.getNativeEventQueue();
        display = factory.getDisplay();

        xaSelection = factory.internAtom(selection);
        xaTargets = factory.internAtom("TARGETS");
        xaMultiple = factory.internAtom("MULTIPLE");
        xaText = factory.internAtom("TEXT");
        xaUTF8 = factory.internAtom("UTF8_STRING");
        xaSTRING = factory.internAtom("STRING");

        owner = new Owner();
        requestor = new Requestor();
        flavorInfo = null;
        availableFormats = null;

        factory.addPreprocessor(this);
        examinationTimer = new PeriodicTimer(EXAMENATION_PERIOD, new Runnable() {
            public void run() {
                EventQueue.invokeLater(new Runnable() {
                    public void run() {
                        updateClipboardFlavors();
                    }
                });
            }
        });

        updateClipboardFlavors();
    }

    public void addFlavorListener(FlavorListener listener) {
        awtSynchronizer.lock();
        try {
            super.addFlavorListener(listener);
            if (getFlavorListeners().length == 1) {
                examinationTimer.start();
            }
        } finally {
            awtSynchronizer.unlock();
        }
    }

    public void removeFlavorListener(FlavorListener listener) {
        awtSynchronizer.lock();
        try {
            super.removeFlavorListener(listener);
            if (getFlavorListeners().length == 0) {
                examinationTimer.stop();
            }
        } finally {
            awtSynchronizer.unlock();
        }
    }

    public DataFlavor[] getAvailableDataFlavors() {
        awtSynchronizer.lock();
        try {
            if (!owner.isOwner) {
                updateClipboardFlavorsImpl();
            }

            return super.getAvailableDataFlavors();
        } finally {
            awtSynchronizer.unlock();
        }
    }

    public boolean isDataFlavorAvailable(DataFlavor flavor) {
        awtSynchronizer.lock();
        try {
            if (!owner.isOwner) {
                updateClipboardFlavorsImpl();
            }

            return super.isDataFlavorAvailable(flavor);
        } finally {
            awtSynchronizer.unlock();
        }
    }

    public void setContents(Transferable contents, ClipboardOwner owner) {
        awtSynchronizer.lock();
        try {
            super.setContents(contents, owner);

            if (!this.owner.acquire()) {
                throw new IllegalStateException("Can't acquire clipboard.");
            }
        } finally {
            awtSynchronizer.unlock();
        }
    }

    public Transferable getContents(Object requestor) {
        awtSynchronizer.lock();
        try {
            if (owner.isOwner) {
                return super.getContents(requestor);
            } else {
                SystemTransferable transferable = updateClipboardFlavorsImpl();

                if (transferable.flavors.isEmpty()) {
                    return super.getContents(requestor);
                }

                HashSet requestedFormats = new HashSet();
                HashMap dataMap = new HashMap();

                for (Iterator i = transferable.flavors.iterator(); i.hasNext(); ) {
                    flavorInfo = (FlavorInfo) transferable.infoMap.get(i.next());
                    Long format = new Long(flavorInfo.format);

                    if (requestedFormats.contains(format)) {
                        flavorInfo.rawData = (byte[]) dataMap.get(format);
                    } else {
                        requestedFormats.add(format);
                        if (!runOnDispatchThread(dataRequestor)) {
                            flavorInfo.rawData = null;
                        }
                        dataMap.put(format, flavorInfo.rawData);
                    }
                }

                return transferable;
            }
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
                flavorInfo = (FlavorInfo) updateClipboardFlavorsImpl().infoMap.get(flavor);

                if (flavorInfo == null) {
                    throw new UnsupportedFlavorException(flavor);
                }

                if (!runOnDispatchThread(dataRequestor) || (flavorInfo.rawData == null)) {
                    throw new IOException("Can't read data.");
                }

                flavorInfo.data = translationManager.translateNative2Java(flavor, flavorInfo);

                if (flavorInfo.data != null) {
                    return flavorInfo.data;
                }
                throw new RuntimeException(
                        "Data translation failed. Can't find proper clipboard translator.");
            }
        } finally {
            awtSynchronizer.unlock();
        }
    }

    private boolean runOnDispatchThread(Runnable runnable) {
        if (EventQueue.isDispatchThread()) {
            runnable.run();
        } else {
            try {
                toolkit.unsafeInvokeAndWait(runnable);
            } catch (Exception e) {
                return false;
            }
        }

        return true;
    }

    private void updateClipboardFlavors() {
        awtSynchronizer.lock();
        try {
            if (!owner.isOwner) {
                updateClipboardFlavorsImpl();
            }
        } finally {
            awtSynchronizer.unlock();
        }
    }

    private SystemTransferable updateClipboardFlavorsImpl() {
        SystemTransferable transferable;

        availableFormats = null;
        runOnDispatchThread(targetsRequestor);
        transferable = translationManager.createTransferableStub(availableFormats);
        super.setContents(transferable, null);

        return transferable;
    }

    public boolean preprocess(X11.XEvent event) {
        awtSynchronizer.lock();
        try {
            int type = event.get_type();

            switch (type) {
            case X11Defs.SelectionRequest:
                return owner.processRequest(event.get_xselectionrequest());
            case X11Defs.SelectionClear:
                return owner.processClear(event.get_xselectionclear());
            case X11Defs.SelectionNotify:
                // Just ignore. Can get here only when notification timeout expired. Too late.
                return true;
            default:
                return false;
            }
        } finally {
            awtSynchronizer.unlock();
        }
    }

    private class Requestor {

        private X11.XEvent event = x11.createXEvent(false);

        byte[] requestData(long target) {
            if (convert(target)) {
                return readData(target);
            } else {
                return null;
            }
        }

        LinkedList requestTargets() {
            if (convert(xaTargets)) {
                return readTargets();
            } else {
                return null;
            }
        }

        private boolean convert(long target) {
            long timeout = System.currentTimeMillis() + OPS_TIMEOUT;

            x11.XDeleteProperty(display, javaWindow, target);
            x11.XConvertSelection(display, xaSelection, target,
                    target, javaWindow, X11Defs.CurrentTime);

            while (true) {
                if (System.currentTimeMillis() > timeout) {
                    return false;
                }

                if (x11.XCheckTypedEvent(display, X11Defs.SelectionNotify, event)
                        == X11Defs.TRUE) {
                X11.XSelectionRequestEvent sre = event.get_xselectionrequest();
                    if ((sre.get_selection() == xaSelection)
                            && (sre.get_requestor() == javaWindow)
                            && (sre.get_target() == target)) {
                        return (sre.get_property() != X11Defs.None);
                    }

                    return false;
                }
            }
        }

        private byte[] readData(long target) {
            PropertyParams params = new PropertyParams();
            long dataPtr;
            int entrySize;
            byte[] data;

            int res = x11.XGetWindowProperty(display, javaWindow, target, 0, Integer.MAX_VALUE,
                    X11Defs.AnyPropertyType, X11Defs.False, params.typePtr, params.formatPtr,
                    params.itemsNumPtr, params.bytesLeftPtr, params.dataPtrPtr);

            if ((res != X11Defs.Success) || (memAccess.getPointer(params.itemsNumPtr) == 0)) {
                return null;
            }

            dataPtr = memAccess.getPointer(params.dataPtrPtr);
            entrySize = memAccess.getInt(params.formatPtr) / 8;
            if (entrySize == 4) {
                entrySize = memAccess.getPointerSize();
            }
            data = new byte[entrySize * (int) memAccess.getPointer(params.itemsNumPtr)];

            try {
                LockedArray locked = memArrAccess.lockArrayShort(data);

                memAccess.memcpy(locked.getAddress(), dataPtr, data.length);

                x11.XDeleteProperty(display, javaWindow, target);
                locked.release();

                return data;
            } finally {
                x11.XFree(dataPtr);
                params.free();
            }
        }

        private LinkedList readTargets() {
            PropertyParams params = new PropertyParams();
            long dataPtr;
            long itemsNum;

            int res = x11.XGetWindowProperty(display, javaWindow, xaTargets, 0, MAX_TARGETS_NUM,
                    X11Defs.AnyPropertyType, X11Defs.False, params.typePtr, params.formatPtr,
                    params.itemsNumPtr, params.bytesLeftPtr, params.dataPtrPtr);
            dataPtr = memAccess.getPointer(params.dataPtrPtr);
            itemsNum = memAccess.getPointer(params.itemsNumPtr);

            try {
                if ((res != X11Defs.Success) || (memAccess.getInt(params.formatPtr) != 32)
                        || (memAccess.getPointer(params.bytesLeftPtr) != 0))
                {
                    return null;
                }

                LinkedList formats = new LinkedList();

                for (long addr = dataPtr + ((int) itemsNum - 1) * POINTER_SIZE;
                        addr >= dataPtr; addr -= POINTER_SIZE)
                {
                    formats.addFirst(new Long(memAccess.getPointer(addr)));
                }
                x11.XDeleteProperty(display, javaWindow, xaTargets);

                return formats;
            } finally {
                x11.XFree(dataPtr);
                params.free();
            }
        }

    }

    private class Owner {

        private long owningTime = 0;
        private boolean isOwner = false;
        private HashMap entriesMap = null;

        boolean acquire() {
            long curTime = System.currentTimeMillis();
            long timeout = curTime + OPS_TIMEOUT;

            owningTime = curTime - LinuxEventDecoder.getUTCOffset();
            do {
                x11.XSetSelectionOwner(display, xaSelection, javaWindow, owningTime);
                if (x11.XGetSelectionOwner(display, xaSelection) == javaWindow) {
                    break;
                }
                if (System.currentTimeMillis() > timeout) {
                    return false;
                }
                Thread.yield();
            } while (true);
            initFields();

            return true;
        }

        boolean processClear(X11.XSelectionClearEvent event) {
            if (event.get_selection() != xaSelection) {
                return false;
            }

            isOwner = false;
            updateClipboardFlavorsImpl();

            // Must return false. Otherwise can wait for OwnershipLost forever
            return false;
        }

        boolean processRequest(X11.XSelectionRequestEvent event) {
            long requestor = event.get_requestor();
            long target = event.get_target();
            long property = event.get_property();
            long time = event.get_time();
            boolean err = false;

            if ((event.get_selection() != xaSelection) || (requestor == X11Defs.None)) {
                return false;
            }

            if (isOwner && ((time == X11Defs.CurrentTime) || (time >= owningTime))) {
                if (target == xaMultiple) {
                    err = (property == X11Defs.None);
                    err = (err || !copyMultiple(requestor, property));
                } else {
                    if (target == xaText) {
                        long text = findTextAtom();

                        if (text == X11Defs.None) {
                            err = true;
                        } else {
                            target = property = text;
                        }
                    } else if (property == X11Defs.None) {
                        property = target;
                    }
                    err = (err || !copyData(requestor, property, target));
                }
            }

            x11.XSendEvent(display, requestor, X11Defs.FALSE, X11Defs.None,
                    createNotifyEvent(event, err ? X11Defs.None : property));

            return true;
        }

        private long findTextAtom() {
            if (entriesMap.containsKey(new Long(xaSTRING))) {
                return xaSTRING;
            } else if (entriesMap.containsKey(new Long(xaUTF8))) {
                return xaUTF8;
            } else {
                return X11Defs.None;
            }
        }

        private void initFields() {
            LinkedList entries = translationManager.translateJava2Native(contents);
            byte[] targets = new byte[POINTER_SIZE * (entries.size() + 3)];
            LockedArray lockedTargets = memArrAccess.lockArrayShort(targets);
            long addr = lockedTargets.getAddress();
            boolean text = false;

            isOwner = true;
            entriesMap = new HashMap();

            memAccess.setPointer(addr, xaMultiple);
            addr += POINTER_SIZE;
            memAccess.setPointer(addr, xaTargets);

            for (Iterator i = entries.iterator(); i.hasNext(); ) {
                ClipboardEntry entry = (ClipboardEntry) i.next();

                text = (text || (entry.format == xaSTRING) || (entry.format == xaUTF8));
                entriesMap.put(new Long(entry.format), entry);
                addr += POINTER_SIZE;
                memAccess.setPointer(addr, entry.format);
            }
            if (text) {
                addr += POINTER_SIZE;
                memAccess.setPointer(addr, xaText);
                entriesMap.put(new Long(xaTargets), new ClipboardEntry(xaTargets, targets));
            } else {
                int length = targets.length - POINTER_SIZE;
                byte[] shortTargets = new byte[length];

                System.arraycopy(targets, 0, shortTargets, 0, length);
                entriesMap.put(new Long(xaTargets), new ClipboardEntry(xaTargets, shortTargets));
            }
            lockedTargets.release();

        }

        private boolean copyMultiple(long requestor, long property) {
            PropertyParams params = new PropertyParams();
            long itemsNum;
            long dataPtr;

            int res = x11.XGetWindowProperty(display, requestor, property, 0, MAX_TARGETS_NUM * 2,
                    X11Defs.AnyPropertyType, X11Defs.False, params.typePtr, params.formatPtr,
                    params.itemsNumPtr, params.bytesLeftPtr, params.dataPtrPtr);
            dataPtr = memAccess.getPointer(params.dataPtrPtr);
            itemsNum = memAccess.getPointer(params.itemsNumPtr);

            try {
                if ((res != X11Defs.Success) || (memAccess.getInt(params.formatPtr) != 32)
                        || ((itemsNum % 2) != 0)
                        || (memAccess.getPointer(params.bytesLeftPtr) != 0))
                {
                    return false;
                }

                if (!copyMultiplePairs(requestor, dataPtr, itemsNum)) {
                    x11.XChangeProperty(display, requestor, property,
                            memAccess.getLong(params.typePtr), 32, X11Defs.PropModeReplace,
                            dataPtr, (int) itemsNum);
                }

                return true;
            } finally {
                x11.XFree(dataPtr);
                params.free();
            }
        }

        private boolean copyMultiplePairs(long requestor, long dataPtr, long itemsNum) {
            boolean noErrs = true;

            for (long pair = dataPtr + ((itemsNum - 2) * POINTER_SIZE);
                    pair >= dataPtr; pair -= 2 * POINTER_SIZE)
            {
                long target = memAccess.getLong(pair);
                long propertyAddr = pair + POINTER_SIZE;
                long property = memAccess.getLong(propertyAddr);
                boolean err = false;

                if (property == xaText) {
                    long text = findTextAtom();

                    if (text == X11Defs.None) {
                        err = true;
                    } else {
                        target = property = text;
                    }
                }
                err = (err || !copyData(requestor, property, target));

                if (err) {
                    noErrs = false;
                    memAccess.setLong(propertyAddr, X11Defs.None);
                }
            }

            return noErrs;
        }

        private boolean copyData(long requestor, long property, long target) {
            ClipboardEntry entry = (ClipboardEntry) entriesMap.get(new Long(target));

            if (entry == null) {
                return false;
            }

            LockedArray locked = memArrAccess.lockArrayShort(entry.data);
            boolean format32 = (target == xaTargets);

            try {
                return (x11.XChangeProperty(display, requestor, property, target,
                        (format32 ? 32 : 8), X11Defs.PropModeReplace, locked.getAddress(),
                        (format32 ? entry.data.length / POINTER_SIZE: entry.data.length))
                        != X11Defs.BadAlloc);
            } finally {
                locked.release();
            }
        }

        private X11.XEvent createNotifyEvent(
                X11.XSelectionRequestEvent request, long property) {
            X11.XEvent retEvent = x11.createXEvent(false);
            X11.XSelectionEvent se = retEvent.get_xselection();

            se.set_type(X11Defs.SelectionNotify);
            se.set_selection(request.get_selection());
            se.set_target(request.get_target());
            se.set_requestor(request.get_requestor());
            se.set_time(request.get_time());
            se.set_property(property);

            return retEvent;
        }

    }

    private class PropertyParams {

        long typePtr = memAccess.malloc(POINTER_SIZE);
        long formatPtr = memAccess.malloc(INT_SIZE);
        long bytesLeftPtr = memAccess.malloc(POINTER_SIZE);
        long itemsNumPtr = memAccess.malloc(POINTER_SIZE);
        long dataPtrPtr = memAccess.malloc(POINTER_SIZE);

        void free() {
            memAccess.free(typePtr);
            memAccess.free(formatPtr);
            memAccess.free(itemsNumPtr);
            memAccess.free(bytesLeftPtr);
            memAccess.free(dataPtrPtr);
        }

    }

}
