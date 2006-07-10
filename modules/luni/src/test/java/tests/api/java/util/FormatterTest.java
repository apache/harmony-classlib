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

import java.io.BufferedOutputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FilePermission;
import java.io.Flushable;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PipedOutputStream;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.nio.charset.Charset;
import java.security.Permission;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.DuplicateFormatFlagsException;
import java.util.FormatFlagsConversionMismatchException;
import java.util.Formattable;
import java.util.FormattableFlags;
import java.util.Formatter;
import java.util.FormatterClosedException;
import java.util.IllegalFormatCodePointException;
import java.util.IllegalFormatConversionException;
import java.util.IllegalFormatException;
import java.util.IllegalFormatFlagsException;
import java.util.IllegalFormatPrecisionException;
import java.util.IllegalFormatWidthException;
import java.util.Locale;
import java.util.MissingFormatArgumentException;
import java.util.MissingFormatWidthException;
import java.util.TimeZone;
import java.util.UnknownFormatConversionException;

import junit.framework.TestCase;

public class FormatterTest extends TestCase {

    class MockAppendable implements Appendable {
        public Appendable append(CharSequence arg0) throws IOException {
            return null;
        }

        public Appendable append(char arg0) throws IOException {
            return null;
        }

        public Appendable append(CharSequence arg0, int arg1, int arg2)
                throws IOException {
            return null;
        }
    }

    class MockSecurityManager extends SecurityManager {
        public void checkPermission(Permission p) {
            if (p.getActions().equals("write") && p instanceof FilePermission) {
                throw new SecurityException("Always throw security exception");
            }
        }

        public void checkPermission(Permission p, Object ctx) {
            checkPermission(p);
        }
    }

    class MockFormattable implements Formattable {
        public void formatTo(Formatter formatter, int flags, int width,
                int precision) throws IllegalFormatException {
            if ((flags & FormattableFlags.UPPERCASE) != 0) {
                formatter.format("CUSTOMIZED FORMAT FUNCTION" + " WIDTH: "
                        + width + " PRECISION: " + precision);
            } else {
                formatter.format("customized format function" + " width: "
                        + width + " precision: " + precision);
            }
        }

        public String toString() {
            return "formattable object";
        }

        public int hashCode() {
            return 0xf;
        }
    }

    class MockDestination implements Appendable, Flushable {

        private StringBuilder data = new StringBuilder();

        private boolean enabled = false;

        public Appendable append(char c) throws IOException {
            if (enabled) {
                data.append(c);
                enabled = true; // enable it after the first append
            } else {
                throw new IOException();
            }
            return this;
        }

        public Appendable append(CharSequence csq) throws IOException {
            if (enabled) {
                data.append(csq);
                enabled = true; // enable it after the first append
            } else {
                throw new IOException();
            }
            return this;
        }

        public Appendable append(CharSequence csq, int start, int end)
                throws IOException {
            if (enabled) {
                data.append(csq, start, end);
                enabled = true; // enable it after the first append
            } else {
                throw new IOException();
            }
            return this;
        }

        public void flush() throws IOException {
            throw new IOException("Always throw IOException");
        }

        public String toString() {
            return data.toString();
        }
    }

    private File notExist;

    private File fileWithContent;

    private File readOnly;

    private File secret;
    
    private TimeZone defaultTimeZone;

    /**
     * @tests java.util.Formatter#Formatter()
     */
    public void test_Constructor() {
        Formatter f = new Formatter();
        assertNotNull(f);
        assertTrue(f.out() instanceof StringBuilder);
        assertEquals(f.locale(), Locale.getDefault());
        assertNotNull(f.toString());
    }

    /**
     * @tests java.util.Formatter#Formatter(Appendable)
     */
    public void test_ConstructorLjava_lang_Appendable() {
        MockAppendable ma = new MockAppendable();
        Formatter f1 = new Formatter(ma);
        assertEquals(ma, f1.out());
        assertEquals(f1.locale(), Locale.getDefault());
        assertNotNull(f1.toString());

        Formatter f2 = new Formatter((Appendable) null);
        /*
         * If a(the input param) is null then a StringBuilder will be created
         * and the output can be attained by invoking the out() method. But RI
         * raises an error of FormatterClosedException when invoking out() or
         * toString().
         */
        Appendable sb = f2.out();
        assertTrue(sb instanceof StringBuilder);
        assertNotNull(f2.toString());
    }

    /**
     * @tests java.util.Formatter#Formatter(Locale)
     */
    public void test_ConstructorLjava_util_Locale() {
        Formatter f1 = new Formatter(Locale.FRANCE);
        assertTrue(f1.out() instanceof StringBuilder);
        assertEquals(f1.locale(), Locale.FRANCE);
        assertNotNull(f1.toString());

        Formatter f2 = new Formatter((Locale) null);
        assertNull(f2.locale());
        assertTrue(f2.out() instanceof StringBuilder);
        assertNotNull(f2.toString());
    }

    /**
     * @tests java.util.Formatter#Formatter(Appendable, Locale)
     */
    public void test_ConstructorLjava_lang_AppendableLjava_util_Locale() {
        MockAppendable ma = new MockAppendable();
        Formatter f1 = new Formatter(ma, Locale.CANADA);
        assertEquals(ma, f1.out());
        assertEquals(f1.locale(), Locale.CANADA);

        Formatter f2 = new Formatter(ma, null);
        assertNull(f2.locale());
        assertEquals(ma, f1.out());

        Formatter f3 = new Formatter(null, Locale.GERMAN);
        assertEquals(f3.locale(), Locale.GERMAN);
        assertTrue(f3.out() instanceof StringBuilder);
    }

    /**
     * @tests java.util.Formatter#Formatter(String)
     */
    public void test_ConstructorLjava_lang_String() throws IOException {
        Formatter f = null;
        try {
            f = new Formatter((String) null);
            fail("should throw NullPointerException");
        } catch (NullPointerException e1) {
            // expected
        }

        f = new Formatter(notExist.getPath());
        assertEquals(f.locale(), Locale.getDefault());
        f.close();

        f = new Formatter(fileWithContent.getPath());
        assertEquals(0, fileWithContent.length());
        f.close();

        // FIXME This exception will not be thrown out on linux.
        try {
            f = new Formatter(readOnly.getPath());
            fail("should throw FileNotFoundException");
        } catch (FileNotFoundException e) {
            // expected
        }

        SecurityManager oldsm = System.getSecurityManager();
        System.setSecurityManager(new MockSecurityManager());
        try {
            f = new Formatter(secret.getPath());
            fail("should throw SecurityException");
        } catch (SecurityException se) {
            // expected
        } finally {
            System.setSecurityManager(oldsm);
        }
    }

    /**
     * @tests java.util.Formatter#Formatter(String, String)
     */
    public void test_ConstructorLjava_lang_StringLjava_lang_String()
            throws IOException {
        Formatter f = null;
        try {
            f = new Formatter((String) null, Charset.defaultCharset().name());
            fail("should throw NullPointerException");
        } catch (NullPointerException e1) {
            // expected
        }

        try {
            f = new Formatter(notExist.getPath(), null);
            fail("should throw NullPointerException");
        } catch (NullPointerException e2) {
            // expected
        }

        f = new Formatter(notExist.getPath(), Charset.defaultCharset().name());
        assertEquals(f.locale(), Locale.getDefault());
        f.close();

        try {
            f = new Formatter(notExist.getPath(), "ISO 1111-1");
            fail("should throw UnsupportedEncodingException");
        } catch (UnsupportedEncodingException e1) {
            // expected
        }

        f = new Formatter(fileWithContent.getPath(), "UTF-16BE");
        assertEquals(0, fileWithContent.length());
        f.close();

        // FIXME This exception will not be thrown out on linux.
        try {
            f = new Formatter(readOnly.getPath(), "UTF-16BE");
            fail("should throw FileNotFoundException");
        } catch (FileNotFoundException e) {
            // expected
        }

        SecurityManager oldsm = System.getSecurityManager();
        System.setSecurityManager(new MockSecurityManager());
        try {
            f = new Formatter(secret.getPath(), "UTF-16BE");
            fail("should throw SecurityException");
        } catch (SecurityException se) {
            // expected
        } finally {
            System.setSecurityManager(oldsm);
        }
    }

    /**
     * @tests java.util.Formatter#Formatter(String, String, Locale)
     */
    public void test_ConstructorLjava_lang_StringLjava_lang_StringLjava_util_Locale()
            throws IOException {
        Formatter f = null;
        try {
            f = new Formatter((String) null, Charset.defaultCharset().name(),
                    Locale.KOREA);
            fail("should throw NullPointerException");
        } catch (NullPointerException e1) {
            // expected
        }

        try {
            f = new Formatter(notExist.getPath(), null, Locale.KOREA);
            fail("should throw NullPointerException");
        } catch (NullPointerException e2) {
            // expected
        }

        f = new Formatter(notExist.getPath(), Charset.defaultCharset().name(),
                null);
        assertNotNull(f);
        f.close();

        f = new Formatter(notExist.getPath(), Charset.defaultCharset().name(),
                Locale.KOREA);
        assertEquals(f.locale(), Locale.KOREA);
        f.close();

        try {
            f = new Formatter(notExist.getPath(), "ISO 1111-1", Locale.CHINA);
            fail("should throw UnsupportedEncodingException");
        } catch (UnsupportedEncodingException e1) {
            // expected
        }

        f = new Formatter(fileWithContent.getPath(), "UTF-16BE",
                Locale.CANADA_FRENCH);
        assertEquals(0, fileWithContent.length());
        f.close();

        try {
            f = new Formatter(readOnly.getPath(), Charset.defaultCharset()
                    .name(), Locale.ITALY);
            fail("should throw FileNotFoundException");
        } catch (FileNotFoundException e) {
            // expected
        }

        SecurityManager oldsm = System.getSecurityManager();
        System.setSecurityManager(new MockSecurityManager());
        try {
            f = new Formatter(secret.getPath(),
                    Charset.defaultCharset().name(), Locale.SIMPLIFIED_CHINESE);
            fail("should throw SecurityException");
        } catch (SecurityException se) {
            // expected
        } finally {
            System.setSecurityManager(oldsm);
        }
    }

    /**
     * @tests java.util.Formatter#Formatter(File)
     */
    public void test_ConstructorLjava_io_File() throws IOException {
        Formatter f = null;
        try {
            f = new Formatter((File) null);
            fail("should throw NullPointerException");
        } catch (NullPointerException e1) {
            // expected
        }

        f = new Formatter(notExist);
        assertEquals(f.locale(), Locale.getDefault());
        f.close();

        f = new Formatter(fileWithContent);
        assertEquals(0, fileWithContent.length());
        f.close();

        // FIXME This exception will not be thrown out on linux.
        try {
            f = new Formatter(readOnly);
            fail("should throw FileNotFoundException");
        } catch (FileNotFoundException e) {
            // expected
        }

        SecurityManager oldsm = System.getSecurityManager();
        System.setSecurityManager(new MockSecurityManager());
        try {
            f = new Formatter(secret);
            fail("should throw SecurityException");
        } catch (SecurityException se) {
            // expected
        } finally {
            System.setSecurityManager(oldsm);
        }
    }

    /**
     * @tests java.util.Formatter#Formatter(File, String)
     */
    public void test_ConstructorLjava_io_FileLjava_lang_String()
            throws IOException {
        Formatter f = null;
        try {
            f = new Formatter((File) null, Charset.defaultCharset().name());
            fail("should throw NullPointerException");
        } catch (NullPointerException e1) {
            // expected
        }

        f = new Formatter(notExist, Charset.defaultCharset().name());
        assertEquals(f.locale(), Locale.getDefault());
        f.close();

        f = new Formatter(fileWithContent, "UTF-16BE");
        assertEquals(0, fileWithContent.length());
        f.close();

        // FIXME This exception will not be thrown out on linux.
        try {
            f = new Formatter(readOnly, Charset.defaultCharset().name());
            fail("should throw FileNotFoundException");
        } catch (FileNotFoundException e) {
            // expected
        }

        SecurityManager oldsm = System.getSecurityManager();
        System.setSecurityManager(new MockSecurityManager());
        try {
            f = new Formatter(secret, Charset.defaultCharset().name());
            fail("should throw SecurityException");
        } catch (SecurityException se) {
            // expected
        } finally {
            System.setSecurityManager(oldsm);
        }

        try {
            f = new Formatter(notExist, null);
            fail("should throw NullPointerException");
        } catch (NullPointerException e2) {
            // expected
        } finally {
            if (notExist.exists()) {
                // Fail on RI on Windows, because output stream is created and
                // not closed when exception thrown
                assertTrue(notExist.delete());
            }
        }

        try {
            f = new Formatter(notExist, "ISO 1111-1");
            fail("should throw UnsupportedEncodingException");
        } catch (UnsupportedEncodingException e1) {
            // expected
        } finally {
            if (notExist.exists()) {
                // Fail on RI on Windows, because output stream is created and
                // not closed when exception thrown
                assertTrue(notExist.delete());
            }
        }
    }

    /**
     * @tests java.util.Formatter#Formatter(File, String, Locale)
     */
    public void test_ConstructorLjava_io_FileLjava_lang_StringLjava_util_Locale()
            throws IOException {
        Formatter f = null;
        try {
            f = new Formatter((File) null, Charset.defaultCharset().name(),
                    Locale.KOREA);
            fail("should throw NullPointerException");
        } catch (NullPointerException e1) {
            // expected
        }

        try {
            f = new Formatter(notExist, null, Locale.KOREA);
            fail("should throw NullPointerException");
        } catch (NullPointerException e2) {
            // expected
        }

        f = new Formatter(notExist, Charset.defaultCharset().name(), null);
        assertNotNull(f);
        f.close();

        f = new Formatter(notExist, Charset.defaultCharset().name(),
                Locale.KOREA);
        assertEquals(f.locale(), Locale.KOREA);
        f.close();

        try {
            f = new Formatter(notExist, "ISO 1111-1", Locale.CHINA);
            fail("should throw UnsupportedEncodingException");
        } catch (UnsupportedEncodingException e1) {
            // expected
        }
        f = new Formatter(fileWithContent.getPath(), "UTF-16BE",
                Locale.CANADA_FRENCH);
        assertEquals(0, fileWithContent.length());
        f.close();

        // FIXME This exception will not be thrown out on linux.
        try {
            f = new Formatter(readOnly.getPath(), Charset.defaultCharset()
                    .name(), Locale.ITALY);
            fail("should throw FileNotFoundException");
        } catch (FileNotFoundException e) {
            // expected
        }

        SecurityManager oldsm = System.getSecurityManager();
        System.setSecurityManager(new MockSecurityManager());
        try {
            f = new Formatter(secret.getPath(),
                    Charset.defaultCharset().name(), Locale.SIMPLIFIED_CHINESE);
            fail("should throw SecurityException");
        } catch (SecurityException se) {
            // expected
        } finally {
            System.setSecurityManager(oldsm);
        }
    }

