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
package java.awt.datatransfer;

import java.awt.*;
import java.io.IOException;
import java.util.*;

import org.apache.harmony.awt.*;
import org.apache.harmony.awt.wtk.Synchronizer;


public class Clipboard {

    protected ClipboardOwner owner;

    protected Transferable contents;

    private final String name;
    private final FlavorEventProcessor processor;
    private final ListenerList listeners;
    private HashSet flavors;
    private Synchronizer awtSynchronizer;

    public Clipboard(String name) {
        this.name = name;
        listeners = new ListenerList();
        processor = new FlavorEventProcessor();
        flavors = new HashSet();
        awtSynchronizer = ContextStorage.getSynchronizer();
    }

    public String getName() {
        return name;
    }

    public Transferable getContents(Object requestor) {
        awtSynchronizer.lock();
        try {
            return contents;
        } finally {
            awtSynchronizer.unlock();
        }
    }

    public void setContents(Transferable contents, ClipboardOwner owner) {
        awtSynchronizer.lock();
        try {
            boolean ownershipLost = (this.owner != owner);
            boolean flavorsChanged;
            HashSet newFlavorsSet = new HashSet();
            DataFlavor[] newFlavorsArray = contents.getTransferDataFlavors();

            for (int i = 0; i < newFlavorsArray.length; i ++) {
                newFlavorsSet.add(newFlavorsArray[i]);
            }
            flavorsChanged = !flavors.equals(newFlavorsSet);

            if (flavorsChanged || ownershipLost) {
                processor.setProcessingParams(ownershipLost ? this.owner : null,
                        this.contents, flavorsChanged);
                EventQueue.invokeLater(processor);
            }

            this.contents = contents;
            this.owner = owner;
            flavors = newFlavorsSet;
        } finally {
            awtSynchronizer.unlock();
        }
    }

    public DataFlavor[] getAvailableDataFlavors() {
        awtSynchronizer.lock();
        try {
            return (contents == null) ?
                    new DataFlavor[0] : contents.getTransferDataFlavors();
        } finally {
            awtSynchronizer.unlock();
        }
    }

    public boolean isDataFlavorAvailable(DataFlavor flavor) {
        awtSynchronizer.lock();
        try {
            return (contents == null) ?
                    false : contents.isDataFlavorSupported(flavor);
        } finally {
            awtSynchronizer.unlock();
        }
    }

    public Object getData(DataFlavor flavor) throws
            UnsupportedFlavorException, IOException
    {
        awtSynchronizer.lock();
        try {
            if (contents == null) {
                throw new UnsupportedFlavorException(flavor);
            } else {
                return contents.getTransferData(flavor);
            }
        } finally {
            awtSynchronizer.unlock();
        }
    }

    public void addFlavorListener(FlavorListener listener) {
        listeners.addUserListener(listener);
    }

    public void removeFlavorListener(FlavorListener listener) {
        listeners.removeUserListener(listener);
    }

    public FlavorListener[] getFlavorListeners() {
        return (FlavorListener[]) listeners.getUserListeners(new FlavorListener[0]);
    }

    private void processFlavorEvent(FlavorEvent e) {
        for (Iterator i = listeners.getUserIterator(); i.hasNext();) {
            ((FlavorListener) i.next()).flavorsChanged(e);
        }
    }

    private class FlavorEventProcessor implements Runnable {

        private Transferable oldContents;
        private ClipboardOwner oldOwner;
        private boolean flavorsChanged;

        void setProcessingParams(ClipboardOwner oldOwner,
                Transferable oldContents, boolean flavorsChanged)
        {
            this.oldContents = oldContents;
            this.oldOwner = oldOwner;
            this.flavorsChanged = flavorsChanged;
        }

        public void run() {
            if (oldOwner != null) {
                oldOwner.lostOwnership(Clipboard.this, oldContents);
            }
            if (flavorsChanged) {
                processFlavorEvent(new FlavorEvent(Clipboard.this));
            }
        }

    }

}
