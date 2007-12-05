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
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Set;

import javax.naming.InvalidNameException;
import javax.naming.Name;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.Attributes;
import javax.naming.directory.BasicAttributes;
import javax.naming.directory.SearchResult;

public class LdapSchemaClassDefContextImpl extends LdapContextImpl {
    
    private Hashtable<String, ArrayList<String>> classTree;
    private LdapContextImpl context;
    public LdapSchemaClassDefContextImpl(
            Name name,
            Hashtable<Object, Object> env,
            Hashtable<String, ArrayList<String>> classTree,
            LdapContextImpl ctx) throws InvalidNameException {
        super(ctx, env, "");
        context = ctx;
        this.classTree = classTree;
    }

    public NamingEnumeration<SearchResult> search(Name name,
            Attributes attributes) throws NamingException {
        Set<String> keyset = classTree.keySet();
        LdapNamingEnumeration<SearchResult> enumeration = new LdapNamingEnumeration<SearchResult>(null, null);
        Attributes as = null;
        for (Iterator<String> iter = keyset.iterator(); iter.hasNext();) {
            ArrayList<String> classes = classTree.get(iter.next());
            for (int j = 0; j < classes.size(); j++) {
                String className = classes.get(j);
                Hashtable<String, Object> classDefInfo = context
                        .findSchemaDefInfo(
                                LdapSchemaContextImpl.OBJECT_CLASSES, className
                                        .toLowerCase());
                as = new BasicAttributes();
                Set<String> keySet = classDefInfo.keySet();
                for (Iterator<String> iterator = keySet.iterator(); iterator
                        .hasNext();) {
                    String key = iterator.next();
                    Object value = classDefInfo.get(key);
                    as.put(key, value);
                }
                SearchResult result = new SearchResult(className, null, as);
                enumeration.add(result);
            }
        }
        return enumeration;
    }
    public NamingEnumeration<SearchResult> search(String name,
            Attributes attributes) throws NamingException {
        return search(convertFromStringToName(name), attributes);
    }
}
