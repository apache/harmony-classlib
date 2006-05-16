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
package ar.org.fitc.test.rmi.activation;

import java.rmi.activation.ActivationGroupID;
import ar.org.fitc.test.util.Messages;
import junit.framework.TestCase;

public class TestActivationGroupID extends TestCase implements Messages {


    public static void main(String[] args) {
    }

    protected void setUp() throws Exception {
        super.setUp();

    }

    protected void tearDown() throws Exception {
        super.tearDown();
    }

    /*
     * Test method for 'java.rmi.activation.ActivationGroupID.ActivationGroupID(ActivationSystem)'
     */
    public void testActivationGroupID001() {

        assertNotNull(msgNotNull, new ActivationGroupID(null));

    }

    /*
     * Test method for 'java.rmi.activation.ActivationGroupID.hashCode()'
     */
    public void testHashCode001() {

        ActivationGroupID at = new ActivationGroupID(null);
        ActivationGroupID at2 = new ActivationGroupID(null);
        assertNotSame("must not be equals", at.hashCode(), at2.hashCode());

    }

    public void testHashCode002() {

        ActivationGroupID at = new ActivationGroupID(null);

        int hc = at.hashCode();
        for (int i = 1; i < 15; i++) {
            assertEquals("must be equals", at.hashCode(), hc);
        }

    }

    /*
     * Test method for 'java.rmi.activation.ActivationGroupID.equals(Object)'
     */
    public void testEquals001() {

        ActivationGroupID at = new ActivationGroupID(null);

        assertTrue("must be equals", at.equals(at));

    }

    public void testEquals002() {

        ActivationGroupID at = new ActivationGroupID(null);

        assertFalse("mustn't be equals", at.equals(null));

    }

    public void testEquals003() {

        ActivationGroupID at = new ActivationGroupID(null);

        assertFalse("mustn't be equals", at.equals(new ActivationGroupID(null)));

    }

    public void testEquals004() {

        ActivationGroupID at = new ActivationGroupID(null);
        ActivationGroupID at2 = new ActivationGroupID(null);
        assertEquals("must be equals", at2.equals(at), at.equals(at2));

    }

    public void testEquals005() {

        ActivationGroupID at = new ActivationGroupID(null);

        Object o = new Object();
        assertFalse("must not be equals", at.equals(o));

    }

    public void testEquals006() {

        ActivationGroupID at = new ActivationGroupID(null);

        assertFalse("must not be equals", at.equals(null));

    }

    public void testEquals007() {

        ActivationGroupID at = new ActivationGroupID(null);

        for (int i = 1; i < 10; i++) {
            assertTrue("must be equals", at.equals(at));
        }

    }

    public void testEquals008() {

        ActivationGroupID at = new ActivationGroupID(null);

        for (int i = 1; i < 10; i++) {
            assertFalse("mustn't be equals", at.equals(null));
        }

    }

    public void testEquals009() {

        ActivationGroupID at = new ActivationGroupID(null);

        for (int i = 1; i < 10; i++) {
            assertFalse("mustn't be equals", at.equals(new ActivationGroupID(
                    null)));
        }

    }

    public void testEquals010() {

        ActivationGroupID at = new ActivationGroupID(null);
        ActivationGroupID at2 = new ActivationGroupID(null);
        for (int i = 1; i < 10; i++) {
            assertEquals("must be equals", at2.equals(at), at.equals(at2));
        }

    }

    public void testEquals011() {

        ActivationGroupID at = new ActivationGroupID(null);
        Object o = new Object();
        for (int i = 1; i < 10; i++) {
            assertFalse("must not be equals", at.equals(o));
        }

    }

    public void testEquals012() {

        ActivationGroupID at = new ActivationGroupID(null);

        for (int i = 1; i < 10; i++) {
            assertFalse("must not be equals", at.equals(null));
        }

    }

    /*
     * Test method for 'java.rmi.activation.ActivationGroupID.getSystem()'
     */
    public void testGetSystem001() {

        assertNull(new ActivationGroupID(null).getSystem());

    }

}