    /**
     * @tests java.util.Formatter#Formatter(PrintStream)
     */
    public void test_ConstructorLjava_io_PrintStream() throws IOException {
        Formatter f = null;
        try {
            f = new Formatter((PrintStream) null);
            fail("should throw NullPointerException");
        } catch (NullPointerException e1) {
            // expected
        }

        PrintStream ps = new PrintStream(notExist, "UTF-16BE");
        f = new Formatter(ps);
        assertEquals(Locale.getDefault(), f.locale());
        f.close();
    }

    /**
     * @tests java.util.Formatter#Formatter(OutputStream)
     */
    public void test_ConstructorLjava_io_OutputStream() throws IOException {
        Formatter f = null;
        try {
            f = new Formatter((OutputStream) null);
            fail("should throw NullPointerException");
        } catch (NullPointerException e1) {
            // expected
        }

        OutputStream os = new FileOutputStream(notExist);
        f = new Formatter(os);
        assertEquals(Locale.getDefault(), f.locale());
        f.close();
    }

    /**
     * @tests java.util.Formatter#Formatter(OutputStream, String)
     */
    public void test_ConstructorLjava_io_OutputStreamLjava_lang_String()
            throws IOException {
        Formatter f = null;
        try {
            f = new Formatter((OutputStream) null, Charset.defaultCharset()
                    .name());
            fail("should throw NullPointerException");
        } catch (NullPointerException e1) {
            // expected
        }

        OutputStream os = null;
        try {
            os = new FileOutputStream(notExist);
            f = new Formatter(os, null);
            fail("should throw NullPointerException");
        } catch (NullPointerException e2) {
            // expected
        } finally {
            os.close();
        }

        try {
            os = new PipedOutputStream();
            f = new Formatter(os, "TMP-1111");
            fail("should throw UnsupportedEncodingException");
        } catch (UnsupportedEncodingException e1) {
            // expected
        } finally {
            os.close();
        }

        os = new FileOutputStream(fileWithContent);
        f = new Formatter(os, "UTF-16BE");
        assertEquals(Locale.getDefault(), f.locale());
        f.close();
    }

    /**
     * Test method for 'java.util.Formatter.Formatter(OutputStream, String,
     * Locale)
     */
    public void test_ConstructorLjava_io_OutputStreamLjava_lang_StringLjava_util_Locale()
            throws IOException {
        Formatter f = null;
        try {
            f = new Formatter((OutputStream) null, Charset.defaultCharset()
                    .name(), Locale.getDefault());
            fail("should throw NullPointerException");
        } catch (NullPointerException e1) {
            // expected
        }

        OutputStream os = null;
        try {
            os = new FileOutputStream(notExist);
            f = new Formatter(os, null, Locale.getDefault());
            fail("should throw NullPointerException");
        } catch (NullPointerException e2) {
            // expected
        } finally {
            os.close();
        }

        os = new FileOutputStream(notExist);
        f = new Formatter(os, Charset.defaultCharset().name(), null);
        f.close();

        try {
            os = new PipedOutputStream();
            f = new Formatter(os, "TMP-1111", Locale.getDefault());
            fail("should throw UnsupportedEncodingException");
        } catch (UnsupportedEncodingException e1) {
            // expected
        }

        os = new FileOutputStream(fileWithContent);
        f = new Formatter(os, "UTF-16BE", Locale.ENGLISH);
        assertEquals(Locale.ENGLISH, f.locale());
        f.close();
    }

    /**
     * @tests java.util.Formatter#locale()
     */
    public void test_locale() {
        Formatter f = null;
        f = new Formatter((Locale) null);
        assertNull(f.locale());

        f.close();
        try {
            f.locale();
            fail("should throw FormatterClosedException");
        } catch (FormatterClosedException e) {
            // expected
        }
    }

    /**
     * @tests java.util.Formatter#out()
     */
    public void test_out() {
        Formatter f = null;
        f = new Formatter();
        assertNotNull(f.out());
        assertTrue(f.out() instanceof StringBuilder);
        f.close();
        try {
            f.out();
            fail("should throw FormatterClosedException");
        } catch (FormatterClosedException e) {
            // expected
        }

    }

    /**
     * @tests java.util.Formatter#flush()
     */
    public void test_flush() throws IOException {
        Formatter f = null;
        f = new Formatter(notExist);
        assertTrue(f instanceof Flushable);
        f.close();
        try {
            f.flush();
            fail("should throw FormatterClosedException");
        } catch (FormatterClosedException e) {
            // expected
        }

        f = new Formatter();
        // For destination that does not implement Flushable
        // No exception should be thrown
        f.flush();
    }

    /**
     * @tests java.util.Formatter#close()
     */
    public void test_close() throws IOException {
        Formatter f = new Formatter(notExist);
        assertTrue(f instanceof Closeable);
        f.close();
        // close next time will not throw exception
        f.close();
        assertNull(f.ioException());
    }

    /**
     * @tests java.util.Formatter#toString()
     */
    public void test_toString() {
        Formatter f = new Formatter();
        assertNotNull(f.toString());
        assertEquals(f.out().toString(), f.toString());
        f.close();
        try {
            f.toString();
            fail("should throw FormatterClosedException");
        } catch (FormatterClosedException e) {
            // expected
        }
    }

    /**
     * @tests java.util.Formatter#ioException()
     */
    public void test_ioException() throws IOException {
        Formatter f = null;
        f = new Formatter(new MockDestination());
        assertNull(f.ioException());
        f.flush();
        assertNotNull(f.ioException());
        f.close();

        MockDestination md = new MockDestination();
        f = new Formatter(md);
        f.format("%s%s", "1", "2");
        // format stop working after IOException
        assertNotNull(f.ioException());
        assertEquals("", f.toString());
    }

    /**
     * @tests java.util.Formatter#format(String, Object...) for null parameter
     */
    public void test_formatLjava_lang_String$Ljava_lang_Object_null() {
        Formatter f = new Formatter();
        try {
            f.format((String) null, "parameter");
            fail("should throw NullPointerException");
        } catch (NullPointerException e) {
            // expected
        }

        f = new Formatter();
        f.format("hello", (Object[]) null);
        assertEquals("hello", f.toString());
    }

    /**
     * @tests java.util.Formatter#format(String, Object...) for argument index
     */
    public void test_formatLjava_lang_String$Ljava_lang_Object_ArgIndex() {
        Formatter formatter = new Formatter(Locale.US);
        formatter.format("%1$s%2$s%3$s%4$s%5$s%6$s%7$s%8$s%9$s%11$s%10$s", "1",
                "2", "3", "4", "5", "6", "7", "8", "9", "10", "11");
        assertEquals("1234567891110", formatter.toString());

        formatter = new Formatter(Locale.JAPAN);
        formatter.format("%0$s", "hello");
        assertEquals("hello", formatter.toString());

        try {
            formatter = new Formatter(Locale.US);
            formatter.format("%-1$s", "1", "2");
            fail("should throw UnknownFormatConversionException");
        } catch (UnknownFormatConversionException e) {
            // expected
        }

        try {
            formatter = new Formatter(Locale.US);
            formatter.format("%$s", "hello", "2");
            fail("should throw UnknownFormatConversionException");
        } catch (UnknownFormatConversionException e) {
            // expected
        }
        
        try {
            Formatter f = new Formatter(Locale.US);
            f.format("%", "string");
            fail("should throw UnknownFormatConversionException");
        } catch (UnknownFormatConversionException e) {
            // expected
        }       

        formatter = new Formatter(Locale.FRANCE);
        formatter.format("%1$s%2$s%3$s%4$s%5$s%6$s%7$s%8$s%<s%s%s%<s", "1",
                "2", "3", "4", "5", "6", "7", "8", "9", "10", "11");
        assertEquals("123456788122", formatter.toString());

        formatter = new Formatter(Locale.FRANCE);
        formatter.format(
                "xx%1$s22%2$s%s%<s%5$s%<s&%7$h%2$s%8$s%<s%s%s%<ssuffix", "1",
                "2", "3", "4", "5", "6", 7, "8", "9", "10", "11");
        assertEquals("xx12221155&7288233suffix", formatter.toString());

        try {
            formatter.format("%<s", "hello");
            fail("should throw MissingFormatArgumentException");
        } catch (MissingFormatArgumentException e) {
            // expected
        }

        formatter = new Formatter(Locale.US);
        try {
            formatter.format("%123$s", "hello");
            fail("should throw MissingFormatArgumentException");
        } catch (MissingFormatArgumentException e) {
            // expected
        }

        formatter = new Formatter(Locale.US);
        try {
            // 2147483648 is the value of Integer.MAX_VALUE + 1
            formatter.format("%2147483648$s", "hello");
            fail("should throw MissingFormatArgumentException");
        } catch (MissingFormatArgumentException e) {
            // expected
        }

        try {
            // 2147483647 is the value of Integer.MAX_VALUE
            formatter.format("%2147483647$s", "hello");
            fail("should throw MissingFormatArgumentException");
        } catch (MissingFormatArgumentException e) {
            // expected
        }

        formatter = new Formatter(Locale.US);
        try {
            formatter.format("%s%s", "hello");
            fail("should throw MissingFormatArgumentException");
        } catch (MissingFormatArgumentException e) {
            // expected
        }

        formatter = new Formatter(Locale.US);
        formatter.format("$100", 100);
        assertEquals("$100", formatter.toString());

        formatter = new Formatter(Locale.UK);
        formatter.format("%01$s", "string");
        assertEquals("string", formatter.toString());
    }

    /**
     * @tests java.util.Formatter#format(String, Object...) for width
     */
    public void test_formatLjava_lang_String$Ljava_lang_Object_Width() {
        Formatter f = new Formatter(Locale.US);
        f.format("%1$8s", "1");
        assertEquals("       1", f.toString());

        f = new Formatter(Locale.US);
        f.format("%1$-1%", "string");
        assertEquals("%", f.toString());

        f = new Formatter(Locale.ITALY);
        // 2147483648 is the value of Integer.MAX_VALUE + 1
        f.format("%2147483648s", "string");
        assertEquals("string", f.toString());

        // the value of Integer.MAX_VALUE will allocate about 4G bytes of
        // memory.
        // It may cause OutOfMemoryError, so this value is not tested
    }

    /**
     * @tests java.util.Formatter#format(String, Object...) for precision
     */
    public void test_formatLjava_lang_String$Ljava_lang_Object_Precision() {
        Formatter f = new Formatter(Locale.US);
        f.format("%.5s", "123456");
        assertEquals("12345", f.toString());

        f = new Formatter(Locale.US);
        // 2147483648 is the value of Integer.MAX_VALUE + 1
        f.format("%.2147483648s", "...");
        assertEquals("...", f.toString());

        // the value of Integer.MAX_VALUE will allocate about 4G bytes of
        // memory.
        // It may cause OutOfMemoryError, so this value is not tested

        f = new Formatter(Locale.US);
        f.format("%10.0b", Boolean.TRUE);
        assertEquals("          ", f.toString());

        f = new Formatter(Locale.US);
        f.format("%10.01s", "hello");
        assertEquals("         h", f.toString());

        try {
            f = new Formatter(Locale.US);
            f.format("%.s", "hello", "2");
            fail("should throw UnknownFormatConversionException");
        } catch (UnknownFormatConversionException e) {
            // expected
        }

        try {
            f = new Formatter(Locale.US);
            f.format("%.-5s", "123456");
            fail("should throw UnknownFormatConversionException");
        } catch (UnknownFormatConversionException e) {
            // expected
        }

        try {
            f = new Formatter(Locale.US);
            f.format("%1.s", "hello", "2");
            fail("should throw UnknownFormatConversionException");
        } catch (UnknownFormatConversionException e) {
            // expected
        }

        f = new Formatter(Locale.US);
        f.format("%5.1s", "hello");
        assertEquals("    h", f.toString());

        f = new Formatter(Locale.FRANCE);
        f.format("%.0s", "hello", "2");
        assertEquals("", f.toString());
    }

