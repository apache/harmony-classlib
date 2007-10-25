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

import org.apache.harmony.jndi.provider.ldap.asn1.ASN1Decodable;
import org.apache.harmony.jndi.provider.ldap.asn1.ASN1Encodable;
import org.apache.harmony.jndi.provider.ldap.asn1.LdapASN1Constant;
import org.apache.harmony.jndi.provider.ldap.asn1.Utils;
import org.apache.harmony.jndi.provider.ldap.asn1.ASN1ChoiceWrap.ChosenValue;
import org.apache.harmony.security.asn1.ASN1Integer;

/**
 * Ldap Bind operation. Refer to
 * {@link http://www.rfc-editor.org/rfc/rfc2251.txt} for detailed information
 * 
 */
final public class BindOp implements LdapOperation, ASN1Encodable,
        ASN1Decodable {

    private int version = DEFAULT_VERSION;

    private static final int DEFAULT_VERSION = 3;

    private String name;

    private AuthenticationChoice authentication;

    final public static class SaslCredentials implements ASN1Encodable {

        private String mechanism;

        private String credentials;

        public void encodeValues(Object[] values) {
            values[0] = Utils.getBytes(mechanism);
            values[1] = Utils.getBytes(credentials);
        }

        public void setMechanism(String mechanism) {
            this.mechanism = mechanism;
        }

        public void setCredentials(String credentials) {
            this.credentials = credentials;
        }

    }

    final public static class AuthenticationChoice implements ASN1Encodable {

        public AuthenticationChoice(int index, SaslCredentials sasl) {
            this.index = index;
            this.sasl = sasl;
            this.value = new Object[2];
            sasl.encodeValues((Object[]) value);
        }

        public AuthenticationChoice(int index, String password) {
            this.index = index;
            this.password = password;
            this.value = Utils.getBytes(this.password);
        }

        private int index;

        private Object value;

        private SaslCredentials sasl;

        private String password;

        public void encodeValues(Object[] values) {
            values[0] = new ChosenValue(index, value);

        }

        public int getIndex() {
            return index;
        }

        public SaslCredentials getSasl() {
            return sasl;
        }

    }

    private LdapResult result;

    private byte[] response; // response from previous negotiation

    public BindOp(int version, String dn) {
        this.version = version;
        this.name = dn;
    }

    public BindOp(String dn, String pwd) {
        this.name = dn;
        this.authentication = new AuthenticationChoice(0, pwd);
        this.response = null;
    }

    public BindOp(String dn, String pwd, String saslMechanism, byte[] res) {
        this.name = dn;
        SaslCredentials sasl = new SaslCredentials();
        sasl.setMechanism(saslMechanism);
        sasl.setCredentials(pwd);
        this.authentication = new AuthenticationChoice(1, sasl);
        this.response = res;
    }

    public LdapResult getResult() {
        return result;
    }

    public ASN1Encodable getRequest() {

        return this;
    }

    public ASN1Decodable getResponse() {

        return this;
    }

    public int getRequestId() {
        return LdapASN1Constant.OP_BIND_REQUEST;
    }

    public int getResponseId() {
        return LdapASN1Constant.OP_BIND_RESPONSE;
    }

    public void encodeValues(Object[] values) {
        values[0] = ASN1Integer.fromIntValue(version);
        values[1] = Utils.getBytes(name);
        Object[] auth = new Object[1];
        // TODO: encoding AuthenticationChoice
        authentication.encodeValues(auth);
        values[2] = auth[0];
    }

    public void decodeValues(Object[] values) {
        result = new LdapResult();
        result.decodeValues(values);
        if (values[4] != null) {
            authentication.getSasl().setCredentials(
                    Utils.getString((byte[]) values[4]));
        }
    }
}
