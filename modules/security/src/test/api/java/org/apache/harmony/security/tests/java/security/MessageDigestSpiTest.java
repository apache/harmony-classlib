/*
 *  Licensed to the Apache Software Foundation (ASF) under one or more
 *  contributor license agreements.  See the NOTICE file distributed with
 *  this work for additional information regarding copyright ownership.
 *  The ASF licenses this file to You under the Apache License, Version 2.0
 *  (the "License"); you may not use this file except in compliance with
 *  the License.  You may obtain a copy of the License at
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

package org.apache.harmony.security.tests.java.security;

import java.nio.ByteBuffer;
import java.security.DigestException;
import java.security.MessageDigestSpi;

import junit.framework.TestCase;

/**
 * Tests for <code>MessageDigestSpi</code> constructor and methods
 */
public class MessageDigestSpiTest extends TestCase {

    /**
     * java.security.MessageDigestSpi#engineDigest(byte[], int, int)
     */
    public void test_engineDigestLB$LILI() throws Exception {

        final int DIGEST_LENGHT = 2;

        MyMessageDigest md = new MyMessageDigest() {

            public int engineGetDigestLength() {
                return DIGEST_LENGHT;
            }

            public byte[] engineDigest() {
                return new byte[DIGEST_LENGHT]; // return non-null value
            }
        };

        byte[] b = new byte[5];
        try {
            // test: null output buffer
            md.engineDigest(null, 1, DIGEST_LENGHT);
            fail("No expected NullPointerException");
        } catch (NullPointerException e) {
        }
        try {
            //test: len param < digest length
            md.engineDigest(b, 1, DIGEST_LENGHT - 1);
            fail("No expected DigestException");
        } catch (DigestException e) {
        }

        assertEquals("incorrect result", DIGEST_LENGHT, md
                .engineDigest(b, 1, 3));
    }

	public void testEngineGetDigestLength() {
		MyMessageDigest md = new MyMessageDigest();
		if (md.engineGetDigestLength() != 0) {
			fail("engineGetDigestLength failed");
		}
	}

	/*
	 * Class under test for void engineUpdate(ByteBuffer)
	 */
	public void testEngineUpdateByteBuffer() {
		MyMessageDigest md = new MyMessageDigest();
		byte[] b = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};

		ByteBuffer buf = ByteBuffer.wrap(b,0,b.length);
		buf.get(b);
		int l = buf.limit();
		md.engineUpdate(buf);
		if (buf.limit() !=l || buf.position() != l) {
			fail("Case 1. Incorrect position");
		}
		
		buf = ByteBuffer.wrap(b,0,b.length);
		buf.get();
		buf.get();
		buf.get();
		md.engineUpdate(buf);
		if (buf.limit() !=l || buf.position() != l) {
			fail("Case 2. Incorrect position");
		}
	}

    /**
     * @tests java.security.MessageDigestSpi#clone()
     */
    public void test_clone() throws CloneNotSupportedException {
        MyMessageDigest md = new MyMessageDigest();
        try {
            md.clone();
            fail("No expected CloneNotSupportedException");
        } catch (CloneNotSupportedException e) {
        }

        MyMessageDigestCloneable mdc = new MyMessageDigestCloneable();
        assertNotSame(mdc, mdc.clone());
    }

	private class MyMessageDigest extends MessageDigestSpi {
		
		public void engineReset() {}

		public byte[] engineDigest() {
			return null;
		}

		public void engineUpdate(byte arg0) {}

		public void engineUpdate(byte[] arg0, int arg1, int arg2) {}

        @Override
        protected int engineDigest(byte[] buf, int offset, int len)
                throws DigestException {
            return super.engineDigest(buf, offset, len);
        }

        @Override
        protected int engineGetDigestLength() {
            return super.engineGetDigestLength();
        }

        @Override
        protected void engineUpdate(ByteBuffer input) {
            super.engineUpdate(input);
        }
	}
    
    private class MyMessageDigestCloneable extends MyMessageDigest implements
            Cloneable {
    }
}
