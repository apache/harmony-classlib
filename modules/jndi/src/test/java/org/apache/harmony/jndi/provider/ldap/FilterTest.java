package org.apache.harmony.jndi.provider.ldap;

import java.util.ArrayList;

import org.apache.harmony.jndi.internal.parser.AttributeTypeAndValuePair;
import org.apache.harmony.jndi.provider.ldap.Filter.SubstringFilter;
import org.apache.harmony.jndi.provider.ldap.asn1.ASN1TestUtils;
import org.apache.harmony.jndi.provider.ldap.asn1.LdapASN1Constant;

import junit.framework.TestCase;

public class FilterTest extends TestCase {
    private Filter filter;

    public void test_constructor_I() {
        filter = new Filter(Filter.APPROX_MATCH_FILTER);
        assertTrue(filter.isLeaf());
        filter = new Filter(Filter.EQUALITY_MATCH_FILTER);
        assertTrue(filter.isLeaf());
        filter = new Filter(Filter.EXTENSIBLE_MATCH_FILTER);
        assertTrue(filter.isLeaf());
        filter = new Filter(Filter.GREATER_OR_EQUAL_FILTER);
        assertTrue(filter.isLeaf());
        filter = new Filter(Filter.LESS_OR_EQUAL_FILTER);
        assertTrue(filter.isLeaf());
        filter = new Filter(Filter.NOT_FILTER);
        assertTrue(filter.isLeaf());
        filter = new Filter(Filter.PRESENT_FILTER);
        assertTrue(filter.isLeaf());
        filter = new Filter(Filter.SUBSTRINGS_FILTER);
        assertTrue(filter.isLeaf());

        filter = new Filter(Filter.AND_FILTER);
        assertFalse(filter.isLeaf());

        filter = new Filter(Filter.OR_FILTER);
        assertFalse(filter.isLeaf());

        try {
            filter = new Filter(-1);
            fail("Should throws IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            // expected
        }

        try {
            filter = new Filter(10);
            fail("Should throws IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            // expected
        }
    }

    public void test_encodeValues() {
        // simple filter
        filter = new Filter(Filter.APPROX_MATCH_FILTER);
        filter.setValue(new AttributeTypeAndValuePair("cn", "test"));
        ASN1TestUtils.checkEncode(filter, LdapASN1Constant.Filter);

        // composite filter
        filter = new Filter(Filter.AND_FILTER);
        Filter equal = new Filter(Filter.EQUALITY_MATCH_FILTER);
        equal.setValue(new AttributeTypeAndValuePair("sn", "tom"));
        filter.addChild(equal);
        
        Filter substring = new Filter(Filter.SUBSTRINGS_FILTER);
        SubstringFilter sub = new SubstringFilter("o");
        sub.addAny("harmony");
        sub.addFinal("good");
        substring.setValue(sub);
        filter.addChild(substring);
        
        Filter present = new Filter(Filter.PRESENT_FILTER);
        present.setValue("objectClass");
        filter.addChild(present);
        
        ASN1TestUtils.checkEncode(filter, LdapASN1Constant.Filter);
        
        // more complex filter
        Filter or = new Filter(Filter.OR_FILTER);
        Filter not = new Filter(Filter.NOT_FILTER);
        Filter greater = new Filter(Filter.GREATER_OR_EQUAL_FILTER);
        greater.setValue(new AttributeTypeAndValuePair("cn", "hello"));
        not.setValue(greater);
        or.addChild(not);
        
        Filter less = new Filter(Filter.LESS_OR_EQUAL_FILTER);
        less.setValue(new AttributeTypeAndValuePair("o", "apache"));
        or.addChild(less);
        filter.addChild(or);
        
        ASN1TestUtils.checkEncode(filter, LdapASN1Constant.Filter);
    }
}
