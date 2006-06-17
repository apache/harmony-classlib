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

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;

import org.apache.harmony.awt.ByteVector;
import org.apache.harmony.awt.nativebridge.windows.Win32;
import org.apache.harmony.misc.accessors.AccessorFactory;
import org.apache.harmony.misc.accessors.ArrayAccessor;
import org.apache.harmony.misc.accessors.LockedArray;

final class TextUtils {

    private static final Win32 win32 = Win32.getInstance();
    private static final ArrayAccessor memArrAccess = AccessorFactory.getArrayAccessor();

    static StringBuffer reader2Buffer(Reader reader) throws IOException {
        StringBuffer buf = new StringBuffer();

        for (int i = reader.read(); i != -1; i = reader.read()) {
            buf.append((char) i);
        }

        return buf;
    }

    /*
     * Returns wide char string w/o terminators
     */
    static ByteVector buffer2WideChar(StringBuffer string, byte[] eol) {
        ByteVector buf = new ByteVector();

        for (int i = 0; i < string.length(); i++) {
            char c = string.charAt(i);

            if (c == '\n') {
                buf.append(eol);
            } else {
                buf.append(c);
            }
        }

        return buf;
    }

    static StringBuffer wideChar2Buffer(byte[] wideChar, byte[] eol) {
        StringBuffer buf = new StringBuffer();
        int eolLength = eol.length;
        int wideCharLength = (wideChar.length / 2) * 2;

        for (int i = 0; i < wideCharLength;) {
            boolean isEOL = true;
            char c;

            for (int j = 0; j < eolLength; j++) {
                if (((i + j) >= wideChar.length) || (eol[j] != wideChar[i + j])) {
                    isEOL = false;
                    break;
                }
            }

            if (isEOL) {
                c = '\n';
                i += eolLength;
            } else {
                c = (char) (wideChar[i] + (((int) wideChar[i + 1]) << 8));
                i += 2;
            }

            if (c != 0) {
                buf.append(c);
            } else {
                break;
            }
        }

        return buf;
    }

    static byte[] wideChar2CodePage(byte[] wideChar, int codePage) {
        LockedArray wideCharLocked = memArrAccess.lockArrayShort(wideChar);
        int codedLength = win32.WideCharToMultiByte(codePage, 0,
                wideCharLocked.getAddress(), -1, 0, 0, 0, 0);
        int nullPos = codedLength - 1;
        byte[] multiByte = new byte[nullPos];
        LockedArray multiByteLocked = memArrAccess.lockArrayShort(multiByte);

        win32.WideCharToMultiByte(codePage, 0, wideCharLocked.getAddress(), -1,
                multiByteLocked.getAddress(), nullPos, 0, 0);
        wideCharLocked.release();
        multiByteLocked.release();

        return multiByte;
    }

    static byte[] codePage2WideChar(byte[] coded, int codePage) {
        LockedArray codedLocked = memArrAccess.lockArrayShort(coded);
        int wideLength = win32.MultiByteToWideChar(codePage, 0,
                codedLocked.getAddress(), -1, 0, 0);
        byte[] wideChar = new byte[wideLength * 2];
        LockedArray wideLocked = memArrAccess.lockArrayShort(wideChar);

        win32.MultiByteToWideChar(codePage, 0,
                codedLocked.getAddress(), -1, wideLocked.getAddress(), wideLength);
        wideLocked.release();
        codedLocked.release();

        return wideChar;
    }

    static String codePage2String(byte[] coded, int codePage) {
        LockedArray codedLocked = memArrAccess.lockArrayShort(coded);
        int wideLength = win32.MultiByteToWideChar(codePage, 0,
                codedLocked.getAddress(), -1, 0, 0);
        char[] wideChar = new char[wideLength];
        LockedArray wideLocked = memArrAccess.lockArrayShort(wideChar);

        win32.MultiByteToWideChar(codePage, 0,
                codedLocked.getAddress(), -1, wideLocked.getAddress(), wideLength);
        wideLocked.release();
        codedLocked.release();

        return new StringBuffer().append(wideChar).toString();
    }

    static class TextInputStream extends InputStream {

        private byte[] text;
        private int cnt;

        TextInputStream(StringBuffer string) {
            int strLength = string.length();
            cnt = 0;
            text = new byte[strLength * 2];

            for (int i = 0, j = 0; i < strLength; i++) {
                char c = string.charAt(i);

                text[j++] = (byte) c;
                text[j++] = (byte) (c >> 8);
            }
        }

        public int read() throws IOException {
            if (cnt < text.length) {
                return text[cnt++];
            } else {
                return -1;
            }
        }

    }

}
