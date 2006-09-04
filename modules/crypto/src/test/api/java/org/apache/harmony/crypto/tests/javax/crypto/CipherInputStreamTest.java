/*
 *  Copyright 2006 The Apache Software Foundation or its licensors, as applicable.
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

package org.apache.harmony.crypto.tests.javax.crypto;

import junit.framework.TestCase;
import javax.crypto.CipherInputStream;
import javax.crypto.NullCipher;

public class CipherInputStreamTest extends TestCase {

    /**
     * @tests javax.crypto.CipherInputStream#read(byte[] b, int off, int len)
     */
    public void testReadBII() throws Exception {
        // Regression for HARMONY-1080
        CipherInputStream stream = new CipherInputStream(null, new NullCipher());
        try {
            stream.read(new byte[1], 1, 0);
            fail("NullPointerException expected");
        } catch (NullPointerException e) {
            // expected
        }
    }
}
