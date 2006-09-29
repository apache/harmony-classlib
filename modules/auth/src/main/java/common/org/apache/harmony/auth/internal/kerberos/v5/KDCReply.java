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

package org.apache.harmony.auth.internal.kerberos.v5;

import java.io.IOException;

import org.apache.harmony.security.asn1.ASN1Any;
import org.apache.harmony.security.asn1.ASN1Constants;
import org.apache.harmony.security.asn1.ASN1Explicit;
import org.apache.harmony.security.asn1.ASN1Integer;
import org.apache.harmony.security.asn1.ASN1Sequence;
import org.apache.harmony.security.asn1.ASN1SequenceOf;
import org.apache.harmony.security.asn1.ASN1StringType;
import org.apache.harmony.security.asn1.ASN1Type;
import org.apache.harmony.security.asn1.BerInputStream;

/**
 * TODO comment me
 * 
 * @see http://www.ietf.org/rfc/rfc4120.txt
 */
public class KDCReply {

    /**
     * Authentication Service request message type
     */
    public static final int AS_REP = 11;

    /**
     * Ticket-Granting Service request message type
     */
    public static final int TGS_REP = 13;

    // type of a protocol message: AS_REP or TGS_REP
    private final int msgType;

    private final PrincipalName cname;

    private final String crealm;

    private final Ticket ticket;

    private final EncryptedData encPart;

    private KDCReply(int msgType, String crealm, PrincipalName cname,
            Ticket ticket, EncryptedData encPart) {
        this.msgType = msgType;
        this.cname = cname;
        this.crealm = crealm;
        this.ticket = ticket;
        this.encPart = encPart;
    }

    public int getMsgtype() {
        return msgType;
    }

    public String getCrealm() {
        return crealm;
    }

    public PrincipalName getCname() {
        return cname;
    }

    public Ticket getTicket() {
        return ticket;
    }

    public EncryptedData getEncPart() {
        return encPart;
    }

    //
    // KDC-REP         ::= SEQUENCE {
    //    pvno            [0] INTEGER (5),
    //    msg-type        [1] INTEGER (11 -- AS -- | 13 -- TGS --),
    //    padata          [2] SEQUENCE OF PA-DATA OPTIONAL
    //                            -- NOTE: not empty --,
    //    crealm          [3] Realm,
    //    cname           [4] PrincipalName,
    //    ticket          [5] Ticket,
    //    enc-part        [6] EncryptedData
    //                            -- EncASRepPart or EncTGSRepPart,
    //                            -- as appropriate
    // }
    //
    static final ASN1Sequence KDC_REP_ASN1 = new ASN1Sequence(new ASN1Type[] {
            new ASN1Explicit(0, ASN1Integer.getInstance()), // pvno
            new ASN1Explicit(1, ASN1Integer.getInstance()), // msg-type
            new ASN1Explicit(2, new ASN1SequenceOf(ASN1Any.getInstance())),
            // TODO should we define Realm type?
            new ASN1Explicit(3, ASN1StringType.GENERALSTRING), // crealm
            new ASN1Explicit(4, PrincipalName.ASN1), // cname
            new ASN1Explicit(5, Ticket.TICKET_ASN1), // ticket 
            new ASN1Explicit(6, EncryptedData.ASN1), // enc-part 
    }) {
        {
            setOptional(2); // padata
        }

        protected Object getDecodedObject(BerInputStream in) throws IOException {

            Object[] values = (Object[]) in.content;

            return new KDCReply(ASN1Integer.toIntValue(values[1]),
                    (String) values[3], (PrincipalName) values[4],
                    (Ticket) values[5], (EncryptedData) values[6]);
        }

        protected void getValues(Object object, Object[] values) {
            throw new RuntimeException(); //FIXME message
        }
    };

    public static final ASN1Explicit AS_REP_ASN1 = new ASN1Explicit(
            ASN1Constants.CLASS_APPLICATION, AS_REP, KDC_REP_ASN1);
}
