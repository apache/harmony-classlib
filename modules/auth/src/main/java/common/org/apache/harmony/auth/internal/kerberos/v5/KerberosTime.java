/*
 *  Copyright 2006 The Apache Software Foundation or its licensors, as applicable.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package org.apache.harmony.auth.internal.kerberos.v5;

import org.apache.harmony.security.asn1.ASN1GeneralizedTime;
import org.apache.harmony.security.asn1.ASN1Type;

/**
 * KerberosTime type.
 * 
 * @see http://www.ietf.org/rfc/rfc4120.txt
 */
public class KerberosTime {

    private KerberosTime() {
    }

    // TODO: should we create encoder that handles fractional seconds?
    private static final ASN1GeneralizedTime ASN1 = ASN1GeneralizedTime
            .getInstance();

    /**
     * KerberosTime is defined as GeneralizedTime with no fractional seconds
     * 
     * @return ASN.1 decoder/encoder for KerberosTime
     */
    public static ASN1Type getASN1() {
        return ASN1;
    }
}
