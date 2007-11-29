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

package org.apache.harmony.jndi.provider.ldap;

import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.directory.InitialDirContext;
import javax.naming.ldap.Control;
import javax.naming.ldap.InitialLdapContext;
import javax.naming.ldap.LdapContext;
import javax.naming.ldap.SortControl;

import junit.framework.TestCase;

import org.apache.harmony.jndi.provider.ldap.asn1.LdapASN1Constant;
import org.apache.harmony.jndi.provider.ldap.mock.BindResponse;
import org.apache.harmony.jndi.provider.ldap.mock.EncodableLdapResult;
import org.apache.harmony.jndi.provider.ldap.mock.MockLdapServer;

public class LdapContextServerMockedTest extends TestCase {
    private MockLdapServer server;

    private Hashtable<Object, Object> env = new Hashtable<Object, Object>();

    @Override
    public void setUp() {
        server = new MockLdapServer();
        server.start();
        env.put(Context.INITIAL_CONTEXT_FACTORY,
                "org.apache.harmony.jndi.provider.ldap.LdapContextFactory");
        env.put(Context.PROVIDER_URL, server.getURL());
    }

    @Override
    public void tearDown() {
        server.stop();
    }

    public void testRequestControls() throws Exception {

        server.setResponseSeq(new LdapMessage[] { new LdapMessage(
                LdapASN1Constant.OP_BIND_RESPONSE, new BindResponse(), null) });

        InitialLdapContext initialContext = new InitialLdapContext(env, null);

        Control[] reqCtls = initialContext.getRequestControls();
        assertEquals(1, reqCtls.length);
        assertEquals("2.16.840.1.113730.3.4.2", reqCtls[0].getID());
        assertEquals(Control.NONCRITICAL, reqCtls[0].isCritical());

        initialContext.setRequestControls(new Control[] { new SortControl("",
                Control.NONCRITICAL) });

        reqCtls = initialContext.getRequestControls();
        assertEquals(2, reqCtls.length);
        Control control = reqCtls[0];
        if (control instanceof SortControl) {
            assertEquals(Control.NONCRITICAL, reqCtls[0].isCritical());
            assertEquals("2.16.840.1.113730.3.4.2", reqCtls[1].getID());
            assertEquals(Control.NONCRITICAL, reqCtls[1].isCritical());
        } else {
            assertEquals("2.16.840.1.113730.3.4.2", control.getID());
            assertEquals(Control.NONCRITICAL, control.isCritical());
            assertTrue(reqCtls[1] instanceof SortControl);
            assertEquals(Control.NONCRITICAL, reqCtls[1].isCritical());
        }

        server.setResponseSeq(new LdapMessage[] { new LdapMessage(
                LdapASN1Constant.OP_SEARCH_RESULT_DONE,
                new EncodableLdapResult(), null) });

        LdapContext context = (LdapContext) initialContext.lookup("");
        // request controls are not inherited
        reqCtls = context.getRequestControls();
        assertEquals(1, reqCtls.length);
        assertEquals("2.16.840.1.113730.3.4.2", reqCtls[0].getID());
        assertEquals(Control.NONCRITICAL, reqCtls[0].isCritical());

        server.setResponseSeq(new LdapMessage[] { new LdapMessage(
                LdapASN1Constant.OP_BIND_RESPONSE, new BindResponse(), null) });
        context = context.newInstance(new Control[] { new SortControl("",
                Control.NONCRITICAL) });
        reqCtls = context.getRequestControls();

        assertEquals(2, reqCtls.length);
        control = reqCtls[0];
        if (control instanceof SortControl) {
            assertEquals(Control.NONCRITICAL, reqCtls[0].isCritical());
            assertEquals("2.16.840.1.113730.3.4.2", reqCtls[1].getID());
            assertEquals(Control.NONCRITICAL, reqCtls[1].isCritical());
        } else {
            assertEquals("2.16.840.1.113730.3.4.2", control.getID());
            assertEquals(Control.NONCRITICAL, control.isCritical());
            assertTrue(reqCtls[1] instanceof SortControl);
            assertEquals(Control.NONCRITICAL, reqCtls[1].isCritical());
        }
    }

