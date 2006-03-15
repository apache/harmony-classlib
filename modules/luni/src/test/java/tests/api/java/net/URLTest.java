/* Copyright 1998, 2005 The Apache Software Foundation or its licensors, as applicable
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

package tests.api.java.net;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLStreamHandler;
import java.net.URLStreamHandlerFactory;

import tests.support.Support_Configuration;
import tests.support.resource.Support_Resources;

public class URLTest extends junit.framework.TestCase {

	public static class MyHandler extends URLStreamHandler {
		protected URLConnection openConnection(URL u)
				throws java.io.IOException {
			return null;
		}
	}

	URL u;

	URL u1;

	URL u2;

	URL u3;

	URL u4;

	URL u5;

	URL u6;

	boolean caught = false;

	/**
	 * @tests java.net.URL#setURLStreamHandlerFactory(java.net.URLStreamHandlerFactory)
	 */
	public void test_setURLStreamHandlerFactoryLjava_net_URLStreamHandlerFactory() {
		// Test for method void
		// java.net.URL.setURLStreamHandlerFactory(java.net.URLStreamHandlerFactory)
		class TestFactory implements URLStreamHandlerFactory {
			public TestFactory() {
			}

			public URLStreamHandler createURLStreamHandler(String protocol) {
				return null;
			}
		}
		try {
			u = new URL("http://www.yahoo.com");
			u.setURLStreamHandlerFactory(new TestFactory());
		} catch (Exception e) {
			fail("Exception during test : " + e.getMessage());
		}
		try {
			u.setURLStreamHandlerFactory(new TestFactory());
			fail("Can only set Factory once.");
		} catch (Error e) {
		}
	}

	/**
	 * @tests java.net.URL#URL(java.lang.String)
	 */
	public void test_ConstructorLjava_lang_String() {
		// Test for method java.net.URL(java.lang.String)
		// Tests for multiple URL instantiation
		try {
			// basic parsing test
			u = new URL(
					"http://www.yahoo1.com:8080/dir1/dir2/test.cgi?point1.html#anchor1");
			assertTrue("u returns a wrong protocol", u.getProtocol().equals(
					"http"));
			assertTrue("u returns a wrong host", u.getHost().equals(
					"www.yahoo1.com"));
			assertTrue("u returns a wrong port", u.getPort() == 8080);
			assertTrue("u returns a wrong file", u.getFile().equals(
					"/dir1/dir2/test.cgi?point1.html"));
			assertTrue("u returns a wrong anchor", u.getRef().equals("anchor1"));

			// test for no file
			u1 = new URL("http://www.yahoo2.com:9999");
			assertTrue("u1 returns a wrong protocol", u1.getProtocol().equals(
					"http"));
			assertTrue("u1 returns a wrong host", u1.getHost().equals(
					"www.yahoo2.com"));
			assertTrue("u1 returns a wrong port", u1.getPort() == 9999);
			assertTrue("u1 returns a wrong file", u1.getFile().equals(""));
			assertTrue("u1 returns a wrong anchor", u1.getRef() == null);

			// test for no port
			u2 = new URL(
					"http://www.yahoo3.com/dir1/dir2/test.cgi?point1.html#anchor1");
			assertTrue("u2 returns a wrong protocol", u2.getProtocol().equals(
					"http"));
			assertTrue("u2 returns a wrong host", u2.getHost().equals(
					"www.yahoo3.com"));
			assertTrue("u2 returns a wrong port", u2.getPort() == -1);
			assertTrue("u2 returns a wrong file", u2.getFile().equals(
					"/dir1/dir2/test.cgi?point1.html"));
			assertTrue("u2 returns a wrong anchor", u2.getRef().equals(
					"anchor1"));

			// test for no port
			URL u2a = new URL(
					"file://www.yahoo3.com/dir1/dir2/test.cgi#anchor1");
			assertTrue("u2a returns a wrong protocol", u2a.getProtocol()
					.equals("file"));
			assertTrue("u2a returns a wrong host", u2a.getHost().equals(
					"www.yahoo3.com"));
			assertTrue("u2a returns a wrong port", u2a.getPort() == -1);
			assertTrue("u2a returns a wrong file", u2a.getFile().equals(
					"/dir1/dir2/test.cgi"));
			assertTrue("u2a returns a wrong anchor", u2a.getRef().equals(
					"anchor1"));

			// test for no file, no port
			u3 = new URL("http://www.yahoo4.com/");
			assertTrue("u3 returns a wrong protocol", u3.getProtocol().equals(
					"http"));
			assertTrue("u3 returns a wrong host", u3.getHost().equals(
					"www.yahoo4.com"));
			assertTrue("u3 returns a wrong port", u3.getPort() == -1);
			assertTrue("u3 returns a wrong file", u3.getFile().equals("/"));
			assertTrue("u3 returns a wrong anchor", u3.getRef() == null);

			// test for no file, no port
			URL u3a = new URL("file://www.yahoo4.com/");
			assertTrue("u3a returns a wrong protocol", u3a.getProtocol()
					.equals("file"));
			assertTrue("u3a returns a wrong host", u3a.getHost().equals(
					"www.yahoo4.com"));
			assertTrue("u3a returns a wrong port", u3a.getPort() == -1);
			assertTrue("u3a returns a wrong file", u3a.getFile().equals("/"));
			assertTrue("u3a returns a wrong anchor", u3a.getRef() == null);

			// test for no file, no port
			URL u3b = new URL("file://www.yahoo4.com");
			assertTrue("u3b returns a wrong protocol", u3b.getProtocol()
					.equals("file"));
			assertTrue("u3b returns a wrong host", u3b.getHost().equals(
					"www.yahoo4.com"));
			assertTrue("u3b returns a wrong port", u3b.getPort() == -1);
			assertTrue("u3b returns a wrong file", u3b.getFile().equals(""));
			assertTrue("u3b returns a wrong anchor", u3b.getRef() == null);

			// test for non-port ":" and wierd characters occurences
			u4 = new URL(
					"http://www.yahoo5.com/di!@$%^&*()_+r1/di:::r2/test.cgi?point1.html#anchor1");
			assertTrue("u4 returns a wrong protocol", u4.getProtocol().equals(
					"http"));
			assertTrue("u4 returns a wrong host", u4.getHost().equals(
					"www.yahoo5.com"));
			assertTrue("u4 returns a wrong port", u4.getPort() == -1);
			assertTrue("u4 returns a wrong file", u4.getFile().equals(
					"/di!@$%^&*()_+r1/di:::r2/test.cgi?point1.html"));
			assertTrue("u4 returns a wrong anchor", u4.getRef().equals(
					"anchor1"));

			u5 = new URL("file:/testing.tst");
			assertTrue("u5 returns a wrong protocol", u5.getProtocol().equals(
					"file"));
			assertTrue("u5 returns a wrong host", u5.getHost().equals(""));
			assertTrue("u5 returns a wrong port", u5.getPort() == -1);
			assertTrue("u5 returns a wrong file", u5.getFile().equals(
					"/testing.tst"));
			assertTrue("u5 returns a wrong anchor", u5.getRef() == null);

			URL u5a = new URL("file:testing.tst");
			assertTrue("u5a returns a wrong protocol", u5a.getProtocol()
					.equals("file"));
			assertTrue("u5a returns a wrong host", u5a.getHost().equals(""));
			assertTrue("u5a returns a wrong port", u5a.getPort() == -1);
			assertTrue("u5a returns a wrong file", u5a.getFile().equals(
					"testing.tst"));
			assertTrue("u5a returns a wrong anchor", u5a.getRef() == null);

			URL u6 = new URL("http://host:/file");
			assertTrue("u6 return a wrong port", u6.getPort() == -1);

			URL u7 = new URL("file:../../file.txt");
			assertTrue("u7 returns a wrong file: " + u7.getFile(), u7.getFile()
					.equals("../../file.txt"));

			URL u8 = new URL("http://[fec0::1:20d:60ff:fe24:7410]:35/file.txt");
			assertTrue("u8 returns a wrong protocol " + u8.getProtocol(), u8
					.getProtocol().equals("http"));
			assertTrue("u8 returns a wrong host " + u8.getHost(), u8.getHost()
					.equals("[fec0::1:20d:60ff:fe24:7410]"));
			assertTrue("u8 returns a wrong port " + u8.getPort(),
					u8.getPort() == 35);
			assertTrue("u8 returns a wrong file " + u8.getFile(), u8.getFile()
					.equals("/file.txt"));
			assertTrue("u8 returns a wrong anchor " + u8.getRef(),
					u8.getRef() == null);

			URL u9 = new URL(
					"file://[fec0::1:20d:60ff:fe24:7410]/file.txt#sogood");
			assertTrue("u9 returns a wrong protocol " + u9.getProtocol(), u9
					.getProtocol().equals("file"));
			assertTrue("u9 returns a wrong host " + u9.getHost(), u9.getHost()
					.equals("[fec0::1:20d:60ff:fe24:7410]"));
			assertTrue("u9 returns a wrong port " + u9.getPort(),
					u9.getPort() == -1);
			assertTrue("u9 returns a wrong file " + u9.getFile(), u9.getFile()
					.equals("/file.txt"));
			assertTrue("u9 returns a wrong anchor " + u9.getRef(), u9.getRef()
					.equals("sogood"));

			URL u10 = new URL("file://[fec0::1:20d:60ff:fe24:7410]");
			assertTrue("u10 returns a wrong protocol " + u10.getProtocol(), u10
					.getProtocol().equals("file"));
			assertTrue("u10 returns a wrong host " + u10.getHost(), u10
					.getHost().equals("[fec0::1:20d:60ff:fe24:7410]"));
			assertTrue("u10 returns a wrong port " + u10.getPort(), u10
					.getPort() == -1);

		} catch (Exception e) {
			fail("Exception during tests : " + e.getMessage());
		}

		// test for error catching

		// Bad HTTP format - no "//"

		try {
			u = new URL(
					"http:www.yahoo5.com::22/dir1/di:::r2/test.cgi?point1.html#anchor1");
		} catch (MalformedURLException e) {
			fail("1 Should not have thrown MalformedURLException : "
					+ e.getMessage());
		} catch (Exception e) {
			fail("1 Threw exception : " + e.getMessage());
		}

		caught = false;
		try {
			u = new URL(
					"http://www.yahoo5.com::22/dir1/di:::r2/test.cgi?point1.html#anchor1");
		} catch (MalformedURLException e) {
			caught = true;
		} catch (Exception e) {
			fail("2 Threw exception : " + e.getMessage());
		}
		assertTrue("Should have throw MalformedURLException", caught);

		// unknown protocol
		try {
			u = new URL("myProtocol://www.yahoo.com:22");
		} catch (MalformedURLException e) {
			caught = true;
		} catch (Exception e) {
			fail("3 Threw the wrong kind of exception : " + e);
		}
		assertTrue("3 Failed to throw MalformedURLException", caught);

		caught = false;
		// no protocol
		try {
			u = new URL("www.yahoo.com");
		} catch (MalformedURLException e) {
			caught = true;
		} catch (Exception e) {
			fail("4 Threw the wrong kind of exception : " + e);
		}
		assertTrue("4 Failed to throw MalformedURLException", caught);

		caught = false;

		URL u1 = null;
		try {
			// No leading or trailing spaces.
			u1 = new URL("file:/some/path");
			assertEquals("5 got wrong file length1", 10, u1.getFile().length());

			// Leading spaces.
			u1 = new URL("  file:/some/path");
			assertEquals("5 got wrong file length2", 10, u1.getFile().length());

			// Trailing spaces.
			u1 = new URL("file:/some/path  ");
			assertEquals("5 got wrong file length3", 10, u1.getFile().length());

			// Leading and trailing.
			u1 = new URL("  file:/some/path ");
			assertEquals("5 got wrong file length4", 10, u1.getFile().length());

			// in-place spaces.
			u1 = new URL("  file:  /some/path ");
			assertEquals("5 got wrong file length5", 12, u1.getFile().length());

		} catch (MalformedURLException e) {
			fail("5 Did not expect the exception " + e);
		}
	}

	/**
	 * @tests java.net.URL#URL(java.net.URL, java.lang.String)
	 */
	public void test_ConstructorLjava_net_URLLjava_lang_String() {
		// Test for method java.net.URL(java.net.URL, java.lang.String)
		try {
			u = new URL("http://www.yahoo.com");
			URL uf = new URL("file://www.yahoo.com");
			// basic ones
			u1 = new URL(u, "file.java");
			assertTrue("1 returns a wrong protocol", u1.getProtocol().equals(
					"http"));
			assertTrue("1 returns a wrong host", u1.getHost().equals(
					"www.yahoo.com"));
			assertTrue("1 returns a wrong port", u1.getPort() == -1);
			assertTrue("1 returns a wrong file", u1.getFile().equals(
					"/file.java"));
			assertTrue("1 returns a wrong anchor", u1.getRef() == null);

			URL u1f = new URL(uf, "file.java");
			assertTrue("1f returns a wrong protocol", u1f.getProtocol().equals(
					"file"));
			assertTrue("1f returns a wrong host", u1f.getHost().equals(
					"www.yahoo.com"));
			assertTrue("1f returns a wrong port", u1f.getPort() == -1);
			assertTrue("1f returns a wrong file", u1f.getFile().equals(
					"/file.java"));
			assertTrue("1f returns a wrong anchor", u1f.getRef() == null);

			u1 = new URL(u, "dir1/dir2/../file.java");
			assertTrue("3 returns a wrong protocol", u1.getProtocol().equals(
					"http"));
			assertTrue("3 returns a wrong host: " + u1.getHost(), u1.getHost()
					.equals("www.yahoo.com"));
			assertTrue("3 returns a wrong port", u1.getPort() == -1);
			assertTrue("3 returns a wrong file", u1.getFile().equals(
					"/dir1/dir2/../file.java"));
			assertTrue("3 returns a wrong anchor", u1.getRef() == null);

			u1 = new URL(u, "http:dir1/dir2/../file.java");
			assertTrue("3a returns a wrong protocol", u1.getProtocol().equals(
					"http"));
			assertTrue("3a returns a wrong host: " + u1.getHost(), u1.getHost()
					.equals(""));
			assertTrue("3a returns a wrong port", u1.getPort() == -1);
			assertTrue("3a returns a wrong file", u1.getFile().equals(
					"dir1/dir2/../file.java"));
			assertTrue("3a returns a wrong anchor", u1.getRef() == null);

			u = new URL("http://www.apache.org/testing/");
			u1 = new URL(u, "file.java");
			assertTrue("4 returns a wrong protocol", u1.getProtocol().equals(
					"http"));
			assertTrue("4 returns a wrong host", u1.getHost().equals(
					"www.apache.org"));
			assertTrue("4 returns a wrong port", u1.getPort() == -1);
			assertTrue("4 returns a wrong file", u1.getFile().equals(
					"/testing/file.java"));
			assertTrue("4 returns a wrong anchor", u1.getRef() == null);

			uf = new URL("file://www.apache.org/testing/");
			u1f = new URL(uf, "file.java");
			assertTrue("4f returns a wrong protocol", u1f.getProtocol().equals(
					"file"));
			assertTrue("4f returns a wrong host", u1f.getHost().equals(
					"www.apache.org"));
			assertTrue("4f returns a wrong port", u1f.getPort() == -1);
			assertTrue("4f returns a wrong file", u1f.getFile().equals(
					"/testing/file.java"));
			assertTrue("4f returns a wrong anchor", u1f.getRef() == null);

			uf = new URL("file:/testing/");
			u1f = new URL(uf, "file.java");
			assertTrue("4fa returns a wrong protocol", u1f.getProtocol()
					.equals("file"));
			assertTrue("4fa returns a wrong host", u1f.getHost().equals(""));
			assertTrue("4fa returns a wrong port", u1f.getPort() == -1);
			assertTrue("4fa returns a wrong file", u1f.getFile().equals(
					"/testing/file.java"));
			assertTrue("4fa returns a wrong anchor", u1f.getRef() == null);

			uf = new URL("file:testing/");
			u1f = new URL(uf, "file.java");
			assertTrue("4fb returns a wrong protocol", u1f.getProtocol()
					.equals("file"));
			assertTrue("4fb returns a wrong host", u1f.getHost().equals(""));
			assertTrue("4fb returns a wrong port", u1f.getPort() == -1);
			assertTrue("4fb returns a wrong file", u1f.getFile().equals(
					"testing/file.java"));
			assertTrue("4fb returns a wrong anchor", u1f.getRef() == null);

			u1f = new URL(uf, "file:file.java");
			assertTrue("4fc returns a wrong protocol", u1f.getProtocol()
					.equals("file"));
			assertTrue("4fc returns a wrong host", u1f.getHost().equals(""));
			assertTrue("4fc returns a wrong port", u1f.getPort() == -1);
			assertTrue("4fc returns a wrong file", u1f.getFile().equals(
					"file.java"));
			assertTrue("4fc returns a wrong anchor", u1f.getRef() == null);

			u1f = new URL(uf, "file:");
			assertTrue("4fd returns a wrong protocol", u1f.getProtocol()
					.equals("file"));
			assertTrue("4fd returns a wrong host", u1f.getHost().equals(""));
			assertTrue("4fd returns a wrong port", u1f.getPort() == -1);
			assertTrue("4fd returns a wrong file", u1f.getFile().equals(""));
			assertTrue("4fd returns a wrong anchor", u1f.getRef() == null);

			u = new URL("http://www.apache.org/testing");
			u1 = new URL(u, "file.java");
			assertTrue("5 returns a wrong protocol", u1.getProtocol().equals(
					"http"));
			assertTrue("5 returns a wrong host", u1.getHost().equals(
					"www.apache.org"));
			assertTrue("5 returns a wrong port", u1.getPort() == -1);
			assertTrue("5 returns a wrong file", u1.getFile().equals(
					"/file.java"));
			assertTrue("5 returns a wrong anchor", u1.getRef() == null);

			uf = new URL("file://www.apache.org/testing");
			u1f = new URL(uf, "file.java");
			assertTrue("5f returns a wrong protocol", u1f.getProtocol().equals(
					"file"));
			assertTrue("5f returns a wrong host", u1f.getHost().equals(
					"www.apache.org"));
			assertTrue("5f returns a wrong port", u1f.getPort() == -1);
			assertTrue("5f returns a wrong file", u1f.getFile().equals(
					"/file.java"));
			assertTrue("5f returns a wrong anchor", u1f.getRef() == null);

			uf = new URL("file:/testing");
			u1f = new URL(uf, "file.java");
			assertTrue("5fa returns a wrong protocol", u1f.getProtocol()
					.equals("file"));
			assertTrue("5fa returns a wrong host", u1f.getHost().equals(""));
			assertTrue("5fa returns a wrong port", u1f.getPort() == -1);
			assertTrue("5fa returns a wrong file", u1f.getFile().equals(
					"/file.java"));
			assertTrue("5fa returns a wrong anchor", u1f.getRef() == null);

			uf = new URL("file:testing");
			u1f = new URL(uf, "file.java");
			assertTrue("5fb returns a wrong protocol", u1f.getProtocol()
					.equals("file"));
			assertTrue("5fb returns a wrong host", u1f.getHost().equals(""));
			assertTrue("5fb returns a wrong port", u1f.getPort() == -1);
			assertTrue("5fb returns a wrong file", u1f.getFile().equals(
					"file.java"));
			assertTrue("5fb returns a wrong anchor", u1f.getRef() == null);

			u = new URL("http://www.apache.org/testing/foobaz");
			u1 = new URL(u, "/file.java");
			assertTrue("6 returns a wrong protocol", u1.getProtocol().equals(
					"http"));
			assertTrue("6 returns a wrong host", u1.getHost().equals(
					"www.apache.org"));
			assertTrue("6 returns a wrong port", u1.getPort() == -1);
			assertTrue("6 returns a wrong file", u1.getFile().equals(
					"/file.java"));
			assertTrue("6 returns a wrong anchor", u1.getRef() == null);

			uf = new URL("file://www.apache.org/testing/foobaz");
			u1f = new URL(uf, "/file.java");
			assertTrue("6f returns a wrong protocol", u1f.getProtocol().equals(
					"file"));
			assertTrue("6f returns a wrong host", u1f.getHost().equals(
					"www.apache.org"));
			assertTrue("6f returns a wrong port", u1f.getPort() == -1);
			assertTrue("6f returns a wrong file", u1f.getFile().equals(
					"/file.java"));
			assertTrue("6f returns a wrong anchor", u1f.getRef() == null);

			u = new URL("http://www.apache.org:8000/testing/foobaz");
			u1 = new URL(u, "/file.java");
			assertTrue("7 returns a wrong protocol", u1.getProtocol().equals(
					"http"));
			assertTrue("7 returns a wrong host", u1.getHost().equals(
					"www.apache.org"));
			assertTrue("7 returns a wrong port", u1.getPort() == 8000);
			assertTrue("7 returns a wrong file", u1.getFile().equals(
					"/file.java"));
			assertTrue("7 returns a wrong anchor", u1.getRef() == null);

			u = new URL("http://www.apache.org/index.html");
			u1 = new URL(u, "#bar");
			assertTrue("8 returns a wrong host", u1.getHost().equals(
					"www.apache.org"));
			assertTrue("8 returns a wrong file", u1.getFile().equals(
					"/index.html"));
			assertTrue("8 returns a wrong anchor", u1.getRef().equals("bar"));

			u = new URL("http://www.apache.org/index.html#foo");
			u1 = new URL(u, "http:#bar");
			assertTrue("9 returns a wrong host", u1.getHost().equals(
					"www.apache.org"));
			assertTrue("9 returns a wrong file", u1.getFile().equals(
					"/index.html"));
			assertTrue("9 returns a wrong anchor", u1.getRef().equals("bar"));

			u = new URL("http://www.apache.org/index.html");
			u1 = new URL(u, "");
			assertTrue("10 returns a wrong host", u1.getHost().equals(
					"www.apache.org"));
			assertTrue("10 returns a wrong file", u1.getFile().equals(
					"/index.html"));
			assertTrue("10 returns a wrong anchor", u1.getRef() == null);

			uf = new URL("file://www.apache.org/index.html");
			u1f = new URL(uf, "");
			assertTrue("10f returns a wrong host", u1.getHost().equals(
					"www.apache.org"));
			assertTrue("10f returns a wrong file", u1.getFile().equals(
					"/index.html"));
			assertTrue("10f returns a wrong anchor", u1.getRef() == null);

			u = new URL("http://www.apache.org/index.html");
			u1 = new URL(u, "http://www.apache.org");
			assertTrue("11 returns a wrong host", u1.getHost().equals(
					"www.apache.org"));
			assertTrue("11 returns a wrong file", u1.getFile().equals(""));
			assertTrue("11 returns a wrong anchor", u1.getRef() == null);

			// test for question mark processing
			u = new URL("http://www.foo.com/d0/d1/d2/cgi-bin?foo=bar/baz");

			// test for relative file and out of bound "/../" processing
			u1 = new URL(u, "../dir1/./dir2/../file.java");
			assertTrue("A) returns a wrong file: " + u1.getFile(), u1.getFile()
					.equals("/d0/d1/dir1/file.java"));

			// test for absolute and relative file processing
			u1 = new URL(u, "/../dir1/./dir2/../file.java");
			assertTrue("B) returns a wrong file", u1.getFile().equals(
					"/../dir1/./dir2/../file.java"));
		} catch (Exception e) {
			fail("1 Exception during tests : " + e.getMessage());
		}

		try {
			// u should raise a MalFormedURLException because u, the context is
			// null
			u = null;
			u1 = new URL(u, "file.java");
		} catch (MalformedURLException e) {
			return;
		} catch (Exception e) {
			fail("2 Exception during tests : " + e.getMessage());
		}
		fail("didn't throw the expected MalFormedURLException");
	}

	/**
	 * @tests java.net.URL#URL(java.net.URL, java.lang.String,
	 *        java.net.URLStreamHandler)
	 */
	public void test_ConstructorLjava_net_URLLjava_lang_StringLjava_net_URLStreamHandler() {
		// Test for method java.net.URL(java.net.URL, java.lang.String,
		// java.net.URLStreamHandler)
		try {
			u = new URL("http://www.yahoo.com");
			// basic ones
			u1 = new URL(u, "file.java", new MyHandler());
			assertTrue("1 returns a wrong protocol", u1.getProtocol().equals(
					"http"));
			assertTrue("1 returns a wrong host", u1.getHost().equals(
					"www.yahoo.com"));
			assertTrue("1 returns a wrong port", u1.getPort() == -1);
			assertTrue("1 returns a wrong file", u1.getFile().equals(
					"/file.java"));
			assertTrue("1 returns a wrong anchor", u1.getRef() == null);

			u1 = new URL(u, "systemresource:/+/FILE0/test.java",
					new MyHandler());
			assertTrue("2 returns a wrong protocol", u1.getProtocol().equals(
					"systemresource"));
			assertTrue("2 returns a wrong host", u1.getHost().equals(""));
			assertTrue("2 returns a wrong port", u1.getPort() == -1);
			assertTrue("2 returns a wrong file", u1.getFile().equals(
					"/+/FILE0/test.java"));
			assertTrue("2 returns a wrong anchor", u1.getRef() == null);

			u1 = new URL(u, "dir1/dir2/../file.java", null);
			assertTrue("3 returns a wrong protocol", u1.getProtocol().equals(
					"http"));
			assertTrue("3 returns a wrong host", u1.getHost().equals(
					"www.yahoo.com"));
			assertTrue("3 returns a wrong port", u1.getPort() == -1);
			assertTrue("3 returns a wrong file", u1.getFile().equals(
					"/dir1/dir2/../file.java"));
			assertTrue("3 returns a wrong anchor", u1.getRef() == null);

			// test for question mark processing
			u = new URL("http://www.foo.com/d0/d1/d2/cgi-bin?foo=bar/baz");

			// test for relative file and out of bound "/../" processing
			u1 = new URL(u, "../dir1/dir2/../file.java", new MyHandler());
			assertTrue("A) returns a wrong file: " + u1.getFile(), u1.getFile()
					.equals("/d0/d1/dir1/file.java"));

			// test for absolute and relative file processing
			u1 = new URL(u, "/../dir1/dir2/../file.java", null);
			assertTrue("B) returns a wrong file", u1.getFile().equals(
					"/../dir1/dir2/../file.java"));
		} catch (Exception e) {
			fail("1 Exception during tests : " + e.getMessage());
		}

		URL one;
		try {
			one = new URL("http://www.ibm.com");
		} catch (MalformedURLException ex) {
			// Should not happen.
			throw new RuntimeException(ex.getMessage());
		}
		try {
			new URL(one, (String) null);
			fail("Specifying null spec on URL constructor should throw MalformedURLException");
		} catch (MalformedURLException e) {
			// expected
		}

		try {
			// u should raise a MalFormedURLException because u, the context is
			// null
			u = null;
			u1 = new URL(u, "file.java", new MyHandler());
		} catch (MalformedURLException e) {
			return;
		} catch (Exception e) {
			fail("2 Exception during tests : " + e.getMessage());
		}
		fail("didn't throw expected MalFormedURLException");
	}

	/**
	 * @tests java.net.URL#URL(java.lang.String, java.lang.String,
	 *        java.lang.String)
	 */
	public void test_ConstructorLjava_lang_StringLjava_lang_StringLjava_lang_String() {
		// Test for method java.net.URL(java.lang.String, java.lang.String,
		// java.lang.String)
		try {
			u = new URL("http", "www.yahoo.com:8080", "test.html#foo");
			assertTrue("SSS returns a wrong protocol", u.getProtocol().equals(
					"http"));
			assertTrue("SSS returns a wrong host: " + u.getHost(), u.getHost()
					.equals("www.yahoo.com:8080"));
			assertTrue("SSS returns a wrong port", u.getPort() == -1);
			assertTrue("SSS returns a wrong file", u.getFile().equals(
					"test.html"));
			assertTrue("SSS returns a wrong anchor: " + u.getRef(), u.getRef()
					.equals("foo"));
		} catch (Exception e) {
			fail("SSS Exception during test : " + e.getMessage());
		}
	}

	/**
	 * @tests java.net.URL#URL(java.lang.String, java.lang.String, int,
	 *        java.lang.String)
	 */
	public void test_ConstructorLjava_lang_StringLjava_lang_StringILjava_lang_String() {
		// Test for method java.net.URL(java.lang.String, java.lang.String, int,
		// java.lang.String)
		try {
			u = new URL("http", "www.yahoo.com", 8080, "test.html#foo");
			assertTrue("SSIS returns a wrong protocol", u.getProtocol().equals(
					"http"));
			assertTrue("SSIS returns a wrong host", u.getHost().equals(
					"www.yahoo.com"));
			assertTrue("SSIS returns a wrong port", u.getPort() == 8080);
			assertTrue("SSIS returns a wrong file", u.getFile().equals(
					"test.html"));
			assertTrue("SSIS returns a wrong anchor: " + u.getRef(), u.getRef()
					.equals("foo"));
		} catch (Exception e) {
			fail("SSIS Exception during test : " + e.getMessage());
		}

	}

	/**
	 * @tests java.net.URL#URL(java.lang.String, java.lang.String, int,
	 *        java.lang.String, java.net.URLStreamHandler)
	 */
	public void test_ConstructorLjava_lang_StringLjava_lang_StringILjava_lang_StringLjava_net_URLStreamHandler() {
		// Test for method java.net.URL(java.lang.String, java.lang.String, int,
		// java.lang.String, java.net.URLStreamHandler)
		try {
			u = new URL("http", "www.yahoo.com", 8080, "test.html#foo", null);
			assertTrue("SSISH1 returns a wrong protocol", u.getProtocol()
					.equals("http"));
			assertTrue("SSISH1 returns a wrong host", u.getHost().equals(
					"www.yahoo.com"));
			assertTrue("SSISH1 returns a wrong port", u.getPort() == 8080);
			assertTrue("SSISH1 returns a wrong file", u.getFile().equals(
					"test.html"));
			assertTrue("SSISH1 returns a wrong anchor: " + u.getRef(), u
					.getRef().equals("foo"));
		} catch (Exception e) {
			fail("SSISH1 Exception during test : " + e.getMessage());
		}

		try {
			u = new URL("http", "www.yahoo.com", 8080, "test.html#foo",
					new MyHandler());
			assertTrue("SSISH2 returns a wrong protocol", u.getProtocol()
					.equals("http"));
			assertTrue("SSISH2 returns a wrong host", u.getHost().equals(
					"www.yahoo.com"));
			assertTrue("SSISH2 returns a wrong port", u.getPort() == 8080);
			assertTrue("SSISH2 returns a wrong file", u.getFile().equals(
					"test.html"));
			assertTrue("SSISH2 returns a wrong anchor: " + u.getRef(), u
					.getRef().equals("foo"));
		} catch (Exception e) {
			fail("SSISH2 Exception during test : " + e.getMessage());
		}

	}

	/**
	 * @tests java.net.URL#equals(java.lang.Object)
	 */
	public void test_equalsLjava_lang_Object() {
		// Test for method boolean java.net.URL.equals(java.lang.Object)
		try {
			u = new URL("http://www.apache.org:8080/dir::23??????????test.html");
			u1 = new URL("http://www.apache.org:8080/dir::23??????????test.html");
			assertTrue("A) equals returns false for two identical URLs", u
					.equals(u1));
			assertTrue("return true for null comaprison", !u1.equals(null));
			u = new URL("ftp://www.apache.org:8080/dir::23??????????test.html");
			assertTrue("Returned true for non-equal URLs", !u.equals(u1));
		} catch (MalformedURLException e) {
			fail("MalformedURLException during equals test : " + e.getMessage());
		}
	}

	/**
	 * @tests java.net.URL#sameFile(java.net.URL)
	 */
	public void test_sameFileLjava_net_URL() {
		// Test for method boolean java.net.URL.sameFile(java.net.URL)
		try {
			u = new URL("http://www.yahoo.com");
			u1 = new URL("http", "www.yahoo.com", "");
			assertTrue("Should be the same1", u.sameFile(u1));
			u = new URL("http://www.yahoo.com/dir1/dir2/test.html#anchor1");
			u1 = new URL("http://www.yahoo.com/dir1/dir2/test.html#anchor2");
			assertTrue("Should be the same ", u.sameFile(u1));
		} catch (Exception e) {
		}
	}

	/**
	 * @tests java.net.URL#getContent()
	 */
	public void test_getContent() {
		// Test for method java.lang.Object java.net.URL.getContent()
		byte[] ba;
		InputStream is;
		String s;
		File resources = Support_Resources.createTempFolder();
		try {
			Support_Resources.copyFile(resources, null, "hyts_htmltest.html");
			u = new URL("file:/" + resources.toString() + "/hyts_htmltest.html");
			u.openConnection();
			is = (InputStream) u.getContent();
			is.read(ba = new byte[4096]);
			s = new String(ba);
			assertTrue(
					"Incorrect content "
							+ u
							+ " does not contain: \" A Seemingly Non Important String \"",
					s.indexOf("A Seemingly Non Important String") >= 0);
		} catch (IOException e) {
			fail("IOException thrown : " + e.getMessage());
		} finally {
			// Support_Resources.deleteTempFolder(resources);
		}

	}

	/**
	 * @tests java.net.URL#openStream()
	 */
	public void test_openStream() {
		// Test for method java.io.InputStream java.net.URL.openStream()
		File resources = Support_Resources.createTempFolder();
		try {
			Support_Resources.copyFile(resources, null, "hyts_htmltest.html");
			u = new URL("file:/" + resources.toString() + "/hyts_htmltest.html");
			// HTTP connection
			InputStream is1 = u.openStream();
			assertTrue("Unable to read from stream", is1.read() != 0);
			is1.close();

			boolean exception = false;
			try {
				u = new URL("file://nonexistenttestdir/tstfile");
				u.openStream();
			} catch (IOException e) {
				// Correct behaviour
				exception = true;
			}
			assertTrue("openStream succeeded for non existent resource",
					exception);

		} catch (MalformedURLException e) {
			fail("Incorrect url specified : " + e.getMessage());
		} catch (IOException e) {
			fail("IOException opening stream : " + e.getMessage());
		}

		try {
			URL u = new URL("jar:"
					+ Support_Resources.getResourceURL("/JUC/lf.jar!/plus.bmp"));
			InputStream in = u.openStream();
			byte[] buf = new byte[3];
			int result = in.read(buf);
			assertTrue("Incompete read: " + result, result == 3);
			in.close();
			assertTrue("Returned incorrect data", buf[0] == 0x42
					&& buf[1] == 0x4d && buf[2] == (byte) 0xbe);
		} catch (java.io.IOException e) {
			fail("IOException during test : " + e.getMessage());
		}

		try {
			URL u = new URL("ftp://" + Support_Configuration.FTPTestAddress
					+ "/nettest.txt");
			InputStream in = u.openStream();
			byte[] buf = new byte[3];
			assertTrue("Incompete read 2", in.read(buf) == 3);
			in.close();
			assertTrue("Returned incorrect data 2", buf[0] == 0x54
					&& buf[1] == 0x68 && buf[2] == 0x69);
		} catch (java.io.IOException e) {
			fail("IOException during test 2 : " + e.getMessage());
		}

		try {
			File test = new File("hytest.$$$");
			FileOutputStream out = new FileOutputStream(test);
			out.write(new byte[] { 0x55, (byte) 0xaa, 0x14 });
			out.close();
			URL u = new URL("file:" + test.getName());
			InputStream in = u.openStream();
			byte[] buf = new byte[3];
			int result = in.read(buf);
			in.close();
			test.delete();
			assertTrue("Incompete read 3", result == 3);
			assertTrue("Returned incorrect data 3", buf[0] == 0x55
					&& buf[1] == (byte) 0xaa && buf[2] == 0x14);
		} catch (java.io.IOException e) {
			fail("IOException during test 3 : " + e.getMessage());
		}
	}

	/**
	 * @tests java.net.URL#openConnection()
	 */
	public void test_openConnection() {
		// Test for method java.net.URLConnection java.net.URL.openConnection()
		try {
			u = new URL("systemresource:/FILE4/+/types.properties");
			URLConnection uConn = u.openConnection();
			assertTrue("u.openConnection() returns null", uConn != null);
		} catch (Exception e) {
		}
	}

	/**
	 * @tests java.net.URL#toString()
	 */
	public void test_toString() {
		// Test for method java.lang.String java.net.URL.toString()
		try {
			u1 = new URL("http://www.yahoo2.com:9999");
			u = new URL(
					"http://www.yahoo1.com:8080/dir1/dir2/test.cgi?point1.html#anchor1");
			assertTrue(
					"a) Does not return the right url string",
					u
							.toString()
							.equals(
									"http://www.yahoo1.com:8080/dir1/dir2/test.cgi?point1.html#anchor1"));
			assertTrue("b) Does not return the right url string", u1.toString()
					.equals("http://www.yahoo2.com:9999"));
			assertTrue("c) Does not return the right url string", u
					.equals(new URL(u.toString())));
		} catch (Exception e) {
		}
	}

	/**
	 * @tests java.net.URL#toExternalForm()
	 */
	public void test_toExternalForm() {
		// Test for method java.lang.String java.net.URL.toExternalForm()
		try {
			u1 = new URL("http://www.yahoo2.com:9999");
			u = new URL(
					"http://www.yahoo1.com:8080/dir1/dir2/test.cgi?point1.html#anchor1");
			assertTrue(
					"a) Does not return the right url string",
					u
							.toString()
							.equals(
									"http://www.yahoo1.com:8080/dir1/dir2/test.cgi?point1.html#anchor1"));
			assertTrue("b) Does not return the right url string", u1.toString()
					.equals("http://www.yahoo2.com:9999"));
			assertTrue("c) Does not return the right url string", u
					.equals(new URL(u.toString())));

			u = new URL("http:index");
			assertTrue("2 wrong external form", u.toExternalForm().equals(
					"http:index"));

			u = new URL("http", null, "index");
			assertTrue("2 wrong external form", u.toExternalForm().equals(
					"http:index"));
		} catch (Exception e) {
		}
	}

	/**
	 * @tests java.net.URL#getFile()
	 */
	public void test_getFile() {
		// Test for method java.lang.String java.net.URL.getFile()
		try {
			u = new URL("http", "www.yahoo.com:8080", 1233,
					"test/!@$%^&*/test.html#foo");
			assertTrue("returns a wrong file", u.getFile().equals(
					"test/!@$%^&*/test.html"));
			u = new URL("http", "www.yahoo.com:8080", 1233, "");
			assertTrue("returns a wrong file", u.getFile().equals(""));
		} catch (Exception e) {
			fail("Exception raised : " + e.getMessage());
		}
	}

	/**
	 * @tests java.net.URL#getHost()
	 */
	public void test_getHost() {
		// Test for method java.lang.String java.net.URL.getHost()
		assertTrue("Used to test", true);
	}

	/**
	 * @tests java.net.URL#getPort()
	 */
	public void test_getPort() {
		// Test for method int java.net.URL.getPort()
		try {
			u = new URL("http://member12.c++.com:9999");
			assertTrue("return wrong port number " + u.getPort(),
					u.getPort() == 9999);
			u = new URL("http://member12.c++.com:9999/");
			assertTrue("return wrong port number", u.getPort() == 9999);
		} catch (Exception e) {
			fail("Threw exception : " + e.getMessage());
		}
	}

	/**
	 * @tests java.net.URL#getProtocol()
	 */
	public void test_getProtocol() {
		// Test for method java.lang.String java.net.URL.getProtocol()
		try {
			u = new URL("http://www.yahoo2.com:9999");
			assertTrue("u returns a wrong protocol: " + u.getProtocol(), u
					.getProtocol().equals("http"));

		} catch (Exception e) {
			fail("Threw exception : " + e.getMessage());
		}
	}

	/**
	 * @tests java.net.URL#getRef()
	 */
	public void test_getRef() {
		// Test for method java.lang.String java.net.URL.getRef()
		try {
			u1 = new URL("http://www.yahoo2.com:9999");
			u = new URL(
					"http://www.yahoo1.com:8080/dir1/dir2/test.cgi?point1.html#anchor1");
			assertTrue("returns a wrong anchor1", u.getRef().equals("anchor1"));
			assertTrue("returns a wrong anchor2", u1.getRef() == null);
			u1 = new URL("http://www.yahoo2.com#ref");
			assertTrue("returns a wrong anchor3", u1.getRef().equals("ref"));
			u1 = new URL("http://www.yahoo2.com/file#ref1#ref2");
			assertTrue("returns a wrong anchor4", u1.getRef().equals(
					"ref1#ref2"));
		} catch (MalformedURLException e) {
			fail("Incorrect URL format : " + e.getMessage());
		}
	}

	/**
	 * @tests java.net.URL#getAuthority()
	 */
	public void test_getAuthority() {
		try {
			URL url = new URL("http", "u:p@home", 80, "/java?q1#ref");
			assertTrue("wrong authority: " + url.getAuthority(), url
					.getAuthority().equals("u:p@home:80"));
			assertTrue("wrong userInfo", url.getUserInfo().equals("u:p"));
			assertTrue("wrong host", url.getHost().equals("home"));
			assertTrue("wrong file", url.getFile().equals("/java?q1"));
			assertTrue("wrong path", url.getPath().equals("/java"));
			assertTrue("wrong query", url.getQuery().equals("q1"));
			assertTrue("wrong ref", url.getRef().equals("ref"));

			url = new URL("http", "home", -1, "/java");
			assertTrue("wrong authority2", url.getAuthority().equals("home"));
			assertTrue("wrong userInfo2", url.getUserInfo() == null);
			assertTrue("wrong host2", url.getHost().equals("home"));
			assertTrue("wrong file2", url.getFile().equals("/java"));
			assertTrue("wrong path2", url.getPath().equals("/java"));
			assertTrue("wrong query2", url.getQuery() == null);
			assertTrue("wrong ref2", url.getRef() == null);
		} catch (MalformedURLException e) {
			fail("Unexpected MalformedURLException : " + e.getMessage());
		}
		;

	}

	/**
	 * Sets up the fixture, for example, open a network connection. This method
	 * is called before a test is executed.
	 */
	protected void setUp() {
	}

	/**
	 * Tears down the fixture, for example, close a network connection. This
	 * method is called after a test is executed.
	 */
	protected void tearDown() {
	}
}
