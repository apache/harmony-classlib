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
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.naming.NamingException;
import javax.naming.directory.Attributes;
import javax.naming.directory.BasicAttributes;

import org.apache.harmony.jndi.provider.ldap.asn1.LdapASN1Constant;
import org.apache.harmony.jndi.provider.ldap.asn1.Utils;
import org.apache.harmony.jndi.provider.ldap.asn1.ASN1ChoiceWrap.ChosenValue;

public class LdapSearchResult {

    /**
     * all search result entries
     */
    private Map<String, Attributes> entries = new HashMap<String, Attributes>();

    /**
     * SearchResultReference from server
     */
    private List<String> refURLs;

    private LdapResult result;

    private NamingException ex;

    private String address;

    public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

    public void decodeSearchResponse(Object[] values) {
        ChosenValue chosen = (ChosenValue) values[0];
        switch (chosen.getIndex()) {
        case LdapASN1Constant.OP_SEARCH_RESULT_ENTRY:
            decodeEntry(chosen.getValue());
            break;
        case LdapASN1Constant.OP_SEARCH_RESULT_REF:
            decodeRef(chosen.getValue());
            break;
        case LdapASN1Constant.OP_SEARCH_RESULT_DONE:
            decodeDone(chosen.getValue());
            break;
        }
    }

    protected void decodeDone(Object value) {
        result = new LdapResult();
        result.decodeValues((Object[]) value);
    }

    protected void decodeRef(Object value) {
        if (refURLs == null) {
            refURLs = new ArrayList<String>();
        }

        Collection<byte[]> list = (Collection<byte[]>) value;
        for (byte[] bs : list) {
            refURLs.add(Utils.getString(bs));
        }
    }

    protected void decodeEntry(Object value) {
        Object[] values = (Object[]) value;
        String name = Utils.getString((byte[]) values[0]);

        if (address != null) {
        	name = address + name;
        }

        Attributes attrs = null;

        if (entries.containsKey(name)) {
            attrs = entries.get(name);
        } else {
            attrs = new BasicAttributes();
            entries.put(name, attrs);
        }

        Collection<Object[]> list = (Collection<Object[]>) values[1];
        for (Object[] objects : list) {
            LdapAttribute attr = new LdapAttribute();
            attr.decodeValues(objects);
            attrs.put(attr);
        }
    }

    public Map<String, Attributes> getEntries() {
        return entries;
    }

    public List<String> getRefURLs() {
        return refURLs;
    }

    public LdapResult getResult() {
        return result;
    }

    public NamingException getException() {
        return ex;
    }

    public void setException(NamingException ex) {
        this.ex = ex;
    }

    public boolean isEmpty() {
        return entries.size() == 0 && refURLs.size() == 0;
    }

    public void setRefURLs(List<String> refURLs) {
        this.refURLs = refURLs;
    }
}
