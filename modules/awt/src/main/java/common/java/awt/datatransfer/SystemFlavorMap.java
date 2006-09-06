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
 * @author Michael Danilov, Pavel Dolgov
 * @version $Revision$
 */
package java.awt.datatransfer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.harmony.awt.datatransfer.DTK;


public final class SystemFlavorMap implements FlavorMap, FlavorTable {

    private static final String SERIALIZED_PREFIX = 
        "org.apache.harmony.awt.datatransfer:";

    private final HashMap flavor2Native = new HashMap();
    private final HashMap native2Flavor = new HashMap();

    private final DTK dtk;

    public static boolean isJavaMIMEType(String str) {
        return ((str != null) && str.startsWith(SERIALIZED_PREFIX));
    }

    public static String encodeJavaMIMEType(String mimeType) {
        if (mimeType == null) {
            return null;
        } else {
            return (SERIALIZED_PREFIX + mimeType);
        }
    }

    public static String decodeJavaMIMEType(String nat) {
        if (isJavaMIMEType(nat)) {
            return nat.substring(SERIALIZED_PREFIX.length());
        } else {
            return null;
        }
    }

    public static String encodeDataFlavor(DataFlavor flav) {
        if (flav == null) {
            return null;
        } else {
            return (SERIALIZED_PREFIX + flav.getMimeType());
        }
    }

    public static DataFlavor decodeDataFlavor(String nat)
            throws ClassNotFoundException {
        if (isJavaMIMEType(nat)) {
            return new DataFlavor(nat.substring(SERIALIZED_PREFIX.length()));
        } else {
            return null;
        }
    }

    public static FlavorMap getDefaultFlavorMap() {
        DTK dtk = DTK.getDTK();

        synchronized (dtk) {
            SystemFlavorMap flavorMap = dtk.getSystemFlavorMap();

            if (flavorMap == null) {
                flavorMap = new SystemFlavorMap(dtk);
                dtk.setSystemFlavorMap(flavorMap);
            }

            return flavorMap;
        }
    }

    private SystemFlavorMap(DTK dtk) {
        this.dtk = dtk;
        dtk.initSystemFlavorMap(this);
    }

    public synchronized List getFlavorsForNative(String nat) {
        if (nat == null) {
            return new ArrayList(flavor2Native.keySet());
        }

        List list = (List) native2Flavor.get(nat);
        if ((list == null || list.isEmpty()) && isJavaMIMEType(nat)) {
            String decodedNat = decodeJavaMIMEType(nat);
            try {
                DataFlavor flavor = new DataFlavor(decodedNat);
                addMapping(nat, flavor);
                list = (List) native2Flavor.get(nat);
            } catch (ClassNotFoundException e) {}
        }
        return (list != null) ? new ArrayList(list) : new ArrayList();
    }

    public synchronized List getNativesForFlavor(DataFlavor flav) {
        if (flav == null) {
            return new ArrayList(native2Flavor.keySet());
        }
        
        List list = (List) flavor2Native.get(flav);
        if ((list == null || list.isEmpty()) 
                && flav.isFlavorSerializedObjectType()) {
            String nat = encodeDataFlavor(flav);
            addMapping(nat, flav);
            list = (List) flavor2Native.get(flav);
        }
        return (list != null) ? new ArrayList(list) : new ArrayList();
    }

    public synchronized Map getFlavorsForNatives(String[] natives) {
        HashMap map = new HashMap();
        Iterator it = (natives != null) ? 
                Arrays.asList(natives).iterator() : 
                    native2Flavor.keySet().iterator();
        while (it.hasNext()) {
            String nat = (String)it.next();
            List list = getFlavorsForNative(nat);
            if (list.size() > 0) {
                map.put(nat, list.get(0));
            }
        }
        return map;
    }

    public synchronized Map getNativesForFlavors(DataFlavor[] flavors) {
        HashMap map = new HashMap();
        Iterator it = (flavors != null) ? 
                Arrays.asList(flavors).iterator() : 
                    flavor2Native.keySet().iterator();
        while (it.hasNext()) {
            DataFlavor flavor = (DataFlavor)it.next();
            List list = getNativesForFlavor(flavor);
            if (list.size() > 0) {
                map.put(flavor, list.get(0));
            }
        }
        return map;
    }

    public synchronized void setNativesForFlavor(
            DataFlavor flav, String[] natives) {
        LinkedList list = new LinkedList();

        for (int i = 0; i < natives.length; i++) {
            String nat = natives[i];

            if (!list.contains(nat)) {
                list.add(nat);
            }
        }

        if (!list.isEmpty()) {
            flavor2Native.put(flav, list);
        } else {
            flavor2Native.remove(flav);
        }
    }

    public synchronized void setFlavorsForNative(
            String nat, DataFlavor[] flavors) {
        LinkedList list = new LinkedList();

        for (int i = 0; i < flavors.length; i++) {
            DataFlavor flav = flavors[i];

            if (!list.contains(flav)) {
                list.add(flav);
            }
        }

        if (!list.isEmpty()) {
            native2Flavor.put(nat, list);
        } else {
            native2Flavor.remove(nat);
        }
    }

    public synchronized void addUnencodedNativeForFlavor(
            DataFlavor flav, String nat) {
        List natives = (List) flavor2Native.get(flav);

        if (natives == null) {
            natives = new LinkedList();
            flavor2Native.put(flav, natives);
        }
        if (!natives.contains(nat)) {
            natives.add(nat);
        }
    }

    public synchronized void addFlavorForUnencodedNative(
            String nat, DataFlavor flav) {
        List flavors = (List) native2Flavor.get(nat);

        if (flavors == null) {
            flavors = new LinkedList();
            native2Flavor.put(nat, flavors);
        }
        if (!flavors.contains(flav)) {
            flavors.add(flav);
        }
    }

    private void addMapping(String nat, DataFlavor flav) {
        addUnencodedNativeForFlavor(flav, nat);
        addFlavorForUnencodedNative(nat, flav);
    }
}
