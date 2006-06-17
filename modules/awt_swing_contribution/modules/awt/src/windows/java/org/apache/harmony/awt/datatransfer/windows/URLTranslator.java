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

import java.io.*;
import java.net.*;

import org.apache.harmony.awt.nativebridge.windows.WindowsDefs;

import java.awt.datatransfer.*;

import org.apache.harmony.awt.ByteVector;
import org.apache.harmony.awt.datatransfer.*;

final class URLTranslator extends TextTranslator {

    public URLTranslator() {
        this("\r\n", new Integer(1));
    }

    public URLTranslator(String eol, Integer terminators) {
        super(eol, terminators);
    }

    public byte[] java2Native(Transferable contents, TranslationPoint point) throws Exception {
        Reader reader = URL.class.isAssignableFrom(point.flavor.getRepresentationClass()) ?
                new StringReader(((URL) contents.getTransferData(point.flavor)).toString()) :
                point.flavor.getReaderForText(contents);
        StringBuffer string = TextUtils.reader2Buffer(reader);

        try {
            new URL(string.toString());

            ByteVector wideChar = TextUtils.buffer2WideChar(string, getEOLBinary()).append((char) 0);
            byte[] ansiText = TextUtils.wideChar2CodePage(wideChar.getAll(), WindowsDefs.CP_ACP);
            ByteVector buf = new ByteVector();

            buf.append(ansiText).append(getTerminatorsBinary());

            return buf.getAll();
        } catch (MalformedURLException e) {
            return null;
        }
    }

    public Object native2Java(byte[] contents, TranslationPoint point) throws Exception {
        byte wideChar[] = TextUtils.codePage2WideChar(contents, WindowsDefs.CP_ACP);
        StringBuffer buf = TextUtils.wideChar2Buffer(wideChar, getEOLBinary());

        if (URL.class.isAssignableFrom(point.flavor.getRepresentationClass())) {
            return new URL(buf.toString());
        } else if (point.flavor.equals(DataFlavor.stringFlavor)) {
            return buf.toString();
        } else {
            return new TextUtils.TextInputStream(buf);
        }
    }

    public void assigned2Point(TranslationPoint point) throws UnsupportedTranslationPoint {
        if (!point.natife.equals(WinFlavorMap.UNIFORM_RESOURCE_LOCATOR)
                || (!point.flavor.isFlavorTextType()
                && point.flavor.equals(new DataFlavor("application/x-java-url;class=java.net.URL", "URL"))
                && point.flavor.equals(new DataFlavor("text/uri-list", "URI"))))
        {
            throw new UnsupportedTranslationPoint(point.toString());
        }
    }

}
