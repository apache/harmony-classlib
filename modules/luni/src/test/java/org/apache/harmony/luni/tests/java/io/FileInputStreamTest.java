/* Copyright 2005 The Apache Software Foundation or its licensors, as applicable
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

package org.apache.harmony.luni.tests.java.io;

import java.io.File;
import java.io.FileInputStream;

import junit.framework.TestCase;

public class FileInputStreamTest extends TestCase {

    /**
     * @tests java.io.FileInputStream#read(byte[], int, int)
     */
    public void test_read$BII() throws Exception {
        // Regression test for HARMONY-285
        try {
            File file = new File("FileInputStream.tmp");
            file.createNewFile();
            file.deleteOnExit();
            FileInputStream in = new FileInputStream(file);
            in.read(null, 0, 0);
        } catch (NullPointerException e) {
        }
    }
}
