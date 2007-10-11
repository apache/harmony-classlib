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
package org.apache.harmony.jndi.provider.ldap.asn1;

import java.lang.reflect.Field;
import java.util.Collection;

import junit.framework.Assert;

import org.apache.harmony.security.asn1.ASN1Boolean;
import org.apache.harmony.security.asn1.ASN1Enumerated;
import org.apache.harmony.security.asn1.ASN1Implicit;
import org.apache.harmony.security.asn1.ASN1Integer;
import org.apache.harmony.security.asn1.ASN1OctetString;
import org.apache.harmony.security.asn1.ASN1SequenceOf;
import org.apache.harmony.security.asn1.ASN1SetOf;
import org.apache.harmony.security.asn1.ASN1Type;
import org.apache.harmony.security.asn1.ASN1ValueCollection;

public class ASN1TestUtils {
    public static void checkEncode(Object value, ASN1Type type) {
        if (type instanceof ASN1Implicit) {
            type = getWrappedType((ASN1Implicit) type);
        }

        if (type instanceof ASN1Integer 
                || type instanceof ASN1Enumerated) {
            Assert.assertTrue(value instanceof byte[]);
            
            Assert.assertFalse("value should not be zero-length byte array",
                    ((byte[]) value).length != 0);
        } else if (type instanceof ASN1Boolean) {
            Assert.assertTrue(value instanceof Boolean);
        } else if (type instanceof ASN1OctetString) {
            Assert.assertTrue(value instanceof byte[]);
        } else if (type instanceof ASN1SequenceWrap) {
            checkEncodeSequence(value, (ASN1SequenceWrap) type);
        } else if (type instanceof ASN1SequenceOf 
                || type instanceof ASN1SetOf) {
            Assert.assertTrue(value instanceof Collection);
            Collection collection = (Collection) value;
            for (Object object : collection) {
                checkEncode(object, ((ASN1ValueCollection) type).type);
            }
        } else {
            Assert.fail("Not supported ASN.1 type");
        }
    }
    
    private static void checkEncodeSequence(Object value, ASN1SequenceWrap type) {
        if (value instanceof Object[]) {
            Object[] objs = (Object[]) value;
            Assert.assertEquals(type.type.length, objs.length);
            for (int i = 0; i < objs.length; i++) {
                if (objs[i] == null && type.OPTIONAL[i]) {
                    continue;
                }
                checkEncode(objs[i], type.type[i]);
            }
        } else if (value instanceof ASN1Encodable) {
            Object[] objs = new Object[type.type.length];
            ((ASN1Encodable) value).encodeValues(objs);
            checkEncodeSequence(objs, type);
        } else {
            Assert
                    .fail("Value for ASN1SequenceWrap should be Object[], "
                            + "or org.apache.harmony.jndi.provider.ldap.asn1.ASN1Encodable");
        }
    }
    
    private static ASN1Type getWrappedType(ASN1Implicit type) {
        try {
            Field field = ASN1Implicit.class.getDeclaredField("type");
            field.setAccessible(true);
            return (ASN1Type) field.get(type);
        } catch (Exception e) {
            // can't reach, unless implement changed
            throw new RuntimeException("Can't get wrapped type.", e);
        } 
    }
}
