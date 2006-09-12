/*
 * Copyright 2006 The Apache Software Foundation or its licensors, as applicable
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
 *
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.harmony.rmi.server;

import java.rmi.server.LogStream;
import junit.framework.TestCase;

public class LogStreamTest extends TestCase {
    /**
     * Test for java.rmi.server.LogStream.write(int b)
     */
    public void testWriteI() throws Exception {
        //regression test for HARMONY-1271
        LogStream.log("tst").write((int)'\n');

        //regression test for HARMONY-994
        LogStream.log("tst").write(0);
    }
    
    public void testSetOutputStreamBad() throws Exception {
        // Regression test HARMONY-1198
        try {
            LogStream ls = LogStream.log("proba");
            ls.setOutputStream(null);
            fail("Expected NPE");
        } catch (NullPointerException e) {
            // expected
        }
    }
    
}
