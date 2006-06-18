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

import java.awt.Toolkit;
import java.awt.datatransfer.MimeTypeProcessor.MimeType;
import java.util.*;
import java.net.URL;

import org.apache.harmony.awt.ContextStorage;
import org.apache.harmony.awt.datatransfer.*;
import org.apache.harmony.awt.wtk.Synchronizer;


public final class SystemFlavorMap implements FlavorMap, FlavorTable {

    private static final String JAVA_PREFIX = 
        "org.apache.harmony.awt.datatransfer:";

    private static final String EOLN_NAME = "eoln";
    private static final String EOLN_DEFAULT_VALUE = "\n";
    private static final String TERMINATORS_NAME = "terminators";
    private static final Integer TERMINATORS_DEFAULT_VALUE = new Integer(0);

    private static final String LINUX_PROPERTY_CLASS =
            "org.apache.harmony.awt.datatransfer.linux.LinuxFlavorMap";
    private static final String WINDOWS_PROPERTY_CLASS =
            "org.apache.harmony.awt.datatransfer.windows.WinFlavorMap";
    private static final String FLAVOR_MAP_PROPERTY = "AWT.DnD.flavorMapFileURL";

    private static final DataFlavor[] dummyFlavorArray = new DataFlavor[0];

    private final HashMap flavor2Native = new HashMap();
    private final HashMap native2Flavor = new HashMap();
    private final FlavorsComparator flavorsComparator = new FlavorsComparator();

    private final NativeTranslationManager translationManager;

    public static boolean isJavaMIMEType(String str) {
        return ((str != null) && str.startsWith(JAVA_PREFIX));
    }

    public static String encodeJavaMIMEType(String mimeType) {
        if (mimeType == null) {
            return null;
        } else {
            return (JAVA_PREFIX + mimeType);
        }
    }

    public static String decodeJavaMIMEType(String nat) {
        if (isJavaMIMEType(nat)) {
            return nat.substring(JAVA_PREFIX.length());
        } else {
            return null;
        }
    }

    public static String encodeDataFlavor(DataFlavor flav) {
        if (flav == null) {
            return null;
        } else {
            return (JAVA_PREFIX + flav.getMimeType());
        }
    }

    public static DataFlavor decodeDataFlavor(String nat) throws ClassNotFoundException {
        if (isJavaMIMEType(nat)) {
            return new DataFlavor(nat.substring(JAVA_PREFIX.length()));
        } else {
            return null;
        }
    }

    public static FlavorMap getDefaultFlavorMap() {
        DTK dtk = DTK.getContextInstance();
        Synchronizer awtSynchronizer = ContextStorage.getSynchronizer();;

        awtSynchronizer.lock();
        try {
            FlavorMap flavorMap = dtk.getFlavorMap();

            if (flavorMap == null) {
                flavorMap = new SystemFlavorMap(dtk);
                dtk.setFlavorMap(flavorMap);
            }

            return flavorMap;
        } finally {
            awtSynchronizer.unlock();
        }
    }

