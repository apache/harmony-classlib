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
import java.util.List;
import java.util.Set;

import javax.naming.InvalidNameException;
import javax.naming.Name;
import javax.naming.NamingException;
import javax.naming.directory.Attributes;
import javax.naming.directory.BasicAttribute;
import javax.naming.directory.BasicAttributes;

public class LdapSchemaAttrDefContextImpl extends LdapContextImpl {

    private Hashtable<String, Object> attrTree;

    public LdapSchemaAttrDefContextImpl(Name name,
            Hashtable<Object, Object> env, Hashtable<String, Object> classTree,
            LdapContextImpl ctx) throws InvalidNameException {
        super(ctx, env, "");
        this.attrTree = classTree;
    }

    public Attributes getAttributes(Name name) throws NamingException {
        return getAttributes(name, new String[0]);
    }

    public Attributes getAttributes(Name name, String[] as)
            throws NamingException {
        BasicAttributes attributes = new BasicAttributes();
        Set<String> keySet = attrTree.keySet();
        Iterator<String> iter = keySet.iterator();
        while (iter.hasNext()) {
            String key = iter.next();
            if (key.equals("orig"))
                continue;
            Object value = attrTree.get(key);
            BasicAttribute attr = new BasicAttribute(key);
            if (value instanceof ArrayList) {
                List list = (List) value;
                for (int i = 0; i < list.size(); i++) {
                    attr.add(list.get(i));
                }
            } else {
                attr.add(value);
            }
            attributes.put(attr);
        }
        return attributes;
    }

    public Attributes getAttributes(String s) throws NamingException {
        return getAttributes(convertFromStringToName(s));
    }
}
