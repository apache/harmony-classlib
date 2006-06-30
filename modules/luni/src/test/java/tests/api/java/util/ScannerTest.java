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
import java.io.EOFException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PipedInputStream;
import java.io.StringReader;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;
import java.nio.CharBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.InputMismatchException;
import java.util.Locale;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.regex.Pattern;

import junit.framework.TestCase;

public class ScannerTest extends TestCase {

    Scanner s;

    ServerSocket server;

    SocketAddress address;

    SocketChannel client;

    Socket serverSocket;

    OutputStream os;

    private static class MockCloseable implements Closeable, Readable {

        public void close() throws IOException {
            throw new IOException();
        }

        public int read(CharBuffer cb) throws IOException {
            throw new EOFException();
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

        tmpFile = File.createTempFile("TestFileForScanner", ".tmp");
        FileOutputStream fos = new FileOutputStream(tmpFile);
        fos.write("test".getBytes());

        s = new Scanner(tmpFile);
        tmpFile.delete();

        // Scanner(File = null)
        try {
            s = new Scanner((File) null);
            fail("Should throw NullPointerException");
        } catch (NullPointerException e) {
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

        try {
            s = new Scanner(tmpFile, null);
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

        //fail on RI. File is opened but not closed when exception is thrown on
        // RI.
        assertTrue(tmpFile.delete());

        // Scanner(File = null, Charset = null)
        try {
            s = new Scanner((File) null, null);
            fail("Should throw NullPointerException");
        } catch (NullPointerException e) {
            // expected
        }

        // Scanner(File = null, Charset = UTF-8)
        try {
            s = new Scanner((File) null, "UTF-8");
            fail("Should throw NullPointerException");
        } catch (NullPointerException e) {
            // expected
        }

        // Scanner(File = null, Charset = invalid)
        try {
            s = new Scanner((File) null, "invalid");
            fail("Should throw NullPointerException");
        } catch (NullPointerException e) {
            // expected
        }

        // Scanner(File, Charset = null)
        try {
            File f = File.createTempFile("test", ".tmp");
            s = new Scanner(f, null);
            fail("Should throw IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            // expected
        }

        // TODO: test if the specified charset is used.
    }

    /**
     * @tests java.util.Scanner#Scanner(InputStream)
     */
    public void test_ConstructorLjava_io_InputStream() {
        s = new Scanner(new PipedInputStream());
        assertNotNull(s);
        s.close();

        // Scanner(InputStream)
        try {
            s = new Scanner((InputStream) null);
            fail("Should throw NullPointerException");
        } catch (NullPointerException e) {
            // expected
        }

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
            s = new Scanner((PipedInputStream) null, "invalid charset");
            fail("should throw NullPointerException");
        } catch (NullPointerException e) {
            // expected
        }

        try {
            s = new Scanner(new PipedInputStream(), null);
            fail("should throw NullPointerException");
        } catch (NullPointerException e) {
            // expected
        }

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

        // Scanner(Readable)
        try {
            s = new Scanner((Readable) null);
            fail("Should throw NullPointerException");
        } catch (NullPointerException e) {
            // expected
        }
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

        // Scanner(ReadableByteChannel)
        try {
            s = new Scanner((ReadableByteChannel) null);
            fail("Should throw NullPointerException");
        } catch (NullPointerException e) {
            // expected
        }

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

        // Scanner(ReadableByteChannel = null, Charset = null)
        try {
            s = new Scanner((ReadableByteChannel) null, null);
            fail("Should throw NullPointerException");
        } catch (NullPointerException e) {
            // expected
        }

        // Scanner(ReadableByteChannel = null, Charset = invalid)
        try {
            s = new Scanner((ReadableByteChannel) null, "invalid");
            fail("Should throw NullPointerException");
        } catch (NullPointerException e) {
            // expected
        }

        // Scanner(ReadableByteChannel, Charset = null)
        try {
            s = new Scanner(fc, null);
            fail("Should throw IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            // expected
        }
        // TODO: test if the specified charset is used.
    }

    /**
     * @tests java.util.Scanner#Scanner(String)
     */
    public void test_ConstructorLjava_lang_String() {
        s = new Scanner("test string");
        assertNotNull(s);
        s.close();

        // Scanner(String)
        try {
            s = new Scanner((String) null);
            fail("Should throw NullPointerException");
        } catch (NullPointerException e) {
            // expected
        }
    }

    /**
     * @tests java.util.Scanner#close()
     */
    public void test_close() throws IOException {
        File tmpFile = File.createTempFile("TestFileForScanner", ".tmp");
        FileOutputStream fos = new FileOutputStream(tmpFile);
        FileChannel fc = fos.getChannel();
        s = new Scanner(fc);

        // Write out a int before the scanner is closed, should be OK.
        fos.write(12);

        s.close();
        assertFalse(fc.isOpen());

        // Write out a int after the scanner is closed, IOException should be
        // thrown out.
        try {
            fos.write(12);
        } catch (IOException e) {
            // expected
        }

        s.close(); // no exception should be thrown
        assertTrue(tmpFile.delete());
    }

    /**
     * @tests java.util.Scanner#ioException()
     */
    public void test_ioException() throws IOException {
        MockCloseable mc = new MockCloseable();
        s = new Scanner(mc);
        assertNull(s.ioException()); // No operation, no exception

        s.close(); // IOException should be cached
        assertNotNull(s.ioException());
        assertTrue(s.ioException() instanceof IOException);
    }

    /**
     * @tests java.util.Scanner#delimiter()
     */
    public void test_delimiter() {
        s = new Scanner("test");
        Pattern pattern = s.delimiter();
        assertEquals("\\p{javaWhitespace}+", pattern.toString());
    }

    /**
     * @tests java.util.Scanner#useDelimiter(Pattern)
     */
    public void test_useDelimiter_LPattern() {
        s = new Scanner("test");
        s.useDelimiter(Pattern.compile("\\w+"));
        assertEquals("\\w+", s.delimiter().toString());

        s = new Scanner("test");
        s.useDelimiter((Pattern) null);
        assertNull(s.delimiter());
    }

    /**
     * @tests java.util.Scanner#useDelimiter(String)
     */
    public void test_useDelimiter_String() {
        s = new Scanner("test");
        try {
            s.useDelimiter((String) null);
            fail("Should throw NullPointerException");
        } catch (NullPointerException e) {
            // expected
        }

        s = new Scanner("test");
        s.useDelimiter("\\w+");
        assertEquals("\\w+", s.delimiter().toString());
    }

    /**
     * @tests java.util.Scanner#locale()
     */
    public void test_locale() {
        s = new Scanner("test");
        assertEquals(Locale.getDefault(), s.locale());
    }

    /**
     * @tests java.util.Scanner#useLocale(Locale)
     */
    public void test_useLocale_LLocale() {
        s = new Scanner("test");
        try {
            s.useLocale(null);
            fail("Should throw NullPointerException");
        } catch (NullPointerException e) {
            // expected
        }

        s.useLocale(new Locale("test", "test"));
        assertEquals(new Locale("test", "test"), s.locale());
    }

    /**
     * @tests java.util.Scanner#radix()
     */
    public void test_radix() {
        s = new Scanner("test");
        assertEquals(10, s.radix());
    }

    /**
     * @tests java.util.Scanner#useRadix()
     */
    public void test_useRadix_I() {
        s = new Scanner("test");
        try {
            s.useRadix(Character.MIN_RADIX - 1);
            fail("Should throw IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            // expected
        }
        try {
            s.useRadix(Character.MAX_RADIX + 1);
            fail("Should throw IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            // expected
        }
        s.useRadix(11);
        assertEquals(11, s.radix());
    }

    /**
     * @throws IOException
     * @tests java.util.Scanner#next()
     */
    public void test_next() throws IOException {
        // use special delimiter
        s = new Scanner("1**2").useDelimiter("\\*");
        assertEquals("1", s.next());
        assertEquals("", s.next());
        assertEquals("2", s.next());

        s = new Scanner("word( )test( )").useDelimiter("\\( \\)");
        assertEquals("word", s.next());
        assertEquals("test", s.next());

        s = new Scanner("? next  ").useDelimiter("( )");
        assertEquals("?", s.next());
        assertEquals("next", s.next());
        assertEquals("", s.next());

        s = new Scanner("word1 word2  ");
        assertEquals("word1", s.next());
        assertEquals("word2", s.next());
        // test boundary case
        try {
            s.next();
            fail("should throw NoSuchElementException");
        } catch (NoSuchElementException e) {
            // Expected
        }

        // just delimiter exists in this scanner
        s = new Scanner(" ");
        try {
            s.next();
            fail("Should throw NoSuchElementException");
        } catch (NoSuchElementException e) {
            // Expected
        }

        // nothing exists in this scanner
        s = new Scanner("");
        try {
            s.next();
            fail("Should throw NoSuchElementException");
        } catch (NoSuchElementException e) {
            // Expected
        }

        // no delimiter exites in this scanner
        s = new Scanner("test");
        assertEquals("test", s.next());

        // input resourse starts with delimiter
        s = new Scanner("  test");
        assertEquals("test", s.next());

        // input resource ends with delimiter
        s = new Scanner("  test  ");
        assertEquals("test", s.next());

        // What if a sentence can not be read in all in once.
        StringBuilder longSentence = new StringBuilder(1025);
        for (int i = 0; i <= 10; i++) {
            longSentence.append(" ");
        }
        for (int i = 11; i <= 1025; i++) {
            longSentence.append("a");
        }
        s = new Scanner(longSentence.toString());
        assertEquals(longSentence.toString().trim(), s.next());

        s = new Scanner(" test test");
        assertEquals("test", s.next());
        assertEquals("test", s.next());

        // What if use a delimiter of length 0.
        s = new Scanner("test\ntest").useDelimiter(Pattern.compile("^",
                Pattern.MULTILINE));
        assertEquals("test\n", s.next());
        assertEquals("test", s.next());

        s = new Scanner("\ntest\ntest").useDelimiter(Pattern.compile("$",
                Pattern.MULTILINE));
        assertEquals("\ntest", s.next());
        assertEquals("\ntest", s.next());

        // test socket inputStream
        // Harmony uses 1024 as default buffer size,
        // what if the leading delimiter is larger than 1023
        for (int i = 0; i < 1024; i++) {
            os.write(" ".getBytes());
        }
        os.write("  1 2 ".getBytes());
        s = new Scanner(client);
        assertEquals("1", s.next());
        assertEquals("2", s.next());
        os.write("  1 2".getBytes());
        serverSocket.close();
        assertEquals("1", s.next());
        assertEquals("2", s.next());
        try {
            s.next();
            fail("should throw NoSuchElementException");
        } catch (NoSuchElementException e) {
            // Expected
        }

    }
    
    /**
     * @throws IOException
     * @tests java.util.Scanner#next(Pattern)
     */
    public void test_nextLPattern() throws IOException {
        Pattern pattern;
        s = new Scanner("aab*2*").useDelimiter("\\*");
        pattern = Pattern.compile("a*b");
        assertEquals("aab", s.next(pattern));
        try {
            s.next(pattern);
            fail("should throw InputMismatchException");
        } catch (InputMismatchException e) {
            // Expected
        }

        s = new Scanner("word ? ");
        pattern = Pattern.compile("\\w+");
        assertEquals("word", s.next(pattern));
        try {
            s.next(pattern);
            fail("should throw InputMismatchException");
        } catch (InputMismatchException e) {
            // Expected
        }

        s = new Scanner("word1 word2  ");
        pattern = Pattern.compile("\\w+");
        assertEquals("word1", s.next(pattern));
        assertEquals("word2", s.next(pattern));
        // test boundary case
        try {
            s.next(pattern);
            fail("should throw NoSuchElementException");
        } catch (NoSuchElementException e) {
            // Expected
        }

        // test socket inputStream

        os.write("aab 2".getBytes());
        serverSocket.close();

        s = new Scanner(client);
        pattern = Pattern.compile("a*b");
        assertEquals("aab", s.next(pattern));
        try {
            s.next(pattern);
            fail("should throw InputMismatchException");
        } catch (InputMismatchException e) {
            // Expected
        }
    }

    /**
     * @throws IOException
     * @tests java.util.Scanner#next(String)
     */
    public void test_nextLString() throws IOException {
        s = new Scanner("b*a*").useDelimiter("\\*");
        assertEquals("b", s.next("a*b"));
        try {
            s.next("a*b");
            fail("should throw InputMismatchException");
        } catch (InputMismatchException e) {
            // Expected
        }

        s = new Scanner("word ? ");
        assertEquals("word", s.next("\\w+"));
        try {
            s.next("\\w+");
            fail("should throw InputMismatchException");
        } catch (InputMismatchException e) {
            // Expected
        }

        s = new Scanner("word1 next  ");
        assertEquals("word1", s.next("\\w+"));
        assertEquals("next", s.next("\\w+"));
        // test boundary case
        try {
            s.next("\\w+");
            fail("should throw NoSuchElementException");
        } catch (NoSuchElementException e) {
            // Expected
        }

        // test socket inputStream
        os.write("aab 2".getBytes());
        serverSocket.close();

        s = new Scanner(client);
        assertEquals("aab", s.next("a*b"));
        try {
            s.next("a*b");
            fail("should throw InputMismatchException");
        } catch (InputMismatchException e) {
            // Expected
        }
    }
    
    public void setUp() throws Exception {
        super.setUp();

        server = new ServerSocket(0);
        address = new InetSocketAddress("localhost", server.getLocalPort());

        client = SocketChannel.open();
        client.connect(address);
        serverSocket = server.accept();

        os = serverSocket.getOutputStream();
    }

    public void tearDown() throws Exception {
        super.tearDown();

        try {
            serverSocket.close();
        } catch (Exception e) {

        }
        try {
            client.close();
        } catch (Exception e) {
            // do nothing
        }
        try {
            server.close();
        } catch (Exception e) {
            // do nothing
        }
    }
}
