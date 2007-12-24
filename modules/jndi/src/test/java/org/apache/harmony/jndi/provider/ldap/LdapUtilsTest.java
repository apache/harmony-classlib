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

import javax.naming.CommunicationException;
import javax.naming.NamingException;
import javax.naming.TimeLimitExceededException;
import javax.naming.directory.InvalidSearchFilterException;

import junit.framework.TestCase;

import org.apache.harmony.jndi.internal.parser.AttributeTypeAndValuePair;
import org.apache.harmony.jndi.provider.ldap.asn1.Utils;
import org.apache.harmony.security.asn1.ASN1Integer;

public class LdapUtilsTest extends TestCase {
    public void test_getExceptionFromResult() {
        String message = "error message";
        LdapResult result = getLdapResult(0, message);
        NamingException ex = LdapUtils.getExceptionFromResult(result);
        assertNull(ex);

        // error code map to CommunicationException
        result = getLdapResult(2, message);
        ex = LdapUtils.getExceptionFromResult(result);
        assertTrue(ex instanceof CommunicationException);
        assertEquals("[LDAP: error code 2 - error message]", ex.getMessage());

        // error code not in map
        result = getLdapResult(100, message);
        ex = LdapUtils.getExceptionFromResult(result);
        assertTrue(ex instanceof NamingException);
        assertEquals("[LDAP: error code 100 - error message]", ex.getMessage());

        // empty error message
        result = getLdapResult(3, "");
        ex = LdapUtils.getExceptionFromResult(result);
        assertTrue(ex instanceof TimeLimitExceededException);
        assertEquals("[LDAP: error code 3]", ex.getMessage());
    }

    public void test_parseFilter() throws Exception {
        try {
            LdapUtils.parseFilter(null, new Object[0]);
            fail("Should throw NullPointerException");
        } catch (NullPointerException e) {
            // expected
        }

        try {
            LdapUtils.parseFilter("object=*", null);
            fail("Should throw InvalidSearchFilterException");
        } catch (InvalidSearchFilterException e) {
            // expected
        }
        

        Filter filter = LdapUtils.parseFilter("(cn={0})", new Object[] { "value" });
        assertEquals(Filter.EQUALITY_MATCH_FILTER, filter.getType());
        assertTrue(filter.getValue() instanceof AttributeTypeAndValuePair);
        AttributeTypeAndValuePair pair = (AttributeTypeAndValuePair) filter.getValue();
        assertEquals("cn", pair.getType());
        assertEquals("value", pair.getValue());

        filter = LdapUtils.parseFilter("(cn=test)", null);
        assertEquals(Filter.EQUALITY_MATCH_FILTER, filter.getType());
        assertTrue(filter.getValue() instanceof AttributeTypeAndValuePair);
        pair = (AttributeTypeAndValuePair) filter.getValue();
        assertEquals("cn", pair.getType());
        assertEquals("test", pair.getValue());
    }

    private LdapResult getLdapResult(int errorCode, String message) {
        LdapResult result = new LdapResult();
        if (message == null) {

        }
        Object[] values = new Object[] { ASN1Integer.fromIntValue(errorCode),
                Utils.getBytes(""), Utils.getBytes(message), null };
        result.decodeValues(values);
        return result;
    }
}