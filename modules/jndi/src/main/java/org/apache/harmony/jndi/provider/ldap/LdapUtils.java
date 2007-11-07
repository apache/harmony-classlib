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

import javax.naming.InvalidNameException;
import javax.naming.directory.InvalidSearchFilterException;

import org.apache.harmony.jndi.internal.nls.Messages;
import org.apache.harmony.jndi.provider.ldap.parser.FilterParser;
import org.apache.harmony.jndi.provider.ldap.parser.LdapUrlParser;
import org.apache.harmony.jndi.provider.ldap.parser.ParseException;

public class LdapUtils {

    public static Filter parseFilter(String filter)
            throws InvalidSearchFilterException {
        if (filter == null) {
            // ldap.28=Parameter of filter should not be null
            throw new NullPointerException(Messages.getString("ldap.28")); //$NON-NLS-1$
        }

        FilterParser parser = new FilterParser(filter);
        try {
            return parser.parse();
        } catch (ParseException e) {
            // ldap.29=Invalid search filter
            InvalidSearchFilterException ex = new InvalidSearchFilterException(
                    Messages.getString("ldap.29")); //$NON-NLS-1$
            ex.setRootCause(e);
            throw ex;
        }
    }

    public static LdapUrlParser parserURL(String url, boolean isAllowedQuery)
            throws InvalidNameException {
        if (url == null) {
            // ldap.2B=LDAP URL should not be null
            throw new NullPointerException(Messages.getString("ldap.2B")); //$NON-NLS-1$
        }

        LdapUrlParser parser = new LdapUrlParser(url);
        try {
            parser.parseURL();
        } catch (ParseException e) {
            // ldap.2C=Invalid LDAP URL
            IllegalArgumentException ex = new IllegalArgumentException(Messages
                    .getString("ldap.2C")); //$NON-NLS-1$
            ex.initCause(e);
            throw ex;
        }

        if (!isAllowedQuery
                && (parser.getFilter() != null || parser.getControls() != null)) {
            // ldap.2D=LDAP URL may only contain host, port and dn components
            throw new InvalidNameException(Messages.getString("ldap.2D")); //$NON-NLS-1$
        }

        return parser;
    }
}
