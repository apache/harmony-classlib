/*
 *  Copyright 2005 The Apache Software Foundation or its licensors, as applicable.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

/**
* @author Boris V. Kuznetsov
* @version $Revision$
*/

package com.openintel.drlx.crypto;

import java.nio.ByteBuffer;

import javax.crypto.ShortBufferException;

import com.openintel.drl.security.test.PerformanceTest;

/**
 *
 * Tests for NullCipher implementation
 */
public class NullCipherSpiTest extends PerformanceTest {

	public void testEngineGetBlockSize() {
		NullCipherSpi spi = new NullCipherSpi();
		if (spi.engineGetBlockSize() != 1) {
			fail("incorrect block size");
		}
	}

	public void testEngineGetOutputSize() {
		NullCipherSpi spi = new NullCipherSpi();
		if (spi.engineGetOutputSize(100) != 100) {
			fail("incorrect output size");
		}
	}

	public void testEngineGetIV() {
		NullCipherSpi spi = new NullCipherSpi();
		if (spi.engineGetIV() != null) {
			fail("incorrect IV");
		}
	}

	/*
	 * Class under test for byte[] engineUpdate(byte[], int, int)
	 */
	public void testEngineUpdatebyteArrayintint() {
		NullCipherSpi spi = new NullCipherSpi();
		byte[] b = {1,2,3,4,5,6,7,8,9};
		byte[] b1 =  spi.engineUpdate(b, 3, 4);
		for (int i = 0; i < 4; i++) {
			if ( b[3+i] != b1[i] ) {
				fail("incorrect update result");
			}
		}
	}

	/*
	 * Class under test for int engineUpdate(byte[], int, int, byte[], int)
	 */
	public void testEngineUpdatebyteArrayintintbyteArrayint() {
		NullCipherSpi spi = new NullCipherSpi();
		byte[] b = {1,2,3,4,5,6,7,8,9};
		byte[] b1 =  new byte[10];
		int n = -1;
		try {
			n =	spi.engineUpdate(b, 3, 4, b1, 5);
		} catch (ShortBufferException e) {
			fail(e.toString());
		}
		if (n != 4) {
			fail("incorrect update result");
		}
		for (int i = 0; i < 4; i++) {
			if ( b[3+i] != b1[5+i] ) {
				fail("incorrect update result");
			}
		}	
	}

	/*
	 * Class under test for byte[] engineDoFinal(byte[], int, int)
	 */
	public void testEngineDoFinalbyteArrayintint() {
		NullCipherSpi spi = new NullCipherSpi();
		byte[] b = {1,2,3,4,5,6,7,8,9};
		byte[] b1 = null; 
		try {
			b1 = spi.engineDoFinal(b, 3, 4);
		} catch (Exception e) {
			fail(e.toString());
		}
		for (int i = 0; i < 4; i++) {
			if ( b[3+i] != b1[i] ) {
				fail("incorrect doFinal result");
			}
		}
	}

	/*
	 * Class under test for int engineDoFinal(byte[], int, int, byte[], int)
	 */
	public void testEngineDoFinalbyteArrayintintbyteArrayint() {
		NullCipherSpi spi = new NullCipherSpi();
		byte[] b = {1,2,3,4,5,6,7,8,9};
		byte[] b1 =  new byte[10];
		int n = -1;
		try {
			n =	spi.engineDoFinal(b, 3, 4, b1, 5);
		} catch (ShortBufferException e) {
			fail(e.toString());
		} catch (Exception e) {
			fail(e.toString());
		}
		if (n != 4) {
			fail("incorrect doFinal result");
		}
		for (int i = 0; i < 4; i++) {
			if ( b[3+i] != b1[5+i] ) {
				fail("incorrect doFinal result");
			}
		}
		
	}

	/*
	 * Class under test for int engineUpdate(ByteBuffer, ByteBuffer)
	 */
	public void testEngineUpdateByteBufferByteBuffer() {
		NullCipherSpi spi = new NullCipherSpi();
		byte[] b = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};

		ByteBuffer inbuf = ByteBuffer.wrap(b,0,b.length);
		ByteBuffer outbuf = ByteBuffer.allocate(6);
		
		try {
			spi.engineUpdate(null, outbuf);
			fail("No expected NullPointerException");
		} catch (NullPointerException e) {	
		} catch (ShortBufferException e) {
			fail(e.toString());
		}
		
		try {
			spi.engineUpdate(inbuf, null);
			fail("No expected NullPointerException");
		} catch (NullPointerException e) {	
		} catch (ShortBufferException e) {
			fail(e.toString());
		}
		
