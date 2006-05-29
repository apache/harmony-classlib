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
import java.io.PipedOutputStream;
import java.io.PrintStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.security.Permission;
import java.util.Arrays;
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
import java.util.UnknownFormatConversionException;
import java.nio.charset.Charset;
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
	public void test_formatLjava_lang_String$Ljava_lang_Object_LineSperator() {
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
		final String[] illFlags = { "%-n", "%+n", "%#n", "% n", "%0n", "%,n",
				"%(n" };
		for (int i = 0; i < illFlags.length; i++) {
			try {
				f.format(illFlags[i]);
				fail("should throw IllegalFormatFlagsException: " + illFlags[i]);
			} catch (IllegalFormatFlagsException e) {
				// expected
			}
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
	 *        conversion
	 */
	public void test_formatLjava_lang_String$Ljava_lang_Object_GeneralConversion() {
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

		try {
			f.format("%-.8s", "something");
			fail("should throw MissingFormatWidthException");
		} catch (MissingFormatWidthException e) {
			// expected
		}

		final Object[] args = { Boolean.FALSE, Boolean.TRUE,
				new Character('c'), new Byte((byte) 0x01),
				new Short((short) 0x0001), new Integer(1), new Float(1.1f),
				new Double(1.1d), "", "string content", new MockFormattable(),
				(Object) null };

		final String[] bResult = { " fa", "false", "fa", " tr", "true", "tr",
				" tr", "true", "tr", " tr", "true", "tr", " tr", "true", "tr",
				" tr", "true", "tr", " tr", "true", "tr", " tr", "true", "tr",
				" tr", "true", "tr", " tr", "true", "tr", " tr", "true", "tr",
				" fa", "false", "fa" };
		for (int i = 0, resultCount = 0; i < args.length; i++) {
			f = new Formatter(Locale.JAPANESE);
			f.format("prefix%3.2b", args[i]);
			assertEquals("prefix" + bResult[resultCount], f.toString());

			f = new Formatter(Locale.JAPANESE);
			f.format("prefix%3.2B", args[i]);
			assertEquals("prefix" + bResult[resultCount++].toUpperCase(), f
					.toString());

			f = new Formatter(Locale.KOREA);
			f.format("%-4.6b", args[i]);
			assertEquals(bResult[resultCount], f.toString());

			f = new Formatter(Locale.KOREA);
			f.format("%-4.6B", args[i]);
			assertEquals(bResult[resultCount++].toUpperCase(), f.toString());

			f = new Formatter(Locale.ITALY);
			f.format("%.2bsuffix", args[i]);
			assertEquals(bResult[resultCount] + "suffix", f.toString());

			f = new Formatter(Locale.ITALY);
			f.format("%.2Bsuffix", args[i]);
			assertEquals(bResult[resultCount++].toUpperCase() + "suffix", f
					.toString());
		}

		final String[] sResult = { "fal", "fals  ", "false", "tru", "true  ",
				"true", " c", "c     ", "c", " 1", "1     ", "1", " 1",
				"1     ", "1", " 1", "1     ", "1", "1.1", "1.1   ", "1.1",
				"1.1", "1.1   ", "1.1", "  ", "      ", "", "str", "stri  ",
				"strin", "customized format function width: 2 precision: 3",
				"customized format function width: 6 precision: 4",
				"customized format function width: -1 precision: 5", "nul",
				"null  ", "null" };
		for (int i = 0, resultCount = 0; i < args.length; i++) {
			f = new Formatter(Locale.CHINA);
			f.format("%2.3s", args[i]);
			assertEquals(sResult[resultCount], f.toString());

			f = new Formatter(Locale.CHINA);
			f.format("%2.3S", args[i]);
			assertEquals(sResult[resultCount++].toUpperCase(), f.toString());

			f = new Formatter(Locale.KOREA);
			f.format("%-6.4s", args[i]);
			assertEquals(sResult[resultCount], f.toString());

			f = new Formatter(Locale.KOREA);
			f.format("%-6.4S", args[i]);
			assertEquals(sResult[resultCount++].toUpperCase(), f.toString());

			f = new Formatter(Locale.GERMAN);
			f.format("%.5s", args[i]);
			assertEquals(sResult[resultCount], f.toString());

			f = new Formatter(Locale.GERMAN);
			f.format("%.5S", args[i]);
			assertEquals(sResult[resultCount++].toUpperCase(), f.toString());
		}

		// skip args[length-1]: null, do it seperately
		for (int i = 0; i < args.length - 1; i++) {
			f = new Formatter(Locale.CHINA);
			f.format("%h", args[i]);
			assertEquals(Integer.toHexString(args[i].hashCode()), f.toString());

			f = new Formatter(Locale.CHINA);
			f.format("%H", args[i]);
			assertEquals(Integer.toHexString(args[i].hashCode()).toUpperCase(),
					f.toString());
		}

		/*
		 * In Turkish locale, the upper case of '\u0069' is '\u0130'. The
		 * following test indicate that '\u0069' is coverted to upper case
		 * without using the turkish locale.
		 */
		f = new Formatter(new Locale("tr"));
		f.format("%S", "\u0069");
		assertEquals("\u0049", f.toString());

		f = new Formatter(Locale.GERMAN);
		for (int i = 0; i < args.length; i++) {
			if (!(args[i] instanceof Formattable)) {
				try {
					f.format("%#s", args[i]);
					/*
					 * fail on RI If the '#' flag is present and the argument is
					 * not a Formattable , then a
					 * FormatFlagsConversionMismatchException should be thrown.
					 */
					fail("should throw FormatFlagsConversionMismatchException");
				} catch (FormatFlagsConversionMismatchException e) {
					// expected
				}
			} else {
				f.format("%#s%<-#8s", args[i]);
				assertEquals(
						"customized format function width: -1 precision: -1customized format function width: 8 precision: -1",
						f.toString());
			}
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

		final Object[] legalArgs = { 'c', '\u0123', (byte) 0x11,
				(short) 0x1111, 0x11 };
		final Object[] result = { "c", "c ", "\u0123", "\u0123 ", "\u0011",
				"\u0011 ", "\u1111", "\u1111 ", "\u0011", "\u0011 " };

		for (int i = 0, resultCount = 0; i < legalArgs.length; i++) {
			f = new Formatter(Locale.US);
			f.format("pre%c", legalArgs[i]);
			assertEquals("pre" + result[resultCount++], f.toString());

			f = new Formatter(Locale.GERMANY);
			f.format("%-2c.txt", legalArgs[i]);
			assertEquals(result[resultCount++] + ".txt", f.toString());
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
	}
}