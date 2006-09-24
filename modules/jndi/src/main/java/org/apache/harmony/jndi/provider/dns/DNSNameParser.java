/*
 * Copyright 2005-2006 The Apache Software Foundation or its licensors, as applicable
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

/**
 * @author Alexei Y. Zakharov
 * @version $Revision: 1.1.2.4 $
 */

package org.apache.harmony.jndi.provider.dns;

import java.util.StringTokenizer;
import javax.naming.InvalidNameException;
import javax.naming.Name;
import javax.naming.NameParser;

/**
 * DNS name parser
 * @author Alexei Zakharov
 * @version $Revision: 1.1.2.4 $
 */
public class DNSNameParser implements NameParser {

    /**
     * Constructs a new name parser.
     */
    public DNSNameParser() {}

    /**
     * Parses string representation of DNS name. Following situations will be
     * treated as an error:
     * <ul>
     * <li>The length of the whole name is longer than 255 characters</li>
     * <li>The length of each label is more than 63 characters</li>
     * <li>more than one null label encountered or null label is not the least
     * specific label (the rightmost)</li> 
     * </ul>
     * @param name string representation of DNS name
     * @return new instance of <code>DNSName</code> class
     * @throws InvalidNameException if given string is not a correct DNS name
     * @see javax.naming.NameParser#parse(java.lang.String)
     * @see RFC 1034
     */
    public Name parse(String name) throws InvalidNameException {
        StringTokenizer st;
        boolean lastTokenWasDilim = false;
        DNSName dnsName = new DNSName();

        if (name == null) {
            throw new InvalidNameException("Given name is null");
        }
        if (name.length() > 255) {
            throw new InvalidNameException("The length of the name is more" +
                    " than 255 characters");
        }
        st = new StringTokenizer(name, ".", true);
        while (st.hasMoreTokens()) {
            String comp = st.nextToken();

            if (comp.equals(".")) {
                if (lastTokenWasDilim) {
                    // two delimiters one after another
                    throw new InvalidNameException(
                            "Null label is not the rightmost one");
                }
                lastTokenWasDilim = true;
                if (dnsName.size() == 0 && st.hasMoreTokens()) {
                    throw new InvalidNameException("DNS name shouldn't " +
                            "start with a dot"); 
                }
            }
            else {
                if (comp.length() > 63) {
                    throw new InvalidNameException("The length of " +
                            comp + " label is more than 63 characters");
                }
                dnsName.add(0, comp);
                lastTokenWasDilim = false;
            }
        }
        if (lastTokenWasDilim) {
            dnsName.add(0, "");
        }
        return dnsName;
    }

    /**
     * @param obj the object to compare with
     * @return <code>true</code> if and only if the given object is instance of
     * class <code>DNSParser</code>; otherwise returns <code>false</code>.
     */
    @Override
    public boolean equals(Object obj) {
        if (obj != null && obj instanceof DNSNameParser) {
            return true;
        }
        return false;
    }
}
