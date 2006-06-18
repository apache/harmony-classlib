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
package org.apache.harmony.awt.datatransfer;

import java.awt.datatransfer.*;
import java.io.IOException;
import java.util.*;

/**
 * Wrapper for native clipboard contents.
 * Used when clipboard is modified outside VM, i.e. AWT is not owner of clipboard.
 * It supports lazy clipboard operations.
 */
public class SystemTransferable implements Transferable {

    private static final DataFlavor[] emptyArray = new DataFlavor[0];

    public final NativeTranslationManager translationManager;
    public final LinkedList flavors;
    public final HashMap infoMap;

    private final FlavorsComparator flavorsComparator = new FlavorsComparator();

    private DataFlavor[] availableFlavors;
    private int textFlavorsCnt;

    /**
     * Creates new system transferable object.
     */
    public SystemTransferable(NativeTranslationManager manager) {
        translationManager = manager;
        flavors = new LinkedList();
        infoMap = new HashMap();

        availableFlavors = null;
        textFlavorsCnt = 0;
    }

    public Object getTransferData(DataFlavor flavor)
            throws UnsupportedFlavorException, IOException
    {
        FlavorInfo info = (FlavorInfo) infoMap.get(flavor);

        if (info == null) {
            throw new UnsupportedFlavorException(flavor);
        }
        if (info.data == null) {
            if (info.rawData == null) {
                throw new IOException("Can't read data.");
            } else if (info.translated) {
                throw new RuntimeException("Data translation failed.");
            } else {
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

    public boolean isDataFlavorSupported(DataFlavor flavor) {
        return flavors.contains(flavor);
    }

    public DataFlavor[] getTransferDataFlavors() {
        return availableFlavors;
    }

    /**
     * Adds information about one more data format in native clipboard.
     * Used for initialization of system transferable.
     */
    public void addFlavor(DataFlavor flavor, String nat, long format) {
        flavors.add(flavor);
        infoMap.put(flavor, new FlavorInfo(nat, format));

        if (flavor.isFlavorTextType()) {
            textFlavorsCnt++;
        }
    }

    public void allFlavorsAdded() {
        availableFlavors = (DataFlavor[]) flavors.toArray(emptyArray);

        if (textFlavorsCnt > 1) {
            Arrays.sort(availableFlavors, flavorsComparator);
        }
    }

}