    public void testConnectControls() throws Exception {

        server.setResponseSeq(new LdapMessage[] { new LdapMessage(
                LdapASN1Constant.OP_BIND_RESPONSE, new BindResponse(), null) });

        InitialDirContext initialDirContext = new InitialDirContext(env);

        server.setResponseSeq(new LdapMessage[] { new LdapMessage(
                LdapASN1Constant.OP_SEARCH_RESULT_DONE,
                new EncodableLdapResult(), null) });
        LdapContext context = (LdapContext) initialDirContext.lookup("");

        assertNull(context.getConnectControls());

        server.setResponseSeq(new LdapMessage[] { new LdapMessage(
                LdapASN1Constant.OP_BIND_RESPONSE, new BindResponse(), null) });
        context.reconnect(new Control[] { new SortControl("",
                Control.NONCRITICAL) });

        Control[] controls = context.getConnectControls();
        assertNotNull(controls);
        assertEquals(1, controls.length);
        Control c = controls[0];
        assertTrue(c instanceof SortControl);
        assertEquals(Control.NONCRITICAL, c.isCritical());

        server.setResponseSeq(new LdapMessage[] { new LdapMessage(
                LdapASN1Constant.OP_SEARCH_RESULT_DONE,
                new EncodableLdapResult(), null) });
        context = (LdapContext) context.lookup("");

        // connect controls are inherited
        controls = context.getConnectControls();
        assertNotNull(controls);
        assertEquals(1, controls.length);
        c = controls[0];
        assertTrue(c instanceof SortControl);
        assertEquals(Control.NONCRITICAL, c.isCritical());

    }

    public void testConnectControls2() throws Exception {
        // set connect controls by property "java.naming.ldap.control.connect"
        env.put("java.naming.ldap.control.connect",
                new Control[] { new SortControl("", Control.NONCRITICAL) });

        server.setResponseSeq(new LdapMessage[] { new LdapMessage(
                LdapASN1Constant.OP_BIND_RESPONSE, new BindResponse(), null) });

        InitialDirContext initialDirContext = new InitialDirContext(env);

        server.setResponseSeq(new LdapMessage[] { new LdapMessage(
                LdapASN1Constant.OP_SEARCH_RESULT_DONE,
                new EncodableLdapResult(), null) });
        LdapContext context = (LdapContext) initialDirContext.lookup("");

        Control[] controls = context.getConnectControls();
        assertNotNull(controls);
        assertEquals(1, controls.length);
        Control c = controls[0];
        assertTrue(c instanceof SortControl);
        assertEquals(Control.NONCRITICAL, c.isCritical());
    }

    public void testConnectControls3() throws Exception {
        // set connect controls by InitialLdapContext
        server.setResponseSeq(new LdapMessage[] { new LdapMessage(
                LdapASN1Constant.OP_BIND_RESPONSE, new BindResponse(), null) });
        InitialLdapContext initialDirContext = new InitialLdapContext(env,
                new Control[] { new SortControl("", Control.NONCRITICAL) });

        server.setResponseSeq(new LdapMessage[] { new LdapMessage(
                LdapASN1Constant.OP_SEARCH_RESULT_DONE,
                new EncodableLdapResult(), null) });
        LdapContext context = (LdapContext) initialDirContext.lookup("");

        Control[] controls = context.getConnectControls();
        assertNotNull(controls);
        assertEquals(1, controls.length);
        Control c = controls[0];
        assertTrue(c instanceof SortControl);
        assertEquals(Control.NONCRITICAL, c.isCritical());

    }

    public void testnewInstance() throws Exception {
        server.setResponseSeq(new LdapMessage[] { new LdapMessage(
                LdapASN1Constant.OP_BIND_RESPONSE, new BindResponse(), null) });
        InitialLdapContext initialDirContext = new InitialLdapContext(env, null);
        Control[] reqCtls = initialDirContext.getRequestControls();
        assertEquals(1, reqCtls.length);
        assertEquals("2.16.840.1.113730.3.4.2", reqCtls[0].getID());
        assertEquals(Control.NONCRITICAL, reqCtls[0].isCritical());

        server.setResponseSeq(new LdapMessage[] { new LdapMessage(
                LdapASN1Constant.OP_BIND_RESPONSE, new BindResponse(), null) });
        LdapContext context = initialDirContext
                .newInstance(new Control[] { new SortControl("",
                        Control.NONCRITICAL) });

        assertNotSame(initialDirContext, context);
        reqCtls = context.getRequestControls();
        assertEquals(2, reqCtls.length);
        Control control = reqCtls[0];
        if (control instanceof SortControl) {
            assertEquals(Control.NONCRITICAL, reqCtls[0].isCritical());
            assertEquals("2.16.840.1.113730.3.4.2", reqCtls[1].getID());
            assertEquals(Control.NONCRITICAL, reqCtls[1].isCritical());
        } else {
            assertEquals("2.16.840.1.113730.3.4.2", control.getID());
            assertEquals(Control.NONCRITICAL, control.isCritical());
            assertTrue(reqCtls[1] instanceof SortControl);
            assertEquals(Control.NONCRITICAL, reqCtls[1].isCritical());
        }

    }
}
