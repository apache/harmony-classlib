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

import java.nio.BufferOverflowException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;
import java.nio.charset.CoderMalfunctionError;
import java.nio.charset.CoderResult;
import java.nio.charset.CodingErrorAction;
import java.nio.charset.MalformedInputException;

import junit.framework.TestCase;

public class CharsetDecoderTest extends TestCase {

    /**
	 * @tests java.nio.charset.CharsetDecoder.CharsetDecoder(Charset, float,
	 *        float)
	 */
	public void test_ConstructorLjava_nio_charset_CharsetFF() {
		// Regression for HARMONY-142
		try {
			Charset cs = Charset.forName("UTF-8"); //$NON-NLS-1$
			new MockCharsetDecoderForHarmony142(cs, 1.1f, 1);
			fail("Assert 0: Should throw IllegalArgumentException."); //$NON-NLS-1$
		} catch (IllegalArgumentException e) {
			// expected
		}
	}

	/*
	 * MockCharsetDecoderForHarmony142: for constructor test
	 */
	static class MockCharsetDecoderForHarmony142 extends CharsetDecoder {
		protected MockCharsetDecoderForHarmony142(Charset cs,
				float averageBytesPerChar, float maxBytesPerChar) {
			super(cs, averageBytesPerChar, maxBytesPerChar);
		}

		protected CoderResult decodeLoop(ByteBuffer in, CharBuffer out) {
			return null;
		}
	}
 
	/**
	 * @tests java.nio.charset.CharsetDecoder#decode(java.nio.ByteBuffer)
	 */
	public void test_decode() throws CharacterCodingException {
		// Regression for HARMONY-33
//		ByteBuffer bb = ByteBuffer.allocate(1);
//		bb.put(0, (byte) 77);
//		CharsetDecoder decoder = Charset.forName("UTF-16").newDecoder();
//		decoder.onMalformedInput(CodingErrorAction.REPLACE);
//		decoder.onUnmappableCharacter(CodingErrorAction.REPLACE);
//		decoder.decode(bb);

		// Regression for HARMONY-67
//		byte[] b = new byte[] { (byte) 1 };
//		ByteBuffer buf = ByteBuffer.wrap(b);
//		CharBuffer charbuf = Charset.forName("UTF-16").decode(buf);
//		assertEquals("Assert 0: charset UTF-16", 1, charbuf.length());
//
//		charbuf = Charset.forName("UTF-16BE").decode(buf);
//		assertEquals("Assert 1: charset UTF-16BE", 0, charbuf.length());
//
//		charbuf = Charset.forName("UTF-16LE").decode(buf);
//		assertEquals("Assert 2: charset UTF16LE", 0, charbuf.length());
		
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
	
    /*
     * Test malfunction decode(ByteBuffer)
     */
    public void test_decodeLjava_nio_ByteBuffer() throws Exception {
		MockMalfunctionCharset cs1 = new MockMalfunctionCharset(
				"Harmony-124-1", null); //$NON-NLS-1$
		try {
			cs1.newDecoder().onMalformedInput(CodingErrorAction.REPLACE)
					.onUnmappableCharacter(CodingErrorAction.REPLACE).decode(
							ByteBuffer.wrap(new byte[] { 0x00, 0x11 }));
			fail("Assert 0: should throw CoderMalfunctionError");  // NON-NLS-1$
		} catch (CoderMalfunctionError e) {
			// expected
		}

		MockMalfunctionCharset cs2 = new MockMalfunctionCharset(
				"Harmony-124-2", null); //$NON-NLS-1$
		try {
			cs2.decode(ByteBuffer.wrap(new byte[] { 0x00, 0x11 }));
			fail("Assert 1: Charset.decode should throw CoderMalfunctionError");  // NON-NLS-1
		} catch (CoderMalfunctionError e) {
			// expected
		}
	}
    
	/*
	 * Mock charset class with malfunction decode & encode.
	 */
	static final class MockMalfunctionCharset extends Charset {

		public MockMalfunctionCharset(String canonicalName, String[] aliases) {
			super(canonicalName, aliases);
		}

		public boolean contains(Charset cs) {
			return false;
		}

		public CharsetDecoder newDecoder() {
			return new MockMalfunctionDecoder(this);
		}

		public CharsetEncoder newEncoder() {
			return new MockMalfunctionEncoder(this);
		}
	}

	/*
	 * Mock decoder. decodeLoop always throws unexpected exception.
	 */
	static class MockMalfunctionDecoder extends java.nio.charset.CharsetDecoder {

		public MockMalfunctionDecoder(Charset cs) {
			super(cs, 1, 10);
		}

		protected CoderResult decodeLoop(ByteBuffer in, CharBuffer out) {
			throw new BufferOverflowException();
		}
	}

	/*
	 * Mock encoder. encodeLoop always throws unexpected exception.
	 */
	static class MockMalfunctionEncoder extends java.nio.charset.CharsetEncoder {

		public MockMalfunctionEncoder(Charset cs) {
			super(cs, 1, 3, new byte[] { (byte) '?' });
		}

		protected CoderResult encodeLoop(CharBuffer in, ByteBuffer out) {
			throw new BufferOverflowException();
		}
	} 
}
