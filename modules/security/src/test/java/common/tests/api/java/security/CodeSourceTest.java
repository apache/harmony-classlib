/* Copyright 2005 The Apache Software Foundation or its licensors, as applicable
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

package tests.api.java.security;

import java.net.MalformedURLException;
import java.net.URL;
import java.security.CodeSource;
import java.security.cert.Certificate;

public class CodeSourceTest extends junit.framework.TestCase {

	/**
	 * @tests java.security.CodeSource#CodeSource(java.net.URL,
	 *        java.security.cert.Certificate[])
	 */
	public void test_ConstructorLjava_net_URL$Ljava_security_cert_Certificate() {
		// Test for method java.security.CodeSource(java.net.URL,
		// java.security.cert.Certificate [])
		try {
			new CodeSource(new java.net.URL("file:///test"),
					(Certificate[]) null);
		} catch (Exception e) {
			fail("Caught an exception: " + e);
		}
	}

	/**
	 * @tests java.security.CodeSource#equals(java.lang.Object)
	 */
	public void test_equalsLjava_lang_Object() {
		// Test for method boolean
		// java.security.CodeSource.equals(java.lang.Object)
		try {
			CodeSource cs1 = new CodeSource(new java.net.URL("file:///test"),
					(Certificate[]) null);
			CodeSource cs2 = new CodeSource(new java.net.URL("file:///test"),
					(Certificate[]) null);
			assertTrue("Identical objects were not equal()!", cs1.equals(cs2));
		} catch (Exception e) {
			fail("Caught an exception: " + e);
		}
	}

	/**
	 * @tests java.security.CodeSource#hashCode()
	 */
	public void test_hashCode() {
		try {
			URL url = new java.net.URL("file:///test");
			CodeSource cs = new CodeSource(url, (Certificate[]) null);
			assertTrue("Did not get expected hashCode!", cs.hashCode() == url
					.hashCode());
		} catch (Exception e) {
			fail("Caught an exception: " + e);
		}
	}

	/**
	 * @tests java.security.CodeSource#getCertificates()
	 */
	public void test_getCertificates() {
		// Test for method java.security.cert.Certificate []
		// java.security.CodeSource.getCertificates()
		try {
			CodeSource cs = new CodeSource(new java.net.URL("file:///test"),
					(Certificate[]) null);
			assertTrue("Should have gotten null certificate list.", cs
					.getCertificates() == null);
		} catch (Exception e) {
			fail("Caught an exception: " + e);
		}
	}

	/**
	 * @tests java.security.CodeSource#getLocation()
	 */
	public void test_getLocation() {
		// Test for method java.net.URL java.security.CodeSource.getLocation()
		try {
			CodeSource cs = new CodeSource(new java.net.URL("file:///test"),
					(Certificate[]) null);
			assertTrue("Did not get expected location!", cs.getLocation()
					.toString().equals("file:/test"));
		} catch (Exception e) {
			fail("Caught an exception: " + e);
		}
	}

	/**
	 * @tests java.security.CodeSource#implies(java.security.CodeSource)
	 */
	public void test_impliesLjava_security_CodeSource() {
		// Test for method boolean
		// java.security.CodeSource.implies(java.security.CodeSource)
		try {
			CodeSource cs1 = new CodeSource(new URL("file:/d:/somedir"),
					(Certificate[]) null);
			CodeSource cs2 = new CodeSource(new URL("file:/d:/somedir/"),
					(Certificate[]) null);
			assertTrue("Does not add /", cs1.implies(cs2));
		} catch (MalformedURLException e) {
			fail("Caught MalformedURLException : " + e.toString());
		}

		try {
			CodeSource cs1 = new CodeSource(new URL("file", null, -1,
					"/d:/somedir/"), (Certificate[]) null);
			CodeSource cs2 = new CodeSource(new URL("file:/d:/somedir/"),
					(Certificate[]) null);
			assertTrue("null host should imply host", cs1.implies(cs2));
			assertTrue("host should not imply null host", !cs2.implies(cs1));
		} catch (MalformedURLException e) {
			fail("Caught MalformedURLException : " + e.toString());
		}
	}

	/**
	 * @tests java.security.CodeSource#toString()
	 */
	public void test_toString() {
		// Test for method java.lang.String java.security.CodeSource.toString()
		try {
			CodeSource cs = new CodeSource(new java.net.URL("file:///test"),
					(Certificate[]) null);
			assertEquals("toString did not return expected value.",
					"(file:/test <no certificates>)", cs.toString());
		} catch (Exception e) {
			fail("Caught an exception: " + e);
		}
	}
}