    /**
     * @tests java.util.Formatter#format(String, Object...) for line sperator
     */
    public void test_formatLjava_lang_String$Ljava_lang_Object_LineSeparator() {
        Formatter f = null;

        String oldSeparator = System.getProperty("line.separator");
        System.setProperty("line.separator", "!\n");

        f = new Formatter(Locale.US);
        f.format("%1$n", 1);
        assertEquals("!\n", f.toString());

        f = new Formatter(Locale.KOREAN);
        f.format("head%1$n%2$n", 1, new Date());
        assertEquals("head!\n!\n", f.toString());

        f = new Formatter(Locale.US);
        f.format("%n%s", "hello");
        assertEquals("!\nhello", f.toString());

        System.setProperty("line.separator", oldSeparator);

        f = new Formatter(Locale.US);
        try {
            f.format("%-n");
            fail("should throw IllegalFormatFlagsException: %-n");
        } catch (IllegalFormatFlagsException e) {
            // expected
        }
        try {
            f.format("%+n");
            fail("should throw IllegalFormatFlagsException: %+n");
        } catch (IllegalFormatFlagsException e) {
            // expected
        }
        try {
            f.format("%#n");
            fail("should throw IllegalFormatFlagsException: %#n");
        } catch (IllegalFormatFlagsException e) {
            // expected
        }
        try {
            f.format("% n");
            fail("should throw IllegalFormatFlagsException: % n");
        } catch (IllegalFormatFlagsException e) {
            // expected
        }
        try {
            f.format("%0n");
            fail("should throw IllegalFormatFlagsException: %0n");
        } catch (IllegalFormatFlagsException e) {
            // expected
        }
        try {
            f.format("%,n");
            fail("should throw IllegalFormatFlagsException: %,n");
        } catch (IllegalFormatFlagsException e) {
            // expected
        }
        try {
            f.format("%(n");
            fail("should throw IllegalFormatFlagsException: %(n");
        } catch (IllegalFormatFlagsException e) {
            // expected
        }

        f = new Formatter(Locale.US);
        try {
            f.format("%4n");
            fail("should throw IllegalFormatWidthException");
        } catch (IllegalFormatWidthException e) {
            // expected
        }

        f = new Formatter(Locale.US);
        try {
            f.format("%-4n");
            fail("should throw IllegalFormatWidthException");
        } catch (IllegalFormatWidthException e) {
            // expected
        }

        f = new Formatter(Locale.US);
        try {
            f.format("%.9n");
            fail("should throw IllegalFormatPrecisionException");
        } catch (IllegalFormatPrecisionException e) {
            // expected
        }

        f = new Formatter(Locale.US);
        try {
            f.format("%5.9n");
            fail("should throw IllegalFormatPrecisionException");
        } catch (IllegalFormatPrecisionException e) {
            // expected
        }
        
        System.setProperty("line.separator", oldSeparator);
    }

    /**
     * @tests java.util.Formatter#format(String, Object...) for percent
     */
    public void test_formatLjava_lang_String$Ljava_lang_Object_Percent() {
        Formatter f = null;

        f = new Formatter(Locale.ENGLISH);
        f.format("%1$%", 100);
        assertEquals("%", f.toString());

        f = new Formatter(Locale.CHINA);
        f.format("%1$%%%", "hello", new Object());
        assertEquals("%%", f.toString());

        f = new Formatter(Locale.CHINA);
        f.format("%%%s", "hello");
        assertEquals("%hello", f.toString());

        f = new Formatter(Locale.US);
        try {
            f.format("%.9%");
            fail("should throw IllegalFormatPrecisionException");
        } catch (IllegalFormatPrecisionException e) {
            // expected
        }

        f = new Formatter(Locale.US);
        try {
            f.format("%5.9%");
            fail("should throw IllegalFormatPrecisionException");
        } catch (IllegalFormatPrecisionException e) {
            // expected
        }

        f = new Formatter(Locale.US);
        final String[] illFlags = { "%+%", "%#%", "% %", "%0%", "%,%", "%(%" };
        for (int i = 0; i < illFlags.length; i++) {
            try {
                f.format(illFlags[i]);
                fail("should throw FormatFlagsConversionMismatchException: "
                        + illFlags[i]);
                /*
                 * error on RI, throw IllegalFormatFlagsException specification
                 * says FormatFlagsConversionMismatchException should be thrown
                 */
            } catch (FormatFlagsConversionMismatchException e) {
                // expected
            }
        }

        f = new Formatter(Locale.KOREAN);
        f.format("%4%", 1);
        /*
         * fail on RI the output string should be right justified by appending
         * spaces till the whole string is 4 chars width.
         */
        assertEquals("   %", f.toString());

        f = new Formatter(Locale.US);
        f.format("%-4%", 100);
        /*
         * fail on RI, throw UnknownFormatConversionException the output string
         * should be left justified by appending spaces till the whole string is
         * 4 chars width.
         */
        assertEquals("%   ", f.toString());
    }

    /**
     * @tests java.util.Formatter#format(String, Object...) for flag
     */
    public void test_formatLjava_lang_String$Ljava_lang_Object_Flag() {
        Formatter f = new Formatter(Locale.US);
        try {
            f.format("%1$-#-8s", "something");
            fail("should throw DuplicateFormatFlagsException");
        } catch (DuplicateFormatFlagsException e) {
            // expected
        }

        final char[] chars = { '-', '#', '+', ' ', '0', ',', '(', '%', '<' };
        Arrays.sort(chars);
        f = new Formatter(Locale.US);
        for (char i = 0; i <= 256; i++) {
            // test 8 bit character
            if (Arrays.binarySearch(chars, i) >= 0 || Character.isDigit(i)
                    || Character.isLetter(i)) {
                // Do not test 0-9, a-z, A-Z and characters in the chars array.
                // They are characters used as flags, width or conversions
                continue;
            }
            try {
                f.format("%" + i + "s", 1);
                fail("should throw UnknownFormatConversionException");
            } catch (UnknownFormatConversionException e) {
                // expected
            }
        }
    }

    /**
     * @tests java.util.Formatter#format(String, Object...) for general
     *        conversion b/B
     */
    public void test_format_LString$LObject_GeneralConversionB() {
        final Object[][] triple = { 
                { Boolean.FALSE,                "%3.2b",  " fa", },
                { Boolean.FALSE,                "%-4.6b", "false", },
                { Boolean.FALSE,                "%.2b",   "fa", }, 
                { Boolean.TRUE,                 "%3.2b",  " tr", },
                { Boolean.TRUE,                 "%-4.6b", "true", },
                { Boolean.TRUE,                 "%.2b",   "tr", },
                { new Character('c'),           "%3.2b",  " tr", },
                { new Character('c'),           "%-4.6b", "true", },
                { new Character('c'),           "%.2b",   "tr", },
                { new Byte((byte) 0x01),        "%3.2b",  " tr", },
                { new Byte((byte) 0x01),        "%-4.6b", "true", },
                { new Byte((byte) 0x01),        "%.2b",   "tr", },
                { new Short((short) 0x0001),    "%3.2b",  " tr", },
                { new Short((short) 0x0001),    "%-4.6b", "true", },
                { new Short((short) 0x0001),    "%.2b",   "tr", },
                { new Integer(1),               "%3.2b",  " tr", },
                { new Integer(1),               "%-4.6b", "true", },
                { new Integer(1),               "%.2b",   "tr", },
                { new Float(1.1f),              "%3.2b",  " tr", },
                { new Float(1.1f),              "%-4.6b", "true", },
                { new Float(1.1f),              "%.2b",   "tr", },
                { new Double(1.1d),             "%3.2b",  " tr", },
                { new Double(1.1d),             "%-4.6b", "true", },
                { new Double(1.1d),             "%.2b",   "tr", },
                { "",                           "%3.2b",  " tr", },
                { "",                           "%-4.6b", "true", },
                { "",                           "%.2b",   "tr", },
                { "string content",             "%3.2b",  " tr", },
                { "string content",             "%-4.6b", "true", },
                { "string content",             "%.2b",   "tr", },
                { new MockFormattable(),        "%3.2b",  " tr", },
                { new MockFormattable(),        "%-4.6b", "true", },
                { new MockFormattable(),        "%.2b",   "tr", },
                { (Object) null,                "%3.2b",  " fa", },
                { (Object) null,                "%-4.6b", "false", },
                { (Object) null,                "%.2b",   "fa", },
                };


        final int input   = 0;
        final int pattern = 1;
        final int output  = 2; 
        Formatter f = null;
        for (int i = 0; i < triple.length; i++) {
            f = new Formatter(Locale.FRANCE);
            f.format((String)triple[i][pattern], triple[i][input]);
            assertEquals("triple[" + i + "]:" + triple[i][input]
                          + ",pattern[" + i + "]:" + triple[i][pattern], triple[i][output], f.toString());

            f = new Formatter(Locale.GERMAN);
            f.format(((String)triple[i][pattern]).toUpperCase(Locale.US), triple[i][input]);
            assertEquals("triple[" + i + "]:" + triple[i][input]
                          + ",pattern[" + i + "]:" + triple[i][pattern], ((String)triple[i][output])
                    .toUpperCase(Locale.US), f.toString());
        }
    }
    
    /**
     * @tests java.util.Formatter#format(String, Object...) for general
     *        conversion type 's' and 'S'
     */
    public void test_format_LString$LObject_GeneralConversionS() {

        final Object[][] triple = { 
                { Boolean.FALSE,                "%2.3s",  "fal", },
                { Boolean.FALSE,                "%-6.4s", "fals  ", },
                { Boolean.FALSE,                "%.5s",   "false", }, 
                { Boolean.TRUE,                 "%2.3s",  "tru", },
                { Boolean.TRUE,                 "%-6.4s", "true  ", },
                { Boolean.TRUE,                 "%.5s",   "true", },
                { new Character('c'),           "%2.3s",  " c", },
                { new Character('c'),           "%-6.4s", "c     ", },
                { new Character('c'),           "%.5s",   "c", },
                { new Byte((byte) 0x01),        "%2.3s",  " 1", },
                { new Byte((byte) 0x01),        "%-6.4s", "1     ", },
                { new Byte((byte) 0x01),        "%.5s",   "1", },
                { new Short((short) 0x0001),    "%2.3s",  " 1", },
                { new Short((short) 0x0001),    "%-6.4s", "1     ", },
                { new Short((short) 0x0001),    "%.5s",   "1", },
                { new Integer(1),               "%2.3s",  " 1", },
                { new Integer(1),               "%-6.4s", "1     ", },
                { new Integer(1),               "%.5s",   "1", },
                { new Float(1.1f),              "%2.3s",  "1.1", },
                { new Float(1.1f),              "%-6.4s", "1.1   ", },
                { new Float(1.1f),              "%.5s",   "1.1", },
                { new Double(1.1d),             "%2.3s",  "1.1", },
                { new Double(1.1d),             "%-6.4s", "1.1   ", },
                { new Double(1.1d),             "%.5s",   "1.1", },
                { "",                           "%2.3s",  "  ", },
                { "",                           "%-6.4s", "      ", },
                { "",                           "%.5s",   "", },
                { "string content",             "%2.3s",  "str", },
                { "string content",             "%-6.4s", "stri  ", },
                { "string content",             "%.5s",   "strin", },
                { new MockFormattable(),        "%2.3s",  "customized format function width: 2 precision: 3", },
                { new MockFormattable(),        "%-6.4s", "customized format function width: 6 precision: 4", },
                { new MockFormattable(),        "%.5s",   "customized format function width: -1 precision: 5", },
                { (Object) null,                "%2.3s",  "nul", },
                { (Object) null,                "%-6.4s", "null  ", },
                { (Object) null,                "%.5s",   "null", },
                };


        final int input   = 0;
        final int pattern = 1;
        final int output  = 2;
        Formatter f = null;
        for (int i = 0; i < triple.length; i++) {
            f = new Formatter(Locale.FRANCE);
            f.format((String)triple[i][pattern], triple[i][input]);
            assertEquals("triple[" + i + "]:" + triple[i][input]
                          + ",pattern[" + i + "]:" + triple[i][pattern], triple[i][output], f.toString());

            f = new Formatter(Locale.GERMAN);
            f.format(((String)triple[i][pattern]).toUpperCase(Locale.US), triple[i][input]);
            assertEquals("triple[" + i + "]:" + triple[i][input]
                          + ",pattern[" + i + "]:" + triple[i][pattern], ((String)triple[i][output])
                    .toUpperCase(Locale.US), f.toString());
        }
    }
    
    /**
     * @tests java.util.Formatter#format(String, Object...) for general
     *        conversion type 'h' and 'H'
     */
    public void test_format_LString$LObject_GeneralConversionH() {

        final Object[] input = { 
                 Boolean.FALSE,                 
                 Boolean.TRUE,                  
                 new Character('c'),            
                 new Byte((byte) 0x01),         
                 new Short((short) 0x0001),     
                 new Integer(1),                
                 new Float(1.1f),               
                 new Double(1.1d),              
                 "",                            
                 "string content",              
                 new MockFormattable(),         
                 (Object) null,                 
                };

        Formatter f = null;
        for (int i = 0; i < input.length - 1; i++) {
            f = new Formatter(Locale.FRANCE);
            f.format("%h", input[i]);
            assertEquals("triple[" + i + "]:" + input[i], 
                    Integer.toHexString(input[i].hashCode()), f.toString());

            f = new Formatter(Locale.GERMAN);
            f.format("%H", input[i]);
            assertEquals("triple[" + i + "]:" + input[i], 
                    Integer.toHexString(input[i].hashCode()).toUpperCase(Locale.US), f.toString());
        }
    }
    
    /**
     * @tests java.util.Formatter#format(String, Object...) for general
     *        conversion other cases
     */
    public void test_formatLjava_lang_String$Ljava_lang_Object_GeneralConversionOther() {
        /*
         * In Turkish locale, the upper case of '\u0069' is '\u0130'. The
         * following test indicate that '\u0069' is coverted to upper case
         * without using the turkish locale.
         */
        Formatter f = new Formatter(new Locale("tr"));
        f.format("%S", "\u0069");
        assertEquals("\u0049", f.toString());

        final Object[] input = { 
                Boolean.FALSE,                 
                Boolean.TRUE,                  
                new Character('c'),            
                new Byte((byte) 0x01),         
                new Short((short) 0x0001),     
                new Integer(1),                
                new Float(1.1f),               
                new Double(1.1d),              
                "",                            
                "string content",              
                new MockFormattable(),         
                (Object) null,                 
               };
        f = new Formatter(Locale.GERMAN);
        for (int i = 0; i < input.length; i++) {
            if (!(input[i] instanceof Formattable)) {
                try {
                    f.format("%#s", input[i]);
                    /*
                     * fail on RI, spec says if the '#' flag is present and the
                     * argument is not a Formattable , then a
                     * FormatFlagsConversionMismatchException will be thrown.
                     */
                    fail("should throw FormatFlagsConversionMismatchException");
                } catch (FormatFlagsConversionMismatchException e) {
                    // expected
                }
            } else {
                f.format("%#s%<-#8s", input[i]);
                assertEquals(
                        "customized format function width: -1 precision: -1customized format function width: 8 precision: -1",
                        f.toString());
            }
        }
    }

