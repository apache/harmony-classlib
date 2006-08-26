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

package org.apache.harmony.rmi;

import java.rmi.ServerError;

import junit.framework.TestCase;

public class ServerErrorTest extends TestCase {

    /**
     * {@link java.rmi.ServerError#ServerError(java.lang.String, java.lang.Error)}.
     */
    public void testServerError() {
        Error t = new Error();
        ServerError e = new ServerError("fixture", t);
        assertTrue(e.getMessage().indexOf("fixture") > -1);
        assertSame(t, e.getCause());
        assertSame(t, e.detail);
    }

}
