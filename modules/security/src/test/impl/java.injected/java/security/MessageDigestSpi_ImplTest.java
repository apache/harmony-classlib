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

package java.security;

import junit.framework.TestCase;


/**
 * Tests for <code>MessageDigestSpi</code> constructor and methods
 * 
 */
public class MessageDigestSpi_ImplTest extends TestCase {
	/*
	 * Class under test for int engineDigest(byte[], int, int)
	 */
	public void testEngineDigestbyteArrayintint() throws Exception {
        MyMessageDigest md = new MyMessageDigest();
        byte[] b = new byte[5];
        try {
            md.engineDigest(null, 1, 1);
            fail("No expected NullPointerException");
        } catch (NullPointerException e) {
        }
        try {
            md.engineDigest(b, 3, 10);
            fail("No expected DigestException");
        } catch (DigestException e) {
        }

        assertEquals("incorrect result", 0, md.engineDigest(b, 1, 3));
    }

	private class MyMessageDigest extends MessageDigestSpi {
		
		public void engineReset() {}

		public byte[] engineDigest() {
			return null;
		}

		public void engineUpdate(byte arg0) {}

		public void engineUpdate(byte[] arg0, int arg1, int arg2) {}

		public Object clone() throws CloneNotSupportedException {
			throw new CloneNotSupportedException();
		}
	}
}