    /**
     * @tests java.util.Formatter#format(String, Object...) for general
     *        conversion exception
     */
    public void test_formatLjava_lang_String$Ljava_lang_Object_GeneralConversionException() {
        final String[] flagMismatch = { "%#b", "%+b", "% b", "%0b", "%,b",
                "%(b", "%#B", "%+B", "% B", "%0B", "%,B", "%(B", "%#h", "%+h",
                "% h", "%0h", "%,h", "%(h", "%#H", "%+H", "% H", "%0H", "%,H",
                "%(H", "%+s", "% s", "%0s", "%,s", "%(s", "%+S", "% S", "%0S",
                "%,S", "%(S" };

        Formatter f = new Formatter(Locale.US);

        for (int i = 0; i < flagMismatch.length; i++) {
            try {
                f.format(flagMismatch[i], "something");
                fail("should throw FormatFlagsConversionMismatchException");
            } catch (FormatFlagsConversionMismatchException e) {
                // expected
            }
        }

        final String[] missingWidth = { "%-b", "%-B", "%-h", "%-H", "%-s",
                "%-S", };
        for (int i = 0; i < missingWidth.length; i++) {
            try {
                f.format(missingWidth[i], "something");
                fail("should throw MissingFormatWidthException");
            } catch (MissingFormatWidthException e) {
                // expected
            }
        }
        
        // Regression test
        f = new Formatter();
        try {
            f.format("%c", (byte)-0x0001);
            fail("Should throw IllegalFormatCodePointException");
        } catch (IllegalFormatCodePointException e) {
            // expected
        }
        
        f = new Formatter();
        try {
            f.format("%c", (short)-0x0001);
            fail("Should throw IllegalFormatCodePointException");
        } catch (IllegalFormatCodePointException e) {
            // expected
        }
        
        f = new Formatter();
        try {
            f.format("%c", -0x0001);
            fail("Should throw IllegalFormatCodePointException");
        } catch (IllegalFormatCodePointException e) {
            // expected
        }
    }

    /**
     * @tests java.util.Formatter#format(String, Object...) for Character
     *        conversion
     */
    public void test_formatLjava_lang_String$Ljava_lang_Object_CharacterConversion() {
        Formatter f = new Formatter(Locale.US);
        final Object[] illArgs = { Boolean.TRUE, new Float(1.1f),
                new Double(1.1d), "string content", new Float(1.1f), new Date() };
        for (int i = 0; i < illArgs.length; i++) {
            try {
                f.format("%c", illArgs[i]);
                fail("should throw IllegalFormatConversionException");
            } catch (IllegalFormatConversionException e) {
                // expected
            }
        }

        try {
            f.format("%c", Integer.MAX_VALUE);
            fail("should throw IllegalFormatCodePointException");
        } catch (IllegalFormatCodePointException e) {
            // expected
        }

        try {
            f.format("%#c", 'c');
            fail("should throw FormatFlagsConversionMismatchException");
        } catch (FormatFlagsConversionMismatchException e) {
            // expected
        }

        final Object[][] triple = {
                {'c',               "%c",   "c"},
                {'c',               "%-2c", "c "},
                {'\u0123',          "%c",   "\u0123"},
                {'\u0123',          "%-2c", "\u0123 "},
                {(byte) 0x11,       "%c",   "\u0011"},
                {(byte) 0x11,       "%-2c", "\u0011 "},
                {(short) 0x1111,    "%c",   "\u1111"},
                {(short) 0x1111,    "%-2c", "\u1111 "},
                {0x11,              "%c",   "\u0011"},
                {0x11,              "%-2c", "\u0011 "},
        };

        final int input   = 0;
        final int pattern = 1;
        final int output  = 2;
        for (int i = 0; i < triple.length; i++) {
                f = new Formatter(Locale.US);
                f.format((String)triple[i][pattern], triple[i][input]);
                assertEquals(triple[i][output], f.toString());
        }

        f = new Formatter(Locale.US);
        f.format("%c", 0x10000);
        assertEquals(0x10000, f.toString().codePointAt(0));

        try {
            f.format("%2.2c", 'c');
            fail("should throw IllegalFormatPrecisionException");
        } catch (IllegalFormatPrecisionException e) {
            // expected
        }

        f = new Formatter(Locale.US);
        f.format("%C", 'w');
        // error on RI, throw UnknownFormatConversionException
        // RI do not support converter 'C'
        assertEquals("W", f.toString());

        f = new Formatter(Locale.JAPAN);
        f.format("%Ced", 0x1111);
        // error on RI, throw UnknownFormatConversionException
        // RI do not support converter 'C'
        assertEquals("\u1111ed", f.toString());
    }
    
    
    
    /**
     * @tests java.util.Formatter#format(String, Object...) for legal
     *        Byte/Short/Integer/Long conversion type 'd'
     */
    public void test_formatLjava_lang_String$Ljava_lang_Object_ByteShortIntegerLongConversionD() {
        final Object[][] triple = { 
                { 0,                "%d",                  "0" }, 
                { 0,                "%10d",       "         0" }, 
                { 0,                "%-1d",                "0" }, 
                { 0,                "%+d",                "+0" }, 
                { 0,                "% d",                " 0" }, 
                { 0,                "%,d",                 "0" }, 
                { 0,                "%(d",                 "0" }, 
                { 0,                "%08d",         "00000000" }, 
                { 0,                "%-+,(11d",  "+0         " }, 
                { 0,                "%0 ,(11d",  " 0000000000" }, 

                { (byte) 0xff,      "%d",                 "-1" }, 
                { (byte) 0xff,      "%10d",       "        -1" }, 
                { (byte) 0xff,      "%-1d",               "-1" }, 
                { (byte) 0xff,      "%+d",                "-1" }, 
                { (byte) 0xff,      "% d",                "-1" }, 
                { (byte) 0xff,      "%,d",                "-1" }, 
                { (byte) 0xff,      "%(d",               "(1)" }, 
                { (byte) 0xff,      "%08d",         "-0000001" }, 
                { (byte) 0xff,      "%-+,(11d",  "(1)        " }, 
                { (byte) 0xff,      "%0 ,(11d",  "(000000001)" }, 
                
                { (short) 0xf123,   "%d",              "-3805" }, 
                { (short) 0xf123,   "%10d",       "     -3805" }, 
                { (short) 0xf123,   "%-1d",            "-3805" }, 
                { (short) 0xf123,   "%+d",             "-3805" }, 
                { (short) 0xf123,   "% d",             "-3805" }, 
                { (short) 0xf123,   "%,d",            "-3.805" }, 
                { (short) 0xf123,   "%(d",            "(3805)" }, 
                { (short) 0xf123,   "%08d",         "-0003805" }, 
                { (short) 0xf123,   "%-+,(11d",  "(3.805)    " }, 
                { (short) 0xf123,   "%0 ,(11d",  "(00003.805)" }, 
                
                {  0x123456,        "%d",            "1193046" }, 
                {  0x123456,        "%10d",       "   1193046" }, 
                {  0x123456,        "%-1d",          "1193046" }, 
                {  0x123456,        "%+d",          "+1193046" }, 
                {  0x123456,        "% d",          " 1193046" }, 
                {  0x123456,        "%,d",         "1.193.046" }, 
                {  0x123456,        "%(d",           "1193046" }, 
                {  0x123456,        "%08d",         "01193046" }, 
                {  0x123456,        "%-+,(11d",  "+1.193.046 " }, 
                {  0x123456,        "%0 ,(11d",  " 01.193.046" }, 
                
                { -3,               "%d",                 "-3" }, 
                { -3,               "%10d",       "        -3" }, 
                { -3,               "%-1d",               "-3" }, 
                { -3,               "%+d",                "-3" }, 
                { -3,               "% d",                "-3" }, 
                { -3,               "%,d",                "-3" }, 
                { -3,               "%(d",               "(3)" }, 
                { -3,               "%08d",         "-0000003" }, 
                { -3,               "%-+,(11d",  "(3)        " }, 
                { -3,               "%0 ,(11d",  "(000000003)" },
                
                { 0x7654321L,       "%d",          "124076833" }, 
                { 0x7654321L,       "%10d",       " 124076833" }, 
                { 0x7654321L,       "%-1d",        "124076833" }, 
                { 0x7654321L,       "%+d",        "+124076833" }, 
                { 0x7654321L,       "% d",        " 124076833" }, 
                { 0x7654321L,       "%,d",       "124.076.833" }, 
                { 0x7654321L,       "%(d",         "124076833" }, 
                { 0x7654321L,       "%08d",        "124076833" }, 
                { 0x7654321L,       "%-+,(11d", "+124.076.833" }, 
                { 0x7654321L,       "%0 ,(11d", " 124.076.833" }, 
                
                { -1L,              "%d",                 "-1" }, 
                { -1L,              "%10d",       "        -1" }, 
                { -1L,              "%-1d",               "-1" }, 
                { -1L,              "%+d",                "-1" }, 
                { -1L,              "% d",                "-1" }, 
                { -1L,              "%,d",                "-1" }, 
                { -1L,              "%(d",               "(1)" }, 
                { -1L,              "%08d",         "-0000001" }, 
                { -1L,              "%-+,(11d",  "(1)        " }, 
                { -1L,              "%0 ,(11d",  "(000000001)" }, 
                };

        final int input = 0;
        final int pattern = 1;
        final int output = 2;
        Formatter f;
        for (int i = 0; i < triple.length; i++) {
            f = new Formatter(Locale.GERMAN);
            f.format((String) triple[i][pattern],
                    triple[i][input]);
            assertEquals("triple[" + i + "]:" + triple[i][input] + ",pattern["
                    + i + "]:" + triple[i][pattern], triple[i][output], f
                    .toString());
        }
    }
    
    /**
     * @tests java.util.Formatter#format(String, Object...) for legal
     *        Byte/Short/Integer/Long conversion type 'o'
     */
    public void test_formatLjava_lang_String$Ljava_lang_Object_ByteShortIntegerLongConversionO() {
        final Object[][] triple = { 
                { 0,                "%o",                 "0" }, 
                { 0,                "%-6o",          "0     " }, 
                { 0,                "%08o",        "00000000" }, 
                { 0,                "%#o",               "00" }, 
                { 0,                "%0#11o",   "00000000000" }, 
                { 0,                "%-#9o",      "00       " }, 

                { (byte) 0xff,      "%o",               "377" }, 
                { (byte) 0xff,      "%-6o",          "377   " }, 
                { (byte) 0xff,      "%08o",        "00000377" }, 
                { (byte) 0xff,      "%#o",             "0377" }, 
                { (byte) 0xff,      "%0#11o",   "00000000377" }, 
                { (byte) 0xff,      "%-#9o",      "0377     " }, 
                
                { (short) 0xf123,   "%o",            "170443" }, 
                { (short) 0xf123,   "%-6o",          "170443" }, 
                { (short) 0xf123,   "%08o",        "00170443" }, 
                { (short) 0xf123,   "%#o",          "0170443" }, 
                { (short) 0xf123,   "%0#11o",   "00000170443" }, 
                { (short) 0xf123,   "%-#9o",      "0170443  " }, 
                
                {  0x123456,        "%o",           "4432126" }, 
                {  0x123456,        "%-6o",         "4432126" }, 
                {  0x123456,        "%08o",        "04432126" }, 
                {  0x123456,        "%#o",         "04432126" }, 
                {  0x123456,        "%0#11o",   "00004432126" }, 
                {  0x123456,        "%-#9o",      "04432126 " }, 
                
                { -3,               "%o",       "37777777775" }, 
                { -3,               "%-6o",     "37777777775" }, 
                { -3,               "%08o",     "37777777775" }, 
                { -3,               "%#o",     "037777777775" }, 
                { -3,               "%0#11o",  "037777777775" }, 
                { -3,               "%-#9o",   "037777777775" }, 
                
                { 0x7654321L,       "%o",          "731241441" }, 
                { 0x7654321L,       "%-6o",        "731241441" }, 
                { 0x7654321L,       "%08o",        "731241441" }, 
                { 0x7654321L,       "%#o",        "0731241441" }, 
                { 0x7654321L,       "%0#11o",    "00731241441" }, 
                { 0x7654321L,       "%-#9o",      "0731241441" }, 
                
                { -1L,              "%o",       "1777777777777777777777" }, 
                { -1L,              "%-6o",     "1777777777777777777777" }, 
                { -1L,              "%08o",     "1777777777777777777777" }, 
                { -1L,              "%#o",     "01777777777777777777777" }, 
                { -1L,              "%0#11o",  "01777777777777777777777" }, 
                { -1L,              "%-#9o",   "01777777777777777777777" }, 
                };

        final int input = 0;
        final int pattern = 1;
        final int output = 2;
        Formatter f;
        for (int i = 0; i < triple.length; i++) {
            f = new Formatter(Locale.ITALY);
            f.format((String) triple[i][pattern],
                    triple[i][input]);
            assertEquals("triple[" + i + "]:" + triple[i][input] + ",pattern["
                    + i + "]:" + triple[i][pattern], triple[i][output], f
                    .toString());
        }
    }
    
