/* Copyright 2006 The Apache Software Foundation or its licensors, as applicable
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.harmony.tests.java.io;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

import junit.framework.TestCase;

public class InputStreamReaderTest extends TestCase {
    public void testGetEncoding_StreamClosed() throws IOException {
        InputStreamReader in = null;
        byte b[] = new byte[5];
        in = new InputStreamReader(new ByteArrayInputStream(b), "UTF-16BE");
        in.close();
        String result = in.getEncoding();
        assertEquals(null, result);
    }

    /**
     * @tests java.io.InputStreamReader#read()
     */
    public void testRead() throws IOException {
        // Regression for HARMONY-166
        InputStream in;
        InputStreamReader reader;

        in = new LimitedByteArrayInputStream(0);
        reader = new InputStreamReader(in, "UTF-16BE");
        assertEquals("Incorrect byte UTF-16BE", '\u6172', reader.read());

        in = new LimitedByteArrayInputStream(0);
        reader = new InputStreamReader(in, "UTF-16LE");
        assertEquals("Incorrect byte UTF-16BE", '\u7261', reader.read());

        in = new LimitedByteArrayInputStream(1);
        reader = new InputStreamReader(in, "UTF-16");
        assertEquals("Incorrect byte UTF-16BE", '\u7261', reader.read());

        in = new LimitedByteArrayInputStream(2);
        reader = new InputStreamReader(in, "ISO2022JP");
        assertEquals("Incorrect byte ISO2022JP 1", '\u4e5d', reader.read());
        assertEquals("Incorrect byte ISO2022JP 2", '\u7b2c', reader.read());
    }

    public void testGetEncoding_NotHistorical() {
        InputStreamReader in = null;
        try {
            in = new InputStreamReader(System.in, "UTF-16BE");
        } catch (UnsupportedEncodingException e) {
            // ok
        }
        String result = in.getEncoding();
        assertEquals(result, "UnicodeBigUnmarked");

    }

    static class LimitedByteArrayInputStream extends ByteArrayInputStream {

        // A ByteArrayInputStream that only returns a single byte per read
        byte[] bytes;

        int count;

        public LimitedByteArrayInputStream(int type) {
            super(new byte[0]);
            switch (type) {
                case 0:
                    bytes = new byte[] { 0x61, 0x72 };
                    break;
                case 1:
                    bytes = new byte[] { (byte) 0xff, (byte) 0xfe, 0x61, 0x72 };
                    break;
                case 2:
                    bytes = new byte[] { '\u001b', '$', 'B', '6', 'e', 'B',
                            'h', '\u001b', '(', 'B' };
                    break;
            }
            count = bytes.length;
        }

        public int read() {
            if (count == 0) {
                return -1;
            }
            count--;
            return bytes[bytes.length - count];
        }

        public int read(byte[] buffer, int offset, int length) {
            if (count == 0) {
                return -1;
            }
            if (length == 0) {
                return 0;
            }
            buffer[offset] = bytes[bytes.length - count];
            count--;
            return 1;
        }

        public int available() {
            return count;
        }
    }
}
