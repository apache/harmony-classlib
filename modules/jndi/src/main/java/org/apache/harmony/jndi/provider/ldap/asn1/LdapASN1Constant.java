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

import org.apache.harmony.security.asn1.ASN1Constants;
import org.apache.harmony.security.asn1.ASN1Implicit;
import org.apache.harmony.security.asn1.ASN1OctetString;
import org.apache.harmony.security.asn1.ASN1SequenceOf;
import org.apache.harmony.security.asn1.ASN1SetOf;
import org.apache.harmony.security.asn1.ASN1Type;

/**
 * This class contains all ASN.1 type defined in RFC 2251.
 */
public class LdapASN1Constant {
    public static final int OP_ADD_REQUEST = 9;
    
    public static final int OP_ADD_RESPONSE = 10;
    
    public static final ASN1Type Attribute = new ASN1SequenceWrap(
            new ASN1Type[] { ASN1OctetString.getInstance(), // type
                    new ASN1SetOf(ASN1OctetString.getInstance()) }); // vals
    
    public static final ASN1Type AttributeList = new ASN1SequenceOf(
            new ASN1SequenceWrap(new ASN1Type[] {
                    ASN1OctetString.getInstance(), // type
                    new ASN1SetOf(ASN1OctetString.getInstance()) })); // values
    
    public static final ASN1Type AddRequest = new ASN1Implicit(
            ASN1Constants.CLASS_APPLICATION, 8, new ASN1SequenceWrap(
                    new ASN1Type[] { ASN1OctetString.getInstance(), // entry
                            AttributeList })); // attributes
    

}
