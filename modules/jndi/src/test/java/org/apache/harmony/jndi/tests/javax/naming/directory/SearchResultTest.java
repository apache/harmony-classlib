/* 
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.harmony.jndi.tests.javax.naming.directory;

import javax.naming.directory.BasicAttributes;
import javax.naming.directory.SearchResult;

import org.apache.harmony.jndi.tests.javax.naming.util.Log;
import junit.framework.TestCase;

public class SearchResultTest extends TestCase {

	static Log log = new Log(SearchResultTest.class);

	BasicAttributes attrs = new BasicAttributes("id_sample", "value_sample");

	/*
	 * Test for void SearchResult(String, Object, Attributes)
	 */
	public void testSearchResult_StringObjectAttributes() {
		log.setMethod("testSearchResult_StringObjectAttributes()");
		SearchResult r;

		r = new SearchResult("name", "obj", attrs);
		assertEquals("java.lang.String", r.getClassName());
		assertEquals("name", r.getName());
		assertEquals("obj", r.getObject());
		assertEquals(attrs, r.getAttributes());
		assertTrue(r.isRelative());
	}

	/*
	 * Test for void SearchResult(String, Object, Attributes, boolean)
	 */
	public void testSearchResult_StringObjectAttributesboolean() {
		log.setMethod("testSearchResult_StringObjectAttributesboolean()");
		SearchResult r;

		r = new SearchResult("name", "obj", attrs, false);
		assertEquals("java.lang.String", r.getClassName());
		assertEquals("name", r.getName());
		assertEquals("obj", r.getObject());
		assertEquals(attrs, r.getAttributes());
		assertFalse(r.isRelative());
	}

	/*
	 * Test for void SearchResult(String, String, Object, Attributes)
	 */
	public void testSearchResult_StringStringObjectAttributes() {
		log.setMethod("testSearchResult_StringStringObjectAttributes()");
		SearchResult r;

		r = new SearchResult("name", "classname", "obj", attrs);
		assertEquals("classname", r.getClassName());
		assertEquals("name", r.getName());
		assertEquals("obj", r.getObject());
		assertEquals(attrs, r.getAttributes());
		assertTrue(r.isRelative());
	}

	/*
	 * Test for void SearchResult(String, String, Object, Attributes, boolean)
	 */
	public void testSearchResult_StringStringObjectAttributesboolean() {
		log.setMethod("testSearchResult_StringStringObjectAttributesboolean()");
		SearchResult r;

		r = new SearchResult("name", "classname", "obj", attrs, false);
		assertEquals("classname", r.getClassName());
		assertEquals("name", r.getName());
		assertEquals("obj", r.getObject());
		assertEquals(attrs, r.getAttributes());
		assertFalse(r.isRelative());
	}

	public void testSearchResult_NullAttributes() {
		log.setMethod("testSearchResult_NullAttributes()");

		try {
			 new SearchResult(null, null, null);
             fail("no illegal argument exception");
		} 
		catch (IllegalArgumentException e) {
			// Expected, name cannot be null
		}
	}

	public void testGetterAndSetter() {
		log.setMethod("testGetterAndSetter()");
		SearchResult r;

		r = new SearchResult("name", "obj", null);
		assertNull(r.getAttributes());
		r.setAttributes(attrs);
		assertEquals(attrs, r.getAttributes());
	}

	/*
	 * Test for String toString()
	 */
	public void testToString() {
		log.setMethod("testToString()");
		SearchResult r;

		r = new SearchResult("name", "obj", attrs);
		String str = r.toString();
		assertTrue(str.indexOf("name") >= 0);
		assertTrue(str.indexOf("obj") >= 0);
		assertTrue(str.indexOf("java.lang.String") >= 0);
		assertTrue(str.indexOf("id_sample") >= 0);
		assertTrue(str.indexOf("value_sample") >= 0);
	}

}
