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

import java.awt.datatransfer.Transferable;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.Reader;

import org.apache.harmony.awt.ByteVector;
import org.apache.harmony.awt.datatransfer.DTK;
import org.apache.harmony.awt.datatransfer.TextTranslator;
import org.apache.harmony.awt.datatransfer.TranslationPoint;
import org.apache.harmony.awt.datatransfer.UnsupportedTranslationPoint;
import org.apache.harmony.awt.nativebridge.windows.WindowsDefs;

final class WinTextTranslator extends TextTranslator {

    public WinTextTranslator() {
        super("\r\n", new Integer(1));
    }

    public byte[] java2Native(Transferable contents, TranslationPoint point) throws Exception {
        Reader reader = point.flavor.getReaderForText(contents);
        StringBuffer string = TextUtils.reader2Buffer(reader);
        ByteVector wideChar = TextUtils.buffer2WideChar(string, getEOLBinary()).append((char) 0);
        byte[] ansiText = TextUtils.wideChar2CodePage(wideChar.getAll(), WindowsDefs.CP_ACP);
        ByteVector buf = new ByteVector();

        buf.append(ansiText).append(getTerminatorsBinary());

        return buf.getAll();
    }

    public Object native2Java(byte[] contents, TranslationPoint point) throws Exception {
        byte wideChar[] = TextUtils.codePage2WideChar(contents, WindowsDefs.CP_ACP);
        StringBuffer buf = TextUtils.wideChar2Buffer(wideChar, getEOLBinary());
        String string = buf.toString();

        if (point.flavor.getRepresentationClass().equals(String.class)) {
            return string;
        } else {
            String flavorCharset = point.flavor.getParameter("charset");

            if ((flavorCharset == null) || (flavorCharset.length() == 0)) {
                flavorCharset = DTK.textDescriptor.getDefaultCharset();
            }

            byte[] binaryStr = string.getBytes(flavorCharset);

            if (point.flavor.getRepresentationClass().equals(InputStream.class)) {
                return new ByteArrayInputStream(binaryStr);
            } else {    // (point.flavor.getRepresentationClass().equals(byte[].class))
                return binaryStr;
            }
        }
    }

    public void assigned2Point(TranslationPoint point) throws UnsupportedTranslationPoint {
        if (!point.flavor.isFlavorTextType()
                || !point.natife.equals(WinFlavorMap.TEXT)
                || !(point.flavor.getRepresentationClass().equals(String.class) ||
                        point.flavor.getRepresentationClass().equals(InputStream.class) ||
                        point.flavor.getRepresentationClass().equals(byte[].class)))
        {
            throw new UnsupportedTranslationPoint(point.toString());
        }

    }

}
