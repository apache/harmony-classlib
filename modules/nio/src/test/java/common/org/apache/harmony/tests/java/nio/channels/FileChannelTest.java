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

package org.apache.harmony.tests.java.nio.channels;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.ClosedChannelException;
import java.nio.channels.FileChannel;
import java.nio.channels.NonWritableChannelException;
import java.util.Arrays;

import junit.framework.TestCase;

public class FileChannelTest extends TestCase {

    private static final String CONTENT = "MYTESTSTRING";

    private static final int CONTENT_LENGTH = CONTENT.length();

    private static final byte[] CONTENT_AS_BYTES = CONTENT.getBytes();

    private static final int CONTENT_AS_BYTES_LENGTH = CONTENT_AS_BYTES.length;

    private FileChannel readOnlyFileChannel;

    private FileChannel writeOnlyFileChannel;

    private FileChannel readWriteFileChannel;

    private File fileOfReadOnlyFileChannel;

    private File fileOfWriteOnlyFileChannel;

    private File fileOfReadWriteFileChannel;

    // to read content from FileChannel
    private FileInputStream fis;

    protected void setUp() throws Exception {
        fileOfReadOnlyFileChannel = File.createTempFile(
                "File_of_readOnlyFileChannel", "tmp");
        fileOfReadOnlyFileChannel.deleteOnExit();
        fileOfWriteOnlyFileChannel = File.createTempFile(
                "File_of_writeOnlyFileChannel", "tmp");
        fileOfWriteOnlyFileChannel.deleteOnExit();
        fileOfReadWriteFileChannel = File.createTempFile(
                "File_of_readWriteFileChannel", "tmp");
        fileOfReadWriteFileChannel.deleteOnExit();
        fis = null;
        readOnlyFileChannel = new FileInputStream(fileOfReadOnlyFileChannel)
                .getChannel();
        writeOnlyFileChannel = new FileOutputStream(fileOfWriteOnlyFileChannel)
                .getChannel();
        readWriteFileChannel = new RandomAccessFile(fileOfReadWriteFileChannel,
                "rw").getChannel();
    }

    protected void tearDown() {
        if (null != readOnlyFileChannel) {
            try {
                readOnlyFileChannel.close();
            } catch (IOException e) {
                // do nothing
            }
        }
        if (null != writeOnlyFileChannel) {
            try {
                writeOnlyFileChannel.close();
            } catch (IOException e) {
                // do nothing
            }
        }
        if (null != readWriteFileChannel) {
            try {
                readWriteFileChannel.close();
            } catch (IOException e) {
                // do nothing
            }
        }
        if (null != fis) {
            try {
                fis.close();
            } catch (IOException e) {
                // do nothing
            }
        }
        if (null != fileOfReadOnlyFileChannel) {
            fileOfReadOnlyFileChannel.delete();
        }
        if (null != fileOfWriteOnlyFileChannel) {
            fileOfWriteOnlyFileChannel.delete();
        }
        if (null != fileOfReadWriteFileChannel) {
            fileOfReadWriteFileChannel.delete();
        }
    }

    /**
     * @tests java.nio.channels.FileChannel#force(boolean)
     */
    public void test_forceJ() throws Exception {
        ByteBuffer writeBuffer = ByteBuffer.wrap(CONTENT_AS_BYTES);
        writeOnlyFileChannel.write(writeBuffer);
        writeOnlyFileChannel.force(true);

        byte[] readBuffer = new byte[CONTENT_AS_BYTES_LENGTH];
        fis = new FileInputStream(fileOfWriteOnlyFileChannel);
        fis.read(readBuffer);
        assertTrue(Arrays.equals(CONTENT_AS_BYTES, readBuffer));
    }

    /**
     * @tests java.nio.channels.FileChannel#force(boolean)
     */
    public void test_forceJ_closed() throws Exception {
        writeOnlyFileChannel.close();
        try {
            writeOnlyFileChannel.force(true);
            fail("should throw ClosedChannelException");
        } catch (ClosedChannelException e) {
            // expected
        }

        try {
            writeOnlyFileChannel.force(false);
            fail("should throw ClosedChannelException");
        } catch (ClosedChannelException e) {
            // expected
        }
    }

