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

package org.apache.harmony.tests.java.util.zip;

import java.io.UnsupportedEncodingException;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

import junit.framework.TestCase;

public class InflaterTest extends TestCase {

	/**
	 * @tests java.util.zip.Inflater#needsDictionary()
	 */
    public void test_needsDictionary () {
    	// Regression test for HARMONY-86
        Inflater inf = new Inflater();
        assertFalse(inf.needsDictionary());
        assertEquals(0,inf.getTotalIn());
        assertEquals(0,inf.getTotalOut());
        assertEquals(0,inf.getBytesRead());
        assertEquals(0,inf.getBytesWritten());
    } 
    
	/**
	 * @tests java.util.zip.Deflater#getBytesRead()
	 */
	public void test_getBytesRead() throws DataFormatException,
			UnsupportedEncodingException {
		// Regression test for HARMONY-158
		Deflater def = new Deflater();
		Inflater inf = new Inflater();
		assertEquals(0, def.getTotalIn());
		assertEquals(0, def.getTotalOut());
		assertEquals(0, def.getBytesRead());
		// Encode a String into bytes
		String inputString = "blahblahblah??";
		byte[] input = inputString.getBytes("UTF-8");

		// Compress the bytes
		byte[] output = new byte[100];
		def.setInput(input);
		def.finish();
		def.deflate(output);
		inf.setInput(output);
		int compressedDataLength =inf.inflate(input);
		assertEquals(16, inf.getTotalIn());
		assertEquals(compressedDataLength, inf.getTotalOut());
		assertEquals(16, inf.getBytesRead());
	}
	
	/**
	 * @tests java.util.zip.Deflater#getBytesRead()
	 */
	public void test_getBytesWritten() throws DataFormatException, UnsupportedEncodingException {
		// Regression test for HARMONY-158
		Deflater def = new Deflater();
		Inflater inf = new Inflater();
		assertEquals(0, def.getTotalIn());
		assertEquals(0, def.getTotalOut());
		assertEquals(0, def.getBytesWritten());
		// Encode a String into bytes
		String inputString = "blahblahblah??";
		byte[] input = inputString.getBytes("UTF-8");

		// Compress the bytes
		byte[] output = new byte[100];
		def.setInput(input);
		def.finish();
		def.deflate(output);
		inf.setInput(output);
		int compressedDataLength =inf.inflate(input);
		assertEquals(16, inf.getTotalIn());
		assertEquals(compressedDataLength, inf.getTotalOut());
		assertEquals(14, inf.getBytesWritten());
	}
}
