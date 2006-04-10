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

package org.apache.harmony.tests.java.util.zip;

import java.io.UnsupportedEncodingException;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;

import junit.framework.TestCase;

public class DeflaterTest extends TestCase {

    /**
     * @throws DataFormatException
     * @throws UnsupportedEncodingException
     * @tests java.util.zip.Deflater#getBytesRead()
     */
    public void test_getBytesRead() throws DataFormatException,
            UnsupportedEncodingException {
        // Regression test for HARMONY-158
        Deflater def = new Deflater();
        assertEquals(0, def.getTotalIn());
        assertEquals(0, def.getTotalOut());
        assertEquals(0, def.getBytesRead());
        // Encode a String into bytes
        String inputString = "blahblahblah??";
        byte[] input = inputString.getBytes("UTF-8");

        // Compress the bytes
        byte[] output = new byte[100];
        def.setInput(input);
        def.finish();
        int compressedDataLength = def.deflate(output);
        assertEquals(14, def.getTotalIn());
        assertEquals(compressedDataLength, def.getTotalOut());
        assertEquals(14, def.getBytesRead());
    }

    /**
     * @throws DataFormatException
     * @throws UnsupportedEncodingException
     * @tests java.util.zip.Deflater#getBytesRead()
     */
    public void test_getBytesWritten() throws DataFormatException,
            UnsupportedEncodingException {
        // Regression test for HARMONY-158
        Deflater def = new Deflater();
        assertEquals(0, def.getTotalIn());
        assertEquals(0, def.getTotalOut());
        assertEquals(0, def.getBytesWritten());
        // Encode a String into bytes
        String inputString = "blahblahblah??";
        byte[] input = inputString.getBytes("UTF-8");

        // Compress the bytes
        byte[] output = new byte[100];
        def.setInput(input);
        def.finish();
        int compressedDataLength = def.deflate(output);
        assertEquals(14, def.getTotalIn());
        assertEquals(compressedDataLength, def.getTotalOut());
        assertEquals(compressedDataLength, def.getBytesWritten());
    }
}
