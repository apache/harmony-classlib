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
import java.io.*;

import org.apache.harmony.awt.ByteVector;


/**
 * Clipboard translator for byte sequences.
 */
public final class ByteTranslator implements ClipboardTranslator {

    /**
     * Whether Java format is binary, i.e. can be processed with this translator.
     */
    public static boolean isByteCodeFlavor(DataFlavor flavor) {
        return (flavor.getRepresentationClass().equals(InputStream.class)
                || flavor.getRepresentationClass().equals(byte[].class));
    }

    public byte[] java2Native(Transferable contents, TranslationPoint point) throws Exception {
        byte[] res;

        if (point.flavor.getRepresentationClass().equals(InputStream.class)) {
            InputStream stream = (InputStream) contents.getTransferData(point.flavor);
            ByteVector buf = new ByteVector();

            stream.reset();
            for (int i = stream.read(); i != -1; i = stream.read()) {
                buf.append((byte) i);
            }
            res = buf.getAll();
        } else if (point.flavor.getRepresentationClass().equals(byte[].class)) {
            res = (byte[]) contents.getTransferData(point.flavor);
        } else {
            return null;
        }

        return res;
    }

    public Object native2Java(byte[] contents, TranslationPoint point) throws Exception {
        if (point.flavor.getRepresentationClass().equals(InputStream.class)) {
            return new ByteArrayInputStream(contents);
        } else if (point.flavor.getRepresentationClass().equals(byte[].class)) {
            return contents;
        } else {
            return null;
        }
    }

    public void assigned2Point(TranslationPoint point) throws UnsupportedTranslationPoint {
        if (!isByteCodeFlavor(point.flavor)) {
            throw new UnsupportedTranslationPoint(point.toString());
        }
    }

}
