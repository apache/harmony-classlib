/*
 *  Licensed to the Apache Software Foundation (ASF) under one or more
 *  contributor license agreements.  See the NOTICE file distributed with
 *  this work for additional information regarding copyright ownership.
 *  The ASF licenses this file to You under the Apache License, Version 2.0
 *  (the "License"); you may not use this file except in compliance with
 *  the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package tests.api.java.net;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.CacheRequest;
import java.net.CacheResponse;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.ResponseCache;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import tests.support.Support_Configuration;
import tests.support.resource.Support_Resources;

public class HttpURLConnectionTest extends junit.framework.TestCase {

	URL url;

	HttpURLConnection uc;
    
    private boolean isGetCalled;

    private boolean isPutCalled;

    private boolean isCacheWriteCalled;

    private boolean isAbortCalled;

    private Map<String, List<String>> mockHeaderMap;
    
    private InputStream mockIs = new MockInputStream();

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

		assertEquals("you like", newHeader.get(0));

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
        URLConnection uc = new URL("http://"
                + Support_Configuration.InetTestAddress).openConnection();
        uc.setDoOutput(true);
        uc.connect();
        assertNotNull(uc.getOutputStream());
    }
    

    /**
     * @tests java.net.URLConnection#setUseCaches() and its real implementation
     *        in HttpURLConnection using GetInputStream() and Connect()
     */
    public void test_UseCache_HttpURLConnection_Connect_GetInputStream()
            throws Exception {
        // set cache before URLConnection created, or it does not take effect
        ResponseCache rc = new MockNonCachedResponseCache();
        ResponseCache.setDefault(rc);
        URLConnection uc = new URL("http://"
                + Support_Configuration.InetTestAddress).openConnection();
        assertFalse(isGetCalled);
        uc.setUseCaches(true);
        uc.setDoOutput(true);
        uc.connect();
        assertTrue(isGetCalled);
        assertFalse(isPutCalled);
        InputStream is = uc.getInputStream();
        assertTrue(isPutCalled);
        is.close();
        ((HttpURLConnection)uc).disconnect();
    }

    /**
     * @tests java.net.URLConnection#setUseCaches() and its real implementation
     *        in HttpURLConnection using GetOutputStream() and Connect()
     */
    public void test_UseCache_HttpURLConnection_Connect_GetOutputStream()
            throws Exception {
        // set cache before URLConnection created, or it does not take effect
        ResponseCache rc = new MockNonCachedResponseCache();
        ResponseCache.setDefault(rc);
        uc.setUseCaches(true);
        URLConnection uc = new URL("http://"
                + Support_Configuration.InetTestAddress).openConnection();
        uc.setDoOutput(true);
        assertFalse(isGetCalled);
        uc.connect();
        assertTrue(isGetCalled);
        assertFalse(isPutCalled);
        OutputStream os = uc.getOutputStream();
        assertFalse(isPutCalled);
        os.close();
        ((HttpURLConnection)uc).disconnect();
    }

    /**
     * @tests java.net.URLConnection#setUseCaches() and its real implementation
     *        in HttpURLConnection using GetOutputStream()
     */
    public void test_UseCache_HttpURLConnection_GetOutputStream()
            throws Exception {
        // set cache before URLConnection created, or it does not take effect
        ResponseCache rc = new MockNonCachedResponseCache();
        ResponseCache.setDefault(rc);
        URLConnection uc = new URL("http://"
                + Support_Configuration.InetTestAddress).openConnection();
        assertFalse(isGetCalled);
        uc.setDoOutput(true);
        uc.setUseCaches(true);
        OutputStream os = uc.getOutputStream();
        assertTrue(isGetCalled);
        assertFalse(isPutCalled);
        os.write(1);
        os.flush();
        os.close();
        ((HttpURLConnection) uc).getResponseCode();
        assertTrue(isGetCalled);
        assertTrue(isPutCalled);
        isGetCalled = false;
        isPutCalled = false;
        InputStream is = uc.getInputStream();
        assertFalse(isGetCalled);
        assertFalse(isPutCalled);
        is.close();
        ((HttpURLConnection)uc).disconnect();
    }

    /**
     * @tests java.net.URLConnection#setUseCaches() and its real implementation
     *        in HttpURLConnection using GetInputStream()
     */
    public void test_UseCache_HttpURLConnection_GetInputStream()
            throws Exception {
        // set cache before URLConnection created, or it does not take effect
        ResponseCache rc = new MockNonCachedResponseCache();
        ResponseCache.setDefault(rc);
        URLConnection uc = new URL("http://"
                + Support_Configuration.InetTestAddress).openConnection();
        assertFalse(isGetCalled);
        uc.setDoOutput(true);
        uc.setUseCaches(true);
        InputStream is = uc.getInputStream();
        assertTrue(isGetCalled);
        assertTrue(isPutCalled);
        ((HttpURLConnection) uc).getResponseCode();
        is.close();
        ((HttpURLConnection)uc).disconnect();
    }

    /**
     * @tests java.net.URLConnection#setUseCaches() and its real implementation
     *        in HttpURLConnection using a MockResponseCache returns cache of
     *        null
     */
    public void test_UseCache_HttpURLConnection_NonCached() throws IOException {
        ResponseCache.setDefault(new MockNonCachedResponseCache());
        URL u = new URL("http://"
                + Support_Configuration.InetTestAddress);
        HttpURLConnection uc = (HttpURLConnection) u.openConnection();

        // default useCaches is true
        assertTrue(uc.getUseCaches());

        // make sure ResponseCache.get/put is called
        isGetCalled = false;
        isPutCalled = false;
        InputStream is = uc.getInputStream();
        assertFalse(is instanceof MockInputStream);
        assertTrue(isGetCalled);
        assertTrue(isPutCalled);

        // make sure protocol handler has tried to write to cache.
        isCacheWriteCalled = false;
        is.read();
        assertTrue(isCacheWriteCalled);

        // make sure protocol handler has tried to write to cache.
        isCacheWriteCalled = false;
        byte[] buf = new byte[1];
        is.read(buf);
        assertTrue(isCacheWriteCalled);

        // make sure protocol handler has tried to write to cache.
        isCacheWriteCalled = false;
        buf = new byte[1];
        is.read(buf, 0, 1);
        assertTrue(isCacheWriteCalled);

        // make sure protocol handler has tried to call abort.
        isAbortCalled = false;
        is.close();
        assertTrue(isAbortCalled);
        uc.disconnect();        
    }

    /**
     * @tests java.net.URLConnection#setUseCaches() and its real implementation
     *        in HttpURLConnection using a MockResponseCache returns a mock
     *        cache
     */
    public void test_UseCache_HttpURLConnection_Cached() throws IOException {
        ResponseCache.setDefault(new MockCachedResponseCache());
        URL u = new URL("http://"
                + Support_Configuration.InetTestAddress);
        HttpURLConnection uc = (HttpURLConnection) u.openConnection();

        // default useCaches is true
        assertTrue(uc.getUseCaches());

        // make sure ResponseCache.get/put is called
        isGetCalled = false;
        isPutCalled = false;
        InputStream is = uc.getInputStream();
        assertTrue(is instanceof MockInputStream);
        assertTrue(isGetCalled);

        // make sure protocol handler doesn't try to write to cache, since
        // it has been in cache already.
        isCacheWriteCalled = false;
        is.read();
        assertFalse(isCacheWriteCalled);

        // make sure protocol handler doesn't try to write to cache, since
        // it has been in cache already.
        isCacheWriteCalled = false;
        byte[] buf = new byte[1];
        is.read(buf);
        assertFalse(isCacheWriteCalled);

        // make sure protocol handler doesn't try to write to cache, since
        // it has been in cache already.
        isCacheWriteCalled = false;
        buf = new byte[1];
        is.read(buf, 0, 1);
        assertFalse(isCacheWriteCalled);

        // make sure abort is not called since no write is performed
        isAbortCalled = false;
        is.close();
        assertFalse(isAbortCalled);
        uc.disconnect();
    }

    /**
     * @tests java.net.URLConnection#setUseCaches() and its real implementation
     *        in HttpURLConnection using getHeaderFields()
     */
    public void test_UseCache_HttpURLConnection_getHeaderFields()
            throws IOException {
        ResponseCache.setDefault(new MockCachedResponseCache());
        URL u = new URL("http://"
                + Support_Configuration.InetTestAddress);
        HttpURLConnection uc = (HttpURLConnection) u.openConnection();
        Map<String, List<String>> headerMap = uc.getHeaderFields();
        assertTrue(isGetCalled);
        assertFalse(isPutCalled);
        assertEquals(mockHeaderMap, headerMap);
        assertEquals(uc.getInputStream(),mockIs);
        uc.disconnect();
    }

    /**
     * @tests java.net.URLConnection#setUseCaches() and its real implementation
     *        in HttpURLConnection using GetOutputStream()
     */
    public void test_UseCache_HttpURLConnection_NoCached_GetOutputStream()
            throws Exception {
        ResponseCache.setDefault(new MockNonCachedResponseCache());
        URL u = new URL("http://"
                + Support_Configuration.InetTestAddress);
        HttpURLConnection uc = (HttpURLConnection) u.openConnection();
        uc.setChunkedStreamingMode(10);
        uc.setDoOutput(true);        
        uc.getOutputStream();
        assertTrue(isGetCalled);
        assertFalse(isPutCalled);
        assertFalse(isAbortCalled);
        uc.disconnect();
    }

    class MockNonCachedResponseCache extends ResponseCache {

        public CacheResponse get(URI arg0, String arg1, Map arg2)
                throws IOException {
            isGetCalled = true;
            return null;
        }

        public CacheRequest put(URI arg0, URLConnection arg1)
                throws IOException {
            isPutCalled = true;
            return new MockCacheRequest();
        }
    }

    class MockCachedResponseCache extends ResponseCache {

        public CacheResponse get(URI arg0, String arg1, Map arg2)
                throws IOException {
            if (null == arg0 || null == arg1 || null == arg2) {
                throw new NullPointerException();
            }
            isGetCalled = true;
            return new MockCacheResponse();
        }

        public CacheRequest put(URI arg0, URLConnection arg1)
                throws IOException {
            if (null == arg0 || null == arg1) {
                throw new NullPointerException();
            }
            isPutCalled = true;
            return new MockCacheRequest();
        }
    }

    class MockCacheRequest extends CacheRequest {

        public OutputStream getBody() throws IOException {
            isCacheWriteCalled = true;
            return new MockOutputStream();
        }

        public void abort() {
            isAbortCalled = true;
        }

    }

    class MockCacheResponse extends CacheResponse {

        public Map<String, List<String>> getHeaders() throws IOException {
            return mockHeaderMap;
        }

        public InputStream getBody() throws IOException {
            return mockIs;
        }
    }

    class MockInputStream extends InputStream {

        public int read() throws IOException {
            return 1;
        }

        public int read(byte[] arg0, int arg1, int arg2) throws IOException {
            return 1;
        }

        public int read(byte[] arg0) throws IOException {
            return 1;
        }

    }

    class MockOutputStream extends OutputStream {

        public void write(int b) throws IOException {
            isCacheWriteCalled = true;
        }

        public void write(byte[] b, int off, int len) throws IOException {
            isCacheWriteCalled = true;
        }

        public void write(byte[] b) throws IOException {
            isCacheWriteCalled = true;
        }
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

    protected void setUp() {
        try {
            url = new URL(Support_Resources
                    .getResourceURL("/URLConnectionTest/Harmony.html"));
            uc = (HttpURLConnection) url.openConnection();
        } catch (Exception e) {
            fail("Exception during setup : " + e.getMessage());
        }
        mockHeaderMap = new Hashtable<String, List<String>>();
        List<String> valueList = new ArrayList<String>();
        valueList.add("value1");
        mockHeaderMap.put("field1", valueList);
        mockHeaderMap.put("field2", valueList);
        isGetCalled = false;
        isPutCalled = false;
        isCacheWriteCalled = false;
    }
	
    protected void tearDown() {
        uc.disconnect();
        ResponseCache.setDefault(null);
    }
}