    /**
     * @tests java.nio.channels.FileChannel#force(boolean)
     */
    public void test_forceJ_ReadOnlyChannel() throws Exception {
        // force on a read only file channel has no effect.
        readOnlyFileChannel.force(true);
        readOnlyFileChannel.force(false);
    }

    /**
     * @tests java.nio.channels.FileChannel#position()
     */
    public void test_position_Init() throws Exception {
        assertEquals(0, readOnlyFileChannel.position());
        assertEquals(0, writeOnlyFileChannel.position());
        assertEquals(0, readWriteFileChannel.position());
    }

    /**
     * @tests java.nio.channels.FileChannel#position()
     */
    public void test_position_ReadOnly() throws Exception {
        writeDataToFile(fileOfReadOnlyFileChannel);

        assertEquals(0, readOnlyFileChannel.position());
        ByteBuffer readBuffer = ByteBuffer.allocate(CONTENT_LENGTH);
        readOnlyFileChannel.read(readBuffer);
        assertEquals(CONTENT_LENGTH, readOnlyFileChannel.position());
    }

    /**
     * Initializes test file.
     * 
     * @param file
     * @throws FileNotFoundException
     * @throws IOException
     */
    private void writeDataToFile(File file) throws FileNotFoundException,
            IOException {
        FileOutputStream fos = new FileOutputStream(file);
        try {
            fos.write(CONTENT_AS_BYTES);
        } finally {
            fos.close();
        }
    }

    /**
     * @tests java.nio.channels.FileChannel#position()
     */
    public void test_position_WriteOnly() throws Exception {
        ByteBuffer writeBuffer = ByteBuffer.wrap(CONTENT_AS_BYTES);
        writeOnlyFileChannel.write(writeBuffer);
        assertEquals(CONTENT_LENGTH, writeOnlyFileChannel.position());
    }

    /**
     * @tests java.nio.channels.FileChannel#position()
     */
    public void test_position_ReadWrite() throws Exception {
        writeDataToFile(fileOfReadWriteFileChannel);

        assertEquals(0, readWriteFileChannel.position());
        ByteBuffer readBuffer = ByteBuffer.allocate(CONTENT_LENGTH);
        readWriteFileChannel.read(readBuffer);
        assertEquals(CONTENT_LENGTH, readWriteFileChannel.position());

        ByteBuffer writeBuffer = ByteBuffer.wrap(CONTENT_AS_BYTES);
        readWriteFileChannel.write(writeBuffer);
        assertEquals(CONTENT_LENGTH * 2, readWriteFileChannel.position());
    }

    /**
     * @tests java.nio.channels.FileChannel#position()
     */
    public void test_position_Closed() throws Exception {
        readOnlyFileChannel.close();
        try {
            readOnlyFileChannel.position();
            fail("should throw ClosedChannelException");
        } catch (ClosedChannelException e) {
            // expected
        }

        writeOnlyFileChannel.close();
        try {
            writeOnlyFileChannel.position();
            fail("should throw ClosedChannelException");
        } catch (ClosedChannelException e) {
            // expected
        }

        readWriteFileChannel.close();
        try {
            readWriteFileChannel.position();
            fail("should throw ClosedChannelException");
        } catch (ClosedChannelException e) {
            // expected
        }
    }

    /**
     * @tests java.nio.channels.FileChannel#position(long)
     */
    public void test_positionJ_Closed() throws Exception {
        final long POSITION = 100;

        readOnlyFileChannel.close();
        try {
            readOnlyFileChannel.position(POSITION);
            fail("should throw ClosedChannelException");
        } catch (ClosedChannelException e) {
            // expected
        }

        writeOnlyFileChannel.close();
        try {
            writeOnlyFileChannel.position(POSITION);
            fail("should throw ClosedChannelException");
        } catch (ClosedChannelException e) {
            // expected
        }

        readWriteFileChannel.close();
        try {
            readWriteFileChannel.position(POSITION);
            fail("should throw ClosedChannelException");
        } catch (ClosedChannelException e) {
            // expected
        }
    }

