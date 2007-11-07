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
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.naming.NamingEnumeration;
import javax.naming.directory.Attributes;
import javax.naming.directory.BasicAttributes;
import javax.naming.directory.SearchResult;

import org.apache.harmony.jndi.provider.dns.BasicNamingEnumerator;
import org.apache.harmony.jndi.provider.ldap.asn1.LdapASN1Constant;
import org.apache.harmony.jndi.provider.ldap.asn1.Utils;
import org.apache.harmony.jndi.provider.ldap.asn1.ASN1ChoiceWrap.ChosenValue;

public class LdapSearchResult {

    /**
     * all search result entries
     */
    private Map<String, SearchResult> entries = new HashMap<String, SearchResult>();

    /**
     * SearchResultReference from server
     * TODO: deal with the references
     */
    private List<String> refURLs = new ArrayList<String>();

    private LdapResult result;

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

    private void decodeDone(Object value) {
        result = new LdapResult();
        result.decodeValues((Object[]) value);
    }

    private void decodeRef(Object value) {
        Collection<byte[]> list = (Collection<byte[]>) value;
        for (byte[] bs : list) {
            refURLs.add(Utils.getString(bs));
        }
    }

    private void decodeEntry(Object value) {
        Object[] values = (Object[]) value;
        String name = Utils.getString((byte[]) values[0]);
        Attributes attrs = null;

        if (entries.containsKey(name)) {
            attrs = entries.get(name).getAttributes();
        } else {
            attrs = new BasicAttributes();
            entries.put(name, new SearchResult(name, new Object(), attrs));
        }

        Collection<Object[]> list = (Collection<Object[]>) values[1];
        for (Object[] objects : list) {
            LdapAttribute attr = new LdapAttribute();
            attr.decodeValues(objects);
            attrs.put(attr);
        }
    }

    public NamingEnumeration<SearchResult> getEnumeration() {
        //TODO: this is simple implementation, need to be completed
        return new BasicNamingEnumerator<SearchResult>(
                new Enumeration<SearchResult>() {
                    private ArrayList<SearchResult> values = new ArrayList<SearchResult>(
                            entries.values());

                    private int index = -1;

                    public boolean hasMoreElements() {
                        if (index == -1) {
                            index = 0;
                        }
                        
                        if (index + 1 <= values.size()) {
                            return true;
                        }

                        return false;
                    }

                    public SearchResult nextElement() {
                        return values.get(index++);
                    }
                });
    }

    public List<String> getRefURLs() {
        return refURLs;
    }

    public LdapResult getResult() {
        return result;
    }
}
