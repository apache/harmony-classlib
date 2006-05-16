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

import java.rmi.dgc.Lease;
import java.rmi.dgc.VMID;

import ar.org.fitc.test.util.Messages;
import junit.framework.TestCase;

public class TestLease extends TestCase implements Messages {
    VMID id = null;

    Lease l = null;

    public static void main(String[] args) {
        junit.textui.TestRunner.run(TestLease.class);
    }

    public TestLease(String name) {
        super(name);
    }

    protected void setUp() throws Exception {
        super.setUp();

    }

    protected void tearDown() throws Exception {
        super.tearDown();
    }

    /*
     * Test method for 'java.rmi.dgc.Lease.Lease(VMID, long)'
     */
    public void testLease001() {
        long duration = 0;
        assertNotNull(msgNotNull, new Lease(id, duration));
    }

    public void testLease002() {
        id = new VMID();
        long duration = 0;
        assertNotNull(msgNotNull, new Lease(id, duration));
    }

    public void testLease003() {
        long duration = Long.MAX_VALUE;
        assertNotNull(msgNotNull, new Lease(id, duration));
    }

    public void testLease004() {
        id = new VMID();
        long duration = Long.MAX_VALUE;
        assertNotNull(msgNotNull, new Lease(id, duration));
    }

    public void testLease005() {
        long duration = Long.MIN_VALUE;
        assertNotNull(msgNotNull, new Lease(id, duration));
    }

    public void testLease006() {
        id = new VMID();
        long duration = Long.MIN_VALUE;
        assertNotNull(msgNotNull, new Lease(id, duration));
    }

    public void testLease007() {
        long duration = 57690;
        assertNotNull(msgNotNull, new Lease(id, duration));
    }

    public void testLease008() {
        id = new VMID();
        long duration = 57690;
        assertNotNull(msgNotNull, new Lease(id, duration));
    }

    public void testLease009() {
        long duration = -57689;
        assertNotNull(msgNotNull, new Lease(id, duration));
    }

    public void testLease010() {
        id = new VMID();
        long duration = -57689;
        assertNotNull(msgNotNull, new Lease(id, duration));
    }

    /*
     * Test method for 'java.rmi.dgc.Lease.getVMID()'
     */
    public void testGetVMID001() {
        long duration = 0;
        l = new Lease(id, duration);
        assertNull(l.getVMID());
    }

    public void testGetVMID002() {
        id = new VMID();
        long duration = 0;
        l = new Lease(id, duration);
        assertNotNull(l.getVMID());
    }

    public void testGetVMID003() {
        long duration = Long.MAX_VALUE;
        l = new Lease(id, duration);
        assertNull(l.getVMID());
    }

    public void testGetVMID004() {
        id = new VMID();
        long duration = Long.MAX_VALUE;
        l = new Lease(id, duration);
        assertNotNull(l.getVMID());
    }

    public void testGetVMID005() {
        long duration = Long.MIN_VALUE;
        l = new Lease(id, duration);
        assertNull(l.getVMID());
    }

    public void testGetVMID006() {
        id = new VMID();
        long duration = Long.MIN_VALUE;
        l = new Lease(id, duration);
        assertNotNull(l.getVMID());
    }

    public void testGetVMID007() {
        long duration = 57690;
        l = new Lease(id, duration);
        assertNull(l.getVMID());
    }

    public void testGetVMID008() {
        id = new VMID();
        long duration = 57690;
        l = new Lease(id, duration);
        assertNotNull(l.getVMID());
    }

    public void testGetVMID009() {
        long duration = -57689;
        l = new Lease(id, duration);
        assertNull(l.getVMID());
    }

    public void testGetVMID010() {
        id = new VMID();
        long duration = -57689;
        l = new Lease(id, duration);
        assertNotNull(l.getVMID());
    }

    /*
     * Test method for 'java.rmi.dgc.Lease.getValue()'
     */
    public void testGetValue001() {
        long duration = 0;
        l = new Lease(id, duration);
        assertEquals(l.getValue(), 0);
    }

    public void testGetValue002() {
        id = new VMID();
        long duration = 0;
        l = new Lease(id, duration);
        assertEquals(l.getValue(), 0);
    }

    public void testGetValue003() {
        long duration = Long.MAX_VALUE;
        l = new Lease(id, duration);
        assertEquals(l.getValue(), Long.MAX_VALUE);
    }

    public void testGetValue004() {
        id = new VMID();
        long duration = Long.MAX_VALUE;
        l = new Lease(id, duration);
        assertEquals(l.getValue(), Long.MAX_VALUE);
    }

    public void testGetValue005() {
        long duration = Long.MIN_VALUE;
        l = new Lease(id, duration);
        assertEquals(l.getValue(), Long.MIN_VALUE);
    }

    public void testGetValue006() {
        id = new VMID();
        long duration = Long.MIN_VALUE;
        l = new Lease(id, duration);
        assertEquals(l.getValue(), Long.MIN_VALUE);
    }

    public void testGetValue007() {
        long duration = 57690;
        l = new Lease(id, duration);
        assertEquals(l.getValue(), 57690);
    }

    public void testGetValue008() {
        id = new VMID();
        long duration = 57690;
        l = new Lease(id, duration);
        assertEquals(l.getValue(), 57690);
    }

    public void testGetValue009() {
        long duration = -57689;
        l = new Lease(id, duration);
        assertEquals(l.getValue(), -57689);
    }

    public void testGetValue010() {
        id = new VMID();
        long duration = -57689;
        l = new Lease(id, duration);
        assertEquals(l.getValue(), -57689);
    }
}
