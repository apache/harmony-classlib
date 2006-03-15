/* Copyright 2004 The Apache Software Foundation or its licensors, as applicable
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

package tests.api.java.nio.charset;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;
import java.nio.charset.CoderResult;
import java.nio.charset.CodingErrorAction;
import java.nio.charset.UnmappableCharacterException;

public class ASCCharsetEncoderTest extends CharsetEncoderTest {

	// charset for ascii
	private static final Charset CS = Charset.forName("ascii");

	/*
	 * @see CharsetEncoderTest#setUp()
	 */
	protected void setUp() throws Exception {
		cs = CS;
		super.setUp();
	}

	/*
	 * @see CharsetEncoderTest#tearDown()
	 */
	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public void testCanEncodeCharSequence() {
		// normal case for ascCS
		assertTrue(encoder.canEncode("\u0077"));
		assertFalse(encoder.canEncode("\uc2a3"));
		assertFalse(encoder.canEncode("\ud800\udc00"));
		try {
			encoder.canEncode(null);
		} catch (NullPointerException e) {
		}
		assertTrue(encoder.canEncode(""));
	}

	public void testCanEncodeICUBug() {
		assertFalse(encoder.canEncode('\ud800'));
		assertFalse(encoder.canEncode("\ud800"));
	}

	public void testCanEncodechar() throws CharacterCodingException {
		assertTrue(encoder.canEncode('\u0077'));
		assertFalse(encoder.canEncode('\uc2a3'));
	}

	public void testSpecificDefaultValue() {
		assertTrue(encoder.averageBytesPerChar() == 1);
		assertTrue(encoder.maxBytesPerChar() == 1);
	}

	CharBuffer getMalformedCharBuffer() {
		return CharBuffer.wrap("\ud800 buffer");
	}

	CharBuffer getUnmapCharBuffer() {
		return CharBuffer.wrap("\ud800\udc00 buffer");
	}

	CharBuffer getExceptionCharBuffer() {
		return null;
	}

	protected byte[] getIllegalByteArray() {
		return new byte[] { (byte) -1 };
	}

	public void testMultiStepEncode() throws CharacterCodingException {
		encoder.onMalformedInput(CodingErrorAction.REPORT);
		encoder.onUnmappableCharacter(CodingErrorAction.REPORT);
		try {
			encoder.encode(CharBuffer.wrap("\ud800\udc00"));
			fail("should unmappable");
		} catch (UnmappableCharacterException e) {
		}
		encoder.reset();
		ByteBuffer out = ByteBuffer.allocate(10);
		assertTrue(encoder.encode(CharBuffer.wrap("\ud800"), out, true)
				.isMalformed());
		encoder.flush(out);
		encoder.reset();
		out = ByteBuffer.allocate(10);
		assertSame(CoderResult.UNDERFLOW, encoder.encode(CharBuffer
				.wrap("\ud800"), out, false));
		assertTrue(encoder.encode(CharBuffer.wrap("\udc00"), out, true)
				.isMalformed());
	}

}
