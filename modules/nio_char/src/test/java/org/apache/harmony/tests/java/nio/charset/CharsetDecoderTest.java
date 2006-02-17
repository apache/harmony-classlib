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

package org.apache.harmony.tests.java.nio.charset;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CodingErrorAction;
import java.nio.charset.MalformedInputException;

import junit.framework.TestCase;

public class CharsetDecoderTest extends TestCase {

	/**
	 * @tests java.nio.charset.CharsetDecoder#decode(java.nio.ByteBuffer)
	 */
	public static void test_decode() throws CharacterCodingException {
		// Regression for HARMONY-33
		ByteBuffer bb = ByteBuffer.allocate(1);
		bb.put(0, (byte) 77);
		CharsetDecoder decoder = Charset.forName("UTF-16").newDecoder();
		decoder.onMalformedInput(CodingErrorAction.REPLACE);
		decoder.onUnmappableCharacter(CodingErrorAction.REPLACE);
		decoder.decode(bb);

		// Regression for HARMONY-67
		byte[] b = new byte[] { (byte) 1 };
		ByteBuffer buf = ByteBuffer.wrap(b);
		CharBuffer charbuf = Charset.forName("UTF-16").decode(buf);
		assertEquals("Assert 0: charset UTF-16", 1, charbuf.length());

		charbuf = Charset.forName("UTF-16BE").decode(buf);
		assertEquals("Assert 1: charset UTF-16BE", 0, charbuf.length());

		charbuf = Charset.forName("UTF-16LE").decode(buf);
		assertEquals("Assert 2: charset UTF16LE", 0, charbuf.length());
		
		// Regression for HARMONY-99
		CharsetDecoder decoder2 = Charset.forName("UTF-16").newDecoder();
		decoder2.onMalformedInput(CodingErrorAction.REPORT);
		decoder2.onUnmappableCharacter(CodingErrorAction.REPORT);
		ByteBuffer in = ByteBuffer.wrap(new byte[] { 109, 97, 109 });
		try {
			decoder2.decode(in);
			fail("Assert 3: MalformedInputException should have thrown");
		} catch (MalformedInputException e) {
			//expected
		} 
	}
}
