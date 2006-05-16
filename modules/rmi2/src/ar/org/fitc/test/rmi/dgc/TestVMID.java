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
package ar.org.fitc.test.rmi.dgc;

import java.rmi.dgc.VMID;

import java.util.StringTokenizer;

import ar.org.fitc.test.util.Messages;

import junit.framework.TestCase;

public class TestVMID extends TestCase implements Messages {
    VMID vmid = null;

    public static void main(String[] args) {
        junit.textui.TestRunner.run(TestVMID.class);
    }

    public TestVMID(String name) {
        super(name);
    }

    protected void setUp() throws Exception {
        super.setUp();
    }

    protected void tearDown() throws Exception {
        super.tearDown();
    }

    /*
     * Test method for 'java.rmi.dgc.VMID.hashCode()'
     */
    public final void testHashCode001() {
        assertNotSame("must not be equals", new VMID().hashCode(), new VMID()
                .hashCode());
    }

    public final void testHashCode002() {
        vmid = new VMID();
        int hc = vmid.hashCode();
        for (int i = 1; i < 15; i++) {
            assertEquals("must be equals", vmid.hashCode(), hc);
        }
    }

    /*
     * Test method for 'java.rmi.dgc.VMID.equals(Object)'
     */
    public void testEquals001() {

        vmid = new VMID();
        assertTrue("must be equals", vmid.equals(vmid));

    }

    public void testEquals002() {
        vmid = new VMID();
        assertFalse("mustn't be equals", vmid.equals(null));

    }

    public void testEquals003() {
        vmid = new VMID();
        assertFalse("mustn't be equals", vmid.equals(new VMID()));

    }

    public void testEquals004() {
        vmid = new VMID();
        VMID uid2 = new VMID();
        assertEquals("must be equals", uid2.equals(vmid), vmid.equals(uid2));

    }

    public void testEquals005() {
        vmid = new VMID();
        Object o = new Object();
        assertFalse("must not be equals", vmid.equals(o));

    }

    public void testEquals006() {
        vmid = new VMID();
        assertFalse("must not be equals", vmid.equals(null));

    }

    public void testEquals007() {
        vmid = new VMID();
        for (int i = 1; i < 10; i++) {
            assertTrue("must be equals", vmid.equals(vmid));
        }

    }

    public void testEquals008() {
        vmid = new VMID();
        for (int i = 1; i < 10; i++) {
            assertFalse("mustn't be equals", vmid.equals(null));
        }

    }

    public void testEquals009() {
        vmid = new VMID();
        for (int i = 1; i < 10; i++) {
            assertFalse("mustn't be equals", vmid.equals(new VMID()));
        }

    }

    public void testEquals010() {
        vmid = new VMID();
        VMID uid2 = new VMID();
        for (int i = 1; i < 10; i++) {
            assertEquals("must be equals", uid2.equals(vmid), vmid.equals(uid2));
        }

    }

    public void testEquals011() {
        vmid = new VMID();
        Object o = new Object();
        for (int i = 1; i < 10; i++) {
            assertFalse("must not be equals", vmid.equals(o));
        }

    }

    public void testEquals012() {
        vmid = new VMID();
        for (int i = 1; i < 10; i++) {
            assertFalse("must not be equals", vmid.equals(null));
        }

    }

    /*
     * Test method for 'java.rmi.dgc..toString()'
     */
    public final void testToString001() {
        vmid = new VMID();
        System.out.print(vmid.toString());

        StringTokenizer st = new StringTokenizer(vmid.toString(), ":");
        String s[] = new String[5];
        for (int i = 0; i < 4; i++) {
            s[i] = st.nextToken(":");
        }
        VMID vmid2 = new VMID();
        StringTokenizer st2 = new StringTokenizer(vmid2.toString(), ":");
        String s2[] = new String[5];
        for (int i = 0; i < 4; i++) {
            s2[i] = st2.nextToken(":");
        }
        for (int i = 0; i < 3; i++) {
            assertEquals(s[i], s2[i]);
        }
        assertNotSame(s[3], s2[3]);
    }

    /*
     * Test method for 'java.rmi.dgc..()'
     */
    public final void testVMID001() {
        assertNotNull(msgNotNull, new VMID());
    }

    /*
     * Test method for 'java.rmi.dgc..isUnique()'
     */
    public final void testIsUnique001() {
        assertTrue(new VMID().isUnique());
    }

}