    /**
     * @tests java.nio.channels.FileChannel#position(long)
     */
    public void test_positionJ_Negative() throws Exception {
        final long NEGATIVE_POSITION = -1;
        try {
            readOnlyFileChannel.position(NEGATIVE_POSITION);
            fail("should throw IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            // expected
        }

        try {
            writeOnlyFileChannel.position(NEGATIVE_POSITION);
            fail("should throw IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            // expected
        }

        try {
            readWriteFileChannel.position(NEGATIVE_POSITION);
            fail("should throw IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            // expected
        }
    }

    /**
     * @tests java.nio.channels.FileChannel#position(long)
     */
    public void test_positionJ_ReadOnly() throws Exception {
        writeDataToFile(fileOfReadOnlyFileChannel);

        // set the position of the read only file channel to POSITION
        final int POSITION = 4;
        readOnlyFileChannel.position(POSITION);

        // reads the content left to readBuffer through read only file channel
        ByteBuffer readBuffer = ByteBuffer.allocate(CONTENT_LENGTH);
        int count = readOnlyFileChannel.read(readBuffer);
        assertEquals(CONTENT_LENGTH - POSITION, count);

        // asserts the content read is the part which stays beyond the POSITION
        readBuffer.flip();
        int i = POSITION;
        while (readBuffer.hasRemaining()) {
            assertEquals(CONTENT_AS_BYTES[i], readBuffer.get());
            i++;
        }
    }

    /**
     * @tests java.nio.channels.FileChannel#position(long)
     */
    public void test_positionJ_WriteOnly() throws Exception {
        writeDataToFile(fileOfWriteOnlyFileChannel);

        // init data to write
        ByteBuffer writeBuffer = ByteBuffer.wrap(CONTENT_AS_BYTES);

        // set the position of the write only file channel to POSITION
        final int POSITION = 4;
        writeOnlyFileChannel.position(POSITION);

        // writes to the write only file channel
        writeOnlyFileChannel.write(writeBuffer);
        // force to write out.
        writeOnlyFileChannel.close();

        // gets the result of the write only file channel
        byte[] result = new byte[POSITION + CONTENT_LENGTH];
        fis = new FileInputStream(fileOfWriteOnlyFileChannel);
        fis.read(result);

        // constructs the expected result which has content[0... POSITION] plus
        // content[0...length()]
        byte[] expectedResult = new byte[POSITION + CONTENT_LENGTH];
        System.arraycopy(CONTENT_AS_BYTES, 0, expectedResult, 0, POSITION);
        System.arraycopy(CONTENT_AS_BYTES, 0, expectedResult, POSITION,
                CONTENT_LENGTH);

        // asserts result of the write only file channel same as expected
        assertTrue(Arrays.equals(expectedResult, result));
    }

    /**
     * @tests java.nio.channels.FileChannel#size()
     */
    public void test_size_Init() throws Exception {
        assertEquals(0, readOnlyFileChannel.size());
        assertEquals(0, writeOnlyFileChannel.size());
        assertEquals(0, readWriteFileChannel.size());
    }

    /**
     * @tests java.nio.channels.FileChannel#size()
     */
    public void test_size() throws Exception {
        writeDataToFile(fileOfReadOnlyFileChannel);
        assertEquals(fileOfReadOnlyFileChannel.length(), readOnlyFileChannel
                .size());
    }

    /**
     * @tests java.nio.channels.FileChannel#size()
     */
    public void test_size_Closed() throws Exception {
        readOnlyFileChannel.close();
        try {
            readOnlyFileChannel.size();
            fail("should throw ClosedChannelException");
        } catch (ClosedChannelException e) {
            // expected
        }

        writeOnlyFileChannel.close();
        try {
            writeOnlyFileChannel.size();
            fail("should throw ClosedChannelException");
        } catch (ClosedChannelException e) {
            // expected
        }

        readWriteFileChannel.close();
        try {
            readWriteFileChannel.size();
            fail("should throw ClosedChannelException");
        } catch (ClosedChannelException e) {
            // expected
        }
    }

    /**
     * @tests java.nio.channels.FileChannel#truncate(long)
     */
    public void test_truncateJ_Closed() throws Exception {
        readOnlyFileChannel.close();
        try {
            readOnlyFileChannel.truncate(0);
            fail("should throw ClosedChannelException");
        } catch (ClosedChannelException e) {
            // expected
        }

        writeOnlyFileChannel.close();
        try {
            writeOnlyFileChannel.truncate(0);
            fail("should throw ClosedChannelException");
        } catch (ClosedChannelException e) {
            // expected
        }

        readWriteFileChannel.close();
        try {
            readWriteFileChannel.truncate(-1);
            fail("should throw ClosedChannelException");
        } catch (ClosedChannelException e) {
            // expected
        }
    }

    /**
     * @tests java.nio.channels.FileChannel#truncate(long)
     */
    public void test_truncateJ_IllegalArgument() throws Exception {
        try {
            writeOnlyFileChannel.truncate(-1);
            fail("should throw IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            // expected
        }

        try {
            readWriteFileChannel.truncate(-1);
            fail("should throw IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            // expected
        }
    }

    /**
     * @tests java.nio.channels.FileChannel#truncate(long)
     */
    public void test_truncateJ_ReadOnly() throws Exception {
        writeDataToFile(fileOfReadOnlyFileChannel);
        try {
            readOnlyFileChannel.truncate(readOnlyFileChannel.size());
            fail("should throw NonWritableChannelException.");
        } catch (NonWritableChannelException e) {
            // expected
        }

        try {
            readOnlyFileChannel.truncate(0);
            fail("should throw NonWritableChannelException.");
        } catch (NonWritableChannelException e) {
            // expected
        }
    }

    /**
     * @tests java.nio.channels.FileChannel#truncate(long)
     */
    public void test_truncateJ() throws Exception {
        writeDataToFile(fileOfReadWriteFileChannel);

        int truncateLength = CONTENT_LENGTH + 2;
        assertEquals(readWriteFileChannel, readWriteFileChannel
                .truncate(truncateLength));
        assertEquals(CONTENT_LENGTH, fileOfReadWriteFileChannel.length());

        truncateLength = CONTENT_LENGTH;
        assertEquals(readWriteFileChannel, readWriteFileChannel
                .truncate(truncateLength));
        assertEquals(CONTENT_LENGTH, fileOfReadWriteFileChannel.length());

        truncateLength = CONTENT_LENGTH / 2;
        assertEquals(readWriteFileChannel, readWriteFileChannel
                .truncate(truncateLength));
        assertEquals(truncateLength, fileOfReadWriteFileChannel.length());
    }

    /**
     * @tests java.nio.channels.FileChannel#isOpen()
     */
    public void test_isOpen() throws Exception {
        // Regression for HARMONY-40
        File logFile = File.createTempFile("out", "tmp");
        logFile.deleteOnExit();
        FileOutputStream out = new FileOutputStream(logFile, true);
        FileChannel channel = out.getChannel();
        out.write(1);
        out.close();
        assertFalse("Assert 0: Channel is still open", channel.isOpen());
    }

    /**
     * @tests java.nio.channels.FileChannel#position()
     */
    public void test_position_append() throws Exception {
        // Regression test for Harmony-508
        File tmpfile = File.createTempFile("FileOutputStream", "tmp");
        tmpfile.deleteOnExit();
        FileOutputStream fos = new FileOutputStream(tmpfile);
        byte[] b = new byte[10];
        for (int i = 0; i < b.length; i++) {
            b[i] = (byte) i;
        }
        fos.write(b);
        fos.flush();
        fos.close();
        FileOutputStream f = new FileOutputStream(tmpfile, true);
        // Below assertion fails on RI. RI behaviour is counter to spec.
        assertEquals(10, f.getChannel().position());
    }
}