    /**
     * @tests java.util.Formatter#format(String, Object...) for legal
     *        Byte/Short/Integer/Long conversion type 'x' and 'X'
     */
    public void test_formatLjava_lang_String$Ljava_lang_Object_ByteShortIntegerLongConversionX() {
        final Object[][] triple = { 
                { 0,                "%x",                 "0" }, 
                { 0,                "%-8x",        "0       " }, 
                { 0,                "%06x",          "000000" }, 
                { 0,                "%#x",              "0x0" }, 
                { 0,                "%0#12x",  "0x0000000000" }, 
                { 0,                "%-#9x",      "0x0      " }, 

                { (byte) 0xff,      "%x",                "ff" }, 
                { (byte) 0xff,      "%-8x",        "ff      " }, 
                { (byte) 0xff,      "%06x",          "0000ff" }, 
                { (byte) 0xff,      "%#x",             "0xff" }, 
                { (byte) 0xff,      "%0#12x",  "0x00000000ff" }, 
                { (byte) 0xff,      "%-#9x",      "0xff     " }, 
                
                { (short) 0xf123,   "%x",              "f123" }, 
                { (short) 0xf123,   "%-8x",        "f123    " }, 
                { (short) 0xf123,   "%06x",          "00f123" }, 
                { (short) 0xf123,   "%#x",           "0xf123" }, 
                { (short) 0xf123,   "%0#12x",  "0x000000f123" }, 
                { (short) 0xf123,   "%-#9x",      "0xf123   " }, 
                
                {  0x123456,        "%x",            "123456" }, 
                {  0x123456,        "%-8x",        "123456  " }, 
                {  0x123456,        "%06x",          "123456" }, 
                {  0x123456,        "%#x",         "0x123456" }, 
                {  0x123456,        "%0#12x",  "0x0000123456" }, 
                {  0x123456,        "%-#9x",      "0x123456 " }, 
                
                { -3,               "%x",          "fffffffd" }, 
                { -3,               "%-8x",        "fffffffd" }, 
                { -3,               "%06x",        "fffffffd" }, 
                { -3,               "%#x",       "0xfffffffd" }, 
                { -3,               "%0#12x",  "0x00fffffffd" }, 
                { -3,               "%-#9x",     "0xfffffffd" }, 
                
                { 0x7654321L,       "%x",          "7654321" }, 
                { 0x7654321L,       "%-8x",       "7654321 " }, 
                { 0x7654321L,       "%06x",        "7654321" }, 
                { 0x7654321L,       "%#x",       "0x7654321" }, 
                { 0x7654321L,       "%0#12x", "0x0007654321" }, 
                { 0x7654321L,       "%-#9x",     "0x7654321" }, 
                
                { -1L,              "%x",       "ffffffffffffffff" }, 
                { -1L,              "%-8x",     "ffffffffffffffff" }, 
                { -1L,              "%06x",     "ffffffffffffffff" }, 
                { -1L,              "%#x",    "0xffffffffffffffff" }, 
                { -1L,              "%0#12x", "0xffffffffffffffff" }, 
                { -1L,              "%-#9x",  "0xffffffffffffffff" }, 
                };

        final int input = 0;
        final int pattern = 1;
        final int output = 2;
        Formatter f;
        for (int i = 0; i < triple.length; i++) {
            f = new Formatter(Locale.FRANCE);
            f.format((String) triple[i][pattern],
                    triple[i][input]);
            assertEquals("triple[" + i + "]:" + triple[i][input] + ",pattern["
                    + i + "]:" + triple[i][pattern], triple[i][output], f
                    .toString());
            
            f = new Formatter(Locale.FRANCE);
            f.format((String) triple[i][pattern],
                    triple[i][input]);
            assertEquals("triple[" + i + "]:" + triple[i][input] + ",pattern["
                    + i + "]:" + triple[i][pattern], triple[i][output], f
                    .toString());
        }
    }
    
