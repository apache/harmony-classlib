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

package org.apache.harmony.tests.java.io;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;

import junit.framework.TestCase;

public class OutputStreamWriterTest extends TestCase {
	public void testGetEncoding_StreamClosed() {
		OutputStreamWriter out = null;
		try {
			out = new OutputStreamWriter(new ByteArrayOutputStream(),
					"UTF-16BE");
		} catch (UnsupportedEncodingException e) {
			fail("Should not throw UnsupportedEncodingException");
		}
		try {
			out.close();
		} catch (IOException e) {
			fail("Should not throw IOException");
		}
		String result = out.getEncoding();
		assertEquals(null, result);
	}

	public void testGetEncoding_NotHistorical() {
		OutputStreamWriter out = null;
		try {
			out = new OutputStreamWriter(new ByteArrayOutputStream(),
					"UTF-16BE");
		} catch (UnsupportedEncodingException e) {
			// ok
		}
		String result = out.getEncoding();
		assertEquals(result, "UnicodeBigUnmarked");

	}
}