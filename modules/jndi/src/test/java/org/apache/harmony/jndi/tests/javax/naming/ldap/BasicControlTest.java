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

package org.apache.harmony.jndi.tests.javax.naming.ldap;

import javax.naming.ldap.BasicControl;
import javax.naming.ldap.Control;
import junit.framework.TestCase;

public class BasicControlTest extends TestCase {
    /**
     * Test method for
     * {@link javax.naming.ldap.BasicControl#BasicControl(java.lang.String)}.
     */
    public void testBasicControlString() {
        BasicControl bc = new BasicControl("fixture");
        assertEquals("fixture", bc.getID());
    }

    /**
     * Test method for
     * {@link javax.naming.ldap.BasicControl#BasicControl(java.lang.String, boolean, byte[])}.
     */
    public void testBasicControlStringBooleanByteArray() {
        byte[] fixture = new byte[] { 0, 1 };
        BasicControl bc = new BasicControl("fixture", Control.CRITICAL, fixture);
        assertEquals("fixture", bc.getID());
        assertEquals(Control.CRITICAL, bc.isCritical());
        // spec says the byte[] is NOT copied
        assertSame(fixture, bc.getEncodedValue());
    }

    /**
     * Test method for {@link javax.naming.ldap.BasicControl#getEncodedValue()}.
     */
    public void testGetEncodedValue() {
        BasicControl bc = new BasicControl("fixture");
        assertNull(bc.getEncodedValue());
        byte[] fixture = new byte[] { 0, 1 };
        bc = new BasicControl("fixture", Control.CRITICAL, fixture);
        // spec says the byte[] is NOT copied
        assertSame(fixture, bc.getEncodedValue());
        
        // spec says that byte[] isn't copied and can be changed
        fixture[0] = Byte.MIN_VALUE;
        fixture[1] = Byte.MAX_VALUE;
        assertEquals(Byte.MIN_VALUE, bc.getEncodedValue()[0]);
        assertEquals(Byte.MAX_VALUE, bc.getEncodedValue()[1]);
    }

    /**
     * Test method for {@link javax.naming.ldap.BasicControl#getID()}.
     */
    public void testGetID() {
        BasicControl bc = new BasicControl("fixture");
        assertEquals("fixture", bc.getID());
        
        bc = new BasicControl(null);
        assertNull(bc.getID());
        
        bc = new BasicControl("");
        assertEquals("", bc.getID());
        
        bc = new BasicControl(null, false, null);
        assertNull(bc.getID());
    }

    /**
     * Test method for {@link javax.naming.ldap.BasicControl#isCritical()}.
     */
    public void testIsCritical() {
        BasicControl bc = new BasicControl("fixture");
        assertFalse(bc.isCritical());
        
        bc = new BasicControl(null, false, null);
        assertFalse(bc.isCritical());
        
        bc = new BasicControl(null, true, null);
        assertTrue(bc.isCritical());
    }
}
