package org.apache.harmony.jndi.provider.ldap.sasl;

import java.util.Hashtable;

import javax.naming.AuthenticationNotSupportedException;
import javax.naming.Context;

import junit.framework.TestCase;

public class SaslBindTest extends TestCase {
    private final String DIGEST_MD5 = "DIGEST-MD5";

    private final String CRAM_MD5 = "CRAM-MD5";

    private final String GSSAPI = "GSSAPI";

    private final String EXTERNAL = "EXTERNAL";

    private SaslBind sbind = new SaslBind();

    private Hashtable<String, Object> env = new Hashtable<String, Object>();

    public void setUp() throws Exception {
        super.setUp();

        env.clear();
    }

    public void tearDown() throws Exception {
        super.tearDown();
    }

    public void test_valueAuthMech_none()
            throws AuthenticationNotSupportedException {
        sbind.valueAuthMech(env);
        assertEquals(SaslBind.AuthMech.None, sbind.getAuthMech());

        env.put(Context.SECURITY_CREDENTIALS, "test_credentials");
        sbind.valueAuthMech(env);
        assertEquals(SaslBind.AuthMech.None, sbind.getAuthMech());

        env.clear();
        env.put(Context.SECURITY_AUTHENTICATION, "none");
        env.put(Context.SECURITY_PRINCIPAL, "test_principal");
        env.put(Context.SECURITY_CREDENTIALS, "test_credentials");
        sbind.valueAuthMech(env);
        assertEquals(SaslBind.AuthMech.None, sbind.getAuthMech());

        env.put(Context.SECURITY_AUTHENTICATION, "NoNe");
        assertEquals(SaslBind.AuthMech.None, sbind.getAuthMech());
    }

    public void test_valueAuthMech_simple()
            throws AuthenticationNotSupportedException {
        env.put(Context.SECURITY_PRINCIPAL, "test_principal");
        sbind.valueAuthMech(env);
        assertEquals(SaslBind.AuthMech.Simple, sbind.getAuthMech());

        env.put(Context.SECURITY_AUTHENTICATION, "simple");
        sbind.valueAuthMech(env);
        assertEquals(SaslBind.AuthMech.Simple, sbind.getAuthMech());

        env.put(Context.SECURITY_AUTHENTICATION, "SiMpLe");
        sbind.valueAuthMech(env);
        assertEquals(SaslBind.AuthMech.Simple, sbind.getAuthMech());
    }

    public void test_valueAuthMech_SASL()
            throws AuthenticationNotSupportedException {
        env.put(Context.SECURITY_AUTHENTICATION, DIGEST_MD5);
        sbind.valueAuthMech(env);
        assertEquals(SaslBind.AuthMech.SASL, sbind.getAuthMech());

        env.put(Context.SECURITY_AUTHENTICATION, CRAM_MD5);
        sbind.valueAuthMech(env);
        assertEquals(SaslBind.AuthMech.SASL, sbind.getAuthMech());

        env.put(Context.SECURITY_AUTHENTICATION, GSSAPI);
        sbind.valueAuthMech(env);
        assertEquals(SaslBind.AuthMech.SASL, sbind.getAuthMech());

        env.put(Context.SECURITY_AUTHENTICATION, EXTERNAL);
        sbind.valueAuthMech(env);
        assertEquals(SaslBind.AuthMech.SASL, sbind.getAuthMech());

        env.put(Context.SECURITY_AUTHENTICATION,
                " test test2  EXTERNAL  test3 GSSAPI");
        sbind.valueAuthMech(env);
        assertEquals(SaslBind.AuthMech.SASL, sbind.getAuthMech());
    }

    public void test_valueAuthMech_notSupportException() {
        env.put(Context.SECURITY_AUTHENTICATION, "test");
        try {
            sbind.valueAuthMech(env);
            fail("AuthenticationNotSupportedException expected");
        } catch (AuthenticationNotSupportedException e) {
            // expected
        }

        env.put(Context.SECURITY_AUTHENTICATION, " test test2  test3");
        try {
            sbind.valueAuthMech(env);
            fail("AuthenticationNotSupportedException expected");
        } catch (AuthenticationNotSupportedException e) {
            // expected
        }
    }
}
