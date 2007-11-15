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
import java.util.HashSet;
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
     * whether the value of attribute is binary
     */
    private boolean isBinary;

    private static HashSet<String> BINARY_ATTRIBUTE = new HashSet<String>();
    static {
        BINARY_ATTRIBUTE.add("photo");
        BINARY_ATTRIBUTE.add("personalSignature");
        BINARY_ATTRIBUTE.add("audio");
        BINARY_ATTRIBUTE.add("jpegPhoto");
        BINARY_ATTRIBUTE.add("javaSerializedData");
        BINARY_ATTRIBUTE.add("thumbnailPhoto");
        BINARY_ATTRIBUTE.add("thumbnailLogo");
        BINARY_ATTRIBUTE.add("userPassword");
        BINARY_ATTRIBUTE.add("userCertificate");
        BINARY_ATTRIBUTE.add("cACertificate");
        BINARY_ATTRIBUTE.add("authorityRevocationList");
        BINARY_ATTRIBUTE.add("certificateRevocationList");
        BINARY_ATTRIBUTE.add("crossCertificatePair");
        BINARY_ATTRIBUTE.add("x500UniqueIdentifier");
    }

    /**
     * constructor for decode
     * 
     */
    public LdapAttribute() {
        super("", false); //$NON-NLS-1$
    }

    public LdapAttribute(String id) {
        super(id, false);
        isBinary = isBinary(id);
    }

    /**
     * Constructs instance from already existing <code>Attribute</code>
     * 
     * @param attr
     *            may never be <code>null</code>
     * @throws NamingException
     */
    public LdapAttribute(Attribute attr) throws NamingException {
        super(attr.getID(), attr.isOrdered());
        isBinary = isBinary(getID());
        NamingEnumeration<?> enu = attr.getAll();
        while (enu.hasMore()) {
            Object value = enu.next();
            add(value);
        }

        attributeDefinition = null;
        attributeSyntaxDefinition = null;
    }

    @SuppressWarnings("unchecked")
    public void decodeValues(Object[] vs) {
        byte[] type = (byte[]) vs[0];
        attrID = Utils.getString(type);
        isBinary = isBinary(attrID);
        Collection<byte[]> list = (Collection<byte[]>) vs[1];
        // FIXME: deal with java.naming.ldap.attributes.binary
        if (!isBinary) {
            for (byte[] bs : list) {
                add(Utils.getString(bs));
            }
        } else {
            for (byte[] bs : list) {
                add(bs);
            }
        }

    }

    public void encodeValues(Object[] vs) {
        vs[0] = Utils.getBytes(attrID);

        List<Object> list = new ArrayList<Object>(this.values.size());

        for (Object object : this.values) {
            if (!isBinary && object instanceof String) {
                String str = (String) object;
                object = Utils.getBytes(str);
            }

            list.add(object);
        }
        vs[1] = list;
    }

    @Override
    public DirContext getAttributeDefinition() throws NamingException {
        if (attributeDefinition != null) {
            return attributeDefinition;
        }
        // TODO: Not yet implemented
        throw new NotYetImplementedException();

    }

    @Override
    public DirContext getAttributeSyntaxDefinition() throws NamingException {
        if (attributeSyntaxDefinition != null) {
            return attributeSyntaxDefinition;
        }
        // TODO: Not yet implemented
        throw new NotYetImplementedException();
    }

    private static boolean isBinary(String name) {
        return BINARY_ATTRIBUTE.contains(name) || name.endsWith(";binary");
    }
}
