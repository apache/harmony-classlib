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

import java.util.Collection;

import org.apache.harmony.jndi.provider.ldap.asn1.ASN1Decodable;
import org.apache.harmony.jndi.provider.ldap.asn1.Utils;
import org.apache.harmony.security.asn1.ASN1Integer;

/**
 * This class represents LDAPResult defined in RFC 2251 page 16.
 */
public class LdapResult implements ASN1Decodable {

    private int resultCode;

    private String machedDN;

    private String errorMessage;

    private String[] referrals;

    @SuppressWarnings("unchecked")
    public void decodeValues(Object[] values) {
        resultCode = ASN1Integer.toIntValue(values[0]);
        machedDN = Utils.getString((byte[]) values[1]);
        errorMessage = Utils.getString((byte[]) values[2]);

        if (values[3] != null) {
            Collection<byte[]> list = (Collection<byte[]>) values[3];
            if (list.size() != 0) {
                referrals = new String[list.size()];
                int index = 0;
                for (byte[] bytes : list) {
                    referrals[index++] = Utils.getString(bytes);
                }
            }
        }
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public String getMachedDN() {
        return machedDN;
    }

    /**
     * Retrieves the referrals.
     * 
     * @return A prossibly null array. <code>null</code> means no referrals
     *         retrieved from server
     */
    public String[] getReferrals() {
        return referrals;
    }

    public int getResultCode() {
        return resultCode;
    }

}
