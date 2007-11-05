package org.apache.harmony.jndi.provider.ldap.parser;

import javax.naming.directory.SearchControls;

import junit.framework.TestCase;

public class LdapUrlParserTest extends TestCase {
    private static final String TEST_FILE = "/parser/parser.url.test";

    private LdapUrlParser parser;

    private SearchControls controls;

    private org.apache.harmony.jndi.provider.ldap.Filter filter;

    public void test_grammar() throws Exception {
        parser = new LdapUrlParser(getClass().getClassLoader()
                .getResourceAsStream(TEST_FILE));
        parser.test();
    }

    public void test_parseURL() throws Exception {
        parser = new LdapUrlParser("ldap://192.168.1.3:389");
        parser.parseURL();
        assertNull(parser.getBaseObject());
        assertNull(parser.getFilter());
        assertNull(parser.getControls());
        assertEquals("192.168.1.3", parser.getHost());
        assertEquals(389, parser.getPort());

        parser = new LdapUrlParser("ldap://192.168.1.3/o=University,c=US");
        parser.parseURL();
        assertEquals("o=University,c=US", parser.getBaseObject());
        assertNull(parser.getFilter());
        assertNull(parser.getControls());
        assertEquals("192.168.1.3", parser.getHost());

        parser = new LdapUrlParser(
                "ldap://192.168.1.3/o=University,c=US?postalAddress");
        parser.parseURL();
        assertEquals("o=University,c=US", parser.getBaseObject());
        assertEquals("192.168.1.3", parser.getHost());
        assertNotNull(parser.getControls());
        controls = parser.getControls();
        assertEquals(SearchControls.OBJECT_SCOPE, controls.getSearchScope());
        assertEquals(1, controls.getReturningAttributes().length);
        assertEquals("postalAddress", controls.getReturningAttributes()[0]);
        assertNull(parser.getFilter());

        parser = new LdapUrlParser(
                "ldap://192.168.1.3/o=University,c=US?postalAddress,o");
        parser.parseURL();
        assertEquals("o=University,c=US", parser.getBaseObject());
        assertEquals("192.168.1.3", parser.getHost());
        assertNotNull(parser.getControls());
        controls = parser.getControls();
        assertEquals(SearchControls.OBJECT_SCOPE, controls.getSearchScope());
        assertEquals(2, controls.getReturningAttributes().length);
        assertEquals("postalAddress", controls.getReturningAttributes()[0]);
        assertEquals("o", controls.getReturningAttributes()[1]);
        assertNull(parser.getFilter());

        parser = new LdapUrlParser("ldap:///o=University,c=US");
        parser.parseURL();
        assertEquals("o=University,c=US", parser.getBaseObject());
        assertNull(parser.getFilter());
        assertNull(parser.getControls());
        assertNull(parser.getHost());

        parser = new LdapUrlParser("ldap://192.168.1.3/o=University,c=US??sub");
        parser.parseURL();
        assertEquals("o=University,c=US", parser.getBaseObject());
        assertEquals("192.168.1.3", parser.getHost());
        assertNotNull(parser.getControls());
        controls = parser.getControls();
        assertEquals(SearchControls.SUBTREE_SCOPE, controls.getSearchScope());

        parser = new LdapUrlParser(
                "ldap://192.168.1.3/o=University,c=US??wrong");
        try {
            parser.parseURL();
            fail("Should raise ParseException");
        } catch (ParseException e) {
            // expected
        }

        parser = new LdapUrlParser(
                "ldap://192.168.1.3/c=US?o=University?sub?(objectClass=*)");
        parser.parseURL();
        assertEquals("c=US", parser.getBaseObject());
        assertEquals("192.168.1.3", parser.getHost());
        assertNotNull(parser.getControls());
        controls = parser.getControls();
        assertEquals(SearchControls.SUBTREE_SCOPE, controls.getSearchScope());
        assertNotNull(parser.getFilter());

        parser = new LdapUrlParser(
                "ldap://192.168.1.3/c=US?o=University?sub?(objectClass=*)");
        parser.parseURL();
        assertEquals("c=US", parser.getBaseObject());
        assertEquals("192.168.1.3", parser.getHost());
        assertNotNull(parser.getControls());
        controls = parser.getControls();
        assertEquals(SearchControls.SUBTREE_SCOPE, controls.getSearchScope());
        assertNotNull(parser.getFilter());

    }
    
    public void test_parseURL_special_char() throws Exception {
        parser = new LdapUrlParser("ldap:///o=University%20of%20Michigan,c=US");
        parser.parseURL();
        assertEquals("o=University of Michigan,c=US", parser.getBaseObject());

    }
}
