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

import java.awt.datatransfer.DataFlavor;

import org.apache.harmony.awt.ContextStorage;
import org.apache.harmony.awt.datatransfer.*;
import org.apache.harmony.awt.wtk.linux.LinuxWindowFactory;


public final class LinuxTranslationManager extends NativeTranslationManager {

    private static final int MAX_FORMAT_NAME_LENGTH = 200;
    private static final FileTranslator fileTranslator = new FileTranslator();
    private static final PureTextTranslator utf8Translator =
            new PureTextTranslator(LinuxFlavorMap.UTF8_STRING, "utf-8");
    private static final PureTextTranslator stringTranslator =
            new PureTextTranslator(LinuxFlavorMap.STRING, "iso8859-1");

    private LinuxWindowFactory factory;

    public LinuxTranslationManager() {
        super();

        factory = (LinuxWindowFactory) ContextStorage.getNativeEventQueue();
        try {
            TextTranslator.TextFlavorsIterator iterator =
                    TextTranslator.getFlavorsIterator();

            for (DataFlavor i = iterator.getMainFlavor(); i != null; i = iterator.getNextFlavor()) {
                addTranslationPoint(
                        new TranslationPoint(i, LinuxFlavorMap.UTF8_STRING), utf8Translator);
                addTranslationPoint(
                        new TranslationPoint(i, LinuxFlavorMap.STRING), stringTranslator);
            }

            addTranslationPoint(LinuxFlavorMap.utfAndUTFStream, utf8Translator);
            addTranslationPoint(LinuxFlavorMap.stringAndISOStream, stringTranslator);
            addTranslationPoint(LinuxFlavorMap.fileAndFileList, fileTranslator);
        } catch (UnsupportedTranslationPoint e) {
            throw new RuntimeException("Can't register standart clipboard translator:" + e);
        }
    }

    protected String getRegisteredNative(long format) {
        return factory.getAtomName(format);
    }

    protected long registerNewFormat(String nat) {
        return factory.internAtom(nat);
    }

    protected ClipboardTranslator deriveTranslatorIfText(TranslationPoint point) {
        if (point.flavor.isFlavorTextType()) {
            if (point.natife.equals(LinuxFlavorMap.UTF8_STRING)) {
                return utf8Translator;
            } else if (point.natife.equals(LinuxFlavorMap.STRING)) {
                return stringTranslator;
            }
        }

        return null;
    }

}
