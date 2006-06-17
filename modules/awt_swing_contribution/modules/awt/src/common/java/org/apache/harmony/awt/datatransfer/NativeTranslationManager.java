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
import java.util.*;

/**
 * Manager of translations between Java and native data formats.
 */
public abstract class NativeTranslationManager {

    private static final ByteTranslator byteTranslator = new ByteTranslator();
    private static final SerializableTranslator serializableTranslator = new SerializableTranslator();
    private static final DummyTranslator dummyTranslator = new DummyTranslator();

    // Native (String) <=> Format (Long)
    private final HashMap native2Format = new HashMap();
    private final HashMap format2Native = new HashMap();

    // Maps translation points to their translators
    private final HashMap knownTransPoints;

    private SystemFlavorMap flavorMap = null;

    /**
     * Returns clipboard translator for byte sequences.
     */
    protected static ByteTranslator getByteTranslator() {
        return byteTranslator;
    }

    /**
     * Returns dummy clipboard translator for incompatible Java<=>native pairs of formats.
     */
    protected static DummyTranslator getDummyTranslator() {
        return dummyTranslator;
    }

    /**
     * Returns clipboard translator for Java serializable objects.
     */
    protected static SerializableTranslator getSerializableTranslator() {
        return serializableTranslator;
    }

    /**
     * Adds system params for translation point from system flavor map.
     *
     * @param point - translation point
     * @param eol - end of line string
     * @param terminators - termination sequence
     */
    public void addSystemParams(TranslationPoint point, String eol, Integer terminators) {
        ClipboardTranslator translator = (ClipboardTranslator) knownTransPoints.get(point);

        if (translator instanceof TextTranslator) {
            TextTranslator textTranslator = (TextTranslator) translator;

            textTranslator.setEOL(eol);
            textTranslator.setTerminators(terminators);
        }
    }

    /**
     * Translate contents of Java clipboard to list of native formats.
     *
     * @param contents - contents of Java clipboard.
     * @return translated data in form of list with ClipboardEntry instances.
     */
    public LinkedList translateJava2Native(Transferable contents) {
        validateFlavorMap();
        HashSet translatedNatives = new HashSet();
        DataFlavor flavors[] = contents.getTransferDataFlavors();
        LinkedList results = new LinkedList();

        for (int i = 0; i < flavors.length; i++) {
            DataFlavor flavor = flavors[i];
            List natives = flavorMap.getNativesForFlavor(flavor);

            for (Iterator j = natives.iterator(); j.hasNext();) {
                String nat = (String) j.next();

                if (!translatedNatives.contains(nat)) {
                    translatedNatives.add(nat);
                    try {
                        TranslationPoint point = new TranslationPoint(flavor, nat);
                        long format = getFormat4Native(point.natife);
                        ClipboardTranslator translator = deriveTranslator(point);
                        byte[] data = translator.java2Native(contents, point);

                        if ((data != null) && (data.length != 0)) {
                            results.add(new ClipboardEntry(format, data));
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        throw new RuntimeException("Data translation failed.");
                    }
                }
            }
        }

        return results;
    }

    /**
     * Create stub of native clipboard contents.
     * Used for lazy Java clipboard implementation.
     *
     * @param formats - list of formats in native clipboard.
     * @return transferable stub
     */
    public SystemTransferable createTransferableStub(LinkedList formats) {
        SystemTransferable transferable = new SystemTransferable(this);

        if (formats == null) {
            transferable.allFlavorsAdded();

            return transferable;
        }

        validateFlavorMap();
        for (Iterator i = formats.iterator(); i.hasNext();) {
            long format = ((Long) i.next()).longValue();
            String nat = getNative4Format(format);
            List flavs = flavorMap.getFlavorsForNative(nat);

            for (Iterator j = flavs.iterator(); j.hasNext();) {
                DataFlavor flavor = (DataFlavor) j.next();

                if (!transferable.flavors.contains(flavor)) {
                    transferable.addFlavor(flavor, nat, format);
                }
            }
        }
        transferable.allFlavorsAdded();

        return transferable;
    }

    /**
     * Translate native data to Java format.
     *
     * @param flavor - Java format.
     * @param info - information about native peer of Java format
     * @return translated data.
     */
    public Object translateNative2Java(DataFlavor flavor, FlavorInfo info) {
        try {
            TranslationPoint point = new TranslationPoint(flavor, info.natife);
            ClipboardTranslator translator = deriveTranslator(point);

            return translator.native2Java(info.rawData, point);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Data translation failed.");
        }

    }

    /**
     * Creates new native translation manager object.
     */
    protected NativeTranslationManager() {
        knownTransPoints = new HashMap();
    }

    /**
     * Adds new native format.
     */
    protected void addNativeFormat(String nat, long format) {
        native2Format.put(nat, new Long(format));
        format2Native.put(new Long(format), nat);
    }

    /**
     * Gets string representation of given native format.
     */
    protected String getNative4Format(long format) {
        String nat = (String) format2Native.get(new Long(format));

        if (nat != null) {
            return nat;
        } else {
            return getRegisteredNative(format);
        }
    }

    /**
     * Gets native format for given string representation.
     */
    protected long getFormat4Native(String nat) {
        Long formatWrapper = (Long) native2Format.get(nat);

        if (formatWrapper != null) {
            return formatWrapper.longValue();
        } else {
            return registerNewFormat(nat);
        }
    }

    /**
     * Adds new translation point.
     *
     * @param point - new translation point.
     * @param translator - clipboard translation for new point.
     * @throws UnsupportedTranslationPoint is thrown when translator doesn't support point.
     */
    protected void addTranslationPoint(TranslationPoint point, ClipboardTranslator translator)
            throws UnsupportedTranslationPoint
    {
        translator.assigned2Point(point);
        knownTransPoints.put(point, translator);
    }

    /**
     * Finds clipboard translator for given point.
     */
    protected ClipboardTranslator deriveTranslator(TranslationPoint point) {
        ClipboardTranslator translator = (ClipboardTranslator) knownTransPoints.get(point);

        if (translator == null) {
            translator = deriveTranslatorIfText(point);

            if (translator == null) {
                if (ByteTranslator.isByteCodeFlavor(point.flavor)) {
                    translator = byteTranslator;
                } else if (point.flavor.isFlavorSerializedObjectType()) {
                    translator = serializableTranslator;
                } else {
                    translator = dummyTranslator;
                }
            }
            try {
                addTranslationPoint(point, translator);
            } catch (UnsupportedTranslationPoint e) {
                try {
                    translator = dummyTranslator;
                    addTranslationPoint(point, dummyTranslator);
                } catch (UnsupportedTranslationPoint e1) {
                    // Never get here
                }
            }
        }

        return translator;
    }

    /**
     * Finds clipboard translator it point describes text format.
     */
    protected abstract ClipboardTranslator deriveTranslatorIfText(TranslationPoint point);

    /**
     * Gets representation string for native format.
     */
    protected abstract String getRegisteredNative(long format);

    /**
     * Gets native format for representation string.
     */
    protected abstract long registerNewFormat(String nat);

    private void validateFlavorMap() {
        if (flavorMap == null) {
            flavorMap = (SystemFlavorMap) SystemFlavorMap.getDefaultFlavorMap();
        }
    }

}
