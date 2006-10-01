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
import java.util.Enumeration;

import junit.framework.TestCase;

import org.apache.harmony.auth.tests.support.SpiEngUtils;

/**
 * Test for Sasl class
 * 
 */
public class Sasl2Test extends TestCase {

    private static final String[] mech = { "mechanism", "NEW-MECHANISM", "AA",
            "LONG-LONG-LONG-LONG-LONG-LONG-LONG-LONG-LONG-LONG-LONG-LONG-LONG-LONG-LONG" };

    private static final String CLNTSRV = "SaslClientFactory.";

    private static final String SRVSSRV = "SaslServerFactory.";

    private static final String fClientClass01 = "javax.security.sasl.myClientFactory01";

    private static final String fServerClass01 = "javax.security.sasl.myServerFactory01";

    private static final String fServerClass02 = "javax.security.sasl.myServerFactory02";
    
    Provider[] mProv;

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
        if (mProv != null) {
            for (Provider element : mProv) {
                Security.removeProvider(element.getName());
            }
        }
    }

    private void addProviders() {
        for (Provider element : mProv) {
            Security.insertProviderAt(element, 2);
        }
    }

    /**
     * Test for <code>getSaslClientFactories()</code> method 
     * 
     * Assertion: returns enumeration of factories for producing SaslClient.
     * 
     * Enumeration consists of 4 elements.
     * 
     * 4 different providers define the same mechanism and refer to the same
     * SaslClientFactory class
     */
    public void testGetClient01() throws SaslException {
        mProv = new Provider[] {
                (new SpiEngUtils()).new MyProvider("MySaslClientProvider1",
                        "Testing provider SaslClientFactory - 1", CLNTSRV
                                .concat(mech[0]), fClientClass01),
                (new SpiEngUtils()).new MyProvider("MySaslClientProvider2",
                        "Testing provider SaslClientFactory - 2", CLNTSRV
                                .concat(mech[0]), fClientClass01),
                (new SpiEngUtils()).new MyProvider("MySaslClientProvider3",
                        "Testing provider SaslClientFactory - 3", CLNTSRV
                                .concat(mech[0]), fClientClass01),
                (new SpiEngUtils()).new MyProvider("MySaslClientProvider4",
                        "Testing provider SaslClientFactory - 4", CLNTSRV
                                .concat(mech[0]), fClientClass01) };

        addProviders();

        Enumeration<?> en = Sasl.getSaslClientFactories();
        assertNotNull("List of SaslClientFactories should not be null", en);
        assertTrue("List of SaslClientFactories should have elements", en
                .hasMoreElements());
        myClientFactory01 mm = new myClientFactory01();
        String[] mech01 = mm.getMechanismNames(null);
        int l = 0;
        while (en.hasMoreElements()) {
            SaslClientFactory f = (SaslClientFactory) en.nextElement();
            if (f instanceof myClientFactory01) {
                l++;
                assertNull("Incorect SaslClient", f.createSaslClient(null,
                        null, null, null, null, null));
                String[] mech00 = f.getMechanismNames(null);
                assertEquals("Wrong length", mech00.length, mech01.length);
                for (int i = 0; i < mech00.length; i++) {
                    assertEquals("Wrong mechanism name", mech00[i], mech01[i]);
                }
            }
        }
        assertEquals("Incorrect number of enumeration elements", l,
                mProv.length);
    }

    /**
     * Test for <code>getSaslClientFactories()</code> method 
     * 
     * Assertion: returns enumeration of factories for producing SaslClient.
     * 
     * Enumeration consists of 4 elements.
     * 
     * 4 different providers define different mechanism and refer to the same
     * SaslClientFactory class
     */
    public void testGetClient02() throws SaslException {
        mProv = new Provider[] {
                (new SpiEngUtils()).new MyProvider("MySaslClientProvider1",
                        "Testing provider SaslClientFactory - 1", CLNTSRV
                                .concat(mech[0]), fClientClass01),
                (new SpiEngUtils()).new MyProvider("MySaslClientProvider2",
                        "Testing provider SaslClientFactory - 2", CLNTSRV
                                .concat(mech[1]), fClientClass01),
                (new SpiEngUtils()).new MyProvider("MySaslClientProvider3",
                        "Testing provider SaslClientFactory - 3", CLNTSRV
                                .concat(mech[2]), fClientClass01),
                (new SpiEngUtils()).new MyProvider("MySaslClientProvider4",
                        "Testing provider SaslClientFactory - 4", CLNTSRV
                                .concat(mech[3]), fClientClass01) };
        addProviders();

        Enumeration<?> en = Sasl.getSaslClientFactories();
        assertNotNull("List of SaslClientFactories should not be null", en);
        assertTrue("List of SaslClientFactories should have elements", en
                .hasMoreElements());
        myClientFactory01 mm = new myClientFactory01();
        String[] mech01 = mm.getMechanismNames(null);
        int l = 0;
        while (en.hasMoreElements()) {
            SaslClientFactory f = (SaslClientFactory) en.nextElement();
            if (f instanceof myClientFactory01) {
                l++;
                assertNull("Incorect SaslClient", f.createSaslClient(null,
                        null, null, null, null, null));
                String[] mech00 = f.getMechanismNames(null);
                assertEquals("Wrong length", mech00.length, mech01.length);
                for (int i = 0; i < mech00.length; i++) {
                    assertEquals("Wrong mechanism name", mech00[i], mech01[i]);
                }
            }
        }
        assertEquals("Incorrect number of enumeration elements", l,
                mProv.length);
    }

    /**
     * Test for <code>getSaslClientFactories()</code> method 
     * 
     * Assertion: returns enumeration of factories for producing SaslClient.
     * 
     * Enumeration consists of 2 elements.
     * 
     * 2 different providers define the same mechanism and refer to the
     * different SaslClientFactory classes
     */
    public void testGetClient03() throws SaslException {
        mProv = new Provider[] {
                (new SpiEngUtils()).new MyProvider("MySaslClientProvider1",
                        "Testing provider SaslClientFactory - 1", CLNTSRV
                                .concat(mech[1]), fClientClass01),
                (new SpiEngUtils()).new MyProvider("MySaslClientProvider2",
                        "Testing provider SaslClientFactory - 2", CLNTSRV
                                .concat(mech[1]), fClientClass01) };
        addProviders();
        Enumeration<?> en = Sasl.getSaslClientFactories();
        assertNotNull("List of SaslClientFactories should not be null", en);
        assertTrue("List of SaslClientFactories should have elements", en
                .hasMoreElements());

        myClientFactory01 mm1 = new myClientFactory01();
        String[] mech01 = mm1.getMechanismNames(null);
        int l = 0;
        while (en.hasMoreElements()) {
            SaslClientFactory f = (SaslClientFactory) en.nextElement();
            if (f instanceof myClientFactory01) {
                l++;
                assertTrue(f instanceof myClientFactory01);
                assertNull("Incorect SaslClient", f.createSaslClient(null,
                        null, null, null, null, null));
                String[] mech00 = f.getMechanismNames(null);
                assertEquals("Wrong length", mech00.length, mech01.length);
                for (int i = 0; i < mech00.length; i++) {
                    assertEquals("Wrong mechanism name", mech00[i], mech01[i]);
                }
            } else if (f instanceof myClientFactory02) {
                l++;
                try {
                    f.createSaslClient(null, null, null, null, null, null);
                    fail("SaslException should be thrown");
                } catch (SaslException e) {
                }
                assertNull("Incorect SaslClient", f.createSaslClient(
                        new String[0], null, null, null, null, null));
                String[] mech00 = f.getMechanismNames(null);
                assertEquals("Wrong length", mech00.length, mech01.length);
                for (int i = 0; i < mech00.length; i++) {
                    assertEquals("Wrong mechanism name", mech00[i], mech01[i]);
                }
            }
        }
        assertEquals("Incorrect number of enumeration elements", l,
                mProv.length);
    }

    /**
     * Test for <code>getSaslClientFactories()</code> method 
     * 
     * Assertion: returns enumeration of factories for producing SaslClient.
     * 
     * Enumeration consists of 4 elements.
     * 
     * 1 provider defines different mechanism and refer to the same
     * SaslClientFactory classes
     */
    public void testGetClient04() throws SaslException {
        mProv = new Provider[] { (new SpiEngUtils()).new MyProvider(
                "MySaslClientProvider1",
                "Testing provider SaslClientFactory - 1", CLNTSRV
                        .concat(mech[0]), fClientClass01) };
        mProv[0].put(CLNTSRV.concat(mech[1]), fClientClass01);
        mProv[0].put(CLNTSRV.concat(mech[2]), fClientClass01);
        mProv[0].put(CLNTSRV.concat(mech[3]), fClientClass01);

        addProviders();
        Enumeration<?> en = Sasl.getSaslClientFactories();
        assertNotNull("List of SaslClientFactories should not be null", en);
        assertTrue("List of SaslClientFactories should have elements", en
                .hasMoreElements());
        myClientFactory01 mm = new myClientFactory01();
        String[] mech01 = mm.getMechanismNames(null);
        int l = 0;
        while (en.hasMoreElements()) {
            SaslClientFactory f = (SaslClientFactory) en.nextElement();
            if ((f instanceof myClientFactory01)) {
                l++;
                assertNull("Incorect SaslClient", f.createSaslClient(null,
                        null, null, null, null, null));
                String[] mech = f.getMechanismNames(null);
                assertEquals("Wrong length", mech.length, mech01.length);
                for (int i = 0; i < mech.length; i++) {
                    assertEquals("Wrong mechanism name", mech[i], mech01[i]);
                }
            }
        }
        assertEquals("Incorrect number of enumeration elements", l,
                mProv.length);
    }

    /**
     * Test for <code>getSaslServerFactories()</code> method 
     * 
     * Assertion: returns enumeration of factories for producing SaslServer.
     * 
     * Enumeration consists of 8 elements.
     * 
     * 4 different providers define different mechanism and refer to the 2
     * different SaslServerFactory classes
     */
    public void testGetServer01() throws SaslException {
        mProv = new Provider[] {
                (new SpiEngUtils()).new MyProvider("MySaslServerProvider1",
                        "Testing provider SaslServerFactory - 1", SRVSSRV
                                .concat(mech[0]), fServerClass02),
                (new SpiEngUtils()).new MyProvider("MySaslServerProvider2",
                        "Testing provider SaslServerFactory - 2", SRVSSRV
                                .concat(mech[0]), fServerClass02),
                (new SpiEngUtils()).new MyProvider("MySaslServerProvider3",
                        "Testing provider SaslServerFactory - 3", SRVSSRV
                                .concat(mech[0]), fServerClass02),
                (new SpiEngUtils()).new MyProvider("MySaslServerProvider4",
                        "Testing provider SaslServerFactory - 4", SRVSSRV
                                .concat(mech[0]), fServerClass02) };
        for (Provider element : mProv) {
            for (int j = 1; j < mech.length; j++) {
                element.put(SRVSSRV.concat(mech[j]).concat(mech[j]),
                        fServerClass02);
                element.put(SRVSSRV.concat(mech[j]).concat(mech[j]),
                        fServerClass01);
            }
            element.put(SRVSSRV.concat(mech[0]).concat(mech[0]),
                    fServerClass01);
        }
        addProviders();
        Enumeration<?> en = Sasl.getSaslServerFactories();
        assertNotNull("List of SaslServerFactories should not be null", en);
        assertTrue("List of SaslServerFactories should have elements", en
                .hasMoreElements());
        myServerFactory01 mm01 = new myServerFactory01();
        String[] mech01 = mm01.getMechanismNames(null);
        int l = 0;
        while (en.hasMoreElements()) {
            SaslServerFactory f = (SaslServerFactory) en.nextElement();
            if (f instanceof myServerFactory02) {
                l++;
                try {
                    f.createSaslServer(null, null, null, null, null);
                    fail("SaslException should be thrown");
                } catch (SaslException e) {
                }
                assertNull("Incorect SaslClient", f.createSaslServer(null, "a",
                        null, null, null));
                assertNull("Wrong length", f.getMechanismNames(null));
            } else if (f instanceof myServerFactory01) {
                l++;
                assertNull("Not null SaslServer", f.createSaslServer(null,
                        null, null, null, null));
                String[] mech00 = f.getMechanismNames(null);
                assertEquals("Wrong length", mech00.length, mech01.length);
                for (int i = 0; i < mech00.length; i++) {
                    assertEquals("Wrong mechanism name", mech00[i], mech01[i]);
                }
            }
        }
        assertEquals("Incorrect number of enumeration elements", l,
                mProv.length * 2);
    }

    /**
     * Test for <code>getSaslServerFactories()</code> method 
     * 
     * Assertion: returns enumeration of factories for producing SaslServer.
     * 
     * Enumeration consists of 4 elements.
     * 
     * 4 different providers define different mechanism and refer to the same
     * SaslServerFactory class
     */
    public void testGetServer02() throws SaslException {
        mProv = new Provider[] {
                (new SpiEngUtils()).new MyProvider("MySaslServerProvider1",
                        "Testing provider SaslServerFactory - 1", SRVSSRV
                                .concat(mech[0]), fServerClass01),
                (new SpiEngUtils()).new MyProvider("MySaslServerProvider2",
                        "Testing provider SaslServerFactory - 2", SRVSSRV
                                .concat(mech[1]), fServerClass01),
                (new SpiEngUtils()).new MyProvider("MySaslServerProvider3",
                        "Testing provider SaslServerFactory - 3", SRVSSRV
                                .concat(mech[2]), fServerClass01),
                (new SpiEngUtils()).new MyProvider("MySaslServerProvider4",
                        "Testing provider SaslServerFactory - 4", SRVSSRV
                                .concat(mech[3]), fServerClass01) };
        addProviders();

        Enumeration<?> en = Sasl.getSaslServerFactories();
        assertNotNull("List of SaslServerFactories should not be null", en);
        assertTrue("List of SaslServerFactories should have elements", en
                .hasMoreElements());
        myServerFactory01 mm01 = new myServerFactory01();
        String[] mech01 = mm01.getMechanismNames(null);
        int l = 0;
        while (en.hasMoreElements()) {
            SaslServerFactory f = (SaslServerFactory) en.nextElement();
            if (f instanceof myServerFactory01) {
                l++;
                assertNull("Incorect SaslServer", f.createSaslServer(null,
                        null, null, null, null));
                String[] mech00 = f.getMechanismNames(null);
                assertEquals("Wrong length", mech00.length, mech01.length);
                for (int i = 0; i < mech00.length; i++) {
                    assertEquals("Wrong mechanism name", mech00[i], mech01[i]);
                }
            }
        }
        assertEquals("Incorrect number of enumeration elements", l,
                mProv.length);
    }

    /**
     * Test for <code>getSaslServerFactories()</code> method 
     * 
     * Assertion: returns enumeration of factories for producing SaslServer.
     * 
     * Enumeration consists of 2 elements.
     * 
     * 2 different providers define the same mechanism and refer to the
     * different SaslServerFactory classes
     */
    public void testGetServer03() throws SaslException {
        mProv = new Provider[] {
                (new SpiEngUtils()).new MyProvider("MySaslServerProvider1",
                        "Testing provider SaslServerFactory - 1", SRVSSRV
                                .concat(mech[1]), fServerClass01),
                (new SpiEngUtils()).new MyProvider("MySaslServerProvider2",
                        "Testing provider SaslServerFactory - 2", SRVSSRV
                                .concat(mech[1]), fServerClass01) };
        addProviders();
        Enumeration<?> en = Sasl.getSaslServerFactories();
        assertNotNull("List of SaslServerFactories should not be null", en);
        assertTrue("List of SaslServerFactories should have elements", en
                .hasMoreElements());

        myServerFactory01 mm1 = new myServerFactory01();
        String[] mech01 = mm1.getMechanismNames(null);
        int l = 0;
        while (en.hasMoreElements()) {
            SaslServerFactory f = (SaslServerFactory) en.nextElement();
            if (f instanceof myServerFactory01) {
                l++;
                assertTrue(f instanceof myServerFactory01);
                assertNull("Incorect SaslServer", f.createSaslServer(null,
                        null, null, null, null));
                String[] mech00 = f.getMechanismNames(null);
                assertEquals("Wrong length", mech00.length, mech01.length);
                for (int i = 0; i < mech00.length; i++) {
                    assertEquals("Wrong mechanism name", mech00[i], mech01[i]);
                }
            } else if (f instanceof myServerFactory02) {
                l++;
                try {
                    f.createSaslServer(null, null, null, null, null);
                    fail("SaslException should be thrown");
                } catch (SaslException e) {
                }
                assertNull("Incorect SaslServer", f.createSaslServer(null, "",
                        null, null, null));
                String[] mech00 = f.getMechanismNames(null);
                assertEquals("Wrong length", mech00.length, mech01.length);
                for (int i = 0; i < mech00.length; i++) {
                    assertEquals("Wrong mechanism name", mech00[i], mech01[i]);
                }
            }
        }
        assertEquals("Incorrect number of enumeration elements", l,
                mProv.length);
    }

    /**
     * Test for <code>getSaslServerFactories()</code> method 
     * 
     * Assertion: returns enumeration of factories for producing SaslServert.
     * 
     * Enumeration consists of 4 elements.
     * 
     * 1 provider defines different mechanism and refer to the same
     * SaslServerFactory classes
     */
    public void testGetServer04() throws SaslException {
        mProv = new Provider[] { (new SpiEngUtils()).new MyProvider(
                "MySaslServerProvider1",
                "Testing provider SaslServerFactory - 1", SRVSSRV
                        .concat(mech[0]), fServerClass02) };
        mProv[0].put(SRVSSRV.concat(mech[1]), fServerClass02);
        mProv[0].put(SRVSSRV.concat(mech[2]), fServerClass02);
        mProv[0].put(SRVSSRV.concat(mech[3]), fServerClass02);

        addProviders();
        Enumeration<?> en = Sasl.getSaslServerFactories();
        assertNotNull("List of SaslServerFactories should not be null", en);
        assertTrue("List of SaslServerFactories should have elements", en
                .hasMoreElements());
        int l = 0;
        while (en.hasMoreElements()) {
            SaslServerFactory f = (SaslServerFactory) en.nextElement();
            if ((f instanceof myServerFactory02)) {
                l++;
                try {
                    f.createSaslServer(null, null, null, null, null);
                    fail("SaslException should be thrown");
                } catch (SaslException e) {
                }
                assertNull("Incorect SaslServer", f.createSaslServer(null, "",
                        null, null, null));
                assertNull("Wrong length", f.getMechanismNames(null));
            }
        }
        assertEquals("Incorrect number of enumeration elements", l,
                mProv.length);
    }

}
