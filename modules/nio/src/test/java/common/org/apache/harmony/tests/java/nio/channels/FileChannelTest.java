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
import java.io.UnsupportedEncodingException;
import java.nio.BufferOverflowException;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.ReadOnlyBufferException;
import java.nio.channels.ClosedChannelException;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.nio.channels.NonReadableChannelException;
import java.nio.channels.NonWritableChannelException;
import java.nio.channels.OverlappingFileLockException;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.WritableByteChannel;
import java.nio.channels.FileChannel.MapMode;
import java.util.Arrays;

import junit.framework.TestCase;

public class FileChannelTest extends TestCase {

    private static final String CONTENT = "it is content of a string for test";

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

    private FileLock fileLock;
    
    private static final byte[] TEST_BYTES;

    static {
        try {
            TEST_BYTES = "test".getBytes("iso8859-1");
        } catch (UnsupportedEncodingException e) {
            throw new Error(e);
        }
    }

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
        fileLock = null;
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

        if (null != fileLock) {
            try {
                fileLock.release();
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
        assertEquals(CONTENT_LENGTH, readWriteFileChannel.size());

        truncateLength = CONTENT_LENGTH;
        assertEquals(readWriteFileChannel, readWriteFileChannel
                .truncate(truncateLength));
        assertEquals(CONTENT_LENGTH, readWriteFileChannel.size());

        truncateLength = CONTENT_LENGTH / 2;
        assertEquals(readWriteFileChannel, readWriteFileChannel
                .truncate(truncateLength));
        assertEquals(truncateLength, readWriteFileChannel.size());
    }

    /**
     * @tests java.nio.channels.FileChannel#lock()
     */
    public void test_lock() throws Exception {
        MockFileChannel mockFileChannel = new MockFileChannel();
        // Verify that calling lock() leads to the method
        // lock(long, long, boolean) being called with a 0 for the
        // first parameter, Long.MAX_VALUE as the second parameter and false
        // as the third parameter.
        mockFileChannel.lock();
        assertTrue(mockFileChannel.isLockCalled);
    }

    /**
     * @tests java.nio.channels.FileChannel#lock(long, long, boolean)
     */
    public void test_lockJJZ_Closed() throws Exception {
        readOnlyFileChannel.close();
        try {
            readOnlyFileChannel.lock(0, 10, false);
            fail("should throw ClosedChannelException");
        } catch (ClosedChannelException e) {
            // expected
        }

        writeOnlyFileChannel.close();
        try {
            writeOnlyFileChannel.lock(0, 10, false);
            fail("should throw ClosedChannelException");
        } catch (ClosedChannelException e) {
            // expected
        }

        readWriteFileChannel.close();
        try {
            readWriteFileChannel.lock(0, 10, false);
            fail("should throw ClosedChannelException");
        } catch (ClosedChannelException e) {
            // expected
        }

        // throws ClosedChannelException before IllegalArgumentException
        try {
            readWriteFileChannel.lock(-1, 0, false);
            fail("should throw ClosedChannelException");
        } catch (ClosedChannelException e) {
            // expected
        }
    }

    /**
     * @tests java.nio.channels.FileChannel#lock(long, long, boolean)
     */
    public void test_lockJJZ_IllegalArgument() throws Exception {
        try {
            writeOnlyFileChannel.lock(0, -1, false);
            fail("should throw IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            // expected
        }

        try {
            writeOnlyFileChannel.lock(-1, 0, false);
            fail("should throw IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            // expected
        }

        try {
            readWriteFileChannel.lock(-1, -1, false);
            fail("should throw IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            // expected
        }

        try {
            readWriteFileChannel.lock(Long.MAX_VALUE, 1, false);
            fail("should throw IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            // expected
        }
    }

    /**
     * @tests java.nio.channels.FileChannel#lock(long, long, boolean)
     */
    public void test_lockJJZ_NonWritable() throws Exception {
        try {
            readOnlyFileChannel.lock(0, 10, false);
            fail("should throw NonWritableChannelException");
        } catch (NonWritableChannelException e) {
            // expected
        }

        // throws NonWritableChannelException before IllegalArgumentException
        try {
            readOnlyFileChannel.lock(-1, 0, false);
            fail("should throw NonWritableChannelException");
        } catch (NonWritableChannelException e) {
            // expected
        }
    }

    /**
     * @tests java.nio.channels.FileChannel#lock(long, long, boolean)
     */
    public void test_lockJJZ_NonReadable() throws Exception {
        try {
            writeOnlyFileChannel.lock(0, 10, true);
            fail("should throw NonReadableChannelException");
        } catch (NonReadableChannelException e) {
            // expected
        }

        // throws NonReadableChannelException before IllegalArgumentException
        try {
            writeOnlyFileChannel.lock(-1, 0, true);
            fail("should throw NonReadableChannelException");
        } catch (NonReadableChannelException e) {
            // expected
        }
    }

    /**
     * @tests java.nio.channels.FileChannel#lock(long, long, boolean)
     */
    public void test_lockJJZ_Shared() throws Exception {
        final long POSITION = 100;
        final long SIZE = 200;
        fileLock = readOnlyFileChannel.lock(POSITION, SIZE, true);
        assertTrue(fileLock.isValid());
        // fileLock.isShared depends on whether the underlying platform support
        // shared lock, but it works on Windows & Linux.
        assertTrue(fileLock.isShared());
        assertSame(readOnlyFileChannel, fileLock.channel());
        assertEquals(POSITION, fileLock.position());
        assertEquals(SIZE, fileLock.size());
    }

    /**
     * @tests java.nio.channels.FileChannel#lock(long, long, boolean)
     */
    public void test_lockJJZ_NotShared() throws Exception {
        final long POSITION = 100;
        final long SIZE = 200;
        fileLock = writeOnlyFileChannel.lock(POSITION, SIZE, false);
        assertTrue(fileLock.isValid());
        assertFalse(fileLock.isShared());
        assertSame(writeOnlyFileChannel, fileLock.channel());
        assertEquals(POSITION, fileLock.position());
        assertEquals(SIZE, fileLock.size());
    }

    /**
     * @tests java.nio.channels.FileChannel#lock(long, long, boolean)
     */
    public void test_lockJJZ_Long_MAX_VALUE() throws Exception {
        final long POSITION = 0;
        final long SIZE = Long.MAX_VALUE;
        fileLock = readOnlyFileChannel.lock(POSITION, SIZE, true);
        assertTrue(fileLock.isValid());
        assertTrue(fileLock.isShared());
        assertEquals(POSITION, fileLock.position());
        assertEquals(SIZE, fileLock.size());
        assertSame(readOnlyFileChannel, fileLock.channel());
    }

    /**
     * @tests java.nio.channels.FileChannel#lock(long, long, boolean)
     */
    public void test_lockJJZ_Overlapping() throws Exception {
        final long POSITION = 100;
        final long SIZE = 200;
        fileLock = writeOnlyFileChannel.lock(POSITION, SIZE, false);
        assertTrue(fileLock.isValid());

        try {
            writeOnlyFileChannel.lock(POSITION + 1, SIZE, false);
            fail("should throw OverlappingFileLockException");
        } catch (OverlappingFileLockException e) {
            // expected
        }
    }

    /**
     * @tests java.nio.channels.FileChannel#lock(long, long, boolean)
     */
    public void test_lockJJZ_NotOverlapping() throws Exception {
        final long POSITION = 100;
        final long SIZE = 200;
        FileLock fileLock1 = writeOnlyFileChannel.lock(POSITION, SIZE, false);
        assertTrue(fileLock1.isValid());
        FileLock fileLock2 = writeOnlyFileChannel.lock(POSITION + SIZE, SIZE,
                false);
        assertTrue(fileLock2.isValid());
    }

    /**
     * @tests java.nio.channels.FileChannel#lock(long,long,boolean)
     */
    public void test_lockJJZ_After_Release() throws Exception {
        fileLock = writeOnlyFileChannel.lock(0, 10, false);
        fileLock.release();
        // after release file lock can be obtained again.
        fileLock = writeOnlyFileChannel.lock(0, 10, false);
        assertTrue(fileLock.isValid());
    }

    /**
     * @tests java.nio.channels.FileChannel#tryLock()
     */
    public void test_tryLock() throws Exception {
        MockFileChannel mockFileChannel = new MockFileChannel();
        // Verify that calling tryLock() leads to the method
        // tryLock(long, long, boolean) being called with a 0 for the
        // first parameter, Long.MAX_VALUE as the second parameter and false
        // as the third parameter.
        mockFileChannel.tryLock();
        assertTrue(mockFileChannel.isTryLockCalled);
    }

    /**
     * @tests java.nio.channels.FileChannel#tryLock(long, long, boolean)
     */
    public void test_tryLockJJZ_Closed() throws Exception {
        readOnlyFileChannel.close();
        try {
            readOnlyFileChannel.tryLock(0, 10, false);
            fail("should throw ClosedChannelException");
        } catch (ClosedChannelException e) {
            // expected
        }

        writeOnlyFileChannel.close();
        try {
            writeOnlyFileChannel.tryLock(0, 10, false);
            fail("should throw ClosedChannelException");
        } catch (ClosedChannelException e) {
            // expected
        }

        readWriteFileChannel.close();
        try {
            readWriteFileChannel.tryLock(0, 10, false);
            fail("should throw ClosedChannelException");
        } catch (ClosedChannelException e) {
            // expected
        }

        // throws ClosedChannelException before IllegalArgumentException
        try {
            readWriteFileChannel.tryLock(-1, 0, false);
            fail("should throw ClosedChannelException");
        } catch (ClosedChannelException e) {
            // expected
        }
    }

    /**
     * @tests java.nio.channels.FileChannel#tryLock(long, long, boolean)
     */
    public void test_tryLockJJZ_IllegalArgument() throws Exception {
        try {
            writeOnlyFileChannel.tryLock(0, -1, false);
            fail("should throw IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            // expected
        }

        try {
            writeOnlyFileChannel.tryLock(-1, 0, false);
            fail("should throw IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            // expected
        }

        try {
            readWriteFileChannel.tryLock(-1, -1, false);
            fail("should throw IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            // expected
        }

        try {
            readWriteFileChannel.tryLock(Long.MAX_VALUE, 1, false);
            fail("should throw IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            // expected
        }
    }

    /**
     * @tests java.nio.channels.FileChannel#tryLock(long, long, boolean)
     */
    public void test_tryLockJJZ_NonWritable() throws Exception {
        try {
            readOnlyFileChannel.tryLock(0, 10, false);
            fail("should throw NonWritableChannelException");
        } catch (NonWritableChannelException e) {
            // expected
        }

        // throws NonWritableChannelException before IllegalArgumentException
        try {
            readOnlyFileChannel.tryLock(-1, 0, false);
            fail("should throw NonWritableChannelException");
        } catch (NonWritableChannelException e) {
            // expected
        }
    }

    /**
     * @tests java.nio.channels.FileChannel#tryLock(long, long, boolean)
     */
    public void test_tryLockJJZ_NonReadable() throws Exception {
        try {
            writeOnlyFileChannel.tryLock(0, 10, true);
            fail("should throw NonReadableChannelException");
        } catch (NonReadableChannelException e) {
            // expected
        }

        // throws NonReadableChannelException before IllegalArgumentException
        try {
            writeOnlyFileChannel.tryLock(-1, 0, true);
            fail("should throw NonReadableChannelException");
        } catch (NonReadableChannelException e) {
            // expected
        }
    }

    /**
     * @tests java.nio.channels.FileChannel#tryLock(long, long, boolean)
     */
    public void test_tryLockJJZ_Shared() throws Exception {
        final long POSITION = 100;
        final long SIZE = 200;
        fileLock = readOnlyFileChannel.tryLock(POSITION, SIZE, true);
        assertTrue(fileLock.isValid());
        // fileLock.isShared depends on whether the underlying platform support
        // shared lock, but it works on Windows & Linux.
        assertTrue(fileLock.isShared());
        assertSame(readOnlyFileChannel, fileLock.channel());
        assertEquals(POSITION, fileLock.position());
        assertEquals(SIZE, fileLock.size());
    }

    /**
     * @tests java.nio.channels.FileChannel#tryLock(long, long, boolean)
     */
    public void test_tryLockJJZ_NotShared() throws Exception {
        final long POSITION = 100;
        final long SIZE = 200;
        fileLock = writeOnlyFileChannel.tryLock(POSITION, SIZE, false);
        assertTrue(fileLock.isValid());
        assertFalse(fileLock.isShared());
        assertSame(writeOnlyFileChannel, fileLock.channel());
        assertEquals(POSITION, fileLock.position());
        assertEquals(SIZE, fileLock.size());
    }

    /**
     * @tests java.nio.channels.FileChannel#tryLock(long, long, boolean)
     */
    public void test_tryLockJJZ_Long_MAX_VALUE() throws Exception {
        final long POSITION = 0;
        final long SIZE = Long.MAX_VALUE;
        fileLock = readOnlyFileChannel.tryLock(POSITION, SIZE, true);
        assertTrue(fileLock.isValid());
        assertTrue(fileLock.isShared());
        assertEquals(POSITION, fileLock.position());
        assertEquals(SIZE, fileLock.size());
        assertSame(readOnlyFileChannel, fileLock.channel());
    }

    /**
     * @tests java.nio.channels.FileChannel#tryLock(long, long, boolean)
     */
    public void test_tryLockJJZ_Overlapping() throws Exception {
        final long POSITION = 100;
        final long SIZE = 200;
        fileLock = writeOnlyFileChannel.lock(POSITION, SIZE, false);
        assertTrue(fileLock.isValid());

        try {
            writeOnlyFileChannel.lock(POSITION + 1, SIZE, false);
            fail("should throw OverlappingFileLockException");
        } catch (OverlappingFileLockException e) {
            // expected
        }
    }

    /**
     * @tests java.nio.channels.FileChannel#tryLock(long, long, boolean)
     */
    public void test_tryLockJJZ_NotOverlapping() throws Exception {
        final long POSITION = 100;
        final long SIZE = 200;
        FileLock fileLock1 = writeOnlyFileChannel
                .tryLock(POSITION, SIZE, false);
        assertTrue(fileLock1.isValid());

        FileLock fileLock2 = writeOnlyFileChannel.tryLock(POSITION + SIZE,
                SIZE, false);
        assertTrue(fileLock2.isValid());
    }

    /**
     * @tests java.nio.channels.FileChannel#tryLock(long,long,boolean)
     */
    public void test_tryLockJJZ_After_Release() throws Exception {
        fileLock = writeOnlyFileChannel.tryLock(0, 10, false);
        fileLock.release();

        // after release file lock can be obtained again.
        fileLock = writeOnlyFileChannel.tryLock(0, 10, false);
        assertTrue(fileLock.isValid());
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
    

    /**
     * @tests java.nio.channels.FileChannel#map(MapMode,long,long)
     */
    public void test_map_AbnormalMode() throws IOException {
        try {
            writeOnlyFileChannel.map(MapMode.READ_ONLY, 0, CONTENT_LENGTH);
            fail("should throw NonReadableChannelException.");
        } catch (NonReadableChannelException ex) {
            // expected;
        }
        try {
            writeOnlyFileChannel.map(MapMode.READ_WRITE, 0, CONTENT_LENGTH);
            fail("should throw NonReadableChannelException.");
        } catch (NonReadableChannelException ex) {
            // expected;
        }
        try {
            writeOnlyFileChannel.map(MapMode.PRIVATE, 0, CONTENT_LENGTH);
            fail("should throw NonReadableChannelException.");
        } catch (NonReadableChannelException ex) {
            // expected;
        }
        writeOnlyFileChannel.close();
        try {
            writeOnlyFileChannel.map(MapMode.READ_WRITE, 0, -1);
            fail("should throw ClosedChannelExeption.");
        } catch (ClosedChannelException ex) {
            // expected;
        }

        try {
            readOnlyFileChannel.map(MapMode.READ_WRITE, 0, CONTENT_LENGTH);
            fail("should throw NonWritableChannelException .");
        } catch (NonWritableChannelException ex) {
            // expected;
        }
        try {
            readOnlyFileChannel.map(MapMode.PRIVATE, 0, CONTENT_LENGTH);
            fail("should throw NonWritableChannelException .");
        } catch (NonWritableChannelException ex) {
            // expected;
        }
        try {
            readOnlyFileChannel.map(MapMode.READ_WRITE, -1, CONTENT_LENGTH);
            fail("should throw IAE.");
        } catch (IllegalArgumentException ex) {
            // expected;
        }
        try {
            readOnlyFileChannel.map(MapMode.READ_WRITE, 0, -1);
            fail("should throw IAE.");
        } catch (IllegalArgumentException ex) {
            // expected;
        }

        try {
            readOnlyFileChannel.map(MapMode.READ_ONLY, 0, CONTENT_LENGTH + 1);
            fail("should throw IOException.");
        } catch (IOException ex) {
            // expected;
        }
        try {
            readOnlyFileChannel.map(MapMode.READ_ONLY, 2, CONTENT_LENGTH - 1);
            fail("should throw IOException.");
        } catch (IOException ex) {
            // expected;
        }

        readOnlyFileChannel.close();
        try {
            readOnlyFileChannel.map(MapMode.READ_WRITE, 0, -1);
            fail("should throw ClosedChannelExeption.");
        } catch (ClosedChannelException ex) {
            // expected;
        }
        try {
            readOnlyFileChannel.map(MapMode.READ_ONLY, 2, CONTENT_LENGTH - 1);
            fail("should throw IOException.");
        } catch (IOException ex) {
            // expected;
        }

        readWriteFileChannel.close();
        try {
            readWriteFileChannel.map(MapMode.READ_WRITE, 0, -1);
            fail("should throw ClosedChannelExeption.");
        } catch (ClosedChannelException ex) {
            // expected;
        }
    }
    
    /**
     * @tests java.nio.channels.FileChannel#map(MapMode,long,long)
     */
    public void test_map_ReadOnly_CloseChannel() throws IOException {
        // close channel has no effect on map if mapped
        assertEquals(0, readWriteFileChannel.size());
        MappedByteBuffer mapped = readWriteFileChannel.map(MapMode.READ_ONLY,
                0, CONTENT_LENGTH);
        assertEquals(CONTENT_LENGTH, readWriteFileChannel.size());
        readOnlyFileChannel.close();
        assertEquals(CONTENT_LENGTH, mapped.limit());
    }

    /**
     * @tests java.nio.channels.FileChannel#map(MapMode,long,long)
     */
    public void test_map_Private_CloseChannel() throws IOException {
        MappedByteBuffer mapped = readWriteFileChannel.map(MapMode.PRIVATE, 0,
                CONTENT_LENGTH);
        readWriteFileChannel.close();
        mapped.put(TEST_BYTES);
        assertEquals(CONTENT_LENGTH, mapped.limit());
        assertEquals("test".length(), mapped.position());
    }

    /**
     * @tests java.nio.channels.FileChannel#map(MapMode,long,long)
     */
    public void test_map_ReadOnly() throws IOException {
        MappedByteBuffer mapped = null;
        // try put something to readonly map
        writeDataToFile(fileOfReadOnlyFileChannel);
        mapped = readOnlyFileChannel.map(MapMode.READ_ONLY, 0, CONTENT_LENGTH);
        try {
            mapped.put(TEST_BYTES);
            fail("should throw ReadOnlyBufferException.");
        } catch (ReadOnlyBufferException ex) {
            // expected;
        }
        assertEquals(CONTENT_LENGTH, mapped.limit());
        assertEquals(CONTENT_LENGTH, mapped.capacity());
        assertEquals(0, mapped.position());

        // try to get a readonly map from read/write channel
        writeDataToFile(fileOfReadWriteFileChannel);
        mapped = readWriteFileChannel.map(MapMode.READ_ONLY, 0, CONTENT
                .length());
        assertEquals(CONTENT_LENGTH, mapped.limit());
        assertEquals(CONTENT_LENGTH, mapped.capacity());
        assertEquals(0, mapped.position());

        // map not change channel's position
        assertEquals(0, readOnlyFileChannel.position());
        assertEquals(0, readWriteFileChannel.position());
    }

    /**
     * @tests java.nio.channels.FileChannel#map(MapMode,long,long)
     */
    public void test_map_ReadOnly_NonZeroPosition() throws IOException {
        this.writeDataToFile(fileOfReadOnlyFileChannel);
        MappedByteBuffer mapped = readOnlyFileChannel.map(MapMode.READ_ONLY,
                10, CONTENT_LENGTH - 10);
        assertEquals(CONTENT_LENGTH - 10, mapped.limit());
        assertEquals(CONTENT_LENGTH - 10, mapped.capacity());
        assertEquals(0, mapped.position());
    }

    /**
     * @tests java.nio.channels.FileChannel#map(MapMode,long,long)
     */
    public void test_map_Private() throws IOException {
        this.writeDataToFile(fileOfReadWriteFileChannel);
        MappedByteBuffer mapped = readWriteFileChannel.map(MapMode.PRIVATE, 0,
                CONTENT_LENGTH);
        assertEquals(CONTENT_LENGTH, mapped.limit());
        // test copy on write if private
        ByteBuffer returnByPut = mapped.put(TEST_BYTES);
        assertSame(returnByPut, mapped);
        ByteBuffer checkBuffer = ByteBuffer.allocate(CONTENT_LENGTH);
        mapped.force();
        readWriteFileChannel.read(checkBuffer);
        assertEquals(CONTENT, new String(checkBuffer.array(), "iso8859-1"));

        // test overflow
        try {
            mapped.put(("test" + CONTENT).getBytes("iso8859-1"));
            fail("should throw BufferOverflowException.");
        } catch (BufferOverflowException ex) {
            // expected;
        }
    }

    /**
     * @tests java.nio.channels.FileChannel#map(MapMode,long,long)
     */
    public void test_map_Private_NonZeroPosition() throws IOException {
        MappedByteBuffer mapped = readWriteFileChannel.map(MapMode.PRIVATE, 10,
                CONTENT_LENGTH - 10);
        assertEquals(CONTENT_LENGTH - 10, mapped.limit());
        assertEquals(CONTENT_LENGTH - 10, mapped.capacity());
        assertEquals(0, mapped.position());
    }

    /**
     * @tests java.nio.channels.FileChannel#map(MapMode,long,long)
     */
    public void test_map_ReadWrite() throws IOException {
        MappedByteBuffer mapped = null;
        writeDataToFile(fileOfReadWriteFileChannel);
        mapped = readWriteFileChannel.map(MapMode.READ_WRITE, 0, CONTENT
                .length());

        // put something will change its channel
        ByteBuffer returnByPut = mapped.put(TEST_BYTES);
        assertSame(returnByPut, mapped);
        String checkString = "test" + CONTENT.substring(4);
        ByteBuffer checkBuffer = ByteBuffer.allocate(CONTENT_LENGTH);
        mapped.force();
        readWriteFileChannel.position(0);
        readWriteFileChannel.read(checkBuffer);
        assertEquals(checkString, new String(checkBuffer.array(), "iso8859-1"));

        try {
            mapped.put(("test" + CONTENT).getBytes("iso8859-1"));
            fail("should throw BufferOverflowException.");
        } catch (BufferOverflowException ex) {
            // expected;
        }
    }

    /**
     * @tests java.nio.channels.FileChannel#map(MapMode,long,long)
     */
    public void test_map_ReadWrite_NonZeroPosition() throws IOException {
        // test position non-zero
        writeDataToFile(fileOfReadWriteFileChannel);
        MappedByteBuffer mapped = readWriteFileChannel.map(MapMode.READ_WRITE,
                10, CONTENT_LENGTH - 10);
        assertEquals(CONTENT_LENGTH - 10, mapped.limit());
        assertEquals(CONTENT.length() - 10, mapped.capacity());
        assertEquals(0, mapped.position());
        mapped.put(TEST_BYTES);
        ByteBuffer checkBuffer = ByteBuffer.allocate(CONTENT_LENGTH);
        readWriteFileChannel.read(checkBuffer);
        String expected = CONTENT.substring(0, 10) + "test"
                + CONTENT.substring(10 + "test".length());
        assertEquals(expected, new String(checkBuffer.array(), "iso8859-1"));
    }


    private class MockFileChannel extends FileChannel {
        
        private boolean isLockCalled = false;
        
        private boolean isTryLockCalled = false;

        public void force(boolean arg0) throws IOException {
            // do nothing
        }

        public FileLock lock(long position, long size, boolean shared)
                throws IOException {
            // verify that calling lock() leads to the method 
            // lock(0, Long.MAX_VALUE, false).
            if (0 == position && Long.MAX_VALUE == size && false == shared) {
                isLockCalled = true;
            }
            return null;
        }

        public MappedByteBuffer map(MapMode arg0, long arg1, long arg2)
                throws IOException {
            return null;
        }

        public long position() throws IOException {
            return 0;
        }

        public FileChannel position(long arg0) throws IOException {
            return null;
        }

        public int read(ByteBuffer arg0) throws IOException {
            return 0;
        }

        public int read(ByteBuffer arg0, long arg1) throws IOException {
            return 0;
        }

        public long read(ByteBuffer[] srcs, int offset, int length)
                throws IOException {
            return 0;
        }

        public long size() throws IOException {
            return 0;
        }

        public long transferFrom(ReadableByteChannel arg0, long arg1, long arg2)
                throws IOException {
            return 0;
        }

        public long transferTo(long arg0, long arg1, WritableByteChannel arg2)
                throws IOException {
            return 0;
        }

        public FileChannel truncate(long arg0) throws IOException {
            return null;
        }

        public FileLock tryLock(long position, long size, boolean shared)
                throws IOException {
            // verify that calling tryLock() leads to the method 
            // tryLock(0, Long.MAX_VALUE, false).
            if (0 == position && Long.MAX_VALUE == size && false == shared) {
                isTryLockCalled = true;
            }
            return null;
        }

        public int write(ByteBuffer arg0) throws IOException {
            return 0;
        }

        public int write(ByteBuffer arg0, long arg1) throws IOException {
            return 0;
        }

        public long write(ByteBuffer[] srcs, int offset, int length)
                throws IOException {
            return 0;
        }

        protected void implCloseChannel() throws IOException {

        }
    }
}
