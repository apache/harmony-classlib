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

import java.rmi.MarshalledObject;
import java.rmi.activation.ActivationDesc;
import java.rmi.activation.ActivationException;
import java.rmi.activation.ActivationGroup_Stub;
import java.rmi.activation.ActivationID;
import java.rmi.server.RemoteRef;

import junit.framework.TestCase;
import ar.org.fitc.test.util.Messages;

public class TestActivationGroup_Stub extends TestCase implements Messages {
    public static void main(String[] args) {
        junit.textui.TestRunner.run(TestActivationGroup_Stub.class);
    }

    public TestActivationGroup_Stub(String name) {
        super(name);
    }

    protected void setUp() throws Exception {
        super.setUp();
    }

    protected void tearDown() throws Exception {
        super.tearDown();
    }

    /*
     * Test method for
     * 'java.rmi.activation.ActivationGroup_Stub.ActivationGroup_Stub(RemoteRef)'
     */
    /**
     * This method test that for a <b>ActivationGroup_Stub(RemoteRef ref)</b>
     * should not return null when a null is passed as parameter
     */
    public void testActivationGroup_Stub001() {
        assertNotNull(new ActivationGroup_Stub(null));
    }

    /**
     * This method test that for a <b>ActivationGroup_Stub(RemoteRef ref)</b>
     * should not return null when a RemoteRef whose value is null is passed as
     * parameter
     */
    public void testActivationGroup_Stub002() {
        RemoteRef rr = null;
        assertNotNull(new ActivationGroup_Stub(rr));
    }

    /*
     * Test method for
     * 'java.rmi.activation.ActivationGroup_Stub.newInstance(ActivationID,
     * ActivationDesc)'
     */

    public void testNewInstance001() {
        ActivationGroup_Stub ag = new ActivationGroup_Stub(null);
        try {
            ag.newInstance(new ActivationID(null), new ActivationDesc(
                    "myClass", "myLocation", new MarshalledObject(null)));
        } catch (ActivationException e) {
        } catch (Throwable e) {
            fail(msgNoException + e);
        }
    }
}
