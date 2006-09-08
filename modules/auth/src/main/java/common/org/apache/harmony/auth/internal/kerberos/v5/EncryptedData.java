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

import java.io.IOException;

import org.apache.harmony.security.asn1.ASN1Explicit;
import org.apache.harmony.security.asn1.ASN1Integer;
import org.apache.harmony.security.asn1.ASN1OctetString;
import org.apache.harmony.security.asn1.ASN1Sequence;
import org.apache.harmony.security.asn1.ASN1Type;
import org.apache.harmony.security.asn1.BerInputStream;

/**
 * TODO comment me
 * 
 * @see http://www.ietf.org/rfc/rfc4120.txt
 */
public class EncryptedData {

    private final int etype;

    private final int kvno;

    private final byte[] cipher;

    private EncryptedData(int etype, int kvno, byte[] cipher) {
        this.etype = etype;
        this.kvno = kvno;
        this.cipher = cipher;
    }

    public int getEtype() {
        return etype;
    }

    public int getKvno() {
        return kvno;
    }

    public byte[] getCipher() {
        return cipher;
    }

    //
    // EncryptedData   ::= SEQUENCE {
    //    etype   [0] Int32 -- EncryptionType --,
    //    kvno    [1] UInt32 OPTIONAL,
    //    cipher  [2] OCTET STRING -- ciphertext
    // }
    //
    static final ASN1Sequence ASN1 = new ASN1Sequence(new ASN1Type[] {
    // TODO should we define Int32 type?
            new ASN1Explicit(0, ASN1Integer.getInstance()), // etype
            // TODO should we define UInt32 type?
            new ASN1Explicit(1, ASN1Integer.getInstance()), // kvno
            // cipher
            new ASN1Explicit(2, ASN1OctetString.getInstance()), }) {
        {
            setOptional(1); // kvno
        }

        protected Object getDecodedObject(BerInputStream in) throws IOException {

            Object[] values = (Object[]) in.content;

            return new EncryptedData(ASN1Integer.toIntValue(values[0]),
                    ASN1Integer.toIntValue(values[1]), (byte[]) values[2]);
        }

        protected void getValues(Object object, Object[] values) {
            throw new RuntimeException(); //FIXME message
        }
    };
}
