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
* @author Vera Y. Petrashkova
* @version $Revision$
*/

package javax.security.sasl;


import java.security.Provider;
import java.security.Security;
import javax.security.auth.callback.CallbackHandler;

import org.apache.harmony.security.SpiEngUtils;
import junit.framework.TestCase;

import java.util.Enumeration;
import java.util.Map;

/**
 * Test for Sasl class
 * 
 */

public class Sasl2Test extends TestCase {

    public static void main(String[] args) {
        junit.textui.TestRunner.run(Sasl2Test.class);
    }

    Provider[] mProv;

    private static final String fClientClass01 = "javax.security.sasl.myClientFactory01";

    private static final String fServerClass01 = "javax.security.sasl.myServerFactory01";

    private static final String fServerClass02 = "javax.security.sasl.myServerFactory02";

    /*
     * @see TestCase#tearDown()
     */
    protected void tearDown() throws Exception {
        super.tearDown();
        if (mProv != null) {
            for (int i = 0; i < mProv.length; i++) {
                Security.removeProvider(mProv[i].getName());
            }
        }
    }

    /**
     * Constructor for Sasl2Test.
     * 
     * @param arg0
     */
    public Sasl2Test(String arg0) {
        super(arg0);
    }

    private void addProviders() {
        for (int i = 0; i < mProv.length; i++) {
            Security.insertProviderAt(mProv[i], 2);
        }
    }

    private static final String[] mech = { "mechanism", "NEW-MECHANISM", "AA",
            "LONG-LONG-LONG-LONG-LONG-LONG-LONG-LONG-LONG-LONG-LONG-LONG-LONG-LONG-LONG" };

    private static final String CLNTSRV = "SaslClientFactory.";

    private static final String SRVSSRV = "SaslServerFactory.";

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

        Enumeration en = Sasl.getSaslClientFactories();
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

        Enumeration en = Sasl.getSaslClientFactories();
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
        Enumeration en = Sasl.getSaslClientFactories();
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
        Enumeration en = Sasl.getSaslClientFactories();
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
        for (int i = 0; i < mProv.length; i++) {
            for (int j = 1; j < mech.length; j++) {
                mProv[i].put(SRVSSRV.concat(mech[j]).concat(mech[j]),
                        fServerClass02);
                mProv[i].put(SRVSSRV.concat(mech[j]).concat(mech[j]),
                        fServerClass01);
            }
            mProv[i].put(SRVSSRV.concat(mech[0]).concat(mech[0]),
                    fServerClass01);
        }
        addProviders();
        Enumeration en = Sasl.getSaslServerFactories();
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

        Enumeration en = Sasl.getSaslServerFactories();
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
        Enumeration en = Sasl.getSaslServerFactories();
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
        Enumeration en = Sasl.getSaslServerFactories();
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

class myServerFactory01 implements SaslServerFactory {
    public myServerFactory01() {
        super();
    }

    public String[] getMechanismNames(Map map) {
        return new String[] { "aaaa", "dddddddddddd",
                "llllllllll sssssssss aaaaaaaaaaa c" };
    }

    public SaslServer createSaslServer(String mech, String prot,
            String srvName, Map prop, CallbackHandler ch) throws SaslException {
        return null;
    }
}

class myServerFactory02 implements SaslServerFactory {
    public myServerFactory02() {
        super();
    }

    public String[] getMechanismNames(Map map) {
        return null;
    }

    public SaslServer createSaslServer(String mech, String prot,
            String srvName, Map prop, CallbackHandler ch) throws SaslException {
        if (prot == null) {
            throw new SaslException("Protocol is null");
        }
        return null;
    }
}

class myClientFactory01 implements SaslClientFactory {
    public myClientFactory01() {
        super();
    }

    public String[] getMechanismNames(Map map) {
        return new String[] { "a1", "a2", "a3", "a4", "a5" };
    }

    public SaslClient createSaslClient(String[] mech, String prot, String auth,
            String srvName, Map prop, CallbackHandler ch) throws SaslException {
        return null;
    }
}

class myClientFactory02 implements SaslClientFactory {
    public myClientFactory02() {
        super();
    }

    public String[] getMechanismNames(Map map) {
        return new String[] { "a11", "a22", "a33", "a44", "a55", "" };
    }

    public SaslClient createSaslClient(String[] mech, String prot, String auth,
            String srvName, Map prop, CallbackHandler ch) throws SaslException {
        return null;
    }
}
