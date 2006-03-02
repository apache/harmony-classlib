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
import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;
import java.nio.charset.CoderResult;

import junit.framework.TestCase;

public class CharsetEncoderTest extends TestCase {

	/**
	 * @tests java.nio.charset.CharsetEncoder.CharsetEncoder(
	 *        java.nio.charset.Charset, float, float)
	 */
	public void test_ConstructorLjava_nio_charset_CharsetFF() {
		// Regression for HARMONY-141
		try {
			Charset cs = Charset.forName("UTF-8"); //$NON-NLS-1$
			new MockCharsetEncoderForHarmony141(cs, 1.1f, 1);
			fail("Assert 0: Should throw IllegalArgumentException."); //$NON-NLS-1$
		} catch (IllegalArgumentException e) {
			// expected
		}

		try {
			Charset cs = Charset.forName("ISO8859-1"); //$NON-NLS-1$
			new MockCharsetEncoderForHarmony141(cs, 1.1f, 1,
					new byte[] { 0x1a });
			fail("Assert 1: Should throw IllegalArgumentException."); //$NON-NLS-1$
		} catch (IllegalArgumentException e) {
			// expected
		}
	}

	/**
	 * Helper for contructor tests
	 */
	public static class MockCharsetEncoderForHarmony141 extends CharsetEncoder {

		protected MockCharsetEncoderForHarmony141(Charset cs,
				float averageBytesPerChar, float maxBytesPerChar) {
			super(cs, averageBytesPerChar, maxBytesPerChar);
		}

		public MockCharsetEncoderForHarmony141(Charset cs,
				float averageBytesPerChar, float maxBytesPerChar,
				byte[] replacement) {
			super(cs, averageBytesPerChar, maxBytesPerChar, replacement);
		}

		protected CoderResult encodeLoop(CharBuffer in, ByteBuffer out) {
			return null;
		}

	}
}
