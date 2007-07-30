/*
 *  Licensed to the Apache Software Foundation (ASF) under one or more
 *  contributor license agreements.  See the NOTICE file distributed with
 *  this work for additional information regarding copyright ownership.
 *  The ASF licenses this file to You under the Apache License, Version 2.0
 *  (the "License"); you may not use this file except in compliance with
 *  the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package org.apache.harmony.nio.tests.java.nio;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

import junit.framework.TestCase;

public class MappedByteBufferTest extends TestCase {

    /**
     * A regression test for failing to correctly set capacity of underlying
     * wrapped buffer from a mapped byte buffer.
     */
    public void testasIntBuffer() throws IOException {
        // Create temp file with 26 bytes and 5 ints
        File tmpFile = File.createTempFile("harmony", "test");  //$NON-NLS-1$//$NON-NLS-2$
        tmpFile.deleteOnExit();
        FileOutputStream fileOutputStream = new FileOutputStream(tmpFile);
        FileChannel fileChannel = fileOutputStream.getChannel();
        ByteBuffer byteBuffer = ByteBuffer.allocateDirect(26 + 20);
        for (int i = 0; i < 26; i++) {
            byteBuffer.put((byte) ('A' + i));
        }
        for (int i = 0; i < 5; i++) {
            byteBuffer.putInt(i + 1);
        }
        byteBuffer.rewind();
        fileChannel.write(byteBuffer);
        fileChannel.close();
        fileOutputStream.close();

        // Map file
        FileInputStream fis = new FileInputStream(tmpFile);
        FileChannel fc = fis.getChannel();
        MappedByteBuffer mmb = fc.map(FileChannel.MapMode.READ_ONLY, 0, fc
                .size());
        int len = mmb.capacity();
        assertEquals("Got wrong number of bytes", 46, len); //$NON-NLS-1$

        // Read in our 26 bytes
        for (int i = 0; i < 26; i++) {
            byte b = mmb.get();
            assertEquals("Got wrong byte value", (byte) 'A' + i, b); //$NON-NLS-1$
        }

        // Now convert to an IntBuffer to read our ints
        IntBuffer ibuffer = mmb.asIntBuffer();
        for (int i = 0; i < 5; i++) {
            int val = ibuffer.get();
            assertEquals("Got wrong int value", i + 1, val); //$NON-NLS-1$
        }
    }
}
