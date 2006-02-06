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

package org.apache.harmony.tests.java.net;

import java.net.MalformedURLException;
import java.net.URL;

import junit.framework.TestCase;

public class URLTest extends TestCase {

	/**
	 * @tests java.net.URL#getHost()
	 */
	public void test_getHost() throws MalformedURLException {
		// Regression for HARMONY-60
		String ipv6Host = "FEDC:BA98:7654:3210:FEDC:BA98:7654:3210";
		URL url = new URL("http", ipv6Host, -1, "myfile");
		assertEquals(("[" + ipv6Host + "]"), url.getHost());
	}
	
	/**
	 * @tests java.net.URL#URL(java.lang.String, java.lang.String, java.lang.String)
	 */
	public void test_ConstructorLjava_lang_StringLjava_lang_StringLjava_lang_String() throws MalformedURLException {
		// Strange behavior in reference, the hostname contains a ':' so it gets wrapped in '[', ']'
		URL testURL = new URL("http", "www.apache.org:8080", "test.html#anch");
		assertEquals("Assert 0: wrong protocol", "http", testURL.getProtocol());
		assertEquals("Assert 1: wrong host", "[www.apache.org:8080]", testURL.getHost());
		assertEquals("Assert 2: wrong port", -1, testURL.getPort());
		assertEquals("Assert 3: wrong file", "test.html", testURL.getFile());
		assertEquals("Assert 4: wrong anchor", "anch", testURL.getRef());
	}
}