		inbuf.get();
		inbuf.get();
		inbuf.get();
		inbuf.get();
		int result = 0;
		try {
			result = spi.engineUpdate(inbuf, outbuf);
			if (result != b.length - 4) {
				fail("incorrect result " + result);
			}
		} catch (ShortBufferException e) {
			fail(e.toString());
		}
		for (int i = 0; i < result; i++) {
			if (outbuf.get(i) != i + 4) {		
				fail("incorrect outbuf");
			}
		}
		
		inbuf = ByteBuffer.wrap(b,0,b.length);
		outbuf = ByteBuffer.allocate(5);
		inbuf.get();
		inbuf.get();
		inbuf.get();
		inbuf.get();
		try {
			spi.engineUpdate(inbuf, outbuf);
			fail("No expected ShortBufferException");
		} catch (ShortBufferException e) {
		} 
	}

	/*
	 * Class under test for int engineDoFinal(ByteBuffer, ByteBuffer)
	 */
	public void testEngineDoFinalByteBufferByteBuffer() {
		NullCipherSpi spi = new NullCipherSpi();
		byte[] b = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};

		ByteBuffer inbuf = ByteBuffer.wrap(b,0,b.length);
		ByteBuffer outbuf = ByteBuffer.allocate(6);
		
		try {
			spi.engineDoFinal(null, outbuf);
			fail("No expected NullPointerException");
		} catch (NullPointerException e) {	
		} catch (ShortBufferException e) {
			fail(e.toString());
		} catch (javax.crypto.BadPaddingException e) {
			fail(e.toString());			
		} catch (javax.crypto.IllegalBlockSizeException e) {
			fail(e.toString());			
		}
		
		try {
			spi.engineDoFinal(inbuf, null);
			fail("No expected NullPointerException");
		} catch (NullPointerException e) {	
		} catch (ShortBufferException e) {
			fail(e.toString());
		} catch (javax.crypto.BadPaddingException e) {
			fail(e.toString());			
		} catch (javax.crypto.IllegalBlockSizeException e) {
			fail(e.toString());			
		}
		
		inbuf.get();
		inbuf.get();
		inbuf.get();
		inbuf.get();
		int result = 0;
		try {
			result = spi.engineDoFinal(inbuf, outbuf);
			if (result != b.length - 4) {
				fail("incorrect result " + result);
			}
		} catch (ShortBufferException e) {
			fail(e.toString());
		} catch (javax.crypto.BadPaddingException e) {
			fail(e.toString());			
		} catch (javax.crypto.IllegalBlockSizeException e) {
			fail(e.toString());			
		}
		for (int i = 0; i < result; i++) {
			if (outbuf.get(i) != i + 4) {		
				fail("incorrect outbuf");
			}
		}
		
		inbuf = ByteBuffer.wrap(b,0,b.length);
		outbuf = ByteBuffer.allocate(5);
		inbuf.get();
		inbuf.get();
		inbuf.get();
		inbuf.get();
		try {
			spi.engineDoFinal(inbuf, outbuf);
			fail("No expected ShortBufferException");
		} catch (ShortBufferException e) {
		} catch (javax.crypto.BadPaddingException e) {
			fail(e.toString());			
		} catch (javax.crypto.IllegalBlockSizeException e) {
			fail(e.toString());			
		}
	}

	/*
	 * Class under test for byte[] engineWrap(Key)
	 */
	public void testEngineWrapKey() {
		NullCipherSpi spi = new NullCipherSpi();
		try {
			spi.engineWrap(null);
			fail("No expected UnsupportedOperationException");
		} catch (UnsupportedOperationException e) {
		} catch (Exception e) {
			fail(e.toString());
		}	}

	/*
	 * Class under test for Key engineUnwrap(byte[], String, int)
	 */
	public void testEngineUnwrapbyteArrayStringint() {
		NullCipherSpi spi = new NullCipherSpi();
		try {
			spi.engineUnwrap(new byte[3], "", 10);
			fail("No expected UnsupportedOperationException");
		} catch (UnsupportedOperationException e) {
		} catch (Exception e) {
			fail(e.toString());
		} 
	}

	/*
	 * Class under test for int engineGetKeySize(Key)
	 */
	public void testEngineGetKeySize() {
		NullCipherSpi spi = new NullCipherSpi();
		try {
			spi.engineGetKeySize(null);
			fail("No expected UnsupportedOperationException");
		} catch (UnsupportedOperationException e) {
		} catch (Exception e) {
			fail(e.toString());
		} 
	}

}
