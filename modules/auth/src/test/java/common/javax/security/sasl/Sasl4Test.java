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

package javax.security.sasl;

import java.security.Provider;
import java.security.Security;

import javax.security.auth.callback.CallbackHandler;

import junit.framework.TestCase;

import org.apache.harmony.auth.tests.support.SpiEngUtils;

/**
 * Test for Sasl class
 * 
 */
public class Sasl4Test extends TestCase {
    private static final String SRVSSRV = "SaslServerFactory.";

    private static final String fServerClass = "javax.security.sasl.mySaslServerFactory";

    private Provider [] provs;
    private boolean initProvs = false;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        if (!initProvs) {
            provs = Security.getProviders();
            initProvs = true;
        }
        if (provs != null) {
            for (Provider element : provs) {
                Security.removeProvider(element.getName());
            }
        }
    }

    protected Provider[] mProv;

    private void addProviders() {
        for (Provider element : mProv) {
            Security.insertProviderAt(element, 1);
        }
    }

    /*
     * @see TestCase#tearDown()
     */
    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
        if (mProv != null) {
            for (Provider element : mProv) {
                Security.removeProvider(element.getName());
            }
        }
        if (provs != null) {
            for (int i = 0; i < provs.length; i++) {
                Security.insertProviderAt(provs[i], (i+1));
            }
        }    }

    /**
     * Test for <code>createSaslServer(String mechanism, 
     *      String protocol, String serverName,
     *      Map prop, CallbackHandler cbh))</code> method 
     * Assertions:
     * throws NullPointerException when mechanism is null;
     * throws SaslException when parameters (protocol, cbh,
     * mechanism) are wrong.
     * 
     * All providers are previously removed and 
     * 2 new providers were added.
     */
    public void testCreateServer01() throws SaslException {
        mProv = new Provider[] {
                (new SpiEngUtils()).new MyProvider("MySaslServerProvider1",
                        "Testing provider SaslServerFactory - 1", SRVSSRV
                                .concat("MECH-1"), fServerClass),
                (new SpiEngUtils()).new MyProvider("MySaslServerProvider2",
                        "Testing provider SaslServerFactory - 2", SRVSSRV
                                .concat("MECH-2"), fServerClass) };
        addProviders();

        CallbackHandler cbH = new cbHand();
        try {
            Sasl.createSaslServer(null, null, null, null, cbH);
            fail("NullPointerException should be thrown when mechanisms is null");
        } catch (NullPointerException e) {
        }
        try {
            Sasl.createSaslServer("MECH-2", "protocol", null, null, cbH);
            fail("SaslException should be thrown when CallbackHandler is wrong");
        } catch (SaslException e) {
        }
        cbH = new cbHandN();
        try {
            Sasl.createSaslServer("MECH-1", "protocol", null, null, cbH);
            fail("SaslException should be thrown when mechamisms is wrong");
        } catch (SaslException e) {
        }
        try {
            Sasl.createSaslServer("MECH-2", null, null, null, cbH);
            fail("SaslException should be thrown when protocol is null");
        } catch (SaslException e) {
        }
    }

    /**
     * Test for <code>createSaslServer(String mechanism, 
     *      String protocol, String serverName,
     *      Map prop, CallbackHandler cbh))</code>
     * method Assertions: throws NullPointerException when mechanisms is null;
     * returns null SaslServer.
     * 
     * All providers are previously removed.
     */
    public void testCreateServer02() throws SaslException {
        try {
            Sasl.createSaslServer(null, null, null, null, null);
            fail("NullPointerException should be thrown when mechanisms is null");
        } catch (NullPointerException e) {
        }
        assertNull("Not null result", Sasl.createSaslServer("MECH-999", null,
                null, null, null));
    }
    
    /**
     * Test for <code>createSaslServer(String mechanism, 
     *      String protocol, String serverName,
     *      Map prop, CallbackHandler cbh))</code> method
     *  
     * Assertions: 
     * returns SaslServer;
     * throws SaslServer for MECH-1 mechanism
     * 
     * All providers are previously removed and 
     * 2 new providers were added.
     */
    public void testCreateServer03() throws SaslException {
        mProv = new Provider[] {
                (new SpiEngUtils()).new MyProvider("MySaslServerProvider1",
                        "Testing provider SaslServerFactory - 1", SRVSSRV
                                .concat("MECH-1"), fServerClass),
                (new SpiEngUtils()).new MyProvider("MySaslServerProvider2",
                        "Testing provider SaslServerFactory - 2", SRVSSRV
                                .concat("MECH-2"), fServerClass) };
        addProviders();

        CallbackHandler cbH = new cbHandN();
        SaslServer saslS = Sasl.createSaslServer("MECH-2", "protocol", null,
                null, cbH);
        assertNotNull("Null result", saslS);
        try {
            saslS.unwrap(null, 1, 1);
            fail("SaslException sould be thrown");
        } catch (SaslException e) {
        }
        assertFalse("Incorrect isComplete() result", saslS.isComplete());
        // try to create Server for wrong mechanism
        try {
            saslS = Sasl
                    .createSaslServer("MECH-1", "protocol", null, null, cbH);
            fail("SaslException sould be thrown");
        } catch (SaslException e) {
        }
    }
    
    /**
     * Test for <code>createSaslServer(String mechanism, 
     *      String protocol, String serverName,
     *      Map prop, CallbackHandler cbh))</code> method
     *  
     * Assertions: 
     * returns SaslServer;
     * throws SaslServer for MECH-1 mechanism
     * 
     * All providers are previously removed and 
     * 1 new provider was added.
     */
    public void testCreateServer04() throws SaslException {
        mProv = new Provider[] { (new SpiEngUtils()).new MyProvider(
                "MySaslServerProvider1",
                "Testing provider SaslServerFactory - 1", SRVSSRV
                        .concat("MECH-1"), fServerClass) };
        mProv[0].put(SRVSSRV.concat("MECH-2"), fServerClass);
        addProviders();
        CallbackHandler cbH = new cbHandN();
        SaslServer saslS = Sasl.createSaslServer("MECH-2", "protocol", null,
                null, cbH);
        assertNotNull("Null result for MECH-2", saslS);
        assertFalse("Incorrect isComplete() result", saslS.isComplete());
        // try to create Server for wrong mechanism
        try {
            saslS = Sasl
                    .createSaslServer("MECH-1", "protocol", null, null, cbH);
            fail("SaslException sould be thrown");
        } catch (SaslException e) {
        }
    }

    /**
     * Test for <code>createSaslServer(String mechanism, 
     *      String protocol, String serverName,
     *      Map prop, CallbackHandler cbh))</code> method
     *  
     * Assertions: 
     * return null Server when there is no provider supported some mechanism
     * returns SaslServer when incorrect mechanism is used
     * 
     * All providers are previously removed and 
     * 2 new providers were added.
     */
    public void testCreateServer05() throws SaslException {
        mProv = new Provider[] {
                (new SpiEngUtils()).new MyProvider("MySaslServerProvider1",
                        "Testing provider SaslServerFactory - 1", SRVSSRV
                                .concat("MECH-2"), fServerClass.concat("Ext")),
                (new SpiEngUtils()).new MyProvider("MySaslServerProvider2",
                        "Testing provider SaslServerFactory - 2", SRVSSRV
                                .concat("MECH-1"), fServerClass),
                (new SpiEngUtils()).new MyProvider("MySaslServerProvider3",
                        "Testing provider SaslServerFactory - 3", SRVSSRV
                                .concat("MECH-6"), fServerClass) };
        mProv[2].put(SRVSSRV.concat("MECH-5"), fServerClass);
        addProviders();

        CallbackHandler cbH = new cbHandN();

        SaslServer saslS;
        // try to create SaslServer for wrong mechanism
        // there is no provider supported MECH-77, MECH-66 mechanisms

        assertNull("Not null object was created for wrong mechanism", Sasl
                .createSaslServer("MECH-77", "protocol", null, null, cbH));

        saslS = Sasl.createSaslServer("MECH-2", "protocol", null, null, cbH);
        assertNotNull("Null result for MECH-2", saslS);
        try {
            saslS.unwrap(null, 1, 1);
            fail("SaslException sould be thrown");
        } catch (SaslException e) {
        }
        assertFalse("Incorrect isComplete() result", saslS.isComplete());
        // MECH-1 was defined in some provider but it is supported in another
        // provider
        try {
            Sasl.createSaslServer("MECH-1", "protocol", null, null, cbH);
            fail("SaslException sould be thrown");
        } catch (SaslException e) {
        }
        // MECH-6 and MECH-5 were defined in one provider but they are supported
        // in another provider
        saslS = Sasl.createSaslServer("MECH-6", "protocol", null, null, cbH);
        assertNotNull("Null result for MECH-6", saslS);
        saslS = Sasl.createSaslServer("MECH-5", "protocol", null, null, cbH);
        assertNotNull("Null result for MECH-5", saslS);
    }
}
