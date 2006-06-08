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
package tests.api.java.util;

import java.io.Closeable;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PipedInputStream;
import java.io.StringReader;
import java.nio.CharBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.util.Scanner;

import junit.framework.TestCase;

public class ScannerTest extends TestCase {

    Scanner s;

    private static class MockCloseable implements Closeable, Readable {

        public void close() throws IOException {
            throw new IOException();
        }

        public int read(CharBuffer cb) throws IOException {
            return 0;
        }

    }

    /**
     * @tests java.util.Scanner#Scanner(File)
     */
    public void test_ConstructorLjava_io_File() throws IOException {
        File tmpFile = File.createTempFile("TestFileForScanner", ".tmp");
        s = new Scanner(tmpFile);
        assertNotNull(s);
        s.close();
        assertTrue(tmpFile.delete());

        try {
            s = new Scanner(tmpFile);
            fail("should throw FileNotFoundException");
        } catch (FileNotFoundException e) {
            // expected
        }

        // TODO: test if the default charset is used.
    }

    /**
     * @tests java.util.Scanner#Scanner(File, String)
     */
    public void test_ConstructorLjava_io_FileLjava_lang_String()
            throws IOException {
        File tmpFile = File.createTempFile("TestFileForScanner", ".tmp");
        s = new Scanner(tmpFile, Charset.defaultCharset().name());
        assertNotNull(s);
        s.close();
        assertTrue(tmpFile.delete());

        try {
            s = new Scanner(tmpFile, Charset.defaultCharset().name());
            fail("should throw FileNotFoundException");
        } catch (FileNotFoundException e) {
            // expected
        }

        tmpFile = File.createTempFile("TestFileForScanner", ".tmp");
        try {
            s = new Scanner(tmpFile, "invalid charset");
            fail("should throw IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            // expected
        }
        // fail on RI. File is opened but not closed when exception is thrown on
        // RI.
        assertTrue(tmpFile.delete());

        // TODO: test if the specified charset is used.
    }

    /**
     * @tests java.util.Scanner#Scanner(InputStream)
     */
    public void test_ConstructorLjava_io_InputStream() {
        s = new Scanner(new PipedInputStream());
        assertNotNull(s);
        s.close();

        // TODO: test if the default charset is used.
    }

    /**
     * @tests java.util.Scanner#Scanner(InputStream, String)
     */
    public void test_ConstructorLjava_io_InputStreamLjava_lang_String() {
        s = new Scanner(new PipedInputStream(), Charset.defaultCharset().name());
        assertNotNull(s);
        s.close();

        try {
            s = new Scanner(new PipedInputStream(), "invalid charset");
            fail("should throw IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            // expected
        }

        // TODO: test if the specified charset is used.
    }

    /**
     * @tests java.util.Scanner#Scanner(Readable)
     */
    public void test_ConstructorLjava_lang_Readable() {
        s = new Scanner(new StringReader("test string"));
        assertNotNull(s);
        s.close();
    }

    /**
     * @tests java.util.Scanner#Scanner(ReadableByteChannel)
     */
    public void test_ConstructorLjava_nio_channels_ReadableByteChannel()
            throws IOException {
        File tmpFile = File.createTempFile("TestFileForScanner", ".tmp");
        FileChannel fc = new FileOutputStream(tmpFile).getChannel();
        s = new Scanner(fc);
        assertNotNull(s);
        s.close();
        assertTrue(tmpFile.delete());

        // TODO: test if the default charset is used.
    }

    /**
     * @tests java.util.Scanner#Scanner(ReadableByteChannel, String)
     */
    public void test_ConstructorLjava_nio_channels_ReadableByteChannelLjava_lang_String()
            throws IOException {
        File tmpFile = File.createTempFile("TestFileForScanner", ".tmp");
        FileChannel fc = new FileOutputStream(tmpFile).getChannel();
        s = new Scanner(fc, Charset.defaultCharset().name());
        assertNotNull(s);
        s.close();

        fc = new FileOutputStream(tmpFile).getChannel();
        try {
            s = new Scanner(fc, "invalid charset");
            fail("should throw IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            // expected
        }
        fc.close();
        assertTrue(tmpFile.delete());

        // TODO: test if the specified charset is used.
    }

    /**
     * @tests java.util.Scanner#Scanner(String)
     */
    public void test_ConstructorLjava_lang_String() {
        s = new Scanner("test string");
        assertNotNull(s);
        s.close();
    }

    /**
     * @tests java.util.Scanner#close()
     */
    public void test_close() throws IOException {
        File tmpFile = File.createTempFile("TestFileForScanner", ".tmp");
        FileChannel fc = new FileOutputStream(tmpFile).getChannel();
        s = new Scanner(fc);
        s.close();
        assertFalse(fc.isOpen());
        s.close(); // no exception should be thrown

        // TODO: test if invoking scan operation will raise
        // IllegalStateException
        assertTrue(tmpFile.delete());
    }

    /**
     * @tests java.util.Scanner#ioException()
     */
    public void test_ioException() throws IOException {
        MockCloseable mc = new MockCloseable();
        s = new Scanner(mc);
        assertNull(s.ioException());
        s.close();
        assertNotNull(s.ioException());
    }
}
