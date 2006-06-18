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
import java.util.Arrays;

/**
 * Pure text clipboard translator.
 * For operations with Java and native formats that are just text in some encoding.
 */
public class PureTextTranslator extends TextTranslator {

    private final String natife;
    private final String nativeCharset;

    public PureTextTranslator(String natife, String nativeCharset)
    {
        super("\n", new Integer(0));

        this.natife = natife;
        this.nativeCharset = nativeCharset;
    }

    public byte[] java2Native(Transferable contents, TranslationPoint point) throws Exception {
        Reader reader = point.flavor.getReaderForText(contents);
        StringBuffer buf = new StringBuffer();
        int terminators = getTerminators().intValue();
        String str;
        byte[] res;

        for (int i = reader.read(); i != -1; i = reader.read()) {
            buf.append((char) i);
        }
        str = switchEOLs(buf.toString(), "\n", getEOL());
        res = str.getBytes(nativeCharset);

        if (terminators > 0) {
            byte[] semiRes = res;
            int semiLength = semiRes.length;
            int length = semiLength + terminators;

            res = new byte[length];
            System.arraycopy(semiRes, 0, res, 0, semiLength);
            Arrays.fill(res, semiLength, length - 1, (byte) 0);
        }

        return res;
    }

    public Object native2Java(byte[] contents, TranslationPoint point) throws Exception {
        byte[] clearContents = filterTerminators(contents);
        String rawStr = new String(clearContents, nativeCharset);
        String cookedStr = switchEOLs(rawStr, getEOL(), "\n");

        if (point.flavor.getRepresentationClass().equals(String.class)) {
            return cookedStr;
        } else {
            String flavorCharset = point.flavor.getParameter("charset");

            if ((flavorCharset == null) || (flavorCharset.length() == 0)) {
                flavorCharset = DTK.textDescriptor.getDefaultCharset();
            }

            byte[] binaryStr = cookedStr.getBytes(flavorCharset);

            if (point.flavor.getRepresentationClass().equals(InputStream.class)) {
                return new ByteArrayInputStream(binaryStr);
            } else {    // (point.flavor.getRepresentationClass().equals(byte[].class))
                return binaryStr;
            }
        }
    }

    public void assigned2Point(TranslationPoint point) throws UnsupportedTranslationPoint {
        if (!point.flavor.isFlavorTextType()
                || !point.natife.equals(natife)
                || !(point.flavor.getRepresentationClass().equals(String.class) ||
                        point.flavor.getRepresentationClass().equals(InputStream.class) ||
                        point.flavor.getRepresentationClass().equals(byte[].class)))
        {
            throw new UnsupportedTranslationPoint(point.toString());
        }
    }

    private byte[] filterTerminators(byte[] contents) {
        int terminators = getTerminators().intValue();

        if (terminators > 0) {
            int contentsLength = contents.length;

            if (terminators < contentsLength) {
                int newLength = contentsLength - terminators;
                byte[] newContents = new byte[newLength];

                System.arraycopy(contents, 0, newContents, 0, newLength);

                return newContents;
            } else {
                return new byte[0];
            }
        } else {
            return contents;
        }
    }

    private String switchEOLs(String str, String oldEOL, String newEOL) {
        String oldStr = str;
        StringBuffer switchedStr = new StringBuffer();
        int oldEOLLength = oldEOL.length();

        do {
            int index = oldStr.indexOf(oldEOL);

            if (index == -1) {
                switchedStr.append(oldStr);
                break;
            } else {
                switchedStr.append(oldStr.substring(0, index)).append(newEOL);
                oldStr = oldStr.substring(index + oldEOLLength);
            }
        } while (true);

        return switchedStr.toString();
    }

}
