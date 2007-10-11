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
import java.util.List;

import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.Attribute;
import javax.naming.directory.BasicAttribute;
import javax.naming.directory.DirContext;

import org.apache.harmony.jndi.provider.ldap.asn1.ASN1Decodable;
import org.apache.harmony.jndi.provider.ldap.asn1.ASN1Encodable;
import org.apache.harmony.jndi.provider.ldap.asn1.Utils;

/**
 * This class add supports to <code>getAttributeDefinition()</code> and
 * <code>getAttributeSyntaxDefinition()</code> methods.
 * 
 */
public class LdapAttribute extends BasicAttribute implements ASN1Decodable,
        ASN1Encodable {

    private static final long serialVersionUID = -6492847268062616321L;

    /**
     * TODO: when to initialize it?
     */
    private DirContext attributeDefinition = null;

    /**
     * TODO: when to initialize it?
     */
    private DirContext attributeSyntaxDefinition = null;

    /**
     * constructor for LdapAttribute
     * 
     */
    protected LdapAttribute() {
        super("", false); //$NON-NLS-1$
    }

    public LdapAttribute(String id) {
        super(id, false);
    }

    /**
     * Constructs instance from already existing <code>Attribute</code>
     * 
     * @param attr
     * @throws NamingException
     */
    public LdapAttribute(Attribute attr) throws NamingException {
        super(attr.getID(), attr.isOrdered());
        NamingEnumeration<?> values = attr.getAll();
        while (values.hasMore()) {
            add(values.next());
        }

        attributeDefinition = attr.getAttributeDefinition();
        attributeSyntaxDefinition = attr.getAttributeSyntaxDefinition();
    }

    @SuppressWarnings("unchecked")
    public void decodeValues(Object[] values) {
        byte[] type = (byte[]) values[0];
        attrID = Utils.getString(type);
        Collection<byte[]> list = (Collection<byte[]>) values[1];
        for (byte[] bs : list) {
            // TODO: convert to corresponding java type according to schema
            add(Utils.getString(bs));
        }

    }

    public void encodeValues(Object[] values) {
        values[0] = Utils.getBytes(attrID);

        List<Object> list = new ArrayList<Object>(this.values.size());

        for (Object object : this.values) {
            // TODO: convert other types to byte[]
            if (object instanceof String) {
                String str = (String) object;
                object = Utils.getBytes(str);
            }

            list.add(object);
        }
        values[1] = list;
    }

    @Override
    public DirContext getAttributeDefinition() throws NamingException {
        return attributeDefinition;
    }

    @Override
    public DirContext getAttributeSyntaxDefinition() throws NamingException {
        return attributeSyntaxDefinition;
    }
}
