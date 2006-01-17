/*
 *  Copyright 2005 The Apache Software Foundation or its licensors, as applicable.
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

/**
* @author Alexander Y. Kleymenov
* @version $Revision$
*/

package com.openintel.drl.security.x509;

import java.io.IOException;
import java.util.Date;

import org.apache.harmony.security.asn1.ASN1Choice;
import org.apache.harmony.security.asn1.ASN1GeneralizedTime;
import org.apache.harmony.security.asn1.ASN1Type;
import org.apache.harmony.security.asn1.ASN1UTCTime;

import org.apache.harmony.security.asn1.*;

/**
 * Class represents the work with the following X.509 structure:
 * (as specified in RFC 3280 -
 *  Internet X.509 Public Key Infrastructure.
 *  Certificate and Certificate Revocation List (CRL) Profile.
 *  http://www.ietf.org/rfc/rfc3280.txt):
 *
 * <pre>
 * Time ::= CHOICE {
 *       utcTime        UTCTime,
 *       generalTime    GeneralizedTime 
 * }
 * </pre>
 */
public class Time {
    
    public static final ASN1Choice ASN1 = new ASN1Choice(new ASN1Type[] {
            ASN1GeneralizedTime.getInstance(), ASN1UTCTime.getInstance() }) {

        public int getIndex(java.lang.Object object) {
            return 1; // always code as ASN1UTCTime ()
            // FIXME: But it is correct only if the Date to encode 
            // is before 2050. See rfc 3280 p.22
        }

        public Object getObjectToEncode(Object object) {
            return object;
        }
    };
}