    /**
     * @tests java.util.Formatter#format(String, Object...) for Date/Time
     *        conversion
     */
    public void test_formatLjava_lang_String$Ljava_lang_Object_DateTimeConversion() {
        Formatter f = null;
        Date now = new Date(1147327147578L);

        Calendar paris = Calendar.getInstance(TimeZone
                .getTimeZone("Europe/Paris"), Locale.FRANCE);
        paris.set(2006, 4, 8, 12, 0, 0);
        paris.set(Calendar.MILLISECOND, 453);
        Calendar china = Calendar.getInstance(
                TimeZone.getTimeZone("GMT-08:00"), Locale.CHINA);
        china.set(2006, 4, 8, 12, 0, 0);
        china.set(Calendar.MILLISECOND, 609);

        final Object[][] lowerCaseGermanTriple = {
                {0L,                        'a', "Do"},  //$NON-NLS-2$
                {Long.MAX_VALUE,            'a', "So"},  //$NON-NLS-2$
                {-1000L,                    'a', "Do"},  //$NON-NLS-2$
                {new Date(1147327147578L),  'a', "Do"},  //$NON-NLS-2$
                {paris,                     'a', "Mo"},  //$NON-NLS-2$
                {china,                     'a', "Mo"},  //$NON-NLS-2$
                {0L,                        'b', "Jan"},  //$NON-NLS-2$
                {Long.MAX_VALUE,            'b', "Aug"},  //$NON-NLS-2$
                {-1000L,                    'b', "Jan"},  //$NON-NLS-2$
                {new Date(1147327147578L),  'b', "Mai"},  //$NON-NLS-2$
                {paris,                     'b', "Mai"},  //$NON-NLS-2$
                {china,                     'b', "Mai"},  //$NON-NLS-2$
                {0L,                        'c', "Do Jan 01 08:00:00 CST 1970"},  //$NON-NLS-2$
                {Long.MAX_VALUE,            'c', "So Aug 17 15:12:55 CST 292278994"},  //$NON-NLS-2$
                {-1000L,                    'c', "Do Jan 01 07:59:59 CST 1970"},  //$NON-NLS-2$
                {new Date(1147327147578L),  'c', "Do Mai 11 13:59:07 CST 2006"},  //$NON-NLS-2$
                {paris,                     'c', "Mo Mai 08 12:00:00 CEST 2006"},  //$NON-NLS-2$
                {china,                     'c', "Mo Mai 08 12:00:00 GMT-08:00 2006"},  //$NON-NLS-2$
                {0L,                        'd', "01"},  //$NON-NLS-2$
                {Long.MAX_VALUE,            'd', "17"},  //$NON-NLS-2$
                {-1000L,                    'd', "01"},  //$NON-NLS-2$
                {new Date(1147327147578L),  'd', "11"},  //$NON-NLS-2$
                {paris,                     'd', "08"},  //$NON-NLS-2$
                {china,                     'd', "08"},  //$NON-NLS-2$
                {0L,                        'e', "1"},  //$NON-NLS-2$
                {Long.MAX_VALUE,            'e', "17"},  //$NON-NLS-2$
                {-1000L,                    'e', "1"},  //$NON-NLS-2$
                {new Date(1147327147578L),  'e', "11"},  //$NON-NLS-2$
                {paris,                     'e', "8"},  //$NON-NLS-2$
                {china,                     'e', "8"},  //$NON-NLS-2$
                {0L,                        'h', "Jan"},  //$NON-NLS-2$
                {Long.MAX_VALUE,            'h', "Aug"},  //$NON-NLS-2$
                {-1000L,                    'h', "Jan"},  //$NON-NLS-2$
                {new Date(1147327147578L),  'h', "Mai"},  //$NON-NLS-2$
                {paris,                     'h', "Mai"},  //$NON-NLS-2$
                {china,                     'h', "Mai"},  //$NON-NLS-2$
                {0L,                        'j', "001"},  //$NON-NLS-2$
                {Long.MAX_VALUE,            'j', "229"},  //$NON-NLS-2$
                {-1000L,                    'j', "001"},  //$NON-NLS-2$
                {new Date(1147327147578L),  'j', "131"},  //$NON-NLS-2$
                {paris,                     'j', "128"},  //$NON-NLS-2$
                {china,                     'j', "128"},  //$NON-NLS-2$
                {0L,                        'k', "8"},  //$NON-NLS-2$
                {Long.MAX_VALUE,            'k', "15"},  //$NON-NLS-2$
                {-1000L,                    'k', "7"},  //$NON-NLS-2$
                {new Date(1147327147578L),  'k', "13"},  //$NON-NLS-2$
                {paris,                     'k', "12"},  //$NON-NLS-2$
                {china,                     'k', "12"},  //$NON-NLS-2$
                {0L,                        'l', "8"}, //$NON-NLS-2$
                {Long.MAX_VALUE,            'l', "3"}, //$NON-NLS-2$
                {-1000L,                    'l', "7"}, //$NON-NLS-2$
                {new Date(1147327147578L),  'l', "1"}, //$NON-NLS-2$
                {paris,                     'l', "12"}, //$NON-NLS-2$
                {china,                     'l', "12"}, //$NON-NLS-2$
                {0L,                        'm', "01"}, //$NON-NLS-2$
                {Long.MAX_VALUE,            'm', "08"}, //$NON-NLS-2$
                {-1000L,                    'm', "01"}, //$NON-NLS-2$
                {new Date(1147327147578L),  'm', "05"}, //$NON-NLS-2$
                {paris,                     'm', "05"}, //$NON-NLS-2$
                {china,                     'm', "05"}, //$NON-NLS-2$
                {0L,                        'p', "am"}, //$NON-NLS-2$
                {Long.MAX_VALUE,            'p', "pm"}, //$NON-NLS-2$
                {-1000L,                    'p', "am"}, //$NON-NLS-2$
                {new Date(1147327147578L),  'p', "pm"}, //$NON-NLS-2$
                {paris,                     'p', "pm"}, //$NON-NLS-2$
                {china,                     'p', "pm"}, //$NON-NLS-2$
                {0L,                        'r', "08:00:00 AM"}, //$NON-NLS-2$
                {Long.MAX_VALUE,            'r', "03:12:55 PM"}, //$NON-NLS-2$
                {-1000L,                    'r', "07:59:59 AM"}, //$NON-NLS-2$
                {new Date(1147327147578L),  'r', "01:59:07 PM"}, //$NON-NLS-2$
                {paris,                     'r', "12:00:00 PM"}, //$NON-NLS-2$
                {china,                     'r', "12:00:00 PM"}, //$NON-NLS-2$
                {0L,                        's', "0"}, //$NON-NLS-2$
                {Long.MAX_VALUE,            's', "9223372036854775"}, //$NON-NLS-2$
                {-1000L,                    's', "-1"}, //$NON-NLS-2$
                {new Date(1147327147578L),  's', "1147327147"}, //$NON-NLS-2$
                {paris,                     's', "1147082400"}, //$NON-NLS-2$
                {china,                     's', "1147118400"}, //$NON-NLS-2$
                {0L,                        'y', "70"}, //$NON-NLS-2$
                {Long.MAX_VALUE,            'y', "94"}, //$NON-NLS-2$
                {-1000L,                    'y', "70"}, //$NON-NLS-2$
                {new Date(1147327147578L),  'y', "06"}, //$NON-NLS-2$
                {paris,                     'y', "06"}, //$NON-NLS-2$
                {china,                     'y', "06"}, //$NON-NLS-2$
                {0L,                        'z', "+0800"}, //$NON-NLS-2$
                {Long.MAX_VALUE,            'z', "+0800"}, //$NON-NLS-2$
                {-1000L,                    'z', "+0800"}, //$NON-NLS-2$
                {new Date(1147327147578L),  'z', "+0800"}, //$NON-NLS-2$
                {paris,                     'z', "+0100"}, //$NON-NLS-2$
                {china,                     'z', "-0800"}, //$NON-NLS-2$
                
        };
        
        final Object[][] lowerCaseFranceTriple = {
                {0L,                        'a', "jeu."}, //$NON-NLS-2$
                {Long.MAX_VALUE,            'a', "dim."}, //$NON-NLS-2$
                {-1000L,                    'a', "jeu."}, //$NON-NLS-2$
                {new Date(1147327147578L),  'a', "jeu."}, //$NON-NLS-2$
                {paris,                     'a', "lun."}, //$NON-NLS-2$
                {china,                     'a', "lun."}, //$NON-NLS-2$
                {0L,                        'b', "janv."}, //$NON-NLS-2$
                {Long.MAX_VALUE,            'b', "ao\u00fbt"}, //$NON-NLS-2$
                {-1000L,                    'b', "janv."}, //$NON-NLS-2$
                {new Date(1147327147578L),  'b', "mai"}, //$NON-NLS-2$
                {paris,                     'b', "mai"}, //$NON-NLS-2$
                {china,                     'b', "mai"}, //$NON-NLS-2$
                {0L,                        'c', "jeu. janv. 01 08:00:00 CST 1970"}, //$NON-NLS-2$
                {Long.MAX_VALUE,            'c', "dim. ao\u00fbt 17 15:12:55 CST 292278994"}, //$NON-NLS-2$
                {-1000L,                    'c', "jeu. janv. 01 07:59:59 CST 1970"}, //$NON-NLS-2$
                {new Date(1147327147578L),  'c', "jeu. mai 11 13:59:07 CST 2006"}, //$NON-NLS-2$
                {paris,                     'c', "lun. mai 08 12:00:00 CEST 2006"}, //$NON-NLS-2$
                {china,                     'c', "lun. mai 08 12:00:00 GMT-08:00 2006"}, //$NON-NLS-2$
                {0L,                        'd', "01"}, //$NON-NLS-2$
                {Long.MAX_VALUE,            'd', "17"}, //$NON-NLS-2$
                {-1000L,                    'd', "01"}, //$NON-NLS-2$
                {new Date(1147327147578L),  'd', "11"}, //$NON-NLS-2$
                {paris,                     'd', "08"}, //$NON-NLS-2$
                {china,                     'd', "08"}, //$NON-NLS-2$
                {0L,                        'e', "1"}, //$NON-NLS-2$
                {Long.MAX_VALUE,            'e', "17"}, //$NON-NLS-2$
                {-1000L,                    'e', "1"}, //$NON-NLS-2$
                {new Date(1147327147578L),  'e', "11"}, //$NON-NLS-2$
                {paris,                     'e', "8"}, //$NON-NLS-2$
                {china,                     'e', "8"}, //$NON-NLS-2$
                {0L,                        'h', "janv."}, //$NON-NLS-2$
                {Long.MAX_VALUE,            'h', "ao\u00fbt"}, //$NON-NLS-2$
                {-1000L,                    'h', "janv."}, //$NON-NLS-2$
                {new Date(1147327147578L),  'h', "mai"}, //$NON-NLS-2$
                {paris,                     'h', "mai"}, //$NON-NLS-2$
                {china,                     'h', "mai"}, //$NON-NLS-2$
                {0L,                        'j', "001"}, //$NON-NLS-2$
                {Long.MAX_VALUE,            'j', "229"}, //$NON-NLS-2$
                {-1000L,                    'j', "001"}, //$NON-NLS-2$
                {new Date(1147327147578L),  'j', "131"}, //$NON-NLS-2$
                {paris,                     'j', "128"}, //$NON-NLS-2$
                {china,                     'j', "128"}, //$NON-NLS-2$
                {0L,                        'k', "8"}, //$NON-NLS-2$
                {Long.MAX_VALUE,            'k', "15"}, //$NON-NLS-2$
                {-1000L,                    'k', "7"}, //$NON-NLS-2$
                {new Date(1147327147578L),  'k', "13"}, //$NON-NLS-2$
                {paris,                     'k', "12"}, //$NON-NLS-2$
                {china,                     'k', "12"}, //$NON-NLS-2$
                {0L,                        'l', "8"}, //$NON-NLS-2$
                {Long.MAX_VALUE,            'l', "3"}, //$NON-NLS-2$
                {-1000L,                    'l', "7"}, //$NON-NLS-2$
                {new Date(1147327147578L),  'l', "1"}, //$NON-NLS-2$
                {paris,                     'l', "12"}, //$NON-NLS-2$
                {china,                     'l', "12"}, //$NON-NLS-2$
                {0L,                        'm', "01"}, //$NON-NLS-2$
                {Long.MAX_VALUE,            'm', "08"}, //$NON-NLS-2$
                {-1000L,                    'm', "01"}, //$NON-NLS-2$
                {new Date(1147327147578L),  'm', "05"}, //$NON-NLS-2$
                {paris,                     'm', "05"}, //$NON-NLS-2$
                {china,                     'm', "05"}, //$NON-NLS-2$
                {0L,                        'p', "am"}, //$NON-NLS-2$
                {Long.MAX_VALUE,            'p', "pm"}, //$NON-NLS-2$
                {-1000L,                    'p', "am"}, //$NON-NLS-2$
                {new Date(1147327147578L),  'p', "pm"}, //$NON-NLS-2$
                {paris,                     'p', "pm"}, //$NON-NLS-2$
                {china,                     'p', "pm"}, //$NON-NLS-2$
                {0L,                        'r', "08:00:00 AM"}, //$NON-NLS-2$
                {Long.MAX_VALUE,            'r', "03:12:55 PM"}, //$NON-NLS-2$
                {-1000L,                    'r', "07:59:59 AM"}, //$NON-NLS-2$
                {new Date(1147327147578L),  'r', "01:59:07 PM"}, //$NON-NLS-2$
                {paris,                     'r', "12:00:00 PM"}, //$NON-NLS-2$
                {china,                     'r', "12:00:00 PM"}, //$NON-NLS-2$
                {0L,                        's', "0"}, //$NON-NLS-2$
                {Long.MAX_VALUE,            's', "9223372036854775"}, //$NON-NLS-2$
                {-1000L,                    's', "-1"}, //$NON-NLS-2$
                {new Date(1147327147578L),  's', "1147327147"}, //$NON-NLS-2$
                {paris,                     's', "1147082400"}, //$NON-NLS-2$
                {china,                     's', "1147118400"}, //$NON-NLS-2$
                {0L,                        'y', "70"}, //$NON-NLS-2$
                {Long.MAX_VALUE,            'y', "94"}, //$NON-NLS-2$
                {-1000L,                    'y', "70"}, //$NON-NLS-2$
                {new Date(1147327147578L),  'y', "06"}, //$NON-NLS-2$
                {paris,                     'y', "06"}, //$NON-NLS-2$
                {china,                     'y', "06"}, //$NON-NLS-2$
                {0L,                        'z', "+0800"}, //$NON-NLS-2$
                {Long.MAX_VALUE,            'z', "+0800"}, //$NON-NLS-2$
                {-1000L,                    'z', "+0800"}, //$NON-NLS-2$
                {new Date(1147327147578L),  'z', "+0800"}, //$NON-NLS-2$
                {paris,                     'z', "+0100"}, //$NON-NLS-2$
                {china,                     'z', "-0800"}, //$NON-NLS-2$
                
        };
        
        final Object[][] lowerCaseJapanTriple = {
                {0L,                        'a', "\u6728"}, //$NON-NLS-2$
                {Long.MAX_VALUE,            'a', "\u65e5"}, //$NON-NLS-2$
                {-1000L,                    'a', "\u6728"}, //$NON-NLS-2$
                {new Date(1147327147578L),  'a', "\u6728"}, //$NON-NLS-2$
                {paris,                     'a', "\u6708"}, //$NON-NLS-2$
                {china,                     'a', "\u6708"}, //$NON-NLS-2$
                {0L,                        'b', "1"}, //$NON-NLS-2$
                {Long.MAX_VALUE,            'b', "8"}, //$NON-NLS-2$
                {-1000L,                    'b', "1"}, //$NON-NLS-2$
                {new Date(1147327147578L),  'b', "5"}, //$NON-NLS-2$
                {paris,                     'b', "5"}, //$NON-NLS-2$
                {china,                     'b', "5"}, //$NON-NLS-2$
                {0L,                        'c', "\u6728 1 01 08:00:00 CST 1970"}, //$NON-NLS-2$
                {Long.MAX_VALUE,            'c', "\u65e5 8 17 15:12:55 CST 292278994"}, //$NON-NLS-2$
                {-1000L,                    'c', "\u6728 1 01 07:59:59 CST 1970"}, //$NON-NLS-2$
                {new Date(1147327147578L),  'c', "\u6728 5 11 13:59:07 CST 2006"}, //$NON-NLS-2$
                {paris,                     'c', "\u6708 5 08 12:00:00 CEST 2006"}, //$NON-NLS-2$
                {china,                     'c', "\u6708 5 08 12:00:00 GMT-08:00 2006"}, //$NON-NLS-2$
                {0L,                        'd', "01"}, //$NON-NLS-2$
                {Long.MAX_VALUE,            'd', "17"}, //$NON-NLS-2$
                {-1000L,                    'd', "01"}, //$NON-NLS-2$
                {new Date(1147327147578L),  'd', "11"}, //$NON-NLS-2$
                {paris,                     'd', "08"}, //$NON-NLS-2$
                {china,                     'd', "08"}, //$NON-NLS-2$
                {0L,                        'e', "1"}, //$NON-NLS-2$
                {Long.MAX_VALUE,            'e', "17"}, //$NON-NLS-2$
                {-1000L,                    'e', "1"}, //$NON-NLS-2$
                {new Date(1147327147578L),  'e', "11"}, //$NON-NLS-2$
                {paris,                     'e', "8"}, //$NON-NLS-2$
                {china,                     'e', "8"}, //$NON-NLS-2$
                {0L,                        'h', "1"}, //$NON-NLS-2$
                {Long.MAX_VALUE,            'h', "8"}, //$NON-NLS-2$
                {-1000L,                    'h', "1"}, //$NON-NLS-2$
                {new Date(1147327147578L),  'h', "5"}, //$NON-NLS-2$
                {paris,                     'h', "5"}, //$NON-NLS-2$
                {china,                     'h', "5"}, //$NON-NLS-2$
                {0L,                        'j', "001"}, //$NON-NLS-2$
                {Long.MAX_VALUE,            'j', "229"}, //$NON-NLS-2$
                {-1000L,                    'j', "001"}, //$NON-NLS-2$
                {new Date(1147327147578L),  'j', "131"}, //$NON-NLS-2$
                {paris,                     'j', "128"}, //$NON-NLS-2$
                {china,                     'j', "128"}, //$NON-NLS-2$
                {0L,                        'k', "8"}, //$NON-NLS-2$
                {Long.MAX_VALUE,            'k', "15"}, //$NON-NLS-2$
                {-1000L,                    'k', "7"}, //$NON-NLS-2$
                {new Date(1147327147578L),  'k', "13"}, //$NON-NLS-2$
                {paris,                     'k', "12"}, //$NON-NLS-2$
                {china,                     'k', "12"}, //$NON-NLS-2$
                {0L,                        'l', "8"}, //$NON-NLS-2$
                {Long.MAX_VALUE,            'l', "3"}, //$NON-NLS-2$
                {-1000L,                    'l', "7"}, //$NON-NLS-2$
                {new Date(1147327147578L),  'l', "1"}, //$NON-NLS-2$
                {paris,                     'l', "12"}, //$NON-NLS-2$
                {china,                     'l', "12"}, //$NON-NLS-2$
                {0L,                        'm', "01"}, //$NON-NLS-2$
                {Long.MAX_VALUE,            'm', "08"}, //$NON-NLS-2$
                {-1000L,                    'm', "01"}, //$NON-NLS-2$
                {new Date(1147327147578L),  'm', "05"}, //$NON-NLS-2$
                {paris,                     'm', "05"}, //$NON-NLS-2$
                {china,                     'm', "05"}, //$NON-NLS-2$
                {0L,                        'p', "\u5348\u524d"}, //$NON-NLS-2$
                {Long.MAX_VALUE,            'p', "\u5348\u5f8c"}, //$NON-NLS-2$
                {-1000L,                    'p', "\u5348\u524d"}, //$NON-NLS-2$
                {new Date(1147327147578L),  'p', "\u5348\u5f8c"}, //$NON-NLS-2$
                {paris,                     'p', "\u5348\u5f8c"}, //$NON-NLS-2$
                {china,                     'p', "\u5348\u5f8c"}, //$NON-NLS-2$
                {0L,                        'r', "08:00:00 \u5348\u524d"}, //$NON-NLS-2$
                {Long.MAX_VALUE,            'r', "03:12:55 \u5348\u5f8c"}, //$NON-NLS-2$
                {-1000L,                    'r', "07:59:59 \u5348\u524d"}, //$NON-NLS-2$
                {new Date(1147327147578L),  'r', "01:59:07 \u5348\u5f8c"}, //$NON-NLS-2$
                {paris,                     'r', "12:00:00 \u5348\u5f8c"}, //$NON-NLS-2$
                {china,                     'r', "12:00:00 \u5348\u5f8c"}, //$NON-NLS-2$
                {0L,                        's', "0"}, //$NON-NLS-2$
                {Long.MAX_VALUE,            's', "9223372036854775"}, //$NON-NLS-2$
                {-1000L,                    's', "-1"}, //$NON-NLS-2$
                {new Date(1147327147578L),  's', "1147327147"}, //$NON-NLS-2$
                {paris,                     's', "1147082400"}, //$NON-NLS-2$
                {china,                     's', "1147118400"}, //$NON-NLS-2$
                {0L,                        'y', "70"}, //$NON-NLS-2$
                {Long.MAX_VALUE,            'y', "94"}, //$NON-NLS-2$
                {-1000L,                    'y', "70"}, //$NON-NLS-2$
                {new Date(1147327147578L),  'y', "06"}, //$NON-NLS-2$
                {paris,                     'y', "06"}, //$NON-NLS-2$
                {china,                     'y', "06"}, //$NON-NLS-2$
                {0L,                        'z', "+0800"}, //$NON-NLS-2$
                {Long.MAX_VALUE,            'z', "+0800"}, //$NON-NLS-2$
                {-1000L,                    'z', "+0800"}, //$NON-NLS-2$
                {new Date(1147327147578L),  'z', "+0800"}, //$NON-NLS-2$
                {paris,                     'z', "+0100"}, //$NON-NLS-2$
                {china,                     'z', "-0800"}, //$NON-NLS-2$
        };

        final int input   = 0;
        final int pattern = 1;
        final int output  = 2;
        for (int i = 0; i < 90; i++) {
            // go through legal conversion 
            String formatSpecifier = "%t" + lowerCaseGermanTriple[i][pattern]; //$NON-NLS-2$
            String formatSpecifierUpper = "%T" + lowerCaseGermanTriple[i][pattern]; //$NON-NLS-2$
            // test '%t'
            f = new Formatter(Locale.GERMAN);
            f.format(formatSpecifier, lowerCaseGermanTriple[i][input]);
            assertEquals("Format pattern: " + formatSpecifier //$NON-NLS-2$
                            + " Argument: " + lowerCaseGermanTriple[i][pattern], //$NON-NLS-2$
                            lowerCaseGermanTriple[i][output], f.toString());

            f = new Formatter(Locale.GERMAN);
            f.format(Locale.FRANCE, formatSpecifier, lowerCaseFranceTriple[i][input]);
            assertEquals("Format pattern: " + formatSpecifier //$NON-NLS-2$
                            + " Argument: " + lowerCaseFranceTriple[i][input], //$NON-NLS-2$
                            lowerCaseFranceTriple[i][output], f.toString());

            f = new Formatter(Locale.GERMAN);
            f.format(Locale.JAPAN, formatSpecifier, lowerCaseJapanTriple[i][input]);
            assertEquals("Format pattern: " + formatSpecifier //$NON-NLS-2$
                            + " Argument: " + lowerCaseJapanTriple[i][input], //$NON-NLS-2$
                            lowerCaseJapanTriple[i][output], f.toString());

            // test '%T'
            f = new Formatter(Locale.GERMAN);
            f.format(formatSpecifierUpper, lowerCaseGermanTriple[i][input]);
            assertEquals("Format pattern: " + formatSpecifierUpper //$NON-NLS-2$
                            + " Argument: " + lowerCaseGermanTriple[i][input], //$NON-NLS-2$
                            ((String)lowerCaseGermanTriple[i][output])
                                    .toUpperCase(Locale.US), f.toString());

            f = new Formatter(Locale.GERMAN);
            f.format(Locale.FRANCE, formatSpecifierUpper, lowerCaseFranceTriple[i][input]);
            assertEquals("Format pattern: " + formatSpecifierUpper //$NON-NLS-2$
                            + " Argument: " + lowerCaseFranceTriple[i][input], //$NON-NLS-2$
                            ((String)lowerCaseFranceTriple[i][output])
                                    .toUpperCase(Locale.US), f.toString());

            f = new Formatter(Locale.GERMAN);
            f.format(Locale.JAPAN, formatSpecifierUpper, lowerCaseJapanTriple[i][input]);
            assertEquals("Format pattern: " + formatSpecifierUpper //$NON-NLS-2$
                            + " Argument: " + lowerCaseJapanTriple[i][input], //$NON-NLS-2$
                            ((String)lowerCaseJapanTriple[i][output])
                                    .toUpperCase(Locale.US), f.toString());
        }

        final Object[][] upperCaseGermanTriple = {
                {0L,                        'A', "Donnerstag"}, //$NON-NLS-2$
                {Long.MAX_VALUE,            'A', "Sonntag"}, //$NON-NLS-2$
                {-1000L,                    'A', "Donnerstag"}, //$NON-NLS-2$
                {new Date(1147327147578L),  'A', "Donnerstag"}, //$NON-NLS-2$
                {paris,                     'A', "Montag"}, //$NON-NLS-2$
                {china,                     'A', "Montag"}, //$NON-NLS-2$
                {0L,                        'B', "Januar"}, //$NON-NLS-2$
                {Long.MAX_VALUE,            'B', "August"}, //$NON-NLS-2$
                {-1000L,                    'B', "Januar"}, //$NON-NLS-2$
                {new Date(1147327147578L),  'B', "Mai"}, //$NON-NLS-2$
                {paris,                     'B', "Mai"}, //$NON-NLS-2$ 
                {china,                     'B', "Mai"}, //$NON-NLS-2$
                {0L,                        'C', "19"}, //$NON-NLS-2$
                {Long.MAX_VALUE,            'C', "2922789"}, //$NON-NLS-2$
                {-1000L,                    'C', "19"}, //$NON-NLS-2$
                {new Date(1147327147578L),  'C', "20"}, //$NON-NLS-2$
                {paris,                     'C', "20"}, //$NON-NLS-2$
                {china,                     'C', "20"}, //$NON-NLS-2$
                {0L,                        'D', "01/01/70"}, //$NON-NLS-2$
                {Long.MAX_VALUE,            'D', "08/17/94"}, //$NON-NLS-2$
                {-1000L,                    'D', "01/01/70"}, //$NON-NLS-2$
                {new Date(1147327147578L),  'D', "05/11/06"}, //$NON-NLS-2$
                {paris,                     'D', "05/08/06"}, //$NON-NLS-2$
                {china,                     'D', "05/08/06"}, //$NON-NLS-2$
                {0L,                        'F', "1970-01-01"}, //$NON-NLS-2$
                {Long.MAX_VALUE,            'F', "292278994-08-17"}, //$NON-NLS-2$
                {-1000L,                    'F', "1970-01-01"}, //$NON-NLS-2$
                {new Date(1147327147578L),  'F', "2006-05-11"}, //$NON-NLS-2$
                {paris,                     'F', "2006-05-08"}, //$NON-NLS-2$
                {china,                     'F', "2006-05-08"}, //$NON-NLS-2$
                {0L,                        'H', "08"}, //$NON-NLS-2$
                {Long.MAX_VALUE,            'H', "15"}, //$NON-NLS-2$
                {-1000L,                    'H', "07"}, //$NON-NLS-2$
                {new Date(1147327147578L),  'H', "13"}, //$NON-NLS-2$
                {paris,                     'H', "12"}, //$NON-NLS-2$
                {china,                     'H', "12"}, //$NON-NLS-2$
                {0L,                        'I', "08"}, //$NON-NLS-2$
                {Long.MAX_VALUE,            'I', "03"}, //$NON-NLS-2$
                {-1000L,                    'I', "07"}, //$NON-NLS-2$
                {new Date(1147327147578L),  'I', "01"}, //$NON-NLS-2$
                {paris,                     'I', "12"}, //$NON-NLS-2$
                {china,                     'I', "12"}, //$NON-NLS-2$
                {0L,                        'L', "000"}, //$NON-NLS-2$
                {Long.MAX_VALUE,            'L', "807"}, //$NON-NLS-2$
                {-1000L,                    'L', "000"}, //$NON-NLS-2$
                {new Date(1147327147578L),  'L', "578"}, //$NON-NLS-2$
                {paris,                     'L', "453"}, //$NON-NLS-2$
                {china,                     'L', "609"}, //$NON-NLS-2$
                {0L,                        'M', "00"}, //$NON-NLS-2$
                {Long.MAX_VALUE,            'M', "12"}, //$NON-NLS-2$
                {-1000L,                    'M', "59"}, //$NON-NLS-2$
                {new Date(1147327147578L),  'M', "59"}, //$NON-NLS-2$
                {paris,                     'M', "00"}, //$NON-NLS-2$
                {china,                     'M', "00"}, //$NON-NLS-2$
                {0L,                        'N', "000000000"}, //$NON-NLS-2$
                {Long.MAX_VALUE,            'N', "807000000"}, //$NON-NLS-2$
                {-1000L,                    'N', "000000000"}, //$NON-NLS-2$
                {new Date(1147327147578L),  'N', "578000000"}, //$NON-NLS-2$
                {paris,                     'N', "609000000"}, //$NON-NLS-2$
                {china,                     'N', "609000000"}, //$NON-NLS-2$
                {0L,                        'Q', "0"}, //$NON-NLS-2$
                {Long.MAX_VALUE,            'Q', "9223372036854775807"}, //$NON-NLS-2$
                {-1000L,                    'Q', "-1000"}, //$NON-NLS-2$
                {new Date(1147327147578L),  'Q', "1147327147578"}, //$NON-NLS-2$
                {paris,                     'Q', "1147082400453"}, //$NON-NLS-2$
                {china,                     'Q', "1147118400609"}, //$NON-NLS-2$
                {0L,                        'R', "08:00"}, //$NON-NLS-2$
                {Long.MAX_VALUE,            'R', "15:12"}, //$NON-NLS-2$
                {-1000L,                    'R', "07:59"}, //$NON-NLS-2$
                {new Date(1147327147578L),  'R', "13:59"}, //$NON-NLS-2$
                {paris,                     'R', "12:00"}, //$NON-NLS-2$
                {china,                     'R', "12:00"}, //$NON-NLS-2$
                {0L,                        'S', "00"}, //$NON-NLS-2$
                {Long.MAX_VALUE,            'S', "55"}, //$NON-NLS-2$
                {-1000L,                    'S', "59"}, //$NON-NLS-2$
                {new Date(1147327147578L),  'S', "07"}, //$NON-NLS-2$
                {paris,                     'S', "00"}, //$NON-NLS-2$
                {china,                     'S', "00"}, //$NON-NLS-2$
                {0L,                        'T', "08:00:00"}, //$NON-NLS-2$
                {Long.MAX_VALUE,            'T', "15:12:55"}, //$NON-NLS-2$
                {-1000L,                    'T', "07:59:59"}, //$NON-NLS-2$
                {new Date(1147327147578L),  'T', "13:59:07"}, //$NON-NLS-2$
                {paris,                     'T', "12:00:00"}, //$NON-NLS-2$
                {china,                     'T', "12:00:00"}, //$NON-NLS-2$
                {0L,                        'Y', "1970"}, //$NON-NLS-2$
                {Long.MAX_VALUE,            'Y', "292278994"}, //$NON-NLS-2$
                {-1000L,                    'Y', "1970"}, //$NON-NLS-2$
                {new Date(1147327147578L),  'Y', "2006"}, //$NON-NLS-2$
                {paris,                     'Y', "2006"}, //$NON-NLS-2$
                {china,                     'Y', "2006"}, //$NON-NLS-2$
                {0L,                        'Z', "CST"}, //$NON-NLS-2$
                {Long.MAX_VALUE,            'Z', "CST"}, //$NON-NLS-2$
                {-1000L,                    'Z', "CST"}, //$NON-NLS-2$
                {new Date(1147327147578L),  'Z', "CST"}, //$NON-NLS-2$
                {paris,                     'Z', "CEST"}, //$NON-NLS-2$
                {china,                     'Z', "GMT-08:00"}, //$NON-NLS-2$
                
        };
        
        final Object[][] upperCaseFranceTriple = {
                {0L,                        'A', "jeudi"}, //$NON-NLS-2$
                {Long.MAX_VALUE,            'A', "dimanche"}, //$NON-NLS-2$
                {-1000L,                    'A', "jeudi"}, //$NON-NLS-2$
                {new Date(1147327147578L),  'A', "jeudi"}, //$NON-NLS-2$
                {paris,                     'A', "lundi"}, //$NON-NLS-2$
                {china,                     'A', "lundi"}, //$NON-NLS-2$
                {0L,                        'B', "janvier"}, //$NON-NLS-2$
                {Long.MAX_VALUE,            'B', "ao\u00fbt"}, //$NON-NLS-2$
                {-1000L,                    'B', "janvier"}, //$NON-NLS-2$
                {new Date(1147327147578L),  'B', "mai"}, //$NON-NLS-2$
                {paris,                     'B', "mai"}, //$NON-NLS-2$
                {china,                     'B', "mai"}, //$NON-NLS-2$
                {0L,                        'C', "19"}, //$NON-NLS-2$
                {Long.MAX_VALUE,            'C', "2922789"}, //$NON-NLS-2$
                {-1000L,                    'C', "19"}, //$NON-NLS-2$
                {new Date(1147327147578L),  'C', "20"}, //$NON-NLS-2$
                {paris,                     'C', "20"}, //$NON-NLS-2$
                {china,                     'C', "20"}, //$NON-NLS-2$
                {0L,                        'D', "01/01/70"}, //$NON-NLS-2$
                {Long.MAX_VALUE,            'D', "08/17/94"}, //$NON-NLS-2$
                {-1000L,                    'D', "01/01/70"}, //$NON-NLS-2$
                {new Date(1147327147578L),  'D', "05/11/06"}, //$NON-NLS-2$
                {paris,                     'D', "05/08/06"}, //$NON-NLS-2$
                {china,                     'D', "05/08/06"}, //$NON-NLS-2$
                {0L,                        'F', "1970-01-01"}, //$NON-NLS-2$
                {Long.MAX_VALUE,            'F', "292278994-08-17"}, //$NON-NLS-2$
                {-1000L,                    'F', "1970-01-01"}, //$NON-NLS-2$
                {new Date(1147327147578L),  'F', "2006-05-11"}, //$NON-NLS-2$
                {paris,                     'F', "2006-05-08"}, //$NON-NLS-2$
                {china,                     'F', "2006-05-08"}, //$NON-NLS-2$
                {0L,                        'H', "08"}, //$NON-NLS-2$
                {Long.MAX_VALUE,            'H', "15"}, //$NON-NLS-2$
                {-1000L,                    'H', "07"}, //$NON-NLS-2$
                {new Date(1147327147578L),  'H', "13"}, //$NON-NLS-2$
                {paris,                     'H', "12"}, //$NON-NLS-2$
                {china,                     'H', "12"}, //$NON-NLS-2$
                {0L,                        'I', "08"}, //$NON-NLS-2$
                {Long.MAX_VALUE,            'I', "03"}, //$NON-NLS-2$
                {-1000L,                    'I', "07"}, //$NON-NLS-2$
                {new Date(1147327147578L),  'I', "01"}, //$NON-NLS-2$
                {paris,                     'I', "12"}, //$NON-NLS-2$
                {china,                     'I', "12"}, //$NON-NLS-2$
                {0L,                        'L', "000"}, //$NON-NLS-2$
                {Long.MAX_VALUE,            'L', "807"}, //$NON-NLS-2$
                {-1000L,                    'L', "000"}, //$NON-NLS-2$
                {new Date(1147327147578L),  'L', "578"}, //$NON-NLS-2$
                {paris,                     'L', "453"}, //$NON-NLS-2$
                {china,                     'L', "609"}, //$NON-NLS-2$
                {0L,                        'M', "00"}, //$NON-NLS-2$
                {Long.MAX_VALUE,            'M', "12"}, //$NON-NLS-2$
                {-1000L,                    'M', "59"}, //$NON-NLS-2$
                {new Date(1147327147578L),  'M', "59"}, //$NON-NLS-2$
                {paris,                     'M', "00"}, //$NON-NLS-2$
                {china,                     'M', "00"}, //$NON-NLS-2$
                {0L,                        'N', "000000000"}, //$NON-NLS-2$
                {Long.MAX_VALUE,            'N', "807000000"}, //$NON-NLS-2$
                {-1000L,                    'N', "000000000"}, //$NON-NLS-2$
                {new Date(1147327147578L),  'N', "578000000"}, //$NON-NLS-2$
                {paris,                     'N', "453000000"}, //$NON-NLS-2$
                {china,                     'N', "468000000"}, //$NON-NLS-2$
                {0L,                        'Q', "0"}, //$NON-NLS-2$
                {Long.MAX_VALUE,            'Q', "9223372036854775807"}, //$NON-NLS-2$
                {-1000L,                    'Q', "-1000"}, //$NON-NLS-2$
                {new Date(1147327147578L),  'Q', "1147327147578"}, //$NON-NLS-2$
                {paris,                     'Q', "1147082400453"}, //$NON-NLS-2$
                {china,                     'Q', "1147118400609"}, //$NON-NLS-2$
                {0L,                        'R', "08:00"}, //$NON-NLS-2$
                {Long.MAX_VALUE,            'R', "15:12"}, //$NON-NLS-2$
                {-1000L,                    'R', "07:59"}, //$NON-NLS-2$
                {new Date(1147327147578L),  'R', "13:59"}, //$NON-NLS-2$
                {paris,                     'R', "12:00"}, //$NON-NLS-2$
                {china,                     'R', "12:00"}, //$NON-NLS-2$
                {0L,                        'S', "00"}, //$NON-NLS-2$
                {Long.MAX_VALUE,            'S', "55"}, //$NON-NLS-2$
                {-1000L,                    'S', "59"}, //$NON-NLS-2$
                {new Date(1147327147578L),  'S', "07"}, //$NON-NLS-2$
                {paris,                     'S', "00"}, //$NON-NLS-2$
                {china,                     'S', "00"}, //$NON-NLS-2$
                {0L,                        'T', "08:00:00"}, //$NON-NLS-2$
                {Long.MAX_VALUE,            'T', "15:12:55"}, //$NON-NLS-2$
                {-1000L,                    'T', "07:59:59"}, //$NON-NLS-2$
                {new Date(1147327147578L),  'T', "13:59:07"}, //$NON-NLS-2$
                {paris,                     'T', "12:00:00"}, //$NON-NLS-2$
                {china,                     'T', "12:00:00"}, //$NON-NLS-2$
                {0L,                        'Y', "1970"}, //$NON-NLS-2$
                {Long.MAX_VALUE,            'Y', "292278994"}, //$NON-NLS-2$
                {-1000L,                    'Y', "1970"}, //$NON-NLS-2$
                {new Date(1147327147578L),  'Y', "2006"}, //$NON-NLS-2$
                {paris,                     'Y', "2006"}, //$NON-NLS-2$
                {china,                     'Y', "2006"}, //$NON-NLS-2$
                {0L,                        'Z', "CST"}, //$NON-NLS-2$
                {Long.MAX_VALUE,            'Z', "CST"}, //$NON-NLS-2$
                {-1000L,                    'Z', "CST"}, //$NON-NLS-2$
                {new Date(1147327147578L),  'Z', "CST"}, //$NON-NLS-2$
                {paris,                     'Z', "CEST"}, //$NON-NLS-2$
                {china,                     'Z', "GMT-08:00"}, //$NON-NLS-2$
                
        };

        final Object[][] upperCaseJapanTriple = {
                {0L,                        'A', "\u6728\u66dc\u65e5"}, //$NON-NLS-2$
                {Long.MAX_VALUE,            'A', "\u65e5\u66dc\u65e5"}, //$NON-NLS-2$
                {-1000L,                    'A', "\u6728\u66dc\u65e5"}, //$NON-NLS-2$
                {new Date(1147327147578L),  'A', "\u6728\u66dc\u65e5"}, //$NON-NLS-2$
                {paris,                     'A', "\u6708\u66dc\u65e5"}, //$NON-NLS-2$
                {china,                     'A', "\u6708\u66dc\u65e5"}, //$NON-NLS-2$
                {0L,                        'B', "1\u6708"}, //$NON-NLS-2$
                {Long.MAX_VALUE,            'B', "8\u6708"}, //$NON-NLS-2$
                {-1000L,                    'B', "1\u6708"}, //$NON-NLS-2$
                {new Date(1147327147578L),  'B', "5\u6708"}, //$NON-NLS-2$
                {paris,                     'B', "5\u6708"}, //$NON-NLS-2$
                {china,                     'B', "5\u6708"}, //$NON-NLS-2$
                {0L,                        'C', "19"}, //$NON-NLS-2$
                {Long.MAX_VALUE,            'C', "2922789"}, //$NON-NLS-2$
                {-1000L,                    'C', "19"}, //$NON-NLS-2$
                {new Date(1147327147578L),  'C', "20"}, //$NON-NLS-2$ 
                {paris,                     'C', "20"}, //$NON-NLS-2$
                {china,                     'C', "20"}, //$NON-NLS-2$
                {0L,                        'D', "01/01/70"}, //$NON-NLS-2$
                {Long.MAX_VALUE,            'D', "08/17/94"}, //$NON-NLS-2$
                {-1000L,                    'D', "01/01/70"}, //$NON-NLS-2$
                {new Date(1147327147578L),  'D', "05/11/06"}, //$NON-NLS-2$
                {paris,                     'D', "05/08/06"}, //$NON-NLS-2$
                {china,                     'D', "05/08/06"}, //$NON-NLS-2$
                {0L,                        'F', "1970-01-01"}, //$NON-NLS-2$
                {Long.MAX_VALUE,            'F', "292278994-08-17"}, //$NON-NLS-2$
                {-1000L,                    'F', "1970-01-01"}, //$NON-NLS-2$
                {new Date(1147327147578L),  'F', "2006-05-11"}, //$NON-NLS-2$
                {paris,                     'F', "2006-05-08"}, //$NON-NLS-2$
                {china,                     'F', "2006-05-08"}, //$NON-NLS-2$
                {0L,                        'H', "08"}, //$NON-NLS-2$
                {Long.MAX_VALUE,            'H', "15"}, //$NON-NLS-2$
                {-1000L,                    'H', "07"}, //$NON-NLS-2$
                {new Date(1147327147578L),  'H', "13"}, //$NON-NLS-2$
                {paris,                     'H', "12"}, //$NON-NLS-2$
                {china,                     'H', "12"}, //$NON-NLS-2$
                {0L,                        'I', "08"}, //$NON-NLS-2$
                {Long.MAX_VALUE,            'I', "03"}, //$NON-NLS-2$
                {-1000L,                    'I', "07"}, //$NON-NLS-2$
                {new Date(1147327147578L),  'I', "01"}, //$NON-NLS-2$
                {paris,                     'I', "12"}, //$NON-NLS-2$
                {china,                     'I', "12"}, //$NON-NLS-2$
                {0L,                        'L', "000"}, //$NON-NLS-2$
                {Long.MAX_VALUE,            'L', "807"}, //$NON-NLS-2$
                {-1000L,                    'L', "000"}, //$NON-NLS-2$
                {new Date(1147327147578L),  'L', "578"}, //$NON-NLS-2$
                {paris,                     'L', "453"}, //$NON-NLS-2$
                {china,                     'L', "609"}, //$NON-NLS-2$
                {0L,                        'M', "00"}, //$NON-NLS-2$
                {Long.MAX_VALUE,            'M', "12"}, //$NON-NLS-2$
                {-1000L,                    'M', "59"}, //$NON-NLS-2$
                {new Date(1147327147578L),  'M', "59"}, //$NON-NLS-2$
                {paris,                     'M', "00"}, //$NON-NLS-2$
                {china,                     'M', "00"}, //$NON-NLS-2$
                {0L,                        'N', "000000000"}, //$NON-NLS-2$
                {Long.MAX_VALUE,            'N', "807000000"}, //$NON-NLS-2$
                {-1000L,                    'N', "000000000"}, //$NON-NLS-2$
                {new Date(1147327147578L),  'N', "578000000"}, //$NON-NLS-2$
                {paris,                     'N', "453000000"}, //$NON-NLS-2$
                {china,                     'N', "468000000"}, //$NON-NLS-2$
                {0L,                        'Q', "0"}, //$NON-NLS-2$
                {Long.MAX_VALUE,            'Q', "9223372036854775807"}, //$NON-NLS-2$
                {-1000L,                    'Q', "-1000"}, //$NON-NLS-2$
                {new Date(1147327147578L),  'Q', "1147327147578"}, //$NON-NLS-2$
                {paris,                     'Q', "1147082400453"}, //$NON-NLS-2$
                {china,                     'Q', "1147118400609"}, //$NON-NLS-2$
                {0L,                        'R', "08:00"}, //$NON-NLS-2$
                {Long.MAX_VALUE,            'R', "15:12"}, //$NON-NLS-2$
                {-1000L,                    'R', "07:59"}, //$NON-NLS-2$
                {new Date(1147327147578L),  'R', "13:59"}, //$NON-NLS-2$
                {paris,                     'R', "12:00"}, //$NON-NLS-2$
                {china,                     'R', "12:00"}, //$NON-NLS-2$
                {0L,                        'S', "00"}, //$NON-NLS-2$
                {Long.MAX_VALUE,            'S', "55"}, //$NON-NLS-2$
                {-1000L,                    'S', "59"}, //$NON-NLS-2$
                {new Date(1147327147578L),  'S', "07"}, //$NON-NLS-2$
                {paris,                     'S', "00"}, //$NON-NLS-2$
                {china,                     'S', "00"}, //$NON-NLS-2$
                {0L,                        'T', "08:00:00"}, //$NON-NLS-2$
                {Long.MAX_VALUE,            'T', "15:12:55"}, //$NON-NLS-2$
                {-1000L,                    'T', "07:59:59"}, //$NON-NLS-2$
                {new Date(1147327147578L),  'T', "13:59:07"}, //$NON-NLS-2$
                {paris,                     'T', "12:00:00"}, //$NON-NLS-2$
                {china,                     'T', "12:00:00"}, //$NON-NLS-2$
                {0L,                        'Y', "1970"}, //$NON-NLS-2$
                {Long.MAX_VALUE,            'Y', "292278994"}, //$NON-NLS-2$
                {-1000L,                    'Y', "1970"}, //$NON-NLS-2$
                {new Date(1147327147578L),  'Y', "2006"}, //$NON-NLS-2$
                {paris,                     'Y', "2006"}, //$NON-NLS-2$
                {china,                     'Y', "2006"}, //$NON-NLS-2$
                {0L,                        'Z', "CST"}, //$NON-NLS-2$
                {Long.MAX_VALUE,            'Z', "CST"}, //$NON-NLS-2$
                {-1000L,                    'Z', "CST"}, //$NON-NLS-2$
                {new Date(1147327147578L),  'Z', "CST"}, //$NON-NLS-2$
                {paris,                     'Z', "CEST"}, //$NON-NLS-2$
                {china,                     'Z', "GMT-08:00"}, //$NON-NLS-2$
        };


        for (int i = 0; i < 90; i++) {
            String formatSpecifier = "%t" + upperCaseGermanTriple[i][pattern]; //$NON-NLS-2$
            String formatSpecifierUpper = "%T" + upperCaseGermanTriple[i][pattern]; //$NON-NLS-2$
                    if ((Character)upperCaseGermanTriple[i][pattern] == 'N') {
                        // result can't be predicted on RI, so skip this test
                        continue;
                    }
                    // test '%t'
                    f = new Formatter(Locale.JAPAN);
                    f.format(formatSpecifier, upperCaseJapanTriple[i][input]);
                    assertEquals("Format pattern: " + formatSpecifier //$NON-NLS-2$
                            + " Argument: " + upperCaseJapanTriple[i][input], //$NON-NLS-2$
                            upperCaseJapanTriple[i][output], f.toString());

                    f = new Formatter(Locale.JAPAN);
                    f.format(Locale.GERMAN, formatSpecifier, upperCaseGermanTriple[i][input]);
                    assertEquals("Format pattern: " + formatSpecifier //$NON-NLS-2$
                            + " Argument: " + upperCaseGermanTriple[i][input], //$NON-NLS-2$
                            upperCaseGermanTriple[i][output], f.toString());

                    f = new Formatter(Locale.JAPAN);
                    f.format(Locale.FRANCE, formatSpecifier, upperCaseFranceTriple[i][input]);
                    assertEquals("Format pattern: " + formatSpecifier //$NON-NLS-2$
                            + " Argument: " + upperCaseFranceTriple[i][input], //$NON-NLS-2$
                            upperCaseFranceTriple[i][output], f.toString());

                    // test '%T'
                    f = new Formatter(Locale.GERMAN);
                    f.format(formatSpecifierUpper, upperCaseGermanTriple[i][input]);
                    assertEquals("Format pattern: " + formatSpecifierUpper //$NON-NLS-2$
                            + " Argument: " + upperCaseGermanTriple[i][input], //$NON-NLS-2$
                            ((String)upperCaseGermanTriple[i][output])
                                    .toUpperCase(Locale.US), f.toString());

                    f = new Formatter(Locale.GERMAN);
                    f.format(Locale.JAPAN, formatSpecifierUpper, upperCaseJapanTriple[i][input]);
                    assertEquals("Format pattern: " + formatSpecifierUpper //$NON-NLS-2$
                            + " Argument: " + upperCaseJapanTriple[i][input], //$NON-NLS-2$
                            ((String)upperCaseJapanTriple[i][output])
                                    .toUpperCase(Locale.US), f.toString());

                    f = new Formatter(Locale.GERMAN);
                    f.format(Locale.FRANCE, formatSpecifierUpper, upperCaseFranceTriple[i][input]);
                    assertEquals("Format pattern: " + formatSpecifierUpper //$NON-NLS-2$
                            + " Argument: " + upperCaseFranceTriple[i][input], //$NON-NLS-2$
                            ((String)upperCaseFranceTriple[i][output])
                                    .toUpperCase(Locale.US), f.toString());
        }

        f = new Formatter(Locale.US);
        f.format("%-10ta", now); //$NON-NLS-2$
        assertEquals("Thu       ", f.toString()); //$NON-NLS-2$

        f = new Formatter(Locale.US);
        f.format("%10000000000000000000000000000000001ta", now); //$NON-NLS-2$
        assertEquals("Thu", f.toString().trim()); //$NON-NLS-2$
    }

