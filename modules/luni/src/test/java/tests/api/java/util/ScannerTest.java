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
import java.util.regex.MatchResult;
import java.util.regex.Pattern;

import junit.framework.TestCase;

public class ScannerTest extends TestCase {

    private Scanner s;

    private ServerSocket server;

    private SocketAddress address;

    private SocketChannel client;

    private Socket serverSocket;

    private OutputStream os;

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
     * @tests java.util.Scanner#remove()
     */
    public void test_remove() {
        s = new Scanner("aab*b*").useDelimiter("\\*");
        try {
            s.remove();
            fail("should throw UnsupportedOperationException");
        } catch (UnsupportedOperationException e) {
            //Expected
        }
    }

    /**
     * @tests java.util.Scanner#match()
     */
    public void test_match() {
        MatchResult result ;
        s = new Scanner("1 2 ");
        try {
            s.match();
            fail("should throw IllegalStateException");
        } catch (IllegalStateException e) {
            // Expected
        }
        assertEquals("1", s.next());
        assertEquals("2", s.next());
        result = s.match();
        assertEquals(2, result.start());
        assertEquals(3, result.end());
        assertEquals(2, result.start(0));
        assertEquals(3, result.end(0));
        assertEquals("2", result.group());
        assertEquals("2", result.group(0));
        assertEquals(0, result.groupCount());
        try {
            result.start(1);
            fail("should throw IndexOutOfBoundsException");
        } catch (IndexOutOfBoundsException e) {
            // Expected
        }
        try {
            s.next();
            fail("should throw NoSuchElementException");
        } catch (NoSuchElementException e) {
            // Expected
        }
        try {
            s.match();
            fail("should throw IllegalStateException");
        } catch (IllegalStateException e) {
            // Expected
        }
        
        s = new Scanner("True faLse");
        try {
            s.match();
            fail("should throw IllegalStateException");
        } catch (IllegalStateException e) {
            // Expected
        }
        assertEquals(true, s.nextBoolean());
        result = s.match();
        assertEquals(0, result.start());
        assertEquals(4, result.end());
        assertEquals(0, result.start(0));
        assertEquals(4, result.end(0));
        assertEquals("True", result.group());
        assertEquals(0, result.groupCount());
        assertEquals(false, s.nextBoolean());
        try {
            s.nextBoolean();
            fail("should throw NoSuchElementException");
        } catch (NoSuchElementException e) {
            // Expected
        }
        try {
            s.match();
            fail("should throw IllegalStateException");
        } catch (IllegalStateException e) {
            // Expected
        }
        
        s = new Scanner("True faLse");
        assertEquals(true, s.nextBoolean());
        result = s.match();
        assertEquals(0, result.start());
        assertEquals(4, result.end());
        assertEquals(0, result.start(0));
        assertEquals(4, result.end(0));
        assertEquals("True", result.group());
        assertEquals(0, result.groupCount());
        s.close();
        try {
            s.nextBoolean();
            fail("should throw IllegalStateException");
        } catch (IllegalStateException e) {
            // Expected
        }
        result = s.match();
        assertEquals(0, result.start());
        assertEquals(4, result.end());
        assertEquals(0, result.start(0));
        assertEquals(4, result.end(0));
        assertEquals("True", result.group());
        assertEquals(0, result.groupCount());
        
        s = new Scanner("True fase");
        assertEquals(true, s.nextBoolean());
        assertEquals(0, result.groupCount());
        try {
            s.nextBoolean();
            fail("Should throw InputMismatchException");
        } catch (InputMismatchException e) {
            // expected
        }
        try {
            s.match();
            fail("should throw IllegalStateException");
        } catch (IllegalStateException e) {
            // Expected
        }
        
        s = new Scanner("True fase");
        assertEquals(true, s.nextBoolean());
        try {
            s.next((Pattern)null);
            fail("Should throw NullPointerException");
        } catch (NullPointerException e) {
            // Expected
        }
        result = s.match();
        assertEquals(0, result.start());
        assertEquals(4, result.end());
        assertEquals(0, result.start(0));
        assertEquals(4, result.end(0));
        assertEquals("True", result.group());
        assertEquals(0, result.groupCount());
        
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

        s = new Scanner(" \t 1 \t 2").useDelimiter("\\s*");
        assertEquals("1", s.next());
        assertEquals("2", s.next());
        try {
            s.next();
            fail("should throw NoSuchElementException");
        } catch (NoSuchElementException e) {
            // Expected
        }
        
        s = new Scanner("a").useDelimiter("a?");
        try {
            s.next();
            fail("should throw NoSuchElementException");
        } catch (NoSuchElementException e) {
            // Expected
        }
        
        s = new Scanner("aa").useDelimiter("a?");
        assertEquals("", s.next());
        try {
            s.next();
            fail("should throw NoSuchElementException");
        } catch (NoSuchElementException e) {
            // Expected
        }
        

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

        // no delimiter exists in this scanner
        s = new Scanner("test");
        assertEquals("test", s.next());

        // input resourse starts with delimiter
        s = new Scanner("  test");
        assertEquals("test", s.next());

        // input resource ends with delimiter
        s = new Scanner("  test  ");
        assertEquals("test", s.next());

        // Harmony uses 1024 as default buffer size,
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
        
        s = new Scanner("").useDelimiter(Pattern.compile("^",
                Pattern.MULTILINE));
        try {
            s.next();
            fail("should throw NoSuchElementException");
        } catch (NoSuchElementException e) {
            // Expected
        }
        
        s = new Scanner("").useDelimiter(Pattern.compile("^*",
                Pattern.MULTILINE));
        try {
            s.next();
            fail("should throw NoSuchElementException");
        } catch (NoSuchElementException e) {
            // Expected
        }

        s = new Scanner("test\ntest").useDelimiter(Pattern.compile("^*",
                Pattern.MULTILINE));
        assertEquals("t", s.next());
        assertEquals("e", s.next());

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
    
    /**
     * @throws IOException
     * @tests java.util.Scanner#nextBoolean()
     */
    public void test_nextBoolean() throws IOException {
        // case insensitive
        s = new Scanner("TRue");
        assertTrue(s.nextBoolean());

        s = new Scanner("tRue false");
        assertTrue(s.nextBoolean());
        assertFalse(s.nextBoolean());
        try {
            s.nextBoolean();
            fail("Should throw NoSuchElementException");
        } catch (NoSuchElementException e) {
            // Expected
        }

        s = new Scanner("true1");
        try {
            s.nextBoolean();
            fail("Should throw InputMismatchException");
        } catch (InputMismatchException e) {
            // Expected
        }

        try {
            s = new Scanner("");
            s.nextBoolean();
            fail("Should throw NoSuchElementException");
        } catch (NoSuchElementException e) {
            // Expected
        }

        // test socket inputStream
        os.write("true false".getBytes());
        serverSocket.close();

        s = new Scanner(client);
        assertTrue(s.nextBoolean());
        assertFalse(s.nextBoolean());

        // ues '*' as delimiter
        s = new Scanner("true**false").useDelimiter("\\*");
        assertTrue(s.nextBoolean());
        try {
            s.nextBoolean();
            fail("should throw NoSuchElementException");
        } catch (NoSuchElementException e) {
            // Expected
        }

        s = new Scanner("false( )").useDelimiter("\\( \\)");
        assertFalse(s.nextBoolean());

    }
    
    /**
     * @throws IOException
     * @tests java.util.Scanner#nextInt(int)
     */
    public void test_nextIntI() throws IOException {
        s = new Scanner("123 456");
        assertEquals(123, s.nextInt(10));
        assertEquals(456, s.nextInt(10));
        try {
            s.nextInt(10);
            fail("Should throw NoSuchElementException");
        } catch (NoSuchElementException e) {
            // Expected
        }

        // If the radix is different from 10
        s = new Scanner("123 456");
        assertEquals(38, s.nextInt(5));
        try {
            s.nextInt(5);
            fail("Should throw InputMismatchException");
        } catch (InputMismatchException e) {
            // Expected
        }

        // If the number is out of range
        s = new Scanner("123456789123456789123456789123456789");
        try {
            s.nextInt(10);
            fail("Should throw InputMismatchException");
        } catch (InputMismatchException e) {
            // Expected
        }

        /*
         * Different locale can only recognize corresponding locale sensitive
         * string. ',' is used in many locales as group separator.
         */
        s = new Scanner("23,456 23,456");
        s.useLocale(Locale.GERMANY);
        try {
            s.nextInt(10);
            fail("Should throw InputMismatchException");
        } catch (InputMismatchException e) {
            // expected
        }
        s.useLocale(Locale.ENGLISH);
        // If exception is thrown out, input will not be advanced.
        assertEquals(23456, s.nextInt(10));
        assertEquals(23456, s.nextInt(10));

        /*
         * ''' is used in many locales as group separator.
         */
        s = new Scanner("23'456 23'456");
        s.useLocale(Locale.GERMANY);
        try {
            s.nextInt(10);
            fail("Should throw InputMismatchException");
        } catch (InputMismatchException e) {
            // expected
        }
        s.useLocale(new Locale("it", "CH"));
        // If exception is thrown out, input will not be advanced.
        assertEquals(23456, s.nextInt(10));
        assertEquals(23456, s.nextInt(10));

        /*
         * The input string has Arabic-Indic digits.
         */
        s = new Scanner("1\u06602 1\u06662");
        assertEquals(102, s.nextInt(10));
        try {
            s.nextInt(5);
            fail("Should throw InputMismatchException");
        } catch (InputMismatchException e) {
            // Expected
        }
        assertEquals(162, s.nextInt(10));

        /*
         * '.' is used in many locales as group separator. The input string
         * has Arabic-Indic digits .
         */
        s = new Scanner("23.45\u0666 23.456");
        s.useLocale(Locale.CHINESE);
        try {
            s.nextInt(10);
            fail("Should throw InputMismatchException");
        } catch (InputMismatchException e) {
            // expected
        }
        s.useLocale(Locale.GERMANY);
        // If exception is thrown out, input will not be advanced.
        assertEquals(23456, s.nextInt(10));
        assertEquals(23456, s.nextInt(10));

        // The input string starts with zero
        s = new Scanner("03,456");
        s.useLocale(Locale.ENGLISH);
        try {
            s.nextInt(10);
            fail("Should throw InputMismatchException");
        } catch (InputMismatchException e) {
            // expected
        }

        s = new Scanner("03456");
        assertEquals(3456, s.nextInt(10));

        s = new Scanner("\u06603,456");
        s.useLocale(Locale.ENGLISH);
        assertEquals(3456, s.nextInt(10));

        s = new Scanner("E3456");
        assertEquals(930902, s.nextInt(16));
        // The following test case fails on RI, because RI does not support
        // letter as leading digit
        s = new Scanner("E3,456");
        s.useLocale(Locale.ENGLISH);
        assertEquals(930902, s.nextInt(16));

        /*
         * There are 3 types of zero digit in all locales, '0' '\u0966' '\u0e50'
         * respectively, but they are not differentiated.
         */
        s = new Scanner("12300");
        s.useLocale(Locale.CHINESE);
        assertEquals(12300, s.nextInt(10));

        s = new Scanner("123\u0966\u0966");
        s.useLocale(Locale.CHINESE);
        assertEquals(12300, s.nextInt(10));
        
        s = new Scanner("123\u0e50\u0e50");
        s.useLocale(Locale.CHINESE);
        assertEquals(12300, s.nextInt(10));

        /*
         * There are three types of negative prefix all in all. '' '-' '(' There
         * are three types of negative suffix all in all. '' '-' ')' '(' and ')'
         * must be used togethor. Prefix '-' and suffix '-' must be used 
         * exclusively.
         */

        /*
         * According to Integer regular expression: Integer :: = ( [-+]? (*
         * Numeral ) ) | LocalPositivePrefix Numeral LocalPositiveSuffix |
         * LocalNegativePrefix Numeral LocalNegativeSuffix 123- should be
         * recognized by scanner with locale ar_AE, (123) shouble be recognized
         * by scanner with locale mk_MK. But this is not the case on RI.
         */
        s = new Scanner("-123 123- -123-");
        s.useLocale(new Locale("ar", "AE"));
        assertEquals(-123, s.nextInt(10));
        // The following test case fails on RI
        assertEquals(-123, s.nextInt(10));
        try {
            s.nextInt(10);
            fail("Should throw InputMismatchException");
        } catch (InputMismatchException e) {
            // expected
        }

        s = new Scanner("-123 123- (123)");
        s.useLocale(new Locale("mk", "MK"));
        assertEquals(-123, s.nextInt(10));
        try {
            s.nextInt();
            fail("Should throw InputMismatchException");
        } catch (InputMismatchException e) {
            // expected
        }
        // Skip the un-recognizable token 123-.
        assertEquals("123-", s.next());
        // The following test case fails on RI
        assertEquals(-123, s.nextInt(10));

        // If the parameter radix is illegal, the following test cases fail on
        // RI
        try {
            s.nextInt(Character.MIN_RADIX - 1);
            fail("Should throw IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            // Expected
        }
        try {
            s.nextInt(Character.MAX_RADIX + 1);
            fail("Should throw IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            // Expected
        }
    }

    /**
     * @throws IOException
     * @tests java.util.Scanner#nextInt()
     */
    public void test_nextInt() throws IOException {
        s = new Scanner("123 456");
        assertEquals(123, s.nextInt());
        assertEquals(456, s.nextInt());
        try {
            s.nextInt();
            fail("Should throw NoSuchElementException");
        } catch (NoSuchElementException e) {
            // Expected
        }

        // If the radix is different from 10
        s = new Scanner("123 456");
        s.useRadix(5);
        assertEquals(38, s.nextInt());
        try {
            s.nextInt();
            fail("Should throw InputMismatchException");
        } catch (InputMismatchException e) {
            // Expected
        }

        // If the number is out of range
        s = new Scanner("123456789123456789123456789123456789");
        try {
            s.nextInt();
            fail("Should throw InputMismatchException");
        } catch (InputMismatchException e) {
            // Expected
        }

        /*
         * Different locale can only recognize corresponding locale sensitive
         * string. ',' is used in many locales as group separator.
         */
        s = new Scanner("23,456 23,456");
        s.useLocale(Locale.GERMANY);
        try {
            s.nextInt();
            fail("Should throw InputMismatchException");
        } catch (InputMismatchException e) {
            // expected
        }
        s.useLocale(Locale.ENGLISH);
        // If exception is thrown out, input will not be advanced.
        assertEquals(23456, s.nextInt());
        assertEquals(23456, s.nextInt());

        /*
         * ''' is used in many locales as group separator.
         */
        s = new Scanner("23'456 23'456");
        s.useLocale(Locale.GERMANY);
        try {
            s.nextInt();
            fail("Should throw InputMismatchException");
        } catch (InputMismatchException e) {
            // expected
        }
        s.useLocale(new Locale("it", "CH"));
        // If exception is thrown out, input will not be advanced.
        assertEquals(23456, s.nextInt());
        assertEquals(23456, s.nextInt());

        /*
         * The input string has Arabic-Indic digits.
         */
        s = new Scanner("1\u06602 1\u06662");
        assertEquals(102, s.nextInt());
        s.useRadix(5);
        try {
            s.nextInt();
            fail("Should throw InputMismatchException");
        } catch (InputMismatchException e) {
            // Expected
        }
        s.useRadix(10);
        assertEquals(162, s.nextInt());

        /*
         * '.' is used in many locales as group separator. The input string
         * has Arabic-Indic digits .
         */
        s = new Scanner("23.45\u0666 23.456");
        s.useLocale(Locale.CHINESE);
        try {
            s.nextInt();
            fail("Should throw InputMismatchException");
        } catch (InputMismatchException e) {
            // expected
        }
        s.useLocale(Locale.GERMANY);
        // If exception is thrown out, input will not be advanced.
        assertEquals(23456, s.nextInt());
        assertEquals(23456, s.nextInt());

        // The input string starts with zero
        s = new Scanner("03,456");
        s.useLocale(Locale.ENGLISH);
        try {
            s.nextInt();
            fail("Should throw InputMismatchException");
        } catch (InputMismatchException e) {
            // expected
        }

        s = new Scanner("03456");
        assertEquals(3456, s.nextInt());

        s = new Scanner("\u06603,456");
        s.useLocale(Locale.ENGLISH);
        assertEquals(3456, s.nextInt());

        s = new Scanner("E3456");
        s.useRadix(16);
        assertEquals(930902, s.nextInt());

        // The following test case fails on RI, because RI does not support
        // letter as leading digit
        s = new Scanner("E3,456");
        s.useLocale(Locale.ENGLISH);
        s.useRadix(16);
        assertEquals(930902, s.nextInt());

        /*
         * There are 3 types of zero digit in all locales, '0' '\u0966' '\u0e50'
         * respectively, but they are not differentiated.
         */
        s = new Scanner("12300");
        s.useLocale(Locale.CHINESE);
        assertEquals(12300, s.nextInt());

        s = new Scanner("123\u0966\u0966");
        s.useLocale(Locale.CHINESE);
        assertEquals(12300, s.nextInt());
        
        s = new Scanner("123\u0e50\u0e50");
        s.useLocale(Locale.CHINESE);
        assertEquals(12300, s.nextInt());

        /*
         * There are three types of negative prefix all in all. '' '-' '(' There
         * are three types of negative suffix all in all. '' '-' ')' '(' and ')'
         * must be used togethor. Prefix '-' and suffix '-' must be used
         * exclusively.
         */

        /*
         * According to Integer regular expression: Integer :: = ( [-+]? (*
         * Numeral ) ) | LocalPositivePrefix Numeral LocalPositiveSuffix |
         * LocalNegativePrefix Numeral LocalNegativeSuffix 123- should be
         * recognized by scanner with locale ar_AE, (123) shouble be recognized
         * by scanner with locale mk_MK. But this is not the case on RI.
         */
        s = new Scanner("-123 123- -123-");
        s.useLocale(new Locale("ar", "AE"));
        assertEquals(-123, s.nextInt());
        // The following test case fails on RI
        assertEquals(-123, s.nextInt());
        try {
            s.nextInt();
            fail("Should throw InputMismatchException");
        } catch (InputMismatchException e) {
            // expected
        }

        s = new Scanner("-123 123- (123)");
        s.useLocale(new Locale("mk", "MK"));
        assertEquals(-123, s.nextInt());
        try {
            s.nextInt();
            fail("Should throw InputMismatchException");
        } catch (InputMismatchException e) {
            // expected
        }
        // Skip the un-recognizable token 123-.
        assertEquals("123-", s.next());
        // The following test case fails on RI
        assertEquals(-123, s.nextInt());
    }
    
    /**
     * @throws IOException
     * @tests java.util.Scanner#nextFloat()
     */
    public void test_nextFloat() throws IOException {
        s = new Scanner("123 45\u0666. 123.4 .123 ");
        s.useLocale(Locale.ENGLISH);
        assertEquals((float)123.0, s.nextFloat());
        assertEquals((float)456.0, s.nextFloat());
        assertEquals((float)123.4, s.nextFloat());
        assertEquals((float)0.123, s.nextFloat());
        try {
            s.nextFloat();
            fail("Should throw NoSuchElementException");
        } catch (NoSuchElementException e) {
            // Expected
        }

        s = new Scanner("+123.4 -456.7 123,456.789 0.1\u06623,4");
        s.useLocale(Locale.ENGLISH);
        assertEquals((float)123.4, s.nextFloat());
        assertEquals((float)-456.7, s.nextFloat());
        assertEquals((float)123456.789, s.nextFloat());
        try {
            s.nextFloat();
            fail("Should throw InputMismatchException");
        } catch (InputMismatchException e) {
            // Expected
        }

        // Scientific notation
        s = new Scanner("+123.4E10 -456.7e+12 123,456.789E-10");
        s.useLocale(Locale.ENGLISH);
        assertEquals((float)1.234E12, s.nextFloat());
        assertEquals((float)-4.567E14, s.nextFloat());
        assertEquals((float)1.23456789E-5, s.nextFloat());

        s = new Scanner("NaN Infinity -Infinity");
        assertEquals(Float.NaN, s.nextFloat());
        assertEquals(Float.POSITIVE_INFINITY, s.nextFloat());
        assertEquals(Float.NEGATIVE_INFINITY, s.nextFloat());

        String str=String.valueOf(Float.MAX_VALUE*2);
        s=new Scanner(str);
        assertEquals(Float.POSITIVE_INFINITY,s.nextFloat());
        
        /*
         * Different locale can only recognize corresponding locale sensitive
         * string. ',' is used in many locales as group separator.
         */
        s = new Scanner("23,456 23,456");
        s.useLocale(Locale.ENGLISH);
        assertEquals((float)23456.0, s.nextFloat());
        s.useLocale(Locale.GERMANY);
        assertEquals((float)23.456, s.nextFloat());

        s = new Scanner("23.456 23.456");
        s.useLocale(Locale.ENGLISH);
        assertEquals((float)23.456, s.nextFloat());
        s.useLocale(Locale.GERMANY);
        assertEquals((float)23456.0, s.nextFloat());

        s = new Scanner("23,456.7 23.456,7");
        s.useLocale(Locale.ENGLISH);
        assertEquals((float)23456.7, s.nextFloat());
        s.useLocale(Locale.GERMANY);
        assertEquals((float)23456.7, s.nextFloat());

        s = new Scanner("-123.4 123.4- -123.4-");
        s.useLocale(new Locale("ar", "AE"));
        assertEquals((float)-123.4, s.nextFloat());
        //The following test case fails on RI
        assertEquals((float)-123.4, s.nextFloat());
        try {
            s.nextFloat();
            fail("Should throw InputMismatchException");
        } catch (InputMismatchException e) {
            // Expected
        }

        s = new Scanner("(123) 123- -123");
        s.useLocale(new Locale("mk", "MK"));
        assertEquals((float)-123.0, s.nextFloat());
        try {
            s.nextFloat();
            fail("Should throw InputMismatchException");
        } catch (InputMismatchException e) {
            // Expected
        }
        // Skip the un-recognizable token 123-.
        assertEquals("123-", s.next());
        assertEquals((float)-123.0, s.nextFloat());

    }
    
    /**
     * @throws IOException
     * @tests java.util.Scanner#hasNext()
     */
    public void test_hasNext() throws IOException {
        s = new Scanner("1##2").useDelimiter("\\#");
        assertTrue(s.hasNext());
        assertEquals("1", s.next());
        assertEquals("", s.next());
        assertEquals("2", s.next());
        assertFalse(s.hasNext());
        s.close();
        try {
            s.hasNext();
            fail("should throw IllegalStateException");
        } catch (IllegalStateException e) {
            // expected
        }

        s = new Scanner("1( )2( )").useDelimiter("\\( \\)");
        assertTrue(s.hasNext());
        assertTrue(s.hasNext());
        assertEquals("1", s.next());
        assertEquals("2", s.next());

        s = new Scanner("1 2  ").useDelimiter("( )");
        assertEquals("1", s.next());
        assertEquals("2", s.next());
        assertTrue(s.hasNext());
        assertEquals("", s.next());

        s = new Scanner("1\n2  ");
        assertEquals("1", s.next());
        assertTrue(s.hasNext());
        assertEquals("2", s.next());
        assertFalse(s.hasNext());
        // test boundary case
        try {
            s.next();
            fail("should throw NoSuchElementException");
        } catch (NoSuchElementException e) {
            // Expected
        }

        s = new Scanner("1'\n'2  ");
        assertEquals("1'", s.next());
        assertTrue(s.hasNext());
        assertEquals("'2", s.next());
        assertFalse(s.hasNext());
        // test boundary case
        try {
            s.next();
            fail("should throw NoSuchElementException");
        } catch (NoSuchElementException e) {
            // Expected
        }

        s = new Scanner("  ");
        assertFalse(s.hasNext());

        // test socket inputStream

        os.write("1 2".getBytes());
        serverSocket.close();

        s = new Scanner(client);
        assertEquals("1", s.next());
        assertTrue(s.hasNext());
        assertEquals("2", s.next());
        assertFalse(s.hasNext());
        try {
            s.next();
            fail("should throw NoSuchElementException");
        } catch (NoSuchElementException e) {
            // Expected
        }
    }
    
    /**
     * @throws IOException
     * @tests java.util.Scanner#hasNext(Pattern)
     */
    public void test_hasNextLPattern() throws IOException {
        Pattern pattern;
        s = new Scanner("aab@2@abb@").useDelimiter("\\@");
        pattern = Pattern.compile("a*b");
        assertTrue(s.hasNext(pattern));
        assertEquals("aab", s.next(pattern));
        assertFalse(s.hasNext(pattern));
        try {
            s.next(pattern);
            fail("should throw InputMismatchException");
        } catch (InputMismatchException e) {
            // Expected
        }

        s = new Scanner("word ? ");
        pattern = Pattern.compile("\\w+");
        assertTrue(s.hasNext(pattern));
        assertEquals("word", s.next(pattern));
        assertFalse(s.hasNext(pattern));
        try {
            s.next(pattern);
            fail("should throw InputMismatchException");
        } catch (InputMismatchException e) {
            // Expected
        }

        s = new Scanner("word1 WorD2  ");
        pattern = Pattern.compile("\\w+");
        assertTrue(s.hasNext(pattern));
        assertEquals("word1", s.next(pattern));
        assertTrue(s.hasNext(pattern));
        assertEquals("WorD2", s.next(pattern));
        assertFalse(s.hasNext(pattern));
        try {
            s.next(pattern);
            fail("should throw NoSuchElementException");
        } catch (NoSuchElementException e) {
            // Expected
        }

        s = new Scanner("word1 WorD2  ");
        pattern = Pattern.compile("\\w+");
        try {
            s.hasNext((Pattern) null);
            fail("Should throw NullPointerException");
        } catch (NullPointerException e) {
            // expected
        }
        s.close();
        try {
            s.hasNext(pattern);
            fail("should throw IllegalStateException");
        } catch (IllegalStateException e) {
            // expected
        }
        
        // test socket inputStream
        os.write("aab b".getBytes());
        serverSocket.close();

        s = new Scanner(client);
        pattern = Pattern.compile("a+b");
        assertTrue(s.hasNext(pattern));
        assertEquals("aab", s.next(pattern));
        assertFalse(s.hasNext(pattern));
        try {
            s.next(pattern);
            fail("should throw InputMismatchException");
        } catch (InputMismatchException e) {
            // Expected
        }
    }
    
    /**
     * @throws IOException
     * @tests java.util.Scanner#hasNext(String)
     */
    public void test_hasNextLString() throws IOException {
        s = new Scanner("aab@2@abb@").useDelimiter("\\@");
        try {
            s.hasNext((String)null);
            fail("Should throw NullPointerException");
        } catch (NullPointerException e) {
            // expected
        }
        
        s = new Scanner("aab*b*").useDelimiter("\\*");
        assertTrue(s.hasNext("a+b"));
        assertEquals("aab", s.next("a+b"));
        assertFalse(s.hasNext("a+b"));
        try {
            s.next("a+b");
            fail("should throw InputMismatchException");
        } catch (InputMismatchException e) {
            // Expected
        }
        s.close();
        try {
            s.hasNext("a+b");
            fail("should throw IllegalStateException");
        } catch (IllegalStateException e) {
            // expected
        }

        s = new Scanner("WORD ? ");
        assertTrue(s.hasNext("\\w+"));
        assertEquals("WORD", s.next("\\w+"));
        assertFalse(s.hasNext("\\w+"));
        try {
            s.next("\\w+");
            fail("should throw InputMismatchException");
        } catch (InputMismatchException e) {
            // Expected
        }

        s = new Scanner("word1 word2  ");
        assertEquals("word1", s.next("\\w+"));
        assertEquals("word2", s.next("\\w+"));
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
        assertTrue(s.hasNext("a*b"));
        assertEquals("aab", s.next("a*b"));
        assertFalse(s.hasNext("a*b"));
        try {
            s.next("a*b");
            fail("should throw InputMismatchException");
        } catch (InputMismatchException e) {
            // Expected
        }
    }
    
    /**
     * @throws IOException
     * @tests java.util.Scanner#hasNextBoolean()
     */
    public void test_hasNextBoolean() throws IOException {

        s = new Scanner("TRue");
        assertTrue(s.hasNextBoolean());
        assertTrue(s.nextBoolean());

        s = new Scanner("tRue false");
        assertTrue(s.hasNextBoolean());
        assertTrue(s.nextBoolean());
        assertTrue(s.hasNextBoolean());
        assertFalse(s.nextBoolean());

        s = new Scanner("");
        assertFalse(s.hasNextBoolean());

        // test socket inputStream

        os.write("true false ".getBytes());
        serverSocket.close();

        s = new Scanner(client);
        assertTrue(s.hasNextBoolean());
        assertTrue(s.nextBoolean());

        // ues '*' as delimiter
        s = new Scanner("true**false").useDelimiter("\\*");
        assertTrue(s.hasNextBoolean());
        assertTrue(s.nextBoolean());
        assertFalse(s.hasNextBoolean());
        try {
            s.nextBoolean();
            fail("should throw NoSuchElementException");
        } catch (NoSuchElementException e) {
            // Expected
        }

        s = new Scanner("false( )").useDelimiter("\\( \\)");
        assertTrue(s.hasNextBoolean());
        assertFalse(s.nextBoolean());
        assertFalse(s.hasNextBoolean());

    }
    
    /**
     * @throws IOException
     * @tests java.util.Scanner#hasNextInt(int)
     */
    public void test_hasNextIntI() throws IOException {
        s = new Scanner("123 456");
        assertEquals(123, s.nextInt(10));
        assertTrue(s.hasNextInt(10));
        assertEquals(456, s.nextInt(10));
        assertFalse(s.hasNextInt(10));
        try {
            s.nextInt(10);
            fail("Should throw NoSuchElementException");
        } catch (NoSuchElementException e) {
            // Expected
        }

        // If the radix is different from 10
        s = new Scanner("123 456");
        assertTrue(s.hasNextInt(5));
        assertEquals(38, s.nextInt(5));
        assertFalse(s.hasNextInt(5));
        try {
            s.nextInt(5);
            fail("Should throw InputMismatchException");
        } catch (InputMismatchException e) {
            // Expected
        }

        // If the number is out of range
        s = new Scanner("123456789123456789123456789123456789");
        assertFalse(s.hasNextInt(10));

        /*
         * Different locale can only recognize corresponding locale sensitive
         * string. ',' is used in many locales as group separator.
         */
        s = new Scanner("23,456");
        s.useLocale(Locale.GERMANY);
        assertFalse(s.hasNextInt(10));
        s.useLocale(Locale.ENGLISH);
        // If exception is thrown out, input will not be advanced.
        assertTrue(s.hasNextInt(10));
        /*
         * ''' is used in many locales as group separator.
         */
        s = new Scanner("23'456");
        s.useLocale(Locale.GERMANY);
        assertFalse(s.hasNextInt(10));
        s.useLocale(new Locale("it", "CH"));
        // If exception is thrown out, input will not be advanced.
        assertTrue(s.hasNextInt(10));

        /*
         * The input string has Arabic-Indic digits.
         */
        s = new Scanner("1\u06662");
        assertTrue(s.hasNextInt(10));
        assertFalse(s.hasNextInt(5));

        /*
         * '.' is used in many locales as group separator. The input string
         * has Arabic-Indic digits .
         */
        s = new Scanner("23.45\u0666");
        s.useLocale(Locale.CHINESE);
        assertFalse(s.hasNextInt(10));
        try {
            s.nextInt(10);
            fail("Should throw InputMismatchException");
        } catch (InputMismatchException e) {
            // expected
        }
        s.useLocale(Locale.GERMANY);
        assertTrue(s.hasNextInt(10));

        // The input string starts with zero
        s = new Scanner("03,456");
        s.useLocale(Locale.ENGLISH);
        assertFalse(s.hasNextInt(10));
        try {
            s.nextInt(10);
            fail("Should throw InputMismatchException");
        } catch (InputMismatchException e) {
            // expected
        }

        s = new Scanner("03456");
        assertTrue(s.hasNextInt(10));
        assertEquals(3456, s.nextInt(10));

        s = new Scanner("\u06603,456");
        s.useLocale(Locale.ENGLISH);
        assertTrue(s.hasNextInt(10));
        assertEquals(3456, s.nextInt(10));

        s = new Scanner("E3456");
        assertTrue(s.hasNextInt(16));
        assertEquals(930902, s.nextInt(16));
        // The following test case fails on RI, because RI does not support
        // letter as leading digit
        s = new Scanner("E3,456");
        s.useLocale(Locale.ENGLISH);
        assertTrue(s.hasNextInt(16));
        assertEquals(930902, s.nextInt(16));

        // If parameter radix is illegal, the following test case fails on RI
        try {
            s.hasNextInt(Character.MIN_RADIX - 1);
            fail("Should throw IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            // Expected
        }

        /*
         * There are 3 types of zero digit in all locales, '0' '\u0966' '\u0e50'
         * respectively, but they are not differentiated.
         */
        s = new Scanner("12300");
        s.useLocale(Locale.CHINESE);
        assertTrue(s.hasNextInt(10));
        assertEquals(12300, s.nextInt(10));

        s = new Scanner("123\u0966\u0966");
        s.useLocale(Locale.CHINESE);
        assertTrue(s.hasNextInt(10));
        assertEquals(12300, s.nextInt(10));

        s = new Scanner("123\u0e50\u0e50");
        s.useLocale(Locale.CHINESE);
        assertTrue(s.hasNextInt(10));
        assertEquals(12300, s.nextInt(10));

        /*
         * There are three types of negative prefix all in all. '' '-' '(' There
         * are three types of negative suffix all in all. '' '-' ')' '(' and ')'
         * must be used togethor. Prefix '-' and suffix '-' must be used 
         * exclusively.
         */

        /*
         * According to Integer regular expression: Integer :: = ( [-+]? (*
         * Numeral ) ) | LocalPositivePrefix Numeral LocalPositiveSuffix |
         * LocalNegativePrefix Numeral LocalNegativeSuffix 123- should be
         * recognized by scanner with locale ar_AE, (123) shouble be recognized
         * by scanner with locale mk_MK. But this is not the case on RI.
         */
        s = new Scanner("-123 123- -123-");
        s.useLocale(new Locale("ar", "AE"));
        assertTrue(s.hasNextInt(10));
        assertEquals(-123, s.nextInt(10));
        // The following test case fails on RI
        assertTrue(s.hasNextInt(10));
        assertEquals(-123, s.nextInt(10));
        assertFalse(s.hasNextInt(10));
        try {
            s.nextInt(10);
            fail("Should throw InputMismatchException");
        } catch (InputMismatchException e) {
            // expected
        }

        s = new Scanner("-123 123- (123)");
        s.useLocale(new Locale("mk", "MK"));
        assertTrue(s.hasNextInt(10));
        assertEquals(-123, s.nextInt(10));
        assertFalse(s.hasNextInt(10));
        try {
            s.nextInt();
            fail("Should throw InputMismatchException");
        } catch (InputMismatchException e) {
            // expected
        }
        // Skip the un-recognizable token 123-.
        assertEquals("123-", s.next());
        // The following test case fails on RI
        assertTrue(s.hasNextInt(10));
        assertEquals(-123, s.nextInt(10));
    }

    /**
     * @throws IOException
     * @tests java.util.Scanner#hasNextInt()
     */
    public void test_hasNextInt() throws IOException {
        s = new Scanner("123 456");
        assertTrue(s.hasNextInt());
        assertEquals(123, s.nextInt());
        assertEquals(456, s.nextInt());
        assertFalse(s.hasNextInt());
        try {
            s.nextInt();
            fail("Should throw NoSuchElementException");
        } catch (NoSuchElementException e) {
            // Expected
        }

        // If the radix is different from 10
        s = new Scanner("123 456");
        s.useRadix(5);
        assertTrue(s.hasNextInt());
        assertEquals(38, s.nextInt());
        assertFalse(s.hasNextInt());
        try {
            s.nextInt();
            fail("Should throw InputMismatchException");
        } catch (InputMismatchException e) {
            // Expected
        }

        // If the number is out of range
        s = new Scanner("123456789123456789123456789123456789");
        assertFalse(s.hasNextInt());

        /*
         * Different locale can only recognize corresponding locale sensitive
         * string. ',' is used in many locales as group separator.
         */
        s = new Scanner("23,456");
        s.useLocale(Locale.GERMANY);
        assertFalse(s.hasNextInt());
        s.useLocale(Locale.ENGLISH);
        assertTrue(s.hasNextInt());

        /*
         * ''' is used in many locales as group separator.
         */
        s = new Scanner("23'456");
        s.useLocale(Locale.GERMANY);
        assertFalse(s.hasNextInt());
        s.useLocale(new Locale("it", "CH"));
        assertTrue(s.hasNextInt());

        /*
         * The input string has Arabic-Indic digits.
         */
        s = new Scanner("1\u06662");
        s.useRadix(5);
        assertFalse(s.hasNextInt());

        /*
         * '.' is used in many locales as group separator. The input string
         * has Arabic-Indic digits .
         */
        s = new Scanner("23.45\u0666");
        s.useLocale(Locale.CHINESE);
        assertFalse(s.hasNextInt());
        s.useLocale(Locale.GERMANY);
        assertTrue(s.hasNextInt());

        // The input string starts with zero
        s = new Scanner("03,456");
        s.useLocale(Locale.ENGLISH);
        assertFalse(s.hasNextInt());
        try {
            s.nextInt();
            fail("Should throw InputMismatchException");
        } catch (InputMismatchException e) {
            // expected
        }

        s = new Scanner("03456");
        assertTrue(s.hasNextInt());
        assertEquals(3456, s.nextInt());

        s = new Scanner("\u06603,456");
        s.useLocale(Locale.ENGLISH);
        assertEquals(3456, s.nextInt());

        s = new Scanner("E3456");
        s.useRadix(16);
        assertTrue(s.hasNextInt());
        assertEquals(930902, s.nextInt());

        // The following test case fails on RI, because RI does not support
        // letter as leading digit
        s = new Scanner("E3,456");
        s.useLocale(Locale.ENGLISH);
        s.useRadix(16);
        assertTrue(s.hasNextInt());
        assertEquals(930902, s.nextInt());

        /*
         * There are 3 types of zero digit in all locales, '0' '\u0966' '\u0e50'
         * respectively, but they are not differentiated.
         */
        s = new Scanner("12300");
        s.useLocale(Locale.CHINESE);
        assertTrue(s.hasNextInt());
        assertEquals(12300, s.nextInt());

        s = new Scanner("123\u0966\u0966");
        s.useLocale(Locale.CHINESE);
        assertTrue(s.hasNextInt());
        assertEquals(12300, s.nextInt());

        s = new Scanner("123\u0e50\u0e50");
        s.useLocale(Locale.CHINESE);
        assertTrue(s.hasNextInt());
        assertEquals(12300, s.nextInt());

        /*
         * There are three types of negative prefix all in all. '' '-' '(' There
         * are three types of negative suffix all in all. '' '-' ')' '(' and ')'
         * must be used togethor. Prefix '-' and suffix '-' must be used 
         * exclusively.
         */

        /*
         * According to Integer regular expression: Integer :: = ( [-+]? (*
         * Numeral ) ) | LocalPositivePrefix Numeral LocalPositiveSuffix |
         * LocalNegativePrefix Numeral LocalNegativeSuffix 123- should be
         * recognized by scanner with locale ar_AE, (123) shouble be recognized
         * by scanner with locale mk_MK. But this is not the case on RI.
         */
        s = new Scanner("-123 123- -123-");
        s.useLocale(new Locale("ar", "AE"));
        assertTrue(s.hasNextInt());
        assertEquals(-123, s.nextInt());
        // The following test case fails on RI
        assertTrue(s.hasNextInt());
        assertEquals(-123, s.nextInt());
        assertFalse(s.hasNextInt());
        try {
            s.nextInt();
            fail("Should throw InputMismatchException");
        } catch (InputMismatchException e) {
            // expected
        }

        s = new Scanner("-123 123- (123)");
        s.useLocale(new Locale("mk", "MK"));
        assertTrue(s.hasNextInt());
        assertEquals(-123, s.nextInt());
        try {
            s.nextInt();
            fail("Should throw InputMismatchException");
        } catch (InputMismatchException e) {
            // expected
        }
        // Skip the un-recognizable token 123-.
        assertEquals("123-", s.next());
        // The following test case fails on RI
        assertTrue(s.hasNextInt());
        assertEquals(-123, s.nextInt());
    }
    
    /**
     * @throws IOException
     * @tests java.util.Scanner#hasNextFloat()
     */
    public void test_hasNextFloat() throws IOException {
        s = new Scanner("123 45\u0666. 123.4 .123 ");
        s.useLocale(Locale.ENGLISH);
        assertTrue(s.hasNextFloat());
        assertEquals((float)123.0, s.nextFloat());
        assertTrue(s.hasNextFloat());
        assertEquals((float)456.0, s.nextFloat());
        assertTrue(s.hasNextFloat());
        assertEquals((float)123.4, s.nextFloat());
        assertTrue(s.hasNextFloat());
        assertEquals((float)0.123, s.nextFloat());
        assertFalse(s.hasNextFloat());
        try {
            s.nextFloat();
            fail("Should throw NoSuchElementException");
        } catch (NoSuchElementException e) {
            // Expected
        }

        s = new Scanner("+123.4 -456.7 123,456.789 0.1\u06623,4");
        s.useLocale(Locale.ENGLISH);
        assertTrue(s.hasNextFloat());
        assertEquals((float)123.4, s.nextFloat());
        assertTrue(s.hasNextFloat());
        assertEquals((float)-456.7, s.nextFloat());
        assertTrue(s.hasNextFloat());
        assertEquals((float)123456.789, s.nextFloat());
        assertFalse(s.hasNextFloat());
        try {
            s.nextFloat();
            fail("Should throw InputMismatchException");
        } catch (InputMismatchException e) {
            // Expected
        }

        // Scientific notation
        s = new Scanner("+123.4E10 -456.7e+12 123,456.789E-10");
        s.useLocale(Locale.ENGLISH);
        assertTrue(s.hasNextFloat());
        assertEquals((float)1.234E12, s.nextFloat());
        assertTrue(s.hasNextFloat());
        assertEquals((float)-4.567E14, s.nextFloat());
        assertTrue(s.hasNextFloat());
        assertEquals((float)1.23456789E-5, s.nextFloat());

        s = new Scanner("NaN Infinity -Infinity");
        assertTrue(s.hasNextFloat());
        assertEquals(Float.NaN, s.nextFloat());
        assertTrue(s.hasNextFloat());
        assertEquals(Float.POSITIVE_INFINITY, s.nextFloat());
        assertTrue(s.hasNextFloat());
        assertEquals(Float.NEGATIVE_INFINITY, s.nextFloat());

        String str=String.valueOf(Float.MAX_VALUE*2);
        s=new Scanner(str);
        assertTrue(s.hasNextFloat());
        assertEquals(Float.POSITIVE_INFINITY,s.nextFloat());
        
        /*
         * Different locale can only recognize corresponding locale sensitive
         * string. ',' is used in many locales as group separator.
         */
        s = new Scanner("23,456 23,456");
        s.useLocale(Locale.ENGLISH);
        assertTrue(s.hasNextFloat());
        assertEquals((float)23456.0, s.nextFloat());
        s.useLocale(Locale.GERMANY);
        assertTrue(s.hasNextFloat());
        assertEquals((float)23.456, s.nextFloat());

        s = new Scanner("23.456 23.456");
        s.useLocale(Locale.ENGLISH);
        assertTrue(s.hasNextFloat());
        assertEquals((float)23.456, s.nextFloat());
        s.useLocale(Locale.GERMANY);
        assertTrue(s.hasNextFloat());
        assertEquals((float)23456.0, s.nextFloat());

        s = new Scanner("23,456.7 23.456,7");
        s.useLocale(Locale.ENGLISH);
        assertTrue(s.hasNextFloat());
        assertEquals((float)23456.7, s.nextFloat());
        s.useLocale(Locale.GERMANY);
        assertTrue(s.hasNextFloat());
        assertEquals((float)23456.7, s.nextFloat());

        s = new Scanner("-123.4 123.4- -123.4-");
        s.useLocale(new Locale("ar", "AE"));
        assertTrue(s.hasNextFloat());
        assertEquals((float)-123.4, s.nextFloat());
        //The following test case fails on RI
        assertTrue(s.hasNextFloat());
        assertEquals((float)-123.4, s.nextFloat());
        try {
            s.nextFloat();
            fail("Should throw InputMismatchException");
        } catch (InputMismatchException e) {
            // Expected
        }

        s = new Scanner("(123) 123- -123");
        s.useLocale(new Locale("mk", "MK"));
        assertTrue(s.hasNextFloat());
        assertEquals((float)-123.0, s.nextFloat());
        assertFalse(s.hasNextFloat());
        try {
            s.nextFloat();
            fail("Should throw InputMismatchException");
        } catch (InputMismatchException e) {
            // Expected
        }
        // Skip the un-recognizable token 123-.
        assertEquals("123-", s.next());
        assertTrue(s.hasNextFloat());
        assertEquals((float)-123.0, s.nextFloat());

    }
    
    private static class MockStringReader extends StringReader {

        public MockStringReader(String param) {
            super(param);
        }

        public int read(CharBuffer target) throws IOException {
            target.append('t');
            target.append('e');
            target.append('s');
            target.append('t');
            throw new IOException();
        }

    }
    
    /**
     * @tests java.util.Scanner#findWithinHorizon(Pattern, int)
     */
    public void test_findWithinHorizon_LPatternI(){

        // This method searches through the input up to the specified search
        // horizon(exclusive).
        s = new Scanner("123test");
        String result = s.findWithinHorizon(Pattern.compile("\\p{Lower}"), 5);
        assertEquals("t", result);
        MatchResult mresult = s.match();
        assertEquals(3, mresult.start());
        assertEquals(4, mresult.end());

        s = new Scanner("12345test1234test next");
        /*
         * If the pattern is found the scanner advances past the input that
         * matched and returns the string that matched the pattern.
         */
        result = s.findWithinHorizon(Pattern.compile("\\p{Digit}+"), 2);
        assertEquals("12", result);
        mresult = s.match();
        assertEquals(0, mresult.start());
        assertEquals(2, mresult.end());
        // Postion is now pointing at the bar. "12|345test1234test next"

        result = s.findWithinHorizon(Pattern.compile("\\p{Digit}+"), 6);
        assertEquals("345", result);

        mresult = s.match();
        assertEquals(2, mresult.start());
        assertEquals(5, mresult.end());
        // Postion is now pointing at the bar. "12345|test1234test next"

        // If no such pattern is detected then the null is returned and the
        // scanner's position remains unchanged.
        result = s.findWithinHorizon(Pattern.compile("\\p{Digit}+"), 3);
        assertNull(result);

        try {
            s.match();
            fail("Should throw IllegalStateException");
        } catch (IllegalStateException e) {
            // expected
        }
        assertEquals("345", mresult.group());
        assertEquals(2, mresult.start());
        assertEquals(5, mresult.end());
        // Postion is now still pointing at the bar. "12345|test1234test next"

        // If horizon is 0, then the horizon is ignored and this method
        // continues to search through the input looking for the specified
        // pattern without bound.
        result = s.findWithinHorizon(Pattern.compile("\\p{Digit}+"), 0);
        mresult = s.match();
        assertEquals(9, mresult.start());
        assertEquals(13, mresult.end());
        // Postion is now pointing at the bar. "12345test1234|test next"

        assertEquals("test", s.next());
        mresult = s.match();
        assertEquals(13, mresult.start());
        assertEquals(17, mresult.end());

        assertEquals("next", s.next());
        mresult = s.match();
        assertEquals(18, mresult.start());
        assertEquals(22, mresult.end());

        try {
            s.findWithinHorizon((Pattern) null, -1);
            fail("Should throw NullPointerException");
        } catch (NullPointerException e) {
            // expected
        }

        try {
            s.findWithinHorizon(Pattern.compile("\\p{Digit}+"), -1);
            fail("Should throw IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            // expected
        }

        s.close();
        try {
            s.findWithinHorizon((Pattern) null, -1);
            fail("Should throw IllegalStateException");
        } catch (IllegalStateException e) {
            // expected
        }

        s = new Scanner("test");
        result = s.findWithinHorizon(Pattern.compile("\\w+"), 10);
        assertEquals("test", result);

        s = new Scanner("aa\n\rb");
        String patternStr = "^(a)$";
        result = s.findWithinHorizon(Pattern.compile("a"), 5);
        assertEquals("a", result);
        mresult = s.match();
        assertEquals(0, mresult.start());
        assertEquals(1, mresult.end());

        result = s.findWithinHorizon(Pattern.compile(patternStr,
                Pattern.MULTILINE), 5);
        assertNull(result);

        try {
            mresult = s.match();
            fail("Should throw IllegalStateException");
        } catch (IllegalStateException e) {
            // expected
        }

        s = new Scanner("");
        result = s.findWithinHorizon(Pattern.compile("^"), 0);
        assertEquals("", result);
        MatchResult matchResult = s.match();
        assertEquals(0, matchResult.start());
        assertEquals(0, matchResult.end());

        result = s.findWithinHorizon(Pattern.compile("$"), 0);
        assertEquals("", result);
        matchResult = s.match();
        assertEquals(0, matchResult.start());
        assertEquals(0, matchResult.end());

        s = new Scanner("1 fish 2 fish red fish blue fish");
        result = s.findWithinHorizon(Pattern
                .compile("(\\d+) fish (\\d+) fish (\\w+) fish (\\w+)"), 10);
        assertNull(result);

        try {
            mresult = s.match();
            fail("Should throw IllegalStateException");
        } catch (IllegalStateException e) {
            // expected
        }
        assertEquals(0, mresult.groupCount());

        result = s.findWithinHorizon(Pattern
                .compile("(\\d+) fish (\\d+) fish (\\w+) fish (\\w+)"), 100);
        assertEquals("1 fish 2 fish red fish blue", result);
        mresult = s.match();
        assertEquals(4, mresult.groupCount());
        assertEquals("1", mresult.group(1));
        assertEquals("2", mresult.group(2));
        assertEquals("red", mresult.group(3));
        assertEquals("blue", mresult.group(4));

        s = new Scanner("test");
        s.close();
        try {
            s.findWithinHorizon(Pattern.compile("test"), 1);
            fail("Should throw IllegalStateException");
        } catch (IllegalStateException e) {
            // expected
        }

        s = new Scanner("word1 WorD2  ");
        s.close();
        try {
            s.findWithinHorizon(Pattern.compile("\\d+"), 10);
            fail("should throw IllegalStateException");
        } catch (IllegalStateException e) {
            // expected
        }

        s = new Scanner("word1 WorD2 wOrd3 ");
        Pattern pattern = Pattern.compile("\\d+");
        assertEquals("1", s.findWithinHorizon(pattern, 10));
        assertEquals("WorD2", s.next());
        assertEquals("3", s.findWithinHorizon(pattern, 15));

        // Regression test
        s = new Scanner(new MockStringReader("MockStringReader"));
        pattern = Pattern.compile("test");
        result = s.findWithinHorizon(pattern, 10);
        assertEquals("test", result);

        // Test the situation when input length is longer than buffer size.
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < 1026; i++) {
            stringBuilder.append('a');
        }
        s = new Scanner(stringBuilder.toString());
        pattern = Pattern.compile("\\p{Lower}+");
        result = s.findWithinHorizon(pattern, 1026);
        assertEquals(stringBuilder.toString(), result);

        // Test the situation when input length is longer than buffer size and
        // set horizon to buffer size.
        stringBuilder = new StringBuilder();
        for (int i = 0; i < 1026; i++) {
            stringBuilder.append('a');
        }
        s = new Scanner(stringBuilder.toString());
        pattern = Pattern.compile("\\p{Lower}+");
        result = s.findWithinHorizon(pattern, 1022);
        assertEquals(1022, result.length());
        assertEquals(stringBuilder.subSequence(0, 1022), result);

        // Test the situation, under which pattern is clipped by buffer.
        stringBuilder = new StringBuilder();
        for (int i = 0; i < 1022; i++) {
            stringBuilder.append(' ');
        }
        stringBuilder.append("bbc");
        assertEquals(1025, stringBuilder.length());
        s = new Scanner(stringBuilder.toString());
        pattern = Pattern.compile("bbc");
        result = s.findWithinHorizon(pattern, 1025);
        assertEquals(3, result.length());
        assertEquals(stringBuilder.subSequence(1022, 1025), result);

        stringBuilder = new StringBuilder();
        for (int i = 0; i < 1026; i++) {
            stringBuilder.append('a');
        }
        s = new Scanner(stringBuilder.toString());
        pattern = Pattern.compile("\\p{Lower}+");
        result = s.findWithinHorizon(pattern, 0);
        assertEquals(stringBuilder.toString(), result);
        
        stringBuilder = new StringBuilder();
        for (int i = 0; i < 10240; i++) {
            stringBuilder.append('-');
        }
        stringBuilder.replace(0, 2, "aa");
        s = new Scanner(stringBuilder.toString());
        result = s.findWithinHorizon(Pattern.compile("aa"), 0);
        assertEquals("aa", result);
        
        s = new Scanner("aaaa");
        result = s.findWithinHorizon(Pattern.compile("a*"), 0);
        assertEquals("aaaa", result);
    }
    
    protected void setUp() throws Exception {
        super.setUp();

        server = new ServerSocket(0);
        address = new InetSocketAddress("localhost", server.getLocalPort());

        client = SocketChannel.open();
        client.connect(address);
        serverSocket = server.accept();

        os = serverSocket.getOutputStream();
    }

    protected void tearDown() throws Exception {
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
