/* Copyright 1998, 2006 The Apache Software Foundation or its licensors, as applicable
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

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.UnknownHostException;
import java.util.List;
import java.util.Map;

import tests.support.Support_Configuration;
import tests.support.resource.Support_Resources;

public class HttpURLConnectionTest extends junit.framework.TestCase {

	URL url;

	HttpURLConnection uc;

	/**
	 * @tests java.net.HttpURLConnection#getResponseCode()
	 */
	public void test_getResponseCode() {
		try {
			assertEquals("Wrong response", 200, uc.getResponseCode());
		} catch (IOException e) {
			fail("Unexpected exception : " + e.getMessage());
		}
	}

	/**
	 * @tests java.net.HttpURLConnection#getResponseMessage()
	 */
	public void test_getResponseMessage() {
		try {
			assertTrue("Wrong response: " + uc.getResponseMessage(), uc
					.getResponseMessage().equals("OK"));
		} catch (IOException e) {
			fail("Unexpected exception : " + e.getMessage());
		}
	}

	/**
	 * @tests java.net.HttpURLConnection#getHeaderFields()
	 */
	public void test_getHeaderFields() {
		try {
			uc.getInputStream();
		} catch (IOException e) {
			fail();
		}
		Map headers = uc.getHeaderFields();
		List list = (List) headers.get("Content-Length");
		if (list == null) {
			list = (List) headers.get("content-length");
		}
		assertNotNull(list);

		// content-length should always appear
		String contentLength = (String) list.get(0);
		assertNotNull(contentLength);

		// there should be at least 2 headers
		assertTrue(headers.size() > 1);

		try {
			// the map should be unmodifiable
			headers.put("hi", "bye");
			fail();
		} catch (UnsupportedOperationException e) {
		}

		try {
			// the list should be unmodifiable
			list.set(0, "whatever");
			fail();
		} catch (UnsupportedOperationException e) {
		}
	}

	/**
	 * @tests java.net.HttpURLConnection#getRequestProperties()
	 */
	public void test_getRequestProperties() {
		uc.setRequestProperty("whatever", "you like");
		Map headers = uc.getRequestProperties();

		List newHeader = (List) headers.get("whatever");
		assertNotNull(newHeader);

		assertEquals(newHeader.get(0), "you like");

		try {
			// the map should be unmodifiable
			headers.put("hi", "bye");
			fail();
		} catch (UnsupportedOperationException e) {
		}
	}

	/**
	 * @tests java.net.HttpURLConnection#usingProxy()
	 */
	public void test_usingProxy() {
		try {
			try {
				System.setProperty("http.proxyHost",
						Support_Configuration.ProxyServerTestHost);

				URL u = new URL("http://" + Support_Configuration.HomeAddress);
				URLConnection conn = u.openConnection();
				conn.getInputStream();
			} catch (ConnectException e) {
				fail("ConnectException connecting to proxy : " + e.getMessage());
			} catch (UnknownHostException e) {
				fail("UnknownHostException connecting to proxy : "
						+ e.getMessage());
			} catch (Exception e) {
				fail("Unexpected exception connecting to proxy : "
						+ e.getMessage());
			}

			boolean exception = false;
			try {
				System.setProperty("http.proxyPort", "81");
				URL u = new URL("http://"
						+ Support_Configuration.InetTestAddress);
				URLConnection conn = u.openConnection();
				conn.getInputStream();
			} catch (IOException e) {
				exception = true;
			} catch (Exception e) {
				exception = false;
			}
			assertTrue("No exception or wrong exception thrown", exception);

			System.setProperty("http.proxyPort", "80");

			try {
				URL u = new URL("http://"
						+ Support_Configuration.ProxyServerTestHost
						+ "/cgi-bin/test.pl");
				java.net.HttpURLConnection conn = (java.net.HttpURLConnection) u
						.openConnection();
				conn.setDoOutput(true);
				conn.setRequestMethod("POST");
				OutputStream out = conn.getOutputStream();
				String posted = "this is a test";
				out.write(posted.getBytes());
				out.close();
				conn.getResponseCode();
				InputStream is = conn.getInputStream();
				String response = "";
				byte[] b = new byte[1024];
				int count = 0;
				while ((count = is.read(b)) > 0)
					response += new String(b, 0, count);
				assertTrue("Response to POST method invalid", response
						.equals(posted));
			} catch (ConnectException e) {
				fail("ConnectException connecting to proxy : " + e.getMessage());
			} catch (UnknownHostException e) {
				fail("UnknownHostException connecting to proxy : "
						+ e.getMessage());
			} catch (Exception e) {
				fail("Unexpected exception connecting to proxy : "
						+ e.getMessage());
			}

			try {
				String posted = "just a test";
				URL u = new URL("http://"
						+ Support_Configuration.ProxyServerTestHost
						+ "/cgi-bin/test.pl");
				java.net.HttpURLConnection conn = (java.net.HttpURLConnection) u
						.openConnection();
				conn.setDoOutput(true);
				conn.setRequestMethod("POST");
				conn.setRequestProperty("Content-length", String.valueOf(posted
						.length()));
				OutputStream out = conn.getOutputStream();
				out.write(posted.getBytes());
				out.close();
				conn.getResponseCode();
				InputStream is = conn.getInputStream();
				String response = "";
				byte[] b = new byte[1024];
				int count = 0;
				while ((count = is.read(b)) > 0)
					response += new String(b, 0, count);
				assertTrue("Response to POST method invalid", response
						.equals(posted));
			} catch (ConnectException e) {
				fail("ConnectException connecting to proxy : " + e.getMessage());
			} catch (UnknownHostException e) {
				fail("UnknownHostException connecting to proxy : "
						+ e.getMessage());
			} catch (Exception e) {
				fail("Unexpected exception connecting to proxy : "
						+ e.getMessage());
			}
		} finally {
			System.setProperties(null);
		}
	}

    /**
     * @tests java.net.HttpURLConnection#setFixedLengthStreamingMode_I()
     */
    public void test_setFixedLengthStreamingModeI() throws Exception {
        url = new URL("http://"
                + Support_Configuration.InetTestAddress);
        uc = (HttpURLConnection) url.openConnection();
        try {
            uc.setFixedLengthStreamingMode(-1);
            fail("should throw IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            // correct
        }
        uc.setFixedLengthStreamingMode(0);
        uc.setFixedLengthStreamingMode(1);
        try {
            uc.setChunkedStreamingMode(1);
            fail("should throw IllegalStateException");
        } catch (IllegalStateException e) {
            // correct
        }
        uc.connect();
        try {
            uc.setFixedLengthStreamingMode(-1);
            fail("should throw IllegalStateException");
        } catch (IllegalStateException e) {
            // correct
        }
        try {
            uc.setChunkedStreamingMode(-1);
            fail("should throw IllegalStateException");
        } catch (IllegalStateException e) {
            // correct
        }
        MockHttpConnection mock = new MockHttpConnection(url);
        assertEquals(-1, mock.getFixedLength());
        mock.setFixedLengthStreamingMode(0);
        assertEquals(0, mock.getFixedLength());
        mock.setFixedLengthStreamingMode(1);
        assertEquals(1, mock.getFixedLength());
        mock.setFixedLengthStreamingMode(0);
        assertEquals(0, mock.getFixedLength());
    }

    /**
     * @tests java.net.HttpURLConnection#setChunkedStreamingMode_I()
     */
    public void test_setChunkedStreamingModeI() throws Exception {
        url = new URL("http://"
                + Support_Configuration.InetTestAddress);
        uc = (HttpURLConnection) url.openConnection();
        uc.setChunkedStreamingMode(0);
        uc.setChunkedStreamingMode(-1);
        uc.setChunkedStreamingMode(-2);

        try {
            uc.setFixedLengthStreamingMode(-1);
            fail("should throw IllegalStateException");
        } catch (IllegalStateException e) {
            // correct
        }
        try {
            uc.setFixedLengthStreamingMode(1);
            fail("should throw IllegalStateException");
        } catch (IllegalStateException e) {
            // correct
        }
        uc.connect();
        try {
            uc.setFixedLengthStreamingMode(-1);
            fail("should throw IllegalStateException");
        } catch (IllegalStateException e) {
            // correct
        }
        try {
            uc.setChunkedStreamingMode(1);
            fail("should throw IllegalStateException");
        } catch (IllegalStateException e) {
            // correct
        }
        MockHttpConnection mock = new MockHttpConnection(url);
        assertEquals(-1, mock.getChunkLength());
        mock.setChunkedStreamingMode(-1);
        int defaultChunk = mock.getChunkLength();
        assertTrue(defaultChunk > 0);
        mock.setChunkedStreamingMode(0);
        assertEquals(mock.getChunkLength(), defaultChunk);
        mock.setChunkedStreamingMode(1);
        assertEquals(1, mock.getChunkLength());
    }

    class MockHttpConnection extends HttpURLConnection {

        protected MockHttpConnection(URL url) {
            super(url);
        }

        public void disconnect() {
            // do nothing
        }

        public boolean usingProxy() {
            return false;
        }

        public void connect() throws IOException {
            // do nothing
        }

        public int getChunkLength() {
            return super.chunkLength;
        }

        public int getFixedLength() {
            return super.fixedContentLength;
        }

    }

    /**
     * @tests java.net.HttpURLConnection#setFixedLengthStreamingMode_I()
     */
    public void test_setFixedLengthStreamingModeI_effect() throws Exception {
        String posted = "just a test";
        URL u = new URL("http://"
                + Support_Configuration.InetTestAddress);
        java.net.HttpURLConnection conn = (java.net.HttpURLConnection) u
                .openConnection();
        conn.setDoOutput(true);
        conn.setRequestMethod("POST");
        conn.setFixedLengthStreamingMode(posted.length() - 1);
        assertNull(conn.getRequestProperty("Content-length"));
        conn.setRequestProperty("Content-length", String.valueOf(posted
                .length()));
        assertEquals(String.valueOf(posted.length()), conn
                .getRequestProperty("Content-length"));
        OutputStream out = conn.getOutputStream();
        try {
            out.write(posted.getBytes());
            fail("should throw IOException");
        } catch (IOException e) {
            // correct, too many bytes written
        }
        try {
            out.close();
            fail("should throw IOException");
        } catch (IOException e) {
            // correct, too many bytes written
        }
    }

    /**
     * @tests java.net.HttpURLConnection#setChunkedStreamingMode_I()
     */
    public void test_setChunkedStreamingModeI_effect() throws Exception {
        String posted = "just a test";
        // for test, use half length of the string
        int chunkSize = posted.length() / 2;
        URL u = new URL("http://"
                + Support_Configuration.InetTestAddress);
        java.net.HttpURLConnection conn = (java.net.HttpURLConnection) u
                .openConnection();
        conn.setDoOutput(true);
        conn.setRequestMethod("POST");
        conn.setChunkedStreamingMode(chunkSize);
        assertNull(conn.getRequestProperty("Transfer-Encoding"));
        // does not take effect
        conn.setRequestProperty("Content-length", String.valueOf(posted
                .length() - 1));
        assertEquals(conn.getRequestProperty("Content-length"), String
                .valueOf(posted.length() - 1));
        OutputStream out = conn.getOutputStream();
        // no error occurs
        out.write(posted.getBytes());
        out.close();
        // no assert here, pass if no exception thrown
        assertTrue(conn.getResponseCode() > 0);
    }
    
    public void test_getOutputStream_afterConnection() throws Exception {
        URLConnection uc = new URL("http://www.apache.org").openConnection();
        uc.setDoOutput(true);
        uc.connect();
        assertNotNull(uc.getOutputStream());
    }
    
	protected void setUp() {
		try {
			url = new URL(Support_Resources
					.getResourceURL("/URLConnectionTest/Harmony.html"));
			uc = (HttpURLConnection) url.openConnection();
		} catch (Exception e) {
			fail("Exception during setup : " + e.getMessage());
		}
	}

	protected void tearDown() {
		uc.disconnect();
	}
}
