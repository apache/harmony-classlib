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

import org.apache.harmony.security.asn1.ASN1Boolean;
import org.apache.harmony.security.asn1.ASN1Constants;
import org.apache.harmony.security.asn1.ASN1Enumerated;
import org.apache.harmony.security.asn1.ASN1Implicit;
import org.apache.harmony.security.asn1.ASN1Integer;
import org.apache.harmony.security.asn1.ASN1OctetString;
import org.apache.harmony.security.asn1.ASN1Sequence;
import org.apache.harmony.security.asn1.ASN1SequenceOf;
import org.apache.harmony.security.asn1.ASN1SetOf;
import org.apache.harmony.security.asn1.ASN1Type;

/**
 * This class contains all ASN.1 type defined in RFC 2251.
 */
public class LdapASN1Constant {

    public static final int OP_BIND_REQUEST = 0;

    public static final int OP_BIND_RESPONSE = 1;

    // FIXME change them to appropriate index number in the future.
    public static final int OP_ADD_REQUEST = 2;
    
    public static final int OP_ADD_RESPONSE = 3;
    
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
    
    public static final ASN1Type SaslCredentials = new ASN1SequenceWrap(
            new ASN1Type[] { ASN1OctetString.getInstance(), // mechanism
                    ASN1OctetString.getInstance() }) { // credentials
        {
            setOptional(1); // credentials is optional
        }
    };
    
    public static final ASN1Type AuthenticationChoice = new ASN1ChoiceWrap(
            new ASN1Type[] {
                    new ASN1Implicit(ASN1Constants.CLASS_CONTEXTSPECIFIC, 0, // simple
                            ASN1OctetString.getInstance()),
                    new ASN1Implicit(ASN1Constants.CLASS_CONTEXTSPECIFIC, 3, // sasl
                            SaslCredentials) });
    
    public static final ASN1Type LDAPResult = new ASN1SequenceWrap(
            new ASN1Type[] { ASN1Enumerated.getInstance(), // resultCode
                    ASN1OctetString.getInstance(), // matchedDN
                    ASN1OctetString.getInstance(), // errorMessage
                    new ASN1Implicit(ASN1Constants.CLASS_CONTEXTSPECIFIC, 3, // referral
                            new ASN1SequenceOf(ASN1OctetString.getInstance())) }) {
        {
            setOptional(3); // referral is optional
        }
    };
    
    public static final ASN1Type AddResponse = new ASN1Implicit(
            ASN1Constants.CLASS_APPLICATION, 9, LDAPResult);
    
    public static final ASN1Type Control = new ASN1SequenceWrap(new ASN1Type[] {
            ASN1OctetString.getInstance(), // controlType
            ASN1Boolean.getInstance(), // criticality
            ASN1OctetString.getInstance() }) { // controlValue
        {
            setDefault(Boolean.FALSE, 1); // criticality default false
            setOptional(2); // controlValue is optional
        }
    };
    
    public static final ASN1Type BindRequest = new ASN1Implicit(
            ASN1Constants.CLASS_APPLICATION, 0, new ASN1SequenceWrap(
                    new ASN1Type[] { ASN1Integer.getInstance(), // version
                            ASN1OctetString.getInstance(), // name
                            AuthenticationChoice })); // authentication
    
    public static final ASN1Type BindResponse = new ASN1Implicit(
            ASN1Constants.CLASS_APPLICATION, 1, Utils.conjoinSequence(
                    (ASN1Sequence) LDAPResult, // result
                    new ASN1SequenceWrap(new ASN1Type[] { new ASN1Implicit(
                            ASN1Constants.CLASS_CONTEXTSPECIFIC, 7, // serverSaslCreds
                            ASN1OctetString.getInstance()) }) {
                        {
                            setOptional(0); // serverSaslCreds is optional
                        }
                    }));
    
    public static final ASN1Type LDAPMessage = new ASN1SequenceWrap(
            new ASN1Type[] {
                    ASN1Integer.getInstance(),
                    new ASN1ChoiceWrap(new ASN1Type[] { BindRequest,
                            BindResponse,
                            AddRequest,
                            AddResponse,
                            }),
                    new ASN1SequenceOf(Control) }) {
        {
            setOptional(2);
        }
    };
    

}
