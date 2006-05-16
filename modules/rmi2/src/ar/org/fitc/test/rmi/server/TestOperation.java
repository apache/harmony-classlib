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
 * @author Hugo Beilis
 * @author Osvaldo Demo
 * @author Jorge Rafael
 * @version 1.0
 */
package ar.org.fitc.test.rmi.server;

import java.rmi.server.Operation;

import ar.org.fitc.test.util.Messages;

import junit.framework.TestCase;

public class TestOperation extends TestCase implements Messages {
    Operation o;

    public static void main(String[] args) {
        junit.textui.TestRunner.run(TestOperation.class);
    }

    public TestOperation(String name) {
        super(name);
    }

    protected void setUp() throws Exception {
        super.setUp();
    }

    protected void tearDown() throws Exception {
        super.tearDown();
    }

    /*
     * Test method for 'java.rmi.server.Operation.Operation(String)'
     */
    public void testOperation001() {
        assertNotNull(msgNotNull, new Operation(
                "greaqy5m, o8985453|´*'00/08.8,-"));
    }

    public void testOperation002() {
        assertNotNull(msgNotNull, new Operation(""));
    }

    public void testOperation003() {
        assertNotNull(msgNotNull, new Operation(null));
    }

    /*
     * Test method for 'java.rmi.server.Operation.getOperation()'
     */
    public void testGetOperation001() {
        o = new Operation("xfohgtrajytklourfiñourfysahtawt'37iú6-szhk'´4w798'2");
        assertEquals("xfohgtrajytklourfiñourfysahtawt'37iú6-szhk'´4w798'2", o
                .getOperation());
    }

    public void testGetOperation002() {
        o = new Operation("");
        assertEquals("", o.getOperation());
    }

    public void testGetOperation003() {
        o = new Operation(null);
        assertEquals(null, o.getOperation());
    }

    /*
     * Test method for 'java.rmi.server.Operation.toString()'
     */

    public void testToString001() {
        o = new Operation("xfohgtrajytklourfiñourfysahtawt'37iú6-szhk'´4w798'2");
        assertEquals("xfohgtrajytklourfiñourfysahtawt'37iú6-szhk'´4w798'2", o
                .toString());
    }

    public void testToString002() {
        o = new Operation("");
        assertEquals("", o.toString());
    }

    public void testToString003() {
        o = new Operation(null);
        assertEquals(null, o.toString());
    }
}