    private SystemFlavorMap(DTK dtk) {
        ResourceBundle properties = loadProperties();
        translationManager = dtk.getTranslationManager();

        for (Enumeration i = properties.getKeys(); i.hasMoreElements();) {
            try {
                String nat = (String) i.nextElement();
                Object flavs = properties.getObject(nat);

                if (flavs instanceof String) {
                    addFlavorNativePair(createSystemFlavor((String) flavs), nat);
                } else {
                    for (Iterator j = ((List) flavs).iterator(); j.hasNext();) {
                        addFlavorNativePair(createSystemFlavor((String) j.next()), nat);
                    }
                }
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    private void addFlavorNativePair(DataFlavor flav, String nat) {
        List flavors = (List) native2Flavor.get(nat);
        List natives = (List) flavor2Native.get(flav);
        MimeType mime = flav.getMimeInfo();

        if (flavors == null) {
            flavors = new LinkedList();
            native2Flavor.put(nat, flavors);
        }
        if (!flavors.contains(flav)) {
            flavors.add(flav);
        }
        if (natives == null) {
            natives = new LinkedList();
            flavor2Native.put(flav, natives);
        }
        if (!natives.contains(nat)) {
            natives.add(nat);
        }

        translationManager.addSystemParams(new TranslationPoint(flav, nat),
                (String) mime.getSystemParameter(EOLN_NAME),
                (Integer) mime.getSystemParameter(TERMINATORS_NAME));
    }

    private ResourceBundle loadProperties() {
        String url = Toolkit.getProperty(FLAVOR_MAP_PROPERTY, null);

        if (url == null) {
            return loadPropertiesFromClass();
        } else {
            return loadPropertiesFromFile(url);
        }
    }

    private ResourceBundle loadPropertiesFromFile(String url) {
        try {
            return new DuplicatedPropertiesResourceBundle(
                    new URL(url).openConnection().getInputStream());
        } catch (Exception e) {
            e.printStackTrace();

            return null;
        }
    }

    private ResourceBundle loadPropertiesFromClass() {
        String osName = System.getProperty("os.name").toLowerCase();
        String className = null;

        if (osName.startsWith("linux")) {
            className = LINUX_PROPERTY_CLASS;
        } else if (osName.startsWith("windows")) {
            className = WINDOWS_PROPERTY_CLASS;
        }

        return ResourceBundle.getBundle(className);
    }

    private DataFlavor createSystemFlavor(String mimeType)
            throws ClassNotFoundException
    {
        DataFlavor flavor = new DataFlavor(mimeType);
        MimeType mimeInfo = flavor.getMimeInfo();
        String eoln = mimeInfo.getParameter(EOLN_NAME);
        String terminators = mimeInfo.getParameter(TERMINATORS_NAME);
        int termValue = TERMINATORS_DEFAULT_VALUE.intValue();

        if ((eoln == null) || (eoln.length() == 0)) {
            eoln = EOLN_DEFAULT_VALUE;
        }
        mimeInfo.removeParameter(EOLN_NAME);
        mimeInfo.addSystemParameter(EOLN_NAME, eoln);

        try {
            int value = Integer.decode(terminators).intValue();

            if (value >= 0) {
                termValue = value;
            }
        } catch (Throwable t) {
        }
        mimeInfo.removeParameter(TERMINATORS_NAME);
        mimeInfo.addSystemParameter(TERMINATORS_NAME, new Integer(termValue));

        return flavor;
    }

    public synchronized List getFlavorsForNative(String nat) {
        if (nat == null) {
            return getAllKnownEntities(flavor2Native, native2Flavor);
        }

        List flavors = (List) native2Flavor.get(nat);

        if (flavors == null) {
            flavors = new LinkedList();
        }
        if (flavors.isEmpty()) {
            if (isJavaMIMEType(nat)) {
                try {
                    String decodedNat = decodeJavaMIMEType(nat);
                    DataFlavor flavor = new DataFlavor(decodedNat);
                    List natives = (List) flavor2Native.get(flavor);

                    flavors.add(flavor);
                    native2Flavor.put(nat, flavors);

                    if (natives == null) {
                        natives = new LinkedList();
                        flavor2Native.put(flavor, natives);
                    }
                    if (!natives.contains(nat)) {
                        natives.add(nat);
                    }
                } catch (ClassNotFoundException e) {
                }
            }
        } else if (DTK.textDescriptor.isTextNative(nat)) {
            flavors = getFlavorsForText(flavors);
        }

        return flavors;
    }

    private List getFlavorsForText(List flavors) {
        if (!flavors.contains(DataFlavor.stringFlavor)) {
            flavors.add(0, DataFlavor.stringFlavor);
        }
        if (!flavors.contains(DataFlavor.plainTextFlavor)) {
            flavors.add(DataFlavor.plainTextFlavor);
        }

        DataFlavor[] flavorArray = (DataFlavor[]) flavors.toArray(dummyFlavorArray);

        Arrays.sort(flavorArray, flavorsComparator);

        return Arrays.asList(flavorArray);
    }

    public synchronized List getNativesForFlavor(DataFlavor flav) {
        if (flav == null) {
            return getAllKnownEntities(native2Flavor, flavor2Native);
        }

        List natives = (List) flavor2Native.get(flav);

        if (natives == null) {
            natives = new LinkedList();
        }
        if (flav.isFlavorTextType()) {
            getNativesForText(natives);
        } else if (natives.isEmpty()) {
            String encodedNat = encodeDataFlavor(flav);
            List flavors = (List) native2Flavor.get(encodedNat);

            natives.add(encodedNat);
            flavor2Native.put(flav, natives);

            if (flavors == null) {
                flavors = new LinkedList();
                native2Flavor.put(encodedNat, flavors);
            }
            if (!flavors.contains(flav)) {
                flavors.add(flav);
            }
        }

        return natives;
    }

    private void getNativesForText(List natives) {
        String[] textNatives = DTK.textDescriptor.getTextNatives();

        for (int i = 0; i < textNatives.length; i++) {
            String nat = textNatives[i];

            if (!natives.contains(nat)) {
                natives.add(nat);
            }
        }
    }

    public synchronized Map getFlavorsForNatives(String[] natives) {
        HashMap map = new HashMap();
        List nativesList = (natives != null) ?
                Arrays.asList(natives) :
                getAllKnownEntities(native2Flavor, flavor2Native);

        for (Iterator i = nativesList.iterator(); i.hasNext();) {
            String nat = (String) i.next();
            List flavors = getFlavorsForNative(nat);

            if (!flavors.isEmpty()) {
                map.put(nat, flavors.get(0));
            }
        }

        return map;
    }

    public synchronized Map getNativesForFlavors(DataFlavor[] flavors) {
        HashMap map = new HashMap();
        List flavorsList = (flavors != null) ?
                Arrays.asList(flavors) :
                getAllKnownEntities(flavor2Native, native2Flavor);

        for (Iterator i = flavorsList.iterator(); i.hasNext();) {
            DataFlavor flav = (DataFlavor) i.next();
            List natives = getNativesForFlavor(flav);

            if (!natives.isEmpty()) {
                map.put(flav, natives.get(0));
            }
        }

        return map;
    }

    public synchronized void setNativesForFlavor(DataFlavor flav, String[] natives) {
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

    public synchronized void setFlavorsForNative(String nat, DataFlavor[] flavors) {
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

    public synchronized void addUnencodedNativeForFlavor(DataFlavor flav, String nat) {
        List natives = (List) flavor2Native.get(flav);

        if (natives == null) {
            natives = new LinkedList();
            flavor2Native.put(flav, natives);
        }
        if (!natives.contains(nat)) {
            natives.add(nat);
        }
    }

    public synchronized void addFlavorForUnencodedNative(String nat, DataFlavor flav) {
        List flavors = (List) native2Flavor.get(nat);

        if (flavors == null) {
            flavors = new LinkedList();
            native2Flavor.put(nat, flavors);
        }
        if (!flavors.contains(flav)) {
            flavors.add(flav);
        }
    }

    private List getAllKnownEntities(HashMap from, HashMap to) {
        HashSet set = new HashSet(from.keySet());
        List toFrom = new LinkedList(to.keySet());

        for (Iterator i = toFrom.iterator(); i.hasNext();) {
            set.addAll((List) to.get(i.next()));
        }

        return new LinkedList(set);
    }

}
