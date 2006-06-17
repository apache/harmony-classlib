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
import java.awt.datatransfer.*;

import org.apache.harmony.awt.ByteVector;
import org.apache.harmony.awt.datatransfer.*;

import org.apache.harmony.awt.nativebridge.windows.WindowsDefs;

final class HTMLTranslator extends TextTranslator {

    private static final byte HTML_HEADER[] = {
            'V','e','r','s','i','o','n',':','0','.','9','\r','\n',
            'S','t','a','r','t','H','T','M','L',':','-','1','\r','\n',
            'E','n','d','H','T','M','L',':','-','1','\r','\n',
            'S','t','a','r','t','F','r','a','g','m','e','n','t',':','1','2','0','\r','\n',
            'E','n','d','F','r','a','g','m','e','n','t',':','0','0','0','0','0','0','0','0','0','0','\r','\n',
            '<','H','T','M','L','>','\r','\n',
            '<','B','O','D','Y','>','\r','\n',
            '<','!','-','-','S','t','a','r','t','F','r','a','g','m','e','n','t','-','-','>','\r','\n',
    };
    private static final byte HTML_FOOTER[] = {
            '\r','\n','<','!','-','-','E','n','d','F','r','a','g','m','e','n','t','-','-','>','\r','\n',
            '<','/','B','O','D','Y','>','\r','\n',
            '<','/','H','T','M','L','>'
    };
    private static final int END_FRAGMENT_END = 80;

    public HTMLTranslator() {
        this("\r\n", new Integer(1));
    }

    public HTMLTranslator(String eol, Integer terminators) {
        super(eol, terminators);
    }

    public byte[] java2Native(Transferable contents, TranslationPoint point) throws Exception {
        byte[] res;

        Reader reader = point.flavor.getReaderForText(contents);
        StringBuffer string = TextUtils.reader2Buffer(reader);
        ByteVector wideChar = TextUtils.buffer2WideChar(string, getEOLBinary()).append((char) 0);
        byte coded[] = TextUtils.wideChar2CodePage(wideChar.getAll(), WindowsDefs.CP_UTF8);

        String endFragment = "" + (coded.length + HTML_HEADER.length);
        ByteVector buf = new ByteVector();

        buf.append(HTML_HEADER).append(coded).append(HTML_FOOTER).append(getTerminatorsBinary());

        res = buf.getAll();
        for (int i = END_FRAGMENT_END - endFragment.length(), j = 0;
                i < END_FRAGMENT_END; i++, j++)
        {
            res[i] = (byte) endFragment.charAt(j);
        }

        return res;
    }

    public Object native2Java(byte[] contents, TranslationPoint point) throws Exception {
        String fullText = TextUtils.codePage2String(contents, WindowsDefs.CP_UTF8);
        int firstByte = decodeIntAfterFragment(fullText, "StartFragment:");
        int lastByte = decodeIntAfterFragment(fullText, "EndFragment:");
        int length = lastByte - firstByte;
        byte[] html = new byte[length + 1];

        System.arraycopy(contents, firstByte, html, 0, length);
        html[length] = 0;
        String htmlText = TextUtils.codePage2String(html, WindowsDefs.CP_UTF8);

        Reader reader = new StringReader(htmlText.replaceAll(getEOL(), "\n"));
        StringBuffer string = TextUtils.reader2Buffer(reader);
        ByteVector wideChar = TextUtils.buffer2WideChar
                (string, new byte[] {13, 0}).append((char) 0);
        byte coded[] = TextUtils.wideChar2CodePage(wideChar.getAll(), WindowsDefs.CP_UTF8);

        return new ByteArrayInputStream(coded);
    }

    public void assigned2Point(TranslationPoint point) throws UnsupportedTranslationPoint {
        if (!point.flavor.equals(new DataFlavor("text/html;charset=utf-8", "HTML Fragment"))
                || !point.natife.equals(WinFlavorMap.HTML))
        {
            throw new UnsupportedTranslationPoint(point.toString());
        }
    }

    private int decodeIntAfterFragment(String text, String fragment) {
        int start = text.indexOf(fragment) + fragment.length();
        StringBuffer number = new StringBuffer();

        for (int i = start; (text.charAt(i) >= '0') && (text.charAt(i) <= '9'); i++) {
            number.append(text.charAt(i));
        }

        return Integer.parseInt(number.toString());
    }

}
