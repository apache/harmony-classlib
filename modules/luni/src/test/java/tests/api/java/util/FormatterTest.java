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
import java.io.Flushable;
import java.io.IOException;
import java.io.PipedOutputStream;
import java.io.PrintStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.security.Permission;
import java.util.Formatter;
import java.util.FormatterClosedException;
import java.util.Locale;
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
		public void checkWrite(String filename) {
			throw new SecurityException();
		}

		public void checkPermission(Permission p) {
			if (p.getActions().equals("setSecurityManager")) {
				return;
			}
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

		try {
			f = new Formatter("notexist");
			assertEquals(f.locale(), Locale.getDefault());
			f.close();
		} catch (FileNotFoundException e) {
			fail("File can not be created");
		}

		f = new Formatter(fileWithContent.getPath());
		assertEquals(0, fileWithContent.length());

		f.close();

		try {
			f = new Formatter(readOnly.getPath());
			fail("FileNotFoundException is expected");
		} catch (FileNotFoundException e) {
			// expected
		}

		SecurityManager oldsm = System.getSecurityManager();
		try {
			System.setSecurityManager(new MockSecurityManager());
			f = new Formatter(secret.getPath());
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
			f = new Formatter("notexist", null);
			fail("should throw NullPointerException");
		} catch (NullPointerException e2) {
			// expected
		}

		f = new Formatter("notexist", Charset.defaultCharset().name());
		assertEquals(f.locale(), Locale.getDefault());
		f.close();

		try {
			f = new Formatter("notexist", "ISO 1111-1");
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
		try {
			System.setSecurityManager(new MockSecurityManager());
			f = new Formatter(secret.getPath(), "UTF-16BE");
			fail("should throw UnsupportedEncodingException");
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
			f = new Formatter("notexist", null, Locale.KOREA);
			fail("should throw NullPointerException");
		} catch (NullPointerException e2) {
			// expected
		}

		f = new Formatter("notexist", Charset.defaultCharset().name(), null);
		assertNotNull(f);
		f.close();

		f = new Formatter("notexist", Charset.defaultCharset().name(),
				Locale.KOREA);
		assertEquals(f.locale(), Locale.KOREA);
		f.close();

		try {
			f = new Formatter("notexist", "ISO 1111-1", Locale.CHINA);
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
		try {
			System.setSecurityManager(new MockSecurityManager());
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
		try {
			System.setSecurityManager(new MockSecurityManager());
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
		try {
			System.setSecurityManager(new MockSecurityManager());
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
				assertTrue(notExist.delete());
				// Fail on RI on Windows, because output stream is created and
				// not closed when exception thrown
			}
		}

		try {
			f = new Formatter(notExist, "ISO 1111-1");
			fail("should throw UnsupportedEncodingException");
		} catch (UnsupportedEncodingException e1) {
			// expected
		} finally {
			if (notExist.exists()) {
				assertTrue(notExist.delete());
				// Fail on RI on Windows, because output stream is created and
				// not closed when exception thrown
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
		try {
			System.setSecurityManager(new MockSecurityManager());
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

		// For destination that does not implement Flushable
		f = new Formatter();
		f.flush();
		// No exception should be throw
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
		f.ioException();
	}

	/**
	 * @tests java.util.Formatter#ioException()
	 */
	public void test_ioException() throws IOException {
		Formatter f = null;
		FileOutputStream fos;
		fos = new FileOutputStream(notExist);
		f = new Formatter(fos);
		assertNull(f.ioException());
		fos.close();
		f.flush();
		f.close();
	}

	/**
	 * Setup resource files for testing
	 */
	protected void setUp() throws IOException {
		notExist = new File("notexist");
		if (notExist.exists()) {
			notExist.delete();
		}

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