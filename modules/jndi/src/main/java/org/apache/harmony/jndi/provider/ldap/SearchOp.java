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

import java.util.ArrayList;
import java.util.List;

import javax.naming.directory.SearchControls;

import org.apache.harmony.jndi.provider.ldap.asn1.ASN1Decodable;
import org.apache.harmony.jndi.provider.ldap.asn1.ASN1Encodable;
import org.apache.harmony.jndi.provider.ldap.asn1.LdapASN1Constant;
import org.apache.harmony.jndi.provider.ldap.asn1.Utils;
import org.apache.harmony.security.asn1.ASN1Integer;

public class SearchOp implements LdapOperation, ASN1Encodable,
        ASN1Decodable {
    private String baseObject;

    private boolean typesOnly = false;

    private Filter filter;

    private SearchControls controls;

    private LdapSearchResult result = new LdapSearchResult();

    public LdapSearchResult getResult() {
        return result;
    }

    public SearchOp(String baseObject, SearchControls controls,
            Filter filter) {
        this.baseObject = baseObject;
        this.controls = controls;
        this.filter = filter;
    }

    public ASN1Encodable getRequest() {
        return this;
    }

    public int getRequestId() {
        return LdapASN1Constant.OP_SEARCH_REQUEST;
    }

    public ASN1Decodable getResponse() {
        return this;
    }

    public int getResponseId() {
        return LdapASN1Constant.OP_SEARCH_RESULT_DONE;
    }

    public void encodeValues(Object[] values) {
        values[0] = Utils.getBytes(baseObject);
        values[1] = ASN1Integer.fromIntValue(controls.getSearchScope());
        if (controls.getDerefLinkFlag()) {
            // derefAlways
            values[2] = ASN1Integer.fromIntValue(3);
        } else {
            // neverDerefAliases
            values[2] = ASN1Integer.fromIntValue(0);
        }
        values[3] = ASN1Integer.fromIntValue((int) controls.getCountLimit());
        values[4] = ASN1Integer.fromIntValue(controls.getTimeLimit());
        values[5] = Boolean.valueOf(typesOnly);
        values[6] = filter;
        String[] attributes = controls.getReturningAttributes();
        if (attributes == null) {
            attributes = new String[] { "" };
        }

        List<byte[]> list = new ArrayList<byte[]>(attributes.length);
        for (String attribute : attributes) {
            list.add(Utils.getBytes(attribute));
        }
        values[7] = list;

    }

    public void decodeValues(Object[] values) {
        
        result.decodeSearchResponse(values);
    }

}