    /**
     * @tests java.util.Formatter#format(String, Object...) for null argment for
     *        Byte/Short/Integer/Long/BigInteger conversion
     */
    public void test_formatLjava_lang_String$Ljava_lang_Object_ByteShortIntegerLongNullConversion() {

        Formatter f = new Formatter(Locale.FRANCE);
        f.format("%d%<o%<x%<5X", (Integer) null);
        assertEquals("nullnullnull NULL", f.toString());

        f = new Formatter(Locale.GERMAN);
        f.format("%d%<#03o %<0#4x%<6X", (Long) null);
        assertEquals("nullnull null  NULL", f.toString());

        f = new Formatter(Locale.GERMAN);
        f.format("%(+,07d%<o %<x%<6X", (Byte) null);
        assertEquals("   nullnull null  NULL", f.toString());

        f = new Formatter(Locale.ITALY);
        f.format("%(+,07d%<o %<x%<0#6X", (Short) null);
        assertEquals("   nullnull null  NULL", f.toString());

        f = new Formatter(Locale.GERMAN);
        f.format("%(+,-7d%<( o%<+(x %<( 06X", (BigInteger) null);
        assertEquals("null   nullnull   NULL", f.toString());
    }

    /**
     * Setup resource files for testing
     */
    protected void setUp() throws IOException {
        notExist = File.createTempFile("notexist", null);
        notExist.delete();

        fileWithContent = File.createTempFile("filewithcontent", null);
        BufferedOutputStream bw = new BufferedOutputStream(
                new FileOutputStream(fileWithContent));
        bw.write(1);// write something into the file
        bw.close();

        readOnly = File.createTempFile("readonly", null);
        readOnly.setReadOnly();

        secret = File.createTempFile("secret", null);
        
        defaultTimeZone = TimeZone.getDefault();
        TimeZone cst = TimeZone.getTimeZone("Asia/Shanghai");
        TimeZone.setDefault(cst);
    }

    /**
     * Delete the resource files if they exist
     */
    protected void tearDown() {
        if (notExist.exists()) {
            notExist.delete();
        }

        if (fileWithContent.exists()) {
            fileWithContent.delete();
        }
        if (readOnly.exists()) {
            readOnly.delete();
        }
        if (secret.exists()) {
            secret.delete();
        }
        
        TimeZone.setDefault(defaultTimeZone);
    }
}
